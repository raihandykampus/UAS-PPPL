package org.example.pages.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.pages.BasePage;

public class AdminPenjadwalanDummyPage extends BasePage {
    public static final String ERROR_DOSEN_PENGUJI_REQUIRED = "Dosen penguji wajib diisi";
    public static final String ERROR_RUANGAN_REQUIRED = "Ruangan sidang wajib diisi";
    public static final String ERROR_TANGGAL_REQUIRED = "Tanggal sidang wajib diisi";
    public static final String ERROR_JADWAL_CONFLICT = "Jadwal bentrok dengan jadwal lain";

    private AdminPenjadwalanDummyData dummyData;
    private final List<String> validationErrors = new ArrayList<>();
    private boolean scheduleVisibleOnCalendar;

    public AdminPenjadwalanDummyPage() {
        super();
    }

    public void loadVerifiedDummySubmission() {
        dummyData = AdminPenjadwalanDummyData.verifiedSubmission();
        validationErrors.clear();
        scheduleVisibleOnCalendar = false;
    }

    public boolean hasVerifiedDummySubmission() {
        return dummyData != null
                && AdminPenjadwalanDummyData.DEFAULT_MAHASISWA.equals(dummyData.getMahasiswa())
                && AdminPenjadwalanDummyData.DEFAULT_JUDUL.equals(dummyData.getJudul())
                && AdminPenjadwalanDummyData.STATUS_SIAP_DIJADWALKAN.equals(dummyData.getStatus());
    }

    public void selectDefaultExaminer() {
        requireDummyData();
        dummyData.setDosenPenguji(AdminPenjadwalanDummyData.DEFAULT_DOSEN_PENGUJI);
    }

    public void clearExaminer() {
        requireDummyData();
        dummyData.setDosenPenguji(null);
    }

    public void selectDefaultRoom() {
        requireDummyData();
        dummyData.setRuangan(AdminPenjadwalanDummyData.DEFAULT_RUANGAN);
    }

    public void clearRoom() {
        requireDummyData();
        dummyData.setRuangan(null);
    }

    public void selectDefaultDate() {
        selectDate(AdminPenjadwalanDummyData.DEFAULT_TANGGAL);
    }

    public void selectMinimumBoundaryDate() {
        selectDate(AdminPenjadwalanDummyData.MIN_TANGGAL);
    }

    public void selectMaximumBoundaryDate() {
        selectDate(AdminPenjadwalanDummyData.MAX_TANGGAL);
    }

    public void clearDate() {
        requireDummyData();
        dummyData.setTanggalSidang(null);
    }

    public void selectConflictingRoom() {
        requireDummyData();
        dummyData.setRuangan(AdminPenjadwalanDummyData.CONFLICT_RUANGAN);
    }

    public void selectConflictingDate() {
        selectDate(AdminPenjadwalanDummyData.CONFLICT_TANGGAL);
    }

    public void clearAllScheduleFields() {
        requireDummyData();
        dummyData.setDosenPenguji(null);
        dummyData.setRuangan(null);
        dummyData.setTanggalSidang(null);
    }

    public void saveSchedule() {
        requireDummyData();
        validationErrors.clear();
        scheduleVisibleOnCalendar = false;

        validateRequiredFields();
        validateScheduleConflict();

        if (validationErrors.isEmpty()) {
            dummyData.setStatus(AdminPenjadwalanDummyData.STATUS_DIJADWALKAN);
            scheduleVisibleOnCalendar = true;
        }
    }

    public String getSubmissionStatus() {
        requireDummyData();
        return dummyData.getStatus();
    }

    public boolean isScheduleVisibleOnCalendar() {
        return scheduleVisibleOnCalendar;
    }

    public boolean hasValidationError(String message) {
        return validationErrors.contains(message);
    }

    public List<String> getValidationErrors() {
        return Collections.unmodifiableList(validationErrors);
    }

    public boolean hasAllRequiredFieldErrors() {
        return validationErrors.contains(ERROR_DOSEN_PENGUJI_REQUIRED)
                && validationErrors.contains(ERROR_RUANGAN_REQUIRED)
                && validationErrors.contains(ERROR_TANGGAL_REQUIRED);
    }

    private void selectDate(LocalDate date) {
        requireDummyData();
        dummyData.setTanggalSidang(date);
    }

    private void validateRequiredFields() {
        if (isBlank(dummyData.getDosenPenguji())) {
            validationErrors.add(ERROR_DOSEN_PENGUJI_REQUIRED);
        }
        if (isBlank(dummyData.getRuangan())) {
            validationErrors.add(ERROR_RUANGAN_REQUIRED);
        }
        if (dummyData.getTanggalSidang() == null) {
            validationErrors.add(ERROR_TANGGAL_REQUIRED);
        }
    }

    private void validateScheduleConflict() {
        if (validationErrors.isEmpty()
                && AdminPenjadwalanDummyData.CONFLICT_RUANGAN.equals(dummyData.getRuangan())
                && AdminPenjadwalanDummyData.CONFLICT_TANGGAL.equals(dummyData.getTanggalSidang())) {
            validationErrors.add(ERROR_JADWAL_CONFLICT);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private void requireDummyData() {
        if (dummyData == null) {
            throw new IllegalStateException("Data pengajuan dummy belum disiapkan.");
        }
    }
}
