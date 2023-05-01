Feature: Login

  Scenario Outline:  User with correct credentials is able to login to the application

    Given user navigates to login page by opening Chrome
    When  user enters correct <username> AND <password> values
    Then user is directed to inventory page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |