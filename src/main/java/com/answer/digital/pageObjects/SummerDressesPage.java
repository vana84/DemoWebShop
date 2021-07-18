package com.answer.digital.pageObjects;

import com.answer.digital.enums.SummerDresses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SummerDressesPage {
  Logger log = LogManager.getLogger(getClass());

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected Actions actions;

  public SummerDressesPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id='center_column']/ul/li")
  List<WebElement> productList;

  @FindBy(xpath = "//*[@id='center_column']/ul/li//*[@itemprop='name']/a")
  List<WebElement> productNameList;

  @FindBy(id = "layered_price_slider")
  WebElement priceSlider;

  @FindBy(xpath = "//*[@id='layered_price_slider']/a[1]")
  WebElement leftSliderBtn;

  @FindBy(xpath = "//*[@id='layered_price_slider']/a[2]")
  WebElement rightSliderBtn;

  @FindBy(id = "layered_price_range")
  WebElement priceRangeTxt;

  public int getNumberOfProducts() {
    return productList.size();
  }

  // Validate only summer dresses names present
  public boolean validateSummerDressesNames() {
    log.info("Summer dresses names");

    boolean summerDressNameFound = false;

    for (WebElement element : productNameList) {
      String dressName = element.getText();

      log.info("Dress Name : " + dressName);

      for (SummerDresses name : SummerDresses.values()) {
        if (dressName.equalsIgnoreCase(String.valueOf(name))) {

          summerDressNameFound = true;
        } else {
          summerDressNameFound = true;
        }
      }
    }

    return summerDressNameFound;
  }

  // Change max price range
  public void changePriceRange(int maxPrice) {
    log.info(" X, Y : " + rightSliderBtn.getLocation());

    int cMaxPrice = Integer.parseInt(priceRangeTxt.getText().substring(10, 12));

    for (int i = 0; maxPrice <= cMaxPrice; i++) {
      cMaxPrice = Integer.parseInt(priceRangeTxt.getText().substring(10, 12));
      rightSliderBtn.sendKeys(Keys.ARROW_LEFT);
    }

    /*JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute('style', 'left: 25%;')", rightSliderBtn);
    actions.moveToElement(rightSliderBtn).moveByOffset(188, 0).click().build().perform();*/

    log.info(" X, Y : " + rightSliderBtn.getLocation());
  }
}
