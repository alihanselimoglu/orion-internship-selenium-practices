package org.example.agustos26_homework.steps;

import io.cucumber.java.en.*;
import org.example.TrendyolHomework.pages.HomePage;
import org.example.TrendyolHomework.utilities.Driver;
import org.junit.Assert;

import java.util.ArrayList;

public class SearchSteps {
    HomePage homePage = new HomePage();

    @When("User searches for {string}")
    public void the_user_searches_for(String item) {
        homePage.searchBox.sendKeys(item);
        homePage.searchIcon.click();
    }

    @Then("User should see the search results for {string}")
    public void the_user_should_see_the_search_results_for(String item) {
        Assert.assertTrue(homePage.searchResultTitle.isDisplayed());
    }

    @Then("User should see the search result count contains {string}")
    public void the_user_should_see_the_search_result_count_contains(String count) {
        String searchResultCount = homePage.searchResultCount.getText();
        Assert.assertTrue(searchResultCount.contains(count));
    }

    @When("User selects the first product")
    public void the_user_selects_the_first_product() throws InterruptedException {
        homePage.firstProduct.click();
        Thread.sleep(1000);

        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(1));
        Thread.sleep(2000);
    }

    @When("User adds the item to the cart")
    public void the_user_adds_the_item_to_the_cart() {
        homePage.anladimButton.click();
        homePage.addToCartButton.click();
    }

    @Then("User should see the item in the cart")
    public void the_user_should_see_the_item_in_the_cart() {
        Assert.assertTrue(homePage.cartItemCount.isDisplayed());
    }

    @Then("User should see items in the cart")
    public void the_user_should_see_items_in_the_cart() {
        String actualItemCount = homePage.cartItemCount.getText();
        Assert.assertEquals(String.valueOf(1), actualItemCount);
    }
}
