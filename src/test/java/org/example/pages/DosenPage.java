package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.example.pages.locators.DosenLocators.*;

public class DosenPage extends BasePage {

    private String mainAppWindowHandle;

    public DosenPage() {
        super();
    }

    public boolean isDosenDashboardDisplayed() {
        try {
            String currentUrl = getCurrentUrl().toLowerCase();
            String pageText = getPageText().toLowerCase();

            boolean correctUrl = currentUrl.contains("/dashboard");

            boolean hasDashboardIdentity = pageText.contains("selamat datang") &&
                    pageText.contains("dosen");

            boolean hasDashboardContent = pageText.contains("ujian mendatang") ||
                    pageText.contains("mahasiswa dibimbing") ||
                    pageText.contains("detail jadwal");

            return correctUrl && hasDashboardIdentity && hasDashboardContent;

        } catch (Exception e) {
            System.out.println("[DosenPage] Dashboard dosen tidak valid.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Current URL: " + getCurrentUrl());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    public void openDetailJadwalMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(DETAIL_JADWAL_MENU)).click();
        sleep(700);
    }

    public boolean isDetailJadwalSidebarPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(DETAIL_JADWAL_TITLE));

            String currentUrl = getCurrentUrl().toLowerCase();
            String pageText = getPageText().toLowerCase();

            boolean correctUrl = currentUrl.contains("/detail-jadwal");
            boolean hasTitle = isElementDisplayed(DETAIL_JADWAL_TITLE);
            boolean hasTable = isElementDisplayed(TABLE_CONTAINER);

            boolean hasPageContent = pageText.contains("detail jadwal") &&
                    pageText.contains("lihat jadwal ujian");

            return correctUrl && hasTitle && hasTable && hasPageContent;

        } catch (Exception e) {
            System.out.println("[DosenPage] Halaman Detail Jadwal tidak valid.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Current URL: " + getCurrentUrl());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    public boolean isAnyScheduleAvailable() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_TABLE_ROW));

            String rowText = driver.findElement(FIRST_TABLE_ROW).getText().trim();

            System.out.println("[DosenPage] Row jadwal pertama:");
            System.out.println(rowText);

            return !rowText.isEmpty();

        } catch (Exception e) {
            System.out.println("[DosenPage] Tidak ada jadwal di tabel.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    public void openFirstScheduleDetail() {
        try {
            System.out.println("[DosenPage] Klik tombol Lihat Berita Acara...");
            wait.until(ExpectedConditions.elementToBeClickable(DETAIL_BUTTON)).click();
        } catch (Exception e) {
            System.out.println("[DosenPage] Tombol Lihat Berita Acara gagal, pakai fallback row pertama...");
            wait.until(ExpectedConditions.elementToBeClickable(FALLBACK_FIRST_ROW_BUTTON)).click();
        }

        sleep(700);
    }

    public boolean isModalDetailDisplayed() {
        try {
            sleep(700);

            String pageText = getPageText().toLowerCase();

            System.out.println("[DosenPage] Cek modal detail...");
            System.out.println("[DosenPage] Page text setelah buka modal: " + pageText);

            boolean hasModalHeader = isElementDisplayed(MODAL_HEADER);
            boolean hasCloseButton = isElementDisplayed(CLOSE_BUTTON);

            boolean hasModalText = pageText.contains("berita acara") ||
                    pageText.contains("data mahasiswa") ||
                    pageText.contains("informasi jadwal");

            return hasModalHeader && hasCloseButton && hasModalText;

        } catch (Exception e) {
            System.out.println("[DosenPage] Modal detail tidak tampil.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    public boolean isDownloadButtonDisplayed() {
        try {
            System.out.println("[DosenPage] Scroll modal untuk mencari tombol Download...");

            scrollModalUntilDocumentSectionVisible();

            WebElement download = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(DOWNLOAD_BUTTON));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    download);

            sleep(300);

            return download.isDisplayed();

        } catch (Exception e) {
            System.out.println("[DosenPage] Tombol Download tidak ditemukan.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    public void openDocumentFile() {
        try {
            System.out.println("[DosenPage] Membuka berkas naskah dari tombol Download...");

            scrollModalUntilDocumentSectionVisible();

            WebElement download = wait.until(
                    ExpectedConditions.elementToBeClickable(DOWNLOAD_BUTTON));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    download);

            sleep(300);

            mainAppWindowHandle = driver.getWindowHandle();

            download.click();

            System.out.println("[DosenPage] Tombol Download diklik.");
            System.out.println("[DosenPage] Menahan halaman berkas selama 5 detik...");

            sleep(5000);

            boolean foundDocumentTab = switchToDocumentTab();

            if (!foundDocumentTab) {
                System.out.println("[DosenPage] Tab dokumen belum ketemu, tunggu ulang 1 detik...");
                sleep(1000);
                foundDocumentTab = switchToDocumentTab();
            }

            if (!foundDocumentTab) {
                System.out.println("[DosenPage] Tab dokumen tetap belum ditemukan.");
                printAllOpenTabs();
            }

            System.out.println("[DosenPage] URL setelah membuka berkas: " + getCurrentUrl());
            System.out.println("[DosenPage] Title setelah membuka berkas: " + driver.getTitle());

        } catch (Exception e) {
            System.out.println("[DosenPage] Gagal membuka berkas naskah.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Current URL: " + getCurrentUrl());
            printAllOpenTabs();
            throw new RuntimeException("Gagal membuka berkas naskah.", e);
        }
    }

    public boolean isDocumentFileOpened() {
        try {
            boolean foundDocumentTab = switchToDocumentTab();

            String currentUrl = getCurrentUrl().toLowerCase();
            String pageTitle = driver.getTitle().toLowerCase();

            System.out.println("[DosenPage] Validasi halaman berkas...");
            System.out.println("[DosenPage] Current URL: " + currentUrl);
            System.out.println("[DosenPage] Page title: " + pageTitle);

            boolean openedDocumentUrl = isDocumentUrl(currentUrl);
            boolean openedDocumentViewer = isDocumentTitle(pageTitle);

            return foundDocumentTab || openedDocumentUrl || openedDocumentViewer;

        } catch (Exception e) {
            System.out.println("[DosenPage] Halaman berkas tidak terbuka.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            printAllOpenTabs();
            return false;
        }
    }

    public void switchBackToMainApplicationTab() {
        try {
            System.out.println("[DosenPage] Menutup tab dokumen jika ada...");

            List<String> windows = new ArrayList<>(driver.getWindowHandles());

            for (String window : windows) {
                driver.switchTo().window(window);

                String currentUrl = getCurrentUrl().toLowerCase();
                String title = driver.getTitle().toLowerCase();

                if (isDocumentUrl(currentUrl) || isDocumentTitle(title)) {
                    System.out.println("[DosenPage] Menutup tab dokumen: " + currentUrl);
                    driver.close();
                    break;
                }
            }

            windows = new ArrayList<>(driver.getWindowHandles());

            if (mainAppWindowHandle != null && windows.contains(mainAppWindowHandle)) {
                driver.switchTo().window(mainAppWindowHandle);
                System.out.println("[DosenPage] Kembali ke tab aplikasi utama.");
            } else {
                for (String window : windows) {
                    driver.switchTo().window(window);

                    String currentUrl = getCurrentUrl().toLowerCase();

                    if (currentUrl.contains("pad-1.vercel.app")) {
                        mainAppWindowHandle = window;
                        System.out.println("[DosenPage] Kembali ke tab aplikasi utama melalui URL.");
                        break;
                    }
                }
            }

            closeModalIfOpen();

            System.out.println("[DosenPage] Menahan halaman Detail Jadwal selama 3 detik setelah modal ditutup...");
            sleep(3000);

        } catch (Exception e) {
            System.out.println("[DosenPage] Gagal kembali ke tab aplikasi utama.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            printAllOpenTabs();
            throw new RuntimeException("Gagal kembali ke tab aplikasi utama.", e);
        }
    }

    public void openRiwayatUjianMenu() {
        closeModalIfOpen();

        wait.until(ExpectedConditions.elementToBeClickable(RIWAYAT_UJIAN_MENU)).click();

        System.out.println("[DosenPage] Menahan halaman Riwayat Ujian selama 3 detik...");
        sleep(3000);
    }

    public boolean isRiwayatUjianPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(RIWAYAT_UJIAN_TITLE));

            String currentUrl = getCurrentUrl().toLowerCase();
            String pageText = getPageText().toLowerCase();

            boolean correctUrl = currentUrl.contains("/riwayat-ujian");
            boolean hasTitle = isElementDisplayed(RIWAYAT_UJIAN_TITLE);
            boolean hasTable = isElementDisplayed(TABLE_CONTAINER);

            boolean hasPageStructure = pageText.contains("riwayat ujian") &&
                    pageText.contains("nama mahasiswa") &&
                    pageText.contains("judul tugas akhir");

            return correctUrl && hasTitle && hasTable && hasPageStructure;

        } catch (Exception e) {
            System.out.println("[DosenPage] Halaman Riwayat Ujian tidak valid.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    public void openNotifikasiMenu() {
        closeModalIfOpen();

        wait.until(ExpectedConditions.elementToBeClickable(NOTIFIKASI_MENU)).click();

        System.out.println("[DosenPage] Menahan halaman Notifikasi selama 3 detik...");
        sleep(3000);
    }

    public boolean isNotifikasiPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(NOTIFIKASI_TITLE));
            wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NOTIFICATION_ITEM));

            String currentUrl = getCurrentUrl().toLowerCase();
            String firstNotificationText = driver.findElement(FIRST_NOTIFICATION_ITEM).getText().toLowerCase();

            System.out.println("[DosenPage] Notifikasi pertama:");
            System.out.println(firstNotificationText);

            boolean correctUrl = currentUrl.contains("/riwayat-pengajuan");
            boolean hasTitle = isElementDisplayed(NOTIFIKASI_TITLE);

            boolean hasNotificationContent = !firstNotificationText.trim().isEmpty()
                    && (firstNotificationText.contains("pengajuan") ||
                            firstNotificationText.contains("dijadwalkan") ||
                            firstNotificationText.contains("disetujui"));

            return correctUrl && hasTitle && hasNotificationContent;

        } catch (Exception e) {
            System.out.println("[DosenPage] Halaman Notifikasi tidak valid.");
            System.out.println("[DosenPage] Error: " + e.getMessage());
            System.out.println("[DosenPage] Page text: " + getPageText());
            return false;
        }
    }

    private boolean switchToDocumentTab() {
        try {
            for (String window : driver.getWindowHandles()) {
                driver.switchTo().window(window);

                String currentUrl = getCurrentUrl().toLowerCase();
                String title = driver.getTitle().toLowerCase();

                System.out.println("[DosenPage] Cek tab:");
                System.out.println("URL   : " + currentUrl);
                System.out.println("Title : " + title);

                if (isDocumentUrl(currentUrl) || isDocumentTitle(title)) {
                    System.out.println("[DosenPage] Tab dokumen ditemukan.");
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            System.out.println("[DosenPage] Gagal mencari tab dokumen: " + e.getMessage());
            return false;
        }
    }

    private boolean isDocumentUrl(String currentUrl) {
        return currentUrl.contains(".pdf") ||
                currentUrl.contains("supabase") ||
                currentUrl.contains("/storage/v1/object") ||
                currentUrl.contains("dokumen");
    }

    private boolean isDocumentTitle(String title) {
        return title.contains("pdf") ||
                title.contains("powerpoint") ||
                title.contains("presentation");
    }

    private void closeModalIfOpen() {
        try {
            List<WebElement> closeButtons = driver.findElements(CLOSE_BUTTON);

            if (!closeButtons.isEmpty() && closeButtons.get(0).isDisplayed()) {
                System.out.println("[DosenPage] Modal masih terbuka, menutup modal...");

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        closeButtons.get(0));

                wait.until(ExpectedConditions.invisibilityOfElementLocated(CLOSE_BUTTON));

                System.out.println("[DosenPage] Modal ditutup.");
                return;
            }

            System.out.println("[DosenPage] Modal tidak terbuka, lanjut.");

        } catch (Exception e) {
            System.out.println("[DosenPage] Modal tidak perlu ditutup atau sudah tertutup.");
        }
    }

    private void scrollModalUntilDocumentSectionVisible() {
        for (int i = 0; i < 10; i++) {
            try {
                if (isElementPresentQuick(DOWNLOAD_BUTTON)) {
                    System.out.println("[DosenPage] Tombol Download sudah terlihat.");
                    return;
                }
            } catch (Exception ignored) {
            }

            try {
                WebElement modalScroll = driver.findElement(MODAL_SCROLL_CONTAINER);

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollTop + 500;",
                        modalScroll);
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelectorAll('*').forEach(el => {" +
                                "if (el.scrollHeight > el.clientHeight) {" +
                                "el.scrollTop = el.scrollTop + 500;" +
                                "}" +
                                "});");
            }

            sleep(300);
        }
    }

    private void printAllOpenTabs() {
        try {
            System.out.println("[DosenPage] Daftar semua tab terbuka:");

            int index = 1;

            for (String window : driver.getWindowHandles()) {
                driver.switchTo().window(window);

                System.out.println("Tab " + index);
                System.out.println("URL   : " + getCurrentUrl());
                System.out.println("Title : " + driver.getTitle());

                index++;
            }
        } catch (Exception e) {
            System.out.println("[DosenPage] Gagal print daftar tab: " + e.getMessage());
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