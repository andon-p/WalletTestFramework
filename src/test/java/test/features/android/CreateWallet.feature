@Android
Feature: Create wallet

  Scenario: Create new wallet
    Given I am on the main page
    When I create a new wallet
    Then the wallet should be created successfully

  Scenario: Create wallet without backup
    Given I am on the main page
    When I create a new wallet without backup
    Then the wallet should be created successfully