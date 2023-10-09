Feature: Login Fail with empty mail and valid password

  Scenario: A user goes to page and tries login without any credentials then gets errors
    Given user writes his mail "" and password "secret_sauce"
    When user clicks login button
    Then  error "Epic sadface: Username is required" is displayed