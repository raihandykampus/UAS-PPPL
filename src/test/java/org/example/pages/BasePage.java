package org.example.pages;

import org.example.users.UserConfig;
import org.example.users.UserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;


public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final int DEBUG_SOURCE_LENGTH = 1000;

    public BasePage() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--remote-debugging-port=9222");
            options.addArguments("--password-store=basic");
            options.addArguments("--use-mock-keychain");
            options.addArguments("--ozone-platform=x11");

            UserConfig activeProfile = UserFactory.getUserProfile();
            activeProfile.applyChromeProfile(options);

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
    }

    protected void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementPresentQuick(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                return elements.get(0).isDisplayed();
            }
        } catch (Exception ignored) {}
        return false;
    }

    protected boolean waitForAnyVisible(By... locators) {
        try {
            wait.until(webDriver -> {
                for (By locator : locators) {
                    List<WebElement> elements = webDriver.findElements(locator);
                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            return true;
                        }
                    }
                }
                return false;
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void waitForDocumentReady() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")
                    .equals("complete"));
        } catch (TimeoutException e) {
            System.err.println("Document readyState did not become complete. Current URL: " + getCurrentUrl());
        }
    }

    public boolean isRedirectedToLogin() {
        String currentUrl = getCurrentUrl().toLowerCase();
        return currentUrl.contains("/login") || currentUrl.contains("accounts.google.com");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public String getPageSourceSnippet() {
        String source = getPageSource();
        if (source == null || source.isBlank()) {
            return "[empty page source]";
        }

        String normalized = source.replaceAll("\\s+", " ").trim();
        if (normalized.length() <= DEBUG_SOURCE_LENGTH) {
            return normalized;
        }

        return normalized.substring(0, DEBUG_SOURCE_LENGTH) + "...";
    }

    public void printDebugSnapshot(String label) {
        System.out.println("========== DEBUG: " + label + " ==========");
        System.out.println("Current URL : " + getCurrentUrl());
        System.out.println("Page title  : " + getTitle());
        System.out.println("Source head : " + getPageSourceSnippet());
        System.out.println("==========================================");
    }

    public String getLoginPreconditionMessage() {
        return "Precondition gagal: admin belum login pada Chrome profile test.";
    }

    public String getPageText() {
        try {
            return driver.findElement(By.tagName("body")).getText();
        } catch (Exception e) {
            return "Unable to retrieve body text: " + e.getMessage();
        }
    }
}
