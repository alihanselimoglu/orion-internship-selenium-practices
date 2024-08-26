package org.example.agustos26_homework.utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Hooks {
    WebDriver driver = Driver.getDriver();

    @Before
    public void setUp() {
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("target/screenshots/" + scenario.getName() + ".png");
            try {
                FileUtils.copyFile(srcFile, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Driver.closeDriver();
    }
}
