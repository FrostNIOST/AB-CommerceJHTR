package shop.abcommerce.domain;

import java.util.UUID;

public class InvoiceTestSamples {

    public static Invoice getInvoiceSample1() {
        return new Invoice().id("id1").pref("pref1").invoiceNumber("invoiceNumber1").refTransaction("refTransaction1");
    }

    public static Invoice getInvoiceSample2() {
        return new Invoice().id("id2").pref("pref2").invoiceNumber("invoiceNumber2").refTransaction("refTransaction2");
    }

    public static Invoice getInvoiceRandomSampleGenerator() {
        return new Invoice()
            .id(UUID.randomUUID().toString())
            .pref(UUID.randomUUID().toString())
            .invoiceNumber(UUID.randomUUID().toString())
            .refTransaction(UUID.randomUUID().toString());
    }
}
