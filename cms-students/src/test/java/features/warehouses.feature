Feature: Warehouse Test

  Scenario: Create Warehouse
    Given Navigate to Warehouse page
    When I click to add Warehouse
    And I fill the Warehouse fields
    And I click to save Warehouse
    Then Save Warehouse


  Scenario: Delete Warehouse
    Given Navigate to Warehouse page
    When I click to add Warehouse
    And I fill the Warehouse fields
    And I click to save Warehouse
    And I click to delete Warehouse
    Then Delete Warehouse

    
  Scenario: Edit Warehouse
    Given Navigate to Warehouse page
    When I click to add Warehouse
    And I fill the Warehouse fields
    And I click to save Warehouse
    When I click to edit Warehouse
    And Edit Warehouse
    And Click to save Warehouse
    Then Save Warehouse
