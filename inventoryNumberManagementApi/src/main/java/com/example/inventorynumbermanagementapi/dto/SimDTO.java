package com.example.inventorynumbermanagementapi.dto;

import lombok.Data;

public class SimDTO {
    private int id;
    private String msisdn;
    private String iccid;
    private String imei;
    private String issuedDateTime;
    private boolean isActivated;
    private String customerName;
    private String connectionType;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMsisdn() {
        return msisdn;
    }
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    public String getIccid() {
        return iccid;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getIssuedDateTime() {
        return issuedDateTime;
    }
    public void setIssuedDateTime(String issuedDateTime) {
        this.issuedDateTime = issuedDateTime;
    }
    public boolean isActivated() {
        return isActivated;
    }
    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        result = prime * result + id;
        result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
        result = prime * result + ((iccid == null) ? 0 : iccid.hashCode());
        result = prime * result + ((imei == null) ? 0 : imei.hashCode());
        result = prime * result + ((issuedDateTime == null) ? 0 : issuedDateTime.hashCode());
        result = prime * result + (isActivated ? 1231 : 1237);
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
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
        SimDTO other = (SimDTO) obj;
        if (id != other.id)
            return false;
        if (msisdn == null) {
            if (other.msisdn != null)
                return false;
        } else if (!msisdn.equals(other.msisdn))
            return false;
        if (iccid == null) {
            if (other.iccid != null)
                return false;
        } else if (!iccid.equals(other.iccid))
            return false;
        if (imei == null) {
            if (other.imei != null)
                return false;
        } else if (!imei.equals(other.imei))
            return false;
        if (issuedDateTime == null) {
            if (other.issuedDateTime != null)
                return false;
        } else if (!issuedDateTime.equals(other.issuedDateTime))
            return false;
        if (isActivated != other.isActivated)
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
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
        return "SimDTO [id=" + id + ", msisdn=" + msisdn + ", iccid=" + iccid + ", imei=" + imei + ", issuedDateTime="
                + issuedDateTime + ", isActivated=" + isActivated + ", customerName=" + customerName
                + ", connectionType=" + connectionType + "]";
    }
}
