package org.example.pages.locators;

import org.openqa.selenium.By;

public final class SettingsLocators {

    private SettingsLocators() {
    }

    public static final By EDIT_PROFILE_LINK = By.xpath(
            "//*[contains(text(), 'Edit Profile') or contains(text(), 'Edit Profil') or contains(text(), 'Settings') or contains(text(), 'Pengaturan')]"
    );

    public static final By NAME_INPUT = By.id("name");
    public static final By NIM_INPUT = By.id("nim");
    public static final By TELEPON_INPUT = By.id("telepon");

    public static final By PRODI_DROPDOWN_TRIGGER = By.xpath("//label[@for='prodi']/following-sibling::button");
    public static final By DOSEN_DROPDOWN_TRIGGER = By.xpath("//label[@for='dosenPembimbing']/following-sibling::button");

    public static final By SAVE_SETTINGS_BUTTON = By.xpath("//button[@type='submit' and contains(., 'Save Settings')]");

    public static By selectOption(String optionText) {
        return By.xpath("//div[@role='option'][contains(., '" + optionText + "')] | //*[contains(text(), '" + optionText + "')]/ancestor-or-self::div[@role='option']");
    }
}
