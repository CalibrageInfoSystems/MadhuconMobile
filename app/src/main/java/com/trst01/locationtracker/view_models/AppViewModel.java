package com.trst01.locationtracker.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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
import com.trst01.locationtracker.models.LoginResponseDTO;
import com.trst01.locationtracker.repositories.AppRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AppViewModel extends ViewModel {
    private AppRepository appRepository;
//    private LiveData<List<LoginResponseDTO>> loginResponseDTOFromServerLiveData;
    private LiveData<String> stringLiveData;
    private LiveData<Integer> integerLiveData;

    @Inject
    public AppViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

//    private LiveData<List<String>> stringListLiveData;
//    private LiveData<String> successStr;
//    private LiveData<FarmerTable> farmerDetailListTableLiveData;
//
//    private LiveData<FarmerTable> farmerDetailListTableLiveDataInsert;
//    private LiveData<DocIdentiFicationDeatilsTable> documentSavingDataLocalDB;
//    private LiveData<BankDetailsSubmitTable> bankDetailsSubmitTableLiveData;
//    private LiveData<DocIdentiFicationDeatilsTable> docIdentiFicationDeatilsTableLiveData;
//    private LiveData<CurrentVisitFarmerTables> currentVisitFarmerTablesLiveData;
//    private LiveData<PlotDetailsListTable> plotDetailListTableLiveData;
//    private LiveData<List<PinCodeDetailsDataTable>> bypincodestatevillagelistitemLiveData;
//
//    private LiveData<List<PinCodeDetailsResponseDTO>> pincodeDetailsResponseFromServerLiveData;
//    private LiveData<List<CropDetailsResponseFromServerDTO>> cropDetailsResponseFromServerLiveData;
//
//    private LiveData<List<PlotNumberDataResponseDTO>> syncDataResponseDTOLiveData;
//
    private LiveData<List<AddGeoBoundriesTable>> plotGeoBoundsFromLocalDBLiveDataNotSync;
    private LiveData<List<LookupHDRTable>> lookupHdrListFromLocalDBLiveData;
    private LiveData<List<LookupDtlTable>> lookupDtlListFromLocalDBLiveData;
    private LiveData<List<VarietyTable>> varietyListFromLocalDBLiveData;
    private LiveData<List<PlantTypeTable>> plantTypeListFromLocalDBLiveData;
    private LiveData<List<PlantSubTypeTable>> plantSubTypeListFromLocalDBLiveData;
    private LiveData<List<CropTable>> cropListFromLocalDBLiveData;
    private LiveData<List<ResonForNotPlantingTable>> reasonListFromLocalDBLiveData;
    private LiveData<List<VillageTable>> villageListFromLocalDBLiveData;
    private LiveData<List<AddFarmerTable>> farmerListFromLocalDBLiveData;
    private LiveData<List<AddPlotTable>> landDetailsLocalDbLiveData;
    private LiveData<List<AddD20Table>> d20DetailsLocalDbLiveData;
    private LiveData<List<AddD10Table>> d10DetailsLocalDbLiveData;
    private LiveData<List<D20DiseaseTable>> d20DiseasesListDbLiveData;
    private LiveData<List<D20WeedTable>> d20WeedListDbLiveData;
    private LiveData<List<D20FertilizerTable>> d20FertilizersListDbLiveData;
    private LiveData<List<D20PestTable>> d20pestListDbLiveData;
    private LiveData<List<LookUpDropDownDataTable>> getLookUpDataListFromLocalDBLiveData;
    private LiveData<LookUpDropDownDataTable> getLookUpSelectionNameLookUpId;
//    private LiveData<List<FarmerTable>> farmerListFromLocalDBLiveDataNotSync;
//    private LiveData<List<PlotDetailsListTable>> landdetailsListFromLocalDBLiveData;
//
//    private LiveData<List<VillageDetailsResponseDTO>> villageDetailsResponseFromServerLiveData;
//
//
//    private LiveData<List<PlotDetailsListTable>> landDetailsLocalDbDataLiveData;
//    private LiveData<List<DocIdentiFicationDeatilsTable>> docIdentificationDetailsSubmitTableLiveData;
//    private LiveData<List<BankDetailsSubmitTable>> bankDetailsSubmitTableFromLocalDbLiveData;
//
//    private LiveData<List<BankDetailsSubmitTable>> bankDetailsFromLocalDBByfarmerCode;
//    private LiveData<List<DocIdentiFicationDeatilsTable>> docIdentificationDetailsByFarmerCode;
//
//    private LiveData<List<SavingComplainImagesTable>> savingComplainImagesTableLiveData;
//    // TODO: Adding Response live data for cluster process
//    private LiveData<List<StateListResponseDTO>> stateDetailsResponseFromServerLiveData;
//    private LiveData<List<DistricDetailsResponseDTO>> districDetailsResponseFromServerLiveData;
//    private LiveData<List<VillageByMandalIdDetailsResponseDTO>> villageDetailsByMandalResponseFromServerLiveData;
//    private LiveData<List<MandalDetailsResponseDTO>> mandalDetailsResponseFromServerLiveData;
//    private LiveData<List<ClusterDetailsResponseDTO>> clusterDetailsResponseFromServerLiveData;
//    private LiveData<List<SyncResponseDTO>> syncResponseDTOFromServerLiveData;
//    private LiveData<GeoBoundariesTable> geoBoundariesTableLocalDbInsertLiveData;
//    private LiveData<List<GeoBoundariesTable>> geoBoundariesTableFromLocalDbLiveData;
//
//
//    private LiveData<AddLandPreparationTable> getLandPreparationFeildDetailsTableLiveDataByDate;
//    private LiveData<AddGeoBoundriesTable> getGeoBoundriesFeildDetailsTableLiveDataByDate;
//    private LiveData<AddFertilizerDetailsTable> getFertilizerFeildDetailsTableLiveDataByDate;
//    private LiveData<AddTransplantingTable> getTransplantingFeildDetailsTableLiveDataByDate;
//    private LiveData<AddWaterManagementTable> getWaterManagementFeildDetailsTableLiveDataByDate;
//    private LiveData<AddWeedManagementTable> getWeedManagementFeildDetailsTableLiveDataByDate;
//    private LiveData<AddYieldTable> getYieldFeildDetailsTableLiveDataByDate;
//    private LiveData<AddMoistureContentTable> getMoistureContentFeildDetailsTableLiveDataByDate;
//    private LiveData<AddNurseryPreparationTable> getNurseryPreparationFeildDetailsTableLiveDataByDate;
//    private LiveData<AddSeedRateTable> getSeedRateFeildDetailsTableLiveDataByDate;
//    private LiveData<AddHarvestingTable> getHarvestingFeildDetailsTableLiveDataByDate;
//    private LiveData<AddPestDiseaseControlTable> getPestDiseasecontrolFeildDetailsTableLiveDataByDate;
//
//
//    private LiveData<List<GeoBoundariesTable>>  listentryValuesGeoBoundaries;
//    // TODO: 1/21/2022 Local Save
//
//
//    // TODO: Adding Response live data for cluster process
//    private LiveData<List<StatesTable>> stateListSavedIntoLocalDBFromServer;
//    private LiveData<List<DistrictTable>> districListSavedIntoLocalDBFromServer;
//    private LiveData<List<MandalTable>> mandalListSavedIntoLocalDBFromServer;
//    private LiveData<List<CropListTable>> cropListSavedIntoLocalDBFromServer;
//    private LiveData<List<VillageTable>> villageListSavedIntoLocalDBFromServer;
//    private LiveData<List<ClusterHDRTable>> clusterHDRlistSavedIntoLocalDBFromServer;
//
//    private LiveData<List<ClusterDTLTable>> clusterdtlListSavedIntoLocalDBFromServer;
//    private LiveData<List<PinCodeDetailsListTable>> pinCodeDetailsListSavedIntoLocalDBFromServer;
//    private LiveData<List<VillageDetailsByPinCodListTable>> pincodevillageDetailsListSavedIntoLocalDBFromServer;
//
//
//    // TODO: Adding server details into local db
//
//    private LiveData<StatesTable> insertStatesListDataIntoLocalDBQueryLiveData;
//    //  private LiveData<DistrictTable> insertDistrictListDataIntoLocalDBQueryLiveData;
//    private LiveData<DistrictTable> insertDistrictListDataIntoLocalLiveData;
//    private LiveData<MandalTable> insertMandalListDataIntoLocalDBQueryLiveData;
//    private LiveData<CropListTable> insertCropListDataIntoLocalDBQueryLiveData;
//    private LiveData<CropVarietyListTable> insertCropVarietyListDataIntoLocalDBQueryLiveData;
//    private LiveData<VillageTable> insertVillageListDataIntoLocalDBQueryLiveData;
    //    private LiveData<ClusterDTLTable> insertClusterDTLListDataIntoLocalDBQueryLiveData;
//
//    // TODO: getting values from local DB
//    private LiveData<List<StatesTable>> getStateListFromLocalDBLiveData;
//    private LiveData<List<DistrictTable>> getDistricListFromLocalDBLiveData;
//    private LiveData<List<MandalTable>> getMandalListFromLocalDBLiveData;
//    private LiveData<List<CropListTable>> getCropListFromLocalDBLiveData;
//    private LiveData<List<CropVarietyListTable>> getCropVarietyListFromLocalDBLiveData;
//    private LiveData<List<VillageTable>> getVillageListFromLocalDBLiveData;
//    private LiveData<List<ClusterHDRTable>> getClusterListFromLocalDBLiveData;
//    private LiveData<ClusterDTLTable> getClusterDTlFromLocalDBLiveData;
//
//
//    private LiveData<List<GetMasterPersonalLandAllDetailsRequestDTO>> getMasterSyncDataFromServerLiveData;
//
//
//    // TODO: 1/26/2022 For handling auto generated pincode and address details
//    private LiveData<List<VillageTable>> villageDetailsByPincode;
//
    private LiveData<List<SeasonTable>> getSeasonlistFromlocalDBLiveData;
    private LiveData<LoginResponseDTO> loginResponseDTOFromServerLiveData;
    private LiveData<VillageTable> getVillageDetailsFromLocalDBByVillageID;
    private LiveData<AddFarmerTable> getFarmerDetailsFromLocalDBByFarmerCode;
    private LiveData<AddGeoBoundriesTable> PlotGeoBoundsTableLiveDataInsert;
    private LiveData<CastTable> getCastDetailsFromLocalDBByCastID;
    private LiveData<PlantTypeTable> getPlantTypeDetailsFromLocalDBByPlantTypeID;
    private LiveData<LookupDtlTable> getLookupDtlDetailsFromLocalDBByID;
    private LiveData<PlotExistOnTable> getPlotExistOnDetailsFromLocalDBByID;
    private LiveData<List<PlotExistOnTable>> getPlotExistOnDetailsListFromLocalDBByID;
    private LiveData<VarietyTable> getVarietyFromLocalDBByID;
    private LiveData<MandalTable> getMandalDetailsFromLocalDBByVillageID;
    private LiveData<SectionTable> getSectionDetailsFromLocalDBBySectionID;
    private LiveData<CircleTable> getCircleDetailsFromLocalDBByCircleID;
    private LiveData<DivisionTable> getDivisionDetailsFromLocalDBByCircleID;
    private LiveData<DistrictTable> getDistrictDetailsFromLocalDBByCircleID;
    private LiveData<BankTable> getBankDetailsFromLocalDBByCircleID;
    private LiveData<BranchTable> getBranchDetailsFromLocalDBByID;
//    private LiveData<MandalTable> getDeatailsFromMandalTableLocalDbLiveData;
//    private LiveData<DistrictTable> getDistrictDetailsFromLocalDB;
//    private LiveData<StatesTable> getStateDetailsFromLocalDB;
    private LiveData<CropTable> getCropDetailsFromLocalDBByCropID;
//    private LiveData<CropVarietyListTable> getCropVarietyDetailsFromLocalDBByCropID;
//
//    private LiveData<PlotDetailsListTable> singleplotDetailsFromLocalDBLiveData;
//
//
//    private LiveData<SavingFarmerProfieImagesTable> savingFarmerProfieImagesTableLiveData;
//    private LiveData<List<SavingFarmerProfieImagesTable>> savingFarmerProfileImagesNotSyncLiveData;
//
//
//
//    private LiveData<SavingPlotProfieImagesTable> savingPlotImagesTableLiveData;
//    private LiveData<List<SavingPlotProfieImagesTable>> savingPlotProfileImagesNotSyncLiveData;
//
//
//
//    private LiveData<SavingBankImagesTable> savingBankImagesTableLiveData;
//    private LiveData<List<SavingBankImagesTable>> savingBankProfileImagesNotSyncLiveData;
//
//    private LiveData<GeoBoundariesTable> geoBoundariesTableLiveDataInsert;
//    private LiveData<List<GeoBoundariesTable>> savingGeoBoundariesTableListData;
//    // TODO: 2/15/2022 log book data
//

    private LiveData<DivisionTable> insertDivisionListIntoLocalDBQueryLiveData;
    private LiveData<List<DivisionTable>> insertDivisionListQueryLiveData;
    private LiveData<SectionTable> insertSectionListIntoLocalDBQueryLiveData;
    private LiveData<List<SectionTable>> insertSectionListQueryLiveData;
    private LiveData<CircleTable> insertCircleListIntoLocalDBQueryLiveData;
    private LiveData<List<CircleTable>> insertCircleListQueryLiveData;
    private LiveData<ResonForNotPlantingTable> insertReasonListIntoLocalDBQueryLiveData;
    private LiveData<VillageTable> insertVillageListIntoLocalDBQueryLiveData;
    private LiveData<CropTable> insertCropListIntoLocalDBQueryLiveData;
    private LiveData<BankTable> insertBankListIntoLocalDBQueryLiveData;
    private LiveData<List<BankTable>> insertBankListQueryLiveData;
    private LiveData<BranchTable> insertBranchListIntoLocalDBQueryLiveData;
    private LiveData<DiseaseTable> insertDiseaseListIntoLocalDBQueryLiveData;
    private LiveData<List<DiseaseTable>> DiseaseListLiveData;
    private LiveData<List<FertilizerTable>> fertilizerListLiveData;
    private LiveData<List<WeedTable>> weedListLiveData;
    private LiveData<List<PestTable>> pestsListLiveData;
    private LiveData<DistrictTable> insertDistrictListIntoLocalDBQueryLiveData;
    private LiveData<FertilizerTable> insertFertilizerListIntoLocalDBQueryLiveData;
    private LiveData<MandalTable> insertMandalListIntoLocalDBQueryLiveData;
    private LiveData<StateTable> insertStateListIntoLocalDBQueryLiveData;
    private LiveData<UsersTable> insertUsersListIntoLocalDBQueryLiveData;
    private LiveData<ParameterTable> insertParameterListIntoLocalDBQueryLiveData;
    private LiveData<PestTable> insertPestListIntoLocalDBQueryLiveData;
    private LiveData<PlantTypeTable> insertPlantTypeListIntoLocalDBQueryLiveData;
    private LiveData<PlantSubTypeTable> insertPlantSubTypeListIntoLocalDBQueryLiveData;
    private LiveData<SampleSlabTable> insertSampleSlabListIntoLocalDBQueryLiveData;
    private LiveData<SeasonTable> insertSeasonListIntoLocalDBQueryLiveData;
    private LiveData<UOMTable> insertUOMListIntoLocalDBQueryLiveData;
    private LiveData<VarietyTable> insertVarietyListIntoLocalDBQueryLiveData;
    private LiveData<WarehouseTable> insertWarehouseListIntoLocalDBQueryLiveData;
    private LiveData<WeedTable> insertWeedListIntoLocalDBQueryLiveData;
    private LiveData<CastTable> insertCastListIntoLocalDBQueryLiveData;
    private LiveData<LookupDtlTable> insertLookupDtlTableListIntoLocalDBQueryLiveData;
    private LiveData<LookupHDRTable> insertLookupHDRTableListIntoLocalDBQueryLiveData;
    private LiveData<PlotExistOnTable> insertPlotExistOnTableListIntoLocalDBQueryLiveData;

    private LiveData<AddFarmerTable> insertFarmerListIntoLocalDBQueryLiveData;
    private LiveData<AddPlotTable> insertPlotListIntoLocalDBQueryLiveData;
    private LiveData<AddD20Table> insertD20ListIntoLocalDBQueryLiveData;
    private LiveData<AddD30Table> insertD30ListIntoLocalDBQueryLiveData;
//    private LiveData<AddD30Table> insertD30ListIntoLocalDBQueryLiveData;
    private LiveData<AddD10Table> insertD10ListIntoLocalDBQueryLiveData;
    private LiveData<AddGeoBoundariesTrackingTable> insertGeoBoundariesTrackingListIntoLocalDBQueryLiveData;
    private LiveData<AddGeoBoundriesTable> insertGeoBoundariesListIntoLocalDBQueryLiveData;
    private LiveData<UserSectionTable> insertUserSectionTableIntoLocalDBQueryLiveData;
    private LiveData<D20FertilizerTable> insertD20FertilizerIntoLocalDBQueryLiveData;
    private LiveData<D20WeedTable> insertD20WeedIntoLocalDBQueryLiveData;
    private LiveData<D20DiseaseTable> insertD20DiseaseIntoLocalDBQueryLiveData;
    private LiveData<D20PestTable> insertD20PestIntoLocalDBQueryLiveData;
    private LiveData<AddPlotOfferTable> insertPlotOfferListIntoLocalDBQueryLiveData;
    private LiveData<AddPlantationTable> insertPlantationListIntoLocalDBQueryLiveData;
    private LiveData<AddGrowthMonitoringTable> insertGrowthMonitoringDBQueryLiveData;
    private LiveData<List<AddGrowthMonitoringTable>> insertGrowthMonitoringListDBQueryLiveData;

//
//    public void logInServiceList(String userId) {
//        try {
//            loginResponseDTOFromServerLiveData = appRepository.getlogInServiceResponse(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void logInServiceList(String userId,String pass,String Imei) {
        try {
            loginResponseDTOFromServerLiveData = appRepository.getlogInServiceResponse(userId,pass,Imei);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<LoginResponseDTO> getloginResponseDTOFromServerLiveData() {
        return loginResponseDTOFromServerLiveData;
    }


    //    private LiveData<LogBookDropDownHDRTable> insertLogBookHDRIntoLocalDBQueryLiveData;
//    private LiveData<LookUpDropDownDataTable> insertLookUpDropDownDataIntoLocalDBQueryLiveData;
//    private LiveData<List<SeasonTable>> getSeasonlistFromlocalDBLiveData;
//    private LiveData<List<LookUpDropDownDataTable>> getLookUpDataListFromLocalDBLiveData;
//
//    // TODO: 2/15/2022 db saving part for log book
//    private LiveData<AddLogBookDetailsTable> addLogBookDetailsTableLiveData;
//    private LiveData<List<AddLogBookDetailsTable>> logbookListLocalDbDataLiveData;
//    private LiveData<AddLogBookDetailsTable> upDateaddLogBookDetailsTableLiveData;
//    private LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableLiveDataByDate;
//
//    private LiveData<AddFertilizerDetailsTable> addFertilizerDetailsTableLiveData;
//    private LiveData<List<AddFertilizerDetailsTable>> fertilizerlistFromLocalDBLiveData;
//    private LiveData<AddFertilizerDetailsTable> getFertilizerDetailsTableLiveDataByDate;
//
//    private LiveData<LogBookModulesStatusDetailsTable> getLogBookModulesStatusDetailsFromLocalDBByLogBookID;
//    private LiveData<LogBookModulesStatusDetailsTable> savingLogBookModulesStatusDetailsTable;
//
//
//
////    private LiveData<AddOrganicDetailsTable> addOrganicDetailsTableLiveData;
////    private LiveData<List<AddOrganicDetailsTable>> orgaincAmendsDatalistFromLocalDBLiveData;
//
//
////    private LiveData<AddWaterSeasonFeildTable> addWaterSeasonFeildTableLiveData;
////    private LiveData<AddWaterSeasonFeildTable> getWaterSeasonFieldDetailsFromLocalDBLiveData;
//
//
//    private LiveData<AddLandPreparationTable> addLandPreparationTableLiveData;
//    private LiveData<List<AddLandPreparationTable>> getLandPreparationDetailsFromLocalDBLiveData;
//
//    private LiveData<AddPestDiseaseControlTable> addPestDiseaseTableLiveData;
//    private LiveData<List<AddPestDiseaseControlTable>> getPestDiseasecontrolDetailsFromLocalDBLiveData;
//
//    private LiveData<AddHarvestingTable> addharvestingTableLiveData;
//    private LiveData<List<AddHarvestingTable>> getharvestingDetailsFromLocalDBLiveData;
//
//    private LiveData<AddSeedRateTable> addSeedRateTableLiveData;
//    private LiveData<List<AddSeedRateTable>> getSeedrateDetailsFromLocalDBLiveData;
//
//    private LiveData<AddNurseryPreparationTable> addNurseryPreparationTableLiveData;
//    private LiveData<List<AddNurseryPreparationTable>> getNurseryPreparationDetailsFromLocalDBLiveData;
//
//    private LiveData<AddMoistureContentTable> addMoistureContentTableLiveData;
//    private LiveData<List<AddMoistureContentTable>> getMoistureContentDetailsFromLocalDBLiveData;
//
//    private LiveData<AddYieldTable> addYieldTableLiveData;
//    private LiveData<List<AddYieldTable>> getYieldDetailsFromLocalDBLiveData;
//
//    private LiveData<AddWeedManagementTable> addWeedManagementTableLiveData;
//    private LiveData<List<AddWeedManagementTable>> getWeedmanagementDetailsFromLocalDBLiveData;
//
//    private LiveData<AddWaterManagementTable> addWaterManagementTableLiveData;
//    private LiveData<List<AddWaterManagementTable>> getWatermanagementDetailsFromLocalDBLiveData;
//
//    private LiveData<AddTransplantingTable> addTransplantingTableLiveData;
//    private LiveData<List<AddTransplantingTable>> getTransplantingDetailsFromLocalDBLiveData;
//
//    private LiveData<AddFertilizerDetailsTable> addFertilizationTableLiveData;
//    private LiveData<List<AddFertilizerDetailsTable>> getFertilizerDetailsFromLocalDBLiveData;
//
//
//    private LiveData<AddGeoBoundriesTable> addGeoBoundriesTableLiveData;
//    private LiveData<List<AddGeoBoundriesTable>> getGeoBoundriesDetailsFromLocalDBLiveData;
//
//    private LiveData<AddHarvestDetailsTable> addHarvestDetailsTableLiveData;
//    private LiveData<AddHarvestDetailsTable> getAddHarvestDetailsFromLocalDBLiveData;
//
//
////    private LiveData<AddBoreWellSeasonTable> addBoreWellPumpSeasonTableLiveData;
////    private LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData;
//
//
////    private LiveData<AddWaterReasonPreSeasonTable> addWaterReasonPreSeasonTableLiveData;
////    private LiveData<AddWaterReasonPreSeasonTable> getWaterReasonPreSeasonDetailsFromLocalDBLiveData;
//
//
////    private LiveData<AddWaterRegimeSeasonDetailsTable> addWaterRegimeSeasonDetailsTableLiveData;
////    private LiveData<AddWaterRegimeSeasonDetailsTable> getWaterRegimeSeasonDetailsFromLocalDBLiveData;
//
//
//
//    private LiveData<List<AddLogBookDetailsTable>> logBookListFromLocalDBLiveDataNotSync;
//
//
    private LiveData<List<AddD10Table>> d10ListFromLocalDBLiveDataNotSync;
    private LiveData<List<AddD20Table>> d20ListFromLocalDBLiveDataNotSync;
    private LiveData<List<AddD30Table>> d30ListFromLocalDBLiveDataNotSync;
    private LiveData<List<AddPlotOfferTable>> ferlizerListFromLocalDBLiveDataNotSync;
    private LiveData<List<AddPlantationTable>> plantationListFromLocalDBLiveDataNotSync;

    private LiveData<List<AddGeoBoundariesTrackingTable>> trackingListFromLocalDBLiveDataNotSync;
//
////    private LiveData<List<AddOrganicDetailsTable>> organicListFromLocalDBLiveDataNotSync;
//
////    private LiveData<List<AddWaterRegimeSeasonDetailsTable>> waterRegimeSeasonListFromLocalDBLiveDataNotSync;
//
////    private LiveData<List<AddWaterReasonPreSeasonTable>> waterpreSeasonFromLocalDBLiveDataNotSync;
////
////    private LiveData<List<AddBoreWellSeasonTable>> boreWellPumpListFromLocalDBLiveDataNotSync;
////
////
////    private LiveData<List<AddWaterSeasonFeildTable>> waterSeasonFieldListFromLocalDBLiveDataNotSync;
//
//    private LiveData<List<AddHarvestDetailsTable>> harvestListFromLocalDBLiveDataNotSync;
//
//    private LiveData<LogBookDropDownHDRTable> getLogBookDropDownHDRTableDetailsFromLocalDBLiveData;
//
//
//    private LiveData<LookUpDropDownDataTable> getLookUpSelectionNameLookUpId;
//    private LiveData<SeasonTable> getSeasonNameBySeasonID;
//
//
//    private LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDetailsFromLocalDBByFarmerCode;
//
//    private LiveData<RefreshTableDateCheck> addRefreshTableDateCheckLiveData;
//    private LiveData<RefreshTableDateCheck> getAddRefreshTableDateCheck;
//
//
//    @Inject
//    public AppViewModel(AppRepository appRepository) {
//        this.appRepository = appRepository;
//    }
//
//
//    public void logInServiceList(String userId) {
//        try {
//            loginResponseDTOFromServerLiveData = appRepository.getlogInServiceResponse(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getPincodeResponseDeatilsFromServer(String userId) {
//        try {
//            pincodeDetailsResponseFromServerLiveData = appRepository.getPincodeDetailsFromServer(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: state details from server
//    public void getStateDetailsFromServer() {
//        try {
//            stateDetailsResponseFromServerLiveData = appRepository.getStateDetailsResponseDTOServer();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: distric details from server
//    public void getDistricDetailsFromServer(String stateID) {
//        try {
//            districDetailsResponseFromServerLiveData = appRepository.getDistricDetailsResponseDTOServer(stateID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: distric details from server
//    public void getMandalDetailsFromServer(String districID) {
//        try {
//            mandalDetailsResponseFromServerLiveData = appRepository.getMandalDetailsResponseDTOServer(districID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: distric details from server
//    public void getVillageIdFromServerByMandalId(String mandalID) {
//        try {
//            villageDetailsByMandalResponseFromServerLiveData = appRepository.getVillageDeatilsByMandalIdFromServer(mandalID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: distric details from server
//    public void getClusertDetailsFromserver(String mandalID) {
//        try {
//            clusterDetailsResponseFromServerLiveData = appRepository.getCluserDetailsFromServer(mandalID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//
//    public void getvillageDetailsResponseFromServer(String userId) {
//        try {
//            villageDetailsResponseFromServerLiveData = appRepository.getVillageDetailsFromServer(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertOrUpdateFarmerDetailListTable(FarmerTable farmerTable) {
//        try {
//            farmerDetailListTableLiveData = appRepository.insertOrUpdateFarmerDetailListTableTable(farmerTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
////    public void insertFarmerDetailListTable(FarmerDetailListTable farmerDetailListTable) {
////        try {
////            farmerDetailListTableLiveDataInsert = appRepository.insertOrUpdateFarmerDetailListTableTable(farmerDetailListTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public void insertFarmerDetailListTableLocal(FarmerTable farmerTable) {
//        try {
//            farmerDetailListTableLiveDataInsert = appRepository.insertFarmerDetailListTableTable(farmerTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertDoctable(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        try {
//            documentSavingDataLocalDB = appRepository.insertDocIntoLocalDB(docIdentiFicationDeatilsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertOrUpdateBankDetailsSubmitTableLocalDb(BankDetailsSubmitTable bankDetailsSubmitTable) {
//        try {
//            bankDetailsSubmitTableLiveData = appRepository.insertOrUpdateBankDetailsSubmitTable(bankDetailsSubmitTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertFarmerProfileImages(SavingFarmerProfieImagesTable savingFarmerProfieImagesTable) {
//        try {
//            savingFarmerProfieImagesTableLiveData = appRepository.insertSavingOfFarmerMultipleImages(savingFarmerProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertPlotPictureImagesToServer(SavingPlotProfieImagesTable savingPlotProfieImagesTable) {
//        try {
//            savingPlotImagesTableLiveData = appRepository.insertSavingOfPlotMultipleImages(savingPlotProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertBankPictureImagesToServer(SavingBankImagesTable savingBankImagesTable) {
//        try {
//            savingBankImagesTableLiveData = appRepository.insertSavingOfBankMultipleImages(savingBankImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertGeoBoundariesvaluesIntolocalDB(GeoBoundariesTable geoBoundariesTable) {
//        try {
//            geoBoundariesTableLiveDataInsert = appRepository.insertGoeDataIntolocaDB(geoBoundariesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertGeoBoundariesListvaluesIntolocalDB(List<GeoBoundariesTable> geoBoundariesTableList) {
//        try {
//            savingGeoBoundariesTableListData = appRepository.insertGoeBoundariesListDataIntolocaDB(geoBoundariesTableList);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void insertLogBookModulesStatusDetailsTable(LogBookModulesStatusDetailsTable logBookModulesStatusDetailsTable) {
//        try {
//            savingLogBookModulesStatusDetailsTable = appRepository.insertSavingOfLogBookModulesStatusDetailsTable(logBookModulesStatusDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/7/2022 saving documnets files local db
//    public void insertOrUpdateDocUploadTable(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        try {
//            docIdentiFicationDeatilsTableLiveData = appRepository.insertOrUpdateDocIdentificationDetailListTable(docIdentiFicationDeatilsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertOrUpdateCurrentVisitFarmerTables(CurrentVisitFarmerTables currentVisitFarmerTables) {
//        try {
//            currentVisitFarmerTablesLiveData = appRepository.insertOrUpdateCurrentVisitFarmerTablesTable(currentVisitFarmerTables);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: sync to server methods
//    public void syncFormerDetailsDataToServer(FarmerTable farmerTable) {
//        try {
//            stringLiveData = appRepository.syncFormerDetailsDataToServer(farmerTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    // TODO: sync to server methods
//    public void syncFormerProfileDetailsToserver(SavingFarmerProfieImagesTable savingFarmerProfieImagesTable) {
//        try {
//            stringLiveData = appRepository.syncFarmerProfileImagesDetaisToServer(savingFarmerProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncPlotProfileDetailsToserver(SavingPlotProfieImagesTable savingPlotProfieImagesTable) {
//        try {
//            stringLiveData = appRepository.syncPlotProfileImagesDetaisToServer(savingPlotProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void syncBankProfileDetailsToserver(SavingBankImagesTable savingBankImagesTable) {
//        try {
//            stringLiveData = appRepository.syncBankProfileImagesDetaisToServer(savingBankImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//    public void syncLandDetailsDataToServer(PlotDetailsListTable plotDetailsListTable) {
//        try {
//            stringLiveData = appRepository.syncLandDetailsDataToServer(plotDetailsListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentifcationDetailsDataToServer(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        try {
//            stringLiveData = appRepository.syncDocIdentificationDetailsDataToServer(docIdentiFicationDeatilsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncBankDetailsSubmitTableDataToServer(BankDetailsSubmitTable bankDetailsSubmitTable) {
//        try {
//            stringLiveData = appRepository.syncBankDetailsDataToServer(bankDetailsSubmitTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentificationDetailsDataToServer(String codeUser, String strPicUrl, String strFileExtension, String typeOfReq, String identityCode,String createdDate,String upDatedDate) {
//        try {
//            stringLiveData = appRepository.syncDocIdentificationDetailsToServer(codeUser, strPicUrl, strFileExtension, typeOfReq, identityCode,createdDate,upDatedDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncGeoBoundariesDetailsSubmitTableDataToServer(GeoBoundariesTable geoBoundariesTable) {
//        try {
//            stringLiveData = appRepository.syncGeoBoundariesDetailsDataToServer(geoBoundariesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 1/7/2022 saving plot details into local db
//    public void insertOrUpdatPlotDetailListTable(PlotDetailsListTable plotDetailsListTable) {
//        try {
//            plotDetailListTableLiveData = appRepository.insertOrUpdateinsertOrUpdatePlotDetails(plotDetailsListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/7/2022 saving plot details into local db
//    public void insertGeoBoundariesTable(GeoBoundariesTable geoBoundariesTable) {
//        try {
//            geoBoundariesTableLocalDbInsertLiveData = appRepository.insertGeoBoundariesValuesIntoLocalDb(geoBoundariesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getFarmerListFromLocalDB() {
//        try {
//            farmerListFromLocalDBLiveDataNotSync = appRepository.getFarmerDetailslistFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//

    private LiveData<SavingComplainImagesTable> savingComplainImagesTableLiveData;
    private LiveData<List<SavingComplainImagesTable>> savingComplainImagesNotSyncLiveData;
    private LiveData<List<AddGrowthMonitoringTable>> gwNotSyncLiveData;

    private LiveData<AddComplaintsDetailsTable> addComplaintsDetailsTableLiveData;
    private LiveData<List<AddComplaintsDetailsTable>> getAddComplainFormDetailsFromLocalDBLiveData;



    private LiveData<SavingComplainImagesTable> addSavingComplainImagesTableLiveData;
    private LiveData<List<AddComplaintsDetailsTable>> complainsDataListFromLocalDBLiveDataNotSync;
    private LiveData<AddComplaintsDetailsTable> getAddComplainDetailsTableId;


    public void insertComplainImagesToServer(SavingComplainImagesTable savingComplainImagesTableList,String logbookno) {
        try {
            savingComplainImagesTableLiveData = appRepository.insertSavingOfComplainMultipleImages(savingComplainImagesTableList,logbookno);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncComplainImgeDetailsToserver(SavingComplainImagesTable savingComplainImagesTable) {
        try {
            stringLiveData = appRepository.syncComplainImagesDetaisToServer(savingComplainImagesTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getComplainImagesLocalDB() {
        try {
            savingComplainImagesNotSyncLiveData = appRepository.getComplainImagesFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getGmLocalDB() {
        try {
            gwNotSyncLiveData = appRepository.getGMFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getComplainImageslistsLocalDB() {
        try {
            savingComplainImagesNotSyncLiveData = appRepository.getComplainImagesFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<Integer> getNotSyncComplainsCountDataFromLocalDB() {
        return appRepository.getNotSyncComplainsCountDataFromLocalDB();
    }

    public LiveData<Integer> getNotSyncGMCountDataFromLocalDB() {
        return appRepository.getNotSyncGMCountDataFromLocalDB();
    }

    public LiveData<Integer> getPlotOfferCount() {
        return appRepository.getPlotOfferCount();
    }

    public void insertAddComplainFormTableLocalDb(AddComplaintsDetailsTable addComplaintsDetailsTable) {
        try {
            addComplaintsDetailsTableLiveData = appRepository.insertAddComplainFormTable(addComplaintsDetailsTable);
            //getAddComplainFormDetailsFromLocalDBLiveData = appRepository.getAddComplainFormTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<AddComplaintsDetailsTable> getAddComplainformTableLiveDataFromLocalDB() {
        return addComplaintsDetailsTableLiveData;
    }
    public LiveData<List<AddComplaintsDetailsTable>> getAddComplainformTableLiveDataFromLocalDBList() {
        return appRepository.getAddComplainFormTable();
    }

    public void insertAddComplainRepositoryTableLocalDb(SavingComplainImagesTable savingComplainImagesTableList,String logBookNo) {
        try {
            addSavingComplainImagesTableLiveData = appRepository.insertSavingOfComplainMultipleImages(savingComplainImagesTableList,logBookNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public LiveData<List<SavingComplainImagesTable>> getSavingComplainImagesNotSyncLiveData() {
        return savingComplainImagesNotSyncLiveData;
    }

    public LiveData<List<AddGrowthMonitoringTable>> getGMNotSyncLiveData() {
        return gwNotSyncLiveData;
    }

    public LiveData<List<SavingComplainImagesTable>> getSavingComplainImagesNotSyncLiveDataList() {
        return appRepository.getComplainImagesFromLocalDBNotSync();
    }

    public LiveData<List<SavingComplainImagesTable>> getSavingComplainImagesNotLiveDataList() {
        return appRepository.getComplainImagesFromLocalDB();
    }

    public void getComplainDataListFromLocalDBNotSync() {
        try {
            complainsDataListFromLocalDBLiveDataNotSync = appRepository.getComplainDataDetailsDetailslistFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //complain data
    public LiveData<List<AddComplaintsDetailsTable>> getAddComplainDataDetailsListNotSyncLiveData() {
        return complainsDataListFromLocalDBLiveDataNotSync;
    }

    public void syncComplainDataDetailsDataToServer(AddComplaintsDetailsTable addComplaintsDetailsTable) {
        try {
            stringLiveData = appRepository.syncAddComplainDataDetailsToServer(addComplaintsDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void syncGMDataDetailsToServer(AddGrowthMonitoringTable addComplaintsDetailsTable) {
        try {
            stringLiveData = appRepository.syncGMDataDetailsToServer(addComplaintsDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public LiveData<LookUpDropDownDataTable> getLookUpDropDownDetailsFromLocalDBByLookUPIDLiveData() {
        return getLookUpSelectionNameLookUpId;
    }


    public void getLookUpSelectionNameLookUpId(String selectionID) {
        try {
            getLookUpSelectionNameLookUpId = appRepository.getLookUpDataNameByID(selectionID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getLookUpDataFromLocalDBByType(String typeOfReq) {
        try {
            getLookUpDataListFromLocalDBLiveData = appRepository.getLookUpDropDownDataListFromLocalDB(typeOfReq);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<Integer> getCountComplaintDetails() {
        return appRepository.getCountComplaintDetailCount();
    }

    public LiveData<List<LookUpDropDownDataTable>> getLookUpDataListFromLocalDBLiveData() {
        return getLookUpDataListFromLocalDBLiveData;
    }

    public void getFarmerListFromLocalDBStatus() {
        try {
            farmerListFromLocalDBLiveData = appRepository.getFarmerDetailslistFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFarmerListFromLocalDBStatus(String seasonCode) {
        try {
            farmerListFromLocalDBLiveData = appRepository.getFarmerDetailslistFromLocalDB(seasonCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFarmerListFromLocalDBStatusSection() {
        try {
            farmerListFromLocalDBLiveData = appRepository.getFarmerDetailslistFromLocalDBSection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFarmerListFromLocalDBStatus(String season,int indicator) {
        try {
            farmerListFromLocalDBLiveData = appRepository.getFarmerDetailslistFromLocalDB(season,indicator);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getVillageListFromLocalDBStatus() {
        try {
            villageListFromLocalDBLiveData = appRepository.getVillageDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getGwList(String season,String farmerCode,String plot,String stage,String remarks) {
        try {
            gwNotSyncLiveData = appRepository.getGwList(season,farmerCode,plot,stage,remarks);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getVillageListBasedOnSectionFromLocalDBStatus() {
        try {
            villageListFromLocalDBLiveData = appRepository.getVillageListBasedOnSectionFromLocalDBStatus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getResonListFromLocalDBStatus() {
        try {
            reasonListFromLocalDBLiveData = appRepository.getResonDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getPlantTypeListFromLocalDBStatus() {
        try {
            plantTypeListFromLocalDBLiveData = appRepository.getPlantTypeDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getPlantSubTypeListFromLocalDBStatus() {
        try {
            plantSubTypeListFromLocalDBLiveData = appRepository.getPlantSubTypeDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getPlantSubTypeDetailsListFromLocalDBByPlantType(int plantTypeId) {
        try {
            plantSubTypeListFromLocalDBLiveData = appRepository.getPlantSubTypeDetailsListFromLocalDBByPlantType(plantTypeId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getCropListFromLocalDBStatus() {
        try {
            cropListFromLocalDBLiveData = appRepository.getCropDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void getVarietyListFromLocalDBStatus() {
        try {
            varietyListFromLocalDBLiveData = appRepository.getVarietyDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        public void getLogBookHdrListFromLocalDBStatus(String code) {
        try {
            lookupHdrListFromLocalDBLiveData = appRepository.getLookupHdrDetailsListFromLocalDB(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        public void getLookupDtlList(String code) {
        try {
            lookupDtlListFromLocalDBLiveData = appRepository.getLookupDtlList(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<Integer> getCountLand() {
        return appRepository.getCountDataLand();

    }

//    public LiveData<Integer> getPlotOfferCount() {
//        return appRepository.getPlotOfferCount();
//
//    }

    public LiveData<Integer> getGeoBoundariesCount() {
        return appRepository.getGeoBoundariesCount();
    }

    public LiveData<Integer> getD10Count() {
        return appRepository.getD10Count();
    }

    public LiveData<Integer> getD20Count() {
        return appRepository.getD20Count();
    }

    public LiveData<Integer> getD30Count() {
        return appRepository.getD30Count();
    }

    public LiveData<Integer> getD20Fertilizer() {
        return appRepository.getD20Fertilizer();
    }

    public LiveData<Integer> getD20Weed() {
        return appRepository.getD20Weed();
    }

    public LiveData<Integer> getD20Pest() {
        return appRepository.getD20Pest();
    }

    public LiveData<Integer> getD20Disease() {
        return appRepository.getD20Disease();
    }

    public LiveData<Integer> getGmCount() {
        return appRepository.getGmCount();
    }

    public void getDeleteGeoBoundariestablesFromlocalDB(String logBookNum)
    {
        try {
            appRepository.deleteGeoBoundariestablesFromlocalDB(logBookNum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotGeoBoundariesDetailsFromLocalDB(String PlotId) {
        try {
            plotGeoBoundsFromLocalDBLiveDataNotSync = appRepository.getPlotGeoBoundariesDetailsFromLocalDB(PlotId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotGeoBoundariesDetailsFromLocalDBNotSync(Boolean sync) {
        try {
            plotGeoBoundsFromLocalDBLiveDataNotSync = appRepository.getPlotGeoBoundariesDetailsFromLocalDBNotSync(sync);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public LiveData<List<AddGeoBoundriesTable>> getPlotGeoBoundsListNotSyncLiveData() {
        return plotGeoBoundsFromLocalDBLiveDataNotSync;
    }


//
//
//
//
////    public void getNumberCountFarmer()
////    {
////        try{
////
////        }catch (Exception e)
////        {
////            e.printStackTrace();
////        }
////    }
//
//    public void getCountNumberFromFarmer() {
//        try {
//            integerLiveData = appRepository.getFarmerDetailslistCountFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getFarmerListFromLocalDBNotSync() {
//        try {
//            farmerListFromLocalDBLiveDataNotSync = appRepository.getFarmerDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void getFarmerProfileImagesLocalDB() {
//        try {
//            savingFarmerProfileImagesNotSyncLiveData = appRepository.getProfileFarmerImagesFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getPlotProfileImagesLocalDB() {
//        try {
//            savingPlotProfileImagesNotSyncLiveData = appRepository.getProfilePlotImagesFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getBankProfileImagesLocalDB() {
//        try {
//            savingBankProfileImagesNotSyncLiveData = appRepository.getProfileBankImagesFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//
//
//
//
//    public void getLandDetailsListFromLocalDb() {
//        try {
//            landdetailsListFromLocalDBLiveData = appRepository.getLandDetailsFromLocalDbNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: get Doc Identification details
//
//    public void getLocalDocIdentificationFromLocalDB() {
//        try {
//            docIdentificationDetailsSubmitTableLiveData = appRepository.getDocIdentiFicationDeatilsTableFromLocalDb();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getBankDetailsFromLocalDb() {
//        try {
//            bankDetailsSubmitTableFromLocalDbLiveData = appRepository.getBankDetailsSubmitTableFromLocalDb();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getGeoBoudariesFromLocalDB() {
//        try {
//            geoBoundariesTableFromLocalDbLiveData = appRepository.getGeoBoundariesTableListFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 2/16/2022 logbook de
//    public void getLogBookListFromLocalDb(String strPlotCode) {
//        try {
//            logbookListLocalDbDataLiveData = appRepository.getLogBookDetailsFromLocalDB(strPlotCode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddLogBookDetailsTable>> getLogBookDetailsTableLocalDbDataLiveData() {
//        return logbookListLocalDbDataLiveData;
//    }
//
//
//
//    public void getFertlizerDetailsListFromLocalDB(String strLogBookNum) {
//        try {
//            fertilizerlistFromLocalDBLiveData = appRepository.getFerlizerListDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFertlizerDetailsTableLocalDbDataLiveData() {
//        return fertilizerlistFromLocalDBLiveData;
//    }

    public void getLandDetailsListFromLocalDb(String strFarmercode) {
        try {
            landDetailsLocalDbLiveData = appRepository.getLandDetailsFromLocalDb(strFarmercode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLandDetailsStageListFromLocalDb(String status,String strFarmercode,String plotNo) {
        try {
            landDetailsLocalDbLiveData = appRepository.getLandDetailsStatusFromLocalDb(status,strFarmercode,plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLandDetailsStageListFromLocalDb(String strFarmercode,String plotNo) {
        try {
            landDetailsLocalDbLiveData = appRepository.getLandDetailsStatusFromLocalDb(strFarmercode,plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLandD20DetailsStageListFromLocalDb(String seasonCode,String strFarmercode) {
        try {
            d20DetailsLocalDbLiveData = appRepository.getLandDetailsStageListFromLocalDb(seasonCode,strFarmercode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLandDetailsMeasureStageListFromLocalDb(String strFarmercode) {
        try {
            d10DetailsLocalDbLiveData = appRepository.getLandDetailsStageListFromLocalDb10(strFarmercode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20DiseaseList(String plotNo) {
        try {
            d20DiseasesListDbLiveData = appRepository.getD20DiseaseList(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20PestsList(String plotNo) {
        try {
            d20pestListDbLiveData = appRepository.getD20PestList(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20DiseaseListNotSync(String plotNo) {
        try {
            d20DiseasesListDbLiveData = appRepository.getD20DiseaseListNotSync(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20DiseaseListNotSync() {
        try {
            d20DiseasesListDbLiveData = appRepository.getD20DiseaseListNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20WeedList(String plotNo) {
        try {
            d20WeedListDbLiveData = appRepository.getD20WeedList(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20WeedListNotSync(String plotNo) {
        try {
            d20WeedListDbLiveData = appRepository.getD20WeedListNotSync(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20WeedListNotSync() {
        try {
            d20WeedListDbLiveData = appRepository.getD20WeedListNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20FertilizerList(String plotNo) {
        try {
            d20FertilizersListDbLiveData = appRepository.getD20FertilizerList(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20FertilizerListNotSync(String plotNo) {
        try {
            d20FertilizersListDbLiveData = appRepository.getD20FertilizerListNotSync(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20FertilizerListNotSync() {
        try {
            d20FertilizersListDbLiveData = appRepository.getD20FertilizerListNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void getD20PestList(String plotNo) {
//        try {
//            d20pestListDbLiveData = appRepository.getD20PestList(plotNo);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void getD20PestListNotSync(String plotNo) {
        try {
            d20pestListDbLiveData = appRepository.getD20PestListNotSync(plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20PestListNotSync() {
        try {
            d20pestListDbLiveData = appRepository.getD20PestListNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD10ListByStatusSeasonCode(String seasonCode,String strFarmercode) {
        try {
            d10DetailsLocalDbLiveData = appRepository.getD10ListByStatusSeasonCode(seasonCode,strFarmercode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotOfferListD10(String seasonCode,String farmerCode,String status) {
        try {
            ferlizerListFromLocalDBLiveDataNotSync = appRepository.getPlotOfferListD10(seasonCode,farmerCode,status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotOfferReportedListD10(String seasonCode,String farmerCode,String status) {
        try {
            ferlizerListFromLocalDBLiveDataNotSync = appRepository.getPlotOfferReportedListD10(seasonCode,farmerCode,status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotOfferListD10(String seasonCode,String farmerCode,String status,String offer) {
        try {
            ferlizerListFromLocalDBLiveDataNotSync = appRepository.getPlotOfferListD10(seasonCode,farmerCode,status,offer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//
//    public void getLandDetailsMeasureStageListFromLocalDb(String strFarmercode) {
//        try {
//            d20DetailsLocalDbLiveData = appRepository.getLandDetailsStageListFromLocalDb(strFarmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


    public void updatePLotNo(String measureArea,String agreedTon,String updateByUserId,String updatedate,String plotNo)
    {
        try {
            appRepository.updatePLotNo(measureArea,agreedTon,updateByUserId,updatedate,plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD10Agreemented(String crop,String micro,String trash,String gaps, String weedStatus, String bioArea, String deepArea, String trashArea, String earthArea,String ratoonArea,String trashShedderArea,String LoadShedderArea, Boolean sync, String updatedDate,String plotNO,String serverStatus)
    {
        try {
            appRepository.updateD10Agreemented(crop,micro,trash,gaps,weedStatus,bioArea,deepArea,trashArea,earthArea,ratoonArea,trashShedderArea,LoadShedderArea,false,updatedDate,plotNO,"0");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD30Agreemented(String crop,String micro,String trash,String gaps, String weedStatus, String bioArea, String deepArea, String trashArea, String earthArea,String ratoonArea,String trashShedderArea,String LoadShedderArea, Boolean sync, String updatedDate,String plotNO,String serverStatus)
    {
        try {
            appRepository.updateD30Agreemented(crop,micro,trash,gaps,weedStatus,bioArea,deepArea,trashArea,earthArea,ratoonArea,trashShedderArea,LoadShedderArea,false,updatedDate,plotNO,"0");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD10(String soilType,String spacing,String previous,String IrrigationTypeId, String IsSettsHotWaterTreatment, String IsDustApplied, String IsTrashMulching, String IsPreviousRedPlot, String IsBasalFertilization,String IsCompositeFormYard,String IsFilterPressMud,String IsGreenManures, Boolean sync, String updatedDate,String plotNO,String serverStatus)
    {
        try {
            appRepository.updateD10(soilType,spacing,previous,IrrigationTypeId,IsSettsHotWaterTreatment,IsDustApplied,IsTrashMulching,IsPreviousRedPlot,IsBasalFertilization,IsCompositeFormYard,IsFilterPressMud,IsGreenManures,false,updatedDate,plotNO,"0");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD10(String interCrop,String updateByUserId,String updatedate,String plotNo,String weedStatus)
    {
        try {
            appRepository.updateD10(interCrop,updateByUserId,updatedate,plotNo,weedStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD20(String interCrop,String updateByUserId,String updatedate,String plotNo,String weedStatus)
    {
        try {
            appRepository.updateD20(interCrop,updateByUserId,updatedate,plotNo,weedStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD30(String interCrop,String updateByUserId,String updatedate,String plotNo,String weedStatus)
    {
        try {
            appRepository.updateD30(interCrop,updateByUserId,updatedate,plotNo,weedStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD20Weed(String weedId,String isActive,String updatedDate,String plotNo,String serverStatus,int id)
    {
        try {
            appRepository.updateD20Weed(weedId,isActive,updatedDate,plotNo,"0",id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD20Fertilizer(String weedId,String isActive,String updatedDate,String plotNo,String serverStatus,int id)
    {
        try {
            appRepository.updateD20Fertilizer(weedId,isActive,updatedDate,plotNo,"0",id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePLotNoStage(String measureArea,String updateByUserId,String updatedate,String plotNo)
    {
        try {
            appRepository.updatePLotNo(measureArea,updatedate,plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePLotNoAgreemented(String measureArea,String updateByUserId,String updatedate,String plotNo)
    {
        try {
            appRepository.updatePLotNoAgreemented(measureArea,updatedate,plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateD10Growth(String soilTypeID,Boolean isSetts, Boolean isDust,
                                Boolean isTrash,String plotNO,String spacingId,Boolean isPreviousRedPlot,
                                Boolean isBasalFertilization, Boolean isCompositeFormYard,Boolean IsFilterPressMud,Boolean IsGreenManures)
    {
        try {
            appRepository.updateD10Growth(soilTypeID,isSetts,isDust,isTrash,plotNO,spacingId,isPreviousRedPlot,isBasalFertilization,isCompositeFormYard,IsFilterPressMud,IsGreenManures);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePLotNoStageReported(String measureArea,String updateByUserId,String updatedate,String plotNo)
    {
        try {
            appRepository.updatePLotNo(measureArea,updatedate,plotNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void updateWeedDate(String updatedate,String id,Boolean isControl)
    {
        try {
            appRepository.updateWeedDate(updatedate,id,isControl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

      public void updateFertilizerDate(String updatedate,String id,Boolean isControl)
    {
        try {
            appRepository.updateFertilizerDate(updatedate,id,isControl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void updateD20Disease(String controlDate,String identifiedDate,String updatedate,String id,Boolean isControl)
    {
        try {
            appRepository.updateD20Disease(controlDate,identifiedDate,updatedate,id,isControl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePestControlDate(String controlDate,String identifiedDate,String updatedate,String id,Boolean isControl)
    {
        try {
            appRepository.updatePestControlDate(controlDate,identifiedDate,updatedate,id,isControl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void getPlotDetailsListFromLocalDb(String strPlotNumber) {
//        try {
//            singleplotDetailsFromLocalDBLiveData = appRepository.getPlotDetailsFromLocalDB(strPlotNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void getDeleteTablesFromLocal()
    {
        try {
            appRepository.deleteAlltablesFromlocal();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAlltablesFromTransactionSync()
    {
        try {
            appRepository.deleteAlltablesFromTransactionSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//
//    public void getDeleteGetDataTablesFromLocal()
//    {
//        try {
//            appRepository.deleteGetAlltablesFromlocal();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void upDateLogBookDetails(String currentDate,String update ,String logBookNumber)
//    {
//        try {
//            appRepository.upDateLogBook(currentDate,update,logBookNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getVillageDetailsListFromLocalDB(String pincode) {
//        try {
//            villageDetailsByPincode = appRepository.getVillageTableDetailsFromLocalDbByPincode(pincode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getSeasonSelectionNameseasonID(String seasonID) {
//        try {
//            getSeasonNameBySeasonID = appRepository.getSeasonName(seasonID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getLookUpSelectionNameLookUpId(String selectionID) {
//        try {
//            getLookUpSelectionNameLookUpId = appRepository.getLookUpDataNameByID(selectionID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getDocIdentiFicationFromLocalDBByFarmerCode(String farmercode) {
//        try {
//            getDocIdentiFicationDetailsFromLocalDBByFarmerCode = appRepository.getDocIdentiFicationDetailsFromLocalDB(farmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void getCastDetailsByCastId(String villageID) {
        try {
            getCastDetailsFromLocalDBByCastID = appRepository.getCastDetailsFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getVillageDetailsByVillageId(String villageID) {
        try {
            getVillageDetailsFromLocalDBByVillageID = appRepository.getVillageDetailsFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getWeedById(String villageID) {
        try {
            insertWeedListIntoLocalDBQueryLiveData = appRepository.getWeedById(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFertilizerById(String villageID) {
        try {
            insertFertilizerListIntoLocalDBQueryLiveData = appRepository.getFertilizerById(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFarmerIDDetailsByFarmerCode(String villageID) {
        try {
            getFarmerDetailsFromLocalDBByFarmerCode = appRepository.getFarmerDetailsFromLocalDB((villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getVillageIdDetailsByVillage(String villageID) {
        try {
            getVillageDetailsFromLocalDBByVillageID = appRepository.getVillageDetailsFromLocalDB((villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void insertPlotGeoBoundsTableLocal(AddGeoBoundriesTable addGeoBoundriesTable) {
        try {
            PlotGeoBoundsTableLiveDataInsert = appRepository.insertPlotGeoBoundsTable(addGeoBoundriesTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//
//    public void insertD20TableLocal(AddGeoBoundriesTable addGeoBoundriesTable) {
//        try {
//            PlotGeoBoundsTableLiveDataInsert = appRepository.insertPlotGeoBoundsTable(addGeoBoundriesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void getPlantTypeDetailsByPlantId(String villageID) {
        try {
            getPlantTypeDetailsFromLocalDBByPlantTypeID = appRepository.getPlantTypeDetailsFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLookupDtlDetailsById(String villageID) {
        try {
            getLookupDtlDetailsFromLocalDBByID = appRepository.getLookupDtlDetailsFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotExistOnDetailsById(String villageID) {
        try {
            getPlotExistOnDetailsFromLocalDBByID = appRepository.getPlotExistOnDetailsFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void getPlotExistOnDetailsById() {
        try {
            getPlotExistOnDetailsListFromLocalDBByID = appRepository.getPlotExistOnDetailsFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getVarietyById(String villageID) {
        try {
            getVarietyFromLocalDBByID = appRepository.getVarietyFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMandalDetailsByVillageId(String villageID) {
        try {
            getMandalDetailsFromLocalDBByVillageID = appRepository.getMandalDetailsFromLocalDB(Integer.parseInt(villageID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getSectionDetailsBySectionId(String section) {
        try {
            getSectionDetailsFromLocalDBBySectionID = appRepository.getSectionDetailsFromLocalDB(Integer.parseInt(section));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getCircleDetailsBySectionId(String section) {
        try {
            getCircleDetailsFromLocalDBByCircleID = appRepository.getCircleDetailsFromLocalDB(Integer.parseInt(section));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDivisionDetailsBySectionId(String section) {
        try {
            getDivisionDetailsFromLocalDBByCircleID = appRepository.getDivisionDetailsFromLocalDB(Integer.parseInt(section));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDistrictDetailsBySectionId(String villageId) {
        try {
            getDistrictDetailsFromLocalDBByCircleID = appRepository.getDistrictDetailsFromLocalDB(Integer.parseInt(villageId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getBankDetailsById(String bankid) {
        try {
            getBankDetailsFromLocalDBByCircleID = appRepository.getBankDetailsFromLocalDB(Integer.parseInt(bankid));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void getBranchDetailsById(String bankid) {
        try {
            getBranchDetailsFromLocalDBByID = appRepository.getBranchDetailsFromLocalDB(Integer.parseInt(bankid));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getCropDetailsByCropId(String cropId) {
        try {
            getCropDetailsFromLocalDBByCropID = appRepository.getCropListDetailsFromLocalDB(Integer.parseInt(cropId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void getCropVarietyDetailsByCropId(String cropVarietyId) {
//        try {
//            getCropVarietyDetailsFromLocalDBByCropID = appRepository.getCropVarietyDetailsFromLocalDB(cropVarietyId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getMandalDetailsByMandalID(String mandalID) {
//        try {
//            getDeatailsFromMandalTableLocalDbLiveData = appRepository.getMandalDetailsFromLocalDb(mandalID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getLogBookModuleStatusByLogBookNumber(String logBookNum) {
//        try {
//            getLogBookModulesStatusDetailsFromLocalDBByLogBookID = appRepository.getLogBookModuleDetailsFromLocalDB(logBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LogBookModulesStatusDetailsTable> getLogBookModuleDetailsFromLocalDBByLogBookNumberLiveData() {
//        return getLogBookModulesStatusDetailsFromLocalDBByLogBookID;
//    }
//
//
//
//
//
//
//    public void getDistricDetailsFromlocalDB(String districID) {
//        try {
//            getDistrictDetailsFromLocalDB = appRepository.getDistrciDetailsFromLocalDB(districID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getStateDetailsFromlocalDB(String stateId) {
//        try {
//            getStateDetailsFromLocalDB = appRepository.getStateDetailsFromLocalDB(stateId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getBankDetailsFromLocalDBByFarmerCode(String strFarmercode) {
//        try {
//            bankDetailsFromLocalDBByfarmerCode = appRepository.getBankDeatilsByFarmerCode(strFarmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getDocIdentityDetailsByFarmerCode(String strFarmercode) {
//        try {
//            docIdentificationDetailsByFarmerCode = appRepository.getDocIdentiFicationDetailsByFarmercode(strFarmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 2/15/2022 log book adding to local db
//    public void insertAddLogBookDetailsTableLocalDb(AddLogBookDetailsTable addLogBookDetailsTable) {
//        try {
//            addLogBookDetailsTableLiveData = appRepository.insertAddLogBookDetailsTable(addLogBookDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableLiveDataFromLocalDB() {
//        return addLogBookDetailsTableLiveData;
//    }
//
//
//    public void upDateAddLogBookDetailsTableLocalDb(String logBookNumber,String dateUpdate) {
//        try {
//            upDateaddLogBookDetailsTableLiveData = appRepository.upDateAddLogBookDetailsTable(logBookNumber,dateUpdate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLogBookDetailsTable> getUpDateAddLogBookDetailsTableLiveDataFromLocalDB() {
//        return upDateaddLogBookDetailsTableLiveData;
//    }
//    public void insertRefreshTableDateCheckTableLocalDb(RefreshTableDateCheck refreshTableDateCheck) {
//        try {
//            addRefreshTableDateCheckLiveData = appRepository.insertRefreshTableDateCheck(refreshTableDateCheck);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<RefreshTableDateCheck> getRefreshTableDateCheckLiveDataFromLocalDB() {
//        return addRefreshTableDateCheckLiveData;
//    }
//
//
//    // TODO: 2/16/2022 Adding Fertilizer data
//    public void insertAddFertilizerDetailsTableLocalDb(AddFertilizerDetailsTable addFertilizerDetailsTable) {
//        try {
//            addFertilizerDetailsTableLiveData = appRepository.insertAddFertilizerDetailsTable(addFertilizerDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerDetailsTableLiveDataFromLocalDB() {
//        return addFertilizerDetailsTableLiveData;
//    }
//
//    // TODO: 2/17/2022 Add Organic Data
////    public void insertAddOrganicDetailsTableLocalDb(AddOrganicDetailsTable addOrganicDetailsTable) {
////        try {
////            addOrganicDetailsTableLiveData = appRepository.insertAddOrganicDetailsTable(addOrganicDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddOrganicDetailsTable> getAddOrganicDetailsTableLiveDataFromLocalDB() {
////        return addOrganicDetailsTableLiveData;
////    }
////
////
////
////    public void insertAddWaterSeasonFeildTableLocalDb(AddWaterSeasonFeildTable addWaterSeasonFeildTable) {
////        try {
////            addWaterSeasonFeildTableLiveData = appRepository.insertAddWaterSeasonFeildTable(addWaterSeasonFeildTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public void insertAddLandpermissionTableLocalDb(AddLandPreparationTable addLandPreparationTable) {
//        try {
//            addLandPreparationTableLiveData = appRepository.insertAddLandPreparationFeildTable(addLandPreparationTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddPestDiseasesTableLocalDb(AddPestDiseaseControlTable addPestDiseaseControlTable) {
//        try {
//            addPestDiseaseTableLiveData = appRepository.insertAddPestDiseasesFeildTable(addPestDiseaseControlTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddHarvestingTableLocalDb(AddHarvestingTable addHarvestingTable) {
//        try {
//            addharvestingTableLiveData = appRepository.insertAddLaHarvestingFeildTable(addHarvestingTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddSeedRateTableLocalDb(AddSeedRateTable addSeedRateTable) {
//        try {
//            addSeedRateTableLiveData = appRepository.insertAddSeedRateFeildTable(addSeedRateTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddNurseryPermissionTableLocalDb(AddNurseryPreparationTable addNurseryPreparationTable) {
//        try {
//            addNurseryPreparationTableLiveData = appRepository.insertAddnurseryPreparationFeildTable(addNurseryPreparationTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddMoistureContentTableLocalDb(AddMoistureContentTable addMoistureContentTable) {
//        try {
//            addMoistureContentTableLiveData = appRepository.insertAddMoistureContentFeildTable(addMoistureContentTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddYieldTableLocalDb(AddYieldTable addYieldTable) {
//        try {
//            addYieldTableLiveData = appRepository.insertAddYieldFeildTable(addYieldTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddWeedmanagementTableLocalDb(AddWeedManagementTable addWeedManagementTable) {
//        try {
//            addWeedManagementTableLiveData = appRepository.insertAddWeedmanagementFeildTable(addWeedManagementTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddWaterManagementTableLocalDb(AddWaterManagementTable addWaterManagementTable) {
//        try {
//            addWaterManagementTableLiveData = appRepository.insertAddWatermanagementFeildTable(addWaterManagementTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddTransplantingTableLocalDb(AddTransplantingTable addTransplantingTable) {
//        try {
//            addTransplantingTableLiveData = appRepository.insertAddTransplantingFeildTable(addTransplantingTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddFertilizerTableLocalDb(AddFertilizerDetailsTable addFertilizerDetailsTable) {
//        try {
//            addFertilizationTableLiveData = appRepository.insertAddFertilizationFeildTable(addFertilizerDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddGeoBoundriesTableLocalDb(AddGeoBoundriesTable addGeoBoundriesTable) {
//        try {
//            addGeoBoundriesTableLiveData = appRepository.insertAddGeoBoundriesFeildTable(addGeoBoundriesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLandPreparationTable> getAddLandPreparationFeildTableLiveDataFromLocalDB() {
//        return addLandPreparationTableLiveData;
//    }
//
//    public LiveData<AddPestDiseaseControlTable> getAddPestDiseasesFeildTableLiveDataFromLocalDB() {
//        return addPestDiseaseTableLiveData;
//    }
//
//    public LiveData<AddHarvestingTable> getAddHarvestingFeildTableLiveDataFromLocalDB() {
//        return addharvestingTableLiveData;
//    }
//
//    public LiveData<AddSeedRateTable> getAddSeedRateFeildTableLiveDataFromLocalDB() {
//        return addSeedRateTableLiveData;
//    }
//
//    public LiveData<AddNurseryPreparationTable> getAddNurseryPreparationFeildTableLiveDataFromLocalDB() {
//        return addNurseryPreparationTableLiveData;
//    }
//
//    public LiveData<AddMoistureContentTable> getAddMoistureContentFeildTableLiveDataFromLocalDB() {
//        return addMoistureContentTableLiveData;
//    }
//
//    public LiveData<AddYieldTable> getAddYieldFeildTableLiveDataFromLocalDB() {
//        return addYieldTableLiveData;
//    }
//
//    public LiveData<AddWeedManagementTable> getAddWeedmanagementFeildTableLiveDataFromLocalDB() {
//        return addWeedManagementTableLiveData;
//    }
//
//    public LiveData<AddWaterManagementTable> getAddWatermanagementFeildTableLiveDataFromLocalDB() {
//        return addWaterManagementTableLiveData;
//    }
//
//    public LiveData<AddTransplantingTable> getAddTransplantingFeildTableLiveDataFromLocalDB() {
//        return addTransplantingTableLiveData;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizationFeildTableLiveDataFromLocalDB() {
//        return addFertilizationTableLiveData;
//    }
//
//    public LiveData<AddGeoBoundriesTable> getAddGeoBoundriesFeildTableLiveDataFromLocalDB() {
//        return addGeoBoundriesTableLiveData;
//    }
//
////    public LiveData<AddWaterSeasonFeildTable> getAddWaterSeasonFeildTableLiveDataFromLocalDB() {
////        return addWaterSeasonFeildTableLiveData;
////    }
////
////
////    public void getWaterSeasonFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
////        try {
////            getWaterSeasonFieldDetailsFromLocalDBLiveData = appRepository.getWaterSeasonFeildDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public void getLandPreparationFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getLandPreparationDetailsFromLocalDBLiveData = appRepository.getLandPreparationFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getPestDiseasecontrolFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getPestDiseasecontrolDetailsFromLocalDBLiveData = appRepository.getPestDiseaseControlFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getHarvestingFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getharvestingDetailsFromLocalDBLiveData = appRepository.getHarvestingFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getSeedRateFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getSeedrateDetailsFromLocalDBLiveData = appRepository.getSeedRateFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getNurseryFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getNurseryPreparationDetailsFromLocalDBLiveData = appRepository.getNurseryPreparationFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getmoistureContentFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getMoistureContentDetailsFromLocalDBLiveData = appRepository.getMoistureContentFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getYieldFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getYieldDetailsFromLocalDBLiveData = appRepository.getYieldFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getWeedmanagementDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getWeedmanagementDetailsFromLocalDBLiveData = appRepository.getWeedmanagementFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getWaterManagementFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getWatermanagementDetailsFromLocalDBLiveData = appRepository.getWatermanagementFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getTransplantingFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getTransplantingDetailsFromLocalDBLiveData = appRepository.getTransplantingFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getFertilizerFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getFertilizerDetailsFromLocalDBLiveData = appRepository.getFertilizerFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getGeoBoundriesFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getGeoBoundriesDetailsFromLocalDBLiveData = appRepository.getGeoBoundriesFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
////    public LiveData<AddWaterSeasonFeildTable> getWaterSeasonFieldDetailsFromLocalDBLiveData() {
////        return getWaterSeasonFieldDetailsFromLocalDBLiveData;
////    }
//
//    public LiveData<List<AddLandPreparationTable>> getLandPreparationFieldDetailsFromLocalDBLiveData() {
//        return getLandPreparationDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddPestDiseaseControlTable>> getPestDiseaseControlFieldDetailsFromLocalDBLiveData() {
//        return getPestDiseasecontrolDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddHarvestingTable>> getHarvestingFieldDetailsFromLocalDBLiveData() {
//        return getharvestingDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddSeedRateTable>> getSeedrateFieldDetailsFromLocalDBLiveData() {
//        return getSeedrateDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddNurseryPreparationTable>> getNurseryPreparationFieldDetailsFromLocalDBLiveData() {
//        return getNurseryPreparationDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddMoistureContentTable>> getMoistureContentFieldDetailsFromLocalDBLiveData() {
//        return getMoistureContentDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddYieldTable>> getYieldFieldDetailsFromLocalDBLiveData() {
//        return getYieldDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddWeedManagementTable>> getWeedmanagementFieldDetailsFromLocalDBLiveData() {
//        return getWeedmanagementDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddWaterManagementTable>> getWaterManagementFieldDetailsFromLocalDBLiveData() {
//        return getWatermanagementDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddTransplantingTable>> getTransplantingFieldDetailsFromLocalDBLiveData() {
//        return getTransplantingDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFertilizerFieldDetailsFromLocalDBLiveData() {
//        return getFertilizerDetailsFromLocalDBLiveData;
//    }
//    public LiveData<Integer> getLandPreparationfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getLandPreparationnfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getPestDiseasefielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getPestDiseaseControlfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getHarvestingfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getharvestingfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getSeedRatefielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getSeedRatefieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getNurseryPreparatinfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getNurseryPreparationfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getMoistureContentfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getMoistureContentfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getYieldfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getYieldfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getWeedmanagementfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getWeedManagementfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getWatermanagementfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getWatermanagementfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getTransplantingfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getTransplantingfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getFertilizerfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getFertilizerfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<List<AddGeoBoundriesTable>> getGeoBoundriesFieldDetailsFromLocalDBLiveData() {
//        return getGeoBoundriesDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<Integer> getGeoBoundriesfieldsDatailsCount(String logBookNum,String strDate) {
//        return appRepository.getGeoBoundriesfieldsDataCount(logBookNum,strDate);
//    }
//
//    public void getAddLandPreparationFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getLandPreparationFeildDetailsTableLiveDataByDate = appRepository.getAddLandPreparationFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddPestDiseaseFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getPestDiseasecontrolFeildDetailsTableLiveDataByDate = appRepository.getAddPestDiseaseFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddHarvestingFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getHarvestingFeildDetailsTableLiveDataByDate = appRepository.getAddHarvestingFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddSeedrateFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getSeedRateFeildDetailsTableLiveDataByDate = appRepository.getAddSeedRateFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddNurseryFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getNurseryPreparationFeildDetailsTableLiveDataByDate = appRepository.getAddNurseryPreparationFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddMoistureContentFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getMoistureContentFeildDetailsTableLiveDataByDate = appRepository.getAddMoistureContentFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddYieldFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getYieldFeildDetailsTableLiveDataByDate = appRepository.getAddYieldFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddWeedManagementFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getWeedManagementFeildDetailsTableLiveDataByDate = appRepository.getAddWeedManagementFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddWaterManagementFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getWaterManagementFeildDetailsTableLiveDataByDate = appRepository.getAddWaterManagementFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddTransplantingFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getTransplantingFeildDetailsTableLiveDataByDate = appRepository.getAddTransplantingFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddFertilizerFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getFertilizerFeildDetailsTableLiveDataByDate = appRepository.getAddFertilizerFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddGeoBoundriesFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getGeoBoundriesFeildDetailsTableLiveDataByDate = appRepository.getAddGeoBoundriesFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLandPreparationTable> getAddLandPreparationFeildTableDetailsTableFromLocalDBLiveData() {
//        return getLandPreparationFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddPestDiseaseControlTable> getAddPestDiseasecontrolFeildTableDetailsTableFromLocalDBLiveData() {
//        return getPestDiseasecontrolFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddHarvestingTable> getAddHarvestingFeildTableDetailsTableFromLocalDBLiveData() {
//        return getHarvestingFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddSeedRateTable> getAddSeedRateFeildTableDetailsTableFromLocalDBLiveData() {
//        return getSeedRateFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddNurseryPreparationTable> getAddNurseryPreparationFeildTableDetailsTableFromLocalDBLiveData() {
//        return getNurseryPreparationFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddMoistureContentTable> getAddMoistureContentFeildTableDetailsTableFromLocalDBLiveData() {
//        return getMoistureContentFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddYieldTable> getAddYieldFeildTableDetailsTableFromLocalDBLiveData() {
//        return getYieldFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddWeedManagementTable> getAddWeedManagementFeildTableDetailsTableFromLocalDBLiveData() {
//        return getWeedManagementFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddWaterManagementTable> getAddWatermanagementFeildTableDetailsTableFromLocalDBLiveData() {
//        return getWaterManagementFeildDetailsTableLiveDataByDate;
//    }
//    public LiveData<AddTransplantingTable> getAddTransplantingFeildTableDetailsTableFromLocalDBLiveData() {
//        return getTransplantingFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerFeildTableDetailsTableFromLocalDBLiveData() {
//        return getFertilizerFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddGeoBoundriesTable> getAddGeoBoundriesFeildTableDetailsTableFromLocalDBLiveData() {
//        return getGeoBoundriesFeildDetailsTableLiveDataByDate;
//    }
//
//
////    public void getOrganicDetailsListFromLocalDB(String strLogBookNum) {
////        try {
////            orgaincAmendsDatalistFromLocalDBLiveData = appRepository.getOrganicDetailsTableListDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddOrganicDetailsTable>> getOrganicDetailsTableLocalDbDataLiveData() {
////        return orgaincAmendsDatalistFromLocalDBLiveData;
////    }
////
//
//    public LiveData<Integer> getCount() {
//        return appRepository.getCountData();
//
//    }
//
//    public LiveData<Integer> getCountLand() {
//        return appRepository.getCountDataLand();
//
//    }
//
////    public VillageTable getVillageNameLocalDB(String villageId) {
////        return appRepository.getVillageNameLocalDB(villageId);
////
////    }
//
//
//    // TODO: 2/25/2022 get not sync count from local db
//
//    public LiveData<Integer> getNotSyncFarmerCountDataFromLocalDB() {
//        return appRepository.getNotSyncFarmerCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncDocCountDataFromLocalDB() {
//        return appRepository.getNotSyncDocCountDataFromLocalDB();
//
//    }
//
//
//    public LiveData<Integer> getNotSyncBankCountDataFromLocalDB() {
//        return appRepository.getNotSyncBankCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncPlotCountDataFromLocalDB() {
//        return appRepository.getNotSyncPlotCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncGeoCountDataFromLocalDB() {
//        return appRepository.getNotSyncGeoCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncLogBookCountDataFromLocalDB() {
//        return appRepository.getNotSyncLogBookCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncFertlizerCountDataFromLocalDB() {
//        return appRepository.getNotSyncFertilizerCountDataFromLocalDB();
//
//    }
//
////    public LiveData<Integer> getNotSyncOrganicCountDataFromLocalDB() {
////        return appRepository.getNotSyncOrganicCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncWaterRegimeCountDataFromLocalDB() {
////        return appRepository.getNotSyncWaterRegimeCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncWaterPreSeasonCountDataFromLocalDB() {
////        return appRepository.getNotSyncWaterPreSeasonCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncBorewellCountDataFromLocalDB() {
////        return appRepository.getNotSyncBorewellCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncWaterFieldCountDataFromLocalDB() {
////        return appRepository.getNotSyncWaterFieldCountDataFromLocalDB();
////
////    }
//
//    public LiveData<Integer> getNotSyncHarvestCountDataFromLocalDB() {
//        return appRepository.getNotSyncHarvestCountDataFromLocalDB();
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    // TODO: 2/18/2022 harvest saving part
//    public void insertAddHarvestDetailsTableLocalDb(AddHarvestDetailsTable addHarvestDetailsTable) {
//        try {
//            addHarvestDetailsTableLiveData = appRepository.insertAddHarvestDetailsTable(addHarvestDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddHarvestDetailsTable> getAddHarvestDetailsTableLiveDataFromLocalDB() {
//        return addHarvestDetailsTableLiveData;
//    }
//
//
//
//    public void getAddHarvestDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getAddHarvestDetailsFromLocalDBLiveData = appRepository.getAddHarvestDetailsTableDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddHarvestDetailsTable> getAddHarvestDetailsFromLocalDBLiveData() {
//        return getAddHarvestDetailsFromLocalDBLiveData;
//    }
//
//
//    // TODO: 2/18/2022 Add Borewell Season Part
//
//
////
////    public void insertAddBoreWellPumpSeasonTableLocalDb(AddBoreWellSeasonTable addBoreWellSeasonTable) {
////        try {
////            addBoreWellPumpSeasonTableLiveData = appRepository.insertAddBoreWellPumpSeasonTable(addBoreWellSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonTableLiveDataFromLocalDB() {
////        return addBoreWellPumpSeasonTableLiveData;
////    }
//
//
//    public void getCodeLogBookDropDownHDRTableDetailsFromLocalDB(String name) {
//        try {
//            getLogBookDropDownHDRTableDetailsFromLocalDBLiveData = appRepository.getLogBookDropDownHDRTableDataFromLocalDB(name);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LogBookDropDownHDRTable> getLogBookDropDownHDRTableDetailsFromLocalDBLiveData() {
//        return getLogBookDropDownHDRTableDetailsFromLocalDBLiveData;
//    }
//
//
////    public void getAddAddBoreWellPumpSeasonDetailsFromLocalDB(String strLogBookNum) {
////        try {
////            getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData = appRepository.getAddBoreWellPumpSeasonTableDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData() {
////        return getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData;
////    }
////
////    // TODO: 2/18/2022 water pre season
////
////    public void insertAddWaterReasonPreSeasonTableLocalDb(AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable) {
////        try {
////            addWaterReasonPreSeasonTableLiveData = appRepository.insertAddWaterReasonPreSeasonTable(addWaterReasonPreSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterReasonPreSeasonTable> getInsertAddWaterReasonPreSeasonTableLiveDataFromLocalDB() {
////        return addWaterReasonPreSeasonTableLiveData;
////    }
////
////
////    public void getWaterReasonPreSeasonDetailsFromLocalDB(String strLogBookNum) {
////        try {
////            getWaterReasonPreSeasonDetailsFromLocalDBLiveData = appRepository.getAddWaterReasonPreSeasonDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterReasonPreSeasonTable> getAddWaterReasonPreSeasonDetailsFromLocalDBLiveData() {
////        return getWaterReasonPreSeasonDetailsFromLocalDBLiveData;
////    }
////
////
////    // TODO: 2/18/2022 water regime season
////
////    public void insertAddWaterRegimeSeasonDetailsTableLocalDb(AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable) {
////        try {
////            addWaterRegimeSeasonDetailsTableLiveData = appRepository.insertAddWaterRegimeSeasonDetailsTable(addWaterRegimeSeasonDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterRegimeSeasonDetailsTable> getInsertAddWaterRegimeSeasonDetailsTableLiveDataFromLocalDB() {
////        return addWaterRegimeSeasonDetailsTableLiveData;
////    }
//
//
//    public void getAddFertilizerDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getFertilizerDetailsTableLiveDataByDate = appRepository.getAddFertilizerDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerDetailsTableFromLocalDBLiveData() {
//        return getFertilizerDetailsTableLiveDataByDate;
//    }
//
//
//    public void getRefreshTableDateCheckFromLocalDBByDate(String DeviceID,String currentDate) {
//        try {
//            getAddRefreshTableDateCheck = appRepository.getAddRefreshTableDateCheckTableDate(DeviceID,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<RefreshTableDateCheck> getRefreshTableDateCheckByDateFromLocalDBLiveData() {
//        return getAddRefreshTableDateCheck;
//    }
//
//
//
//    // TODO: 2/18/2022 logbook date
//    public void getAddLogBookDetailsTableFromLocalDBByDate(String strLogBookNum,String currentDate) {
//        try {
//            getAddLogBookDetailsTableLiveDataByDate = appRepository.getAddLogBookDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableByDateFromLocalDBLiveData() {
//        return getAddLogBookDetailsTableLiveDataByDate;
//    }
//
//    // TODO: 2/18/2022 delete log bokk data
//
//    public void getDeleteLogbookTablesFromLocalDB(String logBookNum)
//    {
//        try {
//            appRepository.deleteLogBooktablesFromlocalDB(logBookNum,"");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<Integer> getCountLogBook() {
//        return appRepository.getLogBookCount();
//    }
//
//    public LiveData<Integer> getCountFertlizerCount(String logBookNum,String strDate) {
//        return appRepository.getFerlizerDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<List<SavingFarmerProfieImagesTable>> getSavingFarmerProfileImagesNotSyncLiveDataList() {
//        return savingFarmerProfileImagesNotSyncLiveData;
//    }
//
//
//    public LiveData<List<SavingBankImagesTable>> getSavingBankProfileImagesNotSyncLiveData() {
//        return savingBankProfileImagesNotSyncLiveData;
//    }
//
//    public LiveData<List<SavingPlotProfieImagesTable>> getSavingPlotProfileImagesNotSyncLiveData() {
//        return savingPlotProfileImagesNotSyncLiveData;
//    }
//
//
//
////    public void getWaterRegimeSeasonDetailsFromLocalDB(String strLogBookNum) {
////        try {
////            getWaterRegimeSeasonDetailsFromLocalDBLiveData = appRepository.getAddWaterRegimeSeasonDetailsDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterRegimeSeasonDetailsTable> getAddWaterRegimeSeasonDetailsFromLocalDBLiveData() {
////        return getWaterRegimeSeasonDetailsFromLocalDBLiveData;
////    }
//
//
//    // TODO: 1/26/2022 Accesing of pincode from local db
//
//    public LiveData<List<VillageTable>> getvillageDetailsByPincodeLiveData() {
//        return villageDetailsByPincode;
//    }
//
//
//
//
//    public LiveData<MandalTable> getLocalDBMandalDetailsLiveData() {
//        return getDeatailsFromMandalTableLocalDbLiveData;
//    }


    public LiveData<AddGeoBoundriesTable> PlotGeoBoundsTableLiveDataInsert() {
        return PlotGeoBoundsTableLiveDataInsert;
    }

    public LiveData<VillageTable> getVillageDetailsFromLocalDBByVillageIdLiveData() {
        return getVillageDetailsFromLocalDBByVillageID;
    }

    public LiveData<AddFarmerTable> getFarmerDetailsFromLocalDBByFarmerIdLiveData() {
        return getFarmerDetailsFromLocalDBByFarmerCode;
    }

    public LiveData<CastTable> getCastDetailsFromLocalDBByCastIdLiveData() {
        return getCastDetailsFromLocalDBByCastID;
    }

    public LiveData<PlantTypeTable> getPlantTypeDetailsFromLocalDBByVillageIdLiveData() {
        return getPlantTypeDetailsFromLocalDBByPlantTypeID;
    }

    public LiveData<LookupDtlTable> getLookupDtlDetailsFromLocalDBByIdLiveData() {
        return getLookupDtlDetailsFromLocalDBByID;
    }

    public LiveData<PlotExistOnTable> getPlotExistOnDetailsFromLocalDBByIdLiveData() {
        return getPlotExistOnDetailsFromLocalDBByID;
    }

    public LiveData<List<PlotExistOnTable>> getPlotExistOnDetailsListFromLocalDBByIdLiveData() {
        return getPlotExistOnDetailsListFromLocalDBByID;
    }

    public LiveData<VarietyTable> getVarietyFromLocalDBByIdLiveData() {
        return getVarietyFromLocalDBByID;
    }

    public LiveData<MandalTable> getMandalDetailsFromLocalDBByVillageIdLiveData() {
        return getMandalDetailsFromLocalDBByVillageID;
    }

    public LiveData<SectionTable> getSectionDetailsFromLocalDBBySectionIdLiveData() {
        return getSectionDetailsFromLocalDBBySectionID;
    }

    public LiveData<CircleTable> getCircleDetailsFromLocalDBByCircleIdLiveData() {
        return getCircleDetailsFromLocalDBByCircleID;
    }

    public LiveData<DivisionTable> getDivisionDetailsFromLocalDBBySectionIdLiveData() {
        return getDivisionDetailsFromLocalDBByCircleID;
    }

    public LiveData<DistrictTable> getDistrictDetailsFromLocalDBBySectionIdLiveData() {
        return getDistrictDetailsFromLocalDBByCircleID;
    }


    public LiveData<BankTable> getBankDetailsFromLocalDBByIdLiveData() {
        return getBankDetailsFromLocalDBByCircleID;
    }

    public LiveData<BranchTable> getBranchDetailsFromLocalDBByIdLiveData() {
        return getBranchDetailsFromLocalDBByID;
    }


//
//    public LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDetailsFromLocalDBByFarmerCodeLiveData() {
//        return getDocIdentiFicationDetailsFromLocalDBByFarmerCode;
//    }
//
//
    public LiveData<CropTable> geCropListDetailsFromLocalDBByCropIdLiveData() {
        return getCropDetailsFromLocalDBByCropID;
    }
//
//    public LiveData<CropVarietyListTable> geCropVarietyListDetailsFromLocalDBByCropIdLiveData() {
//        return getCropVarietyDetailsFromLocalDBByCropID;
//    }
//
//    public LiveData<LookUpDropDownDataTable> getLookUpDropDownDetailsFromLocalDBByLookUPIDLiveData() {
//        return getLookUpSelectionNameLookUpId;
//    }
//
//    public LiveData<SeasonTable> getSeasonTableDetailsFromLocalDBBySeasonIDLiveData() {
//        return getSeasonNameBySeasonID;
//    }
//
//
//
//    public LiveData<PlotDetailsListTable> getPlotDetailsFromLocalDBByPlotNumLiveData() {
//        return singleplotDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<SavingFarmerProfieImagesTable> getInsertionOfFarmerProfileImagesLiveData() {
//        return savingFarmerProfieImagesTableLiveData;
//    }
//
//    public LiveData<SavingPlotProfieImagesTable> getInsertionOfPlotProfileImagesLiveData() {
//        return savingPlotImagesTableLiveData;
//    }
//
//    private LiveData<AddComplaintsDetailsTable> addComplaintsDetailsTableLiveData;
//    private LiveData<List<AddComplaintsDetailsTable>> getAddComplainFormDetailsFromLocalDBLiveData;
//
//
//    public LiveData<SavingBankImagesTable> getInsertionOfBankImagesLiveData() {
//        return savingBankImagesTableLiveData;
//    }
//
//    public LiveData<GeoBoundariesTable> getGeoBoundariesTableLocalDB() {
//        return geoBoundariesTableLiveDataInsert;
//    }
//
//    public LiveData<List<GeoBoundariesTable>> getGeoBoundariesTableListLocalDB() {
//        return savingGeoBoundariesTableListData;
//    }
//
//
//
//
//
//
//    public LiveData<DistrictTable> getDistriclDetailsFromLocalDBLiveData() {
//        return getDistrictDetailsFromLocalDB;
//    }
//
//    public LiveData<StatesTable> getStateTablelDetailsFromLocalDBLiveData() {
//        return getStateDetailsFromLocalDB;
//    }
//

    public LiveData<List<VarietyTable>> getVarietyDetailsListLiveData() {
        return varietyListFromLocalDBLiveData;
    }

    public LiveData<List<LookupHDRTable>> getLookupHdrDetailsListLiveData() {
        return lookupHdrListFromLocalDBLiveData;
    }

//    public LiveData<List<AddGrowthMonitoringTable>> getGmListLiveData() {
//        return gwNotSyncLiveData;
//    }

    public LiveData<List<LookupDtlTable>> getLookupDetailsListLiveData() {
        return lookupDtlListFromLocalDBLiveData;
    }

    public LiveData<List<PlantTypeTable>> getPlantTypeDetailsListLiveData() {
        return plantTypeListFromLocalDBLiveData;
    }

    public LiveData<List<PlantSubTypeTable>> getPlantSubTypeDetailsListLiveData() {
        return plantSubTypeListFromLocalDBLiveData;
    }

    public LiveData<List<CropTable>> getCropDetailsListLiveData() {
        return cropListFromLocalDBLiveData;
    }

    public LiveData<List<ResonForNotPlantingTable>> getReasonDetailsListLiveData() {
        return reasonListFromLocalDBLiveData;
    }

    public LiveData<List<VillageTable>> getVillageDetailsListLiveData() {
        return villageListFromLocalDBLiveData;
    }

    public LiveData<List<AddFarmerTable>> getFarmerDetailsListLiveData() {
        return farmerListFromLocalDBLiveData;
    }

    public LiveData<List<AddPlotTable>> getFarmerPlotDetailsListLiveData() {
        return landDetailsLocalDbLiveData;
    }

    public LiveData<List<AddD20Table>> getD20DetailsListLiveData() {
        return d20DetailsLocalDbLiveData;
    }

    public LiveData<List<AddD10Table>> getD10DetailsListLiveData() {
        return d10DetailsLocalDbLiveData;
    }

    public LiveData<List<D20DiseaseTable>> getD20DiseaseListLiveData() {
        return d20DiseasesListDbLiveData;
    }


    public LiveData<List<D20WeedTable>> getD20WeedListLiveData() {
        return d20WeedListDbLiveData;
    }


    public LiveData<List<D20FertilizerTable>> getD20FertilizerListLiveData() {
        return d20FertilizersListDbLiveData;
    }

    public LiveData<List<D20PestTable>> getD20PestListLiveData() {
        return d20pestListDbLiveData;
    }


//    public LiveData<List<PlotDetailsListTable>> getLandDetailsListTableLocalDBLiveData() {
//        return landdetailsListFromLocalDBLiveData;
//    }
//
//    public LiveData<List<DocIdentiFicationDeatilsTable>> getDocIdentiFicationDeatilsTableFromLocalLiveData() {
//        return docIdentificationDetailsSubmitTableLiveData;
//    }
//
//
//    public LiveData<List<BankDetailsSubmitTable>> getBankDetailsTableFromLocalLiveData() {
//        return bankDetailsSubmitTableFromLocalDbLiveData;
//    }
//
//
//    public LiveData<List<GeoBoundariesTable>> getGeoBoundariesFromLocalLiveData() {
//        return geoBoundariesTableFromLocalDbLiveData;
//    }
//    public LiveData<List<GeoBoundariesTable>> getInsertGeoBoundariesFromLocalLiveData() {
//        return listentryValuesGeoBoundaries;
//    }
//
//
//
//    public LiveData<FarmerTable> getFarmerDetailListTableLiveDataFromLocalDB() {
//        return farmerDetailListTableLiveData;
//    }
//
//    public LiveData<FarmerTable> getfarmerDetailListTableLiveDataInsertLiveDataFromLocalDB() {
//        return farmerDetailListTableLiveDataInsert;
//    }
//
//
//
//    public LiveData<DocIdentiFicationDeatilsTable> getDocDetailLsTableLiveDataFromLocalDB() {
//        return documentSavingDataLocalDB;
//    }
//
//    public LiveData<BankDetailsSubmitTable> getBankDetailsSubmitTableLiveDataFromLocalDB() {
//        return bankDetailsSubmitTableLiveData;
//    }
//
//
//
//    public LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDeatilsTableLiveDataFromLocalDB() {
//        return docIdentiFicationDeatilsTableLiveData;
//    }
//
//
//    public LiveData<CurrentVisitFarmerTables> getCurrentVisitFarmerTablesLiveDataFromLocalDB() {
//        return currentVisitFarmerTablesLiveData;
//    }
//
//
//    public LiveData<PlotDetailsListTable> getPlotDetailListTableLiveData() {
//        return plotDetailListTableLiveData;
//    }
//
//
//    public LiveData<GeoBoundariesTable> getGeoBoundariesInsertTableLocalDBLiveData() {
//        return geoBoundariesTableLocalDbInsertLiveData;
//    }
//
//
//    // TODO: get pin code village
//    public LiveData<List<PinCodeDetailsDataTable>> getByPinCodeStateVillageDataTableLivedata() {
//        return bypincodestatevillagelistitemLiveData;
//    }
//
//
//    public LiveData<List<SyncResponseDTO>> getsyncResponseDTOFromServerLiveData() {
//        return syncResponseDTOFromServerLiveData;
//    }
//
//    public LiveData<List<LoginResponseDTO>> getloginResponseDTOFromServerLiveData() {
//        return loginResponseDTOFromServerLiveData;
//    }
//
//    public LiveData<List<PinCodeDetailsResponseDTO>> getpincodeDetailsResponseFromServerLiveData() {
//        return pincodeDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<CropDetailsResponseFromServerDTO>> getcropDetailsResponseFromServerLiveData() {
//        return cropDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<VillageDetailsResponseDTO>> getVillageDetailsResponseFromServerLiveDataLiveData() {
//        return villageDetailsResponseFromServerLiveData;
//    }
//
//
//
//
//
//    public LiveData<List<PlotNumberDataResponseDTO>> getPlotCodeDetailsFromserverLivedata() {
//        return syncDataResponseDTOLiveData;
//    }
//
//
//    // TODO: Response of state
//
//    public LiveData<List<StateListResponseDTO>> getStateListDetailsFromserverLivedata() {
//        return stateDetailsResponseFromServerLiveData;
//    }
//
//
//    public LiveData<List<DistricDetailsResponseDTO>> getDistricListDetailsFromserverLivedata() {
//        return districDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<ClusterDetailsResponseDTO>> getClusterDetailsFromserverLivedata() {
//        return clusterDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<MandalDetailsResponseDTO>> getMandalDetailsFromserverLivedata() {
//        return mandalDetailsResponseFromServerLiveData;
//    }
//
//
//    public LiveData<List<VillageByMandalIdDetailsResponseDTO>> getvillageDetailsByMandalResponseFromServerLiveData() {
//        return villageDetailsByMandalResponseFromServerLiveData;
//    }
//
//
//    // TODO: Server data saved into local db by master sync
//
//
//    // TODO: state details from server
//    public void getStateDetailsFromServerSavedIntoLocalDB() {
//        try {
//            stateListSavedIntoLocalDBFromServer = appRepository.getStateListFromServerToSaveLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<StatesTable>> getstateListSavedIntoLocalDBFromServerLivedata() {
//        return stateListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: distric details from server
//    public void getDistricDetailsFromServerSavedIntoLocalDB() {
//        try {
//            districListSavedIntoLocalDBFromServer = appRepository.getDistrictTableResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<DistrictTable>> getdistricListSavedIntoLocalDBFromServerLivedata() {
//        return districListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: mandal details from server
//    public void getMandalDetailsFromServerSavedIntoLocalDB() {
//        try {
//            mandalListSavedIntoLocalDBFromServer = appRepository.getMandalResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<MandalTable>> getmandalListSavedIntoLocalDBFromServerLivedata() {
//        return mandalListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: village details from server
//    public void getVillageTotalDetailsFromServerSavedIntoLocalDB() {
//        try {
//            villageListSavedIntoLocalDBFromServer = appRepository.getVillageTableResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<VillageTable>> getvillageListSavedIntoLocalDBFromServerLivedata() {
//        return villageListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: pincode details from server
//    public void getClusterDTLDetailsFromServerSavedIntoLocalDB() {
//        try {
//
//            clusterdtlListSavedIntoLocalDBFromServer = appRepository.getClusterDTlResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: pincode details from server
//    public void getMasterSyncDataFromServerSavedIntoLocalDB() {
//        try {
//
//            getMasterSyncDataFromServerLiveData = appRepository.getMasterSynDetailsFromServer();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<ClusterDTLTable>> getclusterDTLlistSavedIntoLocalDBFromServerLivedata() {
//        return clusterdtlListSavedIntoLocalDBFromServer;
//    }
//
//    public LiveData<List<GetMasterPersonalLandAllDetailsRequestDTO>> getGetMasterPersonalLandAllDetailsRequestDTOLivedata() {
//        return getMasterSyncDataFromServerLiveData;
//    }
//
//
//    // TODO: pincode details from server
//    public void getClusterHDRDetailsFromServerSavedIntoLocalDB() {
//        try {
//            clusterHDRlistSavedIntoLocalDBFromServer = appRepository.getClusterHDRListFromServerToSaveLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<ClusterHDRTable>> getclusterHDRlistSavedIntoLocalDBFromServerLivedata() {
//        return clusterHDRlistSavedIntoLocalDBFromServer;
//    }
//
//    // TODO: crop details from server
//    public void getCropDetailsListFromServerSavedIntoLocalDB() {
//        try {
//            cropListSavedIntoLocalDBFromServer = appRepository.getCropListFromServerToSaveLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<CropListTable>> getcropListSavedIntoLocalDBFromServerLivedata() {
//        return cropListSavedIntoLocalDBFromServer;
//    }
//
//
////    // TODO: pincode village  details from server
////    public void getPincodeDetailsFromServerSavedIntoLocalDB(){
////        try{
////            pinCodeDetailsListSavedIntoLocalDBFromServer=appRepository.getStateDetailsResponseDTOServer();
////        }catch (Exception ex){
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<PinCodeDetailsListTable>> getpinCodeDetailsListSavedIntoLocalDBFromServerLivedata() {
////        return pinCodeDetailsListSavedIntoLocalDBFromServer;
////    }
////
////
////
////    // TODO: pincode village send  details from server
////    public void getPincodeVillageFromServerSavedIntoLocalDB(){
////        try{
////            pincodevillageDetailsListSavedIntoLocalDBFromServer=appRepository.getStateDetailsResponseDTOServer();
////        }catch (Exception ex){
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<VillageDetailsByPinCodListTable>> getvillageDetailsListSavedIntoLocalDBFromServerLivedata() {
////        return pincodevillageDetailsListSavedIntoLocalDBFromServer;
////    }
////
////
////
////
////
////    // TODO: cluster ltr details from server
////    public void getClusterDTLDetailsFromServerSavedIntoLocalDB(){
////        try{
////            clusterdtlListSavedIntoLocalDBFromServer=appRepository.getClusterHDRListFromServerToSaveLocalDB();
////        }catch (Exception ex){
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<ClusterDTLTable>> getclusterdtlListSavedIntoLocalDBFromServerLivedata() {
////        return clusterdtlListSavedIntoLocalDBFromServer;
////    }
//
//
//    // TODO: 1/21/2022 Insert get Master Sync data into Local DB
//
//
//    // TODO: 1/21/2022 InsertValues into local db query
//
//    public void insertStateListDetailIntoLocalDBQuery(StatesTable statesTable) {
//        try {
//            insertStatesListDataIntoLocalDBQueryLiveData = appRepository.insertStateListDataIntoLocalDBRepository(statesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<StatesTable> getinsertStatesListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertStatesListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertClusterDTLListDetailIntoLocalDBQuery(ClusterDTLTable clusterDTLTable) {
//        try {
//            insertClusterDTLListDataIntoLocalDBQueryLiveData = appRepository.insertClusterDTLTableIntoLocalDBRepository(clusterDTLTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<ClusterDTLTable> getinsertClusterDTLListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertClusterDTLListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertDistricListDetailIntoLocalDBQuery(DistrictTable districtTable) {
//        try {
//            insertDistrictListDataIntoLocalLiveData = appRepository.insertDisListDataIntoLocalDBRepository(districtTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<DistrictTable> getinsertDistricListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertDistrictListDataIntoLocalLiveData;
//    }
//
//
//    public void insertMandalListDetailIntoLocalDBQuery(MandalTable mandalTable) {
//        try {
//            insertMandalListDataIntoLocalDBQueryLiveData = appRepository.insertMandalListDataIntoLocalDBRepository(mandalTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<MandalTable> getinsertMandalTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertMandalListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertCropListDetailIntoLocalDBQuery(CropListTable cropListTable) {
//        try {
//            insertCropListDataIntoLocalDBQueryLiveData = appRepository.insertCropListDataIntoLocalDBRepository(cropListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<CropListTable> getinsertCropListTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertCropListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertCropVarietyListDetailIntoLocalDBQuery(CropVarietyListTable cropListTable) {
//        try {
//            insertCropVarietyListDataIntoLocalDBQueryLiveData = appRepository.insertCropVarietyListDataIntoLocalDBRepository(cropListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<CropVarietyListTable> getinsertCropVarietyListTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertCropVarietyListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertVillageListDetailIntoLocalDBQuery(VillageTable villageTable) {
//        try {
//            insertVillageListDataIntoLocalDBQueryLiveData = appRepository.insertVillageListDataIntoLocalDBRepository(villageTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<VillageTable> getinsertVillageTableListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertVillageListDataIntoLocalDBQueryLiveData;
//    }
//

    public void insertFarmerIntoLocalDBQuery(AddFarmerTable divisionTable) {
        try {
            insertFarmerListIntoLocalDBQueryLiveData = appRepository.insertFarmerIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertFarmerIntoLocalDBQuery(List<AddFarmerTable> divisionTable) {
        try {
            farmerListFromLocalDBLiveData = appRepository.insertFarmerIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<AddFarmerTable> getInsertFarmerIntoLocalDBQueryLiveDataLocalDB() {
        return insertFarmerListIntoLocalDBQueryLiveData;
    }

    public void insertPlotIntoLocalDBQuery(AddPlotTable divisionTable) {
        try {
            insertPlotListIntoLocalDBQueryLiveData = appRepository.insertPLotIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD20IntoLocalDBQuery(AddD20Table divisionTable) {
        try {
            insertD20ListIntoLocalDBQueryLiveData = appRepository.insertD20IntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD30IntoLocalDBQuery(AddD30Table divisionTable) {
        try {
            insertD30ListIntoLocalDBQueryLiveData = appRepository.insertD30IntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD10IntoLocalDBQuery(AddD10Table divisionTable) {
        try {
            insertD10ListIntoLocalDBQueryLiveData = appRepository.insertD10IntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<AddPlotTable> getInsertPlotIntoLocalDBQueryLiveDataLocalDB() {
        return insertPlotListIntoLocalDBQueryLiveData;
    }

    public LiveData<AddD20Table> getInsertD20IntoLocalDBQueryLiveDataLocalDB() {
        return insertD20ListIntoLocalDBQueryLiveData;
    }

    public LiveData<AddD10Table> getInsertD10IntoLocalDBQueryLiveDataLocalDB() {
        return insertD10ListIntoLocalDBQueryLiveData;
    }

    public LiveData<AddD30Table> getInsertD30IntoLocalDBQueryLiveDataLocalDB() {
        return insertD30ListIntoLocalDBQueryLiveData;
    }

    public void insertPlotGeoIntoLocalDBQuery(AddGeoBoundriesTable divisionTable) {
        try {
            insertGeoBoundariesListIntoLocalDBQueryLiveData = appRepository.insertGeoBoundariesIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertUserSectionIntoLocalDBQuery(UserSectionTable divisionTable) {
        try {
            insertUserSectionTableIntoLocalDBQueryLiveData = appRepository.insertUserSectionTableIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD20FertilizerIntoLocalDBQuery(D20FertilizerTable divisionTable) {
        try {
            insertD20FertilizerIntoLocalDBQueryLiveData = appRepository.insertD20FertilizerIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertGrowthMonitoringIntoLocalDBQuery(AddGrowthMonitoringTable divisionTable) {
        try {
            insertGrowthMonitoringDBQueryLiveData = appRepository.insertGrowthMonitoringIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertGrowthMonitoringIntoLocalDBQuery(List<AddGrowthMonitoringTable> divisionTable) {
        try {
            insertGrowthMonitoringListDBQueryLiveData = appRepository.insertGrowthMonitoringIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD20WeedIntoLocalDBQuery(D20WeedTable divisionTable) {
        try {
            insertD20WeedIntoLocalDBQueryLiveData = appRepository.insertD20WeedIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void updateD20Weed(D20WeedTable divisionTable) {
//        try {
//            insertD20WeedIntoLocalDBQueryLiveData = appRepository.insertD20WeedIntoLocalDBQuery(divisionTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void insertD20DiseaseIntoLocalDBQuery(D20DiseaseTable divisionTable) {
        try {
            insertD20DiseaseIntoLocalDBQueryLiveData = appRepository.insertD20DiseaseIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD20PestIntoLocalDBQuery(D20PestTable divisionTable) {
        try {
            insertD20PestIntoLocalDBQueryLiveData = appRepository.insertD20PestIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertD20PestIntoLocalDBQuery(List<D20PestTable> divisionTable) {
        try {
            d20pestListDbLiveData = appRepository.insertD20PestIntoLocalDBQuery(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<AddGeoBoundriesTable> getInsertGeoBoundariesIntoLocalDBQueryLiveDataLocalDB() {
        return insertGeoBoundariesListIntoLocalDBQueryLiveData;
    }

    public void insertPlotOfferIntoLocalDBQuery(AddPlotOfferTable divisionTable) {
        try {
            insertPlotOfferListIntoLocalDBQueryLiveData = appRepository.insertPlotOfferIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<AddPlotOfferTable> getInsertPlotOfferIntoLocalDBQueryLiveDataLocalDB() {
        return insertPlotOfferListIntoLocalDBQueryLiveData;
    }

    public void insertPlantationIntoLocalDBQuery(AddPlantationTable divisionTable) {
        try {
            insertPlantationListIntoLocalDBQueryLiveData = appRepository.insertPlantationIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<AddPlantationTable> getInsertPlantationIntoLocalDBQueryLiveDataLocalDB() {
        return insertPlantationListIntoLocalDBQueryLiveData;
    }

    public void insertTrackingIntoLocalDBQuery(AddGeoBoundariesTrackingTable divisionTable) {
        try {
            insertGeoBoundariesTrackingListIntoLocalDBQueryLiveData = appRepository.insertTrackingIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<AddGeoBoundariesTrackingTable> getInsertTrackingIntoLocalDBQueryLiveDataLocalDB() {
        return insertGeoBoundariesTrackingListIntoLocalDBQueryLiveData;
    }

    public void insertDivisionIntoLocalDBQuery(DivisionTable divisionTable) {
        try {
            insertDivisionListIntoLocalDBQueryLiveData = appRepository.insertDivisionIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertDivisionListIntoLocalDBQuery(List<DivisionTable> divisionTable) {
        try {
            insertDivisionListQueryLiveData = appRepository.insertDivisionIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<DivisionTable> getInsertDivisionIntoLocalDBQueryLiveDataLocalDB() {
        return insertDivisionListIntoLocalDBQueryLiveData;
    }

    public void insertSectionIntoLocalDBQuery(SectionTable seasonTable) {
        try {
            insertSectionListIntoLocalDBQueryLiveData = appRepository.insertSectionIntoLocalDB(seasonTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertSectionIntoLocalDBQuery(List<SectionTable> seasonTable) {
        try {
            insertSectionListQueryLiveData = appRepository.insertSectionIntoLocalDB(seasonTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public LiveData<SectionTable> getInsertSectionIntoLocalDBQueryLiveDataLocalDB() {
        return insertSectionListIntoLocalDBQueryLiveData;
    }

    public void insertCircleIntoLocalDBQuery(CircleTable divisionTable) {
        try {
            insertCircleListIntoLocalDBQueryLiveData = appRepository.insertCircleIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertCircleIntoLocalDBQuery(List<CircleTable> divisionTable) {
        try {
            insertCircleListQueryLiveData = appRepository.insertCircleIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<CircleTable> getInsertCircleIntoLocalDBQueryLiveDataLocalDB() {
        return insertCircleListIntoLocalDBQueryLiveData;
    }

    public void insertReasonIntoLocalDBQuery(ResonForNotPlantingTable divisionTable) {
        try {
            insertReasonListIntoLocalDBQueryLiveData = appRepository.insertReasonIntoLocalDB(divisionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<ResonForNotPlantingTable> getInsertReasonIntoLocalDBQueryLiveDataLocalDB() {
        return insertReasonListIntoLocalDBQueryLiveData;
    }

    public void insertVillageIntoLocalDBQuery(VillageTable villageTable) {
        try {
            insertVillageListIntoLocalDBQueryLiveData = appRepository.insertVillageIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<VillageTable> getInsertVillageIntoLocalDBQueryLiveDataLocalDB() {
        return insertVillageListIntoLocalDBQueryLiveData;
    }

    public void insertCropIntoLocalDBQuery(CropTable villageTable) {
        try {
            insertCropListIntoLocalDBQueryLiveData = appRepository.insertCropIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertCropIntoLocalDBQuery(List<CropTable> villageTable) {
        try {
            cropListFromLocalDBLiveData = appRepository.insertCropIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public LiveData<CropTable> getInsertCropIntoLocalDBQueryLiveDataLocalDB() {
        return insertCropListIntoLocalDBQueryLiveData;
    }

    public void insertBankIntoLocalDBQuery(BankTable villageTable) {
        try {
            insertBankListIntoLocalDBQueryLiveData = appRepository.insertBankIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertBankIntoLocalDBQuery(List<BankTable> villageTable) {
        try {
            insertBankListQueryLiveData = appRepository.insertBankIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<BankTable> getInsertBankIntoLocalDBQueryLiveDataLocalDB() {
        return insertBankListIntoLocalDBQueryLiveData;
    }

    public void insertBranchIntoLocalDBQuery(BranchTable villageTable) {
        try {
            insertBranchListIntoLocalDBQueryLiveData = appRepository.insertBranchIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<BranchTable> getInsertBranchIntoLocalDBQueryLiveDataLocalDB() {
        return insertBranchListIntoLocalDBQueryLiveData;
    }

    public void insertDiseaseIntoLocalDBQuery(DiseaseTable villageTable) {
        try {
            insertDiseaseListIntoLocalDBQueryLiveData = appRepository.insertDiseaseIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<DiseaseTable> getInsertDiseaseIntoLocalDBQueryLiveDataLocalDB() {
        return insertDiseaseListIntoLocalDBQueryLiveData;
    }

    public void getDiseaseTableDetailsListFromLocalDB() {
        try {
            DiseaseListLiveData = appRepository.getDiseaseTableDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<List<DiseaseTable>> getDiseaseListLiveDataLocalDB() {
        return DiseaseListLiveData;
    }

    public void getFertilizerDetailsListFromLocalDB() {
        try {
            fertilizerListLiveData = appRepository.getFertilizerDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<List<FertilizerTable>> getFertilizerListLiveDataLocalDB() {
        return fertilizerListLiveData;
    }

    public void getPestTableDetailsListFromLocalDB() {
        try {
            pestsListLiveData = appRepository.getPestTableDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public LiveData<List<PestTable>> getPestListLiveDataLocalDB() {
//        return pestsListLiveData;
//    }

    public void getWeedDetailsListFromLocalDB() {
        try {
            weedListLiveData = appRepository.getWeedDetailsListFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<List<WeedTable>> getWeedListLiveDataLocalDB() {
        return weedListLiveData;
    }
    public LiveData<List<PestTable>> getPestListLiveDataLocalDB() {
        return pestsListLiveData;
    }

    public void insertDistrictIntoLocalDBQuery(DistrictTable villageTable) {
        try {
            insertDistrictListIntoLocalDBQueryLiveData = appRepository.insertDistrictIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<DistrictTable> getInsertDistrictIntoLocalDBQueryLiveDataLocalDB() {
        return insertDistrictListIntoLocalDBQueryLiveData;
    }

    public void insertFertilizerIntoLocalDBQuery(FertilizerTable villageTable) {
        try {
            insertFertilizerListIntoLocalDBQueryLiveData = appRepository.insertFertilizerIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<FertilizerTable> getInsertFertilizerIntoLocalDBQueryLiveDataLocalDB() {
        return insertFertilizerListIntoLocalDBQueryLiveData;
    }

    public void insertMandalIntoLocalDBQuery(MandalTable villageTable) {
        try {
            insertMandalListIntoLocalDBQueryLiveData = appRepository.insertMandalIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<MandalTable> getInsertMandalIntoLocalDBQueryLiveDataLocalDB() {
        return insertMandalListIntoLocalDBQueryLiveData;
    }

    public void insertStateIntoLocalDBQuery(StateTable villageTable) {
        try {
            insertStateListIntoLocalDBQueryLiveData = appRepository.insertStateIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<StateTable> getInsertStateIntoLocalDBQueryLiveDataLocalDB() {
        return insertStateListIntoLocalDBQueryLiveData;
    }

    public void insertUsersIntoLocalDBQuery(UsersTable villageTable) {
        try {
            insertUsersListIntoLocalDBQueryLiveData = appRepository.insertUserIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<UsersTable> getInsertUsersIntoLocalDBQueryLiveDataLocalDB() {
        return insertUsersListIntoLocalDBQueryLiveData;
    }

    public void insertParameterIntoLocalDBQuery(ParameterTable villageTable) {
        try {
            insertParameterListIntoLocalDBQueryLiveData = appRepository.insertParameterIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<ParameterTable> getInsertParameterIntoLocalDBQueryLiveDataLocalDB() {
        return insertParameterListIntoLocalDBQueryLiveData;
    }

    public void insertPestIntoLocalDBQuery(PestTable villageTable) {
        try {
            insertPestListIntoLocalDBQueryLiveData = appRepository.insertPestIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<PestTable> getInsertPestIntoLocalDBQueryLiveDataLocalDB() {
        return insertPestListIntoLocalDBQueryLiveData;
    }

    public void insertPlantTypeIntoLocalDBQuery(PlantTypeTable villageTable) {
        try {
            insertPlantTypeListIntoLocalDBQueryLiveData = appRepository.insertPlantTypeIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<PlantTypeTable> getInsertPlantTypeIntoLocalDBQueryLiveDataLocalDB() {
        return insertPlantTypeListIntoLocalDBQueryLiveData;
    }

    public void insertPlantSubTypeIntoLocalDBQuery(PlantSubTypeTable villageTable) {
        try {
            insertPlantSubTypeListIntoLocalDBQueryLiveData = appRepository.insertPlantSubTypeIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<PlantSubTypeTable> getInsertPlantSubTypeIntoLocalDBQueryLiveDataLocalDB() {
        return insertPlantSubTypeListIntoLocalDBQueryLiveData;
    }

    public void insertSampleSlabIntoLocalDBQuery(SampleSlabTable villageTable) {
        try {
            insertSampleSlabListIntoLocalDBQueryLiveData = appRepository.insertSampleSlabIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<SampleSlabTable> getInsertSampleSlabIntoLocalDBQueryLiveDataLocalDB() {
        return insertSampleSlabListIntoLocalDBQueryLiveData;
    }

    public void insertSeasonIntoLocalDBQuery(SeasonTable villageTable) {
        try {
            insertSeasonListIntoLocalDBQueryLiveData = appRepository.insertSeasonIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<SeasonTable> getInsertSeasonIntoLocalDBQueryLiveDataLocalDB() {
        return insertSeasonListIntoLocalDBQueryLiveData;
    }

    public void insertUOMIntoLocalDBQuery(UOMTable villageTable) {
        try {
            insertUOMListIntoLocalDBQueryLiveData = appRepository.insertUOMIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<UOMTable> getInsertUOMIntoLocalDBQueryLiveDataLocalDB() {
        return insertUOMListIntoLocalDBQueryLiveData;
    }

    public void insertCastIntoLocalDBQuery(CastTable villageTable) {
        try {
            insertCastListIntoLocalDBQueryLiveData = appRepository.insertCastIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<CastTable> getInsertCastIntoLocalDBQueryLiveDataLocalDB() {
        return insertCastListIntoLocalDBQueryLiveData;
    }

    public void insertLookupHdrIntoLocalDBQuery(LookupHDRTable villageTable) {
        try {
            insertLookupHDRTableListIntoLocalDBQueryLiveData = appRepository.insertLookupHdrIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<LookupHDRTable> getInsertLookupHdrIntoLocalDBQueryLiveDataLocalDB() {
        return insertLookupHDRTableListIntoLocalDBQueryLiveData;
    }

    public void insertLookupDtlIntoLocalDBQuery(LookupDtlTable villageTable) {
        try {
            insertLookupDtlTableListIntoLocalDBQueryLiveData = appRepository.insertLookupDtlIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<LookupDtlTable> getInsertLookupDtlTableIntoLocalDBQueryLiveDataLocalDB() {
        return insertLookupDtlTableListIntoLocalDBQueryLiveData;
    }

    public void insertPlotExistOnTableIntoLocalDBQuery(PlotExistOnTable villageTable) {
        try {
            insertPlotExistOnTableListIntoLocalDBQueryLiveData = appRepository.insertPlotExistOnIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<PlotExistOnTable> getInsertPlotExistOnTableIntoLocalDBQueryLiveDataLocalDB() {
        return insertPlotExistOnTableListIntoLocalDBQueryLiveData;
    }

    public void insertVarietyIntoLocalDBQuery(VarietyTable villageTable) {
        try {
            insertVarietyListIntoLocalDBQueryLiveData = appRepository.insertVarietyIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<VarietyTable> getInsertVarietyIntoLocalDBQueryLiveDataLocalDB() {
        return insertVarietyListIntoLocalDBQueryLiveData;
    }

    public void insertWarehouseIntoLocalDBQuery(WarehouseTable villageTable) {
        try {
            insertWarehouseListIntoLocalDBQueryLiveData = appRepository.insertWarehouseIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<WarehouseTable> getInsertWarehouseIntoLocalDBQueryLiveDataLocalDB() {
        return insertWarehouseListIntoLocalDBQueryLiveData;
    }

    public void insertWeedIntoLocalDBQuery(WeedTable villageTable) {
        try {
            insertWeedListIntoLocalDBQueryLiveData = appRepository.insertWeedIntoLocalDB(villageTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public LiveData<WeedTable> getInsertWeedIntoLocalDBQueryLiveDataLocalDB() {
        return insertWeedListIntoLocalDBQueryLiveData;
    }

    public void insertLog(String methodName, String message, String staffId, String screenNo, String screenName, String clientId, String loanType, String moduleType) {
        try {
//            appRepository.insertLog(methodName,message,staffId,screenNo,screenName,clientId,loanType,moduleType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    public void insertLogBookDropDownHDRDetailIntoLocalDBQuery(LogBookDropDownHDRTable logBookDropDownHDRTable) {
//        try {
//            insertLogBookHDRIntoLocalDBQueryLiveData = appRepository.insertLogBookDropDownHDRIntoLocalDBRepository(logBookDropDownHDRTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LogBookDropDownHDRTable> getinsertLogBookDropDownHDRTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertLogBookHDRIntoLocalDBQueryLiveData;
//    }
//
//
//
//    public void insertLookUpDropDownDataDetailIntoLocalDBQuery(LookUpDropDownDataTable lookUpDropDownDataTable) {
//        try {
//            insertLookUpDropDownDataIntoLocalDBQueryLiveData = appRepository.insertLookUpDropDownDataIntoLocalDBRepository(lookUpDropDownDataTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LookUpDropDownDataTable> getinsertLookUpDropDownDataTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertLookUpDropDownDataIntoLocalDBQueryLiveData;
//    }
//
//
//
//    // TODO: 1/21/2022 get values from local db
//    public void getClusterListFromLocalDB() {
//        try {
//            getClusterListFromLocalDBLiveData = appRepository.getClusterHDRTableDetailslistFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<ClusterHDRTable>> getClusterListDataFromLocalDBLiveData() {
//        return getClusterListFromLocalDBLiveData;
//    }
//
//
//    // TODO: 1/21/2022 get values from local db
//    public void getCropListFromLocalDb() {
//        try {
//            getCropListFromLocalDBLiveData = appRepository.getCropListTableFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<CropListTable>> getCropListTableDataFromLocalDBLiveData() {
//        return getCropListFromLocalDBLiveData;
//    }
//
//
//    // TODO: 1/21/2022 get values from local db
//    public void getCropVarietyTableListFromLocalDB(String cropId) {
//        try {
//            getCropVarietyListFromLocalDBLiveData = appRepository.getCropVarietyListDetailsFromLocalDB(cropId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/21/2022 get values from local db
//    public void getVillageTableListFromLocalDB(String clusterID) {
//        try {
//            getVillageListFromLocalDBLiveData = appRepository.getVillageTableDetailsFromLocalDB(clusterID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//    public void getVillageTableListFromLocalDBSingle() {
//        try {
//            getVillageListFromLocalDBLiveData = appRepository.getVillageListFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<VillageTable>> getVillageTableDataFromLocalDBLiveData() {
//        return getVillageListFromLocalDBLiveData;
//    }
//
//    public LiveData<List<CropVarietyListTable>> getCropVarietyTableDataFromLocalDBLiveData() {
//        return getCropVarietyListFromLocalDBLiveData;
//    }
//
//
//    public LiveData<String> getStringLiveData() {
//        return stringLiveData;
//    }
//
//    public LiveData<Integer> getIntegerLiveData() {
//        return integerLiveData;
//    }
//
//
//
//
//    // TODO: data code for log book module
//
//    public void getSeasonListForLogBookFromLocalDB() {
//        try {
//            getSeasonlistFromlocalDBLiveData = appRepository.getSeasonlistFromLocalDb();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<SeasonTable>> getSeasonlistFromlocalDBLiveData() {
//        return getSeasonlistFromlocalDBLiveData;
//    }
//
//
//
//    public void getLookUpDataFromLocalDBByType(String typeOfReq) {
//        try {
//            getLookUpDataListFromLocalDBLiveData = appRepository.getLookUpDropDownDataListFromLocalDB(typeOfReq);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<LookUpDropDownDataTable>> getLookUpDataListFromLocalDBLiveData() {
//        return getLookUpDataListFromLocalDBLiveData;
//    }
//
//
//    // TODO: 2/21/2022 log book module sync to server
//
//    public void getLogBookListFromLocalDBNotSync() {
//        try {
//            logBookListFromLocalDBLiveDataNotSync = appRepository.getAddLogBookDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddLogBookDetailsTable>> getAddLogBookDetailsListNotSyncLiveData() {
//        return logBookListFromLocalDBLiveDataNotSync;
//    }
//
//
//    // TODO: sync to server log book
//
//
//    public void syncLogBookDetailsDataToServer(AddLogBookDetailsTable addLogBookDetailsTable) {
//        try {
//            stringLiveData = appRepository.syncLogBookDetailsToServer(addLogBookDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 2/21/2022  Fertilizer part
//

    public void getSeasonListForLogBookFromLocalDB() {
        try {
            getSeasonlistFromlocalDBLiveData = appRepository.getSeasonlistFromLocalDb();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<List<SeasonTable>> getSeasonlistFromlocalDBLiveData() {
        return getSeasonlistFromlocalDBLiveData;
    }

    public void getFertlizerListFromLocalDBNotSync() {
        try {
            ferlizerListFromLocalDBLiveDataNotSync = appRepository.getFertilizerDetailsDetailslistFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotOfferListFromLocalDB(String farmerCode) {
        try {
            ferlizerListFromLocalDBLiveDataNotSync = appRepository.getPlotOfferListFromLocalDB(farmerCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20ListFromLocalDBNotSync() {
        try {
            d20ListFromLocalDBLiveDataNotSync = appRepository.getAddD20listFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD10ListFromLocalDBNotSync() {
        try {
            d10ListFromLocalDBLiveDataNotSync = appRepository.getAddD10listFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD10ListFromLocalDB() {
        try {
            d10ListFromLocalDBLiveDataNotSync = appRepository.getAddD10listFromLocalDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD30ListFromLocalDBNotSync() {
        try {
            d30ListFromLocalDBLiveDataNotSync = appRepository.getAddD30listFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD30ListFromLocalDBNotSync(String status,String farmerCode,String plotCode) {
        try {
            d30ListFromLocalDBLiveDataNotSync = appRepository.getAddD30listFromLocalDBNotSync(status,farmerCode,plotCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlantationListFromLocalDBNotSync() {
        try {
            plantationListFromLocalDBLiveDataNotSync = appRepository.getPlantationDetailslistFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getGeoboundariesListFromLocalDBNotSync() {
        try {
            plantationListFromLocalDBLiveDataNotSync = appRepository.getPlantationDetailslistFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<List<AddD10Table>> getD10TablesDetailsListNotSyncLiveData() {
        return d10ListFromLocalDBLiveDataNotSync;
    }

    public LiveData<List<AddD20Table>> getD20TablesDetailsListNotSyncLiveData() {
        return d20ListFromLocalDBLiveDataNotSync;
    }

    public LiveData<List<AddD30Table>> getD30TablesDetailsListNotSyncLiveData() {
        return d30ListFromLocalDBLiveDataNotSync;
    }

    public LiveData<List<D20DiseaseTable>> getD20DiseaseListNotSyncLiveData() {
        return d20DiseasesListDbLiveData;
    }

    public LiveData<List<D20WeedTable>> getD20WeedListNotSyncLiveData() {
        return d20WeedListDbLiveData;
    }

    public LiveData<List<D20FertilizerTable>> getD20FertilizersListNotSyncLiveData() {
        return d20FertilizersListDbLiveData;
    }

    public LiveData<List<D20PestTable>> getD20PestListNotSyncLiveData() {
        return d20pestListDbLiveData;
    }

    public LiveData<List<AddPlotOfferTable>> getFertilizerDetailsTableDetailsListNotSyncLiveData() {
        return ferlizerListFromLocalDBLiveDataNotSync;
    }

    public LiveData<List<AddPlantationTable>> getPlantationDetailsListNotSyncLiveData() {
        return plantationListFromLocalDBLiveDataNotSync;
    }

    public void getD10Data(String plotNo,String seasonCode) {
        try {
            insertD10ListIntoLocalDBQueryLiveData = appRepository.getD10Data(plotNo,seasonCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD20Data(String plotNo,String seasonCode) {
        try {
            insertD20ListIntoLocalDBQueryLiveData = appRepository.getD20Data(plotNo,seasonCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD30Data(String plotNo,String seasonCode) {
        try {
            insertD30ListIntoLocalDBQueryLiveData = appRepository.getD30Data(plotNo,seasonCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getTrackingListFromLocalDBNotSync() {
        try {
            trackingListFromLocalDBLiveDataNotSync = appRepository.getTrackingDetailslistFromLocalDBNotSync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LiveData<List<AddGeoBoundariesTrackingTable>> getTrackingDetailsTableDetailsListNotSyncLiveData() {
        return trackingListFromLocalDBLiveDataNotSync;
    }


    public void syncFertilizerDetailsDataToServer(AddPlotOfferTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncAddFertilizerDetailsToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD20DetailsDataToServer(AddD20Table addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD20DetailsToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD10DetailsDataToServer(AddD10Table addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD10DetailsToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD30DetailsDataToServer(AddD30Table addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD30DetailsToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD20DiseaseToServer(D20DiseaseTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD20DiseaseToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD20WeedToServer(D20WeedTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD20WeedToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD20FertilizerToServer(D20FertilizerTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD20FertilizerToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncD20PestToServer(D20PestTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncD20PestToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncTrackingDetailsDataToServer(AddGeoBoundariesTrackingTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncTrackingDetailsToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void syncGeoBoundariesDetailsDataToServer(AddGeoBoundriesTable addFertilizerDetailsTable) {
        try {
            stringLiveData = appRepository.syncGeoBoundariesDetailsToServer(addFertilizerDetailsTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public LiveData<String> getStringLiveData() {
        return stringLiveData;
    }

//
//    // TODO: organic Data part
//
////    public void getOrganicListFromLocalDBNotSync() {
////        try {
////            organicListFromLocalDBLiveDataNotSync = appRepository.getOrganicDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddOrganicDetailsTable>> getAddOrganicDetailsListNotSyncLiveData() {
////        return organicListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncOrganicDetailsDataToServer(AddOrganicDetailsTable addOrganicDetailsTable) {
////        try {
////            stringLiveData = appRepository.syncAddOrganicDetailsToServer(addOrganicDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: Water regime season
////
////    public void getWaterRegimeSeasonListFromLocalDBNotSync() {
////        try {
////            waterRegimeSeasonListFromLocalDBLiveDataNotSync = appRepository.getWaterRegimeSeasonDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddWaterRegimeSeasonDetailsTable>> getAddWaterRegimeSeasonDetailsTableDetailsListNotSyncLiveData() {
////        return waterRegimeSeasonListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncWaterRegimeSeasonDetailsTableDetailsDataToServer(AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable) {
////        try {
////            stringLiveData = appRepository.syncAddWaterRegimeSeasonDetailsToServer(addWaterRegimeSeasonDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: water pre season
////    public void getWaterPreSeasonListFromLocalDBNotSync() {
////        try {
////            waterpreSeasonFromLocalDBLiveDataNotSync = appRepository.getWaterPreSeasonDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddWaterReasonPreSeasonTable>> getAddWaterReasonPreSeasonLogBookDetailsListNotSyncLiveData() {
////        return waterpreSeasonFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncWaterPreSeasonDetailsDataToServer(AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable) {
////        try {
////            stringLiveData = appRepository.syncAddWaterReasonPreSeasonDetailsToServer(addWaterReasonPreSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: 2/21/2022 borewell pump part
////
////    public void getBorewellPumpListFromLocalDBNotSync() {
////        try {
////            boreWellPumpListFromLocalDBLiveDataNotSync = appRepository.getBoreWellPumpSeasonDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddBoreWellSeasonTable>> getAddBoreWellPumpSeasonDetailsListNotSyncLiveData() {
////        return boreWellPumpListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncBoreWellPumpDetailsDataToServer(AddBoreWellSeasonTable addBoreWellSeasonTable) {
////        try {
////            stringLiveData = appRepository.syncAddBoreWellPumpSeasonDetailsToServer(addBoreWellSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: 2/21/2022 water feild part
////    public void getWaterFieldListFromLocalDBNotSync() {
////        try {
////            waterSeasonFieldListFromLocalDBLiveDataNotSync = appRepository.getWaterSeasonFieldDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddWaterSeasonFeildTable>> getAddWaterSeasonFeildTableListNotSyncLiveData() {
////        return waterSeasonFieldListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncAddWaterSeasonFieldDetailsDataToServer(AddWaterSeasonFeildTable addWaterSeasonFeildTable) {
////        try {
////            stringLiveData = appRepository.syncAddWaterSeasonFeildTableDetailsToServer(addWaterSeasonFeildTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//
//    // TODO: 2/21/2022 harvest part
//    public void getHarVestListFromLocalDBNotSync() {
//        try {
//            harvestListFromLocalDBLiveDataNotSync = appRepository.getHarvestDetailsDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddHarvestDetailsTable>> getAddHarvestDetailsListNotSyncLiveData() {
//        return harvestListFromLocalDBLiveDataNotSync;
//    }
//
//
//    public void syncAddHarvestDetailsDataToServer(AddHarvestDetailsTable addHarvestDetailsTable) {
//        try {
//            stringLiveData = appRepository.syncAddHarvestDetailsToServer(addHarvestDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void insertLog(String methodName, String message, String staffId, String screenNo, String screenName, String clientId, String loanType, String moduleType) {
//        try {
////            appRepository.insertLog(methodName,message,staffId,screenNo,screenName,clientId,loanType,moduleType);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<Integer> getCountComplaintDetails() {
//        return appRepository.getCountComplaintDetailCount();
//    }
//
//    public void insertComplainImagesToServer(List<SavingComplainImagesTable> savingComplainImagesTableList, String logbookno) {
//        try {
//            savingComplainImagesTableLiveData = appRepository.insertSavingOfComplainMultipleImages(savingComplainImagesTableList,logbookno);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public void insertAddComplainFormTableLocalDb(AddComplaintsDetailsTable addComplaintsDetailsTable) {
//
//        try {
//            addComplaintsDetailsTableLiveData = appRepository.insertAddComplainFormTable(addComplaintsDetailsTable);
//            getAddComplainFormDetailsFromLocalDBLiveData = appRepository.getAddComplainFormTable();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public LiveData<AddComplaintsDetailsTable> getAddComplainformTableLiveDataFromLocalDB() {
//        return addComplaintsDetailsTableLiveData;
//    }
//
//    public LiveData<List<AddComplaintsDetailsTable>> getAddComplainformTableLiveDataFromLocalDBList() {
//        return appRepository.getAddComplainFormTable();
//    }
//
//
//    public LiveData<List<SavingComplainImagesTable>> getSavingComplainImagesNotSyncLiveDataList() {
//        return appRepository.getComplainImagesFromLocalDBNotSync();
//    }
//}
//
//
//    // TODO: sync to server methods
//    public void syncFormerDetailsDataToServer(FarmerDetailListTable farmerDetailListTable){
//        try{
//            stringLiveData=appRepository.syncFormerDetailsDataToServer(farmerDetailListTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncLandDetailsDataToServer(LandDetailsListTable landDetailsListTable){
//        try{
//            stringLiveData=appRepository.syncLandDetailsDataToServer(landDetailsListTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentifcationDetailsDataToServer(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable){
//        try{
//            stringLiveData=appRepository.syncDocIdentificationDetailsDataToServer(docIdentiFicationDeatilsTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncBankDetailsSubmitTableDataToServer(BankDetailsSubmitTable bankDetailsSubmitTable){
//        try{
//            stringLiveData=appRepository.syncBankDetailsDataToServer(bankDetailsSubmitTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentificationDetailsDataToServer(String codeUser,String strPicUrl ,String strFileExtension,String typeOfReq,String identityCode ){
//        try{
//            stringLiveData=appRepository.syncDocIdentificationDetailsToServer(codeUser,strPicUrl,strFileExtension,typeOfReq,identityCode);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
}

//    private AppRepository appRepository;
//    private LiveData<List<LoginResponseDTO>> loginResponseDTOFromServerLiveData;
//    private LiveData<String> stringLiveData;
//    private LiveData<Integer> integerLiveData;
//    private LiveData<List<String>> stringListLiveData;
//    private LiveData<String> successStr;
//    private LiveData<FarmerTable> farmerDetailListTableLiveData;
//
//    private LiveData<FarmerTable> farmerDetailListTableLiveDataInsert;
//    private LiveData<DocIdentiFicationDeatilsTable> documentSavingDataLocalDB;
//    private LiveData<BankDetailsSubmitTable> bankDetailsSubmitTableLiveData;
//    private LiveData<DocIdentiFicationDeatilsTable> docIdentiFicationDeatilsTableLiveData;
//    private LiveData<CurrentVisitFarmerTables> currentVisitFarmerTablesLiveData;
//    private LiveData<PlotDetailsListTable> plotDetailListTableLiveData;
//    private LiveData<List<PinCodeDetailsDataTable>> bypincodestatevillagelistitemLiveData;
//
//    private LiveData<List<PinCodeDetailsResponseDTO>> pincodeDetailsResponseFromServerLiveData;
//    private LiveData<List<CropDetailsResponseFromServerDTO>> cropDetailsResponseFromServerLiveData;
//
//    private LiveData<List<PlotNumberDataResponseDTO>> syncDataResponseDTOLiveData;
//
//    private LiveData<List<FarmerTable>> farmerListFromLocalDBLiveData;
//    private LiveData<List<FarmerTable>> farmerListFromLocalDBLiveDataNotSync;
//    private LiveData<List<PlotDetailsListTable>> landdetailsListFromLocalDBLiveData;
//
//    private LiveData<List<VillageDetailsResponseDTO>> villageDetailsResponseFromServerLiveData;
//
//
//    private LiveData<List<PlotDetailsListTable>> landDetailsLocalDbDataLiveData;
//    private LiveData<List<DocIdentiFicationDeatilsTable>> docIdentificationDetailsSubmitTableLiveData;
//    private LiveData<List<BankDetailsSubmitTable>> bankDetailsSubmitTableFromLocalDbLiveData;
//
//    private LiveData<List<BankDetailsSubmitTable>> bankDetailsFromLocalDBByfarmerCode;
//    private LiveData<List<DocIdentiFicationDeatilsTable>> docIdentificationDetailsByFarmerCode;
//
//    private LiveData<List<SavingComplainImagesTable>> savingComplainImagesTableLiveData;
//    // TODO: Adding Response live data for cluster process
//    private LiveData<List<StateListResponseDTO>> stateDetailsResponseFromServerLiveData;
//    private LiveData<List<DistricDetailsResponseDTO>> districDetailsResponseFromServerLiveData;
//    private LiveData<List<VillageByMandalIdDetailsResponseDTO>> villageDetailsByMandalResponseFromServerLiveData;
//    private LiveData<List<MandalDetailsResponseDTO>> mandalDetailsResponseFromServerLiveData;
//    private LiveData<List<ClusterDetailsResponseDTO>> clusterDetailsResponseFromServerLiveData;
//    private LiveData<List<SyncResponseDTO>> syncResponseDTOFromServerLiveData;
//    private LiveData<GeoBoundariesTable> geoBoundariesTableLocalDbInsertLiveData;
//    private LiveData<List<GeoBoundariesTable>> geoBoundariesTableFromLocalDbLiveData;
//
//
//    private LiveData<AddLandPreparationTable> getLandPreparationFeildDetailsTableLiveDataByDate;
//    private LiveData<AddGeoBoundriesTable> getGeoBoundriesFeildDetailsTableLiveDataByDate;
//    private LiveData<AddFertilizerDetailsTable> getFertilizerFeildDetailsTableLiveDataByDate;
//    private LiveData<AddTransplantingTable> getTransplantingFeildDetailsTableLiveDataByDate;
//    private LiveData<AddWaterManagementTable> getWaterManagementFeildDetailsTableLiveDataByDate;
//    private LiveData<AddWeedManagementTable> getWeedManagementFeildDetailsTableLiveDataByDate;
//    private LiveData<AddYieldTable> getYieldFeildDetailsTableLiveDataByDate;
//    private LiveData<AddMoistureContentTable> getMoistureContentFeildDetailsTableLiveDataByDate;
//    private LiveData<AddNurseryPreparationTable> getNurseryPreparationFeildDetailsTableLiveDataByDate;
//    private LiveData<AddSeedRateTable> getSeedRateFeildDetailsTableLiveDataByDate;
//    private LiveData<AddHarvestingTable> getHarvestingFeildDetailsTableLiveDataByDate;
//    private LiveData<AddPestDiseaseControlTable> getPestDiseasecontrolFeildDetailsTableLiveDataByDate;
//
//
//    private LiveData<List<GeoBoundariesTable>>  listentryValuesGeoBoundaries;
//    // TODO: 1/21/2022 Local Save
//
//
//    // TODO: Adding Response live data for cluster process
//    private LiveData<List<StatesTable>> stateListSavedIntoLocalDBFromServer;
//    private LiveData<List<DistrictTable>> districListSavedIntoLocalDBFromServer;
//    private LiveData<List<MandalTable>> mandalListSavedIntoLocalDBFromServer;
//    private LiveData<List<CropListTable>> cropListSavedIntoLocalDBFromServer;
//    private LiveData<List<VillageTable>> villageListSavedIntoLocalDBFromServer;
//    private LiveData<List<ClusterHDRTable>> clusterHDRlistSavedIntoLocalDBFromServer;
//
//    private LiveData<List<ClusterDTLTable>> clusterdtlListSavedIntoLocalDBFromServer;
//    private LiveData<List<PinCodeDetailsListTable>> pinCodeDetailsListSavedIntoLocalDBFromServer;
//    private LiveData<List<VillageDetailsByPinCodListTable>> pincodevillageDetailsListSavedIntoLocalDBFromServer;
//
//
//    // TODO: Adding server details into local db
//
//    private LiveData<StatesTable> insertStatesListDataIntoLocalDBQueryLiveData;
//    //  private LiveData<DistrictTable> insertDistrictListDataIntoLocalDBQueryLiveData;
//    private LiveData<DistrictTable> insertDistrictListDataIntoLocalLiveData;
//    private LiveData<MandalTable> insertMandalListDataIntoLocalDBQueryLiveData;
//    private LiveData<CropListTable> insertCropListDataIntoLocalDBQueryLiveData;
//    private LiveData<CropVarietyListTable> insertCropVarietyListDataIntoLocalDBQueryLiveData;
//    private LiveData<VillageTable> insertVillageListDataIntoLocalDBQueryLiveData;
//    private LiveData<ClusterHDRTable> insertClusterHDRListDataIntoLocalDBQueryLiveData;
//    private LiveData<ClusterDTLTable> insertClusterDTLListDataIntoLocalDBQueryLiveData;
//
//    // TODO: getting values from local DB
//    private LiveData<List<StatesTable>> getStateListFromLocalDBLiveData;
//    private LiveData<List<DistrictTable>> getDistricListFromLocalDBLiveData;
//    private LiveData<List<MandalTable>> getMandalListFromLocalDBLiveData;
//    private LiveData<List<CropListTable>> getCropListFromLocalDBLiveData;
//    private LiveData<List<CropVarietyListTable>> getCropVarietyListFromLocalDBLiveData;
//    private LiveData<List<VillageTable>> getVillageListFromLocalDBLiveData;
//    private LiveData<List<ClusterHDRTable>> getClusterListFromLocalDBLiveData;
//    private LiveData<ClusterDTLTable> getClusterDTlFromLocalDBLiveData;
//
//
//    private LiveData<List<GetMasterPersonalLandAllDetailsRequestDTO>> getMasterSyncDataFromServerLiveData;
//
//
//    // TODO: 1/26/2022 For handling auto generated pincode and address details
//    private LiveData<List<VillageTable>> villageDetailsByPincode;
//
//    private LiveData<VillageTable> getVillageDetailsFromLocalDBByVillageID;
//    private LiveData<MandalTable> getDeatailsFromMandalTableLocalDbLiveData;
//    private LiveData<DistrictTable> getDistrictDetailsFromLocalDB;
//    private LiveData<StatesTable> getStateDetailsFromLocalDB;
//    private LiveData<CropListTable> getCropDetailsFromLocalDBByCropID;
//    private LiveData<CropVarietyListTable> getCropVarietyDetailsFromLocalDBByCropID;
//
//    private LiveData<PlotDetailsListTable> singleplotDetailsFromLocalDBLiveData;
//
//
//    private LiveData<SavingFarmerProfieImagesTable> savingFarmerProfieImagesTableLiveData;
//    private LiveData<List<SavingFarmerProfieImagesTable>> savingFarmerProfileImagesNotSyncLiveData;
//
//
//
//    private LiveData<SavingPlotProfieImagesTable> savingPlotImagesTableLiveData;
//    private LiveData<List<SavingPlotProfieImagesTable>> savingPlotProfileImagesNotSyncLiveData;
//
//
//
//    private LiveData<SavingBankImagesTable> savingBankImagesTableLiveData;
//    private LiveData<List<SavingBankImagesTable>> savingBankProfileImagesNotSyncLiveData;
//
//    private LiveData<GeoBoundariesTable> geoBoundariesTableLiveDataInsert;
//    private LiveData<List<GeoBoundariesTable>> savingGeoBoundariesTableListData;
//    // TODO: 2/15/2022 log book data
//
//    private LiveData<SeasonTable> insertSeasonListDataIntoLocalDBQueryLiveData;
//    private LiveData<LogBookDropDownHDRTable> insertLogBookHDRIntoLocalDBQueryLiveData;
//    private LiveData<LookUpDropDownDataTable> insertLookUpDropDownDataIntoLocalDBQueryLiveData;
//    private LiveData<List<SeasonTable>> getSeasonlistFromlocalDBLiveData;
//    private LiveData<List<LookUpDropDownDataTable>> getLookUpDataListFromLocalDBLiveData;
//
//    // TODO: 2/15/2022 db saving part for log book
//    private LiveData<AddLogBookDetailsTable> addLogBookDetailsTableLiveData;
//    private LiveData<List<AddLogBookDetailsTable>> logbookListLocalDbDataLiveData;
//    private LiveData<AddLogBookDetailsTable> upDateaddLogBookDetailsTableLiveData;
//    private LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableLiveDataByDate;
//
//    private LiveData<AddFertilizerDetailsTable> addFertilizerDetailsTableLiveData;
//    private LiveData<List<AddFertilizerDetailsTable>> fertilizerlistFromLocalDBLiveData;
//    private LiveData<AddFertilizerDetailsTable> getFertilizerDetailsTableLiveDataByDate;
//
//    private LiveData<LogBookModulesStatusDetailsTable> getLogBookModulesStatusDetailsFromLocalDBByLogBookID;
//    private LiveData<LogBookModulesStatusDetailsTable> savingLogBookModulesStatusDetailsTable;
//
//
//
////    private LiveData<AddOrganicDetailsTable> addOrganicDetailsTableLiveData;
////    private LiveData<List<AddOrganicDetailsTable>> orgaincAmendsDatalistFromLocalDBLiveData;
//
//
////    private LiveData<AddWaterSeasonFeildTable> addWaterSeasonFeildTableLiveData;
////    private LiveData<AddWaterSeasonFeildTable> getWaterSeasonFieldDetailsFromLocalDBLiveData;
//
//
//    private LiveData<AddLandPreparationTable> addLandPreparationTableLiveData;
//    private LiveData<List<AddLandPreparationTable>> getLandPreparationDetailsFromLocalDBLiveData;
//
//    private LiveData<AddPestDiseaseControlTable> addPestDiseaseTableLiveData;
//    private LiveData<List<AddPestDiseaseControlTable>> getPestDiseasecontrolDetailsFromLocalDBLiveData;
//
//    private LiveData<AddHarvestingTable> addharvestingTableLiveData;
//    private LiveData<List<AddHarvestingTable>> getharvestingDetailsFromLocalDBLiveData;
//
//    private LiveData<AddSeedRateTable> addSeedRateTableLiveData;
//    private LiveData<List<AddSeedRateTable>> getSeedrateDetailsFromLocalDBLiveData;
//
//    private LiveData<AddNurseryPreparationTable> addNurseryPreparationTableLiveData;
//    private LiveData<List<AddNurseryPreparationTable>> getNurseryPreparationDetailsFromLocalDBLiveData;
//
//    private LiveData<AddMoistureContentTable> addMoistureContentTableLiveData;
//    private LiveData<List<AddMoistureContentTable>> getMoistureContentDetailsFromLocalDBLiveData;
//
//    private LiveData<AddYieldTable> addYieldTableLiveData;
//    private LiveData<List<AddYieldTable>> getYieldDetailsFromLocalDBLiveData;
//
//    private LiveData<AddWeedManagementTable> addWeedManagementTableLiveData;
//    private LiveData<List<AddWeedManagementTable>> getWeedmanagementDetailsFromLocalDBLiveData;
//
//    private LiveData<AddWaterManagementTable> addWaterManagementTableLiveData;
//    private LiveData<List<AddWaterManagementTable>> getWatermanagementDetailsFromLocalDBLiveData;
//
//    private LiveData<AddTransplantingTable> addTransplantingTableLiveData;
//    private LiveData<List<AddTransplantingTable>> getTransplantingDetailsFromLocalDBLiveData;
//
//    private LiveData<AddFertilizerDetailsTable> addFertilizationTableLiveData;
//    private LiveData<List<AddFertilizerDetailsTable>> getFertilizerDetailsFromLocalDBLiveData;
//
//
//    private LiveData<AddGeoBoundriesTable> addGeoBoundriesTableLiveData;
//    private LiveData<List<AddGeoBoundriesTable>> getGeoBoundriesDetailsFromLocalDBLiveData;
//
//    private LiveData<AddHarvestDetailsTable> addHarvestDetailsTableLiveData;
//    private LiveData<AddHarvestDetailsTable> getAddHarvestDetailsFromLocalDBLiveData;
//
//
////    private LiveData<AddBoreWellSeasonTable> addBoreWellPumpSeasonTableLiveData;
////    private LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData;
//
//
////    private LiveData<AddWaterReasonPreSeasonTable> addWaterReasonPreSeasonTableLiveData;
////    private LiveData<AddWaterReasonPreSeasonTable> getWaterReasonPreSeasonDetailsFromLocalDBLiveData;
//
//
////    private LiveData<AddWaterRegimeSeasonDetailsTable> addWaterRegimeSeasonDetailsTableLiveData;
////    private LiveData<AddWaterRegimeSeasonDetailsTable> getWaterRegimeSeasonDetailsFromLocalDBLiveData;
//
//
//
//    private LiveData<List<AddLogBookDetailsTable>> logBookListFromLocalDBLiveDataNotSync;
//
//
//    private LiveData<List<AddFertilizerDetailsTable>> ferlizerListFromLocalDBLiveDataNotSync;
//
////    private LiveData<List<AddOrganicDetailsTable>> organicListFromLocalDBLiveDataNotSync;
//
////    private LiveData<List<AddWaterRegimeSeasonDetailsTable>> waterRegimeSeasonListFromLocalDBLiveDataNotSync;
//
////    private LiveData<List<AddWaterReasonPreSeasonTable>> waterpreSeasonFromLocalDBLiveDataNotSync;
////
////    private LiveData<List<AddBoreWellSeasonTable>> boreWellPumpListFromLocalDBLiveDataNotSync;
////
////
////    private LiveData<List<AddWaterSeasonFeildTable>> waterSeasonFieldListFromLocalDBLiveDataNotSync;
//
//    private LiveData<List<AddHarvestDetailsTable>> harvestListFromLocalDBLiveDataNotSync;
//
//    private LiveData<LogBookDropDownHDRTable> getLogBookDropDownHDRTableDetailsFromLocalDBLiveData;
//
//
//    private LiveData<LookUpDropDownDataTable> getLookUpSelectionNameLookUpId;
//    private LiveData<SeasonTable> getSeasonNameBySeasonID;
//
//
//    private LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDetailsFromLocalDBByFarmerCode;
//
//    private LiveData<RefreshTableDateCheck> addRefreshTableDateCheckLiveData;
//    private LiveData<RefreshTableDateCheck> getAddRefreshTableDateCheck;
//
//
//    @Inject
//    public AppViewModel(AppRepository appRepository) {
//        this.appRepository = appRepository;
//    }
//
//
//    public void logInServiceList(String userId) {
//        try {
//            loginResponseDTOFromServerLiveData = appRepository.getlogInServiceResponse(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getPincodeResponseDeatilsFromServer(String userId) {
//        try {
//            pincodeDetailsResponseFromServerLiveData = appRepository.getPincodeDetailsFromServer(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: state details from server
//    public void getStateDetailsFromServer() {
//        try {
//            stateDetailsResponseFromServerLiveData = appRepository.getStateDetailsResponseDTOServer();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: distric details from server
//    public void getDistricDetailsFromServer(String stateID) {
//        try {
//            districDetailsResponseFromServerLiveData = appRepository.getDistricDetailsResponseDTOServer(stateID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: distric details from server
//    public void getMandalDetailsFromServer(String districID) {
//        try {
//            mandalDetailsResponseFromServerLiveData = appRepository.getMandalDetailsResponseDTOServer(districID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: distric details from server
//    public void getVillageIdFromServerByMandalId(String mandalID) {
//        try {
//            villageDetailsByMandalResponseFromServerLiveData = appRepository.getVillageDeatilsByMandalIdFromServer(mandalID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: distric details from server
//    public void getClusertDetailsFromserver(String mandalID) {
//        try {
//            clusterDetailsResponseFromServerLiveData = appRepository.getCluserDetailsFromServer(mandalID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//
//    public void getvillageDetailsResponseFromServer(String userId) {
//        try {
//            villageDetailsResponseFromServerLiveData = appRepository.getVillageDetailsFromServer(userId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertOrUpdateFarmerDetailListTable(FarmerTable farmerTable) {
//        try {
//            farmerDetailListTableLiveData = appRepository.insertOrUpdateFarmerDetailListTableTable(farmerTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
////    public void insertFarmerDetailListTable(FarmerDetailListTable farmerDetailListTable) {
////        try {
////            farmerDetailListTableLiveDataInsert = appRepository.insertOrUpdateFarmerDetailListTableTable(farmerDetailListTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public void insertFarmerDetailListTableLocal(FarmerTable farmerTable) {
//        try {
//            farmerDetailListTableLiveDataInsert = appRepository.insertFarmerDetailListTableTable(farmerTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertDoctable(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        try {
//            documentSavingDataLocalDB = appRepository.insertDocIntoLocalDB(docIdentiFicationDeatilsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertOrUpdateBankDetailsSubmitTableLocalDb(BankDetailsSubmitTable bankDetailsSubmitTable) {
//        try {
//            bankDetailsSubmitTableLiveData = appRepository.insertOrUpdateBankDetailsSubmitTable(bankDetailsSubmitTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertFarmerProfileImages(SavingFarmerProfieImagesTable savingFarmerProfieImagesTable) {
//        try {
//            savingFarmerProfieImagesTableLiveData = appRepository.insertSavingOfFarmerMultipleImages(savingFarmerProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertPlotPictureImagesToServer(SavingPlotProfieImagesTable savingPlotProfieImagesTable) {
//        try {
//            savingPlotImagesTableLiveData = appRepository.insertSavingOfPlotMultipleImages(savingPlotProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertBankPictureImagesToServer(SavingBankImagesTable savingBankImagesTable) {
//        try {
//            savingBankImagesTableLiveData = appRepository.insertSavingOfBankMultipleImages(savingBankImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertGeoBoundariesvaluesIntolocalDB(GeoBoundariesTable geoBoundariesTable) {
//        try {
//            geoBoundariesTableLiveDataInsert = appRepository.insertGoeDataIntolocaDB(geoBoundariesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void insertGeoBoundariesListvaluesIntolocalDB(List<GeoBoundariesTable> geoBoundariesTableList) {
//        try {
//            savingGeoBoundariesTableListData = appRepository.insertGoeBoundariesListDataIntolocaDB(geoBoundariesTableList);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void insertLogBookModulesStatusDetailsTable(LogBookModulesStatusDetailsTable logBookModulesStatusDetailsTable) {
//        try {
//            savingLogBookModulesStatusDetailsTable = appRepository.insertSavingOfLogBookModulesStatusDetailsTable(logBookModulesStatusDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/7/2022 saving documnets files local db
//    public void insertOrUpdateDocUploadTable(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        try {
//            docIdentiFicationDeatilsTableLiveData = appRepository.insertOrUpdateDocIdentificationDetailListTable(docIdentiFicationDeatilsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 1/7/2022 saving formar details local db
//    public void insertOrUpdateCurrentVisitFarmerTables(CurrentVisitFarmerTables currentVisitFarmerTables) {
//        try {
//            currentVisitFarmerTablesLiveData = appRepository.insertOrUpdateCurrentVisitFarmerTablesTable(currentVisitFarmerTables);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: sync to server methods
//    public void syncFormerDetailsDataToServer(FarmerTable farmerTable) {
//        try {
//            stringLiveData = appRepository.syncFormerDetailsDataToServer(farmerTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    // TODO: sync to server methods
//    public void syncFormerProfileDetailsToserver(SavingFarmerProfieImagesTable savingFarmerProfieImagesTable) {
//        try {
//            stringLiveData = appRepository.syncFarmerProfileImagesDetaisToServer(savingFarmerProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncPlotProfileDetailsToserver(SavingPlotProfieImagesTable savingPlotProfieImagesTable) {
//        try {
//            stringLiveData = appRepository.syncPlotProfileImagesDetaisToServer(savingPlotProfieImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void syncBankProfileDetailsToserver(SavingBankImagesTable savingBankImagesTable) {
//        try {
//            stringLiveData = appRepository.syncBankProfileImagesDetaisToServer(savingBankImagesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//    public void syncLandDetailsDataToServer(PlotDetailsListTable plotDetailsListTable) {
//        try {
//            stringLiveData = appRepository.syncLandDetailsDataToServer(plotDetailsListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentifcationDetailsDataToServer(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        try {
//            stringLiveData = appRepository.syncDocIdentificationDetailsDataToServer(docIdentiFicationDeatilsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncBankDetailsSubmitTableDataToServer(BankDetailsSubmitTable bankDetailsSubmitTable) {
//        try {
//            stringLiveData = appRepository.syncBankDetailsDataToServer(bankDetailsSubmitTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentificationDetailsDataToServer(String codeUser, String strPicUrl, String strFileExtension, String typeOfReq, String identityCode,String createdDate,String upDatedDate) {
//        try {
//            stringLiveData = appRepository.syncDocIdentificationDetailsToServer(codeUser, strPicUrl, strFileExtension, typeOfReq, identityCode,createdDate,upDatedDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncGeoBoundariesDetailsSubmitTableDataToServer(GeoBoundariesTable geoBoundariesTable) {
//        try {
//            stringLiveData = appRepository.syncGeoBoundariesDetailsDataToServer(geoBoundariesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 1/7/2022 saving plot details into local db
//    public void insertOrUpdatPlotDetailListTable(PlotDetailsListTable plotDetailsListTable) {
//        try {
//            plotDetailListTableLiveData = appRepository.insertOrUpdateinsertOrUpdatePlotDetails(plotDetailsListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/7/2022 saving plot details into local db
//    public void insertGeoBoundariesTable(GeoBoundariesTable geoBoundariesTable) {
//        try {
//            geoBoundariesTableLocalDbInsertLiveData = appRepository.insertGeoBoundariesValuesIntoLocalDb(geoBoundariesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getFarmerListFromLocalDB() {
//        try {
//            farmerListFromLocalDBLiveDataNotSync = appRepository.getFarmerDetailslistFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getFarmerListFromLocalDBStatus() {
//        try {
//            farmerListFromLocalDBLiveData = appRepository.getFarmerDetailslistFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
////    public void getNumberCountFarmer()
////    {
////        try{
////
////        }catch (Exception e)
////        {
////            e.printStackTrace();
////        }
////    }
//
//    public void getCountNumberFromFarmer() {
//        try {
//            integerLiveData = appRepository.getFarmerDetailslistCountFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getFarmerListFromLocalDBNotSync() {
//        try {
//            farmerListFromLocalDBLiveDataNotSync = appRepository.getFarmerDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void getFarmerProfileImagesLocalDB() {
//        try {
//            savingFarmerProfileImagesNotSyncLiveData = appRepository.getProfileFarmerImagesFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getPlotProfileImagesLocalDB() {
//        try {
//            savingPlotProfileImagesNotSyncLiveData = appRepository.getProfilePlotImagesFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getBankProfileImagesLocalDB() {
//        try {
//            savingBankProfileImagesNotSyncLiveData = appRepository.getProfileBankImagesFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//
//
//
//
//    public void getLandDetailsListFromLocalDb() {
//        try {
//            landdetailsListFromLocalDBLiveData = appRepository.getLandDetailsFromLocalDbNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: get Doc Identification details
//
//    public void getLocalDocIdentificationFromLocalDB() {
//        try {
//            docIdentificationDetailsSubmitTableLiveData = appRepository.getDocIdentiFicationDeatilsTableFromLocalDb();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getBankDetailsFromLocalDb() {
//        try {
//            bankDetailsSubmitTableFromLocalDbLiveData = appRepository.getBankDetailsSubmitTableFromLocalDb();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getGeoBoudariesFromLocalDB() {
//        try {
//            geoBoundariesTableFromLocalDbLiveData = appRepository.getGeoBoundariesTableListFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 2/16/2022 logbook de
//    public void getLogBookListFromLocalDb(String strPlotCode) {
//        try {
//            logbookListLocalDbDataLiveData = appRepository.getLogBookDetailsFromLocalDB(strPlotCode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddLogBookDetailsTable>> getLogBookDetailsTableLocalDbDataLiveData() {
//        return logbookListLocalDbDataLiveData;
//    }
//
//
//
//    public void getFertlizerDetailsListFromLocalDB(String strLogBookNum) {
//        try {
//            fertilizerlistFromLocalDBLiveData = appRepository.getFerlizerListDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFertlizerDetailsTableLocalDbDataLiveData() {
//        return fertilizerlistFromLocalDBLiveData;
//    }
//
//    public void getLandDetailsListFromLocalDb(String strFarmercode) {
//        try {
//            landDetailsLocalDbDataLiveData = appRepository.getLandDetailsFromLocalDb(strFarmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getPlotDetailsListFromLocalDb(String strPlotNumber) {
//        try {
//            singleplotDetailsFromLocalDBLiveData = appRepository.getPlotDetailsFromLocalDB(strPlotNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public void getDeleteTablesFromLocal()
//    {
//        try {
//            appRepository.deleteAlltablesFromlocal();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getDeleteGetDataTablesFromLocal()
//    {
//        try {
//            appRepository.deleteGetAlltablesFromlocal();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void upDateLogBookDetails(String currentDate,String update ,String logBookNumber)
//    {
//        try {
//            appRepository.upDateLogBook(currentDate,update,logBookNumber);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getVillageDetailsListFromLocalDB(String pincode) {
//        try {
//            villageDetailsByPincode = appRepository.getVillageTableDetailsFromLocalDbByPincode(pincode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getSeasonSelectionNameseasonID(String seasonID) {
//        try {
//            getSeasonNameBySeasonID = appRepository.getSeasonName(seasonID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getLookUpSelectionNameLookUpId(String selectionID) {
//        try {
//            getLookUpSelectionNameLookUpId = appRepository.getLookUpDataNameByID(selectionID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getDocIdentiFicationFromLocalDBByFarmerCode(String farmercode) {
//        try {
//            getDocIdentiFicationDetailsFromLocalDBByFarmerCode = appRepository.getDocIdentiFicationDetailsFromLocalDB(farmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getVillageDetailsByVillageId(String villageID) {
//        try {
//            getVillageDetailsFromLocalDBByVillageID = appRepository.getVillageDetailsFromLocalDB(villageID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getCropDetailsByCropId(String cropId) {
//        try {
//            getCropDetailsFromLocalDBByCropID = appRepository.getCropListDetailsFromLocalDB(cropId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getCropVarietyDetailsByCropId(String cropVarietyId) {
//        try {
//            getCropVarietyDetailsFromLocalDBByCropID = appRepository.getCropVarietyDetailsFromLocalDB(cropVarietyId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getMandalDetailsByMandalID(String mandalID) {
//        try {
//            getDeatailsFromMandalTableLocalDbLiveData = appRepository.getMandalDetailsFromLocalDb(mandalID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getLogBookModuleStatusByLogBookNumber(String logBookNum) {
//        try {
//            getLogBookModulesStatusDetailsFromLocalDBByLogBookID = appRepository.getLogBookModuleDetailsFromLocalDB(logBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LogBookModulesStatusDetailsTable> getLogBookModuleDetailsFromLocalDBByLogBookNumberLiveData() {
//        return getLogBookModulesStatusDetailsFromLocalDBByLogBookID;
//    }
//
//
//
//
//
//
//    public void getDistricDetailsFromlocalDB(String districID) {
//        try {
//            getDistrictDetailsFromLocalDB = appRepository.getDistrciDetailsFromLocalDB(districID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getStateDetailsFromlocalDB(String stateId) {
//        try {
//            getStateDetailsFromLocalDB = appRepository.getStateDetailsFromLocalDB(stateId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getBankDetailsFromLocalDBByFarmerCode(String strFarmercode) {
//        try {
//            bankDetailsFromLocalDBByfarmerCode = appRepository.getBankDeatilsByFarmerCode(strFarmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void getDocIdentityDetailsByFarmerCode(String strFarmercode) {
//        try {
//            docIdentificationDetailsByFarmerCode = appRepository.getDocIdentiFicationDetailsByFarmercode(strFarmercode);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 2/15/2022 log book adding to local db
//    public void insertAddLogBookDetailsTableLocalDb(AddLogBookDetailsTable addLogBookDetailsTable) {
//        try {
//            addLogBookDetailsTableLiveData = appRepository.insertAddLogBookDetailsTable(addLogBookDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableLiveDataFromLocalDB() {
//        return addLogBookDetailsTableLiveData;
//    }
//
//
//    public void upDateAddLogBookDetailsTableLocalDb(String logBookNumber,String dateUpdate) {
//        try {
//            upDateaddLogBookDetailsTableLiveData = appRepository.upDateAddLogBookDetailsTable(logBookNumber,dateUpdate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLogBookDetailsTable> getUpDateAddLogBookDetailsTableLiveDataFromLocalDB() {
//        return upDateaddLogBookDetailsTableLiveData;
//    }
//    public void insertRefreshTableDateCheckTableLocalDb(RefreshTableDateCheck refreshTableDateCheck) {
//        try {
//            addRefreshTableDateCheckLiveData = appRepository.insertRefreshTableDateCheck(refreshTableDateCheck);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<RefreshTableDateCheck> getRefreshTableDateCheckLiveDataFromLocalDB() {
//        return addRefreshTableDateCheckLiveData;
//    }
//
//
//    // TODO: 2/16/2022 Adding Fertilizer data
//    public void insertAddFertilizerDetailsTableLocalDb(AddFertilizerDetailsTable addFertilizerDetailsTable) {
//        try {
//            addFertilizerDetailsTableLiveData = appRepository.insertAddFertilizerDetailsTable(addFertilizerDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerDetailsTableLiveDataFromLocalDB() {
//        return addFertilizerDetailsTableLiveData;
//    }
//
//    // TODO: 2/17/2022 Add Organic Data
////    public void insertAddOrganicDetailsTableLocalDb(AddOrganicDetailsTable addOrganicDetailsTable) {
////        try {
////            addOrganicDetailsTableLiveData = appRepository.insertAddOrganicDetailsTable(addOrganicDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddOrganicDetailsTable> getAddOrganicDetailsTableLiveDataFromLocalDB() {
////        return addOrganicDetailsTableLiveData;
////    }
////
////
////
////    public void insertAddWaterSeasonFeildTableLocalDb(AddWaterSeasonFeildTable addWaterSeasonFeildTable) {
////        try {
////            addWaterSeasonFeildTableLiveData = appRepository.insertAddWaterSeasonFeildTable(addWaterSeasonFeildTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public void insertAddLandpermissionTableLocalDb(AddLandPreparationTable addLandPreparationTable) {
//        try {
//            addLandPreparationTableLiveData = appRepository.insertAddLandPreparationFeildTable(addLandPreparationTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddPestDiseasesTableLocalDb(AddPestDiseaseControlTable addPestDiseaseControlTable) {
//        try {
//            addPestDiseaseTableLiveData = appRepository.insertAddPestDiseasesFeildTable(addPestDiseaseControlTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddHarvestingTableLocalDb(AddHarvestingTable addHarvestingTable) {
//        try {
//            addharvestingTableLiveData = appRepository.insertAddLaHarvestingFeildTable(addHarvestingTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddSeedRateTableLocalDb(AddSeedRateTable addSeedRateTable) {
//        try {
//            addSeedRateTableLiveData = appRepository.insertAddSeedRateFeildTable(addSeedRateTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddNurseryPermissionTableLocalDb(AddNurseryPreparationTable addNurseryPreparationTable) {
//        try {
//            addNurseryPreparationTableLiveData = appRepository.insertAddnurseryPreparationFeildTable(addNurseryPreparationTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddMoistureContentTableLocalDb(AddMoistureContentTable addMoistureContentTable) {
//        try {
//            addMoistureContentTableLiveData = appRepository.insertAddMoistureContentFeildTable(addMoistureContentTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddYieldTableLocalDb(AddYieldTable addYieldTable) {
//        try {
//            addYieldTableLiveData = appRepository.insertAddYieldFeildTable(addYieldTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddWeedmanagementTableLocalDb(AddWeedManagementTable addWeedManagementTable) {
//        try {
//            addWeedManagementTableLiveData = appRepository.insertAddWeedmanagementFeildTable(addWeedManagementTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddWaterManagementTableLocalDb(AddWaterManagementTable addWaterManagementTable) {
//        try {
//            addWaterManagementTableLiveData = appRepository.insertAddWatermanagementFeildTable(addWaterManagementTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddTransplantingTableLocalDb(AddTransplantingTable addTransplantingTable) {
//        try {
//            addTransplantingTableLiveData = appRepository.insertAddTransplantingFeildTable(addTransplantingTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddFertilizerTableLocalDb(AddFertilizerDetailsTable addFertilizerDetailsTable) {
//        try {
//            addFertilizationTableLiveData = appRepository.insertAddFertilizationFeildTable(addFertilizerDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void insertAddGeoBoundriesTableLocalDb(AddGeoBoundriesTable addGeoBoundriesTable) {
//        try {
//            addGeoBoundriesTableLiveData = appRepository.insertAddGeoBoundriesFeildTable(addGeoBoundriesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLandPreparationTable> getAddLandPreparationFeildTableLiveDataFromLocalDB() {
//        return addLandPreparationTableLiveData;
//    }
//
//    public LiveData<AddPestDiseaseControlTable> getAddPestDiseasesFeildTableLiveDataFromLocalDB() {
//        return addPestDiseaseTableLiveData;
//    }
//
//    public LiveData<AddHarvestingTable> getAddHarvestingFeildTableLiveDataFromLocalDB() {
//        return addharvestingTableLiveData;
//    }
//
//    public LiveData<AddSeedRateTable> getAddSeedRateFeildTableLiveDataFromLocalDB() {
//        return addSeedRateTableLiveData;
//    }
//
//    public LiveData<AddNurseryPreparationTable> getAddNurseryPreparationFeildTableLiveDataFromLocalDB() {
//        return addNurseryPreparationTableLiveData;
//    }
//
//    public LiveData<AddMoistureContentTable> getAddMoistureContentFeildTableLiveDataFromLocalDB() {
//        return addMoistureContentTableLiveData;
//    }
//
//    public LiveData<AddYieldTable> getAddYieldFeildTableLiveDataFromLocalDB() {
//        return addYieldTableLiveData;
//    }
//
//    public LiveData<AddWeedManagementTable> getAddWeedmanagementFeildTableLiveDataFromLocalDB() {
//        return addWeedManagementTableLiveData;
//    }
//
//    public LiveData<AddWaterManagementTable> getAddWatermanagementFeildTableLiveDataFromLocalDB() {
//        return addWaterManagementTableLiveData;
//    }
//
//    public LiveData<AddTransplantingTable> getAddTransplantingFeildTableLiveDataFromLocalDB() {
//        return addTransplantingTableLiveData;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizationFeildTableLiveDataFromLocalDB() {
//        return addFertilizationTableLiveData;
//    }
//
//    public LiveData<AddGeoBoundriesTable> getAddGeoBoundriesFeildTableLiveDataFromLocalDB() {
//        return addGeoBoundriesTableLiveData;
//    }
//
////    public LiveData<AddWaterSeasonFeildTable> getAddWaterSeasonFeildTableLiveDataFromLocalDB() {
////        return addWaterSeasonFeildTableLiveData;
////    }
////
////
////    public void getWaterSeasonFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
////        try {
////            getWaterSeasonFieldDetailsFromLocalDBLiveData = appRepository.getWaterSeasonFeildDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public void getLandPreparationFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getLandPreparationDetailsFromLocalDBLiveData = appRepository.getLandPreparationFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getPestDiseasecontrolFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getPestDiseasecontrolDetailsFromLocalDBLiveData = appRepository.getPestDiseaseControlFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getHarvestingFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getharvestingDetailsFromLocalDBLiveData = appRepository.getHarvestingFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getSeedRateFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getSeedrateDetailsFromLocalDBLiveData = appRepository.getSeedRateFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getNurseryFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getNurseryPreparationDetailsFromLocalDBLiveData = appRepository.getNurseryPreparationFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getmoistureContentFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getMoistureContentDetailsFromLocalDBLiveData = appRepository.getMoistureContentFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getYieldFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getYieldDetailsFromLocalDBLiveData = appRepository.getYieldFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getWeedmanagementDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getWeedmanagementDetailsFromLocalDBLiveData = appRepository.getWeedmanagementFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getWaterManagementFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getWatermanagementDetailsFromLocalDBLiveData = appRepository.getWatermanagementFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getTransplantingFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getTransplantingDetailsFromLocalDBLiveData = appRepository.getTransplantingFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getFertilizerFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getFertilizerDetailsFromLocalDBLiveData = appRepository.getFertilizerFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getGeoBoundriesFieldDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getGeoBoundriesDetailsFromLocalDBLiveData = appRepository.getGeoBoundriesFeildDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
////    public LiveData<AddWaterSeasonFeildTable> getWaterSeasonFieldDetailsFromLocalDBLiveData() {
////        return getWaterSeasonFieldDetailsFromLocalDBLiveData;
////    }
//
//    public LiveData<List<AddLandPreparationTable>> getLandPreparationFieldDetailsFromLocalDBLiveData() {
//        return getLandPreparationDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddPestDiseaseControlTable>> getPestDiseaseControlFieldDetailsFromLocalDBLiveData() {
//        return getPestDiseasecontrolDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddHarvestingTable>> getHarvestingFieldDetailsFromLocalDBLiveData() {
//        return getharvestingDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddSeedRateTable>> getSeedrateFieldDetailsFromLocalDBLiveData() {
//        return getSeedrateDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddNurseryPreparationTable>> getNurseryPreparationFieldDetailsFromLocalDBLiveData() {
//        return getNurseryPreparationDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddMoistureContentTable>> getMoistureContentFieldDetailsFromLocalDBLiveData() {
//        return getMoistureContentDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddYieldTable>> getYieldFieldDetailsFromLocalDBLiveData() {
//        return getYieldDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddWeedManagementTable>> getWeedmanagementFieldDetailsFromLocalDBLiveData() {
//        return getWeedmanagementDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddWaterManagementTable>> getWaterManagementFieldDetailsFromLocalDBLiveData() {
//        return getWatermanagementDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddTransplantingTable>> getTransplantingFieldDetailsFromLocalDBLiveData() {
//        return getTransplantingDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFertilizerFieldDetailsFromLocalDBLiveData() {
//        return getFertilizerDetailsFromLocalDBLiveData;
//    }
//    public LiveData<Integer> getLandPreparationfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getLandPreparationnfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getPestDiseasefielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getPestDiseaseControlfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getHarvestingfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getharvestingfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getSeedRatefielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getSeedRatefieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getNurseryPreparatinfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getNurseryPreparationfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getMoistureContentfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getMoistureContentfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getYieldfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getYieldfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getWeedmanagementfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getWeedManagementfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getWatermanagementfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getWatermanagementfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getTransplantingfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getTransplantingfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<Integer> getFertilizerfielddetailsCount(String logBookNum,String strDate) {
//        return appRepository.getFertilizerfieldsDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<List<AddGeoBoundriesTable>> getGeoBoundriesFieldDetailsFromLocalDBLiveData() {
//        return getGeoBoundriesDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<Integer> getGeoBoundriesfieldsDatailsCount(String logBookNum,String strDate) {
//        return appRepository.getGeoBoundriesfieldsDataCount(logBookNum,strDate);
//    }
//
//    public void getAddLandPreparationFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getLandPreparationFeildDetailsTableLiveDataByDate = appRepository.getAddLandPreparationFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddPestDiseaseFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getPestDiseasecontrolFeildDetailsTableLiveDataByDate = appRepository.getAddPestDiseaseFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddHarvestingFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getHarvestingFeildDetailsTableLiveDataByDate = appRepository.getAddHarvestingFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddSeedrateFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getSeedRateFeildDetailsTableLiveDataByDate = appRepository.getAddSeedRateFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddNurseryFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getNurseryPreparationFeildDetailsTableLiveDataByDate = appRepository.getAddNurseryPreparationFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddMoistureContentFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getMoistureContentFeildDetailsTableLiveDataByDate = appRepository.getAddMoistureContentFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddYieldFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getYieldFeildDetailsTableLiveDataByDate = appRepository.getAddYieldFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddWeedManagementFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getWeedManagementFeildDetailsTableLiveDataByDate = appRepository.getAddWeedManagementFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddWaterManagementFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getWaterManagementFeildDetailsTableLiveDataByDate = appRepository.getAddWaterManagementFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddTransplantingFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getTransplantingFeildDetailsTableLiveDataByDate = appRepository.getAddTransplantingFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddFertilizerFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getFertilizerFeildDetailsTableLiveDataByDate = appRepository.getAddFertilizerFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void getAddGeoBoundriesFeildDetailsDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getGeoBoundriesFeildDetailsTableLiveDataByDate = appRepository.getAddGeoBoundriesFeildDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLandPreparationTable> getAddLandPreparationFeildTableDetailsTableFromLocalDBLiveData() {
//        return getLandPreparationFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddPestDiseaseControlTable> getAddPestDiseasecontrolFeildTableDetailsTableFromLocalDBLiveData() {
//        return getPestDiseasecontrolFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddHarvestingTable> getAddHarvestingFeildTableDetailsTableFromLocalDBLiveData() {
//        return getHarvestingFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddSeedRateTable> getAddSeedRateFeildTableDetailsTableFromLocalDBLiveData() {
//        return getSeedRateFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddNurseryPreparationTable> getAddNurseryPreparationFeildTableDetailsTableFromLocalDBLiveData() {
//        return getNurseryPreparationFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddMoistureContentTable> getAddMoistureContentFeildTableDetailsTableFromLocalDBLiveData() {
//        return getMoistureContentFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddYieldTable> getAddYieldFeildTableDetailsTableFromLocalDBLiveData() {
//        return getYieldFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddWeedManagementTable> getAddWeedManagementFeildTableDetailsTableFromLocalDBLiveData() {
//        return getWeedManagementFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddWaterManagementTable> getAddWatermanagementFeildTableDetailsTableFromLocalDBLiveData() {
//        return getWaterManagementFeildDetailsTableLiveDataByDate;
//    }
//    public LiveData<AddTransplantingTable> getAddTransplantingFeildTableDetailsTableFromLocalDBLiveData() {
//        return getTransplantingFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerFeildTableDetailsTableFromLocalDBLiveData() {
//        return getFertilizerFeildDetailsTableLiveDataByDate;
//    }
//
//    public LiveData<AddGeoBoundriesTable> getAddGeoBoundriesFeildTableDetailsTableFromLocalDBLiveData() {
//        return getGeoBoundriesFeildDetailsTableLiveDataByDate;
//    }
//
//
////    public void getOrganicDetailsListFromLocalDB(String strLogBookNum) {
////        try {
////            orgaincAmendsDatalistFromLocalDBLiveData = appRepository.getOrganicDetailsTableListDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddOrganicDetailsTable>> getOrganicDetailsTableLocalDbDataLiveData() {
////        return orgaincAmendsDatalistFromLocalDBLiveData;
////    }
////
//
//    public LiveData<Integer> getCount() {
//        return appRepository.getCountData();
//
//    }
//
//    public LiveData<Integer> getCountLand() {
//        return appRepository.getCountDataLand();
//
//    }
//
////    public VillageTable getVillageNameLocalDB(String villageId) {
////        return appRepository.getVillageNameLocalDB(villageId);
////
////    }
//
//
//    // TODO: 2/25/2022 get not sync count from local db
//
//    public LiveData<Integer> getNotSyncFarmerCountDataFromLocalDB() {
//        return appRepository.getNotSyncFarmerCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncDocCountDataFromLocalDB() {
//        return appRepository.getNotSyncDocCountDataFromLocalDB();
//
//    }
//
//
//    public LiveData<Integer> getNotSyncBankCountDataFromLocalDB() {
//        return appRepository.getNotSyncBankCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncPlotCountDataFromLocalDB() {
//        return appRepository.getNotSyncPlotCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncGeoCountDataFromLocalDB() {
//        return appRepository.getNotSyncGeoCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncLogBookCountDataFromLocalDB() {
//        return appRepository.getNotSyncLogBookCountDataFromLocalDB();
//
//    }
//
//    public LiveData<Integer> getNotSyncFertlizerCountDataFromLocalDB() {
//        return appRepository.getNotSyncFertilizerCountDataFromLocalDB();
//
//    }
//
////    public LiveData<Integer> getNotSyncOrganicCountDataFromLocalDB() {
////        return appRepository.getNotSyncOrganicCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncWaterRegimeCountDataFromLocalDB() {
////        return appRepository.getNotSyncWaterRegimeCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncWaterPreSeasonCountDataFromLocalDB() {
////        return appRepository.getNotSyncWaterPreSeasonCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncBorewellCountDataFromLocalDB() {
////        return appRepository.getNotSyncBorewellCountDataFromLocalDB();
////
////    }
////
////    public LiveData<Integer> getNotSyncWaterFieldCountDataFromLocalDB() {
////        return appRepository.getNotSyncWaterFieldCountDataFromLocalDB();
////
////    }
//
//    public LiveData<Integer> getNotSyncHarvestCountDataFromLocalDB() {
//        return appRepository.getNotSyncHarvestCountDataFromLocalDB();
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    // TODO: 2/18/2022 harvest saving part
//    public void insertAddHarvestDetailsTableLocalDb(AddHarvestDetailsTable addHarvestDetailsTable) {
//        try {
//            addHarvestDetailsTableLiveData = appRepository.insertAddHarvestDetailsTable(addHarvestDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddHarvestDetailsTable> getAddHarvestDetailsTableLiveDataFromLocalDB() {
//        return addHarvestDetailsTableLiveData;
//    }
//
//
//
//    public void getAddHarvestDetailsFromLocalDBLiveData(String strLogBookNum) {
//        try {
//            getAddHarvestDetailsFromLocalDBLiveData = appRepository.getAddHarvestDetailsTableDataFromLocalDB(strLogBookNum);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddHarvestDetailsTable> getAddHarvestDetailsFromLocalDBLiveData() {
//        return getAddHarvestDetailsFromLocalDBLiveData;
//    }
//
//
//    // TODO: 2/18/2022 Add Borewell Season Part
//
//
////
////    public void insertAddBoreWellPumpSeasonTableLocalDb(AddBoreWellSeasonTable addBoreWellSeasonTable) {
////        try {
////            addBoreWellPumpSeasonTableLiveData = appRepository.insertAddBoreWellPumpSeasonTable(addBoreWellSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonTableLiveDataFromLocalDB() {
////        return addBoreWellPumpSeasonTableLiveData;
////    }
//
//
//    public void getCodeLogBookDropDownHDRTableDetailsFromLocalDB(String name) {
//        try {
//            getLogBookDropDownHDRTableDetailsFromLocalDBLiveData = appRepository.getLogBookDropDownHDRTableDataFromLocalDB(name);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LogBookDropDownHDRTable> getLogBookDropDownHDRTableDetailsFromLocalDBLiveData() {
//        return getLogBookDropDownHDRTableDetailsFromLocalDBLiveData;
//    }
//
//
////    public void getAddAddBoreWellPumpSeasonDetailsFromLocalDB(String strLogBookNum) {
////        try {
////            getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData = appRepository.getAddBoreWellPumpSeasonTableDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData() {
////        return getAddBoreWellPumpSeasonDetailsFromLocalDBLiveData;
////    }
////
////    // TODO: 2/18/2022 water pre season
////
////    public void insertAddWaterReasonPreSeasonTableLocalDb(AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable) {
////        try {
////            addWaterReasonPreSeasonTableLiveData = appRepository.insertAddWaterReasonPreSeasonTable(addWaterReasonPreSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterReasonPreSeasonTable> getInsertAddWaterReasonPreSeasonTableLiveDataFromLocalDB() {
////        return addWaterReasonPreSeasonTableLiveData;
////    }
////
////
////    public void getWaterReasonPreSeasonDetailsFromLocalDB(String strLogBookNum) {
////        try {
////            getWaterReasonPreSeasonDetailsFromLocalDBLiveData = appRepository.getAddWaterReasonPreSeasonDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterReasonPreSeasonTable> getAddWaterReasonPreSeasonDetailsFromLocalDBLiveData() {
////        return getWaterReasonPreSeasonDetailsFromLocalDBLiveData;
////    }
////
////
////    // TODO: 2/18/2022 water regime season
////
////    public void insertAddWaterRegimeSeasonDetailsTableLocalDb(AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable) {
////        try {
////            addWaterRegimeSeasonDetailsTableLiveData = appRepository.insertAddWaterRegimeSeasonDetailsTable(addWaterRegimeSeasonDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterRegimeSeasonDetailsTable> getInsertAddWaterRegimeSeasonDetailsTableLiveDataFromLocalDB() {
////        return addWaterRegimeSeasonDetailsTableLiveData;
////    }
//
//
//    public void getAddFertilizerDetailsTableFromLocalDB(String strLogBookNum,String currentDate) {
//        try {
//            getFertilizerDetailsTableLiveDataByDate = appRepository.getAddFertilizerDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerDetailsTableFromLocalDBLiveData() {
//        return getFertilizerDetailsTableLiveDataByDate;
//    }
//
//
//    public void getRefreshTableDateCheckFromLocalDBByDate(String DeviceID,String currentDate) {
//        try {
//            getAddRefreshTableDateCheck = appRepository.getAddRefreshTableDateCheckTableDate(DeviceID,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<RefreshTableDateCheck> getRefreshTableDateCheckByDateFromLocalDBLiveData() {
//        return getAddRefreshTableDateCheck;
//    }
//
//
//
//    // TODO: 2/18/2022 logbook date
//    public void getAddLogBookDetailsTableFromLocalDBByDate(String strLogBookNum,String currentDate) {
//        try {
//            getAddLogBookDetailsTableLiveDataByDate = appRepository.getAddLogBookDetailsTableDate(strLogBookNum,currentDate);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableByDateFromLocalDBLiveData() {
//        return getAddLogBookDetailsTableLiveDataByDate;
//    }
//
//    // TODO: 2/18/2022 delete log bokk data
//
//    public void getDeleteLogbookTablesFromLocalDB(String logBookNum)
//    {
//        try {
//            appRepository.deleteLogBooktablesFromlocalDB(logBookNum,"");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<Integer> getCountLogBook() {
//        return appRepository.getLogBookCount();
//    }
//
//    public LiveData<Integer> getCountFertlizerCount(String logBookNum,String strDate) {
//        return appRepository.getFerlizerDataCount(logBookNum,strDate);
//    }
//
//    public LiveData<List<SavingFarmerProfieImagesTable>> getSavingFarmerProfileImagesNotSyncLiveDataList() {
//        return savingFarmerProfileImagesNotSyncLiveData;
//    }
//
//
//    public LiveData<List<SavingBankImagesTable>> getSavingBankProfileImagesNotSyncLiveData() {
//        return savingBankProfileImagesNotSyncLiveData;
//    }
//
//    public LiveData<List<SavingPlotProfieImagesTable>> getSavingPlotProfileImagesNotSyncLiveData() {
//        return savingPlotProfileImagesNotSyncLiveData;
//    }
//
//
//
////    public void getWaterRegimeSeasonDetailsFromLocalDB(String strLogBookNum) {
////        try {
////            getWaterRegimeSeasonDetailsFromLocalDBLiveData = appRepository.getAddWaterRegimeSeasonDetailsDataFromLocalDB(strLogBookNum);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<AddWaterRegimeSeasonDetailsTable> getAddWaterRegimeSeasonDetailsFromLocalDBLiveData() {
////        return getWaterRegimeSeasonDetailsFromLocalDBLiveData;
////    }
//
//
//    // TODO: 1/26/2022 Accesing of pincode from local db
//
//    public LiveData<List<VillageTable>> getvillageDetailsByPincodeLiveData() {
//        return villageDetailsByPincode;
//    }
//
//
//
//
//    public LiveData<MandalTable> getLocalDBMandalDetailsLiveData() {
//        return getDeatailsFromMandalTableLocalDbLiveData;
//    }
//
//    public LiveData<VillageTable> getVillageDetailsFromLocalDBByVillageIdLiveData() {
//        return getVillageDetailsFromLocalDBByVillageID;
//    }
//
//    public LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDetailsFromLocalDBByFarmerCodeLiveData() {
//        return getDocIdentiFicationDetailsFromLocalDBByFarmerCode;
//    }
//
//
//    public LiveData<CropListTable> geCropListDetailsFromLocalDBByCropIdLiveData() {
//        return getCropDetailsFromLocalDBByCropID;
//    }
//
//    public LiveData<CropVarietyListTable> geCropVarietyListDetailsFromLocalDBByCropIdLiveData() {
//        return getCropVarietyDetailsFromLocalDBByCropID;
//    }
//
//    public LiveData<LookUpDropDownDataTable> getLookUpDropDownDetailsFromLocalDBByLookUPIDLiveData() {
//        return getLookUpSelectionNameLookUpId;
//    }
//
//    public LiveData<SeasonTable> getSeasonTableDetailsFromLocalDBBySeasonIDLiveData() {
//        return getSeasonNameBySeasonID;
//    }
//
//
//
//    public LiveData<PlotDetailsListTable> getPlotDetailsFromLocalDBByPlotNumLiveData() {
//        return singleplotDetailsFromLocalDBLiveData;
//    }
//
//    public LiveData<SavingFarmerProfieImagesTable> getInsertionOfFarmerProfileImagesLiveData() {
//        return savingFarmerProfieImagesTableLiveData;
//    }
//
//    public LiveData<SavingPlotProfieImagesTable> getInsertionOfPlotProfileImagesLiveData() {
//        return savingPlotImagesTableLiveData;
//    }
//
//    private LiveData<AddComplaintsDetailsTable> addComplaintsDetailsTableLiveData;
//    private LiveData<List<AddComplaintsDetailsTable>> getAddComplainFormDetailsFromLocalDBLiveData;
//
//
//    public LiveData<SavingBankImagesTable> getInsertionOfBankImagesLiveData() {
//        return savingBankImagesTableLiveData;
//    }
//
//    public LiveData<GeoBoundariesTable> getGeoBoundariesTableLocalDB() {
//        return geoBoundariesTableLiveDataInsert;
//    }
//
//    public LiveData<List<GeoBoundariesTable>> getGeoBoundariesTableListLocalDB() {
//        return savingGeoBoundariesTableListData;
//    }
//
//
//
//
//
//
//    public LiveData<DistrictTable> getDistriclDetailsFromLocalDBLiveData() {
//        return getDistrictDetailsFromLocalDB;
//    }
//
//    public LiveData<StatesTable> getStateTablelDetailsFromLocalDBLiveData() {
//        return getStateDetailsFromLocalDB;
//    }
//
//
//    public LiveData<List<FarmerTable>> getFarmerDetailsListLiveData() {
//        return farmerListFromLocalDBLiveData;
//    }
//
//    public LiveData<List<FarmerTable>> getFarmerDetailsListNotSyncLiveData() {
//        return farmerListFromLocalDBLiveDataNotSync;
//    }
//
//
//    public LiveData<List<PlotDetailsListTable>> getlandDetailsLocalDbDataLiveData() {
//        return landDetailsLocalDbDataLiveData;
//    }
//
//
//    public LiveData<List<BankDetailsSubmitTable>> getBankDetailsFromLocalDbByFarmerCodeLiveData() {
//        return bankDetailsFromLocalDBByfarmerCode;
//    }
//
//    public LiveData<List<DocIdentiFicationDeatilsTable>> getDocIdentiFicationDeatilsTableDbByFarmerCodeLiveData() {
//        return docIdentificationDetailsByFarmerCode;
//    }
//
//
//    public LiveData<List<PlotDetailsListTable>> getLandDetailsListTableLocalDBLiveData() {
//        return landdetailsListFromLocalDBLiveData;
//    }
//
//    public LiveData<List<DocIdentiFicationDeatilsTable>> getDocIdentiFicationDeatilsTableFromLocalLiveData() {
//        return docIdentificationDetailsSubmitTableLiveData;
//    }
//
//
//    public LiveData<List<BankDetailsSubmitTable>> getBankDetailsTableFromLocalLiveData() {
//        return bankDetailsSubmitTableFromLocalDbLiveData;
//    }
//
//
//    public LiveData<List<GeoBoundariesTable>> getGeoBoundariesFromLocalLiveData() {
//        return geoBoundariesTableFromLocalDbLiveData;
//    }
//    public LiveData<List<GeoBoundariesTable>> getInsertGeoBoundariesFromLocalLiveData() {
//        return listentryValuesGeoBoundaries;
//    }
//
//
//
//    public LiveData<FarmerTable> getFarmerDetailListTableLiveDataFromLocalDB() {
//        return farmerDetailListTableLiveData;
//    }
//
//    public LiveData<FarmerTable> getfarmerDetailListTableLiveDataInsertLiveDataFromLocalDB() {
//        return farmerDetailListTableLiveDataInsert;
//    }
//
//
//
//    public LiveData<DocIdentiFicationDeatilsTable> getDocDetailLsTableLiveDataFromLocalDB() {
//        return documentSavingDataLocalDB;
//    }
//
//    public LiveData<BankDetailsSubmitTable> getBankDetailsSubmitTableLiveDataFromLocalDB() {
//        return bankDetailsSubmitTableLiveData;
//    }
//
//
//
//    public LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDeatilsTableLiveDataFromLocalDB() {
//        return docIdentiFicationDeatilsTableLiveData;
//    }
//
//
//    public LiveData<CurrentVisitFarmerTables> getCurrentVisitFarmerTablesLiveDataFromLocalDB() {
//        return currentVisitFarmerTablesLiveData;
//    }
//
//
//    public LiveData<PlotDetailsListTable> getPlotDetailListTableLiveData() {
//        return plotDetailListTableLiveData;
//    }
//
//
//    public LiveData<GeoBoundariesTable> getGeoBoundariesInsertTableLocalDBLiveData() {
//        return geoBoundariesTableLocalDbInsertLiveData;
//    }
//
//
//    // TODO: get pin code village
//    public LiveData<List<PinCodeDetailsDataTable>> getByPinCodeStateVillageDataTableLivedata() {
//        return bypincodestatevillagelistitemLiveData;
//    }
//
//
//    public LiveData<List<SyncResponseDTO>> getsyncResponseDTOFromServerLiveData() {
//        return syncResponseDTOFromServerLiveData;
//    }
//
//    public LiveData<List<LoginResponseDTO>> getloginResponseDTOFromServerLiveData() {
//        return loginResponseDTOFromServerLiveData;
//    }
//
//    public LiveData<List<PinCodeDetailsResponseDTO>> getpincodeDetailsResponseFromServerLiveData() {
//        return pincodeDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<CropDetailsResponseFromServerDTO>> getcropDetailsResponseFromServerLiveData() {
//        return cropDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<VillageDetailsResponseDTO>> getVillageDetailsResponseFromServerLiveDataLiveData() {
//        return villageDetailsResponseFromServerLiveData;
//    }
//
//
//
//
//
//    public LiveData<List<PlotNumberDataResponseDTO>> getPlotCodeDetailsFromserverLivedata() {
//        return syncDataResponseDTOLiveData;
//    }
//
//
//    // TODO: Response of state
//
//    public LiveData<List<StateListResponseDTO>> getStateListDetailsFromserverLivedata() {
//        return stateDetailsResponseFromServerLiveData;
//    }
//
//
//    public LiveData<List<DistricDetailsResponseDTO>> getDistricListDetailsFromserverLivedata() {
//        return districDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<ClusterDetailsResponseDTO>> getClusterDetailsFromserverLivedata() {
//        return clusterDetailsResponseFromServerLiveData;
//    }
//
//    public LiveData<List<MandalDetailsResponseDTO>> getMandalDetailsFromserverLivedata() {
//        return mandalDetailsResponseFromServerLiveData;
//    }
//
//
//    public LiveData<List<VillageByMandalIdDetailsResponseDTO>> getvillageDetailsByMandalResponseFromServerLiveData() {
//        return villageDetailsByMandalResponseFromServerLiveData;
//    }
//
//
//    // TODO: Server data saved into local db by master sync
//
//
//    // TODO: state details from server
//    public void getStateDetailsFromServerSavedIntoLocalDB() {
//        try {
//            stateListSavedIntoLocalDBFromServer = appRepository.getStateListFromServerToSaveLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<StatesTable>> getstateListSavedIntoLocalDBFromServerLivedata() {
//        return stateListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: distric details from server
//    public void getDistricDetailsFromServerSavedIntoLocalDB() {
//        try {
//            districListSavedIntoLocalDBFromServer = appRepository.getDistrictTableResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<DistrictTable>> getdistricListSavedIntoLocalDBFromServerLivedata() {
//        return districListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: mandal details from server
//    public void getMandalDetailsFromServerSavedIntoLocalDB() {
//        try {
//            mandalListSavedIntoLocalDBFromServer = appRepository.getMandalResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<MandalTable>> getmandalListSavedIntoLocalDBFromServerLivedata() {
//        return mandalListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: village details from server
//    public void getVillageTotalDetailsFromServerSavedIntoLocalDB() {
//        try {
//            villageListSavedIntoLocalDBFromServer = appRepository.getVillageTableResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<VillageTable>> getvillageListSavedIntoLocalDBFromServerLivedata() {
//        return villageListSavedIntoLocalDBFromServer;
//    }
//
//
//    // TODO: pincode details from server
//    public void getClusterDTLDetailsFromServerSavedIntoLocalDB() {
//        try {
//
//            clusterdtlListSavedIntoLocalDBFromServer = appRepository.getClusterDTlResponseDTOListForCollection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: pincode details from server
//    public void getMasterSyncDataFromServerSavedIntoLocalDB() {
//        try {
//
//            getMasterSyncDataFromServerLiveData = appRepository.getMasterSynDetailsFromServer();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<ClusterDTLTable>> getclusterDTLlistSavedIntoLocalDBFromServerLivedata() {
//        return clusterdtlListSavedIntoLocalDBFromServer;
//    }
//
//    public LiveData<List<GetMasterPersonalLandAllDetailsRequestDTO>> getGetMasterPersonalLandAllDetailsRequestDTOLivedata() {
//        return getMasterSyncDataFromServerLiveData;
//    }
//
//
//    // TODO: pincode details from server
//    public void getClusterHDRDetailsFromServerSavedIntoLocalDB() {
//        try {
//            clusterHDRlistSavedIntoLocalDBFromServer = appRepository.getClusterHDRListFromServerToSaveLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<ClusterHDRTable>> getclusterHDRlistSavedIntoLocalDBFromServerLivedata() {
//        return clusterHDRlistSavedIntoLocalDBFromServer;
//    }
//
//    // TODO: crop details from server
//    public void getCropDetailsListFromServerSavedIntoLocalDB() {
//        try {
//            cropListSavedIntoLocalDBFromServer = appRepository.getCropListFromServerToSaveLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<CropListTable>> getcropListSavedIntoLocalDBFromServerLivedata() {
//        return cropListSavedIntoLocalDBFromServer;
//    }
//
//
////    // TODO: pincode village  details from server
////    public void getPincodeDetailsFromServerSavedIntoLocalDB(){
////        try{
////            pinCodeDetailsListSavedIntoLocalDBFromServer=appRepository.getStateDetailsResponseDTOServer();
////        }catch (Exception ex){
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<PinCodeDetailsListTable>> getpinCodeDetailsListSavedIntoLocalDBFromServerLivedata() {
////        return pinCodeDetailsListSavedIntoLocalDBFromServer;
////    }
////
////
////
////    // TODO: pincode village send  details from server
////    public void getPincodeVillageFromServerSavedIntoLocalDB(){
////        try{
////            pincodevillageDetailsListSavedIntoLocalDBFromServer=appRepository.getStateDetailsResponseDTOServer();
////        }catch (Exception ex){
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<VillageDetailsByPinCodListTable>> getvillageDetailsListSavedIntoLocalDBFromServerLivedata() {
////        return pincodevillageDetailsListSavedIntoLocalDBFromServer;
////    }
////
////
////
////
////
////    // TODO: cluster ltr details from server
////    public void getClusterDTLDetailsFromServerSavedIntoLocalDB(){
////        try{
////            clusterdtlListSavedIntoLocalDBFromServer=appRepository.getClusterHDRListFromServerToSaveLocalDB();
////        }catch (Exception ex){
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<ClusterDTLTable>> getclusterdtlListSavedIntoLocalDBFromServerLivedata() {
////        return clusterdtlListSavedIntoLocalDBFromServer;
////    }
//
//
//    // TODO: 1/21/2022 Insert get Master Sync data into Local DB
//
//
//    // TODO: 1/21/2022 InsertValues into local db query
//
//    public void insertStateListDetailIntoLocalDBQuery(StatesTable statesTable) {
//        try {
//            insertStatesListDataIntoLocalDBQueryLiveData = appRepository.insertStateListDataIntoLocalDBRepository(statesTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<StatesTable> getinsertStatesListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertStatesListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertClusterDTLListDetailIntoLocalDBQuery(ClusterDTLTable clusterDTLTable) {
//        try {
//            insertClusterDTLListDataIntoLocalDBQueryLiveData = appRepository.insertClusterDTLTableIntoLocalDBRepository(clusterDTLTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<ClusterDTLTable> getinsertClusterDTLListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertClusterDTLListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertDistricListDetailIntoLocalDBQuery(DistrictTable districtTable) {
//        try {
//            insertDistrictListDataIntoLocalLiveData = appRepository.insertDisListDataIntoLocalDBRepository(districtTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<DistrictTable> getinsertDistricListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertDistrictListDataIntoLocalLiveData;
//    }
//
//
//    public void insertMandalListDetailIntoLocalDBQuery(MandalTable mandalTable) {
//        try {
//            insertMandalListDataIntoLocalDBQueryLiveData = appRepository.insertMandalListDataIntoLocalDBRepository(mandalTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<MandalTable> getinsertMandalTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertMandalListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertCropListDetailIntoLocalDBQuery(CropListTable cropListTable) {
//        try {
//            insertCropListDataIntoLocalDBQueryLiveData = appRepository.insertCropListDataIntoLocalDBRepository(cropListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<CropListTable> getinsertCropListTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertCropListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertCropVarietyListDetailIntoLocalDBQuery(CropVarietyListTable cropListTable) {
//        try {
//            insertCropVarietyListDataIntoLocalDBQueryLiveData = appRepository.insertCropVarietyListDataIntoLocalDBRepository(cropListTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<CropVarietyListTable> getinsertCropVarietyListTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertCropVarietyListDataIntoLocalDBQueryLiveData;
//    }
//
//
//    public void insertVillageListDetailIntoLocalDBQuery(VillageTable villageTable) {
//        try {
//            insertVillageListDataIntoLocalDBQueryLiveData = appRepository.insertVillageListDataIntoLocalDBRepository(villageTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<VillageTable> getinsertVillageTableListDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertVillageListDataIntoLocalDBQueryLiveData;
//    }
//

//    public void insertClusterHDRDetailIntoLocalDBQuery(DivisionTable clusterHDRTable) {
//        try {
//            insertClusterHDRListDataIntoLocalDBQueryLiveData = appRepository.insertClusterHDRDataIntoLocalDBRepository(clusterHDRTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

//    public LiveData<DivisionTable> getinsertClusterHDRTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertClusterHDRListDataIntoLocalDBQueryLiveData;
//    }

//    public void insertSeasonDetailIntoLocalDBQuery(SeasonTable seasonTable) {
//        try {
//            insertSeasonListDataIntoLocalDBQueryLiveData = appRepository.insertSeasonDataIntoLocalDBRepository(seasonTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//    public LiveData<SeasonTable> getinsertSeasonTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertSeasonListDataIntoLocalDBQueryLiveData;
//    }
//
//    public void insertLogBookDropDownHDRDetailIntoLocalDBQuery(LogBookDropDownHDRTable logBookDropDownHDRTable) {
//        try {
//            insertLogBookHDRIntoLocalDBQueryLiveData = appRepository.insertLogBookDropDownHDRIntoLocalDBRepository(logBookDropDownHDRTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LogBookDropDownHDRTable> getinsertLogBookDropDownHDRTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertLogBookHDRIntoLocalDBQueryLiveData;
//    }
//
//
//
//    public void insertLookUpDropDownDataDetailIntoLocalDBQuery(LookUpDropDownDataTable lookUpDropDownDataTable) {
//        try {
//            insertLookUpDropDownDataIntoLocalDBQueryLiveData = appRepository.insertLookUpDropDownDataIntoLocalDBRepository(lookUpDropDownDataTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<LookUpDropDownDataTable> getinsertLookUpDropDownDataTableDataIntoLocalDBQueryLiveDataLocalDB() {
//        return insertLookUpDropDownDataIntoLocalDBQueryLiveData;
//    }
//
//
//
//    // TODO: 1/21/2022 get values from local db
//    public void getClusterListFromLocalDB() {
//        try {
//            getClusterListFromLocalDBLiveData = appRepository.getClusterHDRTableDetailslistFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<ClusterHDRTable>> getClusterListDataFromLocalDBLiveData() {
//        return getClusterListFromLocalDBLiveData;
//    }
//
//
//    // TODO: 1/21/2022 get values from local db
//    public void getCropListFromLocalDb() {
//        try {
//            getCropListFromLocalDBLiveData = appRepository.getCropListTableFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<CropListTable>> getCropListTableDataFromLocalDBLiveData() {
//        return getCropListFromLocalDBLiveData;
//    }
//
//
//    // TODO: 1/21/2022 get values from local db
//    public void getCropVarietyTableListFromLocalDB(String cropId) {
//        try {
//            getCropVarietyListFromLocalDBLiveData = appRepository.getCropVarietyListDetailsFromLocalDB(cropId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    // TODO: 1/21/2022 get values from local db
//    public void getVillageTableListFromLocalDB(String clusterID) {
//        try {
//            getVillageListFromLocalDBLiveData = appRepository.getVillageTableDetailsFromLocalDB(clusterID);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//
//    public void getVillageTableListFromLocalDBSingle() {
//        try {
//            getVillageListFromLocalDBLiveData = appRepository.getVillageListFromLocalDB();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<VillageTable>> getVillageTableDataFromLocalDBLiveData() {
//        return getVillageListFromLocalDBLiveData;
//    }
//
//    public LiveData<List<CropVarietyListTable>> getCropVarietyTableDataFromLocalDBLiveData() {
//        return getCropVarietyListFromLocalDBLiveData;
//    }
//
//
//    public LiveData<String> getStringLiveData() {
//        return stringLiveData;
//    }
//
//    public LiveData<Integer> getIntegerLiveData() {
//        return integerLiveData;
//    }
//
//
//
//
//    // TODO: data code for log book module
//
//    public void getSeasonListForLogBookFromLocalDB() {
//        try {
//            getSeasonlistFromlocalDBLiveData = appRepository.getSeasonlistFromLocalDb();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<SeasonTable>> getSeasonlistFromlocalDBLiveData() {
//        return getSeasonlistFromlocalDBLiveData;
//    }
//
//
//
//    public void getLookUpDataFromLocalDBByType(String typeOfReq) {
//        try {
//            getLookUpDataListFromLocalDBLiveData = appRepository.getLookUpDropDownDataListFromLocalDB(typeOfReq);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public LiveData<List<LookUpDropDownDataTable>> getLookUpDataListFromLocalDBLiveData() {
//        return getLookUpDataListFromLocalDBLiveData;
//    }
//
//
//    // TODO: 2/21/2022 log book module sync to server
//
//    public void getLogBookListFromLocalDBNotSync() {
//        try {
//            logBookListFromLocalDBLiveDataNotSync = appRepository.getAddLogBookDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddLogBookDetailsTable>> getAddLogBookDetailsListNotSyncLiveData() {
//        return logBookListFromLocalDBLiveDataNotSync;
//    }
//
//
//    // TODO: sync to server log book
//
//
//    public void syncLogBookDetailsDataToServer(AddLogBookDetailsTable addLogBookDetailsTable) {
//        try {
//            stringLiveData = appRepository.syncLogBookDetailsToServer(addLogBookDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: 2/21/2022  Fertilizer part
//
//
//    public void getFertlizerListFromLocalDBNotSync() {
//        try {
//            ferlizerListFromLocalDBLiveDataNotSync = appRepository.getFertilizerDetailsDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFertilizerDetailsTableDetailsListNotSyncLiveData() {
//        return ferlizerListFromLocalDBLiveDataNotSync;
//    }
//
//
//    public void syncFertilizerDetailsDataToServer(AddFertilizerDetailsTable addFertilizerDetailsTable) {
//        try {
//            stringLiveData = appRepository.syncAddFertilizerDetailsToServer(addFertilizerDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // TODO: organic Data part
//
////    public void getOrganicListFromLocalDBNotSync() {
////        try {
////            organicListFromLocalDBLiveDataNotSync = appRepository.getOrganicDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddOrganicDetailsTable>> getAddOrganicDetailsListNotSyncLiveData() {
////        return organicListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncOrganicDetailsDataToServer(AddOrganicDetailsTable addOrganicDetailsTable) {
////        try {
////            stringLiveData = appRepository.syncAddOrganicDetailsToServer(addOrganicDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: Water regime season
////
////    public void getWaterRegimeSeasonListFromLocalDBNotSync() {
////        try {
////            waterRegimeSeasonListFromLocalDBLiveDataNotSync = appRepository.getWaterRegimeSeasonDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddWaterRegimeSeasonDetailsTable>> getAddWaterRegimeSeasonDetailsTableDetailsListNotSyncLiveData() {
////        return waterRegimeSeasonListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncWaterRegimeSeasonDetailsTableDetailsDataToServer(AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable) {
////        try {
////            stringLiveData = appRepository.syncAddWaterRegimeSeasonDetailsToServer(addWaterRegimeSeasonDetailsTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: water pre season
////    public void getWaterPreSeasonListFromLocalDBNotSync() {
////        try {
////            waterpreSeasonFromLocalDBLiveDataNotSync = appRepository.getWaterPreSeasonDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddWaterReasonPreSeasonTable>> getAddWaterReasonPreSeasonLogBookDetailsListNotSyncLiveData() {
////        return waterpreSeasonFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncWaterPreSeasonDetailsDataToServer(AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable) {
////        try {
////            stringLiveData = appRepository.syncAddWaterReasonPreSeasonDetailsToServer(addWaterReasonPreSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: 2/21/2022 borewell pump part
////
////    public void getBorewellPumpListFromLocalDBNotSync() {
////        try {
////            boreWellPumpListFromLocalDBLiveDataNotSync = appRepository.getBoreWellPumpSeasonDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddBoreWellSeasonTable>> getAddBoreWellPumpSeasonDetailsListNotSyncLiveData() {
////        return boreWellPumpListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncBoreWellPumpDetailsDataToServer(AddBoreWellSeasonTable addBoreWellSeasonTable) {
////        try {
////            stringLiveData = appRepository.syncAddBoreWellPumpSeasonDetailsToServer(addBoreWellSeasonTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////
////    // TODO: 2/21/2022 water feild part
////    public void getWaterFieldListFromLocalDBNotSync() {
////        try {
////            waterSeasonFieldListFromLocalDBLiveDataNotSync = appRepository.getWaterSeasonFieldDetailsDetailslistFromLocalDBNotSync();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public LiveData<List<AddWaterSeasonFeildTable>> getAddWaterSeasonFeildTableListNotSyncLiveData() {
////        return waterSeasonFieldListFromLocalDBLiveDataNotSync;
////    }
////
////
////    public void syncAddWaterSeasonFieldDetailsDataToServer(AddWaterSeasonFeildTable addWaterSeasonFeildTable) {
////        try {
////            stringLiveData = appRepository.syncAddWaterSeasonFeildTableDetailsToServer(addWaterSeasonFeildTable);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//
//    // TODO: 2/21/2022 harvest part
//    public void getHarVestListFromLocalDBNotSync() {
//        try {
//            harvestListFromLocalDBLiveDataNotSync = appRepository.getHarvestDetailsDetailslistFromLocalDBNotSync();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public LiveData<List<AddHarvestDetailsTable>> getAddHarvestDetailsListNotSyncLiveData() {
//        return harvestListFromLocalDBLiveDataNotSync;
//    }
//
//
//    public void syncAddHarvestDetailsDataToServer(AddHarvestDetailsTable addHarvestDetailsTable) {
//        try {
//            stringLiveData = appRepository.syncAddHarvestDetailsToServer(addHarvestDetailsTable);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//



//    public LiveData<Integer> getCountComplaintDetails() {
//        return appRepository.getCountComplaintDetailCount();
//    }
//
//    public void insertComplainImagesToServer(List<SavingComplainImagesTable> savingComplainImagesTableList, String logbookno) {
//        try {
//            savingComplainImagesTableLiveData = appRepository.insertSavingOfComplainMultipleImages(savingComplainImagesTableList,logbookno);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public void insertAddComplainFormTableLocalDb(AddComplaintsDetailsTable addComplaintsDetailsTable) {
//
//        try {
//            addComplaintsDetailsTableLiveData = appRepository.insertAddComplainFormTable(addComplaintsDetailsTable);
//            getAddComplainFormDetailsFromLocalDBLiveData = appRepository.getAddComplainFormTable();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public LiveData<AddComplaintsDetailsTable> getAddComplainformTableLiveDataFromLocalDB() {
//        return addComplaintsDetailsTableLiveData;
//    }
//
//    public LiveData<List<AddComplaintsDetailsTable>> getAddComplainformTableLiveDataFromLocalDBList() {
//        return appRepository.getAddComplainFormTable();
//    }
//
//
//    public LiveData<List<SavingComplainImagesTable>> getSavingComplainImagesNotSyncLiveDataList() {
//        return appRepository.getComplainImagesFromLocalDBNotSync();
//    }
//}


//    // TODO: sync to server methods
//    public void syncFormerDetailsDataToServer(FarmerDetailListTable farmerDetailListTable){
//        try{
//            stringLiveData=appRepository.syncFormerDetailsDataToServer(farmerDetailListTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncLandDetailsDataToServer(LandDetailsListTable landDetailsListTable){
//        try{
//            stringLiveData=appRepository.syncLandDetailsDataToServer(landDetailsListTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentifcationDetailsDataToServer(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable){
//        try{
//            stringLiveData=appRepository.syncDocIdentificationDetailsDataToServer(docIdentiFicationDeatilsTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//
//    public void syncBankDetailsSubmitTableDataToServer(BankDetailsSubmitTable bankDetailsSubmitTable){
//        try{
//            stringLiveData=appRepository.syncBankDetailsDataToServer(bankDetailsSubmitTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void syncDocIdentificationDetailsDataToServer(String codeUser,String strPicUrl ,String strFileExtension,String typeOfReq,String identityCode ){
//        try{
//            stringLiveData=appRepository.syncDocIdentificationDetailsToServer(codeUser,strPicUrl,strFileExtension,typeOfReq,identityCode);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }

