package com.trst01.locationtracker.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SyncPersonalLandAllDetailsRequestDTO {

    ArrayList<Farmer> Farmer = new ArrayList<Farmer>();
    ArrayList<PLot> PLot = new ArrayList<PLot>();
    ArrayList<Bank> Bank= new ArrayList<Bank>();
    ArrayList<Doc> Doc= new ArrayList<Doc>();
    ArrayList<GeoBoundaries> GeoBoundaries= new ArrayList<GeoBoundaries>();

    // TODO: 2/21/2022 logbook functionality
    ArrayList<LogBook> LogBook= new ArrayList<LogBook>();
    ArrayList<LandPreparation> LandPreparation= new ArrayList<LandPreparation>(); //SyncDto has no sync,but available in entity, please confirm
    ArrayList<LogbookGeoBoundaries> LogbookGeoBoundaries= new ArrayList<LogbookGeoBoundaries>();
    ArrayList<NurseryPreparation> NurseryPreparation= new ArrayList<NurseryPreparation>();
    ArrayList<Transplanting> Transplanting= new ArrayList<Transplanting>();
    ArrayList<Fertilizer> Fertilizer= new ArrayList<Fertilizer>();
    ArrayList<SeedRate> SeedRate= new ArrayList<SeedRate>();
    ArrayList<WaterManagement> WaterManagement= new ArrayList<WaterManagement>();
    ArrayList<WeedManagement> WeedManagement= new ArrayList<WeedManagement>();
    ArrayList<PestDiseaseControl> PestDiseaseControl= new ArrayList<PestDiseaseControl>();
    ArrayList<Harvesting> Harvesting= new ArrayList<Harvesting>();
    ArrayList<Moisture> Moisture= new ArrayList<Moisture>();
    ArrayList<Yield> Yield= new ArrayList<Yield>();


    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.PLot> getPLot() {
        return PLot;
    }

    public void setPLot(ArrayList<SyncPersonalLandAllDetailsRequestDTO.PLot> PLot) {
        this.PLot = PLot;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.GeoBoundaries> getGeoBoundaries() {
        return GeoBoundaries;
    }

    public void setGeoBoundaries(ArrayList<SyncPersonalLandAllDetailsRequestDTO.GeoBoundaries> geoBoundaries) {
        GeoBoundaries = geoBoundaries;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.LandPreparation> getLandPreparation() {
        return LandPreparation;
    }

    public void setLandPreparation(ArrayList<SyncPersonalLandAllDetailsRequestDTO.LandPreparation> landPreparation) {
        LandPreparation = landPreparation;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogbookGeoBoundaries> getLogbookGeoBoundaries() {
        return LogbookGeoBoundaries;
    }

    public void setLogbookGeoBoundaries(ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogbookGeoBoundaries> logbookGeoBoundaries) {
        LogbookGeoBoundaries = logbookGeoBoundaries;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.NurseryPreparation> getNurseryPreparation() {
        return NurseryPreparation;
    }

    public void setNurseryPreparation(ArrayList<SyncPersonalLandAllDetailsRequestDTO.NurseryPreparation> nurseryPreparation) {
        NurseryPreparation = nurseryPreparation;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Transplanting> getTransplanting() {
        return Transplanting;
    }

    public void setTransplanting(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Transplanting> transplanting) {
        Transplanting = transplanting;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.SeedRate> getSeedRate() {
        return SeedRate;
    }

    public void setSeedRate(ArrayList<SyncPersonalLandAllDetailsRequestDTO.SeedRate> seedRate) {
        SeedRate = seedRate;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterManagement> getWaterManagement() {
        return WaterManagement;
    }

    public void setWaterManagement(ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterManagement> waterManagement) {
        WaterManagement = waterManagement;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.WeedManagement> getWeedManagement() {
        return WeedManagement;
    }

    public void setWeedManagement(ArrayList<SyncPersonalLandAllDetailsRequestDTO.WeedManagement> weedManagement) {
        WeedManagement = weedManagement;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.PestDiseaseControl> getPestDiseaseControl() {
        return PestDiseaseControl;
    }

    public void setPestDiseaseControl(ArrayList<SyncPersonalLandAllDetailsRequestDTO.PestDiseaseControl> pestDiseaseControl) {
        PestDiseaseControl = pestDiseaseControl;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Harvesting> getHarvesting() {
        return Harvesting;
    }

    public void setHarvesting(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Harvesting> harvesting) {
        Harvesting = harvesting;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Moisture> getMoisture() {
        return Moisture;
    }

    public void setMoisture(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Moisture> moisture) {
        Moisture = moisture;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Yield> getYield() {
        return Yield;
    }

    public void setYield(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Yield> yield) {
        Yield = yield;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Fertilizer> getFertilizer() {
        return Fertilizer;
    }

    public void setFertilizer(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Fertilizer> fertilizer) {
        Fertilizer = fertilizer;
    }

//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.OrganicAmendments> getOrganicAmendments() {
//        return OrganicAmendments;
//    }
//
//    public void setOrganicAmendments(ArrayList<SyncPersonalLandAllDetailsRequestDTO.OrganicAmendments> organicAmendments) {
//        OrganicAmendments = organicAmendments;
//    }
//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeonSeason> getWaterRegimeonSeason() {
//        return WaterRegimeonSeason;
//    }
//
//    public void setWaterRegimeonSeason(ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeonSeason> waterRegimeonSeason) {
//        WaterRegimeonSeason = waterRegimeonSeason;
//    }
//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterReasonPreSeason> getWaterReasonPreSeason() {
//        return WaterReasonPreSeason;
//    }
//
//    public void setWaterReasonPreSeason(ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterReasonPreSeason> waterReasonPreSeason) {
//        WaterReasonPreSeason = waterReasonPreSeason;
//    }
//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.BorewellPumpOperations> getBorewellPumpOperations() {
//        return BorewellPumpOperations;
//    }
//
//    public void setBorewellPumpOperations(ArrayList<SyncPersonalLandAllDetailsRequestDTO.BorewellPumpOperations> borewellPumpOperations) {
//        BorewellPumpOperations = borewellPumpOperations;
//    }
//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeOnthefield> getWaterRegimeOnthefield() {
//        return WaterRegimeOnthefield;
//    }
//
//    public void setWaterRegimeOnthefield(ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeOnthefield> waterRegimeOnthefield) {
//        WaterRegimeOnthefield = waterRegimeOnthefield;
//    }
//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.HarvestDetails> getHarvestDetails() {
//        return HarvestDetails;
//    }
//
//    public void setHarvestDetails(ArrayList<SyncPersonalLandAllDetailsRequestDTO.HarvestDetails> harvestDetails) {
//        HarvestDetails = harvestDetails;
//    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogBook> getLogBook() {
        return LogBook;
    }

    public void setLogBook(ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogBook> logBook) {
        LogBook = logBook;
    }

//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.GPS> getGPS() {
//        return GPS;
//    }
//
//    public void setGPS(ArrayList<SyncPersonalLandAllDetailsRequestDTO.GPS> GPS) {
//        this.GPS = GPS;
//    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Farmer> getFarmer() {
        return Farmer;
    }

    public void setFarmer(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Farmer> farmer) {
        Farmer = farmer;
    }
//
//    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Land> getLand() {
//        return Land;
//    }
//
//    public void setLand(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Land> land) {
//        Land = land;
//    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Bank> getBank() {
        return Bank;
    }

    public void setBank(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Bank> bank) {
        Bank = bank;
    }

    public ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> getDoc() {
        return Doc;
    }

    public void setDoc(ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> doc) {
        Doc = doc;
    }

    public static class Farmer {

        @SerializedName("FarmerId")
        @Expose
        private String FarmerCode = "";//int

        @SerializedName("FarmerTitle")
        @Expose
        private String FarmerTittle = "";


        @SerializedName("FirstName")
        @Expose
        private String FirstName = "";

        @SerializedName("MiddleName")
        @Expose
        private String MiddleName = "";

        @SerializedName("LastName")
        @Expose
        private String LastName = "";


        @SerializedName("FatherName")
        @Expose
        private String FatherName = "";

        @SerializedName("Gender")
        @Expose
        private String Gender = "";


        @SerializedName("Age")
        @Expose
        private String Age = "";

//        @SerializedName("Aadhar")
//        @Expose
//        private String Aadhar = "";

        @SerializedName("MobileNumber")
        @Expose
        private String PrimaryContactNum = "";

//        @SerializedName("SecondaryContactNum")
//        @Expose
//        private String SecondaryContactNum = "";

        @SerializedName("Address")
        @Expose
        private String Address = "";

        @SerializedName("PinCode")
        @Expose
        private String PinCode = "";

        @SerializedName("VillageId")
        @Expose
        private String VillageId = "";
//
//        @SerializedName("MandalId")
//        @Expose
//        private String MandalId = "";
//
//
//        @SerializedName("DistrictId")
//        @Expose
//        private String DistrictId = "";
//
//
//        @SerializedName("StateId")
//        @Expose
//        private String StateId = "";

        @SerializedName("sync")
        @Expose
        private boolean sync;
//
//
//        @SerializedName("IsPlot")
//        @Expose
//        private String IsPlot = "";

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";

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
        private String UpdatedByUserId ;


        public String getFarmerCode() {
            return FarmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            FarmerCode = farmerCode;
        }

        public String getFarmerTittle() {
            return FarmerTittle;
        }

        public void setFarmerTittle(String farmerTittle) {
            FarmerTittle = farmerTittle;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getMiddleName() {
            return MiddleName;
        }

        public void setMiddleName(String middleName) {
            MiddleName = middleName;
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

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String age) {
            Age = age;
        }

        public String getPrimaryContactNum() {
            return PrimaryContactNum;
        }

        public void setPrimaryContactNum(String primaryContactNum) {
            PrimaryContactNum = primaryContactNum;
        }

//        public String getSecondaryContactNum() {
//            return SecondaryContactNum;
//        }
//
//        public void setSecondaryContactNum(String secondaryContactNum) {
//            SecondaryContactNum = secondaryContactNum;
//        }


        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String pinCode) {
            PinCode = pinCode;
        }

        public String getVillageId() {
            return VillageId;
        }

        public void setVillageId(String villageId) {
            VillageId = villageId;
        }
//
//        public String getMandalId() {
//            return MandalId;
//        }
//
//        public void setMandalId(String mandalId) {
//            MandalId = mandalId;
//        }
//
//        public String getDistrictId() {
//            return DistrictId;
//        }
//
//        public void setDistrictId(String districtId) {
//            DistrictId = districtId;
//        }
//
//        public String getStateId() {
//            return StateId;
//        }
//
//        public void setStateId(String stateId) {
//            StateId = stateId;
//        }

        public boolean isSync() {
            return sync;
        }

        public void setSync(boolean sync) {
            this.sync = sync;
        }
//
//        public String getIsPlot() {
//            return IsPlot;
//        }
//
//        public void setIsPlot(String isPlot) {
//            IsPlot = isPlot;
//        }

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
    }

    public static class PLot {

//        @SerializedName("PlotNo")
        @SerializedName("PlotId")
        @Expose
        private String PlotNo = "";

//        @SerializedName("Size")
        @SerializedName("SizeofAcres")
        @Expose
        private String Size = "";

        @SerializedName("CropId")
        @Expose
        private String CropId = "";

        @SerializedName("CropVarietyId")
        @Expose
        private String CropVarietyId = "";

//        @SerializedName("FarmerCode")
        @SerializedName("FarmerId")
        @Expose
        private String FarmerCode = "";

        @SerializedName("Latitude")
        @Expose
        private String Latitude = "";

        @SerializedName("Longitude")
        @Expose
        private String Longitude = "";

        @SerializedName("Address")
        @Expose
        private String Address = "";

        @SerializedName("Plotownership")
        @Expose
        private String Plotownership = "";

//        @SerializedName("Cluster")
        @SerializedName("ClusterId")
        @Expose
        private String Cluster = "";

        @SerializedName("LandMark")
        @Expose
        private String LandMark = "";
//
//        @SerializedName("DistrictId")
//        @Expose
//        private String DistrictId = "";
//
//        @SerializedName("MandalId")
//        @Expose
//        private String MandalId = "";
//
//
//        @SerializedName("StateId")
//        @Expose
//        private String StateId = "";


        @SerializedName("VillageId")
        @Expose
        private String VillageId = "";

        @SerializedName("PinCode")
        @Expose
        private String PinCode = "";

        @SerializedName("SurveyNo")
        @Expose
        private String SurveyNo = "";

        @SerializedName("PattadarBookNo")
        @Expose
        private String PattadarBookNo = "";

//        @SerializedName("GPS")
        @SerializedName("GPSLocation")
        @Expose
        private String GPS = "";

        @SerializedName("GPSPlotArea")
        @Expose
        private String GPSPlotArea = "";

//        @SerializedName("sync")
        @SerializedName("Sync")
        @Expose
        private boolean sync;

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";

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
        private String UpdatedByUserId;

        public String getPlotNo() {
            return PlotNo;
        }

        public void setPlotNo(String plotNo) {
            PlotNo = plotNo;
        }

        public String getSize() {
            return Size;
        }

        public String getCluster() {
            return Cluster;
        }

        public void setCluster(String cluster) {
            Cluster = cluster;
        }

        public String getGPSPlotArea() {
            return GPSPlotArea;
        }

        public void setGPSPlotArea(String GPSPlotArea) {
            this.GPSPlotArea = GPSPlotArea;
        }

        public void setSize(String size) {
            Size = size;
        }

        public String getPlotownership() {
            return Plotownership;
        }

        public void setPlotownership(String plotownership) {
            Plotownership = plotownership;
        }

        public String getLandMark() {
            return LandMark;
        }

        public void setLandMark(String landMark) {
            LandMark = landMark;
        }

        public String getCropId() {
            return CropId;
        }

        public void setCropId(String cropId) {
            CropId = cropId;
        }

        public String getCropVarietyId() {
            return CropVarietyId;
        }

        public void setCropVarietyId(String cropVarietyId) {
            CropVarietyId = cropVarietyId;
        }

        public String getFarmerCode() {
            return FarmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            FarmerCode = farmerCode;
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

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }
//
//        public String getDistrictId() {
//            return DistrictId;
//        }
//
//        public void setDistrictId(String districtId) {
//            DistrictId = districtId;
//        }
//
//        public String getMandalId() {
//            return MandalId;
//        }
//
//        public void setMandalId(String mandalId) {
//            MandalId = mandalId;
//        }
//
//        public String getStateId() {
//            return StateId;
//        }
//
//        public void setStateId(String stateId) {
//            StateId = stateId;
//        }

        public String getVillageId() {
            return VillageId;
        }

        public void setVillageId(String villageId) {
            VillageId = villageId;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String pinCode) {
            PinCode = pinCode;
        }

        public String getSurveyNo() {
            return SurveyNo;
        }

        public void setSurveyNo(String surveyNo) {
            SurveyNo = surveyNo;
        }

        public String getPattadarBookNo() {
            return PattadarBookNo;
        }

        public void setPattadarBookNo(String pattadarBookNo) {
            PattadarBookNo = pattadarBookNo;
        }

        public String getGPS() {
            return GPS;
        }

        public void setGPS(String GPS) {
            this.GPS = GPS;
        }

        public boolean isSync() {
            return sync;
        }

        public void setSync(boolean sync) {
            this.sync = sync;
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
    }

    public static class Bank {
//        @SerializedName("FarmerCode")
        @SerializedName("FarmerId")
        @Expose
        private String FarmerCode = "";


        @SerializedName("IdentityCode")
        @Expose
        private String IdentityCode = "";

        @SerializedName("AccountHolderName")
        @Expose
        private String AccountHolderName = "";


        @SerializedName("AccountNumber")
        @Expose
        private String AccountNumber = "";


        @SerializedName("BranchName")
        @Expose
        private String BranchName = "";

        @SerializedName("IFSCCode")
        @Expose
        private String IFSCCode = "";

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";


        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";


        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync;

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getFarmerCode() {
            return FarmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            FarmerCode = farmerCode;
        }

        public String getIdentityCode() {
            return IdentityCode;
        }

        public void setIdentityCode(String identityCode) {
            IdentityCode = identityCode;
        }

        public String getAccountHolderName() {
            return AccountHolderName;
        }

        public void setAccountHolderName(String accountHolderName) {
            AccountHolderName = accountHolderName;
        }

        public String getAccountNumber() {
            return AccountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            AccountNumber = accountNumber;
        }

        public String getBranchName() {
            return BranchName;
        }

        public void setBranchName(String branchName) {
            BranchName = branchName;
        }

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
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
    }

    public static class Doc {

//        @SerializedName("FarmerCode")
        @SerializedName("FarmerId")
        @Expose
        private String FarmerCode = "";

//        @SerializedName("PlotNo")
        @SerializedName("PlotId")
        @Expose
        private String PlotNo = "";

        @SerializedName("IdentityCode")
        @Expose
        private String IdentityCode = "";

        @SerializedName("DocUrl")
        @Expose
        private String DocUrl = "";

        @SerializedName("DocType")
        @Expose
        private String DocType = "";

        @SerializedName("DocOldNum")
        @Expose
        private String DocOldNum = "";


        @SerializedName("DocExtension")
        @Expose
        private String DocExtension = "";

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";


        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";


        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

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

        public String getDocUrl() {
            return DocUrl;
        }

        public void setDocUrl(String docUrl) {
            DocUrl = docUrl;
        }

        public String getDocType() {
            return DocType;
        }

        public void setDocType(String docType) {
            DocType = docType;
        }

        public String getDocExtension() {
            return DocExtension;
        }

        public void setDocExtension(String docExtension) {
            DocExtension = docExtension;
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

        public String getIdentityCode() {
            return IdentityCode;
        }

        public void setIdentityCode(String identityCode) {
            IdentityCode = identityCode;
        }

        public String getDocOldNum() {
            return DocOldNum;
        }

        public void setDocOldNum(String docOldNum) {
            DocOldNum = docOldNum;
        }
    }

    public static class GeoBoundaries {

//        @SerializedName("PlotNo")
        @SerializedName("PlotId")
        @Expose
        private String PlotNo ;

        @SerializedName("Latitude")
        @Expose
        private double Latitude ;

        @SerializedName("Longitude")
        @Expose
        private double Longitude;
//
//        @SerializedName("GeoCategoryTypeId")
//        @Expose
//        private String GeoCategoryTypeId ;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";


        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";


        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId;


        public String getPlotNo() {
            return PlotNo;
        }

        public void setPlotNo(String plotNo) {
            PlotNo = plotNo;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double latitude) {
            Latitude = latitude;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double longitude) {
            Longitude = longitude;
        }
//
//        public String getGeoCategoryTypeId() {
//            return GeoCategoryTypeId;
//        }
//
//        public void setGeoCategoryTypeId(String geoCategoryTypeId) {
//            GeoCategoryTypeId = geoCategoryTypeId;
//        }
        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
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
    }

    public static class LogBook {

//        @SerializedName("PlotNo")
        @SerializedName("PlotId")
        @Expose
        private String PlotNo;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

//        @SerializedName("Season")
        @SerializedName("SeasonId")
        @Expose
        private Integer Season;

        @SerializedName("DateOfSowing")
        @Expose
        private String  DateOfSowing = "";


//        @SerializedName("CropVariety")
        @SerializedName("CropVarietyId")
        @Expose
        private String CropVariety;

        public String getCropVariety() {
            return CropVariety;
        }

        public void setCropVariety(String cropVariety) {
            CropVariety = cropVariety;
        }

//        @SerializedName("Crop")
        @SerializedName("CropId")
        @Expose
        private String Crop;


        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }
//        @SerializedName("Harvest")
//        @Expose
//        private String Harvest;
//
//        @SerializedName("CultivationPractice")
//        @Expose
//        private Integer CultivationPractice;

        public String getPlotNo() {
            return PlotNo;
        }

        public void setPlotNo(String plotNo) {
            PlotNo = plotNo;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getSeason() {
            return Season;
        }

        public void setSeason(Integer season) {
            Season = season;
        }


        public String getDateOfSowing() {
            return DateOfSowing;
        }

        public void setDateOfSowing(String dateOfSowing) {
            DateOfSowing = dateOfSowing;
        }

        public String getCrop() {
            return Crop;
        }

        public void setCrop(String crop) {
            Crop = crop;
        }

//        public String getHarvest() {
//            return Harvest;
//        }
//
//        public void setHarvest(String harvest) {
//            Harvest = harvest;
//        }
//
//        public Integer getCultivationPractice() {
//            return CultivationPractice;
//        }
//
//        public void setCultivationPractice(Integer cultivationPractice) {
//            CultivationPractice = cultivationPractice;
//        }

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";

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
        private String UpdatedByUserId ;




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
    }

    public static class LandPreparation {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("OrganicAmendmentId")
        @Expose
        private Integer OrganicAmendmentId;

        @SerializedName("Date")
        @Expose
        private String Date;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getOrganicAmendmentId() {
            return OrganicAmendmentId;
        }

        public void setOrganicAmendmentId(Integer organicAmendmentId) {
            OrganicAmendmentId = organicAmendmentId;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }

    }

    public static class LogbookGeoBoundaries {

        @SerializedName("Id")
        @Expose
        private String Id ;

        @SerializedName("PlotId")
        @Expose
        private String PlotId ;

        @SerializedName("Latitude")
        @Expose
        private String Latitude ;

        @SerializedName("Longitude")
        @Expose
        private String Longitude ;

        @SerializedName("TotalPlotArea")
        @Expose
        private String TotalPlotArea ;

        @SerializedName("CultureArea")
        @Expose
        private String CultureArea ;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;


        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getPlotId() {
            return PlotId;
        }

        public void setPlotId(String plotId) {
            PlotId = plotId;
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

        public String getTotalPlotArea() {
            return TotalPlotArea;
        }

        public void setTotalPlotArea(String totalPlotArea) {
            TotalPlotArea = totalPlotArea;
        }

        public String getCultureArea() {
            return CultureArea;
        }

        public void setCultureArea(String cultureArea) {
            CultureArea = cultureArea;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }

    }

    public static class NurseryPreparation {

        @SerializedName("Id")
        @Expose
        private Integer Id ;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo ;

        @SerializedName("SeedVarietyId")
        @Expose
        private Integer SeedVarietyId ;

        @SerializedName("SeedTreatmentId")
        @Expose
        private String SeedTreatmentId ;

        @SerializedName("PestControlId")
        @Expose
        private String PestControlId ;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getSeedVarietyId() {
            return SeedVarietyId;
        }

        public void setSeedVarietyId(Integer seedVarietyId) {
            SeedVarietyId = seedVarietyId;
        }

        public String getSeedTreatmentId() {
            return SeedTreatmentId;
        }

        public void setSeedTreatmentId(String seedTreatmentId) {
            SeedTreatmentId = seedTreatmentId;
        }

        public String getPestControlId() {
            return PestControlId;
        }

        public void setPestControlId(String pestControlId) {
            PestControlId = pestControlId;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class Transplanting {

        @SerializedName("Id")
        @Expose
        private Integer Id ;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo ;

        @SerializedName("SowingId")
        @Expose
        private String SowingId ;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public String getSowingId() {
            return SowingId;
        }

        public void setSowingId(String sowingId) {
            SowingId = sowingId;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class Fertilizer {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("Date")
        @Expose
        private String Date;

        @SerializedName("Sync")
        @Expose
        private boolean sync;

        @SerializedName("IsActive")
        @Expose
        private String IsActive = "1";

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

        @SerializedName("OrganicAmendmentId")
        @Expose
        private String OrganicAmendmentId ;

        @SerializedName("organicamendmentsdesc")
        @Expose
        private String organicamendmentsdesc ;


        @SerializedName("SQNo")
        @Expose
        private String SQNo ;



        public String getOrganicAmendmentId() {
            return OrganicAmendmentId;
        }

        public void setOrganicAmendmentId(String organicAmendmentId) {
            OrganicAmendmentId = organicAmendmentId;
        }

        public String getOrganicamendmentsdesc() {
            return organicamendmentsdesc;
        }

        public void setOrganicamendmentsdesc(String organicamendmentsdesc) {
            this.organicamendmentsdesc = organicamendmentsdesc;
        }

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public boolean isSync() {
            return sync;
        }

        public void setSync(boolean sync) {
            this.sync = sync;
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
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
////
////        public Integer getFertilizer() {
////            return Fertilizer;
////        }
////
////        public void setFertilizer(Integer fertilizer) {
////            Fertilizer = fertilizer;
////        }
////
////        public Integer getFertilizerType() {
////            return FertilizerType;
////        }
////
////        public void setFertilizerType(Integer fertilizerType) {
////            FertilizerType = fertilizerType;
////        }
//
//        public String getDate() {
//            return Date;
//        }
//
//        public void setDate(String date) {
//            Date = date;
//        }
////
////        public String getQty() {
////            return Qty;
////        }
////
////        public void setQty(String qty) {
////            Qty = qty;
////        }
////
////        public Integer getUOM() {
////            return UOM;
////        }
////
////        public void setUOM(Integer UOM) {
////            this.UOM = UOM;
////        }
////
////        public String getCropProtection() {
////            return CropProtection;
////        }
////
////        public void setCropProtection(String cropProtection) {
////            CropProtection = cropProtection;
////        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
////
////        public String getServerStatus() {
////            return serverStatus;
////        }
////
////        public void setServerStatus(String serverStatus) {
////            this.serverStatus = serverStatus;
////        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
    }

    public static class SeedRate {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("MethodofseedingId")
        @Expose
        private Integer MethodofseedingId;

        @SerializedName("Qty")
        @Expose
        private String Qty;

        @SerializedName("DirectSeedling")
        @Expose
        private Double DirectSeedling;

        @SerializedName("IndirectSeedlings")
        @Expose
        private Double IndirectSeedlings;

        @SerializedName("SeedRate")
        @Expose
        private String SeedRate;

        @SerializedName("Spacing")
        @Expose
        private String Spacing;


        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getMethodofseedingId() {
            return MethodofseedingId;
        }

        public void setMethodofseedingId(Integer methodofseedingId) {
            MethodofseedingId = methodofseedingId;
        }

        public String getQty() {
            return Qty;
        }

        public void setQty(String qty) {
            Qty = qty;
        }

        public Double getDirectSeedling() {
            return DirectSeedling;
        }

        public void setDirectSeedling(Double directSeedling) {
            DirectSeedling = directSeedling;
        }

        public Double getIndirectSeedlings() {
            return IndirectSeedlings;
        }

        public void setIndirectSeedlings(Double indirectSeedlings) {
            IndirectSeedlings = indirectSeedlings;
        }

        public String getSeedRate() {
            return SeedRate;
        }

        public void setSeedRate(String seedRate) {
            SeedRate = seedRate;
        }

        public String getSpacing() {
            return Spacing;
        }

        public void setSpacing(String spacing) {
            Spacing = spacing;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }


    }

    public static class WaterManagement {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("MethodofirrigationId")
        @Expose
        private Integer MethodofirrigationId;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getMethodofirrigationId() {
            return MethodofirrigationId;
        }

        public void setMethodofirrigationId(Integer methodofirrigationId) {
            MethodofirrigationId = methodofirrigationId;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class WeedManagement {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("MethodofweedingId")
        @Expose
        private Integer MethodofweedingId;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getMethodofweedingId() {
            return MethodofweedingId;
        }

        public void setMethodofweedingId(Integer methodofweedingId) {
            MethodofweedingId = methodofweedingId;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class PestDiseaseControl {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("MajorPestId")
        @Expose
        private Integer MajorPestId;

        @SerializedName("Neemoil")
        @Expose
        private String Neemoil;

        @SerializedName("Neemseedkernel")
        @Expose
        private String Neemseedkernel;

        @SerializedName("Bramasthram")
        @Expose
        private String Bramasthram;

        @SerializedName("Agniasthram")
        @Expose
        private String Agniasthram;

        @SerializedName("Neemasthram")
        @Expose
        private String Neemasthram;

        @SerializedName("MajorDiseaseId")
        @Expose
        private Integer MajorDiseaseId;

        @SerializedName("TrichodermaViridae")
        @Expose
        private String TrichodermaViridae;

        @SerializedName("Pseudomonas")
        @Expose
        private String Pseudomonas;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public String getNeemasthram() {
            return Neemasthram;
        }

        public void setNeemasthram(String neemasthram) {
            Neemasthram = neemasthram;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Integer getMajorPestId() {
            return MajorPestId;
        }

        public void setMajorPestId(Integer majorPestId) {
            MajorPestId = majorPestId;
        }

        public String getNeemoil() {
            return Neemoil;
        }

        public void setNeemoil(String neemoil) {
            Neemoil = neemoil;
        }

        public String getNeemseedkernel() {
            return Neemseedkernel;
        }

        public void setNeemseedkernel(String neemseedkernel) {
            Neemseedkernel = neemseedkernel;
        }

        public String getBramasthram() {
            return Bramasthram;
        }

        public void setBramasthram(String bramasthram) {
            Bramasthram = bramasthram;
        }

        public String getAgniasthram() {
            return Agniasthram;
        }

        public void setAgniasthram(String agniasthram) {
            Agniasthram = agniasthram;
        }

        public Integer getMajorDiseaseId() {
            return MajorDiseaseId;
        }

        public void setMajorDiseaseId(Integer majorDiseaseId) {
            MajorDiseaseId = majorDiseaseId;
        }

        public String getTrichodermaViridae() {
            return TrichodermaViridae;
        }

        public void setTrichodermaViridae(String trichodermaViridae) {
            TrichodermaViridae = trichodermaViridae;
        }

        public String getPseudomonas() {
            return Pseudomonas;
        }

        public void setPseudomonas(String pseudomonas) {
            Pseudomonas = pseudomonas;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class Harvesting {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("TypeofharvestingId")
        @Expose
        private Integer TypeofharvestingId;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("stagesofharvesting")
        @Expose
        private Boolean stagesofharvesting;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public Integer getTypeofharvestingId() {
            return TypeofharvestingId;
        }

        public void setTypeofharvestingId(Integer typeofharvestingId) {
            TypeofharvestingId = typeofharvestingId;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Boolean getStagesofharvesting() {
            return stagesofharvesting;
        }

        public void setStagesofharvesting(Boolean stagesofharvesting) {
            this.stagesofharvesting = stagesofharvesting;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class Moisture {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("Percentage")
        @Expose
        private Double Percentage;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Double getPercentage() {
            return Percentage;
        }

        public void setPercentage(Double percentage) {
            Percentage = percentage;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

    public static class Yield {

        @SerializedName("Id")
        @Expose
        private Integer Id;

        @SerializedName("LogBookNo")
        @Expose
        private String LogBookNo;

        @SerializedName("YieldQty")
        @Expose
        private Double YieldQty;

        @SerializedName("Sync")
        @Expose
        private Boolean Sync ;

        @SerializedName("Date")
        @Expose
        private String Date ;

        @SerializedName("IsActive")
        @Expose
        private String IsActive ="1";

        @SerializedName("CreatedDate")
        @Expose
        private String CreatedDate = "";

        @SerializedName("CreatedByUserId")
        @Expose
        private String CreatedByUserId ;

        @SerializedName("UpdatedDate")
        @Expose
        private String UpdatedDate = "";

        @SerializedName("UpdatedByUserId")
        @Expose
        private String UpdatedByUserId ;

        @SerializedName("SQNo")
        @Expose
        private String SQNo ;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getLogBookNo() {
            return LogBookNo;
        }

        public void setLogBookNo(String logBookNo) {
            LogBookNo = logBookNo;
        }

        public Double getYieldQty() {
            return YieldQty;
        }

        public void setYieldQty(Double yieldQty) {
            YieldQty = yieldQty;
        }

        public Boolean getSync() {
            return Sync;
        }

        public void setSync(Boolean sync) {
            Sync = sync;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
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

        public String getSQNo() {
            return SQNo;
        }

        public void setSQNo(String SQNo) {
            this.SQNo = SQNo;
        }
    }

//    public static class OrganicAmendments {
//
//        @SerializedName("LogBookNo")
//        @Expose
//        private String LogBookNo;
//
//        @SerializedName("Value")
//        @Expose
//        private Integer Value;
//
//        @SerializedName("Date")
//        @Expose
//        private String Date;
//
//
//        @SerializedName("sync")
//        @Expose
//        private boolean sync;
//
//
//        @SerializedName("IsActive")
//        @Expose
//        private String IsActive = "1";
//
//        @SerializedName("CreatedDate")
//        @Expose
//        private String CreatedDate;
//
//        @SerializedName("CreatedByUserId")
//        @Expose
//        private String CreatedByUserId;
//
//        @SerializedName("UpdatedDate")
//        @Expose
//        private String UpdatedDate;
//
//        @SerializedName("UpdatedByUserId")
//        @Expose
//        private String UpdatedByUserId;
//
//
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
//
//        public Integer getValue() {
//            return Value;
//        }
//
//        public void setValue(Integer value) {
//            Value = value;
//        }
//
//        public String getDate() {
//            return Date;
//        }
//
//        public void setDate(String date) {
//            Date = date;
//        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
//    }
//
//    public static class WaterRegimeonSeason {
//
//        @SerializedName("LogBookNo")
//        @Expose
//        private String LogBookNo;
//
//        @SerializedName("Field")
//        @Expose
//        private Integer Field;
//
//
//        @SerializedName("sync")
//        @Expose
//        private boolean sync;
//
//        @SerializedName("IsActive")
//        @Expose
//        private String IsActive = "1";
//
//        @SerializedName("CreatedDate")
//        @Expose
//        private String CreatedDate;
//
//        @SerializedName("CreatedByUserId")
//        @Expose
//        private String CreatedByUserId;
//
//        @SerializedName("UpdatedDate")
//        @Expose
//        private String UpdatedDate;
//
//        @SerializedName("UpdatedByUserId")
//        @Expose
//        private String UpdatedByUserId ;
//
//
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
//
//        public Integer getField() {
//            return Field;
//        }
//
//        public void setField(Integer field) {
//            Field = field;
//        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
//    }
//
//    public static class WaterReasonPreSeason {
//
//        @SerializedName("LogBookNo")
//        @Expose
//        private String LogBookNo;
//
//        @SerializedName("Field")
//        @Expose
//        private Integer Field;
//
//
//        @SerializedName("sync")
//        @Expose
//        private boolean sync;
//
//        @SerializedName("IsActive")
//        @Expose
//        private String IsActive = "1";
//
//        @SerializedName("CreatedDate")
//        @Expose
//        private String CreatedDate;
//
//        @SerializedName("CreatedByUserId")
//        @Expose
//        private String CreatedByUserId;
//
//        @SerializedName("UpdatedDate")
//        @Expose
//        private String UpdatedDate;
//
//        @SerializedName("UpdatedByUserId")
//        @Expose
//        private String UpdatedByUserId ;
//
//
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
//
//        public Integer getField() {
//            return Field;
//        }
//
//        public void setField(Integer field) {
//            Field = field;
//        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
//    }
//
//    public static class BorewellPumpOperations {
//
//        @SerializedName("LogBookNo")
//        @Expose
//        private String LogBookNo;
//
//        @SerializedName("Date")
//        @Expose
//        private String Date;
//
//
//        @SerializedName("HoursDay")
//        @Expose
//        private Integer HoursDay;
//
//
//        @SerializedName("sync")
//        @Expose
//        private boolean sync;
//
//
//
//
//
//        @SerializedName("IsActive")
//        @Expose
//        private String IsActive = "1";
//
//        @SerializedName("CreatedDate")
//        @Expose
//        private String CreatedDate;
//
//        @SerializedName("CreatedByUserId")
//        @Expose
//        private String CreatedByUserId;
//
//        @SerializedName("UpdatedDate")
//        @Expose
//        private String UpdatedDate;
//
//        @SerializedName("UpdatedByUserId")
//        @Expose
//        private String UpdatedByUserId ;
//
//
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
//
//        public String getDate() {
//            return Date;
//        }
//
//        public void setDate(String date) {
//            Date = date;
//        }
//
//        public Integer getHoursDay() {
//            return HoursDay;
//        }
//
//        public void setHoursDay(Integer hoursDay) {
//            HoursDay = hoursDay;
//        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
//    }
//
//    public static class WaterRegimeOnthefield {
//
//        @SerializedName("LogBookNo")
//        @Expose
//        private String LogBookNo;
//
//        @SerializedName("Field")
//        @Expose
//        private Integer Field;
//
//
//        @SerializedName("sync")
//        @Expose
//        private boolean sync;
//
//        @SerializedName("IsActive")
//        @Expose
//        private String IsActive = "1";
//
//        @SerializedName("CreatedDate")
//        @Expose
//        private String CreatedDate;
//
//        @SerializedName("CreatedByUserId")
//        @Expose
//        private String CreatedByUserId ;
//
//        @SerializedName("UpdatedDate")
//        @Expose
//        private String UpdatedDate;
//
//        @SerializedName("UpdatedByUserId")
//        @Expose
//        private String UpdatedByUserId;
//
//
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
//
//        public Integer getField() {
//            return Field;
//        }
//
//        public void setField(Integer field) {
//            Field = field;
//        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
//    }
//
//    public static class HarvestDetails {
//
//        @SerializedName("LogBookNo")
//        @Expose
//        private String LogBookNo;
//
//        @SerializedName("Date")
//        @Expose
//        private String Date;
//
//        @SerializedName("YeildQty")
//        @Expose
//        private String YeildQty;
//
//        @SerializedName("sync")
//        @Expose
//        private boolean sync;
//
//        @SerializedName("IsActive")
//        @Expose
//        private String IsActive = "1";
//
//        @SerializedName("CreatedDate")
//        @Expose
//        private String CreatedDate;
//
//        @SerializedName("CreatedByUserId")
//        @Expose
//        private String CreatedByUserId ;
//
//        @SerializedName("UpdatedDate")
//        @Expose
//        private String UpdatedDate;
//
//        @SerializedName("UpdatedByUserId")
//        @Expose
//        private String UpdatedByUserId ;
//
//
//        public String getLogBookNo() {
//            return LogBookNo;
//        }
//
//        public void setLogBookNo(String logBookNo) {
//            LogBookNo = logBookNo;
//        }
//
//        public String getDate() {
//            return Date;
//        }
//
//        public void setDate(String date) {
//            Date = date;
//        }
//
//        public String getYeildQty() {
//            return YeildQty;
//        }
//
//        public void setYeildQty(String yeildQty) {
//            YeildQty = yeildQty;
//        }
//
//        public boolean isSync() {
//            return sync;
//        }
//
//        public void setSync(boolean sync) {
//            this.sync = sync;
//        }
//
//        public String getIsActive() {
//            return IsActive;
//        }
//
//        public void setIsActive(String isActive) {
//            IsActive = isActive;
//        }
//
//        public String getCreatedDate() {
//            return CreatedDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            CreatedDate = createdDate;
//        }
//
//        public String getCreatedByUserId() {
//            return CreatedByUserId;
//        }
//
//        public void setCreatedByUserId(String createdByUserId) {
//            CreatedByUserId = createdByUserId;
//        }
//
//        public String getUpdatedDate() {
//            return UpdatedDate;
//        }
//
//        public void setUpdatedDate(String updatedDate) {
//            UpdatedDate = updatedDate;
//        }
//
//        public String getUpdatedByUserId() {
//            return UpdatedByUserId;
//        }
//
//        public void setUpdatedByUserId(String updatedByUserId) {
//            UpdatedByUserId = updatedByUserId;
//        }
//    }

}


