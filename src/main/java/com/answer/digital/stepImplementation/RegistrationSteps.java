package com.answer.digital.stepImplementation;

import com.answer.digital.enums.Data;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationSteps extends BaseSteps {
  Logger log = LogManager.getLogger(getClass());

  public static Map<String, String> dataMap;

  @Given("user enter valid information")
  public void user_enter_valid_information(DataTable dataTable) {
    log.info("user enter valid information");

    // Set Data from First Row
    List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
    dataMap = dataRow.get(0);

    // Goto Login Page
    testContext.getPageFactory().getLandingPage().loadHomePage();

    // Enter Email
    testContext.getPageFactory().getLoginPage().enterEmailAndSubmit(dataMap);

    // Enter Registration Details
//    testContext.getPageFactory().getRegistrationPage().enterRegistrationDetails(dataMap);
  }

//  @When("user submit registration form")
//  public void user_submit_registration_form() {
//    log.info("User Submit registration form");
//    testContext.getPageFactory().getRegistrationPage().submitRegistrationForm();
//  }

  @Then("new account created successfully")
  public void new_account_created_successfully() {
    log.info("New account created successfully");

    String firstName = testContext.getScenarioContext().getContext(Data.FIRSTNAME).toString();
    String lastName = testContext.getScenarioContext().getContext(Data.LASTNAME).toString();

    assertEquals(
        "Invalid Account Name",
        firstName + " " + lastName,
        testContext.getPageFactory().getMyAccountPage().getAccountName());

    assertEquals(
        "Invalid Page Title",
        "My account - My Store",
        testContext.driverFactory.getDriver().getTitle());
  }

  @Given("user don't enter any information")
  public void userDontEnterAnyInformation() {
    log.info("user don't enter any information");
    // Goto Login Page
    testContext.getPageFactory().getLandingPage().loadHomePage();

    // Enter Email
    testContext.getPageFactory().getLoginPage().enterEmailAndSubmit(new HashMap<>());
  }

//  @When("user submit empty registration form")
//  public void userSubmitEmptyRegistrationForm() {
//    log.info("Submit registration page with empty form");
//    testContext.getPageFactory().getRegistrationPage().submitEmptyRegistrationForm();
//  }

//  @Then("registration failed with error message")
//  public void registrationFailedWithErrorMessage() {
//    log.info("Registration failed : Validate Error");
//
//    assertEquals(
//        "Invalid Page Title", "Login - My Store", testContext.driverFactory.getDriver().getTitle());
//
//    assertTrue(
//        "Error message not displayed",
//        testContext.getPageFactory().getRegistrationPage().validateErrorMessageDisplayed());
//  }
}
