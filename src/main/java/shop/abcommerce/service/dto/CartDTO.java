package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.Cart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartDTO implements Serializable {

    private String id;

    @NotNull
    private State state;

    @NotNull
    private AccountUDTO account;

    private Set<CartItemDTO> itemses = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public AccountUDTO getAccount() {
        return account;
    }

    public void setAccount(AccountUDTO account) {
        this.account = account;
    }

    public Set<CartItemDTO> getItemses() {
        return itemses;
    }

    public void setItemses(Set<CartItemDTO> itemses) {
        this.itemses = itemses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDTO)) {
            return false;
        }

        CartDTO cartDTO = (CartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDTO{" +
            "id='" + getId() + "'" +
            ", state='" + getState() + "'" +
            ", account=" + getAccount() +
            ", itemses=" + getItemses() +
            "}";
    }
}
