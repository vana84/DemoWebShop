package com.answer.digital.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OurStoresPage extends BasePage {
  Logger log = LogManager.getLogger(getClass());
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected Actions actions;

  public OurStoresPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@class = 'dismissButton']")
  WebElement alertBtn;

  @FindBy(id = "map")
  WebElement map;

  @FindBy(xpath = "//*[@id='map']/div/div/div[1]/div[3]")
  WebElement map2;

  @FindBy(xpath = "//*[@title = 'Zoom in']")
  WebElement zoomInBnt;

  @FindBy(xpath = "//*[@title = 'Zoom out']")
  WebElement zoomOutBnt;

  @FindBy(xpath = "//*[@id='map']/div/div/div[1]/div[3]/div/div[3]/div[2]/img")
  WebElement fortStore;

  public void scrollOnMap() {
    log.info("Scroll on map.");

    alertBtn.click();
//    zoomOutBnt.click();

    actions.clickAndHold(map2);

    for (int i = 0; i <= 16; i++) {
      actions.dragAndDropBy(map2, 0, 100).perform();
    }
    actions.release().build();

//    zoomInBnt.click();
  }
}
