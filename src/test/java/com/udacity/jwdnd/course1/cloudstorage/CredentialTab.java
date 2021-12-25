package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import okhttp3.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredentialTab {

    private final JavascriptExecutor js;
    private final WebDriver driver;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    @FindBy(id = "nav-credentials")
    private WebElement tabCredentials;

    @FindBy(id = "btn-add-credential")
    private WebElement addCredentials;

    @FindBy(id = "btn-edit-credential")

    private WebElement btnEditCredentials;

    @FindBy(id = "btn-delete-credential")
    private WebElement btnDeleteCredentials;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")

    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(id = "credentials")
    private List<WebElement> credentials;


    public CredentialTab(WebDriver driver, CredentialService credentialService, EncryptionService encryptionService) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    public void addCredential(String url,
                              String username,
                              String password){

        js.executeScript("arguments[0].click();",tabCredentials);
        js.executeScript("arguments[0].click();",addCredentials);
        js.executeScript("arguments[0].value='" + url+"';",credentialUrl);
        js.executeScript("arguments[0].value='" + username + "';", credentialUsername);
        js.executeScript("arguments[0].value='" + password + "';", credentialPassword);
        js.executeScript("arguments[0].click();", credentialSubmit);


    }

    public boolean checkIfCredentialExists(String url){

        js.executeScript("arguments[0].click();",tabCredentials);
        for(WebElement cred:credentials){
            String noteTitle = cred.getAttribute("innerHTML");
            if(noteTitle.contains(url)){
            }

            return true;

        }

        return false;



    }

    public boolean checkCredentialContent(String url,String username,String password){

          Credential credential = credentialService.getCredentialByUrl(url);

          if(credential !=null && credential.getUsername()!= null
          && credential.getUsername().equals(username)
          && credential.getPassword()!=null
          && credential.getPassword().equals(encryptionService.encryptValue(password,credential.getKey())))

              return true;

        return false;

    }

    public void editCredential(String url,String newUrl,String newUsername,String newPassword){

        WebDriverWait wait = new WebDriverWait(driver,2);
        WebElement btnDeleeCred;

        js.executeScript("arguments[0].click();",tabCredentials);

        WebElement homewait = wait.until(webDriver -> webDriver.findElement(By.id("btn-delete-credential")));
        for(WebElement cred : credentials){
            String noteRowHTML = cred.getAttribute("innerHTML");
            if(noteRowHTML.contains(url)){
                WebElement btnEditItem =
                        cred.findElement(By.id("btn-edit-credential"));

                js.executeScript("arguments[0].click();",btnEditItem);
                js.executeScript("arguments[0].click();",tabCredentials);
                js.executeScript("arguments[0].value ='" + newUrl+"';",credentialUrl);
                js.executeScript("arguments[0].value='"+newUsername+"';",credentialUsername);
                js.executeScript("arguments[0].value='"+newPassword+"';",credentialPassword);
                js.executeScript("arguments[0].click();",credentialSubmit);

                return;

            }

        }

    }



    public void deleteCredential(String url){

        WebDriverWait wait = new WebDriverWait(driver,2);
        WebElement btnDeleteCredential;
        js.executeScript("arguments[0].click();",tabCredentials);
        WebElement homeWait = wait.until(webDriver ->
                webDriver.findElement(By.id("btn-delete-credential")));

        for(WebElement cred : credentials){
            String noteRowHTML = cred.getAttribute("innerHTML");

            if(noteRowHTML.contains(url)){

                WebElement btnDelItem =

                        cred.findElement(By.id("btn-delete-credential"));

                js.executeScript("arguments[0].click();",btnDelItem);

                return;
            }
      }

    }

}
