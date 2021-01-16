Feature: Batch Test

  Scenario: Create Batch
    Given Navigate to Batch page
    When I click to add Batch
    And I fill the Batch fields
    And I click to save Batch
    Then Save Batch


  Scenario: Delete Batch
    Given Navigate to Batch page
    When I click to add Batch
    And I fill the Batch fields
    And I click to save Batch
    And I click to delete Batch
    Then Delete Batch

    
  Scenario: Edit Batch
    Given Navigate to Batch page
    When I click to add Batch
    And I fill the Batch fields
    And I click to save Batch
    When I click to edit Batch
    And Edit Batch fields
    And I click to save Batch
    Then Edit Batch
