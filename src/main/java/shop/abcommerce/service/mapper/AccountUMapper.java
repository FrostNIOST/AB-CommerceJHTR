package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.DocumentType;
import shop.abcommerce.domain.User;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.dto.DocumentTypeDTO;
import shop.abcommerce.service.dto.UserDTO;

/**
 * Mapper for the entity {@link AccountU} and its DTO {@link AccountUDTO}.
 */
@Mapper(componentModel = "spring")
public interface AccountUMapper extends EntityMapper<AccountUDTO, AccountU> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeDocumentType")
    AccountUDTO toDto(AccountU s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("documentTypeDocumentType")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "documentType", source = "documentType")
    DocumentTypeDTO toDtoDocumentTypeDocumentType(DocumentType documentType);
}
