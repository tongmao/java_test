package com.tongmao.dto;

public class Register {
    private String username;
    private String password;
    private String key;
    private String registertime;

    @Override
    public String toString() {
        return "Register{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public Register() {
    }

    public Register(String username, String password, String key, String registertime) {
        this.username = username;
        this.password = password;
        this.key = key;
        this.registertime = registertime;
    }
}
