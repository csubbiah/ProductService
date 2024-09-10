package com.tdd.productservice.controller;

import com.tdd.productservice.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public Product getProducts() {
        return Product.builder()
                .productName("Test Product")
                .price(Long.parseLong("10"))
                .quantity(Long.parseLong("5"))
                .build();
    }
}