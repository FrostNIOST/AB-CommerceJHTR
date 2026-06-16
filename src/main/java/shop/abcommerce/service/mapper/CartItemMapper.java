package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.CartItem;
import shop.abcommerce.domain.Product;
import shop.abcommerce.service.dto.CartItemDTO;
import shop.abcommerce.service.dto.ProductDTO;

/**
 * Mapper for the entity {@link CartItem} and its DTO {@link CartItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartItemMapper extends EntityMapper<CartItemDTO, CartItem> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productProductName")
    CartItemDTO toDto(CartItem s);

    @Named("productProductName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productName", source = "productName")
    ProductDTO toDtoProductProductName(Product product);
}
