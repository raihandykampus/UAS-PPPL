package org.example.steps.dosen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.DosenDashboardPage;
import org.junit.jupiter.api.Assertions;

public class DosenSteps {

    private final String BASE_URL = "https://pad-1.vercel.app";
    private DosenDashboardPage dosenDashboardPage;

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
}