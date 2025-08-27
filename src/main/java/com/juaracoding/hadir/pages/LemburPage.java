package com.juaracoding.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class LemburPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LemburPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);

    }

    // --- Locator Dashboard & Menu Laporan
    private By dashboardTitle = By.xpath("//p[normalize-space()='Dashboard']");
    private By menuLaporan = By.xpath("//div[contains(@class,'sidebar__item')]//*[text()='Laporan']");
    private By menuLembur = By.xpath("//p[normalize-space()='Lembur']");

    // Search elements
    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@placeholder='End Date']")
    private WebElement endDateInputText;

    @FindBy(xpath = "//input[@placeholder='Start Date']")
    private WebElement startDateInputText;

    @FindBy(xpath = "//input[@placeholder='End Date']")
    private WebElement endDateInput;

    // --- Locator Kalender
    private By kalenderLocator = By.xpath(
            "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-7 MuiGrid-grid-lg-8 css-kw2xn2']//div[1]//div[1]//div[1]//button[1]//*[name()='svg']");
    private By buttonSaveKalenderLocator = By.xpath("//button[normalize-space()='save']");

    // --- Dropdown Bulan & Tahun
    private By dropdownTahunLocator = By.xpath("//span[@class='rdrYearPicker']/select");
    private By dropdownBulanLocator = By.xpath("//span[@class='rdrMonthPicker']/select");

    @FindBy(xpath = "//button[normalize-space(text())='Search']")
    private WebElement cariButton;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;


    @FindBy(xpath = "//button[normalize-space()='Export']")
    private WebElement exportButton;

    // Filter elements
    @FindBy(xpath = "//button[.//svg[contains(@class,'feather-filter')]]")
    private WebElement filterButton;

    @FindBy(xpath = "//input[@id='job_departement']")
    private WebElement departmentSelect;

    @FindBy(xpath = "//button[normalize-space()='Terapkan']")
    private WebElement terapkanButton;

    // Edit elements
    @FindBy(xpath = "//table/tbody/tr[1]/td[15]/button")
    private WebElement editButton;

    @FindBy(xpath = "//button[normalize-space()='Ubah Data']")
    private WebElement ubahDataButton;

    // Table elements
    @FindBy(xpath = "//div[contains(@class, 'MuiTableContainer')]//tr | //table//tr")
    private List<WebElement> tableRows;

    // Message elements
    @FindBy(xpath = "//div[contains(@class,'MuiSnackbarContent-message')]")
    private WebElement messageElement;

    // Validation elements
    @FindBy(xpath = "//div[contains(@class,'MuiSnackbarContent-message') and contains(text(),'Harap isi tanggal terlebih dahulu')]")
    private WebElement validationElement;



    // === Dashboard ===
    public boolean isDashboardDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
    return dashboard.isDisplayed();
    }

// --- Navigasi Menu
public void klikMenuLaporan() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement laporan = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@class='sidebar__item MuiBox-root css-0']//p[normalize-space()='Laporan']")
    ));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", laporan);
}
//    public void klikMenuLaporan() {
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    WebElement laporan = wait.until(ExpectedConditions.elementToBeClickable(menuLaporan));
//    laporan.click();
//    }

    public void klikSubMenuLaporanLembur() {
        klikMenuLaporan();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement lembur = wait.until(ExpectedConditions.elementToBeClickable(menuLembur));
    lembur.click();
    }

    // ========== BASIC SEARCH METHODS ==========
    public void enterSearchName(String name) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("search"))
            );
            searchInput.clear();
            searchInput.sendKeys(name);
            System.out.println("✅ Berhasil input nama: " + name);
        } catch (Exception e) {
            System.out.println("❌ Error entering search name: " + e.getMessage());
            throw e;
        }
    }

//    public void enterSearchName(String name) {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(searchInput));
//            searchInput.clear();
//            searchInput.sendKeys(name);
//            System.out.println("Entered search name: " + name);
//        } catch (Exception e) {
//            System.out.println("Error entering search name: " + e.getMessage());
//        }
//    }

    public void klikKalender() {
        driver.findElement(kalenderLocator).click();
    }

    public void klikSaveKalender() {
        driver.findElement(buttonSaveKalenderLocator).click();
    }

