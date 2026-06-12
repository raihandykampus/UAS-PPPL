Feature: Role Dosen SIMPENSI

  Scenario: Dosen mengakses minimal lima tab sampai membuka berkas naskah sidang
    Given the user is on the application login page
    When the user selects their active "dosen" credentials
    And the user initiates login with Google
    And selects their authenticated Google account
    Then dashboard dosen tampil

    When dosen membuka halaman detail jadwal sidebar
    Then halaman detail jadwal sidebar valid

    When dosen membuka detail jadwal dari halaman detail jadwal
    Then modal detail jadwal tampil
    And tombol download naskah tersedia

    When dosen membuka berkas naskah
    Then halaman berkas naskah berhasil terbuka

    When dosen kembali ke tab aplikasi utama
    And dosen membuka halaman riwayat ujian
    Then halaman riwayat ujian dosen valid

    When dosen membuka halaman notifikasi
    Then halaman notifikasi dosen valid