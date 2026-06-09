package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DosenDetailJadwalPage extends BasePage {

    private final By dashboardScheduleRow = By.xpath(
            "//tr[contains(.,'Dimas Satriaa') or contains(.,'buat PPPL') or contains(.,'HU207')]"
    );

    private final By dashboardDetailButton = By.xpath(
            "//tr[contains(.,'Dimas Satriaa') or contains(.,'buat PPPL') or contains(.,'HU207')]//button[contains(.,'Detail')]"
    );

    private final By fallbackDetailButton = By.xpath(
            "(//button[contains(.,'Detail')])[1]"
    );

    private final By downloadButton = By.xpath(
            "//button[contains(.,'Download')]"
    );

    public DosenDetailJadwalPage() {
        super();
    }

    public boolean isDashboardScheduleDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardScheduleRow));
            return true;
        } catch (Exception e) {
            System.out.println("[DosenDetailJadwalPage] Jadwal tidak ditemukan di dashboard.");
            System.out.println("[DosenDetailJadwalPage] Page text: " + getPageText());
            return false;
        }
    }

    public void openDetailFromDashboard() {
        try {
            System.out.println("[DosenDetailJadwalPage] Klik tombol Detail dari row dashboard...");
            wait.until(ExpectedConditions.elementToBeClickable(dashboardDetailButton)).click();
        } catch (Exception e) {
            System.out.println("[DosenDetailJadwalPage] Locator row gagal, klik tombol Detail pertama...");
            wait.until(ExpectedConditions.elementToBeClickable(fallbackDetailButton)).click();
        }

        sleep(1000);
    }

    public boolean isModalDetailDisplayed() {
        try {
            sleep(1000);

            String pageText = getPageText().toLowerCase();

            System.out.println("[DosenDetailJadwalPage] Cek modal detail...");
            System.out.println("[DosenDetailJadwalPage] Page text setelah klik detail: " + pageText);

            boolean modalOpened =
                    pageText.contains("berita acara") &&
                            pageText.contains("close");

            return modalOpened;

        } catch (Exception e) {
            System.out.println("[DosenDetailJadwalPage] Modal detail tidak tampil.");
            System.out.println("[DosenDetailJadwalPage] Error: " + e.getMessage());
            System.out.println("[DosenDetailJadwalPage] Page text: " + getPageText());
            return false;
        }
    }

    public boolean isDownloadButtonDisplayed() {
        try {
            System.out.println("[DosenDetailJadwalPage] Scroll modal/container untuk mencari tombol Download...");

            for (int i = 0; i < 6; i++) {
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelectorAll('*').forEach(el => {" +
                                "if (el.scrollHeight > el.clientHeight) {" +
                                "el.scrollTop = el.scrollTop + 500;" +
                                "}" +
                                "});"
                );

                sleep(500);

                String pageText = getPageText().toLowerCase();
                if (pageText.contains("download") || pageText.contains("dokumen tugas akhir")) {
                    break;
                }
            }

            WebElement download = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(downloadButton)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    download
            );

            return download.isDisplayed();

        } catch (Exception e) {
            System.out.println("[DosenDetailJadwalPage] Tombol Download tidak ditemukan.");
            System.out.println("[DosenDetailJadwalPage] Error: " + e.getMessage());
            System.out.println("[DosenDetailJadwalPage] Page text: " + getPageText());
            return false;
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}