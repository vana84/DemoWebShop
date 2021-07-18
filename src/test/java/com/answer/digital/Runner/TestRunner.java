package com.answer.digital.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"com.answer.digital.stepImplementation"},
    tags = "@WTT",
    monochrome = true,
    plugin = {
      "pretty",
      "json:target/jsonReport/cucumber.json",
      "html:target/htmlReport/cucumber-reports.html"
    })
public class TestRunner {}
