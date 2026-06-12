@form_pengajuan
Feature: Form Pengajuan Upload

  Scenario: Mahasiswa uploads form pengajuan successfully
    Given the user is logged in to the dashboard with "mahasiswa" credentials
    When the user navigates to the Form Pengajuan page
    And the user fills the Judul Tugas Akhir with "Testing PPPL"
    And the user uploads the file "smallDocs.pdf"
    And the user submits the form
    Then the form submission should be successful
