package com.iviettech.finalproject.helper;

import com.iviettech.finalproject.entity.ProductDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailRawExport {

    private int id;
    private String color;
    private int quantity;
    private String size;
    private int product_id;

    public ProductDetailRawExport(ProductDetailEntity productDetailEntity) {
        this.id = productDetailEntity.getId();
        this.color = productDetailEntity.getColor();
        this.quantity = productDetailEntity.getQuantity();
        this.size = productDetailEntity.getSize();
        this.product_id = productDetailEntity.getProduct().getId();

    }

}
