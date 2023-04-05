package com.example.ramq.classes;


public class AccountInformation {
    private String userName;
    private String password;
    private String userID;
    private String email;
    private String phoneNumber;

    public AccountInformation(){
        this.userName = "ramq";
        this.password = "ramq";
        this.userID = "01";
        this.email = "ramq@ramq.com";
        this.phoneNumber = "123-456-7890";
    }

    public AccountInformation(String userName, String password, String userID, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public AccountInformation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
