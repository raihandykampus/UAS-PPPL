Feature: Admin Penjadwalan Dummy Data

  Scenario: TC-001 Admin berhasil menjadwalkan sidang menggunakan dummy data
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin memilih dosen penguji
    And admin memilih ruangan sidang
    And admin memilih tanggal sidang
    And admin menyimpan jadwal
    Then status pengajuan berubah menjadi Dijadwalkan
    And jadwal tampil pada kalender utama

  Scenario: TC-002 Dosen penguji kosong
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin mengosongkan dosen penguji
    And admin memilih ruangan sidang
    And admin memilih tanggal sidang
    And admin menyimpan jadwal
    Then sistem menampilkan error "Dosen penguji wajib diisi"
    And status pengajuan tetap Siap Dijadwalkan

  Scenario: TC-003 Ruangan kosong
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin memilih dosen penguji
    And admin mengosongkan ruangan sidang
    And admin memilih tanggal sidang
    And admin menyimpan jadwal
    Then sistem menampilkan error "Ruangan sidang wajib diisi"
    And status pengajuan tetap Siap Dijadwalkan

  Scenario: TC-004 Tanggal sidang kosong
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin memilih dosen penguji
    And admin memilih ruangan sidang
    And admin mengosongkan tanggal sidang
    And admin menyimpan jadwal
    Then sistem menampilkan error "Tanggal sidang wajib diisi"
    And status pengajuan tetap Siap Dijadwalkan

  Scenario: TC-005 Jadwal bentrok dengan jadwal lain
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin memilih dosen penguji
    And admin memilih ruangan sidang yang sudah terpakai
    And admin memilih tanggal sidang yang sudah terpakai
    And admin menyimpan jadwal
    Then sistem menampilkan error "Jadwal bentrok dengan jadwal lain"
    And status pengajuan tetap Siap Dijadwalkan

  Scenario: TC-006 Semua field kosong
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin mengosongkan semua field penjadwalan
    And admin menyimpan jadwal
    Then sistem menampilkan semua error wajib isi
    And status pengajuan tetap Siap Dijadwalkan

  Scenario: TC-007 Tanggal sidang valid batas minimum
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin memilih dosen penguji
    And admin memilih ruangan sidang
    And admin memilih tanggal sidang batas minimum
    And admin menyimpan jadwal
    Then status pengajuan berubah menjadi Dijadwalkan
    And jadwal tampil pada kalender utama

  Scenario: TC-008 Tanggal sidang valid batas maksimum
    Given tersedia data pengajuan dummy yang sudah diverifikasi
    When admin memilih dosen penguji
    And admin memilih ruangan sidang
    And admin memilih tanggal sidang batas maksimum
    And admin menyimpan jadwal
    Then status pengajuan berubah menjadi Dijadwalkan
    And jadwal tampil pada kalender utama
