package com.example.inventorynumbermanagementapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
public class MSISDN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[1-9][0-9]{9}$",message = "The MSISDN number should contain 10 digits")
    private String msisdnNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsisdnNumber() {
        return msisdnNumber;
    }

    public void setMsisdnNumber(String msisdnNumber) {
        this.msisdnNumber = msisdnNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((msisdnNumber == null) ? 0 : msisdnNumber.hashCode());
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
        MSISDN other = (MSISDN) obj;
        if (id != other.id)
            return false;
        if (msisdnNumber == null) {
            if (other.msisdnNumber != null)
                return false;
        } else if (!msisdnNumber.equals(other.msisdnNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MSISDN [id=" + id + ", msisdnNumber=" + msisdnNumber + "]";
    }
}
