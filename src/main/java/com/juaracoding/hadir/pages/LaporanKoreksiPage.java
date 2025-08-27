package com.juaracoding.hadir.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaporanKoreksiPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LaporanKoreksiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locator Dashboard & Menu
    private By dashboardLocator = By.xpath("//p[normalize-space()='Dashboard']");
    private By menuLaporanLocator = By.xpath("//p[normalize-space()='Laporan']");
    private By submenuLaporanKoreksiLocator = By.xpath("//p[normalize-space()='Koreksi']");

    // --- Locator Form & Button
    private By inputAlasanLocator = By.xpath("//input[@id='rejectReason']");
    private By inputNamaLocator = By.xpath("//input[@id='search']");
    private By buttonResetLocator = By.xpath("//button[normalize-space()='Reset']");
    private By buttonCariLocator = By.xpath("//button[normalize-space()='Search']");
    private By filterButtonLocator = By.xpath("//button[contains(@class,'MuiButton-containedSecondary')]");
    private By filterDepartmentLocator = By.xpath("//input[@id='job_departement']");
    private By applyFilterLocator = By
            .xpath("//button[contains(@class,'MuiButton-containedPrimary') and normalize-space(text())='Terapkan']");

    // --- Locator Kalender
    private By kalenderLocator = By.xpath(
            "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-7 MuiGrid-grid-lg-8 css-kw2xn2']//div[1]//div[1]//div[1]//button[1]//*[name()='svg']");
    private By buttonSaveKalenderLocator = By.xpath("//button[normalize-space()='save']");

    // --- Dropdown Bulan & Tahun
    private By dropdownTahunLocator = By.xpath("//span[@class='rdrYearPicker']/select");
    private By dropdownBulanLocator = By.xpath("//span[@class='rdrMonthPicker']/select");

    // --- Locator Snackbar (untuk sukses & error) ---
    private By snackbarMessageLocator = By.xpath("//div[contains(@class,'MuiSnackbarContent-message')]");

    // --- Constants ---
    public static final String SUCCESS_MESSAGE_APPROVE = "Berhasil menyetujui koreksi absen";
    public static final String SUCCES_MESSAGE_REJECT = "Berhasil menolak permintaan koreksi absen";
    public static final String ERROR_MSG_EN = "Harap isi bidang ini.";

    // === Menu navigasi
    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboardLocator).isDisplayed();
    }

    public void klikMenuLaporan() {
        driver.findElement(menuLaporanLocator).click();
    }

    public void klikSubMenuLaporanKoreksi() {
    driver.findElement(submenuLaporanKoreksiLocator).click();
    try {
        Thread.sleep(2000); // jeda 2 detik
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


    public void isiNama(String nama) {
        driver.findElement(inputNamaLocator).sendKeys(nama);
    }

    public void klikReset() {
        driver.findElement(buttonResetLocator).click();
    }

    public void klikCari() {
        driver.findElement(buttonCariLocator).click();
    }

    public void klikKalender() {
        driver.findElement(kalenderLocator).click();
    }

    public void klikSaveKalender() {
        driver.findElement(buttonSaveKalenderLocator).click();
    }

    public void isiAlasan(String alasan) {
        WebElement inputAlasan = driver.findElement(inputAlasanLocator);
        inputAlasan.clear();
        inputAlasan.sendKeys(alasan);
    }

    // === Ambil pesan Snackbar (sukses / error) ===
    public String getSnackbarMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(snackbarMessageLocator));
        return messageElement.getText();
    }

    // MASSAGE SUCCES APPROVE
    public boolean isSuccessApprove() {
        return getSnackbarMessage().contains(SUCCESS_MESSAGE_APPROVE);
    }

    // MASSAGE SUCCES REJECT
    public boolean isSuccessReject() {
        return getSnackbarMessage().contains(SUCCES_MESSAGE_REJECT);
    }

    // === Ambil pesan error bawaan browser (validation bubble) ===
    public String getValidationMessage() {
        WebElement fileInput = driver.findElement(inputAlasanLocator);
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", fileInput);
    }

    // --- filter by departement locator

    public void klikFilterBy() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                WebElement filterBtn = new WebDriverWait(driver, Duration.ofSeconds(500))
                        .until(ExpectedConditions.elementToBeClickable(filterButtonLocator));
                filterBtn.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    // ---------- metod klik terapkan departement
    public void klikTerapkan() {
        // Tunggu overlay hilang (contoh jika ada spinner)
        By overlay = By.xpath("//div[contains(@class,'MuiBackdrop-root')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        } catch (TimeoutException e) {
            // Kalau overlay tidak ada, lanjut saja
        }

        // Tunggu tombol bisa diklik
        WebElement applyBtn = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(applyFilterLocator));

        // Scroll ke tombol
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", applyBtn);

        // Klik pakai JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyBtn);

    }

    // ---- metod filter by departemen
