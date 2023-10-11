package com.trst01.locationtracker.database.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;


import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddD30Table;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundariesTrackingTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
import com.trst01.locationtracker.database.entity.AddPlantationTable;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CastTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.D20DiseaseTable;
import com.trst01.locationtracker.database.entity.D20FertilizerTable;
import com.trst01.locationtracker.database.entity.D20PestTable;
import com.trst01.locationtracker.database.entity.D20WeedTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
import com.trst01.locationtracker.database.entity.KeyValue;
import com.trst01.locationtracker.database.entity.LookUpDropDownDataTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.ParameterTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.PlotExistOnTable;
import com.trst01.locationtracker.database.entity.ResonForNotPlantingTable;
import com.trst01.locationtracker.database.entity.SampleSlabTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.UOMTable;
import com.trst01.locationtracker.database.entity.UserSectionTable;
import com.trst01.locationtracker.database.entity.UsersTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WarehouseTable;
import com.trst01.locationtracker.database.entity.WeedTable;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class AppDAO {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public abstract void insertCurrentVisitFarmerTable(CurrentVisitFarmerTables currentVisitFarmerTables);


    // TODO: 1/21/2022 get top master sync saved local db data
//    @Query("select *  from StatesTable where Code=:code   ORDER BY stateId DESC LIMIT 1")
//    public abstract StatesTable getTopMasterSyncStatesTablDataLocalDBQuery(String code);

    @Insert(onConflict = REPLACE)
    public abstract void insertFarmerTable(AddFarmerTable divisionTable);
    @Insert(onConflict = REPLACE)
    public abstract void insertFarmerTable(List<AddFarmerTable> divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertPlotTable(AddPlotTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD20Table(AddD20Table divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD10Table(AddD10Table divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD30Table(AddD30Table divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertTrackingTable(AddGeoBoundariesTrackingTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertGeoBoundariesTable(AddGeoBoundriesTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertUserSectionTable(UserSectionTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD20Fertilizer(D20FertilizerTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertGrowthMonitoringIntoLocalDBQuery(AddGrowthMonitoringTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertGrowthMonitoringIntoLocalDBQuery(List<AddGrowthMonitoringTable> divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD20Weed(D20WeedTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD20Disease(D20DiseaseTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD20Pest(D20PestTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertD20Pest(List<D20PestTable> divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertAddPlotOfferTable(AddPlotOfferTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertAddPlantationTable(AddPlantationTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertDivisionTable(DivisionTable divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertDivisionTable(List<DivisionTable> divisionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSectionTable(SectionTable sectionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSectionTable(List<SectionTable> sectionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCircleTable(CircleTable circleTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCircleTable(List<CircleTable> circleTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertResonTable(ResonForNotPlantingTable circleTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertVillageTable(VillageTable villageTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCropTable(CropTable cropTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCropTable(List<CropTable> cropTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertBankTable(BankTable bankTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertBankTable(List<BankTable> bankTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertBranchTable(BranchTable branchTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertDiseaseTable(DiseaseTable diseaseTable);
    @Insert(onConflict = REPLACE)
    public abstract void insertDistrictTable(DistrictTable districtTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertFertilizerTable(FertilizerTable fertilizerTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertMandalTable(MandalTable mandalTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertStateTable(StateTable stateTable);
    @Insert(onConflict = REPLACE)
    public abstract void insertUsersTable(UsersTable usersTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertParameterTable(ParameterTable parameterTableTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertPestTable(PestTable pestTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertPlantSubTypeTable(PlantSubTypeTable plantSubTypeTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertPlantTypeTable(PlantTypeTable plantTypeTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSampleSlabTable(SampleSlabTable sampleSlabTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSeasonTable(SeasonTable seasonTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertUOMTable(UOMTable uomTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCastTable(CastTable uomTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertLookupHdrTable(LookupHDRTable uomTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertLookupDtlTable(LookupDtlTable uomTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertPlotExistOnTable(PlotExistOnTable uomTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertVarietyTable(VarietyTable varietyTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertWarehouseTable(WarehouseTable warehouseTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertWeedTable(WeedTable weedTable);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertSavingComplainImages(SavingComplainImagesTable savingComplainImagesTables);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAddComplainFormTable(AddComplaintsDetailsTable addComplaintsDetailsTable);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertPlotGeoBoundsListTableLocalDB(AddGeoBoundriesTable addGeoBoundriesTable);
    @Insert(onConflict = REPLACE)
    public abstract void insertKeyvalues(KeyValue keyTable);

//    @Query("SELECT distinct * FROM AddPlotOfferTable where serverStatus =:value order by PlotOfferId desc")
//    public abstract List<AddPlotOfferTable> getAddFertilizerDetailsListFromLocalDBNotSync(String value);

//    @Query("SELECT distinct * FROM AddPlotOfferTable where serverStatus =:value order by PlotOfferId desc")
    @Query("SELECT * FROM AddPlotOfferTable where serverStatus =:value order by PlotOfferId desc")
    public abstract List<AddPlotOfferTable> getAddFertilizerDetailsListFromLocalDBNotSync(String value);

    @Query("SELECT distinct * FROM AddPlotOfferTable where FarmerCode =:value order by PlotOfferId desc")
    public abstract List<AddPlotOfferTable> getAddFertilizerDetailsListFromLocalDB(String value);

    @Query("SELECT distinct * FROM AddPlotOfferTable where FarmerCode =:value and status=:status and SeasonCode =:season order by PlotOfferId desc")
    public abstract List<AddPlotOfferTable> getAddFertilizerDetailsListFromLocalDB(String value,String status,String season);

//    @Query("SELECT distinct * FROM AddD10Table where serverStatus =:value order by D10Id desc")
    @Query("SELECT * FROM AddD10Table where serverStatus =:value order by D10Id desc")
    public abstract List<AddD10Table> getAddD10ListFromLocalDBNotSync(String value);

    @Query("SELECT distinct * FROM AddD10Table order by D10Id desc")
    public abstract List<AddD10Table> getAddD10ListFromLocalDBNotSync();

//    @Query("SELECT distinct * FROM AddD20Table where serverStatus =:value order by PlotGenId desc")
    @Query("SELECT  * FROM AddD20Table where serverStatus =:value order by PlotGenId desc")
    public abstract List<AddD20Table> getAddD20ListFromLocalDBNotSync(String value);

//    @Query("SELECT distinct * FROM AddD30Table where serverStatus =:value order by D30Id desc")
    @Query("SELECT  * FROM AddD30Table where serverStatus =:value order by D30Id desc")
    public abstract List<AddD30Table> getAddD30ListFromLocalDBNotSync(String value);

    @Query("SELECT distinct * FROM AddD30Table where SeasonCode =:status and FarmerCode=:farmerCode and PlotNo =:plotCode order by D30Id desc")
    public abstract List<AddD30Table> getAddD30ListFromLocalDBNotSync(String status,String farmerCode,String plotCode);

    @Query("SELECT distinct * FROM AddPlantationTable where sync =:value order by plantationId desc")
    public abstract List<AddPlantationTable> getAddPlantationListFromLocalDBNotSync(String value);

    @Query("SELECT distinct  * FROM AddGeoBoundariesTrackingTable where serverStatus =:value order by GeoBoundariesId desc")
    public abstract List<AddGeoBoundariesTrackingTable> getTrackingDetailsListFromLocalDBNotSync(Boolean value);

    @Query("select *  from AddPlotOfferTable where PlotOfferId=:plotNum ORDER BY PlotOfferId DESC LIMIT 1")
    public abstract AddPlotOfferTable getCheckServerStatusPlotData(String plotNum);

    @Query("select *  from AddGeoBoundriesTable where PlotNo=:plotNum ORDER BY GeoBoundriesId DESC LIMIT 1")
    public abstract AddGeoBoundriesTable getCheckServerStatusPlotGeoBoundariesData(String plotNum);

    @Query("select *  from AddD10Table where PlotNo=:plotNum and SeasonCode=:seasonCode ORDER BY D10Id DESC LIMIT 1")
    public abstract AddD10Table getD10Data(String plotNum,String seasonCode);

    @Query("select *  from AddD20Table where PlotNo=:plotNum and SeasonCode=:seasonCode ORDER BY PlotGenId DESC LIMIT 1")
    public abstract AddD20Table getD20Data(String plotNum,String seasonCode);

    @Query("select *  from AddD30Table where PlotNo=:plotNum and SeasonCode=:seasonCode ORDER BY D30Id DESC LIMIT 1")
    public abstract AddD30Table getD30Data(String plotNum,String seasonCode);

    @Query("select *  from AddD10Table where PlotNo=:plotNum ORDER BY D10Id DESC LIMIT 1")
    public abstract AddD10Table getCheckServerStatusPlotD10Data(String plotNum);

    @Query("select *  from D20DiseaseTable where PlotNo=:plotNum ORDER BY DiseasesIdId DESC LIMIT 1")
    public abstract D20DiseaseTable getCheckServerStatusPlotD20DiseaseData(String plotNum);

    @Query("select *  from D20WeedTable where PlotNo=:plotNum ORDER BY WeedIdD20Id DESC LIMIT 1")
    public abstract D20WeedTable getCheckServerStatusPlotD20WeedData(String plotNum);

    @Query("select *  from D20FertilizerTable where PlotNo=:plotNum ORDER BY FertilizerId DESC LIMIT 1")
    public abstract D20FertilizerTable getCheckServerStatusPlotD20FertilizerData(String plotNum);

    @Query("select *  from D20PestTable where PlotNo=:plotNum ORDER BY PestId DESC LIMIT 1")
    public abstract D20PestTable getCheckServerStatusPlotD20PestData(String plotNum);

    @Query("select *  from AddD20Table where PlotNo=:plotNum ORDER BY PlotGenId DESC LIMIT 1")
    public abstract AddD20Table getCheckServerStatusPlotD20Data(String plotNum);

    @Query("select *  from AddD30Table where PlotNo=:plotNum ORDER BY D30Id DESC LIMIT 1")
    public abstract AddD30Table getCheckServerStatusPlotD30Data(String plotNum);

    @Query("select *  from AddGeoBoundariesTrackingTable where GeoBoundariesId=:geoId ORDER BY GeoBoundariesId DESC LIMIT 1")
    public abstract AddGeoBoundariesTrackingTable getCheckServerStatusTrackingData(String geoId);


    @Query("SELECT distinct * FROM SeasonTable order by seasonId asc")
    public abstract List<SeasonTable> getSeasonListFromLocalDb();


    @Query("select *  from AddGeoBoundriesTable where PlotNo=:plotid ORDER BY PlotNo DESC LIMIT 1")
    public abstract AddGeoBoundriesTable getPlotGeoBoundsListData(String plotid);


    @Query("select *  from CastTable where  Id=:vilageID order by castTableId desc")
    public abstract CastTable getCastDetailsFromLocalDB(int vilageID);

    @Query("select *  from VillageTable where  Id=:vilageID order by villageId desc")
    public abstract VillageTable getVillageDetailsFromLocalDB(int vilageID);

    @Query("select *  from WeedTable where  Id=:vilageID order by weedId desc")
    public abstract WeedTable getWeedById(int vilageID);
//changes
    @Query("select *  from FertilizerTable where  Id=:vilageID order by fertilizerId desc")
    public abstract FertilizerTable getFertilizerById(int vilageID);
//changes
    @Query("select *  from VillageTable where  name=:vilageID order by villageId desc")
    public abstract VillageTable getVillageDetailsFromLocalDB(String vilageID);

    @Query("select *  from AddFarmerTable where  code=:vilageID order by FarmerGenId desc")
    public abstract AddFarmerTable getFarmerDetailsFromLocalDB(String vilageID);

    @Query("select *  from CropTable where  Id=:vilageID order by cropId desc")
    public abstract CropTable getCropListDetailsFromLocalDB(int vilageID);

    @Query("select *  from PlantTypeTable where  Id=:vilageID order by plantTypeId desc")
    public abstract PlantTypeTable getPlantTypeDetailsFromLocalDB(int vilageID);

    @Query("select *  from LookupDtlTable where  Id=:vilageID order by lookupDtlTableId desc")
    public abstract LookupDtlTable getLookupDtlDetailsFromLocalDB(int vilageID);

    @Query("select *  from LookupHDRTable where  Code=:vilageID order by lookupHDRTableId desc")
    public abstract List<LookupHDRTable> getLookupHdrDetailsFromLocalDB(String vilageID);

    @Query("select *  from LookupDtlTable where  LookupMstId=:vilageID order by lookupDtlTableId desc")
    public abstract List<LookupDtlTable> getLookupDtlList(String vilageID);

    @Query("SELECT distinct * FROM AddGeoBoundriesTable where PlotNo =:value order by GeoBoundriesId desc")
    public abstract List<AddGeoBoundriesTable> getPlotGeoBoundariesDetailsFromLocalDB(String value);

//    @Query("SELECT distinct * FROM AddGeoBoundriesTable where Sync =:value order by GeoBoundriesId desc")
    @Query("SELECT * FROM AddGeoBoundriesTable where Sync =:value order by GeoBoundriesId desc")
    public abstract List<AddGeoBoundriesTable> getPlotGeoBoundariesDetailsFromLocalDB(Boolean value);

    @Query("SELECT COUNT(*) FROM AddPlantationTable ")
    public abstract LiveData<Integer> getCountLand();

    @Query("SELECT COUNT(*) FROM AddPlotOfferTable where ServerStatus=:serverStatus")
    public abstract LiveData<Integer> getPlotOfferCount(String serverStatus);

    @Query("SELECT COUNT(*) FROM AddGeoBoundriesTable where Sync=:serverStatus")
    public abstract LiveData<Integer> getGeoBoundariesCount(Boolean serverStatus);

    @Query("SELECT COUNT(*) FROM AddD10Table where Sync=:serverStatus")
    public abstract LiveData<Integer> getD10Count(Boolean serverStatus);

    @Query("SELECT COUNT(*) FROM AddD20Table where Sync=:serverStatus")
    public abstract LiveData<Integer> getD20Count(Boolean serverStatus);

    @Query("SELECT COUNT(*) FROM AddD30Table where ServerStatus=:serverStatus")
    public abstract LiveData<Integer> getD30Count(String serverStatus);

    @Query("SELECT COUNT(*) FROM D20FertilizerTable where Sync=:serverStatus")
    public abstract LiveData<Integer> getD20Fertilizer(Boolean serverStatus);

    @Query("SELECT COUNT(*) FROM D20WeedTable where Sync=:serverStatus")
    public abstract LiveData<Integer> getD20Weed(String serverStatus);

    @Query("SELECT COUNT(*) FROM D20PestTable where Sync=:serverStatus")
    public abstract LiveData<Integer> getD20Pest(Boolean serverStatus);

    @Query("SELECT COUNT(*) FROM D20DiseaseTable where Sync=:serverStatus")
    public abstract LiveData<Integer> getD20Disease(Boolean serverStatus);

    @Query("SELECT COUNT(*) FROM AddGrowthMonitoringTable where Sync=:serverStatus")
    public abstract LiveData<Integer> getGmCount(Boolean serverStatus);

//    @Query("SELECT COUNT(*) FROM AddComplaintsDetailsTable where Sync=:serverStatus")
//    public abstract LiveData<Integer> getD20Disease(Boolean serverStatus);

    @Query("delete from AddGeoBoundriesTable where PlotNo=:plot")
    public abstract void deleteAddGeoBoundariesTable(String plot);

    @Query("select *  from PlotExistOnTable where  Id=:vilageID order by plotExistOnId desc")
    public abstract PlotExistOnTable getPlotExistOnDetailsFromLocalDB(int vilageID);

    @Query("select distinct *  from PlotExistOnTable order by plotExistOnId desc")
    public abstract List<PlotExistOnTable> getPlotExistOnDetailsFromLocalDB();

    @Query("select *  from VarietyTable where  Id=:vilageID order by varietyId desc")
    public abstract VarietyTable getVarietyFromLocalDB(int vilageID);

    @Query("select *  from MANDALTABLE where  Id=:manId order by mandalId desc")
    public abstract MandalTable getMandalDetailsFromLocalDB(int manId);

    @Query("select *  from SectionTable where  Id=:secId order by sectionId desc")
    public abstract SectionTable getSectionDetailsFromLocalDB(int secId);

    @Query("select *  from CircleTable where  Id=:cirId order by circleId desc")
    public abstract CircleTable getCircleDetailsFromLocalDB(int cirId);

    @Query("select *  from DivisionTable where  Id=:divId order by divisionId desc")
    public abstract DivisionTable getDivisionDetailsFromLocalDB(int divId);

    @Query("select *  from DistrictTable where  Id=:divId order by districtId desc")
    public abstract DistrictTable getDistrictDetailsFromLocalDB(int divId);

    @Query("select *  from BankTable where  Id=:bankId order by bankId desc")
    public abstract BankTable getBankDetailsFromLocalDB(int bankId);

    @Query("select *  from BranchTable where  Id=:bankId order by branchId desc")
    public abstract BranchTable getBranchDetailsFromLocalDB(int bankId);

    @Query("SELECT distinct * FROM ResonForNotPlantingTable order by  ResonForNotPlantingTableId desc")
    public abstract List<ResonForNotPlantingTable> getReasonDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM PlantTypeTable order by  plantTypeId desc")
    public abstract List<PlantTypeTable> getPlantTypeDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM PlantSubTypeTable order by  plantSubTypeId desc")
    public abstract List<PlantSubTypeTable> getPlantSubTypeDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM PlantSubTypeTable where plantTypeId=:PlantTypeId order by  plantSubTypeId desc")
    public abstract List<PlantSubTypeTable> getPlantSubTypeDetailsListFromLocalDBByPlantType(int PlantTypeId);

    @Query("SELECT distinct * FROM CropTable order by  cropId desc")
    public abstract List<CropTable> getCropDetailsListFromLocalDB();

//    @Query("SELECT * FROM CropTable order by  cropId desc")
//    public abstract List<CropTable> getCropDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM VarietyTable order by  varietyId desc")
    public abstract List<VarietyTable> getVarietyDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM VillageTable order by  villageId desc")
    public abstract List<VillageTable> getVillageDetailsListFromLocalDB();

//    @Query("SELECT * FROM VillageTable v inner join UserSectionTable us on v.sectionId= us.SectionId order by  villageId desc")
    @Query("SELECT distinct v.* FROM VillageTable v inner join UserSectionTable us on v.sectionId= us.SectionId order by  villageId desc")
    public abstract List<VillageTable> getVillageListBasedOnSectionFromLocalDBStatus();

//    @Query("SELECT distinct * FROM AddFarmerTable order by FarmerGenId desc")
    @Query("SELECT  * FROM AddFarmerTable order by FarmerGenId desc")
    public abstract List<AddFarmerTable> getFarmerDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM AddFarmerTable order by FarmerGenId desc")
    public abstract List<AddFarmerTable> getFarmerDetailsListFromLocalDBSection();

    @Query("SELECT * FROM LookUpDropDownDataTable where Code=:selectCode order by lookUpId asc")
    public abstract List<LookUpDropDownDataTable> getLookUpDropDownDataListFromLocalDB(String selectCode);

    @Query("select *  from LookUpDropDownDataTable where  Id=:selectionID order by LookupHdrId asc")
    public abstract LookUpDropDownDataTable getLookUpDropDownDetailsFromLocalDB(String selectionID);

    @Query("SELECT distinct * FROM PestTable order by pestId desc")
    public abstract List<PestTable> getPestTableDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM FertilizerTable order by fertilizerId desc")
    public abstract List<FertilizerTable> getFertilizerDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM DiseaseTable order by diseaseId desc")
    public abstract List<DiseaseTable> getDiseaseTableDetailsListFromLocalDB();

    @Query("SELECT distinct * FROM WeedTable order by weedId desc")
    public abstract List<WeedTable> getWeedDetailsListFromLocalDB();

    @Query("SELECT COUNT(*) FROM AddComplaintsDetailsTable")
    public abstract LiveData<Integer> getAddComplaintsDetailsCount();

    @Query("select *  from AddComplaintsDetailsTable where  LogBookNo=:logBookCode ORDER BY ComplaintsId ASC LIMIT 1")
    public abstract AddComplaintsDetailsTable getAddComplainFormTableData(String logBookCode);


    @Query("select *  from AddComplaintsDetailsTable ORDER BY ComplaintsId desc")
    public abstract List<AddComplaintsDetailsTable> getAddComplainFormTableDataList();

    @Query("SELECT COUNT(*) FROM AddComplaintsDetailsTable where serverStatus=:notSyncValue")
    public abstract LiveData<Integer> getComplainsNotSyncCountFromLocalDB(String notSyncValue);

    @Query("SELECT COUNT(*) FROM AddGrowthMonitoringTable where serverStatus=:notSyncValue")
    public abstract LiveData<Integer> getGMNotSyncCountFromLocalDB(String notSyncValue);

    @Query("DELETE FROM AddComplaintsDetailsTable where serverStatus=:syncStatus")
    public abstract void deleteSyncAddComplainTable(String syncStatus);

    //complain Data
    @Query("SELECT * FROM AddComplaintsDetailsTable where serverStatus =:value order by ComplaintsId desc")
    public abstract List<AddComplaintsDetailsTable> getAddComplainDataDetailsListFromLocalDBNotSync(String value);

    @Query("select *  from AddComplaintsDetailsTable where LogBookNo=:logBookNumber and IsActive =:value ORDER BY ComplaintsId DESC LIMIT 1")
    public abstract AddComplaintsDetailsTable getTopAddComplainDataDetailsTableDataLocalDB(String logBookNumber,String value);

    @Query("select *  from SavingComplainImagesTable where  LogBookNo=:logbook_no ORDER BY SavingComplainImagesID DESC LIMIT 1")
    public abstract SavingComplainImagesTable getComplainImageFromLocalDb(String logbook_no);

    @Query("SELECT * FROM SavingComplainImagesTable order by SavingComplainImagesID desc")
    public abstract List<SavingComplainImagesTable> getComplainImagesFromLocalDB();

    @Query("DELETE FROM SavingComplainImagesTable where serverStatus=:syncStatus")
    public abstract void deleteSyncAddComplainImagesTable(String syncStatus);

    @Query("DELETE FROM AddGrowthMonitoringTable where serverStatus=:syncStatus")
    public abstract void deleteSyncGrowthMonitoringTable(String syncStatus);

    @Query("SELECT * FROM SavingComplainImagesTable where serverStatus =:value order by SavingComplainImagesID desc")
    public abstract List<SavingComplainImagesTable> getComplainImagesFromLocalDBNotSync(String value);

    @Query("SELECT * FROM AddGrowthMonitoringTable where serverStatus =:value order by gmId desc")
    public abstract List<AddGrowthMonitoringTable> getGMFromLocalDBNotSync(String value);

    //    @Query("SELECT DISTINCT f.[Id],[Code],[Name] FROM AddFarmerTable f INNER JOIN AddPlotOfferTable p ON p.FarmerCode=f.Code WHERE p.SeasonCode =:season")
//    @Query("SELECT DISTINCT f.[Id],[Code],[Name],[FarmerGenId] FROM AddFarmerTable f INNER JOIN AddPlotOfferTable p ON p.FarmerCode=f.Code WHERE p.SeasonCode =:season")
    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN AddPlotOfferTable p ON p.FarmerCode=f.Code WHERE p.SeasonCode =:season ")
    public abstract List<AddFarmerTable> getFarmerDetailsListFromLocalDB(String season);

    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN AddD10Table p ON p.FarmerCode=f.Code INNER JOIN ADDPLOTTABLE pt ON pt.FarmerCode=f.Code WHERE p.SeasonCode =:season and pt.SeasonCode=:season and pt.Stage=:stage")
    public abstract List<AddFarmerTable> getFarmerDetailsListFromLocalDBMeasured(String season,String stage);

    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN ADDPLOTTABLE pt ON pt.FarmerCode=f.Code WHERE  pt.SeasonCode=:season and pt.Stage!=:stage")
//    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN ADDPLOTTABLE pt ON pt.FarmerCode=f.Code WHERE  :season!=null and pt.Stage!=:stage")
    public abstract List<AddFarmerTable> getFarmerDetailsListFromLocalDBPlot(String season,String stage);

    @Query("SELECT * FROM AddGrowthMonitoringTable g WHERE  g.SeasonCode=:season and g.farmerCode=:farmerCode and g.PlotNo=:plot and g.Stage=:stage and g.Remarks=:remarks")
    public abstract List<AddGrowthMonitoringTable> getGwList(String season,String farmerCode,String plot,String stage,String remarks);

    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN AddD20Table p ON p.FarmerCode=f.Code INNER JOIN ADDPLOTTABLE pt ON pt.FarmerCode=f.Code WHERE p.SeasonCode =:season and pt.SeasonCode=:season and pt.Stage=:stage")
    public abstract List<AddFarmerTable> getFarmerDetailsListFromLocalDBAgreemented(String season,String stage);

//    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN VillageTable p ON p.villageId=f.Code INNER JOIN UserSectionTable pt ON pt.SectionId=p.sectionId WHERE p.SeasonCode =:season and pt.SeasonCode=:season")
//    @Query("SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN (VillageTable p ON p.villageId=f.Code INNER JOIN UserSectionTable pt ON pt.SectionId=p.sectionId)")
//    @Query("Select * from (SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN ADDPLOTTABLE p ON p.FarmerCode=f.Code WHERE p.SeasonCode =:season) test Inner Join" +
//            " (SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN (Select * from VillageTable p  INNER JOIN UserSectionTable pt ON pt.SectionId=p.sectionId) x on f.VillageId=x.id) match   ")
//    @Query("Select * from (SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN ADDPLOTTABLE p ON p.FarmerCode=f.Code WHERE p.SeasonCode =:season) test Inner Join (Select * from VillageTable p  INNER JOIN UserSectionTable pt ON pt.SectionId=p.sectionId) x on test.VillageId=x.id ")
    @Query( "Select test.* from (SELECT DISTINCT f.* FROM AddFarmerTable f INNER JOIN ADDPLOTTABLE p ON p.FarmerCode=f.Code WHERE p.SeasonCode =:season) test Inner Join (Select * from VillageTable p  INNER JOIN UserSectionTable pt ON pt.SectionId=p.sectionId) x on test.VillageId=x.id")
    public abstract List<AddFarmerTable> getFarmerDetailsListUserSection(String season);
//    public abstract List<AddFarmerTable> getFarmerDetailsListUserSection(String season);

    @Query("SELECT distinct * FROM AddPlotTable WHERE FarmerCode=:farmerCode ORDER BY PlotGenId DESC")
    public abstract List<AddPlotTable> getPlotListByFarmerCode(String farmerCode);

    @Query("SELECT distinct * FROM AddPlotTable WHERE Stage=:status and  FarmerCode=:farmerCode and  PlotNo=:plotCode Order BY PlotGenId DESC")
    public abstract List<AddPlotTable> getPlotListByStatus(String status,String farmerCode,String plotCode);

    @Query("SELECT distinct * FROM AddPlotTable WHERE  FarmerCode=:farmerCode and  PlotNo=:plotCode Order BY PlotGenId DESC")
    public abstract List<AddPlotTable> getPlotListByStatus(String farmerCode,String plotCode);

//    @Query("select * from AddD20Table where FarmerCode=:status and SeasonCode=:seasonCode ORDER BY PlotGenId DESC")
@Query("select Distinct * from AddD20Table D10 inner join AddPlotTable p on p.PlotNo=d10.PlotNo where D10.FarmerCode=:farmerCode and p.Stage= :status and p.SeasonCode=d10.SeasonCode and p.SeasonCode=:seasonCode ORDER BY PlotGenId DESC")
public abstract List<AddD20Table> getPlotListByStatusSeasonCode(String seasonCode,String farmerCode,String status);
//
//    @Query("select * from AddD20Table D20 inner join AddPlotTable p on p.PlotNo=d20.PlotNo where p.Stage= :status and p.SeasonCode=:seasonCode ORDER BY PlotGenId DESC")
//    public abstract List<AddD20Table> getPlotListByStatusSeasonCode(String seasonCode,String status);

    @Query("select Distinct * from AddD10Table D10 inner join AddPlotTable p on p.PlotNo=d10.PlotNo where D10.FarmerCode=:farmerCode and p.Stage= :status and p.SeasonCode=d10.SeasonCode")
    public abstract List<AddD10Table> getPlotListByStatusSeasonCode10(String status,String farmerCode);

    @Query("select Distinct * from D20DiseaseTable d10  where d10.PlotNo=:plotNo ORDER BY id DESC")
    public abstract List<D20DiseaseTable> getD20DiseaseList(String plotNo);

    @Query("select Distinct * from D20DiseaseTable d10  where Sync=:sync ORDER BY id DESC")
    public abstract List<D20DiseaseTable> getD20DiseaseList(Boolean sync);

//    @Query("select Distinct * from D20DiseaseTable d10  where Sync=:sync and IsActive=:isActive ORDER BY id DESC")
    @Query("select  * from D20DiseaseTable d10  where Sync=:sync and IsActive=:isActive ORDER BY id DESC")
    public abstract List<D20DiseaseTable> getD20DiseaseList(Boolean sync,String isActive);

    @Query("select Distinct * from D20DiseaseTable d10  where d10.PlotNo=:plotNo and Sync=:sync Order BY id DESC")
    public abstract List<D20DiseaseTable> getD20DiseaseList(String plotNo,Boolean sync);

    @Query("select Distinct * from D20WeedTable d10  where d10.PlotNo=:plotNo and d10.isActive=:isActive ORDER BY id DESC")
    public abstract List<D20WeedTable> getD20WeedListActive(String plotNo,Boolean isActive);

    @Query("select Distinct * from D20WeedTable d10  where d10.PlotNo=:plotNo ORDER BY id DESC")
    public abstract List<D20WeedTable> getD20WeedListActive(String plotNo);

    @Query("select Distinct * from D20WeedTable d10  where d10.PlotNo=:plotNo and Sync=:sync  ORDER BY id DESC")
    public abstract List<D20WeedTable> getD20WeedList(String plotNo,Boolean sync);

    @Query("select Distinct * from D20WeedTable d10  where  IsActive=:sync  ORDER BY id DESC")
    public abstract List<D20WeedTable> getD20WeedList(String sync);

//    @Query("select Distinct * from D20WeedTable d10  where  Sync=:sync and IsActive=:isActive ORDER BY id DESC")
    @Query("select  * from D20WeedTable d10  where  Sync=:sync and IsActive=:isActive ORDER BY id DESC")
    public abstract List<D20WeedTable> getD20WeedList(String sync,String isActive);

    @Query("select Distinct * from D20FertilizerTable d10  where d10.PlotNo=:plotNo ORDER BY id DESC")
    public abstract List<D20FertilizerTable> getD20FertilizerList(String plotNo);

    @Query("select Distinct * from D20FertilizerTable d10  where d10.PlotNo=:plotNo and Sync=:sync ORDER BY id DESC")
    public abstract List<D20FertilizerTable> getD20FertilizerList(String plotNo,Boolean sync);

//    @Query("select Distinct * from D20FertilizerTable d10  where  Sync=:sync and IsActive=:IsActive ORDER BY id DESC")
    @Query("select  * from D20FertilizerTable d10  where  Sync=:sync and IsActive=:IsActive ORDER BY id DESC")
    public abstract List<D20FertilizerTable> getD20FertilizerList(Boolean sync,String IsActive);
//    @Query("select Distinct * from D20DiseaseTable d10  where d10.PlotNo=:plotNo ORDER BY id DESC")

    @Query("select Distinct * from D20PestTable d10  where d10.PlotNo=:plotNo ORDER BY id DESC")
    public abstract List<D20PestTable> getD20PestList(String plotNo);

    @Query("select Distinct * from D20PestTable d10  where d10.PlotNo=:plotNo and Sync=:sync ORDER BY id DESC")
    public abstract List<D20PestTable> getD20PestList(String plotNo,Boolean sync);

//    @Query("select Distinct * from D20PestTable d10  where  Sync=:sync and IsActive=:IsActive ORDER BY id DESC")
    @Query("select  * from D20PestTable d10  where  Sync=:sync and IsActive=:IsActive ORDER BY id DESC")
    public abstract List<D20PestTable> getD20PestList(Boolean sync,Boolean IsActive);

//    @Query("select p.* from AddPlotOfferTable p left join AddD10Table d10 on p.OfferNo=d10.OfferedNo where D10.Id=null and p.status= :status and p.SeasonCode=d10.SeasonCode ORDER BY PlotOfferId DESC")
//    @Query("select * from AddPlotOfferTable p left join AddD10Table d10 on p.OfferNo=d10.OfferedNo where D10.Id=null and p.status= :status and p.SeasonCode=d10.SeasonCode ORDER BY PlotOfferId DESC")
    @Query("select distinct p.* from AddPlotOfferTable p left join AddD10Table d10 on p.OfferNo=d10.OfferedNo where  p.status=:status   and p.SeasonCode=:seasonCode and p.FarmerCode=:farmerCode and d10.OfferedNo isNull ORDER BY PlotOfferId DESC")
    public abstract List<AddPlotOfferTable> getPlotOfferListByStatusSeasonCode10(String seasonCode,String farmerCode,String status);

    @Query("select * from AddD10Table D10  where D10.FarmerCode=:farmerCode and D10.SeasonCode=:seasonCode ORDER BY D10Id DESC")
    public abstract List<AddD10Table> getD10ListByStatusSeasonCode(String seasonCode,String farmerCode);

    @Query("SELECT distinct * FROM AddPlotOfferTable t WHERE t.FarmerCode=:farmerCode and t.SeasonCode=:season" +
            "    AND t.Status = :status ORDER BY PlotOfferId DESC")
    public abstract List<AddPlotOfferTable> getPlotOfferListD10(String season,String farmerCode,String status);

    @Query("  SELECT distinct * FROM AddPlotOfferTable t WHERE t.FarmerCode=:farmerCode and t.SeasonCode=:season and t.OfferNo=:offer" +
            "    AND t.Status = :status ORDER BY PlotOfferId DESC")
    public abstract List<AddPlotOfferTable> getPlotOfferListD10(String season,String farmerCode,String status,String offer);
//
//    @Query("  SELECT * FROM AddPlotOfferTable t LEFT JOIN AddD10Table d on d.SeasonCode=t.SeasonCode and d.OfferedNo=t.OfferNo " +
//            "inner join PlantTypeTable pt on pt.Id=t.PlantTypeId " +
//            "left join VarietyTable va on va.Id=t.ExpectedVarityId " +
//            "WHERE t.FarmerCode=:farmerCode and t.SeasonCode=:season and t.Active = 1" +
//            "    AND d.Id ISNULL and t.Status = :status ORDER BY PlotOfferId DESC")
//    public abstract List<AddPlotOfferTable> getPlotOfferListD10(String season,String farmerCode,String status);

    @Query("UPDATE AddD10Table set InterCropId = :crop,IsMicronutrientDeficiency= :micro,IsTrashMulching= :trash,IsGapsFilled=:gaps,WeedStatusId=:weedStatus,BioFertilizerAppliedArea=:bioArea,DeepPloughedArea=:deepArea,DeTrashingArea=:trashArea,EarthingUpArea=:earthArea,RatoonManagedUsedArea=:ratoonArea,TrashShedderArea=:trashShedderArea,LoadShedderArea=:LoadShedderArea, UpdatedDate = :updatedDate,CreatedDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updateD10Agreemented(String crop,String micro,String trash,String gaps, String weedStatus, String bioArea, String deepArea, String trashArea, String earthArea,String ratoonArea,String trashShedderArea,String LoadShedderArea, Boolean sync, String updatedDate,String plotNO,String serverStatus);

    @Query("UPDATE AddD30Table set InterCropId = :crop,IsMicronutrientDeficiency= :micro,IsTrashMulching= :trash,IsGapsFilled=:gaps,WeedStatusId=:weedStatus,BioFertilizerAppliedArea=:bioArea,DeepPloughedArea=:deepArea,DeTrashingArea=:trashArea,EarthingUpArea=:earthArea,RatoonManagedUsedArea=:ratoonArea,TrashShedderArea=:trashShedderArea,LoadShedderArea=:LoadShedderArea, UpdatedDate = :updatedDate,CreatedDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updateD30Agreemented(String crop,String micro,String trash,String gaps, String weedStatus, String bioArea, String deepArea, String trashArea, String earthArea,String ratoonArea,String trashShedderArea,String LoadShedderArea, Boolean sync, String updatedDate,String plotNO,String serverStatus);

    @Query("UPDATE AddD10Table set SoilTypeId = :soilType,SpacingId= :spacing,PreviousCropId= :previous,IrrigationTypeId=:IrrigationTypeId,IsSettsHotWaterTreatment=:IsSettsHotWaterTreatment,IsDustApplied=:IsDustApplied,IsTrashMulching=:IsTrashMulching,IsPreviousRedPlot=:IsPreviousRedPlot,IsBasalFertilization=:IsBasalFertilization,IsCompositeFormYard=:IsCompositeFormYard,IsFilterPressMud=:IsFilterPressMud,IsGreenManures=:IsGreenManures, UpdatedDate = :updatedDate,CreatedDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updateD10(String soilType,String spacing,String previous,String IrrigationTypeId, String IsSettsHotWaterTreatment, String IsDustApplied, String IsTrashMulching, String IsPreviousRedPlot, String IsBasalFertilization,String IsCompositeFormYard,String IsFilterPressMud,String IsGreenManures, Boolean sync, String updatedDate,String plotNO,String serverStatus);

    @Query("UPDATE AddD20Table set MeasureArea = :measureArea,AgreedTon=:agreedTon, InspectionDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updatePLotNo(String measureArea,String agreedTon, Boolean sync, String updatedDate,String plotNO,String serverStatus);

    @Query("UPDATE AddD10Table set InterCropId = :interCrop,WeedStatusId= :weedStatusId,UpdatedDate = :updatedDate,CreatedDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updateD10(String interCrop, Boolean sync, String updatedDate,String plotNO,String serverStatus,String weedStatusId);

    @Query("UPDATE AddD20Table set InterCropId = :interCrop,WeedStatusId= :weedStatusId,UpdatedDate = :updatedDate,CreatedDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updateD20(String interCrop, Boolean sync, String updatedDate,String plotNO,String serverStatus,String weedStatusId);

    @Query("UPDATE AddD30Table set InterCropId = :interCrop,WeedStatusId= :weedStatusId,UpdatedDate = :updatedDate,CreatedDate = :updatedDate,Sync =:sync,ServerStatus= :serverStatus  WHERE  PlotNo = :plotNO")
    public abstract void updateD30(String interCrop, Boolean sync, String updatedDate,String plotNO,String serverStatus,String weedStatusId);

//    @Query("UPDATE D20WeedTable set isActive = :measureArea, updatedDate = :updatedDate,ServerStatus= :serverStatus,Sync=:sync,IsActive=:isActive  WHERE  PlotNo = :plotNO and WeedId=:weedId")
//    @Query("UPDATE D20WeedTable set  updatedDate = :updatedDate,ServerStatus= :serverStatus,Sync=:sync,IsActive=:isActive  WHERE  PlotNo = :plotNO and WeedId=:weedId ")
    @Query("UPDATE D20WeedTable set  updatedDate = :updatedDate,ServerStatus= :serverStatus,Sync=:sync,IsActive=:isActive  WHERE  id=:weedId ")
    public abstract void updateD20Weed(String weedId,String updatedDate,String serverStatus,String sync,String isActive);

    @Query("UPDATE D20FertilizerTable set  updatedDate = :updatedDate,ServerStatus= :serverStatus,Sync=:sync,IsActive=:isActive  WHERE  id=:weedId ")
    public abstract void updateD20Fertilizer(String weedId,String updatedDate,String serverStatus,String sync,String isActive);

    @Query("UPDATE AddPlotTable set MeasureArea = :measureArea, InspectionDate = :updatedDate, Stage = :stage  WHERE  PlotNo = :plotNO")
    public abstract void updatePLotNo(String measureArea, String updatedDate,String plotNO,String stage);

    @Query("UPDATE D20WeedTable set updatedDate = :updatedDate,Sync=:sync,ServerStatus=:server,isActive=:isActive WHERE id = :id" )
    public abstract void updateWeedDate( String updatedDate,String id,String server,Boolean sync,Boolean isActive);

    @Query("UPDATE D20FertilizerTable set updatedDate = :updatedDate,Sync=:sync,ServerStatus=:server,isActive=:isActive WHERE id = :id" )
    public abstract void updateFertilizerDate( String updatedDate,String id,String server,Boolean sync,Boolean isActive);

//    @Query("UPDATE D20DiseaseTable set IdentifiedDate = :identifiedDate,ControlDate = :controlDate, updatedDate = :updatedDate,Sync=:sync,ServerStatus=:server,isActive=:isActive WHERE id = :id" )
//    public abstract void updateFertilizerDate(String identifiedDate, String controlDate, String updatedDate,String id,String server,Boolean sync,Boolean isActive);

    @Query("UPDATE D20DiseaseTable set IdentifiedDate = :identifiedDate,ControlDate = :controlDate, updatedDate = :updatedDate,Sync=:sync,ServerStatus=:server,isActive=:isActive WHERE id = :id" )
    public abstract void updateDiseaseControlDate(String identifiedDate, String controlDate, String updatedDate,String id,String server,Boolean sync,Boolean isActive);

    @Query("UPDATE D20PestTable set IdentifiedDate = :identifiedDate,ControlDate = :controlDate, updatedDate = :updatedDate,Sync=:sync,ServerStatus=:server,isActive=:isActive WHERE id = :id" )
    public abstract void updatePestControlDate(String identifiedDate, String controlDate, String updatedDate,String id,String server,Boolean sync,Boolean isActive);

    @Query("UPDATE D20DiseaseTable set IdentifiedDate = :identifiedDate, updatedDate = :updatedDate,Sync=:sync,ServerStatus=:server,isActive=:isActive WHERE id = :id" )
    public abstract void updateDiseaseIdentifiedDate(String identifiedDate, String updatedDate,String id,String server,Boolean sync,Boolean isActive);

    @Query("UPDATE AddPlotTable set AggrementedArea = :measureArea, InspectionDate = :updatedDate, Stage = :stage  WHERE  PlotNo = :plotNO")
    public abstract void updatePLotNoAgreemented(String measureArea, String updatedDate,String plotNO,String stage);

    @Query("UPDATE AddPlotTable set SoilTypeId=:soilTypeID, IsSettsHotWaterTreatment = :isSetts, IsDustApplied = :isDust," +
            " IsTrashMulching = :isTrash, SpacingId=:spacingId , IsPreviousRedPlot=:isPreviousRedPlot, " +
            "IsBasalFertilization=:isBasalFertilization,IsCompositeFormYard=:isCompositeFormYard,IsFilterPressMud=:IsFilterPressMud," +
            "IsGreenManures=:IsGreenManures WHERE  PlotNo = :plotNO")
    public abstract void updateD10Growth(String soilTypeID,Boolean isSetts, Boolean isDust,
                                         Boolean isTrash,String plotNO,String spacingId,Boolean isPreviousRedPlot,
                                         Boolean isBasalFertilization, Boolean isCompositeFormYard,Boolean IsFilterPressMud,Boolean IsGreenManures);

    @Query("UPDATE AddPlotTable set InterCropId=:interCropId, IsMicronutrientDeficiency = :isMicro, IsGapsFilled = :isGaps," +
            " BioFertilizerAppliedArea = :bioArea, DeepPloughedArea=:deepArea , DeTrashingArea=:isTrash, " +
            "EarthingUpArea=:earthArea,RatoonManagedUsedArea=:ratoonArea,TrashShedderArea=:trashArea," +
            "LoadShedderArea=:loadArea WHERE  PlotNo = :plotNO")
    public abstract void updateD30Growth(String interCropId,Boolean isMicro, Boolean isGaps,String bioArea,String deepArea,
                                         String isTrash,String plotNO,String earthArea,String ratoonArea,
                                         String trashArea, String loadArea);


    @Query("SELECT value FROM KeyValue WHERE `key` = :searchKey")
    public abstract int getValueForKey(String searchKey);


//    @Query("UPDATE AddPlotTable set Stage = :stage  WHERE  PlotNo = :plotNO")
//    public abstract void updatePLotNoReported(String measureArea, String updatedDate,String plotNO,String stage);
//
//    @Query("select *  from GeoBoundariesTable order by GeoID desc")
//    public abstract List<GeoBoundariesTable> getGeoBoundariesTableDataList();

//    @Query("SELECT COUNT(*) FROM FarmerTable")
//    public abstract LiveData<Integer> getCount();

//    @Query("SELECT COUNT(*) FROM AddFertilizerDetailsTable where LogBookNo =:logBookNumber  order by FertilizerId desc")
//    public abstract LiveData<Integer> getFertilizerDataCount(String logBookNumber);

//    @Query("SELECT * FROM SavingFarmerProfieImagesTable where serverStatus =:value order by ID asc")
//    public abstract List<SavingFarmerProfieImagesTable> getFarmerProfileImagesFromLocalDBNotSync(String value);

//    @Query("SELECT * FROM BankDetailsSubmitTable")
//    public abstract List<BankDetailsSubmitTable> getBankDetailsSubmitTableFromLocalDb();

    //    @Query("SELECT * FROM GeoBoundariesTable where serverSend =:value order by GeoID desc")
//    public abstract List<GeoBoundariesTable> getGeoBoundariesTableTableFromLocalDb(String value);


//    @Query("SELECT * FROM GeoBoundariesTable where serverSend =:value order by GeoID asc")
//    public abstract List<GeoBoundariesTable> getGeoBoundariesTableTableFromLocalDbList(String value);
//
//    @Query("SELECT * FROM GeoBoundariesTable")
//    public abstract List<GeoBoundariesTable> getGeoBoundariesTableTableFromLocalDb();
//    @Query("SELECT * FROM BankDetailsSubmitTable where FarmerCode=:farmerCode ORDER BY BankID ASC LIMIT 1")
//    public abstract List<BankDetailsSubmitTable> getBankDetailsByfarmerCode(String farmerCode);
//
//    @Query("SELECT VillageTable.villageId ,VillageTable.Id , VillageTable.Name,VillageTable.Code,VillageTable.MandalId,VillageTable.IsActive,VillageTable.CreatedDate,VillageTable.CreatedByUserId,VillageTable.UpdatedDate,VillageTable.UpdatedByUserId,VillageTable.PinCode FROM VillageTable " +
//            "INNER JOIN ClusterDTLTable ON ClusterDTLTable.VillageId = VillageTable.Id " +
//            "WHERE ClusterDTLTable.ClusterHDRId=:clusterID")
//    public abstract List<VillageTable> findVillageIdbyClusterId(String clusterID);
//
//    @Query("select *  from AddGeoBoundriesTable where  LogBookNo=:logBookNum order by GeoBoundriesId asc")
//    public abstract List<AddGeoBoundriesTable> getGeoBoundriesDetailsTableDetailsFromLocalDB(String logBookNum);
//
//     TODO: farmer not sync count
//    @Query("SELECT COUNT(*) FROM FarmerTable where pushToServer=:notSyncValue")
//    public abstract LiveData<Integer> getFarmerNotSyncCountFromLocalDB(String notSyncValue);

    @Query("DELETE FROM DivisionTable")
    public abstract void deleteDivisionTable();

    @Query("DELETE FROM SectionTable")
    public abstract void deleteSectionTable();

    @Query("DELETE FROM VillageTable")
    public abstract void deleteVillageTable();

    @Query("DELETE FROM CropTable")
    public abstract void deleteCropTable();

    @Query("DELETE FROM CircleTable")
    public abstract void deleteCircleTable();

    @Query("DELETE FROM BankTable")
    public abstract void deleteBankTable();

    @Query("DELETE FROM BranchTable")
    public abstract void deleteBranchTable();

    @Query("DELETE FROM DiseaseTable")
    public abstract void deleteDiseaseTable();

    @Query("DELETE FROM DistrictTable")
    public abstract void deleteDistrictTable();

    @Query("DELETE FROM FertilizerTable")
    public abstract void deleteFertilizerTable();

    @Query("DELETE FROM MandalTable")
    public abstract void deleteMandalTable();

    @Query("DELETE FROM StateTable")
    public abstract void deleteStateTable();

    @Query("DELETE FROM UsersTable")
    public abstract void deleteUsersTable();

    @Query("DELETE FROM ParameterTable")
    public abstract void deleteParameterTable();

    @Query("DELETE FROM PestTable")
    public abstract void deletePestTable();

    @Query("DELETE FROM PlantTypeTable")
    public abstract void deletePlantTypeTable();

    @Query("DELETE FROM PlantSubTypeTable")
    public abstract void deletePlantSubTypeTable();

    @Query("DELETE FROM SampleSlabTable")
    public abstract void deleteSampleSlabTable();

    @Query("DELETE FROM SeasonTable")
    public abstract void deleteSeasonTable();

    @Query("DELETE FROM UOMTable")
    public abstract void deleteUOMTable();

    @Query("DELETE FROM VarietyTable")
    public abstract void deleteVarietyTable();

    @Query("DELETE FROM WarehouseTable")
    public abstract void deleteWarehouseTable();

    @Query("DELETE FROM WeedTable")
    public abstract void deleteWeedTable();

    @Query("DELETE FROM PlotExistOnTable")
    public abstract void deletePlotExistOnTable();

    @Query("DELETE FROM LookupDtlTable")
    public abstract void deleteLookupDtlTable();

    @Query("DELETE FROM LookupHDRTable")
    public abstract void deleteLookupHDRTable();

    @Query("DELETE FROM ResonForNotPlantingTable")
    public abstract void deleteReasonsTable();

    @Query("DELETE FROM CastTable")
    public abstract void deleteCastTable();

//    @Query("delete from FarmerTable where pushToServer=:syncStatus")
//    public abstract void deleteSyncFarmerDetailListTable(String syncStatus);

    @Query("DELETE FROM AddFarmerTable")
    public abstract void deleteFarmerTable();

//    @Query("DELETE FROM AddFarmerTable WHERE ")
//    public abstract void deleteFarmerTable();

    @Query("DELETE FROM AddPlotTable")
    public abstract void deletePlotTable();

    @Query("DELETE FROM AddD20Table WHERE ServerStatus = :serverStatus" )
    public abstract void deletePlotD20Table(String serverStatus);

    @Query("DELETE FROM AddGeoBoundriesTable WHERE Sync = :sync")
    public abstract void deleteGeoBoundriesTable(Boolean sync);

    @Query("DELETE FROM AddGeoBoundariesTrackingTable WHERE serverStatus = :serverStatus")
    public abstract void deleteGeoBoundriesTrackingTable(Boolean serverStatus);

    @Query("DELETE FROM AddPlotOfferTable WHERE ServerStatus = :serverStatus")
    public abstract void deleteAddPlotOfferTable(String serverStatus);

    @Query("DELETE FROM UserSectionTable")
    public abstract void deleteUserSectionTable();

    @Query("DELETE FROM D20FertilizerTable where ServerStatus=:isActive")
    public abstract void deleteD20FertilizerTable(String isActive);

    @Query("DELETE FROM D20WeedTable where isActive=:isActive")
    public abstract void deleteD20WeedTable(String isActive);

    @Query("DELETE FROM D20DiseaseTable where isActive=:isActive")
    public abstract void deleteD20DiseaseTable(Boolean isActive);

    @Query("DELETE FROM D20PestTable where isActive=:isActive")
    public abstract void deleteD20PestTable(Boolean isActive);

//    ();
//    ();
//    ();
//    ();

//     TODO: 2/18/2022 log book module clear data
//
//    @Query("delete from AddFertilizerDetailsTable where LogBookNo=:logBookNum")
//    public abstract void deleteAddFertilizerDetailsTable(String logBookNum);
//
//
//    @Query("DELETE FROM AddLandPreparationTable where LogBookNo=:logBookNum")
//    public abstract void deleteLandPreparationDetailsTable(String logBookNum);
//
//
//     TODO: 2/28/2022  get sync  table values to delete from local db
//
//
//    @Query("DELETE FROM RefreshTableDateCheck")
//    public abstract void deleteRefreshTableDateCheck();


//    @Transaction
//    public void deleteLogBookAllTables(String logBookNum) {
//        // Anything inside this method runs in a single transaction.
//        deleteAddFertilizerDetailsTable(logBookNum);
////        deleteAddOrganicDetailsTable(logBookNum);
////        deleteAddWaterRegimeSeasonDetailsTable(logBookNum);
////        deleteAddWaterReasonPreSeasonTable(logBookNum);
////        deleteAddBoreWellPumpSeasonTable(logBookNum);
////        deleteAddWaterSeasonFeildTable(logBookNum);
//        deleteAddHarvestDetailsTable(logBookNum);
//        deleteLandPreparationDetailsTable(logBookNum);
//    }

    @Transaction
    public void deleteGetDataFillSyncTables(String syncValue) {
//        // Anything inside this method runs in a single transaction.

        deleteFarmerTable();
        deletePlotTable();
        deletePlotD20Table("1");
        deleteGeoBoundriesTable(true);
        deleteGeoBoundriesTrackingTable(true);
        deleteAddPlotOfferTable("1");
        deleteUserSectionTable();
        deleteD20FertilizerTable("1");
        deleteD20WeedTable("false");
        deleteD20DiseaseTable(false);
        deleteD20PestTable(false);
        deleteSyncAddComplainTable(syncValue);
        deleteSyncAddComplainImagesTable(syncValue);
        deleteSyncGrowthMonitoringTable("1");
//        deleteSyncFarmerDetailListTable(syncValue);
//        deleteSyncSavingFarmerProfieImagesTable(syncValue);
//        deleteSyncDocIdentiFicationDeatilsTable(syncValue);
//        deleteSyncBankDetailsSubmitTable(syncValue);
//        deleteSyncSavingBankImagesTable(syncValue);
//        deleteSyncPlotDetailsListTable(syncValue);
//        deleteSyncSavingPlotProfieImagesTable(syncValue);
//        deleteSyncSavingGeoBoundariesTable(syncValue);
//
//
//        deleteSyncAddLogBookDetailsTable(syncValue);
//        deleteSyncAddFertilizerDetailsTable(syncValue);
////        deleteSyncAddOrganicDetailsTable(syncValue);
////        deleteSyncAddWaterRegimeSeasonDetailsTable(syncValue);
////        deleteSyncAddWaterReasonPreSeasonTable(syncValue);
////        deleteSyncAddBoreWellPumpSeasonTable(syncValue);
////        deleteSyncAddWaterSeasonFeildTable(syncValue);
//        deleteSyncAddHarvestDetailsTable(syncValue);
//        deleteSyncLandPreparationDetailsTable(syncValue);

    }

    @Transaction
    public void deleteGetDataFillSyncTables(String syncValue,String Season) {
//        // Anything inside this method runs in a single transaction.

        deleteFarmerTable();
        deletePlotTable();
        deletePlotD20Table("1");
        deleteGeoBoundriesTable(true);
        deleteGeoBoundriesTrackingTable(true);
        deleteAddPlotOfferTable("1");
        deleteUserSectionTable();
        deleteD20FertilizerTable("1");
        deleteD20WeedTable("false");
        deleteD20DiseaseTable(false);
        deleteD20PestTable(false);
        deleteSyncAddComplainTable(syncValue);
        deleteSyncAddComplainImagesTable(syncValue);
        deleteSyncGrowthMonitoringTable("1");
//        deleteSyncFarmerDetailListTable(syncValue);
//        deleteSyncSavingFarmerProfieImagesTable(syncValue);
//        deleteSyncDocIdentiFicationDeatilsTable(syncValue);
//        deleteSyncBankDetailsSubmitTable(syncValue);
//        deleteSyncSavingBankImagesTable(syncValue);
//        deleteSyncPlotDetailsListTable(syncValue);
//        deleteSyncSavingPlotProfieImagesTable(syncValue);
//        deleteSyncSavingGeoBoundariesTable(syncValue);
//
//
//        deleteSyncAddLogBookDetailsTable(syncValue);
//        deleteSyncAddFertilizerDetailsTable(syncValue);
////        deleteSyncAddOrganicDetailsTable(syncValue);
////        deleteSyncAddWaterRegimeSeasonDetailsTable(syncValue);
////        deleteSyncAddWaterReasonPreSeasonTable(syncValue);
////        deleteSyncAddBoreWellPumpSeasonTable(syncValue);
////        deleteSyncAddWaterSeasonFeildTable(syncValue);
//        deleteSyncAddHarvestDetailsTable(syncValue);
//        deleteSyncLandPreparationDetailsTable(syncValue);

    }


    @Transaction
    public void deleteAllTables() {
        // Anything inside this method runs in a single transaction.
        deleteDivisionTable();
        deleteSectionTable();
        deleteCircleTable();
        deleteVillageTable();
        deleteCropTable();
        deleteBankTable();
        deleteBranchTable();
        deleteDiseaseTable();
        deleteDistrictTable();
        deleteFertilizerTable();
        deleteMandalTable();
        deleteStateTable();
        deleteUsersTable();
        deleteParameterTable();
        deletePestTable();
        deletePlantTypeTable();
        deletePlantSubTypeTable();
        deleteSampleSlabTable();
        deleteSeasonTable();
        deleteUOMTable();
        deleteVarietyTable();
        deleteWarehouseTable();
        deleteWeedTable();
        deletePlotExistOnTable();
        deleteCastTable();
        deleteLookupHDRTable();
        deleteLookupDtlTable();
        deleteReasonsTable();
//        deleteRefreshTableDateCheck();

    }

//    @Query("SELECT COUNT(*) FROM AddComplaintsDetailsTable")
//    public abstract LiveData<Integer> getAddComplaintsDetailsCount();

    // TODO: 3/2/2022 for db clear query for web team
//    select * from Farmer
//    select * from Land
//    select * from Documents
//    select * from FarmerBank
//    select * from GeoBoundaries
//    select * from LogBook
//    select * from Fertilizer
//    select * from OrganicAmendments
//    select * from WaterReasonPreSeason
//    select * from WaterRegimeonSeason
//    select * from WaterRegimeOnthefield
//    select * from BorewellPumpOperations
//    select * from HarvestDetails
}
