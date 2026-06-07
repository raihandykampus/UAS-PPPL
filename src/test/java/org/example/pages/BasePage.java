package org.example.pages;

import org.example.users.UserConfig;
import org.example.users.UserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
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

        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
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

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public String getPageText() {
        try {
            return driver.findElement(By.tagName("body")).getText();
        } catch (Exception e) {
            return "Unable to retrieve body text: " + e.getMessage();
        }
    }
}