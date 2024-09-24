package com.tdd.productservice.config;

import com.tdd.productservice.repository.ProductServiceRepository;
import com.tdd.productservice.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ProductService productService(ProductServiceRepository productServiceRepository) {
        return new ProductService(productServiceRepository);
    }
}