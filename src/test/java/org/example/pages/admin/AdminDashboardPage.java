package org.example.pages.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminDashboardPage extends BasePage {
    public static final String DASHBOARD_URL = "https://pad-1.vercel.app/dashboard";

    private final By dashboardText = By.xpath("//*[normalize-space()='Dashboard' or contains(normalize-space(),'Dashboard Admin')]");
    private final By adminText = By.xpath("//*[contains(normalize-space(),'Admin')]");

    public AdminDashboardPage() {
        super();
    }

    public void open() {
        navigateTo(DASHBOARD_URL);
        waitForDocumentReady();
    }

    public boolean isDisplayed() {
        if (isRedirectedToLogin()) {
            printDebugSnapshot("Redirected to login on dashboard page");
            return false;
        }

        boolean displayed = waitForAnyVisible(dashboardText, adminText);
        if (!displayed) {
            printDebugSnapshot("Dashboard admin was not displayed");
        }
        return displayed;
    }

    public void waitUntilDisplayedOrLogin() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.urlContains("accounts.google.com"),
                    ExpectedConditions.visibilityOfElementLocated(dashboardText),
                    ExpectedConditions.visibilityOfElementLocated(adminText)));
        } catch (TimeoutException e) {
            printDebugSnapshot("Timeout waiting for dashboard or login redirect");
        }
    }
}
