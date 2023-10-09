Feature: Login Fail with valid mail and empty password

  Scenario: A user goes to page and tries login without any credentials then gets errors
    Given user writes his mail "" and password ""
    When user clicks login button
    Then  error "Epic sadface: Password is required" is displayed
