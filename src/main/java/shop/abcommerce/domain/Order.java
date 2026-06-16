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
import shop.abcommerce.domain.enumeration.OrderStatus;
import shop.abcommerce.domain.enumeration.State;

/**
 * A Order.
 */
@Document(collection = "order")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Order extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("order_number")
    private String orderNumber;

    @NotNull
    @DecimalMin(value = "0")
    @Field("subtotal")
    private Double subtotal;

    @NotNull
    @DecimalMin(value = "0")
    @Field("subtotal_iva")
    private Double subtotalIva;

    @NotNull
    @DecimalMin(value = "0")
    @Field("shipping_cost")
    private Double shippingCost;

    @NotNull
    @DecimalMin(value = "1")
    @Field("total")
    private Double total;

    @NotNull
    @Field("status")
    private OrderStatus status;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("items")
    @JsonIgnoreProperties(value = { "product", "order" }, allowSetters = true)
    private Set<OrderItem> itemses = new HashSet<>();

    @DBRef
    @Field("paymentMethod")
    private PaymentMethod paymentMethod;

    @DBRef
    @Field("shippingAddress")
    @JsonIgnoreProperties(value = { "account" }, allowSetters = true)
    private Address shippingAddress;

    @DBRef
    @Field("account")
    @JsonIgnoreProperties(value = { "user", "addresseses", "orderses", "documentType" }, allowSetters = true)
    private AccountU account;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Order id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public Order orderNumber(String orderNumber) {
        this.setOrderNumber(orderNumber);
        return this;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getSubtotal() {
        return this.subtotal;
    }

    public Order subtotal(Double subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getSubtotalIva() {
        return this.subtotalIva;
    }

    public Order subtotalIva(Double subtotalIva) {
        this.setSubtotalIva(subtotalIva);
        return this;
    }

    public void setSubtotalIva(Double subtotalIva) {
        this.subtotalIva = subtotalIva;
    }

    public Double getShippingCost() {
        return this.shippingCost;
    }

    public Order shippingCost(Double shippingCost) {
        this.setShippingCost(shippingCost);
        return this;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Double getTotal() {
        return this.total;
    }

    public Order total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public Order status(OrderStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public State getState() {
        return this.state;
    }

    public Order state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<OrderItem> getItemses() {
        return this.itemses;
    }

    public void setItemses(Set<OrderItem> orderItems) {
        if (this.itemses != null) {
            this.itemses.forEach(i -> i.setOrder(null));
        }
        if (orderItems != null) {
            orderItems.forEach(i -> i.setOrder(this));
        }
        this.itemses = orderItems;
    }

    public Order itemses(Set<OrderItem> orderItems) {
        this.setItemses(orderItems);
        return this;
    }

    public Order addItems(OrderItem orderItem) {
        this.itemses.add(orderItem);
        orderItem.setOrder(this);
        return this;
    }

    public Order removeItems(OrderItem orderItem) {
        this.itemses.remove(orderItem);
        orderItem.setOrder(null);
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Order paymentMethod(PaymentMethod paymentMethod) {
        this.setPaymentMethod(paymentMethod);
        return this;
    }

    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address address) {
        this.shippingAddress = address;
    }

    public Order shippingAddress(Address address) {
        this.setShippingAddress(address);
        return this;
    }

    public AccountU getAccount() {
        return this.account;
    }

    public void setAccount(AccountU accountU) {
        this.account = accountU;
    }

    public Order account(AccountU accountU) {
        this.setAccount(accountU);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return getId() != null && getId().equals(((Order) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", subtotal=" + getSubtotal() +
            ", subtotalIva=" + getSubtotalIva() +
            ", shippingCost=" + getShippingCost() +
            ", total=" + getTotal() +
            ", status='" + getStatus() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
