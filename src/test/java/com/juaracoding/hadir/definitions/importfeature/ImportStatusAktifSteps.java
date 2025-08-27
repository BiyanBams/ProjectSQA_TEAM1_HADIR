package com.juaracoding.hadir.definitions.importfeature;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.ImportStatusAktifPage;
import com.juaracoding.hadir.utils.DriverUtil;

public class ImportStatusAktifSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportStatusAktifPage importPage = new ImportStatusAktifPage(DriverUtil.getDriver());

    @Given("User masuk ke halaman import status aktif")
    public void user_masuk_ke_halaman_import_status_aktif() {
        importPage.clickSubmenuImportStatusAktif();
    }

    // Scenario 1 : Import Excel Valid
    @When("User memilih file status aktif excel yang valid")
    public void user_memilih_file_status_aktif_excel_valid() {
        importPage.uploadExcelFile(
                "C:\\Users\\Hype\\Documents\\JAVA-VSCODE\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_status_aktif_valid.xlsx");
    }

    @Then("Sistem menampilkan message Berhasil import status aktif excel")
    public void sistem_menampilkan_message_berhasil_import_status_aktif_excel() {
        String actualMessage = importPage.getSnackbarMessage();  // ✅ FIXED
        Assert.assertEquals(actualMessage, ImportStatusAktifPage.SUCCESS_MESSAGE);
    }

    // Scenario 2 : Download Template Excel
    @When("User klik button download status aktif")
    public void user_klik_button_download_status_aktif() {
        importPage.clickDownloadTemplate();
    }

    @Then("Sistem berhasil mengunduh template berupa file status aktif excel")
    public void sistem_berhasil_mengunduh_template_status_aktif_excel() {
        // pakai nama yang sesuai file hasil download
        boolean isDownloaded = importPage.isTemplateDownloaded("DATA_STATUS_USER");
        Assert.assertTrue(isDownloaded, "File template status aktif excel tidak berhasil diunduh!");
    }

    // Scenario 3 : Import Excel Invalid
    @Then("Sistem menampilkan pesan error Please select a file di status aktif")
    public void sistem_menampilkan_pesan_error_choose_file_wajib_diisi_di_status_aktif() {
        String validationMessage = importPage.getValidationMessage();
        System.out.println("Pesan validasi: " + validationMessage);

        boolean isValid = validationMessage.equalsIgnoreCase(ImportStatusAktifPage.ERROR_MSG_EN)
                || validationMessage.equalsIgnoreCase(ImportStatusAktifPage.ERROR_MSG_ID);

        Assert.assertTrue(isValid,
                "Pesan validasi tidak sesuai! Dapat: " + validationMessage);
    }

    // Scenario 4 : Import file dengan format salah
     @When("User memilih file dengan format salah di status aktif")
    public void user_memilih_file_dengan_format_salah_di_status_aktif() {
        importPage.uploadExcelFile(
                "C:\\Users\\Hype\\Documents\\JAVA-VSCODE\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_status_aktif_invalid.pdf");
    }

    @Then("Sistem menampilkan message format file tidak sesuai di status aktif")
    public void sistem_menampilkan_pesan_error_format_file_tidak_sesuai_di_status_aktif() {
        String actualMessage = importPage.getSnackbarMessage(); 
        Assert.assertEquals(actualMessage, ImportStatusAktifPage.ERROR_MESSAGE);
    }
}
