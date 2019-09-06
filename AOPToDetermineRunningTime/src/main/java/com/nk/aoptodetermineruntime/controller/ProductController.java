package com.nk.aoptodetermineruntime.controller;

import com.nk.aoptodetermineruntime.AopExample.LogRunningTime;
import com.nk.aoptodetermineruntime.Service.ProductService;
import com.nk.aoptodetermineruntime.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

        @GetMapping("/products")
        @LogRunningTime
        public List<Product> getAllProduct(){
            return  productService.buildProduct();
        }
}
