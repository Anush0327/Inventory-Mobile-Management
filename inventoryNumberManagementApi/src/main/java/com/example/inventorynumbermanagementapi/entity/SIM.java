package com.example.inventorynumbermanagementapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
public class SIM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    private MSISDN msisdn;

    @OneToOne
    private IMEI imei;

    @OneToOne
    private ICCID iccid;

    private LocalDateTime IssuedDateTime;
    
    private String simType;

    private boolean isActivated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MSISDN getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(MSISDN msisdn) {
        this.msisdn = msisdn;
    }

    public IMEI getImei() {
        return imei;
    }

    public void setImei(IMEI imei) {
        this.imei = imei;
    }

    public ICCID getIccid() {
        return iccid;
    }

    public void setIccid(ICCID iccid) {
        this.iccid = iccid;
    }

    public LocalDateTime getIssuedDateTime() {
        return IssuedDateTime;
    }

    public void setIssuedDateTime(LocalDateTime issuedDateTime) {
        IssuedDateTime = issuedDateTime;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
        result = prime * result + ((imei == null) ? 0 : imei.hashCode());
        result = prime * result + ((iccid == null) ? 0 : iccid.hashCode());
        result = prime * result + ((IssuedDateTime == null) ? 0 : IssuedDateTime.hashCode());
        result = prime * result + ((simType == null) ? 0 : simType.hashCode());
        result = prime * result + (isActivated ? 1231 : 1237);
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
        SIM other = (SIM) obj;
        if (id != other.id)
            return false;
        if (msisdn == null) {
            if (other.msisdn != null)
                return false;
        } else if (!msisdn.equals(other.msisdn))
            return false;
        if (imei == null) {
            if (other.imei != null)
                return false;
        } else if (!imei.equals(other.imei))
            return false;
        if (iccid == null) {
            if (other.iccid != null)
                return false;
        } else if (!iccid.equals(other.iccid))
            return false;
        if (IssuedDateTime == null) {
            if (other.IssuedDateTime != null)
                return false;
        } else if (!IssuedDateTime.equals(other.IssuedDateTime))
            return false;
        if (simType == null) {
            if (other.simType != null)
                return false;
        } else if (!simType.equals(other.simType))
            return false;
        if (isActivated != other.isActivated)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SIM [id=" + id + ", msisdn=" + msisdn + ", imei=" + imei + ", iccid=" + iccid + ", IssuedDateTime="
                + IssuedDateTime + ", simType=" + simType + ", isActivated=" + isActivated + "]";
    }
}
