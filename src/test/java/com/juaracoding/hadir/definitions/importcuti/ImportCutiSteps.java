package com.juaracoding.hadir.definitions.importcuti;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.ImportCutiPage;
import com.juaracoding.hadir.utils.DriverUtil;

public class ImportCutiSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    ImportCutiPage importPage = new ImportCutiPage(DriverUtil.getDriver());

    // ================= ACTION STEP (UMUM UNTUK BUTTON) =================
    @When("User klik button import")
    public void user_klik_button_import() throws InterruptedException {
        importPage.clickImportButton();
        Thread.sleep(3000);
    }

    // ================= POSITIVE SCENARIOS =================

    // Scenario 1: Import file excel valid
    @When("User memilih file excel yang valid")
    public void user_memilih_file_excel_valid() throws InterruptedException {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_cuti_valid.xlsx");
        Thread.sleep(3000);
    }

    @Then("Sistem menampilkan message Berhasil import excel")
    public void sistem_menampilkan_message_berhasil_import_excel() {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, ImportCutiPage.SUCCESS_MESSAGE);
    }

    @Then("Sistem menampilkan jumlah sukses dan gagal import")
    public void sistem_menampilkan_jumlah_sukses_dan_gagal_import() {
        int jumlahSukses = importPage.getJumlahSukses();
        int jumlahGagal = importPage.getJumlahGagal();

        // pastikan nilainya valid (tidak negatif) 
        Assert.assertTrue(jumlahSukses >= 0, "Jumlah sukses harus >= 0");
        Assert.assertTrue(jumlahGagal == 0, "Jumlah gagal harus == 0");
    }

    // Scenario 2: Download template excel

    @When("User klik button download template")
    public void user_klik_button_download_template() {
        importPage.clickDownloadTemplate();
    }

    @Then("Sistem berhasil mengunduh template berupa file excel")
    public void sistem_berhasil_mengunduh_template_excel() {
        boolean isDownloaded = importPage.isTemplateDownloaded("Template_Cuti_Hadir");
        Assert.assertTrue(isDownloaded, "File template excel tidak berhasil diunduh!");
    }

    // ================= NEGATIVE SCENARIOS =================

    // Scenario 3: Import file excel kosong

    @Then("Sistem menampilkan pesan error Please select a file")
    public void sistem_menampilkan_pesan_error_choose_file_wajib_diisi() {
        String validationMessage = importPage.getValidationMessage();
        System.out.println("Pesan validasi: " + validationMessage);

        boolean isValid = validationMessage.equalsIgnoreCase(ImportCutiPage.ERROR_MSG_EN)
                || validationMessage.equalsIgnoreCase(ImportCutiPage.ERROR_MSG_ID);

        Assert.assertTrue(isValid, "Pesan validasi tidak sesuai! Dapat: " + validationMessage);
    }

    // Scenario 4: Import file dengan format pdf

    @When("User memilih file dengan format salah")
    public void user_memilih_file_dengan_format_salah() {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_cuti_invalid.pdf");
            // kl tidak di assert bakal error
            String expectedPdfError = "*File harus berupa file Excel (.xls atau .xlsx)";       
            String actualPdfError = importPage.getErrorMessagePdf();
        Assert.assertEquals(actualPdfError, expectedPdfError);
    }

    @Then("Sistem menampilkan message format file tidak sesuai")
    public void sistem_menampilkan_pesan_error_format_file_tidak_sesuai() {
        String actualMessageBrowser = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessageBrowser, ImportCutiPage.ERROR_MESSAGE);
    }

    // Scenario 5: import file excel dengan sisa cuti habis atau kosong
    @When("User memilih file excel cuti kosong")
    public void user_memilih_file_excel_cuti_kosong() {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_sisa_cuti_kosong.xlsx");
    }

    @Then("Sistem menampilkan pesan Berhasil import excel")
    public void sistem_menampilkan_pesan_berhasil_import_excel() {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, ImportCutiPage.SUCCESS_MESSAGE);
    }

    @Then("Sistem menampilkan logs jumlah sukses dan gagal import")
    public void sistem_menampilkan_logs_jumlah_sukses_dan_gagal_import() {
        int jumlahSukses = importPage.getJumlahSukses();
        int jumlahGagal = importPage.getJumlahGagal();

        // pastikan nilai nya invalid
        Assert.assertTrue(jumlahSukses == 0, "Jumlah sukses harus == 0 krna sisa cuti = 0");
        Assert.assertTrue(jumlahGagal == 0, "Jumlah gagal harus == 0");

    }

    // Scenario 6: import file excel dengan tanggal cuti invalid (tahun cuti dibuat invalid)
    @When("User memilih file excel cuti tahun invalid")
    public void user_memilih_file_excel_cuti_tahun_invalid() {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_cuti_tgl_invalid.xlsx");
    }

    @Then("Sistem menampilkan Berhasil import excel")
    public void sistem_menampilkan_Berhasil_import_excel() {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, ImportCutiPage.SUCCESS_MESSAGE);
    }

    @Then("Sistem menampilkan logs sukses dan gagal import")
    public void sistem_menampilkan_logs_sukses_dan_gagal_import() {
        int jumlahSukses = importPage.getJumlahSukses();
        int jumlahGagal = importPage.getJumlahGagal();

        // pastikan nilainya invalid
        Assert.assertTrue(jumlahSukses == 0, "Jumlah sukses harus == 0 krna tahun cuti invalid");
        Assert.assertTrue(jumlahGagal == 0, "Jumlah gagal harus == 0");

    }

    // Scenario 7: import file excel dengan nik invalid

     @When("User memilih file excel cuti nik invalid")
    public void user_memilih_file_excel_cuti_nik_invalid() {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_cuti_nik_invalid.xlsx");
    }

    @Then("Sistem menampilkan gagal import file excel")
    public void sistem_menampilkan_gagal_import_file_excel() {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, ImportCutiPage.ERROR_MESSAGE);
    }

    // Scenario 8: import file excel dengan format tanggal menjadi "date" dd/mm/yyy

     @When("User memilih file excel cuti format tanggal invalid")
    public void user_memilih_file_excel_cuti_format_tanggal_invalid() {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_cuti_format_tgl_invalid.xlsx");
    }

    @Then("Sistem menampilkan pesan gagal import file excel")
    public void sistem_menampilkan_pesan_gagal_import_file_excel() {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, ImportCutiPage.ERROR_MESSAGE);
    }

    // Scenario 9: Import file excel dengan jenis extension tahun lama "97-2003"

     @When("User memilih file cuti format excel lama")
    public void user_memilih_file_excel_cuti_format_excel_lama() {
        importPage.uploadExcelFile(
            "D:\\juaracoding\\project\\ProjectSQA_TEAM1_HADIR\\src\\test\\resources\\testdata\\data_cuti_format_excel_lama.xls");
            String expectedExcelLama = "*File harus berupa file Excel (.xlsx)";
            String actualMessageGagal = importPage.getErrorMessageExcelLama();
            Assert.assertEquals(actualMessageGagal, expectedExcelLama);
    
    }

    @Then("Sistem menampilkan pesan file harus berupa file excel")
    public void sistem_menampilkan_pesan_file_harus_berupa_file_excel() {
        String actualMessage = importPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, ImportCutiPage.ERROR_MESSAGE);
    }

}
