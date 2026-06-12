package org.example.pages.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPengajuanPage extends BasePage {
    public static final String PENGAJUAN_URL = "https://pad-1.vercel.app/pengajuan";

    private final By pengajuanTitle = By.xpath("//*[normalize-space()='Pengajuan' or contains(normalize-space(),'Pengajuan')]");
    private final By mahasiswaDimas = By.xpath("//*[normalize-space()='Dimas Satriaa' or contains(normalize-space(),'Dimas Satriaa')]");
    private final By detailButton = By.xpath(
            "//*[self::button or self::a][normalize-space()='Detail' or contains(normalize-space(),'Detail')]");
    private final By detailDimasButton = By.xpath(
            "//*[contains(normalize-space(),'Dimas Satriaa')]/ancestor::*[self::tr or self::div][1]//*[self::button or self::a][contains(normalize-space(),'Detail')]");

    public AdminPengajuanPage() {
        super();
    }

    public void open() {
        navigateTo(PENGAJUAN_URL);
        waitForDocumentReady();
    }

    public boolean isDisplayed() {
        if (isRedirectedToLogin()) {
            printDebugSnapshot("Redirected to login on pengajuan page");
            return false;
        }

        boolean displayed = waitForAnyVisible(pengajuanTitle, mahasiswaDimas, detailButton);
        if (!displayed) {
            printDebugSnapshot("Pengajuan page was not displayed");
        }
        return displayed;
    }

    public boolean hasExpectedPengajuanData() {
        if (isRedirectedToLogin()) {
            return false;
        }

        boolean found = waitForAnyVisible(pengajuanTitle, mahasiswaDimas, detailButton);
        if (!found) {
            printDebugSnapshot("Expected pengajuan data was not found");
        }
        return found;
    }

    public void openDetailPengajuanMahasiswa() {
        if (isRedirectedToLogin()) {
            throw new AssertionError(getLoginPreconditionMessage());
        }

        try {
            if (isElementPresentQuick(detailDimasButton)) {
                clickElement(detailDimasButton);
            } else {
                clickElement(detailButton);
            }
            waitForDocumentReady();
        } catch (Exception e) {
            printDebugSnapshot("Failed to open detail pengajuan mahasiswa");
            throw new AssertionError(
                    "Data pengajuan atau tombol 'Detail' tidak ditemukan. URL saat gagal: " + getCurrentUrl(), e);
        }
    }

    public void waitUntilDisplayedOrLogin() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.urlContains("accounts.google.com"),
                    ExpectedConditions.visibilityOfElementLocated(pengajuanTitle),
                    ExpectedConditions.visibilityOfElementLocated(mahasiswaDimas),
                    ExpectedConditions.visibilityOfElementLocated(detailButton)));
        } catch (TimeoutException e) {
            printDebugSnapshot("Timeout waiting for pengajuan or login redirect");
        }
    }
}
