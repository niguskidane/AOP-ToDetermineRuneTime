package com.nk.aoptodetermineruntime.controller;

import com.nk.aoptodetermineruntime.AopExample.LogRunningTime;
import com.nk.aoptodetermineruntime.common.BaseResponse;
import com.nk.aoptodetermineruntime.common.Utilities;
import com.nk.aoptodetermineruntime.Service.ProductService;
import com.nk.aoptodetermineruntime.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    @LogRunningTime
    public ResponseEntity<BaseResponse> addProduct(@RequestBody Product product) {
        logger.warn("addProduct input data : " + Utilities.objectToString(product));
        BaseResponse baseResponse=productService.saveProduct(product);

        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

    }

    @GetMapping("/products/{productId}")
    @LogRunningTime
    public ResponseEntity<BaseResponse> getProduct(@PathVariable("productId") String productId) {
        BaseResponse baseResponse=productService.getSingleProduct(productId);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/products")
    @LogRunningTime
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        try {
            products = productService.getAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return products;
    }

    @PutMapping("/products/{productId}")
    @LogRunningTime
    public void updateProduct(@PathVariable("productId") String productId, @RequestBody Product product) {
        logger.warn("updateProduct input data : " + Utilities.objectToString(product));
        productService.updateProduct(productId, product);
    }

    @DeleteMapping("products/{productId}")
    @LogRunningTime
    public void deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
    }


}
