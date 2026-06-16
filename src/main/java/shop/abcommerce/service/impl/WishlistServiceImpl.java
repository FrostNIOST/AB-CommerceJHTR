package shop.abcommerce.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.abcommerce.domain.Wishlist;
import shop.abcommerce.repository.WishlistRepository;
import shop.abcommerce.service.WishlistService;
import shop.abcommerce.service.dto.WishlistDTO;
import shop.abcommerce.service.mapper.WishlistMapper;

/**
 * Service Implementation for managing {@link shop.abcommerce.domain.Wishlist}.
 */
@Service
public class WishlistServiceImpl implements WishlistService {

    private static final Logger LOG = LoggerFactory.getLogger(WishlistServiceImpl.class);

    private final WishlistRepository wishlistRepository;

    private final WishlistMapper wishlistMapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public WishlistDTO save(WishlistDTO wishlistDTO) {
        LOG.debug("Request to save Wishlist : {}", wishlistDTO);
        Wishlist wishlist = wishlistMapper.toEntity(wishlistDTO);
        wishlist = wishlistRepository.save(wishlist);
        return wishlistMapper.toDto(wishlist);
    }

    @Override
    public WishlistDTO update(WishlistDTO wishlistDTO) {
        LOG.debug("Request to update Wishlist : {}", wishlistDTO);
        Wishlist wishlist = wishlistMapper.toEntity(wishlistDTO);
        wishlist = wishlistRepository.save(wishlist);
        return wishlistMapper.toDto(wishlist);
    }

    @Override
    public Optional<WishlistDTO> partialUpdate(WishlistDTO wishlistDTO) {
        LOG.debug("Request to partially update Wishlist : {}", wishlistDTO);

        return wishlistRepository
            .findById(wishlistDTO.getId())
            .map(existingWishlist -> {
                wishlistMapper.partialUpdate(existingWishlist, wishlistDTO);

                return existingWishlist;
            })
            .map(wishlistRepository::save)
            .map(wishlistMapper::toDto);
    }

    @Override
    public Page<WishlistDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Wishlists");
        return wishlistRepository.findAll(pageable).map(wishlistMapper::toDto);
    }

    public Page<WishlistDTO> findAllWithEagerRelationships(Pageable pageable) {
        return wishlistRepository.findAllWithEagerRelationships(pageable).map(wishlistMapper::toDto);
    }

    @Override
    public Optional<WishlistDTO> findOne(String id) {
        LOG.debug("Request to get Wishlist : {}", id);
        return wishlistRepository.findOneWithEagerRelationships(id).map(wishlistMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete Wishlist : {}", id);
        wishlistRepository.deleteById(id);
    }
}
