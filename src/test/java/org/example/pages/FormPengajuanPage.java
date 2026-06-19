package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import static org.example.pages.locators.FormPengajuanLocators.*;

public class FormPengajuanPage extends BasePage {

    public FormPengajuanPage() {
        super();
    }

    public void navigateToFormPengajuan() {
        clickElement(FORM_PENGAJUAN_MENU_LINK);
        wait.until(ExpectedConditions.urlContains("/form-pengajuan"));
    }

    public void fillJudul(String judulText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(JUDUL_INPUT));
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);

        if (judulText != null && !judulText.isEmpty()) {
            element.sendKeys(judulText);
        } else {
            element.sendKeys(" ");
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void uploadFile(String fileName) {
        String workingDir = System.getProperty("user.dir");
        String relativePath = "src/test/resources/upload_files/" + fileName;
        File file = new File(workingDir, relativePath);

        if (!file.exists()) {
            file = new File(fileName);
        }

        // PERBAIKAN: Tunggu sampai element input file berkas benar-benar ada di DOM sebelum disuntik path file
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(FILE_INPUT));
        fileInput.sendKeys(file.getAbsolutePath());

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
        // PERBAIKAN: Jika layar terblokir oleh Pop-up Radix, jangan klik submit utama karena pasti TimeoutException
        if (isElementPresentQuick(DIALOG_DESCRIPTION)) {
            System.out.println("[Page Object] Pop-up error detected before submit. Skipping main submit click.");
            return;
        }
        clickElement(SUBMIT_BUTTON);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

    public boolean isAlertTriangleDisplayed() {
        return isElementDisplayed(ALERT_TRIANGLE_ICON);
    }

    // Fungsi baru untuk mengambil isi teks dari Pop-up Radix UI
    public String getDialogErrorMessage() {
        try {
            WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DIALOG_DESCRIPTION));
            String errorText = msgElement.getText();

            // Klik tombol 'Kembali' atau 'X' untuk membersihkan dialog agar tidak mengganggu test case selanjutnya
            if (isElementPresentQuick(DIALOG_CLOSE_BUTTON)) {
                clickElement(DIALOG_CLOSE_BUTTON);
            }
            return errorText;
        } catch (Exception e) {
            return "";
        }
    }
    // Tambahkan method ini di dalam FormPengajuanPage.java
    public boolean isJudulHtml5Invalid() {
        try {
            WebElement judulInput = wait.until(ExpectedConditions.presenceOfElementLocated(JUDUL_INPUT));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Mengembalikan 'true' jika browser mendeteksi bahwa field required ini kosong saat disubmit
            return (Boolean) js.executeScript("return arguments[0].validity.valueMissing;", judulInput);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmissionSuccessful() {
        try {
            waitForDocumentReady();
            WebElement successHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_HEADING));
            return successHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}