package com.miloraddjordjevic.ecommerce.entities;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table (name = "roles")
public class Roles {
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "name")
    private String name;
    @OneToOne(mappedBy = "roles")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
