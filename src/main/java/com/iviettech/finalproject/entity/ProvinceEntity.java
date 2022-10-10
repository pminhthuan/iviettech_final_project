package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "provinces")
public class ProvinceEntity {
    @Id
    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "slug")
    private String slug;

    @OneToMany(mappedBy = "provinceEntity")
    private List<DistrictEntity> districtEntityList;
}
