package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.Wishlist} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WishlistDTO implements Serializable {

    private String id;

    @NotNull
    private State state;

    @NotNull
    private Instant updateAt;

    @NotNull
    private AccountUDTO account;

    private Set<ProductDTO> productses = new HashSet<>();

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

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public AccountUDTO getAccount() {
        return account;
    }

    public void setAccount(AccountUDTO account) {
        this.account = account;
    }

    public Set<ProductDTO> getProductses() {
        return productses;
    }

    public void setProductses(Set<ProductDTO> productses) {
        this.productses = productses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WishlistDTO)) {
            return false;
        }

        WishlistDTO wishlistDTO = (WishlistDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, wishlistDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WishlistDTO{" +
            "id='" + getId() + "'" +
            ", state='" + getState() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            ", account=" + getAccount() +
            ", productses=" + getProductses() +
            "}";
    }
}
