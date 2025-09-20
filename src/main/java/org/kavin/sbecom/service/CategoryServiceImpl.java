package org.kavin.sbecom.service;

import org.kavin.sbecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private long idGenerator = 1L;

    private List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        category.setCategoryId(idGenerator++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(long id) {
        Category category = categories.stream().filter(c->c.getCategoryId()==id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        categories.remove(category);
        return "category with id " + id +  " deleted successfully";
    }

    @Override
    public String updateCategory(Category category,long id) {
        Category updateCategory = categories.stream().filter(c->c.getCategoryId()==id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource with id " + id +
                        " Not Found"));
        updateCategory.setCategoryName(category.getCategoryName());
        return "Category with id " + id +  " updated successfully";
    }
}
