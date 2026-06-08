package org.example.pages;

import static org.example.pages.locators.LoginLocators.*;

public class LogoutPage extends BasePage {

    public LogoutPage() {
        super();
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
}
