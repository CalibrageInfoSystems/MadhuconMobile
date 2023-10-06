package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class FertilizerTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("fertilizerId")
    @Expose(serialize = false, deserialize = false)
    private int fertilizerId;

    @SerializedName("Id")
    @Expose
    private String id;


    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Name")
    @Expose
    private String name;


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

    public int getFertilizerId() {
        return fertilizerId;
    }

    public void setFertilizerId(int fertilizerId) {
        this.fertilizerId = fertilizerId;
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