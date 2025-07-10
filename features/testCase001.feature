Feature: Course Search and Details Extraction

Scenario: Search and extract details of multiple courses
  Given the user is on the homepage
  When the user searches for course
  And filters the results by language
  And filters the results by level
  And selects the first 5 courses from the results
  Then the course titles, ratings, and durations should be displayed
