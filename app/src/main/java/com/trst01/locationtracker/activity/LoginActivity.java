package com.trst01.locationtracker.activity;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;
import static com.trst01.locationtracker.constant.AppConstant.DeviceUserName;
import static com.trst01.locationtracker.constant.AppConstant.DeviceUserPwd;
import static com.trst01.locationtracker.constant.AppConstant.IsFirstTime;
import static com.trst01.locationtracker.constant.AppConstant.MESSAGE_NO_INTERNET_CONNECTION;
import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;
import static com.trst01.locationtracker.constant.AppConstant.TestLoc;
import static com.trst01.locationtracker.constant.AppConstant.accessToken;
import static com.trst01.locationtracker.constant.AppConstant.isTouched;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.trst01.locationtracker.MainActivity;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonConstants;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.dagger.App;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddD30Table;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
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
import com.trst01.locationtracker.models.Doc;
import com.trst01.locationtracker.models.LocationDTO;
import com.trst01.locationtracker.models.LoginDTO;
import com.trst01.locationtracker.models.LoginResponseDTO;
import com.trst01.locationtracker.models.MastersResponseDTO;
import com.trst01.locationtracker.models.TransactionSyncResponseDTO;
import com.trst01.locationtracker.repositories.Retrofit_funtion_class;
import com.trst01.locationtracker.services.FaLogTracking.FalogService;
import com.trst01.locationtracker.services.api.AppAPI;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements HasSupportFragmentInjector {
    public static final int ALL_PERMISSION_CODE = 1001;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    public static final String LOG_TAG = LoginActivity.class.getName();
    private static final String TAG = LoginActivity.class.getCanonicalName();
    //private Palm3FoilDatabase palm3FoilDatabase;
    public String strUserDeviceId;
    String userName, userPWd, strToken;
    String TokenAccess = "";

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

    private SharedPreferences sharedPreferences;
    String appVersion;
    TextView txtLogin;
    TextView tvAppVersion;
    EditText edt_login_id,edt_password_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        txtLogin = findViewById(R.id.txtLogin);
        tvAppVersion = (TextView) findViewById(R.id.txtVersion);
        edt_login_id = (EditText) findViewById(R.id.edt_login_id);
        edt_password_id = (EditText) findViewById(R.id.edt_password_id);

        System.out.println(md5("Welcome1"));

        ui();
        configureDagger();
        configureViewModel();



        mGetPermission = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == LoginActivity.RESULT_OK) {
                    Toast.makeText(LoginActivity.this, "Android 11 permission ok", Toast.LENGTH_SHORT).show();
                }
            }
        });

        takePermission();

        LoginActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                App.createDBPath();
            }
        });

        if (Build.VERSION.SDK_INT < 30) {
//            checkAllPermissions();
        }

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                finish();
//            }
//        }, 1 * 2500);
//
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                username : Madhuri
//                password: mstr //imei number: df3c07da4a4acf18
//                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "110").apply();
//                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "101").apply();
//                Toast.makeText(getApplicationContext(),appHelper.getSharedPrefObj().getString(DeviceUserID,""),Toast.LENGTH_LONG).show();
//                getLoginCredentials();


                String testNav = appHelper.getSharedPrefObj().getString(DeviceUserID,"");
                Toast.makeText(LoginActivity.this, testNav, Toast.LENGTH_SHORT).show();
//                if(!appHelper.getSharedPrefObj().getString(DeviceUserID,"").isEmpty()){
                if(testNav.length()>0){
                    Boolean  isFirst = appHelper.getSharedPrefObj().getBoolean(AppConstant.isTouched, false);
                    if(isFirst){
                        Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    } else {
//                        getLoginCredentials();
                        if(!edt_login_id.getText().toString().isEmpty()&&!edt_password_id.getText().toString().isEmpty()){
                            getLoginDetailsByImeiNumber(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()),CommonUtils.getIMEInumber(LoginActivity.this));
                            appHelper.getSharedPrefObj().edit().putBoolean(isTouched,true).apply();
                        }
                    }
                } else {
                    if(!edt_login_id.getText().toString().isEmpty()&&!edt_password_id.getText().toString().isEmpty()){
                        getLoginDetailsByImeiNumber(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()),CommonUtils.getIMEInumber(LoginActivity.this));
                        appHelper.getSharedPrefObj().edit().putBoolean(isTouched,true).apply();
                    }
//                    getLoginCredentials();
                }


//                login(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()));
//                Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                finish();

            }
        });

//        praveen login
//        txtLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "110").apply();
////                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "101").apply();
////                Toast.makeText(getApplicationContext(),appHelper.getSharedPrefObj().getString(DeviceUserID,""),Toast.LENGTH_LONG).show();
//                getLoginCredentials();
////
////
////                String testNav = appHelper.getSharedPrefObj().getString(DeviceUserID,"");
////                Toast.makeText(LoginActivity.this, testNav, Toast.LENGTH_SHORT).show();
//////                if(!appHelper.getSharedPrefObj().getString(DeviceUserID,"").isEmpty()){
////                if(testNav.length()>0){
////                    Boolean  isFirst = appHelper.getSharedPrefObj().getBoolean(AppConstant.isTouched, false);
////                    if(isFirst){
////                        Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
////                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        startActivity(i);
////                        finish();
////                    } else {
//////                        getLoginCredentials();
////                        if(!edt_login_id.getText().toString().isEmpty()&&!edt_password_id.getText().toString().isEmpty()){
////                            getLoginDetailsByImeiNumber(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()),CommonUtils.getIMEInumber(LoginActivity.this));
////                            appHelper.getSharedPrefObj().edit().putBoolean(isTouched,true).apply();
////                        }
////                    }
////                } else {
////                    if(!edt_login_id.getText().toString().isEmpty()&&!edt_password_id.getText().toString().isEmpty()){
////                        getLoginDetailsByImeiNumber(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()),CommonUtils.getIMEInumber(LoginActivity.this));
////                        appHelper.getSharedPrefObj().edit().putBoolean(isTouched,true).apply();
////                    }
//////                    getLoginCredentials();
////                }
//
//
////                login(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()));
////                Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
////                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                startActivity(i);
////                finish();
//
//            }
//        });

