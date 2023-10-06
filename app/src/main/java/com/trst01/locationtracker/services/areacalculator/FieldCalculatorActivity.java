package com.trst01.locationtracker.services.areacalculator;

import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotD20AgreementedDetailsListAdapter;
import com.trst01.locationtracker.activity.plantation.ChoosePlantationActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotListActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddLogBookGeoBoundariesTable;
import com.trst01.locationtracker.helper.ApplicationThread;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import letsrock.areaviewlib.AreaView;
import letsrock.areaviewlib.GPSCoordinate;

public class FieldCalculatorActivity extends BaseActivity {
    private static final String LOG_TAG = FieldCalculatorActivity.class.getName();
    private static final int PERMISSION_REQUEST_CODE = 1;
    public static List<GPSCoordinate> firstFourCoordinates = new ArrayList<>();
    public static List<GPSCoordinate> recordedBoundries = new ArrayList<>();
    public static List<GPSCoordinate> totalBoundries = new ArrayList<>();
    private AreaView measureView;
    private Button startStopButton,saveBtn,resetBtn;
    private Context c;
    private LocationManager locationManager;
    private LinkedHashMap<String, String> latLongMap = new LinkedHashMap<>();
    private Button recordBtn;
    private RecyclerView recordsList;
    String logBookNo = "";
    String plot = "";
    String seasonCode = "";
    String area = "";
    int indicator = 1;
    double estimatedTon = 0.0;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;
    int delayed = 20000;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public AppViewModel viewModel;
    
