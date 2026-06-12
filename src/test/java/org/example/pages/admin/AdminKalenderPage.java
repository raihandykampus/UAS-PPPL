package org.example.pages.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminKalenderPage extends BasePage {
    public static final String KALENDER_URL = "https://pad-1.vercel.app/kalender-utama";

    private final By kalenderTitle = By.xpath("//*[normalize-space()='Kalender Utama']");
    private final By eventUjianTA = By.xpath("//*[normalize-space()='Ujian TA']");

    public AdminKalenderPage() {
        super();
    }

    public void open() {
        navigateTo(KALENDER_URL);
        waitForDocumentReady();
    }

    public boolean isKalenderPageDisplayed() {
        try {
            waitForCalendarOrLoginState();
            if (isRedirectedToLogin()) {
                printDebugSnapshot("Redirected to login on kalender page");
                return false;
            }

            return wait.until(ExpectedConditions.visibilityOfElementLocated(kalenderTitle)).isDisplayed();
        } catch (TimeoutException e) {
            printDebugSnapshot("Kalender page was not displayed");
            return false;
        }
    }

    public boolean hasExpectedCalendarEvent() {
        return isEventUjianTADisplayed();
    }

    public boolean isEventUjianTADisplayed() {
        try {
            waitForCalendarOrLoginState();
            if (isRedirectedToLogin()) {
                printDebugSnapshot("Redirected to login before finding event Ujian TA");
                return false;
            }

            WebElement event = wait.until(ExpectedConditions.visibilityOfElementLocated(eventUjianTA));
            return event.isDisplayed();
        } catch (TimeoutException e) {
            System.err.println("Event 'Ujian TA' was not found. Failed at URL: " + getCurrentUrl());
            printDebugSnapshot("Event Ujian TA lookup failed");
            return false;
        }
    }

    public void waitUntilDisplayedOrLogin() {
        try {
            waitForCalendarOrLoginState();
        } catch (TimeoutException e) {
            printDebugSnapshot("Timeout waiting for kalender or login redirect");
        }
    }

    private void waitForCalendarOrLoginState() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/login"),
                ExpectedConditions.urlContains("accounts.google.com"),
                ExpectedConditions.visibilityOfElementLocated(kalenderTitle),
                ExpectedConditions.visibilityOfElementLocated(eventUjianTA)));
    }
}
