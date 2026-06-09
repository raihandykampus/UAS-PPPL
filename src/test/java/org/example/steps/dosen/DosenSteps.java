package org.example.steps.dosen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.DosenDashboardPage;
import org.example.pages.DosenDetailJadwalPage;
import org.junit.jupiter.api.Assertions;

public class DosenSteps {

    private final String BASE_URL = "https://pad-1.vercel.app";

    private DosenDashboardPage dosenDashboardPage;
    private DosenDetailJadwalPage dosenDetailJadwalPage;

    @Then("dashboard dosen tampil")
    public void dashboardDosenTampil() {
        dosenDashboardPage = new DosenDashboardPage();

        Assertions.assertTrue(
                dosenDashboardPage.isDosenDashboardDisplayed(),
                "Dashboard dosen tidak tampil."
        );
    }

    @When("dosen mencoba membuka halaman admin")
    public void dosenMencobaMembukaHalamanAdmin() {
        dosenDashboardPage = new DosenDashboardPage();
        dosenDashboardPage.navigateTo(BASE_URL + "/data-mahasiswa");
    }

    @Then("akses halaman admin ditolak")
    public void aksesHalamanAdminDitolak() {
        Assertions.assertTrue(
                dosenDashboardPage.isAccessDenied(),
                "Dosen masih bisa mengakses halaman admin."
        );
    }

    @When("dosen membuka detail jadwal dari dashboard")
    public void dosenMembukaDetailJadwalDariDashboard() {
        dosenDetailJadwalPage = new DosenDetailJadwalPage();

        Assertions.assertTrue(
                dosenDetailJadwalPage.isDashboardScheduleDisplayed(),
                "Jadwal sidang tidak ditemukan di dashboard dosen."
        );

        dosenDetailJadwalPage.openDetailFromDashboard();
    }

    @Then("modal detail jadwal tampil")
    public void modalDetailJadwalTampil() {
        Assertions.assertTrue(
                dosenDetailJadwalPage.isModalDetailDisplayed(),
                "Modal detail jadwal tidak tampil."
        );
    }

    @Then("tombol download naskah tersedia")
    public void tombolDownloadNaskahTersedia() {
        Assertions.assertTrue(
                dosenDetailJadwalPage.isDownloadButtonDisplayed(),
                "Tombol download naskah tidak ditemukan."
        );
    }
}