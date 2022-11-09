package com.iviettech.finalproject.helper;

import com.iviettech.finalproject.entity.OrderDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRawExport {
    private int id;
    private int order_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private int product_id;
//    private String product_name;
    private String color;
    private String size;
    private int quantity;
    private double price;
    private String req_date;
    private double total_amount;
    private String payment;

    public OrderDetailRawExport(OrderDetailEntity orderDetailEntity) {

        this.id = orderDetailEntity.getId();
        this.order_id = orderDetailEntity.getOrderEntity().getId();
        this.first_name = orderDetailEntity.getOrderEntity().getFirstName();
        this.last_name = orderDetailEntity.getOrderEntity().getLastName();
        this.email = orderDetailEntity.getOrderEntity().getEmail();
        this.phone = orderDetailEntity.getOrderEntity().getPhoneNumber();
        this.address = orderDetailEntity.getOrderEntity().getAddressDetail();
        this.product_id = orderDetailEntity.getProduct().getId();
//        this.product_name = orderDetailEntity.getProduct().getName();
        this.color = orderDetailEntity.getColor();
        this.size = orderDetailEntity.getSize();
        this.quantity = orderDetailEntity.getQuantity();
        this.price = orderDetailEntity.getPrice();
        try {
            this.req_date =
                    new SimpleDateFormat("yyyy-MM-dd").format(orderDetailEntity.getOrderEntity().getRequireDate());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        this.total_amount = orderDetailEntity.getOrderEntity().getTotalAmount();
        this.payment = orderDetailEntity.getOrderEntity().getPaymentMethod();


    }


}
