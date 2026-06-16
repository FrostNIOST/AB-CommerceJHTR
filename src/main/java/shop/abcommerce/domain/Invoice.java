package shop.abcommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.abcommerce.domain.enumeration.PaymentStatus;
import shop.abcommerce.domain.enumeration.State;

/**
 * A Invoice.
 */
@Document(collection = "invoice")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Invoice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("pref")
    private String pref;

    @NotNull
    @Field("invoice_number")
    private String invoiceNumber;

    @NotNull
    @Field("payment_status")
    private PaymentStatus paymentStatus;

    @Field("ref_transaction")
    private String refTransaction;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("order")
    @JsonIgnoreProperties(value = { "itemses", "paymentMethod", "shippingAddress", "account" }, allowSetters = true)
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Invoice id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPref() {
        return this.pref;
    }

    public Invoice pref(String pref) {
        this.setPref(pref);
        return this;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public Invoice invoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public Invoice paymentStatus(PaymentStatus paymentStatus) {
        this.setPaymentStatus(paymentStatus);
        return this;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRefTransaction() {
        return this.refTransaction;
    }

    public Invoice refTransaction(String refTransaction) {
        this.setRefTransaction(refTransaction);
        return this;
    }

    public void setRefTransaction(String refTransaction) {
        this.refTransaction = refTransaction;
    }

    public State getState() {
        return this.state;
    }

    public Invoice state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Invoice order(Order order) {
        this.setOrder(order);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return getId() != null && getId().equals(((Invoice) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", pref='" + getPref() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", refTransaction='" + getRefTransaction() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
