package com.nk.aoptodetermineruntime.Service;

import com.nk.aoptodetermineruntime.AopExample.LogRunningTime;
import com.nk.aoptodetermineruntime.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class ProductService {

    private static List<Product> products= new ArrayList<>();

    static {
        products.add(new Product("1001","IPhone",100, "Apple"));
        products.add(new Product("1002", "MacBook", 200, "Apple"));
        products.add(new Product("1003", "TV", 10, "Sony"));
    }

    @LogRunningTime
    public List<Product> getAll() throws InterruptedException {
        Thread.sleep(2000);
        return products;
    }

    @Async
    public void saveProduct(Product product){
        products.add(product);
    }

    public Product getSingleProduct(String productId){
        Product product=products.stream().filter(p->p.getId().equals(productId)).findAny().orElse(null);
        return product;
    }

    public void deleteProduct(String productId){
        products.removeIf(product -> product.getId()==productId);
    }

    public void updateProduct(String productId, Product product){
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getId()==productId){
                products.set(i, product);
            }
        }
    }

}
