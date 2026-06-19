@form_pengajuan
Feature: Form Pengajuan Upload

  Scenario: Mahasiswa uploads form pengajuan successfully
    Given the user is logged in to the dashboard with "mahasiswa" credentials
    When the user navigates to the Form Pengajuan page
    And the user fills the Judul Tugas Akhir with "Testing PPPL"
    And the user uploads the file "smallDocs.pdf"
    And the user submits the form
    Then the form submission should be successful

@form_pengajuan_negative
Scenario: Mahasiswa fails to submit form with empty title
  Given the user is logged in to the dashboard with "mahasiswa" credentials
  When the user navigates to the Form Pengajuan page
  And the user uploads the file "smallDocs.pdf"
  And the user submits the form
  Then a form validation alert badge should be displayed on screen

@form_pengajuan_negative
Scenario: Mahasiswa fails to submit form with oversized file
  Given the user is logged in to the dashboard with "mahasiswa" credentials
  When the user navigates to the Form Pengajuan page
  And the user fills the Judul Tugas Akhir with "Testing Ukuran Berkas"
  And the user uploads the file "bigDocs.pdf"
  And the user submits the form
  Then a form validation alert badge should be displayed on screen

@form_pengajuan_negative
Scenario: Mahasiswa fails to submit form with invalid file format
  Given the user is logged in to the dashboard with "mahasiswa" credentials
  When the user navigates to the Form Pengajuan page
  And the user fills the Judul Tugas Akhir with "Testing Format Berkas"
  And the user uploads the file "notPdf.odt"
  And the user submits the form
  Then a form validation alert badge should be displayed on screen