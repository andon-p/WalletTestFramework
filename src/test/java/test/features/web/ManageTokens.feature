@Web
Feature: Manage tokens

  Scenario Outline: Manage a token
    Given I am on the main page
    When I search for the "<token>" token
    And The "<token>" token on "<network>" is displayed
    And I "<action>" the "<token>" token
    And I go back to the main screen
    Then the "<token>" token should "<beInList>" in the list

    Examples:
      | token | network | action | beInList |
      | Bonk  | Solana  | add    | be       |
      | NEAR  | NEAR    | remove | not be   |