package com.juaracoding.hadir.definitions.importabsen;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.ImportAbsenPage;
import com.juaracoding.hadir.utils.DriverUtil;

public class ImportAbsenHadirSteps {

    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportAbsenPage importPage = new ImportAbsenPage(DriverUtil.getDriver());

    // ========== POSITIF 1 : Import file excel valid ==========
    @When("User klik button choose file dan pilih file excel valid")
    public void user_pilih_file_excel_valid() {
        importPage.uploadExcelFile(
                "C:\\BOOTCAMP\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\import_valid.xlsx"
        );
    }

    @When("User klik button import")
    public void user_klik_button_import() {
        importPage.clickImportButton();
    }

    @Then("Sistem menampilkan message {string}")
    public void sistem_menampilkan_message(String expectedMessage) {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Pesan tidak sesuai! Dapat: " + actualMessage);
    }

    // ========== POSITIF 2 : Download template ==========
    @When("User klik button download template")
    public void user_klik_button_download_template() {
        importPage.clickDownloadTemplate();
    }

    @Then("Sistem berhasil mengunduh template berupa file excel")
    public void sistem_berhasil_mengunduh_template_excel() {
        boolean isDownloaded = importPage.isTemplateDownloaded("DATA_ABSEN_HADIR");
        Assert.assertTrue(isDownloaded, "Template excel tidak berhasil diunduh!");
    }

    // ========== NEGATIF 1 : Import file dengan ekstensi salah ==========
    @When("User pilih file dengan ekstensi salah")
    public void user_pilih_file_dengan_format_salah() {
        importPage.uploadExcelFile(
                "C:\\BOOTCAMP\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\import_invalid.pdf"
        );
    }

    @When("User klik button import format salah")
    public void user_klik_button_import_format_salah() {
        importPage.clickImportButton();
    }

    @Then("Sistem menampilkan error message {string}")
    public void sistem_menampilkan_error_message(String expectedError) {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, expectedError,
                "Pesan error tidak sesuai! Dapat: " + actualMessage);
    }

    // ========== NEGATIF 2 : Import tanpa memilih file ==========
    @When("User klik button import tanpa memilih file")
    public void user_klik_button_import_tanpa_memilih_file() {
        importPage.clickImportButton();
    }

    @Then("Sistem menampilkan pop up message {string}")
    public void sistem_menampilkan_pop_up_message(String expectedPopup) {
        String actualPopup = importPage.getValidationMessage();
        Assert.assertEquals(actualPopup, expectedPopup,
                "Pesan pop up tidak sesuai! Dapat: " + actualPopup);
    }
}
