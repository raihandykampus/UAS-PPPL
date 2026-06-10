package org.example.pages.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminFormPenjadwalanPage extends BasePage {
    public static final String FORM_PENJADWALAN_URL = "https://pad-1.vercel.app/form-penjadwalan";

    private final By formTitle = By.xpath("//*[normalize-space()='Form Penjadwalan']");
    private final By emptyState = By.xpath("//*[normalize-space()='Tidak ada pengajuan yang siap dijadwalkan']");
    private final By jadwalkanButton = By.xpath(
            "//*[self::button or self::a][normalize-space()='Jadwalkan' or contains(normalize-space(),'Jadwalkan')]");

    public AdminFormPenjadwalanPage() {
        super();
    }

    public void open() {
        navigateTo(FORM_PENJADWALAN_URL);
        waitForDocumentReady();
    }

    public boolean isDisplayed() {
        if (isRedirectedToLogin()) {
            printDebugSnapshot("Redirected to login on form penjadwalan page");
            return false;
        }

        boolean displayed = waitForAnyVisible(formTitle, emptyState, jadwalkanButton);
        if (!displayed) {
            printDebugSnapshot("Form penjadwalan was not displayed");
        }
        return displayed;
    }

    public boolean hasFormContentOrEmptyState() {
        if (isRedirectedToLogin()) {
            return false;
        }

        boolean found = waitForAnyVisible(formTitle, emptyState, jadwalkanButton);
        if (!found) {
            printDebugSnapshot("Expected form penjadwalan content was not found");
        }
        return found;
    }

    public void waitUntilDisplayedOrLogin() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.urlContains("accounts.google.com"),
                    ExpectedConditions.visibilityOfElementLocated(formTitle),
                    ExpectedConditions.visibilityOfElementLocated(emptyState),
                    ExpectedConditions.visibilityOfElementLocated(jadwalkanButton)));
        } catch (TimeoutException e) {
            printDebugSnapshot("Timeout waiting for form penjadwalan or login redirect");
        }
    }
}
