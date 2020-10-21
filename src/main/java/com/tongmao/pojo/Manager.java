package com.tongmao.pojo;

public class Manager {
    private String username;
    private String password;
    private String registertime;

    @Override
    public String toString() {
        return "Manager{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registertime='" + registertime + '\'' +
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

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public Manager() {
    }

    public Manager(String username, String password, String registertime) {
        this.username = username;
        this.password = password;
        this.registertime = registertime;
    }
}
