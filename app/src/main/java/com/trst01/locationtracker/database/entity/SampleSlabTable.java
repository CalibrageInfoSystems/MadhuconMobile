package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class SampleSlabTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("SampleSlabId")
    @Expose(serialize = false, deserialize = false)
    private int sampleSlabId;

    public int getSampleSlabId() {
        return sampleSlabId;
    }

    public void setSampleSlabId(int sampleSlabId) {
        this.sampleSlabId = sampleSlabId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromArea() {
        return fromArea;
    }

    public void setFromArea(String fromArea) {
        this.fromArea = fromArea;
    }

    public Double getToArea() {
        return toArea;
    }

    public void setToArea(Double toArea) {
        this.toArea = toArea;
    }

    public Integer getNoOfSample() {
        return noOfSample;
    }

    public void setNoOfSample(Integer noOfSample) {
        this.noOfSample = noOfSample;
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

    @SerializedName("FromArea")
    @Expose
    private String fromArea;

    @SerializedName("ToArea")
    @Expose
    private Double toArea;

    @SerializedName("NoOfSample")
    @Expose
    private Integer noOfSample;

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