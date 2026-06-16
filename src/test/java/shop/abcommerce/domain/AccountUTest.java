package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.AccountUTestSamples.*;
import static shop.abcommerce.domain.AddressTestSamples.*;
import static shop.abcommerce.domain.DocumentTypeTestSamples.*;
import static shop.abcommerce.domain.OrderTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class AccountUTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountU.class);
        AccountU accountU1 = getAccountUSample1();
        AccountU accountU2 = new AccountU();
        assertThat(accountU1).isNotEqualTo(accountU2);

        accountU2.setId(accountU1.getId());
        assertThat(accountU1).isEqualTo(accountU2);

        accountU2 = getAccountUSample2();
        assertThat(accountU1).isNotEqualTo(accountU2);
    }

    @Test
    void addressesTest() {
        AccountU accountU = getAccountURandomSampleGenerator();
        Address addressBack = getAddressRandomSampleGenerator();

        accountU.addAddresses(addressBack);
        assertThat(accountU.getAddresseses()).containsOnly(addressBack);
        assertThat(addressBack.getAccount()).isEqualTo(accountU);

        accountU.removeAddresses(addressBack);
        assertThat(accountU.getAddresseses()).doesNotContain(addressBack);
        assertThat(addressBack.getAccount()).isNull();

        accountU.addresseses(new HashSet<>(Set.of(addressBack)));
        assertThat(accountU.getAddresseses()).containsOnly(addressBack);
        assertThat(addressBack.getAccount()).isEqualTo(accountU);

        accountU.setAddresseses(new HashSet<>());
        assertThat(accountU.getAddresseses()).doesNotContain(addressBack);
        assertThat(addressBack.getAccount()).isNull();
    }

    @Test
    void ordersTest() {
        AccountU accountU = getAccountURandomSampleGenerator();
        Order orderBack = getOrderRandomSampleGenerator();

        accountU.addOrders(orderBack);
        assertThat(accountU.getOrderses()).containsOnly(orderBack);
        assertThat(orderBack.getAccount()).isEqualTo(accountU);

        accountU.removeOrders(orderBack);
        assertThat(accountU.getOrderses()).doesNotContain(orderBack);
        assertThat(orderBack.getAccount()).isNull();

        accountU.orderses(new HashSet<>(Set.of(orderBack)));
        assertThat(accountU.getOrderses()).containsOnly(orderBack);
        assertThat(orderBack.getAccount()).isEqualTo(accountU);

        accountU.setOrderses(new HashSet<>());
        assertThat(accountU.getOrderses()).doesNotContain(orderBack);
        assertThat(orderBack.getAccount()).isNull();
    }

    @Test
    void documentTypeTest() {
        AccountU accountU = getAccountURandomSampleGenerator();
        DocumentType documentTypeBack = getDocumentTypeRandomSampleGenerator();

        accountU.setDocumentType(documentTypeBack);
        assertThat(accountU.getDocumentType()).isEqualTo(documentTypeBack);

        accountU.documentType(null);
        assertThat(accountU.getDocumentType()).isNull();
    }
}
