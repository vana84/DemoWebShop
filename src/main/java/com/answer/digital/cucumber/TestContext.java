package com.answer.digital.cucumber;

import com.answer.digital.stepImplementation.BaseSteps;
import com.answer.digital.factory.DriverFactory;
import com.answer.digital.factory.PageFactory;
import com.answer.digital.utils.ScreenshotUtil;

public class TestContext {

  public DriverFactory driverFactory;
  BaseSteps baseSteps;
  ScenarioContext scenarioContext;
  PageFactory pageFactory;
  ScreenshotUtil screenshotUtil;

  public TestContext() {
    driverFactory = new DriverFactory();
    baseSteps = new BaseSteps();
    pageFactory = new PageFactory(driverFactory.getDriver(), driverFactory.getWait());
    scenarioContext = new ScenarioContext();
    screenshotUtil = new ScreenshotUtil(driverFactory.getDriver());
  }

  public ScenarioContext getScenarioContext() {
    return scenarioContext;
  }

  public PageFactory getPageFactory() {
    return pageFactory;
  }

  public ScreenshotUtil getScreenshotUtil() {
    return screenshotUtil;
  }
}
