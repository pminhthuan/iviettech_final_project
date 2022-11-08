package com.iviettech.finalproject.helper;

import com.iviettech.finalproject.entity.OrderDetailEntity;
import com.iviettech.finalproject.entity.OrderEntity;
import com.iviettech.finalproject.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderRawExport {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    private int order_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    private int product_id;
    private String color;
    private String size;
    private int quantity;
    private double price;
    private String req_date;
    private double total_amount;
    private String payment;

    public OrderRawExport(OrderEntity orderEntity) {
        this.order_id = orderEntity.getId();
        this.first_name = orderEntity.getFirstName();
        this.last_name = orderEntity.getLastName();
        this.email = orderEntity.getEmail();
        this.phone = orderEntity.getPhoneNumber();
        this.address = orderEntity.getAddressDetail();

        List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrderEntityId(orderEntity.getId());
        for(OrderDetailEntity orderDetailEntity: orderDetailEntityList){
            this.product_id = orderDetailEntity.getProduct().getId();
//        this.product_name = orderDetailEntity.getProduct().getName();
            this.color = orderDetailEntity.getColor();
            this.size = orderDetailEntity.getSize();
            this.quantity = orderDetailEntity.getQuantity();
            this.price = orderDetailEntity.getPrice();
        }


//        this.product_id = orderDetailEntity.getProduct().getId();
////        this.product_name = orderDetailEntity.getProduct().getName();
//        this.color = orderDetailEntity.getColor();
//        this.size = orderDetailEntity.getSize();
//        this.quantity = orderDetailEntity.getQuantity();
//        this.price = orderDetailEntity.getPrice();
        try {
            this.req_date =
                    new SimpleDateFormat("yyyy-MM-dd").format(orderEntity.getRequireDate());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        this.total_amount = orderEntity.getTotalAmount();
        this.payment = orderEntity.getPaymentMethod();
    }
}
