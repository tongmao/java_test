package com.tongmao.pojo;

public class Member {
    private String username;
    private int level;
    private String name;
    private int age;
    private String tel;
    private String endtime;
    private String email;
    private String address;
    private int id;

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                ", endtime='" + endtime + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member() {
    }

    public Member(String username, int level, String name, int age, String tel, String endtime, String email, String address) {
        this.username = username;
        this.level = level;
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.endtime = endtime;
        this.email = email;
        this.address = address;
    }

    public Member(String username, int level, String name, int age, String tel, String endtime, String email, String address, int id) {
        this.username = username;
        this.level = level;
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.endtime = endtime;
        this.email = email;
        this.address = address;
        this.id = id;
    }
}
