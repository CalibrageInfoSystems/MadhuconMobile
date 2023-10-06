package com.trst01.locationtracker.activity;

import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;
import static com.trst01.locationtracker.constant.AppConstant.MESSAGE_NO_INTERNET_CONNECTION;
import static com.trst01.locationtracker.uiLibrary.helpers.AppConstants.DATE_FORMAT_YYYY_MM_DD;

import androidx.activity.result.ActivityResultLauncher;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.complains.ComplainListDetailsActivity;
import com.trst01.locationtracker.activity.complains.ComplainListsActivity;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerListActivity;
import com.trst01.locationtracker.activity.growthMonitoring.ChooseGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.growthMonitoring.ReportedDetailsGrowthMonitoringActivity;
import com.trst01.locationtracker.activity.plantation.ChoosePlantationActivity;
import com.trst01.locationtracker.activity.plantation.ViewFarmerListPlantationActivity;
import com.trst01.locationtracker.dagger.App;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CastTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.ParameterTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.PlotExistOnTable;
import com.trst01.locationtracker.database.entity.SampleSlabTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.UOMTable;
import com.trst01.locationtracker.database.entity.UsersTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WarehouseTable;
import com.trst01.locationtracker.database.entity.WeedTable;
import com.trst01.locationtracker.models.MastersResponseDTO;
import com.trst01.locationtracker.models.TransactionSyncResponseDTO;
import com.trst01.locationtracker.repositories.Retrofit_funtion_class;
import com.trst01.locationtracker.services.FaLogTracking.FalogService;
import com.trst01.locationtracker.services.api.AppAPI;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends BaseActivity  implements HasSupportFragmentInjector {

    public static final int ALL_PERMISSION_CODE = 1001;
    private static final String TAG = DashBoardActivity.class.getCanonicalName();
    EditText tilUserId, tilPassword;
    CardView btnLogin;
    private TextView tvAppVersion, tvImeiNumber,tv_db_version;
    String appVersion;
    public static String SuserId;
    public String strUserDeviceId;
    // TODO: List of all permissions
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
    };

    private static final int PERMISSIONS_REQUESTS_CODE = 2000;
    SharedPreferences prefs = null;
    ProgressDialog progressDialog;
    ActivityResultLauncher<Intent> mGetPermission;


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    String TokenAccess = "";
    ProgressBar progressBar;
    int i = 0;
    String strTodayDate;


//    TextView txtGrowth,txtKpi,txtFramer,txtPlantation;
    ImageView imgRefresh;
    CardView cardFarmer,cardPlantation,cardGrowth,cardKpi,cardComplaints;
    protected PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        /* This code together with the one in onDestroy()
         * will make the screen be always on until this Activity gets destroyed. */
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
        strTodayDate = appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD);
        Log.d(TAG, "syncFormerDetailsDataToServer: currentdate" + strTodayDate);
        prefs = getSharedPreferences("com.trst01.locationTracker", MODE_PRIVATE);
        ui();


        DashBoardActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                App.createDBPath();
            }
        });

        DashBoardActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure Dagger
                configureDagger();
            }
        });

        DashBoardActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure View Model
                configureViewModel();
            }
        });

    }

    private void ui() {
        cardComplaints = findViewById(R.id.cardComplaints);
        cardFarmer = findViewById(R.id.cardFarmer);
        cardKpi = findViewById(R.id.cardKpi);
        cardGrowth = findViewById(R.id.cardGrowth);
        cardPlantation = findViewById(R.id.cardPlantation);
        imgRefresh = findViewById(R.id.imgRefresh);

        click();
    }

    private void click() {
        cardGrowth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DashBoardActivity.this, ChooseGrowthMonitoringActivity.class);
////                Intent intent = new Intent(DashBoardActivity.this, ReportedDetailsGrowthMonitoringActivity.class);
////                Intent intent = new Intent(DashBoardActivity.this, ChooseFarmerPlotActivity.class);
//                startActivity(intent);

                Intent intent = new Intent(DashBoardActivity.this, ViewFarmerListPlantationActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
                intent.putExtra("indicator",4);
                startActivity(intent);
            }
        });

        cardFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DashBoardActivity.this, ViewFarmerActivity.class);
                Intent intent = new Intent(DashBoardActivity.this, ViewFarmerListActivity.class);
//                Intent intent = new Intent(DashBoardActivity.this, ChooseFarmerPlotActivity.class);
                startActivity(intent);
            }
        });

        cardKpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardPlantation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, ChoosePlantationActivity.class);
//                Intent intent = new Intent(DashBoardActivity.this, ChooseFarmerPlotActivity.class);
                startActivity(intent);
            }
        });

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, SettingsActivity.class);
//                Intent intent = new Intent(DashBoardActivity.this, ChooseFarmerPlotActivity.class);
                startActivity(intent);
//                viewModel.getDeleteTablesFromLocal();
//                getLoginDetailsByImeiNumber("",true);
            }
        });

        cardComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, ComplainListsActivity.class);
//                Intent intent = new Intent(DashBoardActivity.this, ChooseFarmerPlotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        if (checkAllPermissions()) {
            if (!prefs.getBoolean("firstrun", true)) {
                if (appHelper.isNetworkAvailable()) {
//                    appHelper.getSharedPrefObj().edit().remove(DeviceUserID).apply();
//                    appHelper.getSharedPrefObj().edit().remove(accessToken).apply();
//                    appHelper.getSharedPrefObj().edit().remove(DeviceUserName).apply();
//                    appHelper.getSharedPrefObj().edit().remove(DeviceUserPwd).apply();
                    App.createDBPath();
                    viewModel.getDeleteTablesFromLocal();
                     getLoginDetailsByImeiNumber("0d63f76090ec2f5a",false);

                    // TODO: 3/7/2022 testing data user deviceID
                    // getLoginDetailsByImeiNumber("6e37ab3a336a1bed");

//                    getMasterDetails(CommonUtils.getIMEInumber(DashBoardActivity.this));
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MESSAGE_NO_INTERNET_CONNECTION);
                }
            }
