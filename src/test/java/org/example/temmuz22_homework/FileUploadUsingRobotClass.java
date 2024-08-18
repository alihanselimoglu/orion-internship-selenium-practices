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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.nio.file.Paths;

public class FileUploadUsingRobotClass {
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
    public void uploadFileUsingRobotClass() throws AWTException {
        WebElement chooseFileButton = driver.findElement(By.id("myFile"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chooseFileButton);

        Robot robot = new Robot();

        String filePath = Paths.get("src/example_files/Screenshot 2024-07-22 190219.png").toAbsolutePath().toString();
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement submitButton = driver.findElement(By.id("submit-button"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Your file has now been uploaded!");

        alert.accept();
    }

}
