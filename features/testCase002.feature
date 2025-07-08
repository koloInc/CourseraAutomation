Feature: Display available languages for a searched course

  Scenario: List all available languages for Web Development courses
    Given the user is on the Coursera homepage
    When the user searches for "Web Development"
    And filters the results by language "English"
    And filters the results by level "Beginner"
    Then the list of available languages should be displayed
