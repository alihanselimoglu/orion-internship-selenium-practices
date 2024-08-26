package org.example.TrendyolHomework.tests;

import org.example.TrendyolHomework.pages.HomePage;
import org.example.TrendyolHomework.utilities.ConfigReader;
import org.example.TrendyolHomework.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

public class SearchTests {
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        homePage = new HomePage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void searchForElbiseTest() {
        homePage.cookieAccept.click();
        homePage.searchBox.sendKeys("Elbise");
        homePage.searchIcon.click();
        Assert.assertTrue(homePage.searchResultTitle.isDisplayed());
    }

    @Test
    public void verifyNumberOfResultsTest() {
        homePage.cookieAccept.click();
        homePage.searchBox.sendKeys("Elbise");
        homePage.searchIcon.click();

        String searchResultCount = homePage.searchResultCount.getText();
        assertTrue(searchResultCount.contains("100.000+"));
    }

    @Test
    public void addItemToCartTest() throws InterruptedException {
        homePage.cookieAccept.click();
        homePage.searchBox.sendKeys("Elbise");
        homePage.searchIcon.click();
        homePage.firstProduct.click();
        Thread.sleep(1000);

        ArrayList<String> tabs = new ArrayList<String> (Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(1));
        Thread.sleep(2000);

        homePage.anladimButton.click();
        homePage.elbiseBeden.click();
        homePage.addToCartButton.click();

        homePage.cartItemCount.isDisplayed();
    }
}
