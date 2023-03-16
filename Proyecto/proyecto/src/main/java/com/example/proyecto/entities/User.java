package com.example.proyecto.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "pswd")
    private String pswd;

    @Column(name="token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(){

    }

    public User(Long id, String userName, String pswd, String token) {
        this.id = id;
        this.userName = userName;
        this.pswd = pswd;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String password) {
        this.pswd = password;
    }
}
