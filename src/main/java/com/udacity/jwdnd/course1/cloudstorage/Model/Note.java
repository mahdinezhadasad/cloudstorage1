package com.udacity.jwdnd.course1.cloudstorage.Model;
public class Note {
    private Integer noteid;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    public Note( Integer noteid,String noteTitle, String noteDescription, Integer userId) {
        this.noteTitle = noteTitle;
        this.noteid = noteid;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    public Note() {

    }


    public String getNoteTitle() {
        return noteTitle;
    }
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
    public Integer getNoteId() {
        return noteid;
    }
    public void setNoteId(Integer noteid) {
        this.noteid = noteid;
    }
    public String getNoteDescription() {
        return noteDescription;
    }
    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}