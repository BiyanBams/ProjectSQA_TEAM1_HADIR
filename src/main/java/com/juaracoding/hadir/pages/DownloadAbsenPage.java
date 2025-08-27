package com.juaracoding.hadir.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadAbsenPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DownloadAbsenPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locator Dashboard & Menu
    private By dashboardLocator = By.xpath("//p[normalize-space()='Dashboard']");
    private By menuLaporanLocator = By.xpath("//p[normalize-space()='Laporan']");
    private By downloadAbsenLocator = By.xpath("//p[normalize-space()='Download Absen']");

    // -- Locator input
    private By inputNIKLocator = By.xpath("//input[@placeholder='Cari berdasarkan NIK']");
    private By inputNameLocator = By.xpath("//input[@placeholder='Cari berdasarkan nama']");
    private By inputUplinerLocator = By.xpath("//input[@placeholder='Cari berdasarkan upliner']");
    private By inputDivisionLocator = By.xpath("//input[@placeholder='Pilih Divisi']");
    private By inputUnitLocator = By.xpath("//input[@placeholder='Pilih Unit']");
    private By inputReportTypeLocator = By.xpath("//input[@placeholder='Pilih Tipe']");

    // --- Locator Kalender
    private By kalenderLocator = By.xpath(
            "//*[@id=\"generate-report\"]/div[1]/div[1]/div/div/div[1]/div/div/button");

    // --- Dropdown Bulan & Tahun
    private By dropdownTahunLocator = By.xpath("//span[@class='rdrYearPicker']/select");
    private By dropdownBulanLocator = By.xpath("//span[@class='rdrMonthPicker']/select");

    // --- Locator Download & Reset
    private By buttonDownloadLocator = By.xpath("//button[contains(text(),'Download')]");
    private By buttonResetLocator = By.xpath("//button[contains(text(),'Reset')]");

    // --- Locator Snackbar (untuk sukses & error) ---
    private By snackbarMessageLocator = By.xpath("//div[contains(@class,'MuiSnackbarContent-message')]");

    // --- Constants ---
    public static final String SUCCESS_MESSAGE_DOWNLOAD = "Berhasil generate report";

    // ===== Helper Method =====
    private void safeSendKeysDropdown(By inputLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By overlay = By.cssSelector("div.MuiAutocomplete-root input.MuiAutocomplete-input");
        By optionLocator = By.id("free-solo-with-text-demo-option-0");

        // Tunggu modal terbuka
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));

        try {
            // Cari input field (anti stale)
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            inputField.clear();
            inputField.sendKeys(text);

            // Tunggu option muncul
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

            // Scroll & klik JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        } catch (StaleElementReferenceException e) {
            System.out.println("Element stale, ulang input: " + text);

            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            inputField.clear();
            inputField.sendKeys(text);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
    }

    // ===== Actions =====
    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboardLocator).isDisplayed();
    }

    public void klikMenuLaporan() {
        driver.findElement(menuLaporanLocator).click();
    }

    public void klikDownloadAbsen() {
        driver.findElement(downloadAbsenLocator).click();
    }

    public void isiNIK(String nik) {
        safeSendKeysDropdown(inputNIKLocator, nik);
    }

    public void isiNama(String nama) {
        safeSendKeysDropdown(inputNameLocator, nama);
    }

    public void isiUpliner(String upliner) {
        safeSendKeysDropdown(inputUplinerLocator, upliner);
    }

    public void isiDivision(String division) {
        safeSendKeysDropdown(inputDivisionLocator, division);
    }

    public void isiUnit(String unit) {
        safeSendKeysDropdown(inputUnitLocator, unit);
    }

    public void isiReportType(String reportType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Tunggu input field clickable
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(inputReportTypeLocator));

        // Clear pakai JavaScript supaya pasti kehapus
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", inputField);

        // Klik & beri jeda sebentar supaya JS autocomplete reset
        inputField.click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ketik teks baru
        inputField.sendKeys(reportType);

        // Tunggu option muncul dan klik
        By optionLocator = By.id("free-solo-with-text-demo-option-0");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    }

    public void klikKalender() {
        driver.findElement(kalenderLocator).click();
    }

    public void clickDownload() {
        driver.findElement(buttonDownloadLocator).click();
    }

    public void clickReset() {
        driver.findElement(buttonResetLocator).click();
    }

    // === Ambil pesan Snackbar (sukses / error) ===
    public String getSnackbarMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(snackbarMessageLocator));
        return messageElement.getText();
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

        // klik sembarang untuk menutup kalender
        WebElement body = driver.findElement(By.tagName("body"));
        body.click();
        // Tambahkan jeda supaya animasi selesai
        try {
            Thread.sleep(500); // 0.5 detik, bisa diubah sesuai kebutuhan
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
