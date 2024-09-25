package com.tdd.productservice.service;

import com.tdd.productservice.model.Product;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    List<Product> getProducts(); // Get all products
    Optional<List<Product>> searchProducts(Product product); // Search for products
}