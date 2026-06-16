package shop.abcommerce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class SubCategoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubCategoryDTO.class);
        SubCategoryDTO subCategoryDTO1 = new SubCategoryDTO();
        subCategoryDTO1.setId("id1");
        SubCategoryDTO subCategoryDTO2 = new SubCategoryDTO();
        assertThat(subCategoryDTO1).isNotEqualTo(subCategoryDTO2);
        subCategoryDTO2.setId(subCategoryDTO1.getId());
        assertThat(subCategoryDTO1).isEqualTo(subCategoryDTO2);
        subCategoryDTO2.setId("id2");
        assertThat(subCategoryDTO1).isNotEqualTo(subCategoryDTO2);
        subCategoryDTO1.setId(null);
        assertThat(subCategoryDTO1).isNotEqualTo(subCategoryDTO2);
    }
}
