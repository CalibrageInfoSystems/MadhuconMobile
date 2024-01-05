package com.trst01.locationtracker.repositories;

import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;
import static com.trst01.locationtracker.constant.AppConstant.FAILURE_RESPONSE_MESSAGE;
import static com.trst01.locationtracker.constant.AppConstant.RAW_DATA_URL;
import static com.trst01.locationtracker.constant.AppConstant.SUCCESS_RESPONSE_MESSAGE;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.trst01.locationtracker.constant.AppConstant;

import com.trst01.locationtracker.constant.AppHelper;
import com.trst01.locationtracker.database.AppDatabase;
import com.trst01.locationtracker.database.dao.AppDAO;
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
import com.trst01.locationtracker.models.LoginResponseDTO;
import com.trst01.locationtracker.models.TransactionSyncResponseDTO;
import com.trst01.locationtracker.services.api.AppAPI;
import com.trst01.locationtracker.services.webservice.AppWebService;
import com.trst01.locationtracker.view_models.OnInsertCallback;
import com.trst01.locationtracker.view_models.OnInsertionCompleteListener;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AppRepository {

    private static final String TAG = com.trst01.locationtracker.repositories.AppRepository.class.getCanonicalName();

    private final AppDAO appDAO;
//    private final Executor executor;
    private Executor executor;
    private final AppHelper appHelper;
    Context context;



    public AppRepository(AppDAO appDAO, Executor executor, AppHelper appHelper, Context context) {
        this.appDAO = appDAO;
        this.executor = executor;
        this.appHelper = appHelper;
        this.context = context;
    }




//    // TODO: LOG IN SERVICE CALL
//    public LiveData<LoginResponseDTO> logInService(String userId) {
//        final MutableLiveData<LoginResponseDTO> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(LOGIN_URL);
//
////            final LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
////            loginRequestDTO.setDeviceID(userId);
//
//            executor.execute(() -> {
//
//                AppWebService.createService(AppAPI.class).logInService(userId).enqueue(new Callback<LoginResponseDTO>() {
//                    @Override
//                    public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
//                        Log.e(TAG, "DATA REFRESHED FROM NETWORK");
//                        executor.execute(() -> {
//                            Log.e(TAG, "onResponse: Data" + response.body());
//                            LoginResponseDTO loginResponseDTO = response.body();
//                            Log.d(TAG, "onResponse  ==> " + loginResponseDTO);
//                            data.postValue(loginResponseDTO);
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
//                        Log.d(TAG, "onFailure ==> " + t.getMessage());
//                        data.postValue(new LoginResponseDTO());
//                    }
//                });
//
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            data.postValue(new LoginResponseDTO());
//        }
//        return data;
//    }
//
//
    // TODO: get lot code details
//    public LiveData<List<LoginResponseDTO>> getlogInServiceResponse(String userId) {
//        final MutableLiveData<List<LoginResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//
//                if (appHelper.isNetworkAvailable()) { // TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getlogInService(userId)
//                            .enqueue(new Callback<List<LoginResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<LoginResponseDTO>> call, Response<List<LoginResponseDTO>> response) {
//                                    Log.e("TAG", "Login LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<LoginResponseDTO> loginResponseDTOList = response.body();
//                                        if (loginResponseDTOList != null && loginResponseDTOList.size() > 0) {
//                                            // TODO: Delete & Insert Stage List
//                                            for (LoginResponseDTO loginResponseDTO : loginResponseDTOList) {
//
//                                                // if (cropDetailsTable != null && !TextUtils.isEmpty(cropDetailsTable.getId())) {
//                                                LoginResponseDTO loginResponseDTO1 = new LoginResponseDTO();
//                                                Log.d("deviceId", loginResponseDTO.getId());
//                                                loginResponseDTO1.setId(loginResponseDTO.getId());
//                                                loginResponseDTO1.setUserName(loginResponseDTO.getUserName());
//                                                loginResponseDTO1.setPassword(loginResponseDTO.getPassword());
//                                                loginResponseDTO1.setAccessToken(loginResponseDTO.getAccessToken());
////                                                loginResponseDTO1.setDeviceUserID(loginResponseDTO.getDeviceUserID());
////                                                loginResponseDTO1.setDeviceUserID(loginResponseDTO.getDeviceUserID());
////
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(loginResponseDTOList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<LoginResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<LoginResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<LoginResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<LoginResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }

//
//    public LiveData<CurrentVisitFarmerTables> insertOrUpdateCurrentVisitFarmerTablesTable(CurrentVisitFarmerTables currentVisitFarmerTables) {
//        final MutableLiveData<CurrentVisitFarmerTables> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
//            data.postValue(appDAO.getTopCurrentVisitFarmerTablesData(currentVisitFarmerTables.getUserId()));
//        });
//        return data;
//    }
//
//
//    public LiveData<FarmerTable> insertOrUpdateFarmerDetailListTableTable(FarmerTable farmerTable) {
//        final MutableLiveData<FarmerTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertFarmerDetailListTable(farmerTable);
////            boolean dataExist = (appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFarmerCode()) != null);
////            if (dataExist) {
////                data.postValue(appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFarmerCode()));
////                farmerDetailListTable.setFarmerId(farmerDetailListTable.getFarmerId());
////                appDAO.insertFarmerDetailListTable(farmerDetailListTable);
////            } else {
////
////            }
//            //FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
////            if (topFarmerDetailListTableTableData != null) {
////                topFarmerDetailListTableTableData.setFarmerId(farmerDetailListTable.getFarmerId());
////                appDAO.insertFarmerDetailListTable(farmerDetailListTable);
////            } else {
////                appDAO.insertFarmerDetailListTable(farmerDetailListTable);
////            }
//            data.postValue(appDAO.getTopFarmerDetailListTableTableData(farmerTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//
//    public LiveData<FarmerTable> insertFarmerDetailListTableTable(FarmerTable farmerTable) {
//        final MutableLiveData<FarmerTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertFarmerDetailListTableLocalDB(farmerTable);
//            data.postValue(appDAO.geInsertFarmerDetailListTableTableData(farmerTable.getFarmerCode()));
//        });
//        return data;
//    }


//    boolean dataExist = (appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFarmerCode()) != null);
//            if (dataExist) {
//        data.postValue(appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFarmerCode()));
//        farmerDetailListTable.setFarmerId(farmerDetailListTable.getFarmerId());
//        appDAO.insertFarmerDetailListTable(farmerDetailListTable);
//    } else {
//        appDAO.insertFarmerDetailListTable(farmerDetailListTable);
//    }
//    //FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
////            if (topFarmerDetailListTableTableData != null) {
////                topFarmerDetailListTableTableData.setFarmerId(farmerDetailListTable.getFarmerId());
////                appDAO.insertFarmerDetailListTable(farmerDetailListTable);
////            } else {
////                appDAO.insertFarmerDetailListTable(farmerDetailListTable);
////            }
//            data.postValue(appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFarmerCode()));
//});
//
//
//    public LiveData<DocIdentiFicationDeatilsTable> insertDocIntoLocalDB(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        final MutableLiveData<DocIdentiFicationDeatilsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertDocDetailListTable(docIdentiFicationDeatilsTable);
//
////            FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
////            if (topFarmerDetailListTableTableData != null) {
////                topFarmerDetailListTableTableData.setFarmerId(farmerDetailListTable.getFarmerId());
////                appDAO.insertFarmerDetailListTable(farmerDetailListTable);
////            } else {
////
////            }
////            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData( appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////            if (currentVisitFarmerTables != null) {
////                currentVisitFarmerTables.setDocumentsVisit(false);
////                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////            }
//            // TODO: Sending result
//            data.postValue(appDAO.getTopDocListTableDataFromLocal(docIdentiFicationDeatilsTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//    public LiveData<BankDetailsSubmitTable> insertOrUpdateBankDetailsSubmitTable(BankDetailsSubmitTable bankDetailsSubmitTable) {
//        final MutableLiveData<BankDetailsSubmitTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertBankDetailListTable(bankDetailsSubmitTable);
////            BankDetailsSubmitTable topFarmerDetailListTableTableData = appDAO.getTopBankDetailsSubmitTableData(bankDetailsSubmitTable.getFarmerCode());
////            if (topFarmerDetailListTableTableData != null) {
////                topFarmerDetailListTableTableData.setBankID(bankDetailsSubmitTable.getBankID());
////                appDAO.insertBankDetailListTable(bankDetailsSubmitTable);
////            } else {
////                appDAO.insertBankDetailListTable(bankDetailsSubmitTable);
////            }
////            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData( appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////            if (currentVisitFarmerTables != null) {
////                currentVisitFarmerTables.setBankVisit(false);
////                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////            }
//            // TODO: Sending result
//            data.postValue(appDAO.getTopBankDetailsSubmitTableData(bankDetailsSubmitTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//
//    public LiveData<SavingFarmerProfieImagesTable> insertSavingOfFarmerMultipleImages(SavingFarmerProfieImagesTable savingFarmerProfieImagesTable) {
//        final MutableLiveData<SavingFarmerProfieImagesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertSavingFarmerProfileImages(savingFarmerProfieImagesTable);
//            data.postValue(appDAO.getTopFarmerProfileImageFromLocalDb(savingFarmerProfieImagesTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//
//    public LiveData<SavingPlotProfieImagesTable> insertSavingOfPlotMultipleImages(SavingPlotProfieImagesTable savingPlotProfieImagesTable) {
//        final MutableLiveData<SavingPlotProfieImagesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertSavingPlotProfileImages(savingPlotProfieImagesTable);
//            data.postValue(appDAO.getTopPlotProfileImageFromLocalDb(savingPlotProfieImagesTable.getPlotNo()));
//        });
//        return data;
//    }
//
//    public LiveData<GeoBoundariesTable> insertGoeDataIntolocaDB(GeoBoundariesTable geoBoundariesTable) {
//        final MutableLiveData<GeoBoundariesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertGeoData(geoBoundariesTable);
//            data.postValue(appDAO.getTopGeoBoundariesTableFromLocalDb(geoBoundariesTable.getPlotNo()));
//        });
//        return data;
//    }
//
//
//    public LiveData<List<GeoBoundariesTable>> insertGoeBoundariesListDataIntolocaDB(List<GeoBoundariesTable> geoBoundariesTable) {
//        final MutableLiveData<List<GeoBoundariesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertGeoBoundariesList(geoBoundariesTable);
//           // data.postValue(appDAO.getTopListGeoBoundariesTableFromLocalDb(geoBoundariesTable.getPlotNo()));
//        });
//        return data;
//    }
//
//    public LiveData<SavingBankImagesTable> insertSavingOfBankMultipleImages(SavingBankImagesTable savingBankImagesTable) {
//        final MutableLiveData<SavingBankImagesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertSavingBankProfileImages(savingBankImagesTable);
//            data.postValue(appDAO.getTopBankProfileImageFromLocalDb(savingBankImagesTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//    public LiveData<LogBookModulesStatusDetailsTable> insertSavingOfLogBookModulesStatusDetailsTable(LogBookModulesStatusDetailsTable logBookModulesStatusDetailsTable) {
//        final MutableLiveData<LogBookModulesStatusDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertLogBookModulesStatusDetailsTable(logBookModulesStatusDetailsTable);
//            data.postValue(appDAO.getTopLogBookModulesStatusDetailsTableFromLocalDb(logBookModulesStatusDetailsTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<DocIdentiFicationDeatilsTable> insertOrUpdateDocIdentificationDetailListTable(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        final MutableLiveData<DocIdentiFicationDeatilsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            DocIdentiFicationDeatilsTable topFarmerDetailListTableTableData = appDAO.getDocIdentiFicationDeatilsTableData(String.valueOf(docIdentiFicationDeatilsTable.getFarmerCode()));
////            if (topFarmerDetailListTableTableData != null) {
////                topFarmerDetailListTableTableData.setID(docIdentiFicationDeatilsTable.getID());
////                appDAO.insertDocDetailListTable(docIdentiFicationDeatilsTable);
////            } else {
////
////            }
//            appDAO.insertDocDetailListTable(docIdentiFicationDeatilsTable);
////            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData( appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////            if (currentVisitFarmerTables != null) {
////                currentVisitFarmerTables.setDocumentsVisit(false);
////                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////            }
//            // TODO: Sending result
//            data.postValue(appDAO.getDocIdentiFicationDeatilsTableData(docIdentiFicationDeatilsTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//
//    // TODO: local save data
//    public LiveData<GeoBoundariesTable> insertGeoBoundariesValuesIntoLocalDb(GeoBoundariesTable boundariesTable) {
//        final MutableLiveData<GeoBoundariesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertGeoBoundariesTableList(boundariesTable);
//            data.postValue(appDAO.getGeoBoundariesTableData());
//        });
//        return data;
//    }
//
//
//    // TODO: local save data
//    public LiveData<PlotDetailsListTable> insertOrUpdateinsertOrUpdatePlotDetails(PlotDetailsListTable plotDetailsListTable) {
//        final MutableLiveData<PlotDetailsListTable> data = new MutableLiveData<>();
//
//        executor.execute(() -> {
//            //LandDetailsListTable topLandDetailsListTableData = appDAO.getTopPlotDetailListTableData(landDetailsListTable.getFarmerCode(), landDetailsListTable.getPlotNo());
////            if (topLandDetailsListTableData != null) {
////                landDetailsListTable.setPlotId(topLandDetailsListTableData.getPlotId());
////                appDAO.insertPlotDetailListTable(landDetailsListTable);
////            } else {
////
////            }
//            appDAO.insertPlotDetailListTable(plotDetailsListTable);
////            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData( appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////            if (currentVisitFarmerTables != null) {
////                currentVisitFarmerTables.setLandDetailsVisit(false);
////                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////            }
//            // TODO: Sending result
//            data.postValue(appDAO.getTopPlotDetailListTableDataInsert(plotDetailsListTable.getFarmerCode()));
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncFormerDetailsDataToServer(FarmerTable farmerTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Farmer spNameFarmerPersonalDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.Farmer();
//
//            if (!TextUtils.isEmpty(farmerTable.getFarmerCode())) {
//                spNameFarmerPersonalDetailsClass.setFarmerCode(farmerTable.getFarmerCode());
//            } else {
//                spNameFarmerPersonalDetailsClass.setFarmerCode("");
//            }
//            if (!TextUtils.isEmpty(farmerTable.getFarmerTitle())) {
//                spNameFarmerPersonalDetailsClass.setFarmerTittle(farmerTable.getFarmerTitle());
//            } else {
//                spNameFarmerPersonalDetailsClass.setFarmerTittle("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getFirstName())) {
//                spNameFarmerPersonalDetailsClass.setFirstName(farmerTable.getFirstName());
//            } else {
//                spNameFarmerPersonalDetailsClass.setFirstName("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getMiddleName())) {
//                spNameFarmerPersonalDetailsClass.setMiddleName(farmerTable.getMiddleName());
//            } else {
//                spNameFarmerPersonalDetailsClass.setMiddleName("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getLastName())) {
//                spNameFarmerPersonalDetailsClass.setLastName(farmerTable.getLastName());
//            } else {
//                spNameFarmerPersonalDetailsClass.setLastName("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getFatherName())) {
//                spNameFarmerPersonalDetailsClass.setFatherName(farmerTable.getFatherName());
//            } else {
//                spNameFarmerPersonalDetailsClass.setFatherName("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getGender())) {
//                spNameFarmerPersonalDetailsClass.setGender(farmerTable.getGender());
//            } else {
//                spNameFarmerPersonalDetailsClass.setGender("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getAge())) {
//                spNameFarmerPersonalDetailsClass.setAge(farmerTable.getAge());
//            } else {
//                spNameFarmerPersonalDetailsClass.setAge("");
//            }
//
//            if (!farmerTable.isSync()) {
//                spNameFarmerPersonalDetailsClass.setSync(true);
//            } else {
//                spNameFarmerPersonalDetailsClass.setSync(false);
//            }
//
//
//            if (!TextUtils.isEmpty(farmerTable.getPrimaryContactNum())) {
//                spNameFarmerPersonalDetailsClass.setPrimaryContactNum(farmerTable.getPrimaryContactNum());
//            } else {
//                spNameFarmerPersonalDetailsClass.setPrimaryContactNum("");
//            }
////
////            if (!TextUtils.isEmpty(farmerTable.getSecondaryContactNum())) {
////                spNameFarmerPersonalDetailsClass.setSecondaryContactNum(farmerTable.getSecondaryContactNum());
////            } else {
////                spNameFarmerPersonalDetailsClass.setSecondaryContactNum("");
////            }
////
//
//            if (!TextUtils.isEmpty(farmerTable.getAddress())) {
//                spNameFarmerPersonalDetailsClass.setAddress(farmerTable.getAddress());
//            } else {
//                spNameFarmerPersonalDetailsClass.setAddress("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getPinCode())) {
//                spNameFarmerPersonalDetailsClass.setPinCode(farmerTable.getPinCode());
//            } else {
//                spNameFarmerPersonalDetailsClass.setPinCode("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getVillageId())) {
//                spNameFarmerPersonalDetailsClass.setVillageId(farmerTable.getVillageId());
//            } else {
//                spNameFarmerPersonalDetailsClass.setVillageId("");
//            }
//
////            if (!TextUtils.isEmpty(farmerTable.getStateId())) {
////                spNameFarmerPersonalDetailsClass.setStateId(farmerTable.getStateId());
////            } else {
////                spNameFarmerPersonalDetailsClass.setStateId("");
////            }
////
////            if (!TextUtils.isEmpty(farmerTable.getMandalId())) {
////                spNameFarmerPersonalDetailsClass.setMandalId(farmerTable.getMandalId());
////            } else {
////                spNameFarmerPersonalDetailsClass.setMandalId("");
////            }
////
////            if (!TextUtils.isEmpty(farmerTable.getDistrictId())) {
////                spNameFarmerPersonalDetailsClass.setDistrictId(farmerTable.getDistrictId());
////            } else {
////                spNameFarmerPersonalDetailsClass.setDistrictId("");
////            }
//
////            if (!TextUtils.isEmpty(farmerDetailListTable.getCluster())) {
////                spNameFarmerPersonalDetailsClass.setCluster(farmerDetailListTable.getCluster());
////            } else {
////                spNameFarmerPersonalDetailsClass.setCluster("");
////            }
//
//
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
////            spNameFarmerPersonalDetailsClass.setCreatedDate(dateTime);
////            spNameFarmerPersonalDetailsClass.setUpdatedDate(dateTime);
//
//
//            if (!TextUtils.isEmpty(farmerTable.getIsActive())) {
//                spNameFarmerPersonalDetailsClass.setIsActive(farmerTable.getIsActive());
//            } else {
//                spNameFarmerPersonalDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(farmerTable.getCreatedByUserId())) {
//                spNameFarmerPersonalDetailsClass.setCreatedByUserId(farmerTable.getCreatedByUserId());
//            } else {
//                spNameFarmerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getUpdatedByUserId())) {
//                spNameFarmerPersonalDetailsClass.setUpdatedByUserId(farmerTable.getUpdatedByUserId());
//            } else {
//                spNameFarmerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getUpdatedDate())) {
//                spNameFarmerPersonalDetailsClass.setUpdatedDate(farmerTable.getUpdatedDate());
//            } else {
//                spNameFarmerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(farmerTable.getCreatedDate())) {
//                spNameFarmerPersonalDetailsClass.setCreatedDate(farmerTable.getCreatedDate());
//            } else {
//                spNameFarmerPersonalDetailsClass.setCreatedDate("");
//            }
//
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Farmer> spNameFarmerPersonalDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Farmer>();
//            spNameFarmerPersonalDetailsClasses.add(spNameFarmerPersonalDetailsClass);
//            syncPersonalLandAllDetailsRequestDTO.setFarmer(spNameFarmerPersonalDetailsClasses);
//
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
//
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//                                    if (status.equals("1")) {
//                                        FarmerTable businessReviewSurveyTableFromDB = appDAO.getTopFarmerDetailListTableTableData(farmerTable.getFarmerCode());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setFarmerId(businessReviewSurveyTableFromDB.getFarmerId());
//                                            farmerTable.setSync(true);
//                                            farmerTable.setPushToServer("1");
//                                            appDAO.insertFarmerDetailListTable(farmerTable);
//                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                        }
//
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//
//                                    // String strResponse = response.body().string();
//                                    //  String strStatus = json.getString("status");
//
////                                        CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                        if (currentVisitFarmerTables != null) {
////                                            currentVisitFarmerTables.setFarmerTableVisit(true);
////                                            appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                        }
////
////                                    }
//
//
//                                    //  farmerDetailListTable.setSync(true);
//                                    // TODO: insert current visit
//
////                                    List<FarmerDetailListTable> customerSurveyTableList = appDAO.getFarmerDetailListTableListFromLocalDB(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
////                                    if (customerSurveyTableList != null && customerSurveyTableList.size() > 0) {
////                                        boolean allDataSynced = true;
////                                        for (FarmerDetailListTable customerSurveyTableSync : customerSurveyTableList) {
////                                            if (customerSurveyTableSync != null && !customerSurveyTableSync.isSync()) {
////                                                allDataSynced = false;
////                                                break;
////                                            }
////                                        }
//
////                                        if (allDataSynced) {
////
////                                            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                            if (currentVisitFarmerTables != null) {
////                                                currentVisitFarmerTables.setFarmerTableVisit(true);
////                                                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                            }
////                                        }
//
//
//                                    // TODO: Sending result
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//                        //                        String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData "+response);
////                        JSONArray jsonArray = new JSONArray(response.body());
////                                    Log.d(TAG, "onResponse: datavalue" + jsonArray);
////                        String message ="",status="";
////                                    for (int i=0; i<jsonArray.length();i++)
////                        {
////                            JSONObject json = jsonArray.getJSONObject(i);
////                            Log.e(TAG, "onResponse: data json" + json );
////                            message = json.optString("message");
////                            status = json.optString("status");
////                            Log.d(TAG, "status "+ status);
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncFarmerProfileImagesDetaisToServer(SavingFarmerProfieImagesTable savingFarmerProfieImagesTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Doc spNameDoc = new SyncPersonalLandAllDetailsRequestDTO.Doc();
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getFarmerCode())) {
//                spNameDoc.setFarmerCode(savingFarmerProfieImagesTable.getFarmerCode());
//            } else {
//                spNameDoc.setFarmerCode("");
//            }
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getDocExtension())) {
//                spNameDoc.setDocExtension(savingFarmerProfieImagesTable.getDocExtension());
//            } else {
//                spNameDoc.setDocExtension("");
//            }
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getDocUrl())) {
//                spNameDoc.setDocUrl(savingFarmerProfieImagesTable.getDocUrl());
//            } else {
//                spNameDoc.setDocUrl("");
//            }
//
//
//            spNameDoc.setDocOldNum("");
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getDocType())) {
//                spNameDoc.setDocType(savingFarmerProfieImagesTable.getDocType());
//            } else {
//                spNameDoc.setDocType("");
//            }
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getIsActive())) {
//                spNameDoc.setIsActive(savingFarmerProfieImagesTable.getIsActive());
//            } else {
//                spNameDoc.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getCreatedByUserId())) {
//                spNameDoc.setCreatedByUserId(savingFarmerProfieImagesTable.getCreatedByUserId());
//            } else {
//                spNameDoc.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getUpdatedByUserId())) {
//                spNameDoc.setUpdatedByUserId(savingFarmerProfieImagesTable.getUpdatedByUserId());
//            } else {
//                spNameDoc.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getUpdatedDate())) {
//                spNameDoc.setUpdatedDate(savingFarmerProfieImagesTable.getUpdatedDate());
//            } else {
//                spNameDoc.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(savingFarmerProfieImagesTable.getCreatedDate())) {
//                spNameDoc.setCreatedDate(savingFarmerProfieImagesTable.getCreatedDate());
//            } else {
//                spNameDoc.setCreatedDate("");
//            }
//
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> spNameDocDetailsClass = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc>();
//            spNameDocDetailsClass.add(spNameDoc);
//            syncPersonalLandAllDetailsRequestDTO.setDoc(spNameDocDetailsClass);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
//
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//
//                                    if (status.equals("1")) {
//                                        SavingFarmerProfieImagesTable businessReviewSurveyTableFromDB = appDAO.getTopFarmerProfileImageFromLocalDb(savingFarmerProfieImagesTable.getFarmerCode());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setID(businessReviewSurveyTableFromDB.getID());
//                                            savingFarmerProfieImagesTable.setSync(true);
//                                            savingFarmerProfieImagesTable.setServerStatus("1");
//                                            appDAO.insertSavingFarmerProfileImages(savingFarmerProfieImagesTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//                                    // String strResponse = response.body().string();
//                                    //  String strStatus = json.getString("status");
//
////                                        CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                        if (currentVisitFarmerTables != null) {
////                                            currentVisitFarmerTables.setFarmerTableVisit(true);
////                                            appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                        }
////
////                                    }
//
//
//                                    //  farmerDetailListTable.setSync(true);
////                                    List<FarmerDetailListTable> customerSurveyTableList = appDAO.getFarmerDetailListTableListFromLocalDB(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
////                                    if (customerSurveyTableList != null && customerSurveyTableList.size() > 0) {
////                                        boolean allDataSynced = true;
////                                        for (FarmerDetailListTable customerSurveyTableSync : customerSurveyTableList) {
////                                            if (customerSurveyTableSync != null && !customerSurveyTableSync.isSync()) {
////                                                allDataSynced = false;
////                                                break;
////                                            }
////                                        }
//
////                                        if (allDataSynced) {
////
////                                            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                            if (currentVisitFarmerTables != null) {
////                                                currentVisitFarmerTables.setFarmerTableVisit(true);
////                                                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                            }
////                                        }
//
//
//                                    // TODO: Sending result
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//                        //                        String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData "+response);
////                        JSONArray jsonArray = new JSONArray(response.body());
////                                    Log.d(TAG, "onResponse: datavalue" + jsonArray);
////                        String message ="",status="";
////                                    for (int i=0; i<jsonArray.length();i++)
////                        {
////                            JSONObject json = jsonArray.getJSONObject(i);
////                            Log.e(TAG, "onResponse: data json" + json );
////                            message = json.optString("message");
////                            status = json.optString("status");
////                            Log.d(TAG, "status "+ status);
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncPlotProfileImagesDetaisToServer(SavingPlotProfieImagesTable savingPlotProfieImagesTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Doc spNameDoc = new SyncPersonalLandAllDetailsRequestDTO.Doc();
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getPlotNo())) {
//                spNameDoc.setPlotNo(savingPlotProfieImagesTable.getPlotNo());
//            } else {
//                spNameDoc.setPlotNo("");
//            }
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getDocExtension())) {
//                spNameDoc.setDocExtension(savingPlotProfieImagesTable.getDocExtension());
//            } else {
//                spNameDoc.setDocExtension("");
//            }
//            spNameDoc.setDocOldNum("");
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getDocUrl())) {
//                spNameDoc.setDocUrl(savingPlotProfieImagesTable.getDocUrl());
//            } else {
//                spNameDoc.setDocUrl("");
//            }
//
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getDocType())) {
//                spNameDoc.setDocType(savingPlotProfieImagesTable.getDocType());
//            } else {
//                spNameDoc.setDocType("");
//            }
//
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getIsActive())) {
//                spNameDoc.setIsActive(savingPlotProfieImagesTable.getIsActive());
//            } else {
//                spNameDoc.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getCreatedByUserId())) {
//                spNameDoc.setCreatedByUserId(savingPlotProfieImagesTable.getCreatedByUserId());
//            } else {
//                spNameDoc.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getUpdatedByUserId())) {
//                spNameDoc.setUpdatedByUserId(savingPlotProfieImagesTable.getUpdatedByUserId());
//            } else {
//                spNameDoc.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getUpdatedDate())) {
//                spNameDoc.setUpdatedDate(savingPlotProfieImagesTable.getUpdatedDate());
//            } else {
//                spNameDoc.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(savingPlotProfieImagesTable.getCreatedDate())) {
//                spNameDoc.setCreatedDate(savingPlotProfieImagesTable.getCreatedDate());
//            } else {
//                spNameDoc.setCreatedDate("");
//            }
//
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> spNameDocDetailsClass = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc>();
//            spNameDocDetailsClass.add(spNameDoc);
//            syncPersonalLandAllDetailsRequestDTO.setDoc(spNameDocDetailsClass);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
//
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//
//                                    if (status.equals("1")) {
//                                        SavingPlotProfieImagesTable businessReviewSurveyTableFromDB = appDAO.getTopPlotProfileImageFromLocalDb(savingPlotProfieImagesTable.getPlotNo());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setID(businessReviewSurveyTableFromDB.getID());
//                                            savingPlotProfieImagesTable.setSync(true);
//                                            savingPlotProfieImagesTable.setServerStatus("1");
//                                            appDAO.insertSavingPlotProfileImages(savingPlotProfieImagesTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//                                    // String strResponse = response.body().string();
//                                    //  String strStatus = json.getString("status");
//
////                                        CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                        if (currentVisitFarmerTables != null) {
////                                            currentVisitFarmerTables.setFarmerTableVisit(true);
////                                            appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                        }
////
////                                    }
//
//
//                                    //  farmerDetailListTable.setSync(true);
//
//                                    Log.d(TAG, "raw data from server");
//                                    // TODO: insert current visit
//
////                                    List<FarmerDetailListTable> customerSurveyTableList = appDAO.getFarmerDetailListTableListFromLocalDB(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
////                                    if (customerSurveyTableList != null && customerSurveyTableList.size() > 0) {
////                                        boolean allDataSynced = true;
////                                        for (FarmerDetailListTable customerSurveyTableSync : customerSurveyTableList) {
////                                            if (customerSurveyTableSync != null && !customerSurveyTableSync.isSync()) {
////                                                allDataSynced = false;
////                                                break;
////                                            }
////                                        }
//
////                                        if (allDataSynced) {
////
////                                            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                            if (currentVisitFarmerTables != null) {
////                                                currentVisitFarmerTables.setFarmerTableVisit(true);
////                                                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                            }
////                                        }
//
//
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//                        //                        String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData "+response);
////                        JSONArray jsonArray = new JSONArray(response.body());
////                                    Log.d(TAG, "onResponse: datavalue" + jsonArray);
////                        String message ="",status="";
////                                    for (int i=0; i<jsonArray.length();i++)
////                        {
////                            JSONObject json = jsonArray.getJSONObject(i);
////                            Log.e(TAG, "onResponse: data json" + json );
////                            message = json.optString("message");
////                            status = json.optString("status");
////                            Log.d(TAG, "status "+ status);
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncBankProfileImagesDetaisToServer(SavingBankImagesTable savingBankImagesTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Doc spNameDoc = new SyncPersonalLandAllDetailsRequestDTO.Doc();
//            if (!TextUtils.isEmpty(savingBankImagesTable.getFarmerCode())) {
//                spNameDoc.setFarmerCode(savingBankImagesTable.getFarmerCode());
//            } else {
//                spNameDoc.setFarmerCode("");
//            }
//
//            spNameDoc.setIdentityCode("IdentityCode");
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getDocExtension())) {
//                spNameDoc.setDocExtension(savingBankImagesTable.getDocExtension());
//            } else {
//                spNameDoc.setDocExtension("");
//            }
//            spNameDoc.setDocOldNum("");
//            if (!TextUtils.isEmpty(savingBankImagesTable.getDocUrl())) {
//                spNameDoc.setDocUrl(savingBankImagesTable.getDocUrl());
//            } else {
//                spNameDoc.setDocUrl("");
//            }
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getDocType())) {
//                spNameDoc.setDocType(savingBankImagesTable.getDocType());
//            } else {
//                spNameDoc.setDocType("");
//            }
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getIsActive())) {
//                spNameDoc.setIsActive(savingBankImagesTable.getIsActive());
//            } else {
//                spNameDoc.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getCreatedByUserId())) {
//                spNameDoc.setCreatedByUserId(savingBankImagesTable.getCreatedByUserId());
//            } else {
//                spNameDoc.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getUpdatedByUserId())) {
//                spNameDoc.setUpdatedByUserId(savingBankImagesTable.getUpdatedByUserId());
//            } else {
//                spNameDoc.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getUpdatedDate())) {
//                spNameDoc.setUpdatedDate(savingBankImagesTable.getUpdatedDate());
//            } else {
//                spNameDoc.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(savingBankImagesTable.getCreatedDate())) {
//                spNameDoc.setCreatedDate(savingBankImagesTable.getCreatedDate());
//            } else {
//                spNameDoc.setCreatedDate("");
//            }
//
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> spNameDocDetailsClass = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc>();
//            spNameDocDetailsClass.add(spNameDoc);
//            syncPersonalLandAllDetailsRequestDTO.setDoc(spNameDocDetailsClass);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
//
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//
//                                    if (status.equals("1")) {
//                                        SavingBankImagesTable businessReviewSurveyTableFromDB = appDAO.getTopBankProfileImageFromLocalDb(savingBankImagesTable.getFarmerCode());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setID(businessReviewSurveyTableFromDB.getID());
//                                            savingBankImagesTable.setSync(true);
//                                            savingBankImagesTable.setServerStatus("1");
//                                            appDAO.insertSavingBankProfileImages(savingBankImagesTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncLandDetailsDataToServer(PlotDetailsListTable plotDetailsListTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.PLot spNameLandDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.PLot();
//
//            if (!TextUtils.isEmpty(String.valueOf(plotDetailsListTable.getPlotNo()))) {
//                spNameLandDetailsClass.setPlotNo(String.valueOf(plotDetailsListTable.getPlotNo()));
//            } else {
//                spNameLandDetailsClass.setPlotNo("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(plotDetailsListTable.getSize()))) {
//                spNameLandDetailsClass.setSize(String.valueOf(plotDetailsListTable.getSize()));
//            } else {
//                spNameLandDetailsClass.setSize("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(plotDetailsListTable.getCropId()))) {
//                spNameLandDetailsClass.setCropId(String.valueOf(plotDetailsListTable.getCropId()));
//            } else {
//                spNameLandDetailsClass.setCropId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(plotDetailsListTable.getCropVarietyId()))) {
//                spNameLandDetailsClass.setCropId(String.valueOf(plotDetailsListTable.getCropVarietyId()));
//            } else {
//                spNameLandDetailsClass.setCropVarietyId("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getFarmerCode())) {
//                spNameLandDetailsClass.setFarmerCode(plotDetailsListTable.getFarmerCode());
//            } else {
//                spNameLandDetailsClass.setFarmerCode("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getLatitude())) {
//                spNameLandDetailsClass.setLatitude(plotDetailsListTable.getLatitude());
//            } else {
//                spNameLandDetailsClass.setLatitude("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getLongitude())) {
//                spNameLandDetailsClass.setLongitude(plotDetailsListTable.getLongitude());
//            } else {
//                spNameLandDetailsClass.setLongitude("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getAddress())) {
//                spNameLandDetailsClass.setAddress(plotDetailsListTable.getAddress());
//            } else {
//                spNameLandDetailsClass.setAddress("");
//            }
//            if (!TextUtils.isEmpty(plotDetailsListTable.getPlotownership())) {
//                spNameLandDetailsClass.setPlotownership(plotDetailsListTable.getPlotownership());
//            } else {
//                spNameLandDetailsClass.setPlotownership("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getCluster())) {
//                spNameLandDetailsClass.setCluster(plotDetailsListTable.getCluster());
//            } else {
//                spNameLandDetailsClass.setCluster("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getLandMark())) {
//                spNameLandDetailsClass.setLandMark(plotDetailsListTable.getLandMark());
//            } else {
//                spNameLandDetailsClass.setLandMark("");
//            }
//
////            if (!TextUtils.isEmpty(plotDetailsListTable.getDistrictId())) {
////                spNameLandDetailsClass.setDistrictId(plotDetailsListTable.getDistrictId());
////            } else {
////                spNameLandDetailsClass.setDistrictId("");
////            }
////
////
////            if (!TextUtils.isEmpty(plotDetailsListTable.getMandalId())) {
////                spNameLandDetailsClass.setMandalId(plotDetailsListTable.getMandalId());
////            } else {
////                spNameLandDetailsClass.setMandalId("");
////            }
////
////            if (!TextUtils.isEmpty(plotDetailsListTable.getStateId())) {
////                spNameLandDetailsClass.setStateId(plotDetailsListTable.getStateId());
////            } else {
////                spNameLandDetailsClass.setStateId("");
////            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getVillageId())) {
//                spNameLandDetailsClass.setVillageId(plotDetailsListTable.getVillageId());
//            } else {
//                spNameLandDetailsClass.setVillageId("");
//            }
//
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getPinCode())) {
//                spNameLandDetailsClass.setPinCode(plotDetailsListTable.getPinCode());
//            } else {
//                spNameLandDetailsClass.setPinCode("");
//            }
//
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getSurveyNo())) {
//                spNameLandDetailsClass.setSurveyNo(plotDetailsListTable.getSurveyNo());
//            } else {
//                spNameLandDetailsClass.setSurveyNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getPattadarBookNo())) {
//                spNameLandDetailsClass.setPattadarBookNo(plotDetailsListTable.getPattadarBookNo());
//            } else {
//                spNameLandDetailsClass.setPattadarBookNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(String.valueOf(plotDetailsListTable.getGPSPlotArea()))) {
//                spNameLandDetailsClass.setGPSPlotArea(String.valueOf(plotDetailsListTable.getGPSPlotArea()));
//            } else {
//                spNameLandDetailsClass.setGPSPlotArea("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getGPS())) {
//                spNameLandDetailsClass.setGPS(plotDetailsListTable.getGPS());
//            } else {
//                spNameLandDetailsClass.setGPS("");
//            }
//
//            if (!plotDetailsListTable.isSync()) {
//                spNameLandDetailsClass.setSync(true);
//            } else {
//                spNameLandDetailsClass.setSync(false);
//            }
//
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getIsActive())) {
//                spNameLandDetailsClass.setIsActive(plotDetailsListTable.getIsActive());
//            } else {
//                spNameLandDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getCreatedByUserId())) {
//                spNameLandDetailsClass.setCreatedByUserId(plotDetailsListTable.getCreatedByUserId());
//            } else {
//                spNameLandDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getUpdatedByUserId())) {
//                spNameLandDetailsClass.setUpdatedByUserId(plotDetailsListTable.getUpdatedByUserId());
//            } else {
//                spNameLandDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getUpdatedDate())) {
//                spNameLandDetailsClass.setUpdatedDate(plotDetailsListTable.getUpdatedDate());
//            } else {
//                spNameLandDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(plotDetailsListTable.getCreatedDate())) {
//                spNameLandDetailsClass.setCreatedDate(plotDetailsListTable.getCreatedDate());
//            } else {
//                spNameLandDetailsClass.setCreatedDate("");
//            }
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            spNameLandDetailsClass.setCreatedDate(dateTime);
////            spNameLandDetailsClass.setUpdatedDate(dateTime);
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.PLot> spNameLandDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.PLot>();
//            spNameLandDetailsClasses.add(spNameLandDetailsClass);
//            syncPersonalLandAllDetailsRequestDTO.setPLot(spNameLandDetailsClasses);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//
//                                    if (status.equals("1")) {
//                                        PlotDetailsListTable businessReviewSurveyTableFromDB = appDAO.getCheckServerStatusPlotData(plotDetailsListTable.getPlotNo());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setPlotId(businessReviewSurveyTableFromDB.getPlotId());
//                                            plotDetailsListTable.setSync(true);
//                                            plotDetailsListTable.setServerStatus("1");
//                                            appDAO.insertPlotDetailListTable(plotDetailsListTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncDocIdentificationDetailsDataToServer(DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Doc spNameLandDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.Doc();
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getFarmerCode())) {
//                spNameLandDetailsClass.setFarmerCode(docIdentiFicationDeatilsTable.getFarmerCode());
//            } else {
//                spNameLandDetailsClass.setFarmerCode("");
//            }
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getDocType())) {
//                spNameLandDetailsClass.setDocType(String.valueOf(docIdentiFicationDeatilsTable.getDocType()));
//            } else {
//                spNameLandDetailsClass.setDocType("");
//            }
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getDocUrl())) {
//                spNameLandDetailsClass.setDocUrl(docIdentiFicationDeatilsTable.getDocUrl());
//            } else {
//                spNameLandDetailsClass.setDocUrl("");
//            }
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getDocExtension())) {
//                spNameLandDetailsClass.setDocExtension(docIdentiFicationDeatilsTable.getDocExtension());
//            } else {
//                spNameLandDetailsClass.setDocExtension("");
//            }
//
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getDocOldNum())) {
//                spNameLandDetailsClass.setDocOldNum(docIdentiFicationDeatilsTable.getDocOldNum());
//            } else {
//                spNameLandDetailsClass.setDocOldNum("");
//            }
//
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            spNameLandDetailsClass.setCreatedDate(dateTime);
////            spNameLandDetailsClass.setUpdatedDate(dateTime);
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getIsActive())) {
//                spNameLandDetailsClass.setIsActive(docIdentiFicationDeatilsTable.getIsActive());
//            } else {
//                spNameLandDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getCreatedByUserId())) {
//                spNameLandDetailsClass.setCreatedByUserId(docIdentiFicationDeatilsTable.getCreatedByUserId());
//            } else {
//                spNameLandDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getUpdatedByUserId())) {
//                spNameLandDetailsClass.setUpdatedByUserId(docIdentiFicationDeatilsTable.getUpdatedByUserId());
//            } else {
//                spNameLandDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getUpdatedDate())) {
//                spNameLandDetailsClass.setUpdatedDate(docIdentiFicationDeatilsTable.getUpdatedDate());
//            } else {
//                spNameLandDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(docIdentiFicationDeatilsTable.getCreatedDate())) {
//                spNameLandDetailsClass.setCreatedDate(docIdentiFicationDeatilsTable.getCreatedDate());
//            } else {
//                spNameLandDetailsClass.setCreatedDate("");
//            }
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> spNameLandDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc>();
//            spNameLandDetailsClasses.add(spNameLandDetailsClass);
//            syncPersonalLandAllDetailsRequestDTO.setDoc(spNameLandDetailsClasses);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
////
//                                try {
//
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//                                    if (status.equals("1")) {
//                                        DocIdentiFicationDeatilsTable businessReviewSurveyTableFromDB = appDAO.getTopDocListTableDataFromLocal(docIdentiFicationDeatilsTable.getFarmerCode());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setID(businessReviewSurveyTableFromDB.getID());
//                                            docIdentiFicationDeatilsTable.setSync(true);
//                                            docIdentiFicationDeatilsTable.setServerPost("1");
//                                            appDAO.insertDocDetailListTable(docIdentiFicationDeatilsTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//
////                                    CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                    if (currentVisitFarmerTables != null) {
////                                        currentVisitFarmerTables.setDocumentsVisit(true);
////                                        appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                    }
//
//
////                                    List<LandDetailsListTable> customerSurveyTableList = appDAO.getLandDetaillistfromServer(landDetailsListTable.getFarmerCode(), landDetailsListTable.getPlotNo());
////                                    if (customerSurveyTableList != null && customerSurveyTableList.size() > 0) {
////                                        boolean allDataSynced = true;
////                                        for (LandDetailsListTable customerSurveyTableSync : customerSurveyTableList) {
////                                            if (customerSurveyTableSync != null && !customerSurveyTableSync.isSync()) {
////                                                allDataSynced = false;
////                                                break;
////                                            }
////                                        }
//
////                                        if (allDataSynced) {
////
////                                            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                            if (currentVisitFarmerTables != null) {
////                                                currentVisitFarmerTables.setLandDetailsVisit(true);
////                                                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                            }
////                                        }
//
//
//                                    // TODO: Sending result
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncBankDetailsDataToServer(BankDetailsSubmitTable bankDetailsSubmitTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Bank spNameLandDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.Bank();
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getFarmerCode())) {
//                spNameLandDetailsClass.setFarmerCode(bankDetailsSubmitTable.getFarmerCode());
//            } else {
//                spNameLandDetailsClass.setFarmerCode("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getIdentityCode())) {
//                spNameLandDetailsClass.setIdentityCode(String.valueOf(bankDetailsSubmitTable.getIdentityCode()));
//            } else {
//                spNameLandDetailsClass.setIdentityCode("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getAccountHolderName())) {
//                spNameLandDetailsClass.setAccountHolderName(bankDetailsSubmitTable.getAccountHolderName());
//            } else {
//                spNameLandDetailsClass.setAccountHolderName("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getAccountNumber())) {
//                spNameLandDetailsClass.setAccountNumber(bankDetailsSubmitTable.getAccountNumber());
//            } else {
//                spNameLandDetailsClass.setAccountNumber("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getBranchName())) {
//                spNameLandDetailsClass.setBranchName(bankDetailsSubmitTable.getBranchName());
//            } else {
//                spNameLandDetailsClass.setBranchName("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getIFSCCode())) {
//                spNameLandDetailsClass.setIFSCCode(bankDetailsSubmitTable.getIFSCCode());
//            } else {
//                spNameLandDetailsClass.setIFSCCode("");
//            }
//
//
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            spNameLandDetailsClass.setCreatedDate(dateTime);
////            spNameLandDetailsClass.setUpdatedDate(dateTime);
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getIsActive())) {
//                spNameLandDetailsClass.setIsActive(bankDetailsSubmitTable.getIsActive());
//            } else {
//                spNameLandDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getCreatedByUserId())) {
//                spNameLandDetailsClass.setCreatedByUserId(bankDetailsSubmitTable.getCreatedByUserId());
//            } else {
//                spNameLandDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getUpdatedByUserId())) {
//                spNameLandDetailsClass.setUpdatedByUserId(bankDetailsSubmitTable.getUpdatedByUserId());
//            } else {
//                spNameLandDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getUpdatedDate())) {
//                spNameLandDetailsClass.setUpdatedDate(bankDetailsSubmitTable.getUpdatedDate());
//            } else {
//                spNameLandDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(bankDetailsSubmitTable.getCreatedDate())) {
//                spNameLandDetailsClass.setCreatedDate(bankDetailsSubmitTable.getCreatedDate());
//            } else {
//                spNameLandDetailsClass.setCreatedDate("");
//            }
//
//            if (!(bankDetailsSubmitTable.isSync())) {
//                spNameLandDetailsClass.setSync(true);
//            } else {
//                spNameLandDetailsClass.setSync(false);
//            }
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Bank> spNameLandDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Bank>();
//            spNameLandDetailsClasses.add(spNameLandDetailsClass);
//            syncPersonalLandAllDetailsRequestDTO.setBank(spNameLandDetailsClasses);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//                                    if (status.equals("1")) {
//                                        BankDetailsSubmitTable businessReviewSurveyTableFromDB = appDAO.getTopBankDetailsSubmitTableData(bankDetailsSubmitTable.getFarmerCode());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setBankID(businessReviewSurveyTableFromDB.getBankID());
//                                            bankDetailsSubmitTable.setSync(true);
//                                            bankDetailsSubmitTable.setServerSubmit("1");
//                                            appDAO.insertBankDetailListTable(bankDetailsSubmitTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(message);
//                                    }
//
//
////                                    CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                    if (currentVisitFarmerTables != null) {
////                                        currentVisitFarmerTables.setBankVisit(true);
////                                        appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                    }
////                                    List<LandDetailsListTable> customerSurveyTableList = appDAO.getLandDetaillistfromServer(landDetailsListTable.getFarmerCode(), landDetailsListTable.getPlotNo());
////                                    if (customerSurveyTableList != null && customerSurveyTableList.size() > 0) {
////                                        boolean allDataSynced = true;
////                                        for (LandDetailsListTable customerSurveyTableSync : customerSurveyTableList) {
////                                            if (customerSurveyTableSync != null && !customerSurveyTableSync.isSync()) {
////                                                allDataSynced = false;
////                                                break;
////                                            }
////                                        }
//
////                                        if (allDataSynced) {
////
////                                            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                            if (currentVisitFarmerTables != null) {
////                                                currentVisitFarmerTables.setLandDetailsVisit(true);
////                                                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                            }
////                                        }
//
//
//                                    // TODO: Sending result
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncDocIdentificationDetailsToServer(
//            String codeUser, String strPicUrl, String strFileExtension,
//            String typeOfReq, String IdentityCode, String createDate,
//            String updatedDate) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Doc spNameDoc = new SyncPersonalLandAllDetailsRequestDTO.Doc();
//            if (!TextUtils.isEmpty(codeUser)) {
//                if (typeOfReq.equals("farmer")) {
//                    spNameDoc.setFarmerCode(codeUser);
//                } else if (typeOfReq.equals("land")) {
//                    spNameDoc.setPlotNo(codeUser);
//                } else if (typeOfReq.equals("bank")) {
//                    spNameDoc.setFarmerCode(codeUser);
//                }
//            } else {
//                spNameDoc.setPlotNo("");
//                spNameDoc.setFarmerCode("");
//            }
//
//
//            if (typeOfReq.equals("bank")) {
//                if (!TextUtils.isEmpty(IdentityCode)) {
//                    spNameDoc.setIdentityCode(IdentityCode);
//                }
//            }
//
//
//            if (!TextUtils.isEmpty(strPicUrl)) {
//                spNameDoc.setDocUrl(strPicUrl);
//                if (typeOfReq.equals("bank")) {
//                    spNameDoc.setDocType("bank passbook");
//                } else {
//                    spNameDoc.setDocType("photo");
//                }
//            } else {
//                spNameDoc.setDocUrl("");
//                spNameDoc.setDocType("");
//            }
//
//
//            if (!TextUtils.isEmpty(strFileExtension)) {
//                spNameDoc.setDocExtension(strFileExtension);
//            } else {
//                spNameDoc.setDocExtension("");
//            }
//
////
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            spNameDoc.setCreatedDate(dateTime);
////            spNameDoc.setUpdatedDate(dateTime);
//
//            if (!TextUtils.isEmpty(createDate)) {
//                spNameDoc.setCreatedDate(createDate);
//            } else {
//                spNameDoc.setCreatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(updatedDate)) {
//                spNameDoc.setUpdatedDate(updatedDate);
//            } else {
//                spNameDoc.setUpdatedDate("");
//            }
//
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc> spNameDocDetailsClass = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Doc>();
//            spNameDocDetailsClass.add(spNameDoc);
//            syncPersonalLandAllDetailsRequestDTO.setDoc(spNameDocDetailsClass);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
////                                    Log.d(TAG, "onResponse: datavalue" + status);
////                                    if (status.equals("1")) {
////                                        DocIdentiFicationDeatilsTable businessReviewSurveyTableFromDB = appDAO.getTopBankDetailsSubmitTableData(String.valueOf(bankDetailsSubmitTable.getBankID()),bankDetailsSubmitTable.getFarmerCode());
////                                        if (businessReviewSurveyTableFromDB != null) {
////                                            businessReviewSurveyTableFromDB.setBankID(businessReviewSurveyTableFromDB.getBankID());
////                                            .setSync(true);
////                                            appDAO.insertBankDetailListTable(bankDetailsSubmitTable);
////                                        }
////                                    }
//
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncGeoBoundariesDetailsDataToServer(GeoBoundariesTable geoBoundariesTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.LogbookGeoBoundaries spNameGPSDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.LogbookGeoBoundaries();
//
////            if (!TextUtils.isEmpty(geoBoundariesTable.getPlotNo())) {
////                spNameGPSDetailsClass.setPlotNo(geoBoundariesTable.getPlotNo());
////            } else {
////                spNameGPSDetailsClass.setPlotNo("");
////            }
////
//////            if (!TextUtils.isEmpty(String.valueOf(geoBoundariesTable.getLatitude()))) {
//////                spNameGPSDetailsClass.setLatitude(String.valueOf(geoBoundariesTable.getLatitude()));
//////            } else {
//////                spNameGPSDetailsClass.setLatitude("");
//////            }
//////
//////            if (!TextUtils.isEmpty(String.valueOf(geoBoundariesTable.getLongitude()))) {
//////                spNameGPSDetailsClass.setLongitude(String.valueOf(geoBoundariesTable.getLongitude()));
//////            } else {
//////                spNameGPSDetailsClass.setLongitude("");
//////            }
////
////            if (!TextUtils.isEmpty(String.valueOf(geoBoundariesTable.getLatitude())))
////            {
////                spNameGPSDetailsClass.setLatitude(geoBoundariesTable.getLatitude());
////            } else {
////                spNameGPSDetailsClass.setLatitude(0.0);
////            }
////
////            if (!TextUtils.isEmpty(String.valueOf(geoBoundariesTable.getLongitude()))) {
////                spNameGPSDetailsClass.setLongitude(geoBoundariesTable.getLongitude());
////            } else {
////                spNameGPSDetailsClass.setLongitude(0.0);
////            }
////
//////
//////            if (!TextUtils.isEmpty(String.valueOf(geoBoundariesTable.getGeoCategoryTypeId()))) {
//////                spNameGPSDetailsClass.setGeoCategoryTypeId(String.valueOf(geoBoundariesTable.getGeoCategoryTypeId()));
//////            } else {
//////                spNameGPSDetailsClass.setGeoCategoryTypeId("");
//////            }
////
////
//////            if (!geoBoundariesTable.isSync()) {
//////                spNameGPSDetailsClass.setSync(true);
//////            } else {
//////                spNameGPSDetailsClass.setGeoCategoryTypeId("");
//////            }
////
////
//////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////            spNameGPSDetailsClass.setCreatedDate(dateTime);
//////            spNameGPSDetailsClass.setUpdatedDate(dateTime);
//
//            if (!TextUtils.isEmpty(geoBoundariesTable.getIsActive())) {
//                spNameGPSDetailsClass.setIsActive(geoBoundariesTable.getIsActive());
//            } else {
//                spNameGPSDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(geoBoundariesTable.getCreatedByUserId())) {
//                spNameGPSDetailsClass.setCreatedByUserId(geoBoundariesTable.getCreatedByUserId());
//            } else {
//                spNameGPSDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(geoBoundariesTable.getUpdatedByUserId())) {
//                spNameGPSDetailsClass.setUpdatedByUserId(geoBoundariesTable.getUpdatedByUserId());
//            } else {
//                spNameGPSDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(geoBoundariesTable.getUpdatedDate())) {
//                spNameGPSDetailsClass.setUpdatedDate(geoBoundariesTable.getUpdatedDate());
//            } else {
//                spNameGPSDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(geoBoundariesTable.getCreatedDate())) {
//                spNameGPSDetailsClass.setCreatedDate(geoBoundariesTable.getCreatedDate());
//            } else {
//                spNameGPSDetailsClass.setCreatedDate("");
//            }
//
//            if (!(geoBoundariesTable.isSync())) {
//                spNameGPSDetailsClass.setSync(true);
//            } else {
//                spNameGPSDetailsClass.setSync(false);
//            }
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogbookGeoBoundaries> spNameGPSDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogbookGeoBoundaries>();
//            spNameGPSDetailsClasses.add(spNameGPSDetailsClass);
//            syncPersonalLandAllDetailsRequestDTO.setLogbookGeoBoundaries(spNameGPSDetailsClasses);
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//                                    if (status.equals("1")) {
//                                        GeoBoundariesTable businessReviewSurveyTableFromDB = appDAO.getGeoBoundariesTableData(geoBoundariesTable.getPlotNo());
//                                        if (businessReviewSurveyTableFromDB != null) {
//                                            businessReviewSurveyTableFromDB.setGeoID(businessReviewSurveyTableFromDB.getGeoID());
//                                            geoBoundariesTable.setSync(true);
//                                            geoBoundariesTable.setServerSend("1");
//                                            appDAO.insertGeoBoundariesTable(geoBoundariesTable);
//                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                    } else if (status.equals("0")) {
//                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                    }
//
//
////                                    CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                    if (currentVisitFarmerTables != null) {
////                                        currentVisitFarmerTables.setGeoBoundariesVisit(true);
////                                        appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                    }
//
//                                    // TODO: insert current visit
////                                    GeoBoundariesTable businessReviewSurveyTableFromDB = appDAO.getTopBankDetailsSubmitTableData(bankDetailsSubmitTable.getFarmerCode());
////                                    if (businessReviewSurveyTableFromDB != null) {
////                                        businessReviewSurveyTableFromDB.setBankID(businessReviewSurveyTableFromDB.getBankID());
////                                        bankDetailsSubmitTable.setSync(true);
////                                        appDAO.insertBankDetailListTable(bankDetailsSubmitTable);
////                                    }
//
////                                    List<LandDetailsListTable> customerSurveyTableList = appDAO.getLandDetaillistfromServer(landDetailsListTable.getFarmerCode(), landDetailsListTable.getPlotNo());
////                                    if (customerSurveyTableList != null && customerSurveyTableList.size() > 0) {
////                                        boolean allDataSynced = true;
////                                        for (LandDetailsListTable customerSurveyTableSync : customerSurveyTableList) {
////                                            if (customerSurveyTableSync != null && !customerSurveyTableSync.isSync()) {
////                                                allDataSynced = false;
////                                                break;
////                                            }
////                                        }
//
////                                        if (allDataSynced) {
////
////                                            CurrentVisitFarmerTables currentVisitFarmerTables = appDAO.getTopCurrentVisitFarmerTablesData(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
////                                            if (currentVisitFarmerTables != null) {
////                                                currentVisitFarmerTables.setLandDetailsVisit(true);
////                                                appDAO.insertCurrentVisitFarmerTable(currentVisitFarmerTables);
////                                            }
////                                        }
//
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//
//    // TODO: sync and save the farmer details to server db
//    public LiveData<String> syncLogBookDetailsToServer(AddLogBookDetailsTable addLogBookDetailsTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.LogBook spNameLogBookPersonalDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.LogBook();
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getPlotNo())) {
//                spNameLogBookPersonalDetailsClass.setPlotNo(addLogBookDetailsTable.getPlotNo());
//            } else {
//                spNameLogBookPersonalDetailsClass.setPlotNo("");
//            }
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getCrop())) {
//                spNameLogBookPersonalDetailsClass.setCrop(addLogBookDetailsTable.getCrop());
//            } else {
//                spNameLogBookPersonalDetailsClass.setCrop("");
//            }
//
////            if (!TextUtils.isEmpty(String.valueOf(addLogBookDetailsTable.getCultivationPractice()))) {
////                spNameLogBookPersonalDetailsClass.setCultivationPractice(addLogBookDetailsTable.getCultivationPractice());
////            } else {
////                spNameLogBookPersonalDetailsClass.setCultivationPractice(null);
////            }
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getDateOfSowing())) {
//                spNameLogBookPersonalDetailsClass.setDateOfSowing(addLogBookDetailsTable.getDateOfSowing());
//            } else {
//                spNameLogBookPersonalDetailsClass.setDateOfSowing("");
//            }
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getCropVariety())) {
//                spNameLogBookPersonalDetailsClass.setCropVariety(addLogBookDetailsTable.getCropVariety());
//            } else {
//                spNameLogBookPersonalDetailsClass.setCropVariety("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addLogBookDetailsTable.getSeason()))) {
//                spNameLogBookPersonalDetailsClass.setSeason(addLogBookDetailsTable.getSeason());
//            } else {
//                spNameLogBookPersonalDetailsClass.setSeason(null);
//            }
//
////            if (!TextUtils.isEmpty(addLogBookDetailsTable.getHarvest())) {
////                spNameLogBookPersonalDetailsClass.setHarvest(addLogBookDetailsTable.getHarvest());
////            } else {
////                spNameLogBookPersonalDetailsClass.setHarvest("");
////            }
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getLogBookNo())) {
//                spNameLogBookPersonalDetailsClass.setLogBookNo(addLogBookDetailsTable.getLogBookNo());
//            } else {
//                spNameLogBookPersonalDetailsClass.setLogBookNo("");
//            }
//
//
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
////            spNameLogBookPersonalDetailsClass.setCreatedDate(dateTime);
////            spNameLogBookPersonalDetailsClass.setUpdatedDate(dateTime);
//
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getIsActive())) {
//                spNameLogBookPersonalDetailsClass.setIsActive(addLogBookDetailsTable.getIsActive());
//            } else {
//                spNameLogBookPersonalDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getCreatedByUserId())) {
//                spNameLogBookPersonalDetailsClass.setCreatedByUserId(addLogBookDetailsTable.getCreatedByUserId());
//            } else {
//                spNameLogBookPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getUpdatedByUserId())) {
//                spNameLogBookPersonalDetailsClass.setUpdatedByUserId(addLogBookDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameLogBookPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getUpdatedDate())) {
//                spNameLogBookPersonalDetailsClass.setUpdatedDate(addLogBookDetailsTable.getUpdatedDate());
//            } else {
//                spNameLogBookPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addLogBookDetailsTable.getCreatedDate())) {
//                spNameLogBookPersonalDetailsClass.setCreatedDate(addLogBookDetailsTable.getCreatedDate());
//            } else {
//                spNameLogBookPersonalDetailsClass.setCreatedDate("");
//            }
//
//
//            if (!(addLogBookDetailsTable.getSync())) {
//                spNameLogBookPersonalDetailsClass.setSync(true);
//            } else {
//                spNameLogBookPersonalDetailsClass.setSync(false);
//            }
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogBook> spNameLogBookPersonalDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.LogBook>();
//            spNameLogBookPersonalDetailsClasses.add(spNameLogBookPersonalDetailsClass);
//              syncPersonalLandAllDetailsRequestDTO.setLogBook(spNameLogBookPersonalDetailsClasses);
//
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
//
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//
//                                    if (status.equals("1")) {
//                                        AddLogBookDetailsTable addLogBookDetailsFromLocalDB = appDAO.getTopAddLogBookDetailsTableData(addLogBookDetailsTable.getLogBookNo(),"0");
//                                        if (addLogBookDetailsFromLocalDB != null) {
//                                            addLogBookDetailsFromLocalDB.setLogBookId(addLogBookDetailsFromLocalDB.getLogBookId());
//                                            addLogBookDetailsTable.setSync(true);
////                                            addLogBookDetailsTable.setServerStatus("1"); //should add
//                                            appDAO.insertAddLogBookDetailsTable(addLogBookDetailsTable);
//                                            // TODO: Sending result
//                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                        }
//
//                                    } else if (status.equals("0")) {
//                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                    }
//
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//

    public LiveData<LoginResponseDTO> getlogInServiceResponse(String userId,String pass,String imei) {
        final MutableLiveData<LoginResponseDTO> data = new MutableLiveData<>();
        try {
            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
            executor.execute(() -> {

                if (appHelper.isNetworkAvailable()) { // TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getlogInService(userId)//praveen
                    AppWebService.createService(AppAPI.class).getlogInService(userId,pass,imei)
                            .enqueue(new Callback<LoginResponseDTO>() {
                                @Override
                                public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                                    Log.e("TAG", "Login LIST REFRESHED FROM NETWORK");
                                    executor.execute(() -> {
//                                        if(!response.message().equalsIgnoreCase("Invalid UserName/Password")){
                                            LoginResponseDTO loginResponseDTOList = response.body();
                                            if (loginResponseDTOList != null) {
                                                // TODO: Delete & Insert Stage List
//                                            for (LoginResponseDTO loginResponseDTO : loginResponseDTOList) {
//
//                                                // if (cropDetailsTable != null && !TextUtils.isEmpty(cropDetailsTable.getId())) {
//                                                LoginResponseDTO loginResponseDTO1 = new LoginResponseDTO();
//                                                Log.d("deviceId", loginResponseDTO.getId());
//                                                loginResponseDTO1.setId(loginResponseDTO.getId());
//                                                loginResponseDTO1.setUserName(loginResponseDTO.getUserName());
//                                                loginResponseDTO1.setPassword(loginResponseDTO.getPassword());
//                                                loginResponseDTO1.setAccessToken(loginResponseDTO.getAccessToken());
////                                                loginResponseDTO1.setDeviceUserID(loginResponseDTO.getDeviceUserID());
////                                                loginResponseDTO1.setDeviceUserID(loginResponseDTO.getDeviceUserID());
////
//                                            }

                                                // TODO: Sending Final Result
                                                if(loginResponseDTOList.getMessage().isEmpty()){
                                                    data.postValue(loginResponseDTOList);
                                                } else {
                                                    LoginResponseDTO emptyStageList = new LoginResponseDTO();
                                                    data.postValue(emptyStageList);
                                                }

                                            } else {
                                                // TODO: Sending Final Result
                                                LoginResponseDTO emptyStageList = new LoginResponseDTO();
                                                emptyStageList.setMessage("failure");
                                                data.postValue(emptyStageList);
                                            }
//                                        } else {
//                                            LoginResponseDTO emptyStageList = new LoginResponseDTO();
//                                            data.postValue(emptyStageList);
//                                        }

                                    });
                                }

                                @Override
                                public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                                    try {
                                        executor.execute(() -> {
                                            // TODO: Sending Final Result
                                            LoginResponseDTO emptyStageList = new LoginResponseDTO();
                                            data.postValue(emptyStageList);

                                        });

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            executor.execute(() -> {
                // TODO: Sending Final Result
                LoginResponseDTO emptyStageList = new LoginResponseDTO();
                data.postValue(emptyStageList);

            });
        }
        return data;
    }


    public LiveData<List<PestTable>> getPestTableDetailsListFromLocalDB() {
        final MutableLiveData<List<PestTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPestTableDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getPestTableDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<FertilizerTable>> getFertilizerDetailsListFromLocalDB() {
        final MutableLiveData<List<FertilizerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getFertilizerDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getFertilizerDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<DiseaseTable>> getDiseaseTableDetailsListFromLocalDB() {
        final MutableLiveData<List<DiseaseTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getDiseaseTableDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getDiseaseTableDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<AddFarmerTable>> getFarmerDetailslistFromLocalDB() {
        final MutableLiveData<List<AddFarmerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getFarmerDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<AddFarmerTable>> getFarmerDetailslistFromLocalDB(String seasonCode) {
        final MutableLiveData<List<AddFarmerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getFarmerDetailsListUserSection(seasonCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getFarmerDetailsListUserSection(seasonCode));
            }
        });
        return data;
    }

    public LiveData<List<AddFarmerTable>> getFarmerDetailslistFromLocalDBSection() {
        final MutableLiveData<List<AddFarmerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDBSection() != null);
            if (dataExist) {
                data.postValue(appDAO.getFarmerDetailsListFromLocalDBSection());
            }
        });
        return data;
    }


    public LiveData<List<LookUpDropDownDataTable>> getLookUpDropDownDataListFromLocalDB(String typeofReq) {
        final MutableLiveData<List<LookUpDropDownDataTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getLookUpDropDownDataListFromLocalDB(typeofReq) != null);
            if (dataExist) {
                data.postValue(appDAO.getLookUpDropDownDataListFromLocalDB(typeofReq));
            }
        });
        return data;
    }


    public LiveData<LookUpDropDownDataTable> getLookUpDataNameByID(String selectionID) {
        final MutableLiveData<LookUpDropDownDataTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getLookUpDropDownDetailsFromLocalDB(selectionID) != null);
            if (dataExist) {
                data.postValue(appDAO.getLookUpDropDownDetailsFromLocalDB(selectionID));
            }
        });
        return data;
    }


