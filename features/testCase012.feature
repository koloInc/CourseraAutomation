@sanity @regression
Feature: Verify Coursera job listings in India

Scenario: Filter and display job listings in India
  Given the user is on the homepage
  When the user navigates to the Careers page
  And clicks on See Open Positions
  And filters jobs by country India
  Then the user should see a list of job openings in India
