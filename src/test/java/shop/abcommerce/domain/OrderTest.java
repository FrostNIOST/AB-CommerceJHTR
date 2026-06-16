package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.AccountUTestSamples.*;
import static shop.abcommerce.domain.AddressTestSamples.*;
import static shop.abcommerce.domain.OrderItemTestSamples.*;
import static shop.abcommerce.domain.OrderTestSamples.*;
import static shop.abcommerce.domain.PaymentMethodTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class OrderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Order.class);
        Order order1 = getOrderSample1();
        Order order2 = new Order();
        assertThat(order1).isNotEqualTo(order2);

        order2.setId(order1.getId());
        assertThat(order1).isEqualTo(order2);

        order2 = getOrderSample2();
        assertThat(order1).isNotEqualTo(order2);
    }

    @Test
    void itemsTest() {
        Order order = getOrderRandomSampleGenerator();
        OrderItem orderItemBack = getOrderItemRandomSampleGenerator();

        order.addItems(orderItemBack);
        assertThat(order.getItemses()).containsOnly(orderItemBack);
        assertThat(orderItemBack.getOrder()).isEqualTo(order);

        order.removeItems(orderItemBack);
        assertThat(order.getItemses()).doesNotContain(orderItemBack);
        assertThat(orderItemBack.getOrder()).isNull();

        order.itemses(new HashSet<>(Set.of(orderItemBack)));
        assertThat(order.getItemses()).containsOnly(orderItemBack);
        assertThat(orderItemBack.getOrder()).isEqualTo(order);

        order.setItemses(new HashSet<>());
        assertThat(order.getItemses()).doesNotContain(orderItemBack);
        assertThat(orderItemBack.getOrder()).isNull();
    }

    @Test
    void paymentMethodTest() {
        Order order = getOrderRandomSampleGenerator();
        PaymentMethod paymentMethodBack = getPaymentMethodRandomSampleGenerator();

        order.setPaymentMethod(paymentMethodBack);
        assertThat(order.getPaymentMethod()).isEqualTo(paymentMethodBack);

        order.paymentMethod(null);
        assertThat(order.getPaymentMethod()).isNull();
    }

    @Test
    void shippingAddressTest() {
        Order order = getOrderRandomSampleGenerator();
        Address addressBack = getAddressRandomSampleGenerator();

        order.setShippingAddress(addressBack);
        assertThat(order.getShippingAddress()).isEqualTo(addressBack);

        order.shippingAddress(null);
        assertThat(order.getShippingAddress()).isNull();
    }

    @Test
    void accountTest() {
        Order order = getOrderRandomSampleGenerator();
        AccountU accountUBack = getAccountURandomSampleGenerator();

        order.setAccount(accountUBack);
        assertThat(order.getAccount()).isEqualTo(accountUBack);

        order.account(null);
        assertThat(order.getAccount()).isNull();
    }
}
