package com.example.myprojectandroidapp.ds;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Person extends User implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String position;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Person(String login, String psw, String name, String surname, String email, String position) {
        super(login, psw);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
    }

    public Person(String name, String surname, String email, String position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
