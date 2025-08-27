package com.juaracoding.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ImportAbsenPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locator Dashboard & Import ---
    private By dashboardLocator = By.xpath("//p[normalize-space()='Dashboard']");
    private By menuImportLocator = By.xpath("//div[@class='sidebar__item MuiBox-root css-0']//p[normalize-space()='Import']");
    private By submenuImportAbsbenLocator = By.xpath("//p[normalize-space()='Import Absen']");

    // --- Locator Upload & Button ---
    private By fileInputLocator = By.xpath("//input[@type='file']");
    private By importButtonLocator = By.xpath("//button[normalize-space()='Import']");
    private By downloadTemplateButtonLocator = By.xpath("//button[normalize-space()='Download Template']");

    // --- Locator Snackbar (untuk sukses & error) ---
    private By snackbarMessageLocator = By.xpath("//div[@class='MuiSnackbarContent-message css-1w0ym84']");

    // --- Constants ---
    public static final String SUCCESS_MESSAGE = "Berhasil import excel";
    public static final String ERROR_MESSAGE = "*File harus berupa file Excel (.xls atau .xlsx)";
    public static final String ERROR_MSG_EN = "Please select a file.";
    public static final String ERROR_MSG_ID = "Pilih file.";

    public ImportAbsenPage(WebDriver driver) {
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

    // === Klik submenu Import Absen ===
    public void clickSubmenuImportAbsen() {
        driver.findElement(submenuImportAbsbenLocator).click();
    }

    // === Upload file Excel ===
    public void uploadExcelFile(String filePath) {
        driver.findElement(fileInputLocator).sendKeys(filePath);
    }

    // Klik Import
    public void clickImportButton() {
        driver.findElement(importButtonLocator).click();
    }

    // Klik Download Template
    public void clickDownloadTemplate() {
        driver.findElement(downloadTemplateButtonLocator).click();
    }

    // Ambil pesan Snackbar (sukses / error)
    public String getSnackbarMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(snackbarMessageLocator));
        return messageElement.getText();
    }

    // untuk cek pesan sukses / error
    public boolean isSuccessImport() {
        return getSnackbarMessage().contains(SUCCESS_MESSAGE);
    }

    public boolean isErrorImport() {
        return getSnackbarMessage().contains(ERROR_MESSAGE);
    }

    // === Ambil pesan error bawaan browser (validation bubble) ===
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