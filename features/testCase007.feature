Feature: View Course Content

  Scenario: User searches for a course and views its content
    Given the user is on the homepage
 	 	When the user searches for "Web Development"
    And selects the first course from the results
    Then the course page should display the course title
    And the total number of modules
    And the course rating
    And the total number of reviews
    And the user clicks on "View all skills"
    Then the skills and learning outcomes should be displayed
