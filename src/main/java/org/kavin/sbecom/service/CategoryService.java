package org.kavin.sbecom.service;

import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.payload.CategoryDTO;
import org.kavin.sbecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String order);
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(long id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO,long id);

    default void printCategory() {
        System.out.println("Hi");
    }
}
