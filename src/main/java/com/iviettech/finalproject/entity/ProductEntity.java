package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_detail_id")
    private CategoryDetailEntity categoryDetail;

    @Column(name = "description")
    private String description;

    @Column(name = "orginal_price")
    private BigDecimal original_price;

    @Column(name = "actual_price")
    private BigDecimal actual_price;

    @ManyToOne
    @JoinColumn(name = "manufactor_id")
    private ManufactorEntity manufactor;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> productImageEntityList;

    @OneToMany(mappedBy = "product")
    private List<ProductPromotionEntity> productPromotionEntityList;

    @OneToMany(mappedBy = "product")
    private List<OrderDetailEntity> orderDetailEntityList;

}
