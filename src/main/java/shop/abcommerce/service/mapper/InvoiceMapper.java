package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.Invoice;
import shop.abcommerce.domain.Order;
import shop.abcommerce.service.dto.InvoiceDTO;
import shop.abcommerce.service.dto.OrderDTO;

/**
 * Mapper for the entity {@link Invoice} and its DTO {@link InvoiceDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper extends EntityMapper<InvoiceDTO, Invoice> {
    @Mapping(target = "order", source = "order", qualifiedByName = "orderOrderNumber")
    InvoiceDTO toDto(Invoice s);

    @Named("orderOrderNumber")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderNumber", source = "orderNumber")
    OrderDTO toDtoOrderOrderNumber(Order order);
}
