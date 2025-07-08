Feature: Government form validation

  Scenario: Submit invalid email and phone number and verify error messages
    Given the user is on the homepage and navigates to the For Business section
    When the user fills out the government request form with invalid email and phone number
    And submits the request for information
    Then the application should display appropriate error messages for email and phone number
