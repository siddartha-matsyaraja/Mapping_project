
package com.example.demo;
import java.io.*;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Provider implements Serializable {
    private String name;
    private String spec;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int provider_id;

    @ManyToMany(mappedBy = "providers")
    @JsonIgnoreProperties("providers")
    
       private List<Patient> patient;

    public Provider() {
        
    }

    public Provider(String name, String spec) {
        super();
        this.name = name;
        this.spec = spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getProviderId() {
        return provider_id;
    }

    public void setProviderId(int providerId) {
        this.provider_id = providerId;
    }

    public List<Patient> getPatients() {
        return patient;
    }

    public void setPatients(List<Patient> patients) {
        this.patient = patients;
    }

    @Override
    public String toString() {
        return "Provider [name=" + name + ", spec=" + spec + ", providerId=" + provider_id + "]";
    }
}
