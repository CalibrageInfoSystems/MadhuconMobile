package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class D20PestTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("D20PestTableId")
    @Expose(serialize = false, deserialize = false)
    private int D20PestTableId;

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("PlotNo")
    @Expose
    private String PlotNo;

    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;

    @SerializedName("IdentifiedDate")
    @Expose
    private String IdentifiedDate;

    @SerializedName("ControlDate")
    @Expose
    private String ControlDate;

    @SerializedName("PestId")
    @Expose
    private String PestId;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId;

    @SerializedName("ErrorNumber")
    @Expose
    private String ErrorNumber;

    @SerializedName("ErrorMessage")
    @Expose
    private String ErrorMessage;

    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String updatedByUserId;
    @SerializedName("Sync")
    @Expose
    private Boolean Sync;

    @SerializedName("ServerStatus")
    @Expose
    private String ServerStatus;

    public Boolean getSync() {
        return Sync;
    }

    public void setSync(Boolean sync) {
        Sync = sync;
    }

    public String getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(String serverStatus) {
        ServerStatus = serverStatus;
    }



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

//    public String getDiseasesId() {
//        return DiseasesId;
//    }
//
//    public void setDiseasesId(String diseasesId) {
//        DiseasesId = diseasesId;
//    }

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

    public String getErrorNumber() {
        return ErrorNumber;
    }

    public void setErrorNumber(String errorNumber) {
        ErrorNumber = errorNumber;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
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

//    public int getDiseasesIdId() {
//        return DiseasesIdId;
//    }
//
//    public void setDiseasesIdId(int diseasesIdId) {
//        DiseasesIdId = diseasesIdId;
//    }

    public String getIdentifiedDate() {
        return IdentifiedDate;
    }

    public void setIdentifiedDate(String identifiedDate) {
        IdentifiedDate = identifiedDate;
    }

    public String getControlDate() {
        return ControlDate;
    }

    public void setControlDate(String controlDate) {
        ControlDate = controlDate;
    }

    public int getD20PestTableId() {
        return D20PestTableId;
    }

    public void setD20PestTableId(int d20PestTableId) {
        D20PestTableId = d20PestTableId;
    }

    public String getPestId() {
        return PestId;
    }

    public void setPestId(String pestId) {
        PestId = pestId;
    }

    //    public String getFertilizerId() {
//        return FertilizerId;
//    }
}