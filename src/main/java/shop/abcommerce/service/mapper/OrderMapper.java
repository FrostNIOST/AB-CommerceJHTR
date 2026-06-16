package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.Address;
import shop.abcommerce.domain.Order;
import shop.abcommerce.domain.PaymentMethod;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.dto.AddressDTO;
import shop.abcommerce.service.dto.OrderDTO;
import shop.abcommerce.service.dto.PaymentMethodDTO;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Mapping(target = "paymentMethod", source = "paymentMethod", qualifiedByName = "paymentMethodNamePaymentMethod")
    @Mapping(target = "shippingAddress", source = "shippingAddress", qualifiedByName = "addressAdress")
    @Mapping(target = "account", source = "account", qualifiedByName = "accountUId")
    OrderDTO toDto(Order s);

    @Named("paymentMethodNamePaymentMethod")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "namePaymentMethod", source = "namePaymentMethod")
    PaymentMethodDTO toDtoPaymentMethodNamePaymentMethod(PaymentMethod paymentMethod);

    @Named("addressAdress")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "adress", source = "adress")
    AddressDTO toDtoAddressAdress(Address address);

    @Named("accountUId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUDTO toDtoAccountUId(AccountU accountU);
}
