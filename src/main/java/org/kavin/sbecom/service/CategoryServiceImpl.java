package org.kavin.sbecom.service;

import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

//    private long idGenerator = 1L;
//
//    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
//        category.setCategoryId(idGenerator++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        categoryRepository.delete(category);
        return "category with id " + id +  " deleted successfully";
    }

    @Override
    public String updateCategory(Category category,long id) {

        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource with id " + id +
                        " Not Found"));
        updateCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(updateCategory);
        return "Category with id " + id +  " updated successfully";
    }
}
