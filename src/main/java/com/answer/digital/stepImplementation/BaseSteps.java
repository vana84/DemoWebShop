package com.answer.digital.stepImplementation;

import com.answer.digital.cucumber.TestContext;
import com.answer.digital.factory.PropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseSteps {
  Logger log = LogManager.getLogger(getClass());

  public static TestContext testContext;

  /** Launch Browser & Goto Landing Page & Goto Login Page */
  public void gotoLoginPage() {
    // Goto Landing Page
    testContext = new TestContext();
    testContext.driverFactory.getDriver().get(PropertyManager.getInstance().getPropValue("appUrl"));
    testContext.getPageFactory().getBasePage().setScenarioContext(testContext.getScenarioContext());

    // Goto Login Page
    testContext.getPageFactory().getLandingPage().loadHomePage();
  }

  /** Close Browser */
  public void closeBrowser() {
    testContext.driverFactory.closeDriver();
  }
}
