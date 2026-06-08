Feature: Google SSO Logout

  @logout
  Scenario: Successful logout
    Given the user is logged in to the dashboard with "mahasiswa" credentials
    When the user initiates logout
    Then the user should land successfully on the login page