    RecordedCoordinatesAdapter recordedCoordinatesAdapter;

    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getExtras() != null) {
                latLongMap.put(String.valueOf(intent.getExtras().getDouble("latitude")), String.valueOf(intent.getExtras().getDouble("longitude")));

                if (null != firstFourCoordinates && firstFourCoordinates.size() <= 4) {
                    firstFourCoordinates.add(new GPSCoordinate(intent.getExtras().getDouble("latitude"), intent.getExtras().getDouble("longitude"), 0));
                }
                totalBoundries.add(new GPSCoordinate(intent.getExtras().getDouble("latitude"), intent.getExtras().getDouble("longitude"), 0));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = this;
        logBookNo = getIntent().getStringExtra("logBookNo");
        plot = getIntent().getStringExtra("plot");
        seasonCode = getIntent().getStringExtra("seasonCode");
        estimatedTon = Double.parseDouble(getIntent().getStringExtra("estimatedTon"));
        area = getIntent().getStringExtra("area");
//        indicator = getIntent().getIntExtra("indicator",0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isLocationPermissionGranted()) {
            Log.v(LOG_TAG, "Location Permissions Not Granted");
            requestLocationPermissions();
        } else {
            initViews();

        }

        configureDagger();
        configureViewModel();

        LocalBroadcastManager.getInstance(this).registerReceiver(locationReceiver, new IntentFilter("location_receiver"));

    }


    private void configureDagger() {
        AndroidInjection.inject(this);

    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
    }

    public void initViews() {

        setContentView(R.layout.activity_field_caluculator);
        recordedBoundries.clear();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        recordBtn = (Button) findViewById(R.id.recordBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        resetBtn = (Button) findViewById(R.id.reset);
        recordsList = (RecyclerView) findViewById(R.id.gpsRecords);
        recordsList.setLayoutManager(new LinearLayoutManager(FieldCalculatorActivity.this, LinearLayoutManager.VERTICAL, false));

        recordBtn.setOnClickListener(view -> {
            Toast.makeText(c, "clicked", Toast.LENGTH_SHORT).show();
            startLocationServiceCheck(false);
//            if (measureView.isRunning()) {
//                GPSCoordinate pointsToRecord;
//                if (null != recordedBoundries && recordedBoundries.size() > 0) {
//                    double distance = CommonUtils.distance(recordedBoundries.get(recordedBoundries.size() - 1).latitude,
//                            recordedBoundries.get(recordedBoundries.size() - 1).longitude, AreaView.latitude, AreaView.longitude, 'M');
//                    pointsToRecord = new GPSCoordinate(AreaView.latitude, AreaView.longitude, distance);
//                } else {
//                    pointsToRecord = new GPSCoordinate(AreaView.latitude, AreaView.longitude, 0.0);
//                }
//
//                recordedBoundries.add(pointsToRecord);
//
//                recordsList.setAdapter(new RecordedCoordinatesAdapter(FieldCalculatorActivity.this, recordedBoundries));
//            }
        });
        resetBtn.setOnClickListener(view -> {
            measureView.reset();
            measureView.invalidate();
            recordedBoundries.clear();
            recordsList.setAdapter(new RecordedCoordinatesAdapter(FieldCalculatorActivity.this, recordedBoundries));

        });

        saveBtn.setOnClickListener(view -> {
            double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
            if (measureView.isRunning()) {
                Toast.makeText(FieldCalculatorActivity.this, "Please stop area measuring and save", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!measureView.isReadyToStart()) {
                Toast.makeText(FieldCalculatorActivity.this, "Gps signal not received", Toast.LENGTH_SHORT).show();
                return;
            }
            if (measuredArea > 0) {
//                displayAreaAreaDialog();
                displayAreaAreaDialog();
            } else {
                Toast.makeText(FieldCalculatorActivity.this, "Area is not measured", Toast.LENGTH_SHORT).show();
            }

//           saveLatLongData();
        });

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            displayGpsDialog();
        }

        measureView = (AreaView) findViewById(R.id.measureView);
        measureView.setLengthUnits(AreaView.LENGTH_UNITS_KILOMETER);
        measureView.setAreaUnits(AreaView.AREA_UNITS_ACRE);
        startStopButton = (Button) findViewById(R.id.startBtn);
        startStopButton.setOnClickListener(view -> {

            if (measureView.isReadyToStart()) {
                measureView.start();
                startStopButton.setText(c.getString(R.string.stop));
                startStopButton.postInvalidate();
                startLocationServiceCheck(true);
//                checkStart();

            } else if (measureView.isRunning()) {
                measureView.stop();
                startStopButton.setText(c.getString(R.string.start));
                startStopButton.postInvalidate();
            } else {
                Toast.makeText(FieldCalculatorActivity.this, "Waiting for gps signal", Toast.LENGTH_SHORT).show();
            }
        });


        recordedCoordinatesAdapter = new RecordedCoordinatesAdapter(FieldCalculatorActivity.this, recordedBoundries);
        recordsList.setAdapter(recordedCoordinatesAdapter);
        recordedCoordinatesAdapter.notifyDataSetChanged();

    }
    private void checkStart() {
//        ApplicationThread.nuiPost(LOG_TAG, "start location check", new Runnable() {
//            @Override
//            public void run() {
//                handler.postDelayed(runnable = new Runnable() {
//                    public void run() {
//                        handler.postDelayed(runnable, delayed);
////                        Toast.makeText(context, "This method is run every 10 seconds", Toast.LENGTH_SHORT).show();
//                        startLocationServiceCheck(false);
//                    }
//                }, delayed);
//                startLocationServiceCheck(true);
//
//            }
//        });
    }

    private void startLocationServiceCheck(boolean yes) {
//        Toast.makeText(c, "startLocationServiceCheck", Toast.LENGTH_SHORT).show();
        if (measureView.isRunning()) {
            GPSCoordinate pointsToRecord;
            if (null != recordedBoundries && recordedBoundries.size() > 0) {
                double distance = CommonUtils.distance(recordedBoundries.get(recordedBoundries.size() - 1).latitude,
                        recordedBoundries.get(recordedBoundries.size() - 1).longitude, AreaView.latitude, AreaView.longitude, 'M');
                pointsToRecord = new GPSCoordinate(AreaView.latitude, AreaView.longitude, distance);
            } else {
                pointsToRecord = new GPSCoordinate(AreaView.latitude, AreaView.longitude, 0.0);
            }

            recordedBoundries.add(pointsToRecord);

            recordsList.setAdapter(new RecordedCoordinatesAdapter(FieldCalculatorActivity.this, recordedBoundries));
            try {
//                Toast.makeText(c, recordedBoundries.size(), Toast.LENGTH_SHORT).show();
            } catch (Exception e){
//                Toast.makeText(c, e.getMessage()+"  size is zero?", Toast.LENGTH_SHORT).show();
            }

        }

        if(yes){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startLocationServiceCheck(true);
                }

            }, 1 * 10000);
        }

    }

    public boolean isLocationPermissionGranted() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        return hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
                && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED;
    }

    public void requestLocationPermissions() {
        if (!isLocationPermissionGranted()) {
            String[] perms = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, perms, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    initViews();

                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        displayGpsDialog();
                    }

                } else {

                }
                break;
        }
    }

    private void displayGpsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS is turned off ,Please Enable GPS").setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(settingsIntent);
                    }
                });
        builder.show();

    }

    private void displayAreaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
        builder.setMessage("Total field area is : " + measuredArea + " " + measureView.getAreaUnit()).setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                saveLatLongData();
            }
        });
        builder.show();

    }


    private void displayAreaAreaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
