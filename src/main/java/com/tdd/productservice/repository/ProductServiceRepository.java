package com.tdd.productservice.repository;

import com.tdd.productservice.model.Product;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Getter
@Repository
public class ProductServiceRepository {

    private final List<Product> products;

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

    public Optional<List<Product>> searchProduct(Product product) {
        AtomicReference<Optional<List<Product>>> result = new AtomicReference<>(Optional.empty());
        for (Product p : products) {
            if (product.getName() != null && !product.getName().isEmpty() && !p.getName().equals(product.getName())
            || product.getPrice() > 0 && p.getPrice() != product.getPrice() || product.getQuantity() > 0 && p.getQuantity() != product.getQuantity()) {
                continue;
            }
            result.get().ifPresentOrElse(
                    list -> list.add(p),
                    () -> {
                        List<Product> newList = new ArrayList<>();
                        newList.add(p);
                        result.set(Optional.of(newList));
                    }
            );
        }
        return result.get();
    }

    // Add other repository methods as needed
}