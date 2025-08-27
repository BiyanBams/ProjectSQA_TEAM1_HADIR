package com.juaracoding.hadir.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Masuk']")
    private WebElement loginbtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void setEmail(String value) {
        emailField.clear();
        emailField.sendKeys(value);
    }

    public void setPassword(String value) {
        passwordField.clear();
        passwordField.sendKeys(value);
    }

    public void clickLoginButton() {
        loginbtn.click();
    }

    public void performLogin() {
        setEmail("admin@hadir.com");
        setPassword("MagangSQA_JC@123");
        clickLoginButton();
    }
}