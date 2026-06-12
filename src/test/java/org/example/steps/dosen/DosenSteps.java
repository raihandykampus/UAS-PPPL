package org.example.steps.dosen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.DosenPage;
import org.junit.jupiter.api.Assertions;

public class DosenSteps {

    private DosenPage dosenPage;

    @Then("dashboard dosen tampil")
    public void dashboardDosenTampil() {
        dosenPage = new DosenPage();

        Assertions.assertTrue(
                dosenPage.isDosenDashboardDisplayed(),
                "Dashboard dosen tidak tampil."
        );
    }

    @When("dosen membuka halaman detail jadwal sidebar")
    public void dosenMembukaHalamanDetailJadwalSidebar() {
        dosenPage.openDetailJadwalMenu();
    }

    @Then("halaman detail jadwal sidebar valid")
    public void halamanDetailJadwalSidebarValid() {
        Assertions.assertTrue(
                dosenPage.isDetailJadwalSidebarPageDisplayed(),
                "Halaman detail jadwal sidebar tidak valid."
        );
    }

    @When("dosen membuka detail jadwal dari halaman detail jadwal")
    public void dosenMembukaDetailJadwalDariHalamanDetailJadwal() {
        Assertions.assertTrue(
                dosenPage.isAnyScheduleAvailable(),
                "Tidak ada data jadwal sidang yang dapat dibuka."
        );

        dosenPage.openFirstScheduleDetail();
    }

    @Then("modal detail jadwal tampil")
    public void modalDetailJadwalTampil() {
        Assertions.assertTrue(
                dosenPage.isModalDetailDisplayed(),
                "Modal detail jadwal tidak tampil."
        );
    }

    @Then("tombol download naskah tersedia")
    public void tombolDownloadNaskahTersedia() {
        Assertions.assertTrue(
                dosenPage.isDownloadButtonDisplayed(),
                "Tombol download naskah tidak ditemukan."
        );
    }

    @When("dosen membuka berkas naskah")
    public void dosenMembukaBerkasNaskah() {
        dosenPage.openDocumentFile();
    }

    @Then("halaman berkas naskah berhasil terbuka")
    public void halamanBerkasNaskahBerhasilTerbuka() {
        Assertions.assertTrue(
                dosenPage.isDocumentFileOpened(),
                "Halaman berkas naskah tidak berhasil terbuka."
        );
    }

    @When("dosen kembali ke tab aplikasi utama")
    public void dosenKembaliKeTabAplikasiUtama() {
        dosenPage.switchBackToMainApplicationTab();
    }

    @When("dosen membuka halaman riwayat ujian")
    public void dosenMembukaHalamanRiwayatUjian() {
        dosenPage.openRiwayatUjianMenu();
    }

    @Then("halaman riwayat ujian dosen valid")
    public void halamanRiwayatUjianDosenValid() {
        Assertions.assertTrue(
                dosenPage.isRiwayatUjianPageDisplayed(),
                "Halaman riwayat ujian dosen tidak valid."
        );
    }

    @When("dosen membuka halaman notifikasi")
    public void dosenMembukaHalamanNotifikasi() {
        dosenPage.openNotifikasiMenu();
    }

    @Then("halaman notifikasi dosen valid")
    public void halamanNotifikasiDosenValid() {
        Assertions.assertTrue(
                dosenPage.isNotifikasiPageDisplayed(),
                "Halaman notifikasi dosen tidak valid."
        );
    }
}