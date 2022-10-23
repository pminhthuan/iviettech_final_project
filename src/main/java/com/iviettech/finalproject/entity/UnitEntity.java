package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "administrative_units")
public class UnitEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "short_name_en")
    private String shortNameEn;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_name_en")
    private String codeNameEn;

    @OneToMany(mappedBy = "unitEntity")
    private List<ProvinceEntity> provinceEntityList;

    @OneToMany(mappedBy = "unitEntity")
    private List<DistrictEntity> districtEntityList;

    @OneToMany(mappedBy = "unitEntity")
    private List<WardEntity> wardEntityList;
}
