package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.Product;

/**
 * Spring Data MongoDB repository for the Product entity.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{}")
    Page<Product> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Product> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Product> findOneWithEagerRelationships(String id);
}
