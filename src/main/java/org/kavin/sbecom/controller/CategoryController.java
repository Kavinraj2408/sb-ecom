package org.kavin.sbecom.controller;

import jakarta.validation.Valid;
import org.kavin.sbecom.constant.AppConstant;
import org.kavin.sbecom.payload.CategoryDTO;
import org.kavin.sbecom.payload.CategoryResponse;
import org.kavin.sbecom.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping(path = "/public/get-categories",produces = MediaType.APPLICATION_XML_VALUE)
    @GetMapping("/public/get-categories")
    public ResponseEntity<CategoryResponse> getCategories(
            @RequestParam(name="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstant.SORT_ORDER, required = false) String sortOrder){
        return new  ResponseEntity<>(categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder), HttpStatus.OK);
    }

    @PostMapping("/admin/add-category")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedDTO = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete-category/{id}")
//    @RequestMapping(value = "/admin/deleteCategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long id) {
        CategoryDTO deleteCategory = categoryService.deleteCategory(id);
        return new ResponseEntity<>(deleteCategory, HttpStatus.OK);
    }

    @PutMapping("/admin/update-category/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable long id) {
        CategoryDTO updatedDTO = categoryService.updateCategory(categoryDTO,id);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }
}
