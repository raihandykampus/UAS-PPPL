package org.example.steps.admin;

import org.example.pages.admin.AdminRuanganPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminRuanganSteps {
    private AdminRuanganPage ruanganPage;

    @Given("admin membuka halaman manajemen ruangan")
    public void admin_membuka_halaman_manajemen_ruangan() {
        ruanganPage = new AdminRuanganPage();
        ruanganPage.open();
        ruanganPage.waitUntilDisplayedOrLogin();
        ruanganPage.printDebugSnapshot("After opening admin ruangan page");

        Assertions.assertFalse(
                ruanganPage.isRedirectedToLogin(),
                ruanganPage.getLoginPreconditionMessage());
    }

    @Then("daftar ruangan berhasil ditampilkan")
    public void daftar_ruangan_berhasil_ditampilkan() {
        Assertions.assertTrue(
                ruanganPage.isDisplayed(),
                "Judul 'Manajemen Ruangan' tidak tampil. URL saat gagal: " + ruanganPage.getCurrentUrl());
        Assertions.assertTrue(
                ruanganPage.isRoomTableDisplayed(),
                "Tabel ruangan tidak tampil. URL saat gagal: " + ruanganPage.getCurrentUrl());
        Assertions.assertTrue(
                ruanganPage.hasAtLeastOneRoomData(),
                "Minimal satu data ruangan tidak ditemukan. URL saat gagal: " + ruanganPage.getCurrentUrl());
        Assertions.assertTrue(
                ruanganPage.hasHu207Room(),
                "Data ruangan HU207 tidak ditemukan. URL saat gagal: " + ruanganPage.getCurrentUrl());
        Assertions.assertTrue(
                ruanganPage.hasHu208Room(),
                "Data ruangan HU208 tidak ditemukan. URL saat gagal: " + ruanganPage.getCurrentUrl());
        Assertions.assertTrue(
                ruanganPage.hasLabRplRoom(),
                "Data ruangan Lab RPL tidak ditemukan. URL saat gagal: " + ruanganPage.getCurrentUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (ruanganPage == null) {
            return;
        }

        if (scenario.isFailed()) {
            ruanganPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
        }

        ruanganPage.quitDriver();
    }
}
