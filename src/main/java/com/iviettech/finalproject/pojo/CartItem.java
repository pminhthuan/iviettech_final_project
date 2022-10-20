package com.iviettech.finalproject.pojo;

import lombok.Data;

@Data
public class CartItem {
    private int productId;
    private int quantity;
    private String imgSource;
    private String title;
    private String price;
    private String size;
    private String color;
    private String totalPrice;
    private Double totalPriceInNumber;

    // can be replaced by lombok lib
    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem(int productId, int quantity, String imgSource, String title, String price, String size, String color) {
        this.productId = productId;
        this.quantity = quantity;
        this.imgSource = imgSource;
        this.title = title;
        this.price = price;
        this.color = color;
        this.size = size;
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        try{
            Double totalPrice = Double.valueOf(this.price.substring(this.price.lastIndexOf("$") + 1)) * this.quantity;
            this.totalPrice = "$" + String.format("%.2f", totalPrice);;
            this.totalPriceInNumber = totalPrice;
        } catch (Exception e) {
            this.totalPrice = "ERROR";
        }
    }

}
