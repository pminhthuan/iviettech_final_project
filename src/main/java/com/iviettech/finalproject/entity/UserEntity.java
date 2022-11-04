package com.iviettech.finalproject.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, length = 1024)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    // hashed value by using pbkdf2
    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "age")
    @ColumnDefault(value = "0")
    private int age;

    @Column(name = "gender")
    @Comment(value = "1: male; 2: female; 3: others")
    private int gender;

    @Column(name = "address", length = 2000)
    private String address;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "status")
    @ColumnDefault("0")
    private int status;

    @ManyToOne
    @ColumnDefault(value = "1")
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orderEntityList;
}
