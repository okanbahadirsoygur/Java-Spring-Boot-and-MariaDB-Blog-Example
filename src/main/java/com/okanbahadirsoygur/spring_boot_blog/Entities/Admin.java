package com.okanbahadirsoygur.spring_boot_blog.Entities;

import javax.persistence.*;

@Table
@Entity(name = "admin")
public class Admin {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email,pass,last_login;

    public Long getId() {
        return id;
    }


    public String getEmail(){
        return email;
    }


    public String getPass(){
    return pass;
    }


    public String getLast_login(){
        return last_login;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }


}
