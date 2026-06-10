package org.example.pages.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminDetailPengajuanPage extends BasePage {
    private final By verifikasiPengajuanTitle = By.xpath("//*[normalize-space()='Verifikasi Pengajuan']");
    private final By dijadwalkanStatus = By.xpath("//*[normalize-space()='DIJADWALKAN']");
    private final By verifikasiButton = By.xpath(
            "//*[self::button or self::a][normalize-space()='Verifikasi' or contains(normalize-space(),'Verifikasi')]");
    private final By tolakButton = By.xpath(
            "//*[self::button or self::a][normalize-space()='Tolak' or contains(normalize-space(),'Tolak')]");

    public AdminDetailPengajuanPage() {
        super();
    }

    public boolean isDisplayed() {
        if (isRedirectedToLogin()) {
            printDebugSnapshot("Redirected to login on detail pengajuan page");
            return false;
        }

        boolean displayed = waitForAnyVisible(
                verifikasiPengajuanTitle,
                dijadwalkanStatus,
                verifikasiButton,
                tolakButton);
        if (!displayed) {
            printDebugSnapshot("Detail pengajuan was not displayed");
        }
        return displayed;
    }

    public boolean hasExpectedDetailContent() {
        if (isRedirectedToLogin()) {
            return false;
        }

        boolean found = waitForAnyVisible(
                verifikasiPengajuanTitle,
                dijadwalkanStatus,
                verifikasiButton,
                tolakButton);
        if (!found) {
            printDebugSnapshot("Expected detail pengajuan content was not found");
        }
        return found;
    }

    public void waitUntilDisplayedOrLogin() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.urlContains("accounts.google.com"),
                    ExpectedConditions.visibilityOfElementLocated(verifikasiPengajuanTitle),
                    ExpectedConditions.visibilityOfElementLocated(dijadwalkanStatus),
                    ExpectedConditions.visibilityOfElementLocated(verifikasiButton),
                    ExpectedConditions.visibilityOfElementLocated(tolakButton)));
        } catch (TimeoutException e) {
            printDebugSnapshot("Timeout waiting for detail pengajuan or login redirect");
        }
    }
}
