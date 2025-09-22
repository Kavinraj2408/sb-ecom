package org.kavin.sbecom.repositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.kavin.sbecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(@NotBlank(message = "Category name must not be blank") @Size(min=3, message = "Category name must have at least 3 characters") String categoryName);
}
