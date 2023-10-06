package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class AddGeoBoundariesTrackingTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("GeoBoundariesTrackingId")
    @Expose(serialize = false, deserialize = false)
    private int GeoBoundariesId;
    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("UserId")
    @Expose
    private Integer UserId;

    @SerializedName("SeqNo")
    @Expose
    private Integer SeqNo;

//    @SerializedName("PlotNo")
//    @Expose
//    private String PlotNo;

    @SerializedName("Latitude")
    @Expose
    private String Latitude;

    @SerializedName("Longitude")
    @Expose
    private String Longitude;
//
//    @SerializedName("TotalPlotArea")
//    @Expose
//    private String TotalPlotArea;
//
//    @SerializedName("CultureArea")
//    @Expose
//    private String CultureArea;
//    @SerializedName("Sync")
//    @Expose
//    private boolean Sync;
    @SerializedName("IsActive")
    @Expose
    private Boolean IsActive ;
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
    @SerializedName("ServerStatus")
    @Expose
    private Boolean serverStatus;

    public Integer getSeqNo() {
        return SeqNo;
    }

    public void setSeqNo(Integer seqNo) {
        SeqNo = seqNo;
    }

    public int getGeoBoundariesId() {
        return GeoBoundariesId;
    }

    public void setGeoBoundariesId(int geoBoundariesId) {
        GeoBoundariesId = geoBoundariesId;
    }

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
//
//    public String getPlotNo() {
//        return PlotNo;
//    }
//
//    public void setPlotNo(String plotNo) {
//        PlotNo = plotNo;
//    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public Boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(Boolean isActive) {
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

    public Boolean getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Boolean serverStatus) {
        this.serverStatus = serverStatus;
    }
}
