package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class AddComplaintsDetailsTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ComplaintsId")
    @Expose(serialize = false, deserialize = false)
    private int ComplaintsId;

    @SerializedName("Code")
    @Expose
    private String Code;

    @SerializedName("FarmerCode")
    @Expose
    private String FarmerCode;


    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;


    @SerializedName("ComplaintStatus")
    @Expose
    private String ComplaintStatus;
    @SerializedName("ComplaintType")
    @Expose
    private String ComplaintType;
    @SerializedName("solution")
    @Expose
    private String solution;
    @SerializedName("LogBookNo")//plot no
    @Expose
    private String LogBookNo;


    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("IsActive")
    @Expose
    private String IsActive = "true";

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;
    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId;
    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate;

    @SerializedName("serverStatus")
    @Expose
    private String serverStatus;

    public String getFarmerCode() {
        return FarmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        FarmerCode = farmerCode;
    }

    public String getSeasonCode() {
        return SeasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        SeasonCode = seasonCode;
    }

    public int getComplaintsId() {
        return ComplaintsId;
    }

    public void setComplaintsId(int complaintsId) {
        ComplaintsId = complaintsId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getCreatedByUserId() {
        return CreatedByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getUpdatedByUserId() {
        return UpdatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getComplaintStatus() {
        return ComplaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        ComplaintStatus = complaintStatus;
    }

    public String getComplaintType() {
        return ComplaintType;
    }

    public void setComplaintType(String complaintType) {
        ComplaintType = complaintType;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getLogBookNo() {
        return LogBookNo;
    }

    public void setLogBookNo(String logBookNo) {
        LogBookNo = logBookNo;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }
}
