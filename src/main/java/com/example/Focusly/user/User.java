package com.example.Focusly.user;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email_id;
    private String username;
    private String password;
    
    // New field to store user role
    private String role;

    public User() {
        super();
    }

    public User(Long id, String name, String email_id, String username, String password, String role) {
        super();
        this.id = id;
        this.name = name;
        this.email_id = email_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email_id=" + email_id + ", username=" + username + ", password="
                + password + ", role=" + role + "]";
    }
}
