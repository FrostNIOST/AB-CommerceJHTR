package shop.abcommerce.domain;

import java.util.UUID;

public class WishlistTestSamples {

    public static Wishlist getWishlistSample1() {
        return new Wishlist().id("id1");
    }

    public static Wishlist getWishlistSample2() {
        return new Wishlist().id("id2");
    }

    public static Wishlist getWishlistRandomSampleGenerator() {
        return new Wishlist().id(UUID.randomUUID().toString());
    }
}
