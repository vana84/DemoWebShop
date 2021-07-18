package com.answer.digital.stepImplementation;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;


public class ShoppingSteps extends BaseSteps {
  Logger log = LogManager.getLogger(getClass());


 

  @Then("products with price less {int} are visible")
  public void productsWithPriceLessAreVisible(int arg0) {}
  
  
  @Given("I add four different products to my wish list")
  public void addFourDifferentProducts() {
	  testContext.getPageFactory().getHomePage().addWishlist();
  }

  @When("I view my wishlist table")
  public void viewMyWishlist() {
	  testContext.getPageFactory().getHomePage().moveToWishlistPage();
  }

  @Then("I find total four selected items in my wishlist")
  public void assertSelectedItemsInWishlist() {
	  int ItemsWished = testContext.getPageFactory().getHomePage().getATCLinksWishlist();
	  assertEquals(
			  "Wrong wish count",
			  4,
			  ItemsWished);
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
