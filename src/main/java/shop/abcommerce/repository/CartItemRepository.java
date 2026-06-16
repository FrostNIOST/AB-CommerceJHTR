package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.CartItem;

/**
 * Spring Data MongoDB repository for the CartItem entity.
 */
@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
    @Query("{}")
    Page<CartItem> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<CartItem> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<CartItem> findOneWithEagerRelationships(String id);
}
