package com.tdd.productservice.service;

import com.tdd.productservice.model.Product;
import com.tdd.productservice.repository.ProductServiceRepository;


import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService implements IProductService {

    ProductServiceRepository productServiceRepository; // Assume this is initialized elsewhere

    //used for TDD tests
    public ProductService(ProductServiceRepository productServiceRepository) {

        this.productServiceRepository = productServiceRepository;
    }

    /**
     * @return list of All Available Products
     */
    @Override
    public List<Product> getProducts() {
        return productServiceRepository.getProducts();
    }

    @Override
    public List<Product> searchProducts(Product product) {
        validateProduct(product);
        return productServiceRepository.searchProduct(product);
    }

    private void validateProduct(Product product) {
        if (product.getName() != null && product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }
}