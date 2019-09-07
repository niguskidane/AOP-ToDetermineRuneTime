package com.nk.aoptodetermineruntime.model;

import lombok.*;

public class Product {
    private String id;
    private String productName;
    private int amount;
    private String brand;

    public Product(){}

    public Product(String id, String productName, int amount, String brand) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", brand='" + brand + '\'' +
                '}';
    }
}
