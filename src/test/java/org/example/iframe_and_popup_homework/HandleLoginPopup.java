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
import java.util.ArrayList;

public class HandleLoginPopup {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://webdriveruniversity.com/");
    }

    @AfterMethod
    public void teardown() {
            driver.quit();
    }

    @Test
    public void handleLoginPopup() {
        WebElement loginPortalLink = driver.findElement(By.id("login-portal"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginPortalLink);

        loginPortalLink.click();

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement usernameField = driver.findElement(By.id("text"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("testuser");
        passwordField.sendKeys("testpassword");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "validation failed");

        alert.accept();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        String expectedUrl = "https://webdriveruniversity.com/";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
}
