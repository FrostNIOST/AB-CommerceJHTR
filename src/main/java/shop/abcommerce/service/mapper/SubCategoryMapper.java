package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.Category;
import shop.abcommerce.domain.SubCategory;
import shop.abcommerce.service.dto.CategoryDTO;
import shop.abcommerce.service.dto.SubCategoryDTO;

/**
 * Mapper for the entity {@link SubCategory} and its DTO {@link SubCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface SubCategoryMapper extends EntityMapper<SubCategoryDTO, SubCategory> {
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryCategoryName")
    SubCategoryDTO toDto(SubCategory s);

    @Named("categoryCategoryName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "categoryName", source = "categoryName")
    CategoryDTO toDtoCategoryCategoryName(Category category);
}
