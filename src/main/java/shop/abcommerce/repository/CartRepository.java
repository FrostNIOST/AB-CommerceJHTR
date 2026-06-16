package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.Cart;

/**
 * Spring Data MongoDB repository for the Cart entity.
 */
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    @Query("{}")
    Page<Cart> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Cart> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Cart> findOneWithEagerRelationships(String id);
}
