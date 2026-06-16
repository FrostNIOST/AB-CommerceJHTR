package shop.abcommerce.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.abcommerce.service.dto.AccountUDTO;

/**
 * Service Interface for managing {@link shop.abcommerce.domain.AccountU}.
 */
public interface AccountUService {
    /**
     * Save a accountU.
     *
     * @param accountUDTO the entity to save.
     * @return the persisted entity.
     */
    AccountUDTO save(AccountUDTO accountUDTO);

    /**
     * Updates a accountU.
     *
     * @param accountUDTO the entity to update.
     * @return the persisted entity.
     */
    AccountUDTO update(AccountUDTO accountUDTO);

    /**
     * Partially updates a accountU.
     *
     * @param accountUDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AccountUDTO> partialUpdate(AccountUDTO accountUDTO);

    /**
     * Get all the accountUS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountUDTO> findAll(Pageable pageable);

    /**
     * Get all the accountUS with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountUDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" accountU.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountUDTO> findOne(String id);

    /**
     * Delete the "id" accountU.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