//            else {
//                getTodayRecordExist();
//
//            }

        }

    }

    private void getLoginDetailsByImeiNumber(String s,Boolean sync) {
        final AppAPI service = Retrofit_funtion_class.getClient().create(AppAPI.class);
        Call<MastersResponseDTO> callRetrofit = null;
        callRetrofit = service.getMasterSyncDetailsFromServer();
        // progressBar.setVisibility(View.VISIBLE);
        progressDialog = new ProgressDialog(DashBoardActivity.this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        callRetrofit.enqueue(new Callback<MastersResponseDTO>() {
            @Override
            public void onResponse(Call<MastersResponseDTO> call, Response<MastersResponseDTO> response) {
                try {
                    App.createDBPath();
                    MastersResponseDTO mastersResponseDTO = response.body();
                    String strResponse = String.valueOf(response.body());
//                    JSONArray jsonArray = new JSONArray(strResponse);

//                    Log.d(TAG, "onResponse: Json_array" + jsonArray);
//                    if (jsonArray.length() > 0) {

                        //get farmer data as well
                        if (appHelper.isNetworkAvailable()) {
//                            viewModel.getDeleteGetDataTablesFromLocal();
                            if(sync){

                            } else {
                                getSyncFarmerAllDataFromServer( );
                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MESSAGE_NO_INTERNET_CONNECTION);
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    prefs.edit().putBoolean("firstrun", true).commit();

//                                    JSONArray jsonClusterHDRArray = jsonArray.getJSONArray(0);
//                                    JSONArray jsonClusterDTLArray = jsonArray.getJSONArray(1);
//                                    JSONArray jsonStateArray = jsonArray.getJSONArray(2);
//                                    JSONArray jsonDistricArray = jsonArray.getJSONArray(3);
//                                    JSONArray jsonMandalArray = jsonArray.getJSONArray(4);
//                                    JSONArray jsonVillageArray = jsonArray.getJSONArray(5);
//                                    JSONArray jsonSeasonArray = jsonArray.getJSONArray(6);
//                                    JSONArray jsonLogBookHDrArray = jsonArray.optJSONArray(7);
//                                    JSONArray jsonLookUpDTLArray = jsonArray.getJSONArray(8);
//                                    JSONArray jsonCropListArray = jsonArray.getJSONArray(9);
//                                    JSONArray jsonCropVariety = jsonArray.getJSONArray(10);

                                    for (int i = 0; i < mastersResponseDTO.getDivision().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        DivisionTable divisionTable = new DivisionTable();
                                        divisionTable.setId(mastersResponseDTO.getDivision().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getDivision().get(i).getCode());
                                        divisionTable.setActive(mastersResponseDTO.getDivision().get(i).getActive());
                                        divisionTable.setName(mastersResponseDTO.getDivision().get(i).getName());
                                        divisionTable.setIncharge(mastersResponseDTO.getDivision().get(i).getIncharge());
                                        divisionTable.setInchargePhone(mastersResponseDTO.getDivision().get(i).getInchargePhone());
                                        divisionTable.setAddress(mastersResponseDTO.getDivision().get(i).getAddress());
                                        divisionTable.setOrd(mastersResponseDTO.getDivision().get(i).getOrd());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getDivision().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getDivision().get(i).getCreatedByUserId());
                                        divisionTable.setUpdateByUserId(mastersResponseDTO.getDivision().get(i).getUpdateByUserId());
                                        divisionTable.setUpdateDate(mastersResponseDTO.getDivision().get(i).getUpdateDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertDivisionIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getSection().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        SectionTable divisionTable = new SectionTable();
                                        divisionTable.setId(mastersResponseDTO.getSection().get(i).getId());
                                        divisionTable.setCircleId(mastersResponseDTO.getSection().get(i).getCircleId());
                                        divisionTable.setDivisionalId(mastersResponseDTO.getSection().get(i).getDivisionalId());
                                        divisionTable.setCode(mastersResponseDTO.getSection().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getSection().get(i).getName());
                                        divisionTable.setIncharge(mastersResponseDTO.getSection().get(i).getIncharge());
                                        divisionTable.setInchargePhone(mastersResponseDTO.getSection().get(i).getInchargePhone());
                                        divisionTable.setAddress(mastersResponseDTO.getSection().get(i).getAddress());
                                        divisionTable.setOrd(mastersResponseDTO.getSection().get(i).getOrd());
                                        divisionTable.setIsActive(mastersResponseDTO.getSection().get(i).getIsActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getSection().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getSection().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getSection().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getSection().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertSectionIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getCircle().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        CircleTable divisionTable = new CircleTable();
                                        divisionTable.setId(mastersResponseDTO.getCircle().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getCircle().get(i).getCode());
                                        divisionTable.setActive(mastersResponseDTO.getCircle().get(i).getActive());
                                        divisionTable.setName(mastersResponseDTO.getCircle().get(i).getName());
                                        divisionTable.setIncharge(mastersResponseDTO.getCircle().get(i).getIncharge());
                                        divisionTable.setInchargePhone(mastersResponseDTO.getCircle().get(i).getInchargePhone());
                                        divisionTable.setAddress(mastersResponseDTO.getCircle().get(i).getAddress());
                                        divisionTable.setOrd(mastersResponseDTO.getCircle().get(i).getOrd());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getCircle().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getCircle().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getCircle().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getCircle().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertCircleIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }


                                    for (int i = 0; i < mastersResponseDTO.getCrop().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        CropTable divisionTable = new CropTable();
                                        divisionTable.setId(mastersResponseDTO.getCrop().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getCrop().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getCrop().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getCrop().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getCrop().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getCrop().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getCrop().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getCrop().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertCropIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getBank().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        BankTable divisionTable = new BankTable();
                                        divisionTable.setId(mastersResponseDTO.getBank().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getBank().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getBank().get(i).getName());
                                        divisionTable.setAbbr(mastersResponseDTO.getBank().get(i).getAbbr());
                                        divisionTable.setActive(mastersResponseDTO.getBank().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getBank().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getBank().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getBank().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getBank().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertBankIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getBranch().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        BranchTable divisionTable = new BranchTable();
                                        divisionTable.setId(mastersResponseDTO.getBranch().get(i).getId());
                                        divisionTable.setBankId(mastersResponseDTO.getBranch().get(i).getBankId());
                                        divisionTable.setCode(mastersResponseDTO.getBranch().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getBranch().get(i).getName());
                                        divisionTable.setAbbr(mastersResponseDTO.getBranch().get(i).getAbbr());
                                        divisionTable.setAddress(mastersResponseDTO.getBranch().get(i).getAddress());
                                        divisionTable.setPinCode(mastersResponseDTO.getBranch().get(i).getPinCode());
                                        divisionTable.setMobile(mastersResponseDTO.getBranch().get(i).getMobile());
                                        divisionTable.setEmail(mastersResponseDTO.getBranch().get(i).getEmail());
                                        divisionTable.setIFSC(mastersResponseDTO.getBranch().get(i).getIFSC());
                                        divisionTable.setActive(mastersResponseDTO.getBranch().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getBranch().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getBranch().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getBranch().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getBranch().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertBranchIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getDisease().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        DiseaseTable divisionTable = new DiseaseTable();
                                        divisionTable.setId(mastersResponseDTO.getDisease().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getDisease().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getDisease().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getDisease().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getDisease().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getDisease().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getDisease().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getDisease().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertDiseaseIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getDistrict().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        DistrictTable divisionTable = new DistrictTable();
                                        divisionTable.setId(mastersResponseDTO.getDistrict().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getDistrict().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getDistrict().get(i).getName());
                                        divisionTable.setStateId(mastersResponseDTO.getDistrict().get(i).getStateId());
                                        divisionTable.setActive(mastersResponseDTO.getDistrict().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getDistrict().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getDistrict().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getDistrict().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getDistrict().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertDistrictIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getFertilizer().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        FertilizerTable divisionTable = new FertilizerTable();
                                        divisionTable.setId(mastersResponseDTO.getFertilizer().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getFertilizer().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getFertilizer().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getFertilizer().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getFertilizer().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getFertilizer().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getFertilizer().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getFertilizer().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertFertilizerIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getMandal().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        MandalTable divisionTable = new MandalTable();
                                        divisionTable.setId(mastersResponseDTO.getMandal().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getMandal().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getMandal().get(i).getName());
                                        divisionTable.setDistrictId(mastersResponseDTO.getMandal().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getMandal().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getMandal().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getMandal().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getMandal().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getMandal().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertMandalIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getState().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        StateTable divisionTable = new StateTable();
                                        divisionTable.setId(mastersResponseDTO.getState().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getState().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getState().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getState().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getState().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getState().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getState().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getState().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertStateIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getUsers().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        UsersTable divisionTable = new UsersTable();
                                        divisionTable.setId(mastersResponseDTO.getUsers().get(i).getId());
                                        divisionTable.setEmplId(mastersResponseDTO.getUsers().get(i).getEmplId());
                                        divisionTable.setUserName(mastersResponseDTO.getUsers().get(i).getUserName());
                                        divisionTable.setPassword(mastersResponseDTO.getUsers().get(i).getPassword());
                                        divisionTable.setFirstName(mastersResponseDTO.getUsers().get(i).getFirstName());
                                        divisionTable.setMiddleName(mastersResponseDTO.getUsers().get(i).getMiddleName());
                                        divisionTable.setLastName(mastersResponseDTO.getUsers().get(i).getLastName());
                                        divisionTable.setEmailId(mastersResponseDTO.getUsers().get(i).getEmailId());
                                        divisionTable.setMobileNumber(mastersResponseDTO.getUsers().get(i).getMobileNumber());
                                        divisionTable.setRoleld(mastersResponseDTO.getUsers().get(i).getRoleld());
                                        divisionTable.setAdmin(mastersResponseDTO.getUsers().get(i).getAdmin());
                                        divisionTable.setIPAddress(mastersResponseDTO.getUsers().get(i).getIPAddress());
                                        divisionTable.setAdminGate(mastersResponseDTO.getUsers().get(i).getAdminGate());
                                        divisionTable.setGross(mastersResponseDTO.getUsers().get(i).getGross());
                                        divisionTable.setTare(mastersResponseDTO.getUsers().get(i).getTare());
                                        divisionTable.setDumpYard(mastersResponseDTO.getUsers().get(i).getDumpYard());
                                        divisionTable.setActive(mastersResponseDTO.getUsers().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getUsers().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getUsers().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getUsers().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getUsers().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertUsersIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getParameter().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        ParameterTable divisionTable = new ParameterTable();
                                        divisionTable.setId(mastersResponseDTO.getParameter().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getParameter().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getParameter().get(i).getName());
                                        divisionTable.setDataType(mastersResponseDTO.getParameter().get(i).getDataType());
                                        divisionTable.setUIControl(mastersResponseDTO.getParameter().get(i).getUIControl());
                                        divisionTable.setDefaultQuery(mastersResponseDTO.getParameter().get(i).getDefaultQuery());
                                        divisionTable.setUIControlQuery(mastersResponseDTO.getParameter().get(i).getUIControlQuery());
                                        divisionTable.setOrder(mastersResponseDTO.getParameter().get(i).getOrder());
                                        divisionTable.setActive(mastersResponseDTO.getUsers().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getUsers().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getUsers().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getUsers().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getUsers().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertParameterIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getPest().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        PestTable divisionTable = new PestTable();
                                        divisionTable.setId(mastersResponseDTO.getPest().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getPest().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getPest().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getPest().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getPest().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getPest().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getPest().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getPest().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertPestIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getPlantType().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        PlantTypeTable divisionTable = new PlantTypeTable();
                                        divisionTable.setId(mastersResponseDTO.getPlantType().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getPlantType().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getPlantType().get(i).getName());
                                        divisionTable.setEstimatedTon(mastersResponseDTO.getPlantType().get(i).getEstimatedTon());
                                        divisionTable.setLoanEligible(mastersResponseDTO.getPlantType().get(i).getLoanEligible());
                                        divisionTable.setActive(mastersResponseDTO.getPlantType().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getPlantType().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getPlantType().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getPlantType().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getPlantType().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertPlantTypeIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getPlantSubType().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        PlantSubTypeTable divisionTable = new PlantSubTypeTable();
                                        divisionTable.setId(mastersResponseDTO.getPlantSubType().get(i).getId());
                                        divisionTable.setPlantTypeId(mastersResponseDTO.getPlantSubType().get(i).getPlantTypeId());
                                        divisionTable.setCode(mastersResponseDTO.getPlantSubType().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getPlantSubType().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getPlantSubType().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getPlantSubType().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getPlantSubType().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getPlantSubType().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getPlantSubType().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertPlantSubTypeIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getSampleSlab().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        SampleSlabTable divisionTable = new SampleSlabTable();
                                        divisionTable.setId(mastersResponseDTO.getSampleSlab().get(i).getId());
                                        divisionTable.setFromArea(mastersResponseDTO.getSampleSlab().get(i).getFromArea());
                                        divisionTable.setToArea(mastersResponseDTO.getSampleSlab().get(i).getToArea());
                                        divisionTable.setNoOfSample(mastersResponseDTO.getSampleSlab().get(i).getNoOfSample());
                                        divisionTable.setActive(mastersResponseDTO.getSampleSlab().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getSampleSlab().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getSampleSlab().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getSampleSlab().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getSampleSlab().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertSampleSlabIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getSeason().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        SeasonTable divisionTable = new SeasonTable();
                                        divisionTable.setId(mastersResponseDTO.getSeason().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getSeason().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getSeason().get(i).getName());
                                        divisionTable.setPlantFrom(mastersResponseDTO.getSeason().get(i).getPlantFrom());
                                        divisionTable.setPlantTo(mastersResponseDTO.getSeason().get(i).getPlantTo());
                                        divisionTable.setCrushFrom(mastersResponseDTO.getSeason().get(i).getCrushFrom());
                                        divisionTable.setCrushTo(mastersResponseDTO.getSeason().get(i).getCrushTo());
                                        divisionTable.setBurnCaneRate(mastersResponseDTO.getSeason().get(i).getBurnCaneRate());
                                        divisionTable.setCaneRate(mastersResponseDTO.getSeason().get(i).getCaneRate());
                                        divisionTable.setCapacity(mastersResponseDTO.getSeason().get(i).getCapacity());
                                        divisionTable.setActive(mastersResponseDTO.getSeason().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getSeason().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getSeason().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getSeason().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getSeason().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertSeasonIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getUom().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        UOMTable divisionTable = new UOMTable();
                                        divisionTable.setId(mastersResponseDTO.getUom().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getUom().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getUom().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getUom().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getUom().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getUom().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getUom().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getUom().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertUOMIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getCast().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        CastTable divisionTable = new CastTable();
                                        divisionTable.setId(mastersResponseDTO.getCast().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getCast().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getCast().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getCast().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getCast().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getCast().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getCast().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getCast().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertCastIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getLookUpHDR().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        LookupHDRTable divisionTable = new LookupHDRTable();
                                        divisionTable.setId(mastersResponseDTO.getLookUpHDR().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getLookUpHDR().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getLookUpHDR().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getLookUpHDR().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getLookUpHDR().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getLookUpHDR().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getLookUpHDR().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getLookUpHDR().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertLookupHdrIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getLookupDtl().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        LookupDtlTable divisionTable = new LookupDtlTable();
                                        divisionTable.setId(mastersResponseDTO.getLookupDtl().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getLookupDtl().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getLookupDtl().get(i).getName());
                                        divisionTable.setRemarks(mastersResponseDTO.getLookupDtl().get(i).getRemarks());
                                        divisionTable.setOrd(mastersResponseDTO.getLookupDtl().get(i).getOrd());
                                        divisionTable.setActive(mastersResponseDTO.getLookupDtl().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getLookupDtl().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getLookupDtl().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getLookupDtl().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getLookupDtl().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertLookupDtlIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getPlotExistOn().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        PlotExistOnTable divisionTable = new PlotExistOnTable();
                                        divisionTable.setId(mastersResponseDTO.getPlotExistOn().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getPlotExistOn().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getPlotExistOn().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getPlotExistOn().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getPlotExistOn().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getPlotExistOn().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getPlotExistOn().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getPlotExistOn().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertPlotExistOnTableIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getVariety().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        VarietyTable divisionTable = new VarietyTable();
                                        divisionTable.setId(mastersResponseDTO.getVariety().get(i).getId());
                                        divisionTable.setVarietyId(mastersResponseDTO.getVariety().get(i).getVarietyId());
                                        divisionTable.setCode(mastersResponseDTO.getVariety().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getVariety().get(i).getName());
                                        divisionTable.setPlantAge(mastersResponseDTO.getVariety().get(i).getPlantAge());
                                        divisionTable.setRatoonAge(mastersResponseDTO.getVariety().get(i).getRatoonAge());
                                        divisionTable.setSugarContent(mastersResponseDTO.getVariety().get(i).getSugarContent());
                                        divisionTable.setPlantSuitability(mastersResponseDTO.getVariety().get(i).getPlantSuitability());
                                        divisionTable.setActive(mastersResponseDTO.getVariety().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getVariety().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getVariety().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getVariety().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getVariety().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertVarietyIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getWarehouse().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        WarehouseTable divisionTable = new WarehouseTable();
                                        divisionTable.setId(mastersResponseDTO.getWarehouse().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getWarehouse().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getWarehouse().get(i).getName());
                                        divisionTable.setGLCode(mastersResponseDTO.getWarehouse().get(i).getGLCode());
                                        divisionTable.setSubGLCode(mastersResponseDTO.getWarehouse().get(i).getSubGLCode());
                                        divisionTable.setActive(mastersResponseDTO.getWarehouse().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getWarehouse().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getWarehouse().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getWarehouse().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getWarehouse().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertWarehouseIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getWeed().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        WeedTable divisionTable = new WeedTable();
                                        divisionTable.setId(mastersResponseDTO.getWeed().get(i).getId());
                                        divisionTable.setCode(mastersResponseDTO.getWeed().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getWeed().get(i).getName());
                                        divisionTable.setActive(mastersResponseDTO.getWeed().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getWeed().get(i).getCreatedDate());
                                        divisionTable.setCreatedByUserId(mastersResponseDTO.getWeed().get(i).getCreatedByUserId());
                                        divisionTable.setUpdatedByUserId(mastersResponseDTO.getWeed().get(i).getUpdatedByUserId());
                                        divisionTable.setUpdatedDate(mastersResponseDTO.getWeed().get(i).getUpdatedDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertWeedIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }

                                    for (int i = 0; i < mastersResponseDTO.getVillage().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                        VillageTable divisionTable = new VillageTable();
                                        divisionTable.setId(mastersResponseDTO.getVillage().get(i).getId());
                                        divisionTable.setSectionName(mastersResponseDTO.getVillage().get(i).getSectionName());
                                        divisionTable.setMandalName(mastersResponseDTO.getVillage().get(i).getMandalName());
                                        divisionTable.setMandalId(mastersResponseDTO.getVillage().get(i).getMandalId());
                                        divisionTable.setDistrictId(mastersResponseDTO.getVillage().get(i).getDistrictId());
                                        divisionTable.setDistrictName(mastersResponseDTO.getVillage().get(i).getDistrictName());
                                        divisionTable.setDivisonId(mastersResponseDTO.getVillage().get(i).getDivisonId());
                                        divisionTable.setDivisonName(mastersResponseDTO.getVillage().get(i).getDivisonName());
                                        divisionTable.setCircleId(mastersResponseDTO.getVillage().get(i).getCircleId());
                                        divisionTable.setCircleName(mastersResponseDTO.getVillage().get(i).getCircleName());
                                        divisionTable.setSectionId(mastersResponseDTO.getVillage().get(i).getSectionId());

                                        divisionTable.setCode(mastersResponseDTO.getVillage().get(i).getCode());
                                        divisionTable.setName(mastersResponseDTO.getVillage().get(i).getName());

                                        divisionTable.setIncharge(mastersResponseDTO.getVillage().get(i).getIncharge());
                                        divisionTable.setInchargePhone(mastersResponseDTO.getVillage().get(i).getInchargePhone());
                                        divisionTable.setAddress(mastersResponseDTO.getVillage().get(i).getAddress());
                                        divisionTable.setPincode(mastersResponseDTO.getVillage().get(i).getPincode());
                                        divisionTable.setDistance(mastersResponseDTO.getVillage().get(i).getDistance());
                                        divisionTable.setTPTRate(mastersResponseDTO.getVillage().get(i).getTPTRate());
                                        divisionTable.setDivertedDistance(mastersResponseDTO.getVillage().get(i).getDivertedDistance());
                                        divisionTable.setPotentialArea(mastersResponseDTO.getVillage().get(i).getPotentialArea());
                                        divisionTable.setGeoArea(mastersResponseDTO.getVillage().get(i).getGeoArea());
                                        divisionTable.setDryArea(mastersResponseDTO.getVillage().get(i).getDryArea());
                                        divisionTable.setNotSuitableArea(mastersResponseDTO.getVillage().get(i).getNotSuitableArea());
                                        divisionTable.setIrrigationArea(mastersResponseDTO.getVillage().get(i).getIrrigationArea());
                                        divisionTable.setColtivableArea(mastersResponseDTO.getVillage().get(i).getColtivableArea());
                                        divisionTable.setNoOfEBServices(mastersResponseDTO.getVillage().get(i).getNoOfEBServices());
                                        divisionTable.setOrd(mastersResponseDTO.getVillage().get(i).getOrd());

                                        divisionTable.setActive(mastersResponseDTO.getVillage().get(i).getActive());
                                        divisionTable.setCreatedDate(mastersResponseDTO.getVillage().get(i).getCreatedDate());
                                        divisionTable.setCreateByUserId(mastersResponseDTO.getVillage().get(i).getCreateByUserId());
                                        divisionTable.setUpdatedUserId(mastersResponseDTO.getVillage().get(i).getUpdatedUserId());
                                        divisionTable.setUpdateDate(mastersResponseDTO.getVillage().get(i).getUpdateDate());
                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                        viewModel.insertVillageIntoLocalDBQuery(divisionTable);
                                        //getClusterHDRList.add(clusterHDr_value);
                                    }


//                                    for (int clusterDTL = 0; clusterDTL < jsonClusterDTLArray.length(); clusterDTL++) {
//                                        JSONObject jsonObjectclusterDTL = jsonClusterDTLArray.getJSONObject(clusterDTL);
//                                        ClusterDTLTable clusterDTL_value = new ClusterDTLTable();
//                                        clusterDTL_value.setId(jsonObjectclusterDTL.getString("Id"));
//                                        clusterDTL_value.setClusterHDRId(jsonObjectclusterDTL.getString("ClusterHDRId"));
//                                        clusterDTL_value.setVillageId(jsonObjectclusterDTL.getString("VillageId"));
//
//                                        clusterDTL_value.setCreatedDate(jsonObjectclusterDTL.getString("CreatedDate"));
//                                        clusterDTL_value.setUpdatedDate(jsonObjectclusterDTL.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectClusterHDR.getString("IsActive"));
//                                        clusterDTL_value.setIsActive("1");
//                                        clusterDTL_value.setCreatedByUserId(jsonObjectclusterDTL.getString("CreatedByUserId"));
//                                        clusterDTL_value.setUpdatedByUserId(jsonObjectclusterDTL.getString("UpdatedByUserId"));
//                                        //  insertClusterIdValuesIntoLocalDB(clusterDTL_value);
//                                        viewModel.insertClusterDTLListDetailIntoLocalDBQuery(clusterDTL_value);
//                                        //ClusterDTLList.add(clusterDTL_value);
////                        }
//                                    }
//                                    for (int distric = 0; distric < jsonDistricArray.length(); distric++) {
//                                        JSONObject jsonObjectDistric = jsonDistricArray.getJSONObject(distric);
//                                        // JSONObject json_Object = json_arry.getJSONObject(27);
//                                        Log.d(TAG, "onResponse:Distric " + jsonObjectDistric);
//                                        DistrictTable distric_value = new DistrictTable();
//                                        distric_value.setId(jsonObjectDistric.getString("Id"));
//                                        distric_value.setCode(jsonObjectDistric.getString("Code"));
//                                        distric_value.setName(jsonObjectDistric.getString("Name"));
//                                        distric_value.setStateId(jsonObjectDistric.getString("StateId"));
//
//                                        distric_value.setCreatedDate(jsonObjectDistric.getString("CreatedDate"));
//                                        distric_value.setUpdatedDate(jsonObjectDistric.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectDistric.getString("IsActive"));
//                                        distric_value.setIsActive("1");
//                                        distric_value.setCreatedByUserId(jsonObjectDistric.getString("CreatedByUserId"));
//                                        distric_value.setUpdatedByUserId(jsonObjectDistric.getString("UpdatedByUserId"));
//                                        // insertDistricValuesIntoLocalDB(distric_value);
//                                        viewModel.insertDistricListDetailIntoLocalDBQuery(distric_value);
//                                        // DistricList.add(distric_value);
//                                    }
//                                    for (int state = 0; state < jsonStateArray.length(); state++) {
//                                        JSONObject jsonObjectState = jsonStateArray.getJSONObject(state);
//                                        // JSONObject json_Object = json_arry.getJSONObject(27);
//                                        Log.d(TAG, "onResponse:state " + jsonObjectState);
//                                        StatesTable state_value = new StatesTable();
//                                        state_value.setId(jsonObjectState.getString("Id"));
//                                        state_value.setCode(jsonObjectState.getString("Code"));
//                                        state_value.setName(jsonObjectState.getString("Name"));
//                                        //  insertStateValuesIntoLocalDB(state_value);
//                                        state_value.setCreatedDate(jsonObjectState.getString("CreatedDate"));
//                                        state_value.setUpdatedDate(jsonObjectState.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectDistric.getString("IsActive"));
//                                        state_value.setIsActive("1");
//                                        state_value.setCreatedByUserId(jsonObjectState.getString("CreatedByUserId"));
//                                        state_value.setUpdatedByUserId(jsonObjectState.getString("UpdatedByUserId"));
//                                        viewModel.insertStateListDetailIntoLocalDBQuery(state_value);
//                                    }
//                                    for (int mandal = 0; mandal < jsonMandalArray.length(); mandal++) {
//                                        JSONObject jsonObjectMandal = jsonMandalArray.getJSONObject(mandal);
//                                        // JSONObject json_Object = json_arry.getJSONObject(27);
//                                        Log.d(TAG, "onResponse:mandal " + jsonObjectMandal);
//                                        MandalTable mandal_value = new MandalTable();
//                                        mandal_value.setId(jsonObjectMandal.getString("Id"));
//                                        mandal_value.setCode(jsonObjectMandal.getString("Code"));
//                                        mandal_value.setName(jsonObjectMandal.getString("Name"));
//                                        mandal_value.setDistrictId(jsonObjectMandal.getString("DistrictId"));
//
//                                        mandal_value.setCreatedDate(jsonObjectMandal.getString("CreatedDate"));
//                                        mandal_value.setUpdatedDate(jsonObjectMandal.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectDistric.getString("IsActive"));
//                                        mandal_value.setIsActive("1");
//                                        mandal_value.setCreatedByUserId(jsonObjectMandal.getString("CreatedByUserId"));
//                                        mandal_value.setUpdatedByUserId(jsonObjectMandal.getString("UpdatedByUserId"));
//
//                                        viewModel.insertMandalListDetailIntoLocalDBQuery(mandal_value);
//                                    }
//                                    for (int village = 0; village < jsonVillageArray.length(); village++) {
//                                        JSONObject jsonObjectVillage = jsonVillageArray.getJSONObject(village);
//                                        // JSONObject json_Object = json_arry.getJSONObject(27);
//                                        Log.d(TAG, "onResponse: village" + jsonObjectVillage);
//                                        VillageTable village_value = new VillageTable();
//                                        village_value.setId(jsonObjectVillage.getString("Id"));
//                                        village_value.setCode(jsonObjectVillage.getString("Code"));
//                                        village_value.setName(jsonObjectVillage.getString("Name"));
//                                        village_value.setMandalId(jsonObjectVillage.getString("MandalId"));
//                                        village_value.setPinCode(jsonObjectVillage.getString("PinCode"));
//
//                                        village_value.setCreatedDate(jsonObjectVillage.getString("CreatedDate"));
//                                        village_value.setUpdatedDate(jsonObjectVillage.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectDistric.getString("IsActive"));
//                                        village_value.setIsActive("1");
//                                        village_value.setCreatedByUserId(jsonObjectVillage.getString("CreatedByUserId"));
//                                        village_value.setUpdatedByUserId(jsonObjectVillage.getString("UpdatedByUserId"));
//
//                                        viewModel.insertVillageListDetailIntoLocalDBQuery(village_value);
//                                    }
//
//                                    for (int season = 0; season < jsonSeasonArray.length(); season++) {
//                                        JSONObject jsonObjectSeason = jsonSeasonArray.getJSONObject(season);
//                                        SeasonTable season_value = new SeasonTable();
//                                        season_value.setId(jsonObjectSeason.getString("Id"));
//                                        season_value.setCode(jsonObjectSeason.getString("Code"));
//                                        season_value.setName(jsonObjectSeason.getString("Name"));
//                                        season_value.setPlantFrom(jsonObjectSeason.getString("PlantFrom"));
//                                        season_value.setPlantTo(jsonObjectSeason.getString("PlantTo"));
//                                        season_value.setCreatedDate(jsonObjectSeason.getString("CreatedDate"));
//                                        season_value.setUpdatedDate(jsonObjectSeason.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectClusterHDR.getString("IsActive"));
//                                        season_value.setIsActive("1");
//                                        season_value.setCreatedByUserId(jsonObjectSeason.getString("CreatedByUserId"));
//                                        season_value.setUpdatedByUserId(jsonObjectSeason.getString("UpdatedByUserId"));
//                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
//                                        viewModel.insertSeasonDetailIntoLocalDBQuery(season_value);
//                                        //getClusterHDRList.add(clusterHDr_value);
//                                    }
//
//                                    for (int lookUPHDR = 0; lookUPHDR < jsonLogBookHDrArray.length(); lookUPHDR++) {
//                                        JSONObject jsonObjectlookUPHDR = jsonLogBookHDrArray.getJSONObject(lookUPHDR);
//                                        LogBookDropDownHDRTable lookUPHDR_value = new LogBookDropDownHDRTable();
//                                        lookUPHDR_value.setId(jsonObjectlookUPHDR.optString("Id"));
//                                        lookUPHDR_value.setCode(jsonObjectlookUPHDR.optString("Code"));
//                                        lookUPHDR_value.setName(jsonObjectlookUPHDR.getString("Name"));
//
//                                        lookUPHDR_value.setCreatedDate(jsonObjectlookUPHDR.getString("CreatedDate"));
//                                        lookUPHDR_value.setUpdatedDate(jsonObjectlookUPHDR.getString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectClusterHDR.getString("IsActive"));
//                                        lookUPHDR_value.setIsActive("1");
//                                        lookUPHDR_value.setCreatedByUserId(jsonObjectlookUPHDR.getString("CreatedByUserId"));
//                                        lookUPHDR_value.setUpdatedByUserId(jsonObjectlookUPHDR.getString("UpdatedByUserId"));
//                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
//                                        viewModel.insertLogBookDropDownHDRDetailIntoLocalDBQuery(lookUPHDR_value);
//                                        //getClusterHDRList.add(clusterHDr_value);
//                                    }
//
//                                    for (int lookUpData = 0; lookUpData < jsonLookUpDTLArray.length(); lookUpData++) {
//                                        JSONObject jsonObjectlookUpData = jsonLookUpDTLArray.getJSONObject(lookUpData);
//                                        LookUpDropDownDataTable lookUpData_value = new LookUpDropDownDataTable();
//                                        lookUpData_value.setId(jsonObjectlookUpData.getString("Id"));
//                                        lookUpData_value.setLookupHdrId(jsonObjectlookUpData.optString("LookupHdrId"));
//                                        lookUpData_value.setCode(jsonObjectlookUpData.optString("Code"));
//                                        lookUpData_value.setName(jsonObjectlookUpData.optString("Name"));
//                                        lookUpData_value.setRemarks(jsonObjectlookUpData.optString("Remarks"));
//                                        lookUpData_value.setOrd(jsonObjectlookUpData.optString("Ord"));
//                                        lookUpData_value.setCreatedDate(jsonObjectlookUpData.optString("CreatedDate"));
//                                        lookUpData_value.setUpdatedDate(jsonObjectlookUpData.optString("UpdatedDate"));
//                                        //  clusterHDr_value.setIsActive(jsonObjectClusterHDR.getString("IsActive"));
//                                        lookUpData_value.setIsActive("1");
//                                        lookUpData_value.setCreatedByUserId(jsonObjectlookUpData.getString("CreatedByUserId"));
//                                        lookUpData_value.setUpdatedByUserId(jsonObjectlookUpData.getString("UpdatedByUserId"));
//                                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
//                                        viewModel.insertLookUpDropDownDataDetailIntoLocalDBQuery(lookUpData_value);
//                                        //getClusterHDRList.add(clusterHDr_value);
//                                    }
//
//                                    for (int crop = 0; crop < jsonCropListArray.length(); crop++) {
//                                        JSONObject jsonObjectCropData = jsonCropListArray.getJSONObject(crop);
//                                        CropListTable cropListTable = new CropListTable();
//                                        cropListTable.setId(jsonObjectCropData.getString("Id"));
//                                        cropListTable.setCode(jsonObjectCropData.optString("Code"));
//                                        cropListTable.setName(jsonObjectCropData.optString("Name"));
//                                        cropListTable.setCreatedDate(jsonObjectCropData.optString("CreatedDate"));
//                                        cropListTable.setUpdatedDate(jsonObjectCropData.optString("UpdatedDate"));
//                                        cropListTable.setIsActive("1");
//                                        cropListTable.setCreatedByUserId(jsonObjectCropData.getString("CreatedByUserId"));
//                                        cropListTable.setUpdatedByUserId(jsonObjectCropData.getString("UpdatedByUserId"));
////                                        cropListTable.setSeason(jsonObjectCropData.getString("Season"));
//
//                                        viewModel.insertCropListDetailIntoLocalDBQuery(cropListTable);
//                                    }
//
//                                    for (int cropVariety = 0; cropVariety < jsonCropVariety.length(); cropVariety++) {
//                                        JSONObject jsonObjectCropVarietyData = jsonCropVariety.getJSONObject(cropVariety);
//                                        CropVarietyListTable cropVarietyListTable = new CropVarietyListTable();
//                                        cropVarietyListTable.setId(jsonObjectCropVarietyData.getString("Id"));
//                                        cropVarietyListTable.setCode(jsonObjectCropVarietyData.optString("Code"));
//                                        cropVarietyListTable.setName(jsonObjectCropVarietyData.optString("Name"));
//                                        cropVarietyListTable.setCropId(jsonObjectCropVarietyData.optInt("CropId"));
//                                        cropVarietyListTable.setCreatedDate(jsonObjectCropVarietyData.optString("CreatedDate"));
//                                        cropVarietyListTable.setUpdatedDate(jsonObjectCropVarietyData.optString("UpdatedDate"));
//                                        cropVarietyListTable.setIsActive("1");
//                                        cropVarietyListTable.setCreatedByUserId(jsonObjectCropVarietyData.getString("CreatedByUserId"));
//                                        cropVarietyListTable.setUpdatedByUserId(jsonObjectCropVarietyData.getString("UpdatedByUserId"));
//                                        viewModel.insertCropVarietyListDetailIntoLocalDBQuery(cropVarietyListTable);
//                                    }
                                    progressDialog.dismiss();
                                    Toast.makeText(DashBoardActivity.this, "Master Sync Successfully", Toast.LENGTH_LONG).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    Log.d("Error", ">>>>" + ex.toString());
                                }
                            }
                        }, 20000);
//                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.d("Error", ">>>>" + ex.toString());
                }
            }

            @Override
            public void onFailure(Call<MastersResponseDTO> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();
                Log.d("Error Call", ">>>>" + call.toString());
                Log.d("Error", ">>>>" + t.toString());

            }
        });
    }

    // TODO: 2/25/2022 get data from server
    public void getSyncFarmerAllDataFromServer() {
        final AppAPI service = Retrofit_funtion_class.getClient().create(AppAPI.class);
        Call<TransactionSyncResponseDTO> callRetrofit = null;
        callRetrofit = service.getTransactionDetails(appHelper.getSharedPrefObj().getString(DeviceUserID,""),"2022-23");
//        callRetrofit = service.getTransactionDetails(CommonUtils.getIMEInumber(DashBoardActivity.this), appHelper.getSharedPrefObj().getString(accessToken, ""));
        final ProgressDialog progressDialog = new ProgressDialog(DashBoardActivity.this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching All Data From Server please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        callRetrofit.enqueue(new Callback<TransactionSyncResponseDTO>() {
            @Override
            public void onResponse(Call<TransactionSyncResponseDTO> call, Response<TransactionSyncResponseDTO> response) {
                try {
                    String strResponse = String.valueOf(response.body());
                    TransactionSyncResponseDTO transactionSyncResponseDTO = response.body();


                    for (int i = 0; i < transactionSyncResponseDTO.getFarmer().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddFarmerTable divisionTable = new AddFarmerTable();
                        divisionTable.setId(transactionSyncResponseDTO.getFarmer().get(i).getId());
                        divisionTable.setCode(transactionSyncResponseDTO.getFarmer().get(i).getCode());
                        divisionTable.setAliasName(transactionSyncResponseDTO.getFarmer().get(i).getAliasName());
                        divisionTable.setName(transactionSyncResponseDTO.getFarmer().get(i).getName());
                        divisionTable.setGender(transactionSyncResponseDTO.getFarmer().get(i).getGender());
                        divisionTable.setFatherName(transactionSyncResponseDTO.getFarmer().get(i).getFatherName());
                        divisionTable.setCastId(transactionSyncResponseDTO.getFarmer().get(i).getCastId());
                        divisionTable.setAddress(transactionSyncResponseDTO.getFarmer().get(i).getAddress());
                        divisionTable.setVillageId(transactionSyncResponseDTO.getFarmer().get(i).getVillageId());
                        divisionTable.setMobile(transactionSyncResponseDTO.getFarmer().get(i).getMobile());
                        divisionTable.setEmail(transactionSyncResponseDTO.getFarmer().get(i).getEmail());
                        divisionTable.setPanNo(transactionSyncResponseDTO.getFarmer().get(i).getPanNo());
                        divisionTable.setAadharNo(transactionSyncResponseDTO.getFarmer().get(i).getAadharNo());
                        divisionTable.setOldCode(transactionSyncResponseDTO.getFarmer().get(i).getOldCode());
                        divisionTable.setJFNo(transactionSyncResponseDTO.getFarmer().get(i).getJFNo());
                        divisionTable.setBranchId(transactionSyncResponseDTO.getFarmer().get(i).getBranchId());
                        divisionTable.setACNo(transactionSyncResponseDTO.getFarmer().get(i).getACNo());
                        divisionTable.setTotalArea(transactionSyncResponseDTO.getFarmer().get(i).getTotalArea());
                        divisionTable.setCultivatedArea(transactionSyncResponseDTO.getFarmer().get(i).getCultivatedArea());
                        divisionTable.setGLCode(transactionSyncResponseDTO.getFarmer().get(i).getGLCode());
                        divisionTable.setSubGLCode(transactionSyncResponseDTO.getFarmer().get(i).getSubGLCode());
                        divisionTable.setOtherCode(transactionSyncResponseDTO.getFarmer().get(i).getOtherCode());
                        divisionTable.setImageUrl(transactionSyncResponseDTO.getFarmer().get(i).getImageUrl());
                        divisionTable.setRegistered(transactionSyncResponseDTO.getFarmer().get(i).getRegistered());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getFarmer().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getFarmer().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getFarmer().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getFarmer().get(i).getUpdatedDate());
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertFarmerIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getPlot().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddPlotTable divisionTable = new AddPlotTable();
                        divisionTable.setId(transactionSyncResponseDTO.getPlot().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getPlot().get(i).getSeasonCode());
                        divisionTable.setCropTypeId(transactionSyncResponseDTO.getPlot().get(i).getCropTypeId());
                        divisionTable.setOfferedNo(transactionSyncResponseDTO.getPlot().get(i).getOfferedNo());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getPlot().get(i).getFarmerCode());
                        divisionTable.setFatherName(transactionSyncResponseDTO.getPlot().get(i).getFatherName());
                        divisionTable.setRelationShipTypeId(transactionSyncResponseDTO.getPlot().get(i).getRelationShipTypeId());
                        divisionTable.setNominee(transactionSyncResponseDTO.getPlot().get(i).getNominee());
                        divisionTable.setGuarantor1(transactionSyncResponseDTO.getPlot().get(i).getGuarantor1());
                        divisionTable.setGuarantor2(transactionSyncResponseDTO.getPlot().get(i).getGuarantor2());
                        divisionTable.setGuarantor3(transactionSyncResponseDTO.getPlot().get(i).getGuarantor3());
                        divisionTable.setFarmerVillageId(transactionSyncResponseDTO.getPlot().get(i).getFarmerVillageId());
                        divisionTable.setPlotVillageId(transactionSyncResponseDTO.getPlot().get(i).getPlotVillageId());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getPlot().get(i).getPlotNo());
                        divisionTable.setPlantingDate(transactionSyncResponseDTO.getPlot().get(i).getPlantingDate());
                        divisionTable.setPlotTypeId(transactionSyncResponseDTO.getPlot().get(i).getPlotTypeId());
                        divisionTable.setPlantTypeId(transactionSyncResponseDTO.getPlot().get(i).getPlantTypeId());
                        divisionTable.setPlantSubTypeId(transactionSyncResponseDTO.getPlot().get(i).getPlantSubTypeId());
                        divisionTable.setVarietyId(transactionSyncResponseDTO.getPlot().get(i).getVarietyId());
                        divisionTable.setSurveyNo(transactionSyncResponseDTO.getPlot().get(i).getSurveyNo());
                        divisionTable.setFieldName(transactionSyncResponseDTO.getPlot().get(i).getFieldName());
                        divisionTable.setBIRNo(transactionSyncResponseDTO.getPlot().get(i).getBIRNo());
                        divisionTable.setBIRDate(transactionSyncResponseDTO.getPlot().get(i).getBIRDate());
                        divisionTable.setTotalArea(transactionSyncResponseDTO.getPlot().get(i).getTotalArea());
                        divisionTable.setCultivatedArea(transactionSyncResponseDTO.getPlot().get(i).getCultivatedArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getPlot().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getPlot().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getPlot().get(i).getMeasureArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getPlot().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getPlot().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getPlot().get(i).getMeasureArea());
                        divisionTable.setAggrementedArea(transactionSyncResponseDTO.getPlot().get(i).getAggrementedArea());
                        divisionTable.setNetArea(transactionSyncResponseDTO.getPlot().get(i).getNetArea());
                        divisionTable.setAgreedTon(transactionSyncResponseDTO.getPlot().get(i).getAgreedTon());
                        divisionTable.setEstimatedTon(transactionSyncResponseDTO.getPlot().get(i).getEstimatedTon());
                        divisionTable.setIrrigationTypeId(transactionSyncResponseDTO.getPlot().get(i).getIrrigationTypeId());
                        divisionTable.setSoilTypeId(transactionSyncResponseDTO.getPlot().get(i).getSoilTypeId());
                        divisionTable.setSpacingId(transactionSyncResponseDTO.getPlot().get(i).getSpacingId());
                        divisionTable.setSettsTypeId(transactionSyncResponseDTO.getPlot().get(i).getSettsTypeId());
                        divisionTable.setPreviousCropId(transactionSyncResponseDTO.getPlot().get(i).getPreviousCropId());
                        divisionTable.setInterCropId(transactionSyncResponseDTO.getPlot().get(i).getInterCropId());
                        divisionTable.setSeedMaterialUsedId(transactionSyncResponseDTO.getPlot().get(i).getSeedMaterialUsedId());
                        divisionTable.setPlotExistOnId(transactionSyncResponseDTO.getPlot().get(i).getPlotExistOnId());
                        divisionTable.setDistanceFromPlot(transactionSyncResponseDTO.getPlot().get(i).getDistanceFromPlot());
                        divisionTable.setProfile(transactionSyncResponseDTO.getPlot().get(i).getProfile());
                        divisionTable.setIsSettsHotWaterTreatment(transactionSyncResponseDTO.getPlot().get(i).getIsSettsHotWaterTreatment());
                        divisionTable.setIsPreviousRedPlot(transactionSyncResponseDTO.getPlot().get(i).getIsPreviousRedPlot());
                        divisionTable.setIsDustApplied(transactionSyncResponseDTO.getPlot().get(i).getIsDustApplied());
                        divisionTable.setIsBasalFertilization(transactionSyncResponseDTO.getPlot().get(i).getIsBasalFertilization());
                        divisionTable.setIsTrashMulching(transactionSyncResponseDTO.getPlot().get(i).getIsTrashMulching());
                        divisionTable.setIsCompositeFormYard(transactionSyncResponseDTO.getPlot().get(i).getIsCompositeFormYard());
                        divisionTable.setIsFilterPressMud(transactionSyncResponseDTO.getPlot().get(i).getIsFilterPressMud());
                        divisionTable.setIsGreenManures(transactionSyncResponseDTO.getPlot().get(i).getIsGreenManures());
                        divisionTable.setIsMicronutrientDeficiency(transactionSyncResponseDTO.getPlot().get(i).getIsMicronutrientDeficiency());
                        divisionTable.setIsGapsFilled(transactionSyncResponseDTO.getPlot().get(i).getIsGapsFilled());
                        divisionTable.setInspectionDate(transactionSyncResponseDTO.getPlot().get(i).getInspectionDate());
                        divisionTable.setWeedStatusId(transactionSyncResponseDTO.getPlot().get(i).getWeedStatusId());
                        divisionTable.setActionPlanId(transactionSyncResponseDTO.getPlot().get(i).getActionPlanId());
                        divisionTable.setPerishableReasonId(transactionSyncResponseDTO.getPlot().get(i).getPerishableReasonId());
                        divisionTable.setPerishedArea(transactionSyncResponseDTO.getPlot().get(i).getPerishedArea());
                        divisionTable.setNotGrownArea(transactionSyncResponseDTO.getPlot().get(i).getNotGrownArea());
                        divisionTable.setHarvestingArea(transactionSyncResponseDTO.getPlot().get(i).getHarvestingArea());
                        divisionTable.setPoorCropArea(transactionSyncResponseDTO.getPlot().get(i).getPoorCropArea());
                        divisionTable.setRemovedArea(transactionSyncResponseDTO.getPlot().get(i).getRemovedArea());
                        divisionTable.setBioFertilizerAppliedArea(transactionSyncResponseDTO.getPlot().get(i).getBioFertilizerAppliedArea());
                        divisionTable.setDeTrashingArea(transactionSyncResponseDTO.getPlot().get(i).getDeTrashingArea());
                        divisionTable.setDeepPloughedArea(transactionSyncResponseDTO.getPlot().get(i).getDeepPloughedArea());
                        divisionTable.setEarthingUpArea(transactionSyncResponseDTO.getPlot().get(i).getEarthingUpArea());
                        divisionTable.setRatoonManagedUsedArea(transactionSyncResponseDTO.getPlot().get(i).getRatoonManagedUsedArea());
                        divisionTable.setTrashShedderArea(transactionSyncResponseDTO.getPlot().get(i).getTrashShedderArea());
                        divisionTable.setLoadShedderArea(transactionSyncResponseDTO.getPlot().get(i).getLoadShedderArea());
                        divisionTable.setIsSeedArea(transactionSyncResponseDTO.getPlot().get(i).getIsSeedArea());
                        divisionTable.setDivertToSelfSeed(transactionSyncResponseDTO.getPlot().get(i).getDivertToSelfSeed());
                        divisionTable.setDivertToOthers(transactionSyncResponseDTO.getPlot().get(i).getDivertToOthers());
                        divisionTable.setSchGroupNo(transactionSyncResponseDTO.getPlot().get(i).getSchGroupNo());
                        divisionTable.setBrix(transactionSyncResponseDTO.getPlot().get(i).getBrix());
                        divisionTable.setPol(transactionSyncResponseDTO.getPlot().get(i).getPol());
                        divisionTable.setPurity(transactionSyncResponseDTO.getPlot().get(i).getPurity());
                        divisionTable.setCCS(transactionSyncResponseDTO.getPlot().get(i).getCCS());
                        divisionTable.setNoOfSamples(transactionSyncResponseDTO.getPlot().get(i).getNoOfSamples());
                        divisionTable.setSampleDate(transactionSyncResponseDTO.getPlot().get(i).getSampleDate());
                        divisionTable.setCuttingOrderNo(transactionSyncResponseDTO.getPlot().get(i).getCuttingOrderNo());
                        divisionTable.setProppingArea(transactionSyncResponseDTO.getPlot().get(i).getProppingArea());
                        divisionTable.setProppingStageId(transactionSyncResponseDTO.getPlot().get(i).getProppingStageId());
                        divisionTable.setTransferArea(transactionSyncResponseDTO.getPlot().get(i).getTransferArea());
                        divisionTable.setIsRegistered(transactionSyncResponseDTO.getPlot().get(i).getIsRegistered());
                        divisionTable.setIsOver(transactionSyncResponseDTO.getPlot().get(i).getIsOver());
                        divisionTable.setPlotOverReasonId(transactionSyncResponseDTO.getPlot().get(i).getPlotOverReasonId());
                        divisionTable.setPlotOverDate(transactionSyncResponseDTO.getPlot().get(i).getPlotOverDate());
                        divisionTable.setIsActive(transactionSyncResponseDTO.getPlot().get(i).getIsActive());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getPlot().get(i).getPlantingMethodId());
                        divisionTable.setStage(transactionSyncResponseDTO.getPlot().get(i).getStage());
                        divisionTable.setImageUrl(transactionSyncResponseDTO.getPlot().get(i).getImageUrl());
                        divisionTable.setLatitude(transactionSyncResponseDTO.getPlot().get(i).getLatitude());
                        divisionTable.setLongitude(transactionSyncResponseDTO.getPlot().get(i).getLongitude());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getPlot().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getPlot().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getPlot().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getPlot().get(i).getUpdatedDate());
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertPlotIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getGeoBoundaries().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddGeoBoundriesTable divisionTable = new AddGeoBoundriesTable();
                        divisionTable.setId(transactionSyncResponseDTO.getGeoBoundaries().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getGeoBoundaries().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getGeoBoundaries().get(i).getPlotNo());
                        divisionTable.setLatitude(transactionSyncResponseDTO.getGeoBoundaries().get(i).getLatitude());
                        divisionTable.setLongitude(transactionSyncResponseDTO.getGeoBoundaries().get(i).getLongitude());
                        divisionTable.setIsActive(transactionSyncResponseDTO.getGeoBoundaries().get(i).getIsActive());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getGeoBoundaries().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getGeoBoundaries().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getGeoBoundaries().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getGeoBoundaries().get(i).getUpdatedDate());
                        divisionTable.setStage(transactionSyncResponseDTO.getGeoBoundaries().get(i).getStage());
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertPlotGeoIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }



                    Log.d(TAG, "onResponse: >>>"+strResponse);
                    progressDialog.dismiss();
//                    JSONArray jsonArray = new JSONArray(strResponse);
//                    if (jsonArray.length() > 0) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    JSONArray jsonFarmerPDArray = jsonArray.getJSONArray(0);
//                                    JSONArray jsonPlotDetailsArray = jsonArray.getJSONArray(1);
//
//                                    JSONArray jsonFarmerBankArray = jsonArray.getJSONArray(2);
//                                    JSONArray jsonFarmerDocArray = jsonArray.getJSONArray(3);
//
//                                    JSONArray jsonGeoBoundariesArray = jsonArray.getJSONArray(4);
//                                    JSONArray jsonPoPArray = jsonArray.getJSONArray(5);
//
//
//                                    JSONArray jsonLogBookArray = jsonArray.getJSONArray(6);
////                                    JSONArray jsonFertilizerArray = jsonArray.getJSONArray(6);
//                                    JSONArray jsonLandPreparationArray = jsonArray.getJSONArray(7);
//                                    JSONArray jsonLogBookGeoBoundariesArray = jsonArray.getJSONArray(8);
//                                    JSONArray jsonNurseryPreparationArray = jsonArray.getJSONArray(9);
//                                    JSONArray jsonTransplantingArray = jsonArray.getJSONArray(10);
//                                    JSONArray jsonFertilizerArray = jsonArray.getJSONArray(11);
//                                    JSONArray jsonSeedRateArray = jsonArray.getJSONArray(12);
//                                    JSONArray jsonWaterManagementArray = jsonArray.getJSONArray(13);
//                                    JSONArray jsonWeedManagementArray = jsonArray.getJSONArray(14);
//                                    JSONArray jsonPestDiseaseControlArray = jsonArray.getJSONArray(15);
//                                    JSONArray jsonHarvestArray = jsonArray.optJSONArray(16);
//                                    JSONArray jsonMoistureArray = jsonArray.optJSONArray(17);
//                                    JSONArray jsonYieldArray = jsonArray.optJSONArray(18);
//
////                                    if (jsonFarmerPDArray.length() != 0 || jsonFarmerPDArray.length() > 0) {
////                                        getPersoanlEmptyDataFromServer = false;
////
////                                        for (int farmerPD = 0; farmerPD < jsonFarmerPDArray.length(); farmerPD++) {
////                                            JSONObject jsonObjectFarmerPD = jsonFarmerPDArray.getJSONObject(farmerPD);
////                                            FarmerTable farmerTable = new FarmerTable();
////                                            SavingFarmerProfieImagesTable savingFarmerProfieImagesTable = new SavingFarmerProfieImagesTable();
////                                            farmerTable.setFarmerCode(jsonObjectFarmerPD.getString("FarmerId"));
////                                            farmerTable.setFarmerTitle(jsonObjectFarmerPD.getString("FarmerTitle"));
////                                            farmerTable.setFirstName(jsonObjectFarmerPD.getString("FirstName"));
////                                            farmerTable.setMiddleName(jsonObjectFarmerPD.getString("MiddleName"));
////                                            farmerTable.setLastName(jsonObjectFarmerPD.getString("LastName"));
////                                            farmerTable.setFatherName(jsonObjectFarmerPD.getString("FatherName"));
////                                            farmerTable.setGender(jsonObjectFarmerPD.getString("Gender"));
////                                            farmerTable.setAge(jsonObjectFarmerPD.getString("Age"));
////                                            farmerTable.setPrimaryContactNum(jsonObjectFarmerPD.getString("MobileNumber"));
//////                                            farmerTable.setSecondaryContactNum(jsonObjectFarmerPD.getString("SecondaryContactNum"));
////                                            farmerTable.setAddress(jsonObjectFarmerPD.getString("Address"));
////
////                                            farmerTable.setVillageId(jsonObjectFarmerPD.getString("VillageId"));
////
//////                                            farmerTable.setMandalId(jsonObjectFarmerPD.getString("MandalId"));
//////                                            farmerTable.setDistrictId(jsonObjectFarmerPD.getString("DistrictId"));
//////                                            farmerTable.setStateId(jsonObjectFarmerPD.getString("StateId"));
////
////
//////                                            farmerTable.setCluster("cluster");
////                                            farmerTable.setSync(true);
//////                                            farmerTable.setSync(1);
////
//////                                        getFarmerVillageDetailsByVillageId(jsonObjectFarmerPD.getString("VillageId"), farmerDetailListTable);
//////                                        getFarmerMandalDetailsFromLocalDB(jsonObjectFarmerPD.getString("MandalId"), farmerDetailListTable);
//////                                        getFarmerDistricDetailsFromLocalDB(jsonObjectFarmerPD.getString("DistrictId"), farmerDetailListTable);
//////                                        getFarmerStateDetailsFromlocalDB(jsonObjectFarmerPD.getString("StateId"), farmerDetailListTable);
////
////
//////                                        farmerDetailListTable.setVillageName(jsonObjectFarmerPD.getString("VillageName"));
//////                                        farmerDetailListTable.setMandalName(jsonObjectFarmerPD.getString("MandalName"));
//////                                        farmerDetailListTable.setDistrictName(jsonObjectFarmerPD.getString("DistrictName"));
//////                                        farmerDetailListTable.setStateName(jsonObjectFarmerPD.getString("StateName"));
////
////                                            // farmerDetailListTable.setFarmerImage(jsonObjectFarmerPD.getString("DocUrl"));
////                                            farmerTable.setPinCode(jsonObjectFarmerPD.getString("PinCode"));
////
////                                            // TODO: 1/13/2022 adding image purpose
////                                            farmerTable.setLocalImage(jsonObjectFarmerPD.getString("DocUrl"));
////                                            farmerTable.setDocUrl(jsonObjectFarmerPD.getString("DocUrl"));
////                                            farmerTable.setDocExtension(jsonObjectFarmerPD.getString("DocUrl").substring(jsonObjectFarmerPD.getString("DocUrl").length() - 4));
////                                            String dateTime = appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                            Log.d(TAG, "onClick: date" + dateTime);
////                                            farmerTable.setIsActive("1");
////                                            farmerTable.setUpdatedByUserId(jsonObjectFarmerPD.getString("UpdatedByUserId"));
////                                            farmerTable.setCreatedByUserId(jsonObjectFarmerPD.getString("CreatedByUserId"));
////
////                                            try {
////                                                //  TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectFarmerPD.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectFarmerPD.getString("UpdatedDate"));
////                                                farmerTable.setCreatedDate(destFormat.format(createDate));
////                                                farmerTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////
////
////                                            savingFarmerProfieImagesTable.setDocUrl(jsonObjectFarmerPD.getString("DocUrl"));
////                                            savingFarmerProfieImagesTable.setFarmerCode(jsonObjectFarmerPD.getString("FarmerId"));
////                                            savingFarmerProfieImagesTable.setDocExtension(jsonObjectFarmerPD.getString("DocUrl").substring(jsonObjectFarmerPD.getString("DocUrl").length() - 4));
////                                            savingFarmerProfieImagesTable.setDocType("photo");
////                                            savingFarmerProfieImagesTable.setSync(true);
////                                            savingFarmerProfieImagesTable.setServerStatus("1");
////                                            savingFarmerProfieImagesTable.setCreatedByUserId(jsonObjectFarmerPD.getString("CreatedByUserId"));
////                                            savingFarmerProfieImagesTable.setUpdatedByUserId(jsonObjectFarmerPD.getString("UpdatedByUserId"));
////                                            savingFarmerProfieImagesTable.setIsActive("1");
////                                            try {
////                                                //  TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectFarmerPD.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectFarmerPD.getString("UpdatedDate"));
////                                                savingFarmerProfieImagesTable.setCreatedDate(destFormat.format(createDate));
////                                                savingFarmerProfieImagesTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////
////
////                                            viewModel.insertFarmerProfileImages(savingFarmerProfieImagesTable);
////
////                                            viewModel.insertFarmerDetailListTableLocal(farmerTable);
////                                            //   Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Personal  details added successfully", Toast.LENGTH_SHORT).show();
////
////                                        }
////                                    } else {
////                                        getPersoanlEmptyDataFromServer = true;
////                                    }
////
////
////                                    if (jsonPlotDetailsArray.length() != 0 || jsonPlotDetailsArray.length() > 0) {
////                                        getPlotEmptyDataFromServer = false;
////                                        for (int plotDetails = 0; plotDetails < jsonPlotDetailsArray.length(); plotDetails++) {
////                                            JSONObject jsonObjectplotDetails = jsonPlotDetailsArray.getJSONObject(plotDetails);
////                                            PlotDetailsListTable plotDetailsListTable = new PlotDetailsListTable();
////                                            SavingPlotProfieImagesTable savingPlotProfieImagesTable = new SavingPlotProfieImagesTable();
////                                            plotDetailsListTable.setPlotNo(jsonObjectplotDetails.getString("PlotId"));
////                                            plotDetailsListTable.setSize(jsonObjectplotDetails.getString("SizeofAcres"));
////                                            plotDetailsListTable.setCropId(jsonObjectplotDetails.getString("CropId"));
////                                            plotDetailsListTable.setCropVarietyId(jsonObjectplotDetails.getString("CropVarietyId"));
////                                            //getCropNameByCropID(jsonObjectplotDetails.getString("CropId"),plotDetailsListTable);
////
////                                            //   plotDetailsListTable.setCropName(jsonObjectplotDetails.getString("CropName"));
////                                            plotDetailsListTable.setFarmerCode(jsonObjectplotDetails.getString("FarmerId"));
////                                            plotDetailsListTable.setLatitude(jsonObjectplotDetails.getString("Latitude"));
////                                            plotDetailsListTable.setLongitude(jsonObjectplotDetails.getString("Longitude"));
////                                            plotDetailsListTable.setAddress(jsonObjectplotDetails.getString("Address"));
////                                            plotDetailsListTable.setPlotownership(jsonObjectplotDetails.getString("Plotownership"));
////                                            plotDetailsListTable.setLandMark(jsonObjectplotDetails.getString("LandMark"));
//////                                            plotDetailsListTable.setDistrictId(jsonObjectplotDetails.getString("DistrictId"));
//////                                            plotDetailsListTable.setMandalId(jsonObjectplotDetails.getString("MandalId"));
////                                            plotDetailsListTable.setVillageId(jsonObjectplotDetails.getString("VillageId"));
//////                                            plotDetailsListTable.setStateId(jsonObjectplotDetails.getString("StateId"));
////                                            plotDetailsListTable.setPinCode(jsonObjectplotDetails.getString("PinCode"));
////                                            plotDetailsListTable.setSurveyNo(jsonObjectplotDetails.getString("SurveyNo"));
////                                            plotDetailsListTable.setPattadarBookNo(jsonObjectplotDetails.getString("PattadarBookNo"));
////                                            plotDetailsListTable.setGPSPlotArea(jsonObjectplotDetails.getString("GPSLocation"));
//////                                            plotDetailsListTable.setCluster(jsonObjectplotDetails.getString("Cluster"));
////
//////                                        plotDetailsListTable.setStateName(jsonObjectplotDetails.getString("StateName"));
//////                                        plotDetailsListTable.setDistrictName(jsonObjectplotDetails.getString("DistrictName"));
//////                                        plotDetailsListTable.setMandalName(jsonObjectplotDetails.getString("MandalName"));
//////                                        plotDetailsListTable.setVillageName(jsonObjectplotDetails.getString("VillageName"));
////
////                                            plotDetailsListTable.setLandImage(jsonObjectplotDetails.getString("DocUrl"));
////                                            plotDetailsListTable.setLandLocalImage(jsonObjectplotDetails.getString("DocUrl"));
////                                            plotDetailsListTable.setSync(true);
////                                            plotDetailsListTable.setServerStatus("1");
////                                            // landDetailsListTable.setGPSPlotArea("1.2Acre");
//////                                            plotDetailsListTable.setGPS(jsonObjectplotDetails.getString("GPS"));
////
////                                            plotDetailsListTable.setDocUrl(jsonObjectplotDetails.getString("DocUrl"));
////                                            plotDetailsListTable.setDocExtension(jsonObjectplotDetails.getString("DocUrl").substring(jsonObjectplotDetails.getString("DocUrl").length() - 4));
////
////                                            plotDetailsListTable.setIsActive("1");
////                                            plotDetailsListTable.setUpdatedByUserId(jsonObjectplotDetails.getString("UpdatedByUserId"));
////                                            plotDetailsListTable.setCreatedByUserId(jsonObjectplotDetails.getString("UpdatedByUserId"));
////                                            try {
////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                //  sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectplotDetails.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectplotDetails.getString("UpdatedDate"));
////                                                plotDetailsListTable.setCreatedDate(destFormat.format(createDate));
////                                                plotDetailsListTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////
//////
////                                            savingPlotProfieImagesTable.setDocUrl(jsonObjectplotDetails.getString("DocUrl"));
////                                            savingPlotProfieImagesTable.setPlotNo(jsonObjectplotDetails.getString("PlotId"));
////                                            savingPlotProfieImagesTable.setDocExtension(jsonObjectplotDetails.getString("DocUrl").substring(jsonObjectplotDetails.getString("DocUrl").length() - 4));
////                                            savingPlotProfieImagesTable.setDocType("photo");
////                                            savingPlotProfieImagesTable.setSync(true);
////                                            savingPlotProfieImagesTable.setServerStatus("1");
////                                            savingPlotProfieImagesTable.setCreatedByUserId(jsonObjectplotDetails.getString("UpdatedByUserId"));
////                                            savingPlotProfieImagesTable.setUpdatedByUserId(jsonObjectplotDetails.getString("UpdatedByUserId"));
////                                            savingPlotProfieImagesTable.setIsActive("1");
////
////                                            try {
////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                //  sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectplotDetails.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectplotDetails.getString("UpdatedDate"));
////                                                savingPlotProfieImagesTable.setCreatedDate(destFormat.format(createDate));
////                                                savingPlotProfieImagesTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////
////                                            viewModel.insertPlotPictureImagesToServer(savingPlotProfieImagesTable);
////                                            viewModel.insertOrUpdatPlotDetailListTable(plotDetailsListTable);
////
////                                            //   Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Plot  details added successfully", Toast.LENGTH_SHORT).show();
////
////
////                                        }
////
////                                    } else {
////                                        getPlotEmptyDataFromServer = true;
////                                    }
////
////                                    if (jsonGeoBoundariesArray.length() != 0 || jsonGeoBoundariesArray.length() > 0) {
////                                        for (int geoBoundaries = 0; geoBoundaries < jsonGeoBoundariesArray.length(); geoBoundaries++) {
////                                            JSONObject jsonObjectGeoBoundaries = jsonGeoBoundariesArray.getJSONObject(geoBoundaries);
////                                            Log.d(TAG, "onResponse:jsonObjectGeoBoundaries " + jsonObjectGeoBoundaries);
////
////                                            AddGeoBoundriesTable geoBoundary = new AddGeoBoundriesTable();
////                                            geoBoundary.setPlotId(jsonObjectGeoBoundaries.getString("PlotId"));
////                                            geoBoundary.setLatitude(String.valueOf(jsonObjectGeoBoundaries.optDouble("Latitude",0.0)));
////                                            geoBoundary.setLongitude(String.valueOf(jsonObjectGeoBoundaries.optDouble("Longitude",0.0)));
//////                                            geoBoundary.setTotalPlotArea(String.valueOf(jsonObjectGeoBoundaries.getString("TotalPlotArea")));
//////                                            geoBoundary.setCultureArea(String.valueOf(jsonObjectGeoBoundaries.getString("CultureArea")));
////                                            geoBoundary.setSync(true);
//////                                            geoBoundary.setServerSend("1");
////                                            geoBoundary.setIsActive("1");
////                                            geoBoundary.setUpdatedByUserId(jsonObjectGeoBoundaries.getString("UpdatedByUserId"));
////                                            geoBoundary.setCreatedByUserId(jsonObjectGeoBoundaries.getString("UpdatedByUserId"));
////
////                                            try {
////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectGeoBoundaries.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectGeoBoundaries.getString("UpdatedDate"));
////
////                                                geoBoundary.setCreatedDate(destFormat.format(createDate));
////                                                geoBoundary.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////
////                                            viewModel.insertGeoBoundariesvaluesIntolocalDB(geoBoundary);
////
////                                            //  Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Geo  details are saved", Toast.LENGTH_LONG).show();
////
////                                        }
////                                    } else {
////                                        getGeoEmptyDataFromServer = true;
////                                    }
////
////                                    if (jsonFarmerBankArray.length() != 0 || jsonFarmerBankArray.length() > 0) {
////                                        for (int farmerBank = 0; farmerBank < jsonFarmerBankArray.length(); farmerBank++) {
////                                            JSONObject jsonObjectfarmerBank = jsonFarmerBankArray.getJSONObject(farmerBank);
////                                            BankDetailsSubmitTable bankDetailsSubmitTable = new BankDetailsSubmitTable();
////
////                                            bankDetailsSubmitTable.setFarmerCode(jsonObjectfarmerBank.getString("FarmerId"));
////                                            //bankDetailsSubmitTable.setIdentityCode(strIdentityCode);
////                                            bankDetailsSubmitTable.setAccountNumber(jsonObjectfarmerBank.getString("AccountNumber"));
////                                            bankDetailsSubmitTable.setAccountHolderName(jsonObjectfarmerBank.getString("AccountHolderName"));
////                                            bankDetailsSubmitTable.setBranchName(jsonObjectfarmerBank.getString("BranchName"));
////                                            bankDetailsSubmitTable.setIFSCCode(jsonObjectfarmerBank.getString("IFSCCode"));
////                                            bankDetailsSubmitTable.setDocUrl(jsonObjectfarmerBank.getString("DocUrl"));
////                                            bankDetailsSubmitTable.setLocalDocUrl(jsonObjectfarmerBank.getString("DocUrl"));
////                                            bankDetailsSubmitTable.setDocExtension(jsonObjectfarmerBank.getString("DocUrl").substring(jsonObjectfarmerBank.getString("DocUrl").length() - 4));
////                                            bankDetailsSubmitTable.setSync(true);
////                                            bankDetailsSubmitTable.setServerSubmit("1");
////                                            bankDetailsSubmitTable.setIsActive("1");
////                                            try {
////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectfarmerBank.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectfarmerBank.getString("UpdatedDate"));
////
////                                                bankDetailsSubmitTable.setCreatedDate(destFormat.format(createDate));
////                                                bankDetailsSubmitTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////                                            bankDetailsSubmitTable.setUpdatedByUserId(jsonObjectfarmerBank.getString("CreatedByUserId"));
////                                            bankDetailsSubmitTable.setCreatedByUserId(jsonObjectfarmerBank.getString("UpdatedByUserId"));
////
////
////                                            SavingBankImagesTable savingBankImagesTable = new SavingBankImagesTable();
////                                            savingBankImagesTable.setDocUrl(jsonObjectfarmerBank.getString("DocUrl"));
////                                            savingBankImagesTable.setFarmerCode(jsonObjectfarmerBank.getString("FarmerId"));
////                                            savingBankImagesTable.setDocExtension(jsonObjectfarmerBank.getString("DocUrl").substring(jsonObjectfarmerBank.getString("DocUrl").length() - 4));
////                                            savingBankImagesTable.setDocType("bank passbook");
////                                            savingBankImagesTable.setSync(true);
////                                            savingBankImagesTable.setServerStatus("1");
////                                            savingBankImagesTable.setCreatedByUserId(jsonObjectfarmerBank.getString("CreatedByUserId"));
////                                            savingBankImagesTable.setUpdatedByUserId(jsonObjectfarmerBank.getString("UpdatedByUserId"));
////                                            savingBankImagesTable.setIsActive("1");
////                                            try {
////                                                //  TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectfarmerBank.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectfarmerBank.getString("UpdatedDate"));
////
////                                                savingBankImagesTable.setCreatedDate(destFormat.format(createDate));
////                                                savingBankImagesTable.setUpdatedDate(destFormat.format(updatedDate));
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////
////                                            viewModel.insertBankPictureImagesToServer(savingBankImagesTable);
////                                            viewModel.insertOrUpdateBankDetailsSubmitTableLocalDb(bankDetailsSubmitTable);
////
////                                            //Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Bank details are saved", Toast.LENGTH_LONG).show();
////                                            // Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Bank  details added successfully", Toast.LENGTH_SHORT).show();
////
////                                        }
////                                    } else {
////                                        getBankEmptyDataFromServer = true;
////                                    }
////
////                                    if (jsonFarmerDocArray.length() != 0 || jsonFarmerDocArray.length() > 0) {
////                                        for (int farmerDoc = 0; farmerDoc < jsonFarmerDocArray.length(); farmerDoc++) {
////                                            JSONObject jsonObjectfarmerDoc = jsonFarmerDocArray.getJSONObject(farmerDoc);
////                                            DocIdentiFicationDeatilsTable docIdentiFicationDeatilsTable = new DocIdentiFicationDeatilsTable();
////                                            docIdentiFicationDeatilsTable.setFarmerCode(jsonObjectfarmerDoc.getString("FarmerId"));
////                                            docIdentiFicationDeatilsTable.setDocType(jsonObjectfarmerDoc.getString("DocType"));
////                                            docIdentiFicationDeatilsTable.setDocLocal(jsonObjectfarmerDoc.getString("DocUrl"));
////                                            docIdentiFicationDeatilsTable.setDocExtension(jsonObjectfarmerDoc.getString("DocExtension"));
////                                            docIdentiFicationDeatilsTable.setDocUrl(jsonObjectfarmerDoc.getString("DocUrl"));
////                                            docIdentiFicationDeatilsTable.setSync(true);
////                                            docIdentiFicationDeatilsTable.setServerPost("1");
////                                            docIdentiFicationDeatilsTable.setDocOldNum(jsonObjectfarmerDoc.getString("DocOldNum"));
////
////                                            docIdentiFicationDeatilsTable.setIsActive("1");
////                                            try {
////                                                //TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectfarmerDoc.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectfarmerDoc.getString("UpdatedDate"));
////
////                                                docIdentiFicationDeatilsTable.setCreatedDate(destFormat.format(createDate));
////                                                docIdentiFicationDeatilsTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////                                            docIdentiFicationDeatilsTable.setUpdatedByUserId(jsonObjectfarmerDoc.getString("CreatedByUserId"));
////                                            docIdentiFicationDeatilsTable.setCreatedByUserId(jsonObjectfarmerDoc.getString("UpdatedByUserId"));
////
////                                            viewModel.insertDoctable(docIdentiFicationDeatilsTable);
////                                            //    Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Documents  details added successfully", Toast.LENGTH_SHORT).show();
////
////
////                                        }
////                                    } else {
////                                        getDocEmptyDataFromServer = true;
////                                    }
////
//////                                    if (jsonLogBookArray.length() != 0 || jsonLogBookArray.length() > 0) {
//////                                        for (int logBook = 0; logBook < jsonLogBookArray.length(); logBook++) {
//////
//////                                            JSONObject jsonObjectlogBook = jsonLogBookArray.getJSONObject(logBook);
//////                                            AddLogBookDetailsTable addLogBookDetailsTable = new AddLogBookDetailsTable();
//////                                            addLogBookDetailsTable.setPlotNo(jsonObjectlogBook.getString("PlotNo"));
//////                                            addLogBookDetailsTable.setLogBookNo(jsonObjectlogBook.getString("LogBookNo"));
//////                                            addLogBookDetailsTable.setDateOfSowing(jsonObjectlogBook.getString("DateOfSowing"));
//////                                            addLogBookDetailsTable.setSeason(Integer.parseInt(jsonObjectlogBook.getString("Season")));
////////                                            addLogBookDetailsTable.setCultivationPractice(Integer.parseInt(jsonObjectlogBook.getString("CultivationPractice")));
//////                                            //addLogBookDetailsTable.setCultivationPracticeName(jsonObjectlogBook.getString("CultivationPracticeName"));
//////                                            // addLogBookDetailsTable.setSeasonName(jsonObjectlogBook.getString("SeasonName"));
//////                                            addLogBookDetailsTable.setCrop(jsonObjectlogBook.getString("Crop"));
////////                                            addLogBookDetailsTable.setHarvest(jsonObjectlogBook.getString("Harvest"));
//////
//////                                            addLogBookDetailsTable.setSync(true);
//////                                            addLogBookDetailsTable.setServerStatus("1");
//////
//////                                            addLogBookDetailsTable.setIsActive("1");
//////                                            addLogBookDetailsTable.setUpdatedByUserId(jsonObjectlogBook.getString("UpdatedByUserId"));
//////                                            addLogBookDetailsTable.setCreatedByUserId(jsonObjectlogBook.getString("CreatedByUserId"));
//////
//////                                            try {
//////                                                //TimeZone utc = TimeZone.getTimeZone("UTC");
//////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
//////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////
//////                                                SimpleDateFormat newDateIndiaFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
//////                                                //sourceFormat.setTimeZone(utc);
//////
//////                                                Date createDate = sourceFormat.parse(jsonObjectlogBook.getString("CreatedDate"));
//////                                                Date updatedDate = sourceFormat.parse(jsonObjectlogBook.getString("UpdatedDate"));
//////                                                Date indiaDate = sourceFormat.parse(jsonObjectlogBook.getString("CreatedDate"));
//////
//////                                                addLogBookDetailsTable.setDateCheck(newDateIndiaFormat.format(indiaDate));
//////
//////                                                addLogBookDetailsTable.setCreatedDate(destFormat.format(createDate));
//////                                                addLogBookDetailsTable.setUpdatedDate(destFormat.format(updatedDate));
//////
//////                                            } catch (Exception e) {
//////                                                e.printStackTrace();
//////                                            }
//////
//////                                            viewModel.insertAddLogBookDetailsTableLocalDb(addLogBookDetailsTable);
//////                                            // Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Logbook  details added successfully", Toast.LENGTH_SHORT).show();
//////
//////
//////                                        }
//////                                    } else {
//////                                        getLogBookEmptyDataFromServer = true;
//////                                    }
//////
//////
//////                                    if (jsonFertilizerArray.length() != 0 || jsonFertilizerArray.length() > 0) {
//////                                        for (int fertlizer = 0; fertlizer < jsonFertilizerArray.length(); fertlizer++) {
//////
//////                                            JSONObject jsonObjectfertlizer = jsonFertilizerArray.getJSONObject(fertlizer);
//////                                            AddFertilizerDetailsTable fertilizerDetailsTable = new AddFertilizerDetailsTable();
//////                                            fertilizerDetailsTable.setLogBookNo(jsonObjectfertlizer.getString("LogBookNo"));
////////                                            fertilizerDetailsTable.setFertilizer(Integer.parseInt(jsonObjectfertlizer.getString("Fertilizer")));
////////                                            fertilizerDetailsTable.setFertilizerType(Integer.parseInt(jsonObjectfertlizer.getString("FertilizerType")));
////////                                            fertilizerDetailsTable.setQty(jsonObjectfertlizer.getString("Qty"));
////////                                            fertilizerDetailsTable.setUOM(Integer.parseInt(jsonObjectfertlizer.getString("UOM")));
////////                                            fertilizerDetailsTable.setCropProtection(jsonObjectfertlizer.getString("CropProtection"));
//////                                            fertilizerDetailsTable.setSync(true);
//////                                            fertilizerDetailsTable.setServerStatus("1");
//////                                            fertilizerDetailsTable.setIsActive("1");
//////                                            fertilizerDetailsTable.setUpdatedByUserId(jsonObjectfertlizer.getString("UpdatedByUserId"));
//////                                            fertilizerDetailsTable.setCreatedByUserId(jsonObjectfertlizer.getString("CreatedByUserId"));
//////
//////                                            try {
//////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
//////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
//////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////
//////                                                SimpleDateFormat newDateIndiaFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
//////
//////                                                //sourceFormat.setTimeZone(utc);
//////
//////                                                Date createDate = sourceFormat.parse(jsonObjectfertlizer.getString("CreatedDate"));
//////
//////                                                Date selectDate = sourceFormat.parse(jsonObjectfertlizer.getString("Date"));
//////
//////                                                Date updatedDate = sourceFormat.parse(jsonObjectfertlizer.getString("UpdatedDate"));
//////                                                Date indiaDate = sourceFormat.parse(jsonObjectfertlizer.getString("CreatedDate"));
//////
//////                                                fertilizerDetailsTable.setDate(newDateIndiaFormat.format(selectDate));
////////                                                fertilizerDetailsTable.setDateCheck(newDateIndiaFormat.format(indiaDate));
//////
//////                                                fertilizerDetailsTable.setCreatedDate(destFormat.format(createDate));
//////                                                fertilizerDetailsTable.setUpdatedDate(destFormat.format(updatedDate));
//////
//////                                            } catch (Exception e) {
//////                                                e.printStackTrace();
//////                                            }
//////
//////                                            viewModel.insertAddFertilizerDetailsTableLocalDb(fertilizerDetailsTable);
//////
//////                                            // Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Fertilizer  details added successfully", Toast.LENGTH_SHORT).show();
//////
//////
//////                                        }
//////                                    } else {
//////                                        getFertilizerEmptyDataFromServer = true;
//////                                    }
//////
//////
////////                                    if (jsonOrganicArray.length() != 0 || jsonOrganicArray.length() > 0) {
////////                                        for (int organic = 0; organic < jsonOrganicArray.length(); organic++) {
////////                                            JSONObject jsonObjectorganic = jsonOrganicArray.getJSONObject(organic);
////////                                            AddOrganicDetailsTable organicDetailsTable = new AddOrganicDetailsTable();
////////                                            organicDetailsTable.setLogBookNo(jsonObjectorganic.getString("LogBookNo"));
////////                                            organicDetailsTable.setValue(Integer.valueOf(jsonObjectorganic.getString("Value")));
////////                                            // organicDetailsTable.setDate(jsonObjectorganic.getString("LogBookNo"));
////////                                            organicDetailsTable.setSync(true);
////////                                            organicDetailsTable.setServerStatus("1");
////////                                            organicDetailsTable.setIsActive("1");
////////
////////                                            organicDetailsTable.setUpdatedByUserId(jsonObjectorganic.getString("UpdatedByUserId"));
////////                                            organicDetailsTable.setCreatedByUserId(jsonObjectorganic.getString("CreatedByUserId"));
////////                                            try {
////////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////////                                                SimpleDateFormat newDateIndiaFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
////////                                                //  sourceFormat.setTimeZone(utc);
////////
////////                                                Date createDate = sourceFormat.parse(jsonObjectorganic.getString("CreatedDate"));
////////                                                Date selectDate = sourceFormat.parse(jsonObjectorganic.getString("Date"));
////////                                                Date updatedDate = sourceFormat.parse(jsonObjectorganic.getString("UpdatedDate"));
////////
////////                                                organicDetailsTable.setDate(newDateIndiaFormat.format(selectDate));
////////                                                organicDetailsTable.setCreatedDate(destFormat.format(createDate));
////////                                                organicDetailsTable.setUpdatedDate(destFormat.format(updatedDate));
////////
////////                                            } catch (Exception e) {
////////                                                e.printStackTrace();
////////                                            }
////////
////////
////////                                            viewModel.insertAddOrganicDetailsTableLocalDb(organicDetailsTable);
////////
////////                                            //   Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Organic Amendments  details added successfully", Toast.LENGTH_SHORT).show();
////////
////////
////////                                        }
////////                                    } else {
////////                                        getOrganicEmptyDataFromServer = true;
////////                                    }
//////
////////                                    if (jsonWaterRegimeArray.length() != 0 || jsonWaterRegimeArray.length() > 0) {
////////                                        for (int waterRegime = 0; waterRegime < jsonWaterRegimeArray.length(); waterRegime++) {
////////                                            JSONObject jsonObjectwaterRegime = jsonWaterRegimeArray.getJSONObject(waterRegime);
////////                                            AddWaterRegimeSeasonDetailsTable addWaterRegimeSeasonDetailsTable = new AddWaterRegimeSeasonDetailsTable();
////////
////////                                            addWaterRegimeSeasonDetailsTable.setLogBookNo(jsonObjectwaterRegime.getString("LogBookNo"));
////////
////////                                            addWaterRegimeSeasonDetailsTable.setField(Integer.valueOf(jsonObjectwaterRegime.optString("Field")));
////////                                            addWaterRegimeSeasonDetailsTable.setSync(true);
////////                                            addWaterRegimeSeasonDetailsTable.setServerStatus("1");
////////                                            addWaterRegimeSeasonDetailsTable.setIsActive("1");
////////
////////                                            addWaterRegimeSeasonDetailsTable.setCreatedByUserId(jsonObjectwaterRegime.getString("CreatedByUserId"));
////////                                            addWaterRegimeSeasonDetailsTable.setUpdatedByUserId(jsonObjectwaterRegime.getString("CreatedByUserId"));
////////
////////                                            try {
////////                                                //  TimeZone utc = TimeZone.getTimeZone("UTC");
////////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////////                                                //  sourceFormat.setTimeZone(utc);
////////                                                Date createDate = sourceFormat.parse(jsonObjectwaterRegime.getString("CreatedDate"));
////////                                                Date updatedDate = sourceFormat.parse(jsonObjectwaterRegime.getString("UpdatedDate"));
////////
////////                                                addWaterRegimeSeasonDetailsTable.setCreatedDate(destFormat.format(createDate));
////////                                                addWaterRegimeSeasonDetailsTable.setUpdatedDate(destFormat.format(updatedDate));
////////
////////                                            } catch (Exception e) {
////////                                                e.printStackTrace();
////////                                            }
////////                                            viewModel.insertAddWaterRegimeSeasonDetailsTableLocalDb(addWaterRegimeSeasonDetailsTable);
////////
////////                                            // Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Water Regime on Season  details added successfully", Toast.LENGTH_SHORT).show();
////////
////////                                        }
////////                                    } else {
////////                                        getWaterRegimeEmptyDataFromServer = true;
////////                                    }
////////
////////                                    if (jsonWaterPreSeasonArray.length() != 0 || jsonWaterPreSeasonArray.length() > 0) {
////////                                        for (int waterPreseason = 0; waterPreseason < jsonWaterPreSeasonArray.length(); waterPreseason++) {
////////                                            JSONObject jsonObjectwaterPreseason = jsonWaterPreSeasonArray.getJSONObject(waterPreseason);
////////                                            AddWaterReasonPreSeasonTable addWaterReasonPreSeasonTable = new AddWaterReasonPreSeasonTable();
////////
////////                                            addWaterReasonPreSeasonTable.setLogBookNo(jsonObjectwaterPreseason.getString("LogBookNo"));
////////                                            addWaterReasonPreSeasonTable.setField(Integer.valueOf(jsonObjectwaterPreseason.optString("Field")));
////////                                            addWaterReasonPreSeasonTable.setSync(true);
////////                                            addWaterReasonPreSeasonTable.setServerStatus("1");
////////                                            addWaterReasonPreSeasonTable.setIsActive("1");
////////
////////                                            addWaterReasonPreSeasonTable.setCreatedByUserId(jsonObjectwaterPreseason.getString("CreatedByUserId"));
////////                                            addWaterReasonPreSeasonTable.setUpdatedByUserId(jsonObjectwaterPreseason.getString("CreatedByUserId"));
////////
////////                                            try {
////////                                                //   TimeZone utc = TimeZone.getTimeZone("UTC");
////////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////////                                                // sourceFormat.setTimeZone(utc);
////////                                                Date createDate = sourceFormat.parse(jsonObjectwaterPreseason.getString("CreatedDate"));
////////                                                Date updatedDate = sourceFormat.parse(jsonObjectwaterPreseason.getString("UpdatedDate"));
////////
////////                                                addWaterReasonPreSeasonTable.setCreatedDate(destFormat.format(createDate));
////////                                                addWaterReasonPreSeasonTable.setUpdatedDate(destFormat.format(updatedDate));
////////
////////                                            } catch (Exception e) {
////////                                                e.printStackTrace();
////////                                            }
////////                                            viewModel.insertAddWaterReasonPreSeasonTableLocalDb(addWaterReasonPreSeasonTable);
////////                                            //  Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Water Reason Pre-Season  details added successfully", Toast.LENGTH_SHORT).show();
////////
////////                                        }
////////                                    } else {
////////                                        getWaterPreSeasonEmptyDataFromServer = true;
////////                                    }
////////
////////                                    if (jsonBoreWellPumpArray.length() != 0 || jsonBoreWellPumpArray.length() > 0) {
//////////                                        for (int boreWell = 0; boreWell < jsonBoreWellPumpArray.length(); boreWell++) {
//////////                                            JSONObject jsonObjectboreWell = jsonBoreWellPumpArray.getJSONObject(boreWell);
//////////                                            AddBoreWellPumpSeasonTable addBoreWellPumpSeasonTable = new AddBoreWellPumpSeasonTable();
//////////                                            addBoreWellPumpSeasonTable.setLogBookNo(jsonObjectboreWell.getString("LogBookNo"));
//////////                                            addBoreWellPumpSeasonTable.setHoursDay(Integer.valueOf(jsonObjectboreWell.optString("HoursDay")));
////////////                                            addBoreWellPumpSeasonTable.setTypeOfIrrigation((jsonObjectboreWell.optString("TypeOfIrrigation")));
////////////                                            addBoreWellPumpSeasonTable.setPumpCapacity((jsonObjectboreWell.optString("PumpCapacity")));
////////////                                            addBoreWellPumpSeasonTable.setDepthOfBorewll((jsonObjectboreWell.optString("DepthOfBorewll")));
//////////
//////////                                            addBoreWellPumpSeasonTable.setSync(true);
//////////                                            addBoreWellPumpSeasonTable.setServerStatus("1");
//////////                                            addBoreWellPumpSeasonTable.setIsActive("1");
//////////
//////////                                            addBoreWellPumpSeasonTable.setCreatedByUserId(jsonObjectboreWell.getString("CreatedByUserId"));
//////////                                            addBoreWellPumpSeasonTable.setUpdatedByUserId(jsonObjectboreWell.getString("CreatedByUserId"));
//////////
//////////                                            try {
//////////                                                //   TimeZone utc = TimeZone.getTimeZone("UTC");
//////////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
//////////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//////////                                                SimpleDateFormat newDateIndiaFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
//////////                                                // sourceFormat.setTimeZone(utc);
//////////                                                Date createDate = sourceFormat.parse(jsonObjectboreWell.getString("CreatedDate"));
//////////                                                Date updatedDate = sourceFormat.parse(jsonObjectboreWell.getString("UpdatedDate"));
//////////                                                Date selectDate = sourceFormat.parse(jsonObjectboreWell.getString("Date"));
//////////                                                addBoreWellPumpSeasonTable.setDate(newDateIndiaFormat.format(selectDate));
//////////                                                addBoreWellPumpSeasonTable.setCreatedDate(destFormat.format(createDate));
//////////                                                addBoreWellPumpSeasonTable.setUpdatedDate(destFormat.format(updatedDate));
//////////
//////////                                            } catch (Exception e) {
//////////                                                e.printStackTrace();
//////////                                            }
//////////                                            viewModel.insertAddBoreWellPumpSeasonTableLocalDb(addBoreWellPumpSeasonTable);
//////////                                            //  Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Borewell, Pump Operations  details added successfully", Toast.LENGTH_SHORT).show();
//////////
//////////                                        }
////////                                    } else {
////////                                        getBorewellEmptyDataFromServer = true;
////////                                    }
////////
////////                                    if (jsonWaterFieldArray.length() != 0 || jsonWaterFieldArray.length() > 0) {
////////                                        for (int waterField = 0; waterField < jsonWaterFieldArray.length(); waterField++) {
////////                                            JSONObject jsonObjectwaterField = jsonWaterFieldArray.getJSONObject(waterField);
////////                                            AddWaterSeasonFeildTable addWaterSeasonFeildTable = new AddWaterSeasonFeildTable();
////////
////////                                            addWaterSeasonFeildTable.setLogBookNo(jsonObjectwaterField.getString("LogBookNo"));
////////                                            addWaterSeasonFeildTable.setField(Integer.valueOf(jsonObjectwaterField.optString("Field")));
////////                                            addWaterSeasonFeildTable.setSync(true);
////////                                            addWaterSeasonFeildTable.setServerStatus("1");
////////                                            addWaterSeasonFeildTable.setIsActive("1");
////////
////////                                            addWaterSeasonFeildTable.setCreatedByUserId(jsonObjectwaterField.getString("CreatedByUserId"));
////////                                            addWaterSeasonFeildTable.setUpdatedByUserId(jsonObjectwaterField.getString("CreatedByUserId"));
////////
////////                                            try {
////////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////////                                                //sourceFormat.setTimeZone(utc);
////////                                                Date createDate = sourceFormat.parse(jsonObjectwaterField.getString("CreatedDate"));
////////                                                Date updatedDate = sourceFormat.parse(jsonObjectwaterField.getString("UpdatedDate"));
////////
////////                                                addWaterSeasonFeildTable.setCreatedDate(destFormat.format(createDate));
////////                                                addWaterSeasonFeildTable.setUpdatedDate(destFormat.format(updatedDate));
////////
////////                                            } catch (Exception e) {
////////                                                e.printStackTrace();
////////                                            }
////////                                            viewModel.insertAddWaterSeasonFeildTableLocalDb(addWaterSeasonFeildTable);
////////                                            //  Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Water Regime on the field  details added successfully", Toast.LENGTH_SHORT).show();
////////
////////                                        }
////////                                    } else {
////////                                        getWaterFieldEmptyDataFromServer = true;
////////                                    }
////
////                                    if (jsonLogBookArray.length() != 0 || jsonLogBookArray.length() > 0) {
////                                        for (int log = 0; log < jsonLogBookArray.length(); log++) {
////                                            JSONObject jsonObjectharvest = jsonLogBookArray.getJSONObject(log);
////                                            AddLogBookDetailsTable addHarvestDetailsTable = new AddLogBookDetailsTable();
////                                            addHarvestDetailsTable.setId(jsonObjectharvest.optInt("Id"));
////                                            addHarvestDetailsTable.setDateCheck(jsonObjectharvest.optString("DateCheck"));
////                                            addHarvestDetailsTable.setPlotNo(jsonObjectharvest.optString("PlotId"));
////                                            addHarvestDetailsTable.setLogBookNo(jsonObjectharvest.optString("LogBookNo"));
////                                            addHarvestDetailsTable.setSQNo(jsonObjectharvest.optString("SQNo"));
////                                            addHarvestDetailsTable.setSync(jsonObjectharvest.optBoolean("Sync"));
////                                            addHarvestDetailsTable.setDateOfSowing(jsonObjectharvest.optString("DateOfSowing"));
////                                            addHarvestDetailsTable.setSeason(jsonObjectharvest.optInt("SeasonId",0));
////                                            addHarvestDetailsTable.setCrop(jsonObjectharvest.optInt("CropId",0));
////                                            addHarvestDetailsTable.setCropVariety(jsonObjectharvest.optInt("CropVarietyId",0));
////                                            addHarvestDetailsTable.setServerStatus("1");
////                                            addHarvestDetailsTable.setIsActive(true);
////
////                                            addHarvestDetailsTable.setCreatedByUserId(jsonObjectharvest.getString("CreatedByUserId"));
////                                            addHarvestDetailsTable.setUpdatedByUserId(jsonObjectharvest.getString("UpdatedByUserId"));
////
////                                            try {
////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                SimpleDateFormat newDateIndiaFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectharvest.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectharvest.getString("UpdatedDate"));
////                                                Date selectDate = sourceFormat.parse(jsonObjectharvest.getString("Date"));
//////                                                addHarvestDetailsTable.setDate(newDateIndiaFormat.format(selectDate));
////                                                addHarvestDetailsTable.setCreatedDate(destFormat.format(createDate));
////                                                addHarvestDetailsTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////                                            viewModel.insertAddLogBookDetailsTableLocalDb(addHarvestDetailsTable);
////                                            // Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Harvest  details added successfully", Toast.LENGTH_SHORT).show();
////
////                                        }
////                                    } else {
////                                        getLogBookEmptyDataFromServer = true;
////                                    }
////
////                                    if (jsonLandPreparationArray.length() != 0 || jsonLandPreparationArray.length() > 0) {
////                                        for (int log = 0; log < jsonLandPreparationArray.length(); log++) {
////                                            JSONObject jsonObjectharvest = jsonLandPreparationArray.getJSONObject(log);
////                                            AddLandPreparationTable addHarvestDetailsTable = new AddLandPreparationTable();
////                                            addHarvestDetailsTable.setId(jsonObjectharvest.optInt("Id"));
////                                            addHarvestDetailsTable.setLogBookNo(jsonObjectharvest.optString("LogBookNo"));
////                                            addHarvestDetailsTable.setOrganicAmendmentId(jsonObjectharvest.optString("OrganicAmendmentId"));
////                                            addHarvestDetailsTable.setDate(jsonObjectharvest.optString("Date"));
////                                            addHarvestDetailsTable.setSQNo(jsonObjectharvest.optString("SQNo"));
//////                                            addHarvestDetailsTable.setSync(jsonObjectharvest.optBoolean("Sync"));
////                                            addHarvestDetailsTable.setServerStatus("1");
////                                            addHarvestDetailsTable.setSync(true);
////                                            addHarvestDetailsTable.setIsActive(true);
////
////                                            addHarvestDetailsTable.setCreatedByUserId(jsonObjectharvest.getString("CreatedByUserId"));
////                                            addHarvestDetailsTable.setUpdatedByUserId(jsonObjectharvest.getString("UpdatedByUserId"));
////
////                                            try {
////                                                // TimeZone utc = TimeZone.getTimeZone("UTC");
////                                                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS);
////                                                SimpleDateFormat destFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
////                                                SimpleDateFormat newDateIndiaFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
////                                                // sourceFormat.setTimeZone(utc);
////                                                Date createDate = sourceFormat.parse(jsonObjectharvest.getString("CreatedDate"));
////                                                Date updatedDate = sourceFormat.parse(jsonObjectharvest.getString("UpdatedDate"));
////                                                Date selectDate = sourceFormat.parse(jsonObjectharvest.getString("Date"));
//////                                                addHarvestDetailsTable.setDate(newDateIndiaFormat.format(selectDate));
////                                                addHarvestDetailsTable.setCreatedDate(destFormat.format(createDate));
////                                                addHarvestDetailsTable.setUpdatedDate(destFormat.format(updatedDate));
////
////                                            } catch (Exception e) {
////                                                e.printStackTrace();
////                                            }
////                                            viewModel.insertAddLandPreparationTableLocalDb(addHarvestDetailsTable);
////                                            // Toast.makeText(SyncFunctionalityDeatilsActivity.this, "Harvest  details added successfully", Toast.LENGTH_SHORT).show();
////
////                                        }
////                                    } else {
////                                        getLandPreparationEmptyDataFromServer = true;
////                                    }
////
////                                    if (getPersoanlEmptyDataFromServer && getDocEmptyDataFromServer && getBankEmptyDataFromServer &&
////                                            getPlotEmptyDataFromServer && getGeoEmptyDataFromServer &&
////                                            getLogBookEmptyDataFromServer && getFertilizerEmptyDataFromServer && getOrganicEmptyDataFromServer &&
////                                            getWaterRegimeEmptyDataFromServer && getWaterPreSeasonEmptyDataFromServer && getBorewellEmptyDataFromServer &&
////                                            getWaterFieldEmptyDataFromServer && getHarvestEmptyDataFromServer) {
////                                        progressDialog.dismiss();
////                                        Toast.makeText(LoginActivity.this, "no data found", Toast.LENGTH_LONG).show();
////
////                                    } else {
////                                        progressDialog.dismiss();
////                                        Toast.makeText(LoginActivity.this, "Fetch All Data From Server SuccessFully", Toast.LENGTH_LONG).show();
////
////                                    }
//
//
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    Log.d("Error", ">>>>" + ex.toString());
//                                }
//                            }
//                        }, 20000);
//                    } else {
//                        Toast.makeText(DashBoardActivity.this, "no records found", Toast.LENGTH_LONG).show();
//                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    progressDialog.dismiss();
                    Log.d("Error", ">>>>" + ex.toString());
                }
            }

            @Override
            public void onFailure(Call<TransactionSyncResponseDTO> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("Error Call", ">>>>" + call.toString());
                Log.d("Error", ">>>>" + t.toString());

            }
        });
    }

    private boolean checkAllPermissions() {
        try {

            // TODO: Check which permissions are granted
            List<String> listPermissionsNeeded = new ArrayList<>();
            for (String permission : PERMISSIONS_STORAGE) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permission);
                }
            }

            // TODO: Ask for non granted permissions
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSIONS_REQUESTS_CODE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();

//        startService(new Intent(this, FalogService.class));

        if(!isMyServiceRunning(FalogService.class)){
            if (isLocationPermissionGranted(this) ) {
                try {
                    startService(new Intent(this, FalogService.class));
                } catch (Exception ex){

                }

            }
//            Toast.makeText(this, "Service has started", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "Service is running", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public static boolean isLocationPermissionGranted(final Context context) {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);

        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Build.VERSION.SDK_INT >= 28
                || (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
                && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED));
    }

    @Override
    protected void onDestroy() {
        this.mWakeLock.release();
        super.onDestroy();
    }
}