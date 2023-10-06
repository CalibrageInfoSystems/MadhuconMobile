package com.trst01.locationtracker.activity.growthMonitoring;

import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;
import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.BuildConfig;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.activity.plantation.AddPlantation;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.constant.ImageUtility;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
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

public class ReportedDetailsGrowthMonitoringActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    TextView txtSave;
    RadioButton settsYes,settsNo,dustYes,dustNo,trashYes,trashNo,redPlotYes,redPlotNo,
            baselYes,baselNo,compositeYes,compositeNo,pressMudYes,pressMudNo,greenManureYes,greenManureNo;
    Spinner spSpacing,spSoilType,spPreviousLandUse,spSourceOfIrrigation;

    //previous crop id
    List<String> crops = new ArrayList<>();
    List<String> cropIds = new ArrayList<>();
    List<CropTable> cropTableList = new ArrayList<>();
    String cropId;

    //spacing
    List<String> spacings = new ArrayList<>();
    List<String> spacingIds = new ArrayList<>();
    List<LookupDtlTable> spacingIdList = new ArrayList<>();
    String spacingId;

    //soilType
    List<String> soilTypes = new ArrayList<>();
    List<String> soilTypeIds = new ArrayList<>();
    List<LookupDtlTable> soilTypeIdList = new ArrayList<>();
    String soilTypeId;

    //irrigation
    List<String> irrigation = new ArrayList<>();
    List<String> irrigationIds = new ArrayList<>();
    List<LookupDtlTable> irrigationIdList = new ArrayList<>();
    String irrigationId;
    public static final int REQUEST_CAM_PERMISSIONS = 1;
    public static final int REQUEST_UPDATE_PERSONAL_DETAILS = 0;

    private static final int CAMERA_REQUEST = 1888;
    private static final int SECOND_CAMERA_REQUEST = 1889;

    ImageView imageOne;
    private String strImageOnePath = "";
    private byte[] bytes = null;
    Bitmap bitmapOne = null;

    ImageView imageTwo;
    private String strImageTwoPath = "";
    private byte[] bytesPatta = null;
    Bitmap bitmapTwo = null;

    String strFileExtension = null;
    String strFileExtensionTwo = null;

    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Boolean isOne= false;
    TextView txtComplain;
    String plotNo="";
    String farmerCode="";
    AddD10Table table = new AddD10Table();

