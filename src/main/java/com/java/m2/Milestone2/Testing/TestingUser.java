package com.java.m2.Milestone2.Testing;

import com.java.m2.Milestone2.entity.UserEntity;
import com.java.m2.Milestone2.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestingUser {

    UserRepository userRepository;
    @Test
    public void checkUser(){
        String username = "aakash";
        String password = "Aakash@123";
        boolean result = false;
        List<UserEntity> users = userRepository.findAll();
        for(UserEntity user : users){
            if(user.getUsername() == username && user.getPassword() == password){
                result = true;
                break;
            }
        }
        Assert.assertEquals(true, result);
    }
}
