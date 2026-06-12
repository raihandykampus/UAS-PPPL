package org.example.steps.admin;

import org.example.pages.admin.AdminDashboardPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminDashboardSteps {

    private AdminDashboardPage dashboardPage;

    @Given("admin membuka halaman dashboard")
    public void admin_membuka_halaman_dashboard() {
        dashboardPage = new AdminDashboardPage();
        dashboardPage.open();
        dashboardPage.waitUntilDisplayedOrLogin();
        dashboardPage.printDebugSnapshot("After opening admin dashboard page");

        Assertions.assertFalse(
                dashboardPage.isRedirectedToLogin(),
                dashboardPage.getLoginPreconditionMessage());
    }

    @Then("dashboard admin berhasil ditampilkan")
    public void dashboard_admin_berhasil_ditampilkan() {
        Assertions.assertTrue(
                dashboardPage.isDisplayed(),
                "Dashboard admin tidak tampil. URL saat gagal: " + dashboardPage.getCurrentUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (dashboardPage == null) {
            return;
        }

        if (scenario.isFailed()) {
            dashboardPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
        }

        dashboardPage.quitDriver();
    }
}
