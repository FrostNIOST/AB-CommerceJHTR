package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import shop.abcommerce.domain.enumeration.State;

/**
 * A DTO for the {@link shop.abcommerce.domain.DocumentType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentTypeDTO implements Serializable {

    private String id;

    @NotNull
    @Size(max = 20)
    private String cod;

    @NotNull
    @Size(max = 100)
    private String documentType;

    @NotNull
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentTypeDTO)) {
            return false;
        }

        DocumentTypeDTO documentTypeDTO = (DocumentTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, documentTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentTypeDTO{" +
            "id='" + getId() + "'" +
            ", cod='" + getCod() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
