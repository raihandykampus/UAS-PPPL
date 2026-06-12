package org.example.steps.admin;

import org.example.pages.admin.AdminKalenderPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminKalenderSteps {

    private AdminKalenderPage kalenderPage;

    @Given("admin membuka halaman kalender utama")
    public void admin_membuka_halaman_kalender_utama() {
        kalenderPage = new AdminKalenderPage();
        kalenderPage.open();
        kalenderPage.waitUntilDisplayedOrLogin();
        kalenderPage.printDebugSnapshot("After opening admin calendar page");

        Assertions.assertFalse(
                kalenderPage.isRedirectedToLogin(),
                kalenderPage.getLoginPreconditionMessage());

        Assertions.assertTrue(
                kalenderPage.isKalenderPageDisplayed(),
                "Halaman kalender tidak tampil. URL saat gagal: " + kalenderPage.getCurrentUrl());
    }

    @Then("event ujian TA berhasil ditampilkan")
    public void event_ujian_ta_berhasil_ditampilkan() {
        boolean eventDisplayed = kalenderPage.hasExpectedCalendarEvent();

        Assertions.assertTrue(
                eventDisplayed,
                "Event 'Ujian TA' tidak ditemukan. URL saat gagal: " + kalenderPage.getCurrentUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (kalenderPage == null) {
            return;
        }

        if (scenario.isFailed()) {
            kalenderPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
        }

        kalenderPage.quitDriver();
    }
}