//7277597d81181158
    }

    private void login(String toString, String md5) {

    }

    private void ui() {
//        appHelper.getSharedPrefObj().edit().remove(TestLoc).apply();
//        LocationDTO locationDTO = new LocationDTO();
//        Doc doc = new Doc();
//        doc.setLat("4");
//        doc.setLon("4");
//        Doc doc1 = new Doc();
//        doc1.setLat("4");
//        doc1.setLon("4");
//        ArrayList<Doc> docs = new ArrayList<>();
//        docs.add(doc);
//        docs.add(doc1);
//        locationDTO.setDoc(docs);
//        Gson gson = new Gson();
//        String jsonArray = gson.toJson(locationDTO, LocationDTO.class);
//        appHelper.getSharedPrefObj().edit().putString(TestLoc, jsonArray).apply();
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = packageInfo.versionName;
            if (!TextUtils.isEmpty(appVersion)) {
                tvAppVersion.setText(CommonUtils.getIMEInumber(this));
//                tvAppVersion.setText(appVersion);
                if (isLocationPermissionGranted(LoginActivity.this) ) {
                    startService(new Intent(this, FalogService.class));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        if(appHelper.getSharedPrefObj().getBoolean(IsFirstTime,true)){
            appHelper.getSharedPrefObj().edit().putBoolean(IsFirstTime,false).apply();
//            getLoginCredentials();
//                getLoginDetailsByImeiNumber(CommonUtils.getIMEInumber(LoginActivity.this));
        }

    }

    public void getLoginDetailsByImeiNumber(String userId,String pass,String imei) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            Log.e(TAG, "getLoginDetailsByImeiNumber: " + userId);
//            viewModel.logInServiceList("a6f14af3b58d267f");
//            Lo
//            viewModel.logInServiceList(userId,pass,"7277597d81181158");//rpr 1902
//            viewModel.logInServiceList(userId,pass,"ab787e4c79276833");//ho ho
//            viewModel.logInServiceList(userId,pass,"df3c07da4a4acf18");//krishna krishna
            viewModel.logInServiceList(userId,pass,imei);
            if (viewModel.getloginResponseDTOFromServerLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        LoginResponseDTO loginResponseDTOList = (LoginResponseDTO) o;
                        viewModel.getloginResponseDTOFromServerLiveData().removeObserver(this);
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (loginResponseDTOList != null) {

                            if(loginResponseDTOList.getMessage().isEmpty()){

                                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, String.valueOf(loginResponseDTOList.getId())).apply();

//                            for (int i = 0; i < loginResponseDTOList.size(); i++) {
                                App.createDBPath();
                                Log.d(TAG, "onChanged: " + App.createDBPath());
//                                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, String.valueOf(loginResponseDTOList.getId())).apply();
//                                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "110").apply();
//                                appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "101").apply();
                                // appHelper.getSharedPrefObj().edit().putString(DeviceUserID, "10").apply();

                                strUserDeviceId = String.valueOf(loginResponseDTOList.getId());
//                            Toast.makeText(appHelper,loginResponseDTOList.getId() , Toast.LENGTH_SHORT).show();
                                appHelper.getSharedPrefObj().edit().putString(DeviceUserName, loginResponseDTOList.getUserName()).apply();
                                edt_login_id.setText(loginResponseDTOList.getUserName());
//                            edt_password_id.setText(loginResponseDTOList.getUserName());
//                                appHelper.getSharedPrefObj().edit().putString(DeviceUserPwd, loginResponseDTOList.get(i).getPassword()).apply();

//                                tilUserId.setText(loginResponseDTOList.get(i).getUserName());//AM
//                                tilPassword.setText(loginResponseDTOList.get(i).getPassword());

                                Log.d(TAG, "onChanged: " + loginResponseDTOList.getId() + loginResponseDTOList.getUserName());
                                String token = "Bearer " + loginResponseDTOList.getAccessToken();
                                TokenAccess = token;
                                App.createDBPath();
                                // TODO: Inserting into key store
                                appHelper.getSharedPrefObj().edit().putString(accessToken, token).apply();
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        "Login Details fetched successfully", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                App.createDBPath();
                                                //   if (prefs.getBoolean("firstrun", true)) {
//                                                viewModel.getDeleteTablesFromLocal();
//                                                insertDailyRecord(token);
//                                                getCropDetailsFromServer();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        //   if (appHelper.isNetworkAvailable()) {
                                                        viewModel.getDeleteTablesFromLocal();
                                                        viewModel.deleteAlltablesFromTransactionSync();


                                                        getLoginDetailsByImeiNumber(token,false);
//                                                            } else {
//                                                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MESSAGE_NO_INTERNET_CONNECTION);
//                                                            }

                                                    }
                                                }, 500);
                                            }
                                            //}
                                        });

                            } else {
                                appHelper.getSharedPrefObj().edit().remove(DeviceUserID).apply();
                                appHelper.getSharedPrefObj().edit().remove(accessToken).apply();
                                appHelper.getSharedPrefObj().edit().remove(DeviceUserName).apply();
                                appHelper.getSharedPrefObj().edit().remove(DeviceUserPwd).apply();
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Unable to get login Details please contact Admin");
                            }

//                            }
                        }

                        else {
                            appHelper.getSharedPrefObj().edit().remove(DeviceUserID).apply();
                            appHelper.getSharedPrefObj().edit().remove(accessToken).apply();
                            appHelper.getSharedPrefObj().edit().remove(DeviceUserName).apply();
                            appHelper.getSharedPrefObj().edit().remove(DeviceUserPwd).apply();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Unable to get login Details please contact Admin");

                        }

                    }
                };
                viewModel.getloginResponseDTOFromServerLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getLoginDetailsByImeiNumber(String n,Boolean sync) {
        final AppAPI service = Retrofit_funtion_class.getClient().create(AppAPI.class);
        Call<MastersResponseDTO> callRetrofit = null;
        callRetrofit = service.getMasterSyncDetailsFromServer();
        // progressBar.setVisibility(View.VISIBLE);
        progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppCompatAlertDialogStyle);
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


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                                prefs.edit().putBoolean("firstrun", true).commit();

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
                                List<DivisionTable> divisionTableList = new ArrayList<>();
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

                                    divisionTableList.add(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);

//                                    viewModel.insertDivisionIntoLocalDBQuery(divisionTable);
                                }

                                viewModel.insertDivisionListIntoLocalDBQuery(divisionTableList);

                                List<SectionTable> sectionTableList = new ArrayList<>();
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
//                                    viewModel.insertSectionIntoLocalDBQuery(divisionTable);
                                    sectionTableList.add(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);
                                }

                                viewModel.insertSectionIntoLocalDBQuery(sectionTableList);


                                List<CircleTable> circleTableList = new ArrayList<>();
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
//                                    viewModel.insertCircleIntoLocalDBQuery(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);

                                    circleTableList.add(divisionTable);
                                }

                                viewModel.insertCircleIntoLocalDBQuery(circleTableList);

                                List<CropTable> cropTableList = new ArrayList<>();
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
//                                    viewModel.insertCropIntoLocalDBQuery(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);
                                    cropTableList.add(divisionTable);

                                }
                                viewModel.insertCropIntoLocalDBQuery(cropTableList);

                                List<BankTable> bankTableList = new ArrayList<>();
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
//                                    viewModel.insertBankIntoLocalDBQuery(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);
                                    bankTableList.add(divisionTable);
                                }
                                viewModel.insertBankIntoLocalDBQuery(bankTableList);

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
//
//                                for (int i = 0; i < mastersResponseDTO.getKeyValue().size(); i++) {
////                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
//                                    DistrictTable divisionTable = new DistrictTable();
//                                    divisionTable.setId(mastersResponseDTO.getDistrict().get(i).getId());
//                                    divisionTable.setCode(mastersResponseDTO.getDistrict().get(i).getCode());
//                                    divisionTable.setName(mastersResponseDTO.getDistrict().get(i).getName());
//                                    divisionTable.setStateId(mastersResponseDTO.getDistrict().get(i).getStateId());
//                                    divisionTable.setActive(mastersResponseDTO.getDistrict().get(i).getActive());
//                                    divisionTable.setCreatedDate(mastersResponseDTO.getDistrict().get(i).getCreatedDate());
//                                    divisionTable.setCreatedByUserId(mastersResponseDTO.getDistrict().get(i).getCreatedByUserId());
//                                    divisionTable.setUpdatedByUserId(mastersResponseDTO.getDistrict().get(i).getUpdatedByUserId());
//                                    divisionTable.setUpdatedDate(mastersResponseDTO.getDistrict().get(i).getUpdatedDate());
//                                    //insertClusterValuesIntoLocalDB(clusterHDr_value);
//                                    viewModel.insertDistrictIntoLocalDBQuery(divisionTable);
//                                    //getClusterHDRList.add(clusterHDr_value);
//                                }

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
                                    divisionTable.setLookupMstId(mastersResponseDTO.getLookupDtl().get(i).getLookupMstId());
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

                                for (int i = 0; i < mastersResponseDTO.getReson().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                    ResonForNotPlantingTable divisionTable = new ResonForNotPlantingTable();
                                    divisionTable.setId(mastersResponseDTO.getReson().get(i).getId());
                                    divisionTable.setCode(mastersResponseDTO.getReson().get(i).getCode());
                                    divisionTable.setActive(mastersResponseDTO.getReson().get(i).getActive());
                                    divisionTable.setName(mastersResponseDTO.getReson().get(i).getName());
                                    divisionTable.setCreatedDate(mastersResponseDTO.getReson().get(i).getCreatedDate());
                                    divisionTable.setCreatedByUserId(mastersResponseDTO.getReson().get(i).getCreatedByUserId());
                                    divisionTable.setUpdatedByUserId(mastersResponseDTO.getReson().get(i).getUpdatedByUserId());
                                    divisionTable.setUpdatedDate(mastersResponseDTO.getReson().get(i).getUpdatedDate());
                                    //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                    viewModel.insertReasonIntoLocalDBQuery(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);
                                }



                                for (int i = 0; i < mastersResponseDTO.getReson().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                    ResonForNotPlantingTable divisionTable = new ResonForNotPlantingTable();
                                    divisionTable.setId(mastersResponseDTO.getReson().get(i).getId());
                                    divisionTable.setCode(mastersResponseDTO.getReson().get(i).getCode());
                                    divisionTable.setActive(mastersResponseDTO.getReson().get(i).getActive());
                                    divisionTable.setName(mastersResponseDTO.getReson().get(i).getName());
                                    divisionTable.setCreatedDate(mastersResponseDTO.getReson().get(i).getCreatedDate());
                                    divisionTable.setCreatedByUserId(mastersResponseDTO.getReson().get(i).getCreatedByUserId());
                                    divisionTable.setUpdatedByUserId(mastersResponseDTO.getReson().get(i).getUpdatedByUserId());
                                    divisionTable.setUpdatedDate(mastersResponseDTO.getReson().get(i).getUpdatedDate());
                                    //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                    viewModel.insertReasonIntoLocalDBQuery(divisionTable);
                                    //getClusterHDRList.add(clusterHDr_value);
                                }
