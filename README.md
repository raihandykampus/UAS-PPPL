# Selenium Web Testing SIMPENSI

README.md untuk UAS kelompok 12. Beranggotakan:
1. Raihan Esfandyka Suwandi (24/543374/SV/25201) B2
2. Brilian Fatih Wicaksono (24/535871/SV/24320) B1
3. Dimas Satria Widjatmiko (24/541372/SV/24899) B1
4. Abdullah Afif Habiburrohman (24/537611/SV/24441) B2

## Test Case

Test Case yang ada:
1. Autentikasi (Login/logout) & Google SSO: Oleh Abdullah Afif
2. Form Pengajuan Sidang: Oleh Raihan Esfandyka
3. Manajemen Pengaturan Profil: Oleh Abdullah Afif
4. Admin Pengajuan Sidang : Oleh Brilian Fatih Wicaksono
5. Manajemen : Oleh

## Arsitektur

Proyek ini menggunakan Behavior-Driven Development (BDD) dengan Cucumber dan Page Object Model (POM)

## Direktori

```text
src/test/
├── java
│   └── org
│       └── example
│           ├── pages          # PAGE ACTIONS LAYER (Alur logika operasional web)
│           │   ├── admin      # Operasional khusus dashboard Admin
│           │   ├── locators   # UI DEFINITION LAYER (Kamus global elemen XPath/CSS)
│           │   ├── BasePage.java
│           │   ├── LoginPage.java
│           │   └── ...
│           ├── runners        # TEST SUITE RUNNERS (JUnit 5 Suite Configurations)
│           │   ├── RunTest.java
│           │   └── DosenRunTest.java
│           ├── steps          # GLUE LAYER (Penerjemah kalimat Gherkin ke fungsi Java)
│           │   ├── admin
│           │   ├── dosen
│           │   ├── LoginSteps.java
│           │   └── ...
│           └── users          # CONFIGURATION LAYER (Manajemen kredensial enkapsulasi multi-user)
│               ├── UserFactory.java
│               ├── EnvConfig.java
│               └── Roles.java
└── resources
    ├── features               # SKENARIO BDD (Gherkin Business Requirements File)
    ├── features_old           # Arsip riwayat skenario autentikasi dasar
    └── upload_files           # DATA TEST ARTIFACTS (Dokumen untuk pengujian upload)
        ├── smallDocs.pdf      # Berkas valid (< 10MB)
        ├── bigDocs.pdf        # Berkas batas eror (> 10MB)
        └── notPdf.odt         # Berkas format salah
```

# Penjelasan Fitur

## Auth dan Google SSO

### Login

1. Gherkin (login.feature)
Fungsi: Test Case Gherkin untuk Login
Isi: Langkah pembukaan halaman login, pemilihan akun Google SSO, dan verifikasi pendaratan di dashboard.

2. Kode Langkah-langkah/Tahapan (LoginSteps.java)
Fungsi: Tahapan untuk otomatisasi login
Isi: Mengatur peran (role) yang ingin diuji, mengambil email dari Roles.java, dan melakukan assertion teks.

3. Kode Locators (LoginLocators.java)
Fungsi: Locator untuk tiap Id, Class, X-Path
Isi: Locator tombol UGM ID, input email, teks dashboard, dan generator XPath dinamis.

4. Halaman Login (LoginPage.java)
Fungsi: Bentuk dari halaman login
Isi: Letak dari halaman login dan fitur-fitur yang ada untuk testing.

5. Misc (Afif.java, UserFactory.java, EnvConfig.java)
Fungsi: Injecting akun google yang ada pada chrome
Isi: `EnvConfig` menentukan user yang aktif. `Afif.java` injecting `--user-data-dir` dari folder `.config` Linux Fedora.

### Logout

1. Gherkin (logout.feature)
Fungsi: Skenario pengujian keluar dari sistem.
Isi: Langkah prasyarat pengguna harus sudah masuk sebagai mahasiswa, menekan tombol keluar, dan memastikan halaman kembali ke gerbang awal.

2. Kode Langkah-langkah/Tahapan (LogoutSteps.java)
Fungsi: Tahapan untuk otomatisasi proses logout.
Isi: Memastikan kondisi awal pengguna sudah berada di dashboard lewat pengecekan otomatis (jika belum masuk, sistem otomatis bantu login via Google), memanggil fungsi logout, dan melakukan assertion ke arah tombol masuk UGM ID.

3. Kode Locators (LoginLocators.java)
Fungsi: Menggunakan kembali (reusable) lokator yang sudah ada.
Isi: Meminjam alamat area klik SIDEBAR_FOOTER di sudut bawah web, alamat tombol akhir LOGOUT_BUTTON di dalam popup menu, serta GOOGLE_LOGIN_BUTTON untuk penanda suksesnya proses keluar.

4. Halaman Logout (LogoutPage.java)
Fungsi: Logika operasional fungsional pembersihan sesi aplikasi.
Isi: Mengeklik bagian bawah menu samping (sidebar), menekan konfirmasi tombol keluar, dan mengembalikan status kebenaran (true/false) apakah tombol login awal muncul kembali di layar browser.

## Profile Settings

1. Gherkin (settings.feature)
Fungsi: Skenario pengujian halaman profil.
Isi: Alur sukses ganti data profil dan skenario tabel (Scenario Outline) untuk menguji form eror jika ada kolom wajib (Nama, NIM, Telp) yang dikosongkan sengaja.

2. Kode Langkah-langkah/Tahapan (SettingsSteps.java)
Fungsi: Tahapan otomatisasi input data profil.
Isi: Mengarahkan navigasi ke menu profil, mengirim data dari tabel Gherkin ke kolom input, menekan tombol simpan, serta melakukan assertion apakah profil sukses berubah atau justru muncul tanda peringatan eror.

3. Kode Locators (SettingsLocators.java)
Fungsi: Kamus penyimpan alamat Id, Class, atau XPath halaman profil.
Isi: Locator untuk kotak input nama, NIM, nomor telepon, tombol buka menu prodi/dosen, tombol Save, ikon segitiga merah eror (lucide-triangle-alert), serta generator fungsi dinamis untuk memilih nama opsi dosen.

4. Halaman Profil (SettingsPage.java)
Fungsi: Logika operasional fungsional halaman profil.
Isi:
  - Fungsi Verifikasi Data: Membaca teks asli isi form (getAttribute("value")) lalu membandingkannya dengan data baru pasca disimpan.
  - Deteksi Segitiga Eror: Memeriksa keberadaan ikon peringatan visual saat pengujian skenario kosong dilakukan.
