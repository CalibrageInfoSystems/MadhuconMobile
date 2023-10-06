package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class D20WeedTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("WeedIdD20Id")
    @Expose(serialize = false, deserialize = false)
    private int WeedIdD20Id;

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("PlotNo")
    @Expose
    private String PlotNo;

    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;

    @SerializedName("WeedId")
    @Expose
    private String WeedId;


//    @SerializedName("IsActive")
//    @Expose
//    private String getIsActive;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("IsActive")
    @Expose
    private String IsActive;

    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId;
//
//    @SerializedName("ErrorNumber")
//    @Expose
//    private String ErrorNumber;
//
//    @SerializedName("ErrorMessage")
//    @Expose
//    private String ErrorMessage;

    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String updatedByUserId;


    @SerializedName("Sync")
    @Expose
    private String Sync;

    @SerializedName("ServerStatus")
    @Expose
    private String ServerStatus;

    public String getSync() {
        return Sync;
    }

    public void setSync(String sync) {
        Sync = sync;
    }

    public String getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(String serverStatus) {
        ServerStatus = serverStatus;
    }


//    public int getFertilizerId() {
//        return fertilizerId;
//    }

//    public void setFertilizerId(int fertilizerId) {
//        this.fertilizerId = fertilizerId;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlotNo() {
        return PlotNo;
    }

    public void setPlotNo(String plotNo) {
        PlotNo = plotNo;
    }

    public String getSeasonCode() {
        return SeasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        SeasonCode = seasonCode;
    }

//    public void setFertilizerId(String fertilizerId) {
//        FertilizerId = fertilizerId;
//    }

//    public String getActive() {
//        return getIsActive;
//    }
//
//    public void setActive(String active) {
//        getIsActive = active;
//    }


    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
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
//
//    public String getErrorNumber() {
//        return ErrorNumber;
//    }
//
//    public void setErrorNumber(String errorNumber) {
//        ErrorNumber = errorNumber;
//    }
//
//    public String getErrorMessage() {
//        return ErrorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        ErrorMessage = errorMessage;
//    }

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

    public int getWeedIdD20Id() {
        return WeedIdD20Id;
    }

    public void setWeedIdD20Id(int weedIdD20Id) {
        WeedIdD20Id = weedIdD20Id;
    }

    public String getWeedId() {
        return WeedId;
    }

    public void setWeedId(String weedId) {
        WeedId = weedId;
    }
}