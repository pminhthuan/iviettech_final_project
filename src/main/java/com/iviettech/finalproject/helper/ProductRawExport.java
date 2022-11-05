package com.iviettech.finalproject.helper;

import com.iviettech.finalproject.entity.ProductEntity;
import lombok.*;

import java.text.SimpleDateFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRawExport {
    private int id;
    private String name;
    private int category_detail_id;
    private double original_price;
    private double actual_price;
    private int manufactor_id;
    private String add_date;
    private int status;
    private String description;
    private  String addition_info;
//    private String image;

    public ProductRawExport(ProductEntity productEntity) {

        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.category_detail_id = productEntity.getCategoryDetail().getId();
        this.original_price = productEntity.getOriginal_price();
        this.actual_price = productEntity.getActual_price();
        this.manufactor_id = productEntity.getManufactor().getId();
        this.add_date = new SimpleDateFormat("yyyy-MM-dd").format(productEntity.getAddDate());
        this.status = productEntity.getStatus();
        this.description = productEntity.getDescription();
        this.addition_info = productEntity.getAdditionInfo();



    }




}
