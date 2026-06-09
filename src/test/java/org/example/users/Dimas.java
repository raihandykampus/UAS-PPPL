package org.example.users;

import org.openqa.selenium.chrome.ChromeOptions;

public class Dimas implements UserConfig {
    @Override
    public void applyChromeProfile(ChromeOptions options) {
        String userHome = System.getProperty("user.home");
        String automationPath = userHome + "\\.config\\simpensi-dimas-dosen-profile";

        options.addArguments("--user-data-dir=" + automationPath);
        options.addArguments("--profile-directory=Default");
    }
}