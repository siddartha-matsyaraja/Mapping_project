package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.JoinColumn;

@Component
@Entity
public class Patient {
    private String name;
    private String email;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patient_id;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "patient_provider",
        joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    )
    private List<Provider> providers;
    
    public Patient() {
        super();
    }
    
    public Patient(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getId() {
        return patient_id;
    }
    
    public void setId(int id) {
        this.patient_id = id;
    }
    
    public List<Provider> getProviders() {
        return providers;
    }
    
    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
    
    @Override
    public String toString() {
        return "Patient [name=" + name + ", email=" + email + ", id=" + patient_id + ", providers=" + providers + "]";
    }
}