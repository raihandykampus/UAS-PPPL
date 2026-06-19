package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.example.pages.locators.LoginLocators.SIDEBAR_FOOTER;
import static org.example.pages.locators.SettingsLocators.*;

public class SettingsPage extends BasePage {

    public SettingsPage() {
        super();
    }

    public void navigateToSettings() {
        System.out.println("Navigating to settings/profile page...");
        clickElement(SIDEBAR_FOOTER);
        clickElement(EDIT_PROFILE_LINK);
        wait.until(ExpectedConditions.urlContains("/profile"));
        System.out.println("Landed on profile page: " + getCurrentUrl());
    }

    public void fillTextField(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.click();

        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);

        if (value != null && !value.isEmpty()) {
            element.sendKeys(value);
        } else {
            element.sendKeys(" ");
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void selectDropdownOption(By triggerLocator, String optionText) {
        System.out.println("Opening dropdown trigger: " + triggerLocator);
        clickElement(triggerLocator);
        By optionLocator = selectOption(optionText);
        System.out.println("Clicking option: " + optionText);
        clickElement(optionLocator);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    public void updateProfileDetails(String name, String nim, String prodi, String telepon, String dosen) {
        fillTextField(NAME_INPUT, name);
        fillTextField(NIM_INPUT, nim);
        selectDropdownOption(PRODI_DROPDOWN_TRIGGER, prodi);
        fillTextField(TELEPON_INPUT, telepon);
        selectDropdownOption(DOSEN_DROPDOWN_TRIGGER, dosen);
    }

    public void saveSettings() {
        System.out.println("Clicking Save Settings button...");
        clickElement(SAVE_SETTINGS_BUTTON);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}
    }

    public boolean isAlertTriangleDisplayed() {
        return isElementDisplayed(ALERT_TRIANGLE_ICON);
    }

    public boolean isProfileUpdatedSuccessfully(String name, String nim, String prodi, String telepon, String dosen) {
        try {
            waitForDocumentReady();
            String currentUrl = getCurrentUrl();
            if (!currentUrl.contains("/profile")) {
                System.out.println("Verification failed: Not on /profile page. URL: " + currentUrl);
                return false;
            }

            WebElement nameEl = driver.findElement(NAME_INPUT);
            WebElement nimEl = driver.findElement(NIM_INPUT);
            WebElement teleponEl = driver.findElement(TELEPON_INPUT);

            String actualName = nameEl.getAttribute("value");
            String actualNim = nimEl.getAttribute("value");
            String actualTelepon = teleponEl.getAttribute("value");

            System.out.println("Verifying values:");
            System.out.println("Expected Name: " + name + " | Actual: " + actualName);
            System.out.println("Expected NIM:  " + nim + " | Actual: " + actualNim);
            System.out.println("Expected Telp: " + telepon + " | Actual: " + actualTelepon);

            boolean textFieldsMatch = name.equals(actualName) && nim.equals(actualNim) && telepon.equals(actualTelepon);

            WebElement prodiBtn = driver.findElement(PRODI_DROPDOWN_TRIGGER);
            WebElement dosenBtn = driver.findElement(DOSEN_DROPDOWN_TRIGGER);

            String actualProdi = prodiBtn.getText();
            String actualDosen = dosenBtn.getText();

            System.out.println("Expected Prodi: " + prodi + " | Actual: " + actualProdi);
            System.out.println("Expected Dosen: " + dosen + " | Actual: " + actualDosen);

            boolean dropdownsMatch = actualProdi.contains(prodi) && actualDosen.contains(dosen);

            return textFieldsMatch && dropdownsMatch;

        } catch (Exception e) {
            System.out.println("Exception during profile verification: " + e.getMessage());
            return false;
        }
    }
}
