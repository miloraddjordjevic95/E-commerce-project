package com.miloraddjordjevic.ecommerce.entities;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table (name = "korisnici")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private long id;
    @Column (name = "email")
    private String email;
    @Column (name = "username")
    private String username;
    @Column (name = "password")
    private String password;
    @Column (name = "role_id")
    private long role_id;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
