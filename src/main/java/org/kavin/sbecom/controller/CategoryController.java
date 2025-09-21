package org.kavin.sbecom.controller;

import jakarta.validation.Valid;
import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/get-categories")
    public ResponseEntity<List<Category>> getCategories() {
        return new  ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/admin/add-category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-category/{id}")
//    @RequestMapping(value = "/admin/deleteCategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        try {
            String message = categoryService.deleteCategory(id);
//            return new ResponseEntity<>(message, HttpStatus.OK);
//            return ResponseEntity.ok(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/admin/update-category/{id}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable long id) {
        try {
            String message = categoryService.updateCategory(category,id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
