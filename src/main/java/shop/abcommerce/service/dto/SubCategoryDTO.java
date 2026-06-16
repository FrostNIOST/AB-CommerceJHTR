package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.SubCategory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SubCategoryDTO implements Serializable {

    private String id;

    @NotNull
    @Size(min = 5, max = 70)
    private String subCategoryName;

    @Size(min = 10, max = 200)
    private String description;

    @NotNull
    private State state;

    @NotNull
    private CategoryDTO category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubCategoryDTO)) {
            return false;
        }

        SubCategoryDTO subCategoryDTO = (SubCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, subCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubCategoryDTO{" +
            "id='" + getId() + "'" +
            ", subCategoryName='" + getSubCategoryName() + "'" +
            ", description='" + getDescription() + "'" +
            ", state='" + getState() + "'" +
            ", category=" + getCategory() +
            "}";
    }
}
