package org.example.steps.admin;

import org.example.pages.admin.AdminFormPenjadwalanPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminFormPenjadwalanSteps {

    private AdminFormPenjadwalanPage formPenjadwalanPage;

    @Given("admin membuka halaman formulir penjadwalan")
    public void admin_membuka_halaman_formulir_penjadwalan() {
        formPenjadwalanPage = new AdminFormPenjadwalanPage();
        formPenjadwalanPage.open();
        formPenjadwalanPage.waitUntilDisplayedOrLogin();
        formPenjadwalanPage.printDebugSnapshot("After opening admin form penjadwalan page");

        Assertions.assertFalse(
                formPenjadwalanPage.isRedirectedToLogin(),
                formPenjadwalanPage.getLoginPreconditionMessage());
    }

    @Then("halaman formulir penjadwalan berhasil ditampilkan")
    public void halaman_formulir_penjadwalan_berhasil_ditampilkan() {
        Assertions.assertTrue(
                formPenjadwalanPage.hasFormContentOrEmptyState(),
                "Form penjadwalan tidak tampil. Teks 'Form Penjadwalan', empty state, atau tombol 'Jadwalkan' tidak ditemukan. URL saat gagal: "
                        + formPenjadwalanPage.getCurrentUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (formPenjadwalanPage == null) {
            return;
        }

        if (scenario.isFailed()) {
            formPenjadwalanPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
        }

        formPenjadwalanPage.quitDriver();
    }
}
