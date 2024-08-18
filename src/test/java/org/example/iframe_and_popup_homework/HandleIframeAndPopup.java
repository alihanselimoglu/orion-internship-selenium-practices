package org.example.iframe_and_popup_homework;

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

public class HandleIframeAndPopup {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://webdriveruniversity.com/IFrame/index.html");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void handleIframeAndPopup() {
        WebElement iframe = driver.findElement(By.id("frame"));
        driver.switchTo().frame(iframe);

        WebElement ourProductsButton = driver.findElement(By.xpath("//a[text()='Our Products']"));
        ourProductsButton.click();

        WebElement desktopSystemsLink = driver.findElement(By.xpath("//p[text()='Desktop Systems']"));
        desktopSystemsLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

        String modalMessage = modalDialog.getText();
        System.out.println("Modal message: " + modalMessage);

        WebElement closeButton = modalDialog.findElement(By.xpath("//button[text()='Close']"));
        closeButton.click();

        driver.switchTo().defaultContent();

        WebElement topLink = driver.findElement(By.xpath("//a[contains(text(),'WebdriverUniversity.com (IFrame)')]"));
        topLink.click();

        String expectedUrl = "https://webdriveruniversity.com/index.html";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
}
