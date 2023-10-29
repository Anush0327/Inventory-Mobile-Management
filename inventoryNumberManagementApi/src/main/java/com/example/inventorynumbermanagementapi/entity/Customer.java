package com.example.inventorynumbermanagementapi.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Pattern(regexp = "^[0-9]{12}$",message = "UID must be 12 digits long")
    private String aadharUID;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SIM> sims;
}
