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
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = "^[0-9]{12}$",message = "UID must be 12 digits long")
    private String aadharUID;

    public String getAadharUID() {
        return aadharUID;
    }

    public void setAadharUID(String aadharUID) {
        this.aadharUID = aadharUID;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SIM> sims;

    public List<SIM> getSims() {
        return sims;
    }

    public void setSims(List<SIM> sims) {
        this.sims = sims;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((aadharUID == null) ? 0 : aadharUID.hashCode());
        result = prime * result + ((sims == null) ? 0 : sims.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (aadharUID == null) {
            if (other.aadharUID != null)
                return false;
        } else if (!aadharUID.equals(other.aadharUID))
            return false;
        if (sims == null) {
            if (other.sims != null)
                return false;
        } else if (!sims.equals(other.sims))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", aadharUID=" + aadharUID + ", sims=" + sims + "]";
    }
}
