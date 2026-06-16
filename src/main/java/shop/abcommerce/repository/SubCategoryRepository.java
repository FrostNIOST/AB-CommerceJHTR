package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.SubCategory;

/**
 * Spring Data MongoDB repository for the SubCategory entity.
 */
@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategory, String> {
    @Query("{}")
    Page<SubCategory> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<SubCategory> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<SubCategory> findOneWithEagerRelationships(String id);
}
