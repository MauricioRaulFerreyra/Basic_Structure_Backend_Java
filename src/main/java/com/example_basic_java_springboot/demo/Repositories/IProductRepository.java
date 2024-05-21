package com.example_basic_java_springboot.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example_basic_java_springboot.demo.Entities.Product;

public interface IProductRepository extends JpaRepository<Product,Long> {

}
