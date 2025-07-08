Feature: App Store and Play Store screenshots

  Scenario: Take screenshots of App Store and Play Store pages
    Given the user is on the homepage
    Then the user clicks the App Store link
    Then a screenshot of the App Store page is taken
    When the user navigates back
    And the user clicks the Play Store link
    Then a screenshot of the Play Store page is taken
    And the user navigates back to the homepage