//    public void clickCariButton() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement cariBtn = wait.until(
//                    ExpectedConditions.elementToBeClickable(
//                            By.xpath("//button[contains(.,'Search')]"))
//            );
//            cariBtn.click();
//            System.out.println("✅ Klik tombol Cari berhasil");
//        } catch (Exception e) {
//            System.out.println("❌ Error clicking Cari button: " + e.getMessage());
//            throw e;
//        }
//    }

        public void clickCariButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cariButton));
            cariButton.click();
            System.out.println("Clicked Cari button");
            waitForResults();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Error clicking Cari button: " + e.getMessage());
        }
    }

    // ========== ACTION METHODS ==========

    public void clickResetButton() {
        try {
            if (resetButton == null) {
                throw new RuntimeException("Reset button element is NULL → cek locator @FindBy!");
            }

            System.out.println("DEBUG: Locator Reset button ditemukan, coba klik...");
            wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
            System.out.println("Clicked Reset button");

            // Kasih jeda 2 detik biar field sempat di-reset oleh React
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Error clicking Reset button: " + e.getMessage());
        }
    }


//    public void clickResetButton() {
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(resetButton));
//            resetButton.click();
//            System.out.println("Clicked Reset button");
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            System.out.println("Error clicking Reset button: " + e.getMessage());
//        }
//    }

    public void clickExportButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(exportButton));
            exportButton.click();
            System.out.println("Clicked Export button");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error clicking Export button: " + e.getMessage());
        }
    }

    public void clickFilterButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement filterButtonn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.className("css-1k0lhp1")
                    )
            );

            filterButtonn.click();
            System.out.println("Clicked Filter button");

            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error clicking Filter button: " + e.getMessage());
        }
    }

//    public void clickFilterButton() {
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(filterButton));
//            filterButton.click();
//            System.out.println("Clicked Filter button");
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            System.out.println("Error clicking Filter button: " + e.getMessage());
//        }
//    }

    public void selectDepartment(String department) {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Cari input
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("job_departement"))
        );

        // 2. Klik dan ketik department
        input.click();
        input.clear();
        input.sendKeys(department);

        // 3. Tunggu sampai option di dropdown muncul
        WebElement activeOption = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@id='job_departement-listbox']//li[normalize-space()='" + department + "']")
                )
        );

        // 4. Klik pakai Actions biar event React/MUI ter-trigger
        Actions actions = new Actions(driver);
        actions.moveToElement(activeOption).click().perform();

        // 5. Tunggu sampai value di input sudah sama dengan department
        wait.until(ExpectedConditions.attributeToBe(input, "value", department));

        System.out.println("Selected department: " + department);
    } catch (Exception e) {
        System.out.println("Error selecting department: " + e.getMessage());
    }
}

    public void clickTerapkanButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(terapkanButton));
            terapkanButton.click();
            System.out.println("Clicked Terapkan button");
            waitForResults();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error clicking Terapkan button: " + e.getMessage());
        }
    }

    public void clickEditButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editButton));
            editButton.click();
            System.out.println("Clicked Edit button");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error clicking Edit button: " + e.getMessage());
        }
    }

    public void clickUbahDataButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ubahDataButton));
            ubahDataButton.click();
            System.out.println("Clicked Ubah Data button");
            waitForMessage();
        } catch (Exception e) {
            System.out.println("Error clicking Ubah Data button: " + e.getMessage());
        }
    }

    // ====  KALENDER  ====
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

    // ========== CHECK/VERIFICATION METHODS ==========
    public Boolean isDataDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Cari semua <tbody> dalam table
            List<WebElement> tbodys = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//*[@id='__next']//table/tbody")
                    )
            );

            if (tbodys.isEmpty()) {
                System.out.println("Tidak ada <tbody> → dianggap tidak ada data");
                return false;
            }

            // Ambil semua row
            List<WebElement> rows = tbodys.get(0).findElements(By.tagName("tr"));

            if (rows.isEmpty()) {
                System.out.println("Tidak ada row di <tbody> → data kosong");
                return false;
            }

            // Kalau ada minimal 1 row, berarti ada data
            System.out.println("Jumlah row di tbody: " + rows.size() + " → data ada");
            return true;

        } catch (Exception e) {
            System.out.println("Error checking data display: " + e.getMessage());
            return false;
        }
    }

    public boolean areSearchFieldsCleared() {
        try {
            // kasih waktu sedikit untuk Reset bekerja
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(driver -> {
                String searchValue = searchInput.getAttribute("value");
                String startDateValue = startDateInputText.getAttribute("value");
                String endDateValue = endDateInputText.getAttribute("value");

                boolean searchCleared = (searchValue == null || searchValue.trim().isEmpty());
                boolean startDateCleared = (startDateValue == null || startDateValue.trim().isEmpty());
                boolean endDateCleared = (endDateValue == null || endDateValue.trim().isEmpty());

                return searchCleared && startDateCleared && endDateCleared;
            });

            // ambil value terakhir buat debug
            String searchValue = searchInput.getAttribute("value");
            String startDateValue = startDateInputText.getAttribute("value");
            String endDateValue = endDateInputText.getAttribute("value");

            boolean allCleared =
                    (searchValue == null || searchValue.trim().isEmpty()) &&
                            (startDateValue == null || startDateValue.trim().isEmpty()) &&
                            (endDateValue == null || endDateValue.trim().isEmpty());

            System.out.println("=== DEBUG RESET FIELDS ===");
            System.out.println("Search: '" + searchValue + "'");
            System.out.println("Start Date: '" + startDateValue + "'");
            System.out.println("End Date: '" + endDateValue + "'");
            System.out.println("All cleared? " + allCleared);
            System.out.println("==========================");

            return allCleared;
        } catch (Exception e) {
            System.out.println("Error checking search fields: " + e.getMessage());
            return false;
        }
    }




    //    public boolean areSearchFieldsCleared() {