//    //weedStatus
//    List<String> weedStatus = new ArrayList<>();
//    List<String> weedStatusIds = new ArrayList<>();
//    List<LookupDtlTable> weedStatusList = new ArrayList<>();
//    String weedStatusId;
//    AddD10Table table = new AddD10Table();
String seasonCode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_details1_details2);
        plotNo = getIntent().getStringExtra("plotNo");
        farmerCode = getIntent().getStringExtra("farmerCode");
        spSpacing = findViewById(R.id.spSpacing);
        spSoilType = findViewById(R.id.spSoilType);
        spPreviousLandUse = findViewById(R.id.spPreviousLandUse);
        spSourceOfIrrigation = findViewById(R.id.spSourceOfIrrigation);
        settsYes = findViewById(R.id.settsYes);
        settsNo = findViewById(R.id.settsNo);
        dustYes = findViewById(R.id.dustYes);
        dustNo = findViewById(R.id.dustNo);
        trashYes = findViewById(R.id.trashYes);
        trashNo = findViewById(R.id.trashNo);
        redPlotYes = findViewById(R.id.redPlotYes);
        redPlotNo = findViewById(R.id.redPlotNo);
        baselYes = findViewById(R.id.baselYes);
        baselNo = findViewById(R.id.baselNo);
        compositeYes = findViewById(R.id.compositeYes);
        compositeNo = findViewById(R.id.compositeNo);
        pressMudYes = findViewById(R.id.pressMudYes);
        pressMudNo = findViewById(R.id.pressMudNo);
        greenManureYes = findViewById(R.id.greenManureYes);
        greenManureNo = findViewById(R.id.greenManureNo);
        txtSave = findViewById(R.id.txtSave);

        txtComplain = findViewById(R.id.txtComplain);


        seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");

        if(seasonCode.isEmpty()){
            seasonCode="2022-23";
        }


        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportedDetailsGrowthMonitoringActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","ReportedGM");

                intent.putExtra("strPlotNumber",plotNo);
                intent.putExtra("strFarmerCode",farmerCode);
                startActivity(intent);
            }
        });

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!strImageOnePath.isEmpty()&&!strImageTwoPath.isEmpty()){
//                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//
//                    String strComplainImage1 =  ImageUtility.convertBitmapToString(bitmapOne);
////                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
//                    String strBankLocalImagePath = strImageOnePath;
//
//                    AddGrowthMonitoringTable savingComplainImagesTable = new AddGrowthMonitoringTable();
//                    savingComplainImagesTable.setId(null);
//                    savingComplainImagesTable.setRemarks("");
//                    savingComplainImagesTable.setStage("10");
//
//                    savingComplainImagesTable.setFarmerCode(farmerCode);
//                    savingComplainImagesTable.setSeasonCode("2022-23");
//                    savingComplainImagesTable.setFileUrl(strComplainImage1);
//                    savingComplainImagesTable.setLocalDocUrl(strBankLocalImagePath);
//                    savingComplainImagesTable.setSync(false);
//                    savingComplainImagesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
//                    savingComplainImagesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
//                    savingComplainImagesTable.setCreatedDate(dateTime);
//                    savingComplainImagesTable.setUpdatedDate(dateTime);
//                    savingComplainImagesTable.setActive(true);
//                    savingComplainImagesTable.setServerStatus("0");
//                    savingComplainImagesTable.setPlotNo(plotNo);
////                        savingComplainImagesTable.setLogBookNo(logbookno);
//
//
//                    viewModel.insertGrowthMonitoringIntoLocalDBQuery(savingComplainImagesTable);
//
//                    String strComplainImage2 =  ImageUtility.convertBitmapToString(bitmapTwo);
////                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
//                    String strBankLocalImagePath2 = strImageTwoPath;
//
//                    AddGrowthMonitoringTable addGrowthMonitoringTable = new AddGrowthMonitoringTable();
//                    addGrowthMonitoringTable.setId(null);
//                    addGrowthMonitoringTable.setRemarks("");
//                    addGrowthMonitoringTable.setStage("10");
//
//                    addGrowthMonitoringTable.setFarmerCode(farmerCode);
//                    addGrowthMonitoringTable.setSeasonCode("2022-23");
//                    addGrowthMonitoringTable.setFileUrl(strComplainImage2);
//                    addGrowthMonitoringTable.setLocalDocUrl(strBankLocalImagePath2);
//                    addGrowthMonitoringTable.setSync(false);
//                    addGrowthMonitoringTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
//                    addGrowthMonitoringTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
//                    addGrowthMonitoringTable.setCreatedDate(dateTime);
//                    addGrowthMonitoringTable.setUpdatedDate(dateTime);
//                    addGrowthMonitoringTable.setActive(true);
//                    addGrowthMonitoringTable.setServerStatus("0");
//                    addGrowthMonitoringTable.setPlotNo(plotNo);
////                        savingComplainImagesTable.setLogBookNo(logbookno);
//
//
//                    viewModel.insertGrowthMonitoringIntoLocalDBQuery(addGrowthMonitoringTable);
//
//                }
                if(!strImageOnePath.isEmpty()&&!strImageTwoPath.isEmpty()){
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

                    String strComplainImage1 =  ImageUtility.convertBitmapToString(bitmapOne);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                    String strBankLocalImagePath = strImageOnePath;

                    AddGrowthMonitoringTable savingComplainImagesTable = new AddGrowthMonitoringTable();
                    savingComplainImagesTable.setId(null);
                    savingComplainImagesTable.setRemarks("10");
                    savingComplainImagesTable.setStage("10");

                    savingComplainImagesTable.setFarmerCode(farmerCode);
                    savingComplainImagesTable.setSeasonCode(seasonCode);
//                    savingComplainImagesTable.setSeasonCode("2022-23");
                    savingComplainImagesTable.setFileUrl(strComplainImage1);
                    savingComplainImagesTable.setLocalDocUrl(strBankLocalImagePath);
                    savingComplainImagesTable.setSync(false);
                    savingComplainImagesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    savingComplainImagesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    savingComplainImagesTable.setCreatedDate(dateTime);
                    savingComplainImagesTable.setUpdatedDate(dateTime);
                    savingComplainImagesTable.setActive(true);
                    savingComplainImagesTable.setServerStatus("0");
                    savingComplainImagesTable.setPlotNo(plotNo);
//                        savingComplainImagesTable.setLogBookNo(logbookno);


                    viewModel.insertGrowthMonitoringIntoLocalDBQuery(savingComplainImagesTable);

                    String strComplainImage2 =  ImageUtility.convertBitmapToString(bitmapTwo);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                    String strBankLocalImagePath2 = strImageTwoPath;

                    AddGrowthMonitoringTable addGrowthMonitoringTable = new AddGrowthMonitoringTable();
                    addGrowthMonitoringTable.setId(null);
                    addGrowthMonitoringTable.setRemarks("10");
                    addGrowthMonitoringTable.setStage("10");

                    addGrowthMonitoringTable.setFarmerCode(farmerCode);
                    addGrowthMonitoringTable.setSeasonCode(seasonCode);
//                    addGrowthMonitoringTable.setSeasonCode("2022-23");
                    addGrowthMonitoringTable.setFileUrl(strComplainImage2);
                    addGrowthMonitoringTable.setLocalDocUrl(strBankLocalImagePath2);
                    addGrowthMonitoringTable.setSync(false);
                    addGrowthMonitoringTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGrowthMonitoringTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGrowthMonitoringTable.setCreatedDate(dateTime);
                    addGrowthMonitoringTable.setUpdatedDate(dateTime);
                    addGrowthMonitoringTable.setActive(true);
                    addGrowthMonitoringTable.setServerStatus("0");
                    addGrowthMonitoringTable.setPlotNo(plotNo);
//                        savingComplainImagesTable.setLogBookNo(logbookno);


                    viewModel.insertGrowthMonitoringIntoLocalDBQuery(addGrowthMonitoringTable);

                }
                else if(!strImageOnePath.isEmpty()||!strImageTwoPath.isEmpty()){
                    if(!strImageOnePath.isEmpty()){

                        String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

                        String strComplainImage1 =  ImageUtility.convertBitmapToString(bitmapOne);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                        String strBankLocalImagePath = strImageOnePath;

                        AddGrowthMonitoringTable savingComplainImagesTable = new AddGrowthMonitoringTable();
                        savingComplainImagesTable.setId(null);
                        savingComplainImagesTable.setRemarks("10");
                        savingComplainImagesTable.setStage("10");

                        savingComplainImagesTable.setFarmerCode(farmerCode);
                        savingComplainImagesTable.setSeasonCode(seasonCode);
//                        savingComplainImagesTable.setSeasonCode("2022-23");
                        savingComplainImagesTable.setFileUrl(strComplainImage1);
                        savingComplainImagesTable.setLocalDocUrl(strBankLocalImagePath);
                        savingComplainImagesTable.setSync(false);
                        savingComplainImagesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        savingComplainImagesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        savingComplainImagesTable.setCreatedDate(dateTime);
                        savingComplainImagesTable.setUpdatedDate(dateTime);
                        savingComplainImagesTable.setActive(true);
                        savingComplainImagesTable.setServerStatus("0");
                        savingComplainImagesTable.setPlotNo(plotNo);
//                        savingComplainImagesTable.setLogBookNo(logbookno);


                        viewModel.insertGrowthMonitoringIntoLocalDBQuery(savingComplainImagesTable);

                    }
                    else {
                        String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

                        String strComplainImage2 =  ImageUtility.convertBitmapToString(bitmapTwo);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                        String strBankLocalImagePath2 = strImageTwoPath;

                        AddGrowthMonitoringTable addGrowthMonitoringTable = new AddGrowthMonitoringTable();
                        addGrowthMonitoringTable.setId(null);
                        addGrowthMonitoringTable.setRemarks("10");
                        addGrowthMonitoringTable.setStage("10");

                        addGrowthMonitoringTable.setFarmerCode(farmerCode);
                        addGrowthMonitoringTable.setSeasonCode(seasonCode);
//                        addGrowthMonitoringTable.setSeasonCode("2022-23");
                        addGrowthMonitoringTable.setFileUrl(strComplainImage2);
                        addGrowthMonitoringTable.setLocalDocUrl(strBankLocalImagePath2);
                        addGrowthMonitoringTable.setSync(false);
                        addGrowthMonitoringTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        addGrowthMonitoringTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                        addGrowthMonitoringTable.setCreatedDate(dateTime);
                        addGrowthMonitoringTable.setUpdatedDate(dateTime);
                        addGrowthMonitoringTable.setActive(true);
                        addGrowthMonitoringTable.setServerStatus("0");
                        addGrowthMonitoringTable.setPlotNo(plotNo);
//                        savingComplainImagesTable.setLogBookNo(logbookno);

                        viewModel.insertGrowthMonitoringIntoLocalDBQuery(addGrowthMonitoringTable);

                    }

                }

                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                String setts="";
                String dust="";
                String trash="";
                String redPlot="";
                String basel="";
                String composite="";
                String mud="";
                String manure="";
                if(settsYes.isChecked()){
                    setts="true";
                } else {
                    setts="false";
                }
                if(dustYes.isChecked()){
                    dust="true";
                } else {
                    dust="false";
                }
                if(trashYes.isChecked()){
                    trash="true";
                } else {
                    trash="false";
                }
                if(redPlotYes.isChecked()){
                    redPlot="true";
                } else {
                    redPlot="false";
                }
                if(baselYes.isChecked()){
                    basel="true";
                } else {
                    basel="false";
                }
                if(compositeYes.isChecked()){
                    composite="true";
                } else {
                    composite="false";
                }
                if(pressMudYes.isChecked()){
                    mud="true";
                } else {
                    mud="false";
                }
                if(greenManureYes.isChecked()){
                    manure="true";
                } else {
                    manure="false";
                }
                Toast.makeText(ReportedDetailsGrowthMonitoringActivity.this, soilTypeId+" ", Toast.LENGTH_SHORT).show();
                viewModel.updateD10(soilTypeId,spacingId,cropId,irrigationId,setts,dust,trash,redPlot,basel,composite,mud,manure,false,dateTime,plotNo,"0");
                finish();
            }
        });


        spSourceOfIrrigation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    if(position>0) {
                        irrigationId = String.valueOf(irrigationIdList.get(position).getId());
//                        irrigationId = String.valueOf(irrigationIdList.get(position - 1).getId());
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPreviousLandUse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    if(position>0) {
                        cropId = String.valueOf(cropTableList.get(position).getId());
//                        cropId = String.valueOf(cropTableList.get(position - 1).getId());
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSpacing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    if(position>0) {
                        spacingId = String.valueOf(spacingIdList.get(position ).getId());
//                        spacingId = String.valueOf(spacingIdList.get(position - 1).getId());
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSoilType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    if(position>0) {
                        soilTypeId = String.valueOf(soilTypeIdList.get(position).getId());
//                        soilTypeId = String.valueOf(soilTypeIdList.get(position - 1).getId());
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        configureDagger();
        configureViewModel();
        imageOne = findViewById(R.id.img_picture_first_plot);
        imageTwo = findViewById(R.id.img_picture_first_pattadhar);

        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOne = true;
                openCameraPermission(true);
            }
        });
        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOne = false;
                openCameraPermission(false);
            }
        });

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
//        getInterCropListDataFromLocalDB(spPreviousLandUse);
//        getSpacingListDataFromLocalDB(spSpacing);
//        getSoilTypeListDataFromLocalDB(spSoilType);
//        getSourceOfIrrigationListDataFromLocalDB(spSourceOfIrrigation);
        getGwList();
        getD10Data(plotNo,seasonCode);
