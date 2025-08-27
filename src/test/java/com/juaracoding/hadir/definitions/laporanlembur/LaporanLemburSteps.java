package com.juaracoding.hadir.definitions.laporanlembur;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.LemburPage;
import com.juaracoding.hadir.utils.DriverUtil;

public class LaporanLemburSteps {

    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    LemburPage lemburPage = new LemburPage(DriverUtil.getDriver());

    // ========== STEP INPUT & ACTION ==========
    @When("User memasukkan nama {string} di kolom search")
    public void user_memasukkan_nama_di_kolom_search(String nama) {
        lemburPage.enterSearchName(nama);
    }

    @When("User memilih tanggal dari {string} sampai {string} di bulan {string} tahun {string}")
    public void user_memilih_tanggal(String startDate, String endDate, String bulan, String tahun) {
        lemburPage.pilihRangeTanggal(startDate, endDate, bulan, tahun);
    }

    @When("User klik tombol Reset")
    public void user_klik_tombol_reset() {
        lemburPage.clickResetButton();
    }

    @When("User klik button Export")
    public void user_klik_button_export() {
        lemburPage.clickExportButton();
    }

    @When("User klik tombol Filter by")
    public void user_klik_tombol_filter_by() {
        lemburPage.clickFilterButton();
    }

    @When("User memilih unit {string}")
    public void user_memilih_unit(String unit) {
        lemburPage.selectDepartment(unit);
    }

    @When("User klik Terapkan")
    public void user_klik_terapkan() {
        lemburPage.clickTerapkanButton();
    }

    @When("User klik button edit pada kolom aksi")
    public void user_klik_button_edit_pada_kolom_aksi() {
        // Pastikan ada data yang bisa di-edit terlebih dahulu
        if (lemburPage.isDataDisplayed()) {
            lemburPage.clickEditButton();
        } else {
            Assert.fail("Tidak ada data yang bisa di-edit");
        }
    }

    @When("User klik button ubah data")
    public void user_klik_button_ubah_data() {
        lemburPage.clickUbahDataButton();
    }

    // ========== ASSERTIONS POSITIVE ==========
    @Then("Sistem menampilkan data laporan lembur sesuai yang dicari")
    public void sistem_menampilkan_data_laporan_lembur_sesuai_yang_dicari() {
        Assert.assertTrue(lemburPage.isDataDisplayed(), "Data tidak ditampilkan!");
    }

    @Then("Sistem menghapus data sebelumnya yang dicari oleh user")
    public void sistem_menghapus_data_sebelumnya_yang_dicari_oleh_user() {
        Assert.assertTrue(lemburPage.areSearchFieldsCleared(), "Field search tidak terreset!");

    }

    @Then("Sistem berhasil mengunduh data lembur sesuai tanggal yang dicari berupa file excel")
    public void sistem_berhasil_mengunduh_data_lembur_berupa_file_excel() {
        Assert.assertTrue(lemburPage.isExportSuccessful(), "Export tidak berhasil!");
    }

    @Then("Sistem menampilkan validasi berupa message {string}")
    public void sistem_menampilkan_validasi_berupa_message(String expectedMessage) {
        String actualMessage = lemburPage.getMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Pesan tidak sesuai! Expected: " + expectedMessage + ", Actual: " + actualMessage);
    }

    // ========== ASSERTIONS NEGATIVE ==========
    @Then("Sistem tidak menampilkan data yang dicari atau data kosong")
    public void sistem_tidak_menampilkan_data_yang_dicari_atau_data_kosong() {
        Assert.assertFalse(lemburPage.isDataDisplayed(), "Data masih ditampilkan padahal seharusnya kosong!");
    }

    @Then("Sistem berhasil mengunduh namun dengan data kosong")
    public void sistem_berhasil_mengunduh_namun_dengan_data_kosong() {
        Assert.assertTrue(lemburPage.isExportSuccessful(), "Export tidak berhasil!");
    }

    @Then("Sistem menampilkan pesan {string}")
    public void sistem_menampilkan_pesan(String expectedMessage) {
        String actualMessage = lemburPage.getValidationMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Pesan tidak sesuai! Expected: " + expectedMessage + ", Actual: " + actualMessage);
    }
}