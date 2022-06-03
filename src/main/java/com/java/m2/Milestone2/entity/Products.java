package com.java.m2.Milestone2.entity;

import javax.persistence.*;
@Entity
@Table(name = "products")
public class Products implements Comparable<Products>{
    private  @Id
    @GeneratedValue long id;
    private String product_name;
    private String product_description;
    private String product_category;
    private long product_price;

    public Products() {
    }
    public Products(String product_name, String product_description, String product_category, Long product_price) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_category = product_category;
        this.product_price = product_price;
    }

    public long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getProduct_name(){
        return product_name;
    }

    public void setProduct_name(String product_name){
        this.product_name = product_name;
    }

    public String getProduct_description(){
        return product_description;
    }

    public void setProduct_description(String product_description){
        this.product_description = product_description;
    }

    public String getProduct_category(){
        return product_category;
    }

    public void setProduct_category(String product_category){
        this.product_category = product_category;
    }

    public long getProduct_price(){
        return product_price;
    }

    public void setProduct_price(long product_price){
        this.product_price = product_price;
    }

    @Override
    public int compareTo(Products e) {
        return (int) (this.getProduct_price() - (e.getProduct_price()));
    }
    public String displayProduct(){
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", password='" + product_description + '\'' +
                ", email='" + product_category + '\'' +
                ", role='" + product_price + '\'' +
                '}';
    }
}
