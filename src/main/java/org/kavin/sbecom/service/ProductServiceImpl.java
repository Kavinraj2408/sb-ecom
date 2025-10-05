package org.kavin.sbecom.service;

import org.kavin.sbecom.exceptions.ResourceNotFoundException;
import org.kavin.sbecom.model.Category;
import org.kavin.sbecom.model.Product;
import org.kavin.sbecom.payload.ProductDTO;
import org.kavin.sbecom.payload.ProductResponse;
import org.kavin.sbecom.repositories.CategoryRepository;
import org.kavin.sbecom.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO saveProduct(Product product, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("categoryId",categoryId,"Category"));
        product.setCategory(category);
        product.setImage("default.png");

        Double specialPrice = product.getPrice() -
                ((product.getDiscount()*0.01)*product.getPrice());
        product.setSpecialPrice(specialPrice);

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {

        List<Product> allProducts = productRepository.findAll();

        List<ProductDTO> productDTOList = allProducts.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);
        return productResponse;
    }
}
