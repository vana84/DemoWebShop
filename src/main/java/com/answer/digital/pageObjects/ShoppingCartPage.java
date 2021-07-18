package com.answer.digital.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShoppingCartPage {
  Logger log = LogManager.getLogger(getClass());

  protected WebDriver driver;
  protected WebDriverWait wait;

  public ShoppingCartPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "cart_summary")
  WebElement cartSummaryTable;

  @FindBy(xpath = "//a[@title='Delete']")
  List<WebElement> deleteItemList;

  @FindBy(xpath = "//*[@class = 'alert alert-warning']")
  WebElement emptyCartMsg;

  // Check cart is empty or not
  public boolean verifyCartIsNotEmpty() {
    log.info("Cart is not empty");
    return cartSummaryTable.isDisplayed();
  }

  // Remove all items from shopping cart
  public void removeAllItemsFromCart() {
    for (WebElement item : deleteItemList) {
      wait.until(ExpectedConditions.elementToBeClickable(item));
      item.click();
    }
  }

  // Wait for message visibility and return Message Text
  public String getEmptyCartBannerMessage() {
    wait.until(ExpectedConditions.visibilityOf(emptyCartMsg));
    return emptyCartMsg.getText().trim();
  }

}
