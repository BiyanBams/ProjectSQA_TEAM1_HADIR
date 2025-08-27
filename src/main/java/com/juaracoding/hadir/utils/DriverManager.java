package com.juaracoding.hadir.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
  private WebDriver driver;

  public DriverManager() {
    // Set download preferences
    String downloadFilepath = "C:\\Users\\Hype\\Downloads";

    Map<String, Object> prefs = new HashMap<>();
    prefs.put("download.default_directory", downloadFilepath);
    prefs.put("download.prompt_for_download", false); // langsung download tanpa popup
    prefs.put("profile.default_content_settings.popups", 0);

    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", prefs);

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void quitDriver() {
    if (driver != null) {
      driver.quit();
    }
  }
}