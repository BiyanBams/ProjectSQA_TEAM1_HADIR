package com.juaracoding.hadir.definitions.laporanfeature;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.juaracoding.hadir.pages.LoginPage;
import com.juaracoding.hadir.pages.LaporanKoreksiPage;
import com.juaracoding.hadir.utils.DriverUtil;

public class LaporanKoreksiSteps {

    LoginPage loginPage = new LoginPage(DriverUtil.getDriver());
    LaporanKoreksiPage laporanKoreksiPage = new LaporanKoreksiPage(DriverUtil.getDriver());

    // === Scenario 1 : User mencari laporan koreksi berdasarkan nama & tanggal ada

    @When("User memasukkan nama di kolom search valid1")
    public void user_memasukkan_nama_di_kolom_search_valid1() {
        laporanKoreksiPage.isiNama("Hadir SQA Testing 1");
    }

    @And("User pilih tanggal mulai dan tanggal akhir untuk 1 bulan penuh")
    public void user_pilih_tanggal_mulai_dan_tanggal_akhir_untuk_1_bulan_penuh() {
        laporanKoreksiPage.pilihRangeTanggal("01", "31", "August", "2025");
    }

    @Then("Sistem menampilkan data laporan koreksi sesuai yang dicari valid1")
    public void sistem_menampilkan_data_laporan_koreksi_sesuai_yang_dicari_valid1() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilName("Hadir SQA Testing 1"),
                "Data laporan koreksi tidak tampil!");
    }

    // === Scenario 2 : User mencari laporan koreksi berdasarkan nama yang
    // valid/terdaftar ===
    @When("User memasukkan nama di kolom search valid2")
    public void user_memasukkan_nama_di_kolom_search_valid2() {
        laporanKoreksiPage.isiNama("Hadir SQA Testing 1");
    }

    @Then("Sistem menampilkan semua data laporan koreksi berdasarkan nama yang dicari valid2")
    public void sistem_menampilkan_semua_data_laporan_koreksi_berdasarkan_nama_yang_dicari_valid2() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilName("Hadir SQA Testing 1"),
                "Data laporan koreksi tidak tampil!");
    }

    // === Scenario 3 : User melakukan reset pada pencarian laporan koreksi Dengan
    // Tanggal valid===

    @And("User pilih tanggal mulai dan tanggal akhir valid3")
    public void user_pilih_tanggal_mulai_dan_tanggal_akhir_valid3() {
        laporanKoreksiPage.pilihRangeTanggal("01", "30", "June", "2025");
    }

    @Then("Sistem menampilkan semua data laporan koreksi berdasarkan tanggal yang dicari valid3")
    public void sistem_menampilkan_semua_data_laporan_koreksi_berdasarkan_tanggal_yang_dicari_valid3() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilTanggal("28 Jun 2025"),
                "Data laporan koreksi tidak tampil!");
    }

    @And("User klik tombol Reset")
    public void user_klik_tombol_Reset() {
        laporanKoreksiPage.klikReset();
    }

    @Then("Sistem menampilkan semua data laporan koreksi berdasarkan nama yang dicari valid3")
    public void sistem_menampilkan_semua_data_laporan_koreksi_berdasarkan_nama_yang_dicari_valid3() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilName("Hadir SQA Testing 1"),
                "Data laporan koreksi tidak tampil!");
    }

    // Scenario 4 : User menyetujui pengajuan koreksi absen
    @When("User memasukkan nama di kolom search valid4")
    public void user_memasukkan_nama_di_kolom_search_valid4() {
        laporanKoreksiPage.isiNama("Hadir SQA Testing 1");
    }

    @And("User pilih tanggal mulai dan tanggal akhir valid4")
    public void user_pilih_tanggal_mulai_dan_tanggal_akhir_valid4() {
        laporanKoreksiPage.pilihRangeTanggal("01", "31", "July", "2025");
    }

    @Then("Sistem menampilkan data laporan koreksi sesuai yang dicari valid4")
    public void sistem_menampilkan_data_laporan_koreksi_sesuai_yang_dicari_valid4() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilName("Hadir SQA Testing 1"),
                "Data laporan koreksi tidak tampil!");
    }

    @And("User klik tombol Approval Koreksi")
    public void user_klik_tombol_approval_koreksi() {
        laporanKoreksiPage.cekStatusPendingAda();
        laporanKoreksiPage.klikApproveDenganPaging();
        laporanKoreksiPage.klikKonfirmasiSetujui();
    }

    @Then("Sistem menampilkan validasi pesan berhasil menyetujui koreksi absen dan field status berubah menjadi Approved")
    public void sistem_menampilkan_validasi_pesan_berhasil_menyetujui_koreksi_absen_dan_field_status_berubah_menjadi_Approved() {
        String actualMessage = laporanKoreksiPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, LaporanKoreksiPage.SUCCESS_MESSAGE_APPROVE);
    }

    // Scenario 5 : User mencari laporan koreksi berdasarkan filter untuk departemen yang ada
    @When("User klik tombol Filter by Departemen valid5")
    public void user_klik_tombol_Filter_by_Departemen_valid5() {
         laporanKoreksiPage.klikFilterBy(); 
    }

    @And("User pilih atau ketik unit yang ingin dicari valid5")
    public void user_pilih_ketik_unit_yang_ingin_dicari_valid5() {
        laporanKoreksiPage.filterByDepartment("EDC Nasional");
    }

    @Then("Sistem menampilkan data sesuai departemen yang dicari valid5")
    public void sistem_menampilkan_data_sesuai_departemen_yang_dicari_valid5() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilName("Kelompok 5"),
                "Data laporan koreksi tidak tampil!");
    }

    // Scenario 6 : User menolak pengajuan koreksi absen
    @When("User memasukkan nama di kolom search valid6")
    public void user_memasukkan_nama_di_kolom_search_valid6() {
        laporanKoreksiPage.isiNama("Hadir SQA Testing 1");
    }

    @And("User pilih tanggal mulai dan tanggal akhir valid6")
    public void user_pilih_tanggal_mulai_dan_tanggal_akhir_valid6() {
        laporanKoreksiPage.pilihRangeTanggal("01", "31", "July", "2025");
    }

    @Then("Sistem menampilkan data laporan koreksi sesuai yang dicari valid6")
    public void sistem_menampilkan_data_laporan_koreksi_sesuai_yang_dicari_TKL5() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiTampilName("Hadir SQA Testing 1"),
                "Data laporan koreksi tidak tampil!");
    }

    @And("User klik tombol Reject Koreksi")
    public void user_klik_tombol_reject_koreksi() {
        laporanKoreksiPage.cekStatusPendingAda();
        laporanKoreksiPage.klikRejectDenganPaging();
    }

    @And("User mengisi alasan penolakan")
    public void user_mengisi_alasan_penolakan() {
        laporanKoreksiPage.isiAlasan("data palsu");
    }

    @And("User klik tombol Tolak")
    public void user_klik_tombol_tolak() {
        laporanKoreksiPage.klikTolak();
    }

    @Then("Sistem menampilkan validasi pesan berhasil menolak permintaan koreksi absen dan field status berubah menjadi Rejected")
    public void sistem_menampilkan_validasi_pesan_berhasil_menolak_permohonan_koreksi_absen_dan_field_status_berubah_menjadi_Rejected() {
        String actualMessage = laporanKoreksiPage.getSnackbarMessage();
        Assert.assertEquals(actualMessage, LaporanKoreksiPage.SUCCES_MESSAGE_REJECT);
    }

    // Scenario 7 : User mencari laporan koreksi berdasarkan sebagian nama yang
    // invalid
    @When("User memasukkan nama yang invalid1 pada kolom search")
    public void user_memasukkan_nama_yang_invalid1_pada_kolom_search() {
        laporanKoreksiPage.isiNama("yanto");
    }

    @Then("Sistem tidak menampilkan data yang dicari data kosong invalid1")
    public void sistem_tidak_menampilkan_data_yang_dicari_data_kosong_invalid1() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiKosong("yanto"),
                "Data laporan koreksi tampil!");
    }

    // Scenario 8 : User mencari laporan koreksi berdasarkan nama valid & tanggal invalid
    @When("User memasukkan nama yang valid1 pada kolom search")
    public void user_memasukkan_nama_yang_valid1_pada_kolom_search() {
        laporanKoreksiPage.isiNama("Hadir SQA Testing 1");
    }

    @And("User pilih tanggal invalid2")
    public void user_pilih_tanggal_invalid2() {
        laporanKoreksiPage.pilihRangeTanggal("01", "30", "June", "2020");
    }

    @Then("Sistem tidak menampilkan data yang dicari invalid2")
    public void sistem_tidak_menampilkan_data_yang_dicari_invalid2() {
        boolean result = laporanKoreksiPage.isDataLaporanKoreksiKosong("Hadir SQA Testing 1");
        System.out.println("DEBUG Hasil: " + result);
        Assert.assertTrue(result, "Data laporan koreksi tampil!");
    }


    // Scenario 9 : User input tanggal dengan format tidak valid
    @When("User pilih tanggal mulai dan akhir invalid3")
    public void user_input_tanggal_dengan_format_tidak_valid3() {
        laporanKoreksiPage.pilihRangeTanggal("01", "31", "July", "2020");
    }

    @Then("Sistem tidak menampilkan data yang dicari invalid3")
    public void sistem_tidak_menampilkan_data_yang_dicari_invalid3() {
        boolean result = laporanKoreksiPage.isDataLaporanKoreksiKosongDenganTanggal("28 Jun 2020");
        System.out.println("DEBUG Hasil: " + result);
        Assert.assertTrue(result, "Data laporan koreksi tampil!");
    }

    // Scenario 10 : User tidak menyetujui pengajuan koreksi absen tanpa memberi alasan penolakan
    @When("User memasukkan nama di kolom search invalid4")
    public void user_memasukkan_nama_di_kolom_search_invalid4() {
        laporanKoreksiPage.isiNama("Hadir SQA Testing 1");
    }

    @And("User pilih tanggal mulai dan akhir invalid4")
    public void user_pilih_tanggal_mulai_dan_tanggal_akhir_invalid4() {
        laporanKoreksiPage.pilihRangeTanggal("01", "31", "July", "2025");
    }

    @And("User klik tombol reject koreksi invalid4")
    public void user_klik_tombol_reject_koreksi_invalid4() {
        laporanKoreksiPage.cekStatusPendingAda();
        laporanKoreksiPage.klikRejectDenganPaging();
    }

    @And("User mengisi alasan penolakan invalid4")
    public void user_mengisi_alasan_penolakan_invalid4() {
        laporanKoreksiPage.isiAlasan("");
    }

    @And("User klik tombol Tolak invalid4")
    public void user_klik_tombol_tolak_invalid4() {
        laporanKoreksiPage.klikTolak();
    }

    @Then("Sistem memberikan pop up message please fill out this field invalid4")
    public void sistem_memberikan_pop_up_message_please_fill_out_this_field_invalid4() {
        String actualMessage = laporanKoreksiPage.getValidationMessage();
        Assert.assertEquals(actualMessage, LaporanKoreksiPage.ERROR_MSG_EN);
    }

    // Scenario 11 :User mencari laporan koreksi berdasarkan filter untuk departemen yang tidak ada
    @When("User klik tombol filter by departemen invalid6")
    public void user_klik_tombol_filter_by_departemen_invalid6() {
        laporanKoreksiPage.klikFilterBy();
    }
    @And("User pilih atau ketik departemen yang tidak ada")
    public void user_pilih_ketik_departemen_yang_tidak_ada() {
        laporanKoreksiPage.filterByDepartment("Sales");
    }
    @Then("Sistem tidak menampilkan data sesuai departemen alias kosong")
    public void sistem_tidak_menampilkan_data_sesuai_departemen_alias_kosong() {
        Assert.assertTrue(
                laporanKoreksiPage.isDataLaporanKoreksiKosong("Sales"),
                "Data laporan koreksi tampil!");
    }

}