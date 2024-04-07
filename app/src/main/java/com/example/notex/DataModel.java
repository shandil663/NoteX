package com.example.notex;

public class DataModel {

    String title,notesdata;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotesdata() {
        return notesdata;
    }

    public void setNotesdata(String notesdata) {
        this.notesdata = notesdata;
    }

    public DataModel() {
    }

    public DataModel(String title, String notesdata) {
        this.title = title;
        this.notesdata = notesdata;
    }
}
