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

    @Override
    public ProductResponse searchByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("categoryId",categoryId,"Category"));
        List<Product> products = productRepository.findByCategory(category);

        List<ProductDTO> productDTOList = products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);

        return productResponse;
    }

    @Override
    public ProductResponse findProductsByKeyWord(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase("%"+keyword+"%");

        List<ProductDTO> productDTOList = products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);

        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Product product, Long productid) {
        //Get the product from DB
        Product productFromDb = productRepository.findById(productid)
                .orElseThrow(()-> new ResourceNotFoundException("productId",productid,"Product"));

        //Update the product
        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setDiscount(product.getDiscount());
        Double specialPrice = product.getPrice() -
                ((product.getDiscount()*0.01)*product.getPrice());
        productFromDb.setSpecialPrice(specialPrice);

        //Save into the DB
        Product savedProduct = productRepository.save(productFromDb);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
