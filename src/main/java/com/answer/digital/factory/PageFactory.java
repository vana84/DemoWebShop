package com.answer.digital.factory;

import com.answer.digital.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactory {
  private final WebDriver driver;
  private final WebDriverWait wait;

  public BasePage basePage;
  public LandingPage landingPage;
  public LoginPage loginPage;
  public HomePage homePage;
//  private RegistrationPage registrationPage;
  private MyAccountPage myAccountPage;
  private SearchResultPage searchResultPage;
  private ShoppingCartPage shoppingCartPage;
  private SummerDressesPage summerDressesPage;
  private OurStoresPage ourStoresPage;

  public PageFactory(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public BasePage getBasePage() {
    return (basePage == null) ? basePage = new BasePage() : basePage;
  }

  public LandingPage getLandingPage() {
    return (landingPage == null) ? landingPage = new LandingPage(driver, wait) : landingPage;
  }

  public LoginPage getLoginPage() {
    return (loginPage == null) ? loginPage = new LoginPage(driver, wait) : loginPage;
  }
  
  public HomePage getHomePage() {
	    return (homePage == null) ? homePage = new HomePage(driver, wait) : homePage;
	  }

//  public RegistrationPage getRegistrationPage() {
//    return (registrationPage == null)
//        ? registrationPage = new RegistrationPage(driver, wait)
//        : registrationPage;
//  }

  public MyAccountPage getMyAccountPage() {
    return (myAccountPage == null)
        ? myAccountPage = new MyAccountPage(driver, wait)
        : myAccountPage;
  }

  public SearchResultPage getSearchResultPage() {
    return (searchResultPage == null)
        ? searchResultPage = new SearchResultPage(driver, wait)
        : searchResultPage;
  }

  public ShoppingCartPage getShoppingCartPage() {
    return (shoppingCartPage == null)
        ? shoppingCartPage = new ShoppingCartPage(driver, wait)
        : shoppingCartPage;
  }

  public SummerDressesPage getSummerDressesPage() {
    return (summerDressesPage == null)
        ? summerDressesPage = new SummerDressesPage(driver, wait)
        : summerDressesPage;
  }

  public OurStoresPage getOurStoresPage() {
    return (ourStoresPage == null)
        ? ourStoresPage = new OurStoresPage(driver, wait)
        : ourStoresPage;
  }
}
