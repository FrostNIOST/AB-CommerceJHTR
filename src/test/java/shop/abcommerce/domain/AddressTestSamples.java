package shop.abcommerce.domain;

import java.util.UUID;

public class AddressTestSamples {

    public static Address getAddressSample1() {
        return new Address().id("id1").adress("adress1").city("city1").department("department1").postalCode("postalCode1");
    }

    public static Address getAddressSample2() {
        return new Address().id("id2").adress("adress2").city("city2").department("department2").postalCode("postalCode2");
    }

    public static Address getAddressRandomSampleGenerator() {
        return new Address()
            .id(UUID.randomUUID().toString())
            .adress(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString());
    }
}
