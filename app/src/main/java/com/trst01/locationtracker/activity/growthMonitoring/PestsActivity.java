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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.BuildConfig;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.activity.growthMonitoring.adapter.DiseaseDetailsListAdapter;
import com.trst01.locationtracker.activity.growthMonitoring.adapter.PestsDetailsListAdapter;
import com.trst01.locationtracker.activity.growthMonitoring.adapter.WeedicideDetailsListAdapter;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.constant.ImageUtility;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
import com.trst01.locationtracker.database.entity.D20DiseaseTable;
import com.trst01.locationtracker.database.entity.D20PestTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.WeedTable;
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

//public class PestsActivity extends BaseActivity implements HasSupportFragmentInjector,PestsDetailsListAdapter.SyncCallbackInterface {
public class PestsActivity extends BaseActivity implements HasSupportFragmentInjector,DiseaseDetailsListAdapter.SyncCallbackInterface,PestsDetailsListAdapter.SyncCallbackInterface {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    RecyclerView recyclerBio ;

    PestsDetailsListAdapter pestsDetailsListAdapter;
    TextView txtSave;

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
    DiseaseDetailsListAdapter diseaseDetailsListAdapter;
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
    List<D20PestTable> d20PestTableArrayList = new ArrayList<>();
//    ArrayList<String> diseaseIdArrayList = new ArrayList<>();
    Boolean isChanged=false;
    List<D20DiseaseTable> d20DiseaseTableArrayList = new ArrayList<>();
    ArrayList<String> pestsIdArrayList = new ArrayList<>();
    String seasonCode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_disease);
        setContentView(R.layout.activity_pests);

        plotNo = getIntent().getStringExtra("plotNo");
        farmerCode = getIntent().getStringExtra("farmerCode");

        seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");

        if(seasonCode.isEmpty()){
            seasonCode="2022-23";
        }


        recyclerBio = findViewById(R.id.recyclerBio);
        txtSave = findViewById(R.id.txtSave);

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
//                    savingComplainImagesTable.setRemarks("Pest");
//                    savingComplainImagesTable.setStage("0");
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
//                    addGrowthMonitoringTable.setRemarks("Pest");
//                    addGrowthMonitoringTable.setStage("0");
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
                    savingComplainImagesTable.setRemarks("Pests");
                    savingComplainImagesTable.setStage("0");

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
                    addGrowthMonitoringTable.setRemarks("Pests");
                    addGrowthMonitoringTable.setStage("0");

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
                        savingComplainImagesTable.setRemarks("Pests");
                        savingComplainImagesTable.setStage("0");

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
                        addGrowthMonitoringTable.setRemarks("Pests");
                        addGrowthMonitoringTable.setStage("0");

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


                if(isChanged){
                    for (int i=0;i<d20PestTableArrayList.size();i++){
                        if(!d20PestTableArrayList.get(i).getSync()){
                            if(d20PestTableArrayList.get(i).getControlDate()==null){
                                viewModel.updatePestControlDate("",d20PestTableArrayList.get(i).getIdentifiedDate(),d20PestTableArrayList.get(i).getUpdatedDate(),d20PestTableArrayList.get(i).getId(),false);
                            } else {
                                viewModel.updatePestControlDate(d20PestTableArrayList.get(i).getControlDate(),d20PestTableArrayList.get(i).getIdentifiedDate(),d20PestTableArrayList.get(i).getUpdatedDate(),d20PestTableArrayList.get(i).getId(),false);

                            }
                       Log.e("testConrol",d20PestTableArrayList.get(i).getControlDate()+"test "+d20PestTableArrayList.get(i).getIdentifiedDate()+"empty");
                        }
                    }
                    finish();

                } else {
                    finish();
                }

            }
        });

//        configureDagger();
//        configureViewModel();
        imageOne = findViewById(R.id.img_picture_first_plot);
        imageTwo = findViewById(R.id.img_picture_first_pattadhar);

        txtComplain = findViewById(R.id.txtComplain);

        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PestsActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","Pests");
                intent.putExtra("strPlotNumber",plotNo);
                intent.putExtra("strFarmerCode",farmerCode);
                startActivity(intent);
            }
        });

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


        configureDagger();
        configureViewModel();

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
//        getWeedlistFromLocalDb();
        getD20WeedListFromLocalDb();

        getGwList();
