package org.kavin.sbecom.controller;

import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/getAllCategories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/api/public/addCategory")
    public String addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return "Category added successfully!";
    }
}
