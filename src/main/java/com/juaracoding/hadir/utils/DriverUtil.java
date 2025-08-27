//package com.juaracoding.hadir.utils;
//
//import org.openqa.selenium.WebDriver;
//
//public class DriverUtil {
//    private static WebDriver driver;
//    private static boolean isDriverActive = false;
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//            DriverManager dm = new DriverManager();
//            driver = dm.getDriver();
//            isDriverActive = true;
//        }
//        return driver;
//    }
//
//    public static void openDriver() {
//        getDriver(); // Memastikan driver terinisialisasi
//    }
//
//    public static void quitDriver() {
//        if (driver != null && isDriverActive) {
//            driver.quit();
//            driver = null;
//            isDriverActive = false;
//            System.out.println("Driver closed successfully");
//        }
//    }
//
//    public static boolean isDriverActive() {
//        return isDriverActive;
//    }
//
//    public static void resetDriver() {
//        if (driver != null) {
//            quitDriver();
//        }
//        driver = null;
//        isDriverActive = false;
//    }
//}
//
package com.juaracoding.hadir.utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtil {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void openDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver.set(new ChromeDriver(options));
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}