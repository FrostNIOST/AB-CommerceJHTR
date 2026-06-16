package shop.abcommerce.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.Cart;
import shop.abcommerce.domain.CartItem;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.dto.CartDTO;
import shop.abcommerce.service.dto.CartItemDTO;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {
    @Mapping(target = "account", source = "account", qualifiedByName = "accountUId")
    @Mapping(target = "itemses", source = "itemses", qualifiedByName = "cartItemIdSet")
    CartDTO toDto(Cart s);

    @Mapping(target = "removeItems", ignore = true)
    Cart toEntity(CartDTO cartDTO);

    @Named("accountUId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUDTO toDtoAccountUId(AccountU accountU);

    @Named("cartItemId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CartItemDTO toDtoCartItemId(CartItem cartItem);

    @Named("cartItemIdSet")
    default Set<CartItemDTO> toDtoCartItemIdSet(Set<CartItem> cartItem) {
        return cartItem.stream().map(this::toDtoCartItemId).collect(Collectors.toSet());
    }
}
