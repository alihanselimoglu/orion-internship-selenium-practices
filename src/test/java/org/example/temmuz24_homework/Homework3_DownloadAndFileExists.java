package org.example.temmuz24_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class Homework3_DownloadAndFileExists {

    WebDriver driver;

    @Test
    public void downloadTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/download");

        WebElement downloadLink = driver.findElement(By.linkText("text.txt"));
        downloadLink.click();

        Thread.sleep(5000);

        Assert.assertTrue(isExist("text.txt"));

        driver.quit();
    }

    public boolean isExist(String fileName) {
        String downloadDir = System.getProperty("user.home") + "/Downloads/";

        File file = new File(downloadDir + fileName);

        return file.exists();
    }
}
