package com.juaracoding.hadir.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/resources/features/LaporanSakit.feature",
        glue = {"com.juaracoding.hadir.definitions.laporansakit",
                "com.juaracoding.hadir.definitions.cummonsteps"
        },

        plugin = {
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
    "json:target/cucumber-reporting/reports.json",
    "json:target/cucumber.json", 
    "pretty", 
    "html:target/cucumber-reporting/reports.html"
})
public class RunnerLaporanSakitTest extends AbstractTestNGCucumberTests {
   
}
