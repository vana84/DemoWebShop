package com.answer.digital.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

  private static PropertyManager propertyManager;
  public Properties properties = new Properties();

  static {
    try {
      propertyManager = new PropertyManager();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private PropertyManager() throws IOException {
    loadConfigProperties();
  }

  public static PropertyManager getInstance() {
    return propertyManager;
  }

  public void loadConfigProperties() throws IOException {
    properties.load(new FileInputStream("config.properties"));
  }

  public String getPropValue(String key) {
    return properties.getProperty(key);
  }
}