//
                                for (int i = 0; i < mastersResponseDTO.getKeyValue().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                                    KeyValue KeyValueTable = new KeyValue();
                                    KeyValueTable.setId(mastersResponseDTO.getKeyValue().get(i).getId());
                                    KeyValueTable.setKey(mastersResponseDTO.getKeyValue().get(i).getKey());
                                    KeyValueTable.setValue(mastersResponseDTO.getKeyValue().get(i).getValue());
                                    KeyValueTable.setValue1(mastersResponseDTO.getKeyValue().get(i).getValue1());
                                    KeyValueTable.setValue2(mastersResponseDTO.getKeyValue().get(i).getValue2());
                                    KeyValueTable.setValue3(mastersResponseDTO.getKeyValue().get(i).getValue3());
                                    KeyValueTable.setValue4(mastersResponseDTO.getKeyValue().get(i).getValue4());
                                    KeyValueTable.setValue5(mastersResponseDTO.getKeyValue().get(i).getValue5());


                                    KeyValueTable.setIsActive(mastersResponseDTO.getKeyValue().get(i).getIsActive());
                                    KeyValueTable.setCreatedDate(mastersResponseDTO.getKeyValue().get(i).getCreatedDate());
                                    KeyValueTable.setCreatedByUserId(mastersResponseDTO.getKeyValue().get(i).getCreatedByUserId());
                                    KeyValueTable.setUpdatedDate(mastersResponseDTO.getKeyValue().get(i).getUpdatedDate());
                                    KeyValueTable.setUpdatedByUserId(mastersResponseDTO.getKeyValue().get(i).getUpdatedByUserId());

                                    //insertClusterValuesIntoLocalDB(clusterHDr_value);
                                    viewModel.insertkeyvalueintoLocalDBQuery(KeyValueTable);
                                    //getClusterHDRList.add(clusterHDr_value);
                                }
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Master Sync Successfully", Toast.LENGTH_LONG).show();

                                if (appHelper.isNetworkAvailable()) {
                                    getSyncFarmerAllDataFromServer( );
//                            viewModel.getDeleteGetDataTablesFromLocal();
//                        if(sync){
//
//                        } else {
//                            getSyncFarmerAllDataFromServer( );
//                        }
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MESSAGE_NO_INTERNET_CONNECTION);
                                }
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

    public void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                mGetPermission.launch(intent);
                checkAllPermissions();
                // startActivityForResult(intent, 2296);
            } catch (Exception e) {
                e.printStackTrace();
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//                startActivityForResult(intent, 2296);
            }
        } else {
            checkAllPermissions();
        }
    }

    private boolean isPermissionGranted() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
            return readExternalStoragePermission == PackageManager.PERMISSION_DENIED;
        }

    }

    public void takePermission() {
        if (isPermissionGranted()) {
            App.createDBPath();
        } else {
            requestPermission();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 101) {
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    Toast.makeText(LoginActivity.this, "permission taken alredy", Toast.LENGTH_SHORT).show();
                } else {
                    takePermission();
                }
            }
        }
    }

    public static boolean isLocationPermissionGranted(final Context context) {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);

        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Build.VERSION.SDK_INT >= 28
                || (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
                && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    public void getSyncFarmerAllDataFromServer() {
        final AppAPI service = Retrofit_funtion_class.getClient().create(AppAPI.class);
        Call<TransactionSyncResponseDTO> callRetrofit = null;
        callRetrofit = service.getTransactionDetails(appHelper.getSharedPrefObj().getString(DeviceUserID,""),"2022-23");
        appHelper.getSharedPrefObj().edit().putString(SeasonCode,"2022-23").apply();
//        callRetrofit = service.getTransactionDetails("101");
//        callRetrofit = service.getTransactionDetails("28");
//        callRetrofit = service.getTransactionDetails("1");
//        callRetrofit = service.getTransactionDetails(CommonUtils.getIMEInumber(DashBoardActivity.this), appHelper.getSharedPrefObj().getString(accessToken, ""));
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppCompatAlertDialogStyle);
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

                   List<AddFarmerTable> farmerTableList = new ArrayList<>();
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
//                        viewModel.insertFarmerIntoLocalDBQuery(divisionTable);
                        farmerTableList.add(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    viewModel.insertFarmerIntoLocalDBQuery(farmerTableList);

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

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc20().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddD20Table divisionTable = new AddD20Table();
                        divisionTable.setServerStatus("1");
                        divisionTable.setId(transactionSyncResponseDTO.getDoc20().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc20().get(i).getSeasonCode());
                        divisionTable.setCropTypeId(transactionSyncResponseDTO.getDoc20().get(i).getCropTypeId());
                        divisionTable.setOfferedNo(transactionSyncResponseDTO.getDoc20().get(i).getOfferedNo());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getDoc20().get(i).getFarmerCode());
                        divisionTable.setFatherName(transactionSyncResponseDTO.getDoc20().get(i).getFatherName());
                        divisionTable.setRelationShipTypeId(transactionSyncResponseDTO.getDoc20().get(i).getRelationShipTypeId());
                        divisionTable.setNominee(transactionSyncResponseDTO.getDoc20().get(i).getNominee());
                        divisionTable.setGuarantor1(transactionSyncResponseDTO.getDoc20().get(i).getGuarantor1());
                        divisionTable.setGuarantor2(transactionSyncResponseDTO.getDoc20().get(i).getGuarantor2());
                        divisionTable.setGuarantor3(transactionSyncResponseDTO.getDoc20().get(i).getGuarantor3());
                        divisionTable.setFarmerVillageId(transactionSyncResponseDTO.getDoc20().get(i).getFarmerVillageId());
                        divisionTable.setPlotVillageId(transactionSyncResponseDTO.getDoc20().get(i).getPlotVillageId());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc20().get(i).getPlotNo());
                        divisionTable.setPlantingDate(transactionSyncResponseDTO.getDoc20().get(i).getPlantingDate());
                        divisionTable.setPlotTypeId(transactionSyncResponseDTO.getDoc20().get(i).getPlotTypeId());
                        divisionTable.setPlantTypeId(transactionSyncResponseDTO.getDoc20().get(i).getPlantTypeId());
                        divisionTable.setPlantSubTypeId(transactionSyncResponseDTO.getDoc20().get(i).getPlantSubTypeId());
                        divisionTable.setVarietyId(transactionSyncResponseDTO.getDoc20().get(i).getVarietyId());
                        divisionTable.setSurveyNo(transactionSyncResponseDTO.getDoc20().get(i).getSurveyNo());
                        divisionTable.setFieldName(transactionSyncResponseDTO.getDoc20().get(i).getFieldName());
                        divisionTable.setBIRNo(transactionSyncResponseDTO.getDoc20().get(i).getBIRNo());
                        divisionTable.setBIRDate(transactionSyncResponseDTO.getDoc20().get(i).getBIRDate());
                        divisionTable.setTotalArea(transactionSyncResponseDTO.getDoc20().get(i).getTotalArea());
                        divisionTable.setCultivatedArea(transactionSyncResponseDTO.getDoc20().get(i).getCultivatedArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getDoc20().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getDoc20().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getDoc20().get(i).getMeasureArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getDoc20().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getDoc20().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getDoc20().get(i).getMeasureArea());
                        divisionTable.setAggrementedArea(transactionSyncResponseDTO.getDoc20().get(i).getAggrementedArea());
                        divisionTable.setNetArea(transactionSyncResponseDTO.getDoc20().get(i).getNetArea());
                        divisionTable.setAgreedTon(transactionSyncResponseDTO.getDoc20().get(i).getAgreedTon());
                        divisionTable.setEstimatedTon(transactionSyncResponseDTO.getDoc20().get(i).getEstimatedTon());
                        divisionTable.setIrrigationTypeId(transactionSyncResponseDTO.getDoc20().get(i).getIrrigationTypeId());
                        divisionTable.setSoilTypeId(transactionSyncResponseDTO.getDoc20().get(i).getSoilTypeId());
                        divisionTable.setSpacingId(transactionSyncResponseDTO.getDoc20().get(i).getSpacingId());
                        divisionTable.setSettsTypeId(transactionSyncResponseDTO.getDoc20().get(i).getSettsTypeId());
                        divisionTable.setPreviousCropId(transactionSyncResponseDTO.getDoc20().get(i).getPreviousCropId());
                        divisionTable.setInterCropId(transactionSyncResponseDTO.getDoc20().get(i).getInterCropId());
                        divisionTable.setSeedMaterialUsedId(transactionSyncResponseDTO.getDoc20().get(i).getSeedMaterialUsedId());
                        divisionTable.setPlotExistOnId(transactionSyncResponseDTO.getDoc20().get(i).getPlotExistOnId());
                        divisionTable.setDistanceFromPlot(transactionSyncResponseDTO.getDoc20().get(i).getDistanceFromPlot());
                        divisionTable.setProfile(transactionSyncResponseDTO.getDoc20().get(i).getProfile());
                        divisionTable.setIsSettsHotWaterTreatment(transactionSyncResponseDTO.getDoc20().get(i).getIsSettsHotWaterTreatment());
                        divisionTable.setIsPreviousRedPlot(transactionSyncResponseDTO.getDoc20().get(i).getIsPreviousRedPlot());
                        divisionTable.setIsDustApplied(transactionSyncResponseDTO.getDoc20().get(i).getIsDustApplied());
                        divisionTable.setIsBasalFertilization(transactionSyncResponseDTO.getDoc20().get(i).getIsBasalFertilization());
                        divisionTable.setIsTrashMulching(transactionSyncResponseDTO.getDoc20().get(i).getIsTrashMulching());
                        divisionTable.setIsCompositeFormYard(transactionSyncResponseDTO.getDoc20().get(i).getIsCompositeFormYard());
                        divisionTable.setIsFilterPressMud(transactionSyncResponseDTO.getDoc20().get(i).getIsFilterPressMud());
                        divisionTable.setIsGreenManures(transactionSyncResponseDTO.getDoc20().get(i).getIsGreenManures());
                        divisionTable.setIsMicronutrientDeficiency(transactionSyncResponseDTO.getDoc20().get(i).getIsMicronutrientDeficiency());
                        divisionTable.setIsGapsFilled(transactionSyncResponseDTO.getDoc20().get(i).getIsGapsFilled());
                        divisionTable.setInspectionDate(transactionSyncResponseDTO.getDoc20().get(i).getInspectionDate());
                        divisionTable.setWeedStatusId(transactionSyncResponseDTO.getDoc20().get(i).getWeedStatusId());
                        divisionTable.setActionPlanId(transactionSyncResponseDTO.getDoc20().get(i).getActionPlanId());
                        divisionTable.setPerishableReasonId(transactionSyncResponseDTO.getDoc20().get(i).getPerishableReasonId());
                        divisionTable.setPerishedArea(transactionSyncResponseDTO.getDoc20().get(i).getPerishedArea());
                        divisionTable.setNotGrownArea(transactionSyncResponseDTO.getDoc20().get(i).getNotGrownArea());
                        divisionTable.setHarvestingArea(transactionSyncResponseDTO.getDoc20().get(i).getHarvestingArea());
                        divisionTable.setPoorCropArea(transactionSyncResponseDTO.getDoc20().get(i).getPoorCropArea());
                        divisionTable.setRemovedArea(transactionSyncResponseDTO.getDoc20().get(i).getRemovedArea());
                        divisionTable.setBioFertilizerAppliedArea(transactionSyncResponseDTO.getDoc20().get(i).getBioFertilizerAppliedArea());
                        divisionTable.setDeTrashingArea(transactionSyncResponseDTO.getDoc20().get(i).getDeTrashingArea());
                        divisionTable.setDeepPloughedArea(transactionSyncResponseDTO.getDoc20().get(i).getDeepPloughedArea());
                        divisionTable.setEarthingUpArea(transactionSyncResponseDTO.getDoc20().get(i).getEarthingUpArea());
                        divisionTable.setRatoonManagedUsedArea(transactionSyncResponseDTO.getDoc20().get(i).getRatoonManagedUsedArea());
                        divisionTable.setTrashShedderArea(transactionSyncResponseDTO.getDoc20().get(i).getTrashShedderArea());
                        divisionTable.setLoadShedderArea(transactionSyncResponseDTO.getDoc20().get(i).getLoadShedderArea());
                        divisionTable.setIsSeedArea(transactionSyncResponseDTO.getDoc20().get(i).getIsSeedArea());
                        divisionTable.setDivertToSelfSeed(transactionSyncResponseDTO.getDoc20().get(i).getDivertToSelfSeed());
                        divisionTable.setDivertToOthers(transactionSyncResponseDTO.getDoc20().get(i).getDivertToOthers());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getDoc20().get(i).getPlantingMethodId());
                        divisionTable.setSync(true);
