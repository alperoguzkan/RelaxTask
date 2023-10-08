Feature: Login Fail without credentials

  Scenario: A user goes to page and tries login without any credentials then gets errors
    And user writes his mail "standart user" and password ""
    When user clicks login button
    Then  error "Epic sadface: Username is required" is displayed