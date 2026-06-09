Feature: Role Dosen SIMPENSI

  Scenario: Dosen berhasil melihat dashboard
    Given the user is on the application login page
    When the user selects their active "dosen" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then dashboard dosen tampil

  Scenario: Dosen tidak dapat mengakses halaman admin
    Given the user is on the application login page
    When the user selects their active "dosen" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then dashboard dosen tampil
    When dosen mencoba membuka halaman admin
    Then akses halaman admin ditolak

  Scenario: Dosen berhasil membuka detail jadwal sidang dari dashboard
    Given the user is on the application login page
    When the user selects their active "dosen" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then dashboard dosen tampil
    When dosen membuka detail jadwal dari dashboard
    Then modal detail jadwal tampil
    And tombol download naskah tersedia