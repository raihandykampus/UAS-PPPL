package org.example.pages.locators;

import org.openqa.selenium.By;

public final class DosenLocators {

    private DosenLocators() {
    }

    public static final By DETAIL_JADWAL_MENU = By.xpath("//a[@href='/detail-jadwal']");
    public static final By RIWAYAT_UJIAN_MENU = By.xpath("//a[@href='/riwayat-ujian']");
    public static final By NOTIFIKASI_MENU = By.xpath("//a[@href='/riwayat-pengajuan']");

    public static final By DETAIL_JADWAL_TITLE = By.xpath("//h1[normalize-space()='Detail Jadwal']");
    public static final By RIWAYAT_UJIAN_TITLE = By.xpath("//h1[normalize-space()='Riwayat Ujian']");
    public static final By NOTIFIKASI_TITLE = By.xpath("//h1[normalize-space()='Notifikasi']");

    public static final By TABLE_CONTAINER = By.xpath(
            "//div[contains(@class,'overflow-x-auto') and contains(@class,'rounded-lg')]"
    );

    public static final By FIRST_TABLE_ROW = By.xpath("(//tbody/tr)[1]");

    public static final By DETAIL_BUTTON = By.xpath("//button[@title='Lihat Berita Acara']");

    public static final By FALLBACK_FIRST_ROW_BUTTON = By.xpath("(//tbody/tr[1]//button)[1]");

    public static final By MODAL_HEADER = By.xpath(
            "//div[contains(@class,'flex') and contains(@class,'items-start') and contains(@class,'gap-3')]"
    );

    public static final By MODAL_SCROLL_CONTAINER = By.xpath(
            "//div[contains(@class,'overflow-y-auto') and contains(@class,'px-6') and contains(@class,'py-4')]"
    );

    public static final By DOWNLOAD_BUTTON = By.xpath("//button[normalize-space()='Download']");

    public static final By CLOSE_BUTTON = By.xpath(
            "//button[contains(@class,'absolute') and contains(@class,'top-4') and contains(@class,'right-4')]"
    );

    public static final By FIRST_NOTIFICATION_ITEM = By.xpath(
            "(//div[contains(@class,'flex') and contains(@class,'gap-4') and contains(@class,'relative')])[1]"
    );
}