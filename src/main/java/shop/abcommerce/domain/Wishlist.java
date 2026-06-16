package shop.abcommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.abcommerce.domain.enumeration.State;

/**
 * A Wishlist.
 */
@Document(collection = "wishlist")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Wishlist implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("state")
    private State state;

    @NotNull
    @Field("update_at")
    private Instant updateAt;

    @DBRef
    @Field("account")
    private AccountU account;

    @DBRef
    @Field("productses")
    @JsonIgnoreProperties(value = { "subCategory", "wishlistses" }, allowSetters = true)
    private Set<Product> productses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Wishlist id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return this.state;
    }

    public Wishlist state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Instant getUpdateAt() {
        return this.updateAt;
    }

    public Wishlist updateAt(Instant updateAt) {
        this.setUpdateAt(updateAt);
        return this;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public AccountU getAccount() {
        return this.account;
    }

    public void setAccount(AccountU accountU) {
        this.account = accountU;
    }

    public Wishlist account(AccountU accountU) {
        this.setAccount(accountU);
        return this;
    }

    public Set<Product> getProductses() {
        return this.productses;
    }

    public void setProductses(Set<Product> products) {
        this.productses = products;
    }

    public Wishlist productses(Set<Product> products) {
        this.setProductses(products);
        return this;
    }

    public Wishlist addProducts(Product product) {
        this.productses.add(product);
        return this;
    }

    public Wishlist removeProducts(Product product) {
        this.productses.remove(product);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wishlist)) {
            return false;
        }
        return getId() != null && getId().equals(((Wishlist) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wishlist{" +
            "id=" + getId() +
            ", state='" + getState() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
}
