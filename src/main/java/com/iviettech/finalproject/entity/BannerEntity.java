package com.iviettech.finalproject.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "banners")
public class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image_url", length = 5000)
    private String imageUrl;

    @Column(name = "image_link", length = 1024)
    private String imageLink;

    @Column(name = "name", length = 1024)
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "status", columnDefinition = "integer default 0")
    private int status;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;
}
