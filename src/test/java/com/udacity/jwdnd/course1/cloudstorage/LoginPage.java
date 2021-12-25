package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final JavascriptExecutor js;

    @FindBy( id = "login-error-msg")
    private WebElement loginErrorMsg;

    @FindBy( id ="login-signup-msg" )

    private WebElement loginSigupMsg;

    @FindBy(id = "login-logout-msg" )

    private WebElement logoutMsg;

    @FindBy(id = "login-input-username")

    private WebElement loginInputUserName;

    @FindBy(id = "login-input-password" )
    private WebElement loginInputPassword;

    @FindBy(id = "login-signup-link")
    private WebElement signupLink;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;


    public LoginPage(WebDriver driver){

        PageFactory.initElements(driver,this);
        this.js = (JavascriptExecutor) driver;

    }

    public  void login(String username, String password){

        js.executeScript("arguments[0].value='"+ username + "';",loginInputUserName);
        js.executeScript("arguments[0].value='" + password + "';",loginInputPassword);
        js.executeScript("arguments[0].click();",loginButton); }


        public String  getErrorMsg(){

        return this.loginErrorMsg.getAttribute("innerHTML");

        }

        public String getLogoutMsg(){

            return this.logoutMsg.getAttribute("innerHTML");

        }
}
