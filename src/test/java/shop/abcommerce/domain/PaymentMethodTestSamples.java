package shop.abcommerce.domain;

import java.util.UUID;

public class PaymentMethodTestSamples {

    public static PaymentMethod getPaymentMethodSample1() {
        return new PaymentMethod()
            .id("id1")
            .namePaymentMethod("namePaymentMethod1")
            .cod("cod1")
            .description("description1")
            .type("type1")
            .apiKey("apiKey1")
            .endpoint("endpoint1");
    }

    public static PaymentMethod getPaymentMethodSample2() {
        return new PaymentMethod()
            .id("id2")
            .namePaymentMethod("namePaymentMethod2")
            .cod("cod2")
            .description("description2")
            .type("type2")
            .apiKey("apiKey2")
            .endpoint("endpoint2");
    }

    public static PaymentMethod getPaymentMethodRandomSampleGenerator() {
        return new PaymentMethod()
            .id(UUID.randomUUID().toString())
            .namePaymentMethod(UUID.randomUUID().toString())
            .cod(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString())
            .apiKey(UUID.randomUUID().toString())
            .endpoint(UUID.randomUUID().toString());
    }
}