//    public LiveData<Integer> getCountComplaintDetailCount() {
//        return appDAO.getAddComplaintsDetailsCount();
//    }


    public LiveData<List<WeedTable>> getWeedDetailsListFromLocalDB() {
        final MutableLiveData<List<WeedTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getWeedDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getWeedDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<AddFarmerTable>> getFarmerDetailslistFromLocalDB(String season,int indicator) {
        final MutableLiveData<List<AddFarmerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            if(indicator==1){
                boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDB(season) != null);
                if (dataExist) {
                    data.postValue(appDAO.getFarmerDetailsListFromLocalDB(season));
                }
            } else if(indicator==2){
                boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDBMeasured(season,"10") != null);
                if (dataExist) {
                    data.postValue(appDAO.getFarmerDetailsListFromLocalDBMeasured(season,"10"));
                }
            } else if(indicator==3){
                boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDBAgreemented(season,"20") != null);
                if (dataExist) {
                    data.postValue(appDAO.getFarmerDetailsListFromLocalDBAgreemented(season,"20"));
                }
            } else if(indicator==4){
                boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDBPlot(season,"0") != null);
                if (dataExist) {
                    data.postValue(appDAO.getFarmerDetailsListFromLocalDBPlot(season,"0"));
                }
            }

        });
        return data;
    }

    public LiveData<List<AddGrowthMonitoringTable>> getGwList(String season,String farmerCode,String plot,String stage,String remarks) {
        final MutableLiveData<List<AddGrowthMonitoringTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getGwList(season,farmerCode,plot,stage,remarks) != null);
            if (dataExist) {
                data.postValue(appDAO.getGwList(season,farmerCode,plot,stage,remarks));
            }
        });
        return data;
    }

    public LiveData<List<VillageTable>> getVillageDetailsListFromLocalDB() {
        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getVillageDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getVillageDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<VillageTable>> getVillageListBasedOnSectionFromLocalDBStatus() {
        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getVillageListBasedOnSectionFromLocalDBStatus() != null);
            if (dataExist) {
                data.postValue(appDAO.getVillageListBasedOnSectionFromLocalDBStatus());
            }
        });
        return data;
    }

    public LiveData<List<ResonForNotPlantingTable>> getResonDetailsListFromLocalDB() {
        final MutableLiveData<List<ResonForNotPlantingTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getReasonDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getReasonDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<VarietyTable>> getVarietyDetailsListFromLocalDB() {
        final MutableLiveData<List<VarietyTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getVarietyDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getVarietyDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<LookupHDRTable>> getLookupHdrDetailsListFromLocalDB(String code) {
        final MutableLiveData<List<LookupHDRTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getLookupHdrDetailsFromLocalDB(code) != null);
            if (dataExist) {
                data.postValue(appDAO.getLookupHdrDetailsFromLocalDB(code));
            }
        });
        return data;
    }

    public LiveData<List<LookupDtlTable>> getLookupDtlList(String code) {
        final MutableLiveData<List<LookupDtlTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getLookupDtlList(code) != null);
            if (dataExist) {
                data.postValue(appDAO.getLookupDtlList(code));
            }
        });
        return data;
    }


    public LiveData<List<AddGeoBoundriesTable>> getPlotGeoBoundariesDetailsFromLocalDB(String PlotId) {
        final MutableLiveData<List<AddGeoBoundriesTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotGeoBoundariesDetailsFromLocalDB(PlotId) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotGeoBoundariesDetailsFromLocalDB(PlotId));
            }
        });
        return data;
    }

    public LiveData<List<AddGeoBoundriesTable>> getPlotGeoBoundariesDetailsFromLocalDBNotSync(Boolean sync) {
        final MutableLiveData<List<AddGeoBoundriesTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotGeoBoundariesDetailsFromLocalDB(sync) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotGeoBoundariesDetailsFromLocalDB(sync));
            }
        });
        return data;
    }


    public LiveData<Integer> getCountDataLand() {
        return appDAO.getCountLand();
    }

    public LiveData<Integer> getPlotOfferCount() {
        return appDAO.getPlotOfferCount("0");
    }

    public LiveData<Integer> getGeoBoundariesCount() {
        return appDAO.getGeoBoundariesCount(false);
    }

    public LiveData<Integer> getD10Count() {
        return appDAO.getD10Count(false);
    }

    public LiveData<Integer> getD20Count() {
        return appDAO.getD20Count(false);
    }

    public LiveData<Integer> getD30Count() {
        return appDAO.getD30Count("0");
    }

    public LiveData<Integer> getD20Fertilizer() {
        return appDAO.getD20Fertilizer(false);
    }

    public LiveData<Integer> getD20Weed() {
        return appDAO.getD20Weed("0");
    }

    public LiveData<Integer> getD20Pest() {
        return appDAO.getD20Pest(false);
    }

    public LiveData<Integer> getD20Disease() {
        return appDAO.getD20Disease(false);
    }

    public LiveData<Integer> getGmCount() {
        return appDAO.getGmCount(false);
    }


    public void deleteGeoBoundariestablesFromlocalDB(String logBookNum) {
        executor.execute(() -> {
            appDAO.deleteAddGeoBoundariesTable(logBookNum);
        });
    }


    public LiveData<List<PlantTypeTable>> getPlantTypeDetailsListFromLocalDB() {
        final MutableLiveData<List<PlantTypeTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlantTypeDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getPlantTypeDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<PlantSubTypeTable>> getPlantSubTypeDetailsListFromLocalDB() {
        final MutableLiveData<List<PlantSubTypeTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlantSubTypeDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getPlantSubTypeDetailsListFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<List<PlantSubTypeTable>> getPlantSubTypeDetailsListFromLocalDBByPlantType(int plantTypeId) {
        final MutableLiveData<List<PlantSubTypeTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlantSubTypeDetailsListFromLocalDBByPlantType(plantTypeId) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlantSubTypeDetailsListFromLocalDBByPlantType(plantTypeId));
            }
        });
        return data;
    }

    public LiveData<List<CropTable>> getCropDetailsListFromLocalDB() {
        final MutableLiveData<List<CropTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getCropDetailsListFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getCropDetailsListFromLocalDB());
            }
        });
        return data;
    }

