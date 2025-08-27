package com.juaracoding.hadir.definitions.importfeature;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.ImportStrukturUserPage;
import com.juaracoding.hadir.utils.DriverUtil;

public class ImportStrukturUserSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportStrukturUserPage importPage = new ImportStrukturUserPage(DriverUtil.getDriver());

    @Given("User masuk ke halaman Struktur User")
    public void user_masuk_ke_halaman_import_struktur_user() {
        importPage.clickSubmenuImportStrukturUser();
    }

    // Scenario 1 : Import Excel Valid
    @When("User memilih file struktur user excel yang valid")
    public void user_memilih_file_struktur_user_excel_valid() {
        importPage.uploadExcelFile(
                "C:\\Users\\Hype\\Documents\\JAVA-VSCODE\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_status_aktif_valid.xlsx");
    }

    @Then("Sistem menampilkan message Berhasil import struktur user excel")
    public void sistem_menampilkan_message_berhasil_import_struktur_user_excel() {
        String actualMessage = importPage.getSnackbarMessage(); 
        Assert.assertEquals(actualMessage, ImportStrukturUserPage.SUCCESS_MESSAGE);
    }

    // Scenario 2 : Download Template Excel
    @When("User klik button download struktur user")
    public void user_klik_button_download_struktur_user() {
        importPage.clickDownloadTemplate();
    }

    @Then("Sistem berhasil mengunduh template berupa file struktur user excel")
    public void sistem_berhasil_mengunduh_template_struktur_user_excel() {
        // pakai nama yang sesuai file hasil download
        boolean isDownloaded = importPage.isTemplateDownloaded("DATA_STRUKTUR_USER");
        Assert.assertTrue(isDownloaded, "File template struktur user excel tidak berhasil diunduh!");
    }

    // Scenario 3 : Import Struktur User Invalid
    @Then("Sistem menampilkan pesan error Please select a file di struktur user")
    public void sistem_menampilkan_pesan_error_choose_file_wajib_diisi_di_struktur_user() {
        String validationMessage = importPage.getValidationMessage();
        System.out.println("Pesan validasi: " + validationMessage);

        boolean isValid = validationMessage.equalsIgnoreCase(ImportStrukturUserPage.ERROR_MSG_EN)
                || validationMessage.equalsIgnoreCase(ImportStrukturUserPage.ERROR_MSG_ID);

        Assert.assertTrue(isValid,
                "Pesan validasi tidak sesuai! Dapat: " + validationMessage);
    }

    // Scenario 4 : Import file dengan format salah
    @When("User memilih file dengan format salah di struktur user")
    public void user_memilih_file_dengan_format_salah_di_struktur_user() {
        importPage.uploadExcelFile(
                "C:\\Users\\Hype\\Documents\\JAVA-VSCODE\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_status_aktif_invalid.pdf");
    }

    @Then("Sistem menampilkan message format file tidak sesuai di struktur user")
    public void sistem_menampilkan_pesan_error_format_file_tidak_sesuai_di_struktur_user() {
        String actualMessage = importPage.getSnackbarMessage(); 
        Assert.assertEquals(actualMessage, ImportStrukturUserPage.ERROR_MESSAGE);
    }
}
