package org.example.pages.admin;

import java.time.LocalDate;

public class AdminPenjadwalanDummyData {
    public static final String STATUS_TERVERIFIKASI = "Terverifikasi";
    public static final String STATUS_SIAP_DIJADWALKAN = "Siap Dijadwalkan";
    public static final String STATUS_DIJADWALKAN = "Dijadwalkan";

    public static final String DEFAULT_MAHASISWA = "Dimas Satriaa";
    public static final String DEFAULT_JUDUL = "Sistem Informasi Penjadwalan Sidang";
    public static final String DEFAULT_DOSEN_PENGUJI = "Dr. Budi Santoso";
    public static final String DEFAULT_RUANGAN = "Ruang Sidang 1";

    public static final String CONFLICT_DOSEN_PENGUJI = "Dr. Budi Santoso";
    public static final String CONFLICT_RUANGAN = "Ruang Sidang Bentrok";
    public static final LocalDate CONFLICT_TANGGAL = LocalDate.of(2026, 6, 20);

    public static final LocalDate DEFAULT_TANGGAL = LocalDate.of(2026, 6, 15);
    public static final LocalDate MIN_TANGGAL = LocalDate.of(2026, 6, 10);
    public static final LocalDate MAX_TANGGAL = LocalDate.of(2026, 12, 31);

    private String mahasiswa;
    private String judul;
    private String status;
    private String dosenPenguji;
    private String ruangan;
    private LocalDate tanggalSidang;

    public static AdminPenjadwalanDummyData verifiedSubmission() {
        AdminPenjadwalanDummyData data = new AdminPenjadwalanDummyData();
        data.mahasiswa = DEFAULT_MAHASISWA;
        data.judul = DEFAULT_JUDUL;
        data.status = STATUS_SIAP_DIJADWALKAN;
        return data;
    }

    public String getMahasiswa() {
        return mahasiswa;
    }

    public String getJudul() {
        return judul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDosenPenguji() {
        return dosenPenguji;
    }

    public void setDosenPenguji(String dosenPenguji) {
        this.dosenPenguji = dosenPenguji;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public LocalDate getTanggalSidang() {
        return tanggalSidang;
    }

    public void setTanggalSidang(LocalDate tanggalSidang) {
        this.tanggalSidang = tanggalSidang;
    }
}
