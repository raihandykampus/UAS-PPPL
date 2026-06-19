package org.example.pages.locators;

import org.openqa.selenium.By;

public final class FormPengajuanLocators {

    private FormPengajuanLocators() {
    }

    public static final By FORM_PENGAJUAN_MENU_LINK = By.xpath(
            "//a[@href='/form-pengajuan']"
    );

    public static final By JUDUL_INPUT = By.id("judul");
    public static final By FILE_INPUT = By.id("berkas");
    public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit' and contains(., 'Submit')]");
    public static final By SUCCESS_HEADING = By.xpath("//*[contains(text(), 'Pengajuan Berhasil!')]");
    public static final By ALERT_TRIANGLE_ICON = By.cssSelector("svg.lucide-triangle-alert, svg[class*='lucide-triangle-alert']");
    public static final By DIALOG_DESCRIPTION = By.cssSelector("[data-slot='dialog-description'], #radix-_r_2_");
    public static final By DIALOG_CLOSE_BUTTON = By.cssSelector("[data-slot='dialog-footer'] button, [data-slot='dialog-close']");
}