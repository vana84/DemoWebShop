package com.answer.digital.pageObjects;

import static org.junit.Assert.assertEquals;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	  Logger log = LogManager.getLogger(getClass());
	  protected WebDriver driver;
	  protected WebDriverWait wait;

	  public HomePage(WebDriver driver, WebDriverWait wait) {
	    this.driver = driver;
	    this.wait = wait;
	    PageFactory.initElements(driver, this);
	  }

//	  @FindBy(xpath = "//ul[contains(@class,'products')]//li[1]")
//	  WebElement ProductPane1;

	  
	  @FindBy(xpath = "//img[@alt='TESTSCRIPTDEMO']")
	  WebElement TSImg; 
	  	
	  
	  @FindBy(xpath = "//a[contains(@href,'?add_to_wishlist')]//span[text()='Add to wishlist']")
	  WebElement addWishlistLnk;
//	  @FindBy(xpath = "//a[contains(@href,'?add_to_wishlist=14')]//span")
//	  WebElement addWishlistLnk2;

	  
	  @FindBy(xpath = "//a[@title='Wishlist']") 
	  WebElement WishlistLnk;
	  
	  @FindBy(xpath = "//tbody[@class='wishlist-items-wrapper']//tr")
	  WebElement wishlistTBRows;
	  
	
	  
	  @FindBy(xpath = "//a[text()='Add to cart']")
	  WebElement ATCLinks;

	  @FindBy(xpath = "//a[@title='Cart']//i")
	  WebElement cartLink;
	  
	  @FindBy(xpath = "//a[text()='View cart']")
	  WebElement viewCartLink;
	  
	  @FindBy(xpath = "//td[@class='product-price']//span//bdi")
	  WebElement cartPrice;
	  
	  @FindBy(xpath = "//i[@class='la la-shopping-bag']//span")
	  WebElement cartItemCount;
	  
	  
	  /** Adding WishList
	 * @throws InterruptedException */
	  public void addWishlist() {
		  log.info("In adding Items to wishlist method");
//		  JavascriptExecutor js = ((JavascriptExecutor) driver);
		  Actions actions = new Actions(driver);
//		  System.out.println(driver.findElements(By.tagName("//a")));
//		  js.executeScript("window.scrollBy(0,250)");
//		  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		  
		  for (int i=1; i<=4; i++) {
			  WebElement PP = driver.findElement(By.xpath("//ul[contains(@class,'products')]//li["+i+"]"));
			  actions.moveToElement(PP).perform();
			  PP.click();
			  TSImg.click();
			  actions.moveToElement(addWishlistLnk).perform();
			  addWishlistLnk.click();
		  }
		  
	  }
	  
	  public void moveToWishlistPage() {
		  log.info("In adding Items to moveTowishlistMethod");
		  WishlistLnk.click();
		  System.out.println(driver.getTitle());
		  assertEquals(
			        "Invalid page title.",
			        "Wishlist – TESTSCRIPTDEMO",
			        driver.getTitle());
	  }
	  
	  List<WebElement> wishlistTable = new ArrayList<>();
	  HashMap<Integer, String> hmin = new HashMap<>();
	  HashMap<Integer, String> hmax = new HashMap<>();
	  HashMap<Integer, Double> hLatest = new HashMap<>();
	  int minPriceIndex = 0;
	  Double minPrice= 0.0;
	  
	  public int getATCLinksWishlist() {
		  wishlistTable= driver.findElements(By.xpath("//tbody[@class='wishlist-items-wrapper']//tr"));
		  System.out.println(wishlistTable.size());
		  return wishlistTable.size();
	  }

	  
	  public void getPriceList() throws ParseException {
		  log.info("getPriceList method");
		for(int i=1;i<=wishlistTable.size();i++) {
			String priceRange = driver.findElement(By.xpath("//tr["+i+"]//td[@class='product-price']")).getText();		
			System.out.println(priceRange);
			if(priceRange.contains(" – ")) {
					hmin.put(i,(driver.findElement(By.xpath("//tr["+i+"]//td[@class='product-price']//span[1]//bdi"))).getText());
					hmax.put(i,(driver.findElement(By.xpath("//tr["+i+"]//td[@class='product-price']//span[2]//bdi"))).getText());
			} else {
				hmin.put(i,(driver.findElement(By.xpath("//tr["+i+"]//td[@class='product-price']/ins/span//bdi"))).getText());
			}								
		}
		System.out.println(hmin);
		System.out.println(hmax);
		StringToInt();
	  }
	  
	  public void StringToInt() throws ParseException {
		  System.out.println("In String to double mehtod");	  
		  NumberFormat format = NumberFormat.getCurrencyInstance();
		  Iterator<Integer> it = hmin.keySet().iterator();
			while(it.hasNext()) {
				int hKeys = it.next();
				Number number = format.parse(hmin.get(hKeys));
//				System.out.println(number.toString());
				double priceValues = number.doubleValue();
				hLatest.put(hKeys, priceValues);
//				System.out.println(hLatest);				
			}
			System.out.println(hLatest);
				  
	  }
	  
  
	  public int finMinVal() {
		  System.out.println("Finding the min value method");
		  double minValueInMap=(double) (Collections.min(hLatest.values())); 
	        for (Entry<Integer, Double> entry : hLatest.entrySet()) {  
	            if (entry.getValue()==minValueInMap) {
	            	minPriceIndex = entry.getKey();
	            	minPrice= entry.getValue();
	                System.out.println(entry.getKey());
	                System.out.println(minPrice);
	            }
	        }
	        return minPriceIndex;
	  	  
	  }
	  
	  public void selectLowestPriceItem() {
		  log.info("Selecting the lowest priced Item");
		  int index = finMinVal();
		  driver.findElement(By.xpath("//tbody[@class='wishlist-items-wrapper']//tr["+index+"]//td[@class='product-add-to-cart']//a")).click();
		 
//		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='alert'])"))));		  
//		  System.out.println(driver.findElement(By.xpath("//div[@role='alert'])")).getText()); 		  
//				  Product added to cart successfully
	  }
	  

	  
	  public void verifyCart() {
		  log.info("Verifying Cart");
		  Actions actions = new Actions(driver);
		  actions.moveToElement(cartLink).perform();
		  cartLink.click();		  
//		  System.out.println("CartCount");
//		  System.out.println(cartItemCount.getText());		  
		  assertEquals(
				  "Wrong cart Count",
				  1,
				  Integer.parseInt(cartItemCount.getText()));		  
//		  System.out.println("Amount in Wishlist");
//		  System.out.println(hmin.get(minPriceIndex));
//		  System.out.println("value in Cart");
//		  System.out.println(driver.findElement(By.xpath("//td[@class='product-price']//span//bdi")).getText());		  
		  assertEquals(
				  "Wrong cart Item",
				  hmin.get(minPriceIndex),
			      cartPrice.getText());		  
	  }
	  
	  
	  
	}




