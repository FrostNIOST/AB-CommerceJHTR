package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.Category;
import shop.abcommerce.service.dto.CategoryDTO;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {}
