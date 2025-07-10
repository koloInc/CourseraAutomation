Feature: Extract and display partner information

  Scenario: Display all partner links, logos, and names for a selected country
    Given the user is on the homepage
    Then the user navigates to the Partners section
    And opens the country Partners list
    Then the application should display all partner links, logo visibility, and names
