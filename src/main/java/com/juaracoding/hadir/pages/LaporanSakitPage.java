package com.juaracoding.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import java.util.List;

public class LaporanSakitPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LaporanSakitPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // --- Locator Dashboard & Menu Laporan
    private By dashboardLocator = By.xpath("//p[normalize-space()='Dashboard']");
    private By menuLaporanLocator = By.xpath("//p[normalize-space()='Laporan']");
    private By submenuLaporanSakitLocator = By.xpath("//p[normalize-space()='Sakit']");

    // --- Locator Form Laporan Sakit
    private By inputNamaLocator = By.xpath("//input[@id='search']");
    private By buttonResetLocator = By.xpath("//button[normalize-space()='Reset']");
    private By buttonCariLocator = By.xpath("//button[normalize-space()='Search']");

    // --- Locator Kalender
    private By kalenderLocator = By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-7 MuiGrid-grid-lg-8 css-kw2xn2']//div[1]//div[1]//div[1]//button[1]//*[name()='svg']");
    private By buttonSaveKalenderLocator = By.xpath("//button[normalize-space()='save']");

    // --- Dropdown Bulan & Tahun
    private By dropdownTahunLocator = By.xpath("//span[@class='rdrYearPicker']/select");
    private By dropdownBulanLocator = By.xpath("//span[@class='rdrMonthPicker']/select");

    // --- Locator filter by departement
    private By filterButtonLocator = By.xpath("//button[contains(@class,'MuiButton-containedSecondary')]");
    private By filterDepartmentLocator = By.xpath("//input[@id='job_departement']");
    private By applyFilterLocator = By.xpath("//button[contains(@class,'MuiButton-containedPrimary') and normalize-space(text())='Terapkan']");

    // Locator tabel ada setelah di filterby
    private By tableRowsLocator = By.xpath("//table//tbody//tr");

    // Locator tabel kosong setelah di filterby
    private By tableRowsLocatorKosong = By.cssSelector("#tableDepartemen tbody tr:not(.no-data)");

    // ---- locator view & download button
    private By viewButtonLocator = By.xpath("//tbody/tr[1]/td[5]//a[normalize-space()='View']");
    private By downloadButtonLocator = By.xpath("//a[normalize-space()='Download']");

    // untuk halaman view foto ada
    private By fotoPreviewLocator = By.xpath("//img[@alt='Photo sakit']");
    
    // untuk halaman view foto tidak ada
    private By fotoViewLocator = By.xpath("//img[@alt='Photo sakit']");

    // untuk halaman Download berhasil (tab baru)
    private By fotoPreviewLocatorDownload = By.tagName("img");

    // untuk halaman download foto yang tidak ada (tab baru)
    private By fotoPriviewError = By.xpath("//h1[@id='error-code']");

    // === Dashboard ===
    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboardLocator).isDisplayed();
    }

    // --- Navigasi Menu
    public void klikMenuLaporan() {
        driver.findElement(menuLaporanLocator).click();
    }

    public void klikSubMenuLaporanSakit() {
        driver.findElement(submenuLaporanSakitLocator).click();
    }

    // === input field & button reset & search ====
   public void isiNama(String nama) {
        driver.findElement(inputNamaLocator).sendKeys(nama);
    }

    public void klikReset() {
        driver.findElement(buttonResetLocator).click();
    }


    public void klikCari() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Tunggu tombol sampai bisa diklik
    WebElement cariBtn = wait.until(ExpectedConditions.elementToBeClickable(buttonCariLocator));

    // Scroll biar kelihatan (hindari ketutupan header/toolbar)
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cariBtn);

    try {
        cariBtn.click(); // coba klik normal
        } catch (ElementClickInterceptedException e) {
        // Kalau masih ketutupan, pakai JS click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cariBtn);
        }
    }

    // ===== masuk kalander ====
    public void klikKalender() {
        driver.findElement(kalenderLocator).click();
    }

    public void klikSaveKalender() {
        driver.findElement(buttonSaveKalenderLocator).click();
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
    public boolean isDataLaporanSakitTampilName(String nama) {
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
    public boolean isDataLaporanSakitTampilTanggal(String tanggal) {
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
    public boolean isDataLaporanSakitKosong(String nama) {
        try {
            By locator = By.xpath("//table[@id='laporanSakit']//td[contains(text(),'" + nama + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("DEBUG - Data ditemukan: " + nama);
            return false; // data ada
        } catch (TimeoutException e) {
            System.out.println("DEBUG - Data tidak ditemukan: " + nama);
            return true; // data kosong
        }
    }

    // == Validasi Hasil Pencarian expected Data Kosong dengan Tanggal
    public boolean isDataLaporanSakitKosongDenganTanggal(String tanggal) {
        try {
            By locator = By.xpath("//table[@id='laporanSakit']//td[contains(text(),'" + tanggal + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("DEBUG - Data ditemukan: " + tanggal);
            return false; // data ada
        } catch (TimeoutException e) {
            System.out.println("DEBUG - Data tidak ditemukan: " + tanggal);
            return true; // data kosong
        }
    }

     // --- filter by departement locator

        public void klikFilterBy() {
        int attempts = 0;
        while(attempts < 2) {
            try {
                WebElement filterBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(filterButtonLocator));
                filterBtn.click();
                break;
            } catch(StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    //---- metod filter by departemen
    public void filterByDepartment(String department) {

    // Tunggu modal terbuka
    By overlay = By.cssSelector("div.MuiDialog-container");
    new WebDriverWait(driver, Duration.ofSeconds(15))
        .until(ExpectedConditions.visibilityOfElementLocated(overlay));

    // Ketik departemen
    WebElement filterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(filterDepartmentLocator));
    filterInput.clear();
    filterInput.sendKeys(department);

    // Tunggu dropdown muncul (gunakan id atau fallback ke text)
    By optionLocator = By.id("job_departement-option-0");

    // Retry loop untuk handle StaleElementReferenceException
    int attempts = 0;
    while (attempts < 2) {
        try {
            WebElement option = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(optionLocator));

    // Scroll & klik pakai JS
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            break; // sukses, keluar loop
        }catch (StaleElementReferenceException e) {
            attempts++;
            if (attempts == 2) throw e; // kalau gagal 2x baru lempar error
            }
        
        }
    }

    // ---------- metod klik terapkan departement
    public void klikTerapkan() {

    // Tunggu overlay hilang (contoh jika ada spinner)
    By overlay = By.xpath("//div[contains(@class,'MuiBackdrop-root')]");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    try {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
    } catch (TimeoutException e) {
        // Kalau overlay tidak ada, lanjut saja
    }

    // Tunggu tombol bisa diklik
    WebElement applyBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
        .until(ExpectedConditions.elementToBeClickable(applyFilterLocator));

    // Scroll ke tombol
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", applyBtn);

    // Klik pakai JS
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyBtn);
    
    }

    // --- Verifikasi Data Filter --- 
    // Karena tidak ada kolom departemen, cek cukup ada data setelah filter
    public boolean isDataTampilSetelahFilter() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRowsLocator));
            List<WebElement> rows = driver.findElements(tableRowsLocator);
            return !rows.isEmpty(); // minimal ada 1 row tampil
        } catch (TimeoutException e) {
            return false;
        }
    }

    // metod untuk departemen yg dicari tidak ada dan mengembalikan data kosong
    public boolean isDataKosongSetelahFilter() {
    List<WebElement> rows; // deklarasi di luar try
        try {
            rows = driver.findElements(tableRowsLocatorKosong);
            return rows.isEmpty(); // true jika tidak ada data
        } catch (StaleElementReferenceException e) {
            // Ambil ulang elemen
            rows = driver.findElements(tableRowsLocatorKosong);
            return rows.isEmpty();
        }
    }

    // --- View Foto
    public void klikView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]")));
        for (int i = 0; i < 3; i++) {
            try {
                WebElement viewLink = wait.until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewLink);
                viewLink.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Element stale, retry ke-" + (i+1));
            }
        }
    }
    
    public By getFotoPreviewLocatorView() {
        return fotoPreviewLocator;
    }

    public By getFotoviewLocator() {
        return fotoViewLocator;
    }

    // === klik download (umum untuk semua kasus) ===
    public void klikDownload() {
        // tunggu tabel/tr muncul dan spinner hilang
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-spinner']")));

        // retry klik aman untuk menghindari StaleElementReferenceException
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(downloadButtonLocator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadLink);
                downloadLink.click();
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
            }
        }

        // tunggu tab baru muncul
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // pindah ke tab baru
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // === cek foto tampil (positif case) ===
    public boolean isFotoTampil() {
        boolean tampil = wait.until(ExpectedConditions.visibilityOfElementLocated(fotoPreviewLocatorDownload)).isDisplayed();
        // tutup tab baru dan kembali ke tab utama
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        return tampil;
    }

    // === cek error 404 tampil (negatif case) ===
    public boolean isError404Tampil() {
        boolean tampil = wait.until(ExpectedConditions.visibilityOfElementLocated(fotoPriviewError)).isDisplayed();
        // tutup tab baru dan kembali ke tab utama
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        return tampil;
    }

    
   // validasi untuk tampil foto
   public boolean isFotoDisplayed(By locator) {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Tunggu elemen muncul di DOM
        WebElement foto = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        // Scroll ke elemen kalau lazy-load
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", foto);

        // Tunggu elemen terlihat
        wait.until(ExpectedConditions.visibilityOf(foto));

        return foto.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Foto tidak muncul: " + e.getMessage());
            return false;
        }
    }

}
