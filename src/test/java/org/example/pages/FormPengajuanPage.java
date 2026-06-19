package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import static org.example.pages.locators.FormPengajuanLocators.*;

public class FormPengajuanPage extends BasePage {

    public FormPengajuanPage() {
        super();
    }

    public void navigateToFormPengajuan() {
        System.out.println("Navigating to Form Pengajuan page...");
        clickElement(FORM_PENGAJUAN_MENU_LINK);

        wait.until(ExpectedConditions.urlContains("/form-pengajuan"));
        System.out.println("Landed on Form Pengajuan page: " + getCurrentUrl());
    }

    public void fillJudul(String judulText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(JUDUL_INPUT));
        element.clear();
        element.sendKeys(judulText);
        System.out.println("Filled Judul Tugas Akhir: " + judulText);
    }

    public void uploadFile(String fileName) {
        String workingDir = System.getProperty("user.dir");
        String relativePath = "src/test/resources/upload_files/" + fileName;
        File file = new File(workingDir, relativePath);

        if (!file.exists()) {
            file = new File(fileName);
        }

        String absolutePath = file.getAbsolutePath();
        System.out.println("Uploading file from resolved path: " + absolutePath);

        WebElement fileInput = driver.findElement(FILE_INPUT);
        fileInput.sendKeys(absolutePath);

        System.out.println("Dispatching change event via JavaScript executor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "var input = document.getElementById('berkas');" +
                        "var event = new Event('change', { bubbles: true });" +
                        "input.dispatchEvent(event);");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

    public void submitForm() {
        System.out.println("Clicking Submit form button...");
        clickElement(SUBMIT_BUTTON);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }
    }

    public boolean isSubmissionSuccessful() {
        try {
            waitForDocumentReady();
            WebElement successHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_HEADING));
            System.out.println("Submission success confirmed: " + successHeading.getText());
            return successHeading.isDisplayed();
        } catch (Exception e) {
            System.out.println("Failed to confirm form submission success: " + e.getMessage());
            return false;
        }
    }
}
