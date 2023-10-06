package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class BankTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("BankId")
    @Expose
    private Integer bankId;

    @SerializedName("Id")
    @Expose
    private Integer Id;


    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("Abbr")
    @Expose
    private String abbr;


    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;


    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;


    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId;


    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;


    @SerializedName("UpdatedByUserId")
    @Expose
    private String updatedByUserId;

    @NonNull
    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(@NonNull Integer bankId) {
        this.bankId = bankId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
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