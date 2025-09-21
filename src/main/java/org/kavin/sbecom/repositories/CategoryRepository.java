package org.kavin.sbecom.repositories;

import org.kavin.sbecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
