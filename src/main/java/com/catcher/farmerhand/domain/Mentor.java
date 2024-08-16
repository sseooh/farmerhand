package com.catcher.farmerhand.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "mentor")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String crop;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    // Constructors
    public Mentor() {}

    public Mentor(String name, String location, String crop, String email, String phone) {
        this.name = name;
        this.location = location;
        this.crop = crop;
        this.email = email;
        this.phone = phone;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
