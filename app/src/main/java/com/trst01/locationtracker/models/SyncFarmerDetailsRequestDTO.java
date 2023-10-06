package com.trst01.locationtracker.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncFarmerDetailsRequestDTO {

//    @SerializedName("Id")
//    @Expose
//    private String id="null";
    @SerializedName("Id")
    @Expose
    private String id=null;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("LastName")
    @Expose
    private String LastName;

    @SerializedName("FatherName")
    @Expose
    private String FatherName;

    @SerializedName("Aadhar")
    @Expose
    private String Aadhar;

    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;

    @SerializedName("Address")
    @Expose
    private String Address;

    @SerializedName("PlotDetails")
    @Expose
    private String PlotDetails;

    @SerializedName("State")
    @Expose
    private String State;


    @SerializedName("Village")
    @Expose
    private String Village;


    @SerializedName("StateId")
    @Expose
    private String StateId;

    @SerializedName("DistrictId")
    @Expose
    private String DistrictId;

    @SerializedName("MandalId")
    @Expose
    private String MandalId;

    @SerializedName("VillageId")
    @Expose
    private String VillageId;




    @SerializedName("StateName")
    @Expose
    private String StateName;

    @SerializedName("MandalName")
    @Expose
    private String MandalName;

    @SerializedName("VillageName")
    @Expose
    private String VillageName;

    @SerializedName("DistrictName")
    @Expose
    private String DistrictName;

    @SerializedName("PinCode")
    @Expose
    private String PinCode;

    @SerializedName("IsActive")
    @Expose
    private String IsActive="1";

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId="1";

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId="1";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getAadhar() {
        return Aadhar;
    }

    public void setAadhar(String aadhar) {
        Aadhar = aadhar;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPlotDetails() {
        return PlotDetails;
    }

    public void setPlotDetails(String plotDetails) {
        PlotDetails = plotDetails;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

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

    public String getStateId() {
        return StateId;
    }

    public void setStateId(String stateId) {
        StateId = stateId;
    }

    public String getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(String districtId) {
        DistrictId = districtId;
    }

    public String getMandalId() {
        return MandalId;
    }

    public void setMandalId(String mandalId) {
        MandalId = mandalId;
    }

    public String getVillageId() {
        return VillageId;
    }

    public void setVillageId(String villageId) {
        VillageId = villageId;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getMandalName() {
        return MandalName;
    }

    public void setMandalName(String mandalName) {
        MandalName = mandalName;
    }

    public String getVillageName() {
        return VillageName;
    }

    public void setVillageName(String villageName) {
        VillageName = villageName;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }
}