//
//    public LiveData<Integer> getFarmerDetailslistCountFromLocalDB() {
//        final MutableLiveData<Integer> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getNumberOfRows() != 0);
//            if (dataExist) {
//                //   int  count = appDAO.getNumberOfRows();
//                //value = count;
//                data.postValue(appDAO.getNumberOfRows());
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<FarmerTable>> getFarmerUserAndCountList() {
//        final MutableLiveData<List<FarmerTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
////            boolean dataExist = (appDAO.getUserAndCountList() != null);
////            if (dataExist) {
////                data.postValue(appDAO.getUserAndCountList());
////            }
//        });
//        return data;
//    }
//
//    public LiveData<Integer> getCountData() {
//        return appDAO.getCount();
//    }
//
//
////    public VillageTable getVillageNameLocalDB(String villageId) {
////        return appDAO.getVillageNameByID(villageId);
////    }
//
//    // TODO: 2/25/2022 not sync count data from local db
//
//    // TODO:  farmer not sync count
//    public LiveData<Integer> getNotSyncFarmerCountDataFromLocalDB() {
//        return appDAO.getFarmerNotSyncCountFromLocalDB("0");
//    }
//
//
//    public LiveData<Integer> getNotSyncDocCountDataFromLocalDB() {
//        return appDAO.getDocNotSyncCountFromLocalDB("0");
//    }
//
//    public LiveData<Integer> getNotSyncBankCountDataFromLocalDB() {
//        return appDAO.getBankNotSyncCountFromLocalDB("0");
//    }
//
//    public LiveData<Integer> getNotSyncPlotCountDataFromLocalDB() {
//        return appDAO.getPlotNotSyncCountFromLocalDB("0");
//    }
//
//    public LiveData<Integer> getNotSyncGeoCountDataFromLocalDB() {
//        return appDAO.getGeoNotSyncCountFromLocalDB("0");
//    }
//
//    public LiveData<Integer> getNotSyncLogBookCountDataFromLocalDB() {
//        return appDAO.getLogBookNotSyncCountFromLocalDB("0");
//    }
//
//    public LiveData<Integer> getNotSyncFertilizerCountDataFromLocalDB() {
//        return appDAO.getFertlizerNotSyncCountFromLocalDB("0");
//    }
//
////    public LiveData<Integer> getNotSyncOrganicCountDataFromLocalDB() {
////        return appDAO.getOrganicNotSyncCountFromLocalDB("0");
////    }
//
////    public LiveData<Integer> getNotSyncWaterRegimeCountDataFromLocalDB() {
////        return appDAO.getWaterRegimeNotSyncCountFromLocalDB("0");
////    }
//
////    public LiveData<Integer> getNotSyncWaterPreSeasonCountDataFromLocalDB() {
////        return appDAO.getWaterPreSeasonNotSyncCountFromLocalDB("0");
////    }
//
////    public LiveData<Integer> getNotSyncBorewellCountDataFromLocalDB() {
////        return appDAO.getBorewellNotSyncCountFromLocalDB("0");
////    }
//
////    public LiveData<Integer> getNotSyncWaterFieldCountDataFromLocalDB() {
////        return appDAO.getWaterFieldNotSyncCountFromLocalDB("0");
////    }
//
//    public LiveData<Integer> getNotSyncHarvestCountDataFromLocalDB() {
//        return appDAO.getHarvestNotSyncCountFromLocalDB("0");
//    }
//
//    public LiveData<Integer> getCountDataLand() {
//        return appDAO.getCountLand();
//    }
//
//    public LiveData<Integer> getLogBookCount() {
//        return appDAO.getLogBookCount();
//    }
//
//    public LiveData<Integer> getFerlizerDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getFerlizerDataCount: " + logBookNum);
//        return appDAO.getFertilizerDataCount(logBookNum);
//    }
//
//    public void getCountDataLandDeleteAlltables() {
//        appDAO.deleteAllTables();
//    }
//
//
//    public LiveData<List<FarmerTable>> getFarmerDetailslistFromLocalDBNotSync() {
//        final MutableLiveData<List<FarmerTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getFarmerDetailsListFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getFarmerDetailsListFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<SavingFarmerProfieImagesTable>> getProfileFarmerImagesFromLocalDBNotSync() {
//        final MutableLiveData<List<SavingFarmerProfieImagesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getFarmerProfileImagesFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getFarmerProfileImagesFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
//
//
//    public LiveData<List<SavingPlotProfieImagesTable>> getProfilePlotImagesFromLocalDBNotSync() {
//        final MutableLiveData<List<SavingPlotProfieImagesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getFarmerPlotImagesFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getFarmerPlotImagesFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
//
//
//    public LiveData<List<SavingBankImagesTable>> getProfileBankImagesFromLocalDBNotSync() {
//        final MutableLiveData<List<SavingBankImagesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getFarmerBankImagesFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getFarmerBankImagesFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
//
//
//    public LiveData<FarmerTable> getFarmerCodeLocalDB() {
//        final MutableLiveData<FarmerTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
////            boolean dataExist = (appDAO.getMaxSubNumber() != null);
////            if (dataExist) {
////                data.postValue(appDAO.getMaxSubNumber());
////            }
//        });
//        return data;
//    }
//
//
//    public LiveData<List<PlotDetailsListTable>> getLandDetailsFromLocalDb(String strFarmercode) {
//        final MutableLiveData<List<PlotDetailsListTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "Farmercode" + strFarmercode);
//            boolean dataExist = (appDAO.getPlotListByFarmercode(strFarmercode) != null);
//            Log.d(TAG, "dataland: " + appDAO.getPlotListByFarmercode(strFarmercode));
//            if (dataExist) {
//                data.postValue(appDAO.getPlotListByFarmercode(strFarmercode));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddLogBookDetailsTable>> getLogBookDetailsFromLocalDB(String strPlotCode) {
//        final MutableLiveData<List<AddLogBookDetailsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "PlotCode" + strPlotCode);
//            boolean dataExist = (appDAO.getLogBookListFromLocalDB(strPlotCode) != null);
//            Log.d(TAG, "datalogBook: " + appDAO.getLogBookListFromLocalDB(strPlotCode));
//            if (dataExist) {
//                data.postValue(appDAO.getLogBookListFromLocalDB(strPlotCode));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFerlizerListDataFromLocalDB(String strLogBookNum) {
//        final MutableLiveData<List<AddFertilizerDetailsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getFerlizerListDataFromLocalDB(strLogBookNum) != null);
//            Log.d(TAG, "datalogBook: " + appDAO.getFerlizerListDataFromLocalDB(strLogBookNum));
//            if (dataExist) {
//                data.postValue(appDAO.getFerlizerListDataFromLocalDB(strLogBookNum));
//            }
//        });
//        return data;
//    }
//
//
////    public LiveData<List<AddOrganicDetailsTable>> getOrganicDetailsTableListDataFromLocalDB(String strLogBookNum) {
////        final MutableLiveData<List<AddOrganicDetailsTable>> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            boolean dataExist = (appDAO.getOrganicListDataFromLocalDB(strLogBookNum) != null);
////            Log.d(TAG, "datalogBook: " + appDAO.getOrganicListDataFromLocalDB(strLogBookNum));
////            if (dataExist) {
////                data.postValue(appDAO.getOrganicListDataFromLocalDB(strLogBookNum));
////            }
////        });
////        return data;
////    }
//
//
//    public LiveData<PlotDetailsListTable> getPlotDetailsFromLocalDB(String strPlotNum) {
//        final MutableLiveData<PlotDetailsListTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "plotnum" + strPlotNum);
//            boolean dataExist = (appDAO.getPlotDetailsFromLocalDB(strPlotNum) != null);
//            Log.d(TAG, "plotnum: " + appDAO.getPlotDetailsFromLocalDB(strPlotNum));
//            if (dataExist) {
//                data.postValue(appDAO.getPlotDetailsFromLocalDB(strPlotNum));
//            }
//        });
//        return data;
//    }
//
//
//    // TODO: 1/26/2022 accesing of village details from pincode
//
//    public LiveData<List<VillageTable>> getVillageTableDetailsFromLocalDbByPincode(String pincode) {
//        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "pincode" + pincode);
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDbByPincode(pincode) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getVillageDetailsFromLocalDbByPincode(pincode));
//            }
//        });
//        return data;
//    }

    public void deleteAlltablesFromlocal() {
        executor.execute(() -> {
            appDAO.deleteAllTables();
        });
    }

    public void deleteAlltablesFromTransactionSync() {
        executor.execute(() -> {
            appDAO.deleteGetDataFillSyncTables("1");
        });
    }

//
//    public void deleteGetAlltablesFromlocal() {
//        executor.execute(() -> {
//            appDAO.deleteGetDataFillSyncTables("1");
//        });
//    }
//
//    public void upDateLogBook(String currentdate ,String upDate, String logBookNumber) {
//        executor.execute(() -> {
//            appDAO.updateLogBookTable(currentdate,upDate, logBookNumber,false,"0");
//        });
//    }
//
//    public LiveData<MandalTable> getMandalDetailsFromLocalDb(String mandalID) {
//        final MutableLiveData<MandalTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "pincode" + mandalID);
//            boolean dataExist = (appDAO.getMandalDetailsFromLocalDB(mandalID) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getMandalDetailsFromLocalDB(mandalID));
//            }
//        });
//        return data;
//    }
//
//
//    public LiveData<LogBookModulesStatusDetailsTable> getLogBookModuleDetailsFromLocalDB(String logBooknum) {
//        final MutableLiveData<LogBookModulesStatusDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBooknum" + logBooknum);
//            boolean dataExist = (appDAO.getLogBookModuleStatusDetailsFromLocalDB(logBooknum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getLogBookModuleStatusDetailsFromLocalDB(logBooknum));
//            }
//        });
//        return data;
//    }
//
    public LiveData<VillageTable> getVillageDetailsFromLocalDB(int villageID) {
        final MutableLiveData<VillageTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }

    public LiveData<WeedTable> getWeedById(int villageID) {
        final MutableLiveData<WeedTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getWeedById(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getWeedById(villageID));
            }
        });
        return data;
    }

    public LiveData<FertilizerTable> getFertilizerById(int villageID) {
        final MutableLiveData<FertilizerTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getFertilizerById(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getFertilizerById(villageID));
            }
        });
        return data;
    }

    public LiveData<AddFarmerTable> getFarmerDetailsFromLocalDB(String villageID) {
        final MutableLiveData<AddFarmerTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getFarmerDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getFarmerDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }


    public LiveData<VillageTable> getVillageDetailsFromLocalDB(String villageID) {
        final MutableLiveData<VillageTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }

    public LiveData<CastTable> getCastDetailsFromLocalDB(int villageID) {
        final MutableLiveData<CastTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getCastDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getCastDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }


    public LiveData<AddGeoBoundriesTable> insertPlotGeoBoundsTable(AddGeoBoundriesTable addGeoBoundriesTable) {
        final MutableLiveData<AddGeoBoundriesTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            appDAO.insertPlotGeoBoundsListTableLocalDB(addGeoBoundriesTable);
            data.postValue(appDAO.getPlotGeoBoundsListData(addGeoBoundriesTable.getPlotNo()));
        });
        return data;
    }



    public LiveData<PlantTypeTable> getPlantTypeDetailsFromLocalDB(int villageID) {
        final MutableLiveData<PlantTypeTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getPlantTypeDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlantTypeDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }

    public LiveData<LookupDtlTable> getLookupDtlDetailsFromLocalDB(int villageID) {
        final MutableLiveData<LookupDtlTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getLookupDtlDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getLookupDtlDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }

    public LiveData<PlotExistOnTable> getPlotExistOnDetailsFromLocalDB(int villageID) {
        final MutableLiveData<PlotExistOnTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getPlotExistOnDetailsFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotExistOnDetailsFromLocalDB(villageID));
            }
        });
        return data;
    }

    public LiveData<List<PlotExistOnTable>> getPlotExistOnDetailsFromLocalDB() {
        final MutableLiveData<List<PlotExistOnTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getPlotExistOnDetailsFromLocalDB() != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotExistOnDetailsFromLocalDB());
            }
        });
        return data;
    }

    public LiveData<VarietyTable> getVarietyFromLocalDB(int villageID) {
        final MutableLiveData<VarietyTable> data = new MutableLiveData<>();
        executor.execute(() -> {
//            data.postValue(appDAO.getVillageDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
            boolean dataExist = (appDAO.getVarietyFromLocalDB(villageID) != null);
            if (dataExist) {
                data.postValue(appDAO.getVarietyFromLocalDB(villageID));
            }
        });
        return data;
    }

    public LiveData<MandalTable> getMandalDetailsFromLocalDB(int villageID) {
        final MutableLiveData<MandalTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getMandalDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

    public LiveData<SectionTable> getSectionDetailsFromLocalDB(int villageID) {
        final MutableLiveData<SectionTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getSectionDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

    public LiveData<CircleTable> getCircleDetailsFromLocalDB(int villageID) {
        final MutableLiveData<CircleTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getCircleDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

    public LiveData<DivisionTable> getDivisionDetailsFromLocalDB(int villageID) {
        final MutableLiveData<DivisionTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getDivisionDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

    public LiveData<DistrictTable> getDistrictDetailsFromLocalDB(int villageID) {
        final MutableLiveData<DistrictTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getDistrictDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

    public LiveData<BankTable> getBankDetailsFromLocalDB(int villageID) {
        final MutableLiveData<BankTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getBankDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

    public LiveData<BranchTable> getBranchDetailsFromLocalDB(int villageID) {
        final MutableLiveData<BranchTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getBranchDetailsFromLocalDB(villageID));
//            boolean dataExist = (appDAO.getVillageDetailsFromLocalDB(villageID) != null);
//            if (dataExist) {
//
//            }
        });
        return data;
    }

//    public LiveData<DocIdentiFicationDeatilsTable> getDocIdentiFicationDetailsFromLocalDB(String farmercode) {
//        final MutableLiveData<DocIdentiFicationDeatilsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "farmercode" + farmercode);
//            boolean dataExist = (appDAO.getDocIdentiFicationDeatilsTableDetailsFromLocalDB(farmercode,"photo","bank passbook") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getDocIdentiFicationDeatilsTableDetailsFromLocalDB(farmercode,"photo","bank passbook"));
//            }
//        });
//        return data;
//    }

    public LiveData<CropTable> getCropListDetailsFromLocalDB(int cropId) {
        final MutableLiveData<CropTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            Log.d(TAG, "cropID" + cropId);
            boolean dataExist = (appDAO.getCropListDetailsFromLocalDB((cropId)) != null);
            if (dataExist) {
                data.postValue(appDAO.getCropListDetailsFromLocalDB((cropId)));
            }
        });
        return data;
    }
//
//    public LiveData<CropVarietyListTable> getCropVarietyDetailsFromLocalDB(String cropId) {
//        final MutableLiveData<CropVarietyListTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "cropID" + cropId);
//            boolean dataExist = (appDAO.getCropVarietyListDetailsFromLocalDB(cropId) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getCropVarietyListDetailsFromLocalDB(cropId));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<CropVarietyListTable>> getCropVarietyListDetailsFromLocalDB(String cropId) {
//        final MutableLiveData<List<CropVarietyListTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "cropID" + cropId);
//            boolean dataExist = (appDAO.findCropVarietyByCropId(cropId) != null);
//            if (dataExist) {
//                data.postValue(appDAO.findCropVarietyByCropId(cropId));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<LookUpDropDownDataTable> getLookUpDataNameByID(String selectionID) {
//        final MutableLiveData<LookUpDropDownDataTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getLookUpDropDownDetailsFromLocalDB(selectionID) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getLookUpDropDownDetailsFromLocalDB(selectionID));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<SeasonTable> getSeasonName(String seasonID) {
//        final MutableLiveData<SeasonTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getSeasonTableDetailsFromLocalDB(seasonID) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getSeasonTableDetailsFromLocalDB(seasonID));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<DistrictTable> getDistrciDetailsFromLocalDB(String districId) {
//        final MutableLiveData<DistrictTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "districId" + districId);
//            boolean dataExist = (appDAO.getDistrictTableDetailsFromLocalDB(districId) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getDistrictTableDetailsFromLocalDB(districId));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<StatesTable> getStateDetailsFromLocalDB(String stateID) {
//        final MutableLiveData<StatesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "stateID" + stateID);
//            boolean dataExist = (appDAO.getStatesTableDetailsFromLocalDB(stateID) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getStatesTableDetailsFromLocalDB(stateID));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<BankDetailsSubmitTable>> getBankDeatilsByFarmerCode(String strFarmercode) {
//        final MutableLiveData<List<BankDetailsSubmitTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "Farmercode" + strFarmercode);
//            boolean dataExist = (appDAO.getBankDetailsByfarmerCode(strFarmercode) != null);
//            Log.d(TAG, "dataland: " + appDAO.getBankDetailsByfarmerCode(strFarmercode));
//            if (dataExist) {
//                data.postValue(appDAO.getBankDetailsByfarmerCode(strFarmercode));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<DocIdentiFicationDeatilsTable>> getDocIdentiFicationDetailsByFarmercode(String strFarmercode) {
//        final MutableLiveData<List<DocIdentiFicationDeatilsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "Farmercode" + strFarmercode);
//            boolean dataExist = (appDAO.getDocumentaionDetailsByfarmerCode(strFarmercode) != null);
//            Log.d(TAG, "dataland: " + appDAO.getDocumentaionDetailsByfarmerCode(strFarmercode));
//            if (dataExist) {
//                data.postValue(appDAO.getDocumentaionDetailsByfarmerCode(strFarmercode));
//            }
//        });
//        return data;
//    }
//

    public LiveData<List<AddPlotTable>> getLandDetailsFromLocalDb(String farmerCode) {
        final MutableLiveData<List<AddPlotTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotListByFarmerCode(farmerCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotListByFarmerCode(farmerCode));
            }
        });
        return data;
    }

    public LiveData<List<AddPlotTable>> getLandDetailsStatusFromLocalDb(String status,String farmerCode,String plotCode) {
        final MutableLiveData<List<AddPlotTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotListByStatus(status,farmerCode,plotCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotListByStatus(status,farmerCode,plotCode));
            }
        });
        return data;
    }

    public LiveData<List<AddPlotTable>> getLandDetailsStatusFromLocalDb(String farmerCode,String plotCode) {
        final MutableLiveData<List<AddPlotTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotListByStatus(farmerCode,plotCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotListByStatus(farmerCode,plotCode));
            }
        });
        return data;
    }

    public LiveData<List<AddD20Table>> getLandDetailsStageListFromLocalDb(String seasonCode,String farmerCode) {
        final MutableLiveData<List<AddD20Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotListByStatusSeasonCode(seasonCode,farmerCode,"20") != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotListByStatusSeasonCode(seasonCode,farmerCode,"20"));
            }
        });
        return data;
    }

    public LiveData<List<AddD10Table>> getLandDetailsStageListFromLocalDb10(String farmerCode) {
        final MutableLiveData<List<AddD10Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotListByStatusSeasonCode10("10",farmerCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotListByStatusSeasonCode10("10",farmerCode));
            }
        });
        return data;
    }

    public LiveData<List<D20DiseaseTable>> getD20DiseaseList(String plotNo) {
        final MutableLiveData<List<D20DiseaseTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20DiseaseList(plotNo) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20DiseaseList(plotNo));
            }
        });
        return data;
    }
    public LiveData<List<D20PestTable>> getD20PestList(String plotNo) {
        final MutableLiveData<List<D20PestTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20PestList(plotNo) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20PestList(plotNo));
            }
        });
        return data;
    }
    public LiveData<List<D20DiseaseTable>> getD20DiseaseListNotSync(String plotNo) {
        final MutableLiveData<List<D20DiseaseTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20DiseaseList(plotNo,false) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20DiseaseList(plotNo,false));
            }
        });
        return data;
    }
    public LiveData<List<D20DiseaseTable>> getD20DiseaseListNotSync() {
        final MutableLiveData<List<D20DiseaseTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20DiseaseList(false,"1") != null);
//            boolean dataExist = (appDAO.getD20DiseaseList(false) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20DiseaseList(false,"1"));
//                data.postValue(appDAO.getD20DiseaseList(false));
            }
        });
        return data;
    }

        public LiveData<List<D20WeedTable>> getD20WeedList(String plotNo) {
        final MutableLiveData<List<D20WeedTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20WeedListActive(plotNo) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20WeedListActive(plotNo));
            }
        });
        return data;
    }

    public LiveData<List<D20WeedTable>> getD20WeedListNotSync(String plotNo) {
        final MutableLiveData<List<D20WeedTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20WeedList(plotNo,false) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20WeedList(plotNo,false));
            }
        });
        return data;
    }

    public LiveData<List<D20WeedTable>> getD20WeedListNotSync() {
        final MutableLiveData<List<D20WeedTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20WeedList("0","true") != null);
//            boolean dataExist = (appDAO.getD20WeedList(true) != null);
//            boolean dataExist = (appDAO.getD20WeedList(false) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20WeedList("0","true"));
//                data.postValue(appDAO.getD20WeedList(true));
            }
        });
        return data;
    }

    public LiveData<List<D20FertilizerTable>> getD20FertilizerList(String plotNo) {
        final MutableLiveData<List<D20FertilizerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20FertilizerList(plotNo) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20FertilizerList(plotNo));
            }
        });
        return data;
    }

    public LiveData<List<D20FertilizerTable>> getD20FertilizerListNotSync(String plotNo) {
        final MutableLiveData<List<D20FertilizerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20FertilizerList(plotNo,false) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20FertilizerList(plotNo,false));
            }
        });
        return data;
    }

    public LiveData<List<D20FertilizerTable>> getD20FertilizerListNotSync() {
        final MutableLiveData<List<D20FertilizerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20FertilizerList(false,"true") != null);
            if (dataExist) {
                data.postValue(appDAO.getD20FertilizerList(false,"true"));
            }
        });
        return data;
    }

//        public LiveData<List<D20PestTable>> getD20PestList(String plotNo) {
//        final MutableLiveData<List<D20PestTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getD20PestList(plotNo) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getD20PestList(plotNo));
//            }
//        });
//        return data;
//    }

        public LiveData<List<D20PestTable>> getD20PestListNotSync(String plotNo) {
        final MutableLiveData<List<D20PestTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20PestList(plotNo,false) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20PestList(plotNo,false));
            }
        });
        return data;
    }


        public LiveData<List<D20PestTable>> getD20PestListNotSync() {
        final MutableLiveData<List<D20PestTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20PestList(false,true) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20PestList(false,true));
            }
        });
        return data;
    }


    public LiveData<List<AddD10Table>> getD10ListByStatusSeasonCode(String seasonCode,String farmerCode) {
        final MutableLiveData<List<AddD10Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD10ListByStatusSeasonCode(seasonCode,farmerCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getD10ListByStatusSeasonCode(seasonCode,farmerCode));
            }
        });
        return data;
    }

    public LiveData<List<AddPlotOfferTable>> getPlotOfferListD10(String seasonCode,String farmerCode,String status) {
        final MutableLiveData<List<AddPlotOfferTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotOfferListD10(seasonCode,farmerCode,status) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotOfferListD10(seasonCode,farmerCode,status));
            }
        });
        return data;
    }

    public LiveData<List<AddPlotOfferTable>> getPlotOfferReportedListD10(String seasonCode,String farmerCode,String status) {
        final MutableLiveData<List<AddPlotOfferTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotOfferListByStatusSeasonCode10(seasonCode,farmerCode,status) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotOfferListByStatusSeasonCode10(seasonCode,farmerCode,status));
            }
        });
        return data;
    }

    public LiveData<List<AddPlotOfferTable>> getPlotOfferListD10(String seasonCode,String farmerCode,String status,String offer) {
        final MutableLiveData<List<AddPlotOfferTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getPlotOfferListD10(seasonCode,farmerCode,status,offer) != null);
            if (dataExist) {
                data.postValue(appDAO.getPlotOfferListD10(seasonCode,farmerCode,status,offer));
            }
        });
        return data;
    }


    public void updatePLotNo(String measureArea,String agreedTon,String updatedByUserId,String updateddate,String plotNo) {
        executor.execute(() -> {
            appDAO.updatePLotNo(measureArea,agreedTon,false,updateddate,plotNo,"0");
            Log.e("========>updateddate",updateddate);
        });
    }

    public void updateD10(String soilType,String spacing,String previous,String IrrigationTypeId, String IsSettsHotWaterTreatment, String IsDustApplied, String IsTrashMulching, String IsPreviousRedPlot, String IsBasalFertilization,String IsCompositeFormYard,String IsFilterPressMud,String IsGreenManures, Boolean sync, String updatedDate,String plotNO,String serverStatus) {
        executor.execute(() -> {
            appDAO.updateD10(soilType,spacing,previous,IrrigationTypeId,IsSettsHotWaterTreatment,IsDustApplied,IsTrashMulching,IsPreviousRedPlot,IsBasalFertilization,IsCompositeFormYard,IsFilterPressMud,IsGreenManures,false,updatedDate,plotNO,"0");
        });
    }

    public void updateD10Agreemented(String crop,String micro,String trash,String gaps, String weedStatus, String bioArea, String deepArea, String trashArea, String earthArea,String ratoonArea,String trashShedderArea,String LoadShedderArea, Boolean sync, String updatedDate,String plotNO,String serverStatus) {
        executor.execute(() -> {
            appDAO.updateD10Agreemented(crop,micro,trash,gaps,weedStatus,bioArea,deepArea,trashArea,earthArea,ratoonArea,trashShedderArea,LoadShedderArea,false,updatedDate,plotNO,"0");
        });
    }

    public void updateD30Agreemented(String crop,String micro,String trash,String gaps, String weedStatus, String bioArea, String deepArea, String trashArea, String earthArea,String ratoonArea,String trashShedderArea,String LoadShedderArea, Boolean sync, String updatedDate,String plotNO,String serverStatus) {
        executor.execute(() -> {
            appDAO.updateD30Agreemented(crop,micro,trash,gaps,weedStatus,bioArea,deepArea,trashArea,earthArea,ratoonArea,trashShedderArea,
                    LoadShedderArea,false,updatedDate,plotNO,"0");
        });
    }


    public void updateD30updatevalues(String inspectiondate, String agreementarea, String nomini, String g1, String g2, String g3, String relationTypeId, boolean b, String updatedDate, String updateByUserId,String plotNO, String s) {
        executor.execute(() -> {
            appDAO.updateD30values(inspectiondate,agreementarea,nomini,g1,g2,g3,relationTypeId,false,updatedDate,plotNO,"0");
        });
    }

    public void updateD10(String interCrop,String updatedByUserId,String updateddate,String plotNo,String weedStatusId) {
        executor.execute(() -> {
            appDAO.updateD10(interCrop,false,updateddate,plotNo,"0",weedStatusId);
        });
    }

    public void updateD20(String interCrop,String updatedByUserId,String updateddate,String plotNo,String weedStatusId) {
        executor.execute(() -> {
            appDAO.updateD20(interCrop,false,updateddate,plotNo,"0",weedStatusId);
        });
    }

    public void updateD30(String interCrop,String updatedByUserId,String updateddate,String plotNo,String weedStatusId) {
        executor.execute(() -> {
            appDAO.updateD30(interCrop,false,updateddate,plotNo,"0",weedStatusId);
        });
    }

    public void updateD20Weed(String weedId,String measureArea,String updatedDate,String plotNo,String serverStatus,int id) {
        executor.execute(() -> {
            appDAO.updateD20Weed(String.valueOf(weedId),updatedDate,"0","0","true");
//            appDAO.updateD20Weed(String.valueOf(weedId),updatedDate,plotNo,"0","0","true");
        });
    }

    public void updateD20Fertilizer(String weedId,String measureArea,String updatedDate,String plotNo,String serverStatus,int id) {
        executor.execute(() -> {
            appDAO.updateD20Fertilizer(String.valueOf(weedId),updatedDate,"0","0","true");
//            appDAO.updateD20Weed(String.valueOf(weedId),updatedDate,plotNo,"0","0","true");
        });
    }

    public void updatePLotNo(String measureArea,String updateddate,String plotNo) {
        executor.execute(() -> {
            appDAO.updatePLotNo(measureArea,updateddate,plotNo,"20");
        });
    }

    public void updateWeedDate(String updatedate,String id,Boolean isControl) {
        executor.execute(() -> {
//            if(isControl){
                appDAO.updateWeedDate(updatedate,id,"0",false,true);
//            } else {
//                appDAO.updateDiseaseControlDate(identifiedDate,controlDate,updatedate,id,"0",false,true);
//            }

        });
    }

    public void updateFertilizerDate(String updatedate,String id,Boolean isControl) {
        executor.execute(() -> {
//            if(isControl){
                appDAO.updateFertilizerDate(updatedate,id,"0",false,true);
//            } else {
//                appDAO.updateDiseaseControlDate(identifiedDate,controlDate,updatedate,id,"0",false,true);
//            }

        });
    }

    public void updateD20Disease(String controlDate,String identifiedDate,String updatedate,String id,Boolean isControl) {
        executor.execute(() -> {
//            if(isControl){
                appDAO.updateDiseaseControlDate(identifiedDate,controlDate,updatedate,id,"0",false,true);
//            } else {
//                appDAO.updateDiseaseControlDate(identifiedDate,controlDate,updatedate,id,"0",false,true);
//            }

        });
    }

    public void updatePestControlDate(String controlDate,String identifiedDate,String updatedate,String id,Boolean isControl) {
        executor.execute(() -> {
//            if(isControl){
                appDAO.updatePestControlDate(identifiedDate,controlDate,updatedate,id,"0",false,true);
//            } else {
//                appDAO.updateDiseaseControlDate(identifiedDate,controlDate,updatedate,id,"0",false,true);
//            }

        });
    }

    public void updatePLotNoAgreemented(String measureArea,String updateddate,String plotNo) {
        executor.execute(() -> {
            appDAO.updatePLotNoAgreemented(measureArea,updateddate,plotNo,"30");
        });
    }

    public void updateD10Growth(String soilTypeID,Boolean isSetts, Boolean isDust,
                                Boolean isTrash,String plotNO,String spacingId,Boolean isPreviousRedPlot,
                                Boolean isBasalFertilization, Boolean isCompositeFormYard,Boolean IsFilterPressMud,Boolean IsGreenManures) {
        executor.execute(() -> {
            appDAO.updateD10Growth(soilTypeID,isSetts,isDust,isTrash,plotNO,spacingId,isPreviousRedPlot,isBasalFertilization,isCompositeFormYard,IsFilterPressMud,IsGreenManures);
        });
    }

//    public void updatePLotNo(String updateddate,String plotNo) {
//        executor.execute(() -> {
//            appDAO.updatePLotNo(updateddate,plotNo,"0");
//        });
//    }

