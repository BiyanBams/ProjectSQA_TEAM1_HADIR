package com.juaracoding.hadir.utils;




import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverThreadManager {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // inisialisasi driver
    public static void initDriver() {
        if (driverThread.get() == null) {
            String downloadFilepath = "C:\\Users\\ne497\\Downloads";

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadFilepath);
            prefs.put("download.prompt_for_download", false);
            prefs.put("profile.default_content_settings.popups", 0);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);

            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driverThread.set(driver);
        }
    }

    // ambil driver
    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            initDriver();
        }
        return driverThread.get();
    }

    // quit driver
    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}



