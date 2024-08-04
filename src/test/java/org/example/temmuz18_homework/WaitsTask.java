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

public class WaitsTask {
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
    public void testImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();

        WebElement itsGoneMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(itsGoneMessage.getText(), "It's gone!");

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        addButton.click();

        WebElement itsBackMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(itsBackMessage.getText(), "It's back!");
    }

    @Test
    public void testExplicitWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Remove']")));
        removeButton.click();

        WebElement itsGoneMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(itsGoneMessage.getText(), "It's gone!");


        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        addButton.click();

        WebElement itsBackMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(itsBackMessage.getText(), "It's back!");
    }

}
