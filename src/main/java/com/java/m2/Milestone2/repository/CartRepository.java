package com.java.m2.Milestone2.repository;

import com.java.m2.Milestone2.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartProduct, Integer> {
}