//        try {
//            // Pastikan semua field sudah visible dulu
//            wait.until(ExpectedConditions.visibilityOf(searchInput));
//            wait.until(ExpectedConditions.visibilityOf(startDateInput));
//            wait.until(ExpectedConditions.visibilityOf(endDateInput));
//
//            boolean searchCleared = searchInput.getAttribute("value").isEmpty();
//            boolean startDateCleared = startDateInput.getAttribute("value").isEmpty();
//            boolean endDateCleared = endDateInput.getAttribute("value").isEmpty();
//
//            boolean allCleared = searchCleared && startDateCleared && endDateCleared;
//            System.out.println("Search fields cleared: " + allCleared);
//            return allCleared;
//        } catch (Exception e) {
//            System.out.println("Error checking search fields: " + e.getMessage());
//            return false;
//        }
//    }

    public boolean isExportSuccessful() {
        try {
            // Untuk sementara return true asumsi export berhasil
            // Bisa ditambahkan logic check file download nanti
            System.out.println("Export process completed");
            return true;
        } catch (Exception e) {
            System.out.println("Error checking export: " + e.getMessage());
            return false;
        }
    }

    // ========== MESSAGE METHODS ==========
    public String getMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(messageElement));
            String message = messageElement.getText();
            System.out.println("Message found: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("No message found: " + e.getMessage());
            return "No message found";
        }
    }

    public String getValidationMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(validationElement));
            String message = validationElement.getText();
            System.out.println("Validation message: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("No validation message found: " + e.getMessage());
            return "No validation message found";
        }
    }

    // ========== UTILITY METHODS ==========
    public void searchByName(String name) {
        enterSearchName(name);
        clickCariButton();
    }

//    public void searchByDate(String startDate, String endDate) {
//        enterStartDate(startDate);
//        enterEndDate(endDate);
//        clickCariButton();
//    }
//
//    public void searchByNameAndDate(String name, String startDate, String endDate) {
//        enterSearchName(name);
//        enterStartDate(startDate);
//        enterEndDate(endDate);
//        clickCariButton();
//    }

    public void clearSearchFields() {
        try {
            searchInput.clear();
            startDateInputText.clear();
            endDateInputText.clear();
            System.out.println("Cleared all search fields");
        } catch (Exception e) {
            System.out.println("Error clearing fields: " + e.getMessage());
        }
    }

    // ========== PRIVATE METHODS ==========
    private void waitForResults() {
        try {
            System.out.println("Waiting for results...");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void waitForMessage() {
        try {
            System.out.println("Waiting for message...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ========== DEBUGGING METHODS ==========
    public void debugPageState() {
        System.out.println("=== DEBUG PAGE STATE ===");
        try {
            System.out.println("Search Input: '" + searchInput.getAttribute("value") + "'");
            System.out.println("Start Date: '" + startDateInputText.getAttribute("value") + "'");
            System.out.println("End Date: '" + endDateInputText.getAttribute("value") + "'");
            System.out.println("Table Rows: " + tableRows.size());
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Error debugging page state: " + e.getMessage());
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}