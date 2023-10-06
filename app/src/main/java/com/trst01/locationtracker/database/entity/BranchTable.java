package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class BranchTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("BranchId")
    @Expose(serialize = false, deserialize = false)
    private int branchId;

    @SerializedName("Abbr")
    @Expose
    private String Abbr;

    @SerializedName("Address")
    @Expose
    private String Address ;

    @SerializedName("BankId")
    @Expose
    private Integer BankId;

    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Email")
    @Expose
    private String Email;


    @SerializedName("Id")
    @Expose
    private Integer id;


    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId ;

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId ;

    @SerializedName("IFSC")
    @Expose
    private String IFSC ;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("Name")
    @Expose
    private String Name ;

    @SerializedName("PinCode")
    @Expose
    private String pinCode ;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getAbbr() {
        return Abbr;
    }

    public void setAbbr(String abbr) {
        Abbr = abbr;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getBankId() {
        return BankId;
    }

    public void setBankId(Integer bankId) {
        BankId = bankId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}