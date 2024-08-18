package org.example.temmuz18_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifyTextBoxIsEnable {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void textBoxIsEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement textBox = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertFalse(textBox.isEnabled());

        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();

        WebElement itsEnabledMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(itsEnabledMessage.getText(), "It's enabled!");

        textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));
        Assert.assertTrue(textBox.isEnabled());
    }
}
