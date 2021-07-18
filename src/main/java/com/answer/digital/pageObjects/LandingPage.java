package com.answer.digital.pageObjects;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.answer.digital.cucumber.TestContext;


public class LandingPage extends BasePage {
  Logger log = LogManager.getLogger(getClass());
  protected WebDriver driver;
  protected WebDriverWait wait;
  public static TestContext testContext;

  public LandingPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }


  /** Goto login Page */
  public void loadHomePage() {
	  System.out.println("Navigating to Home Page Method");
	  System.out.println(driver.getTitle());
	  assertEquals(
		        "Invalid page title.",
		        "TESTSCRIPTDEMO – Automation Practice",
		        driver.getTitle());
  }
}
