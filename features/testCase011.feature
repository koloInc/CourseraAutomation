Feature: Verify Coursera social media links

  Scenario: Open and verify each social media link from the Coursera homepage
    Given the user is on the homepage
    When the user scrolls to the footer
    Then the user opens each social media link in a new tab
    And verifies the title and URL of each page