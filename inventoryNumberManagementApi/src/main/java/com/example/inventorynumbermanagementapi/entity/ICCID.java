package com.example.inventorynumbermanagementapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
public class ICCID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[0-9]{18}[0-9xX]$",message = "The ICCID number must contain 19 digits")
    private String iccidNumber;

    private String provider;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIccidNumber() {
        return iccidNumber;
    }

    public void setIccidNumber(String iccidNumber) {
        this.iccidNumber = iccidNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((iccidNumber == null) ? 0 : iccidNumber.hashCode());
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
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
        ICCID other = (ICCID) obj;
        if (id != other.id)
            return false;
        if (iccidNumber == null) {
            if (other.iccidNumber != null)
                return false;
        } else if (!iccidNumber.equals(other.iccidNumber))
            return false;
        if (provider == null) {
            if (other.provider != null)
                return false;
        } else if (!provider.equals(other.provider))
            return false;
        return true;
    }
}
