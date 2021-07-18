package com.answer.digital.cucumber;


import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
  private final Map<String, Object> scenarioContext;

  public ScenarioContext() {
    scenarioContext = new HashMap<>();
  }
}
