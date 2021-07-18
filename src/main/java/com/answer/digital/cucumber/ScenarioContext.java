package com.answer.digital.cucumber;

import com.answer.digital.enums.Data;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
  private final Map<String, Object> scenarioContext;

  public ScenarioContext() {
    scenarioContext = new HashMap<>();
  }

  public void setContext(Data key, Object value) {
    scenarioContext.put(key.toString(), value);
  }

  public Object getContext(Data key) {
    return scenarioContext.get(key.toString());
  }

  public Boolean isContains(Data key) {
    return scenarioContext.containsKey(key.toString());
  }
}
