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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.BuildConfig;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotD20AgreementedDetailsListAdapter;
import com.trst01.locationtracker.activity.plantation.AgreementedAreaActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotListActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.constant.ImageUtility;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddD30Table;
import com.trst01.locationtracker.database.entity.AddGrowthMonitoringTable;
import com.trst01.locationtracker.database.entity.CropTable;
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

public class AgreementedDetailsGrowthMonitoringActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    TextView txtSave;
    Spinner spInterCrop,spWeedStatus;
    RadioButton microNutrientDeficiencyYes,microNutrientDeficiencyNo,gapsFilledYes,gapsFilledNo,trashMulchingYes,trashMulchingNo;
    EditText edtBioFertilizersAppliedArea,edtDeepPloughedArea,edtDeTrashingArea,edtEarthingUpArea,edtRatoonManagedUsedArea,
            edtTrashShedderArea,edtLoadShedderArea;

    //previous crop id
    List<String> crops = new ArrayList<>();
    List<String> cropIds = new ArrayList<>();
    List<CropTable> cropTableList = new ArrayList<>();
    String cropId;
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
    String strPlotNumber,strFarmerCode,seasonCode;
    //weedStatus
    List<String> weedStatus = new ArrayList<>();
    List<String> weedStatusIds = new ArrayList<>();
    List<LookupDtlTable> weedStatusList = new ArrayList<>();
    String weedStatusId;

