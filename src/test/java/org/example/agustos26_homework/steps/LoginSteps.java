package org.example.agustos26_homework.steps;

import io.cucumber.java.en.*;
import org.example.TrendyolHomework.pages.LoginPage;
import org.example.TrendyolHomework.utilities.ConfigReader;
import org.example.TrendyolHomework.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();

    @Given("User is on the Trendyol homepage")
    public void user_is_on_the_trendyol_homepage() {
        driver.get(ConfigReader.getProperty("url"));
    }

    @When("User accepts the cookies")
    public void user_accepts_the_cookies() {
        loginPage.cookieAccept.click();
    }

    @When("User clicks on the login button on the header")
    public void user_clicks_on_the_login_button_on_the_header() {
        loginPage.loginButtonHeader.click();
    }

    @When("User enters valid username")
    public void user_enters_valid_username() {
        loginPage.usernameInput.sendKeys(ConfigReader.getProperty("validUsername"));
    }

    @When("User enters valid password")
    public void user_enters_valid_password() {
        loginPage.passwordInput.sendKeys(ConfigReader.getProperty("validPassword"));
    }

    @When("User enters invalid username")
    public void user_enters_invalid_username() {
        loginPage.usernameInput.sendKeys(ConfigReader.getProperty("invalidUsername"));
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.loginButton.click();
    }

    @Then("User should see the {string} text after login")
    public void user_should_see_the_text_after_login(String expectedTextAfterLogin) throws InterruptedException {
        Thread.sleep(3000);
        String textAfterLogin = loginPage.loginButtonHeader.getText();
        Assert.assertEquals(expectedTextAfterLogin, textAfterLogin);
    }

    @Then("User should see an error message")
    public void user_should_see_an_error_message() {
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
    }
}
