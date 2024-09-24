package com.tdd.productservice.service;

import com.tdd.productservice.model.Product;
import com.tdd.productservice.repository.ProductServiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTests {

    private final Product firstProduct = Product.builder().id(1).name("Product 1").price(100).quantity(10).build();
    private final Product secondProduct = Product.builder().id(2).name("Product 2").price(200).quantity(20).build();
    private List<Product>   products;

    @BeforeEach
    public void setUp() {
        this.products = Arrays.asList(this.firstProduct, this.secondProduct);

    }


    @Mock
    private ProductServiceRepository mockProductServiceRepository;

    @InjectMocks
    private IProductService productService = new ProductService(mockProductServiceRepository);


    @AfterEach
    public void tearDown() {
        //Test cleanup
    }

    @Test
    void getAllProducts() {
        Mockito.when(mockProductServiceRepository.getProducts()).thenReturn(products);
        // Act
        List<Product> result = this.productService.getProducts();
        // Assert
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());
    }
}