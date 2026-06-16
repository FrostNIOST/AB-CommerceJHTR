package shop.abcommerce.domain;

import java.util.UUID;

public class SubCategoryTestSamples {

    public static SubCategory getSubCategorySample1() {
        return new SubCategory().id("id1").subCategoryName("subCategoryName1").description("description1");
    }

    public static SubCategory getSubCategorySample2() {
        return new SubCategory().id("id2").subCategoryName("subCategoryName2").description("description2");
    }

    public static SubCategory getSubCategoryRandomSampleGenerator() {
        return new SubCategory()
            .id(UUID.randomUUID().toString())
            .subCategoryName(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
