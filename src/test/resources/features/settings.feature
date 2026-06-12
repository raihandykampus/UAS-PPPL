@settings
Feature: Edit Profile Settings

  Scenario: User updates their profile details successfully
    Given the user is logged in to the dashboard with "mahasiswa" credentials
    When the user navigates to the Edit Profile page
    And the user updates their profile details with name "Afif", nim "24/537611/SV/24441", prodi "Teknologi Rekayasa Perangkat Lunak", telepon "085111111111", and dosen pembimbing "Wawan Gorengan"
    And the user saves the settings
    Then the profile settings should be updated successfully
