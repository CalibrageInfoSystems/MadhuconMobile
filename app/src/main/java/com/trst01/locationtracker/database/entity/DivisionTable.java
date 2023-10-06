package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class DivisionTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("divisionId")
    @Expose(serialize = false, deserialize = false)
    private int divisionId;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("Code")
    @Expose
    private String code;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Incharge")
    @Expose
    private String incharge;

    @SerializedName("InchargePhone")
    @Expose
    private String inchargePhone;

    @SerializedName("Address")
    @Expose
    private String address;

    @SerializedName("Ord")
    @Expose
    private Integer ord;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String updateByUserId;

    @SerializedName("UpdatedDate")
    @Expose
    private String updateDate;

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getInchargePhone() {
        return inchargePhone;
    }

    public void setInchargePhone(String inchargePhone) {
        this.inchargePhone = inchargePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
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

    public String getUpdateByUserId() {
        return updateByUserId;
    }

    public void setUpdateByUserId(String updateByUserId) {
        this.updateByUserId = updateByUserId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
