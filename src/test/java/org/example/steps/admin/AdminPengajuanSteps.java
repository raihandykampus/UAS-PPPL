package org.example.steps.admin;

import org.example.pages.admin.AdminPengajuanPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminPengajuanSteps {

    private AdminPengajuanPage pengajuanPage;

    @Given("admin membuka halaman pengajuan")
    public void admin_membuka_halaman_pengajuan() {
        pengajuanPage = new AdminPengajuanPage();
        pengajuanPage.open();
        pengajuanPage.waitUntilDisplayedOrLogin();
        pengajuanPage.printDebugSnapshot("After opening admin pengajuan page");

        Assertions.assertFalse(
                pengajuanPage.isRedirectedToLogin(),
                pengajuanPage.getLoginPreconditionMessage());
    }

    @Then("daftar pengajuan berhasil ditampilkan")
    public void daftar_pengajuan_berhasil_ditampilkan() {
        Assertions.assertTrue(
                pengajuanPage.hasExpectedPengajuanData(),
                "Daftar pengajuan tidak tampil atau data 'Dimas Satriaa' / tombol 'Detail' tidak ditemukan. URL saat gagal: "
                        + pengajuanPage.getCurrentUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (pengajuanPage == null) {
            return;
        }

        if (scenario.isFailed()) {
            pengajuanPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
        }

        pengajuanPage.quitDriver();
    }
}
