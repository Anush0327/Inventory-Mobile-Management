package com.example.inventorynumbermanagementapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
public class IMEI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[0-9]{15}$",message = "The IMEI number should contain 15 digits")
    private String imeiNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((imeiNumber == null) ? 0 : imeiNumber.hashCode());
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
        IMEI other = (IMEI) obj;
        if (id != other.id)
            return false;
        if (imeiNumber == null) {
            if (other.imeiNumber != null)
                return false;
        } else if (!imeiNumber.equals(other.imeiNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "IMEI [id=" + id + ", imeiNumber=" + imeiNumber + "]";
    }
}
