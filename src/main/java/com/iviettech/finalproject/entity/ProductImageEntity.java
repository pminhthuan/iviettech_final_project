package com.iviettech.finalproject.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_image")
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image_url", length = 5000)
    private String imageUrl;

    @Column(name = "image_alt")
    private String imageAlt;

    @Column(name = "is_main_image")
    @ColumnDefault("0")
    private int isMainImage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
