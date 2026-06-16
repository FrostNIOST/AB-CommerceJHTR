package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.AccountUTestSamples.*;
import static shop.abcommerce.domain.CartItemTestSamples.*;
import static shop.abcommerce.domain.CartTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class CartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cart.class);
        Cart cart1 = getCartSample1();
        Cart cart2 = new Cart();
        assertThat(cart1).isNotEqualTo(cart2);

        cart2.setId(cart1.getId());
        assertThat(cart1).isEqualTo(cart2);

        cart2 = getCartSample2();
        assertThat(cart1).isNotEqualTo(cart2);
    }

    @Test
    void accountTest() {
        Cart cart = getCartRandomSampleGenerator();
        AccountU accountUBack = getAccountURandomSampleGenerator();

        cart.setAccount(accountUBack);
        assertThat(cart.getAccount()).isEqualTo(accountUBack);

        cart.account(null);
        assertThat(cart.getAccount()).isNull();
    }

    @Test
    void itemsTest() {
        Cart cart = getCartRandomSampleGenerator();
        CartItem cartItemBack = getCartItemRandomSampleGenerator();

        cart.addItems(cartItemBack);
        assertThat(cart.getItemses()).containsOnly(cartItemBack);

        cart.removeItems(cartItemBack);
        assertThat(cart.getItemses()).doesNotContain(cartItemBack);

        cart.itemses(new HashSet<>(Set.of(cartItemBack)));
        assertThat(cart.getItemses()).containsOnly(cartItemBack);

        cart.setItemses(new HashSet<>());
        assertThat(cart.getItemses()).doesNotContain(cartItemBack);
    }
}
