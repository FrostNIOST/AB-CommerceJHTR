package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.AccountUTestSamples.*;
import static shop.abcommerce.domain.AddressTestSamples.*;

import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class AddressTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Address.class);
        Address address1 = getAddressSample1();
        Address address2 = new Address();
        assertThat(address1).isNotEqualTo(address2);

        address2.setId(address1.getId());
        assertThat(address1).isEqualTo(address2);

        address2 = getAddressSample2();
        assertThat(address1).isNotEqualTo(address2);
    }

    @Test
    void accountTest() {
        Address address = getAddressRandomSampleGenerator();
        AccountU accountUBack = getAccountURandomSampleGenerator();

        address.setAccount(accountUBack);
        assertThat(address.getAccount()).isEqualTo(accountUBack);

        address.account(null);
        assertThat(address.getAccount()).isNull();
    }
}
