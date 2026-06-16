package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.ProductTestSamples.*;
import static shop.abcommerce.domain.SubCategoryTestSamples.*;
import static shop.abcommerce.domain.WishlistTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = getProductSample1();
        Product product2 = new Product();
        assertThat(product1).isNotEqualTo(product2);

        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);

        product2 = getProductSample2();
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    void subCategoryTest() {
        Product product = getProductRandomSampleGenerator();
        SubCategory subCategoryBack = getSubCategoryRandomSampleGenerator();

        product.setSubCategory(subCategoryBack);
        assertThat(product.getSubCategory()).isEqualTo(subCategoryBack);

        product.subCategory(null);
        assertThat(product.getSubCategory()).isNull();
    }

    @Test
    void wishlistsTest() {
        Product product = getProductRandomSampleGenerator();
        Wishlist wishlistBack = getWishlistRandomSampleGenerator();

        product.addWishlists(wishlistBack);
        assertThat(product.getWishlistses()).containsOnly(wishlistBack);
        assertThat(wishlistBack.getProductses()).containsOnly(product);

        product.removeWishlists(wishlistBack);
        assertThat(product.getWishlistses()).doesNotContain(wishlistBack);
        assertThat(wishlistBack.getProductses()).doesNotContain(product);

        product.wishlistses(new HashSet<>(Set.of(wishlistBack)));
        assertThat(product.getWishlistses()).containsOnly(wishlistBack);
        assertThat(wishlistBack.getProductses()).containsOnly(product);

        product.setWishlistses(new HashSet<>());
        assertThat(product.getWishlistses()).doesNotContain(wishlistBack);
        assertThat(wishlistBack.getProductses()).doesNotContain(product);
    }
}
