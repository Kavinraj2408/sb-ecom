package org.kavin.sbecom.service;

import org.kavin.sbecom.exceptions.APIException;
import org.kavin.sbecom.exceptions.ResourceNotFoundException;
import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.payload.CategoryDTO;
import org.kavin.sbecom.payload.CategoryResponse;
import org.kavin.sbecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
            throw new APIException("No categories found");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null){
            throw new APIException("Category with name " + category.getCategoryName() + " already Exists !!!");
        }
        Category category1 = categoryRepository.save(category);
        return modelMapper.map(category1,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",id,"Category"));

        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,long id) {
        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",id,"Category"));
        findCategory.setCategoryName(categoryDTO.getCategoryName());
        Category updateCategory = categoryRepository.save(findCategory);
        return modelMapper.map(updateCategory,CategoryDTO.class);
    }
}
