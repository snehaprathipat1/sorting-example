@test
Feature: user login to the saucedemo and selects the highest price item and add to cart

  Scenario: user login wih valid credentials
    Given I am on the home page
    When I enter user-name and password
    And I click on login button
    And I should see app_logo as Swag Labs
    When I select highest price item
    And I add it to cart
    And I click on the basket
    Then I should see the item in basket


