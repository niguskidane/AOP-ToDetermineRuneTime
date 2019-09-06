package com.nk.aoptodetermineruntime.Service;

import com.nk.aoptodetermineruntime.model.Product;

public class ProductService {

    public Product buildProduct(){
        return new Product("1001","IPhone",30,"Apple");
    }
}
