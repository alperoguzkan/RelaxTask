Feature: Flight
  Scenario: A user goes to page and tries login without any credentials then gets errors
    Given user goes to flights tab
    And clicks to multi-city option
    And clicks to "4" adults
    And Clicks to Add another flight
    When Adds a first flight from "MLT" to "BCN" of date "October 19, 2023"
    And Adds a second flight from "BCN" to "CTA" of date "October 22, 2023"
    And Adds a third flight from "CTA" to "MLT" of date "October 25, 2023"
    And clicks search button
    Then flights loaded
    And check is page is redirected
    And select first flight for each flight
    And check if sum of individual flights are matching with final amount