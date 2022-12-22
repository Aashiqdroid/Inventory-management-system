package com.aashiq.entity;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@ToString
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private  String password;
    private String mobile;
    private String address;
    private String dob;

    public User() {
    }

    public User(Long id, String username, String password, String mobile, String address, String dob) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.dob = dob;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
