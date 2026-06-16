package shop.abcommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.DocumentType;

/**
 * Spring Data MongoDB repository for the DocumentType entity.
 */
@Repository
public interface DocumentTypeRepository extends MongoRepository<DocumentType, String> {}