//                        divisionTable.setSchGroupNo(transactionSyncResponseDTO.getPlot().get(i).getSchGroupNo());
//                        divisionTable.setBrix(transactionSyncResponseDTO.getPlot().get(i).getBrix());
//                        divisionTable.setPol(transactionSyncResponseDTO.getPlot().get(i).getPol());
//                        divisionTable.setPurity(transactionSyncResponseDTO.getPlot().get(i).getPurity());
//                        divisionTable.setCCS(transactionSyncResponseDTO.getPlot().get(i).getCCS());
//                        divisionTable.setNoOfSamples(transactionSyncResponseDTO.getPlot().get(i).getNoOfSamples());
//                        divisionTable.setSampleDate(transactionSyncResponseDTO.getPlot().get(i).getSampleDate());
//                        divisionTable.setCuttingOrderNo(transactionSyncResponseDTO.getPlot().get(i).getCuttingOrderNo());
//                        divisionTable.setProppingArea(transactionSyncResponseDTO.getPlot().get(i).getProppingArea());
//                        divisionTable.setProppingStageId(transactionSyncResponseDTO.getPlot().get(i).getProppingStageId());
//                        divisionTable.setTransferArea(transactionSyncResponseDTO.getPlot().get(i).getTransferArea());
//                        divisionTable.setIsRegistered(transactionSyncResponseDTO.getPlot().get(i).getIsRegistered());
//                        divisionTable.setIsOver(transactionSyncResponseDTO.getPlot().get(i).getIsOver());
//                        divisionTable.setPlotOverReasonId(transactionSyncResponseDTO.getPlot().get(i).getPlotOverReasonId());
//                        divisionTable.setPlotOverDate(transactionSyncResponseDTO.getPlot().get(i).getPlotOverDate());
//                        divisionTable.setIsActive(transactionSyncResponseDTO.getPlot().get(i).getIsActive());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getDoc20().get(i).getPlantingMethodId());
//                        divisionTable.setStage(transactionSyncResponseDTO.getPlot().get(i).getStage());
//                        divisionTable.setImageUrl(transactionSyncResponseDTO.getPlot().get(i).getImageUrl());
//                        divisionTable.setLatitude(transactionSyncResponseDTO.getPlot().get(i).getLatitude());
//                        divisionTable.setLongitude(transactionSyncResponseDTO.getPlot().get(i).getLongitude());
//                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getPlot().get(i).getCreatedDate());
//                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getPlot().get(i).getCreatedByUserId());
//                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getPlot().get(i).getUpdatedByUserId());
//                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getPlot().get(i).getUpdatedDate());
//                        //insertClusterValuesIntoLocalDB(clusterHDr_value);

                        viewModel.insertD20IntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc10().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddD10Table divisionTable = new AddD10Table();
                        divisionTable.setServerStatus("1");
                        divisionTable.setId(transactionSyncResponseDTO.getDoc10().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc10().get(i).getSeasonCode());
                        divisionTable.setCropTypeId(transactionSyncResponseDTO.getDoc10().get(i).getCropTypeId());
                        divisionTable.setOfferedNo(transactionSyncResponseDTO.getDoc10().get(i).getOfferedNo());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getDoc10().get(i).getFarmerCode());
                        divisionTable.setFatherName(transactionSyncResponseDTO.getDoc10().get(i).getFatherName());
                        divisionTable.setRelationShipTypeId(transactionSyncResponseDTO.getDoc10().get(i).getRelationShipTypeId());
                        divisionTable.setNominee(transactionSyncResponseDTO.getDoc10().get(i).getNominee());
                        divisionTable.setGuarantor1(transactionSyncResponseDTO.getDoc10().get(i).getGuarantor1());
                        divisionTable.setGuarantor2(transactionSyncResponseDTO.getDoc10().get(i).getGuarantor2());
                        divisionTable.setGuarantor3(transactionSyncResponseDTO.getDoc10().get(i).getGuarantor3());
                        divisionTable.setFarmerVillageId(transactionSyncResponseDTO.getDoc10().get(i).getFarmerVillageId());
                        divisionTable.setPlotVillageId(transactionSyncResponseDTO.getDoc10().get(i).getPlotVillageId());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc10().get(i).getPlotNo());
                        divisionTable.setPlantingDate(transactionSyncResponseDTO.getDoc10().get(i).getPlantingDate());
                        divisionTable.setPlotTypeId(transactionSyncResponseDTO.getDoc10().get(i).getPlotTypeId());
                        divisionTable.setPlantTypeId(transactionSyncResponseDTO.getDoc10().get(i).getPlantTypeId());
                        divisionTable.setPlantSubTypeId(transactionSyncResponseDTO.getDoc10().get(i).getPlantSubTypeId());
                        divisionTable.setVarietyId(transactionSyncResponseDTO.getDoc10().get(i).getVarietyId());
                        divisionTable.setSurveyNo(transactionSyncResponseDTO.getDoc10().get(i).getSurveyNo());
                        divisionTable.setFieldName(transactionSyncResponseDTO.getDoc10().get(i).getFieldName());
                        divisionTable.setBIRNo(transactionSyncResponseDTO.getDoc10().get(i).getBIRNo());
                        divisionTable.setBIRDate(transactionSyncResponseDTO.getDoc10().get(i).getBIRDate());
                        divisionTable.setTotalArea(transactionSyncResponseDTO.getDoc10().get(i).getTotalArea());
                        divisionTable.setCultivatedArea(transactionSyncResponseDTO.getDoc10().get(i).getCultivatedArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getDoc10().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getDoc10().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getDoc10().get(i).getMeasureArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getDoc10().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getDoc10().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getDoc10().get(i).getMeasureArea());
                        divisionTable.setAggrementedArea(transactionSyncResponseDTO.getDoc10().get(i).getAggrementedArea());
                        divisionTable.setNetArea(transactionSyncResponseDTO.getDoc10().get(i).getNetArea());
                        divisionTable.setAgreedTon(transactionSyncResponseDTO.getDoc10().get(i).getAgreedTon());
                        divisionTable.setEstimatedTon(transactionSyncResponseDTO.getDoc10().get(i).getEstimatedTon());
                        divisionTable.setIrrigationTypeId(transactionSyncResponseDTO.getDoc10().get(i).getIrrigationTypeId());
                        divisionTable.setSoilTypeId(transactionSyncResponseDTO.getDoc10().get(i).getSoilTypeId());
                        divisionTable.setSpacingId(transactionSyncResponseDTO.getDoc10().get(i).getSpacingId());
                        divisionTable.setSettsTypeId(transactionSyncResponseDTO.getDoc10().get(i).getSettsTypeId());
                        divisionTable.setPreviousCropId(transactionSyncResponseDTO.getDoc10().get(i).getPreviousCropId());
                        divisionTable.setInterCropId(transactionSyncResponseDTO.getDoc10().get(i).getInterCropId());
                        divisionTable.setSeedMaterialUsedId(transactionSyncResponseDTO.getDoc10().get(i).getSeedMaterialUsedId());
                        divisionTable.setPlotExistOnId(transactionSyncResponseDTO.getDoc10().get(i).getPlotExistOnId());
                        divisionTable.setDistanceFromPlot(transactionSyncResponseDTO.getDoc10().get(i).getDistanceFromPlot());
                        divisionTable.setProfile(transactionSyncResponseDTO.getDoc10().get(i).getProfile());
                        divisionTable.setIsSettsHotWaterTreatment(transactionSyncResponseDTO.getDoc10().get(i).getIsSettsHotWaterTreatment());
                        divisionTable.setIsPreviousRedPlot(transactionSyncResponseDTO.getDoc10().get(i).getIsPreviousRedPlot());
                        divisionTable.setIsDustApplied(transactionSyncResponseDTO.getDoc10().get(i).getIsDustApplied());
                        divisionTable.setIsBasalFertilization(transactionSyncResponseDTO.getDoc10().get(i).getIsBasalFertilization());
                        divisionTable.setIsTrashMulching(transactionSyncResponseDTO.getDoc10().get(i).getIsTrashMulching());
                        divisionTable.setIsCompositeFormYard(transactionSyncResponseDTO.getDoc10().get(i).getIsCompositeFormYard());
                        divisionTable.setIsFilterPressMud(transactionSyncResponseDTO.getDoc10().get(i).getIsFilterPressMud());
                        divisionTable.setIsGreenManures(transactionSyncResponseDTO.getDoc10().get(i).getIsGreenManures());
                        divisionTable.setIsMicronutrientDeficiency(transactionSyncResponseDTO.getDoc10().get(i).getIsMicronutrientDeficiency());
                        divisionTable.setIsGapsFilled(transactionSyncResponseDTO.getDoc10().get(i).getIsGapsFilled());
                        divisionTable.setInspectionDate(transactionSyncResponseDTO.getDoc10().get(i).getInspectionDate());
                        divisionTable.setWeedStatusId(transactionSyncResponseDTO.getDoc10().get(i).getWeedStatusId());
                        divisionTable.setActionPlanId(transactionSyncResponseDTO.getDoc10().get(i).getActionPlanId());
                        divisionTable.setPerishableReasonId(transactionSyncResponseDTO.getDoc10().get(i).getPerishableReasonId());
                        divisionTable.setPerishedArea(transactionSyncResponseDTO.getDoc10().get(i).getPerishedArea());
                        divisionTable.setNotGrownArea(transactionSyncResponseDTO.getDoc10().get(i).getNotGrownArea());
                        divisionTable.setHarvestingArea(transactionSyncResponseDTO.getDoc10().get(i).getHarvestingArea());
                        divisionTable.setPoorCropArea(transactionSyncResponseDTO.getDoc10().get(i).getPoorCropArea());
                        divisionTable.setRemovedArea(transactionSyncResponseDTO.getDoc10().get(i).getRemovedArea());
                        divisionTable.setBioFertilizerAppliedArea(transactionSyncResponseDTO.getDoc10().get(i).getBioFertilizerAppliedArea());
                        divisionTable.setDeTrashingArea(transactionSyncResponseDTO.getDoc10().get(i).getDeTrashingArea());
                        divisionTable.setDeepPloughedArea(transactionSyncResponseDTO.getDoc10().get(i).getDeepPloughedArea());
                        divisionTable.setEarthingUpArea(transactionSyncResponseDTO.getDoc10().get(i).getEarthingUpArea());
                        divisionTable.setRatoonManagedUsedArea(transactionSyncResponseDTO.getDoc10().get(i).getRatoonManagedUsedArea());
                        divisionTable.setTrashShedderArea(transactionSyncResponseDTO.getDoc10().get(i).getTrashShedderArea());
                        divisionTable.setLoadShedderArea(transactionSyncResponseDTO.getDoc10().get(i).getLoadShedderArea());
                        divisionTable.setIsSeedArea(transactionSyncResponseDTO.getDoc10().get(i).getIsSeedArea());
                        divisionTable.setDivertToSelfSeed(transactionSyncResponseDTO.getDoc10().get(i).getDivertToSelfSeed());
                        divisionTable.setDivertToOthers(transactionSyncResponseDTO.getDoc10().get(i).getDivertToOthers());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getDoc10().get(i).getPlantingMethodId());
                        divisionTable.setSync(true);
