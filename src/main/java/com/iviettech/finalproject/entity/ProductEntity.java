package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 1024)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_detail_id")
    private CategoryDetailEntity categoryDetail;

    @Column(name = "description", length = 40000)
    private String description;

    @Column(name = "addition_info", length = 40000)
    private String additionInfo;

    @Column(name = "orginal_price")
    private double original_price;

    @Column(name = "actual_price")
    private double actual_price;

    @ManyToOne
    @JoinColumn(name = "manufactor_id")
    private ManufactorEntity manufactor;

    @Column(name = "status", columnDefinition = "integer default 0")
    private int status;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> productImageEntityList;

    @OneToMany(mappedBy = "product")
    private List<ProductPromotionEntity> productPromotionEntityList;

    @OneToMany(mappedBy = "product")
    private List<OrderDetailEntity> orderDetailEntityList;

}
