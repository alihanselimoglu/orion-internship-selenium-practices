package org.example;

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

public class LoginTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod
    public void tearDown() {
            driver.quit();
    }

    @Test
    public void testSuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement username =  wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys("student");
        password.sendKeys("Password123");
        submitButton.click();

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Log out']")));
        Assert.assertTrue(logoutButton.isDisplayed());
    }

    @Test
    public void testInvalidUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys("invalidUsername");
        password.sendKeys("Password123");
        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your username is invalid!");
    }

    @Test
    public void testInvalidPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys("student");
        password.sendKeys("invalidPassword");
        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("error")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Your password is invalid!");
    }

    @Test
    public void testPageTitleOnLoginPage() {
        String expectedTitle = "Test Login | Practice Test Automation";
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @Test
    public void testPageTitleAfterLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys("student");
        password.sendKeys("Password123");
        submitButton.click();

        String expectedTitle = "Logged In Successfully | Practice Test Automation";
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @Test
    public void testLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys("student");
        password.sendKeys("Password123");
        submitButton.click();

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Log out']")));
        logoutButton.click();

        Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).isDisplayed());
    }

}