//        getSeasonListDataFromLocalDB(spSeason);
//        getFarmerListDataFromLocalDB(spFarmer);
//        getVillageListDataFromLocalDB(spVillage);
//        getResonListDataFromLocalDB(spReason);
//        getVarietyListDataFromLocalDB(spVariety);
//        getPlantTypeListDataFromLocalDB(spPlantType);
    }

    public void getGwList() {
        try {
            viewModel.getGwList(seasonCode,farmerCode,plotNo,"0","Pests");
//            viewModel.getGwList("2022-23",farmerCode,plotNo,"0","Pests");
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

//
//    public void getD20WeedListFromLocalDb() {
//        try {
//            viewModel.getD20DiseaseList(plotNo);
//            viewModel.getD20DiseaseList(plotNo);
//            if (viewModel.getD20DiseaseListNotSyncLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        List<D20DiseaseTable> addFertilizerDetailsTables = (List<D20DiseaseTable>) o;
//                        viewModel.getD20DiseaseListNotSyncLiveData().removeObserver(this);
//
//                        if (addFertilizerDetailsTables != null && addFertilizerDetailsTables.size() > 0) {
//                            d20DiseaseTableArrayList = addFertilizerDetailsTables;
////                            List<DiseaseTable> diseaseTables = new ArrayList<>();
////
////                            diseaseDetailsListAdapter =new DiseaseDetailsListAdapter(DiseaseActivity.this,diseaseTables,d20DiseaseTableArrayList,DiseaseActivity.this,appHelper,viewModel);
////
////                            // setting grid layout manager to implement grid view.
////                            // in this method '2' represents number of columns to be displayed in grid view.
////                            LinearLayoutManager layoutManager=new LinearLayoutManager(DiseaseActivity.this,RecyclerView.VERTICAL,false);
////
////                            // at last set adapter to recycler view.
////                            recyclerDisease.setLayoutManager(layoutManager);
////                            recyclerDisease.setAdapter(diseaseDetailsListAdapter);
//
////                            for(int i=0;i<addFertilizerDetailsTables.size();i++){
////                                if(addFertilizerDetailsTables.get(i).getActive()){
////                                    d20WeedTableArrayListInsert.add(addFertilizerDetailsTables.get(i));
////                                }
////                            }
//
//                            getWeedlistFromLocalDb();
//                        } else {
//                            getWeedlistFromLocalDb();
//                        }
//
//
//                    }
//
//                };
//                viewModel.getD20DiseaseListNotSyncLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//        }
////        ui();
////        syncProgressDialog.dismiss();
//    }
//
//
//    public void getWeedlistFromLocalDb() {
//        try {
//            //appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
////            final ProgressDialog  progressDialog = new ProgressDialog(RegisterdFarmerListActivity.this, R.style.AppCompatAlertDialogStyle);
////            progressDialog.setCancelable(false);
////            progressDialog.setMessage("data loading...");
////            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////            progressDialog.show();
//            viewModel.getDiseaseTableDetailsListFromLocalDB();
//            if (viewModel.getDiseaseListLiveDataLocalDB() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        List<DiseaseTable> odVisitSurveyTableList = (List<DiseaseTable>) o;
//                        viewModel.getDiseaseListLiveDataLocalDB().removeObserver(this);
//                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {
////                            new Handler().postDelayed(new Runnable() {
////                                @Override
////                                public void run() {
////                                    try {
////                                        progressDialog.dismiss();
////                                    } catch (Exception e) {
////
////                                    }
////                                }
////                            }, 200);
//                            for(int i =0;i<odVisitSurveyTableList.size();i++){
//                                diseaseIdArrayList.add(String.valueOf(odVisitSurveyTableList.get(i).getId()));
//                            }
//                            diseaseDetailsListAdapter =new DiseaseDetailsListAdapter(PestsActivity.this,odVisitSurveyTableList,d20DiseaseTableArrayList,PestsActivity.this,appHelper,viewModel);
//
//                            // setting grid layout manager to implement grid view.
//                            // in this method '2' represents number of columns to be displayed in grid view.
//                            LinearLayoutManager layoutManager=new LinearLayoutManager(PestsActivity.this,RecyclerView.VERTICAL,false);
//
//                            // at last set adapter to recycler view.
//                            recyclerBio.setLayoutManager(layoutManager);
//                            recyclerBio.setAdapter(diseaseDetailsListAdapter);
//
//                            diseaseDetailsListAdapter.notifyDataSetChanged();
//                        } else {
//                            // progressDialog.dismiss();
//                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer list");
//                        }
//                    }
//                };
//                viewModel.getDiseaseListLiveDataLocalDB().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            //progressDialog.dismiss();
//            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//
//        }
//    }



    public void getD20WeedListFromLocalDb() {
        try {
            viewModel.getD20PestsList(plotNo);
            if (viewModel.getD20PestListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<D20PestTable> addFertilizerDetailsTables = (List<D20PestTable>) o;
                        viewModel.getD20PestListLiveData().removeObserver(this);

                        if (addFertilizerDetailsTables != null && addFertilizerDetailsTables.size() > 0) {
                            d20PestTableArrayList = addFertilizerDetailsTables;
                            for(int i=0;i<addFertilizerDetailsTables.size();i++){
//                                if(addFertilizerDetailsTables.get(i).getActive()){
                                    pestsIdArrayList.add(addFertilizerDetailsTables.get(i).getPestId());
//                                }
                            }
                            getWeedlistFromLocalDb();
                        } else {
                            getWeedlistFromLocalDb();
                        }


                    }

                };
                viewModel.getD20PestListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
//        ui();
//        syncProgressDialog.dismiss();
    }


    public void getWeedlistFromLocalDb() {
        try {
            //appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            final ProgressDialog  progressDialog = new ProgressDialog(RegisterdFarmerListActivity.this, R.style.AppCompatAlertDialogStyle);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("data loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.show();
            viewModel.getPestTableDetailsListFromLocalDB();
            if (viewModel.getPestListLiveDataLocalDB() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<PestTable> odVisitSurveyTableList = (List<PestTable>) o;
                        viewModel.getPestListLiveDataLocalDB().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        progressDialog.dismiss();
//                                    } catch (Exception e) {
//
//                                    }
//                                }
//                            }, 200);
//                            for(int i =0;i<odVisitSurveyTableList.size();i++){
//                                diseaseIdArrayList.add(String.valueOf(odVisitSurveyTableList.get(i).getId()));
//                            }
                            pestsDetailsListAdapter =new PestsDetailsListAdapter(PestsActivity.this,odVisitSurveyTableList,d20PestTableArrayList,PestsActivity.this,appHelper,viewModel);

                            // setting grid layout manager to implement grid view.
                            // in this method '2' represents number of columns to be displayed in grid view.
                            LinearLayoutManager layoutManager=new LinearLayoutManager(PestsActivity.this,RecyclerView.VERTICAL,false);

                            // at last set adapter to recycler view.
                            recyclerBio.setLayoutManager(layoutManager);
                            recyclerBio.setAdapter(pestsDetailsListAdapter);

                            pestsDetailsListAdapter.notifyDataSetChanged();
                        } else {
                            // progressDialog.dismiss();
                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer list");
                        }
                    }
                };
                viewModel.getPestListLiveDataLocalDB().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //progressDialog.dismiss();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

        }
    }



    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    @Override
    public void openScreenCallback(int position, PestTable farmerTable, List<PestTable> farmer, String applicationType) {

    }

    @Override
    public void updateItemCallback(int position, PestTable pestTable, String controlDate) {
        isChanged= true;
        if(isAvailable(String.valueOf(pestTable.getId()))){
            String diseaseId= String.valueOf(pestTable.getId());
            for(int i=0;i<d20PestTableArrayList.size();i++){
                if(diseaseId.equals(d20PestTableArrayList.get(i).getPestId())){
                    d20PestTableArrayList.get(i).setControlDate(controlDate);
                    d20PestTableArrayList.get(i).setSync(false);
                    d20PestTableArrayList.get(i).setServerStatus("0");

                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    d20PestTableArrayList.get(i).setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
                    d20PestTableArrayList.get(i).setUpdatedDate(dateTime);
//                    Log.e("testConrol",d20PestTableArrayList.get(i).getControlDate()+"test "+d20DiseaseTableArrayList.get(i).getIdentifiedDate()+"empty");

                    break;
                }

//            if(i+1==d20DiseaseTableArrayList.size()){
//                D20DiseaseTable d20DiseaseTable = new D20DiseaseTable();
//                d20DiseaseTable.setDiseasesId(diseaseId);
//                d20DiseaseTable.setControlDate(controlDate);
//                d20DiseaseTable.setIdentifiedDate("");
//                d20DiseaseTable.setPlotNo(plotNo);
//                d20DiseaseTable.setSeasonCode(seasonCode);
//                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//                Log.d("TAG", "onClick: date" + dateTime);
//                d20DiseaseTable.setCreatedDate(dateTime);
//                d20DiseaseTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
//                d20DiseaseTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
//                d20DiseaseTable.setUpdatedDate(dateTime);
//
//                d20DiseaseTableArrayList.add(d20DiseaseTable);
//
//            }
            }
        }
    }

    private boolean isAvailable(String seq){
        if(pestsIdArrayList.contains(seq)){
            return true;
        }
        return false;
    }


    @Override
    public void addPlotDetailsCallback(int position, PestTable pestTable, String identifiedDate) {
        isChanged= true;
        if(isAvailable(String.valueOf(pestTable.getId()))){
            String diseaseId= String.valueOf(pestTable.getId());
            for(int i=0;i<d20PestTableArrayList.size();i++){
                if(diseaseId.equals(d20PestTableArrayList.get(i).getPestId())){
                    d20PestTableArrayList.get(i).setIdentifiedDate(identifiedDate);
                    d20PestTableArrayList.get(i).setSync(false);
                    d20PestTableArrayList.get(i).setServerStatus("0");
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    d20PestTableArrayList.get(i).setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
                    d20PestTableArrayList.get(i).setUpdatedDate(dateTime);
                    break;
                }
//                if(i+1==d20DiseaseTableArrayList.size()){
//                    D20DiseaseTable d20DiseaseTable = new D20DiseaseTable();
//                    d20DiseaseTable.setDiseasesId(diseaseId);
//                    d20DiseaseTable.setControlDate("");
//                    d20DiseaseTable.setIdentifiedDate(identifiedDate);
//                    d20DiseaseTable.setPlotNo(plotNo);
//                    d20DiseaseTable.setSeasonCode(seasonCode);
//                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
//                    Log.d("TAG", "onClick: date" + dateTime);
//                    d20DiseaseTable.setCreatedDate(dateTime);
//                    d20DiseaseTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
//                    d20DiseaseTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
//                    d20DiseaseTable.setUpdatedDate(dateTime);
//
//                    d20DiseaseTableArrayList.add(d20DiseaseTable);
            }
        }
    }


    private void openCameraPermission(Boolean land) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!CommonUtils.isPermissionAllowed(PestsActivity.this, Manifest.permission.CAMERA))) {
            android.util.Log.v("MeasuredDetailsGrowthMonitoringActivity", "Location Permissions Not Granted");
            ActivityCompat.requestPermissions(
                    PestsActivity.this,
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


                        Uri photoURI = FileProvider.getUriForFile(PestsActivity.this,
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


                        Uri photoURI = FileProvider.getUriForFile(PestsActivity.this,
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
                mediaStorageDir = new File(PestsActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
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
                mediaStorageDir = new File(PestsActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
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


    @Override
    public void openScreenCallback(int position, DiseaseTable farmerTable, List<DiseaseTable> farmer, String applicationType) {

    }

    @Override
    public void updateItemCallback(int position, DiseaseTable applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, DiseaseTable applicationStatusTable, String strFarmercode) {

    }
}