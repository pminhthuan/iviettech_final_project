package com.iviettech.finalproject.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "product_promotion")
public class ProductPromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;

    @Column(name = "sale_off")
    private double saleOff;
}
