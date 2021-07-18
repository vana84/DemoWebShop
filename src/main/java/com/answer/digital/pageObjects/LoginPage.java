package com.answer.digital.pageObjects;

import com.answer.digital.enums.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class LoginPage extends BasePage {
  Logger log = LogManager.getLogger(getClass());
  protected WebDriver driver;
  protected WebDriverWait wait;

  public LoginPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "email_create")
  WebElement createEmailTxtBox;

  @FindBy(id = "SubmitCreate")
  WebElement createAccountBtn;

  @FindBy(id = "email")
  WebElement emailTxtBox;

  @FindBy(id = "passwd")
  WebElement passwordTxtBox;

  @FindBy(id = "SubmitLogin")
  WebElement signInBtn;

  /**
   * Enter new mail and submit
   *
   * @param dataMap Data from Feature File
   */
  public void enterEmailAndSubmit(Map<String, String> dataMap) {
    String email = dataMap.getOrDefault("email", dataGenerator.getEmail());
    scenarioContext.setContext(Data.EMAIL, email);

    log.info("Email for registration : " + email);

    createEmailTxtBox.sendKeys(email);
    createAccountBtn.click();
  }

  public void doLogin(String email, String password) {
    emailTxtBox.sendKeys(email);
    passwordTxtBox.sendKeys(password);

    signInBtn.click();
  }
}
