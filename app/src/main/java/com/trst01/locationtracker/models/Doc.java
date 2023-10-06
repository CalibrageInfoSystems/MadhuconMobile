package com.trst01.locationtracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doc {

    //        @SerializedName("FarmerCode")
    @SerializedName("Lat")
    @Expose
    private Double Lat ;

    //        @SerializedName("PlotNo")
    @SerializedName("Lon")
    @Expose
    private Double Lon ;

    @SerializedName("IsActive")
    @Expose
    private String IsActive = "1";


    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate = "";

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId ;

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate = "";


    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId ;

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLon() {
        return Lon;
    }

    public void setLon(Double lon) {
        Lon = lon;
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

