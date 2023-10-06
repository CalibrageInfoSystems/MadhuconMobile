package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class AddGrowthMonitoringTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("gmId")
    @Expose(serialize = false, deserialize = false)
    private int gmId;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("Sync")
    @Expose
    private Boolean Sync;

    @SerializedName("ServerStatus")
    @Expose
    private String ServerStatus;

    public String getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(String serverStatus) {
        ServerStatus = serverStatus;
    }

    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;

    @SerializedName("FarmerCode")
    @Expose
    private String FarmerCode;

    @SerializedName("PlotNo")
    @Expose
    private String PlotNo;

//    @SerializedName("FileUrl")
    @SerializedName("ImagePath")
    @Expose
    private String FileUrl;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("Stage")
    @Expose
    private String Stage;

    @SerializedName("IsActive")
    @Expose
    private Boolean Active;

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

    @SerializedName("LocalDocUrl")
    @Expose
    private String LocalDocUrl="";

    public String getLocalDocUrl() {
        return LocalDocUrl;
    }

    public void setLocalDocUrl(String localDocUrl) {
        LocalDocUrl = localDocUrl;
    }

    public int getGmId() {
        return gmId;
    }

    public void setGmId(int gmId) {
        this.gmId = gmId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Boolean getSync() {
        return Sync;
    }

    public void setSync(Boolean sync) {
        Sync = sync;
    }

    public String getSeasonCode() {
        return SeasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        SeasonCode = seasonCode;
    }

    public String getFarmerCode() {
        return FarmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        FarmerCode = farmerCode;
    }

    public String getPlotNo() {
        return PlotNo;
    }

    public void setPlotNo(String plotNo) {
        PlotNo = plotNo;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
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
