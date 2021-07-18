package com.answer.digital.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class DataGenerator {

  public static FakeValuesService fakeValuesService;

  public static Faker faker;

  public DataGenerator() {
    fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());
    faker = new Faker(new Locale("en-US"));
  }

  public String getEmail() {
    return faker.name().username() + "@test.com";
  }

  public String getFirstName() {
    return faker.name().firstName();
  }

  public String getLastName() {
    return faker.name().lastName();
  }
}
