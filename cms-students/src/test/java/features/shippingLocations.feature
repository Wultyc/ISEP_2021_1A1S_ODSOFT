Feature: Shipping Location Test

  Scenario: Create Shipping Location
    Given Navigate to Shipping Location page
    When I click to add Shipping Location
    And I fill the Shipping Location fields
    And I click to save Shipping Location
    Then Save Shipping Location


  Scenario: Delete Shipping Location
    Given Navigate to Shipping Location page
    When I click to add Shipping Location
    And I fill the Shipping Location fields
    And I click to save Shipping Location
    And I click to delete Shipping Location
    Then Delete Shipping Location

    
  Scenario: Edit Shipping Location
    Given Navigate to Shipping Location page
    When I click to add Shipping Location
    And I fill the Shipping Location fields
    And I click to save Shipping Location
    When I click to edit Shipping Location
    And Edit Shipping Location fields
    And I click to save Shipping Location
    Then Edit Shipping Location
