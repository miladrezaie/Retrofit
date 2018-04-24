package com.example.milad.retrofit.model;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

/**
 * Created by milad on 4/19/2018.
 */

public class User {
    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("avatar")
    private String avatar;

    public User() {
    }

    public User(String username) {
        this.username = username;

    }

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonNumber(String phonNumber) {
        this.phoneNumber = phonNumber;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstName(String input_firstName) {
        return firstName;
    }

    public String getLastName(String input_lastName) {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail(String s) {
        return email;
    }

    public String getPhonNumber() {
        return phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }
}
