Feature: Unsuccessful registration

  Scenario: User register fails with mail only and no password
    Given user sends a post request with payload with email as "eve.holt@reqres.in" and password as "" and gets "400"
    And gets "Missing password" error


