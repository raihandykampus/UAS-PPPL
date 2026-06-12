package org.example.steps.admin;

import org.example.pages.admin.AdminDetailPengajuanPage;
import org.example.pages.admin.AdminPengajuanPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminDetailPengajuanSteps {

    private AdminPengajuanPage pengajuanPage;
    private AdminDetailPengajuanPage detailPengajuanPage;

    @When("admin membuka detail pengajuan mahasiswa")
    public void admin_membuka_detail_pengajuan_mahasiswa() {
        pengajuanPage = new AdminPengajuanPage();

        Assertions.assertFalse(
                pengajuanPage.isRedirectedToLogin(),
                pengajuanPage.getLoginPreconditionMessage());

        pengajuanPage.openDetailPengajuanMahasiswa();

        detailPengajuanPage = new AdminDetailPengajuanPage();
        detailPengajuanPage.waitUntilDisplayedOrLogin();
        detailPengajuanPage.printDebugSnapshot("After opening admin detail pengajuan page");

        Assertions.assertFalse(
                detailPengajuanPage.isRedirectedToLogin(),
                detailPengajuanPage.getLoginPreconditionMessage());
    }

    @Then("detail pengajuan berhasil ditampilkan")
    public void detail_pengajuan_berhasil_ditampilkan() {
        Assertions.assertTrue(
                detailPengajuanPage.hasExpectedDetailContent(),
                "Detail pengajuan tidak tampil atau teks 'Verifikasi Pengajuan' / 'DIJADWALKAN' / tombol 'Verifikasi' / 'Tolak' tidak ditemukan. URL saat gagal: "
                        + detailPengajuanPage.getCurrentUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (detailPengajuanPage != null) {
            if (scenario.isFailed()) {
                detailPengajuanPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
            }
            detailPengajuanPage.quitDriver();
            return;
        }

        if (pengajuanPage != null) {
            if (scenario.isFailed()) {
                pengajuanPage.printDebugSnapshot("Scenario failed: " + scenario.getName());
            }
            pengajuanPage.quitDriver();
        }
    }
}
