package org.kavin.sbecom.repositories;

import org.kavin.sbecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
