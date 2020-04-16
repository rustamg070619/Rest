package com.example.demo.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    private String role;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String name) {
        this.role = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return role;
    }
}