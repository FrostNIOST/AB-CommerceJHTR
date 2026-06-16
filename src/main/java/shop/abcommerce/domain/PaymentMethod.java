package shop.abcommerce.domain;

import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.abcommerce.domain.enumeration.State;

/**
 * A PaymentMethod.
 */
@Document(collection = "payment_method")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethod implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 20)
    @Field("name_payment_method")
    private String namePaymentMethod;

    @NotNull
    @Size(max = 20)
    @Field("cod")
    private String cod;

    @Size(max = 100)
    @Field("description")
    private String description;

    @NotNull
    @Field("type")
    private String type;

    @Field("api_key")
    private String apiKey;

    @Field("endpoint")
    private String endpoint;

    @NotNull
    @Field("state")
    private State state;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public PaymentMethod id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamePaymentMethod() {
        return this.namePaymentMethod;
    }

    public PaymentMethod namePaymentMethod(String namePaymentMethod) {
        this.setNamePaymentMethod(namePaymentMethod);
        return this;
    }

    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }

    public String getCod() {
        return this.cod;
    }

    public PaymentMethod cod(String cod) {
        this.setCod(cod);
        return this;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return this.description;
    }

    public PaymentMethod description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public PaymentMethod type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public PaymentMethod apiKey(String apiKey) {
        this.setApiKey(apiKey);
        return this;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public PaymentMethod endpoint(String endpoint) {
        this.setEndpoint(endpoint);
        return this;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public State getState() {
        return this.state;
    }

    public PaymentMethod state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethod)) {
            return false;
        }
        return getId() != null && getId().equals(((PaymentMethod) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethod{" +
            "id=" + getId() +
            ", namePaymentMethod='" + getNamePaymentMethod() + "'" +
            ", cod='" + getCod() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", apiKey='" + getApiKey() + "'" +
            ", endpoint='" + getEndpoint() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
