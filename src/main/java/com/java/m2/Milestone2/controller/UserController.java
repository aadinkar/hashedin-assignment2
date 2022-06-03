package com.java.m2.Milestone2.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.java.m2.Milestone2.entity.Products;
import com.java.m2.Milestone2.entity.Status;
import com.java.m2.Milestone2.entity.UserEntity;
import com.java.m2.Milestone2.repository.CartRepository;
import com.java.m2.Milestone2.repository.ProductRepository;
import com.java.m2.Milestone2.repository.UserRepository;
import com.java.m2.Milestone2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.lang.Math;
import java.util.*;
import java.sql.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;

import static java.lang.Long.parseLong;
import static java.util.Base64.*;


@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/check")
    public String check(){
        return "Hello world";
    }

    @PostMapping("/encryptcreds")
    public String encryptCreds(@RequestParam("username") String user, @RequestParam("password") String password){
        String auth_string = "";
        byte[] bytes = (user + ":" + password).getBytes(StandardCharsets.UTF_8);
        auth_string = Base64.getEncoder().encodeToString(bytes);
        return auth_string;
    }
    @Autowired
    UserRepository userRepository;
    @PostMapping("/users/register")
    public Status registerUser(@RequestBody UserEntity newUser) {
        List<UserEntity> users = userRepository.findAll();
        System.out.println("New user: " + newUser.toString());
        for (UserEntity user : users) {
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userRepository.save(newUser);
        return Status.SUCCESS;
    }
    @PostMapping("/users/login")
    public Status loginUser( @RequestBody UserEntity user) {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @PostMapping("/users/logout")
    public Status logUserOut(@RequestBody UserEntity user) {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }

    @Autowired
    ProductRepository prodRepository;
    @PostMapping("/users/add_product")
    public String add_product(@RequestBody Products prod){
        prodRepository.save(prod);
        return prod.displayProduct();
    }

    @GetMapping("/users/all_products")
    public List<Products> all_products(){
        System.out.println(prodRepository.findAll());
        return prodRepository.findAll();
    }

    @GetMapping("/users/products/{id}")
    public Products all_products(@RequestParam int id){
        System.out.println(prodRepository.findById(id).get());
        return prodRepository.findById(id).get();
    }

    @GetMapping("/users/products/low_to_high")
    public List<Products> lowTohigh() {
        List<Products> prods = prodRepository.findAll();
        Collections.sort(prods);
        return prods;
    }

    @GetMapping("/users/products/high_to_low")
    public List<Products> highTolow() {
        List<Products> prods = prodRepository.findAll();
        Collections.sort(prods, Collections.reverseOrder());
        return prods;
    }

//    CartRepository cartRepository;
//    @PostMapping("users/cart/add_item")
//    public String getProductid(@RequestParam("productid") String productids){
//        String[] product_ids = productids.split(",");
//        for (String product_id : product_ids){
//            cartRepository.save(product_id);
//        }
//        return "added to cart";
    }
}
