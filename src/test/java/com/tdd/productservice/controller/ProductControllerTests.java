package com.tdd.productservice.controller;



import com.tdd.productservice.model.Product;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mockito;


import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@WebMvcTest(value = ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTests {

    List<Product> products = new ArrayList<>(1);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    void getAllProducts(){

        fail("Products list is not present");
//        // Arrange
//        Product product1 = Product.builder().id(1).name("Product 1").price(100).quantity(10).build();
//        Product product2 = Product.builder().id(2).name("Product 2").price(200).quantity(20).build();
//        Mockito.when(productService.getProducts()).thenReturn(Arrays.asList(product1, product2));
//        //Act
//        Optional<List<Product>> products = Optional.ofNullable(productController.getProducts());
//        //Assert
//        // Assert
//        if (products.isPresent()) {
//            assertEquals(2, products.get().size());
//            assertEquals("Product 1", products.get().get(0).getName());
//            assertEquals("Product 2", products.get().get(1).getName());
//        } else {
//            fail("Products list is not present");
//        }
    }

}