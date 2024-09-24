package com.tdd.productservice.repository;

import com.tdd.productservice.model.Product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;



@Repository
public class ProductServiceRepository {

    private List<Product> products = new ArrayList<>();

    public ProductServiceRepository(List<Product> products) {
        this.products = products;
        if(!products.isEmpty()) {
            this.products.addAll(products);
        }
        else {
            this.products.add(Product.builder().id(1).name("Product 1").price(100).quantity(10).build());
            this.products.add(Product.builder().id(2).name("Product 2").price(200).quantity(20).build());
            this.products.add(Product.builder().id(3).name("Product 3").price(300).quantity(30).build());
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> searchProduct(Product product) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (product.getName() != null && !product.getName().isEmpty() && !p.getName().equals(product.getName())
            || product.getPrice() > 0 && p.getPrice() != product.getPrice() || product.getQuantity() > 0 && p.getQuantity() != product.getQuantity()) {
                continue;
            }
            result.add(p);
        }
        return result;
    }

    // Add other repository methods as needed
}