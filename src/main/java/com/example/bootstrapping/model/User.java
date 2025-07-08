package com.example.bootstrapping.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime accountCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime accountUpdated;

    @PrePersist
    protected void onCreate() {
        this.accountCreated = LocalDateTime.now();
        this.accountUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.accountUpdated = LocalDateTime.now();
    }


    // --- Getters ---
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getAccountCreated() {
        return accountCreated;
    }

    public LocalDateTime getAccountUpdated() {
        return accountUpdated;
    }

    // --- Setters ---
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountCreated(LocalDateTime accountCreated) {
        this.accountCreated = accountCreated;
    }

    public void setAccountUpdated(LocalDateTime accountUpdated) {
        this.accountUpdated = accountUpdated;
    }
}
