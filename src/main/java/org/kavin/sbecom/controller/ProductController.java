package org.kavin.sbecom.controller;

import org.kavin.sbecom.model.Product;
import org.kavin.sbecom.payload.ProductDTO;
import org.kavin.sbecom.payload.ProductResponse;
import org.kavin.sbecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/admin/{categoryId}/save-product")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody Product product, @PathVariable Long categoryId) {
        ProductDTO productDTO = service.saveProduct(product,categoryId);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/get-all-products")
    public ResponseEntity<ProductResponse> getAllProducts() {
        ProductResponse productResponse = service.getAllProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


}
