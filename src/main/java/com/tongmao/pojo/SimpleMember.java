package com.tongmao.pojo;

public class SimpleMember {
    private String username;
    private int level;
    private int id;

    @Override
    public String toString() {
        return "SimpleMember{" +
                "username='" + username + '\'' +
                ", level=" + level +
                ", id=" + id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleMember() {
    }

    public SimpleMember(String username, int level) {
        this.username = username;
        this.level = level;
    }

    public SimpleMember(String username, int level, int id) {
        this.username = username;
        this.level = level;
        this.id = id;
    }
}
