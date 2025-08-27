package com.juaracoding.hadir.definitions.cummonsteps;

import org.testng.Assert;

import com.juaracoding.hadir.pages.LaporanSakitPage;
import com.juaracoding.hadir.pages.ImportCutiPage;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.utils.DriverUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CummonSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportCutiPage importPage = new ImportCutiPage(DriverUtil.getDriver());
    LaporanSakitPage laporanSakitPage = new LaporanSakitPage(DriverUtil.getDriver());

    // ================= STEP UMUM =================
    @Given("User login sebagai admin")
    public void user_login_sebagai_admin() throws InterruptedException {
        loginPage = new LoginPage(DriverUtil.getDriver());
        loginPage.performLogin();
        Thread.sleep(3000);
    }

    @Given("User masuk ke dashboard")
    public void user_masuk_ke_dashboard() {
        Assert.assertTrue(importPage.isDashboardDisplayed(), "Dashboard tidak tampil!");
    }

    // ====== Halaman Laporan sakit ====
    @Given("User masuk ke halaman Laporan")
    public void user_masuk_ke_halaman_laporan() {
        laporanSakitPage.klikMenuLaporan();
    }
    @Given("User masuk ke halaman Laporan sakit")
    public void user_masuk_ke_halaman_laporan_sakit() {
        laporanSakitPage.klikSubMenuLaporanSakit();
    }

    //==== untuk semua scenario bisa klik tombol search===
    @When("User klik tombol Cari")
    public void user_klik_tombol_cari() {
        laporanSakitPage.klikCari();
    }

    // ====== Halaman Import cuti =====
    @Given("User masuk ke halaman import cuti")
    public void user_masuk_ke_halaman_import_cuti() {
        importPage.clickMenuImport();
        importPage.clickSubmenuImportCuti();
    }
}
