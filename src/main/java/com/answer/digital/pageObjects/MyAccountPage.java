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

import static com.answer.digital.enums.Data.ACCOUNT_NAME;

public class MyAccountPage extends BasePage {
  Logger log = LogManager.getLogger(getClass());

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected Actions actions;

  public MyAccountPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@title='View my customer account']/span")
  WebElement accountNameTxt;

  @FindBy(id = "search_query_top")
  WebElement searchTxtBox;

  @FindBy(name = "submit_search")
  WebElement searchBtn;

  @FindBy(xpath = "//*[@title='Log me out']")
  WebElement signOutBtn;

  @FindBy(xpath = "//a[@title='Women']")
  WebElement womenMenu;

  @FindBy(xpath = "//*[@title='Women']/following-sibling::ul")
  WebElement womenSubMenuContainer;

  @FindBy(xpath = "//*[@title='Tops']")
  WebElement topsSubMenu;

  @FindBy(xpath = "//*[@title='Dresses']")
  WebElement dressesSubMenu;

  @FindBy(xpath = "//*[@title='Summer Dresses']")
  WebElement summerDressesMenu;

  @FindBy(xpath = "//*[@title='Our stores']")
  WebElement ourStoresPageLink;

  // Get Account Name
  public String getAccountName() {
    String accountName = accountNameTxt.getText();
    scenarioContext.setContext(ACCOUNT_NAME, accountName);
    return accountName;
  }

  // Search Item
  public void searchItem(String itemName) {
    log.info("Search item : " + itemName);

    searchTxtBox.clear();
    searchTxtBox.sendKeys(itemName);
    searchBtn.click();
  }

  // Do Sign Out
  public void doSignOut() {
    if (!driver.getTitle().equalsIgnoreCase("Login - My Store")) signOutBtn.click();
  }

  // Mouse-Hover Women Menu
  public void hoverWomenMenu() {
    log.info("Hover Women Menu");

    actions.moveToElement(womenMenu).perform();
  }

  // Verify Sub Navigation Container Displayed
  public boolean verifyWomenSubNavDisplayed() {
    return womenSubMenuContainer.isDisplayed();
  }

  // Verify Sub Menu (Tops & Dresses) displayed
  public boolean validateWomenSubMenuList() {
    return topsSubMenu.isDisplayed() && dressesSubMenu.isDisplayed();
  }

  // Goto Summer Dresses
  public void gotoSummerDresses() {
    hoverWomenMenu();
    summerDressesMenu.click();
  }

  // Goto Our Stores Page
  public void gotoOurStoresPage() {
    ourStoresPageLink.click();
    wait.until(ExpectedConditions.titleIs("Stores - My Store"));
  }
}