public void filterByDepartment(String department) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    By overlay = By.cssSelector("div.MuiDialog-container");
    By optionLocator = By.id("job_departement-option-0");

    // Tunggu modal filter terbuka
    wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));

    try {
        // Cari field departemen (dengan retry anti stale)
        WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(filterDepartmentLocator));
        filterInput.clear();
        filterInput.sendKeys(department);

        // Tunggu suggestion dropdown muncul
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

        // Scroll & klik dengan JS agar lebih stabil
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

    } catch (StaleElementReferenceException e) {
        System.out.println("Element stale, coba ulang filter departemen...");

        // Cari ulang input setelah stale
        WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(filterDepartmentLocator));
        filterInput.clear();
        filterInput.sendKeys(department);

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    }
}


    // === Pilih Bulan
    public void pilihBulan(String bulan) {
        WebElement bulanDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownBulanLocator));
        Select selectBulan = new Select(bulanDropdown);
        selectBulan.selectByVisibleText(bulan);
    }

    // === Pilih Tahun
    public void pilihTahun(String tahun) {
        WebElement tahunDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownTahunLocator));
        Select selectTahun = new Select(tahunDropdown);
        selectTahun.selectByVisibleText(tahun);
    }

    public void pilihTanggal(String tanggal) {
        String xpathTanggal = String.format(
                "//div[@class='rdrDays']//button[not(contains(@class,'rdrDayPassive')) and normalize-space()='%s']",
                tanggal.replaceFirst("^0+(?!$)", ""));

        By tanggalLocator = By.xpath(xpathTanggal);

        // tunggu element visible
        WebElement elemenTanggal = wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalLocator));

        // tunggu bener2 clickable (kadang habis animasi calendar popup)
        wait.until(ExpectedConditions.elementToBeClickable(elemenTanggal));

        elemenTanggal.click();
    }

    // format klik buka kalender milih tanggal , bulan , tahun
    public void pilihRangeTanggal(String startDate, String endDate, String bulan, String tahun) {
        klikKalender();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownTahunLocator));

        pilihTahun(tahun);
        pilihBulan(bulan);

        pilihTanggal(startDate);
        pilihTanggal(endDate);

        klikSaveKalender();
    }

    // === Validasi hasil pencarian expeted Name
    public boolean isDataLaporanKoreksiTampilName(String nama) {
        try {
            By locator = By.xpath("//*[contains(text(),'" + nama + "')]");
            WebElement elemen = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return elemen.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("DEBUG - Isi tabel sekarang: " + driver.findElement(By.tagName("table")).getText());
            throw e;
        }
    }

    // === Validasi hasil pencarian expected Tanggal
    public boolean isDataLaporanKoreksiTampilTanggal(String tanggal) {
        try {

            By locator = By.xpath(
                    "//table[contains(@class,'MuiTable-root')]//tbody//td//*[contains(text(),'" + tanggal + "')]");
            WebElement elemen = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return elemen.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("DEBUG - Isi tabel sekarang: " + driver.findElement(By.tagName("table")).getText());
            throw e;
        }
    }

    // == Validasi Hasil Pencarian expected Data Kosong
    public boolean isDataLaporanKoreksiKosong(String nama) {
        try {
            By locator = By.xpath("//table[@id='laporanKoreksi']//td[contains(text(),'" + nama + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("DEBUG - Data ditemukan: " + nama);
            return false; // data ada
        } catch (TimeoutException e) {
            System.out.println("DEBUG - Data tidak ditemukan: " + nama);
            return true; // data kosong
        }
    }

    // Validasi apakah status PENDING ada
    public boolean cekStatusPendingAda() {
        try {
            By statusPending = By.xpath("//h6[contains(normalize-space(),'PENDING')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(statusPending));
            System.out.println("Status PENDING ditemukan.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Tidak ada status PENDING.");
            return false;
        }
    }

    // == Validasi Hasil Pencarian expected Data Kosong dengan Tanggal
    public boolean isDataLaporanKoreksiKosongDenganTanggal(String tanggal) {
        try {
            By locator = By.xpath("//table[@id='laporanKoreksi']//td[contains(text(),'" + tanggal + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("DEBUG - Data ditemukan: " + tanggal);
            return false; // data ada
        } catch (TimeoutException e) {
            System.out.println("DEBUG - Data tidak ditemukan: " + tanggal);
            return true; // data kosong
        }
    }

    // Klik tombol Approve, kalau tidak ada coba Next Page
    public void klikApproveDenganPaging() {
        By approveBtnLocator = By.xpath("//button[@aria-label='Approval Koreksi']");
        By nextPageBtnLocator = By.xpath("//button[@aria-label='Go to next page']");

        boolean ditemukan = false;

        while (true) {
            try {
                // Cari & klik tombol Approve di halaman ini dengan retry anti-stale
                int attempts = 0;
                while (attempts < 3) {
                    try {
                        WebElement approveBtn = wait.until(
                                ExpectedConditions.elementToBeClickable(approveBtnLocator));
                        approveBtn.click();
                        System.out.println("Tombol Approve berhasil diklik.");
                        ditemukan = true;
                        break;
                    } catch (StaleElementReferenceException se) {
                        System.out.println("DEBUG: Approve button stale, coba lagi... attempt " + (attempts + 1));
                    }
                    attempts++;
                }

                if (ditemukan)
                    break; // sudah berhasil klik, keluar dari loop

            } catch (TimeoutException e) {
                System.out.println("Tidak ada tombol Approve di halaman ini. Coba klik Next Page...");

                try {
                    // Scroll biar tombol Next kelihatan
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                    Thread.sleep(500);

                    WebElement nextBtn = wait.until(
                            ExpectedConditions.presenceOfElementLocated(nextPageBtnLocator));

                    if (!nextBtn.isEnabled()) {
                        System.out.println("Tombol Next Page disabled. Halaman terakhir sudah dicapai.");
                        break;
                    }

                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
                    System.out.println("Berpindah ke halaman berikutnya...");

                    // reset posisi
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
                    Thread.sleep(1000);

                } catch (TimeoutException ex) {
                    System.out.println("Tombol Next Page tidak ditemukan. Halaman terakhir sudah dicapai.");
                    break;
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada tombol Approve di semua halaman.");
        }
    }

    // Klik tombol konfirmasi "Setujui"
    public void klikKonfirmasiSetujui() {
        try {
            By btnSetujui = By.xpath("//button[normalize-space(text())='Setujui']");
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(btnSetujui));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
            System.out.println("Tombol 'Setujui' berhasil diklik.");
        } catch (TimeoutException e) {
            System.out.println("Tombol 'Setujui' tidak ditemukan atau tidak bisa diklik.");
        }
    }

    // Klik tombol Reject, kalau tidak ada coba Next Page

    public void klikRejectDenganPaging() {
        By rejectBtnLocator = By.xpath("//button[@aria-label='Reject Koreksi']");
        By nextPageBtnLocator = By.xpath("//button[@aria-label='Go to next page']");

        boolean ditemukan = false;

        while (true) {
            try {
                // Cari & klik tombol Reject di halaman ini dengan retry anti-stale
                int attempts = 0;
                while (attempts < 3) {
                    try {
                        WebElement rejectBtn = wait.until(ExpectedConditions.elementToBeClickable(rejectBtnLocator));
                        rejectBtn.click();
                        System.out.println("Tombol Reject berhasil diklik.");
                        ditemukan = true;
                        break;
                    } catch (StaleElementReferenceException se) {
                        System.out.println("DEBUG: Reject button stale, coba lagi... attempt " + (attempts + 1));
                    }
                    attempts++;
                }

                if (ditemukan)
                    break; // sudah berhasil klik, keluar dari loop

            } catch (TimeoutException e) {
                System.out.println("Tidak ada tombol Reject di halaman ini. Coba klik Next Page...");

                try {
                    // Scroll ke bawah dulu supaya tombol Next kelihatan
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                    Thread.sleep(500);

                    // Cari tombol Next Page
                    WebElement nextBtn = wait.until(ExpectedConditions.presenceOfElementLocated(nextPageBtnLocator));

                    // Kalau tombol disable → stop
                    if (!nextBtn.isEnabled()) {
                        System.out.println("Tombol Next Page disabled. Halaman terakhir sudah dicapai.");
                        break;
                    }

                    // Pakai JS click biar aman
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
                    System.out.println("Berpindah ke halaman berikutnya...");

                    // Setelah pindah page, scroll balik ke atas
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
                    Thread.sleep(1000); // tunggu halaman load
                } catch (TimeoutException ex) {
                    System.out.println("Tombol Next Page tidak ditemukan. Halaman terakhir sudah dicapai.");
                    break; // sudah sampai halaman terakhir
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada tombol Reject di semua halaman.");
        }
    }

    // klik button tolak
    public void klikTolak() {
        try {
            By btnTolak = By.xpath("//button[normalize-space(text())='Tolak']");
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(btnTolak));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
            System.out.println("Tombol 'Tolak' berhasil diklik.");
        } catch (TimeoutException e) {
            System.out.println("Tombol 'Tolak' tidak ditemukan atau tidak bisa diklik.");
        }
    }

}
