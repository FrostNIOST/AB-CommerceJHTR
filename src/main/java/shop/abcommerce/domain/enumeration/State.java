package shop.abcommerce.domain.enumeration;

/**
 * The State enumeration.
 */
public enum State {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
