Feature: Extract and display online degree program information

  Scenario: Filter and display online degree programs by level and subject
    Given the user is on the homepage
    When the user navigates to the Online Degrees section
    And selects a random program level filter
    And selects a random subject filter
    Then the application should display degree card details
