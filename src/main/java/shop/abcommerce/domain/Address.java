package shop.abcommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.abcommerce.domain.enumeration.State;

/**
 * A Address.
 */
@Document(collection = "address")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 10, max = 200)
    @Field("adress")
    private String adress;

    @NotNull
    @Size(max = 100)
    @Field("city")
    private String city;

    @Size(max = 100)
    @Field("department")
    private String department;

    @Size(max = 20)
    @Field("postal_code")
    private String postalCode;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("account")
    @JsonIgnoreProperties(value = { "user", "addresseses", "orderses", "documentType" }, allowSetters = true)
    private AccountU account;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Address id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return this.adress;
    }

    public Address adress(String adress) {
        this.setAdress(adress);
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return this.city;
    }

    public Address city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return this.department;
    }

    public Address department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Address postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public State getState() {
        return this.state;
    }

    public Address state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public AccountU getAccount() {
        return this.account;
    }

    public void setAccount(AccountU accountU) {
        this.account = accountU;
    }

    public Address account(AccountU accountU) {
        this.setAccount(accountU);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return getId() != null && getId().equals(((Address) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", adress='" + getAdress() + "'" +
            ", city='" + getCity() + "'" +
            ", department='" + getDepartment() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
