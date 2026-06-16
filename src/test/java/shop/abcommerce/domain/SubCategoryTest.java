package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.CategoryTestSamples.*;
import static shop.abcommerce.domain.ProductTestSamples.*;
import static shop.abcommerce.domain.SubCategoryTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class SubCategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubCategory.class);
        SubCategory subCategory1 = getSubCategorySample1();
        SubCategory subCategory2 = new SubCategory();
        assertThat(subCategory1).isNotEqualTo(subCategory2);

        subCategory2.setId(subCategory1.getId());
        assertThat(subCategory1).isEqualTo(subCategory2);

        subCategory2 = getSubCategorySample2();
        assertThat(subCategory1).isNotEqualTo(subCategory2);
    }

    @Test
    void productsTest() {
        SubCategory subCategory = getSubCategoryRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        subCategory.addProducts(productBack);
        assertThat(subCategory.getProductses()).containsOnly(productBack);
        assertThat(productBack.getSubCategory()).isEqualTo(subCategory);

        subCategory.removeProducts(productBack);
        assertThat(subCategory.getProductses()).doesNotContain(productBack);
        assertThat(productBack.getSubCategory()).isNull();

        subCategory.productses(new HashSet<>(Set.of(productBack)));
        assertThat(subCategory.getProductses()).containsOnly(productBack);
        assertThat(productBack.getSubCategory()).isEqualTo(subCategory);

        subCategory.setProductses(new HashSet<>());
        assertThat(subCategory.getProductses()).doesNotContain(productBack);
        assertThat(productBack.getSubCategory()).isNull();
    }

    @Test
    void categoryTest() {
        SubCategory subCategory = getSubCategoryRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        subCategory.setCategory(categoryBack);
        assertThat(subCategory.getCategory()).isEqualTo(categoryBack);

        subCategory.category(null);
        assertThat(subCategory.getCategory()).isNull();
    }
}
