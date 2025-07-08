Feature: Government Contact Form Submission

  Scenario: Submit a valid government contact form and verify confirmation
    Given the user is on the homepage
    Then the user navigates to the For Government section
    When the user clicks the Contact Sales button
    And fills out the government form with valid details
    And submits the government request form
    Then the application should display a confirmation message