//    AddD10Table table= new AddD10Table();
    AddD30Table table= new AddD30Table();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreemented_details_growth_monitoring);

        strPlotNumber = getIntent().getStringExtra("plotNo");
        strFarmerCode = getIntent().getStringExtra("farmerCode");
        seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
        txtComplain = findViewById(R.id.txtComplain);
        spWeedStatus = findViewById(R.id.spWeedStatus);
        trashMulchingYes = findViewById(R.id.trashMulchingYes);
        trashMulchingNo = findViewById(R.id.trashMulchingNo);

        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgreementedDetailsGrowthMonitoringActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","AgreementedGM");
                intent.putExtra("strPlotNumber",strPlotNumber);
                intent.putExtra("strFarmerCode",strFarmerCode);
                intent.putExtra("seasonCode",seasonCode);
                startActivity(intent);
            }
        });

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

        txtSave = findViewById(R.id.txtSave);
        spInterCrop = findViewById(R.id.spInterCrop);
        microNutrientDeficiencyYes = findViewById(R.id.microNutrientDeficiencyYes);
        microNutrientDeficiencyNo = findViewById(R.id.microNutrientDeficiencyNo);
        gapsFilledYes = findViewById(R.id.gapsFilledYes);
        gapsFilledNo = findViewById(R.id.gapsFilledNo);
        edtBioFertilizersAppliedArea = findViewById(R.id.edtBioFertilizersAppliedArea);
        edtDeepPloughedArea = findViewById(R.id.edtDeepPloughedArea);
        edtDeTrashingArea = findViewById(R.id.edtDeTrashingArea);
        edtEarthingUpArea = findViewById(R.id.edtEarthingUpArea);
        edtRatoonManagedUsedArea = findViewById(R.id.edtRatoonManagedUsedArea);
        edtTrashShedderArea = findViewById(R.id.edtTrashShedderArea);
        edtLoadShedderArea = findViewById(R.id.edtLoadShedderArea);

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!strImageOnePath.isEmpty()&&!strImageTwoPath.isEmpty()){
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

                    String strComplainImage1 =  ImageUtility.convertBitmapToString(bitmapOne);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                    String strBankLocalImagePath = strImageOnePath;

                    AddGrowthMonitoringTable savingComplainImagesTable = new AddGrowthMonitoringTable();
                    savingComplainImagesTable.setId(null);
                    savingComplainImagesTable.setRemarks("30");
                    savingComplainImagesTable.setStage("30");
                    savingComplainImagesTable.setFarmerCode(strFarmerCode);
//                    String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
                    if(seasonCode.isEmpty()){
                        savingComplainImagesTable.setSeasonCode("2022-23");
                    } else {
                        savingComplainImagesTable.setSeasonCode(seasonCode);
                    }

                    savingComplainImagesTable.setFileUrl(strComplainImage1);
                    savingComplainImagesTable.setLocalDocUrl(strBankLocalImagePath);
                    savingComplainImagesTable.setSync(false);
                    savingComplainImagesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    savingComplainImagesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    savingComplainImagesTable.setCreatedDate(dateTime);
                    savingComplainImagesTable.setUpdatedDate(dateTime);
                    savingComplainImagesTable.setActive(true);
                    savingComplainImagesTable.setServerStatus("0");
                    savingComplainImagesTable.setPlotNo(strPlotNumber);
//                        savingComplainImagesTable.setLogBookNo(logbookno);


                    viewModel.insertGrowthMonitoringIntoLocalDBQuery(savingComplainImagesTable);

                    String strComplainImage2 =  ImageUtility.convertBitmapToString(bitmapTwo);
//                        String strComplainImage1 = "data:image/png;base64," + ImageUtility.convertBitmapToString(bitmapLand1);
                    String strBankLocalImagePath2 = strImageTwoPath;

                    AddGrowthMonitoringTable addGrowthMonitoringTable = new AddGrowthMonitoringTable();
                    addGrowthMonitoringTable.setId(null);
                    addGrowthMonitoringTable.setRemarks("30");
                    addGrowthMonitoringTable.setStage("30");

                    addGrowthMonitoringTable.setFarmerCode(strFarmerCode);
                    if(seasonCode.isEmpty()){
                        addGrowthMonitoringTable.setSeasonCode("2022-23");
                    } else {
                        addGrowthMonitoringTable.setSeasonCode(seasonCode);
                    }

                    addGrowthMonitoringTable.setFileUrl(strComplainImage2);
                    addGrowthMonitoringTable.setLocalDocUrl(strBankLocalImagePath2);
                    addGrowthMonitoringTable.setSync(false);
                    addGrowthMonitoringTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGrowthMonitoringTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGrowthMonitoringTable.setCreatedDate(dateTime);
                    addGrowthMonitoringTable.setUpdatedDate(dateTime);
                    addGrowthMonitoringTable.setActive(true);
                    addGrowthMonitoringTable.setServerStatus("0");
                    addGrowthMonitoringTable.setPlotNo(strPlotNumber);
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
                        savingComplainImagesTable.setRemarks("30");
                        savingComplainImagesTable.setStage("30");

                        savingComplainImagesTable.setFarmerCode(strFarmerCode);
//                        String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
                        if(seasonCode.isEmpty()){
                            savingComplainImagesTable.setSeasonCode("2022-23");
                        } else {
                            savingComplainImagesTable.setSeasonCode(seasonCode);
                        }

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
                        savingComplainImagesTable.setPlotNo(strPlotNumber);
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
                        addGrowthMonitoringTable.setRemarks("30");
                        addGrowthMonitoringTable.setStage("30");

                        addGrowthMonitoringTable.setFarmerCode(strFarmerCode);
                        if(seasonCode.isEmpty()){
                            addGrowthMonitoringTable.setSeasonCode("2022-23");
                        } else {
                            addGrowthMonitoringTable.setSeasonCode(seasonCode);
                        }
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
                        addGrowthMonitoringTable.setPlotNo(strPlotNumber);
//                        savingComplainImagesTable.setLogBookNo(logbookno);

                        viewModel.insertGrowthMonitoringIntoLocalDBQuery(addGrowthMonitoringTable);

                    }

                }

                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                String micro="";
                String trash="";
                String gaps="";
                if(microNutrientDeficiencyYes.isChecked()){
                    micro="true";
                } else {
                    micro="false";
                }
                if(trashMulchingYes.isChecked()){
                    trash="true";
                } else {
                    trash="false";
                }
                if(gapsFilledYes.isChecked()){
                    gaps="true";
                } else {
                    gaps="false";
                }
                String bio="";
                if(edtBioFertilizersAppliedArea.getText().toString().isEmpty()){
                    bio="";
                } else {
                    bio=edtBioFertilizersAppliedArea.getText().toString();
                }
                String deep="";
                if(edtDeepPloughedArea.getText().toString().isEmpty()){
                    deep="";
                } else {
                    deep=edtDeepPloughedArea.getText().toString();
                }
                String deTrash="";
                if(edtDeTrashingArea.getText().toString().isEmpty()){
                    deTrash="";
                } else {
                    deTrash=edtDeTrashingArea.getText().toString();
                }
                String earth="";
                if(edtEarthingUpArea.getText().toString().isEmpty()){
                    earth="";
                } else {
                    earth=edtEarthingUpArea.getText().toString();
                }
                String ratoon="";
                if(edtRatoonManagedUsedArea.getText().toString().isEmpty()){
                    ratoon="";
                } else {
                    ratoon=edtRatoonManagedUsedArea.getText().toString();
                }
                String trashShedder="";
                if(edtTrashShedderArea.getText().toString().isEmpty()){
                    trashShedder="";
                } else {
                    trashShedder=edtTrashShedderArea.getText().toString();
                }
                String load="";
                if(edtLoadShedderArea.getText().toString().isEmpty()){
                    load="";
                } else {
                    load=edtLoadShedderArea.getText().toString();
                }
                Log.e("agreemented save",cropId+" - "+ weedStatusId);
