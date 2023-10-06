package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class AddPlotTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("PlotGenId")
    @Expose(serialize = false, deserialize = false)
    private int PlotGenId;

    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("SeasonCode")
    @Expose
    private String SeasonCode;

    @SerializedName("CropTypeId")
    @Expose
    private int CropTypeId;

    @SerializedName("OfferedNo")
    @Expose
    private int OfferedNo;

    @SerializedName("FarmerCode")
    @Expose
    private String FarmerCode;

    @SerializedName("FatherName")
    @Expose
    private String FatherName;

    @SerializedName("RelationShipTypeId")
    @Expose
    private String RelationShipTypeId;
    @SerializedName("Nominee")
    @Expose
    private String Nominee;
    @SerializedName("Guarantor1")
    @Expose
    private String Guarantor1;
    @SerializedName("Guarantor2")
    @Expose
    private String Guarantor2;
    @SerializedName("Guarantor3")
    @Expose
    private String Guarantor3;
    @SerializedName("FarmerVillageId")
    @Expose
    private String FarmerVillageId;
    @SerializedName("PlotVillageId")
    @Expose
    private String PlotVillageId;
    @SerializedName("PlotNo")
    @Expose
    private String PlotNo;

    @SerializedName("PlantingDate")
    @Expose
    private String PlantingDate;

    @SerializedName("PlotTypeId")
    @Expose
    private String PlotTypeId;

    @SerializedName("PlantTypeId")
    @Expose
    private String PlantTypeId;
    @SerializedName("PlantSubTypeId")
    @Expose
    private int PlantSubTypeId;
    @SerializedName("VarietyId")
    @Expose
    private String VarietyId;
    @SerializedName("SurveyNo")
    @Expose
    private String SurveyNo;
    @SerializedName("FieldName")
    @Expose
    private String FieldName;
    @SerializedName("BIRNo")
    @Expose
    private String BIRNo;
    @SerializedName("BIRDate")
    @Expose
    private String BIRDate;
    @SerializedName("TotalArea")
    @Expose
    private String TotalArea;

    @SerializedName("CultivatedArea")
    @Expose
    private String CultivatedArea;
    @SerializedName("DemoPlotArea")
    @Expose
    private String DemoPlotArea;
    @SerializedName("ReportedArea")
    @Expose
    private String ReportedArea;

    @SerializedName("MeasureArea")
    @Expose
    private String MeasureArea;

    @SerializedName("AggrementedArea")
    @Expose
    private String AggrementedArea;

    @SerializedName("NetArea")
    @Expose
    private String NetArea;
    @SerializedName("AgreedTon")
    @Expose
    private String AgreedTon;
    @SerializedName("EstimatedTon")
    @Expose
    private String EstimatedTon;

    @SerializedName("IrrigationTypeId")
    @Expose
    private String IrrigationTypeId;

    @SerializedName("SoilTypeId")
    @Expose
    private String SoilTypeId;

    @SerializedName("SpacingId")
    @Expose
    private String SpacingId;
    @SerializedName("SettsTypeId")
    @Expose
    private String SettsTypeId;
    @SerializedName("PreviousCropId")
    @Expose
    private String PreviousCropId;

    @SerializedName("InterCropId")
    @Expose
    private String InterCropId;

    @SerializedName("SeedMaterialUsedId")
    @Expose
    private String SeedMaterialUsedId;

    @SerializedName("PlotExistOnId")
    @Expose
    private String PlotExistOnId;
    @SerializedName("DistanceFromPlot")
    @Expose
    private String DistanceFromPlot;
    @SerializedName("Profile")
    @Expose
    private String Profile;

    @SerializedName("IsSettsHotWaterTreatment")
    @Expose
    private String IsSettsHotWaterTreatment;

    @SerializedName("IsPreviousRedPlot")
    @Expose
    private String IsPreviousRedPlot;

    @SerializedName("IsDustApplied")
    @Expose
    private String IsDustApplied;
    @SerializedName("IsBasalFertilization")
    @Expose
    private String IsBasalFertilization;
    @SerializedName("IsTrashMulching")
    @Expose
    private String IsTrashMulching;

    @SerializedName("IsCompositeFormYard")
    @Expose
    private String IsCompositeFormYard;

    @SerializedName("IsFilterPressMud")
    @Expose
    private String IsFilterPressMud;

    @SerializedName("IsGreenManures")
    @Expose
    private String IsGreenManures;

    @SerializedName("IsMicronutrientDeficiency")
    @Expose
    private String IsMicronutrientDeficiency;

    @SerializedName("IsGapsFilled")
    @Expose
    private String IsGapsFilled;
    @SerializedName("InspectionDate")
    @Expose
    private String InspectionDate;
    @SerializedName("WeedStatusId")
    @Expose
    private String WeedStatusId;

    @SerializedName("ActionPlanId")
    @Expose
    private String ActionPlanId;

    @SerializedName("PerishableReasonId")
    @Expose
    private String PerishableReasonId;

    @SerializedName("PerishedArea")
    @Expose
    private String PerishedArea;
    @SerializedName("NotGrownArea")
    @Expose
    private String NotGrownArea;
    @SerializedName("HarvestingArea")
    @Expose
    private String HarvestingArea;

    @SerializedName("PoorCropArea")
    @Expose
    private String PoorCropArea;

    @SerializedName("RemovedArea")
    @Expose
    private String RemovedArea;

    @SerializedName("BioFertilizerAppliedArea")
    @Expose
    private String BioFertilizerAppliedArea;

    @SerializedName("DeTrashingArea")
    @Expose
    private String DeTrashingArea;

    @SerializedName("DeepPloughedArea")
    @Expose
    private String DeepPloughedArea;
    @SerializedName("EarthingUpArea")
    @Expose
    private String EarthingUpArea;
    @SerializedName("RatoonManagedUsedArea")
    @Expose
    private String RatoonManagedUsedArea;

    @SerializedName("TrashShedderArea")
    @Expose
    private String TrashShedderArea;

    @SerializedName("LoadShedderArea")
    @Expose
    private String LoadShedderArea;

    @SerializedName("IsSeedArea")
    @Expose
    private String IsSeedArea;
    @SerializedName("DivertToSelfSeed")
    @Expose
    private String DivertToSelfSeed;
    @SerializedName("DivertToOthers")
    @Expose
    private String DivertToOthers;
