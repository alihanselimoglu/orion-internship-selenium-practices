package org.example.temmuz24_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Homework2_KeyboardActions {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://html.com/tags/iframe/");
    }

    @AfterMethod
    public void teardown() {
            driver.quit();
    }

    @Test
    public void testPlayYouTubeVideo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement youtubeIframe = driver.findElement(By.cssSelector("iframe[src*='youtube.com']"));
        js.executeScript("arguments[0].scrollIntoView(true);", youtubeIframe);

        driver.switchTo().frame(youtubeIframe);

        WebElement playButton = driver.findElement(By.cssSelector("button.ytp-large-play-button"));
        playButton.click();

        wait.until(ExpectedConditions.invisibilityOf(playButton));

        WebElement pauseButton = driver.findElement(By.cssSelector("button.ytp-play-button"));
        String ariaLabel = pauseButton.getAttribute("aria-label");
        Assert.assertEquals(ariaLabel, "Duraklat klavye kısayolu k");

        driver.switchTo().defaultContent();
    }
}
