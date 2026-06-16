package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private String id;

    @NotNull
    @Size(min = 5, max = 70)
    private String productName;

    @Size(min = 10, max = 200)
    private String description;

    @NotNull
    @DecimalMin(value = "1")
    private Double purchasePrice;

    @NotNull
    @DecimalMin(value = "1")
    private Double sellingPrice;

    private byte[] imgProduct;

    private String imgProductContentType;

    @NotNull
    @Size(min = 3, max = 150)
    private String slug;

    @NotNull
    @Min(value = 0)
    private Integer stock;

    @NotNull
    private State state;

    @NotNull
    private SubCategoryDTO subCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public byte[] getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(byte[] imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getImgProductContentType() {
        return imgProductContentType;
    }

    public void setImgProductContentType(String imgProductContentType) {
        this.imgProductContentType = imgProductContentType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public SubCategoryDTO getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryDTO subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id='" + getId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", description='" + getDescription() + "'" +
            ", purchasePrice=" + getPurchasePrice() +
            ", sellingPrice=" + getSellingPrice() +
            ", imgProduct='" + getImgProduct() + "'" +
            ", slug='" + getSlug() + "'" +
            ", stock=" + getStock() +
            ", state='" + getState() + "'" +
            ", subCategory=" + getSubCategory() +
            "}";
    }
}
