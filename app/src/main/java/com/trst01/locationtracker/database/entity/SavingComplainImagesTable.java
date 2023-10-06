package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class SavingComplainImagesTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("SavingComplainImagesID")
    @Expose(serialize = false, deserialize = false)
    private int SavingComplainImagesID;

    @SerializedName("ComplaintCode")
    @Expose
    private String ComplaintCode = "";
    @SerializedName("ModuleTypeId")
    @Expose
    private String ModuleTypeId = "";

    @SerializedName("FarmerCode")
    @Expose
    private String FarmerCode;


    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;


    @SerializedName("FileName")
    @Expose
    private String FileName = "";
    @SerializedName("FileLocation")
    @Expose
    private String FileLocation = "";

    @SerializedName("FileExtension")
    @Expose
    private String FileExtension = "";

    @SerializedName("ServerUpdatedStatus")
    @Expose
    private boolean ServerUpdatedStatus;

    @SerializedName("LogBookNo")
    @Expose
    private String LogBookNo;

    @SerializedName("LocalDocUrl")
    @Expose
    private String LocalDocUrl="";

    @SerializedName("IsVideoRecording")
    @Expose
    private String IsVideoRecording="false";

    @SerializedName("IsResult")
    @Expose
    private String IsResult="true";

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId = "";

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate = "";
    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId = "";

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate = "";


    @SerializedName("IsActive")
    @Expose
    private String IsActive="true";

    @SerializedName("serverStatus")
    @Expose
    private String serverStatus = "";

    public String getFarmerCode() {
        return FarmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        FarmerCode = farmerCode;
    }

    public String getSeasonCode() {
        return SeasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        SeasonCode = seasonCode;
    }

    public int getSavingComplainImagesID() {
        return SavingComplainImagesID;
    }

    public void setSavingComplainImagesID(int savingComplainImagesID) {
        SavingComplainImagesID = savingComplainImagesID;
    }

    public String getComplaintCode() {
        return ComplaintCode;
    }

    public void setComplaintCode(String complaintCode) {
        ComplaintCode = complaintCode;
    }

    public String getModuleTypeId() {
        return ModuleTypeId;
    }

    public void setModuleTypeId(String moduleTypeId) {
        ModuleTypeId = moduleTypeId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileLocation() {
        return FileLocation;
    }

    public void setFileLocation(String fileLocation) {
        FileLocation = fileLocation;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getCreatedByUserId() {
        return CreatedByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getUpdatedByUserId() {
        return UpdatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public boolean isServerUpdatedStatus() {
        return ServerUpdatedStatus;
    }

    public void setServerUpdatedStatus(boolean serverUpdatedStatus) {
        ServerUpdatedStatus = serverUpdatedStatus;
    }

    public String getLogBookNo() {
        return LogBookNo;
    }

    public void setLogBookNo(String logBookNo) {
        LogBookNo = logBookNo;
    }

    public String getLocalDocUrl() {
        return LocalDocUrl;
    }

    public void setLocalDocUrl(String localDocUrl) {
        LocalDocUrl = localDocUrl;
    }


    public String getIsVideoRecording() {
        return IsVideoRecording;
    }

    public void setIsVideoRecording(String isVideoRecording) {
        IsVideoRecording = isVideoRecording;
    }

    public String getIsResult() {
        return IsResult;
    }

    public void setIsResult(String isResult) {
        IsResult = isResult;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }
}
