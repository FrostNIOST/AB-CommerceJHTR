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
 * A Product.
 */
@Document(collection = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(min = 5, max = 70)
    @Field("product_name")
    private String productName;

    @Size(min = 10, max = 200)
    @Field("description")
    private String description;

    @NotNull
    @DecimalMin(value = "1")
    @Field("purchase_price")
    private Double purchasePrice;

    @NotNull
    @DecimalMin(value = "1")
    @Field("selling_price")
    private Double sellingPrice;

    @Field("img_product")
    private byte[] imgProduct;

    @Field("img_product_content_type")
    private String imgProductContentType;

    @NotNull
    @Size(min = 3, max = 150)
    @Field("slug")
    private String slug;

    @NotNull
    @Min(value = 0)
    @Field("stock")
    private Integer stock;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("subCategory")
    @JsonIgnoreProperties(value = { "productses", "category" }, allowSetters = true)
    private SubCategory subCategory;

    @DBRef
    @Field("wishlistses")
    @JsonIgnoreProperties(value = { "account", "productses" }, allowSetters = true)
    private Set<Wishlist> wishlistses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Product id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public Product productName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPurchasePrice() {
        return this.purchasePrice;
    }

    public Product purchasePrice(Double purchasePrice) {
        this.setPurchasePrice(purchasePrice);
        return this;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellingPrice() {
        return this.sellingPrice;
    }

    public Product sellingPrice(Double sellingPrice) {
        this.setSellingPrice(sellingPrice);
        return this;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public byte[] getImgProduct() {
        return this.imgProduct;
    }

    public Product imgProduct(byte[] imgProduct) {
        this.setImgProduct(imgProduct);
        return this;
    }

    public void setImgProduct(byte[] imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getImgProductContentType() {
        return this.imgProductContentType;
    }

    public Product imgProductContentType(String imgProductContentType) {
        this.imgProductContentType = imgProductContentType;
        return this;
    }

    public void setImgProductContentType(String imgProductContentType) {
        this.imgProductContentType = imgProductContentType;
    }

    public String getSlug() {
        return this.slug;
    }

    public Product slug(String slug) {
        this.setSlug(slug);
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getStock() {
        return this.stock;
    }

    public Product stock(Integer stock) {
        this.setStock(stock);
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public State getState() {
        return this.state;
    }

    public Product state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Product subCategory(SubCategory subCategory) {
        this.setSubCategory(subCategory);
        return this;
    }

    public Set<Wishlist> getWishlistses() {
        return this.wishlistses;
    }

    public void setWishlistses(Set<Wishlist> wishlists) {
        if (this.wishlistses != null) {
            this.wishlistses.forEach(i -> i.removeProducts(this));
        }
        if (wishlists != null) {
            wishlists.forEach(i -> i.addProducts(this));
        }
        this.wishlistses = wishlists;
    }

    public Product wishlistses(Set<Wishlist> wishlists) {
        this.setWishlistses(wishlists);
        return this;
    }

    public Product addWishlists(Wishlist wishlist) {
        this.wishlistses.add(wishlist);
        wishlist.getProductses().add(this);
        return this;
    }

    public Product removeWishlists(Wishlist wishlist) {
        this.wishlistses.remove(wishlist);
        wishlist.getProductses().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", description='" + getDescription() + "'" +
            ", purchasePrice=" + getPurchasePrice() +
            ", sellingPrice=" + getSellingPrice() +
            ", imgProduct='" + getImgProduct() + "'" +
            ", imgProductContentType='" + getImgProductContentType() + "'" +
            ", slug='" + getSlug() + "'" +
            ", stock=" + getStock() +
            ", state='" + getState() + "'" +
            "}";
    }
}
