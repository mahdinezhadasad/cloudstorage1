package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    private final JavascriptExecutor js;


    @FindBy(id ="signup-error-msg")
    private WebElement eerorMsg;

    @FindBy(id="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName" )
    private WebElement inputLastName;

    @FindBy(id= "inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id ="goback-link")
    private WebElement backToLoginLink;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement signupButton;

    public SignUpPage(WebDriver driver){

        PageFactory.initElements(driver,this);

        this.js = (JavascriptExecutor) driver;


    }

    public void signup(String firstname,
                       String lastname,
                       String username,
                       String password) {
        js.executeScript("arguments[0].value='" + firstname + "';", inputFirstName);
        js.executeScript("arguments[0].value='" + lastname + "';", inputLastName);
        js.executeScript("arguments[0].value='" + username + "';", inputUsername);
        js.executeScript("arguments[0].value='" + password + "';", inputPassword);
        js.executeScript("arguments[0].click();", signupButton);
    }

    public void goBackToLogin() {
        js.executeScript("arguments[0].click();", backToLoginLink);
    }


}
