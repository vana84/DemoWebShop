package com.answer.digital.factory;

import com.answer.digital.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactory {
  private final WebDriver driver;
  private final WebDriverWait wait;

  public BasePage basePage;
  public LandingPage landingPage;
  public HomePage homePage;
  private ShoppingCartPage shoppingCartPage;


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
  
  public HomePage getHomePage() {
	    return (homePage == null) ? homePage = new HomePage(driver, wait) : homePage;
	  }

  public ShoppingCartPage getShoppingCartPage() {
    return (shoppingCartPage == null)
        ? shoppingCartPage = new ShoppingCartPage(driver, wait)
        : shoppingCartPage;
  }


}
