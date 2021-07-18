package com.answer.digital.stepImplementation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertEquals;

public class OurStoresSteps extends BaseSteps {
  Logger log = LogManager.getLogger(getClass());

  @When("user scroll on map on Our Stores")
  public void userScrollOnMapOnOurStores() {
    log.info("Goto Our Stores Page");

    testContext.getPageFactory().getMyAccountPage().gotoOurStoresPage();

    assertEquals(
        "Invalid page title.",
        "Stores - My Store",
        testContext.driverFactory.getDriver().getTitle());
  }

  @Then("user can see West Palm Beach")
  public void userCanSeeWestPalmBeach() {

    testContext.getPageFactory().getOurStoresPage().scrollOnMap();
  }

  @And("Take screenshot of page")
  public void takeScreenshotOfPage() {
    testContext.getScreenshotUtil().takeScreenshot();
  }
}
