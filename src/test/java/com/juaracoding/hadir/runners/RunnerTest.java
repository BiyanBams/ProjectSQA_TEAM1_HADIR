package com.juaracoding.hadir.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/LaporanKoreksi.feature", 
    glue = {"com.juaracoding.hadir.definitions.laporanfeature",
            "com.juaracoding.hadir.definitions.cummonsteps",
            "com.juaracoding.hadir.definitions.hooks"
},

        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-reporting/reports.json",
                "json:target/cucumber.json",
                "pretty",
                "html:target/cucumber-reporting/reports.html"
        })
public class RunnerTest extends AbstractTestNGCucumberTests {

}
