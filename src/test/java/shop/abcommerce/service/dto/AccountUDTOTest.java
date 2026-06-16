package shop.abcommerce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class AccountUDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountUDTO.class);
        AccountUDTO accountUDTO1 = new AccountUDTO();
        accountUDTO1.setId("id1");
        AccountUDTO accountUDTO2 = new AccountUDTO();
        assertThat(accountUDTO1).isNotEqualTo(accountUDTO2);
        accountUDTO2.setId(accountUDTO1.getId());
        assertThat(accountUDTO1).isEqualTo(accountUDTO2);
        accountUDTO2.setId("id2");
        assertThat(accountUDTO1).isNotEqualTo(accountUDTO2);
        accountUDTO1.setId(null);
        assertThat(accountUDTO1).isNotEqualTo(accountUDTO2);
    }
}
