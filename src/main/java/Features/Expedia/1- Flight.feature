Feature: Flight
  Scenario: A user goes to page and tries login without any credentials then gets errors
    Given user goes to flights tab
    And clicks to multi-city option
    When Adds a first flight from "MLT" to "BCN"
    And Adds a second flight from "BCN" to "CTA"
    And Clicks to Add another flight
    And Adds a third flight from "CTA" to "MLT"
    And clicks search button