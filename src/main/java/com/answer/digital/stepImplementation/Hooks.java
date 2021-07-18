package com.answer.digital.stepImplementation;

import com.answer.digital.factory.PropertyManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Hooks extends BaseSteps {
  Logger log = LogManager.getLogger(getClass());


  @Before
  public void setup() {
    log.info("************************* Test Execution Started *************************");
    log.info("Setup : Driver initialization & setup");
    gotoLoginPage();
  }

  @AfterStep
  public void toDoSomething() {
    if (PropertyManager.getInstance().getPropValue("captureScreen").equalsIgnoreCase("Yes"))
      testContext.getScreenshotUtil().takeScreenshot();
  }

  @After
  public void cleanup() {
    log.info("Cleanup : Driver closer");
//    testContext.getPageFactory().getMyAccountPage().doSignOut();
    closeBrowser();
    log.info("************************* Test Execution Completed *************************");
  }
}
