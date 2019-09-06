package com.nk.aoptodetermineruntime.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    private String id;
    private String productName;
    private int amount;
    private String brand;

}
