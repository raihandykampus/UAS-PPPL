package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.example.pages.locators.LoginLocators.*;

public class LoginPage extends BasePage {

    private static final long ACCOUNT_SELECTION_TIMEOUT_MS = 10000;
    private static final long CONSENT_TIMEOUT_MS = 30000;
    private static final long SHORT_RETRY_SLEEP_MS = 500;
    private static final long CONSENT_LOOP_SLEEP_MS = 1000;

    public LoginPage() {
        super();
    }

    public boolean isLoggedIn() {
        return isDashboardState();
    }

    public void clickGoogleLoginBtn() {
        if (skipIfAlreadyLoggedIn("Google login click")) {
            return;
        }
        System.out.println("Clicking Google SSO button...");
        clickElement(GOOGLE_LOGIN_BUTTON);
    }

    public void selectGoogleAccount(String email) {
        if (skipIfAlreadyLoggedIn("account selection")) {
            return;
        }
        System.out.println("Selecting Google account: " + email);
        By accountChooser = googleAccountByIdentifier(email);
        By xpathChooser = googleAccountChooser(email);
        By emailInput = GOOGLE_EMAIL_INPUT;

        long endTime = System.currentTimeMillis() + ACCOUNT_SELECTION_TIMEOUT_MS;
        while (System.currentTimeMillis() < endTime) {
            if (tryClickIfVisible(accountChooser)) {
                return;
            }
            if (tryClickIfVisible(xpathChooser)) {
                return;
            }
            if (isElementDisplayed(emailInput)) {
                System.err.println(
                        ">>> AUTO-ABORT: No authenticated Google account found in this Chrome profile! Please sign in manually first.");
                throw new RuntimeException("ABORT: Chrome profile not logged in to Google account: " + email);
            }
            sleepQuietly(SHORT_RETRY_SLEEP_MS);
        }
        throw new RuntimeException("ABORT: Timeout waiting for Google account chooser or sign-in page.");
    }

    public void handleGoogleConsentScreens() {
        if (skipIfAlreadyLoggedIn("consent screens")) {
            return;
        }
        System.out.println("Waiting and handling Google consent/warning screens...");
        long endTime = System.currentTimeMillis() + CONSENT_TIMEOUT_MS;
        while (System.currentTimeMillis() < endTime) {
            String currentUrl = driver.getCurrentUrl();
            System.out.println("OAuth screen iteration. Current URL: " + currentUrl);

            if (isDashboardState()) {
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
                        sleepQuietly(SHORT_RETRY_SLEEP_MS);
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed to process checkboxes: " + e.getMessage());
            }

            if (tryClickIfPresentQuick(GOOGLE_ADVANCED_BUTTON,
                    "Unverified app screen detected. Clicking 'Advanced'...")) {
                try {
                    tryClickIfPresentQuick(GOOGLE_UNSAFE_LINK, "Clicking the unsafe link...");
                } catch (Exception e) {
                    System.out.println("Failed to click Advanced/Unsafe: " + e.getMessage());
                }
                continue;
            }

            if (tryClickIfPresentQuick(GOOGLE_CONTINUE_OR_ALLOW_BUTTON,
                    "Consent prompt detected. Clicking Continue/Allow...")) {
                continue;
            }

            sleepQuietly(CONSENT_LOOP_SLEEP_MS);
        }
    }

    public boolean isDashboardDisplayed(String expectedName) {
        return isElementDisplayed(dashboardWelcome(expectedName));
    }

    public void logout() {
        System.out.println("Initiating logout...");
        clickElement(SIDEBAR_FOOTER);
        System.out.println("Clicking the logout button in the popup...");
        clickElement(LOGOUT_BUTTON);
    }

    public boolean isLoggedOut() {
        return isElementDisplayed(GOOGLE_LOGIN_BUTTON);
    }

    private boolean skipIfAlreadyLoggedIn(String action) {
        if (isDashboardState()) {
            System.out.println("User is already logged in (dashboard detected). Skipping " + action + ".");
            return true;
        }
        return false;
    }

    private boolean isDashboardState() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("/dashboard") || isElementPresentQuick(DASHBOARD_WELCOME_TEXT);
    }

    private boolean tryClickIfVisible(By locator) {
        try {
            if (isElementDisplayed(locator)) {
                clickElement(locator);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Stale or detached element in tryClickIfVisible: " + e.getMessage());
        }
        return false;
    }

    private boolean tryClickIfPresentQuick(By locator, String message) {
        try {
            if (isElementPresentQuick(locator)) {
                System.out.println(message);
                clickElement(locator);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Stale or detached element in tryClickIfPresentQuick: " + e.getMessage());
        }
        return false;
    }

    private void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}