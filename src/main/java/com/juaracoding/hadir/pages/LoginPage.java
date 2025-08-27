package com.juaracoding.hadir.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
 private WebDriver driver;

  @FindBy(xpath = "//input[@id='email']")
  private WebElement username;

  @FindBy(xpath = "//input[@id='password']")
  private WebElement password;

  @FindBy(xpath = "//button[normalize-space()='Masuk']")
  private WebElement loginButton;

    
  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(this.driver, this);
}

  public void setUsername(String value) {
    username.sendKeys(value);
  }

  public void setPassword(String value) {
    password.sendKeys(value);
  }

  public void clickLoginButton() {
    loginButton.click();
  }

  public void performLogin() {
    setUsername("admin@hadir.com");
    setPassword("MagangSQA_JC@123");
    clickLoginButton();
  }

  public void performLogin(String username, String password) {
    setUsername(username);
    setPassword(password);
    clickLoginButton();
  }

}