//
    @SerializedName("SchGroupNo")
    @Expose
    private String SchGroupNo;

    @SerializedName("Brix")
    @Expose
    private String Brix;

    @SerializedName("Pol")
    @Expose
    private String Pol;
    @SerializedName("Purity")
    @Expose
    private String Purity;

    @SerializedName("CCS")
    @Expose
    private String CCS;

    @SerializedName("NoOfSamples")
    @Expose
    private String NoOfSamples;

    @SerializedName("SampleDate")
    @Expose
    private String SampleDate;

    @SerializedName("CuttingOrderNo")
    @Expose
    private String CuttingOrderNo;

    @SerializedName("ProppingArea")
    @Expose
    private String ProppingArea;
    @SerializedName("ProppingStageId")
    @Expose
    private String ProppingStageId;
    @SerializedName("TransferArea")
    @Expose
    private String TransferArea;

    @SerializedName("IsRegistered")
    @Expose
    private String IsRegistered;

    @SerializedName("IsOver")
    @Expose
    private String IsOver;

    @SerializedName("PlotOverReasonId")
    @Expose
    private String PlotOverReasonId;
    @SerializedName("PlotOverDate")
    @Expose
    private String PlotOverDate;
    @SerializedName("IsActive")
    @Expose
    private String IsActive;

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

    @SerializedName("PlantingMethodId")
    @Expose
    private String PlantingMethodId;

    @SerializedName("Stage")
    @Expose
    private String  Stage;
    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;

    @SerializedName("Latitude")
    @Expose
    private String Latitude;

    @SerializedName("Longitude")
    @Expose
    private String Longitude;

    public int getPlotGenId() {
        return PlotGenId;
    }

    public void setPlotGenId(int plotGenId) {
        PlotGenId = plotGenId;
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

    public int getCropTypeId() {
        return CropTypeId;
    }

    public void setCropTypeId(int cropTypeId) {
        CropTypeId = cropTypeId;
    }

    public int getOfferedNo() {
        return OfferedNo;
    }

    public void setOfferedNo(int offeredNo) {
        OfferedNo = offeredNo;
    }

    public String getFarmerCode() {
        return FarmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        FarmerCode = farmerCode;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getRelationShipTypeId() {
        return RelationShipTypeId;
    }

    public void setRelationShipTypeId(String relationShipTypeId) {
        RelationShipTypeId = relationShipTypeId;
    }

    public String getNominee() {
        return Nominee;
    }

    public void setNominee(String nominee) {
        Nominee = nominee;
    }

    public String getGuarantor1() {
        return Guarantor1;
    }

    public void setGuarantor1(String guarantor1) {
        Guarantor1 = guarantor1;
    }

    public String getGuarantor2() {
        return Guarantor2;
    }

    public void setGuarantor2(String guarantor2) {
        Guarantor2 = guarantor2;
    }

    public String getGuarantor3() {
        return Guarantor3;
    }

    public void setGuarantor3(String guarantor3) {
        Guarantor3 = guarantor3;
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

    public String getPlotNo() {
        return PlotNo;
    }

    public void setPlotNo(String plotNo) {
        PlotNo = plotNo;
    }

    public String getPlantingDate() {
        return PlantingDate;
    }

    public void setPlantingDate(String plantingDate) {
        PlantingDate = plantingDate;
    }

    public String getPlotTypeId() {
        return PlotTypeId;
    }

    public void setPlotTypeId(String plotTypeId) {
        PlotTypeId = plotTypeId;
    }

    public String getPlantTypeId() {
        return PlantTypeId;
    }

    public void setPlantTypeId(String plantTypeId) {
        PlantTypeId = plantTypeId;
    }

    public int getPlantSubTypeId() {
        return PlantSubTypeId;
    }

    public void setPlantSubTypeId(int plantSubTypeId) {
        PlantSubTypeId = plantSubTypeId;
    }

    public String getVarietyId() {
        return VarietyId;
    }

    public void setVarietyId(String varietyId) {
        VarietyId = varietyId;
    }

    public String getSurveyNo() {
        return SurveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        SurveyNo = surveyNo;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getBIRNo() {
        return BIRNo;
    }

    public void setBIRNo(String BIRNo) {
        this.BIRNo = BIRNo;
    }

    public String getBIRDate() {
        return BIRDate;
    }

    public void setBIRDate(String BIRDate) {
        this.BIRDate = BIRDate;
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

    public String getDemoPlotArea() {
        return DemoPlotArea;
    }

    public void setDemoPlotArea(String demoPlotArea) {
        DemoPlotArea = demoPlotArea;
    }

    public String getReportedArea() {
        return ReportedArea;
    }

    public void setReportedArea(String reportedArea) {
        ReportedArea = reportedArea;
    }

    public String getMeasureArea() {
        return MeasureArea;
    }

    public void setMeasureArea(String measureArea) {
        MeasureArea = measureArea;
    }

    public String getAggrementedArea() {
        return AggrementedArea;
    }

    public void setAggrementedArea(String aggrementedArea) {
        AggrementedArea = aggrementedArea;
    }

    public String getNetArea() {
        return NetArea;
    }

    public void setNetArea(String netArea) {
        NetArea = netArea;
    }

    public String getAgreedTon() {
        return AgreedTon;
    }

    public void setAgreedTon(String agreedTon) {
        AgreedTon = agreedTon;
    }

    public String getEstimatedTon() {
        return EstimatedTon;
    }

    public void setEstimatedTon(String estimatedTon) {
        EstimatedTon = estimatedTon;
    }

    public String getIrrigationTypeId() {
        return IrrigationTypeId;
    }

    public void setIrrigationTypeId(String irrigationTypeId) {
        IrrigationTypeId = irrigationTypeId;
    }

    public String getSoilTypeId() {
        return SoilTypeId;
    }

    public void setSoilTypeId(String soilTypeId) {
        SoilTypeId = soilTypeId;
    }

    public String getSpacingId() {
        return SpacingId;
    }

    public void setSpacingId(String spacingId) {
        SpacingId = spacingId;
    }

    public String getSettsTypeId() {
        return SettsTypeId;
    }

    public void setSettsTypeId(String settsTypeId) {
        SettsTypeId = settsTypeId;
    }

    public String getPreviousCropId() {
        return PreviousCropId;
    }

    public void setPreviousCropId(String previousCropId) {
        PreviousCropId = previousCropId;
    }

    public String getInterCropId() {
        return InterCropId;
    }

    public void setInterCropId(String interCropId) {
        InterCropId = interCropId;
    }

    public String getSeedMaterialUsedId() {
        return SeedMaterialUsedId;
    }

    public void setSeedMaterialUsedId(String seedMaterialUsedId) {
        SeedMaterialUsedId = seedMaterialUsedId;
    }

    public String getPlotExistOnId() {
        return PlotExistOnId;
    }

    public void setPlotExistOnId(String plotExistOnId) {
        PlotExistOnId = plotExistOnId;
    }

    public String getDistanceFromPlot() {
        return DistanceFromPlot;
    }

    public void setDistanceFromPlot(String distanceFromPlot) {
        DistanceFromPlot = distanceFromPlot;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String profile) {
        Profile = profile;
    }

    public String getIsSettsHotWaterTreatment() {
        return IsSettsHotWaterTreatment;
    }

    public void setIsSettsHotWaterTreatment(String isSettsHotWaterTreatment) {
        IsSettsHotWaterTreatment = isSettsHotWaterTreatment;
    }

    public String getIsPreviousRedPlot() {
        return IsPreviousRedPlot;
    }

    public void setIsPreviousRedPlot(String isPreviousRedPlot) {
        IsPreviousRedPlot = isPreviousRedPlot;
    }

    public String getIsDustApplied() {
        return IsDustApplied;
    }

    public void setIsDustApplied(String isDustApplied) {
        IsDustApplied = isDustApplied;
    }

    public String getIsBasalFertilization() {
        return IsBasalFertilization;
    }

    public void setIsBasalFertilization(String isBasalFertilization) {
        IsBasalFertilization = isBasalFertilization;
    }

    public String getIsTrashMulching() {
        return IsTrashMulching;
    }

    public void setIsTrashMulching(String isTrashMulching) {
        IsTrashMulching = isTrashMulching;
    }

    public String getIsCompositeFormYard() {
        return IsCompositeFormYard;
    }

    public void setIsCompositeFormYard(String isCompositeFormYard) {
        IsCompositeFormYard = isCompositeFormYard;
    }

    public String getIsFilterPressMud() {
        return IsFilterPressMud;
    }

    public void setIsFilterPressMud(String isFilterPressMud) {
        IsFilterPressMud = isFilterPressMud;
    }

    public String getIsGreenManures() {
        return IsGreenManures;
    }

    public void setIsGreenManures(String isGreenManures) {
        IsGreenManures = isGreenManures;
    }

    public String getIsMicronutrientDeficiency() {
        return IsMicronutrientDeficiency;
    }

    public void setIsMicronutrientDeficiency(String isMicronutrientDeficiency) {
        IsMicronutrientDeficiency = isMicronutrientDeficiency;
    }

    public String getIsGapsFilled() {
        return IsGapsFilled;
    }

    public void setIsGapsFilled(String isGapsFilled) {
        IsGapsFilled = isGapsFilled;
    }

    public String getInspectionDate() {
        return InspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        InspectionDate = inspectionDate;
    }

    public String getWeedStatusId() {
        return WeedStatusId;
    }

    public void setWeedStatusId(String weedStatusId) {
        WeedStatusId = weedStatusId;
    }

    public String getActionPlanId() {
        return ActionPlanId;
    }

    public void setActionPlanId(String actionPlanId) {
        ActionPlanId = actionPlanId;
    }

    public String getPerishableReasonId() {
        return PerishableReasonId;
    }

    public void setPerishableReasonId(String perishableReasonId) {
        PerishableReasonId = perishableReasonId;
    }

    public String getPerishedArea() {
        return PerishedArea;
    }

    public void setPerishedArea(String perishedArea) {
        PerishedArea = perishedArea;
    }

    public String getNotGrownArea() {
        return NotGrownArea;
    }

    public void setNotGrownArea(String notGrownArea) {
        NotGrownArea = notGrownArea;
    }

    public String getHarvestingArea() {
        return HarvestingArea;
    }

    public void setHarvestingArea(String harvestingArea) {
        HarvestingArea = harvestingArea;
    }

    public String getPoorCropArea() {
        return PoorCropArea;
    }

    public void setPoorCropArea(String poorCropArea) {
        PoorCropArea = poorCropArea;
    }

    public String getRemovedArea() {
        return RemovedArea;
    }

    public void setRemovedArea(String removedArea) {
        RemovedArea = removedArea;
    }

    public String getBioFertilizerAppliedArea() {
        return BioFertilizerAppliedArea;
    }

    public void setBioFertilizerAppliedArea(String bioFertilizerAppliedArea) {
        BioFertilizerAppliedArea = bioFertilizerAppliedArea;
    }

    public String getDeTrashingArea() {
        return DeTrashingArea;
    }

    public void setDeTrashingArea(String deTrashingArea) {
        DeTrashingArea = deTrashingArea;
    }

    public String getDeepPloughedArea() {
        return DeepPloughedArea;
    }

    public void setDeepPloughedArea(String deepPloughedArea) {
        DeepPloughedArea = deepPloughedArea;
    }

    public String getEarthingUpArea() {
        return EarthingUpArea;
    }

    public void setEarthingUpArea(String earthingUpArea) {
        EarthingUpArea = earthingUpArea;
    }

    public String getRatoonManagedUsedArea() {
        return RatoonManagedUsedArea;
    }

    public void setRatoonManagedUsedArea(String ratoonManagedUsedArea) {
        RatoonManagedUsedArea = ratoonManagedUsedArea;
    }

    public String getTrashShedderArea() {
        return TrashShedderArea;
    }

    public void setTrashShedderArea(String trashShedderArea) {
        TrashShedderArea = trashShedderArea;
    }

    public String getLoadShedderArea() {
        return LoadShedderArea;
    }

    public void setLoadShedderArea(String loadShedderArea) {
        LoadShedderArea = loadShedderArea;
    }

    public String getIsSeedArea() {
        return IsSeedArea;
    }

    public void setIsSeedArea(String isSeedArea) {
        IsSeedArea = isSeedArea;
    }

    public String getDivertToSelfSeed() {
        return DivertToSelfSeed;
    }

    public void setDivertToSelfSeed(String divertToSelfSeed) {
        DivertToSelfSeed = divertToSelfSeed;
    }

    public String getDivertToOthers() {
        return DivertToOthers;
    }

    public void setDivertToOthers(String divertToOthers) {
        DivertToOthers = divertToOthers;
    }

    public String getSchGroupNo() {
        return SchGroupNo;
    }

    public void setSchGroupNo(String schGroupNo) {
        SchGroupNo = schGroupNo;
    }

    public String getBrix() {
        return Brix;
    }

    public void setBrix(String brix) {
        Brix = brix;
    }

    public String getPol() {
        return Pol;
    }

    public void setPol(String pol) {
        Pol = pol;
    }

    public String getPurity() {
        return Purity;
    }

    public void setPurity(String purity) {
        Purity = purity;
    }

    public String getCCS() {
        return CCS;
    }

    public void setCCS(String CCS) {
        this.CCS = CCS;
    }

    public String getNoOfSamples() {
        return NoOfSamples;
    }

    public void setNoOfSamples(String noOfSamples) {
        NoOfSamples = noOfSamples;
    }

    public String getSampleDate() {
        return SampleDate;
    }

    public void setSampleDate(String sampleDate) {
        SampleDate = sampleDate;
    }

    public String getCuttingOrderNo() {
        return CuttingOrderNo;
    }

    public void setCuttingOrderNo(String cuttingOrderNo) {
        CuttingOrderNo = cuttingOrderNo;
    }

    public String getProppingArea() {
        return ProppingArea;
    }

    public void setProppingArea(String proppingArea) {
        ProppingArea = proppingArea;
    }

    public String getProppingStageId() {
        return ProppingStageId;
    }

    public void setProppingStageId(String proppingStageId) {
        ProppingStageId = proppingStageId;
    }

    public String getTransferArea() {
        return TransferArea;
    }

    public void setTransferArea(String transferArea) {
        TransferArea = transferArea;
    }

    public String getIsRegistered() {
        return IsRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        IsRegistered = isRegistered;
    }

    public String getIsOver() {
        return IsOver;
    }

    public void setIsOver(String isOver) {
        IsOver = isOver;
    }

    public String getPlotOverReasonId() {
        return PlotOverReasonId;
    }

    public void setPlotOverReasonId(String plotOverReasonId) {
        PlotOverReasonId = plotOverReasonId;
    }

    public String getPlotOverDate() {
        return PlotOverDate;
    }

    public void setPlotOverDate(String plotOverDate) {
        PlotOverDate = plotOverDate;
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

    public String getPlantingMethodId() {
        return PlantingMethodId;
    }

    public void setPlantingMethodId(String plantingMethodId) {
        PlantingMethodId = plantingMethodId;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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
}
