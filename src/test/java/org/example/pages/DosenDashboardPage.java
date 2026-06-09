package org.example.pages;

public class DosenDashboardPage extends BasePage {

    public DosenDashboardPage() {
        super();
    }

    public boolean isAccessDenied() {
        String currentUrl = getCurrentUrl().toLowerCase();
        String pageText = getPageText().toLowerCase();

        boolean redirectedFromAdmin =
                !currentUrl.contains("/data-mahasiswa") &&
                        !currentUrl.contains("/form-penjadwalan") &&
                        !currentUrl.contains("/data-dosen") &&
                        !currentUrl.contains("/pengajuan") &&
                        !currentUrl.contains("/ruangan") &&
                        !currentUrl.contains("/riwayat-dan-laporan");

        boolean redirectedToDashboard = currentUrl.contains("/dashboard");

        boolean containsForbiddenText =
                pageText.contains("unauthorized") ||
                        pageText.contains("forbidden") ||
                        pageText.contains("akses ditolak") ||
                        pageText.contains("tidak memiliki akses") ||
                        pageText.contains("not allowed");

        return redirectedFromAdmin || redirectedToDashboard || containsForbiddenText;
    }
}