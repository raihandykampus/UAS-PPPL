package org.example.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.example.pages.FormPengajuanPage;
import org.junit.jupiter.api.Assertions;

public class FormPengajuanSteps {
    private FormPengajuanPage formPage;

    @When("the user navigates to the Form Pengajuan page")
    public void userNavigatesToFormPengajuanPage() {
        formPage = new FormPengajuanPage();
        formPage.navigateToFormPengajuan();
    }

    @When("the user fills the Judul Tugas Akhir with {string}")
    public void userFillsJudul(String judulText) {
        formPage.fillJudul(judulText);
    }

    @When("the user uploads the file {string}")
    public void userUploadsFile(String fileName) {
        formPage.uploadFile(fileName);
    }

    @When("the user submits the form")
    public void userSubmitsForm() {
        formPage.submitForm();
    }

    @Then("the form submission should be successful")
    public void verifyFormSubmissionSuccessful() {
        boolean success = formPage.isSubmissionSuccessful();
        Assertions.assertTrue(success, "Form submission was not successful or success heading was not displayed!");
        System.out.println("Form submission successfully completed and verified!");
    }

    @After("@form_pengajuan")
    public void tearDown(Scenario scenario) {
        if (formPage != null) {
            formPage.quitDriver();
        }
    }
}
