package org.example.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.pages.LoginPage;
import org.example.pages.LogoutPage;
import org.example.users.Roles;
import org.junit.jupiter.api.Assertions;

public class LogoutSteps {
    private LoginPage loginPage;
    private LogoutPage logoutPage;

    @Given("the user is logged in to the dashboard with {string} credentials")
    public void userIsLoggedInWithCredentials(String roleName) {
        loginPage = new LoginPage();
        logoutPage = new LogoutPage();

        loginPage.navigateTo("https://pad-1.vercel.app/login");

        String targetEmail;
        String expectedName;

        switch (roleName.trim().toLowerCase()) {
            case "mahasiswa":
                targetEmail = Roles.MAHASISWA;
                expectedName = Roles.NAMA_MAHASISWA;
                break;
            case "admin":
                targetEmail = Roles.ADMIN;
                expectedName = Roles.NAMA_ADMIN;
                break;
            case "dosen":
                targetEmail = Roles.DOSEN;
                expectedName = Roles.NAMA_DOSEN;
                break;
            default:
                throw new IllegalArgumentException("Unknown BDD scenario role selection: " + roleName);
        }

        if (!loginPage.isLoggedIn()) {
            loginPage.clickGoogleLoginBtn();
            loginPage.selectGoogleAccount(targetEmail);
            loginPage.handleGoogleConsentScreens();
        }

        Assertions.assertTrue(
                loginPage.isDashboardDisplayed(expectedName),
                "Dashboard welcome message for " + expectedName + " was not displayed!"
        );

        System.out.println("Successfully set up logged-in state for: " + expectedName);
    }

    @When("the user initiates logout")
    public void initiateLogout() {
        if (logoutPage == null) {
            logoutPage = new LogoutPage();
        }

        logoutPage.logout();
    }

    @Then("the user should land successfully on the login page")
    public void verifyLoginLanding() {
        Assertions.assertTrue(
                logoutPage.isLoggedOut(),
                "User was not successfully logged out to the login page!"
        );

        System.out.println("Successfully logged out and landed on the login page!");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (logoutPage != null) {
            logoutPage.quitDriver();
        } else if (loginPage != null) {
            loginPage.quitDriver();
        }
    }
}