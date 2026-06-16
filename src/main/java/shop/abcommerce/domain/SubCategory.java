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
import shop.abcommerce.domain.enumeration.State;

/**
 * A SubCategory.
 */
@Document(collection = "sub_category")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SubCategory extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 5, max = 70)
    @Field("sub_category_name")
    private String subCategoryName;

    @Size(min = 10, max = 200)
    @Field("description")
    private String description;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("products")
    @JsonIgnoreProperties(value = { "subCategory", "wishlistses" }, allowSetters = true)
    private Set<Product> productses = new HashSet<>();

    @DBRef
    @Field("category")
    @JsonIgnoreProperties(value = { "subCategorieses" }, allowSetters = true)
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SubCategory id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public SubCategory subCategoryName(String subCategoryName) {
        this.setSubCategoryName(subCategoryName);
        return this;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getDescription() {
        return this.description;
    }

    public SubCategory description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return this.state;
    }

    public SubCategory state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<Product> getProductses() {
        return this.productses;
    }

    public void setProductses(Set<Product> products) {
        if (this.productses != null) {
            this.productses.forEach(i -> i.setSubCategory(null));
        }
        if (products != null) {
            products.forEach(i -> i.setSubCategory(this));
        }
        this.productses = products;
    }

    public SubCategory productses(Set<Product> products) {
        this.setProductses(products);
        return this;
    }

    public SubCategory addProducts(Product product) {
        this.productses.add(product);
        product.setSubCategory(this);
        return this;
    }

    public SubCategory removeProducts(Product product) {
        this.productses.remove(product);
        product.setSubCategory(null);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory category(Category category) {
        this.setCategory(category);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubCategory)) {
            return false;
        }
        return getId() != null && getId().equals(((SubCategory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubCategory{" +
            "id=" + getId() +
            ", subCategoryName='" + getSubCategoryName() + "'" +
            ", description='" + getDescription() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
