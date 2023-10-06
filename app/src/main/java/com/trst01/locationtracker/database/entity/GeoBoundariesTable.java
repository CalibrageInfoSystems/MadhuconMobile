package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class GeoBoundariesTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("GeoID")
    @Expose(serialize = false, deserialize = false)
    private int GeoID;

    @SerializedName("PlotNo")
    @Expose
    private String PlotNo = "";

    //Code for Only Bank
    @SerializedName("Latitude")
    @Expose
    private double Latitude;

    @SerializedName("Longitude")
    @Expose
    private double Longitude;

    @SerializedName("GeoCategoryTypeId")
    @Expose
    private Integer GeoCategoryTypeId ;
    @SerializedName("IsActive")
    @Expose
    private String IsActive = "1";

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId;

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId;


    @SerializedName("serverSend")
    @Expose
    private String serverSend ;

    public String getServerSend() {
        return serverSend;
    }

    public void setServerSend(String serverSend) {
        this.serverSend = serverSend;
    }

    //    //extennsion of file
//    @SerializedName("CropMaintenanceCode")
//    @Expose
//    private String CropMaintenanceCode = "";
//
//    @SerializedName("ServerUpdatedStatus")
//    @Expose
//    private int ServerUpdatedStatus;

    @SerializedName("sync")
    @Expose
    private boolean sync;


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

    public int getGeoID() {
        return GeoID;
    }

    public void setGeoID(int geoID) {
        GeoID = geoID;
    }

    public String getPlotNo() {
        return PlotNo;
    }

    public void setPlotNo(String plotNo) {
        PlotNo = plotNo;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public Integer getGeoCategoryTypeId() {
        return GeoCategoryTypeId;
    }

    public void setGeoCategoryTypeId(Integer geoCategoryTypeId) {
        GeoCategoryTypeId = geoCategoryTypeId;
    }





    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}