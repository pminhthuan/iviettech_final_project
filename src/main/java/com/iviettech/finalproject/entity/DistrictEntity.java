package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "district")
public class DistrictEntity {
    @Id
    @Column(name = "district_id")
    private String districtId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity provinceEntity;

    @OneToMany(mappedBy = "districtEntity")
    private List<WardEntity> wardEntityList;
}
