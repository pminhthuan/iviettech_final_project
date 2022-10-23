package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "administrative_regions")
public class RegionEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_name_en")
    private String codeNameEn;

    @OneToMany(mappedBy = "regionEntity")
    private List<ProvinceEntity> provinceEntityList;
}
