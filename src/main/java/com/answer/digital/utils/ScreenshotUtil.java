package com.answer.digital.utils;

import com.answer.digital.factory.PropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenshotUtil {
  Logger log = LogManager.getLogger(getClass());

  protected WebDriver driver;

  public ScreenshotUtil(WebDriver driver) {
    this.driver = driver;
  }

  /** Capture Visible area */
  public void takeScreenshot() {
    log.info("Take Screenshot ");

    try {
      File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String sPAth =
          System.getProperty("user.dir")
              + PropertyManager.getInstance().getPropValue("screenshotsPath")
              + "SCR_"
              + LocalDateTime.now().toString()
              + ".png";

      log.info("Screenshot Path : " + sPAth + "SCR_" + LocalDateTime.now().toString());

      FileUtils.copyFile(src, new File(sPAth));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
