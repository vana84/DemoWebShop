package com.answer.digital.stepImplementation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class ShoppingSteps extends BaseSteps {
  Logger log = LogManager.getLogger(getClass());

  @When("user mouse hover on women menu")
  public void userMouseHoverOnWomenMenu() {
    log.info("User mouse hove on women menu");

    testContext.getPageFactory().getMyAccountPage().hoverWomenMenu();
  }

  @Then("sub navigation displayed")
  public void subNavigationDisplayed() {
    log.info("Verify sub navigation displayed");

    assertTrue(
        "Women Sub Navigation not displayed.",
        testContext.getPageFactory().getMyAccountPage().verifyWomenSubNavDisplayed());

    assertTrue(
        "Invalid sub navigation under women menu.",
        testContext.getPageFactory().getMyAccountPage().validateWomenSubMenuList());
  }

  @When("user select {string} option")
  public void userSelectSummerDressesOption(String subMenu) {
    log.info("Navigate to summer dresses page");

    testContext.getPageFactory().getMyAccountPage().gotoSummerDresses();

    assertTrue(
        "Redirected to invalid page.",
        testContext
            .driverFactory
            .getDriver()
            .getTitle()
            .equalsIgnoreCase("Summer Dresses - My Store"));
  }

  @Then("only {string} items displayed on search results")
  public void onlySummerItemsDisplayedOnSearchResults(String item) {
    log.info("Verify number of products and only summer products displayed");

    assertEquals(
        "Invalid number of products found on Summer Dresses page.",
        3,
        testContext.getPageFactory().getSummerDressesPage().getNumberOfProducts());

    assertTrue(
        "Invalid summer dresses name",
        testContext.getPageFactory().getSummerDressesPage().validateSummerDressesNames());
  }

  @And("user is on summer dresses page")
  public void userIsOnSummerDressesPage() {
    log.info("Goto Summer Dresses Page");

    testContext.getPageFactory().getMyAccountPage().gotoSummerDresses();

    assertTrue(
        "Invalid page title",
        testContext
            .driverFactory
            .getDriver()
            .getTitle()
            .equalsIgnoreCase("Summer Dresses - My Store"));
  }

  @When("user change maximum price to {int}")
  public void userChangeMaximumPriceTo(int maxPrice) {
    log.info("Filter product by changing max price range to under " + maxPrice);

    testContext.getPageFactory().getSummerDressesPage().changePriceRange(maxPrice);
  }

  @Then("products with price less {int} are visible")
  public void productsWithPriceLessAreVisible(int arg0) {}
  
  
  @Given("I add four different products to my wish list")
  public void addFourDifferentProducts() throws InterruptedException {
	 Thread.sleep(500);
	  testContext.getPageFactory().getHomePage().addWishlist();
  }

  @When("I view my wishlist table")
  public void viewMyWishlist() {
	  testContext.getPageFactory().getHomePage().moveToWishlistPage();
  }

  @Then("I find total four selected items in my wishlist")
  public void assertSelectedItemsInWishlist() throws InterruptedException {
	  Thread.sleep(1000);
	  int aa = testContext.getPageFactory().getHomePage().getATCLinksWishlist();
	  System.out.print(aa);
  }

  @When("I search for lowest price product")
  public void SearchLowestPriceProduct() throws ParseException {
	  	  testContext.getPageFactory().getHomePage().getPriceList();

  }

  @When("I am able to add the lowest price item to my cart")
  public void addLowestPriceItemtoCart() {
	  testContext.getPageFactory().getHomePage().selectLowestPriceItem();    
  }

  @Then("I am able to verify the item in my cart")
  public void verifyItemInCart() {
	  testContext.getPageFactory().getHomePage().verifyCart();
  }
}
