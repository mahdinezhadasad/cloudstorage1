package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NoteTab {

    private final JavascriptExecutor js;
    private final WebDriver driver;
    private NoteService noteService;

    @FindBy(id = "nav-notes-tab")
    private WebElement tabNotes;

    @FindBy(id = "btn-add-note")
    private WebElement btnAddNote;

    @FindBy(id = "btn-edit-note")
    private WebElement btnEditNote;

    @FindBy(id = "btn-delete-note")
    private WebElement btnDeletNote;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(xpath = "//div//button[normalize-space()='Logout']")
    private WebElement btnLogout;

    @FindBy(id = "notes")
    private List<WebElement> notes;


    public NoteTab(WebDriver driver, NoteService noteService){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.noteService = noteService;
    }

    public void addNote(String title,String description){
        js.executeScript("arguments[0].click();", tabNotes);
        js.executeScript("arguments[0].click();", btnAddNote);
        js.executeScript("arguments[0].value='" + title + "';", noteTitle);
        js.executeScript("arguments[0].value='" + description + "';", noteDescription);
        js.executeScript("arguments[0].click();", noteSubmit);




    }

    public void editNote(String title, String newTitle, String newDescription) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement btnDeleteNote;

        js.executeScript("arguments[0].click();", tabNotes);
        WebElement homeWait = wait.until(webDriver ->
                webDriver.findElement(By.id("btn-delete-note")));
        for (WebElement note : notes) {
            String noteRowHTML = note.getAttribute("innerHTML");
            System.out.println(noteRowHTML);
            if (noteRowHTML.contains(title)) {
                WebElement btnEditItem =
                        note.findElement(By.id("edit-note"));
                js.executeScript("arguments[0].click();", btnEditItem);
                js.executeScript("arguments[0].click();", tabNotes);
                js.executeScript("arguments[0].value='" + newTitle + "';", noteTitle);
                js.executeScript("arguments[0].value='" + newDescription + "';", noteDescription);
                js.executeScript("arguments[0].click();", noteSubmit);
                return;
            }
        }
    }


        public void deleteNote(String title){

        WebDriverWait wait = new WebDriverWait(driver,2);
        WebElement btnDeletNote;

        js.executeScript("arguments[0].click();",tabNotes);
        WebElement  homeWait = wait.until(webDriver -> webDriver.findElement(By.id("btn-delete-note")));

        for(WebElement note : notes){
            String noteTitle = note.getAttribute("innerHTML");

            System.out.println(noteTitle);
            if(noteTitle.contains(title)){

                WebElement btnDelITem =
                        note.findElement(By.id("btn-delete-note"));

               js.executeScript("arguments[0].click();",btnDelITem);

               return;
            }




        }







    }

    public Boolean checkIfNoteExists(String title){
        js.executeScript("arguments[0].click();",tabNotes);
        WebDriverWait wait =new WebDriverWait(driver,5);

        WebElement omeWait = wait.until(webDriver ->
                webDriver.findElement(By.id("btn-delete-note")));
        for(WebElement note: notes){
            String noteTitle = note.getAttribute("innerHTML");
            System.out.println(noteTitle);
            if(noteTitle.contains(noteTitle)){
                return true;
     }

        }

        return false;




    }

    public Boolean chekNoteDescription(String title,String description){
        Note note =noteService.getNoteByTitle(title);

        if(note != null
        && note.getNoteDescription() != null
        && note.getNoteDescription().equals(description))

            return true;

        return false;

    }

    public void logout(){

        js.executeScript("arguments[0].click();",btnLogout);
    }




}
