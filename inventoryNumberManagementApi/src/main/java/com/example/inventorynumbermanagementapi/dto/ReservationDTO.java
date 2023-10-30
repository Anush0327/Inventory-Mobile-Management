package com.example.inventorynumbermanagementapi.dto;

import lombok.Data;

public class ReservationDTO {
    private String customerName;
    
    private String aadharUID;

    private String provider;

    private String reservingNumber;

    private String location;

    private String connectionType;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAadharUID() {
        return aadharUID;
    }

    public void setAadharUID(String aadharUID) {
        this.aadharUID = aadharUID;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getReservingNumber() {
        return reservingNumber;
    }

    public void setReservingNumber(String reservingNumber) {
        this.reservingNumber = reservingNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((aadharUID == null) ? 0 : aadharUID.hashCode());
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
        result = prime * result + ((reservingNumber == null) ? 0 : reservingNumber.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((connectionType == null) ? 0 : connectionType.hashCode());
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
        ReservationDTO other = (ReservationDTO) obj;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (aadharUID == null) {
            if (other.aadharUID != null)
                return false;
        } else if (!aadharUID.equals(other.aadharUID))
            return false;
        if (provider == null) {
            if (other.provider != null)
                return false;
        } else if (!provider.equals(other.provider))
            return false;
        if (reservingNumber == null) {
            if (other.reservingNumber != null)
                return false;
        } else if (!reservingNumber.equals(other.reservingNumber))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (connectionType == null) {
            if (other.connectionType != null)
                return false;
        } else if (!connectionType.equals(other.connectionType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ReservationDTO [customerName=" + customerName + ", aadharUID=" + aadharUID + ", provider=" + provider
                + ", reservingNumber=" + reservingNumber + ", location=" + location + ", connectionType="
                + connectionType + "]";
    }

}
