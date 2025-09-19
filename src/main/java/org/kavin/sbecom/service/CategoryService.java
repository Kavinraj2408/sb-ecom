package org.kavin.sbecom.service;

import org.kavin.sbecom.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void addCategory(Category category);
    String deleteCategory(long id);

    String updateCategory(Category category,long id);
}
