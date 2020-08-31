package com.miloraddjordjevic.ecommerce.entities;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table (name = "korisnici")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    @Column (name = "email")
    private String email;
    @Column (name = "username")
    private String username;
    @Column (name = "password")
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        String str = this.username + " " + this.password;
        return str;
    }
}
