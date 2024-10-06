Feature: Product Management

  Scenario: Get all products
    Given the product repository is initialized
    When I request all products
    Then I should get a list of 3 products

  Scenario: Search for a product by criteria
    Given the product repository is initialized
    When I search for a product with ID 1, price 100, and quantity 10
    Then I should get the product "Product 1"