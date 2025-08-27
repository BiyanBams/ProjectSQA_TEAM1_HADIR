package com.juaracoding.hadir.definitions.laporansakit;

import org.testng.Assert;

import com.juaracoding.hadir.pages.LaporanSakitPage;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.utils.DriverUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LaporanSakitSteps {
    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    LaporanSakitPage laporanSakitPage = new LaporanSakitPage(DriverUtil.getDriver());

    // ====================== POSITIVE ===================
    // ==== Scenario 1 Pencarian berdasarkan nama & tanggal yang valid

    @When("User memasukkan nama di kolom search TLS1")
    public void User_memasukkan_nama_di_kolom_search_TLS1() {
        laporanSakitPage.isiNama("Hadir SQA Testing 1");;
    }

    @When("User pilih tanggal mulai dan tanggal akhir TLS1")
    public void user_pilih_tanggal_bulan_penuh_TLS1() {
        laporanSakitPage.pilihRangeTanggal("01", "31", "August", "2025");;
    }

    @Then("Sistem menampilkan data laporan sakit sesuai yang dicari")
    public void sistem_menampilkan_data_dicari_TLS1() {
        Assert.assertTrue(laporanSakitPage.isDataLaporanSakitTampilName("Hadir SQA Testing 1"), 
        "Data laporan sakit tidak tampil!");
    }

    // ===== Scenario 2: Pencarian hanya berdasarkan nama yang valid

    @When("User memasukkan nama di kolom search TLS2")
    public void User_memasukkan_nama_di_kolom_search_TLS2() {
        laporanSakitPage.isiNama("Hadir SQA Testing 1");
    }

    @Then("Sistem menampilkan semua data laporan sakit berdasarkan nama yang dicari")
    public void sistem_menampilkan_data_berdasarkan_nama_TLS2() {
        Assert.assertTrue(laporanSakitPage.isDataLaporanSakitTampilName("Hadir SQA Testing 1"), 
        "Data laporan sakit tidak tampil!");
    }

    // ==== Scenario 3: Pencarian berdasarkan input tanggal valid
   
    @When("User pilih tanggal mulai dan tanggal akhir TLS3")
    public void user_memilih_tanggal_bulan_penuh_TLS3() {
       laporanSakitPage.pilihRangeTanggal("01", "31", "August", "2025");;
    }

    @Then("Sistem menampilkan semua data laporan sakit berdasarkan tanggal yang dicari")
    public void sistem_menampilkan_data_berdasarkan_tanggal() {
        Assert.assertTrue(laporanSakitPage.isDataLaporanSakitTampilTanggal("22 Agt 2025"), 
        "Data laporan sakit tidak tampil!");
    }

    // Scenario 4: Reset pencarian

    @When("User memasukkan nama di kolom search TLS4")
    public void User_memasukkan_nama_di_kolom_search_TLS4() {
        laporanSakitPage.isiNama("Hadir SQA Testing 1");
    }

    @When("User pilih tanggal mulai dan tanggal akhir TLS4")
    public void user_pilih_tanggal_awal_akhir_bulan_penuh_TLS4() {
        laporanSakitPage.pilihRangeTanggal("24", "31", "August", "2025");
    }

     @Then("Sistem menampilkan semua data laporan sakit berdasarkan tanggal yang dicari TLS4")
    public void sistem_menampilkan_semua_data_laporan_sakit_berdasarkan_tanggal_yang_dicari_TLS4() {
        Assert.assertTrue(
                laporanSakitPage.isDataLaporanSakitTampilTanggal("22 Agt 2025"),
                "Data laporan sakit tidak tampil!");
    }

    @When("User klik tombol Reset")
    public void user_klik_tombol_reset() {
        laporanSakitPage.klikReset();
    }

     @Then("Sistem menghapus nama dan tanggal yang dicari oleh user")
    public void sistem_menhapus_nama_dan_tanggal_yang_dicari() {
        Assert.assertTrue(
                laporanSakitPage.isDataLaporanSakitTampilName("Hadir SQA Testing 1"),
                "Data laporan sakit sebelum dicari seharusnya tampil!");
    }

    // Scenario 5: Filter laporan sakit berdasarkan departemen
    
    @When("User klik tombol Filter by")
    public void user_klik_filter_by() {
        laporanSakitPage.klikFilterBy();
    }

    @When("User ketik dan pilih unit {string} yang ingin dicari")
    public void user_pilih_unit(String unit) {
        laporanSakitPage.filterByDepartment(unit);
    }

    @When("User klik tombol Terapkan")
    public void user_klik_terapkan() {
        laporanSakitPage.klikTerapkan();
    }

    @Then("Sistem menampilkan data sesuai departemen yang dicari")
    public void sistem_menampilkan_data_departemen() {
        Assert.assertTrue(laporanSakitPage.isDataTampilSetelahFilter(),
        "Data harus tampil setelah filter");
    }   

    // Scenario 6: View foto
    
    @When("User memasukkan nama di kolom search TLS6")
    public void User_memasukkan_nama_di_kolom_search_TLS6() {
        laporanSakitPage.isiNama("Zuhdi");
    }

    @When("User klik tombol view")
    public void user_klik_view() {
        laporanSakitPage.klikView();
    }

    @Then("Sistem menampilkan foto dengan jelas sesuai data yang dipilih")
    public void sistem_menampilkan_foto() {
        Assert.assertTrue(laporanSakitPage.isFotoDisplayed(laporanSakitPage.getFotoPreviewLocatorView()), "Foto should be displayed");
    }

    // Scenario 7: Download foto
    
     @When("User memasukkan nama di kolom search TLS7")
    public void User_memasukkan_nama_di_kolom_search_TLS7() {
        laporanSakitPage.isiNama("Zuhdi");
    }

    @When("User klik tombol download")
    public void user_klik_download() {
        laporanSakitPage.klikDownload();
    }

    @Then("Sistem menampilkan foto untuk diunduh")
    public void sistem_tampilkan_foto_untuk_diunduh() {
        Assert.assertTrue(laporanSakitPage.isFotoTampil(), "Foto harus ditampilkan di tab baru");
    }

    // ========================= NEGATIVE ================================

    // Scenario 1: search by name invalid
    
    @When("User memasukkan nama di kolom search TLS8")
    public void User_memasukkan_nama_di_kolom_search_TLS8() {
        laporanSakitPage.isiNama("agus");
    }

    @Then("Sistem tidak menampilkan data yang dicari atau data kosong TLS8")
    public void sistem_menampilkan_data_yang_dicari_kosong() {
        Assert.assertTrue(laporanSakitPage.isDataLaporanSakitKosong("agus"), 
        "Data laporan sakit tampil!");
    }

    // Scenario 2: search by nama valid & tgl invalid

    @When("User memasukkan nama di kolom search TLS9")
    public void User_memasukkan_nama_di_kolom_search_TLS9() {
        laporanSakitPage.isiNama("Hadir SQA Testing 1");
    }

    @When("User pilih tanggal invalid TLS9")
    public void user_pilih_tanggal_invalid_TLS9() {
        laporanSakitPage.pilihRangeTanggal("03", "31", "July", "2025");
    }

    @Then("Sistem tidak menampilkan data yang dicari atau data kosong TLS9")
    public void sistem_tidak_menampilkan_data_yang_dicari_atau_data_kosong_TLS9() {
        boolean result = laporanSakitPage.isDataLaporanSakitKosong("Hadir SQA Testing 1");
        System.out.println("DEBUG Hasil: " + result);
        Assert.assertTrue(result, "Data laporan sakit tampil!");
    }

    // scenario 3: search by format tgl invalid

    @When("User pilih tanggal invalid TLS10")
    public void user_pilih_tanggal_invalid_TLS10() {
        laporanSakitPage.pilihRangeTanggal("10", "10", "July", "2025");
    }

    @Then("Sistem tidak menampilkan data yang dicari atau data kosong TLS10")
    public void sistem_tidak_menampilkan_data_yang_dicari_atau_data_kosong_TLS10() {
        boolean result = laporanSakitPage.isDataLaporanSakitKosongDenganTanggal("10 Jul 2025");
        System.out.println("DEBUG Hasil: " + result);
        Assert.assertTrue(result, "Data laporan sakit tampil!");
    }

    // scenario 4: search by departement yang tidak ada

    @When("User klik tombol Filter by departemen invalid")
    public void user_klik_filter_by_departemen_invalid() {
        laporanSakitPage.klikFilterBy();
    }

    @When("User ketik dan pilih unit {string} yang ingin dicari invalid")
    public void user_pilih_unit_invalid(String unit) {
        laporanSakitPage.filterByDepartment(unit);
    }

    @When("User klik tombol Terapkan departemen invalid")
    public void user_klik_terapkan_departemen_invalid() {
        laporanSakitPage.klikTerapkan();
    }

    @Then("Sistem tidak menampilkan data sesuai departemen yang dicari atau data kosong")
    public void sistem_tidak_menampilkan_data_departemen_yang_dicari() {
        Assert.assertTrue(laporanSakitPage.isDataKosongSetelahFilter(),
        "Data seharusnya tidak tampil karena departemen tidak ada");
    }   

    // Scenario 5: view foto tidak terlampir

    @When("User memasukkan nama di kolom search TLS12")
    public void User_memasukkan_nama_di_kolom_search_TLS12() {
        laporanSakitPage.isiNama("Alif Irfan Zuhdi");
    }

    @Then("Sistem menampilkan foto yang tidak terlampir")
    public void sistem_menampilkan_foto_tidak_terlampir() {
        Assert.assertTrue(laporanSakitPage.isFotoDisplayed(laporanSakitPage.getFotoviewLocator()), 
        "Foto seharusnya yang tidak terlampir");
    }

    // Scenario 6: download foto tidak terlampir

     @When("User memasukkan nama di kolom search TLS13")
    public void User_memasukkan_nama_di_kolom_search_TLS13() {
        laporanSakitPage.isiNama("Alif Irfan Zuhdi");
    }

    @When("User klik tombol download foto tidak terlampir")
    public void user_download_foto_tidak_ada() {
        laporanSakitPage.klikDownload();
    }

    @Then("Sistem tidak dapat mengunduh dan menampilkan pesan error 404")
    public void sistem_menampilkan_error() {
        Assert.assertTrue(laporanSakitPage.isError404Tampil(), "Error 404 harus ditampilkan di tab baru");
    }


}
