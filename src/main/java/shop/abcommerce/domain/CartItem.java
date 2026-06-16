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
 * A CartItem.
 */
@Document(collection = "cart_item")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartItem extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Min(value = 1)
    @Field("quantity")
    private Integer quantity;

    @DBRef
    @Field("product")
    @JsonIgnoreProperties(value = { "subCategory", "wishlistses" }, allowSetters = true)
    private Product product;

    @DBRef
    @Field("carts")
    @JsonIgnoreProperties(value = { "account", "itemses" }, allowSetters = true)
    private Set<Cart> carts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public CartItem id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public CartItem quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItem product(Product product) {
        this.setProduct(product);
        return this;
    }

    public Set<Cart> getCarts() {
        return this.carts;
    }

    public void setCarts(Set<Cart> carts) {
        if (this.carts != null) {
            this.carts.forEach(i -> i.removeItems(this));
        }
        if (carts != null) {
            carts.forEach(i -> i.addItems(this));
        }
        this.carts = carts;
    }

    public CartItem carts(Set<Cart> carts) {
        this.setCarts(carts);
        return this;
    }

    public CartItem addCart(Cart cart) {
        this.carts.add(cart);
        cart.getItemses().add(this);
        return this;
    }

    public CartItem removeCart(Cart cart) {
        this.carts.remove(cart);
        cart.getItemses().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }
        return getId() != null && getId().equals(((CartItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartItem{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
