Feature: Google SSO Authentication

  Scenario: Successful login via Google Single Sign-On
    Given the user is on the application login page
    When the user selects their active "mahasiswa" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then the user should land successfully on the dashboard