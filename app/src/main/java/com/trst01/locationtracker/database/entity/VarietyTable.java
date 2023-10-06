package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class VarietyTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("VarietyId")
    @Expose(serialize = false, deserialize = false)
    private int varietyId;

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("PlantAge")
    @Expose
    private Integer plantAge;


    @SerializedName("RatoonAge")
    @Expose
    private Integer RatoonAge;

    public Integer getRatoonAge() {
        return RatoonAge;
    }

    public void setRatoonAge(Integer ratoonAge) {
        RatoonAge = ratoonAge;
    }

    @SerializedName("SugarContent")
    @Expose
    private String sugarContent;



    @SerializedName("PlantSuitability")
    @Expose
    private String plantSuitability;


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

    public int getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(int varietyId) {
        this.varietyId = varietyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getPlantAge() {
        return plantAge;
    }

    public void setPlantAge(Integer plantAge) {
        this.plantAge = plantAge;
    }

    public String getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(String sugarContent) {
        this.sugarContent = sugarContent;
    }

    public String getPlantSuitability() {
        return plantSuitability;
    }

    public void setPlantSuitability(String plantSuitability) {
        this.plantSuitability = plantSuitability;
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