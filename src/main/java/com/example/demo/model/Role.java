package com.example.demo.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<User> users;
    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> user) {
        this.users = user;
    }


    @Override
    public String toString() {
        return role;

    }
}