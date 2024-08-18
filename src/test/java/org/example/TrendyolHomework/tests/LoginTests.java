package org.example.TrendyolHomework.tests;

import org.example.TrendyolHomework.pages.LoginPage;
import org.example.TrendyolHomework.utilities.ConfigReader;
import org.example.TrendyolHomework.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        loginPage.cookieAccept.click();
        loginPage.loginButtonHeader.click();
        loginPage.usernameInput.sendKeys(ConfigReader.getProperty("validUsername"));
        loginPage.passwordInput.sendKeys(ConfigReader.getProperty("validPassword"));
        loginPage.loginButton.click();

        Thread.sleep(3000);

        String textAfterLogin = loginPage.loginButtonHeader.getText();
        String expectedTextAfterLogin = "Hesabım";
        Assert.assertEquals(textAfterLogin, expectedTextAfterLogin);
    }

    @Test
    public void invalidUsernameTest() {
        loginPage.cookieAccept.click();
        loginPage.loginButtonHeader.click();
        loginPage.usernameInput.sendKeys(ConfigReader.getProperty("invalidUsername"));
        loginPage.passwordInput.sendKeys(ConfigReader.getProperty("validPassword"));
        loginPage.loginButton.click();
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
    }
}
