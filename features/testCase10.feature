#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: App Store and Play Store Links

  Scenario: Take screenshots of App Store and Play Store pages
    Given the user is on the homepage
    When the user clicks the App Store link
    Then a screenshot of the App Store page is taken
    When the user navigates back
    And the user clicks the Play Store link
    Then a screenshot of the Play Store page is taken
    And the user navigates back to the homepage