//        double diffPercentage = CommonUtils.getPercentage(measuredArea, ConversionLandDetailsFragment.plotEnteredArea);
        double diffPercentage = 0;
        double roundedValue = 0.0;

        try {
            DecimalFormat f = new DecimalFormat("##.000000");
            String formattedValue = f.format(diffPercentage);
            if (!TextUtils.isEmpty(formattedValue)) {
                roundedValue = Double.parseDouble(formattedValue);
            }
        } catch (Exception e) {
            roundedValue = 0;
        }

        double acre_value = measuredArea ;
//        double acre_value = measuredArea * 2.47105;

        String message = "Total field area is : " + acre_value + " " + measureView.getAreaUnit();
//        String message = "Total field area is : " + measuredArea + " " + measureView.getAreaUnit();

        if (diffPercentage >= 60 && roundedValue != Double.POSITIVE_INFINITY && diffPercentage != Double.NEGATIVE_INFINITY) {
            message = message + "\n Variation between Plot area and Gps area is " + roundedValue + " %";
        }
        builder.setMessage(message).setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                saveLatLongData();
            }
        });
        builder.show();

    }

    public void saveLatLongData() {
//        ProgressBar.showProgressBar(FieldCalculatorActivity.this, "Saving Gps data");
        try {

            if (recordedBoundries.isEmpty()) {
                recordedBoundries.addAll(totalBoundries);
            }

            if(indicator==1){
                for (int i=0;i<recordedBoundries.size();i++){

//                for (latLngList.size()=0,latLngList.size()>1;latLngList.size()-1){
//
//                }
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

                    AddGeoBoundriesTable addGeoBoundriesTable = new AddGeoBoundriesTable();
                    addGeoBoundriesTable.setGeoBoundriesId(addGeoBoundriesTable.getGeoBoundriesId());
//                    addGeoBoundriesTable.setLogBookNo(logBookNo);
//                addGeoBoundriesTable.setLogBookNo("test123");
                    addGeoBoundriesTable.setPlotNo(plot);
                    addGeoBoundriesTable.setSeasonCode(seasonCode);

                    addGeoBoundriesTable.setLatitude(String.valueOf(recordedBoundries.get(i).latitude));
                    addGeoBoundriesTable.setLongitude(String.valueOf(recordedBoundries.get(i).longitude));
                    double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
                    double acre_value = measuredArea ;
//                    double acre_value = measuredArea * 2.47105;
                    addGeoBoundriesTable.setArea(String.valueOf(acre_value));
//                    addGeoBoundriesTable.setArea(String.valueOf(measuredArea));
//                    addGeoBoundriesTable.setTotalPlotArea(String.valueOf(measuredArea));
//                    addGeoBoundriesTable.setCultureArea("null");
//                    addGeoBoundriesTable.setSync(false);
//                    addGeoBoundriesTable.setServerStatus("0");
                    addGeoBoundriesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGeoBoundriesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGeoBoundriesTable.setIsActive(true);
                    addGeoBoundriesTable.setCreatedDate(dateTime);
                    addGeoBoundriesTable.setSync(false);
//                    addGeoBoundriesTable.setSQNo(dateTime);
                    addGeoBoundriesTable.setUpdatedDate(dateTime);
//                    addGeoBoundriesTable.setUpdatedDate(dateTime);
                    addGeoBoundriesTable.setStage("20");
//                    addGeoBoundriesTable.setSeqNo(String.valueOf(i+1));

                    insertPlotGeoBoundsToLocalDB(addGeoBoundriesTable);

                    if(recordedBoundries.size()==i+1){

//                    double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
//                    Intent intent = new Intent();
//                    intent.putExtra("area", measuredArea);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                        Intent intent= new Intent(FieldCalculatorActivity.this, ViewGpsBoundaries.class);
//                        intent.putExtra("Area",measuredArea);
//                        intent.putExtra("PlotId",plot);
//                        intent.putExtra("indicator",1);
//                        startActivity(intent);
                        AddD20Table addD20Table = new AddD20Table();
                        viewModel.updatePLotNo(String.valueOf(measuredArea),(String.valueOf(measuredArea*estimatedTon)) ,appHelper.getSharedPrefObj().getString(DeviceUserID, ""),dateTime,plot);
                        viewModel.updatePLotNoStage(String.valueOf(measuredArea),appHelper.getSharedPrefObj().getString(DeviceUserID, ""),dateTime,plot);
//                        viewModel.updatePLotNo(String.valueOf(measuredArea),"1",dateTime,plot);
//                        viewModel.updatePLotNoStage(String.valueOf(measuredArea),"1",dateTime,plot);
                        viewModel.insertD20IntoLocalDBQuery(addD20Table);

                        finish();

                    }

                }
            }
            else {
                for (int i=0;i<recordedBoundries.size();i++){

//                for (latLngList.size()=0,latLngList.size()>1;latLngList.size()-1){
//
//                }
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

                    AddLogBookGeoBoundariesTable addGeoBoundriesTable = new AddLogBookGeoBoundariesTable();

//                    addGeoBoundriesTable.setLogBookNo(logBookNo);
//                addGeoBoundriesTable.setLogBookNo("test123");
                    addGeoBoundriesTable.setPlotId(plot);
                    addGeoBoundriesTable.setLatitude(String.valueOf(recordedBoundries.get(i).latitude));
                    addGeoBoundriesTable.setLongitude(String.valueOf(recordedBoundries.get(i).longitude));
                    double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
                    addGeoBoundriesTable.setTotalPlotArea(area);
                    addGeoBoundriesTable.setCultureArea(String.valueOf(measuredArea));
                    addGeoBoundriesTable.setSync("false");
//                    addGeoBoundriesTable.setServerStatus("0");
                    addGeoBoundriesTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGeoBoundriesTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addGeoBoundriesTable.setIsActive("1");
                    addGeoBoundriesTable.setCreatedDate(dateTime);
                    addGeoBoundriesTable.setSQNo(dateTime);
                    addGeoBoundriesTable.setUpdatedDate(dateTime);
                    addGeoBoundriesTable.setUpdatedDate(dateTime);
                    addGeoBoundriesTable.setSQNo(String.valueOf(i+1));

                    insertLogBookGeoBoundsToLocalDB(addGeoBoundriesTable);

                    if(recordedBoundries.size()==i+1){

//                    double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
//                    Intent intent = new Intent();
//                    intent.putExtra("area", measuredArea);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                        Intent intent= new Intent(FieldCalculatorActivity.this, ViewGpsBoundaries.class);
//                        intent.putExtra("Area",measuredArea);
//                        intent.putExtra("indicator",1);
//                        startActivity(intent);
                        finish();

                    }

                }
            }



        } catch (Exception e) {
            Log.v(LOG_TAG, "@@@@ Error while saving lat longs");
        }

    }

    @Override
    public void onBackPressed() {
        totalBoundries.clear();
        recordedBoundries.clear();
        firstFourCoordinates.clear();
//        Intent intent = new Intent();
//        intent.putExtra("area", 0.0);
//        setResult(RESULT_OK, intent);


//        Intent intent = new Intent(FieldCalculatorActivity.this, ViewStatusPlotListActivity.class);
////                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
////                intent.putExtra("indicator",2);
//        startActivity(intent);
        finish();
    }

    public class RecordedCoordinatesAdapter extends RecyclerView.Adapter<RecordedCoordinatesAdapter.MyHolder> {
        private Context mContext;
        private List<GPSCoordinate> gpsCoordinates;


        public RecordedCoordinatesAdapter(Context mContext, List<GPSCoordinate> gpsCoordinates) {
            this.mContext = mContext;
            this.gpsCoordinates = gpsCoordinates;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View bookingView = inflater.inflate(R.layout.records_list_item, null);
            MyHolder myHolder = new MyHolder(bookingView);
            return myHolder;
        }


        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.latitude.setText("" + gpsCoordinates.get(position).latitude);
            holder.longitude.setText("" + gpsCoordinates.get(position).longitude);
            if (gpsCoordinates != null && gpsCoordinates.size() > 1) {
                holder.distance.setText("" + gpsCoordinates.get(position).altitude + " Meters");

            } else {
                holder.distance.setText("0 " + "Meters");
            }
        }

        @Override
        public int getItemCount() {
            return gpsCoordinates.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            TextView latitude, longitude, distance;
            public MyHolder(View itemView) {
                super(itemView);
                latitude = (TextView) itemView.findViewById(R.id.latitude);
                longitude = (TextView) itemView.findViewById(R.id.longitude);
                distance = (TextView) itemView.findViewById(R.id.distance);
            }
        }
    }

    private void insertPlotGeoBoundsToLocalDB(AddGeoBoundriesTable addGeoBoundriesTable) {
        try {
            //  appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.insertPlotGeoBoundsTableLocal(addGeoBoundriesTable);
            if (viewModel.PlotGeoBoundsTableLiveDataInsert() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AddGeoBoundriesTable customerSurveyTable1 = (AddGeoBoundriesTable) o;
                        viewModel.PlotGeoBoundsTableLiveDataInsert().removeObserver(this);
                        if (customerSurveyTable1 != null) {
                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                            Toast.makeText(FieldCalculatorActivity.this, "Geobounds details are saved successfully", Toast.LENGTH_LONG).show();

//                            Intent intent= new Intent(GepBoundariesMap.this, PlotDetailsActivity.class);
//                            intent.putExtra("Area",txtArea.getText().toString());
//                            startActivity(intent);
//                            finish();
                        }
                    }
                };
                viewModel.PlotGeoBoundsTableLiveDataInsert().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }

    }

    private void insertLogBookGeoBoundsToLocalDB(AddLogBookGeoBoundariesTable addGeoBoundriesTable) {
//        try {
//            //  appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            viewModel.insertLogBookGeoBoundsTableLocal(addGeoBoundriesTable);
//            if (viewModel.logBookGeoBoundsTableLiveDataInsert() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        AddLogBookGeoBoundariesTable customerSurveyTable1 = (AddLogBookGeoBoundariesTable) o;
//                        viewModel.logBookGeoBoundsTableLiveDataInsert().removeObserver(this);
//                        if (customerSurveyTable1 != null) {
//                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                            Toast.makeText(FieldCalculatorActivity.this, "Geobounds details are saved successfully", Toast.LENGTH_LONG).show();
//
////                            Intent intent= new Intent(GepBoundariesMap.this, PlotDetailsActivity.class);
////                            intent.putExtra("Area",txtArea.getText().toString());
////                            startActivity(intent);
////                            finish();
//                        }
//                    }
//                };
//                viewModel.logBookGeoBoundsTableLiveDataInsert().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//        }

    }



}
//    private static final String LOG_TAG = FieldCalculatorActivity.class.getName();
//    private static final int PERMISSION_REQUEST_CODE = 1;
//    public static List<GPSCoordinate> firstFourCoordinates = new ArrayList<>();
//    public static List<GPSCoordinate> recordedBoundries = new ArrayList<>();
//    public static List<GPSCoordinate> totalBoundries = new ArrayList<>();
//    private AreaView measureView;
//    private Button startStopButton, saveBtn, resetBtn;
//    private Context c;
//    private LocationManager locationManager;
//    private LinkedHashMap<String, String> latLongMap = new LinkedHashMap<>();
//    private Button recordBtn;
//    private RecyclerView recordsList;
//
//    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent != null && intent.getAction() != null && intent.getExtras() != null) {
//                latLongMap.put(String.valueOf(intent.getExtras().getDouble("latitude")), String.valueOf(intent.getExtras().getDouble("longitude")));
//
//                if (null != firstFourCoordinates && firstFourCoordinates.size() <= 4) {
//                    firstFourCoordinates.add(new GPSCoordinate(intent.getExtras().getDouble("latitude"), intent.getExtras().getDouble("longitude"), 0));
//                }
//                totalBoundries.add(new GPSCoordinate(intent.getExtras().getDouble("latitude"), intent.getExtras().getDouble("longitude"), 0));
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        c = this;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isLocationPermissionGranted()) {
//            Log.v(LOG_TAG, "Location Permissions Not Granted");
//            requestLocationPermissions();
//        } else {
//            initViews();
//        }
//
//        LocalBroadcastManager.getInstance(this).registerReceiver(locationReceiver, new IntentFilter("location_receiver"));
//
//    }
//
//    public void initViews() {
//
//        setContentView(R.layout.activity_field_caluculator);
//        recordedBoundries.clear();
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        recordBtn = (Button) findViewById(R.id.recordBtn);
//        saveBtn = (Button) findViewById(R.id.saveBtn);
//        resetBtn = (Button) findViewById(R.id.reset);
//        recordsList = (RecyclerView) findViewById(R.id.gpsRecords);
//        recordsList.setLayoutManager(new LinearLayoutManager(FieldCalculatorActivity.this, LinearLayoutManager.VERTICAL, false));
//
//        recordBtn.setOnClickListener(view -> {
//
//            if (measureView.isRunning()) {
//                GPSCoordinate pointsToRecord;
//                if (null != recordedBoundries && recordedBoundries.size() > 0) {
//                    double distance = CommonUtils.distance(recordedBoundries.get(recordedBoundries.size() - 1).latitude,
//                            recordedBoundries.get(recordedBoundries.size() - 1).longitude, AreaView.latitude, AreaView.longitude, 'M');
//                    pointsToRecord = new GPSCoordinate(AreaView.latitude, AreaView.longitude, distance);
//                } else {
//                    pointsToRecord = new GPSCoordinate(AreaView.latitude, AreaView.longitude, 0.0);
//                }
//
//                recordedBoundries.add(pointsToRecord);
//                recordsList.setAdapter(new RecordedCoordinatesAdapter(FieldCalculatorActivity.this, recordedBoundries));
//            }
//
//        });
//        resetBtn.setOnClickListener(view -> {
//            measureView.reset();
//            measureView.invalidate();
//            recordedBoundries.clear();
//            recordsList.setAdapter(new RecordedCoordinatesAdapter(FieldCalculatorActivity.this, recordedBoundries));
//
//        });
//
//        saveBtn.setOnClickListener(view -> {
//           double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
//            if (measureView.isRunning()) {
//                Toast.makeText(FieldCalculatorActivity.this, "Please stop area measuring and save", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (!measureView.isReadyToStart()) {
//                Toast.makeText(FieldCalculatorActivity.this, "Gps signal not received", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (measuredArea > 0) {
//                displayAreaAreaDialog();
//            } else {
//                Toast.makeText(FieldCalculatorActivity.this, "Area is not measured", Toast.LENGTH_SHORT).show();
//            }
//
//
//
//
//       //    saveLatLongData();
//        });
//
//        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            displayGpsDialog();
//        }
//
//        measureView = (AreaView) findViewById(R.id.measureView);
//        measureView.setLengthUnits(AreaView.LENGTH_UNITS_KILOMETER);
//        measureView.setAreaUnits(AreaView.AREA_UNITS_HECTARE);
//        startStopButton = (Button) findViewById(R.id.startBtn);
//        startStopButton.setOnClickListener(view -> {
//
//            if (measureView.isReadyToStart()) {
//                measureView.start();
//                startStopButton.setText(c.getString(R.string.stop));
//                startStopButton.postInvalidate();
//            } else if (measureView.isRunning()) {
//                measureView.stop();
//                startStopButton.setText(c.getString(R.string.start));
//                startStopButton.postInvalidate();
//            } else {
//                Toast.makeText(FieldCalculatorActivity.this, "Waiting for gps signal", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    public boolean isLocationPermissionGranted() {
//        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        return hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
//                && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED;
//    }
//
//    public void requestLocationPermissions() {
//        if (!isLocationPermissionGranted()) {
//            String[] perms = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
//            ActivityCompat.requestPermissions(this, perms, PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    initViews();
//
//                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                        displayGpsDialog();
//                    }
//
//                } else {
//
//                }
//                break;
//        }
//    }
//
//    private void displayGpsDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("GPS is turned off ,Please Enable GPS").setCancelable(false)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(settingsIntent);
//                    }
//                });
//        builder.show();
//
//    }
//
//    private void displayAreaDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
//        builder.setMessage("Total field area is : " + measuredArea + " " + measureView.getAreaUnit()).setCancelable(false);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//                saveLatLongData();
//            }
//        });
//        builder.show();
//
//    }
//
//
//    private void displayAreaAreaDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
////        double diffPercentage = CommonUtils.getPercentage(measuredArea, ConversionLandDetailsFragment.plotEnteredArea);
//        double diffPercentage = 0;
//        double roundedValue = 0.0;
//
//        try {
//            DecimalFormat f = new DecimalFormat("##.000000");
//            String formattedValue = f.format(diffPercentage);
//            if (!TextUtils.isEmpty(formattedValue)) {
//                roundedValue = Double.parseDouble(formattedValue);
//            }
//        } catch (Exception e) {
//            roundedValue = 0;
//        }
//
//
//        String message = "Total field area is : " + measuredArea + " " + measureView.getAreaUnit();
//
//        if (diffPercentage >= 60 && roundedValue != Double.POSITIVE_INFINITY && diffPercentage != Double.NEGATIVE_INFINITY) {
//            message = message + "\n Variation between Plot area and Gps area is " + roundedValue + " %";
//        }
//        builder.setMessage(message).setCancelable(false);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//                saveLatLongData();
//            }
//        });
//        builder.show();
//
//    }
//
//    public void saveLatLongData() {
//        ProgressBar.showProgressBar(FieldCalculatorActivity.this, "Saving Gps data");
//        try {
//
//            if (recordedBoundries.isEmpty()) {
//                recordedBoundries.addAll(totalBoundries);
//            }
//
//            double measuredArea = Math.round(100 * measureView.getArea()) / (double) 100;
//            Intent intent = new Intent();
//            intent.putExtra("area", measuredArea);
//            setResult(RESULT_OK, intent);
//            finish();
//
//        } catch (Exception e) {
//            Log.v(LOG_TAG, "@@@@ Error while saving lat longs");
//        }
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        totalBoundries.clear();
//        recordedBoundries.clear();
//        firstFourCoordinates.clear();
//        Intent intent = new Intent();
//        intent.putExtra("area", 0.0);
//        setResult(RESULT_OK, intent);
//        finish();
//    }
//
//    public class RecordedCoordinatesAdapter extends RecyclerView.Adapter<RecordedCoordinatesAdapter.MyHolder> {
//        private Context mContext;
//        private List<GPSCoordinate> gpsCoordinates;
//
//
//        public RecordedCoordinatesAdapter(Context mContext, List<GPSCoordinate> gpsCoordinates) {
//            this.mContext = mContext;
//            this.gpsCoordinates = gpsCoordinates;
//        }
//
//        @Override
//        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View bookingView = inflater.inflate(R.layout.records_list_item, null);
//            MyHolder myHolder = new MyHolder(bookingView);
//            return myHolder;
//        }
//
//
//        @Override
//        public void onBindViewHolder(MyHolder holder, int position) {
//            holder.latitude.setText("" + gpsCoordinates.get(position).latitude);
//            holder.longitude.setText("" + gpsCoordinates.get(position).longitude);
//            if (gpsCoordinates != null && gpsCoordinates.size() > 1) {
//                holder.distance.setText("" + gpsCoordinates.get(position).altitude + " Meters");
//
//            } else {
//                holder.distance.setText("0 " + "Meters");
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return gpsCoordinates.size();
//        }
//
//        public class MyHolder extends RecyclerView.ViewHolder {
//            TextView latitude, longitude, distance;
//            public MyHolder(View itemView) {
//                super(itemView);
//                latitude = (TextView) itemView.findViewById(R.id.latitude);
//                longitude = (TextView) itemView.findViewById(R.id.longitude);
//                distance = (TextView) itemView.findViewById(R.id.distance);
//            }
//        }
//    }