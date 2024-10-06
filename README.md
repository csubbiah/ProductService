<b>Product Service</b>

<b>Prerequisite:</b>
 Open UnitTest class in rightside window of IntelliJ IDE and corresponding class definition in leftside window 
1. Begin with ServiceClass test method
2. Test Data / Repository layer is something we would like to mock. So @Mock applied to Repository instance
3. Service class is something we would inject the mock so, @InjectMocks to Service instance

<b>Functionality getAllProducts steps</b><br/>
5. Start with each atomic test in Service - getAllProducts() - Red<br/>
6. Build up the corresponding getAllProducts in Service class with constant return value - Green<br/>
7. Replace the constant return value in Service class with Repository - Introduce the Repository layer so persistence is handled - Blue (Refactor)

<b>Application test for getProducts</b> <br/>
Request: curl "http://localhost:8080/products" <br/>
Response: [{"id":1,"name":"Product 1","price":100.0,"quantity":10},{"id":2,"name":"Product 2","price":200.0,"quantity":20},{"id":3,"name":"Product 3","price":300.0,"quantity":30}] <br/>

<b>Functionality searchProducts steps</b> <br/>
8. Start with each atomic test in Service - searchProducts() - Red <br/>
9. Build up the corresponding searchProducts in Service class with constant return value - Green <br/>
10. Replace the constant return value in Service class with Repository - Introduce the Repository layer so persistence is handled - Blue1 (Refactor) <br/>
11. Introduce Validation of the input search parameter in Product Object using Validator annotations - Blue2 (Refactor) <br/>
12. Introduce Validation of the input search parameter in Controller searchProducts method to RequestBody using Validate annotation - Blue3 (Refactor) <br/>

<b>Application test for searchProducts & parameter to search for</b> <br/>
Request: curl -X GET "http://localhost:8080/products/search?id=1&name=Product%201&price=100&quantity=10" <br/>
Response: [{"id":1,"name":"Product 1","price":100.0,"quantity":10}] <br/>


<B>Adding Cucumber BDD on top of existing project</B>

<u>Add Cucumber dependencies to your build.gradle file:  </u>

    testImplementation 'io.cucumber:cucumber-java:7.11.1'
    testImplementation 'io.cucumber:cucumber-spring:7.11.1'
    testImplementation 'io.cucumber:cucumber-junit:7.11.1'
    testImplementation 'io.cucumber:cucumber-junit-platform-engine:7.11.1' // Add this line
		
Create a feature file: Create a new file src/test/resources/features/product.feature with the following content:  

	Feature: Product Management

	  Scenario: Get all products
	    Given the product repository is initialized
	    When I request all products
	    Then I should get a list of 2 products
	
	  Scenario: Search for a product by criteria
	    Given the product repository is initialized
	    When I search for a product with ID 1, price 100, and quantity 10
	    Then I should get the product "Product 1"
		
Create step definitions: Create a new file src/test/java/com/tdd/productservice/steps/ProductSteps.java with the following content:  

	package com.tdd.productservice.steps;

	import com.tdd.productservice.model.Product;
	import com.tdd.productservice.service.IProductService;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	
	import java.util.List;
	import java.util.Optional;
	
	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.junit.jupiter.api.Assertions.assertTrue;

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
Create a test runner: Create a new file src/test/java/com/tdd/productservice/CucumberTestRunner.java with the following content:  
		package com.tdd.productservice;
		
		import io.cucumber.junit.Cucumber;
		import io.cucumber.junit.CucumberOptions;
		import org.junit.runner.RunWith;
		
		@RunWith(Cucumber.class)
		@CucumberOptions(
		    features = "src/test/resources/features",
		    glue = "com.tdd.productservice.steps",
		    plugin = {"pretty", "html:target/cucumber-reports"}
		)
		public class CucumberTestRunner {}
