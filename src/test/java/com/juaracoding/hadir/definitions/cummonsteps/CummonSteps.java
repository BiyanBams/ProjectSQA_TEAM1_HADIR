package com.juaracoding.hadir.definitions.cummonsteps;

import com.juaracoding.hadir.pages.ImportAbsenPage;
import com.juaracoding.hadir.pages.LemburPage;
import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.utils.DriverUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CummonSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportAbsenPage absenPage = new ImportAbsenPage(DriverUtil.getDriver());
    LemburPage lemburPage = new LemburPage(DriverUtil.getDriver());

    // ================= STEP UMUM =================
    @Given("User login sebagai admin")
    public void user_login_sebagai_admin() throws InterruptedException {
        loginPage = new LoginPage(DriverUtil.getDriver());
        loginPage.performLogin();
        Thread.sleep(3000);
    }

    @Given("User masuk ke dashboard")
    public void user_masuk_ke_dashboard() {
        Assert.assertTrue(absenPage.isDashboardDisplayed(), "Dashboard tidak tampil!");
    }

    // ====== Halaman Laporan lembur ====
    @Given("User masuk ke halaman Laporan")
    public void user_masuk_ke_halaman_laporan() {
        lemburPage.klikMenuLaporan();
    }
    @Given("User masuk ke halaman Laporan lembur")
    public void user_masuk_ke_halaman_laporan_lembur() {
        lemburPage.klikSubMenuLaporanLembur();
    }

    //==== untuk semua scenario bisa klik tombol search===
    @When("User klik tombol Cari")
    public void user_klik_tombol_cari() {
        lemburPage.clickCariButton();
    }

    // ====== Halaman Import absen =====
    @Given("User masuk ke halaman import absen")
    public void user_masuk_ke_halaman_import_absen() {
        absenPage.clickMenuImport();
        absenPage.clickSubmenuImportAbsen();
    }
}