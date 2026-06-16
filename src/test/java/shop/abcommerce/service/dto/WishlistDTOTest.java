package shop.abcommerce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class WishlistDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WishlistDTO.class);
        WishlistDTO wishlistDTO1 = new WishlistDTO();
        wishlistDTO1.setId("id1");
        WishlistDTO wishlistDTO2 = new WishlistDTO();
        assertThat(wishlistDTO1).isNotEqualTo(wishlistDTO2);
        wishlistDTO2.setId(wishlistDTO1.getId());
        assertThat(wishlistDTO1).isEqualTo(wishlistDTO2);
        wishlistDTO2.setId("id2");
        assertThat(wishlistDTO1).isNotEqualTo(wishlistDTO2);
        wishlistDTO1.setId(null);
        assertThat(wishlistDTO1).isNotEqualTo(wishlistDTO2);
    }
}
