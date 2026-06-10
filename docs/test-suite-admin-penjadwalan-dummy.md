# Test Suite Admin Penjadwalan Dummy Data

Test suite ini dibuat untuk memutus dependensi automation Admin Penjadwalan dari modul verifikasi pengajuan yang belum menghasilkan data siap dijadwalkan. Dummy data hanya digunakan pada layer automation testing dan tidak mengubah source aplikasi PAD, API, atau database.

| Test Case ID | Scenario | Halaman | Preconditions | Test Steps | Test Data | Expected Result | Actual Result | Status | Test Method |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| TC-001 | Berhasil menjadwalkan sidang (data valid) | Form Penjadwalan, Kalender Utama | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Pilih dosen penguji; pilih ruangan; pilih tanggal; simpan jadwal | Dosen: Dr. Budi Santoso; Ruangan: Ruang Sidang 1; Tanggal: 2026-06-15 | Status berubah menjadi Dijadwalkan dan jadwal tampil pada Kalender Utama | Sesuai simulasi automation | Passed | Equivalence Partitioning, Positive Test |
| TC-002 | Dosen penguji kosong | Form Penjadwalan | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Kosongkan dosen penguji; pilih ruangan; pilih tanggal; simpan jadwal | Dosen: kosong; Ruangan: Ruang Sidang 1; Tanggal: 2026-06-15 | Sistem menampilkan error `Dosen penguji wajib diisi`; status tetap Siap Dijadwalkan | Sesuai simulasi automation | Passed | Equivalence Partitioning, Negative Test |
| TC-003 | Ruangan kosong | Form Penjadwalan | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Pilih dosen penguji; kosongkan ruangan; pilih tanggal; simpan jadwal | Dosen: Dr. Budi Santoso; Ruangan: kosong; Tanggal: 2026-06-15 | Sistem menampilkan error `Ruangan sidang wajib diisi`; status tetap Siap Dijadwalkan | Sesuai simulasi automation | Passed | Equivalence Partitioning, Negative Test |
| TC-004 | Tanggal sidang kosong | Form Penjadwalan | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Pilih dosen penguji; pilih ruangan; kosongkan tanggal; simpan jadwal | Dosen: Dr. Budi Santoso; Ruangan: Ruang Sidang 1; Tanggal: kosong | Sistem menampilkan error `Tanggal sidang wajib diisi`; status tetap Siap Dijadwalkan | Sesuai simulasi automation | Passed | Equivalence Partitioning, Negative Test |
| TC-005 | Jadwal bentrok dengan jadwal lain | Form Penjadwalan | Tersedia pengajuan dummy dan terdapat jadwal lain pada ruangan/tanggal yang sama | Pilih dosen penguji; pilih ruangan terpakai; pilih tanggal terpakai; simpan jadwal | Ruangan: Ruang Sidang Bentrok; Tanggal: 2026-06-20 | Sistem menampilkan error `Jadwal bentrok dengan jadwal lain`; status tetap Siap Dijadwalkan | Sesuai simulasi automation | Passed | Equivalence Partitioning, Negative Test |
| TC-006 | Semua field kosong | Form Penjadwalan | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Kosongkan dosen penguji, ruangan, dan tanggal; simpan jadwal | Dosen: kosong; Ruangan: kosong; Tanggal: kosong | Semua error wajib isi muncul; status tetap Siap Dijadwalkan | Sesuai simulasi automation | Passed | Boundary Value Analysis, Negative Test |
| TC-007 | Tanggal sidang valid batas minimum | Form Penjadwalan, Kalender Utama | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Pilih dosen penguji; pilih ruangan; pilih tanggal batas minimum; simpan jadwal | Tanggal minimum valid: 2026-06-10 | Status berubah menjadi Dijadwalkan dan jadwal tampil pada Kalender Utama | Sesuai simulasi automation | Passed | Boundary Value Analysis, Positive Test |
| TC-008 | Tanggal sidang valid batas maksimum | Form Penjadwalan, Kalender Utama | Tersedia pengajuan dummy berstatus Siap Dijadwalkan | Pilih dosen penguji; pilih ruangan; pilih tanggal batas maksimum; simpan jadwal | Tanggal maksimum valid: 2026-12-31 | Status berubah menjadi Dijadwalkan dan jadwal tampil pada Kalender Utama | Sesuai simulasi automation | Passed | Boundary Value Analysis, Positive Test |

## Metode Pengujian

- Equivalence Partitioning membagi input menjadi kelas data valid, field kosong, dan jadwal bentrok.
- Boundary Value Analysis digunakan pada kondisi semua field kosong sebagai batas minimum kelengkapan input dan tanggal sidang pada batas minimum serta maksimum yang masih valid.
- Positive Test memastikan data dummy valid dapat menghasilkan jadwal.
- Negative Test memastikan data tidak lengkap atau bentrok tidak mengubah status menjadi Dijadwalkan.

## Dummy Data

| Field | Nilai |
| --- | --- |
| Mahasiswa | Dimas Satriaa |
| Judul | Sistem Informasi Penjadwalan Sidang |
| Status awal | Siap Dijadwalkan |
| Dosen default | Dr. Budi Santoso |
| Ruangan default | Ruang Sidang 1 |
| Tanggal default | 2026-06-15 |
| Tanggal minimum valid | 2026-06-10 |
| Tanggal maksimum valid | 2026-12-31 |
| Ruangan bentrok | Ruang Sidang Bentrok |
| Tanggal bentrok | 2026-06-20 |