//                        divisionTable.setSchGroupNo(transactionSyncResponseDTO.getPlot().get(i).getSchGroupNo());
//                        divisionTable.setBrix(transactionSyncResponseDTO.getPlot().get(i).getBrix());
//                        divisionTable.setPol(transactionSyncResponseDTO.getPlot().get(i).getPol());
//                        divisionTable.setPurity(transactionSyncResponseDTO.getPlot().get(i).getPurity());
//                        divisionTable.setCCS(transactionSyncResponseDTO.getPlot().get(i).getCCS());
//                        divisionTable.setNoOfSamples(transactionSyncResponseDTO.getPlot().get(i).getNoOfSamples());
//                        divisionTable.setSampleDate(transactionSyncResponseDTO.getPlot().get(i).getSampleDate());
//                        divisionTable.setCuttingOrderNo(transactionSyncResponseDTO.getPlot().get(i).getCuttingOrderNo());
//                        divisionTable.setProppingArea(transactionSyncResponseDTO.getPlot().get(i).getProppingArea());
//                        divisionTable.setProppingStageId(transactionSyncResponseDTO.getPlot().get(i).getProppingStageId());
//                        divisionTable.setTransferArea(transactionSyncResponseDTO.getPlot().get(i).getTransferArea());
//                        divisionTable.setIsRegistered(transactionSyncResponseDTO.getPlot().get(i).getIsRegistered());
//                        divisionTable.setIsOver(transactionSyncResponseDTO.getPlot().get(i).getIsOver());
//                        divisionTable.setPlotOverReasonId(transactionSyncResponseDTO.getPlot().get(i).getPlotOverReasonId());
//                        divisionTable.setPlotOverDate(transactionSyncResponseDTO.getPlot().get(i).getPlotOverDate());
//                        divisionTable.setIsActive(transactionSyncResponseDTO.getPlot().get(i).getIsActive());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getDoc10().get(i).getPlantingMethodId());
//                        divisionTable.setStage(transactionSyncResponseDTO.getPlot().get(i).getStage());
//                        divisionTable.setImageUrl(transactionSyncResponseDTO.getPlot().get(i).getImageUrl());
//                        divisionTable.setLatitude(transactionSyncResponseDTO.getPlot().get(i).getLatitude());
//                        divisionTable.setLongitude(transactionSyncResponseDTO.getPlot().get(i).getLongitude());
                       divisionTable.setCreatedDate(transactionSyncResponseDTO.getDoc10().get(i).getCreatedDate());
                     divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getDoc10().get(i).getCreatedByUserId());
                       divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getDoc10().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getDoc10().get(i).getUpdatedDate());
