Feature: Admin Penjadwalan

  Scenario: Admin melihat dashboard
    Given admin membuka halaman dashboard
    Then dashboard admin berhasil ditampilkan

  Scenario: Admin melihat daftar pengajuan
    Given admin membuka halaman pengajuan
    Then daftar pengajuan berhasil ditampilkan

  Scenario: Admin melihat detail pengajuan
    Given admin membuka halaman pengajuan
    When admin membuka detail pengajuan mahasiswa
    Then detail pengajuan berhasil ditampilkan

  Scenario: Admin melihat formulir penjadwalan
    Given admin membuka halaman formulir penjadwalan
    Then halaman formulir penjadwalan berhasil ditampilkan

  Scenario: Admin melihat event pada kalender utama
    Given admin membuka halaman kalender utama
    Then event ujian TA berhasil ditampilkan

  Scenario: Admin melihat daftar ruangan
    Given admin membuka halaman manajemen ruangan
    Then daftar ruangan berhasil ditampilkan
