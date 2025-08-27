package com.juaracoding.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportCutiPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locator Dashboard & Import ---
    private By dashboardLocator = By.xpath("//p[normalize-space()='Dashboard']");
    private By menuImportLocator = By.xpath("//p[normalize-space()='Import']");
    private By submenuImportCutiLocator = By.xpath("//p[normalize-space()='Import Cuti']");

    // --- Locator Upload & Button ---
    private By fileInputLocator = By.xpath("//input[@id='file_excel']");
    private By importButtonLocator = By.xpath("//button[normalize-space()='Import']");
    private By downloadTemplateButtonLocator = By.xpath("//button[normalize-space()='Download Template']");

    // --- Locator Snackbar (untuk sukses & error) ---
    private By snackbarMessageLocator = By.xpath("//div[@class='MuiSnackbarContent-message css-1w0ym84']");

    // ---- Locator error field -----
    private By errorMessagePdfLocator = By.xpath("//p[@id='file_excel-helper-text']");
    private By errorMessageExcelLamaLocator = By.xpath("//p[@id='file_excel-helper-text']");

    // --- locator logs
    private By jumlahSuksesLocator = By.xpath("//p[contains(text(),'Jumlah Sukses Import Cuti')]");
    private By jumlahGagalLocator = By.xpath("//p[contains(text(),'Jumlah Gagal Import Cuti')]");

    // --- Constants ---
    public static final String SUCCESS_MESSAGE = "Berhasil import excel";
    public static final String ERROR_MESSAGE= "Gagal import excel";
    public static final String ERROR_MSG_EN = "Please select a file.";
    public static final String ERROR_MSG_ID = "Pilih file.";

    public ImportCutiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // === Dashboard ===
    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboardLocator).isDisplayed();
    }

    // === Klik menu Import ===
    public void clickMenuImport() {
        driver.findElement(menuImportLocator).click();
    }

    // === Klik submenu Import Cuti ===
    public void clickSubmenuImportCuti() {
        driver.findElement(submenuImportCutiLocator).click();
    }

    // === Upload file Excel ===
    public void uploadExcelFile(String filePath) {
        driver.findElement(fileInputLocator).sendKeys(filePath);
    }

    // === Klik Import ===
    public void clickImportButton() {
        driver.findElement(importButtonLocator).click();
    }

    // === Klik Download Template ===
    public void clickDownloadTemplate() {
        driver.findElement(downloadTemplateButtonLocator).click();
    }

    // === metod untuk jumlah sukses & gagal import cuti di logs === 
    public int getJumlahSukses() {
    WebElement messageElement = wait.until(
        ExpectedConditions.visibilityOfElementLocated(jumlahSuksesLocator)
    );
    String text = messageElement.getText(); 
    // contoh kemungkinan text:
    // "Jumlah sukses import: 0"
    // "Sukses : 2"
    // "2"

    // cari angka pertama di dalam text
    Matcher matcher = Pattern.compile("\\d+").matcher(text);
    if (matcher.find()) {
        return Integer.parseInt(matcher.group());
    }
    return 0; // default kalau tidak ada angka
    
    }

    public int getJumlahGagal() {
    WebElement messageElement = wait.until(
        ExpectedConditions.visibilityOfElementLocated(jumlahGagalLocator)
    );
    String text = messageElement.getText();
    Matcher matcher = Pattern.compile("\\d+").matcher(text);
    if (matcher.find()) {
        return Integer.parseInt(matcher.group());
    }
    return 0;
    }

    // === Ambil pesan error pdf  ===
    public String getErrorMessagePdf() {
        WebElement messageElement = 
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessagePdfLocator));
        return messageElement.getText();
    }

    // === Ambil pesan error excel lama  ===
    public String getErrorMessageExcelLama() {
        WebElement messageElement = 
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageExcelLamaLocator));
        return messageElement.getText();
    }

    // === Ambil pesan Snackbar (sukses / error) ===
    public String getSnackbarMessage() {
        WebElement messageElement = 
        wait.until(ExpectedConditions.visibilityOfElementLocated(snackbarMessageLocator));
        return messageElement.getText();
    }

    // === Helper untuk cek pesan sukses / error ===
    public boolean isSuccessImport() {
        return getSnackbarMessage().contains(SUCCESS_MESSAGE);
    }

    public boolean isErrorImport() {
        return getSnackbarMessage().contains(ERROR_MESSAGE);
    }

    // === Ambil pesan error bawaan browser (validation bubble) untuk HTML5 contoh: please a select file===
    public String getValidationMessage() {
        WebElement fileInput = driver.findElement(fileInputLocator);
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", fileInput);
    }

    // === Cek apakah file sudah terunduh ===
    public boolean isTemplateDownloaded(String expectedFileName) {
        String downloadPath = System.getProperty("user.home") + "\\Downloads";
        File dir = new File(downloadPath);

        // tunggu max 15 detik
        int retries = 0;
        while (retries < 15) {
            File[] files = dir.listFiles((d, name) -> name.toLowerCase().contains(expectedFileName.toLowerCase())
                    && name.toLowerCase().endsWith(".xlsx"));
            if (files != null && files.length > 0) {
                System.out.println("File ditemukan: " + files[0].getName());
                return true;
            }
            try {
                Thread.sleep(1000); // tunggu 1 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retries++;
        }

        // kalau gagal, tampilkan isi folder
        System.out.println("File tidak ditemukan, isi folder Downloads:");
        for (File f : dir.listFiles()) {
            System.out.println(" - " + f.getName());
        }

        return false;
    }
}
