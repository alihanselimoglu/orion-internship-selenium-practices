package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TrendyolTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://trendyol.com");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".account-user"))).click();

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-email")));
        WebElement password = driver.findElement(By.id("login-password-input"));
        WebElement loginButton = driver.findElement(By.cssSelector(".q-primary.q-fluid.q-button-medium.q-button.submit"));

        email.sendKeys("validmail@gmail.com");
        password.sendKeys("validpassword");
        loginButton.click();

        WebElement accountButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".account-user")));
        Assert.assertTrue(accountButton.isDisplayed());
    }

    @Test
    public void testInvalidUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".account-user"))).click();

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-email")));
        WebElement password = driver.findElement(By.id("login-password-input"));
        WebElement loginButton = driver.findElement(By.cssSelector(".q-primary.q-fluid.q-button-medium.q-button.submit"));

        email.sendKeys("invalid_email@gmail.com");
        password.sendKeys("invalidPassword");
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".message")));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "E-posta adresiniz ve/veya şifreniz hatalı.");
    }

    @Test
    public void testSearchElbiseItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sfx-discovery-search-suggestions\"]/div/div[1]/input")));
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"sfx-discovery-search-suggestions\"]/div/div/i"));

        searchBox.sendKeys("Elbise");
        searchIcon.click();

        WebElement searchResults = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("prdct-cntnr-wrppr")));
        Assert.assertTrue(searchResults.isDisplayed());
    }

    @Test
    public void testNumberOfElbiseResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();


        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sfx-discovery-search-suggestions\"]/div/div[1]/input")));
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"sfx-discovery-search-suggestions\"]/div/div/i"));
        searchBox.sendKeys("Elbise");
        searchIcon.click();

        WebElement searchResults = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("prdct-cntnr-wrppr")));
        int itemCount = searchResults.findElements(By.cssSelector(".p-card-wrppr")).size();
        System.out.println(itemCount);
        Assert.assertTrue(itemCount > 0);
    }

    @Test
    public void testAddElbiseItemToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sfx-discovery-search-suggestions\"]/div/div[1]/input")));
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id=\"sfx-discovery-search-suggestions\"]/div/div/i"));
        searchBox.sendKeys("Elbise");
        searchIcon.click();

        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-card-wrppr")));
        firstItem.click();

        Thread.sleep(1000);

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Thread.sleep(2000);

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"product-detail-app\"]/div/div[2]/div/div[2]/div[2]/div/div[1]/div[4]/button/div[1]")));
        addToCartButton.click();

        WebElement cartItemCount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"account-navigation-container\"]/div/div[2]/div")));
        Assert.assertTrue(cartItemCount.isDisplayed());
    }
}
