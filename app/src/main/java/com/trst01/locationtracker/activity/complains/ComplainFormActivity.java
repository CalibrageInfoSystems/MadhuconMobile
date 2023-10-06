package com.trst01.locationtracker.activity.complains;


import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;
import static com.trst01.locationtracker.constant.AppConstant.LogBookNumber;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.trst01.locationtracker.BuildConfig;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.plantation.AgreementedAreaActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonConstants;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.constant.ImageUtility;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.LookUpDropDownDataTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ComplainFormActivity extends ComplainListsBaseActivity implements View.OnClickListener, HasSupportFragmentInjector {
    private static final String TAG = ComplainFormActivity.class.getCanonicalName();
    TextView tv_complain_id,txt_save_Button_complain;
    EditText et_comments;
    AppCompatImageView img_picture_complain;
    ImageView img_cancel_complain,img_picture_complain_two;
    AppCompatSpinner sp_select_complaint_type,sp_select_complaint_status;

    Integer selectValueId,selectValueId1;
    String strSelectName,strSelectName1;
    Integer countValuePlot;
    String formatted;

   //generate complainID
   Integer countValueLogBook;
    String strPlotNumber, strLogBookCode, strCropName, strSelectSeason, strSelectCultivation,strFarmerCode;


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    // TODO: for select Value
    List<String> waterRegimeSeasonList = new ArrayList<>();
    List<String> waterRegimeSeasonListIDs = new ArrayList<>();
    List<String> waterRegimeSeasonListCode = new ArrayList<>();

    // TODO: for select Value
    List<String> waterRegimeSeasonList1 = new ArrayList<>();
    List<String> waterRegimeSeasonListIDs1 = new ArrayList<>();
    List<String> waterRegimeSeasonListCode1 = new ArrayList<>();

    List<SavingComplainImagesTable> savingComplainImagesTableList = new ArrayList<>();

    //image upload
    private String strLandImagePath1 = "null",strLandImagePath2 = "null", strPictureSecondPath = "null";
    private byte[] bytes = null;
    Bitmap bitmapLand1 = null;
    Bitmap bitmapLand2 = null;

    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final int REQUEST_CAM_PERMISSIONS = 1;
    public static final int REQUEST_UPDATE_PERSONAL_DETAILS = 0;
    private static final int CAMERA_REQUEST = 1888;
    private static final int SECOND_CAMERA_REQUEST = 1889;
    String strFileExtension1 = null;
    String strFileExtension2 = null;

    String logbookno;
    String from;

    //second image upload
    private static final int CAMERA_REQUEST_TWO = 2000;
    private String strLandImagePath_two = "null", strPictureSecondPath_two = "null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_form);


        strFarmerCode = getIntent().getStringExtra("strFarmerCode");
        strPlotNumber = getIntent().getStringExtra("strPlotNumber");
        from = getIntent().getStringExtra("from");
//        strClusterName = getIntent().getStringExtra("cluster_name");
//        strPlotVillageId = getIntent().getStringExtra("villageID");

        initializeUI();

        initializeValues();
        configureDagger();
        configureViewModel();
    }

    private void initializeUI() {
        tv_complain_id = findViewById(R.id.tv_complain_id);
        txt_save_Button_complain = (TextView) findViewById(R.id.txt_save_Button_complain);
        sp_select_complaint_type = findViewById(R.id.sp_select_complaint_type);
        sp_select_complaint_status = findViewById(R.id.sp_select_complaint_status);
        et_comments = findViewById(R.id.et_comments);
        img_picture_complain = findViewById(R.id.img_picture_complain);
        img_cancel_complain = findViewById(R.id.img_cancel_complain);
        img_picture_complain_two = findViewById(R.id.img_picture_complain_two);
    }

    private void initializeValues() {

        logbookno = strPlotNumber;
//        logbookno = appHelper.getSharedPrefObj().getString(LogBookNumber, "");
        System.out.println("LOGBOOKNO>>>"+logbookno);
        //get plot No
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
//            strPlotNumber = bundle.getString("plotNo");
//            strCropName = bundle.getString("crop");
        }

        txt_save_Button_complain.setOnClickListener(this);
        img_picture_complain.setOnClickListener(this);
        img_picture_complain_two.setOnClickListener(this);
        img_cancel_complain.setOnClickListener(this);
        sp_select_complaint_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    strSelectName = waterRegimeSeasonList.get(position);
                    selectValueId = Integer.valueOf(waterRegimeSeasonListIDs.get(position));
                    Log.d(TAG, "onItemSelected: "+strSelectName);
