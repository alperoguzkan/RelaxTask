Feature: Flight
  Scenario: A user goes to page and tries login without any credentials then gets errors
    Given user goes to flights tab
    And clicks to multi-city option
    When Adds a first flight from "Malta" to "that"
