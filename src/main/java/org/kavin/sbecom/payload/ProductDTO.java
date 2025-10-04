package org.kavin.sbecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kavin.sbecom.model.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String productName;
    private String description;
    private Integer quantity;
    private Double price;
    private Double specialPrice;
    private Category category;
}
