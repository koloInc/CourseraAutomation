Feature: Display available languages for a searched course

  Scenario: List all available languages for Web Development courses
    Given the user is on the homepage
    When the user searches for course
    And filters the results by language "English"
    Then the list of available languages should be displayed
