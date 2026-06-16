package shop.abcommerce.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.abcommerce.domain.CartItem;
import shop.abcommerce.repository.CartItemRepository;
import shop.abcommerce.service.CartItemService;
import shop.abcommerce.service.dto.CartItemDTO;
import shop.abcommerce.service.mapper.CartItemMapper;

/**
 * Service Implementation for managing {@link shop.abcommerce.domain.CartItem}.
 */
@Service
public class CartItemServiceImpl implements CartItemService {

    private static final Logger LOG = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private final CartItemRepository cartItemRepository;

    private final CartItemMapper cartItemMapper;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartItemDTO save(CartItemDTO cartItemDTO) {
        LOG.debug("Request to save CartItem : {}", cartItemDTO);
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public CartItemDTO update(CartItemDTO cartItemDTO) {
        LOG.debug("Request to update CartItem : {}", cartItemDTO);
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public Optional<CartItemDTO> partialUpdate(CartItemDTO cartItemDTO) {
        LOG.debug("Request to partially update CartItem : {}", cartItemDTO);

        return cartItemRepository
            .findById(cartItemDTO.getId())
            .map(existingCartItem -> {
                cartItemMapper.partialUpdate(existingCartItem, cartItemDTO);

                return existingCartItem;
            })
            .map(cartItemRepository::save)
            .map(cartItemMapper::toDto);
    }

    @Override
    public Page<CartItemDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all CartItems");
        return cartItemRepository.findAll(pageable).map(cartItemMapper::toDto);
    }

    public Page<CartItemDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cartItemRepository.findAllWithEagerRelationships(pageable).map(cartItemMapper::toDto);
    }

    @Override
    public Optional<CartItemDTO> findOne(String id) {
        LOG.debug("Request to get CartItem : {}", id);
        return cartItemRepository.findOneWithEagerRelationships(id).map(cartItemMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete CartItem : {}", id);
        cartItemRepository.deleteById(id);
    }
}