//                    Toast.makeText(ComplainFormActivity.this, ">>>"+strSelectName, Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_select_complaint_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    strSelectName1 = waterRegimeSeasonList1.get(position);
                    selectValueId1 = Integer.valueOf(waterRegimeSeasonListIDs1.get(position));
                    Log.d(TAG, "onItemSelected: "+strSelectName1);
//                    Toast.makeText(ComplainFormActivity.this, ">>>"+strSelectName1, Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void configureDagger() {
        AndroidInjection.inject(ComplainFormActivity.this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(ComplainFormActivity.this, viewModelFactory).get(AppViewModel.class);
//        getWaterReasonRegimeSeasonDeatilsFromLocalDB();
        getWeedStatusHDRListDataFromLocalDB();
        getComplaintStatusHDRListDataFromLocalDB();
//        getWaterReasonRegimeSeasonDeatilsFromLocalDB1();
        getLogBookCount();
    }


    public void getWeedStatusHDRListDataFromLocalDB() {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("Complaint Type");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getWeedStatusListDataFromLocalDB(seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No complaints list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getComplaintStatusHDRListDataFromLocalDB() {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("Complaint Status");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getComplaintStatusListDataFromLocalDB(seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No complaint status list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getWeedStatusListDataFromLocalDB(Integer id) {
        try {
            viewModel.getLookupDtlList(String.valueOf(id));
            if (viewModel.getLookupDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupDtlTable> seasonTableList = (List<LookupDtlTable>) o;
                        viewModel.getLookupDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        waterRegimeSeasonList.clear();
                        waterRegimeSeasonListIDs.clear();
                        waterRegimeSeasonListCode.clear();
                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            waterRegimeSeasonList.add("Select Field *");
                            for (int i = 0; i < seasonTableList.size(); i++) {
                                waterRegimeSeasonList.add(seasonTableList.get(i).getName());
                                waterRegimeSeasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                waterRegimeSeasonListCode.add(seasonTableList.get(i).getCode());
                                //strStateID = stateListResponseDTOList.get(i).getStateId();
                                Log.e("data_id", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e("data_name", seasonTableList.get(i).getName());
                                Log.e("data_code", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ComplainFormActivity.this,
                                    R.layout.spinner_dropdown_layout, waterRegimeSeasonList);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            sp_select_complaint_type.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No WeedStatus list ");
                        }
                    }
                };
                viewModel.getLookupDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getComplaintStatusListDataFromLocalDB(Integer id) {
        try {
            viewModel.getLookupDtlList(String.valueOf(id));
            if (viewModel.getLookupDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupDtlTable> seasonTableList = (List<LookupDtlTable>) o;
                        viewModel.getLookupDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        waterRegimeSeasonList1.clear();
                        waterRegimeSeasonListIDs1.clear();
                        waterRegimeSeasonListCode1.clear();
                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            waterRegimeSeasonList1.add("Select Field *");
//                            for (int i = 0; i < lookUpDropDownDataTableList.size(); i++) {
                            for (int i = 0; i < 1; i++) {
                                waterRegimeSeasonList1.add(seasonTableList.get(i).getName());
                                waterRegimeSeasonListIDs1.add(String.valueOf(seasonTableList.get(i).getId()));
                                waterRegimeSeasonListCode1.add(seasonTableList.get(i).getCode());
                                //strStateID = stateListResponseDTOList.get(i).getStateId();
                                Log.e("data_id", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e("data_name", seasonTableList.get(i).getName());
                                Log.e("data_code", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ComplainFormActivity.this,
                                    R.layout.spinner_dropdown_layout, waterRegimeSeasonList1);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            sp_select_complaint_status.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No complaint Status list ");
                        }
                    }
                };
                viewModel.getLookupDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getWaterReasonRegimeSeasonDeatilsFromLocalDB() {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLookUpDataFromLocalDBByType("Complaint Type");
            if (viewModel.getLookUpDataListFromLocalDBLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookUpDropDownDataTable> lookUpDropDownDataTableList = (List<LookUpDropDownDataTable>) o;
                        viewModel.getLookUpDataListFromLocalDBLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        waterRegimeSeasonList.clear();
                        waterRegimeSeasonListIDs.clear();
                        waterRegimeSeasonListCode.clear();
                        if (lookUpDropDownDataTableList != null && lookUpDropDownDataTableList.size() > 0) {
//                            waterRegimeSeasonList.add("Select Field *");
                            for (int i = 0; i < lookUpDropDownDataTableList.size(); i++) {
                                waterRegimeSeasonList.add(lookUpDropDownDataTableList.get(i).getName());
                                waterRegimeSeasonListIDs.add(lookUpDropDownDataTableList.get(i).getId());
                                waterRegimeSeasonListCode.add(lookUpDropDownDataTableList.get(i).getCode());
                                //strStateID = stateListResponseDTOList.get(i).getStateId();
                                Log.e("data_id", lookUpDropDownDataTableList.get(i).getId());
                                Log.e("data_name", lookUpDropDownDataTableList.get(i).getName());
                                Log.e("data_code", lookUpDropDownDataTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ComplainFormActivity.this,
                                    R.layout.spinner_dropdown_layout, waterRegimeSeasonList);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            sp_select_complaint_type.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        }
//                        else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Feild  list ");
//                        }
                    }
                };
                viewModel.getLookUpDataListFromLocalDBLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLookUpDataListFromLocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getWaterReasonRegimeSeasonDeatilsFromLocalDB1() {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLookUpDataFromLocalDBByType("Complaint Status");
            if (viewModel.getLookUpDataListFromLocalDBLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookUpDropDownDataTable> lookUpDropDownDataTableList = (List<LookUpDropDownDataTable>) o;
                        viewModel.getLookUpDataListFromLocalDBLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        waterRegimeSeasonList1.clear();
                        waterRegimeSeasonListIDs1.clear();
                        waterRegimeSeasonListCode1.clear();
                        if (lookUpDropDownDataTableList != null && lookUpDropDownDataTableList.size() > 0) {
//                            waterRegimeSeasonList1.add("Select Field *");
//                            for (int i = 0; i < lookUpDropDownDataTableList.size(); i++) {
                            for (int i = 0; i < 1; i++) {
                                waterRegimeSeasonList1.add(lookUpDropDownDataTableList.get(i).getName());
                                waterRegimeSeasonListIDs1.add(lookUpDropDownDataTableList.get(i).getId());
                                waterRegimeSeasonListCode1.add(lookUpDropDownDataTableList.get(i).getCode());
                                //strStateID = stateListResponseDTOList.get(i).getStateId();
                                Log.e("data_id", lookUpDropDownDataTableList.get(i).getId());
                                Log.e("data_name", lookUpDropDownDataTableList.get(i).getName());
                                Log.e("data_code", lookUpDropDownDataTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ComplainFormActivity.this,
                                    R.layout.spinner_dropdown_layout, waterRegimeSeasonList1);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            sp_select_complaint_status.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        }
//                        else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Feild  list ");
//                        }
                    }
                };
                viewModel.getLookUpDataListFromLocalDBLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLookUpDataListFromLocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        ComplainFormActivity.this.overridePendingTransition(R.anim.left_to_right_anim, R.anim.right_to_left_anim);

    }


    @Override
    public void onResume() {
        super.onResume();
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onResume", "Exception : " + ex.getMessage());
        }
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, "userId", "", "BreederdetailsActivity"
                        , "BreederId", "", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    //generate complainID
    public void getLogBookCount() {
        try {
            viewModel.getCountComplaintDetails().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    countValueLogBook = integer;
                    // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    Log.d(TAG, "onChanged: CountValue" + countValueLogBook);
                    // formatted = String.format("%03d", Integer.parseInt(appHelper.getSharedPrefObj().getString(DeviceUserID, "")));
                   String updated_logbooknumber =  logbookno.replaceAll("[\\s\\_()]", "");
                    CommonConstants.LOGBOOK_CODE = CommonUtils.getGeneratedComplainId(updated_logbooknumber, countValueLogBook);
                    //etPlotNo.setText(CommonConstants.LOGBOOK_CODE);
                    strLogBookCode = CommonConstants.LOGBOOK_CODE;
                    Log.d(TAG, "onChanged: LogBookId" + strLogBookCode);
                    tv_complain_id.setText(strLogBookCode);
                    System.out.println("COMPLAIN_CODE_LOGBOOK >>> "+ strLogBookCode);
//                    appHelper.getSharedPrefObj().edit().putString(LogBookNumber, strLogBookCode).apply();
//                    Intent a = new Intent(AddingLogBookActivity.this, LogBookModulesHomeScreenActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("plot", strPlotCode);
//                    bundle.putString("crop", strCropName);
//                    bundle.putString("season", strSelectSeason);
//                    bundle.putString("cultivation", strSelectCultivation);
//                    bundle.putInt("seasonId", seasonId);
//                    bundle.putInt("cultivationID", cultivationID);
//                    bundle.putString("date", strDate);
//                    a.putExtras(bundle);
//                    startActivity(a);

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }



    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.img_picture_complain:
                    openCameraPermission();
                    break;
                case R.id.img_picture_complain_two:
                    openCameraPermission_two();
                    break;
                case R.id.img_cancel_complain:
                    finish();
                    break;
                case R.id.txt_save_Button_complain:
                    if (tv_complain_id.getText().toString().isEmpty())
                    {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"Complain ID Empty");
                    }else if (et_comments.getText().toString().trim().isEmpty()){
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"please Enter Comments");
                    }else if (strLandImagePath1.equals("null")) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "select Complain picture");
                    }else if (strLandImagePath_two.equals("null")) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "select Complain picture");
                    }
                    else {

//                        String strDate = txtDate.getText().toString();
                        // String strTime = strHourValue;

//                    Integer strFileIdValue = selectFileId;
//                    Log.d(TAG, "Get Select Irrigation Type: "+selectFileId.toString());

                        AddComplaintsDetailsTable addComplaintsDetailsTable = new AddComplaintsDetailsTable();
                        addComplaintsDetailsTable.setCode(strLogBookCode);
                        addComplaintsDetailsTable.setFarmerCode(strFarmerCode);
                        addComplaintsDetailsTable.setSeasonCode("2022-23");
//                        addComplaintsDetailsTable.setCode(strLogBookCode);
                        addComplaintsDetailsTable.setComplaintType(selectValueId.toString());
                        addComplaintsDetailsTable.setComplaintStatus(selectValueId1.toString());
                        addComplaintsDetailsTable.setSolution((et_comments.getText().toString().trim()));
                        addComplaintsDetailsTable.setSync(false);
                        addComplaintsDetailsTable.setIsActive("true");
                        addComplaintsDetailsTable.setServerStatus("0");
                        addComplaintsDetailsTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        addComplaintsDetailsTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                        addComplaintsDetailsTable.setCreatedDate(dateTime);
                        addComplaintsDetailsTable.setUpdatedDate(dateTime);
                        addComplaintsDetailsTable.setLogBookNo(strPlotNumber);


                        //multiple image insert

                        String strComplainImage1 =  ImageUtility.convertBitmapToString(bitmapLand1);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                        String strBankLocalImagePath = strLandImagePath1;

                        SavingComplainImagesTable savingComplainImagesTable = new SavingComplainImagesTable();
                        savingComplainImagesTable.setComplaintCode(strLogBookCode);
                        savingComplainImagesTable.setModuleTypeId("1");
                        savingComplainImagesTable.setFileName("images");

                        savingComplainImagesTable.setFarmerCode(strFarmerCode);
                        savingComplainImagesTable.setSeasonCode("2022-23");
                        savingComplainImagesTable.setFileLocation(strComplainImage1);
                        savingComplainImagesTable.setFileExtension(strFileExtension1);
                        savingComplainImagesTable.setLocalDocUrl(strBankLocalImagePath);
                        savingComplainImagesTable.setSync(false);
                        savingComplainImagesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        savingComplainImagesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        savingComplainImagesTable.setCreatedDate(dateTime);
                        savingComplainImagesTable.setUpdatedDate(dateTime);
                        savingComplainImagesTable.setServerUpdatedStatus(false);
                        savingComplainImagesTable.setIsResult("true");
                        savingComplainImagesTable.setIsActive("true");
                        savingComplainImagesTable.setIsVideoRecording("false");
                        savingComplainImagesTable.setServerStatus("0");
                        savingComplainImagesTable.setLogBookNo(strPlotNumber);
//                        savingComplainImagesTable.setLogBookNo(logbookno);

                        savingComplainImagesTableList.add(savingComplainImagesTable);

                        viewModel.insertComplainImagesToServer(savingComplainImagesTable,logbookno);

//                        String strComplainImage2 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand2);
                        String strComplainImage2 = ImageUtility.convertBitmapToString(bitmapLand2);
                        String strBankLocalImagePath2 = strLandImagePath_two;

                        SavingComplainImagesTable savingComplainImagesTable1 = new SavingComplainImagesTable();
                        savingComplainImagesTable1.setComplaintCode(strLogBookCode);
                        savingComplainImagesTable1.setModuleTypeId("1");
                        savingComplainImagesTable1.setFileName("images");

                        savingComplainImagesTable1.setFarmerCode(strFarmerCode);
                        savingComplainImagesTable1.setSeasonCode("2022-23");
                        savingComplainImagesTable1.setFileLocation(strComplainImage2);
                        savingComplainImagesTable1.setLocalDocUrl(strBankLocalImagePath2);
                        savingComplainImagesTable1.setFileExtension(strFileExtension2);
                        savingComplainImagesTable1.setSync(false);
                        savingComplainImagesTable1.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        savingComplainImagesTable1.setCreatedDate(dateTime);
                        savingComplainImagesTable1.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        savingComplainImagesTable1.setUpdatedDate(dateTime);
                        savingComplainImagesTable1.setServerUpdatedStatus(false);
                        savingComplainImagesTable1.setIsResult("true");
                        savingComplainImagesTable1.setIsActive("true");
                        savingComplainImagesTable1.setIsVideoRecording("false");
                        savingComplainImagesTable1.setLogBookNo(strPlotNumber);
//                        savingComplainImagesTable1.setLogBookNo(logbookno);
                        savingComplainImagesTable1.setServerStatus("0");
                        savingComplainImagesTableList.add(savingComplainImagesTable1);

                        viewModel.insertComplainImagesToServer(savingComplainImagesTable1,logbookno);

//                        for (SavingComplainImagesTable savingComplainImagesTableInner : savingComplainImagesTableList) {
//                            viewModel.insertComplainImagesToServer(savingComplainImagesTableList,logbookno);
//                        }

                        insertionOfComplaiFOrmTableIntoLocaDB(addComplaintsDetailsTable);
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void insertionOfComplaiFOrmTableIntoLocaDB(AddComplaintsDetailsTable addComplaintsDetailsTable){

        try {
            viewModel.insertAddComplainFormTableLocalDb(addComplaintsDetailsTable);
            if (viewModel.getAddComplainformTableLiveDataFromLocalDB() != null) {
                Observer getLeadReadObserver = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        AddComplaintsDetailsTable addBoreWellPumpSeasonTable1 =  (AddComplaintsDetailsTable) o;
                        viewModel.getAddComplainformTableLiveDataFromLocalDB().removeObserver(this);
                        if (addBoreWellPumpSeasonTable1 != null)
                        {
                            Toast.makeText(ComplainFormActivity.this, "Complain Forms   details added successfully", Toast.LENGTH_LONG).show();
                            finish();
                            ComplainFormActivity.this.overridePendingTransition(R.anim.left_to_right_anim, R.anim.right_to_left_anim);

                        }

                    }
                };
                viewModel.getAddComplainformTableLiveDataFromLocalDB().observe(this, getLeadReadObserver);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    //first image upload
    private void openCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!CommonUtils.isPermissionAllowed(ComplainFormActivity.this, Manifest.permission.CAMERA))) {
            Log.v(TAG, "Location Permissions Not Granted");
            ActivityCompat.requestPermissions(
                    ComplainFormActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_CAM_PERMISSIONS
            );
        } else {
            firstDispatchTakePictureIntent(CAMERA_REQUEST);
        }
    }
    private void firstDispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (actionCode) {
            case CAMERA_REQUEST:
                File f = null;
                strLandImagePath1 = null;
                try {
                    f = setUpPhotoFile();
                    strLandImagePath1 = f.getAbsolutePath();
                    System.out.println("strLandImagePath>>>>"+strLandImagePath1);
                    //strLandImagePath = f.getPath();
                    strFileExtension1 = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
                    Uri photoURI = FileProvider.getUriForFile(ComplainFormActivity.this,
                            BuildConfig.APPLICATION_ID + ".provider",
                            f);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    strLandImagePath1 = null;
                }
                break;

            default:
                break;
        } // switch
        startActivityForResult(takePictureIntent, actionCode);
    }

    private File setUpPhotoFile() throws IOException {

        File f;
        f = createImageFileFirst();
        strLandImagePath1 = f.getAbsolutePath();


        return f;
    }

    private File createImageFileFirst() {
        File mediaStorageDir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mediaStorageDir = new File(ComplainFormActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
        } else
            mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "CoreCarbonLandPictures");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        File image = null;
        try {
            image = File.createTempFile("imageFiles", ".jpg", mediaStorageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    try {
                        handleBigCameraPhoto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    strLandImagePath1 = null;
                }
                break;
            case CAMERA_REQUEST_TWO:
                if (resultCode == RESULT_OK) {
                    try {
                        handleBigCameraPhoto_two();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    strLandImagePath_two = null;
                }
                break;
        } // switch
    }

    private void handleBigCameraPhoto() throws Exception {

        if (strLandImagePath1 != null) {
            setPic();
            galleryAddPic();
        }


    }
    private void setPic() throws Exception {

        /* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

        /* Get the size of the ImageView */

        int targetW = img_picture_complain.getWidth();
        int targetH = img_picture_complain.getHeight();

        /* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strLandImagePath1, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        /* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }

        /* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        bitmapLand1 = BitmapFactory.decodeFile(strLandImagePath1, bmOptions);
        getBytesFromBitmap(bitmapLand1);

        ExifInterface ei = new ExifInterface(strLandImagePath1);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
        /* Decode the JPEG file into a Bitmap */

        //bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//        imageLand.setImageBitmap(rotatedBitmap);
        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmapLand1, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmapLand1, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmapLand1, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmapLand1;
        }

        bitmapLand1 = rotatedBitmap;
        img_picture_complain.setImageBitmap(rotatedBitmap);
        /* Decode the JPEG file into a Bitmap */
//        bitmapLand = BitmapFactory.decodeFile(strLandImagePath, bmOptions);
//        getBytesFromBitmap(bitmapLand);
//        bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//

        /* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

        /* Get the size of the ImageView */


        img_picture_complain.invalidate();

    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f;
        f = new File(strLandImagePath1);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        bytes = stream.toByteArray();
        return stream.toByteArray();
    }

    //second image upload
    private void openCameraPermission_two() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!CommonUtils.isPermissionAllowed(ComplainFormActivity.this, Manifest.permission.CAMERA))) {
            Log.v(TAG, "Location Permissions Not Granted");
            ActivityCompat.requestPermissions(
                    ComplainFormActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_CAM_PERMISSIONS
            );
        } else {
            firstDispatchTakePictureIntent_two(CAMERA_REQUEST_TWO);
        }
    }
    private void firstDispatchTakePictureIntent_two(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (actionCode) {
            case CAMERA_REQUEST_TWO:
                File f = null;
                strLandImagePath_two = null;
                try {
                    f = setUpPhotoFile_two();
                    strLandImagePath_two = f.getAbsolutePath();
                    System.out.println("strLandImagePath_two>>>>"+strLandImagePath_two);
                    //strLandImagePath = f.getPath();
                    strFileExtension2 = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
                    Uri photoURI = FileProvider.getUriForFile(ComplainFormActivity.this,
                            BuildConfig.APPLICATION_ID + ".provider",
                            f);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    strLandImagePath_two = null;
                }
                break;

            default:
                break;
        } // switch
        startActivityForResult(takePictureIntent, actionCode);
    }
    private File setUpPhotoFile_two() throws IOException {

        File f;
        f = createImageFileFirst_two();
        strLandImagePath_two = f.getAbsolutePath();


        return f;
    }
    private File createImageFileFirst_two() {
        File mediaStorageDir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mediaStorageDir = new File(ComplainFormActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
        } else
            mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "CoreCarbonLandPictures");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        File image = null;
        try {
            image = File.createTempFile("imageFiles", ".jpg", mediaStorageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    private void handleBigCameraPhoto_two() throws Exception {

        if (strLandImagePath_two != null) {
            setPic_two();
            galleryAddPic_two();
        }


    }
    private void setPic_two() throws Exception {

        /* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

        /* Get the size of the ImageView */

        int targetW = img_picture_complain_two.getWidth();
        int targetH = img_picture_complain_two.getHeight();

        /* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strLandImagePath_two, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        /* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }

        /* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        bitmapLand2 = BitmapFactory.decodeFile(strLandImagePath_two, bmOptions);
        getBytesFromBitmap_two(bitmapLand2);

        ExifInterface ei = new ExifInterface(strLandImagePath_two);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
        /* Decode the JPEG file into a Bitmap */

        //bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//        imageLand.setImageBitmap(rotatedBitmap);
        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage_two(bitmapLand2, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage_two(bitmapLand2, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage_two(bitmapLand2, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmapLand2;
        }

        bitmapLand2 = rotatedBitmap;
        img_picture_complain_two.setImageBitmap(rotatedBitmap);
        /* Decode the JPEG file into a Bitmap */
//        bitmapLand = BitmapFactory.decodeFile(strLandImagePath, bmOptions);
//        getBytesFromBitmap_two(bitmapLand);
//        bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//

        /* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

        /* Get the size of the ImageView */


        img_picture_complain_two.invalidate();

    }
    private static Bitmap rotateImage_two(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    private void galleryAddPic_two() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f;
        f = new File(strLandImagePath_two);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }
    public byte[] getBytesFromBitmap_two(Bitmap bitmap) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        bytes = stream.toByteArray();
        return stream.toByteArray();
    }

}