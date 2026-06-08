package org.example.pages.locators;

import org.openqa.selenium.By;

public final class LoginLocators {

    private LoginLocators() {
    }

    public static final By GOOGLE_LOGIN_BUTTON = By.xpath("//button[contains(., 'Masuk dengan UGM ID')]");
    public static final By DASHBOARD_WELCOME_TEXT = By.xpath("//*[contains(text(), 'Selamat datang')]");
    public static final By GOOGLE_EMAIL_INPUT = By.cssSelector("input[type='email'], #identifierId");
    public static final By GOOGLE_ADVANCED_BUTTON = By.xpath(
            "//div[@role='button' and (contains(text(), 'Advanced') or contains(text(), 'Lanjutan'))] | " +
                    "//span[contains(text(), 'Advanced') or contains(text(), 'Lanjutan')]");
    public static final By GOOGLE_UNSAFE_LINK = By.xpath(
            "//a[contains(@href, 'unsafe') or contains(text(), 'unsafe') or contains(text(), 'tidak aman')]");
    public static final By GOOGLE_CONTINUE_OR_ALLOW_BUTTON = By.xpath(
            "//button[contains(., 'Continue') or contains(., 'Lanjutkan') or contains(., 'Allow') or contains(., 'Izinkan')] | " +
                    "//input[@type='submit' and (@value='Continue' or @value='Lanjutkan' or @value='Allow' or @value='Izinkan')] | " +
                    "//div[@role='button' and (contains(., 'Continue') or contains(., 'Lanjutkan') or contains(., 'Allow') or contains(., 'Izinkan'))] | " +
                    "//span[contains(text(), 'Continue') or contains(text(), 'Lanjutkan') or contains(text(), 'Allow') or contains(text(), 'Izinkan')] | " +
                    "//*[@id='submit_approve_access']");
    public static final By SIDEBAR_FOOTER = By.cssSelector(
            "[data-slot='sidebar-footer'] button, [data-slot='sidebar-footer'] [role='button'], [data-slot='sidebar-footer']");
    public static final By LOGOUT_BUTTON = By.xpath(
            "//*[contains(text(), 'Logout') or contains(text(), 'Log out') or contains(text(), 'Keluar')]/ancestor-or-self::button | " +
                    "//*[contains(text(), 'Logout') or contains(text(), 'Log out') or contains(text(), 'Keluar')]/ancestor-or-self::a | " +
                    "//*[@role='menuitem' and (contains(., 'Logout') or contains(., 'Keluar'))]");

    public static By googleAccountByIdentifier(String email) {
        return By.cssSelector("[data-identifier='" + email + "']");
    }

    public static By googleAccountChooser(String email) {
        return By.xpath("//div[@data-identifier='" + email + "'] | //div[contains(text(), '" + email + "')]");
    }

    public static By dashboardWelcome(String expectedName) {
        return By.xpath("//*[contains(text(), 'Selamat datang') and contains(text(), '" + expectedName + "')]");
    }
}
