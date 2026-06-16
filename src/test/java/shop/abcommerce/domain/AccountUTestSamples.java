package shop.abcommerce.domain;

import java.util.UUID;

public class AccountUTestSamples {

    public static AccountU getAccountUSample1() {
        return new AccountU()
            .id("id1")
            .documentNumber("documentNumber1")
            .firstName("firstName1")
            .secondName("secondName1")
            .firstLastName("firstLastName1")
            .secondLastName("secondLastName1");
    }

    public static AccountU getAccountUSample2() {
        return new AccountU()
            .id("id2")
            .documentNumber("documentNumber2")
            .firstName("firstName2")
            .secondName("secondName2")
            .firstLastName("firstLastName2")
            .secondLastName("secondLastName2");
    }

    public static AccountU getAccountURandomSampleGenerator() {
        return new AccountU()
            .id(UUID.randomUUID().toString())
            .documentNumber(UUID.randomUUID().toString())
            .firstName(UUID.randomUUID().toString())
            .secondName(UUID.randomUUID().toString())
            .firstLastName(UUID.randomUUID().toString())
            .secondLastName(UUID.randomUUID().toString());
    }
}
