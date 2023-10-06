package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class SeasonTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("SeasonId")
    @Expose(serialize = false, deserialize = false)
    private int SeasonId;

    public int getSeasonId() {
        return SeasonId;
    }

    public void setSeasonId(int seasonId) {
        SeasonId = seasonId;
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

    public String getPlantFrom() {
        return plantFrom;
    }

    public void setPlantFrom(String plantFrom) {
        this.plantFrom = plantFrom;
    }

    public String getPlantTo() {
        return plantTo;
    }

    public void setPlantTo(String plantTo) {
        this.plantTo = plantTo;
    }

    public String getCrushFrom() {
        return crushFrom;
    }

    public void setCrushFrom(String crushFrom) {
        this.crushFrom = crushFrom;
    }

    public String getCrushTo() {
        return crushTo;
    }

    public void setCrushTo(String crushTo) {
        this.crushTo = crushTo;
    }

    public Integer getBurnCaneRate() {
        return burnCaneRate;
    }

    public void setBurnCaneRate(Integer burnCaneRate) {
        this.burnCaneRate = burnCaneRate;
    }

    public Integer getCaneRate() {
        return caneRate;
    }

    public void setCaneRate(Integer caneRate) {
        this.caneRate = caneRate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("PlantFrom")
    @Expose
    private String plantFrom;


    @SerializedName("PlantTo")
    @Expose
    private String plantTo;


    @SerializedName("CrushFrom")
    @Expose
    private String crushFrom;


    @SerializedName("CrushTo")
    @Expose
    private String crushTo;


    @SerializedName("BurnCaneRate")
    @Expose
    private Integer burnCaneRate;


    @SerializedName("CaneRate")
    @Expose
    private Integer caneRate;


    @SerializedName("Capacity")
    @Expose
    private Integer capacity;

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

}