package shop.abcommerce.domain;

import java.util.UUID;

public class OrderTestSamples {

    public static Order getOrderSample1() {
        return new Order().id("id1").orderNumber("orderNumber1");
    }

    public static Order getOrderSample2() {
        return new Order().id("id2").orderNumber("orderNumber2");
    }

    public static Order getOrderRandomSampleGenerator() {
        return new Order().id(UUID.randomUUID().toString()).orderNumber(UUID.randomUUID().toString());
    }
}
