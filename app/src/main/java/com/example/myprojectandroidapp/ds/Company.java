package com.example.myprojectandroidapp.ds;
import java.io.Serializable;
import java.time.LocalDate;

public class Company extends User implements Serializable {
    private String companyName;
    private String representative;
    private String address;
    private String phoneNum;

    public Company(String login, String psw, LocalDate dateCreated, LocalDate dateModified, boolean isActive, String companyName, String representative, String address, String phoneNum) {
        super(login, psw, dateCreated, dateModified, isActive);
        this.companyName = companyName;
        this.representative = representative;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public Company(String login, String psw, String companyName, String representative, String address, String phoneNum) {
        super(login, psw);
        this.companyName = companyName;
        this.representative = representative;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public Company() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
