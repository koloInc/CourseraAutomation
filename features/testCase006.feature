Feature: For Business Plan Selection

  Scenario: Select a business plan and display purchase summary
    Given the user is on the homepage
    Then the user navigates to the For Business section
    When the user selects the "For Teams" plan
    And enters a random number of users between 6 and 300
    And selects the quarterly payment option
    Then the application should display the purchase summary
