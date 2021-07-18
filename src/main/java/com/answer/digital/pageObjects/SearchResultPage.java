package com.answer.digital.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage {
  Logger log = LogManager.getLogger(getClass());

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected Actions actions;

  public SearchResultPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//a[@title='Add to cart']")
  WebElement addToCartBtn;

  @FindBy(xpath = "//a[@title='Add to cart']")
  List<WebElement> addToCartList;

  @FindBy(xpath = "//*[@title='Continue shopping']")
  WebElement continueShoppingBtn;

  @FindBy(xpath = "//*[contains(text(), 'item in your cart')]")
  WebElement itemAddedAlert;

  @FindBy(xpath = "//*[@class='left-block']")
  WebElement popupBox;

  @FindBy(xpath = "//*[@title='View my shopping cart']")
  WebElement shoppingCartBtn;

  // Add one item in cart
  public void addItemInCart() {
    log.info("Add item into shopping cart");
    actions.moveToElement(popupBox).perform();
    wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
    addToCartBtn.click();
  }

  // Verify Item Added In Cart
  public boolean verifyItemAddedInCart() {
    log.info("Verify item is added into cart");
    return itemAddedAlert.isEnabled();
  }

  // Close Shopping Cart PopUp
  public void closeCartPopUp() {
    log.info("Close Item added layer popup");
    continueShoppingBtn.click();
  }

  // Goto Shopping Cart
  public void gotoShoppingCart() {
    log.info("Goto shopping cart");
    shoppingCartBtn.click();
  }
}
