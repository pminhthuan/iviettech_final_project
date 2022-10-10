package com.iviettech.finalproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "wards")
public class WardEntity {
    @Id
    @Column(name = "ward_id")
    private String wardId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private DistrictEntity districtEntity;
}
