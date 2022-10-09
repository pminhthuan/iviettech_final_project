package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "category_detail")
public class CategoryDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "categoryDetail")
    private List<ProductEntity> productEntityList;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
