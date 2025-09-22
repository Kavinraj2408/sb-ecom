package org.kavin.sbecom.service;

import org.kavin.sbecom.exceptions.APIException;
import org.kavin.sbecom.exceptions.ResourceNotFoundException;
import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No categories found");
        }
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null){
            throw new APIException("Category with name " + category.getCategoryName() + " already Exists !!!");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",id,"Category"));

        categoryRepository.delete(category);
        return "category with id " + id +  " deleted successfully";
    }

    @Override
    public String updateCategory(Category category,long id) {

        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",id,"Category"));
        updateCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(updateCategory);
        return "Category with id " + id +  " updated successfully";
    }
}
