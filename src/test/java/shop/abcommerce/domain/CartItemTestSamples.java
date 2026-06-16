package shop.abcommerce.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CartItemTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CartItem getCartItemSample1() {
        return new CartItem().id("id1").quantity(1);
    }

    public static CartItem getCartItemSample2() {
        return new CartItem().id("id2").quantity(2);
    }

    public static CartItem getCartItemRandomSampleGenerator() {
        return new CartItem().id(UUID.randomUUID().toString()).quantity(intCount.incrementAndGet());
    }
}
