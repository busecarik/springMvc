package com.busecarik.entity;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String birthday;
    private String email;
    private int sex;
    private boolean enabled;

    public User() {}

    public User(String username, String password, String birthday, String email, int sex, boolean enabled) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.sex = sex;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}