package com.tdd.productservice.steps;

import com.tdd.productservice.model.Product;
import com.tdd.productservice.service.IProductService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest
public class ProductSteps {

    @Autowired
    private IProductService productService;

    private List<Product> products;
    private Optional<List<Product>> searchResult;

    @Given("the product repository is initialized")
    public void theProductRepositoryIsInitialized() {
        products = productService.getProducts();
    }

    @When("I request all products")
    public void iRequestAllProducts() {
        products = productService.getProducts();
    }

    @Then("I should get a list of {int} products")
    public void iShouldGetAListOfProducts(int count) {
        assertEquals(count, products.size());
    }

    @When("I search for a product with ID {int}, price {double}, and quantity {int}")
    public void iSearchForAProductWithIDPriceAndQuantity(int id, double price, int quantity) {
        Product searchCriteria = Product.builder().id(id).price(price).quantity(quantity).build();
        searchResult = productService.searchProducts(searchCriteria);
    }

    @Then("I should get the product {string}")
    public void iShouldGetTheProduct(String productName) {
        assertTrue(searchResult.isPresent());
        List<Product> productList = searchResult.get();
        assertEquals(1, productList.size());
        assertEquals(productName, productList.get(0).getName());
    }
}