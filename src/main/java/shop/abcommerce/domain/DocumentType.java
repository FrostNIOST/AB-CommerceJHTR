package shop.abcommerce.domain;

import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DocumentType.
 */
@Document(collection = "document_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 20)
    @Field("cod")
    private String cod;

    @NotNull
    @Size(max = 100)
    @Field("document_type")
    private String documentType;

    @NotNull
    @Field("state")
    private State state;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DocumentType id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod() {
        return this.cod;
    }

    public DocumentType cod(String cod) {
        this.setCod(cod);
        return this;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public DocumentType documentType(String documentType) {
        this.setDocumentType(documentType);
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public State getState() {
        return this.state;
    }

    public DocumentType state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentType)) {
            return false;
        }
        return getId() != null && getId().equals(((DocumentType) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentType{" +
            "id=" + getId() +
            ", cod='" + getCod() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
