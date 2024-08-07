Feature: Login

  @positive
  @smoke
  Scenario: Successful login
    Given user enter login page
    When user enters valid credentials
    Then main page opens
    And the page has text in the footer "2024 Sauce Labs. All Rights Reserved."

  @negative
  Scenario Outline: Incorrect login
    Given user enter login page
    When user enters login '<login>' and password '<password>'
    Then error message contains text '<errorMessage>'

    Examples:
      | login           | password          | errorMessage                                |
      | standard_user   | incorrectPassword | Username and password do not match any user |
      | locked_out_user | secret_sauce      | Sorry, this user has been locked out        |
      | standard_user   |                   | Password is required                        |