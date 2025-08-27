package com.juaracoding.hadir.definitions.cummonsteps;

import org.testng.Assert;

import com.juaracoding.hadir.pages.LaporanSakitPage;
import com.juaracoding.hadir.pages.ImportCutiPage;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.utils.DriverUtil;

import io.cucumber.java.en.Given;
import com.juaracoding.hadir.pages.DownloadAbsenPage;
import com.juaracoding.hadir.pages.ImportStatusAktifPage;
import com.juaracoding.hadir.pages.LaporanKoreksiPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CummonSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportCutiPage importCutiPage = new ImportCutiPage(DriverUtil.getDriver());
    LaporanSakitPage laporanSakitPage = new LaporanSakitPage(DriverUtil.getDriver());
    ImportStatusAktifPage importPage = new ImportStatusAktifPage(DriverUtil.getDriver());
    LaporanKoreksiPage laporanKoreksiPage = new LaporanKoreksiPage(DriverUtil.getDriver());
    DownloadAbsenPage downloadAbsenPage = new DownloadAbsenPage(DriverUtil.getDriver());

     @Given("User login sebagai admin")
    public void user_login_sebagai_admin() {
        loginPage = new LoginPage(DriverUtil.getDriver());
        loginPage.performLogin();
    }


    @Given("User masuk ke dashboard")
    public void user_masuk_ke_dashboard() {
        Assert.assertTrue(importPage.isDashboardDisplayed(), "Dashboard tidak tampil!");
    }


    @Given("User masuk ke halaman Laporan sakit")
    public void user_masuk_ke_halaman_laporan_sakit() {
        laporanSakitPage.klikSubMenuLaporanSakit();
    }

    //==== untuk semua scenario bisa klik tombol search===
    @When("Di Laporan sakit, User klik tombol Cari")
    public void user_klik_tombol_cari_pada_laporan_sakit() {
        laporanSakitPage.klikCari();
    }

    // ====== Halaman Import cuti =====
    @Given("User masuk ke halaman import cuti")
    public void user_masuk_ke_halaman_import_cuti() {
        importCutiPage.clickMenuImport();
        importCutiPage.clickSubmenuImportCuti();
    }

    // Halaman Import
    @Given("User masuk ke halaman import")
    public void user_masuk_ke_halaman_import() {
        importPage.clickMenuImport();
    }

    @When("User klik button import")
    public void user_klik_button_import() {
        importPage.clickImportButton();
    }

    // Halaman Laporan
    @Given("User masuk ke halaman Laporan")
    public void user_masuk_ke_halaman_laporan() {
        laporanKoreksiPage.klikMenuLaporan();
    }

    @Given("User masuk ke halaman Laporan Koreksi")
    public void user_masuk_ke_halaman_laporan_koreksi() {
        laporanKoreksiPage.klikSubMenuLaporanKoreksi();
    }

    @And("User klik tombol Cari")
    public void user_klik_tombol_cari() {
        laporanKoreksiPage.klikCari();
    }

    @And("User klik tombol Terapkan")
    public void user_klik_tombol_Terapkan() {
        laporanKoreksiPage.klikTerapkan();
    }

    // Download Absen
    @Given("User masuk ke halaman download absen")
    public void user_masuk_ke_halaman_download_absen() {
        downloadAbsenPage.klikMenuLaporan();
        downloadAbsenPage.klikDownloadAbsen();
    }

    @And("User klik button Download")
    public void user_klik_button_download() {
        downloadAbsenPage.clickDownload();
    }

    @Then("Sistem berhasil mengunduh file excel rekap absen")
    public void user_berhasil_mendownload_laporan_absen() {
        Assert.assertTrue(downloadAbsenPage.getSnackbarMessage().contains(DownloadAbsenPage.SUCCESS_MESSAGE_DOWNLOAD),
                "Gagal download laporan absen!");
    }
}
