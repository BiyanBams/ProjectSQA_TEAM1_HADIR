package com.juaracoding.hadir.definitions.laporanlembur;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.juaracoding.hadir.utils.DriverUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {
    @Before
    public static void setup() {
        DriverUtil.openDriver();
        DriverUtil.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Buka URL aplikasi langsung di awal
        String baseUrl = "https://magang.dikahadir.com/authentication/login"; // <-- ganti sesuai URL project kamu
        DriverUtil.getDriver().get(baseUrl);
    }

    @After
    public void teardown(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) DriverUtil.getDriver())
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

        // Delay sebelum menutup driver
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DriverUtil.quitDriver();
    }
}