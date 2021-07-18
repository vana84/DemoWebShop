package com.answer.digital.pageObjects;

import com.answer.digital.cucumber.ScenarioContext;

public class BasePage {

  protected static ScenarioContext scenarioContext;

  public void setScenarioContext(ScenarioContext scenarioContext) {
    BasePage.scenarioContext = scenarioContext;
  }

  protected boolean isEmptyOrNullString(String str) {
    if (str == null) {
      return false;
    } else return str.equals("null") || str.isEmpty();
  }
}
