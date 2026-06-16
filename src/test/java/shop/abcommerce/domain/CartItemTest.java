package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.CartItemTestSamples.*;
import static shop.abcommerce.domain.CartTestSamples.*;
import static shop.abcommerce.domain.ProductTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class CartItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CartItem.class);
        CartItem cartItem1 = getCartItemSample1();
        CartItem cartItem2 = new CartItem();
        assertThat(cartItem1).isNotEqualTo(cartItem2);

        cartItem2.setId(cartItem1.getId());
        assertThat(cartItem1).isEqualTo(cartItem2);

        cartItem2 = getCartItemSample2();
        assertThat(cartItem1).isNotEqualTo(cartItem2);
    }

    @Test
    void productTest() {
        CartItem cartItem = getCartItemRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        cartItem.setProduct(productBack);
        assertThat(cartItem.getProduct()).isEqualTo(productBack);

        cartItem.product(null);
        assertThat(cartItem.getProduct()).isNull();
    }

    @Test
    void cartTest() {
        CartItem cartItem = getCartItemRandomSampleGenerator();
        Cart cartBack = getCartRandomSampleGenerator();

        cartItem.addCart(cartBack);
        assertThat(cartItem.getCarts()).containsOnly(cartBack);
        assertThat(cartBack.getItemses()).containsOnly(cartItem);

        cartItem.removeCart(cartBack);
        assertThat(cartItem.getCarts()).doesNotContain(cartBack);
        assertThat(cartBack.getItemses()).doesNotContain(cartItem);

        cartItem.carts(new HashSet<>(Set.of(cartBack)));
        assertThat(cartItem.getCarts()).containsOnly(cartBack);
        assertThat(cartBack.getItemses()).containsOnly(cartItem);

        cartItem.setCarts(new HashSet<>());
        assertThat(cartItem.getCarts()).doesNotContain(cartBack);
        assertThat(cartBack.getItemses()).doesNotContain(cartItem);
    }
}
