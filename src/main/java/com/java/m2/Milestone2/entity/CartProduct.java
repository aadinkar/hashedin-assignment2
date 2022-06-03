package com.java.m2.Milestone2.entity;

import com.java.m2.Milestone2.repository.CartRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartProduct {
    private  @Id
    @GeneratedValue long id;

    public long getId() {
        return id;
    }
    private String product_id;

    public CartProduct(){

    }

    public CartProduct(String product_id){
        this.product_id = product_id;
    }

    public String getProduct_id(){
        return product_id;
    }

    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }
}
