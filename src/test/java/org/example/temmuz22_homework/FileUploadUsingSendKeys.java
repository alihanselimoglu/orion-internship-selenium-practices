package org.example.temmuz22_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.nio.file.Paths;

public class FileUploadUsingSendKeys {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://webdriveruniversity.com/File-Upload/index.html");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void uploadFileUsingSendKeys() {
        WebElement chooseFileButton = driver.findElement(By.id("myFile"));
        String filePath = Paths.get("src/example_files/Screenshot 2024-07-22 190219.png").toAbsolutePath().toString();
        chooseFileButton.sendKeys(filePath);

        WebElement submitButton = driver.findElement(By.id("submit-button"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Your file has now been uploaded!");

        alert.accept();
    }
}
