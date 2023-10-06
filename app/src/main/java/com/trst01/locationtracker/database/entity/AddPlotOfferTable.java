package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class AddPlotOfferTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("PlotOfferId")
    @Expose(serialize = false, deserialize = false)
    private int PlotOfferId;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;

    @SerializedName("IsNewFarmer")
    @Expose
    private String NewFarmer;

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("OfferNo")
    @Expose
    private String OfferNo;

    @SerializedName("OfferDate")
    @Expose
    private String OfferDate;

    @SerializedName("FarmerCode")
    @Expose
    private String FarmerCode;

    @SerializedName("FarmerName")
    @Expose
    private String FarmerName;

    @SerializedName("FatherName")
    @Expose
    private String FatherName;

    @SerializedName("FarmerVillageId")
    @Expose
    private String FarmerVillageId;

    @SerializedName("PlotVillageId")
    @Expose
    private String PlotVillageId;

    @SerializedName("PlotIndNo")
    @Expose
    private String PlotIndNo;

    @SerializedName("PlantTypeId")
    @Expose
    private String PlantTypeId;

    @SerializedName("ExpectedVarityId")
    @Expose
    private String ExpectedVarityId;

    @SerializedName("ExpectedPlantingDate")
    @Expose
    private String ExpectedPlantingDate;

    @SerializedName("ExpectedArea")
    @Expose
    private String ExpectedArea;

    @SerializedName("ReportedArea")
    @Expose
    private String ReportedArea;

    @SerializedName("ReasonForNotPlantingId")
    @Expose
    private String ReasonForNotPlantingId;

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

    @SerializedName("ServerStatus")
    @Expose
    private String ServerStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(String serverStatus) {
        ServerStatus = serverStatus;
    }

    public int getPlotOfferId() {
        return PlotOfferId;
    }

    public void setPlotOfferId(int plotOfferId) {
        PlotOfferId = plotOfferId;
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

    public String getNewFarmer() {
        return NewFarmer;
    }

    public void setNewFarmer(String newFarmer) {
        NewFarmer = newFarmer;
    }

    public String getOfferNo() {
        return OfferNo;
    }

    public void setOfferNo(String offerNo) {
        OfferNo = offerNo;
    }

    public String getOfferDate() {
        return OfferDate;
    }

    public void setOfferDate(String offerDate) {
        OfferDate = offerDate;
    }

    public String getFarmerCode() {
        return FarmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        FarmerCode = farmerCode;
    }

    public String getFarmerName() {
        return FarmerName;
    }

    public void setFarmerName(String farmerName) {
        FarmerName = farmerName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getFarmerVillageId() {
        return FarmerVillageId;
    }

    public void setFarmerVillageId(String farmerVillageId) {
        FarmerVillageId = farmerVillageId;
    }

    public String getPlotVillageId() {
        return PlotVillageId;
    }

    public void setPlotVillageId(String plotVillageId) {
        PlotVillageId = plotVillageId;
    }

    public String getPlotIndNo() {
        return PlotIndNo;
    }

    public void setPlotIndNo(String plotIndNo) {
        PlotIndNo = plotIndNo;
    }

    public String getPlantTypeId() {
        return PlantTypeId;
    }

    public void setPlantTypeId(String plantTypeId) {
        PlantTypeId = plantTypeId;
    }

    public String getExpectedVarityId() {
        return ExpectedVarityId;
    }

    public void setExpectedVarityId(String expectedVarityId) {
        ExpectedVarityId = expectedVarityId;
    }

    public String getExpectedPlantingDate() {
        return ExpectedPlantingDate;
    }

    public void setExpectedPlantingDate(String expectedPlantingDate) {
        ExpectedPlantingDate = expectedPlantingDate;
    }

    public String getExpectedArea() {
        return ExpectedArea;
    }

    public void setExpectedArea(String expectedArea) {
        ExpectedArea = expectedArea;
    }

    public String getReportedArea() {
        return ReportedArea;
    }

    public void setReportedArea(String reportedArea) {
        ReportedArea = reportedArea;
    }

    public String getReasonForNotPlantingId() {
        return ReasonForNotPlantingId;
    }

    public void setReasonForNotPlantingId(String reasonForNotPlantingId) {
        ReasonForNotPlantingId = reasonForNotPlantingId;
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
//    public int getFarmerGenId() {
//        return FarmerGenId;
//    }
//
//    public void setFarmerGenId(int farmerGenId) {
//        FarmerGenId = farmerGenId;
//    }

//
//    public int getPlotOfferId() {
//        return PlotOfferId;
//    }
//
//    public void setPlotOfferId(int plotOfferId) {
//        PlotOfferId = plotOfferId;
//    }
//
//    public Integer getId() {
//        return Id;
//    }
//
//    public void setId(Integer id) {
//        Id = id;
//    }
//
//    public String getCode() {
//        return Code;
//    }
//
//    public void setCode(String code) {
//        Code = code;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getAliasName() {
//        return AliasName;
//    }
//
//    public void setAliasName(String aliasName) {
//        AliasName = aliasName;
//    }
//
//    public String getGender() {
//        return Gender;
//    }
//
//    public void setGender(String gender) {
//        Gender = gender;
//    }
//
//    public String getFatherName() {
//        return FatherName;
//    }
//
//    public void setFatherName(String fatherName) {
//        FatherName = fatherName;
//    }
//
//    public String getCastId() {
//        return CastId;
//    }
//
//    public void setCastId(String castId) {
//        CastId = castId;
//    }
//
//    public String getAddress() {
//        return Address;
//    }
//
//    public void setAddress(String address) {
//        Address = address;
//    }
//
//    public String getVillageId() {
//        return VillageId;
//    }
//
//    public void setVillageId(String villageId) {
//        VillageId = villageId;
//    }
//
//    public String getMobile() {
//        return Mobile;
//    }
//
//    public void setMobile(String mobile) {
//        Mobile = mobile;
//    }
//
//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }
//
//    public String getPanNo() {
//        return PanNo;
//    }
//
//    public void setPanNo(String panNo) {
//        PanNo = panNo;
//    }
//
//    public String getAadharNo() {
//        return AadharNo;
//    }
//
//    public void setAadharNo(String aadharNo) {
//        AadharNo = aadharNo;
//    }
//
//    public String getOldCode() {
//        return OldCode;
//    }
//
//    public void setOldCode(String oldCode) {
//        OldCode = oldCode;
//    }
//
//    public String getJFNo() {
//        return JFNo;
//    }
//
//    public void setJFNo(String JFNo) {
//        this.JFNo = JFNo;
//    }
//
//    public String getBranchId() {
//        return BranchId;
//    }
//
//    public void setBranchId(String branchId) {
//        BranchId = branchId;
//    }
//
//    public String getACNo() {
//        return ACNo;
//    }
//
//    public void setACNo(String ACNo) {
//        this.ACNo = ACNo;
//    }
//
//    public String getTotalArea() {
//        return TotalArea;
//    }
//
//    public void setTotalArea(String totalArea) {
//        TotalArea = totalArea;
//    }
//
//    public String getCultivatedArea() {
//        return CultivatedArea;
//    }
//
//    public void setCultivatedArea(String cultivatedArea) {
//        CultivatedArea = cultivatedArea;
//    }
//
//    public String getGLCode() {
//        return GLCode;
//    }
//
//    public void setGLCode(String GLCode) {
//        this.GLCode = GLCode;
//    }
//
//    public String getSubGLCode() {
//        return SubGLCode;
//    }
//
//    public void setSubGLCode(String subGLCode) {
//        SubGLCode = subGLCode;
//    }
//
//    public String getOtherCode() {
//        return OtherCode;
//    }
//
//    public void setOtherCode(String otherCode) {
//        OtherCode = otherCode;
//    }
//
//    public String getImageUrl() {
//        return ImageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        ImageUrl = imageUrl;
//    }
//
//    public String getRegistered() {
//        return Registered;
//    }
//
//    public void setRegistered(String registered) {
//        Registered = registered;
//    }
//
//    public String getActive() {
//        return Active;
//    }
//
//    public void setActive(String isActive) {
//        Active = isActive;
//    }
//
//    public String getCreatedDate() {
//        return CreatedDate;
//    }
//
//    public void setCreatedDate(String createdDate) {
//        CreatedDate = createdDate;
//    }
//
//    public String getCreatedByUserId() {
//        return CreatedByUserId;
//    }
//
//    public void setCreatedByUserId(String createdByUserId) {
//        CreatedByUserId = createdByUserId;
//    }
//
//    public String getUpdatedDate() {
//        return UpdatedDate;
//    }
//
//    public void setUpdatedDate(String updatedDate) {
//        UpdatedDate = updatedDate;
//    }
//
//    public String getUpdatedByUserId() {
//        return UpdatedByUserId;
//    }
//
//    public void setUpdatedByUserId(String updatedByUserId) {
//        UpdatedByUserId = updatedByUserId;
//    }