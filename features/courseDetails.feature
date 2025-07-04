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

Feature: Details of Course

  Scenario: Search course
    Given User is on homepage
    When search course in search bar
    Then course details page opens
    
	Scenario: Filter out Language
    Given hidden languages in page need to click to show all
    When click on specific language
    Then language get selected
   
 	Scenario: Filter out level
    Given levels section
    When click on specific level
    Then level get selected

	Scenario: Select first data from course
    Given cards of first course
    When get title rating and duration
    Then title rating and duration for first course are fetched
    
	Scenario: Select second data from course
    Given cards of second course
    When get title rating and duration
    Then title rating and duration for second course are fetched