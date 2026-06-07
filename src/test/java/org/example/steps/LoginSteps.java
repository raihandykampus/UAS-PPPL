package org.example.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.pages.LoginPage;
import org.example.users.Roles;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    private LoginPage loginPage;
    private String activeRole;
    private String activeEmail;

    @Given("the user is on the application login page")
    public void navigateToLoginPage() {
        loginPage = new LoginPage();
        loginPage.navigateTo("https://pad-1.vercel.app/login");
    }

    @When("the user selects their active {string} credentials")
    public void selectCredentialsForActiveRole(String roleName) {
        String targetEmail;

        switch (roleName.trim().toLowerCase()) {
            case "mahasiswa":
                targetEmail = Roles.MAHASISWA;
                break;
            case "admin":
                targetEmail = Roles.ADMIN;
                break;
            case "dosen":
                targetEmail = Roles.DOSEN;
                break;
            default:
                throw new IllegalArgumentException("Unknown BDD scenario role selection: " + roleName);
        }

        this.activeRole = roleName.trim().toLowerCase();
        this.activeEmail = targetEmail;
        System.out.println("Executing automated loop using credentials: " + targetEmail);
    }

    @When("the user initiates login with Google")
    public void clickGoogleLogin() {
        loginPage.clickGoogleLoginBtn();
    }

    @And("selects their authenticated Google account")
    public void selectGoogleAccount() {
        loginPage.selectGoogleAccount(activeEmail);
        loginPage.handleGoogleConsentScreens();
    }

    @Then("the user should land successfully on the dashboard")
    public void verifyDashboardLanding() {
        String expectedName = "";
        if ("mahasiswa".equals(activeRole)) {
            expectedName = "ABDULLAH AFIF HABIBURROHMAN";
        } else if ("admin".equals(activeRole)) {
            expectedName = "ADMIN";
        } else if ("dosen".equals(activeRole)) {
            expectedName = "DOSEN";
        }
        
        String currentUrl = loginPage.getCurrentUrl();
        System.out.println("CURRENT URL AT DASHBOARD STEP: " + currentUrl);
        
        boolean isDisplayed = loginPage.isDashboardDisplayed(expectedName);
        if (!isDisplayed) {
            System.err.println("Dashboard verification failed!");
            System.err.println("Current URL: " + currentUrl);
            System.err.println("Page text:\n" + loginPage.getPageText());
        }
        Assertions.assertTrue(isDisplayed, "Dashboard welcome message for " + expectedName + " was not displayed!");
        System.out.println("Successfully landed on dashboard and verified welcome message!");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (loginPage != null) {
            loginPage.quitDriver();
        }
    }
}