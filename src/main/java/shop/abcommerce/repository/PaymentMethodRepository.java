package shop.abcommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.PaymentMethod;

/**
 * Spring Data MongoDB repository for the PaymentMethod entity.
 */
@Repository
public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, String> {}
