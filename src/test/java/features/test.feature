@tag
Feature: Test Amazon Web Application
  I want to use this template for my feature file

  @tag1
  Scenario: Validate Sorting functionality
    Given Navigate to "www.amazon.com" application
    And As a Guest user search for Nikon Products
    And Sort from High to Low price
    When Results are sorted 
    Then Select second product from the result
    And Verify Product should be titled as "Nikon D3X"
    