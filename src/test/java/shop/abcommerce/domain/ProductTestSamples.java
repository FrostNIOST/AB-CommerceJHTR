package shop.abcommerce.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Product getProductSample1() {
        return new Product().id("id1").productName("productName1").description("description1").slug("slug1").stock(1);
    }

    public static Product getProductSample2() {
        return new Product().id("id2").productName("productName2").description("description2").slug("slug2").stock(2);
    }

    public static Product getProductRandomSampleGenerator() {
        return new Product()
            .id(UUID.randomUUID().toString())
            .productName(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .slug(UUID.randomUUID().toString())
            .stock(intCount.incrementAndGet());
    }
}
