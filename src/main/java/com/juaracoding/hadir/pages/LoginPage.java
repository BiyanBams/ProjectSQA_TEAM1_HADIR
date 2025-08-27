package com.juaracoding.hadir.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
 private WebDriver driver;

  @FindBy(xpath = "//input[@id='email']")
  private WebElement username;

  @FindBy(xpath = "//input[@id='email']")
  private WebElement email;

  @FindBy(xpath = "//input[@id='password']")
  private WebElement password;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[2]/div/div[2]/form/button")
  private WebElement loginButton;


  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(this.driver, this);
  }

  public void setEmail(String value) {
    email.sendKeys(value);
  }

  public void setPassword(String value) {
    password.sendKeys(value);
  }

  public void clickLoginButton() {
    loginButton.click();
  }

  public void performLogin() {
    setEmail("admin@hadir.com");
    setPassword("MagangSQA_JC@123");
    clickLoginButton();
  }

  public void performLogin(String email, String password) {
    setEmail(email);
    setPassword(password);
    clickLoginButton();
  }

}
