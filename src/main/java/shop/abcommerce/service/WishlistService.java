package shop.abcommerce.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.abcommerce.service.dto.WishlistDTO;

/**
 * Service Interface for managing {@link shop.abcommerce.domain.Wishlist}.
 */
public interface WishlistService {
    /**
     * Save a wishlist.
     *
     * @param wishlistDTO the entity to save.
     * @return the persisted entity.
     */
    WishlistDTO save(WishlistDTO wishlistDTO);

    /**
     * Updates a wishlist.
     *
     * @param wishlistDTO the entity to update.
     * @return the persisted entity.
     */
    WishlistDTO update(WishlistDTO wishlistDTO);

    /**
     * Partially updates a wishlist.
     *
     * @param wishlistDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WishlistDTO> partialUpdate(WishlistDTO wishlistDTO);

    /**
     * Get all the wishlists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WishlistDTO> findAll(Pageable pageable);

    /**
     * Get all the wishlists with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WishlistDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" wishlist.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WishlistDTO> findOne(String id);

    /**
     * Delete the "id" wishlist.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