//
//    public LiveData<List<PlotDetailsListTable>> getLandDetailsFromLocalDbNotSync() {
//        final MutableLiveData<List<PlotDetailsListTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getLandDetailsFromLocalDbNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getLandDetailsFromLocalDbNotSync("0"));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<DocIdentiFicationDeatilsTable>> getDocIdentiFicationDeatilsTableFromLocalDb() {
//        final MutableLiveData<List<DocIdentiFicationDeatilsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getDocIdentiFicationDeatilsTableFromLocalDb("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getDocIdentiFicationDeatilsTableFromLocalDb("0"));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<BankDetailsSubmitTable>> getBankDetailsSubmitTableFromLocalDb() {
//        final MutableLiveData<List<BankDetailsSubmitTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getBankDetailsSubmitTableFromLocalDbData("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getBankDetailsSubmitTableFromLocalDbData("0"));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<GeoBoundariesTable>> getGeoBoundariesTableListFromLocalDB() {
//        final MutableLiveData<List<GeoBoundariesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getGeoBoundariesTableTableFromLocalDbList("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getGeoBoundariesTableTableFromLocalDbList("0"));
//            }
//        });
//        return data;
//    }
//
//    // TODO: get pincode  Details from server
//    public LiveData<List<PinCodeDetailsResponseDTO>> getPincodeDetailsFromServer(String userId) {
//        final MutableLiveData<List<PinCodeDetailsResponseDTO>> data = new MutableLiveData<>();
//
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//
//            executor.execute(() -> {
//
//                if (appHelper.isNetworkAvailable()) { // TODO: Checking internet connection
//
//                    AppWebService.createService(AppAPI.class).getPinCodeDeatilsFromServer(userId, appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<PinCodeDetailsResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<PinCodeDetailsResponseDTO>> call, Response<List<PinCodeDetailsResponseDTO>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<PinCodeDetailsResponseDTO> pinCodeDetailsDataTableList = response.body();
//                                        if (pinCodeDetailsDataTableList != null && pinCodeDetailsDataTableList.size() > 0) {
//                                            // TODO: Delete & Insert Stage List
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (PinCodeDetailsResponseDTO pinCodeDetailsDataTable : pinCodeDetailsDataTableList) {
//                                                //if (farmerDetailListTable != null )) {
//                                                PinCodeDetailsResponseDTO pinCodeDetailsDataTableListData = new PinCodeDetailsResponseDTO();
//                                                Log.d("onResponseData: ", pinCodeDetailsDataTableListData.getVillageId() + pinCodeDetailsDataTableListData.getVillageName());
//                                                //  pinCodeDetailsDataTableListData.setId(pinCodeDetailsDataTable.getId());
//                                                pinCodeDetailsDataTableListData.setVillageId(pinCodeDetailsDataTable.getVillageId());
//                                                pinCodeDetailsDataTableListData.setVillageName(pinCodeDetailsDataTable.getVillageName());
////                                                pinCodeDetailsDataTableListData.setMandalId(pinCodeDetailsDataTable.getMandalId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(pinCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<PinCodeDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<PinCodeDetailsResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<PinCodeDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<PinCodeDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//    // TODO: get state  Details from server
//    public LiveData<List<StateListResponseDTO>> getStateDetailsResponseDTOServer() {
//        final MutableLiveData<List<StateListResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getStateDeatilsFromServer(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<StateListResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<StateListResponseDTO>> call, Response<List<StateListResponseDTO>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<StateListResponseDTO> stateCodeDetailsDataTableList = response.body();
//                                        if (stateCodeDetailsDataTableList != null && stateCodeDetailsDataTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (StateListResponseDTO stateListResponseDTO : stateCodeDetailsDataTableList) {
//                                                StateListResponseDTO stateDetailsDataTableListData = new StateListResponseDTO();
//                                                Log.d("onResponseData: ", stateDetailsDataTableListData.getId() + stateDetailsDataTableListData.getName());
//                                                stateDetailsDataTableListData.setId(stateListResponseDTO.getId());
//                                                stateDetailsDataTableListData.setName(stateListResponseDTO.getName());
//                                                stateDetailsDataTableListData.setCode(stateListResponseDTO.getCode());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(stateCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<StateListResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<StateListResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<StateListResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<StateListResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get state  Details from server
//    public LiveData<List<DistricDetailsResponseDTO>> getDistricDetailsResponseDTOServer(String stateID) {
//        final MutableLiveData<List<DistricDetailsResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getDistricDeatilsBystateIdFromServer(stateID, appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<DistricDetailsResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<DistricDetailsResponseDTO>> call, Response<List<DistricDetailsResponseDTO>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<DistricDetailsResponseDTO> stateCodeDetailsDataTableList = response.body();
//                                        if (stateCodeDetailsDataTableList != null && stateCodeDetailsDataTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (DistricDetailsResponseDTO districDetailsResponseDTO : stateCodeDetailsDataTableList) {
//                                                DistricDetailsResponseDTO stateDetailsDataTableListData = new DistricDetailsResponseDTO();
//                                                Log.d("onResponseData: ", stateDetailsDataTableListData.getId() + stateDetailsDataTableListData.getName());
//                                                stateDetailsDataTableListData.setId(districDetailsResponseDTO.getId());
//                                                stateDetailsDataTableListData.setName(districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setCode(districDetailsResponseDTO.getCode());
//                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
//                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(stateCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<DistricDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<DistricDetailsResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<DistricDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<DistricDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get state  Details from server
//    public LiveData<List<MandalDetailsResponseDTO>> getMandalDetailsResponseDTOServer(String districID) {
//        final MutableLiveData<List<MandalDetailsResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getMandalDeatilsByDistricIdFromServer(districID, appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<MandalDetailsResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<MandalDetailsResponseDTO>> call, Response<List<MandalDetailsResponseDTO>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<MandalDetailsResponseDTO> stateCodeDetailsDataTableList = response.body();
//                                        if (stateCodeDetailsDataTableList != null && stateCodeDetailsDataTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (MandalDetailsResponseDTO districDetailsResponseDTO : stateCodeDetailsDataTableList) {
//                                                MandalDetailsResponseDTO stateDetailsDataTableListData = new MandalDetailsResponseDTO();
//                                                Log.d("onResponseData: ", districDetailsResponseDTO.getId() + districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setId(districDetailsResponseDTO.getId());
//                                                stateDetailsDataTableListData.setName(districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setCode(districDetailsResponseDTO.getCode());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(stateCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<MandalDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<MandalDetailsResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<MandalDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<MandalDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get state  Details from server
//    public LiveData<List<VillageByMandalIdDetailsResponseDTO>> getVillageDeatilsByMandalIdFromServer(String mandalId) {
//        final MutableLiveData<List<VillageByMandalIdDetailsResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getVillageDeatilsByMandalIdFromServer(mandalId, appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<VillageByMandalIdDetailsResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<VillageByMandalIdDetailsResponseDTO>> call, Response<List<VillageByMandalIdDetailsResponseDTO>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<VillageByMandalIdDetailsResponseDTO> stateCodeDetailsDataTableList = response.body();
//                                        if (stateCodeDetailsDataTableList != null && stateCodeDetailsDataTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (VillageByMandalIdDetailsResponseDTO districDetailsResponseDTO : stateCodeDetailsDataTableList) {
//                                                VillageByMandalIdDetailsResponseDTO stateDetailsDataTableListData = new VillageByMandalIdDetailsResponseDTO();
//                                                Log.d("onResponseData: ", districDetailsResponseDTO.getId() + districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setId(districDetailsResponseDTO.getId());
//                                                stateDetailsDataTableListData.setName(districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setCode(districDetailsResponseDTO.getCode());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(stateCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<VillageByMandalIdDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<VillageByMandalIdDetailsResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<VillageByMandalIdDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<VillageByMandalIdDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get state  Details from server
//    public LiveData<List<ClusterDetailsResponseDTO>> getCluserDetailsFromServer(String villageId) {
//        final MutableLiveData<List<ClusterDetailsResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getClusertResponseFromServer(villageId, appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<ClusterDetailsResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<ClusterDetailsResponseDTO>> call, Response<List<ClusterDetailsResponseDTO>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<ClusterDetailsResponseDTO> stateCodeDetailsDataTableList = response.body();
//                                        if (stateCodeDetailsDataTableList != null && stateCodeDetailsDataTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (ClusterDetailsResponseDTO districDetailsResponseDTO : stateCodeDetailsDataTableList) {
//                                                ClusterDetailsResponseDTO stateDetailsDataTableListData = new ClusterDetailsResponseDTO();
//                                                Log.d("onResponseData: ", districDetailsResponseDTO.getId() + districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setId(districDetailsResponseDTO.getId());
//                                                stateDetailsDataTableListData.setName(districDetailsResponseDTO.getName());
//                                                stateDetailsDataTableListData.setCode(districDetailsResponseDTO.getCode());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(stateCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<ClusterDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<ClusterDetailsResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<ClusterDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<ClusterDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get mandal  Details from server
//    public LiveData<List<VillageDetailsResponseDTO>> getVillageDetailsFromServer(String userId) {
//        final MutableLiveData<List<VillageDetailsResponseDTO>> data = new MutableLiveData<>();
//
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//
//            executor.execute(() -> {
//
//                if (appHelper.isNetworkAvailable()) { // TODO: Checking internet connection
//
//                    AppWebService.createService(AppAPI.class).getVillageDetailsResponseFromServer(userId,
//                            appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<VillageDetailsResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<VillageDetailsResponseDTO>> call, Response<List<VillageDetailsResponseDTO>> response) {
//                                    Log.e("TAG", "Mandal list LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<VillageDetailsResponseDTO> pinCodeDetailsDataTableList = response.body();
//                                        if (pinCodeDetailsDataTableList != null && pinCodeDetailsDataTableList.size() > 0) {
//                                            // TODO: Delete & Insert Stage List
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (VillageDetailsResponseDTO pinCodeDetailsDataTable : pinCodeDetailsDataTableList) {
//                                                //if (farmerDetailListTable != null )) {
//                                                PinCodeDetailsDataTable pinCodeDetailsDataTableListData = new PinCodeDetailsDataTable();
//                                                Log.d("onResponseData: ", pinCodeDetailsDataTableListData.getVillageId() + pinCodeDetailsDataTableListData.getVillageName());
//                                                //  pinCodeDetailsDataTableListData.setId(pinCodeDetailsDataTable.getId());
////                                                pinCodeDetailsDataTableListData.setVillageId(pinCodeDetailsDataTable.getVillageId());
////                                                pinCodeDetailsDataTableListData.setVillageName(pinCodeDetailsDataTable.getVillageName());
////
//                                                pinCodeDetailsDataTableListData.setMandalId(pinCodeDetailsDataTable.getMandalId());
//                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
//                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
//                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
//                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
//                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
//                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(pinCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<VillageDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<VillageDetailsResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<VillageDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<VillageDetailsResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//
//
//    // TODO: get state  Details from server
//    public LiveData<List<StatesTable>> getStateListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<StatesTable>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getStateListFromServerTosaveIntoLocalDB(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<StatesTable>>() {
//                                @Override
//                                public void onResponse(Call<List<StatesTable>> call, Response<List<StatesTable>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<StatesTable> stateCodeDetailsDataTableList = response.body();
//                                        if (stateCodeDetailsDataTableList != null && stateCodeDetailsDataTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (StatesTable statesTable : stateCodeDetailsDataTableList) {
//                                                StatesTable stateDetailsDataTableListData = new StatesTable();
//                                                Log.d("onResponseData: ", stateDetailsDataTableListData.getId() + stateDetailsDataTableListData.getName());
//                                                stateDetailsDataTableListData.setId(statesTable.getId());
//                                                stateDetailsDataTableListData.setName(statesTable.getName());
//                                                stateDetailsDataTableListData.setCode(statesTable.getCode());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(stateCodeDetailsDataTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<StatesTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<StatesTable>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<StatesTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<StatesTable> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get distric  Details from server
//    public LiveData<List<DistrictTable>> getDistricListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<DistrictTable>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getDistricListFromServerTosaveIntoLocalDB(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<DistrictTable>>() {
//                                @Override
//                                public void onResponse(Call<List<DistrictTable>> call, Response<List<DistrictTable>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<DistrictTable> districtTableList = response.body();
//                                        if (districtTableList != null && districtTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//
//                                            for (int i = 0; i < districtTableList.size(); i++) {
//                                                if (i == 27 && i == 29) {
//                                                    DistrictTable districtTable1 = new DistrictTable();
//                                                    Log.d("onResponseData: ", districtTableList.get(i).getId() + districtTable1.getName());
//                                                    districtTable1.setId(districtTableList.get(i).getId());
//                                                    districtTable1.setName(districtTableList.get(i).getName());
//                                                    districtTable1.setCode(districtTableList.get(i).getCode());
//                                                    districtTable1.setStateId(districtTableList.get(i).getStateId());
//                                                    districtTableList.add(districtTable1);
//                                                }
//                                            }
////                                            for (DistrictTable districtTable : districtTableList) {
////                                                DistrictTable districtTable1 = new DistrictTable();
////                                                Log.d("onResponseData: ", districtTable1.getId() + districtTable1.getName());
////                                                districtTable1.setId(districtTable.getId());
////                                                districtTable1.setName(districtTable.getName());
////                                                districtTable1.setCode(districtTable.getCode());
////                                                districtTable1.setStateId(districtTable.getStateId());
//////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
//////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
//////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
//////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
//////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
//////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
//////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
//////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
////
////                                                //}
////
////                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(districtTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<DistrictTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<DistrictTable>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<DistrictTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<DistrictTable> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get mandal  Details from server
//    public LiveData<List<MandalTable>> getMandalTableListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<MandalTable>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getMandalListFromServerTosaveIntoLocalDB(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<MandalTable>>() {
//                                @Override
//                                public void onResponse(Call<List<MandalTable>> call, Response<List<MandalTable>> response) {
//                                    Log.e("TAG", "MandalTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<MandalTable> mandalTableList = response.body();
//                                        if (mandalTableList != null && mandalTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (MandalTable mandalTable : mandalTableList) {
//                                                MandalTable mandalTable1 = new MandalTable();
//                                                Log.d("onResponseData: ", mandalTable.getId() + mandalTable.getName());
//                                                mandalTable1.setId(mandalTable.getId());
//                                                mandalTable1.setName(mandalTable.getName());
//                                                mandalTable1.setCode(mandalTable.getCode());
//                                                mandalTable1.setDistrictId(mandalTable.getDistrictId());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(mandalTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<MandalTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<MandalTable>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<MandalTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<MandalTable> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get village  Details from server
//    public LiveData<List<VillageTable>> getVillageListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getVillageListFromServerTosaveIntoLocalDB(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<VillageTable>>() {
//                                @Override
//                                public void onResponse(Call<List<VillageTable>> call, Response<List<VillageTable>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<VillageTable> villageTableList = response.body();
//                                        if (villageTableList != null && villageTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (VillageTable villageTable : villageTableList) {
//                                                VillageTable villageTable1 = new VillageTable();
//                                                Log.d("onResponseData: ", villageTable.getId() + villageTable.getName());
//                                                villageTable1.setId(villageTable.getId());
//                                                villageTable1.setName(villageTable.getName());
//                                                villageTable1.setCode(villageTable.getCode());
//                                                villageTable1.setMandalId(villageTable.getMandalId());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(villageTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<VillageTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<VillageTable>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<VillageTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<VillageTable> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: get cluster  Details from server
//    public LiveData<List<ClusterHDRTable>> getClusterHDRListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<ClusterHDRTable>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getClusterHDRListFromServerTosaveIntoLocalDB(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<ClusterHDRTable>>() {
//                                @Override
//                                public void onResponse(Call<List<ClusterHDRTable>> call, Response<List<ClusterHDRTable>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<ClusterHDRTable> clusterHDRTableList = response.body();
//                                        if (clusterHDRTableList != null && clusterHDRTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (ClusterHDRTable clusterHDRTable : clusterHDRTableList) {
//                                                ClusterHDRTable clusterHDRTable1 = new ClusterHDRTable();
//                                                Log.d("onResponseData: ", clusterHDRTable.getId() + clusterHDRTable.getName());
//                                                clusterHDRTable1.setId(clusterHDRTable.getId());
//                                                clusterHDRTable1.setName(clusterHDRTable.getName());
//                                                clusterHDRTable1.setCode(clusterHDRTable.getCode());
//
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(clusterHDRTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<ClusterHDRTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<ClusterHDRTable>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<ClusterHDRTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<ClusterHDRTable> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//    // TODO: get crop  Details from server
//    public LiveData<List<CropListTable>> getCropListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<CropListTable>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getCropListTableFromServerTosaveIntoLocalDB(appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<CropListTable>>() {
//                                @Override
//                                public void onResponse(Call<List<CropListTable>> call, Response<List<CropListTable>> response) {
//                                    Log.e("TAG", "FarmerDetailListTable LIST REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        List<CropListTable> cropListTableList = response.body();
//                                        if (cropListTableList != null && cropListTableList.size() > 0) {
//                                            //dynamicUIDao.deleteAndInsertStageList(stageDetailsTableList);
//                                            for (CropListTable cropListTable : cropListTableList) {
//                                                CropListTable cropListTable1 = new CropListTable();
//                                                Log.d("onResponseData: ", cropListTable.getId() + cropListTable.getName());
//                                                cropListTable1.setId(cropListTable.getId());
//                                                cropListTable1.setName(cropListTable.getName());
//                                                cropListTable1.setCode(cropListTable.getCode());
////                                                stateDetailsDataTableListData.setStateName(districDetailsResponseDTO.getStateName());
////                                                stateDetailsDataTableListData.setStateId(districDetailsResponseDTO.getStateId());
////                                                pinCodeDetailsDataTableListData.setMandalName(pinCodeDetailsDataTable.getMandalName());
////                                                pinCodeDetailsDataTableListData.setStateId(pinCodeDetailsDataTable.getStateId()); // TODO: LOAN TYPE
////                                                pinCodeDetailsDataTableListData.setStateName(pinCodeDetailsDataTable.getStateName());
////                                                pinCodeDetailsDataTableListData.setDistrictId(pinCodeDetailsDataTable.getDistrictId());
////                                                pinCodeDetailsDataTableListData.setDistrictName(pinCodeDetailsDataTable.getDistrictName());
////                                                //pinCodeDetailsDataTableListData.setIsActive(pinCodeDetailsDataTable.getIsActive()); // TODO: STAFF ID
//
//                                                //}
//
//                                            }
//
//                                            // TODO: Sending Final Result
//                                            data.postValue(cropListTableList);
//                                        } else {
//                                            // TODO: Sending Final Result
//                                            List<CropListTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<CropListTable>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<CropListTable> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<CropListTable> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//
//    // TODO: 1/21/2022 Adding Into local db from master values
//
//    public LiveData<StatesTable> insertStateListDataIntoLocalDBRepository(StatesTable statesTable) {
//        final MutableLiveData<StatesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncStateListIntoLocalDB(statesTable);
//            // data.postValue(appDAO.getTopMasterSyncStatesTablDataLocalDBQuery(statesTable.getCode()));
//        });
//        return data;
//    }
//
//    public LiveData<ClusterDTLTable> insertClusterDTLTableIntoLocalDBRepository(ClusterDTLTable statesTable) {
//        final MutableLiveData<ClusterDTLTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncClusterDTLTableIntoLocalDB(statesTable);
//            // data.postValue(appDAO.getTopMasterSyncClusterDTLTableDataLocalDBQuery(statesTable.getId()));
//        });
//        return data;
//    }
//
//
//    public LiveData<DistrictTable> insertDistrictListDataIntoLocalDBRepository(DistrictTable districtTable) {
//        final MutableLiveData<DistrictTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//
//            appDAO.insertMasterSyncDistrictListIntoLocalDB(districtTable);
//
//            data.postValue(appDAO.getTopMasterSyncDistricTablDataLocalDBQuery(districtTable.getCode()));
//        });
//        return data;
//    }
//
//    public LiveData<DistrictTable> insertDisListDataIntoLocalDBRepository(DistrictTable districtTable) {
//        final MutableLiveData<DistrictTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncDisListIntoLocalDB(districtTable);
//            //data.postValue(appDAO.getTopMasterSyncDistrictTableDataLocalDBQuery(districtTable.getCode()));
//        });
//        return data;
//    }
//
//    public LiveData<MandalTable> insertMandalListDataIntoLocalDBRepository(MandalTable mandalTable) {
//        final MutableLiveData<MandalTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertMasterSyncMandalListIntoLocalDB(mandalTable);
//            //   data.postValue(appDAO.getTopMasterSyncMandalTablDataLocalDBQuery(mandalTable.getId()));
//        });
//        return data;
//    }
//
//
//    public LiveData<VillageTable> insertVillageListDataIntoLocalDBRepository(VillageTable villageTable) {
//        final MutableLiveData<VillageTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncvillageListIntoLocalDB(villageTable);
//            //data.postValue(appDAO.getTopMasterSyncVillageTablDataLocalDBQuery(villageTable.getCode()));
//        });
//        return data;
//    }
//
//
    public LiveData<AddFarmerTable> insertFarmerIntoLocalDB(AddFarmerTable clusterHDRTable) {
        final MutableLiveData<AddFarmerTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertFarmerTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<List<AddFarmerTable>> insertFarmerIntoLocalDB(List<AddFarmerTable> clusterHDRTable) {
        final MutableLiveData<List<AddFarmerTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertFarmerTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }


    public LiveData<AddPlotTable> insertPLotIntoLocalDB(AddPlotTable clusterHDRTable) {
        final MutableLiveData<AddPlotTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertPlotTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<AddD20Table> insertD20IntoLocalDB(AddD20Table clusterHDRTable) {
        final MutableLiveData<AddD20Table> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD20Table(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<AddD10Table> insertD10IntoLocalDB(AddD10Table clusterHDRTable) {
        final MutableLiveData<AddD10Table> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD10Table(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<AddD30Table> insertD30IntoLocalDB(AddD30Table clusterHDRTable) {
        final MutableLiveData<AddD30Table> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD30Table(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
//    public void insertTrackingIntoLocalDB(AddGeoBoundariesTrackingTable clusterHDRTable, OnInsertCallback callback) {
//        // Perform the database insertion on a background thread
//        // Assuming you are using Room, and dao is an instance of your DAO
//        executor.execute(() -> {
//            // Perform the actual insertion
//            appDAO.insertTrackingTable(clusterHDRTable);
//
//            // Fetch the inserted items if needed
//            List<AddGeoBoundariesTrackingTable> insertedItems = appDAO.getTrackingDetailsListFromLocalDBNotSync(false);
//            // Notify the callback with the inserted items
//            callback.onInsertComplete(insertedItems);
//        });
//    }

    public void insertTrackingIntoLocalDB(AddGeoBoundariesTrackingTable clusterHDRTable, OnInsertionCompleteListener listener) {
        if (executor != null) {
            executor.execute(() -> {
                try {
                    // Perform the database insertion
                    appDAO.insertTrackingTable(clusterHDRTable);
                    Log.e("=======>insertTrackingTable1", clusterHDRTable.getLatitude());
                    Log.e("=======>insertTrackingTable2", clusterHDRTable.getCreatedByUserId());
                    Log.e("=======>insertTrackingTable3", clusterHDRTable.getCreatedDate());

                    // Notify the listener about successful insertion
                    if (listener != null) {
                        listener.onInsertionComplete(true);
                    }
                } catch (Exception e) {
                    // Handle any exceptions and notify the listener about the failure
                    Log.e("InsertionError", "Error during database insertion", e);
                    if (listener != null) {
                        listener.onInsertionComplete(false);
                    }
                }
            });
        } else {
            Log.e("=======>error", "error 4867");
            if (listener != null) {
                listener.onInsertionComplete(false);
            }
        }
    }

    public LiveData<AddGeoBoundariesTrackingTable> insertTrackingIntoLocalDB(AddGeoBoundariesTrackingTable clusterHDRTable) {
        final MutableLiveData<AddGeoBoundariesTrackingTable> data = new MutableLiveData<>();

        if (executor != null) {
            executor.execute(() -> {
                // Perform the database insertion
                appDAO.insertTrackingTable(clusterHDRTable);
                Log.e("=======>insertTrackingTable1", clusterHDRTable.getLatitude());
                Log.e("=======>insertTrackingTable2", clusterHDRTable.getCreatedByUserId());
                Log.e("=======>insertTrackingTable3", clusterHDRTable.getCreatedDate());

                // Notify observers with the inserted data
                data.postValue(clusterHDRTable);
            });
        } else {
            Log.e("=======>error", "error 4867");
        }
        Log.e("=======>insertTrackingTable", data.toString());
        return data;
    }
//    public LiveData<AddGeoBoundariesTrackingTable> insertTrackingIntoLocalDB(AddGeoBoundariesTrackingTable clusterHDRTable) {
//
//        final MutableLiveData<AddGeoBoundariesTrackingTable> data = new MutableLiveData<>();
//        if (executor != null) {
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertTrackingTable(clusterHDRTable);
//            Log.e("=======>insertTrackingTable",clusterHDRTable.getUpdatedDate());;
//            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
//        });}
//          else  {
//Log.e("=======>error","error 4867");
//            }
//        return data;
//    }
//

    public LiveData<AddGeoBoundriesTable> insertGeoBoundariesIntoLocalDB(AddGeoBoundriesTable clusterHDRTable) {
        final MutableLiveData<AddGeoBoundriesTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertGeoBoundariesTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<UserSectionTable> insertUserSectionTableIntoLocalDB(UserSectionTable clusterHDRTable) {
        final MutableLiveData<UserSectionTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertUserSectionTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<D20FertilizerTable> insertD20FertilizerIntoLocalDBQuery(D20FertilizerTable clusterHDRTable) {
        final MutableLiveData<D20FertilizerTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD20Fertilizer(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<AddGrowthMonitoringTable> insertGrowthMonitoringIntoLocalDBQuery(AddGrowthMonitoringTable clusterHDRTable) {
        final MutableLiveData<AddGrowthMonitoringTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertGrowthMonitoringIntoLocalDBQuery(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<List<AddGrowthMonitoringTable>> insertGrowthMonitoringIntoLocalDBQuery(List<AddGrowthMonitoringTable> clusterHDRTable) {
        final MutableLiveData<List<AddGrowthMonitoringTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertGrowthMonitoringIntoLocalDBQuery(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<D20WeedTable> insertD20WeedIntoLocalDBQuery(D20WeedTable clusterHDRTable) {
        final MutableLiveData<D20WeedTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD20Weed(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<D20DiseaseTable> insertD20DiseaseIntoLocalDBQuery(D20DiseaseTable clusterHDRTable) {
        final MutableLiveData<D20DiseaseTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD20Disease(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<D20PestTable> insertD20PestIntoLocalDBQuery(D20PestTable clusterHDRTable) {
        final MutableLiveData<D20PestTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD20Pest(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<List<D20PestTable>> insertD20PestIntoLocalDBQuery(List<D20PestTable> clusterHDRTable) {
        final MutableLiveData<List<D20PestTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertD20Pest(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<AddPlotOfferTable> insertPlotOfferIntoLocalDB(AddPlotOfferTable clusterHDRTable) {
        final MutableLiveData<AddPlotOfferTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertAddPlotOfferTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<AddPlantationTable> insertPlantationIntoLocalDB(AddPlantationTable clusterHDRTable) {
        final MutableLiveData<AddPlantationTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertAddPlantationTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<DivisionTable> insertDivisionIntoLocalDB(DivisionTable clusterHDRTable) {
        final MutableLiveData<DivisionTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertDivisionTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

        public LiveData<List<DivisionTable>> insertDivisionIntoLocalDB(List<DivisionTable> clusterHDRTable) {
        final MutableLiveData<List<DivisionTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertDivisionTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }



    public LiveData<SectionTable> insertSectionIntoLocalDB(SectionTable clusterHDRTable) {
        final MutableLiveData<SectionTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertSectionTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }


    public LiveData<List<SectionTable>> insertSectionIntoLocalDB(List<SectionTable> clusterHDRTable) {
        final MutableLiveData<List<SectionTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertSectionTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }



    public LiveData<CircleTable> insertCircleIntoLocalDB(CircleTable clusterHDRTable) {
        final MutableLiveData<CircleTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertCircleTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
    public LiveData<List<CircleTable>> insertCircleIntoLocalDB(List<CircleTable> clusterHDRTable) {
        final MutableLiveData<List<CircleTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertCircleTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }


    public LiveData<ResonForNotPlantingTable> insertReasonIntoLocalDB(ResonForNotPlantingTable clusterHDRTable) {
        final MutableLiveData<ResonForNotPlantingTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertResonTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<VillageTable> insertVillageIntoLocalDB(VillageTable clusterHDRTable) {
        final MutableLiveData<VillageTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertVillageTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
    public LiveData<CropTable> insertCropIntoLocalDB(CropTable clusterHDRTable) {
        final MutableLiveData<CropTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertCropTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
        public LiveData<List<CropTable>> insertCropIntoLocalDB(List<CropTable> clusterHDRTable) {
        final MutableLiveData<List<CropTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertCropTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }


    public LiveData<BankTable> insertBankIntoLocalDB(BankTable clusterHDRTable) {
        final MutableLiveData<BankTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertBankTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }


    public LiveData<List<BankTable>> insertBankIntoLocalDB(List<BankTable> clusterHDRTable) {
        final MutableLiveData<List<BankTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertBankTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }


        public LiveData<BranchTable> insertBranchIntoLocalDB(BranchTable clusterHDRTable) {
        final MutableLiveData<BranchTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertBranchTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
            public LiveData<DiseaseTable> insertDiseaseIntoLocalDB(DiseaseTable clusterHDRTable) {
        final MutableLiveData<DiseaseTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertDiseaseTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
    public LiveData<DistrictTable> insertDistrictIntoLocalDB(DistrictTable clusterHDRTable) {
        final MutableLiveData<DistrictTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertDistrictTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<FertilizerTable> insertFertilizerIntoLocalDB(FertilizerTable clusterHDRTable) {
        final MutableLiveData<FertilizerTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertFertilizerTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<MandalTable> insertMandalIntoLocalDB(MandalTable clusterHDRTable) {
        final MutableLiveData<MandalTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertMandalTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<StateTable> insertStateIntoLocalDB(StateTable clusterHDRTable) {
        final MutableLiveData<StateTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertStateTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<UsersTable> insertUserIntoLocalDB(UsersTable clusterHDRTable) {
        final MutableLiveData<UsersTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertUsersTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<ParameterTable> insertParameterIntoLocalDB(ParameterTable clusterHDRTable) {
        final MutableLiveData<ParameterTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertParameterTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<PestTable> insertPestIntoLocalDB(PestTable clusterHDRTable) {
        final MutableLiveData<PestTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertPestTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<PlantTypeTable> insertPlantTypeIntoLocalDB(PlantTypeTable clusterHDRTable) {
        final MutableLiveData<PlantTypeTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertPlantTypeTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<PlantSubTypeTable> insertPlantSubTypeIntoLocalDB(PlantSubTypeTable clusterHDRTable) {
        final MutableLiveData<PlantSubTypeTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertPlantSubTypeTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<SampleSlabTable> insertSampleSlabIntoLocalDB(SampleSlabTable clusterHDRTable) {
        final MutableLiveData<SampleSlabTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertSampleSlabTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<SeasonTable> insertSeasonIntoLocalDB(SeasonTable clusterHDRTable) {
        final MutableLiveData<SeasonTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertSeasonTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<UOMTable> insertUOMIntoLocalDB(UOMTable clusterHDRTable) {
        final MutableLiveData<UOMTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertUOMTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<CastTable> insertCastIntoLocalDB(CastTable clusterHDRTable) {
        final MutableLiveData<CastTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertCastTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<LookupHDRTable> insertLookupHdrIntoLocalDB(LookupHDRTable clusterHDRTable) {
        final MutableLiveData<LookupHDRTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertLookupHdrTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<LookupDtlTable> insertLookupDtlIntoLocalDB(LookupDtlTable clusterHDRTable) {
        final MutableLiveData<LookupDtlTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertLookupDtlTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<PlotExistOnTable> insertPlotExistOnIntoLocalDB(PlotExistOnTable clusterHDRTable) {
        final MutableLiveData<PlotExistOnTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertPlotExistOnTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<VarietyTable> insertVarietyIntoLocalDB(VarietyTable clusterHDRTable) {
        final MutableLiveData<VarietyTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertVarietyTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<WarehouseTable> insertWarehouseIntoLocalDB(WarehouseTable clusterHDRTable) {
        final MutableLiveData<WarehouseTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertWarehouseTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<WeedTable> insertWeedIntoLocalDB(WeedTable clusterHDRTable) {
        final MutableLiveData<WeedTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertWeedTable(clusterHDRTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }

    public LiveData<KeyValue> insertKeyValueIntoLocalDB(KeyValue keyvalueTable) {
        final MutableLiveData<KeyValue> data = new MutableLiveData<>();
        executor.execute(() -> {
            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
            appDAO.insertKeyvalues(keyvalueTable);
            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
        });
        return data;
    }
//
//
//    public LiveData<SeasonTable> insertSeasonDataIntoLocalDBRepository(SeasonTable seasonTable) {
//        final MutableLiveData<SeasonTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncSeasonDataIntoLocalDB(seasonTable);
//            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
//        });
//        return data;
//    }
//
//
//    public LiveData<LogBookDropDownHDRTable> insertLogBookDropDownHDRIntoLocalDBRepository(LogBookDropDownHDRTable logBookDropDownHDRTable) {
//        final MutableLiveData<LogBookDropDownHDRTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncLogBookDropDownHDRDataIntoLocalDB(logBookDropDownHDRTable);
//            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
//        });
//        return data;
//    }
//
//
//    public LiveData<LookUpDropDownDataTable> insertLookUpDropDownDataIntoLocalDBRepository(LookUpDropDownDataTable lookUpDropDownDataTable) {
//        final MutableLiveData<LookUpDropDownDataTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncLookUpDropDownDataIntoLocalDB(lookUpDropDownDataTable);
//            //data.postValue(appDAO.getTopMasterSyncClusterTablDataLocalDBQuery(clusterHDRTable.getCode()));
//        });
//        return data;
//    }
//
//
//    public LiveData<CropListTable> insertCropListDataIntoLocalDBRepository(CropListTable cropListTable) {
//        final MutableLiveData<CropListTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncCropListIntoLocalDB(cropListTable);
//            data.postValue(appDAO.getTopMasterSyncCropListTablDataLocalDBQuery(cropListTable.getCode()));
//        });
//        return data;
//    }
//
//    public LiveData<CropVarietyListTable> insertCropVarietyListDataIntoLocalDBRepository(CropVarietyListTable cropListTable) {
//        final MutableLiveData<CropVarietyListTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            // FarmerDetailListTable topFarmerDetailListTableTableData = appDAO.getTopFarmerDetailListTableTableData(farmerDetailListTable.getFirstName(), farmerDetailListTable.getFarmerCode());
//            appDAO.insertMasterSyncCropVarietyListIntoLocalDB(cropListTable);
//            data.postValue(appDAO.getTopMasterSyncCropVarietyListTablDataLocalDBQuery(cropListTable.getCode()));
//        });
//        return data;
//    }
//
//    // TODO: get cluster  Details from server
//    public LiveData<List<ClusterDTlResponseDTO>> getClusterDTLListFromServerToSaveLocalDB() {
//        final MutableLiveData<List<ClusterDTlResponseDTO>> data = new MutableLiveData<>();
//        try {
//            AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//            executor.execute(() -> {
//                if (appHelper.isNetworkAvailable()) {// TODO: Checking internet connection
//                    AppWebService.createService(AppAPI.class).getMasterSyncListFromServerTosaveIntoLocalDB(
//                            appHelper.getSharedPrefObj().getString(accessToken, ""))
//                            .enqueue(new Callback<List<ClusterDTlResponseDTO>>() {
//                                @Override
//                                public void onResponse(Call<List<ClusterDTlResponseDTO>> call, Response<List<ClusterDTlResponseDTO>> response) {
//                                    Log.e("TAG", "ClusterDTlResponseDTO REFRESHED FROM NETWORK");
//                                    executor.execute(() -> {
//                                        Log.d(TAG, "ClusterDTlResponseDTO: data " + response.body());
//                                        List<ClusterDTlResponseDTO> clusterDTlResponseDTOList = response.body();
////                                        List<ClusterDTlResponseDTO> clusterDTlResponseDTOList = null;
////                                        try{
////                                            JSONArray json = new JSONArray(response.body());
////                                            Log.e(TAG, "onResponse: +json" + json );
////                                            for(int i=0;i<json.length();i++){
////                                                JSONArray array = json.getJSONArray(6);
////                                                Log.d(TAG, "onResponse: array " + array);
//////                                                for(int j=6;i<array.length();j++){
//////                                                    JSONObject object = array.getJSONObject(j);
//////                                                   // String Notification = object.getString("Notification");
//////
//////                                                }
////                                              //  clusterDTlResponseDTOList = new Gson().fromJson(String.valueOf(array),ClusterDTlResponseDTO.class);
////                                                clusterDTlResponseDTOList = new Gson().fromJson(String.valueOf(array),ClusterDTlResponseDTO.class);
////
////                                            }
////                                        }
////                                        catch (JSONException e){
////                                            e.printStackTrace();
////                                        }
////
////                                      //  List<ClusterDTlResponseDTO> clusterDTlResponseDTOList = response.body();
////                                        if (clusterDTlResponseDTOList != null && clusterDTlResponseDTOList.size() > 0) {
////                                            Log.d(TAG, "onResponse: +cluster " + clusterDTlResponseDTOList);
//////                                            for (ClusterDTLTable clusterDTLTable : clusterDTlResponseDTOList)
////////
//////                                           ClusterDTLTable clusterHDRTable1 = new ClusterDTLTable();
//////                                             Log.d("onResponseData: ", clusterDTlResponseDTOList.get(6).getId() + clusterDTlResponseDTOList.getClusterHDRId());
//////                                            clusterHDRTable1.setId(clusterDTlResponseDTOList.get(6).getId());
//////                                            clusterHDRTable1.setClusterHDRId(clusterDTlResponseDTOList.get(6).getClusterHDRId());
//////                                            clusterHDRTable1.setVillageId(clusterDTlResponseDTOList.get(6).getVillageId());
////                                        }  else {
////                                            // TODO: Sending Final Result
////                                            List<ClusterDTlResponseDTO> emptyStageList = new ArrayList<>();
////                                            data.postValue(emptyStageList);
////                                        }
////                                        }
//                                        data.postValue(clusterDTlResponseDTOList);
//                                        //List<ClusterDTLTable>  clusterDTLTablesData = new List();
////                                        if (clusterHDRTableList != null && clusterHDRTableList.size() > 0) {
////
////                                            clusterHDRTable1 = new ClusterDTLTable();
////                                           // Log.d("onResponseData: ", clusterDTLTable.getId() + clusterDTLTable.getClusterHDRId());
////                                            clusterHDRTable1.setId(clusterHDRTableList.get(6).getId());
////                                            clusterHDRTable1.setClusterHDRId(clusterHDRTableList.get(6).getClusterHDRId());
////                                            clusterHDRTable1.setVillageId(clusterHDRTableList.get(6).getVillageId());
////                                            clusterHDRTableList.add(clusterHDRTable1);
////                                            Log.d(TAG, "onResponse: DataCluster"+clusterHDRTableListData);
//////                                            for (int i =0 ;i < clusterHDRTableList.size() ;i++)
//////                                            {
////////                                                if (i==6)
////////                                                {
////////                                                    for (ClusterDTLTable clusterDTLTable : clusterHDRTableList) {
////////                                                        clusterHDRTable1 = new ClusterDTLTable();
////////                                                        Log.d("onResponseData: ", clusterDTLTable.getId() + clusterDTLTable.getClusterHDRId());
////////                                                        clusterHDRTable1.setId(clusterDTLTable.getId());
////////                                                        clusterHDRTable1.setClusterHDRId(clusterDTLTable.getClusterHDRId());
////////                                                        clusterHDRTable1.setVillageId(clusterDTLTable.getVillageId());
////////                                                        clusterHDRTableList.add(clusterHDRTable1);
////////                                                    }
////////                                                }
//////
//////                                            }
////
////
////                                            // TODO: Sending Final Result
////                                            data.postValue(clusterHDRTableListData);
////                                        } else {
////                                            // TODO: Sending Final Result
////                                            List<ClusterDTLTable> emptyStageList = new ArrayList<>();
////                                            data.postValue(emptyStageList);
////                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<ClusterDTlResponseDTO>> call, Throwable t) {
//                                    try {
//                                        executor.execute(() -> {
//                                            // TODO: Sending Final Result
//                                            List<ClusterDTlResponseDTO> emptyStageList = new ArrayList<>();
//                                            data.postValue(emptyStageList);
//
//                                        });
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            });
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            executor.execute(() -> {
//                // TODO: Sending Final Result
//                List<ClusterDTlResponseDTO> emptyStageList = new ArrayList<>();
//                data.postValue(emptyStageList);
//
//            });
//        }
//        return data;
//    }
//
//    public LiveData<List<ClusterDTLTable>> getClusterDTlResponseDTOListForCollection() {
//        final MutableLiveData<List<ClusterDTLTable>> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            AppWebService.createService(AppAPI.class).getListFromServerTosaveIntoLocalDB(
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONArray jsonArray = new JSONArray(strResponse);
//                                    Log.d(TAG, "onResponse: datavalue" + jsonArray);
//                                    List<ClusterDTLTable> collectionCustomerTableList = new ArrayList<>();
//                                    if (jsonArray.length() > 0) {
//                                        JSONArray json_arry = jsonArray.getJSONArray(6);
//                                        Log.d(TAG, "onResponse: jsonsub_value" + json_arry);
//                                        for (int j = 0; j < json_arry.length(); j++) {
//                                            JSONObject jsonObject = json_arry.getJSONObject(j);
//                                            Log.d(TAG, "onResponse: " + jsonObject);
//                                            ClusterDTLTable collectionCustomerTable = new ClusterDTLTable();
//                                            collectionCustomerTable.setId(jsonObject.getString("Id"));
//                                            collectionCustomerTable.setClusterHDRId(jsonObject.getString("ClusterHDRId"));
//                                            collectionCustomerTable.setVillageId(jsonObject.getString("VillageId"));
//                                            collectionCustomerTableList.add(collectionCustomerTable);
//                                        }
//
//                                        // }
//                                    }
//                                    if (collectionCustomerTableList != null && collectionCustomerTableList.size() > 0) {
//
//                                        Log.e(TAG, "onResponse: sucess" + collectionCustomerTableList);
//                                        // TODO: Sending result
//                                        data.postValue(collectionCustomerTableList);
//
//                                    } else {
//                                        // TODO: Sending result
//                                        List<ClusterDTLTable> emptyStageList = new ArrayList<>();
//                                        data.postValue(emptyStageList);
//                                        // data.postValue(new ArrayList<>());
//                                    }
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    List<ClusterDTLTable> emptyStageList = new ArrayList<>();
//                                    data.postValue(emptyStageList);
//                                    // data.postValue(new ArrayList<>());
//                                }
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                List<ClusterDTLTable> emptyStageList = new ArrayList<>();
//                                data.postValue(emptyStageList);
//                            });
//                        }
//                    });
//
//        });
//        return data;
//    }
//
//    public LiveData<List<MandalTable>> getMandalResponseDTOListForCollection() {
//        final MutableLiveData<List<MandalTable>> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            AppWebService.createService(AppAPI.class).getListFromServerTosaveIntoLocalDB(
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    JSONArray jsonArray = new JSONArray(strResponse);
//                                    List<MandalTable> collectionCustomerTableList = new ArrayList<>();
//                                    if (jsonArray.length() > 0) {
//                                        JSONArray json_arry = jsonArray.getJSONArray(9);
//                                        Log.d(TAG, "onResponse: jsonsub_value" + json_arry);
//                                        for (int j = 0; j < json_arry.length(); j++) {
//                                            JSONObject jsonObject = json_arry.getJSONObject(j);
//                                            Log.d(TAG, "onResponse: " + jsonObject);
//                                            MandalTable collectionCustomerTable = new MandalTable();
//                                            collectionCustomerTable.setId(jsonObject.getString("Id"));
//                                            collectionCustomerTable.setName(jsonObject.getString("Name"));
//                                            collectionCustomerTable.setDistrictId(jsonObject.getString("DistrictId"));
//                                            collectionCustomerTable.setCode(jsonObject.getString("Code"));
//                                            collectionCustomerTableList.add(collectionCustomerTable);
//                                        }
//                                    }
//                                    if (collectionCustomerTableList != null && collectionCustomerTableList.size() > 0) {
//
//                                        Log.e(TAG, "onResponse: sucess" + collectionCustomerTableList);
//                                        // TODO: Sending result
//                                        data.postValue(collectionCustomerTableList);
//
//                                    } else {
//                                        // TODO: Sending result
//                                        List<MandalTable> emptyStageList = new ArrayList<>();
//                                        data.postValue(emptyStageList);
//                                        // data.postValue(new ArrayList<>());
//                                    }
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    List<MandalTable> emptyStageList = new ArrayList<>();
//                                    data.postValue(emptyStageList);
//                                    // data.postValue(new ArrayList<>());
//                                }
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                List<MandalTable> emptyStageList = new ArrayList<>();
//                                data.postValue(emptyStageList);
//                            });
//                        }
//                    });
//
//        });
//        return data;
//    }
//
//    public LiveData<List<DistrictTable>> getDistrictTableResponseDTOListForCollection() {
//        final MutableLiveData<List<DistrictTable>> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            AppWebService.createService(AppAPI.class).getListFromServerTosaveIntoLocalDB(
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONArray jsonArray = new JSONArray(strResponse);
//                                    Log.d(TAG, "onResponse: datavalue" + jsonArray);
//                                    List<DistrictTable> collectionCustomerTableList = new ArrayList<>();
//                                    if (jsonArray.length() > 0) {
//                                        JSONArray json_arry = jsonArray.getJSONArray(8);
//                                        Log.d(TAG, "onResponse: jsonsub_value" + json_arry);
//                                        for (int j = 0; j < json_arry.length(); j++) {
//                                            JSONObject jsonObject = json_arry.getJSONObject(j);
//                                            JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonObject);
//                                            DistrictTable collectionCustomerTable = new DistrictTable();
//                                            collectionCustomerTable.setId(jsonObject.getString("Id"));
//                                            collectionCustomerTable.setName(jsonObject.getString("Name"));
//                                            collectionCustomerTable.setStateId(jsonObject.getString("StateId"));
//                                            collectionCustomerTable.setCode(jsonObject.getString("Code"));
//
////                                        DistrictTable collectionCustomerTable1 = new DistrictTable();
////                                        collectionCustomerTable1.setId(json_Object.getString("Id"));
////                                        collectionCustomerTable1.setName(json_Object.getString("Name"));
////                                        collectionCustomerTable1.setStateId(json_Object.getString("StateId"));
////                                        collectionCustomerTable1.setCode(json_Object.getString("Code"));
////
////                                        collectionCustomerTableList.add(collectionCustomerTable1);
//
//                                            collectionCustomerTableList.add(collectionCustomerTable);
//                                        }
//                                        // }
//                                    }
//                                    if (collectionCustomerTableList != null && collectionCustomerTableList.size() > 0) {
//                                        Log.e(TAG, "onResponse: sucess" + collectionCustomerTableList);
//                                        // TODO: Sending result
//                                        data.postValue(collectionCustomerTableList);
//
//                                    } else {
//                                        // TODO: Sending result
//                                        List<DistrictTable> emptyStageList = new ArrayList<>();
//                                        data.postValue(emptyStageList);
//                                        // data.postValue(new ArrayList<>());
//                                    }
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    List<DistrictTable> emptyStageList = new ArrayList<>();
//                                    data.postValue(emptyStageList);
//                                    // data.postValue(new ArrayList<>());
//                                }
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                List<DistrictTable> emptyStageList = new ArrayList<>();
//                                data.postValue(emptyStageList);
//                            });
//                        }
//                    });
//
//        });
//        return data;
//    }
//
//    public LiveData<List<VillageTable>> getVillageTableResponseDTOListForCollection() {
//        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            AppWebService.createService(AppAPI.class).getListFromServerTosaveIntoLocalDB(
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    JSONArray jsonArray = new JSONArray(strResponse);
//                                    List<VillageTable> collectionCustomerTableList = new ArrayList<>();
//                                    if (jsonArray.length() > 0) {
//                                        JSONArray json_arry = jsonArray.getJSONArray(10);
//                                        for (int j = 0; j < json_arry.length(); j++) {
//                                            JSONObject jsonObject = json_arry.getJSONObject(j);
//                                            VillageTable collectionCustomerTable = new VillageTable();
//                                            collectionCustomerTable.setId(jsonObject.getString("Id"));
//                                            collectionCustomerTable.setName(jsonObject.getString("Name"));
//                                            collectionCustomerTable.setMandalId(jsonObject.getString("MandalId"));
//                                            collectionCustomerTable.setCode(jsonObject.getString("Code"));
//                                            collectionCustomerTable.setPinCode(jsonObject.optString("PinCode"));
//                                            collectionCustomerTableList.add(collectionCustomerTable);
//                                        }
//                                    }
//
//                                    if (collectionCustomerTableList != null && collectionCustomerTableList.size() > 0) {
//
//                                        Log.e(TAG, "onResponse: sucess" + collectionCustomerTableList);
//                                        // TODO: Sending result
//                                        data.postValue(collectionCustomerTableList);
//
//                                    } else {
//                                        // TODO: Sending result
//                                        List<VillageTable> emptyStageList = new ArrayList<>();
//                                        data.postValue(emptyStageList);
//                                        // data.postValue(new ArrayList<>());
//                                    }
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    List<VillageTable> emptyStageList = new ArrayList<>();
//                                    data.postValue(emptyStageList);
//                                    // data.postValue(new ArrayList<>());
//                                }
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                List<VillageTable> emptyStageList = new ArrayList<>();
//                                data.postValue(emptyStageList);
//                            });
//                        }
//                    });
//
//        });
//        return data;
//    }
//
//    // TODO: 1/21/2022 get local db values
//
//    public LiveData<List<CropListTable>> getCropListTableFromLocalDB() {
//        final MutableLiveData<List<CropListTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getCropListTableTableDetailsListFromLocalDB() != null);
//            if (dataExist) {
//                data.postValue(appDAO.getCropListTableTableDetailsListFromLocalDB());
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<ClusterHDRTable>> getClusterHDRTableDetailslistFromLocalDB() {
//        final MutableLiveData<List<ClusterHDRTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getClusterHDRTableDetailsListFromLocalDB() != null);
//            if (dataExist) {
//                data.postValue(appDAO.getClusterHDRTableDetailsListFromLocalDB());
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<VillageTable>> getVillageListFromLocalDB() {
//        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getVillageTableDetailsListFromLocalDB() != null);
//            if (dataExist) {
//                data.postValue(appDAO.getVillageTableDetailsListFromLocalDB());
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<CropVarietyListTable>> getCropVarietyListTableFromLocalDB(String cropId) {
//        final MutableLiveData<List<CropVarietyListTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.findCropVarietyByCropId(cropId) != null);
//            if (dataExist) {
//                data.postValue(appDAO.findCropVarietyByCropId(cropId));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<VillageTable>> getVillageTableDetailsFromLocalDB(String clusterID) {
//        final MutableLiveData<List<VillageTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.findVillageIdbyClusterId(clusterID) != null);
//            if (dataExist) {
//                data.postValue(appDAO.findVillageIdbyClusterId(clusterID));
//            }
//        });
//        return data;
//    }
//
//
//    public LiveData<List<GetMasterPersonalLandAllDetailsRequestDTO>> getMasterSynDetailsFromServer() {
//        final MutableLiveData<List<GetMasterPersonalLandAllDetailsRequestDTO>> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            AppWebService.createService(AppAPI.class).getListFromServerTosaveIntoLocalDB(
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONArray jsonArray = new JSONArray(strResponse);
//                                    Log.d(TAG, "onResponse: datavalue" + jsonArray);
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO> getMasterPersonalLandAllDetailsRequestDTOList = new ArrayList<>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.Farmer> getFarmerList = new ArrayList<>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.ClusterHDR> getClusterHDRList = new ArrayList<>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.ClusterDTL> ClusterDTLList = new ArrayList<GetMasterPersonalLandAllDetailsRequestDTO.ClusterDTL>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.State> StateList = new ArrayList<GetMasterPersonalLandAllDetailsRequestDTO.State>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.Distric> DistricList = new ArrayList<GetMasterPersonalLandAllDetailsRequestDTO.Distric>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.Mandal> MandalList = new ArrayList<GetMasterPersonalLandAllDetailsRequestDTO.Mandal>();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO.Village> VillageList = new ArrayList<GetMasterPersonalLandAllDetailsRequestDTO.Village>();
//                                    if (jsonArray.length() > 0) {
//                                        JSONArray jsonFarmerArray = jsonArray.getJSONArray(0);
//                                        JSONArray jsonLandArray = jsonArray.getJSONArray(1);
//                                        JSONArray jsonGeoArray = jsonArray.getJSONArray(2);
//                                        JSONArray jsonBankArray = jsonArray.getJSONArray(3);
//                                        JSONArray jsonIdentityArray = jsonArray.getJSONArray(4);
//                                        JSONArray jsonClusterHDRArray = jsonArray.getJSONArray(5);
//                                        JSONArray jsonClusterDTLArray = jsonArray.getJSONArray(6);
//                                        JSONArray jsonStateArray = jsonArray.getJSONArray(7);
//                                        JSONArray jsonDistricArray = jsonArray.getJSONArray(8);
//                                        JSONArray jsonMandalArray = jsonArray.getJSONArray(9);
//                                        JSONArray jsonVillageArray = jsonArray.getJSONArray(10);
//
////                                        for (int j = 0; j < jsonFarmerArray.length(); j++) {
////                                            JSONObject jsonObjectFarmer = jsonFarmerArray.getJSONObject(j);
////                                           // JSONObject json_Object = json_arry.getJSONObject(27);
////                                            Log.d(TAG, "onResponse: " + jsonObjectFarmer);
////                                            GetMasterPersonalLandAllDetailsRequestDTO.Farmer farmer_value = new GetMasterPersonalLandAllDetailsRequestDTO.Farmer();
////                                            farmer_value.setFarmerCode(jsonObjectFarmer.getString("FarmerCode"));
////                                            farmer_value.setFarmerTittle(jsonObjectFarmer.getString("FarmerTitle"));
////                                            farmer_value.setFirstName(jsonObjectFarmer.getString("FirstName"));
////                                            farmer_value.setMiddleName(jsonObjectFarmer.getString("MiddleName"));
////                                            farmer_value.setLastName(jsonObjectFarmer.getString("LastName"));
////                                            farmer_value.setFatherName(jsonObjectFarmer.getString("FatherName"));
////                                            farmer_value.setGender(jsonObjectFarmer.getString("Gender"));
////                                            farmer_value.setAge(jsonObjectFarmer.getString("Age"));
////                                            farmer_value.setPrimaryContactNum(jsonObjectFarmer.getString("PrimaryContactNum"));
////                                            farmer_value.setSecondaryContactNum(jsonObjectFarmer.getString("SecondaryContactNum"));
////                                            farmer_value.setAddress(jsonObjectFarmer.getString("Address"));
////                                            farmer_value.setDistrictId(jsonObjectFarmer.getString("DistrictId"));
////                                            farmer_value.setMandalId(jsonObjectFarmer.getString("MandalId"));
////                                            farmer_value.setStateId(jsonObjectFarmer.getString("StateId"));
////                                            farmer_value.setVillageId(jsonObjectFarmer.getString("VillageId"));
////                                            farmer_value.setPinCode(jsonObjectFarmer.getString("PinCode"));
////                                          //  farmer_value.setSync(jsonObjectFarmer.getString("Sync"));
////                                            farmer_value.setIsPlot(jsonObjectFarmer.getString("IsPlot"));
////
////                                            getFarmerList.add(farmer_value);
////                                        }
////                                        for (int k = 0; k < jsonLandArray.length(); k++) {
////                                            JSONObject jsonObjectFarmer = jsonFarmerArray.getJSONObject(k);
////                                            // JSONObject json_Object = json_arry.getJSONObject(27);
////                                            Log.d(TAG, "onResponse: " + jsonObjectFarmer);
//////                                            GetMasterPersonalLandAllDetailsRequestDTO.Farmer farmer_value = new GetMasterPersonalLandAllDetailsRequestDTO.Farmer();
//////                                            farmer_value.setFarmerCode(jsonObjectFarmer.getString("FarmerCode"));
//////                                            farmer_value.setFarmerTittle(jsonObjectFarmer.getString("FarmerTitle"));
//////                                            farmer_value.setFirstName(jsonObjectFarmer.getString("FirstName"));
//////                                            farmer_value.setMiddleName(jsonObjectFarmer.getString("MiddleName"));
//////                                            farmer_value.setLastName(jsonObjectFarmer.getString("LastName"));
//////                                            farmer_value.setFatherName(jsonObjectFarmer.getString("FatherName"));
//////                                            farmer_value.setGender(jsonObjectFarmer.getString("Gender"));
//////                                            farmer_value.setAge(jsonObjectFarmer.getString("Age"));
//////                                            farmer_value.setPrimaryContactNum(jsonObjectFarmer.getString("PrimaryContactNum"));
//////                                            farmer_value.setSecondaryContactNum(jsonObjectFarmer.getString("SecondaryContactNum"));
//////                                            farmer_value.setAddress(jsonObjectFarmer.getString("Address"));
//////                                            farmer_value.setDistrictId(jsonObjectFarmer.getString("DistrictId"));
//////                                            farmer_value.setMandalId(jsonObjectFarmer.getString("MandalId"));
//////                                            farmer_value.setStateId(jsonObjectFarmer.getString("StateId"));
//////                                            farmer_value.setVillageId(jsonObjectFarmer.getString("VillageId"));
//////                                            farmer_value.setPinCode(jsonObjectFarmer.getString("PinCode"));
//////                                            //  farmer_value.setSync(jsonObjectFarmer.getString("Sync"));
//////                                            farmer_value.setIsPlot(jsonObjectFarmer.getString("IsPlot"));
////
////                                            //getFarmerList.add(farmer_value);
////                                        }
////                                        for (int l = 0; l < jsonGeoArray.length(); l++) {
////                                            JSONObject jsonObjectFarmer = jsonFarmerArray.getJSONObject(l);
////                                            // JSONObject json_Object = json_arry.getJSONObject(27);
////                                            Log.d(TAG, "onResponse: " + jsonObjectFarmer);
//////                                            GetMasterPersonalLandAllDetailsRequestDTO.Farmer farmer_value = new GetMasterPersonalLandAllDetailsRequestDTO.Farmer();
//////                                            farmer_value.setFarmerCode(jsonObjectFarmer.getString("FarmerCode"));
//////                                            farmer_value.setFarmerTittle(jsonObjectFarmer.getString("FarmerTitle"));
//////                                            farmer_value.setFirstName(jsonObjectFarmer.getString("FirstName"));
//////                                            farmer_value.setMiddleName(jsonObjectFarmer.getString("MiddleName"));
//////                                            farmer_value.setLastName(jsonObjectFarmer.getString("LastName"));
//////                                            farmer_value.setFatherName(jsonObjectFarmer.getString("FatherName"));
//////                                            farmer_value.setGender(jsonObjectFarmer.getString("Gender"));
//////                                            farmer_value.setAge(jsonObjectFarmer.getString("Age"));
//////                                            farmer_value.setPrimaryContactNum(jsonObjectFarmer.getString("PrimaryContactNum"));
//////                                            farmer_value.setSecondaryContactNum(jsonObjectFarmer.getString("SecondaryContactNum"));
//////                                            farmer_value.setAddress(jsonObjectFarmer.getString("Address"));
//////                                            farmer_value.setDistrictId(jsonObjectFarmer.getString("DistrictId"));
//////                                            farmer_value.setMandalId(jsonObjectFarmer.getString("MandalId"));
//////                                            farmer_value.setStateId(jsonObjectFarmer.getString("StateId"));
//////                                            farmer_value.setVillageId(jsonObjectFarmer.getString("VillageId"));
//////                                            farmer_value.setPinCode(jsonObjectFarmer.getString("PinCode"));
//////                                            //  farmer_value.setSync(jsonObjectFarmer.getString("Sync"));
//////                                            farmer_value.setIsPlot(jsonObjectFarmer.getString("IsPlot"));
//////
//////                                            getFarmerList.add(farmer_value);
////                                        }
////                                        for (int m = 0; m < jsonBankArray.length(); m++) {
////                                            JSONObject jsonObjectFarmer = jsonFarmerArray.getJSONObject(m);
////                                            // JSONObject json_Object = json_arry.getJSONObject(27);
////                                            Log.d(TAG, "onResponse: " + jsonObjectFarmer);
//////                                            GetMasterPersonalLandAllDetailsRequestDTO.Farmer farmer_value = new GetMasterPersonalLandAllDetailsRequestDTO.Farmer();
//////                                            farmer_value.setFarmerCode(jsonObjectFarmer.getString("FarmerCode"));
//////                                            farmer_value.setFarmerTittle(jsonObjectFarmer.getString("FarmerTitle"));
//////                                            farmer_value.setFirstName(jsonObjectFarmer.getString("FirstName"));
//////                                            farmer_value.setMiddleName(jsonObjectFarmer.getString("MiddleName"));
//////                                            farmer_value.setLastName(jsonObjectFarmer.getString("LastName"));
//////                                            farmer_value.setFatherName(jsonObjectFarmer.getString("FatherName"));
//////                                            farmer_value.setGender(jsonObjectFarmer.getString("Gender"));
//////                                            farmer_value.setAge(jsonObjectFarmer.getString("Age"));
//////                                            farmer_value.setPrimaryContactNum(jsonObjectFarmer.getString("PrimaryContactNum"));
//////                                            farmer_value.setSecondaryContactNum(jsonObjectFarmer.getString("SecondaryContactNum"));
//////                                            farmer_value.setAddress(jsonObjectFarmer.getString("Address"));
//////                                            farmer_value.setDistrictId(jsonObjectFarmer.getString("DistrictId"));
//////                                            farmer_value.setMandalId(jsonObjectFarmer.getString("MandalId"));
//////                                            farmer_value.setStateId(jsonObjectFarmer.getString("StateId"));
//////                                            farmer_value.setVillageId(jsonObjectFarmer.getString("VillageId"));
//////                                            farmer_value.setPinCode(jsonObjectFarmer.getString("PinCode"));
//////                                            //  farmer_value.setSync(jsonObjectFarmer.getString("Sync"));
//////                                            farmer_value.setIsPlot(jsonObjectFarmer.getString("IsPlot"));
//////
//////                                            getFarmerList.add(farmer_value);
////                                        }
////                                        for (int n = 0; n < jsonIdentityArray.length(); n++) {
////                                            JSONObject jsonObjectFarmer = jsonFarmerArray.getJSONObject(n);
////                                            // JSONObject json_Object = json_arry.getJSONObject(27);
////                                            Log.d(TAG, "onResponse: " + jsonObjectFarmer);
//////                                            GetMasterPersonalLandAllDetailsRequestDTO.Farmer farmer_value = new GetMasterPersonalLandAllDetailsRequestDTO.Farmer();
//////                                            farmer_value.setFarmerCode(jsonObjectFarmer.getString("FarmerCode"));
//////                                            farmer_value.setFarmerTittle(jsonObjectFarmer.getString("FarmerTitle"));
//////                                            farmer_value.setFirstName(jsonObjectFarmer.getString("FirstName"));
//////                                            farmer_value.setMiddleName(jsonObjectFarmer.getString("MiddleName"));
//////                                            farmer_value.setLastName(jsonObjectFarmer.getString("LastName"));
//////                                            farmer_value.setFatherName(jsonObjectFarmer.getString("FatherName"));
//////                                            farmer_value.setGender(jsonObjectFarmer.getString("Gender"));
//////                                            farmer_value.setAge(jsonObjectFarmer.getString("Age"));
//////                                            farmer_value.setPrimaryContactNum(jsonObjectFarmer.getString("PrimaryContactNum"));
//////                                            farmer_value.setSecondaryContactNum(jsonObjectFarmer.getString("SecondaryContactNum"));
//////                                            farmer_value.setAddress(jsonObjectFarmer.getString("Address"));
//////                                            farmer_value.setDistrictId(jsonObjectFarmer.getString("DistrictId"));
//////                                            farmer_value.setMandalId(jsonObjectFarmer.getString("MandalId"));
//////                                            farmer_value.setStateId(jsonObjectFarmer.getString("StateId"));
//////                                            farmer_value.setVillageId(jsonObjectFarmer.getString("VillageId"));
//////                                            farmer_value.setPinCode(jsonObjectFarmer.getString("PinCode"));
//////                                            //  farmer_value.setSync(jsonObjectFarmer.getString("Sync"));
//////                                            farmer_value.setIsPlot(jsonObjectFarmer.getString("IsPlot"));
//////
//////                                            getFarmerList.add(farmer_value);
////                                        }
//
//                                        for (int clusterHDR = 0; clusterHDR < jsonClusterHDRArray.length(); clusterHDR++) {
//                                            JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
//                                            // JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonObjectClusterHDR);
//                                            GetMasterPersonalLandAllDetailsRequestDTO.ClusterHDR clusterHDr_value = new GetMasterPersonalLandAllDetailsRequestDTO.ClusterHDR();
//                                            clusterHDr_value.setId(jsonObjectClusterHDR.getString("Id"));
//                                            clusterHDr_value.setCode(jsonObjectClusterHDR.getString("Code"));
//                                            clusterHDr_value.setName(jsonObjectClusterHDR.getString("Name"));
//                                            getClusterHDRList.add(clusterHDr_value);
//                                        }
//
//                                        for (int clusterDTL = 0; clusterDTL < jsonClusterDTLArray.length(); clusterDTL++) {
//                                            JSONObject jsonObjectclusterDTL = jsonClusterDTLArray.getJSONObject(clusterDTL);
//                                            // JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonObjectclusterDTL);
//                                            GetMasterPersonalLandAllDetailsRequestDTO.ClusterDTL clusterDTL_value = new GetMasterPersonalLandAllDetailsRequestDTO.ClusterDTL();
//                                            clusterDTL_value.setId(jsonObjectclusterDTL.getString("Id"));
//                                            clusterDTL_value.setClusterHDRId(jsonObjectclusterDTL.getString("ClusterHDRId"));
//                                            clusterDTL_value.setVillageId(jsonObjectclusterDTL.getString("VillageId"));
//                                            ClusterDTLList.add(clusterDTL_value);
//                                        }
//
//                                        for (int state = 0; state < jsonStateArray.length(); state++) {
//                                            JSONObject jsonObjectState = jsonStateArray.getJSONObject(state);
//                                            // JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonObjectState);
//                                            GetMasterPersonalLandAllDetailsRequestDTO.State state_value = new GetMasterPersonalLandAllDetailsRequestDTO.State();
//                                            state_value.setId(jsonObjectState.getString("Id"));
//                                            state_value.setCode(jsonObjectState.getString("Code"));
//                                            state_value.setName(jsonObjectState.getString("Name"));
//                                            // state_value.setVillageId(jsonObjectState.getString("VillageId"));
//                                            StateList.add(state_value);
//                                        }
//
//                                        for (int distric = 0; distric < jsonDistricArray.length(); distric++) {
//                                            JSONObject jsonObjectDistric = jsonDistricArray.getJSONObject(distric);
//                                            // JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonObjectDistric);
//                                            GetMasterPersonalLandAllDetailsRequestDTO.Distric distric_value = new GetMasterPersonalLandAllDetailsRequestDTO.Distric();
//                                            distric_value.setId(jsonObjectDistric.getString("Id"));
//                                            distric_value.setCode(jsonObjectDistric.getString("Code"));
//                                            distric_value.setName(jsonObjectDistric.getString("Name"));
//                                            distric_value.setStateId(jsonObjectDistric.getString("StateId"));
//                                            DistricList.add(distric_value);
//                                        }
//
//                                        for (int mandal = 0; mandal < jsonMandalArray.length(); mandal++) {
//                                            JSONObject jsonObjectMandal = jsonMandalArray.getJSONObject(mandal);
//                                            // JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonObjectMandal);
//                                            GetMasterPersonalLandAllDetailsRequestDTO.Mandal mandal_value = new GetMasterPersonalLandAllDetailsRequestDTO.Mandal();
//                                            mandal_value.setId(jsonObjectMandal.getString("Id"));
//                                            mandal_value.setCode(jsonObjectMandal.getString("Code"));
//                                            mandal_value.setName(jsonObjectMandal.getString("Name"));
//                                            mandal_value.setDistrictId(jsonObjectMandal.getString("DistrictId"));
//                                            MandalList.add(mandal_value);
//                                        }
//
//                                        for (int village = 0; village < jsonVillageArray.length(); village++) {
//                                            JSONObject jsonObjectVillage = jsonVillageArray.getJSONObject(village);
//                                            // JSONObject json_Object = json_arry.getJSONObject(27);
//                                            Log.d(TAG, "onResponse: " + jsonVillageArray);
//                                            GetMasterPersonalLandAllDetailsRequestDTO.Village village_value = new GetMasterPersonalLandAllDetailsRequestDTO.Village();
//                                            village_value.setId(jsonObjectVillage.getString("Id"));
//                                            village_value.setCode(jsonObjectVillage.getString("Code"));
//                                            village_value.setName(jsonObjectVillage.getString("Name"));
//                                            village_value.setMandalId(jsonObjectVillage.getString("MandalId"));
//                                            VillageList.add(village_value);
//                                        }
//
//                                        //getMasterPersonalLandAllDetailsRequestDTOList.get(0).getFarmer().addAll(getFarmerList);
//
//                                        getMasterPersonalLandAllDetailsRequestDTOList.get(5).getClusterHDR().addAll(getClusterHDRList);
//                                        getMasterPersonalLandAllDetailsRequestDTOList.get(6).getClusterDTL().addAll(ClusterDTLList);
//                                        getMasterPersonalLandAllDetailsRequestDTOList.get(7).getState().addAll(StateList);
//                                        getMasterPersonalLandAllDetailsRequestDTOList.get(8).getDistric().addAll(DistricList);
//                                        getMasterPersonalLandAllDetailsRequestDTOList.get(9).getMandal().addAll(MandalList);
//                                        getMasterPersonalLandAllDetailsRequestDTOList.get(10).getVillage().addAll(VillageList);
//                                    }
//
//                                    if (getMasterPersonalLandAllDetailsRequestDTOList != null && getMasterPersonalLandAllDetailsRequestDTOList.size() > 0) {
//                                        Log.e(TAG, "onResponse: sucess data" + getMasterPersonalLandAllDetailsRequestDTOList);
//                                        // TODO: Sending result
//                                        data.postValue(getMasterPersonalLandAllDetailsRequestDTOList);
//
//                                    } else {
//                                        // TODO: Sending result
//                                        List<GetMasterPersonalLandAllDetailsRequestDTO> emptyStageList = new ArrayList<>();
//                                        data.postValue(emptyStageList);
//                                        // data.postValue(new ArrayList<>());
//                                    }
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    List<GetMasterPersonalLandAllDetailsRequestDTO> emptyStageList = new ArrayList<>();
//                                    data.postValue(emptyStageList);
//                                    // data.postValue(new ArrayList<>());
//                                }
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                List<GetMasterPersonalLandAllDetailsRequestDTO> emptyStageList = new ArrayList<>();
//                                data.postValue(emptyStageList);
//                            });
//                        }
//                    });
//
//        });
//        return data;
//    }
//
//
//    // TODO: 2/14/2022 Log Book Module Begins
    public LiveData<List<SeasonTable>> getSeasonlistFromLocalDb() {
        final MutableLiveData<List<SeasonTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getSeasonListFromLocalDb() != null);
            if (dataExist) {
                data.postValue(appDAO.getSeasonListFromLocalDb());
            }
        });
        return data;
    }



//    public LiveData<List<LookUpDropDownDataTable>> getLookUpDropDownDataListFromLocalDB(String typeofReq) {
//        final MutableLiveData<List<LookUpDropDownDataTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getLookUpDropDownDataListFromLocalDB(typeofReq) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getLookUpDropDownDataListFromLocalDB(typeofReq));
//            }
//        });
//        return data;
//    }
//
//
//    // TODO: 2/15/2022 saving log book data into room db
//
//    public LiveData<AddLogBookDetailsTable> insertAddLogBookDetailsTable(AddLogBookDetailsTable addLogBookDetailsTable) {
//        final MutableLiveData<AddLogBookDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddLogBookDetailsTable(addLogBookDetailsTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddLogBookDetailsTableeData(addLogBookDetailsTable.getPlotNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddLogBookDetailsTable> upDateAddLogBookDetailsTable(String logBookNumber, String upDate) {
//        final MutableLiveData<AddLogBookDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.updateLogBookTable("",upDate, logBookNumber,false,"0");
//            data.postValue(appDAO.getTopAddLogBookDetailsTableeData(logBookNumber));
//        });
//        return data;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> insertAddFertilizerDetailsTable(AddFertilizerDetailsTable fertilizerDetailsTable) {
//        final MutableLiveData<AddFertilizerDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddFertilizerDetailsTable(fertilizerDetailsTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddFertilizerDetailsTableData(fertilizerDetailsTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//
//    public LiveData<RefreshTableDateCheck> insertRefreshTableDateCheck(RefreshTableDateCheck refreshTableDateCheck) {
//        final MutableLiveData<RefreshTableDateCheck> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertRefreshTableDateCheckTable(refreshTableDateCheck);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopRefreshTableDateCheckData(refreshTableDateCheck.getDeviceID()));
//        });
//        return data;
//    }
//
////    public LiveData<AddOrganicDetailsTable> insertAddOrganicDetailsTable(AddOrganicDetailsTable organicDetailsTable) {
////        final MutableLiveData<AddOrganicDetailsTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            appDAO.insertAddOrganicDetailsTable(organicDetailsTable);
////            // TODO: Sending result
////            data.postValue(appDAO.getTopAddOrganicDetailsTableData(organicDetailsTable.getLogBookNo()));
////        });
////        return data;
////    }
////
////    public LiveData<AddWaterSeasonFeildTable> insertAddWaterSeasonFeildTable(AddWaterSeasonFeildTable addWaterSeasonFeildTable) {
////        final MutableLiveData<AddWaterSeasonFeildTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            appDAO.insertAddWaterSeasonFeildTable(addWaterSeasonFeildTable);
////            // TODO: Sending result
////            data.postValue(appDAO.getTopAddWaterSeasonFeildTableData(addWaterSeasonFeildTable.getLogBookNo()));
////        });
////        return data;
////    }
//
//    public LiveData<AddLandPreparationTable> insertAddLandPreparationFeildTable(AddLandPreparationTable addLandPreparationTable) {
//        final MutableLiveData<AddLandPreparationTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddLandPermissionFeildTable(addLandPreparationTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddLandPreparationFeildTableData(addLandPreparationTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddPestDiseaseControlTable> insertAddPestDiseasesFeildTable(AddPestDiseaseControlTable addPestDiseaseControlTable) {
//        final MutableLiveData<AddPestDiseaseControlTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddPestDiseasesFeildTable(addPestDiseaseControlTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddPestDiseaseFeildTableData(addPestDiseaseControlTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddHarvestingTable> insertAddLaHarvestingFeildTable(AddHarvestingTable addHarvestingTable) {
//        final MutableLiveData<AddHarvestingTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddHarvestingFeildTable(addHarvestingTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddHarvestingFeildTableData(addHarvestingTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddSeedRateTable> insertAddSeedRateFeildTable(AddSeedRateTable addSeedRateTable) {
//        final MutableLiveData<AddSeedRateTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddSeedRateFeildTable(addSeedRateTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddSeedrateFeildTableData(addSeedRateTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//
//    public LiveData<AddNurseryPreparationTable> insertAddnurseryPreparationFeildTable(AddNurseryPreparationTable addNurseryPreparationTable) {
//        final MutableLiveData<AddNurseryPreparationTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddNurseryPermissionFeildTable(addNurseryPreparationTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddNurseryPreparationFeildTableData(addNurseryPreparationTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddMoistureContentTable> insertAddMoistureContentFeildTable(AddMoistureContentTable addMoistureContentTable) {
//        final MutableLiveData<AddMoistureContentTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddMoistureFeildTable(addMoistureContentTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddMoistureContentFeildTableData(addMoistureContentTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddYieldTable> insertAddYieldFeildTable(AddYieldTable addYieldTable) {
//        final MutableLiveData<AddYieldTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddYieldFeildTable(addYieldTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddYieldFeildTableData(addYieldTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddWeedManagementTable> insertAddWeedmanagementFeildTable(AddWeedManagementTable addWeedManagementTable) {
//        final MutableLiveData<AddWeedManagementTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddWeedManagementFeildTable(addWeedManagementTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddWeedmanagementFeildTableData(addWeedManagementTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddWaterManagementTable> insertAddWatermanagementFeildTable(AddWaterManagementTable addWaterManagementTable) {
//        final MutableLiveData<AddWaterManagementTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddWatermanagementFeildTable(addWaterManagementTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopWatermanagementFeildTableData(addWaterManagementTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddTransplantingTable> insertAddTransplantingFeildTable(AddTransplantingTable addTransplantingTable) {
//        final MutableLiveData<AddTransplantingTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddTransplantingFeildTable(addTransplantingTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddTransplantingFeildTableData(addTransplantingTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> insertAddFertilizationFeildTable(AddFertilizerDetailsTable addFertilizerDetailsTable) {
//        final MutableLiveData<AddFertilizerDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddFertilizationFeildTable(addFertilizerDetailsTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddFertilizationFeildTableData(addFertilizerDetailsTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<AddGeoBoundriesTable> insertAddGeoBoundriesFeildTable(AddGeoBoundriesTable addGeoBoundriesTable) {
//        final MutableLiveData<AddGeoBoundriesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddGeoBoundriesFeildTable(addGeoBoundriesTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddGeoBoundriesFeildTableData(addGeoBoundriesTable.getLogBookNo()));
//        });
//        return data;
//    }
//
////    public LiveData<AddWaterSeasonFeildTable> getWaterSeasonFeildDataFromLocalDB(String logBookNum) {
////        final MutableLiveData<AddWaterSeasonFeildTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            Log.d(TAG, "logBookNum" + logBookNum);
////            boolean dataExist = (appDAO.getWaterSeasonFeildTableDetailsFromLocalDB(logBookNum) != null);
////            if (dataExist) {
////                data.postValue(appDAO.getWaterSeasonFeildTableDetailsFromLocalDB(logBookNum));
////            }
////        });
////        return data;
////    }
//
//    public LiveData<AddLandPreparationTable> getAddLandPreparationFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddLandPreparationTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getLandPreparationFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddPestDiseaseControlTable> getAddPestDiseaseFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddPestDiseaseControlTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getPestDiseaseControlFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddHarvestingTable> getAddHarvestingFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddHarvestingTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getHarvestingFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddSeedRateTable> getAddSeedRateFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddSeedRateTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getSeedRateFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddNurseryPreparationTable> getAddNurseryPreparationFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddNurseryPreparationTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getNurseryPreparationFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddMoistureContentTable> getAddMoistureContentFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddMoistureContentTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getMoistureContentFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddYieldTable> getAddYieldFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddYieldTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getYieldFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddWeedManagementTable> getAddWeedManagementFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddWeedManagementTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getWeedmanagementFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//        });
//        return data;
//    }
//
//    public LiveData<AddWaterManagementTable> getAddWaterManagementFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddWaterManagementTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getWaterManagementFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//
//        });
//        return data;
//    }
//
//    public LiveData<AddTransplantingTable> getAddTransplantingFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddTransplantingTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getTransplantingFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//
//        });
//        return data;
//    }
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddFertilizerDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getFertilizerFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//
//        });
//        return data;
//    }
//
//    public LiveData<AddGeoBoundriesTable> getAddGeoBoundriesFeildDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddGeoBoundriesTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getGeoBoundriesFeildDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//
//        });
//        return data;
//    }
//
//    public LiveData<Integer> getLandPreparationnfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getLandPreparationnfieldsDataCount: " + logBookNum);
//        return appDAO.getLandPreparationFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getPestDiseaseControlfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getPestDiseaseControlfieldsDataCount: " + logBookNum);
//        return appDAO.getPestDiseaseFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getharvestingfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getharvestingfieldsDataCount: " + logBookNum);
//        return appDAO.getHarvestingFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getSeedRatefieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getSeedRatefieldsDataCount: " + logBookNum);
//        return appDAO.getSeedRateFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getNurseryPreparationfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getNurseryPreparationfieldsDataCount: " + logBookNum);
//        return appDAO.getNurseryPreparationFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getMoistureContentfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getMoistureContentfieldsDataCount: " + logBookNum);
//        return appDAO.getMoistureContentFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getYieldfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getYieldfieldsDataCount: " + logBookNum);
//        return appDAO.getYieldFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getWeedManagementfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getWeedManagementfieldsDataCount: " + logBookNum);
//        return appDAO.getWeedManagementFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getWatermanagementfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getWaterManagementFeildDataCount: " + logBookNum);
//        return appDAO.getWaterManagementFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getTransplantingfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getTransplantingfieldsDataCount: " + logBookNum);
//        return appDAO.getTransplantingFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getFertilizerfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getFertilizerfieldsDataCount: " + logBookNum);
//        return appDAO.getFertilizerFeildDataCount(logBookNum);
//    }
//
//    public LiveData<Integer> getGeoBoundriesfieldsDataCount(String logBookNum, String strDate) {
//        Log.d(TAG, "getGeoBoundriesfieldsDataCount: " + logBookNum);
//        return appDAO.getAddGeoBoundriesTableFeildDataCount(logBookNum);
//    }
//
//    public LiveData<List<AddLandPreparationTable>> getLandPreparationFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddLandPreparationTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getLandPreparationDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getLandPreparationDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//    public LiveData<List<AddPestDiseaseControlTable>> getPestDiseaseControlFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddPestDiseaseControlTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getPestDiseaseControlDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getPestDiseaseControlDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddHarvestingTable>> getHarvestingFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddHarvestingTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getHarvestingDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getHarvestingDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddSeedRateTable>> getSeedRateFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddSeedRateTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getSeedRateDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getSeedRateDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddNurseryPreparationTable>> getNurseryPreparationFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddNurseryPreparationTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getNurseryPreparationDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getNurseryPreparationDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddMoistureContentTable>> getMoistureContentFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddMoistureContentTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getMoistureContentDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getMoistureContentDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddYieldTable>> getYieldFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddYieldTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getYieldDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getYieldDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddWeedManagementTable>> getWeedmanagementFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddWeedManagementTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getWeedManagementDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getWeedManagementDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddWaterManagementTable>> getWatermanagementFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddWaterManagementTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getWatermanagementDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getWatermanagementDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddTransplantingTable>> getTransplantingFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddTransplantingTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getTransplantingDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getTransplantingDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddFertilizerDetailsTable>> getFertilizerFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddFertilizerDetailsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getFertilizerDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getFertilizerDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//    public LiveData<List<AddGeoBoundriesTable>> getGeoBoundriesFeildDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<List<AddGeoBoundriesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getGeoBoundriesDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getGeoBoundriesDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//
//    // TODO: 2/18/2022 harvest part
//    public LiveData<AddHarvestDetailsTable> insertAddHarvestDetailsTable(AddHarvestDetailsTable addHarvestDetailsTable) {
//        final MutableLiveData<AddHarvestDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddHarvestDetailsTable(addHarvestDetailsTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getTopAddHarvestDetailsTableData(addHarvestDetailsTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//
//    public LiveData<AddHarvestDetailsTable> getAddHarvestDetailsTableDataFromLocalDB(String logBookNum) {
//        final MutableLiveData<AddHarvestDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            boolean dataExist = (appDAO.getAddHarvestDetailsTableDetailsFromLocalDB(logBookNum) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getAddHarvestDetailsTableDetailsFromLocalDB(logBookNum));
//            }
//        });
//        return data;
//    }
//
//
//    // TODO: 2/18/2022 Add BoreWell part
////    public LiveData<AddBoreWellSeasonTable> insertAddBoreWellPumpSeasonTable(AddBoreWellSeasonTable addBoreWellSeasonTable) {
////        final MutableLiveData<AddBoreWellSeasonTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            appDAO.insertAddBoreWellPumpSeasonTable(addBoreWellSeasonTable);
////            // TODO: Sending result
////            data.postValue(appDAO.getAddBoreWellPumpSeasonTableData(addBoreWellSeasonTable.getLogBookNo()));
////        });
////        return data;
////    }
//
//
////    public LiveData<AddBoreWellSeasonTable> getAddBoreWellPumpSeasonTableDataFromLocalDB(String logBookNum) {
////        final MutableLiveData<AddBoreWellSeasonTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            Log.d(TAG, "logBookNum" + logBookNum);
////            boolean dataExist = (appDAO.getAddBoreWellPumpSeasonTableDetailsFromLocalDB(logBookNum) != null);
////            if (dataExist) {
////                data.postValue(appDAO.getAddBoreWellPumpSeasonTableDetailsFromLocalDB(logBookNum));
////            }
////        });
////        return data;
////    }
//
//
//    // TODO: 2/24/2022 look up header data code
//    public LiveData<LogBookDropDownHDRTable> getLogBookDropDownHDRTableDataFromLocalDB(String name) {
//        final MutableLiveData<LogBookDropDownHDRTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "name" + name);
//            boolean dataExist = (appDAO.getLookUpDropDownDataTableDetailsFromLocalDB(name) != null);
//            if (dataExist) {
//                data.postValue(appDAO.getLookUpDropDownDataTableDetailsFromLocalDB(name));
//            }
//        });
//        return data;
//    }
//
//
//    // TODO: 2/18/2022 water season
//
////    public LiveData<AddWaterReasonPreSeasonTable> insertAddWaterReasonPreSeasonTable(AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable) {
////        final MutableLiveData<AddWaterReasonPreSeasonTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            appDAO.insertAddWaterReasonPreSeasonTable(addWaterReasonPreSeasonTable);
////            // TODO: Sending result
////            data.postValue(appDAO.getTopAddWaterReasonPreSeasonTableData(addWaterReasonPreSeasonTable.getLogBookNo()));
////        });
////        return data;
////    }
////
////    public LiveData<AddWaterReasonPreSeasonTable> getAddWaterReasonPreSeasonDataFromLocalDB(String logBookNum) {
////        final MutableLiveData<AddWaterReasonPreSeasonTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            Log.d(TAG, "logBookNum" + logBookNum);
////            boolean dataExist = (appDAO.getWaterReasonPreSeasonTableDetailsFromLocalDB(logBookNum) != null);
////            if (dataExist) {
////                data.postValue(appDAO.getWaterReasonPreSeasonTableDetailsFromLocalDB(logBookNum));
////            }
////        });
////        return data;
////    }
//
//
//    // TODO: 2/18/2022 water regime data
////    public LiveData<AddWaterRegimeSeasonDetailsTable> insertAddWaterRegimeSeasonDetailsTable(AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable) {
////        final MutableLiveData<AddWaterRegimeSeasonDetailsTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            appDAO.insertAddWaterRegimeSeasonDetailsTable(addWaterRegimeSeasonDetailsTable);
////            // TODO: Sending result
////            data.postValue(appDAO.getTopAddWaterRegimeSeasonDetailsTableData(addWaterRegimeSeasonDetailsTable.getLogBookNo()));
////        });
////        return data;
////    }
//
////    public LiveData<AddWaterRegimeSeasonDetailsTable> getAddWaterRegimeSeasonDetailsDataFromLocalDB(String logBookNum) {
////        final MutableLiveData<AddWaterRegimeSeasonDetailsTable> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            Log.d(TAG, "logBookNum" + logBookNum);
////            boolean dataExist = (appDAO.getWaterRegimeSeasonDetailsTableDetailsFromLocalDB(logBookNum) != null);
////            if (dataExist) {
////                data.postValue(appDAO.getWaterRegimeSeasonDetailsTableDetailsFromLocalDB(logBookNum));
////            }
////        });
////        return data;
////    }
//
//
//    public void deleteLogBooktablesFromlocalDB(String logBookNum, String createdDate) {
//        executor.execute(() -> {
//            appDAO.deleteLogBookAllTables(logBookNum);
//        });
//    }
//
//
//    public LiveData<AddFertilizerDetailsTable> getAddFertilizerDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddFertilizerDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddFertilizerDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////            }
//
//        });
//        return data;
//    }
//
//
//    public LiveData<AddLogBookDetailsTable> getAddLogBookDetailsTableDate(String logBookNum, String currentDate) {
//        final MutableLiveData<AddLogBookDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "logBookNum" + logBookNum);
//            data.postValue(appDAO.getAddLogBookDetailsTableDetailsFromLocalDB(logBookNum, currentDate));
////            boolean dataExist = (appDAO.getAddLogBookDetailsTableDetailsFromLocalDB(logBookNum, currentDate) != null);
////            if (dataExist) {
////
////            }
//        });
//        return data;
//    }
//
//
//    public LiveData<RefreshTableDateCheck> getAddRefreshTableDateCheckTableDate(String deviceID, String currentDate) {
//        final MutableLiveData<RefreshTableDateCheck> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            Log.d(TAG, "deviceID" + deviceID + "date" + currentDate);
//            data.postValue(appDAO.getRefreshTableDateCheckDetailsFromLocalDB(deviceID, currentDate));
//
//        });
//        return data;
//    }
//
//    // TODO: 2/21/2022 log book sync functionality
//
//    public LiveData<List<AddLogBookDetailsTable>> getAddLogBookDetailslistFromLocalDBNotSync() {
//        final MutableLiveData<List<AddLogBookDetailsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getAddLogBookDetailsListFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getAddLogBookDetailsListFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
//

    public LiveData<List<AddPlotOfferTable>> getFertilizerDetailsDetailslistFromLocalDBNotSync() {
        final MutableLiveData<List<AddPlotOfferTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddFertilizerDetailsListFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getAddFertilizerDetailsListFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<List<AddPlotOfferTable>> getPlotOfferListFromLocalDB(String farmerCode) {
        final MutableLiveData<List<AddPlotOfferTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddFertilizerDetailsListFromLocalDB(farmerCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getAddFertilizerDetailsListFromLocalDB(farmerCode));
            }
        });
        return data;
    }

    public LiveData<List<AddD20Table>> getAddD20listFromLocalDBNotSync() {
        final MutableLiveData<List<AddD20Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddD20ListFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getAddD20ListFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<List<AddD30Table>> getAddD30listFromLocalDBNotSync() {
        final MutableLiveData<List<AddD30Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddD30ListFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getAddD30ListFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<List<AddD30Table>> getAddD30listFromLocalDBNotSync(String status,String farmerCode,String plotCode) {
        final MutableLiveData<List<AddD30Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddD30ListFromLocalDBNotSync(status,farmerCode,plotCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getAddD30ListFromLocalDBNotSync(status,farmerCode,plotCode));
            }
        });
        return data;
    }

    public LiveData<List<AddD10Table>> getAddD10listFromLocalDBNotSync() {
        final MutableLiveData<List<AddD10Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddD10ListFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getAddD10ListFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<List<AddD10Table>> getAddD10listFromLocalDB() {
        final MutableLiveData<List<AddD10Table>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddD10ListFromLocalDBNotSync() != null);
            if (dataExist) {
                data.postValue(appDAO.getAddD10ListFromLocalDBNotSync());
            }
        });
        return data;
    }

    public LiveData<List<AddPlantationTable>> getPlantationDetailslistFromLocalDBNotSync() {
        final MutableLiveData<List<AddPlantationTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddPlantationListFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getAddPlantationListFromLocalDBNotSync("0"));
            }
        });
        return data;
    }


    public LiveData<List<AddGeoBoundariesTrackingTable>> getTrackingDetailslistFromLocalDBNotSync() {
        final MutableLiveData<List<AddGeoBoundariesTrackingTable>> data = new MutableLiveData<>();
        if (executor != null) {
            executor.execute(() -> {
                boolean dataExist = (appDAO.getTrackingDetailsListFromLocalDBNotSync(false) != null);
                Log.e("==========>dataExist",dataExist+"");
                if (dataExist) {
                    data.postValue(appDAO.getTrackingDetailsListFromLocalDBNotSync(false));
                    Log.e("==========>dataExist","getTrackingDetailsListFromLocalDBNotSync");
                }
            });
        }
        else{
            Log.e("error===>","error 6965");
        }
        return data;
    }

    public LiveData<AddD10Table> getD10Data(String plotNum,String seasonCode) {
        final MutableLiveData<AddD10Table> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD10Data(plotNum,seasonCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getD10Data(plotNum,seasonCode));
            }
        });
        return data;
    }

    public LiveData<AddD20Table> getD20Data(String plotNum,String seasonCode) {
        final MutableLiveData<AddD20Table> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD20Data(plotNum,seasonCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getD20Data(plotNum,seasonCode));
            }
        });
        return data;
    }

    public LiveData<AddD30Table> getD30Data(String plotNum,String seasonCode) {
        final MutableLiveData<AddD30Table> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getD30Data(plotNum,seasonCode) != null);
            if (dataExist) {
                data.postValue(appDAO.getD30Data(plotNum,seasonCode));
            }
        });
        return data;
    }

//    // TODO: 2/21/2022 sync fertlizer details
//

    public LiveData<String> syncAddFertilizerDetailsToServer(AddPlotOfferTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddPlotOfferTable spNameFertilizerPersonalDetailsClass = new AddPlotOfferTable();

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getFertilizer()))) {
//                spNameFertilizerPersonalDetailsClass.setFertilizer(addFertilizerDetailsTable.getFertilizer());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setFertilizer(null);
//            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getSeasonCode())) {
                spNameFertilizerPersonalDetailsClass.setSeasonCode(addFertilizerDetailsTable.getSeasonCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeasonCode("");
            }
            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getStatus())) {
                spNameFertilizerPersonalDetailsClass.setStatus(addFertilizerDetailsTable.getStatus());
            } else {
                spNameFertilizerPersonalDetailsClass.setStatus("");
            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getFertilizerType()))) {
//                spNameFertilizerPersonalDetailsClass.setFertilizerType(addFertilizerDetailsTable.getFertilizerType());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setFertilizerType(null);
//            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getUOM()))) {
//                spNameFertilizerPersonalDetailsClass.setUOM(addFertilizerDetailsTable.getUOM());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUOM(null);
//            }

//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCropProtection())) {
//                spNameFertilizerPersonalDetailsClass.setCropProtection(addFertilizerDetailsTable.getCropProtection());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCropProtection(null);
//            }

//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getQty())) {
//                spNameFertilizerPersonalDetailsClass.setQty(addFertilizerDetailsTable.getQty());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setQty("");
//            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getNewFarmer())) {
                spNameFertilizerPersonalDetailsClass.setNewFarmer("0");
//                spNameFertilizerPersonalDetailsClass.setNewFarmer(addFertilizerDetailsTable.getNewFarmer());
            } else {
                spNameFertilizerPersonalDetailsClass.setNewFarmer("1");
            }

            //offer not required

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getOfferDate())) {
                spNameFertilizerPersonalDetailsClass.setOfferDate(addFertilizerDetailsTable.getOfferDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setOfferDate("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getFarmerCode())) {
                spNameFertilizerPersonalDetailsClass.setFarmerCode(addFertilizerDetailsTable.getFarmerCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setFarmerCode("");
            }


            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getFarmerName())) {
                spNameFertilizerPersonalDetailsClass.setFarmerName(addFertilizerDetailsTable.getFarmerName());
            } else {
                spNameFertilizerPersonalDetailsClass.setFarmerName("");
            }


            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getFatherName())) {
                spNameFertilizerPersonalDetailsClass.setFatherName(addFertilizerDetailsTable.getFatherName());
            } else {
                spNameFertilizerPersonalDetailsClass.setFatherName("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getOfferDate())) {
                spNameFertilizerPersonalDetailsClass.setOfferDate(addFertilizerDetailsTable.getOfferDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setOfferDate("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getFarmerVillageId())) {
                spNameFertilizerPersonalDetailsClass.setFarmerVillageId(addFertilizerDetailsTable.getFarmerVillageId());
            } else {
                spNameFertilizerPersonalDetailsClass.setFarmerVillageId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotVillageId())) {
                spNameFertilizerPersonalDetailsClass.setPlotVillageId(addFertilizerDetailsTable.getPlotVillageId());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlotVillageId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotIndNo())) {
                spNameFertilizerPersonalDetailsClass.setPlotIndNo(addFertilizerDetailsTable.getPlotIndNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlotIndNo("");
            }


            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlantTypeId())) {
                spNameFertilizerPersonalDetailsClass.setPlantTypeId(addFertilizerDetailsTable.getPlantTypeId());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlantTypeId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getExpectedVarityId())) {
                spNameFertilizerPersonalDetailsClass.setExpectedVarityId(addFertilizerDetailsTable.getExpectedVarityId());
            } else {
                spNameFertilizerPersonalDetailsClass.setExpectedVarityId("");
            }


            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getExpectedArea())) {
                spNameFertilizerPersonalDetailsClass.setExpectedArea(addFertilizerDetailsTable.getExpectedArea());
            } else {
                spNameFertilizerPersonalDetailsClass.setExpectedArea("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getExpectedPlantingDate())) {
                spNameFertilizerPersonalDetailsClass.setExpectedPlantingDate(addFertilizerDetailsTable.getExpectedPlantingDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setExpectedPlantingDate("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getReportedArea())) {
                spNameFertilizerPersonalDetailsClass.setReportedArea(addFertilizerDetailsTable.getReportedArea());
            } else {
                spNameFertilizerPersonalDetailsClass.setReportedArea("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getReasonForNotPlantingId())) {
                spNameFertilizerPersonalDetailsClass.setReasonForNotPlantingId(addFertilizerDetailsTable.getReasonForNotPlantingId());
            } else {
                spNameFertilizerPersonalDetailsClass.setReasonForNotPlantingId("");
            }

            if ((addFertilizerDetailsTable.getActive())) {
                spNameFertilizerPersonalDetailsClass.setActive(addFertilizerDetailsTable.getActive());
            } else {
                spNameFertilizerPersonalDetailsClass.setActive(false);
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }


            ArrayList<AddPlotOfferTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddPlotOfferTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setPlotOffer(spNameFertilizerPersonalDetailsClasses);

            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
                    enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);

//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                                        AddPlotOfferTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotData(String.valueOf(addFertilizerDetailsTable.getPlotOfferId()));
                                        if (addFertilizerDetailsFromLocalDB != null) {
                                            addFertilizerDetailsFromLocalDB.setPlotOfferId(addFertilizerDetailsFromLocalDB.getPlotOfferId());
//                                            addFertilizerDetailsTable.setSync(true);
                                            addFertilizerDetailsTable.setServerStatus("1");  //should set
                                            appDAO.insertAddPlotOfferTable(addFertilizerDetailsTable);
                                            // TODO: Sending result
                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else  {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncTrackingDetailsToServer(AddGeoBoundariesTrackingTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddGeoBoundariesTrackingTable spNameFertilizerPersonalDetailsClass = new AddGeoBoundariesTrackingTable();

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getFertilizer()))) {
//                spNameFertilizerPersonalDetailsClass.setFertilizer(addFertilizerDetailsTable.getFertilizer());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setFertilizer(null);
//            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getUserId()))) {
                spNameFertilizerPersonalDetailsClass.setUserId(addFertilizerDetailsTable.getUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUserId(0);
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getSeqNo()))) {
                spNameFertilizerPersonalDetailsClass.setSeqNo(addFertilizerDetailsTable.getSeqNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeqNo(0);
            }

                spNameFertilizerPersonalDetailsClass.setId(null);

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
            } else {
                spNameFertilizerPersonalDetailsClass.setLatitude(null);
            }
            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
            } else {
                spNameFertilizerPersonalDetailsClass.setLongitude(null);
            }
            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
            } else {
                spNameFertilizerPersonalDetailsClass.setLongitude(null);
            }

            spNameFertilizerPersonalDetailsClass.setServerStatus(true);
            spNameFertilizerPersonalDetailsClass.setIsActive(true);

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getUOM()))) {
//                spNameFertilizerPersonalDetailsClass.setUOM(addFertilizerDetailsTable.getUOM());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUOM(null);
//            }

//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCropProtection())) {
//                spNameFertilizerPersonalDetailsClass.setCropProtection(addFertilizerDetailsTable.getCropProtection());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCropProtection(null);
//            }

//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getQty())) {
//                spNameFertilizerPersonalDetailsClass.setQty(addFertilizerDetailsTable.getQty());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setQty("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getIsActive())) {
//
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsActive(false);
//            }


            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }


            ArrayList<AddGeoBoundariesTrackingTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddGeoBoundariesTrackingTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setUserTracking(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
                    enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);

                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                                        AddGeoBoundariesTrackingTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusTrackingData(String.valueOf(addFertilizerDetailsTable.getGeoBoundariesId()));
                                        if (addFertilizerDetailsFromLocalDB != null) {
                                            addFertilizerDetailsFromLocalDB.setGeoBoundariesId(addFertilizerDetailsFromLocalDB.getGeoBoundariesId());
//                                            addFertilizerDetailsTable.setSync(true);
                                            addFertilizerDetailsTable.setServerStatus(true);  //should set
                                            appDAO.insertTrackingTable(addFertilizerDetailsTable);
                                            // TODO: Sending result
                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else  {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncGeoBoundariesDetailsToServer(AddGeoBoundriesTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddGeoBoundriesTable spNameFertilizerPersonalDetailsClass = new AddGeoBoundriesTable();

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getFertilizer()))) {
//                spNameFertilizerPersonalDetailsClass.setFertilizer(addFertilizerDetailsTable.getFertilizer());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setFertilizer(null);
//            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getSeasonCode()))) {
                spNameFertilizerPersonalDetailsClass.setSeasonCode(addFertilizerDetailsTable.getSeasonCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeasonCode("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
            } else {
                spNameFertilizerPersonalDetailsClass.setStage("");
            }

                spNameFertilizerPersonalDetailsClass.setId(null);

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
            } else {
                spNameFertilizerPersonalDetailsClass.setLatitude(null);
            }
            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
            } else {
                spNameFertilizerPersonalDetailsClass.setLongitude(null);
            }

            spNameFertilizerPersonalDetailsClass.setIsActive(true);

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getUOM()))) {
//                spNameFertilizerPersonalDetailsClass.setUOM(addFertilizerDetailsTable.getUOM());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUOM(null);
//            }

//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCropProtection())) {
//                spNameFertilizerPersonalDetailsClass.setCropProtection(addFertilizerDetailsTable.getCropProtection());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCropProtection(null);
//            }

//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getQty())) {
//                spNameFertilizerPersonalDetailsClass.setQty(addFertilizerDetailsTable.getQty());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setQty("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getIsActive())) {
//
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsActive(false);
//            }
            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlotNo("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }


            ArrayList<AddGeoBoundriesTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddGeoBoundriesTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setGeoBoundaries(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
                    enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData GeoBoundaries" + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);

//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                                        AddGeoBoundriesTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotGeoBoundariesData(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                                        if (addFertilizerDetailsFromLocalDB != null) {
                                            addFertilizerDetailsFromLocalDB.setGeoBoundriesId(addFertilizerDetailsFromLocalDB.getGeoBoundriesId());
//                                            addFertilizerDetailsTable.setSync(true);
                                            addFertilizerDetailsTable.setSync(true);  //should set
                                            appDAO.insertGeoBoundariesTable(addFertilizerDetailsTable);
                                            // TODO: Sending result
                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else  {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncD20DetailsToServer(AddD20Table addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddD20Table spNameFertilizerPersonalDetailsClass = new AddD20Table();

//                spNameFertilizerPersonalDetailsClass.setId(null);

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getId()))) {
                spNameFertilizerPersonalDetailsClass.setId(addFertilizerDetailsTable.getId());
            } else {
                spNameFertilizerPersonalDetailsClass.setId(null);
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlotNo("");
            }


            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
            } else {
                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
            } else {
                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
            } else {
                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
            } else {
                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
            } else {
                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
            } else {
                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getAgreedTon()))) {
                spNameFertilizerPersonalDetailsClass.setAgreedTon(addFertilizerDetailsTable.getAgreedTon());
            } else {
                spNameFertilizerPersonalDetailsClass.setAgreedTon("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
            } else {
                spNameFertilizerPersonalDetailsClass.setInterCropId("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


            spNameFertilizerPersonalDetailsClass.setActive(true);

            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
            Log.d("TAG", "onClick: date" + dateTime);
            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);


            ArrayList<AddD20Table> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddD20Table>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc20(spNameFertilizerPersonalDetailsClasses);
            Log.d("TAG", "onClick: doc30" + spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
                    enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
//                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                                        AddD20Table addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD20Data(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                                        if (addFertilizerDetailsFromLocalDB != null) {
                                            addFertilizerDetailsFromLocalDB.setPlotGenId(addFertilizerDetailsFromLocalDB.getPlotGenId());
//                                            addFertilizerDetailsTable.setSync(true);
                                            addFertilizerDetailsTable.setSync(true);  //should set
                                            addFertilizerDetailsTable.setServerStatus("1");  //should set
                                            appDAO.insertD20Table(addFertilizerDetailsTable);
                                            // TODO: Sending result
                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else  {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncD10DetailsToServer(AddD10Table addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddD10Table spNameFertilizerPersonalDetailsClass = new AddD10Table();
            spNameFertilizerPersonalDetailsClass = addFertilizerDetailsTable;
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
//                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlotNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
//                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInterCropId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
//            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


//            spNameFertilizerPersonalDetailsClass.setActive(true);
//
//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d("TAG", "onClick: date" + dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);
//

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getId()))) {
                spNameFertilizerPersonalDetailsClass.setId(addFertilizerDetailsTable.getId());
            } else {
                spNameFertilizerPersonalDetailsClass.setId(null);
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getSeasonCode()))) {
                spNameFertilizerPersonalDetailsClass.setSeasonCode(addFertilizerDetailsTable.getSeasonCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeasonCode("2022-23");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getSeedMaterialUsedId()))) {
                spNameFertilizerPersonalDetailsClass.setSeedMaterialUsedId(addFertilizerDetailsTable.getSeedMaterialUsedId());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeedMaterialUsedId("0");
            }

            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getVarietyId())){
                spNameFertilizerPersonalDetailsClass.setVarietyId(addFertilizerDetailsTable.getVarietyId());
            } else {
                spNameFertilizerPersonalDetailsClass.setVarietyId("0");
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())){
                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())){
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }
            if((addFertilizerDetailsTable.getActive())!=null){
                spNameFertilizerPersonalDetailsClass.setActive(true);
            } else {
                spNameFertilizerPersonalDetailsClass.setActive(true);
            }

            ArrayList<AddD10Table> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddD10Table>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc10(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
                    enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                                        AddD10Table addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD10Data(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                                        if (addFertilizerDetailsFromLocalDB != null) {
                                            addFertilizerDetailsFromLocalDB.setD10Id(addFertilizerDetailsFromLocalDB.getD10Id());
//                                            addFertilizerDetailsTable.setSync(true);
                                            addFertilizerDetailsTable.setSync(true);  //should set
                                            addFertilizerDetailsTable.setServerStatus("1");  //should set
                                            appDAO.insertD10Table(addFertilizerDetailsTable);
                                            // TODO: Sending result
                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else  {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncD30DetailsToServer(AddD30Table addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddD30Table spNameFertilizerPersonalDetailsClass = new AddD30Table();

            spNameFertilizerPersonalDetailsClass = addFertilizerDetailsTable;


            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getId()))) {
                spNameFertilizerPersonalDetailsClass.setId(addFertilizerDetailsTable.getId());
            } else {
                spNameFertilizerPersonalDetailsClass.setId(null);
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlotNo("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getNominee())) {
                spNameFertilizerPersonalDetailsClass.setNominee(addFertilizerDetailsTable.getNominee());
            } else {
                spNameFertilizerPersonalDetailsClass.setNominee("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getGuarantor1())) {
                spNameFertilizerPersonalDetailsClass.setGuarantor1(addFertilizerDetailsTable.getGuarantor1());
            } else {
                spNameFertilizerPersonalDetailsClass.setGuarantor1("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getGuarantor2())) {
                spNameFertilizerPersonalDetailsClass.setGuarantor2(addFertilizerDetailsTable.getGuarantor2());
            } else {
                spNameFertilizerPersonalDetailsClass.setGuarantor2("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getGuarantor3())) {
                spNameFertilizerPersonalDetailsClass.setGuarantor3(addFertilizerDetailsTable.getGuarantor3());
            } else {
                spNameFertilizerPersonalDetailsClass.setGuarantor3("");
            }

            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getRelationShipTypeId())) {
                spNameFertilizerPersonalDetailsClass.setRelationShipTypeId(addFertilizerDetailsTable.getRelationShipTypeId());
            } else {
                spNameFertilizerPersonalDetailsClass.setRelationShipTypeId("");
            }


            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
            } else {
                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
            } else {
                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
            } else {
                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
            } else {
                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
            } else {
                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
            } else {
                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
            } else {
                spNameFertilizerPersonalDetailsClass.setInterCropId("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getAgreedTon()))) {
                spNameFertilizerPersonalDetailsClass.setAgreedTon(addFertilizerDetailsTable.getAgreedTon());
            } else {
                spNameFertilizerPersonalDetailsClass.setAgreedTon("");
            }
            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getEstimatedTon()))) {
                spNameFertilizerPersonalDetailsClass.setEstimatedTon(addFertilizerDetailsTable.getEstimatedTon());
            } else {
                spNameFertilizerPersonalDetailsClass.setEstimatedTon("");
            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


            spNameFertilizerPersonalDetailsClass.setActive(true);

            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
            Log.d("TAG", "onClick: date" + dateTime);
            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);

            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            ArrayList<AddD30Table> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddD30Table>();
            spNameFertilizerPersonalDetailsClasses.add(addFertilizerDetailsTable);
//            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc30(spNameFertilizerPersonalDetailsClasses);
//            syncPersonalLandAllDetailsRequestDTO.setDoc30(spNameFertilizerPersonalDetailsClasses);
            Log.d("TAG", "onClick: doc30" + spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
                    enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                                        AddD30Table addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD30Data(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                                        if (addFertilizerDetailsFromLocalDB != null) {
                                            addFertilizerDetailsFromLocalDB.setD30Id(addFertilizerDetailsFromLocalDB.getD30Id());
//                                            addFertilizerDetailsTable.setSync(true);
                                            addFertilizerDetailsTable.setSync(true);  //should set
                                            addFertilizerDetailsTable.setServerStatus("1");  //should set
                                            appDAO.insertD30Table(addFertilizerDetailsTable);
                                            // TODO: Sending result
                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else  {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncD20DiseaseToServer(D20DiseaseTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            D20DiseaseTable spNameFertilizerPersonalDetailsClass = new D20DiseaseTable();
            spNameFertilizerPersonalDetailsClass= addFertilizerDetailsTable;
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
//                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlotNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
//                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInterCropId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
//            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


//            spNameFertilizerPersonalDetailsClass.setActive(true);
//
//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d("TAG", "onClick: date" + dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);
//

//            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getVarietyId())){
//                spNameFertilizerPersonalDetailsClass.setVarietyId(addFertilizerDetailsTable.getVarietyId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setVarietyId("0");
//            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            spNameFertilizerPersonalDetailsClass = addFertilizerDetailsTable;

            ArrayList<D20DiseaseTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<D20DiseaseTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc20Disease(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        executor.execute(() -> {
            try {
                String strResponse = response.body();
//                                    String strResponse = response.body().string();
                Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                if (strResponse.equals("Transanctions Synced Successfully")) {
                if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                    D20DiseaseTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD20DiseaseData(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                    if (addFertilizerDetailsFromLocalDB != null) {
                        addFertilizerDetailsFromLocalDB.setDiseasesIdId(addFertilizerDetailsFromLocalDB.getDiseasesIdId());
//                                            addFertilizerDetailsTable.setSync(true);
                        addFertilizerDetailsTable.setSync(true);  //should set
                        addFertilizerDetailsTable.setServerStatus("1");  //should set
                        appDAO.insertD20Disease(addFertilizerDetailsTable);
                        // TODO: Sending result
                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
                    }

                } else  {
                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                data.postValue(FAILURE_RESPONSE_MESSAGE);
            }


        });
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
        // TODO: Sending result
        executor.execute(() -> {
            data.postValue(FAILURE_RESPONSE_MESSAGE);
        });
    }
});
        });
        return data;
    }

    public LiveData<String> syncD20WeedToServer(D20WeedTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            D20WeedTable spNameFertilizerPersonalDetailsClass = new D20WeedTable();
            spNameFertilizerPersonalDetailsClass= addFertilizerDetailsTable;
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
//                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlotNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
//                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInterCropId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
//            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


//            spNameFertilizerPersonalDetailsClass.setActive(true);
//
//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d("TAG", "onClick: date" + dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);
//

//            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getVarietyId())){
//                spNameFertilizerPersonalDetailsClass.setVarietyId(addFertilizerDetailsTable.getVarietyId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setVarietyId("0");
//            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            spNameFertilizerPersonalDetailsClass = addFertilizerDetailsTable;

            ArrayList<D20WeedTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<D20WeedTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc20Weed(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        executor.execute(() -> {
            try {
                String strResponse = response.body();
//                                    String strResponse = response.body().string();
                Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                if (strResponse.equals("Transanctions Synced Successfully")) {
                if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                    D20WeedTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD20WeedData(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                    if (addFertilizerDetailsFromLocalDB != null) {
                        addFertilizerDetailsFromLocalDB.setWeedIdD20Id(addFertilizerDetailsFromLocalDB.getWeedIdD20Id());
//                                            addFertilizerDetailsTable.setSync(true);
                        addFertilizerDetailsTable.setSync("1");  //should set
                        addFertilizerDetailsTable.setServerStatus("1");  //should set
                        appDAO.insertD20Weed(addFertilizerDetailsTable);
                        // TODO: Sending result
                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
                    }

                } else  {
                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                data.postValue(FAILURE_RESPONSE_MESSAGE);
            }


        });
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
        // TODO: Sending result
        executor.execute(() -> {
            data.postValue(FAILURE_RESPONSE_MESSAGE);
        });
    }
});
        });
        return data;
    }

    public LiveData<String> syncD20FertilizerToServer(D20FertilizerTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            D20FertilizerTable spNameFertilizerPersonalDetailsClass = new D20FertilizerTable();
            spNameFertilizerPersonalDetailsClass= addFertilizerDetailsTable;
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
//                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlotNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
//                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInterCropId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
//            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


//            spNameFertilizerPersonalDetailsClass.setActive(true);
//
//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d("TAG", "onClick: date" + dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);
//

//            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getVarietyId())){
//                spNameFertilizerPersonalDetailsClass.setVarietyId(addFertilizerDetailsTable.getVarietyId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setVarietyId("0");
//            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            spNameFertilizerPersonalDetailsClass = addFertilizerDetailsTable;

            ArrayList<D20FertilizerTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<D20FertilizerTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc20Fertilizer(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        executor.execute(() -> {
            try {
                String strResponse = response.body();
//                                    String strResponse = response.body().string();
                Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                if (strResponse.equals("Transanctions Synced Successfully")) {
                if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                    D20FertilizerTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD20FertilizerData(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                    if (addFertilizerDetailsFromLocalDB != null) {
                        addFertilizerDetailsFromLocalDB.setFertilizerIdP(addFertilizerDetailsFromLocalDB.getFertilizerIdP());
//                                            addFertilizerDetailsTable.setSync(true);
                        addFertilizerDetailsTable.setSync(true);  //should set
                        addFertilizerDetailsTable.setServerStatus("1");  //should set
                        appDAO.insertD20Fertilizer(addFertilizerDetailsTable);
                        // TODO: Sending result
                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
                    }

                } else  {
                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                data.postValue(FAILURE_RESPONSE_MESSAGE);
            }


        });
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
        // TODO: Sending result
        executor.execute(() -> {
            data.postValue(FAILURE_RESPONSE_MESSAGE);
        });
    }
});
        });
        return data;
    }

    public LiveData<String> syncD20PestToServer(D20PestTable addFertilizerDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {
            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            D20PestTable spNameFertilizerPersonalDetailsClass = new D20PestTable();
            spNameFertilizerPersonalDetailsClass= addFertilizerDetailsTable;
//            spNameFertilizerPersonalDetailsClass = addFertilizerDetailsTable;
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getPlotNo())) {
//                spNameFertilizerPersonalDetailsClass.setPlotNo(addFertilizerDetailsTable.getPlotNo());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlotNo("");
//            }
//
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getDemoPlotArea()))) {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea(addFertilizerDetailsTable.getDemoPlotArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setDemoPlotArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getMeasureArea()))) {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea(addFertilizerDetailsTable.getMeasureArea());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setMeasureArea("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsMicronutrientDeficiency()))) {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency(addFertilizerDetailsTable.getIsMicronutrientDeficiency());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsMicronutrientDeficiency("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsTrashMulching()))) {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching(addFertilizerDetailsTable.getIsTrashMulching());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsTrashMulching("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getIsGapsFilled()))) {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled(addFertilizerDetailsTable.getIsGapsFilled());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setIsGapsFilled("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getWeedStatusId()))) {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId(addFertilizerDetailsTable.getWeedStatusId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setWeedStatusId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInterCropId()))) {
//                spNameFertilizerPersonalDetailsClass.setInterCropId(addFertilizerDetailsTable.getInterCropId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInterCropId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getPlantingMethodId()))) {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId(addFertilizerDetailsTable.getPlantingMethodId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setPlantingMethodId("");
//            }
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getInspectionDate()))) {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate(addFertilizerDetailsTable.getInspectionDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setInspectionDate("");
//            }

//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getStage()))) {
//                spNameFertilizerPersonalDetailsClass.setStage(addFertilizerDetailsTable.getStage());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setStage("");
//            }
//
//                spNameFertilizerPersonalDetailsClass.setId(null);
//
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLatitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLatitude(addFertilizerDetailsTable.getLatitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLatitude(null);
//            }
//            if (!TextUtils.isEmpty(String.valueOf(addFertilizerDetailsTable.getLongitude()))) {
//                spNameFertilizerPersonalDetailsClass.setLongitude(addFertilizerDetailsTable.getLongitude());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setLongitude(null);
//            }


//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addFertilizerDetailsTable.getUpdatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedDate())) {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate(addFertilizerDetailsTable.getCreatedDate());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
//            }


//            spNameFertilizerPersonalDetailsClass.setActive(true);
//
//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d("TAG", "onClick: date" + dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedDate(dateTime);
//            spNameFertilizerPersonalDetailsClass.setCreatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("1");
//            spNameFertilizerPersonalDetailsClass.setUpdatedDate(dateTime);
//

//            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getVarietyId())){
//                spNameFertilizerPersonalDetailsClass.setVarietyId(addFertilizerDetailsTable.getVarietyId());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setVarietyId("0");
//            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getCreatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addFertilizerDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }
            if(!TextUtils.isEmpty(addFertilizerDetailsTable.getUpdatedByUserId())){
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addFertilizerDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
            }


            ArrayList<D20PestTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<D20PestTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setDoc20Pest(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        executor.execute(() -> {
            try {
                String strResponse = response.body();
//                                    String strResponse = response.body().string();
                Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
//                if (strResponse.equals("Transanctions Synced Successfully")) {
                if (strResponse.equals("Transaction Sync Completed Successfully.")) {
                    D20PestTable addFertilizerDetailsFromLocalDB = appDAO.getCheckServerStatusPlotD20PestData(String.valueOf(addFertilizerDetailsTable.getPlotNo()));
                    if (addFertilizerDetailsFromLocalDB != null) {
                        addFertilizerDetailsFromLocalDB.setD20PestTableId(addFertilizerDetailsFromLocalDB.getD20PestTableId());
//                                            addFertilizerDetailsTable.setSync(true);
                        addFertilizerDetailsTable.setSync(true);  //should set
                        addFertilizerDetailsTable.setServerStatus("1");  //should set
                        appDAO.insertD20Pest(addFertilizerDetailsTable);
                        // TODO: Sending result
                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
                    }

                } else  {
                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                data.postValue(FAILURE_RESPONSE_MESSAGE);
            }


        });
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
        // TODO: Sending result
        executor.execute(() -> {
            data.postValue(FAILURE_RESPONSE_MESSAGE);
        });
    }
});
        });
        return data;
    }

    public LiveData<Integer> getNotSyncComplainsCountDataFromLocalDB() {
        return appDAO.getComplainsNotSyncCountFromLocalDB("0");
    }

    public LiveData<Integer> getNotSyncGMCountDataFromLocalDB() {
        return appDAO.getGMNotSyncCountFromLocalDB("0");
    }

    public LiveData<SavingComplainImagesTable> insertSavingOfComplainMultipleImages(SavingComplainImagesTable savingComplainImagesTableList, String logbookno) {
        final MutableLiveData<SavingComplainImagesTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            appDAO.insertSavingComplainImages(savingComplainImagesTableList);


//            for (int i = 0; i < savingComplainImagesTableList.size(); i++) {
//                data.postValue(appDAO.getComplainImageFromLocalDb(String.valueOf(savingComplainImagesTableList.size())));
            data.postValue(appDAO.getComplainImageFromLocalDb(logbookno));
//            }
        });
        return data;
    }

    //send complain Images to server DB
    public LiveData<String> syncComplainImagesDetaisToServer(SavingComplainImagesTable savingComplainImagesTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {

            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            SavingComplainImagesTable spNameFertilizerPersonalDetailsClass = new SavingComplainImagesTable();
            spNameFertilizerPersonalDetailsClass= savingComplainImagesTable;

            if (!TextUtils.isEmpty(savingComplainImagesTable.getComplaintCode())) {
                spNameFertilizerPersonalDetailsClass.setComplaintCode(savingComplainImagesTable.getComplaintCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setComplaintCode("");
            }

            spNameFertilizerPersonalDetailsClass.setModuleTypeId("");

            if (!TextUtils.isEmpty(savingComplainImagesTable.getFileName())) {
                spNameFertilizerPersonalDetailsClass.setFileName(savingComplainImagesTable.getFileName());
            } else {
                spNameFertilizerPersonalDetailsClass.setFileName("");
            }
//            spNameDoc.setDocOldNum("");
            if (!TextUtils.isEmpty(savingComplainImagesTable.getFileLocation())) {
                spNameFertilizerPersonalDetailsClass.setFileLocation(savingComplainImagesTable.getFileLocation());
            } else {
                spNameFertilizerPersonalDetailsClass.setFileLocation("");
            }

            if (!TextUtils.isEmpty(savingComplainImagesTable.getFileExtension())) {
                spNameFertilizerPersonalDetailsClass.setFileExtension(savingComplainImagesTable.getFileExtension());
            } else {
                spNameFertilizerPersonalDetailsClass.setFileExtension("");
            }

//            if (!TextUtils.isEmpty(savingComplainImagesTable.isSync())) {
            spNameFertilizerPersonalDetailsClass.setSync(savingComplainImagesTable.isSync());
//            } else {
//                spNameDoc.setIsActive("");
//            }

            spNameFertilizerPersonalDetailsClass.setIsVideoRecording(savingComplainImagesTable.getIsVideoRecording());
            spNameFertilizerPersonalDetailsClass.setIsActive(savingComplainImagesTable.getIsActive());
            spNameFertilizerPersonalDetailsClass.setIsResult(savingComplainImagesTable.getIsResult());


            if (!TextUtils.isEmpty(savingComplainImagesTable.getCreatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(savingComplainImagesTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
            }

            if (!TextUtils.isEmpty(savingComplainImagesTable.getUpdatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(savingComplainImagesTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
            }

            if (!TextUtils.isEmpty(savingComplainImagesTable.getUpdatedDate())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(savingComplainImagesTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }

            if (!TextUtils.isEmpty(savingComplainImagesTable.getCreatedDate())) {
                spNameFertilizerPersonalDetailsClass.setCreatedDate(savingComplainImagesTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }
            if (!TextUtils.isEmpty(savingComplainImagesTable.getLogBookNo())) {
                spNameFertilizerPersonalDetailsClass.setLogBookNo(savingComplainImagesTable.getLogBookNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setLogBookNo("");
            }
//            if (!TextUtils.isEmpty(savingComplainImagesTable.getLocalDocUrl())) {
//                spNameDoc.setLocalDocUrl(savingComplainImagesTable.getLocalDocUrl());
//            } else {
//                spNameDoc.setLocalDocUrl("");
//            }
//            if (!TextUtils.isEmpty(savingComplainImagesTable.get())) {
//                spNameDoc.setServerUpdatedStatus(savingComplainImagesTable.getLocalDocUrl());
//            } else {
//                spNameDoc.setLocalDocUrl("");
//            }


            if (!TextUtils.isEmpty(savingComplainImagesTable.getFarmerCode())) {
                spNameFertilizerPersonalDetailsClass.setFarmerCode(savingComplainImagesTable.getFarmerCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setFarmerCode("");
            }

            if (!TextUtils.isEmpty(savingComplainImagesTable.getSeasonCode())) {
                spNameFertilizerPersonalDetailsClass.setSeasonCode(savingComplainImagesTable.getSeasonCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeasonCode("");
            }

            ArrayList<SavingComplainImagesTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<SavingComplainImagesTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setComplaintRepository(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
//                                        List<SavingComplainImagesTable> savingComplainImagesTableTableFromDB = appDAO.getComplainImageFromLocalDb(savingComplainImagesTable.getLogBookNo());
                                        SavingComplainImagesTable savingComplainImagesTableTableFromDB = appDAO.getComplainImageFromLocalDb(savingComplainImagesTable.getLogBookNo());
                                        if (savingComplainImagesTableTableFromDB != null) {
//                                            savingComplainImagesTableTableFromDB.setID(businessReviewSurveyTableFromDB.getID());
//                                            savingComplainImagesTable.setSync(true);
//                                            savingComplainImagesTable.setServerStatus("1");
//                                            savingComplainImagesTable.setServerUpdatedStatus(true);

                                            /*for (int i = 0; i <savingComplainImagesTableTableFromDB.size(); i++) {
                                                savingComplainImagesTable.setSync(true);
                                                savingComplainImagesTable.setServerStatus("1");
                                                savingComplainImagesTable.setServerUpdatedStatus(true);
                                                savingComplainImagesTable.setLocalDocUrl(savingComplainImagesTable.getLocalDocUrl());
//                                                savingComplainImagesTableTableFromDB.add(savingComplainImagesTable);
                                            }*/


//                                            List<SavingComplainImagesTable> savingComplainImagesTableTableFromDBTemp = new ArrayList<>();
//                                            SavingComplainImagesTable savingComplainImagesTableTableFromDBTemp = new ArrayList<>();
                                            savingComplainImagesTable.setSync(true);
                                            savingComplainImagesTable.setServerStatus("1");
                                            savingComplainImagesTable.setServerUpdatedStatus(true);
                                            savingComplainImagesTable.setLocalDocUrl(savingComplainImagesTable.getLocalDocUrl());
//                                            savingComplainImagesTableTableFromDBTemp.add(savingComplainImagesTable);


//                                            savingComplainImagesTable.setSync(true);
//                                            savingComplainImagesTable.setServerStatus("1");
//                                            savingComplainImagesTable.setServerUpdatedStatus(true);
//                                            savingComplainImagesTable.setLocalDocUrl(savingComplainImagesTable.getLocalDocUrl());

//                                            savingComplainImagesTableTableFromDB.add(savingComplainImagesTable);
                                            appDAO.insertSavingComplainImages(savingComplainImagesTable);
                                            data.postValue("Transaction Sync Completed Successfully.");
//                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                            appDAO.insertSavingComplainImages(savingComplainImagesTableTableFromDBTemp);
                                        }
//                                        data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                    } else {

                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<List<SavingComplainImagesTable>> getComplainImagesFromLocalDBNotSync() {
        final MutableLiveData<List<SavingComplainImagesTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getComplainImagesFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getComplainImagesFromLocalDBNotSync("0"));
//                data.postValue(appDAO.getComplainImagesFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<List<AddGrowthMonitoringTable>> getGMFromLocalDBNotSync() {
        final MutableLiveData<List<AddGrowthMonitoringTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getGMFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getGMFromLocalDBNotSync("0"));
//                data.postValue(appDAO.getComplainImagesFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<List<SavingComplainImagesTable>> getComplainImagesFromLocalDB() {
        final MutableLiveData<List<SavingComplainImagesTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getComplainImagesFromLocalDB());
        });
        return data;
    }


    public LiveData<Integer> getCountComplaintDetailCount() {
        return appDAO.getAddComplaintsDetailsCount();
    }

    public LiveData<AddComplaintsDetailsTable> insertAddComplainFormTable(AddComplaintsDetailsTable addComplaintsDetailsTable) {
        final MutableLiveData<AddComplaintsDetailsTable> data = new MutableLiveData<>();
        executor.execute(() -> {
            appDAO.insertAddComplainFormTable(addComplaintsDetailsTable);
            // TODO: Sending result
            data.postValue(appDAO.getAddComplainFormTableData(addComplaintsDetailsTable.getLogBookNo()));
        });
        return data;
    }

    public LiveData<List<AddComplaintsDetailsTable>> getAddComplainFormTable() {
        final MutableLiveData<List<AddComplaintsDetailsTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            data.postValue(appDAO.getAddComplainFormTableDataList());
        });
        return data;
    }
    //complainData
    public LiveData<List<AddComplaintsDetailsTable>> getComplainDataDetailsDetailslistFromLocalDBNotSync() {
        final MutableLiveData<List<AddComplaintsDetailsTable>> data = new MutableLiveData<>();
        executor.execute(() -> {
            boolean dataExist = (appDAO.getAddComplainDataDetailsListFromLocalDBNotSync("0") != null);
            if (dataExist) {
                data.postValue(appDAO.getAddComplainDataDetailsListFromLocalDBNotSync("0"));
            }
        });
        return data;
    }

    public LiveData<String> syncAddComplainDataDetailsToServer(AddComplaintsDetailsTable addComplaintsDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {

            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddComplaintsDetailsTable spNameFertilizerPersonalDetailsClass = new AddComplaintsDetailsTable();
            spNameFertilizerPersonalDetailsClass= addComplaintsDetailsTable;

            System.out.println("Calling>>>>" + addComplaintsDetailsTable.getCode());
            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.getCode()))) {
                spNameFertilizerPersonalDetailsClass.setCode(addComplaintsDetailsTable.getCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setCode(null);
            }

//            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.isSync()))) {
//                complaints.setSync(addComplaintsDetailsTable.isSync());
//            } else {
//                complaints.setSync(false);
//            }

            spNameFertilizerPersonalDetailsClass.setIsActive(addComplaintsDetailsTable.getIsActive());

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getCreatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addComplaintsDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getUpdatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addComplaintsDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getUpdatedDate())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addComplaintsDetailsTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getCreatedDate())) {
                spNameFertilizerPersonalDetailsClass.setCreatedDate(addComplaintsDetailsTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.getComplaintStatus()))) {
                spNameFertilizerPersonalDetailsClass.setComplaintStatus(addComplaintsDetailsTable.getComplaintStatus());
            } else {
                spNameFertilizerPersonalDetailsClass.setComplaintStatus(null);
            }

            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.getComplaintType()))) {
                spNameFertilizerPersonalDetailsClass.setComplaintType(addComplaintsDetailsTable.getComplaintType());
            } else {
                spNameFertilizerPersonalDetailsClass.setComplaintType(null);
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getSolution())) {
                spNameFertilizerPersonalDetailsClass.setSolution(addComplaintsDetailsTable.getSolution());
            } else {
                spNameFertilizerPersonalDetailsClass.setSolution("");
            }


            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getLogBookNo())) {
                spNameFertilizerPersonalDetailsClass.setLogBookNo(addComplaintsDetailsTable.getLogBookNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setLogBookNo("");
            }


            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getFarmerCode())) {
                spNameFertilizerPersonalDetailsClass.setFarmerCode(addComplaintsDetailsTable.getFarmerCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setFarmerCode("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getSeasonCode())) {
                spNameFertilizerPersonalDetailsClass.setSeasonCode(addComplaintsDetailsTable.getSeasonCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeasonCode("");
            }


//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//            spNameBorewellDetailsClass.setCreatedDate(dateTime);
//            spNameBorewellDetailsClass.setUpdatedDate(dateTime);


            ArrayList<AddComplaintsDetailsTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddComplaintsDetailsTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setComplaints(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
//                                    Toast.makeText(context, "success coming", Toast.LENGTH_SHORT).show();
                                    Log.d("biruonResponse", "onResponse: " + response.body());
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
//                                        AddComplaintsDetailsTable addComplaintsDetailsFromLocalDB = appDAO.getTopAddComplainDataDetailsTableDataLocalDB(addComplaintsDetailsTable.getLogBookNo(),"0");
                                        if (addComplaintsDetailsTable != null) {
                                            addComplaintsDetailsTable.setLogBookNo(addComplaintsDetailsTable.getLogBookNo());
                                            addComplaintsDetailsTable.setComplaintsId(addComplaintsDetailsTable.getComplaintsId());
                                            addComplaintsDetailsTable.setSync(true);
                                            addComplaintsDetailsTable.setIsActive("true");
                                            addComplaintsDetailsTable.setServerStatus("1");
                                            appDAO.insertAddComplainFormTable(addComplaintsDetailsTable);
                                            // TODO: Sending result
                                            data.postValue("Transaction Sync Completed Successfully.");
//                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }
//                                        if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                        System.out.println("Error");
//                                        Log.d("biruExceptioneeeeee", "onResponse: Exception: ");
//                                    }

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    Log.d("biruException", "onResponse: Exception: " + ex);
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }

    public LiveData<String> syncGMDataDetailsToServer(AddGrowthMonitoringTable addComplaintsDetailsTable) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
        executor.execute(() -> {

            final TransactionSyncResponseDTO syncPersonalLandAllDetailsRequestDTO = new TransactionSyncResponseDTO();
            AddGrowthMonitoringTable spNameFertilizerPersonalDetailsClass = new AddGrowthMonitoringTable();
            spNameFertilizerPersonalDetailsClass= addComplaintsDetailsTable;

            System.out.println("Calling>>>>" + addComplaintsDetailsTable.getPlotNo());


            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.getStage()))) {
                spNameFertilizerPersonalDetailsClass.setStage(addComplaintsDetailsTable.getStage());
            } else {
                spNameFertilizerPersonalDetailsClass.setStage(null);
            }

//            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.isSync()))) {
//                complaints.setSync(addComplaintsDetailsTable.isSync());
//            } else {
//                complaints.setSync(false);
//            }

            spNameFertilizerPersonalDetailsClass.setActive(true);

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getCreatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId(addComplaintsDetailsTable.getCreatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedByUserId("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getUpdatedByUserId())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId(addComplaintsDetailsTable.getUpdatedByUserId());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedByUserId("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getUpdatedDate())) {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate(addComplaintsDetailsTable.getUpdatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setUpdatedDate("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getCreatedDate())) {
                spNameFertilizerPersonalDetailsClass.setCreatedDate(addComplaintsDetailsTable.getCreatedDate());
            } else {
                spNameFertilizerPersonalDetailsClass.setCreatedDate("");
            }

            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.getPlotNo()))) {
                spNameFertilizerPersonalDetailsClass.setPlotNo(addComplaintsDetailsTable.getPlotNo());
            } else {
                spNameFertilizerPersonalDetailsClass.setPlotNo(null);
            }

            if (!TextUtils.isEmpty(String.valueOf(addComplaintsDetailsTable.getRemarks()))) {
                spNameFertilizerPersonalDetailsClass.setRemarks(addComplaintsDetailsTable.getRemarks());
            } else {
                spNameFertilizerPersonalDetailsClass.setRemarks(null);
            }

//            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getSolution())) {
//                spNameFertilizerPersonalDetailsClass.setSolution(addComplaintsDetailsTable.getSolution());
//            } else {
//                spNameFertilizerPersonalDetailsClass.setSolution("");
//            }


            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getFileUrl())) {
                spNameFertilizerPersonalDetailsClass.setFileUrl(addComplaintsDetailsTable.getFileUrl());
            } else {
                spNameFertilizerPersonalDetailsClass.setFileUrl("");
            }


            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getFarmerCode())) {
                spNameFertilizerPersonalDetailsClass.setFarmerCode(addComplaintsDetailsTable.getFarmerCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setFarmerCode("");
            }

            if (!TextUtils.isEmpty(addComplaintsDetailsTable.getSeasonCode())) {
                spNameFertilizerPersonalDetailsClass.setSeasonCode(addComplaintsDetailsTable.getSeasonCode());
            } else {
                spNameFertilizerPersonalDetailsClass.setSeasonCode("");
            }


//            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//            spNameBorewellDetailsClass.setCreatedDate(dateTime);
//            spNameBorewellDetailsClass.setUpdatedDate(dateTime);


            ArrayList<AddGrowthMonitoringTable> spNameFertilizerPersonalDetailsClasses = new ArrayList<AddGrowthMonitoringTable>();
            spNameFertilizerPersonalDetailsClasses.add(spNameFertilizerPersonalDetailsClass);
            syncPersonalLandAllDetailsRequestDTO.setGrowthMonitoring(spNameFertilizerPersonalDetailsClasses);
            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO).
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO, appHelper.getSharedPrefObj().getString(accessToken, "")).
        enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            executor.execute(() -> {
                                try {
//                                    Toast.makeText(context, "success coming", Toast.LENGTH_SHORT).show();
                                    Log.d("biruonResponse", "onResponse: " + response.body());
                                    String strResponse = response.body();
//                                    String strResponse = response.body().string();
                                    Log.d(TAG, "onResponse: AppData D20 " + response.body());
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);Transanctions Synced Successfully
                                    if (strResponse.equals("Transaction Sync Completed Successfully.")) {
//                                    if (strResponse.equals("Transanctions Synced Successfully")) {
//                                        AddComplaintsDetailsTable addComplaintsDetailsFromLocalDB = appDAO.getTopAddComplainDataDetailsTableDataLocalDB(addComplaintsDetailsTable.getLogBookNo(),"0");
                                        if (addComplaintsDetailsTable != null) {
//                                            addComplaintsDetailsTable.setLogBookNo(addComplaintsDetailsTable.getLogBookNo());
//                                            addComplaintsDetailsTable.setComplaintsId(addComplaintsDetailsTable.getComplaintsId());
                                            addComplaintsDetailsTable.setSync(true);
                                            addComplaintsDetailsTable.setActive(true);
                                            addComplaintsDetailsTable.setServerStatus("1");
                                            appDAO.insertGrowthMonitoringIntoLocalDBQuery(addComplaintsDetailsTable);
                                            // TODO: Sending result
                                            data.postValue("Transaction Sync Completed Successfully.");
//                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
                                        }

                                    } else {
                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
                                    }
//                                        if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                        System.out.println("Error");
//                                        Log.d("biruExceptioneeeeee", "onResponse: Exception: ");
//                                    }

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    Log.d("biruException", "onResponse: Exception: " + ex);
                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
                                }


                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            // TODO: Sending result
                            executor.execute(() -> {
                                data.postValue(FAILURE_RESPONSE_MESSAGE);
                            });
                        }
                    });
        });
        return data;
    }


    public int lockedvalue(String key) {
        // Implement your logic to retrieve the value associated with the key from your data source (e.g., a database or cache)
        System.out.println("maxLockPeriodkey: " + key);
        try {
            // Example: Retrieve the value from a database
            int value  = Integer.parseInt(appDAO.getValueForKey(key).getValue());
            System.out.println("maxLockPeriodvalue: " + value);
            // Return the retrieved value
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle the exception or return a specific error code
            return -1;
        }
    }


