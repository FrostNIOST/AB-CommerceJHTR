package shop.abcommerce.domain;

import java.util.UUID;

public class CartTestSamples {

    public static Cart getCartSample1() {
        return new Cart().id("id1");
    }

    public static Cart getCartSample2() {
        return new Cart().id("id2");
    }

    public static Cart getCartRandomSampleGenerator() {
        return new Cart().id(UUID.randomUUID().toString());
    }
}
