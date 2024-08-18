package org.example.TrendyolHomework.pages;

import org.example.TrendyolHomework.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    public WebElement cookieAccept;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement searchBox;

    @FindBy(xpath = "//i[@data-testid='search-icon']")
    public WebElement searchIcon;

    @FindBy(xpath = "//h1[contains(text(),'Elbise')]")
    public WebElement elbiseResultTitle;

    @FindBy(xpath = "//div[@class='dscrptn dscrptn-V2']")
    public WebElement searchResultCount;

    @FindBy(css = ".p-card-wrppr")
    public WebElement firstProduct;

    @FindBy(xpath = "//div[@class='sp-itm']")
    public WebElement elbiseBeden;

    @FindBy(xpath = "//*[@id=\"product-detail-app\"]/div/div[2]/div/div[2]/div[2]/div/div[1]/div[4]/button/div[1]")
    public WebElement addToCartButton;

    @FindBy(xpath = "//*[@id=\"account-navigation-container\"]/div/div[2]/div")
    public WebElement cartItemCount;
}
