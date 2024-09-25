package com.tdd.productservice.controller;


import com.tdd.productservice.model.Product;
import com.tdd.productservice.service.IProductService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {

        return productService.getProducts();
    }

    @GetMapping("/search")
    public Optional<List<Product>> searchProducts(@Valid @RequestBody Product product) {

        return productService.searchProducts(product);
    }
}