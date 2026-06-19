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
        if (formPage == null) { formPage = new FormPengajuanPage(); }
        formPage.navigateToFormPengajuan();
    }

    @When("the user fills the Judul Tugas Akhir with {string}")
    public void userFillsJudul(String judulText) {
        if (formPage == null) { formPage = new FormPengajuanPage(); }
        formPage.fillJudul(judulText);
    }

    @When("the user uploads the file {string}")
    public void userUploadsFile(String fileName) {
        if (formPage == null) { formPage = new FormPengajuanPage(); }
        formPage.uploadFile(fileName);
    }

    @When("the user submits the form")
    public void userSubmitsForm() {
        formPage.submitForm();
    }

    @Then("the form submission should be successful")
    public void verifyFormSubmissionSuccessful() {
        boolean success = formPage.isSubmissionSuccessful();
        Assertions.assertTrue(success, "Form submission was not successful!");
    }

    @Then("a form validation alert badge should be displayed on screen")
    public void verifyValidationAlertBadgeDisplayed() {
        String dialogError = formPage.getDialogErrorMessage();
        if (!dialogError.isEmpty()) {
            System.out.println("Captured Radix UI Error Message: " + dialogError);
            boolean isValidError = dialogError.contains("Ukuran file terlalu besar") ||
                    dialogError.contains("bukan format PDF");
            Assertions.assertTrue(isValidError, "Unexpected dialog error text: " + dialogError);
            return;
        }

        if (formPage.isJudulHtml5Invalid()) {
            System.out.println("Success detected: HTML5 Browser Blocked submission due to missing Title!");
            Assertions.assertTrue(true);
            return;
        }

        boolean isTriangleVisible = formPage.isAlertTriangleDisplayed();
        Assertions.assertTrue(isTriangleVisible, "Expected validation warning was not found anywhere (Radix Dialog, HTML5 Validation, or Alert Triangle)!");
    }

    @After("@form_pengajuan")
    public void tearDown(Scenario scenario) {
        if (formPage != null) {
            formPage.quitDriver();
        }
    }
}