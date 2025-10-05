package org.kavin.sbecom.service;

import org.kavin.sbecom.model.Product;
import org.kavin.sbecom.payload.ProductDTO;
import org.kavin.sbecom.payload.ProductResponse;

public interface ProductService {
    ProductDTO saveProduct(Product product, Long categoryId);

    ProductResponse getAllProducts();
}
