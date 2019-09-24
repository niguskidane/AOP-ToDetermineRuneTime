package com.nk.aoptodetermineruntime.Service;

import com.nk.aoptodetermineruntime.AopExample.LogRunningTime;
import com.nk.aoptodetermineruntime.AopExample.SaveEvent;
import com.nk.aoptodetermineruntime.common.BaseResponse;
import com.nk.aoptodetermineruntime.common.ProcessingResults;
import com.nk.aoptodetermineruntime.model.Product;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @LogRunningTime
    @SaveEvent(value = "ADD_PRO_INFO", replay = true)
    public BaseResponse saveProduct(Product product){
        products.add(product);
        BaseResponse baseResponse=new BaseResponse("ProductInfo");
        ProcessingResults processingResults=new ProcessingResults();
        baseResponse.setResponseData(product);
        baseResponse.setProcessingResults(processingResults);
        return baseResponse;
    }

    @SaveEvent(value = "PRO_INFO", replay = true)
    public BaseResponse getSingleProduct(String productId){
        Product product=products.stream().filter(p->p.getId().equals(productId)).findAny().orElse(null);
        BaseResponse baseResponse=new BaseResponse("ProductInfo");
        ProcessingResults processingResults=new ProcessingResults();
        baseResponse.setResponseData(product);
        baseResponse.setProcessingResults(processingResults);
        return baseResponse;
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