//                        //insertClusterValuesIntoLocalDB(clusterHDr_value);

                        viewModel.insertD10IntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc30().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddD30Table divisionTable = new AddD30Table();
                        divisionTable.setServerStatus("1");
                        divisionTable.setId(transactionSyncResponseDTO.getDoc30().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc30().get(i).getSeasonCode());
                        divisionTable.setCropTypeId(transactionSyncResponseDTO.getDoc30().get(i).getCropTypeId());
                        divisionTable.setOfferedNo(transactionSyncResponseDTO.getDoc30().get(i).getOfferedNo());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getDoc30().get(i).getFarmerCode());
                        divisionTable.setFatherName(transactionSyncResponseDTO.getDoc30().get(i).getFatherName());
                        divisionTable.setRelationShipTypeId(transactionSyncResponseDTO.getDoc30().get(i).getRelationShipTypeId());
                        divisionTable.setNominee(transactionSyncResponseDTO.getDoc30().get(i).getNominee());
                        divisionTable.setGuarantor1(transactionSyncResponseDTO.getDoc30().get(i).getGuarantor1());
                        divisionTable.setGuarantor2(transactionSyncResponseDTO.getDoc30().get(i).getGuarantor2());
                        divisionTable.setGuarantor3(transactionSyncResponseDTO.getDoc30().get(i).getGuarantor3());
                        divisionTable.setFarmerVillageId(transactionSyncResponseDTO.getDoc30().get(i).getFarmerVillageId());
                        divisionTable.setPlotVillageId(transactionSyncResponseDTO.getDoc30().get(i).getPlotVillageId());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc30().get(i).getPlotNo());
                        divisionTable.setPlantingDate(transactionSyncResponseDTO.getDoc30().get(i).getPlantingDate());
                        divisionTable.setPlotTypeId(transactionSyncResponseDTO.getDoc30().get(i).getPlotTypeId());
                        divisionTable.setPlantTypeId(transactionSyncResponseDTO.getDoc30().get(i).getPlantTypeId());
                        divisionTable.setPlantSubTypeId(transactionSyncResponseDTO.getDoc30().get(i).getPlantSubTypeId());
                        divisionTable.setVarietyId(transactionSyncResponseDTO.getDoc30().get(i).getVarietyId());
                        divisionTable.setSurveyNo(transactionSyncResponseDTO.getDoc30().get(i).getSurveyNo());
                        divisionTable.setFieldName(transactionSyncResponseDTO.getDoc30().get(i).getFieldName());
                        divisionTable.setBIRNo(transactionSyncResponseDTO.getDoc30().get(i).getBIRNo());
                        divisionTable.setBIRDate(transactionSyncResponseDTO.getDoc30().get(i).getBIRDate());
                        divisionTable.setTotalArea(transactionSyncResponseDTO.getDoc30().get(i).getTotalArea());
                        divisionTable.setCultivatedArea(transactionSyncResponseDTO.getDoc30().get(i).getCultivatedArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getDoc30().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getDoc30().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getDoc30().get(i).getMeasureArea());
                        divisionTable.setDemoPlotArea(transactionSyncResponseDTO.getDoc30().get(i).getDemoPlotArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getDoc30().get(i).getReportedArea());
                        divisionTable.setMeasureArea(transactionSyncResponseDTO.getDoc30().get(i).getMeasureArea());
                        divisionTable.setAggrementedArea(transactionSyncResponseDTO.getDoc30().get(i).getAggrementedArea());
                        divisionTable.setNetArea(transactionSyncResponseDTO.getDoc30().get(i).getNetArea());
                        divisionTable.setAgreedTon(transactionSyncResponseDTO.getDoc30().get(i).getAgreedTon());
                        divisionTable.setEstimatedTon(transactionSyncResponseDTO.getDoc30().get(i).getEstimatedTon());
                        divisionTable.setIrrigationTypeId(transactionSyncResponseDTO.getDoc30().get(i).getIrrigationTypeId());
                        divisionTable.setSoilTypeId(transactionSyncResponseDTO.getDoc30().get(i).getSoilTypeId());
                        divisionTable.setSpacingId(transactionSyncResponseDTO.getDoc30().get(i).getSpacingId());
                        divisionTable.setSettsTypeId(transactionSyncResponseDTO.getDoc30().get(i).getSettsTypeId());
                        divisionTable.setPreviousCropId(transactionSyncResponseDTO.getDoc30().get(i).getPreviousCropId());
                        divisionTable.setInterCropId(transactionSyncResponseDTO.getDoc30().get(i).getInterCropId());
                        divisionTable.setSeedMaterialUsedId(transactionSyncResponseDTO.getDoc30().get(i).getSeedMaterialUsedId());
                        divisionTable.setPlotExistOnId(transactionSyncResponseDTO.getDoc30().get(i).getPlotExistOnId());
                        divisionTable.setDistanceFromPlot(transactionSyncResponseDTO.getDoc30().get(i).getDistanceFromPlot());
                        divisionTable.setProfile(transactionSyncResponseDTO.getDoc30().get(i).getProfile());
                        divisionTable.setIsSettsHotWaterTreatment(transactionSyncResponseDTO.getDoc30().get(i).getIsSettsHotWaterTreatment());
                        divisionTable.setIsPreviousRedPlot(transactionSyncResponseDTO.getDoc30().get(i).getIsPreviousRedPlot());
                        divisionTable.setIsDustApplied(transactionSyncResponseDTO.getDoc30().get(i).getIsDustApplied());
                        divisionTable.setIsBasalFertilization(transactionSyncResponseDTO.getDoc30().get(i).getIsBasalFertilization());
                        divisionTable.setIsTrashMulching(transactionSyncResponseDTO.getDoc30().get(i).getIsTrashMulching());
                        divisionTable.setIsCompositeFormYard(transactionSyncResponseDTO.getDoc30().get(i).getIsCompositeFormYard());
                        divisionTable.setIsFilterPressMud(transactionSyncResponseDTO.getDoc30().get(i).getIsFilterPressMud());
                        divisionTable.setIsGreenManures(transactionSyncResponseDTO.getDoc30().get(i).getIsGreenManures());
                        divisionTable.setIsMicronutrientDeficiency(transactionSyncResponseDTO.getDoc30().get(i).getIsMicronutrientDeficiency());
                        divisionTable.setIsGapsFilled(transactionSyncResponseDTO.getDoc30().get(i).getIsGapsFilled());
                        divisionTable.setInspectionDate(transactionSyncResponseDTO.getDoc30().get(i).getInspectionDate());
                        divisionTable.setWeedStatusId(transactionSyncResponseDTO.getDoc30().get(i).getWeedStatusId());
                        divisionTable.setActionPlanId(transactionSyncResponseDTO.getDoc30().get(i).getActionPlanId());
                        divisionTable.setPerishableReasonId(transactionSyncResponseDTO.getDoc30().get(i).getPerishableReasonId());
                        divisionTable.setPerishedArea(transactionSyncResponseDTO.getDoc30().get(i).getPerishedArea());
                        divisionTable.setNotGrownArea(transactionSyncResponseDTO.getDoc30().get(i).getNotGrownArea());
                        divisionTable.setHarvestingArea(transactionSyncResponseDTO.getDoc30().get(i).getHarvestingArea());
                        divisionTable.setPoorCropArea(transactionSyncResponseDTO.getDoc30().get(i).getPoorCropArea());
                        divisionTable.setRemovedArea(transactionSyncResponseDTO.getDoc30().get(i).getRemovedArea());
                        divisionTable.setBioFertilizerAppliedArea(transactionSyncResponseDTO.getDoc30().get(i).getBioFertilizerAppliedArea());
                        divisionTable.setDeTrashingArea(transactionSyncResponseDTO.getDoc30().get(i).getDeTrashingArea());
                        divisionTable.setDeepPloughedArea(transactionSyncResponseDTO.getDoc30().get(i).getDeepPloughedArea());
                        divisionTable.setEarthingUpArea(transactionSyncResponseDTO.getDoc30().get(i).getEarthingUpArea());
                        divisionTable.setRatoonManagedUsedArea(transactionSyncResponseDTO.getDoc30().get(i).getRatoonManagedUsedArea());
                        divisionTable.setTrashShedderArea(transactionSyncResponseDTO.getDoc30().get(i).getTrashShedderArea());
                        divisionTable.setLoadShedderArea(transactionSyncResponseDTO.getDoc30().get(i).getLoadShedderArea());
                        divisionTable.setIsSeedArea(transactionSyncResponseDTO.getDoc30().get(i).getIsSeedArea());
                        divisionTable.setDivertToSelfSeed(transactionSyncResponseDTO.getDoc30().get(i).getDivertToSelfSeed());
                        divisionTable.setDivertToOthers(transactionSyncResponseDTO.getDoc30().get(i).getDivertToOthers());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getDoc30().get(i).getPlantingMethodId());
                        divisionTable.setSync(true);
