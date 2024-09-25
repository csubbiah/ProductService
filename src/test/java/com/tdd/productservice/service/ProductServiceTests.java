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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTests {

    private final Product firstProduct = Product.builder().id(1).name("Product 1").price(100).quantity(10).build();
    private final Product secondProduct = Product.builder().id(2).name("Product 2").price(200).quantity(20).build();
    private List<Product>   products;

    @BeforeEach
    public void setUp() {
        this.products = new ArrayList<>(Arrays.asList(this.firstProduct, this.secondProduct));
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

    @Test
    void givensearch_whencriteriaisID1Price100Qty10_thenreturnfirstproduct() {
        ClearProducts();
        AddProducts(firstProduct);
        Mockito.when(mockProductServiceRepository.searchProduct(firstProduct)).thenReturn(Optional.of(products));
        // Act
        Optional<List<Product>> result = this.productService.searchProducts(firstProduct);
        // Assert
        if (result.isPresent()) {
            List<Product> productList = result.get();
            assertEquals(1, productList.size());
            assertEquals("Product 1", productList.get(0).getName());
            assertEquals(100, productList.get(0).getPrice());
            assertEquals(10, productList.get(0).getQuantity());
        } else {
            fail("Expected product list to be present");
        }
    }

    @Test
    void givensearch_whencriteriaisIDisNullPrice100Qty10_thenreturnEmpty() {
        ClearProducts();
        Mockito.when(mockProductServiceRepository.searchProduct(firstProduct)).thenReturn(Optional.of(products));
        // Act
        Optional<List<Product>> result = this.productService.searchProducts(firstProduct);
        // Assert
        if (result.isPresent()) {
            List<Product> productList = result.get();
            assertEquals(0, productList.size());
        } else {
            fail("Expected product list to be present");
        }
    }

    void ClearProducts() {
        this.products.clear();
    }

    void AddProducts(Product product) {
        this.products.add(product);
    }
}