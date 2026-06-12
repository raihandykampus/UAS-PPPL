# Bug Report Admin Penjadwalan SIMPENSI

## BUG-001

| Field | Detail |
| --- | --- |
| Judul | Tombol Verifikasi dan Tolak masih tampil pada pengajuan berstatus DIJADWALKAN |
| Modul | Admin Penjadwalan - Detail Pengajuan |
| Environment | Chrome profile test, `https://pad-1.vercel.app` |
| Preconditions | Admin sudah login dan terdapat pengajuan dengan status `DIJADWALKAN` |
| Steps to Reproduce | Buka halaman Pengajuan; buka detail pengajuan mahasiswa; amati tombol aksi pada detail pengajuan |
| Expected | Tombol Verifikasi/Tolak tidak tampil atau disabled pada status DIJADWALKAN |
| Actual | Tombol masih tampil |
| Severity | Medium |
| Priority | Medium |
| Status | Open |
| Evidence | Ditemukan saat eksplorasi manual pada flow Detail Pengajuan |

## BUG-002

| Field | Detail |
| --- | --- |
| Judul | Form Penjadwalan kosong meskipun terdapat data yang sudah diproses |
| Modul | Admin Penjadwalan - Form Penjadwalan |
| Environment | Chrome profile test, `https://pad-1.vercel.app` |
| Preconditions | Admin sudah login dan terdapat data pengajuan yang sudah diproses |
| Steps to Reproduce | Buka halaman Form Penjadwalan; amati daftar pengajuan yang siap dijadwalkan |
| Expected | Data yang siap dijadwalkan tampil atau sistem memberi informasi status dengan jelas |
| Actual | Halaman menampilkan `Tidak ada pengajuan yang siap dijadwalkan` |
| Severity | Medium |
| Priority | Medium |
| Status | Open |
| Evidence | Ditemukan saat eksplorasi manual pada flow Form Penjadwalan |
