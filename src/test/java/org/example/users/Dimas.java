package org.example.users;

import org.openqa.selenium.chrome.ChromeOptions;

public class Dimas implements UserConfig {
    @Override
    public void applyChromeProfile(ChromeOptions options) {
        String userHome = System.getProperty("user.home");

        String chromePath = userHome + "\\AppData\\Local\\Google\\Chrome\\User Data";

        options.addArguments("--user-data-dir=" + chromePath);
        options.addArguments("--profile-directory=Default");
    }
}