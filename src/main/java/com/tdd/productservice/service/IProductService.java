package com.tdd.productservice.service;

import com.tdd.productservice.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> getProducts(); // Get all products
    List<Product> searchProducts(Product product); // Search for products
}