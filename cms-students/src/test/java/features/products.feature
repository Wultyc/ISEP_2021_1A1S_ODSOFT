Feature: Product Test

  Scenario: Create Product
    Given Navigate to Product page
    When I click to add Product
    And I fill the Product fields
    And I click to save Product
    Then Save Product


  Scenario: Delete Product
    Given Navigate to Product page
    When I click to add Product
    And I fill the Product fields
    And I click to save Product
    And I click to delete Product
    Then Delete Product

    
  Scenario: Edit Product
    Given Navigate to Product page
    When I click to add Product
    And I fill the Product fields
    And I click to save Product
    When I click to edit Product
    And Edit Product fields
    And I click to save Product
    Then Edit Product
