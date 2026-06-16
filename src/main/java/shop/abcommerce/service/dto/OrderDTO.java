package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import shop.abcommerce.domain.enumeration.OrderStatus;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.Order} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderDTO implements Serializable {

    private String id;

    @NotNull
    private String orderNumber;

    @NotNull
    @DecimalMin(value = "0")
    private Double subtotal;

    @NotNull
    @DecimalMin(value = "0")
    private Double subtotalIva;

    @NotNull
    @DecimalMin(value = "0")
    private Double shippingCost;

    @NotNull
    @DecimalMin(value = "1")
    private Double total;

    @NotNull
    private OrderStatus status;

    @NotNull
    private State state;

    @NotNull
    private PaymentMethodDTO paymentMethod;

    @NotNull
    private AddressDTO shippingAddress;

    @NotNull
    private AccountUDTO account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getSubtotalIva() {
        return subtotalIva;
    }

    public void setSubtotalIva(Double subtotalIva) {
        this.subtotalIva = subtotalIva;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public AddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AccountUDTO getAccount() {
        return account;
    }

    public void setAccount(AccountUDTO account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id='" + getId() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", subtotal=" + getSubtotal() +
            ", subtotalIva=" + getSubtotalIva() +
            ", shippingCost=" + getShippingCost() +
            ", total=" + getTotal() +
            ", status='" + getStatus() + "'" +
            ", state='" + getState() + "'" +
            ", paymentMethod=" + getPaymentMethod() +
            ", shippingAddress=" + getShippingAddress() +
            ", account=" + getAccount() +
            "}";
    }
}
