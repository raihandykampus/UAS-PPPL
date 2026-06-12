# Bug Report Admin Penjadwalan Dummy Data

Bug report ini disusun dari eksplorasi kebutuhan Admin Penjadwalan dan disimulasikan pada layer automation menggunakan dummy data. Tujuannya adalah menjaga kontribusi pengujian Admin Penjadwalan tetap dapat dipresentasikan meskipun modul verifikasi pengajuan belum menyediakan data siap dijadwalkan.

## BUG-DUMMY-001

| Field | Detail |
| --- | --- |
| Judul | Validasi field wajib pada Form Penjadwalan perlu mencegah penyimpanan data kosong |
| Modul | Admin Penjadwalan - Form Penjadwalan |
| Preconditions | Admin memiliki pengajuan yang sudah diverifikasi dan siap dijadwalkan |
| Steps to Reproduce | Kosongkan dosen penguji, ruangan sidang, dan tanggal sidang; klik Simpan Jadwal |
| Expected | Sistem menampilkan error untuk setiap field wajib dan status tetap Siap Dijadwalkan |
| Actual | Pada simulasi dummy, field kosong ditolak oleh automation layer; validasi ini perlu dipastikan juga pada aplikasi real |
| Severity | Medium |
| Priority | Medium |
| Status | Open for real-app verification |
| Test Case Terkait | TC-002, TC-003, TC-004, TC-006 |

## BUG-DUMMY-002

| Field | Detail |
| --- | --- |
| Judul | Jadwal bentrok harus ditolak saat ruangan dan tanggal sudah digunakan |
| Modul | Admin Penjadwalan - Form Penjadwalan dan Kalender Utama |
| Preconditions | Admin memiliki pengajuan siap dijadwalkan dan terdapat jadwal lain pada ruangan/tanggal yang sama |
| Steps to Reproduce | Pilih ruangan yang sudah terpakai; pilih tanggal yang sudah terpakai; klik Simpan Jadwal |
| Expected | Sistem menampilkan error jadwal bentrok dan tidak membuat event duplikat pada Kalender Utama |
| Actual | Pada simulasi dummy, jadwal bentrok ditolak oleh automation layer; perilaku ini perlu diverifikasi pada aplikasi real setelah data siap dijadwalkan tersedia |
| Severity | High |
| Priority | Medium |
| Status | Open for real-app verification |
| Test Case Terkait | TC-005 |
