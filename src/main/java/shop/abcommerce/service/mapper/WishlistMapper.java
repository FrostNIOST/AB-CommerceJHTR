package shop.abcommerce.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.Product;
import shop.abcommerce.domain.Wishlist;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.dto.ProductDTO;
import shop.abcommerce.service.dto.WishlistDTO;

/**
 * Mapper for the entity {@link Wishlist} and its DTO {@link WishlistDTO}.
 */
@Mapper(componentModel = "spring")
public interface WishlistMapper extends EntityMapper<WishlistDTO, Wishlist> {
    @Mapping(target = "account", source = "account", qualifiedByName = "accountUId")
    @Mapping(target = "productses", source = "productses", qualifiedByName = "productProductNameSet")
    WishlistDTO toDto(Wishlist s);

    @Mapping(target = "removeProducts", ignore = true)
    Wishlist toEntity(WishlistDTO wishlistDTO);

    @Named("accountUId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUDTO toDtoAccountUId(AccountU accountU);

    @Named("productProductName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productName", source = "productName")
    ProductDTO toDtoProductProductName(Product product);

    @Named("productProductNameSet")
    default Set<ProductDTO> toDtoProductProductNameSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductProductName).collect(Collectors.toSet());
    }
}
