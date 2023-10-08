Feature: Login Fail invalid mail and password

  Scenario: A user goes to page and tries login without any credentials then gets errors
    And user writes his mail "wrong mail" and password "wrong password  "
    When user clicks login button
    Then  error "Epic sadface: Username and password do not match any user in this service" is displayed
