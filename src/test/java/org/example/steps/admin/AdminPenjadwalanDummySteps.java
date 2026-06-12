package org.example.steps.admin;

import org.example.pages.admin.AdminPenjadwalanDummyData;
import org.example.pages.admin.AdminPenjadwalanDummyPage;
import org.junit.jupiter.api.Assertions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminPenjadwalanDummySteps {
    private AdminPenjadwalanDummyPage dummyPage;

    @Given("tersedia data pengajuan dummy yang sudah diverifikasi")
    public void tersedia_data_pengajuan_dummy_yang_sudah_diverifikasi() {
        dummyPage = new AdminPenjadwalanDummyPage();
        dummyPage.loadVerifiedDummySubmission();

        Assertions.assertTrue(
                dummyPage.hasVerifiedDummySubmission(),
                "Data dummy pengajuan terverifikasi tidak tersedia untuk simulasi penjadwalan.");
    }

    @When("admin memilih dosen penguji")
    public void admin_memilih_dosen_penguji() {
        dummyPage.selectDefaultExaminer();
    }

    @When("admin mengosongkan dosen penguji")
    public void admin_mengosongkan_dosen_penguji() {
        dummyPage.clearExaminer();
    }

    @And("admin memilih ruangan sidang")
    public void admin_memilih_ruangan_sidang() {
        dummyPage.selectDefaultRoom();
    }

    @And("admin mengosongkan ruangan sidang")
    public void admin_mengosongkan_ruangan_sidang() {
        dummyPage.clearRoom();
    }

    @And("admin memilih tanggal sidang")
    public void admin_memilih_tanggal_sidang() {
        dummyPage.selectDefaultDate();
    }

    @And("admin mengosongkan tanggal sidang")
    public void admin_mengosongkan_tanggal_sidang() {
        dummyPage.clearDate();
    }

    @And("admin memilih ruangan sidang yang sudah terpakai")
    public void admin_memilih_ruangan_sidang_yang_sudah_terpakai() {
        dummyPage.selectConflictingRoom();
    }

    @And("admin memilih tanggal sidang yang sudah terpakai")
    public void admin_memilih_tanggal_sidang_yang_sudah_terpakai() {
        dummyPage.selectConflictingDate();
    }

    @When("admin mengosongkan semua field penjadwalan")
    public void admin_mengosongkan_semua_field_penjadwalan() {
        dummyPage.clearAllScheduleFields();
    }

    @And("admin memilih tanggal sidang batas minimum")
    public void admin_memilih_tanggal_sidang_batas_minimum() {
        dummyPage.selectMinimumBoundaryDate();
    }

    @And("admin memilih tanggal sidang batas maksimum")
    public void admin_memilih_tanggal_sidang_batas_maksimum() {
        dummyPage.selectMaximumBoundaryDate();
    }

    @And("admin menyimpan jadwal")
    public void admin_menyimpan_jadwal() {
        dummyPage.saveSchedule();
    }

    @Then("status pengajuan berubah menjadi Dijadwalkan")
    public void status_pengajuan_berubah_menjadi_dijadwalkan() {
        Assertions.assertEquals(
                AdminPenjadwalanDummyData.STATUS_DIJADWALKAN,
                dummyPage.getSubmissionStatus(),
                "Status pengajuan dummy harus berubah menjadi Dijadwalkan setelah penjadwalan valid.");
    }

    @And("jadwal tampil pada kalender utama")
    public void jadwal_tampil_pada_kalender_utama() {
        Assertions.assertTrue(
                dummyPage.isScheduleVisibleOnCalendar(),
                "Jadwal dummy yang berhasil disimpan harus tampil pada simulasi Kalender Utama.");
    }

    @Then("sistem menampilkan error {string}")
    public void sistem_menampilkan_error(String expectedError) {
        Assertions.assertTrue(
                dummyPage.hasValidationError(expectedError),
                "Error yang diharapkan tidak muncul. Expected: " + expectedError
                        + ", actual errors: " + dummyPage.getValidationErrors());
    }

    @And("status pengajuan tetap Siap Dijadwalkan")
    public void status_pengajuan_tetap_siap_dijadwalkan() {
        Assertions.assertEquals(
                AdminPenjadwalanDummyData.STATUS_SIAP_DIJADWALKAN,
                dummyPage.getSubmissionStatus(),
                "Status pengajuan dummy harus tetap Siap Dijadwalkan saat validasi gagal.");
    }

    @Then("sistem menampilkan semua error wajib isi")
    public void sistem_menampilkan_semua_error_wajib_isi() {
        Assertions.assertTrue(
                dummyPage.hasAllRequiredFieldErrors(),
                "Semua error wajib isi harus muncul. Actual errors: " + dummyPage.getValidationErrors());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (dummyPage == null) {
            return;
        }

        if (scenario.isFailed()) {
            dummyPage.printDebugSnapshot("Dummy scenario failed: " + scenario.getName());
        }

        dummyPage.quitDriver();
    }
}
