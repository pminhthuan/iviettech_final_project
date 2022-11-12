package com.iviettech.finalproject.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    @TableGenerator(name = "id_gen", initialValue = 3000)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_gen")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserEntity user;

    @Column(name = "require_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date requireDate;

    @Column(name = "shipping_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date shippingDate;

    @Column(name = "order_status", columnDefinition = "integer default 0")
    private int orderStatus;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Column(name = "first_name", length = 1024)
    private String firstName;

    @Column(name = "last_name", length = 1024)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "province")
    private ProvinceEntity province;

    @ManyToOne
    @JoinColumn(name = "district")
    private DistrictEntity district;

    @ManyToOne
    @JoinColumn(name = "ward")
    private WardEntity ward;

    @Column(name = "address_detail", length = 2000)
    private String addressDetail;

    @Column(name = "email", length = 1024)
    private String email;

    @Column(name = "total_amount") //tổng tiền
    private double totalAmount;

    @Column(name = "notes_of_customer", length = 5000)
    private String notesOfCustomer;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status") //0 not yet, 1 paid
    private int paymentStatus;

    @Column(name = "qr_code_payment")
    private String qrCodePayment;

    @OneToMany(mappedBy = "orderEntity",fetch = FetchType.EAGER)
    private List<OrderDetailEntity> orderDetailEntityList;
}
