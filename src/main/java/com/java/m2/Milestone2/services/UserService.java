package com.java.m2.Milestone2.services;

import com.java.m2.Milestone2.entity.UserEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface UserService {
    UserEntity Post(UserEntity params);

    List<UserEntity> Get();

    UserEntity Get(int id);

    UserEntity Update(UserEntity params, int id);

    String Delete(int id);
}