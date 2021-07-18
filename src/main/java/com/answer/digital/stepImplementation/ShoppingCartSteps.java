package com.answer.digital.stepImplementation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartSteps extends BaseSteps {
  Logger log = LogManager.getLogger(getClass());

  @Given("user have an item in shopping cart")
  public void userHaveAnItemInShoppingCart(DataTable dataTable) {
    // Search item
    List<List<String>> dataRow = dataTable.asLists(String.class);

    // Add item into cart and validate
    for (String item : dataRow.get(0)) {
      log.info("Search and add : " + item + " in shopping cart.");

      testContext.getPageFactory().getMyAccountPage().searchItem(item);
      testContext.getPageFactory().getSearchResultPage().addItemInCart();

      assertTrue(
          "Items not added into cart",
          testContext.getPageFactory().getSearchResultPage().verifyItemAddedInCart());

      testContext.getPageFactory().getSearchResultPage().closeCartPopUp();
    }
    testContext.getPageFactory().getSearchResultPage().gotoShoppingCart();
  }

  @When("user delete all the items from shopping cart")
  public void userDeleteAllTheItemsFromShoppingCart(DataTable dataTable) {
    log.info("Check if items are present in shopping cart.");

    // First check that cart is not empty
    assertTrue(
        "Cart is Empty", testContext.getPageFactory().getShoppingCartPage().verifyCartIsNotEmpty());

    // Verify delete button present. If yes then delete all item
    testContext.getPageFactory().getShoppingCartPage().removeAllItemsFromCart();
  }

  @Then("shopping cart should be empty")
  public void shoppingCartShouldBeEmpty() {
    log.info("Verify shopping cart is empty.");

    assertEquals(
        "Invalid Banner Message.",
        "Your shopping cart is empty.",
        testContext.getPageFactory().getShoppingCartPage().getEmptyCartBannerMessage());
  }
}
