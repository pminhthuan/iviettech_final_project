package com.iviettech.finalproject.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @Column(name = "account_id")
    private UserEntity userEntity;

    @Column(name = "require_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date requireDate;

    @Column(name = "shipping_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date shippingDate;

    @Column(name = "status")
    private String status;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address", length = 2000)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "total_amount") //tổng tiền
    private BigDecimal totalAmount;

    @Column(name = "note")
    private String note;

    @Column(name = "qr_code_payment")
    private String qrCodePayment;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetailEntityList;
}
