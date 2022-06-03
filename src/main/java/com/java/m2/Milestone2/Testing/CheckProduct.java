package com.java.m2.Milestone2.Testing;

import com.java.m2.Milestone2.entity.Products;
import com.java.m2.Milestone2.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CheckProduct {
    ProductRepository prodRepository;
    @Test
    public void checkProduct(){
        String product_name = "Mobile";
        List<Products> prods = prodRepository.findAll();
        boolean result = false;
        for(Products prod : prods){
            if (prod.getProduct_name() == product_name){
                result = true;
            }
        }
        Assert.assertEquals(true, result);
    }
}
