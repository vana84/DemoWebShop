package com.answer.digital.factory;

import com.answer.digital.enums.Drivers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
  Logger log = LogManager.getLogger(getClass());

  private WebDriver driver;
  private WebDriverWait wait;
  private static Drivers browser;
  private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

  public DriverFactory() {
    browser = Drivers.CHROME;
  }

  public WebDriver getDriver() {
    log.info("Get Driver");
    if (driver == null) driver = createDriver();
    return driver;
  }

  // Create Driver according to OS & Browser type
  private WebDriver createDriver() {
    String os = System.getProperty("os.name");
    log.info("Operating System : " + os);

    switch (browser) {
      case CHROME:
        String driverPath = System.getProperty("user.dir");

        if (os.contains("Mac")) {
          driverPath = driverPath + PropertyManager.getInstance().getPropValue("mac_chromepath");
        } else if (os.contains("Windows")) {
          driverPath = driverPath + PropertyManager.getInstance().getPropValue("win_chromepath");
        } else {
          Assert.fail("This Script is not build for " + os + " system.");
        }

        System.setProperty(CHROME_DRIVER_PROPERTY, driverPath);
        driver = new ChromeDriver();
        break;

      case FIREFOX:
        log.error("Implement Firefox Driver.");
        Assert.fail();

      default:
        log.error("Invalid driver type.");
        Assert.fail();
        break;
    }

    driver.manage().window().maximize();
    long waitTime = Long.parseLong(PropertyManager.getInstance().getPropValue("waitTime"));

    driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(waitTime, TimeUnit.SECONDS);

    return driver;
  }

  // Webdriver Wait
  public WebDriverWait getWait() {
    if (wait == null)
      wait =
          new WebDriverWait(
              getDriver(), Long.parseLong(PropertyManager.getInstance().getPropValue("waitTime")));

    return wait;
  }

  // Close Driver
  public void closeDriver() {
    driver.close();
    driver.quit();

    driver = null;
  }
}
