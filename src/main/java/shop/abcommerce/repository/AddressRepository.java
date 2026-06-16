package shop.abcommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.Address;

/**
 * Spring Data MongoDB repository for the Address entity.
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, String> {}
