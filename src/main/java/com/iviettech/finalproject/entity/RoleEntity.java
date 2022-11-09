package com.iviettech.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // value should be
    //   CUSTOMER
    //   MANAGER
    //   ADMIN
    @Column(name = "name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> userList;
}
