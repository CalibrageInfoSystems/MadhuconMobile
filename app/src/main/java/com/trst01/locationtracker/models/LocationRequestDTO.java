package com.trst01.locationtracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationRequestDTO implements Serializable {
    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("UserId")
    @Expose
    public Integer UserId;

    @SerializedName("Latitude")
    @Expose
    private Double Latitude;

    @SerializedName("Longitude")
    @Expose
    public Double Longitude;

    @SerializedName("ServerStatus")
    @Expose
    public Boolean ServerStatus;

    @SerializedName("IsActive")
    @Expose
    public Boolean IsActive;

    @SerializedName("CreatedDate")
    @Expose
    public String CreatedDate;

    @SerializedName("CreatedByUserId")
    @Expose
    public Integer CreatedByUserId;

    @SerializedName("UpdatedDate")
    @Expose
    public String UpdatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    public Integer UpdatedByUserId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Boolean getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(Boolean serverStatus) {
        ServerStatus = serverStatus;
    }

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public Integer getCreatedByUserId() {
        return CreatedByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public Integer getUpdatedByUserId() {
        return UpdatedByUserId;
    }

    public void setUpdatedByUserId(Integer updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }
}
