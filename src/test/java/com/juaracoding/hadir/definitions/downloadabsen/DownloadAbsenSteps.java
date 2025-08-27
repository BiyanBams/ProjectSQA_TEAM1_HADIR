package com.juaracoding.hadir.definitions.downloadabsen;

import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.DownloadAbsenPage;
import com.juaracoding.hadir.utils.DriverUtil;


import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DownloadAbsenSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    DownloadAbsenPage downloadAbsenPage = new DownloadAbsenPage(DriverUtil.getDriver());

    // Scenario 1 : Download absen berdasarkan input lengkap
    @And ("User memilih Start Date dan End Date valid1")
    public void user_memilih_start_date_dan_end_date_valid1() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "August", "2025");
    }
    @When ("User memilih Nama User valid1")
    public void user_memilih_nama_user_valid1() {
        downloadAbsenPage.isiNama("Izzah Luthfiah");
    }
    @When ("User memilih NIK User valid1")
    public void user_memilih_nik_user_valid1() {
        downloadAbsenPage.isiNIK("D7240014");
    }

    @When ("User memilih Upliner valid1")
    public void user_memilih_upliner_valid1() {
        downloadAbsenPage.isiUpliner("Izzah Luthfiah");
    }

    @When ("User memilih Divisi valid1")
    public void user_memilih_divisi_valid1() {
        downloadAbsenPage.isiDivision("!Finance");
    }
    @When ("User memilih Unit valid1")
    public void user_memilih_unit_valid1() {
        downloadAbsenPage.isiUnit("123456");
    }
    @When ("User memilih Tipe Report valid1")
    public void user_memilih_tipe_report_valid1() {
        downloadAbsenPage.isiReportType("Rekap Absen 1");
    }

    // Scenario 2 : Download absen bedasarkan tanggal
    @And ("User memilih Start Date dan End Date valid2")
    public void user_memilih_start_date_dan_end_date_valid2() {
        downloadAbsenPage.isiReportType("");
        downloadAbsenPage.pilihRangeTanggal("01", "31", "July", "2025");
    }

    // Scenario 3 : Download absen berdasarkan tanggal dan tipe report valid
    @And ("User memilih Start Date dan End Date valid3")
    public void user_memilih_start_date_dan_end_date_valid3() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "August", "2025");
    }

    @When ("User memilih Tipe Report valid3")
    public void user_memilih_tipe_report_valid3() {
        downloadAbsenPage.isiReportType("Rekap Absen 2");
    }

    // Scenario 4 : Download absen berdasarkan tanggal, nama, dan tipe report
    @And ("User memilih Start Date dan End Date valid4")
    public void user_memilih_start_date_dan_end_date_valid4() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "July", "2025");
    }

    @When ("User memilih Nama User valid4")
    public void user_memilih_nama_user_valid4() {
        downloadAbsenPage.isiNama("Izzah Luthfiah");
    }

    @When ("User memilih Tipe Report valid4")
    public void user_memilih_tipe_report_valid4() {
        downloadAbsenPage.isiReportType("Rekap Absen 2");
    }

    // Scenario 5 : Download absen berdasarkan tanggal, NIK, dan tipe
    @And ("User memilih Start Date dan End Date valid5")
    public void user_memilih_start_date_dan_end_date_valid5() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "August", "2025");
    }

    @When ("User memilih NIK User valid5")
    public void user_memilih_nik_user_valid5() {
        downloadAbsenPage.isiNIK("D7240014");
    }

    @When ("User memilih Tipe Report valid5")
    public void user_memilih_tipe_report_valid5() {
        downloadAbsenPage.isiReportType("Rekap Absen 2");
    }

    // Scenario 6 : Download absen berdasarkan tanggal, nama upliner, dan tipe report valid
    @And ("User memilih Start Date dan End Date valid6")
    public void user_memilih_start_date_dan_end_date_valid6() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "August", "2025");
    }

    @When ("User memilih Nama Upliner valid6")
    public void user_memilih_nama_upliner_valid6() {
        downloadAbsenPage.isiUpliner("Izzah Luthfiah");
    }

    @When ("User memilih Tipe Report valid6")
    public void user_memilih_tipe_report_valid6() {
        downloadAbsenPage.isiReportType("Rekap Absen 2");
    }

    // Scenario 7 : Download absen berdasarkan tanggal, divisi, unit, dan tipe report valid
    @And ("User memilih Start Date dan End Date valid7")
    public void user_memilih_start_date_dan_end_date_valid7() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "August", "2025");
    }

    @When ("User memilih Divisi valid7")
    public void user_memilih_divisi_valid7() {
        downloadAbsenPage.isiDivision("!Finance");
    }

    @When ("User memilih Unit valid7")
    public void user_memilih_unit_valid7() {
        downloadAbsenPage.isiUnit("123456");
    }

    @When ("User memilih Tipe Report valid7")
    public void user_memilih_tipe_report_valid7() {
        downloadAbsenPage.isiReportType("Rekap Absen 2");
    }

    // Scenario 8 : Download absen berdasarkan Download absen berdasarkan input lengkap invalid
    @And ("User memilih Start Date dan End Date invalid1")
    public void user_memilih_start_date_dan_end_date_invalid1() {
        downloadAbsenPage.pilihRangeTanggal("01", "31", "July", "2025");
    }

    
    @When ("User memilih Nama User invalid1")
    public void user_memilih_nama_user_invalid1() {
        downloadAbsenPage.isiNama("yanto");
    }
    @When ("User memilih NIK invalid1")
    public void user_memilih_nik_invalid1() {
        downloadAbsenPage.isiNIK("D03499222");
    }

    @When ("User memilih Upliner invalid1")
    public void user_memilih_upliner_invalid1() {
        downloadAbsenPage.isiUpliner("yanto");
    }

    @When ("User memilih Divisi invalid1")
    public void user_memilih_divisi_invalid1() {
        downloadAbsenPage.isiDivision("Bank BCA");
    }

    @When ("User memilih Unit invalid1")
    public void user_memilih_unit_invalid1() {
        downloadAbsenPage.isiUnit("66644555");
    }

    @When ("User memilih Tipe Report invalid1")
    public void user_memilih_tipe_report_invalid1() {
        downloadAbsenPage.isiReportType("Rekap Absen 10");
    }

}