//        getD10Data(plotNo,"2022-23");
    }

    public void getD10Data(String plotNum,String seasonCode) {
        try {
            viewModel.getD10Data(plotNum,seasonCode);
            if (viewModel.getInsertD10IntoLocalDBQueryLiveDataLocalDB() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AddD10Table addD10Table = (AddD10Table) o;
                        viewModel.getInsertD10IntoLocalDBQueryLiveDataLocalDB().removeObserver(this);

                        if (addD10Table != null ) {
                            table= addD10Table;
//                            if(addD10Table.getInterCropId()!=null){
//
//                                int pos = setSpinnerPosition(cropIds,addD10Table.getInterCropId());
//                                spPreviousLandUse.setSelection(pos,true);
//                                spPreviousLandUse.setEnabled(false);
//                            }
//
//                            if(addD10Table.getWeedStatusId()!=null){
//
//                                int pos = setSpinnerPosition(weedStatusIds,addD10Table.getWeedStatusId());
//                                spWeedStatus.setSelection(pos,true);
//                                spWeedStatus.setEnabled(false);
//                            }

//                            getInterCropListDataFromLocalDB(spPreviousLandUse);
                            Toast.makeText(ReportedDetailsGrowthMonitoringActivity.this, table.getSoilTypeId()+" ", Toast.LENGTH_SHORT).show();

                            getInterCropListDataFromLocalDB(spPreviousLandUse);
                            getSpacingListDataFromLocalDB(spSpacing);
                            getSoilTypeListDataFromLocalDB(spSoilType);
                            getSourceOfIrrigationListDataFromLocalDB(spSourceOfIrrigation);
//                            getWeedStatusHDRListDataFromLocalDB(spWeedStatus);


                            if (addD10Table.getIsSettsHotWaterTreatment()!=null&&addD10Table.getIsSettsHotWaterTreatment().equalsIgnoreCase("true")){
                                settsYes.setChecked(true);
                                settsYes.setEnabled(false);
                                settsNo.setEnabled(false);
                            }

                            if (addD10Table.getIsDustApplied()!=null&&addD10Table.getIsDustApplied().equalsIgnoreCase("true")){
                                dustYes.setChecked(true);
                                dustYes.setEnabled(false);
                                dustNo.setEnabled(false);
                            }

                            if (addD10Table.getIsTrashMulching()!=null&&addD10Table.getIsTrashMulching().equalsIgnoreCase("true")){
                                trashYes.setChecked(true);
                                trashYes.setEnabled(false);
                                trashNo.setEnabled(false);
                            }

                            if (addD10Table.getIsPreviousRedPlot()!=null&&addD10Table.getIsPreviousRedPlot().equalsIgnoreCase("true")){
                                redPlotYes.setChecked(true);
                                redPlotYes.setEnabled(false);
                                redPlotNo.setEnabled(false);
                            }

                            if (addD10Table.getIsPreviousRedPlot()!=null&&addD10Table.getIsPreviousRedPlot().equalsIgnoreCase("true")){
                                redPlotYes.setChecked(true);
                                redPlotYes.setEnabled(false);
                                redPlotNo.setEnabled(false);
                            }

                            if (addD10Table.getIsBasalFertilization()!=null&&addD10Table.getIsBasalFertilization().equalsIgnoreCase("true")){
                                baselYes.setChecked(true);
                                baselYes.setEnabled(false);
                                baselNo.setEnabled(false);
                            }

                            if (addD10Table.getIsCompositeFormYard()!=null&&addD10Table.getIsCompositeFormYard().equalsIgnoreCase("true")){
                                compositeYes.setChecked(true);
                                compositeYes.setEnabled(false);
                                compositeNo.setEnabled(false);
                            }

                            if (addD10Table.getIsFilterPressMud()!=null&&addD10Table.getIsFilterPressMud().equalsIgnoreCase("true")){
                                pressMudYes.setChecked(true);
                                pressMudYes.setEnabled(false);
                                pressMudNo.setEnabled(false);
                            }

                            if (addD10Table.getIsGreenManures()!=null&&addD10Table.getIsGreenManures().equalsIgnoreCase("true")){
                                greenManureYes.setChecked(true);
                                greenManureYes.setEnabled(false);
                                greenManureNo.setEnabled(false);
                            }

                        }


                    }

                };
                viewModel.getInsertD10IntoLocalDBQueryLiveDataLocalDB().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    private int setSpinnerPosition(List<String> codeList, String code) {
        int position=0;
        for(int toFind=0;toFind<codeList.size();toFind++){
            if(String.valueOf(codeList.get(toFind)).equals(code)){
                position= toFind;
                break;
            }
        }
        return position;
    }

    public void getGwList() {
        try {
            viewModel.getGwList(seasonCode,farmerCode,plotNo,"10","10");
//            viewModel.getGwList("2022-23",farmerCode,plotNo,"10","10");
            if (viewModel.getGMNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddGrowthMonitoringTable> seasonTableList = (List<AddGrowthMonitoringTable>) o;
                        viewModel.getGMNotSyncLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");

                        if (seasonTableList != null && seasonTableList.size() > 0) {

                            if(seasonTableList.size()==1){
                                //image set
                                try {
                                    //first image set
                                    if (seasonTableList.get(0).getServerStatus().equalsIgnoreCase("0")){
                                        Uri uri = null;
                                        File imgFile = new File(seasonTableList.get(0).getLocalDocUrl());
                                        if (imgFile.exists()) {
                                            uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                            Picasso.get()
                                                    .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                    .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                    .placeholder(R.drawable.loading_icon)
                                                    .into(imageOne);
                                        }
                                    }else if (seasonTableList.get(0).getServerStatus().equalsIgnoreCase("1")){
                                        if(seasonTableList.get(0).getFileUrl().length()>300){
                                            Uri uri = null;
                                            File imgFile = new File(seasonTableList.get(0).getLocalDocUrl());
                                            if (imgFile.exists()) {
                                                uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                                Picasso.get()
                                                        .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                        .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                        .placeholder(R.drawable.loading_icon)
                                                        .into(imageOne);
                                            }
                                        } else {
                                            Picasso.get()
                                                    .load(seasonTableList.get(0).getFileUrl())
                                                    .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                                                    .placeholder(R.drawable.loading_icon)
                                                    .into(imageOne);
                                        }

                                    }

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
//                                try {
//                                    //second image set
//                                    if (savingComplainImagesTableList_filter.get(1).getServerStatus().equalsIgnoreCase("0")){
//                                        Uri uri = null;
//                                        File imgFile = new File(savingComplainImagesTableList_filter.get(1).getLocalDocUrl());
//                                        if (imgFile.exists()) {
//                                            uri = Uri.fromFile(imgFile);
////                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
//                                            Picasso.get()
//                                                    .load(uri)
////                                .error(R.drawable.round_image_shape)
//                                                    .error(R.drawable.loading_icon)
////                                .placeholder(R.drawable.round_image_shape)
//                                                    .placeholder(R.drawable.loading_icon)
//                                                    .into(imageview_complain_second);
//                                        }
//                                    }else if (savingComplainImagesTableList_filter.get(1).getServerStatus().equalsIgnoreCase("1")){
//                                        if(savingComplainImagesTableList_filter.get(1).getFileLocation().length()>300){
//                                            Uri uri = null;
//                                            File imgFile = new File(savingComplainImagesTableList_filter.get(1).getLocalDocUrl());
//                                            if (imgFile.exists()) {
//                                                uri = Uri.fromFile(imgFile);
////                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
//                                                Picasso.get()
//                                                        .load(uri)
////                                .error(R.drawable.round_image_shape)
//                                                        .error(R.drawable.loading_icon)
////                                .placeholder(R.drawable.round_image_shape)
//                                                        .placeholder(R.drawable.loading_icon)
//                                                        .into(imageview_complain_second);
//                                            }
//                                        } else {
//                                            Picasso.get()
//                                                    .load(savingComplainImagesTableList_filter.get(1).getFileLocation())
//                                                    .error(R.drawable.loading_icon)
////                            .error(R.drawable.placeholder_image)
////                            .placeholder(R.drawable.loading_icon)
////                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
//                                                    .placeholder(R.drawable.loading_icon)
//                                                    .into(imageview_complain_second);
//                                        }
//                                    }
//
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
                                imageOne.setEnabled(false);
                            }
                            else {
                                //image set
                                try {
                                    //first image set
                                    if (seasonTableList.get(0).getServerStatus().equalsIgnoreCase("0")){
                                        Uri uri = null;
                                        File imgFile = new File(seasonTableList.get(0).getLocalDocUrl());
                                        if (imgFile.exists()) {
                                            uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                            Picasso.get()
                                                    .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                    .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                    .placeholder(R.drawable.loading_icon)
                                                    .into(imageOne);
                                        }
                                    }else if (seasonTableList.get(0).getServerStatus().equalsIgnoreCase("1")){
                                        if(seasonTableList.get(0).getFileUrl().length()>300){
                                            Uri uri = null;
                                            File imgFile = new File(seasonTableList.get(0).getLocalDocUrl());
                                            if (imgFile.exists()) {
                                                uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                                Picasso.get()
                                                        .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                        .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                        .placeholder(R.drawable.loading_icon)
                                                        .into(imageOne);
                                            }
                                        } else {
                                            Picasso.get()
                                                    .load(seasonTableList.get(0).getFileUrl())
                                                    .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                                                    .placeholder(R.drawable.loading_icon)
                                                    .into(imageOne);
                                        }

                                    }
                                    imageOne.setEnabled(false);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                try {
                                    //second image set
                                    if (seasonTableList.get(1).getServerStatus().equalsIgnoreCase("0")){
                                        Uri uri = null;
                                        File imgFile = new File(seasonTableList.get(1).getLocalDocUrl());
                                        if (imgFile.exists()) {
                                            uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                            Picasso.get()
                                                    .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                    .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                    .placeholder(R.drawable.loading_icon)
                                                    .into(imageTwo);
                                        }
                                    }else if (seasonTableList.get(1).getServerStatus().equalsIgnoreCase("1")){
                                        if(seasonTableList.get(1).getFileUrl().length()>300){
                                            Uri uri = null;
                                            File imgFile = new File(seasonTableList.get(1).getLocalDocUrl());
                                            if (imgFile.exists()) {
                                                uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                                Picasso.get()
                                                        .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                        .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                        .placeholder(R.drawable.loading_icon)
                                                        .into(imageTwo);
                                            }
                                        } else {
                                            Picasso.get()
                                                    .load(seasonTableList.get(1).getFileUrl())
                                                    .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                                                    .placeholder(R.drawable.loading_icon)
                                                    .into(imageTwo);
                                        }
                                    }

                                    imageTwo.setEnabled(false);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }

                        } else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No gw list ");
                        }
                    }
                };
                viewModel.getGMNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getInterCropListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCropListFromLocalDBStatus();
            if (viewModel.getCropDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CropTable> seasonTableList = (List<CropTable>) o;
                        viewModel.getCropDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        crops.clear();
                        cropIds.clear();
                        cropTableList.clear();
                        cropTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            crops.add("Select crop *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                crops.add(seasonTableList.get(i).getName());
                                cropIds.add(String.valueOf(seasonTableList.get(i).getId()));
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":crops", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedDetailsGrowthMonitoringActivity.this,
                                    R.layout.spinner_dropdown_layout, crops);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            if(table.getPreviousCropId()!=null){

                                int pos = setSpinnerPosition(cropIds,table.getPreviousCropId());
                                spPreviousLandUse.setSelection(pos,true);
                                spPreviousLandUse.setEnabled(false);
                                spPreviousLandUse.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.appColor));
                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No crops list ");
                        }
                    }
                };
                viewModel.getCropDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getSourceOfIrrigationListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLogBookHdrListFromLocalDBStatus("Source of Irrigation");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSourceOfIrrigationListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Source of Irrigation list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getSourceOfIrrigationListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLookupDtlList(String.valueOf(id));
            if (viewModel.getLookupDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupDtlTable> seasonTableList = (List<LookupDtlTable>) o;
                        viewModel.getLookupDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        irrigation.clear();
                        irrigationIds.clear();
                        irrigationIdList.clear();
                        irrigationIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            irrigation.add("Select Source of Irrigation *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                irrigation.add(seasonTableList.get(i).getName());
                                irrigationIds.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":irrigation", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedDetailsGrowthMonitoringActivity.this,
                                    R.layout.spinner_dropdown_layout, irrigation);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            if(table.getIrrigationTypeId()!=null){

                                int pos = setSpinnerPosition(irrigationIds,table.getIrrigationTypeId());
                                spSourceOfIrrigation.setSelection(pos,true);
                                spSourceOfIrrigation.setEnabled(false);
                                spSourceOfIrrigation.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.appColor));
                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No irrigation list ");
                        }
                    }
                };
                viewModel.getLookupDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    //

    public void getSoilTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLogBookHdrListFromLocalDBStatus("SoilType");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSoilTypeListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No soilType list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getSoilTypeListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLookupDtlList(String.valueOf(id));
            if (viewModel.getLookupDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupDtlTable> seasonTableList = (List<LookupDtlTable>) o;
                        viewModel.getLookupDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        soilTypes.clear();
                        soilTypeIds.clear();
                        soilTypeIdList.clear();
                        soilTypeIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            soilTypes.add("Select SoilType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                soilTypes.add(seasonTableList.get(i).getName());
                                soilTypeIds.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":SoilType", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedDetailsGrowthMonitoringActivity.this,
                                    R.layout.spinner_dropdown_layout, soilTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            if(table.getSoilTypeId()!=null){

                                int pos = setSpinnerPosition(soilTypeIds,table.getSoilTypeId());
                                spSoilType.setSelection(pos,true);
                                spSoilType.setEnabled(false);
                                spSoilType.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.appColor));
                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No soilType list ");
                        }
                    }
                };
                viewModel.getLookupDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getSpacingListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLogBookHdrListFromLocalDBStatus("Spacing");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSpacingListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No spacings list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getSpacingListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLookupDtlList(String.valueOf(id));
            if (viewModel.getLookupDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupDtlTable> seasonTableList = (List<LookupDtlTable>) o;
                        viewModel.getLookupDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        spacings.clear();
                        spacingIds.clear();
                        spacingIdList.clear();
                        spacingIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            spacings.add("Select Spacing *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                spacingIds.add(String.valueOf(seasonTableList.get(i).getId()));
                                spacings.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedDetailsGrowthMonitoringActivity.this,
                                    R.layout.spinner_dropdown_layout, spacings);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            if(table.getSpacingId()!=null){

                                int pos = setSpinnerPosition(spacingIds,table.getSpacingId());
                                spSpacing.setSelection(pos,true);
                                spSpacing.setEnabled(false);
                                spSpacing.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.appColor));
                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No spacings list ");
                        }
                    }
                };
                viewModel.getLookupDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }


    private void openCameraPermission(Boolean land) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!CommonUtils.isPermissionAllowed(ReportedDetailsGrowthMonitoringActivity.this, Manifest.permission.CAMERA))) {
            android.util.Log.v("MeasuredDetailsGrowthMonitoringActivity", "Location Permissions Not Granted");
            ActivityCompat.requestPermissions(
                    ReportedDetailsGrowthMonitoringActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_CAM_PERMISSIONS
            );
        } else {

            firstDispatchTakePictureIntent(CAMERA_REQUEST,land);


        }
    }

    private void firstDispatchTakePictureIntent(int actionCode,Boolean land) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (actionCode) {
            case CAMERA_REQUEST:
                if(land){
                    File f = null;
                    strImageOnePath = null;
//                strPattaImagePath = null;
                    try {
                        f = setUpPhotoFile();

                        if(!isOne){
//                        strPattaImagePath = f.getAbsolutePath();
                        } else {
                            strImageOnePath = f.getAbsolutePath();
                        }
                        //strLandImagePath = f.getPath();
                        if(isOne){
                            strFileExtension = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
                        } else {
                            strFileExtensionTwo = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
                        }


                        Uri photoURI = FileProvider.getUriForFile(ReportedDetailsGrowthMonitoringActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                f);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        f = null;
                        strImageOnePath = null;
                    }
                } else {
                    File f = null;
                    strImageTwoPath = null;
                    try {
                        f = setUpPhotoFile();

                        strImageTwoPath = f.getAbsolutePath();
//                        }
                        //strLandImagePath = f.getPath();
//                        if(isLand){
//                            strFileExtension = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
//                        } else {
                        strFileExtensionTwo = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
//                        }


                        Uri photoURI = FileProvider.getUriForFile(ReportedDetailsGrowthMonitoringActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                f);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        f = null;
                        strImageTwoPath = null;
                    }
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

        if(!isOne){
            strImageTwoPath =  f.getAbsolutePath();
        } else {
            strImageOnePath = f.getAbsolutePath();
        }


        return f;
    }

    private File createImageFileFirst() {
        File image = null;
        if(isOne){

            File mediaStorageDir = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mediaStorageDir = new File(ReportedDetailsGrowthMonitoringActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
            } else
                mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "CoreCarbonLandPictures");
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("App", "failed to create directory");
                }
            }
