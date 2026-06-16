package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.CategoryTestSamples.*;
import static shop.abcommerce.domain.SubCategoryTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class CategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category.class);
        Category category1 = getCategorySample1();
        Category category2 = new Category();
        assertThat(category1).isNotEqualTo(category2);

        category2.setId(category1.getId());
        assertThat(category1).isEqualTo(category2);

        category2 = getCategorySample2();
        assertThat(category1).isNotEqualTo(category2);
    }

    @Test
    void subCategoriesTest() {
        Category category = getCategoryRandomSampleGenerator();
        SubCategory subCategoryBack = getSubCategoryRandomSampleGenerator();

        category.addSubCategories(subCategoryBack);
        assertThat(category.getSubCategorieses()).containsOnly(subCategoryBack);
        assertThat(subCategoryBack.getCategory()).isEqualTo(category);

        category.removeSubCategories(subCategoryBack);
        assertThat(category.getSubCategorieses()).doesNotContain(subCategoryBack);
        assertThat(subCategoryBack.getCategory()).isNull();

        category.subCategorieses(new HashSet<>(Set.of(subCategoryBack)));
        assertThat(category.getSubCategorieses()).containsOnly(subCategoryBack);
        assertThat(subCategoryBack.getCategory()).isEqualTo(category);

        category.setSubCategorieses(new HashSet<>());
        assertThat(category.getSubCategorieses()).doesNotContain(subCategoryBack);
        assertThat(subCategoryBack.getCategory()).isNull();
    }
}
