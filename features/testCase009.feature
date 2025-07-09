Feature: Footer Section Validation

  Scenario: Extract and print all footer titles and their links
    Given the user is on the homepage
    When the user retrieves all footer sections
    Then the footer titles and their links should be printed in the console
