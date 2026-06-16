package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.Address;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.dto.AddressDTO;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
    @Mapping(target = "account", source = "account", qualifiedByName = "accountUId")
    AddressDTO toDto(Address s);

    @Named("accountUId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUDTO toDtoAccountUId(AccountU accountU);
}