//                        divisionTable.setSchGroupNo(transactionSyncResponseDTO.getPlot().get(i).getSchGroupNo());
//                        divisionTable.setBrix(transactionSyncResponseDTO.getPlot().get(i).getBrix());
//                        divisionTable.setPol(transactionSyncResponseDTO.getPlot().get(i).getPol());
//                        divisionTable.setPurity(transactionSyncResponseDTO.getPlot().get(i).getPurity());
//                        divisionTable.setCCS(transactionSyncResponseDTO.getPlot().get(i).getCCS());
//                        divisionTable.setNoOfSamples(transactionSyncResponseDTO.getPlot().get(i).getNoOfSamples());
//                        divisionTable.setSampleDate(transactionSyncResponseDTO.getPlot().get(i).getSampleDate());
//                        divisionTable.setCuttingOrderNo(transactionSyncResponseDTO.getPlot().get(i).getCuttingOrderNo());
//                        divisionTable.setProppingArea(transactionSyncResponseDTO.getPlot().get(i).getProppingArea());
//                        divisionTable.setProppingStageId(transactionSyncResponseDTO.getPlot().get(i).getProppingStageId());
//                        divisionTable.setTransferArea(transactionSyncResponseDTO.getPlot().get(i).getTransferArea());
//                        divisionTable.setIsRegistered(transactionSyncResponseDTO.getPlot().get(i).getIsRegistered());
//                        divisionTable.setIsOver(transactionSyncResponseDTO.getPlot().get(i).getIsOver());
//                        divisionTable.setPlotOverReasonId(transactionSyncResponseDTO.getPlot().get(i).getPlotOverReasonId());
//                        divisionTable.setPlotOverDate(transactionSyncResponseDTO.getPlot().get(i).getPlotOverDate());
//                        divisionTable.setIsActive(transactionSyncResponseDTO.getPlot().get(i).getIsActive());
                        divisionTable.setPlantingMethodId(transactionSyncResponseDTO.getDoc30().get(i).getPlantingMethodId());
//                        divisionTable.setStage(transactionSyncResponseDTO.getPlot().get(i).getStage());
//                        divisionTable.setImageUrl(transactionSyncResponseDTO.getPlot().get(i).getImageUrl());
//                        divisionTable.setLatitude(transactionSyncResponseDTO.getPlot().get(i).getLatitude());
//                        divisionTable.setLongitude(transactionSyncResponseDTO.getPlot().get(i).getLongitude());
//                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getPlot().get(i).getCreatedDate());
//                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getPlot().get(i).getCreatedByUserId());
//                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getPlot().get(i).getUpdatedByUserId());
//                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getPlot().get(i).getUpdatedDate());
//                        //insertClusterValuesIntoLocalDB(clusterHDr_value);

                        viewModel.insertD30IntoLocalDBQuery(divisionTable);
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
                        divisionTable.setSync(true);
                        divisionTable.setStage(transactionSyncResponseDTO.getGeoBoundaries().get(i).getStage());
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertPlotGeoIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getPlotOffer().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddPlotOfferTable divisionTable = new AddPlotOfferTable();
                        divisionTable.setId(transactionSyncResponseDTO.getPlotOffer().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getPlotOffer().get(i).getSeasonCode());
                        divisionTable.setNewFarmer(transactionSyncResponseDTO.getPlotOffer().get(i).getNewFarmer());
                        divisionTable.setOfferNo(transactionSyncResponseDTO.getPlotOffer().get(i).getOfferNo());
                        divisionTable.setOfferDate(transactionSyncResponseDTO.getPlotOffer().get(i).getOfferDate());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getPlotOffer().get(i).getFarmerCode());
                        divisionTable.setFarmerName(transactionSyncResponseDTO.getPlotOffer().get(i).getFarmerName());
                        divisionTable.setFatherName(transactionSyncResponseDTO.getPlotOffer().get(i).getFatherName());
                        divisionTable.setFarmerVillageId(transactionSyncResponseDTO.getPlotOffer().get(i).getFarmerVillageId());
                        divisionTable.setPlotVillageId(transactionSyncResponseDTO.getPlotOffer().get(i).getPlotVillageId());
                        divisionTable.setPlotIndNo(transactionSyncResponseDTO.getPlotOffer().get(i).getPlotIndNo());
                        divisionTable.setPlantTypeId(transactionSyncResponseDTO.getPlotOffer().get(i).getPlantTypeId());
                        divisionTable.setExpectedVarityId(transactionSyncResponseDTO.getPlotOffer().get(i).getExpectedVarityId());
                        divisionTable.setExpectedPlantingDate(transactionSyncResponseDTO.getPlotOffer().get(i).getExpectedPlantingDate());
                        divisionTable.setExpectedArea(transactionSyncResponseDTO.getPlotOffer().get(i).getExpectedArea());
                        divisionTable.setReportedArea(transactionSyncResponseDTO.getPlotOffer().get(i).getReportedArea());
                        divisionTable.setReasonForNotPlantingId(transactionSyncResponseDTO.getPlotOffer().get(i).getReasonForNotPlantingId());
                        divisionTable.setActive(transactionSyncResponseDTO.getPlotOffer().get(i).getActive());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getPlotOffer().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getPlotOffer().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getPlotOffer().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getPlotOffer().get(i).getUpdatedDate());
                        divisionTable.setStatus(transactionSyncResponseDTO.getPlotOffer().get(i).getStatus());
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertPlotOfferIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getUserSection().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        UserSectionTable divisionTable = new UserSectionTable();
                        divisionTable.setId(transactionSyncResponseDTO.getUserSection().get(i).getId());
                        divisionTable.setSectionId(transactionSyncResponseDTO.getUserSection().get(i).getSectionId());
                        divisionTable.setUserId(transactionSyncResponseDTO.getUserSection().get(i).getUserId());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getUserSection().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getUserSection().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getUserSection().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getUserSection().get(i).getUpdatedDate());
                        divisionTable.setActive(true);
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertUserSectionIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc20Fertilizer().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        D20FertilizerTable divisionTable = new D20FertilizerTable();
                        divisionTable.setId(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getPlotNo());
                        divisionTable.setFertilizerId(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getFertilizerId());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getUpdatedDate());
                        divisionTable.setIsActive(transactionSyncResponseDTO.getDoc20Fertilizer().get(i).getIsActive());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertD20FertilizerIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc20Weed().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        D20WeedTable divisionTable = new D20WeedTable();
                        divisionTable.setId(transactionSyncResponseDTO.getDoc20Weed().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc20Weed().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc20Weed().get(i).getPlotNo());
                        divisionTable.setWeedId(transactionSyncResponseDTO.getDoc20Weed().get(i).getWeedId());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getDoc20Weed().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getDoc20Weed().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getDoc20Weed().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getDoc20Weed().get(i).getUpdatedDate());
