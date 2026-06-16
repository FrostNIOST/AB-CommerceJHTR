package shop.abcommerce.domain.enumeration;

/**
 * The PaymentStatus enumeration.
 */
public enum PaymentStatus {
    PENDING("Pending"),
    PAID("Paid"),
    UNPAID("Unpaid");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
