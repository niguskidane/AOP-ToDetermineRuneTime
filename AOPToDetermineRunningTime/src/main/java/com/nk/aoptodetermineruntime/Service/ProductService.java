package com.nk.aoptodetermineruntime.Service;

import com.nk.aoptodetermineruntime.AopExample.LogRunningTime;
import com.nk.aoptodetermineruntime.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class ProductService {

    @LogRunningTime
    public List<Product> buildProduct(){
        List<Product> products= Arrays.asList(new Product("1001","IPhone",100, "Apple"),
                new Product("1002", "MacBook", 200, "Apple")
        );
        return products;
    }
}
