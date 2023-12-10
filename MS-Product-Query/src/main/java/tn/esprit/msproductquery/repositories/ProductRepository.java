package tn.esprit.msproductquery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.msproductquery.entities.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, Long> {

    Optional<Product> findByName(String name);
}