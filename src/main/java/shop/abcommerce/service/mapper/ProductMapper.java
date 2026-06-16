package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.Product;
import shop.abcommerce.domain.SubCategory;
import shop.abcommerce.service.dto.ProductDTO;
import shop.abcommerce.service.dto.SubCategoryDTO;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "subCategory", source = "subCategory", qualifiedByName = "subCategorySubCategoryName")
    ProductDTO toDto(Product s);

    @Named("subCategorySubCategoryName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "subCategoryName", source = "subCategoryName")
    SubCategoryDTO toDtoSubCategorySubCategoryName(SubCategory subCategory);
}
