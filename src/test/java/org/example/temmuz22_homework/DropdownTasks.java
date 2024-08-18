package org.example.temmuz22_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DropdownTasks {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://pynishant.github.io/dropdown-selenium-python-select.html");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void selectJavaFromCustomSelect() {
        WebElement dropdown = driver.findElement(By.className("select-selected"));
        dropdown.click();

        WebElement javaOption = driver.findElement(By.xpath("/html/body/div/div[2]/div[3]"));
        javaOption.click();
    }


    @Test
    public void selectPythonFromLangDropdown() {
        driver.get("https://pynishant.github.io/dropdown-selenium-python-select.html");

        WebElement dropdown = driver.findElement(By.id("lang"));
        dropdown.click();

        WebElement pythonOption = driver.findElement(By.xpath("//select[@id='lang']/option[text()='Python']"));
        pythonOption.click();

        WebElement submitButton = driver.findElement(By.xpath("/html/body/form[1]/input"));
        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://pynishant.github.io/dropdown-selenium-python-select.html?lang=c%23";
        Assert.assertEquals(currentUrl, expectedUrl);
    }


    @Test
    public void selectMultipleLanguagesFromLang2Dropdown() {
        driver.get("https://pynishant.github.io/dropdown-selenium-python-select.html");

        WebElement dropdown = driver.findElement(By.id("lang2"));
        dropdown.click();

        WebElement phpOption = driver.findElement(By.xpath("//select[@id='lang2']/option[text()='PHP']"));
        phpOption.click();

        WebElement pythonOption = driver.findElement(By.xpath("//select[@id='lang2']/option[text()='Python']"));
        pythonOption.click();

        WebElement cSharpOption = driver.findElement(By.xpath("//select[@id='lang2']/option[text()='C#']"));
        cSharpOption.click();

        WebElement submitButton = driver.findElement(By.xpath("/html/body/form[2]/input"));
        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://pynishant.github.io/dropdown-selenium-python-select.html?lang2=php&lang2=c%23";
        Assert.assertEquals(currentUrl, expectedUrl);
    }

}
