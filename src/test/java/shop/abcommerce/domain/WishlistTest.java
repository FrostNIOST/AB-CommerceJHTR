package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.AccountUTestSamples.*;
import static shop.abcommerce.domain.ProductTestSamples.*;
import static shop.abcommerce.domain.WishlistTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class WishlistTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wishlist.class);
        Wishlist wishlist1 = getWishlistSample1();
        Wishlist wishlist2 = new Wishlist();
        assertThat(wishlist1).isNotEqualTo(wishlist2);

        wishlist2.setId(wishlist1.getId());
        assertThat(wishlist1).isEqualTo(wishlist2);

        wishlist2 = getWishlistSample2();
        assertThat(wishlist1).isNotEqualTo(wishlist2);
    }

    @Test
    void accountTest() {
        Wishlist wishlist = getWishlistRandomSampleGenerator();
        AccountU accountUBack = getAccountURandomSampleGenerator();

        wishlist.setAccount(accountUBack);
        assertThat(wishlist.getAccount()).isEqualTo(accountUBack);

        wishlist.account(null);
        assertThat(wishlist.getAccount()).isNull();
    }

    @Test
    void productsTest() {
        Wishlist wishlist = getWishlistRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        wishlist.addProducts(productBack);
        assertThat(wishlist.getProductses()).containsOnly(productBack);

        wishlist.removeProducts(productBack);
        assertThat(wishlist.getProductses()).doesNotContain(productBack);

        wishlist.productses(new HashSet<>(Set.of(productBack)));
        assertThat(wishlist.getProductses()).containsOnly(productBack);

        wishlist.setProductses(new HashSet<>());
        assertThat(wishlist.getProductses()).doesNotContain(productBack);
    }
}
