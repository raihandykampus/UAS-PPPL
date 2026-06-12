package org.example.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.pages.SettingsPage;
import org.junit.jupiter.api.Assertions;

public class SettingsSteps {
    private SettingsPage settingsPage;
    private String expectedName;
    private String expectedNim;
    private String expectedProdi;
    private String expectedTelepon;
    private String expectedDosen;

    @When("the user navigates to the Edit Profile page")
    public void userNavigatesToEditProfilePage() {
        settingsPage = new SettingsPage();
        settingsPage.navigateToSettings();
    }

    @When("the user updates their profile details with name {string}, nim {string}, prodi {string}, telepon {string}, and dosen pembimbing {string}")
    public void userUpdatesProfileDetails(String name, String nim, String prodi, String telepon, String dosen) {
        this.expectedName = name;
        this.expectedNim = nim;
        this.expectedProdi = prodi;
        this.expectedTelepon = telepon;
        this.expectedDosen = dosen;
        
        settingsPage.updateProfileDetails(name, nim, prodi, telepon, dosen);
    }

    @When("the user saves the settings")
    public void userSavesSettings() {
        settingsPage.saveSettings();
    }

    @Then("the profile settings should be updated successfully")
    public void verifyProfileUpdated() {
        boolean success = settingsPage.isProfileUpdatedSuccessfully(
                expectedName, expectedNim, expectedProdi, expectedTelepon, expectedDosen
        );
        Assertions.assertTrue(success, "Profile settings were not successfully updated/matched!");
        System.out.println("Profile settings successfully updated and verified!");
    }

    @After("@settings")
    public void tearDown(Scenario scenario) {
        if (settingsPage != null) {
            settingsPage.quitDriver();
        }
    }
}