//
//    // TODO: 2/21/2022 organic
//
////    public LiveData<List<AddOrganicDetailsTable>> getOrganicDetailsDetailslistFromLocalDBNotSync() {
////        final MutableLiveData<List<AddOrganicDetailsTable>> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            boolean dataExist = (appDAO.getAddOrganicDetailsListFromLocalDBNotSync("0") != null);
////            if (dataExist) {
////                data.postValue(appDAO.getAddOrganicDetailsListFromLocalDBNotSync("0"));
////            }
////        });
////        return data;
////    }
//
//    // TODO: 2/21/2022 sync Organic details
//
////    public LiveData<String> syncAddOrganicDetailsToServer(AddOrganicDetailsTable addOrganicDetailsTable) {
////        final MutableLiveData<String> data = new MutableLiveData<>();
////        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
////        executor.execute(() -> {
////            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
////            SyncPersonalLandAllDetailsRequestDTO.OrganicAmendments spNameOrganicPersonalDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.OrganicAmendments();
////
////            if (!TextUtils.isEmpty(String.valueOf(addOrganicDetailsTable.getValue()))) {
////                spNameOrganicPersonalDetailsClass.setValue(addOrganicDetailsTable.getValue());
////            } else {
////                spNameOrganicPersonalDetailsClass.setValue(null);
////            }
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getDate())) {
////                spNameOrganicPersonalDetailsClass.setDate(addOrganicDetailsTable.getDate());
////            } else {
////                spNameOrganicPersonalDetailsClass.setDate("");
////            }
////
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getLogBookNo())) {
////                spNameOrganicPersonalDetailsClass.setLogBookNo(addOrganicDetailsTable.getLogBookNo());
////            } else {
////                spNameOrganicPersonalDetailsClass.setLogBookNo("");
////            }
////
////
//////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//////            spNameOrganicPersonalDetailsClass.setCreatedDate(dateTime);
//////            spNameOrganicPersonalDetailsClass.setUpdatedDate(dateTime);
////
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getIsActive())) {
////                spNameOrganicPersonalDetailsClass.setIsActive(addOrganicDetailsTable.getIsActive());
////            } else {
////                spNameOrganicPersonalDetailsClass.setIsActive("");
////            }
////
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getCreatedByUserId())) {
////                spNameOrganicPersonalDetailsClass.setCreatedByUserId(addOrganicDetailsTable.getCreatedByUserId());
////            } else {
////                spNameOrganicPersonalDetailsClass.setCreatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getUpdatedByUserId())) {
////                spNameOrganicPersonalDetailsClass.setUpdatedByUserId(addOrganicDetailsTable.getUpdatedByUserId());
////            } else {
////                spNameOrganicPersonalDetailsClass.setUpdatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getUpdatedDate())) {
////                spNameOrganicPersonalDetailsClass.setUpdatedDate(addOrganicDetailsTable.getUpdatedDate());
////            } else {
////                spNameOrganicPersonalDetailsClass.setUpdatedDate("");
////            }
////
////            if (!TextUtils.isEmpty(addOrganicDetailsTable.getCreatedDate())) {
////                spNameOrganicPersonalDetailsClass.setCreatedDate(addOrganicDetailsTable.getCreatedDate());
////            } else {
////                spNameOrganicPersonalDetailsClass.setCreatedDate("");
////            }
////
////
////            ArrayList<SyncPersonalLandAllDetailsRequestDTO.OrganicAmendments> spNameOrganicPersonalDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.OrganicAmendments>();
////            spNameOrganicPersonalDetailsClasses.add(spNameOrganicPersonalDetailsClass);
////            syncPersonalLandAllDetailsRequestDTO.setOrganicAmendments(spNameOrganicPersonalDetailsClasses);
////
////            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
////
////                    appHelper.getSharedPrefObj().getString(accessToken, "")).
////                    enqueue(new Callback<ResponseBody>() {
////                        @Override
////                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                            executor.execute(() -> {
////                                try {
////                                    String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData " + response);
////                                    JSONObject json_object = new JSONObject(strResponse);
////                                    String message = "", status = "";
////                                    Log.e(TAG, "onResponse: data json" + json_object);
////                                    message = json_object.getString("message");
////                                    status = json_object.getString("status");
////                                    Log.d(TAG, "status " + status);
////
////                                    if (status.equals("1")) {
////                                        AddOrganicDetailsTable addOrganicDetailsFromLocalDB = appDAO.getTopAddOrganicDetailsTableDataLocalDB(addOrganicDetailsTable.getLogBookNo(),"0");
////                                        if (addOrganicDetailsFromLocalDB != null) {
////                                            addOrganicDetailsFromLocalDB.setOrganicId(addOrganicDetailsFromLocalDB.getOrganicId());
////                                            addOrganicDetailsTable.setSync(true);
////                                            addOrganicDetailsTable.setServerStatus("1");
////                                            appDAO.insertAddOrganicDetailsTable(addOrganicDetailsTable);
////                                            // TODO: Sending result
////                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
////                                        }
////
////                                    } else if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                    }
////
////                                } catch (Exception ex) {
////                                    ex.printStackTrace();
////                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                }
////
////
////                            });
////                        }
////
////                        @Override
////                        public void onFailure(Call<ResponseBody> call, Throwable t) {
////                            t.printStackTrace();
////                            // TODO: Sending result
////                            executor.execute(() -> {
////                                data.postValue(FAILURE_RESPONSE_MESSAGE);
////                            });
////                        }
////                    });
////        });
////        return data;
////    }
//
//
//    // TODO: 2/21/2022 Water Regime Season
////
////    public LiveData<List<AddWaterRegimeSeasonDetailsTable>> getWaterRegimeSeasonDetailsDetailslistFromLocalDBNotSync() {
////        final MutableLiveData<List<AddWaterRegimeSeasonDetailsTable>> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            boolean dataExist = (appDAO.getAddWaterRegimeSeasonDetailsListFromLocalDBNotSync("0") != null);
////            if (dataExist) {
////                data.postValue(appDAO.getAddWaterRegimeSeasonDetailsListFromLocalDBNotSync("0"));
////            }
////        });
////        return data;
////    }
//
//    // TODO: 2/21/2022 sync Water Regime Season
//
////    public LiveData<String> syncAddWaterRegimeSeasonDetailsToServer(AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable) {
////        final MutableLiveData<String> data = new MutableLiveData<>();
////        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
////        executor.execute(() -> {
////            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
////            SyncPersonalLandAllDetailsRequestDTO.WaterRegimeonSeason spNameWaterRegimeSeasonDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.WaterRegimeonSeason();
////
////            if (!TextUtils.isEmpty(String.valueOf(addWaterRegimeSeasonDetailsTable.getField()))) {
////                spNameWaterRegimeSeasonDetailsClass.setField(addWaterRegimeSeasonDetailsTable.getField());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setField(null);
////            }
////
////
////            if (!TextUtils.isEmpty(addWaterRegimeSeasonDetailsTable.getLogBookNo())) {
////                spNameWaterRegimeSeasonDetailsClass.setLogBookNo(addWaterRegimeSeasonDetailsTable.getLogBookNo());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setLogBookNo("");
////            }
////
////
//////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//////            spNameWaterRegimeSeasonDetailsClass.setCreatedDate(dateTime);
//////            spNameWaterRegimeSeasonDetailsClass.setUpdatedDate(dateTime);
////
////
////            if (!TextUtils.isEmpty(addWaterRegimeSeasonDetailsTable.getIsActive())) {
////                spNameWaterRegimeSeasonDetailsClass.setIsActive(addWaterRegimeSeasonDetailsTable.getIsActive());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setIsActive("");
////            }
////
////
////            if (!TextUtils.isEmpty(addWaterRegimeSeasonDetailsTable.getCreatedByUserId())) {
////                spNameWaterRegimeSeasonDetailsClass.setCreatedByUserId(addWaterRegimeSeasonDetailsTable.getCreatedByUserId());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setCreatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterRegimeSeasonDetailsTable.getUpdatedByUserId())) {
////                spNameWaterRegimeSeasonDetailsClass.setUpdatedByUserId(addWaterRegimeSeasonDetailsTable.getUpdatedByUserId());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setUpdatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterRegimeSeasonDetailsTable.getUpdatedDate())) {
////                spNameWaterRegimeSeasonDetailsClass.setUpdatedDate(addWaterRegimeSeasonDetailsTable.getUpdatedDate());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setUpdatedDate("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterRegimeSeasonDetailsTable.getCreatedDate())) {
////                spNameWaterRegimeSeasonDetailsClass.setCreatedDate(addWaterRegimeSeasonDetailsTable.getCreatedDate());
////            } else {
////                spNameWaterRegimeSeasonDetailsClass.setCreatedDate("");
////            }
////
////
////            ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeonSeason> spNameWaterRegimeDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeonSeason>();
////            spNameWaterRegimeDetailsClasses.add(spNameWaterRegimeSeasonDetailsClass);
////            syncPersonalLandAllDetailsRequestDTO.setWaterRegimeonSeason(spNameWaterRegimeDetailsClasses);
////
////            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
////
////                    appHelper.getSharedPrefObj().getString(accessToken, "")).
////                    enqueue(new Callback<ResponseBody>() {
////                        @Override
////                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                            executor.execute(() -> {
////                                try {
////                                    String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData " + response);
////                                    JSONObject json_object = new JSONObject(strResponse);
////                                    String message = "", status = "";
////                                    Log.e(TAG, "onResponse: data json" + json_object);
////                                    message = json_object.getString("message");
////                                    status = json_object.getString("status");
////                                    Log.d(TAG, "status " + status);
////
////                                    if (status.equals("1")) {
////                                        AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsFromLocalDB = appDAO.getTopAddWaterRegimeSeasonDetailsTableDataLocalDB(addWaterRegimeSeasonDetailsTable.getLogBookNo(),"0");
////                                        if (addWaterRegimeSeasonDetailsFromLocalDB != null) {
////                                            addWaterRegimeSeasonDetailsFromLocalDB.setWaterRegSeasonId(addWaterRegimeSeasonDetailsFromLocalDB.getWaterRegSeasonId());
////                                            addWaterRegimeSeasonDetailsTable.setSync(true);
////                                            addWaterRegimeSeasonDetailsTable.setServerStatus("1");
////                                            appDAO.insertAddWaterRegimeSeasonDetailsTable(addWaterRegimeSeasonDetailsTable);
////                                            // TODO: Sending result
////                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
////                                        }
////
////                                    } else if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                    }
////
////                                } catch (Exception ex) {
////                                    ex.printStackTrace();
////                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                }
////
////
////                            });
////                        }
////
////                        @Override
////                        public void onFailure(Call<ResponseBody> call, Throwable t) {
////                            t.printStackTrace();
////                            // TODO: Sending result
////                            executor.execute(() -> {
////                                data.postValue(FAILURE_RESPONSE_MESSAGE);
////                            });
////                        }
////                    });
////        });
////        return data;
////    }
//
//
//    // TODO: 2/21/2022 Water Pre  Season
//
////    public LiveData<List<AddWaterReasonPreSeasonTable>> getWaterPreSeasonDetailsDetailslistFromLocalDBNotSync() {
////        final MutableLiveData<List<AddWaterReasonPreSeasonTable>> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            boolean dataExist = (appDAO.getAddWaterReasonPreSeasonDetailsListFromLocalDBNotSync("0") != null);
////            if (dataExist) {
////                data.postValue(appDAO.getAddWaterReasonPreSeasonDetailsListFromLocalDBNotSync("0"));
////            }
////        });
////        return data;
////    }
//
//    // TODO: 2/21/2022 sync Water Pre Season
//
////    public LiveData<String> syncAddWaterReasonPreSeasonDetailsToServer(AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable) {
////        final MutableLiveData<String> data = new MutableLiveData<>();
////        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
////        executor.execute(() -> {
////            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
////            SyncPersonalLandAllDetailsRequestDTO.WaterReasonPreSeason spNameWaterPreSeasonDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.WaterReasonPreSeason();
////
////            if (!TextUtils.isEmpty(String.valueOf(addWaterReasonPreSeasonTable.getField()))) {
////                spNameWaterPreSeasonDetailsClass.setField(addWaterReasonPreSeasonTable.getField());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setField(null);
////            }
////
////
////            if (!TextUtils.isEmpty(addWaterReasonPreSeasonTable.getLogBookNo())) {
////                spNameWaterPreSeasonDetailsClass.setLogBookNo(addWaterReasonPreSeasonTable.getLogBookNo());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setLogBookNo("");
////            }
////
////
//////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//////            spNameOrganicPersonalDetailsClass.setCreatedDate(dateTime);
//////            spNameOrganicPersonalDetailsClass.setUpdatedDate(dateTime);
////
////
////            if (!TextUtils.isEmpty(addWaterReasonPreSeasonTable.getIsActive())) {
////                spNameWaterPreSeasonDetailsClass.setIsActive(addWaterReasonPreSeasonTable.getIsActive());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setIsActive("");
////            }
////
////
////            if (!TextUtils.isEmpty(addWaterReasonPreSeasonTable.getCreatedByUserId())) {
////                spNameWaterPreSeasonDetailsClass.setCreatedByUserId(addWaterReasonPreSeasonTable.getCreatedByUserId());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setCreatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterReasonPreSeasonTable.getUpdatedByUserId())) {
////                spNameWaterPreSeasonDetailsClass.setUpdatedByUserId(addWaterReasonPreSeasonTable.getUpdatedByUserId());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setUpdatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterReasonPreSeasonTable.getUpdatedDate())) {
////                spNameWaterPreSeasonDetailsClass.setUpdatedDate(addWaterReasonPreSeasonTable.getUpdatedDate());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setUpdatedDate("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterReasonPreSeasonTable.getCreatedDate())) {
////                spNameWaterPreSeasonDetailsClass.setCreatedDate(addWaterReasonPreSeasonTable.getCreatedDate());
////            } else {
////                spNameWaterPreSeasonDetailsClass.setCreatedDate("");
////            }
////
////
////            ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterReasonPreSeason> spNameWaterPreSeasonDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterReasonPreSeason>();
////            spNameWaterPreSeasonDetailsClasses.add(spNameWaterPreSeasonDetailsClass);
////
////            syncPersonalLandAllDetailsRequestDTO.setWaterReasonPreSeason(spNameWaterPreSeasonDetailsClasses);
////
////            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
////
////                    appHelper.getSharedPrefObj().getString(accessToken, "")).
////                    enqueue(new Callback<ResponseBody>() {
////                        @Override
////                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                            executor.execute(() -> {
////                                try {
////                                    String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData " + response);
////                                    JSONObject json_object = new JSONObject(strResponse);
////                                    String message = "", status = "";
////                                    Log.e(TAG, "onResponse: data json" + json_object);
////                                    message = json_object.getString("message");
////                                    status = json_object.getString("status");
////                                    Log.d(TAG, "status " + status);
////
////                                    if (status.equals("1")) {
////                                        AddWaterReasonPreSeasonTable addWaterReasonPreSeasonDetailsFromLocalDB = appDAO.getTopAddWaterReasonPreSeasonDetailsTableDataLocalDB(addWaterReasonPreSeasonTable.getLogBookNo(),"0");
////                                        if (addWaterReasonPreSeasonDetailsFromLocalDB != null) {
////                                            addWaterReasonPreSeasonDetailsFromLocalDB.setWaterPreSeasonId(addWaterReasonPreSeasonDetailsFromLocalDB.getWaterPreSeasonId());
////                                            addWaterReasonPreSeasonTable.setSync(true);
////                                            addWaterReasonPreSeasonTable.setServerStatus("1");
////                                            appDAO.insertAddWaterReasonPreSeasonTable(addWaterReasonPreSeasonTable);
////                                            // TODO: Sending result
////                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
////                                        }
////
////                                    } else if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                    }
////
////                                } catch (Exception ex) {
////                                    ex.printStackTrace();
////                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                }
////
////
////                            });
////                        }
////
////                        @Override
////                        public void onFailure(Call<ResponseBody> call, Throwable t) {
////                            t.printStackTrace();
////                            // TODO: Sending result
////                            executor.execute(() -> {
////                                data.postValue(FAILURE_RESPONSE_MESSAGE);
////                            });
////                        }
////                    });
////        });
////        return data;
////    }
//
//
//    // TODO: 2/21/2022 BoreWell Pump
////
////    public LiveData<List<AddBoreWellSeasonTable>> getBoreWellPumpSeasonDetailsDetailslistFromLocalDBNotSync() {
////        final MutableLiveData<List<AddBoreWellSeasonTable>> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            boolean dataExist = (appDAO.getAddBoreWellPumpSeasonDetailsListFromLocalDBNotSync("0") != null);
////            if (dataExist) {
////                data.postValue(appDAO.getAddBoreWellPumpSeasonDetailsListFromLocalDBNotSync("0"));
////            }
////        });
////        return data;
////    }
//
//    // TODO: 2/21/2022 sync BoreWell Pump
//
////    public LiveData<String> syncAddBoreWellPumpSeasonDetailsToServer(AddBoreWellSeasonTable addBoreWellSeasonTable) {
////        final MutableLiveData<String> data = new MutableLiveData<>();
////        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
////        executor.execute(() -> {
////            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
////            SyncPersonalLandAllDetailsRequestDTO.BorewellPumpOperations spNameBorewellDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.BorewellPumpOperations();
////
////            if (!TextUtils.isEmpty(String.valueOf(addBoreWellSeasonTable.getHoursDay()))) {
////                spNameBorewellDetailsClass.setHoursDay(addBoreWellSeasonTable.getHoursDay());
////            } else {
////                spNameBorewellDetailsClass.setHoursDay(null);
////            }
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getDate())) {
////                spNameBorewellDetailsClass.setDate(addBoreWellSeasonTable.getDate());
////            } else {
////                spNameBorewellDetailsClass.setDate("");
////            }
////
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getLogBookNo())) {
////                spNameBorewellDetailsClass.setLogBookNo(addBoreWellSeasonTable.getLogBookNo());
////            } else {
////                spNameBorewellDetailsClass.setLogBookNo("");
////            }
////
////
//////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//////            spNameBorewellDetailsClass.setCreatedDate(dateTime);
//////            spNameBorewellDetailsClass.setUpdatedDate(dateTime);
////
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getIsActive())) {
////                spNameBorewellDetailsClass.setIsActive(addBoreWellSeasonTable.getIsActive());
////            } else {
////                spNameBorewellDetailsClass.setIsActive("");
////            }
////
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getCreatedByUserId())) {
////                spNameBorewellDetailsClass.setCreatedByUserId(addBoreWellSeasonTable.getCreatedByUserId());
////            } else {
////                spNameBorewellDetailsClass.setCreatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getUpdatedByUserId())) {
////                spNameBorewellDetailsClass.setUpdatedByUserId(addBoreWellSeasonTable.getUpdatedByUserId());
////            } else {
////                spNameBorewellDetailsClass.setUpdatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getUpdatedDate())) {
////                spNameBorewellDetailsClass.setUpdatedDate(addBoreWellSeasonTable.getUpdatedDate());
////            } else {
////                spNameBorewellDetailsClass.setUpdatedDate("");
////            }
////
////            if (!TextUtils.isEmpty(addBoreWellSeasonTable.getCreatedDate())) {
////                spNameBorewellDetailsClass.setCreatedDate(addBoreWellSeasonTable.getCreatedDate());
////            } else {
////                spNameBorewellDetailsClass.setCreatedDate("");
////            }
////
////
////            ArrayList<SyncPersonalLandAllDetailsRequestDTO.BorewellPumpOperations> spNameBorewellDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.BorewellPumpOperations>();
////            spNameBorewellDetailsClasses.add(spNameBorewellDetailsClass);
////            syncPersonalLandAllDetailsRequestDTO.setBorewellPumpOperations(spNameBorewellDetailsClasses);
////
////            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
////
////                    appHelper.getSharedPrefObj().getString(accessToken, "")).
////                    enqueue(new Callback<ResponseBody>() {
////                        @Override
////                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                            executor.execute(() -> {
////                                try {
////                                    String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData " + response);
////                                    JSONObject json_object = new JSONObject(strResponse);
////                                    String message = "", status = "";
////                                    Log.e(TAG, "onResponse: data json" + json_object);
////                                    message = json_object.getString("message");
////                                    status = json_object.getString("status");
////                                    Log.d(TAG, "status " + status);
////                                    if (status.equals("1")) {
////                                        AddBoreWellSeasonTable addBoreWellPumpSeasonDetailsFromLocalDB = appDAO.getTopAddBoreWellPumpSeasonDetailsTableDataLocalDB(addBoreWellSeasonTable.getLogBookNo(),"0");
////                                        if (addBoreWellPumpSeasonDetailsFromLocalDB != null) {
////                                            addBoreWellPumpSeasonDetailsFromLocalDB.setBorewellID(addBoreWellPumpSeasonDetailsFromLocalDB.getBorewellID());
////                                            addBoreWellSeasonTable.setSync(true);
////                                            addBoreWellSeasonTable.setServerStatus("1");
////                                            appDAO.insertAddBoreWellPumpSeasonTable(addBoreWellSeasonTable);
////                                            // TODO: Sending result
////                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
////                                        }
////
////                                    } else if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                    }
////
////                                } catch (Exception ex) {
////                                    ex.printStackTrace();
////                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                }
////
////
////                            });
////                        }
////
////                        @Override
////                        public void onFailure(Call<ResponseBody> call, Throwable t) {
////                            t.printStackTrace();
////                            // TODO: Sending result
////                            executor.execute(() -> {
////                                data.postValue(FAILURE_RESPONSE_MESSAGE);
////                            });
////                        }
////                    });
////        });
////        return data;
////    }
//
//
//    // TODO: 2/21/2022 Water Field
////
////    public LiveData<List<AddWaterSeasonFeildTable>> getWaterSeasonFieldDetailsDetailslistFromLocalDBNotSync() {
////        final MutableLiveData<List<AddWaterSeasonFeildTable>> data = new MutableLiveData<>();
////        executor.execute(() -> {
////            boolean dataExist = (appDAO.getAddWaterSeasonFeildDetailsListFromLocalDBNotSync("0") != null);
////            if (dataExist) {
////                data.postValue(appDAO.getAddWaterSeasonFeildDetailsListFromLocalDBNotSync("0"));
////            }
////        });
////        return data;
////    }
//
//    // TODO: 2/21/2022 sync Water Field
//
////    public LiveData<String> syncAddWaterSeasonFeildTableDetailsToServer(AddWaterSeasonFeildTable addWaterSeasonFeildTable) {
////        final MutableLiveData<String> data = new MutableLiveData<>();
////        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
////        executor.execute(() -> {
////            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
////            SyncPersonalLandAllDetailsRequestDTO.WaterRegimeOnthefield spNameWaterFieldDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.WaterRegimeOnthefield();
////
////            if (!TextUtils.isEmpty(String.valueOf(addWaterSeasonFeildTable.getField()))) {
////                spNameWaterFieldDetailsClass.setField(addWaterSeasonFeildTable.getField());
////            } else {
////                spNameWaterFieldDetailsClass.setField(null);
////            }
////
////
////            if (!TextUtils.isEmpty(addWaterSeasonFeildTable.getLogBookNo())) {
////                spNameWaterFieldDetailsClass.setLogBookNo(addWaterSeasonFeildTable.getLogBookNo());
////            } else {
////                spNameWaterFieldDetailsClass.setLogBookNo("");
////            }
////
////
//////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
//////            spNameWaterFieldDetailsClass.setCreatedDate(dateTime);
//////            spNameWaterFieldDetailsClass.setUpdatedDate(dateTime);
////
////
////            if (!TextUtils.isEmpty(addWaterSeasonFeildTable.getIsActive())) {
////                spNameWaterFieldDetailsClass.setIsActive(addWaterSeasonFeildTable.getIsActive());
////            } else {
////                spNameWaterFieldDetailsClass.setIsActive("");
////            }
////
////
////            if (!TextUtils.isEmpty(addWaterSeasonFeildTable.getCreatedByUserId())) {
////                spNameWaterFieldDetailsClass.setCreatedByUserId(addWaterSeasonFeildTable.getCreatedByUserId());
////            } else {
////                spNameWaterFieldDetailsClass.setCreatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterSeasonFeildTable.getUpdatedByUserId())) {
////                spNameWaterFieldDetailsClass.setUpdatedByUserId(addWaterSeasonFeildTable.getUpdatedByUserId());
////            } else {
////                spNameWaterFieldDetailsClass.setUpdatedByUserId("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterSeasonFeildTable.getUpdatedDate())) {
////                spNameWaterFieldDetailsClass.setUpdatedDate(addWaterSeasonFeildTable.getUpdatedDate());
////            } else {
////                spNameWaterFieldDetailsClass.setUpdatedDate("");
////            }
////
////            if (!TextUtils.isEmpty(addWaterSeasonFeildTable.getCreatedDate())) {
////                spNameWaterFieldDetailsClass.setCreatedDate(addWaterSeasonFeildTable.getCreatedDate());
////            } else {
////                spNameWaterFieldDetailsClass.setCreatedDate("");
////            }
////
////
////            ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeOnthefield> spNameWaterFieldDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.WaterRegimeOnthefield>();
////            spNameWaterFieldDetailsClasses.add(spNameWaterFieldDetailsClass);
////            syncPersonalLandAllDetailsRequestDTO.setWaterRegimeOnthefield(spNameWaterFieldDetailsClasses);
////
////            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
////
////                    appHelper.getSharedPrefObj().getString(accessToken, "")).
////                    enqueue(new Callback<ResponseBody>() {
////                        @Override
////                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                            executor.execute(() -> {
////                                try {
////                                    String strResponse = response.body().string();
////                                    Log.d(TAG, "onResponse: AppData " + response);
////                                    JSONObject json_object = new JSONObject(strResponse);
////                                    String message = "", status = "";
////                                    Log.e(TAG, "onResponse: data json" + json_object);
////                                    message = json_object.getString("message");
////                                    status = json_object.getString("status");
////                                    Log.d(TAG, "status " + status);
////
////                                    if (status.equals("1")) {
////                                        AddWaterSeasonFeildTable addWaterSeasonFeildDetailsFromLocalDB = appDAO.getTopAddWaterSeasonFeildDetailsTableDataLocalDB(addWaterSeasonFeildTable.getLogBookNo(),"0");
////                                        if (addWaterSeasonFeildDetailsFromLocalDB != null) {
////                                            addWaterSeasonFeildDetailsFromLocalDB.setWaterSeasonFieldId(addWaterSeasonFeildDetailsFromLocalDB.getWaterSeasonFieldId());
////                                            addWaterSeasonFeildTable.setSync(true);
////                                            addWaterSeasonFeildTable.setServerStatus("1");
////                                            appDAO.insertAddWaterSeasonFeildTable(addWaterSeasonFeildTable);
////                                            // TODO: Sending result
////                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
////                                        }
////
////                                    } else if (status.equals("0")) {
////                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                    }
////
////                                } catch (Exception ex) {
////                                    ex.printStackTrace();
////                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
////                                }
////
////
////                            });
////                        }
////
////                        @Override
////                        public void onFailure(Call<ResponseBody> call, Throwable t) {
////                            t.printStackTrace();
////                            // TODO: Sending result
////                            executor.execute(() -> {
////                                data.postValue(FAILURE_RESPONSE_MESSAGE);
////                            });
////                        }
////                    });
////        });
////        return data;
////    }
//
//
//    // TODO: 2/21/2022 Harvest
//
//    public LiveData<List<AddHarvestDetailsTable>> getHarvestDetailsDetailslistFromLocalDBNotSync() {
//        final MutableLiveData<List<AddHarvestDetailsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getHarvestDetailsListFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getHarvestDetailsListFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
//
//    // TODO: 2/21/2022 sync Harvest
//
//    public LiveData<String> syncAddHarvestDetailsToServer(AddHarvestDetailsTable addHarvestDetailsTable) {
//        final MutableLiveData<String> data = new MutableLiveData<>();
//        AppWebService.changeApiBaseUrl(RAW_DATA_URL);
//        executor.execute(() -> {
//            final SyncPersonalLandAllDetailsRequestDTO syncPersonalLandAllDetailsRequestDTO = new SyncPersonalLandAllDetailsRequestDTO();
//            SyncPersonalLandAllDetailsRequestDTO.Harvesting spNameHarvestDetailsClass = new SyncPersonalLandAllDetailsRequestDTO.Harvesting();
//
////            if (!TextUtils.isEmpty(addHarvestDetailsTable.getYeildQty())) {
////                spNameHarvestDetailsClass.setYeildQty(addHarvestDetailsTable.getYeildQty());
////            } else {
////                spNameHarvestDetailsClass.setYeildQty(null);
////            }
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getDate())) {
//                spNameHarvestDetailsClass.setDate(addHarvestDetailsTable.getDate());
//            } else {
//                spNameHarvestDetailsClass.setDate("");
//            }
//
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getLogBookNo())) {
//                spNameHarvestDetailsClass.setLogBookNo(addHarvestDetailsTable.getLogBookNo());
//            } else {
//                spNameHarvestDetailsClass.setLogBookNo("");
//            }
//
//
////            String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////            Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + dateTime);
////            spNameHarvestDetailsClass.setCreatedDate(dateTime);
////            spNameHarvestDetailsClass.setUpdatedDate(dateTime);
//
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getIsActive())) {
//                spNameHarvestDetailsClass.setIsActive(addHarvestDetailsTable.getIsActive());
//            } else {
//                spNameHarvestDetailsClass.setIsActive("");
//            }
//
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getCreatedByUserId())) {
//                spNameHarvestDetailsClass.setCreatedByUserId(addHarvestDetailsTable.getCreatedByUserId());
//            } else {
//                spNameHarvestDetailsClass.setCreatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getUpdatedByUserId())) {
//                spNameHarvestDetailsClass.setUpdatedByUserId(addHarvestDetailsTable.getUpdatedByUserId());
//            } else {
//                spNameHarvestDetailsClass.setUpdatedByUserId("");
//            }
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getUpdatedDate())) {
//                spNameHarvestDetailsClass.setUpdatedDate(addHarvestDetailsTable.getUpdatedDate());
//            } else {
//                spNameHarvestDetailsClass.setUpdatedDate("");
//            }
//
//            if (!TextUtils.isEmpty(addHarvestDetailsTable.getCreatedDate())) {
//                spNameHarvestDetailsClass.setCreatedDate(addHarvestDetailsTable.getCreatedDate());
//            } else {
//                spNameHarvestDetailsClass.setCreatedDate("");
//            }
//
//
//            ArrayList<SyncPersonalLandAllDetailsRequestDTO.Harvesting> spNameHarvestDetailsClasses = new ArrayList<SyncPersonalLandAllDetailsRequestDTO.Harvesting>();
//            spNameHarvestDetailsClasses.add(spNameHarvestDetailsClass);
//            syncPersonalLandAllDetailsRequestDTO.setHarvesting(spNameHarvestDetailsClasses);
//
//            AppWebService.createService(AppAPI.class).syncFarmerDetailsDataToServer(syncPersonalLandAllDetailsRequestDTO,
//
//                    appHelper.getSharedPrefObj().getString(accessToken, "")).
//                    enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            executor.execute(() -> {
//                                try {
//                                    String strResponse = response.body().string();
//                                    Log.d(TAG, "onResponse: AppData " + response);
//                                    JSONObject json_object = new JSONObject(strResponse);
//                                    String message = "", status = "";
//                                    Log.e(TAG, "onResponse: data json" + json_object);
//                                    message = json_object.getString("message");
//                                    status = json_object.getString("status");
//                                    Log.d(TAG, "status " + status);
//
//                                    if (status.equals("1")) {
//                                        AddHarvestDetailsTable addHarvestDetailsFromLocalDB = appDAO.getTopAddHarvestDetailsTableDataLocalDB(addHarvestDetailsTable.getLogBookNo(),"0");
//                                        if (addHarvestDetailsFromLocalDB != null) {
//                                            addHarvestDetailsFromLocalDB.setHarvestID(addHarvestDetailsTable.getHarvestID());
//                                            addHarvestDetailsTable.setSync(true);
//                                            addHarvestDetailsTable.setServerStatus("1");
//                                            appDAO.insertAddHarvestDetailsTable(addHarvestDetailsTable);
//                                            // TODO: Sending result
//                                            data.postValue(SUCCESS_RESPONSE_MESSAGE);
//                                        }
//
//                                    } else if (status.equals("0")) {
//                                        data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                    }
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    data.postValue(FAILURE_RESPONSE_MESSAGE);
//                                }
//
//
//                            });
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
//                            // TODO: Sending result
//                            executor.execute(() -> {
//                                data.postValue(FAILURE_RESPONSE_MESSAGE);
//                            });
//                        }
//                    });
//        });
//        return data;
//    }
//
//    /**
//     * This function upload the large file to server with other POST values.
//     *
//     * @param file
//     * @param targetUrl
//     * @return
//     */
//    public static String uploadFileToServer(File file, String targetUrl, String userID,String token,final ApplicationThread.OnComplete<String> onComplete) {
//        String response = "error";
//        HttpURLConnection connection = null;
//        DataOutputStream outputStream = null;
//
//        String urlServer = targetUrl;
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//
//        int bytesRead, bytesAvailable, bufferSize;
//        byte[] buffer;
//        int maxBufferSize = 1 * 1024;
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//
//            URL url = new URL(urlServer);
//            connection = (HttpURLConnection) url.openConnection();
//
//            // Allow Inputs & Outputs
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setUseCaches(false);
//            connection.setChunkedStreamingMode(1024);
//            // Enable POST method
//            connection.setRequestMethod("POST");
//
//            connection.setRequestProperty("Connection", "Keep-Alive");
//            connection.setRequestProperty("Content-Type",
//                    "multipart/form-data; boundary=" + boundary);
//
//            outputStream = new DataOutputStream(connection.getOutputStream());
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//
//           // String token = CommonConstants.USER_ID;
//            outputStream.writeBytes("Content-Disposition: form-data; name=\"userId\"" + lineEnd);
//            outputStream.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
//            outputStream.writeBytes("Content-Length: " + token.length() + lineEnd);
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes(token + lineEnd);
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//
//            String taskId = userID;
//            outputStream.writeBytes("Content-Disposition: form-data; name=\"tabId\"" + lineEnd);
//            outputStream.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
//            outputStream.writeBytes("Content-Length: " + taskId.length() + lineEnd);
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes(taskId + lineEnd);
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//
//            String connstr = null;
//            connstr = "Content-Disposition: form-data; name=\"UploadDatabase\";filename=\""
//                    + file.getAbsolutePath() + "\"" + lineEnd;
//
//            outputStream.writeBytes(connstr);
//            outputStream.writeBytes(lineEnd);
//
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            buffer = new byte[bufferSize];
//
//            // Read file
//            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//            System.out.println("Image length " + bytesAvailable + "");
//            try {
//                while (bytesRead > 0) {
//                    try {
//                        outputStream.write(buffer, 0, bufferSize);
//                    } catch (OutOfMemoryError e) {
//                        e.printStackTrace();
//                        response = "outofmemoryerror";
//                        onComplete.execute(false, response, response);
//                        return response;
//                    }
//                    bytesAvailable = fileInputStream.available();
//                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                response = "error";
//                onComplete.execute(false, response, e.getMessage());
//                return response;
//            }
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes(twoHyphens + boundary + twoHyphens
//                    + lineEnd);
//
//            // Responses from the server (code and message)
//            int serverResponseCode = connection.getResponseCode();
//            String serverResponseMessage = connection.getResponseMessage();
//            System.out.println("Server Response Code " + " " + serverResponseCode);
//            System.out.println("Server Response Message " + serverResponseMessage);
//
//            Log.d(TAG, "uploadFileToServer: " + serverResponseMessage);
//            if (serverResponseCode == 200) {
//                response = "true";
//                onComplete.execute(true, response, response);
//            } else {
//                response = "false";
//                onComplete.execute(false, response, response);
//            }
//
//            fileInputStream.close();
//            outputStream.flush();
//
//            connection.getInputStream();
//            //for android InputStream is = connection.getInputStream();
//            java.io.InputStream is = connection.getInputStream();
//
//            int ch;
//            StringBuffer b = new StringBuffer();
//            while ((ch = is.read()) != -1) {
//                b.append((char) ch);
//            }
//
//            String responseString = b.toString();
//            System.out.println("response string is" + responseString); //Here is the actual output
//
//            outputStream.close();
//            outputStream = null;
//
//        } catch (Exception ex) {
//            // Exception handling
//            response = "error";
//            System.out.println("Send file Exception" + ex.getMessage() + "");
//            onComplete.execute(false, response, "Send file Exception" + ex.getMessage() + "");
//            ex.printStackTrace();
//        }
//        return response;
//    }
//
//
//    public LiveData<Integer> getCountComplaintDetailCount() {
//
//        return appDAO.getAddComplaintsDetailsCount();
//
//    }
//
//    public LiveData<List<SavingComplainImagesTable>> insertSavingOfComplainMultipleImages(List<SavingComplainImagesTable> savingComplainImagesTableList, String logbookno) {
//        final MutableLiveData<List<SavingComplainImagesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertSavingComplainImages(savingComplainImagesTableList);
//
//
////            for (int i = 0; i < savingComplainImagesTableList.size(); i++) {
////                data.postValue(appDAO.getComplainImageFromLocalDb(String.valueOf(savingComplainImagesTableList.size())));
//            data.postValue(appDAO.getComplainImageFromLocalDb(logbookno));
////            }
//        });
//        return data;
//
//    }
//
//    public LiveData<AddComplaintsDetailsTable> insertAddComplainFormTable(AddComplaintsDetailsTable addComplaintsDetailsTable) {
//
//        final MutableLiveData<AddComplaintsDetailsTable> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            appDAO.insertAddComplainFormTable(addComplaintsDetailsTable);
//            // TODO: Sending result
//            data.postValue(appDAO.getAddComplainFormTableData(addComplaintsDetailsTable.getLogBookNo()));
//        });
//        return data;
//    }
//
//    public LiveData<List<AddComplaintsDetailsTable>> getAddComplainFormTable() {
//
//        final MutableLiveData<List<AddComplaintsDetailsTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            data.postValue(appDAO.getAddComplainFormTableDataList());
//        });
//        return data;
//
//    }
//
//    public LiveData<List<SavingComplainImagesTable>> getComplainImagesFromLocalDBNotSync() {
//        final MutableLiveData<List<SavingComplainImagesTable>> data = new MutableLiveData<>();
//        executor.execute(() -> {
//            boolean dataExist = (appDAO.getComplainImagesFromLocalDBNotSync("0") != null);
//            if (dataExist) {
//                data.postValue(appDAO.getComplainImagesFromLocalDBNotSync("0"));
//            }
//        });
//        return data;
//    }
}


