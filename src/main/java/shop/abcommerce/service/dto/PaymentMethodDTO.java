package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.PaymentMethod} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethodDTO implements Serializable {

    private String id;

    @NotNull
    @Size(max = 20)
    private String namePaymentMethod;

    @NotNull
    @Size(max = 20)
    private String cod;

    @Size(max = 100)
    private String description;

    @NotNull
    private String type;

    private String apiKey;

    private String endpoint;

    @NotNull
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }

    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethodDTO)) {
            return false;
        }

        PaymentMethodDTO paymentMethodDTO = (PaymentMethodDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentMethodDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethodDTO{" +
            "id='" + getId() + "'" +
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
