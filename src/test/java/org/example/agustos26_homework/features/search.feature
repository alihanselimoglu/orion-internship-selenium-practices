Feature: Search Functionality

  Background:
    Given User is on the Trendyol homepage

  Scenario: Search for "Telefon"
    When User accepts the cookies
    When User searches for "Telefon"
    Then User should see the search results for "Telefon"

  Scenario: Verify the number of results for "Telefon"
    When User accepts the cookies
    When User searches for "Telefon"
    Then User should see the search result count contains "100.000+"

  Scenario: Add an "Telefon" item to the cart
    When User accepts the cookies
    When User searches for "Telefon"
    And User selects the first product
    And User adds the item to the cart
    Then User should see the item in the cart

  Scenario: Verify the number of the items in the cart
    When User accepts the cookies
    When User searches for "Telefon"
    And User selects the first product
    And User adds the item to the cart
    Then User should see the item in the cart
    Then User should see items in the cart
