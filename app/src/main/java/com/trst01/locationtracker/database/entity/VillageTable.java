package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class VillageTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("VillageId")
    @Expose(serialize = false, deserialize = false)
    private int villageId;


    @SerializedName("Id")
    @Expose
    private Integer id;


    @SerializedName("SectionName")
    @Expose
    private String sectionName;


    @SerializedName("MandalName")
    @Expose
    private String  mandalName;


    @SerializedName("MandalId")
    @Expose
    private Integer mandalId;


    @SerializedName("DistrictId")
    @Expose
    private Integer districtId;


    @SerializedName("DistrictName")
    @Expose
    private String  districtName;


    @SerializedName("DivisionId")
    @Expose
    private Integer divisonId;


    @SerializedName("DivisionName")
    @Expose
    private String DivisonName;


    @SerializedName("CircleId")
    @Expose
    private Integer circleId;


    @SerializedName("CircleName")
    @Expose
    private String circleName;


    @SerializedName("SectionId")
    @Expose
    private Integer sectionId;


    @SerializedName("Code")
    @Expose
    private String  Code;


    @SerializedName("Name")
    @Expose
    private String name;


    @SerializedName("Incharge")
    @Expose
    private String incharge;


    @SerializedName("InchargePhone")
    @Expose
    private String  inchargePhone;


    @SerializedName("Address")
    @Expose
    private String address;


    @SerializedName("Pincode")
    @Expose
    private Integer pincode;


    @SerializedName("Distance")
    @Expose
    private Double distance;


    @SerializedName("TPTRate")
    @Expose
    private Double TPTRate;


    @SerializedName("DivertedDistance")
    @Expose
    private Double divertedDistance;


    @SerializedName("PotentialArea")
    @Expose
    private Double potentialArea;


    @SerializedName("GeoArea")
    @Expose
    private Double geoArea;


    @SerializedName("DryArea")
    @Expose
    private Double dryArea;


    @SerializedName("IrrigationArea")
    @Expose
    private Double irrigationArea;


    @SerializedName("NotSuitableArea")
    @Expose
    private Double notSuitableArea;


    @SerializedName("ColtivableArea")
    @Expose
    private Double coltivableArea;


    @SerializedName("NoOfEBServices")
    @Expose
    private Integer noOfEBServices;


    @SerializedName("Ord")
    @Expose
    private Integer ord;


    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;


    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;


    @SerializedName("UpdatedUserId")
    @Expose
    private String updatedUserId;


    @SerializedName("CreatedByUserId")
    @Expose
    private String createByUserId;


    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;


    @SerializedName("UpdateByUser")
    @Expose
    private String updateByUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }

    public Integer getMandalId() {
        return mandalId;
    }

    public void setMandalId(Integer mandalId) {
        this.mandalId = mandalId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getDivisonId() {
        return divisonId;
    }

    public void setDivisonId(Integer divisonId) {
        this.divisonId = divisonId;
    }

    public String getDivisonName() {
        return DivisonName;
    }

    public void setDivisonName(String divisonName) {
        DivisonName = divisonName;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getInchargePhone() {
        return inchargePhone;
    }

    public void setInchargePhone(String inchargePhone) {
        this.inchargePhone = inchargePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


    public Double getDivertedDistance() {
        return divertedDistance;
    }

    public void setDivertedDistance(Double divertedDistance) {
        this.divertedDistance = divertedDistance;
    }

    public Double getPotentialArea() {
        return potentialArea;
    }

    public void setPotentialArea(Double potentialArea) {
        this.potentialArea = potentialArea;
    }

    public Double getGeoArea() {
        return geoArea;
    }

    public void setGeoArea(Double geoArea) {
        this.geoArea = geoArea;
    }

    public Double getDryArea() {
        return dryArea;
    }

    public void setDryArea(Double dryArea) {
        this.dryArea = dryArea;
    }

    public Double getIrrigationArea() {
        return irrigationArea;
    }

    public void setIrrigationArea(Double irrigationArea) {
        this.irrigationArea = irrigationArea;
    }

    public Double getNotSuitableArea() {
        return notSuitableArea;
    }

    public void setNotSuitableArea(Double notSuitableArea) {
        this.notSuitableArea = notSuitableArea;
    }

    public Double getColtivableArea() {
        return coltivableArea;
    }

    public void setColtivableArea(Double coltivableArea) {
        this.coltivableArea = coltivableArea;
    }

    public Integer getNoOfEBServices() {
        return noOfEBServices;
    }

    public void setNoOfEBServices(Integer noOfEBServices) {
        this.noOfEBServices = noOfEBServices;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

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

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public String getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(String createByUserId) {
        this.createByUserId = createByUserId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateByUser() {
        return updateByUser;
    }

    public void setUpdateByUser(String updateByUser) {
        this.updateByUser = updateByUser;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

//    @SerializedName("Id")
//    @Expose
//    private Integer id;
//
//    @SerializedName("SectionId")
//    @Expose
//    private Integer sectionId;
//
//    @SerializedName("MandalId")
//    @Expose
//    private Integer mandalId;
//
//    @SerializedName("Code")
//    @Expose
//    private String code;
//
//    @SerializedName("Name")
//    @Expose
//    private String  name;
//
//    @SerializedName("Incharge")
//    @Expose
//    private String incharge;
//
//    @SerializedName("InchargePhone")
//    @Expose
//    private String inchargePhone;
//
//    @SerializedName("Address")
//    @Expose
//    private String address;
//
//    @SerializedName("PinCode")
//    @Expose
//    private String pinCode;
//
//    @SerializedName("Distance")
//    @Expose
//    private Double distance;
//
//    @SerializedName("DivertedDistance")
//    @Expose
//    private Double divertedDistance;
//
//    @SerializedName("PotentialArea")
//    @Expose
//    private Double potentialArea;
//
//    @SerializedName("GeoArea")
//    @Expose
//    private Double geoArea;
//
//    @SerializedName("DryArea")
//    @Expose
//    private Double dryArea;
//
//    @SerializedName("NotSuitableArea")
//    @Expose
//    private Double notSuitableArea;
//
//    @SerializedName("IrrigationArea")
//    @Expose
//    private Double irrigationArea;
//
//    @SerializedName("ColtivableArea")
//    @Expose
//    private Double coltivableArea;
//
//    @SerializedName("NoOfEBServices")
//    @Expose
//    private Integer noOfEBServices;
//
//
//    @SerializedName("Ord")
//    @Expose
//    private Integer ord;
//
//
//    @SerializedName("TPTRate")
//    @Expose
//    private Double TPTRate;
//
//
//    @SerializedName("IsActive")
//    @Expose
//    private Boolean isActive;
//
//    @SerializedName("CreatedDate")
//    @Expose
//    private String createdDate;
//
//    @SerializedName("CreatedByUserId")
//    @Expose
//    private String createdByUserId;
//
//    @SerializedName("UpdatedDate")
//    @Expose
//    private String updatedDate;
//
//    @SerializedName("UpdatedByUserId")
//    @Expose
//    private Integer updatedByUserId;
//
//    @NonNull
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(@NonNull Integer id) {
//        this.id = id;
//    }
//
//    public Integer getSectionId() {
//        return sectionId;
//    }
//
//    public void setSectionId(Integer sectionId) {
//        this.sectionId = sectionId;
//    }
//
//    public Integer getMandalId() {
//        return mandalId;
//    }
//
//    public void setMandalId(Integer mandalId) {
//        this.mandalId = mandalId;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getIncharge() {
//        return incharge;
//    }
//
//    public void setIncharge(String incharge) {
//        this.incharge = incharge;
//    }
//
//    public String getInchargePhone() {
//        return inchargePhone;
//    }
//
//    public void setInchargePhone(String inchargePhone) {
//        this.inchargePhone = inchargePhone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPinCode() {
//        return pinCode;
//    }
//
//    public void setPinCode(String pinCode) {
//        this.pinCode = pinCode;
//    }
//
//    public Double getDistance() {
//        return distance;
//    }
//
//    public void setDistance(Double distance) {
//        this.distance = distance;
//    }
//
//    public Double getDivertedDistance() {
//        return divertedDistance;
//    }
//
//    public void setDivertedDistance(Double divertedDistance) {
//        this.divertedDistance = divertedDistance;
//    }
//
//    public Double getPotentialArea() {
//        return potentialArea;
//    }
//
//    public void setPotentialArea(Double potentialArea) {
//        this.potentialArea = potentialArea;
//    }
//
//    public Double getGeoArea() {
//        return geoArea;
//    }
//
//    public void setGeoArea(Double geoArea) {
//        this.geoArea = geoArea;
//    }
//
//    public Double getDryArea() {
//        return dryArea;
//    }
//
//    public void setDryArea(Double dryArea) {
//        this.dryArea = dryArea;
//    }
//
//    public Double getNotSuitableArea() {
//        return notSuitableArea;
//    }
//
//    public void setNotSuitableArea(Double notSuitableArea) {
//        this.notSuitableArea = notSuitableArea;
//    }
//
//    public Double getIrrigationArea() {
//        return irrigationArea;
//    }
//
//    public void setIrrigationArea(Double irrigationArea) {
//        this.irrigationArea = irrigationArea;
//    }
//
//    public Double getColtivableArea() {
//        return coltivableArea;
//    }
//
//    public void setColtivableArea(Double coltivableArea) {
//        this.coltivableArea = coltivableArea;
//    }
//
//    public Integer getNoOfEBServices() {
//        return noOfEBServices;
//    }
//
//    public void setNoOfEBServices(Integer noOfEBServices) {
//        this.noOfEBServices = noOfEBServices;
//    }
//
//    public Integer getOrd() {
//        return ord;
//    }
//
//    public void setOrd(Integer ord) {
//        this.ord = ord;
//    }

    public Double getTPTRate() {
        return TPTRate;
    }

    public void setTPTRate(Double TPTRate) {
        this.TPTRate = TPTRate;
    }

//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
//
//    public String getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(String createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public String getCreatedByUserId() {
//        return createdByUserId;
//    }
//
//    public void setCreatedByUserId(String createdByUserId) {
//        this.createdByUserId = createdByUserId;
//    }
//
//    public String getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(String updatedDate) {
//        this.updatedDate = updatedDate;
//    }
//
//    public Integer getUpdatedByUserId() {
//        return updatedByUserId;
//    }
//
//    public void setUpdatedByUserId(Integer updatedByUserId) {
//        this.updatedByUserId = updatedByUserId;
//    }
}