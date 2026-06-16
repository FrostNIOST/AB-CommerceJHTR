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
 * A Category.
 */
@Document(collection = "category")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Category extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 5, max = 70)
    @Field("category_name")
    private String categoryName;

    @Size(min = 10, max = 200)
    @Field("description")
    private String description;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("subCategories")
    @JsonIgnoreProperties(value = { "productses", "category" }, allowSetters = true)
    private Set<SubCategory> subCategorieses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Category id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public Category categoryName(String categoryName) {
        this.setCategoryName(categoryName);
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return this.description;
    }

    public Category description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return this.state;
    }

    public Category state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<SubCategory> getSubCategorieses() {
        return this.subCategorieses;
    }

    public void setSubCategorieses(Set<SubCategory> subCategories) {
        if (this.subCategorieses != null) {
            this.subCategorieses.forEach(i -> i.setCategory(null));
        }
        if (subCategories != null) {
            subCategories.forEach(i -> i.setCategory(this));
        }
        this.subCategorieses = subCategories;
    }

    public Category subCategorieses(Set<SubCategory> subCategories) {
        this.setSubCategorieses(subCategories);
        return this;
    }

    public Category addSubCategories(SubCategory subCategory) {
        this.subCategorieses.add(subCategory);
        subCategory.setCategory(this);
        return this;
    }

    public Category removeSubCategories(SubCategory subCategory) {
        this.subCategorieses.remove(subCategory);
        subCategory.setCategory(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return getId() != null && getId().equals(((Category) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", description='" + getDescription() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
