package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.DocumentType;
import shop.abcommerce.service.dto.DocumentTypeDTO;

/**
 * Mapper for the entity {@link DocumentType} and its DTO {@link DocumentTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentTypeMapper extends EntityMapper<DocumentTypeDTO, DocumentType> {}
