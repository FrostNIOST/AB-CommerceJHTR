package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.Order;
import shop.abcommerce.domain.OrderItem;
import shop.abcommerce.domain.Product;
import shop.abcommerce.service.dto.OrderDTO;
import shop.abcommerce.service.dto.OrderItemDTO;
import shop.abcommerce.service.dto.ProductDTO;

/**
 * Mapper for the entity {@link OrderItem} and its DTO {@link OrderItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productProductName")
    @Mapping(target = "order", source = "order", qualifiedByName = "orderId")
    OrderItemDTO toDto(OrderItem s);

    @Named("productProductName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productName", source = "productName")
    ProductDTO toDtoProductProductName(Product product);

    @Named("orderId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderDTO toDtoOrderId(Order order);
}
