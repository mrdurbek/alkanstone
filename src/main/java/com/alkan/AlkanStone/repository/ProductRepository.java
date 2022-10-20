package com.alkan.AlkanStone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkan.AlkanStone.entity.Product;

public interface ProductRepository extends JpaRepository<Product , Integer> {

}
