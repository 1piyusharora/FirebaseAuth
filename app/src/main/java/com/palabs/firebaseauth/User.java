package com.palabs.firebaseauth;

/**
 * Created by White Wolf on 7/8/2018.
 */

public class User {

    public String name;
    public String email;
    public String password;

    public User(){

    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
