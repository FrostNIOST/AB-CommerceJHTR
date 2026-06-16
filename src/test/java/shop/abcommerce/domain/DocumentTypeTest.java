package shop.abcommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.abcommerce.domain.DocumentTypeTestSamples.*;

import org.junit.jupiter.api.Test;
import shop.abcommerce.web.rest.TestUtil;

class DocumentTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentType.class);
        DocumentType documentType1 = getDocumentTypeSample1();
        DocumentType documentType2 = new DocumentType();
        assertThat(documentType1).isNotEqualTo(documentType2);

        documentType2.setId(documentType1.getId());
        assertThat(documentType1).isEqualTo(documentType2);

        documentType2 = getDocumentTypeSample2();
        assertThat(documentType1).isNotEqualTo(documentType2);
    }
}
