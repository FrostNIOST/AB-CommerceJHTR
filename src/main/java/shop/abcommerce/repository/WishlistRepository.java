package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.Wishlist;

/**
 * Spring Data MongoDB repository for the Wishlist entity.
 */
@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    @Query("{}")
    Page<Wishlist> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Wishlist> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Wishlist> findOneWithEagerRelationships(String id);
}
