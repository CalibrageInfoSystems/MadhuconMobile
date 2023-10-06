package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class AddGeoBoundriesTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("GeoBoundriesId")
    @Expose(serialize = false, deserialize = false)
    private int GeoBoundriesId;
    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;

    @SerializedName("Area")
    @Expose
    private String area;

    @SerializedName("Stage")
    @Expose
    private String Stage;
//    @SerializedName("LogBookNo")
//    @Expose
//    private String LogBookNo;

    @SerializedName("PlotNo")
    @Expose
    private String PlotNo;

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
    @SerializedName("CultureArea")
    @Expose
    private String CultureArea;
    @SerializedName("Sync")
    @Expose
    private boolean Sync;
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
//    @SerializedName("serverStatus")
//    @Expose
//    private String serverStatus;
//
//    @SerializedName("SQNo")
//    @Expose
//    private String SQNo;


    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public String getCultureArea() {
        return CultureArea;
    }

    public void setCultureArea(String cultureArea) {
        CultureArea = cultureArea;
    }

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
    }

    public int getGeoBoundriesId() {
        return GeoBoundriesId;
    }

    public void setGeoBoundriesId(int geoBoundriesId) {
        GeoBoundriesId = geoBoundriesId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getSeasonCode() {
        return SeasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        SeasonCode = seasonCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPlotNo() {
        return PlotNo;
    }

    public void setPlotNo(String plotNo) {
        PlotNo = plotNo;
    }

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
}
