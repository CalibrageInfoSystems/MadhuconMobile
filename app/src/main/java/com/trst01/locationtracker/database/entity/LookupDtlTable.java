package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class LookupDtlTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("LookupDtlTableId")
    @Expose(serialize = false, deserialize = false)
    private int lookupDtlTableId;

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("LookupMstId")
    @Expose
    private Integer LookupMstId;

    @SerializedName("Ord")
    @Expose
    private Integer ord;

    @SerializedName("Code")
    @Expose
    private String code;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Remarks")
    @Expose
    private String remarks;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId ;

    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String updatedByUserId ;

    public Integer getLookupMstId() {
        return LookupMstId;
    }

    public void setLookupMstId(Integer lookupMstId) {
        LookupMstId = lookupMstId;
    }

    public int getLookupDtlTableId() {
        return lookupDtlTableId;
    }

    public void setLookupDtlTableId(int lookupDtlTableId) {
        this.lookupDtlTableId = lookupDtlTableId;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }
}