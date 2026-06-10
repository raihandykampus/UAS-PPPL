package org.example.pages.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminRuanganPage extends BasePage {
    public static final String RUANGAN_URL = "https://pad-1.vercel.app/ruangan";

    private final By pageTitle = By.xpath(
            "//*[self::h1 or self::h2 or self::h3][normalize-space()='Manajemen Ruangan']");
    private final By roomTable = By.xpath("//table | //*[@role='table']");
    private final By roomHu207 = By.xpath("//*[self::td or self::th or self::span or self::div][normalize-space()='HU207']");
    private final By roomHu208 = By.xpath("//*[self::td or self::th or self::span or self::div][normalize-space()='HU208']");
    private final By roomLabRpl = By.xpath("//*[self::td or self::th or self::span or self::div][normalize-space()='Lab RPL']");

    public AdminRuanganPage() {
        super();
    }

    public void open() {
        navigateTo(RUANGAN_URL);
        waitForDocumentReady();
    }

    public void waitUntilDisplayedOrLogin() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.urlContains("accounts.google.com"),
                    ExpectedConditions.visibilityOfElementLocated(pageTitle),
                    ExpectedConditions.visibilityOfElementLocated(roomTable),
                    ExpectedConditions.visibilityOfElementLocated(roomHu207),
                    ExpectedConditions.visibilityOfElementLocated(roomHu208),
                    ExpectedConditions.visibilityOfElementLocated(roomLabRpl)));
        } catch (TimeoutException e) {
            printDebugSnapshot("Timeout waiting for manajemen ruangan or login redirect");
        }
    }

    public boolean isDisplayed() {
        if (isRedirectedToLogin()) {
            printDebugSnapshot("Redirected to login on ruangan page");
            return false;
        }

        boolean displayed = waitForAnyVisible(pageTitle);
        if (!displayed) {
            printDebugSnapshot("Manajemen Ruangan title was not displayed");
        }
        return displayed;
    }

    public boolean isRoomTableDisplayed() {
        if (isRedirectedToLogin()) {
            return false;
        }

        boolean displayed = waitForAnyVisible(roomTable, roomHu207, roomHu208, roomLabRpl);
        if (!displayed) {
            printDebugSnapshot("Room table was not displayed");
        }
        return displayed;
    }

    public boolean hasAtLeastOneRoomData() {
        return waitForAnyVisible(roomHu207, roomHu208, roomLabRpl);
    }

    public boolean hasHu207Room() {
        return waitForAnyVisible(roomHu207);
    }

    public boolean hasHu208Room() {
        return waitForAnyVisible(roomHu208);
    }

    public boolean hasLabRplRoom() {
        return waitForAnyVisible(roomLabRpl);
    }
}
