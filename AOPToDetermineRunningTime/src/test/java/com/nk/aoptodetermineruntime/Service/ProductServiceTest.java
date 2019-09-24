package com.nk.aoptodetermineruntime.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService=new ProductService();
    }



    @Test
    public void getAll() {

    }

    @Test
    public void saveProduct() {
    }

    @Test
    public void getSingleProduct() {
    }

    @Test
    public void deleteProduct() {
    }

    @Test
    public void updateProduct() {
    }

    @After
    public void tearDown() throws Exception {
        productService=null;
    }
}