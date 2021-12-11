package com.example.myprojectandroidapp.ds;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class User implements Serializable {
    private int id;
    private String login;
    private String psw;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private boolean isActive;

    public User(String login, String psw, LocalDate dateCreated, LocalDate dateModified, boolean isActive) {
        this.login = login;
        this.psw = psw;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String login, String psw) {
        this.login = login;
        this.psw = psw;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.isActive = true;
    }

    public User(int id, String login, String psw, LocalDate dateCreated, LocalDate dateModified, boolean isActive) {
        this.id = id;
        this.login = login;
        this.psw = psw;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