//            File image = null;
            try {
                image = File.createTempFile("imageFiles", ".jpg", mediaStorageDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            return image;
        } else {

            File mediaStorageDir = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mediaStorageDir = new File(ReportedDetailsGrowthMonitoringActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
            } else
                mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "CoreCarbonLandPictures");
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("App", "failed to create directory");
                }
            }

            try {
                image = File.createTempFile("imageFiles", ".jpg", mediaStorageDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
                    strImageOnePath = null;
                    strImageOnePath = null;
                }
                break;


        } // switch
    }

    private void handleBigCameraPhoto() throws Exception {

        if(isOne){

            if (strImageOnePath != null) {
                setPic();
                galleryAddPic();
            }
        } else {

            if (strImageTwoPath != null) {
                setPic();
                galleryAddPic();
            }
        }



    }

    private void setPic() throws Exception {

        /* There isn't enough memory to open up more than a couple camera photos */
        /* So pre-scale the target bitmap into which the file is decoded */

        /* Get the size of the ImageView */

        if(isOne){
            int targetW = imageOne.getWidth();
            int targetH = imageOne.getHeight();

            /* Get the size of the image */
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(strImageOnePath, bmOptions);
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

            bitmapOne = BitmapFactory.decodeFile(strImageOnePath, bmOptions);
//        if(!isLand){
//            bitmapPatta = BitmapFactory.decodeFile(strLandImagePath, bmOptions);
//        } else {
//
//        }
            getBytesFromBitmap(bitmapOne);

            ExifInterface ei = new ExifInterface(strImageOnePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            /* Decode the JPEG file into a Bitmap */

            //bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//        imageLand.setImageBitmap(rotatedBitmap);
            Bitmap rotatedBitmap = null;
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmapOne, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmapOne, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmapOne, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmapOne;
            }


//        bitmapLand = rotatedBitmap;
//        if(isLand){
            bitmapOne = rotatedBitmap;
            imageOne.setImageBitmap(rotatedBitmap);
            imageOne.invalidate();
//        } else {
//            bitmapPatta = rotatedBitmap;
//            imagePatta.setImageBitmap(rotatedBitmap);
//            imagePatta.invalidate();
//        }

            /* Decode the JPEG file into a Bitmap */
//        bitmapLand = BitmapFactory.decodeFile(strLandImagePath, bmOptions);
//        getBytesFromBitmap(bitmapLand);
//        bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//

            /* There isn't enough memory to open up more than a couple camera photos */
            /* So pre-scale the target bitmap into which the file is decoded */

            /* Get the size of the ImageView */


        } else {
            int targetW = imageTwo.getWidth();
            int targetH = imageTwo.getHeight();

            /* Get the size of the image */
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(strImageTwoPath, bmOptions);
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

            bitmapTwo = BitmapFactory.decodeFile(strImageTwoPath, bmOptions);
//        if(!isLand){
//            bitmapPatta = BitmapFactory.decodeFile(strLandImagePath, bmOptions);
//        } else {
//
//        }
            getBytesFromBitmap(bitmapTwo);

            ExifInterface ei = new ExifInterface(strImageTwoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            /* Decode the JPEG file into a Bitmap */

            //bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//        imageLand.setImageBitmap(rotatedBitmap);
            Bitmap rotatedBitmap = null;
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmapTwo, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmapTwo, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmapTwo, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmapTwo;
            }


//        bitmapLand = rotatedBitmap;
//        if(isLand){
            bitmapTwo = rotatedBitmap;
            imageTwo.setImageBitmap(rotatedBitmap);
            imageTwo.invalidate();
//        } else {
//            bitmapPatta = rotatedBitmap;
//            imagePatta.setImageBitmap(rotatedBitmap);
//            imagePatta.invalidate();
//        }

            /* Decode the JPEG file into a Bitmap */
//        bitmapLand = BitmapFactory.decodeFile(strLandImagePath, bmOptions);
//        getBytesFromBitmap(bitmapLand);
//        bitmapLand = ImageUtility.rotatePicture(90, bitmapLand);
//

            /* There isn't enough memory to open up more than a couple camera photos */
            /* So pre-scale the target bitmap into which the file is decoded */

            /* Get the size of the ImageView */


        }




    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    private void galleryAddPic() {
        if(isOne){

            Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            File f;
            f = new File(strImageOnePath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);
        } else {
            Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            File f;
            f = new File(strImageTwoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);
        }
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        bytes = stream.toByteArray();
        return stream.toByteArray();
    }

}