//                viewModel.updateD10Agreemented(cropId,micro,trash,gaps,weedStatusId,bio,deep,deTrash,earth,ratoon,trashShedder,load,false,dateTime,strPlotNumber,"0");
                viewModel.updateD30Agreemented(cropId,micro,trash,gaps,weedStatusId,bio,deep,deTrash,earth,ratoon,trashShedder,load,false,dateTime,strPlotNumber,"0");



                finish();
            }
        });


        spInterCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    if(position>0) {
                    cropId = String.valueOf(cropTableList.get(position).getId());
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spWeedStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
//                    if(position>0){
                    weedStatusId= String.valueOf(weedStatusList.get(position).getId());
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
    }

    private void openCameraPermission(Boolean land) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!CommonUtils.isPermissionAllowed(AgreementedDetailsGrowthMonitoringActivity.this, Manifest.permission.CAMERA))) {
            android.util.Log.v("MeasuredDetailsGrowthMonitoringActivity", "Location Permissions Not Granted");
            ActivityCompat.requestPermissions(
                    AgreementedDetailsGrowthMonitoringActivity.this,
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


                        Uri photoURI = FileProvider.getUriForFile(AgreementedDetailsGrowthMonitoringActivity.this,
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


                        Uri photoURI = FileProvider.getUriForFile(AgreementedDetailsGrowthMonitoringActivity.this,
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
                mediaStorageDir = new File(AgreementedDetailsGrowthMonitoringActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
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
                mediaStorageDir = new File(AgreementedDetailsGrowthMonitoringActivity.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "CoreCarbonLand");
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

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
//        getInterCropListDataFromLocalDB(spInterCrop);
        getGwList();
        if(seasonCode.isEmpty()){
            getD10Data(strPlotNumber,"2022-23");
        } else {
            getD10Data(strPlotNumber,seasonCode);
        }
//        getD10Data(strPlotNumber,"2022-23");
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

    public void getWeedStatusHDRListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("WeedStatus");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getWeedStatusListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No WeedStatus list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getWeedStatusListDataFromLocalDB(Spinner spSelectSeason, Integer id) {
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
                        weedStatus.clear();
                        weedStatusIds.clear();
                        weedStatusList.clear();
                        weedStatusList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            weedStatus.add("Select WeedStatus *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                weedStatus.add(seasonTableList.get(i).getName());
                                weedStatusIds.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AgreementedDetailsGrowthMonitoringActivity.this,
                                    R.layout.spinner_dropdown_layout, weedStatus);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            if(table.getWeedStatusId()!=null){

                                int pos = setSpinnerPosition(weedStatusIds,table.getWeedStatusId());
                                spWeedStatus.setSelection(pos,true);
                                spWeedStatus.setEnabled(false);
                                spWeedStatus.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.appColor));
                            }
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

    public void getD10Data(String plotNum,String seasonCode) {
        try {
            viewModel.getD30Data(plotNum,seasonCode);
            if (viewModel.getInsertD30IntoLocalDBQueryLiveDataLocalDB() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AddD30Table addD10Table = (AddD30Table) o;
                        viewModel.getInsertD30IntoLocalDBQueryLiveDataLocalDB().removeObserver(this);

                        if (addD10Table != null ) {

                            table = addD10Table;

                            Log.e("agreemented",table.getInterCropId()+" - "+ table.getWeedStatusId());

//                            if(addD10Table.getInterCropId()!=null){
//
//                                int pos = setSpinnerPosition(cropIds,addD10Table.getInterCropId());
//                                spPreviousLandUse.setSelection(pos,true);
//                                spPreviousLandUse.setEnabled(false);
//                            }

//                            if(addD10Table.getWeedStatusId()!=null){
//
//                                int pos = setSpinnerPosition(weedStatusIds,addD10Table.getWeedStatusId());
//                                spWeedStatus.setSelection(pos,true);
//                                spWeedStatus.setEnabled(false);
//                            }
                            getInterCropListDataFromLocalDB(spInterCrop);
                            getWeedStatusHDRListDataFromLocalDB(spWeedStatus);

                            if(addD10Table.getIsMicronutrientDeficiency()!=null&&addD10Table.getIsMicronutrientDeficiency().equalsIgnoreCase("true")){
                                microNutrientDeficiencyYes.setChecked(true);
                                microNutrientDeficiencyYes.setEnabled(false);
                                microNutrientDeficiencyNo.setEnabled(false);
                            }

                            if(addD10Table.getIsTrashMulching()!=null&&addD10Table.getIsTrashMulching().equalsIgnoreCase("true")){
                                trashMulchingYes.setChecked(true);
                                trashMulchingYes.setEnabled(false);
                                trashMulchingNo.setEnabled(false);
                            }
                            if(addD10Table.getIsGapsFilled()!=null&&addD10Table.getIsGapsFilled().equalsIgnoreCase("true")){
                                gapsFilledYes.setChecked(true);
                                gapsFilledYes.setEnabled(false);
                                gapsFilledNo.setEnabled(false);
                            }

                            if(addD10Table.getBioFertilizerAppliedArea()!=null&&!addD10Table.getBioFertilizerAppliedArea().isEmpty()){
                                edtBioFertilizersAppliedArea.setText(addD10Table.getBioFertilizerAppliedArea());
                                edtBioFertilizersAppliedArea.setEnabled(false);
                            }

                            if(addD10Table.getDeepPloughedArea()!=null&&!addD10Table.getDeepPloughedArea().isEmpty()){
                                edtDeepPloughedArea.setText(addD10Table.getDeepPloughedArea());
                                edtDeepPloughedArea.setEnabled(false);
                            }

                            if(addD10Table.getDeTrashingArea()!=null&&!addD10Table.getDeTrashingArea().isEmpty()){
                                edtDeTrashingArea.setText(addD10Table.getDeTrashingArea());
                                edtDeTrashingArea.setEnabled(false);
                            }

                            if(addD10Table.getEarthingUpArea()!=null&&!addD10Table.getEarthingUpArea().isEmpty()){
                                edtEarthingUpArea.setText(addD10Table.getEarthingUpArea());
                                edtEarthingUpArea.setEnabled(false);
                            }

                            if(addD10Table.getRatoonManagedUsedArea()!=null&&!addD10Table.getRatoonManagedUsedArea().isEmpty()){
                                edtRatoonManagedUsedArea.setText(addD10Table.getRatoonManagedUsedArea());
                                edtRatoonManagedUsedArea.setEnabled(false);
                            }

                            if(addD10Table.getTrashShedderArea()!=null&&!addD10Table.getTrashShedderArea().isEmpty()){
                                edtTrashShedderArea.setText(addD10Table.getTrashShedderArea());
                                edtTrashShedderArea.setEnabled(false);
                            }

                            if(addD10Table.getLoadShedderArea()!=null&&!addD10Table.getLoadShedderArea().isEmpty()){
                                edtLoadShedderArea.setText(addD10Table.getLoadShedderArea());
                                edtLoadShedderArea.setEnabled(false);
                            }


                        }


                    }

                };
                viewModel.getInsertD30IntoLocalDBQueryLiveDataLocalDB().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

//    public void getAgreementedFarmerPLotListFromLocalDb(String farmerCode) {
//        try {
//            INSERT_LOG("getFarmerPLotListFromLocalDb", "BEGIN");
//            //appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
////            final ProgressDialog  progressDialog = new ProgressDialog(RegisterdFarmerListActivity.this, R.style.AppCompatAlertDialogStyle);
////            progressDialog.setCancelable(false);
////            progressDialog.setMessage("data loading...");
////            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////            progressDialog.show();
//            viewModel.getLandD20DetailsStageListFromLocalDb("2022-23",farmerCode);
//            if (viewModel.getD20DetailsListLiveData() != null) {
////            if (viewModel.getD20DetailsListLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
////                        List<AddD10Table> odVisitSurveyTableList = (List<AddD10Table>) o;
//                        List<AddD20Table> odVisitSurveyTableList = (List<AddD20Table>) o;
//                        viewModel.getD20DetailsListLiveData().removeObserver(this);
//                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {
//
//                            agreementedDetailsListAdapter = new PlotD20AgreementedDetailsListAdapter(ViewStatusPlotListActivity.this,
//                                    odVisitSurveyTableList, ViewStatusPlotListActivity.this, appHelper, viewModel);
//                            rvFarmer.setAdapter(agreementedDetailsListAdapter);
//                            agreementedDetailsListAdapter.notifyDataSetChanged();
//                        } else {
//
//                            agreementedDetailsListAdapter = new PlotD20AgreementedDetailsListAdapter(ViewStatusPlotListActivity.this,
//                                    odVisitSurveyTableList, ViewStatusPlotListActivity.this, appHelper, viewModel);
//                            rvFarmer.setAdapter(agreementedDetailsListAdapter);
//                            agreementedDetailsListAdapter.notifyDataSetChanged();
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Plot list");
//                        }
//                    }
//                };
//                viewModel.getD20DetailsListLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            //progressDialog.dismiss();
//            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
//        }
//    }

    public void getGwList() {
        try {
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getGwList("2022-23",strFarmerCode,strPlotNumber,"30","30");
            } else {
                viewModel.getGwList(seasonCode,strFarmerCode,strPlotNumber,"30","30");
            }
//            viewModel.getGwList("2022-23",strFarmerCode,strPlotNumber,"30","30");
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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AgreementedDetailsGrowthMonitoringActivity.this,
                                    R.layout.spinner_dropdown_layout, crops);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            if(table.getInterCropId()!=null){

                                int pos = setSpinnerPosition(cropIds,table.getInterCropId());
                                spInterCrop.setSelection(pos,true);
                                spInterCrop.setEnabled(false);
                                spInterCrop.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.appColor));
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


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }
}