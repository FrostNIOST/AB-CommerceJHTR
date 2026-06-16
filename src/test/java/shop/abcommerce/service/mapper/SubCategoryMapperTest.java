package shop.abcommerce.service.mapper;

import static shop.abcommerce.domain.SubCategoryAsserts.*;
import static shop.abcommerce.domain.SubCategoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubCategoryMapperTest {

    private SubCategoryMapper subCategoryMapper;

    @BeforeEach
    void setUp() {
        subCategoryMapper = new SubCategoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSubCategorySample1();
        var actual = subCategoryMapper.toEntity(subCategoryMapper.toDto(expected));
        assertSubCategoryAllPropertiesEquals(expected, actual);
    }
}
