# Ringkasan Laporan UAS

## Judul

Pengujian End-to-End Admin Penjadwalan pada Aplikasi SIMPENSI Menggunakan Selenium, Cucumber, dan Page Object Model.

## Tujuan Pengujian

Pengujian bertujuan memastikan user flow Admin Penjadwalan dapat berjalan pada minimal lima halaman utama aplikasi serta mendokumentasikan hasil pengujian, test suite, dan bug report.

## Scope Pengujian

Scope pengujian berfokus pada role Admin Penjadwalan dengan halaman:

1. Dashboard
2. Pengajuan
3. Detail Pengajuan
4. Form Penjadwalan
5. Kalender Utama

Login Google OAuth tidak diuji ulang karena dianggap sebagai precondition menggunakan Chrome profile test yang sudah login.

## User Flow

Admin membuka dashboard, melihat daftar pengajuan, membuka detail pengajuan mahasiswa, mengakses form penjadwalan, lalu memvalidasi jadwal atau event pada Kalender Utama.

Route yang digunakan:

| Halaman | Route |
| --- | --- |
| Dashboard | `https://pad-1.vercel.app/dashboard` |
| Pengajuan | `https://pad-1.vercel.app/pengajuan` |
| Form Penjadwalan | `https://pad-1.vercel.app/form-penjadwalan` |
| Kalender Utama | `https://pad-1.vercel.app/kalender-utama` |
| Ruangan | `https://pad-1.vercel.app/ruangan` |

## Tools

| Tool | Keterangan |
| --- | --- |
| Java 21 | Bahasa pemrograman test automation |
| Maven | Build tool dan test runner |
| Selenium 4.39.0 | Browser automation |
| Cucumber 7.15 | BDD Gherkin syntax |
| JUnit Platform | Eksekusi test Cucumber |
| Chrome profile test | Precondition login admin |

## Implementasi POM

Kode pengujian menggunakan Page Object Model. Page Object admin berada pada `src/test/java/org/example/pages/admin` dan seluruh Page Object admin `.java` mewarisi `BasePage`. Inisialisasi WebDriver tetap dipusatkan di `BasePage`, sehingga Step Definition tidak membuat `new ChromeDriver()` secara langsung.

## Implementasi BDD

Scenario BDD ditulis pada `src/test/resources/features/admin_penjadwalan.feature`. Step Definition admin berada pada `src/test/java/org/example/steps/admin`. Feature memuat lima scenario utama:

1. Admin melihat dashboard
2. Admin melihat daftar pengajuan
3. Admin membuka detail pengajuan
4. Admin melihat formulir penjadwalan
5. Admin melihat event pada kalender utama

## Test Suite

Test suite terdokumentasi pada `docs/test-suite-admin-penjadwalan.md`. Test suite memanfaatkan Equivalence Partitioning dan Boundary Value Analysis serta mencakup Positive Test dan Negative Test.

## Bug Report

Bug report terdokumentasi pada `docs/bug-report-admin-penjadwalan.md`. Bug utama yang dicatat:

1. Tombol Verifikasi dan Tolak masih tampil pada pengajuan berstatus DIJADWALKAN.
2. Form Penjadwalan kosong meskipun terdapat data yang sudah diproses.

## Hasil Eksekusi

Eksekusi terakhir menggunakan `mvn clean test` menghasilkan:

| Metric | Hasil |
| --- | --- |
| Tests run | 5 |
| Failures | 0 |
| Errors | 0 |
| Build | SUCCESS |

Automated report tersedia pada:

- `target/cucumber-report.html`
- `target/cucumber-report.json`

Runner juga mengaktifkan output `pretty`.

## Kesimpulan

Pengujian Admin Penjadwalan telah memenuhi ketentuan UAS: menggunakan satu user flow end-to-end, mencakup minimal lima halaman web, memiliki test suite, menggunakan metode Equivalence Partitioning dan Boundary Value Analysis, menerapkan POM, menggunakan BDD Gherkin dengan Cucumber, menyertakan bug reporting, serta menghasilkan automated Cucumber report.
