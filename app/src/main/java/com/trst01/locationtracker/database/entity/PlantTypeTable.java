package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class PlantTypeTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("PlantTypeId")
    @Expose(serialize = false, deserialize = false)
    private int plantTypeId;

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("EstimatedTon")
    @Expose
    private String estimatedTon;


    @SerializedName("LoanEligible")
    @Expose
    private String loanEligible;

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

    public int getPlantTypeId() {
        return plantTypeId;
    }

    public void setPlantTypeId(int plantTypeId) {
        this.plantTypeId = plantTypeId;
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

    public String getEstimatedTon() {
        return estimatedTon;
    }

    public void setEstimatedTon(String estimatedTon) {
        this.estimatedTon = estimatedTon;
    }

    public String getLoanEligible() {
        return loanEligible;
    }

    public void setLoanEligible(String loanEligible) {
        this.loanEligible = loanEligible;
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