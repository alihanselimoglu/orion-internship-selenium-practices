Feature: Trendyol Login Feature

  Background:
    Given User is on the Trendyol homepage

  Scenario: Verify successful login with valid credentials
    When User accepts the cookies
    And User clicks on the login button on the header
    And User enters valid username
    And User enters valid password
    And User clicks on the login button
    Then User should see the "Hesabım" text after login

  Scenario: Verify error message is shown after trying with invalid username
    When User accepts the cookies
    And User clicks on the login button on the header
    And User enters invalid username
    And User enters valid password
    And User clicks on the login button
    Then User should see an error message
