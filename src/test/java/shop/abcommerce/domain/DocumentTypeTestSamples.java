package shop.abcommerce.domain;

import java.util.UUID;

public class DocumentTypeTestSamples {

    public static DocumentType getDocumentTypeSample1() {
        return new DocumentType().id("id1").cod("cod1").documentType("documentType1");
    }

    public static DocumentType getDocumentTypeSample2() {
        return new DocumentType().id("id2").cod("cod2").documentType("documentType2");
    }

    public static DocumentType getDocumentTypeRandomSampleGenerator() {
        return new DocumentType()
            .id(UUID.randomUUID().toString())
            .cod(UUID.randomUUID().toString())
            .documentType(UUID.randomUUID().toString());
    }
}
