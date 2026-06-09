Feature: Role Dosen SIMPENSI

  Scenario: Dosen berhasil melihat dashboard
    Given the user is on the application login page
    When the user selects their active "dosen" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then the user should land successfully on the dashboard

  Scenario: Dosen tidak dapat mengakses halaman admin
    Given the user is on the application login page
    When the user selects their active "dosen" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then the user should land successfully on the dashboard
    When dosen mencoba membuka halaman admin
    Then akses halaman admin ditolak