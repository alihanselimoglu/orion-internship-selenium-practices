package org.example.TrendyolHomework.pages;

import org.example.TrendyolHomework.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    public WebElement cookieAccept;

    @FindBy(css = ".account-user")
    public WebElement loginButtonHeader;

    @FindBy(id = "login-email")
    public WebElement usernameInput;

    @FindBy(id = "login-password-input")
    public WebElement passwordInput;

    @FindBy(css = ".q-primary.q-fluid.q-button-medium.q-button.submit")
    public WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'E-posta adresiniz ve/veya şifreniz hatalı.')]")
    public WebElement errorMessage;
}
