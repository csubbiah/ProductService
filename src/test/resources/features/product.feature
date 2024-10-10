@ProductManagement
Feature: Product Management

  Background:
    Given the product repository is initialized

  Scenario Outline: Get all products

    When I request all products
    Then I should get a list of <count> products
    Examples:
      | count |
      | 2     |

    @Search
  Scenario Outline: Search for a product by criteria

    When I search for a product with ID <id>, price <price>, and quantity <quantity>
    Then I should get the product <productName>
      @SearchById
    Examples:
      | id | price | quantity | productName |
      | 1  | 100   | 10       | "Product 1" |
      | 2  | 200   | 20       | "Product 2" |