package shop.abcommerce.domain;

import java.util.UUID;

public class CategoryTestSamples {

    public static Category getCategorySample1() {
        return new Category().id("id1").categoryName("categoryName1").description("description1");
    }

    public static Category getCategorySample2() {
        return new Category().id("id2").categoryName("categoryName2").description("description2");
    }

    public static Category getCategoryRandomSampleGenerator() {
        return new Category()
            .id(UUID.randomUUID().toString())
            .categoryName(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
