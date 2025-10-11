package org.kavin.sbecom.repositories;

import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(Category category);

    List<Product> findByProductNameLikeIgnoreCase(String productName);
}
