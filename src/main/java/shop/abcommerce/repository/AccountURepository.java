package shop.abcommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.AccountU;

/**
 * Spring Data MongoDB repository for the AccountU entity.
 */
@Repository
public interface AccountURepository extends MongoRepository<AccountU, String> {
    @Query("{}")
    Page<AccountU> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<AccountU> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<AccountU> findOneWithEagerRelationships(String id);
}
