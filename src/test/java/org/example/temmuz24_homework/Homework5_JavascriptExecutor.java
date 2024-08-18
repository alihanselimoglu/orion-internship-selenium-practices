package org.example.temmuz24_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework5_JavascriptExecutor {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void javascriptExecutorTest() {
        driver.get("https://www.selenium.dev/");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        WebElement aboutSelenium = driver.findElement(By.linkText("About Selenium"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", aboutSelenium);

        jsExecutor.executeScript("arguments[0].click();", aboutSelenium);
    }
}
