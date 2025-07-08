Feature: Extract and display partner information

  Scenario: Display all India partner links, logos, and names
    Given the user is on the homepage
    Then the user navigates to the Partners section
    And opens the India Partners list
    Then the application should display all partner links, logo visibility, and names
