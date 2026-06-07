package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LoginPage extends BasePage {

    private final By googleLoginButton = By.xpath("//button[contains(., 'Masuk dengan UGM ID')]");

    public LoginPage() {
        super();
    }

    public void clickGoogleLoginBtn() {
        System.out.println("Clicking Google SSO button...");
        clickElement(googleLoginButton);
    }

    public void selectGoogleAccount(String email) {
        System.out.println("Selecting Google account: " + email);
        By accountChooser = By.cssSelector("[data-identifier='" + email + "']");
        By xpathChooser = By
                .xpath("//div[@data-identifier='" + email + "'] | //div[contains(text(), '" + email + "')]");
        By emailInput = By.cssSelector("input[type='email'], #identifierId");

        long endTime = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < endTime) {
            if (isElementDisplayed(accountChooser)) {
                clickElement(accountChooser);
                return;
            }
            if (isElementDisplayed(xpathChooser)) {
                clickElement(xpathChooser);
                return;
            }
            if (isElementDisplayed(emailInput)) {
                System.err.println(
                        ">>> AUTO-ABORT: No authenticated Google account found in this Chrome profile! Please sign in manually first.");
                throw new RuntimeException("ABORT: Chrome profile not logged in to Google account: " + email);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
        throw new RuntimeException("ABORT: Timeout waiting for Google account chooser or sign-in page.");
    }

    public void handleGoogleConsentScreens() {
        System.out.println("Waiting and handling Google consent/warning screens...");
        long endTime = System.currentTimeMillis() + 30000;
        while (System.currentTimeMillis() < endTime) {
            String currentUrl = driver.getCurrentUrl();
            System.out.println("OAuth screen iteration. Current URL: " + currentUrl);

            if (currentUrl.contains("pad-1.vercel.app/dashboard") ||
                    (!currentUrl.contains("pad-1.vercel.app/login")
                            && isElementPresentQuick(By.xpath("//*[contains(text(), 'Selamat datang')]")))) {
                System.out.println("Successfully bypassed OAuth consent screens, reached dashboard.");
                break;
            }

            try {
                List<WebElement> checkboxes = driver
                        .findElements(By.cssSelector("input[type='checkbox'], [role='checkbox']"));
                for (WebElement cb : checkboxes) {
                    String checked = cb.getAttribute("aria-checked");
                    String selected = cb.getAttribute("checked");
                    boolean isChecked = "true".equals(checked) || "true".equals(selected) || cb.isSelected();
                    if (!isChecked && cb.isDisplayed() && cb.isEnabled()) {
                        System.out.println("Auto-checking checkbox: " + cb.getTagName() + " - " + cb.getText());
                        cb.click();
                        Thread.sleep(500);
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed to process checkboxes: " + e.getMessage());
            }

            By advancedBtn = By.xpath(
                    "//div[@role='button' and (contains(text(), 'Advanced') or contains(text(), 'Lanjutan'))] | //span[contains(text(), 'Advanced') or contains(text(), 'Lanjutan')]");
            if (isElementPresentQuick(advancedBtn)) {
                System.out.println("Unverified app screen detected. Clicking 'Advanced'...");
                try {
                    clickElement(advancedBtn);

                    By unsafeLink = By.xpath(
                            "//a[contains(@href, 'unsafe') or contains(text(), 'unsafe') or contains(text(), 'tidak aman')]");
                    if (isElementPresentQuick(unsafeLink)) {
                        System.out.println("Clicking the unsafe link...");
                        clickElement(unsafeLink);
                    }
                } catch (Exception e) {
                    System.out.println("Failed to click Advanced/Unsafe: " + e.getMessage());
                }
                continue;
            }

            By continueBtn = By.xpath(
                    "//button[contains(., 'Continue') or contains(., 'Lanjutkan') or contains(., 'Allow') or contains(., 'Izinkan')] | "
                            +
                            "//input[@type='submit' and (@value='Continue' or @value='Lanjutkan' or @value='Allow' or @value='Izinkan')] | "
                            +
                            "//div[@role='button' and (contains(., 'Continue') or contains(., 'Lanjutkan') or contains(., 'Allow') or contains(., 'Izinkan'))] | "
                            +
                            "//span[contains(text(), 'Continue') or contains(text(), 'Lanjutkan') or contains(text(), 'Allow') or contains(text(), 'Izinkan')] | "
                            +
                            "//*[@id='submit_approve_access']");
            if (isElementPresentQuick(continueBtn)) {
                System.out.println("Consent prompt detected. Clicking Continue/Allow...");
                try {
                    clickElement(continueBtn);
                } catch (Exception e) {
                    System.out.println("Failed to click Continue/Allow: " + e.getMessage());
                }
                continue;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public boolean isDashboardDisplayed(String expectedName) {
        By welcomeText = By
                .xpath("//*[contains(text(), 'Selamat datang') and contains(text(), '" + expectedName + "')]");
        return isElementDisplayed(welcomeText);
    }
}