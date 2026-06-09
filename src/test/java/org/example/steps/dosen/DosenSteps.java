package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.example.pages.DosenDashboardPage;
import org.junit.jupiter.api.Assertions;

public class DosenSteps {

    private final String BASE_URL = "https://pad-1.vercel.app";

    private DosenDashboardPage dosenDashboardPage;

    @Given("dosen membuka aplikasi SIMPENSI")
    public void dosenMembukaAplikasiSIMPENSI() {
        dosenDashboardPage = new DosenDashboardPage();
        dosenDashboardPage.navigateTo(BASE_URL + "/dashboard");
    }

    @Then("dosen melihat dashboard dosen")
    public void dosenMelihatDashboardDosen() {
        Assertions.assertTrue(
                dosenDashboardPage.isDashboardDisplayed(),
                "Dashboard dosen tidak tampil. Pastikan Chrome profile sudah login sebagai akun dosen."
        );
    }

    @When("dosen mencoba membuka halaman admin")
    public void dosenMencobaMembukaHalamanAdmin() {
        dosenDashboardPage.navigateTo(BASE_URL + "/data-mahasiswa");
    }

    @Then("akses halaman admin ditolak")
    public void aksesHalamanAdminDitolak() {
        Assertions.assertTrue(
                dosenDashboardPage.isAccessDenied(),
                "Dosen masih bisa mengakses halaman admin."
        );
    }

    @After
    public void tearDown() {
        if (dosenDashboardPage != null) {
            dosenDashboardPage.quitDriver();
        }
    }
}