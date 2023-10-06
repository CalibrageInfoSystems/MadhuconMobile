package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class AddFarmerTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("FarmerGenId")
    @Expose(serialize = false, deserialize = false)
    private int FarmerGenId;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("Code")
    @Expose
    private String Code;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("AliasName")
    @Expose
    private String AliasName;

    @SerializedName("Gender")
    @Expose
    private String Gender;

    @SerializedName("FatherName")
    @Expose
    private String FatherName;

    @SerializedName("CastId")
    @Expose
    private String CastId;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("VillageId")
    @Expose
    private String VillageId;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("PanNo")
    @Expose
    private String PanNo;
    @SerializedName("AadharNo")
    @Expose
    private String AadharNo;
    @SerializedName("OldCode")
    @Expose
    private String OldCode;

    @SerializedName("JFNo")
    @Expose
    private String JFNo;

    @SerializedName("BranchId")
    @Expose
    private String BranchId;

    @SerializedName("ACNo")
    @Expose
    private String ACNo;
    @SerializedName("TotalArea")
    @Expose
    private String TotalArea;
    @SerializedName("CultivatedArea")
    @Expose
    private String CultivatedArea;
    @SerializedName("GLCode")
    @Expose
    private String GLCode;
    @SerializedName("SubGLCode")
    @Expose
    private String SubGLCode;
    @SerializedName("OtherCode")
    @Expose
    private String OtherCode;
    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;
    @SerializedName("Registered")
    @Expose
    private String Registered;

    @SerializedName("IsActive")
    @Expose
    private String Active;
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

    public int getFarmerGenId() {
        return FarmerGenId;
    }

    public void setFarmerGenId(int farmerGenId) {
        FarmerGenId = farmerGenId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAliasName() {
        return AliasName;
    }

    public void setAliasName(String aliasName) {
        AliasName = aliasName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getCastId() {
        return CastId;
    }

    public void setCastId(String castId) {
        CastId = castId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getVillageId() {
        return VillageId;
    }

    public void setVillageId(String villageId) {
        VillageId = villageId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPanNo() {
        return PanNo;
    }

    public void setPanNo(String panNo) {
        PanNo = panNo;
    }

    public String getAadharNo() {
        return AadharNo;
    }

    public void setAadharNo(String aadharNo) {
        AadharNo = aadharNo;
    }

    public String getOldCode() {
        return OldCode;
    }

    public void setOldCode(String oldCode) {
        OldCode = oldCode;
    }

    public String getJFNo() {
        return JFNo;
    }

    public void setJFNo(String JFNo) {
        this.JFNo = JFNo;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getACNo() {
        return ACNo;
    }

    public void setACNo(String ACNo) {
        this.ACNo = ACNo;
    }

    public String getTotalArea() {
        return TotalArea;
    }

    public void setTotalArea(String totalArea) {
        TotalArea = totalArea;
    }

    public String getCultivatedArea() {
        return CultivatedArea;
    }

    public void setCultivatedArea(String cultivatedArea) {
        CultivatedArea = cultivatedArea;
    }

    public String getGLCode() {
        return GLCode;
    }

    public void setGLCode(String GLCode) {
        this.GLCode = GLCode;
    }

    public String getSubGLCode() {
        return SubGLCode;
    }

    public void setSubGLCode(String subGLCode) {
        SubGLCode = subGLCode;
    }

    public String getOtherCode() {
        return OtherCode;
    }

    public void setOtherCode(String otherCode) {
        OtherCode = otherCode;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getRegistered() {
        return Registered;
    }

    public void setRegistered(String registered) {
        Registered = registered;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String isActive) {
        Active = isActive;
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
