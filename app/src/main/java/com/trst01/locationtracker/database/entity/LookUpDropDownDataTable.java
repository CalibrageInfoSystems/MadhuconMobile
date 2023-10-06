package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class LookUpDropDownDataTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("lookUpId")
    @Expose(serialize = false, deserialize = false)
    private int lookUpId;

    @SerializedName("Id")
    @Expose
    private String Id;

    @SerializedName("LookupHdrId")
    @Expose
    private String LookupHdrId = "";

    @SerializedName("Code")
    @Expose
    private String Code;


    @SerializedName("Name")
    @Expose
    private String Name;


    @SerializedName("Remarks")
    @Expose
    private String Remarks;


    @SerializedName("Ord")
    @Expose
    private String Ord;


    @SerializedName("IsActive")
    @Expose
    private String IsActive = "1";

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId ;

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId ;


    public int getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(int lookUpId) {
        this.lookUpId = lookUpId;
    }

    public String getLookupHdrId() {
        return LookupHdrId;
    }

    public void setLookupHdrId(String lookupHdrId) {
        LookupHdrId = lookupHdrId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getOrd() {
        return Ord;
    }

    public void setOrd(String ord) {
        Ord = ord;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }



    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedByUserId() {
        return CreatedByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getUpdatedByUserId() {
        return UpdatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }
}