package com.tongmao.dto;

public class Loginer {
    private String username;
    private String password;
    private boolean checkbox;

    @Override
    public String toString() {
        return "Loginer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", checkbox=" + checkbox +
                '}';
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

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Loginer() {
    }

    public Loginer(String username, String password, boolean checkbox) {
        this.username = username;
        this.password = password;
        this.checkbox = checkbox;
    }
}
