package shop.abcommerce.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link shop.abcommerce.domain.AccountU} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AccountUDTO implements Serializable {

    private String id;

    @NotNull
    @Size(max = 20)
    private String documentNumber;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    private String firstLastName;

    @Size(max = 50)
    private String secondLastName;

    @NotNull
    private UserDTO user;

    @NotNull
    private DocumentTypeDTO documentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public DocumentTypeDTO getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeDTO documentType) {
        this.documentType = documentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountUDTO)) {
            return false;
        }

        AccountUDTO accountUDTO = (AccountUDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, accountUDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountUDTO{" +
            "id='" + getId() + "'" +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", secondName='" + getSecondName() + "'" +
            ", firstLastName='" + getFirstLastName() + "'" +
            ", secondLastName='" + getSecondLastName() + "'" +
            ", user=" + getUser() +
            ", documentType=" + getDocumentType() +
            "}";
    }
}
