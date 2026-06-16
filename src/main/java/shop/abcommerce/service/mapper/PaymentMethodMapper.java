package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.PaymentMethod;
import shop.abcommerce.service.dto.PaymentMethodDTO;

/**
 * Mapper for the entity {@link PaymentMethod} and its DTO {@link PaymentMethodDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentMethodMapper extends EntityMapper<PaymentMethodDTO, PaymentMethod> {}