//                        divisionTable.setActive(true);
                        divisionTable.setIsActive(transactionSyncResponseDTO.getDoc20Weed().get(i).getIsActive());
                        divisionTable.setSync("1");
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertD20WeedIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc20Disease().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        D20DiseaseTable divisionTable = new D20DiseaseTable();
                        divisionTable.setId(transactionSyncResponseDTO.getDoc20Disease().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc20Disease().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc20Disease().get(i).getPlotNo());
                        divisionTable.setDiseasesId(transactionSyncResponseDTO.getDoc20Disease().get(i).getDiseasesId());
                        divisionTable.setIdentifiedDate(transactionSyncResponseDTO.getDoc20Disease().get(i).getIdentifiedDate());
                        divisionTable.setControlDate(transactionSyncResponseDTO.getDoc20Disease().get(i).getControlDate());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getDoc20Disease().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getDoc20Disease().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getDoc20Disease().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getDoc20Disease().get(i).getUpdatedDate());
                        divisionTable.setActive(transactionSyncResponseDTO.getDoc20Disease().get(i).getActive());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertD20DiseaseIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc20Pest().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        D20PestTable divisionTable = new D20PestTable();
                        divisionTable.setId(transactionSyncResponseDTO.getDoc20Pest().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc20Pest().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc20Pest().get(i).getPlotNo());
                        divisionTable.setPestId(transactionSyncResponseDTO.getDoc20Pest().get(i).getPestId());
                        divisionTable.setIdentifiedDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getIdentifiedDate());
                        divisionTable.setControlDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getControlDate());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getDoc20Pest().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getDoc20Pest().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getUpdatedDate());
                        divisionTable.setActive(transactionSyncResponseDTO.getDoc20Pest().get(i).getActive());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertD20PestIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getComplaints().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddComplaintsDetailsTable divisionTable = new AddComplaintsDetailsTable();
                        divisionTable.setCode(transactionSyncResponseDTO.getComplaints().get(i).getCode());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getComplaints().get(i).getSeasonCode());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getComplaints().get(i).getFarmerCode());
                        divisionTable.setComplaintStatus(transactionSyncResponseDTO.getComplaints().get(i).getComplaintStatus());
                        divisionTable.setComplaintType(transactionSyncResponseDTO.getComplaints().get(i).getComplaintType());
                        divisionTable.setSolution(transactionSyncResponseDTO.getComplaints().get(i).getSolution());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getComplaints().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getComplaints().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getComplaints().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getComplaints().get(i).getUpdatedDate());
                        divisionTable.setIsActive(transactionSyncResponseDTO.getComplaints().get(i).getIsActive());
                        divisionTable.setLogBookNo(transactionSyncResponseDTO.getComplaints().get(i).getLogBookNo());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertAddComplainFormTableLocalDb(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getComplaintRepository().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        SavingComplainImagesTable divisionTable = new SavingComplainImagesTable();
                        divisionTable.setComplaintCode(transactionSyncResponseDTO.getComplaintRepository().get(i).getComplaintCode());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getComplaintRepository().get(i).getSeasonCode());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getComplaintRepository().get(i).getFarmerCode());
                        divisionTable.setFileName(transactionSyncResponseDTO.getComplaintRepository().get(i).getFileName());
                        divisionTable.setFileLocation(transactionSyncResponseDTO.getComplaintRepository().get(i).getFileLocation());
                        divisionTable.setFileExtension(transactionSyncResponseDTO.getComplaintRepository().get(i).getFileExtension());
                        divisionTable.setLocalDocUrl(transactionSyncResponseDTO.getComplaintRepository().get(i).getFileLocation());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getComplaintRepository().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getComplaintRepository().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getComplaintRepository().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getComplaintRepository().get(i).getUpdatedDate());
                        divisionTable.setIsActive(transactionSyncResponseDTO.getComplaintRepository().get(i).getIsActive());
                        divisionTable.setLogBookNo(transactionSyncResponseDTO.getComplaintRepository().get(i).getLogBookNo());
                        divisionTable.setIsResult(transactionSyncResponseDTO.getComplaintRepository().get(i).getIsResult());
                        divisionTable.setIsVideoRecording(transactionSyncResponseDTO.getComplaintRepository().get(i).getIsVideoRecording());
                        divisionTable.setModuleTypeId(transactionSyncResponseDTO.getComplaintRepository().get(i).getModuleTypeId());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        divisionTable.setServerUpdatedStatus(true);
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertComplainImagesToServer(divisionTable,transactionSyncResponseDTO.getComplaintRepository().get(i).getLogBookNo());
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getDoc20Pest().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        D20PestTable divisionTable = new D20PestTable();
                        divisionTable.setId(transactionSyncResponseDTO.getDoc20Pest().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getDoc20Pest().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getDoc20Pest().get(i).getPlotNo());
                        divisionTable.setPestId(transactionSyncResponseDTO.getDoc20Pest().get(i).getPestId());
                        divisionTable.setIdentifiedDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getIdentifiedDate());
                        divisionTable.setControlDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getControlDate());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getDoc20Pest().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getDoc20Pest().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getDoc20Pest().get(i).getUpdatedDate());
                        divisionTable.setActive(transactionSyncResponseDTO.getDoc20Pest().get(i).getActive());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertD20PestIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }

                    for (int i = 0; i < transactionSyncResponseDTO.getGrowthMonitoring().size(); i++) {
//                                        JSONObject jsonObjectClusterHDR = jsonClusterHDRArray.getJSONObject(clusterHDR);
                        AddGrowthMonitoringTable divisionTable = new AddGrowthMonitoringTable();
                        divisionTable.setId(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getId());
                        divisionTable.setSeasonCode(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getSeasonCode());
                        divisionTable.setPlotNo(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getPlotNo());
                        divisionTable.setFileUrl(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getFileUrl());
                        divisionTable.setFarmerCode(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getFarmerCode());
                        divisionTable.setRemarks(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getRemarks());
                        divisionTable.setStage(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getStage());
                        divisionTable.setCreatedDate(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getCreatedDate());
                        divisionTable.setCreatedByUserId(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getCreatedByUserId());
                        divisionTable.setUpdatedByUserId(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getUpdatedByUserId());
                        divisionTable.setUpdatedDate(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getUpdatedDate());
                        divisionTable.setActive(transactionSyncResponseDTO.getGrowthMonitoring().get(i).getActive());
                        divisionTable.setSync(true);
                        divisionTable.setServerStatus("1");
                        //insertClusterValuesIntoLocalDB(clusterHDr_value);
                        viewModel.insertGrowthMonitoringIntoLocalDBQuery(divisionTable);
                        //getClusterHDRList.add(clusterHDr_value);
                    }



                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);

                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();

                        }

                    }, 1 * 120000);


                    Log.d("TAG", "onResponse: >>>"+strResponse);

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

    public void getLoginCredentials() {
        final AppAPI service = Retrofit_funtion_class.getClient().create(AppAPI.class);
        Call<List<LoginDTO>> callRetrofit = null;
        callRetrofit = service.getLoginCredentials(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()));
//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppCompatAlertDialogStyle);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching All Data From Server please wait...");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
        callRetrofit.enqueue(new Callback<List<LoginDTO>>() {
            @Override
            public void onResponse(Call<List<LoginDTO>> call, Response<List<LoginDTO>> response) {
                try {
//                    String strResponse = String.valueOf(response.body());
                    List<LoginDTO> strResponse = response.body();
                    if(strResponse.size()>0){
//                            [{"Id":35,"EmplId":null,"FirstName":"Praveen","LastName":"Reddy","UserName":"RPR","MobileNumber":9676555559,"EmailId":"praveen@mspil.com","RoleName":"SuperAdmin (SA)"}]

//                        appHelper.getSharedPrefObj().edit().putString(DeviceUserID, String.valueOf(strResponse.get(0).getId())).apply();
                        getLoginDetailsByImeiNumber(String.valueOf(strResponse.get(0).getId()),md5(edt_password_id.getText().toString()),CommonUtils.getIMEInumber(LoginActivity.this));
//                        getLoginDetailsByImeiNumber(edt_login_id.getText().toString(),md5(edt_password_id.getText().toString()),CommonUtils.getIMEInumber(LoginActivity.this));

                    }
//                    Log.e("loginResponse",strResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
//                    progressDialog.dismiss();
                    Log.d("Error", ">>>>" + ex.toString());
                }
            }
            @Override
            public void onFailure(Call<List<LoginDTO>> call, Throwable t) {
//                progressDialog.dismiss();
                Log.d("Error Call", ">>>>" + call.toString());
                Log.d("Error", ">>>>" + t.toString());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLocationPermissionGranted(LoginActivity.this) ) {
            try {
                startService(new Intent(this, FalogService.class));
            } catch (Exception ex){

            }

        }
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


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