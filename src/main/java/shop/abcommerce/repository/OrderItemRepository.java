package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.OrderItem;

/**
 * Spring Data MongoDB repository for the OrderItem entity.
 */
@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    @Query("{}")
    Page<OrderItem> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<OrderItem> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<OrderItem> findOneWithEagerRelationships(String id);
}
