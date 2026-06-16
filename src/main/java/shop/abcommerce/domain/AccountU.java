package shop.abcommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AccountU.
 */
@Document(collection = "accountu")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AccountU extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 20)
    @Field("document_number")
    private String documentNumber;

    @NotNull
    @Size(max = 50)
    @Field("first_name")
    private String firstName;

    @Size(max = 50)
    @Field("second_name")
    private String secondName;

    @NotNull
    @Size(max = 50)
    @Field("first_last_name")
    private String firstLastName;

    @Size(max = 50)
    @Field("second_last_name")
    private String secondLastName;

    @DBRef
    @Field("user")
    private User user;

    @DBRef
    @Field("addresses")
    @JsonIgnoreProperties(value = { "account" }, allowSetters = true)
    private Set<Address> addresseses = new HashSet<>();

    @DBRef
    @Field("orders")
    @JsonIgnoreProperties(value = { "itemses", "paymentMethod", "shippingAddress", "account" }, allowSetters = true)
    private Set<Order> orderses = new HashSet<>();

    @DBRef
    @Field("documentType")
    private DocumentType documentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public AccountU id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public AccountU documentNumber(String documentNumber) {
        this.setDocumentNumber(documentNumber);
        return this;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public AccountU firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public AccountU secondName(String secondName) {
        this.setSecondName(secondName);
        return this;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return this.firstLastName;
    }

    public AccountU firstLastName(String firstLastName) {
        this.setFirstLastName(firstLastName);
        return this;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return this.secondLastName;
    }

    public AccountU secondLastName(String secondLastName) {
        this.setSecondLastName(secondLastName);
        return this;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountU user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Address> getAddresseses() {
        return this.addresseses;
    }

    public void setAddresseses(Set<Address> addresses) {
        if (this.addresseses != null) {
            this.addresseses.forEach(i -> i.setAccount(null));
        }
        if (addresses != null) {
            addresses.forEach(i -> i.setAccount(this));
        }
        this.addresseses = addresses;
    }

    public AccountU addresseses(Set<Address> addresses) {
        this.setAddresseses(addresses);
        return this;
    }

    public AccountU addAddresses(Address address) {
        this.addresseses.add(address);
        address.setAccount(this);
        return this;
    }

    public AccountU removeAddresses(Address address) {
        this.addresseses.remove(address);
        address.setAccount(null);
        return this;
    }

    public Set<Order> getOrderses() {
        return this.orderses;
    }

    public void setOrderses(Set<Order> orders) {
        if (this.orderses != null) {
            this.orderses.forEach(i -> i.setAccount(null));
        }
        if (orders != null) {
            orders.forEach(i -> i.setAccount(this));
        }
        this.orderses = orders;
    }

    public AccountU orderses(Set<Order> orders) {
        this.setOrderses(orders);
        return this;
    }

    public AccountU addOrders(Order order) {
        this.orderses.add(order);
        order.setAccount(this);
        return this;
    }

    public AccountU removeOrders(Order order) {
        this.orderses.remove(order);
        order.setAccount(null);
        return this;
    }

    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public AccountU documentType(DocumentType documentType) {
        this.setDocumentType(documentType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountU)) {
            return false;
        }
        return getId() != null && getId().equals(((AccountU) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountU{" +
            "id=" + getId() +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", secondName='" + getSecondName() + "'" +
            ", firstLastName='" + getFirstLastName() + "'" +
            ", secondLastName='" + getSecondLastName() + "'" +
            "}";
    }
}
