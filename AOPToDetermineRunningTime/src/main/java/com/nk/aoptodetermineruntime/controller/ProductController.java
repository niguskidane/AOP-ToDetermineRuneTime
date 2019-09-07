package com.nk.aoptodetermineruntime.controller;

import com.nk.aoptodetermineruntime.AopExample.LogRunningTime;
import com.nk.aoptodetermineruntime.Service.ProductService;
import com.nk.aoptodetermineruntime.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private Logger logger= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

        @GetMapping("/products")
        @LogRunningTime
        public List<Product> getAllProduct(){
            List<Product> products=new ArrayList<>();
            try {
                products=productService.buildProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return products;
        }


}
