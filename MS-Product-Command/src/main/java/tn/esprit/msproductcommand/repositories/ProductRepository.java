package tn.esprit.msproductcommand.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.msproductcommand.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}