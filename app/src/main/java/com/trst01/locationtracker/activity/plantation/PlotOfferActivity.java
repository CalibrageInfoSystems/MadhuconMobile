package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MMM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.ResonForNotPlantingTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class PlotOfferActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    TextView txtSave;

    // TODO: for select season
    List<String> seasonList = new ArrayList<>();
    List<String> seasonListIDs = new ArrayList<>();
    List<String> seasonListCode = new ArrayList<>();

    List<String> farmerCodes = new ArrayList<>();
    List<String> villageNames = new ArrayList<>();
    List<String> reasons = new ArrayList<>();
    List<String> varieties = new ArrayList<>();
    List<String> plantTypes = new ArrayList<>();

    List<AddFarmerTable> farmerTableList = new ArrayList<>();
    List<VillageTable> villageTableList = new ArrayList<>();
    List<ResonForNotPlantingTable> reasonForNotPlantingTableList = new ArrayList<>();
    List<PlantTypeTable> plantTypeTableList = new ArrayList<>();
    List<VarietyTable> varietyTableList = new ArrayList<>();

    Spinner spSeason,spVillage,spPlantType,spFarmer,spVariety,spReason;
    String strSelectSeason,farmerId,plotVillageId,reasonId,plantTypeId,varietyId,farmerVillageId;
    Integer seasonId;

    EditText edtOfferDate,edtExpectedPlantingDate,
            edtRyotName,edtRyotFatherName,edtRyotVillage,edtRyotDivision,edtRyotCircle,edtRyotSection,
            edtPlotDivision,edtPlotCircle,edtPlotSection,edtExpectedArea;
    RadioButton radioNewFarmerYes,radioNewFarmerNo,radioActiveYes,radioActiveNo;
    AutoCompleteTextView edtRyotNo;
    AutoCompleteTextView edtPlotVillageNo;
    String strSelectDate="";
    String strSelectDatePlantingDate="";
    TextView txtComplain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_offer);
//        setContentView(R.layout.activity_add_plantation);

        ui();

        configureDagger();
        configureViewModel();

    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getSeasonListDataFromLocalDB(spSeason);
        getFarmerListDataFromLocalDB(spFarmer);
        getVillageListDataFromLocalDB(spVillage);
        getResonListDataFromLocalDB(spReason);
        getVarietyListDataFromLocalDB(spVariety);
        getPlantTypeListDataFromLocalDB(spPlantType);


        edtRyotNo.setText(getIntent().getStringExtra("farmerCode"));
        getFarmerIDDetailsByFarmerCode(getIntent().getStringExtra("farmerCode"));
    }

    public void getSeasonListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getSeasonListForLogBookFromLocalDB();
            if (viewModel.getSeasonlistFromlocalDBLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<SeasonTable> seasonTableList = (List<SeasonTable>) o;
                        viewModel.getSeasonlistFromlocalDBLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        seasonList.clear();
                        seasonListIDs.clear();
                        seasonListCode.clear();
                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            seasonList.add("Select Season *");
                            seasonListIDs.add("");
                            seasonListCode.add("");
                            for (int i = 0; i < seasonTableList.size(); i++) {
                                seasonList.add(seasonTableList.get(i).getName());
                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                seasonListCode.add(seasonTableList.get(i).getCode());
                                //strStateID = stateListResponseDTOList.get(i).getStateId();
                                Log.e(":dsvbjl_id_season", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this,
                                    R.layout.spinner_dropdown_layout, seasonList);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No season  list ");
                        }
                    }
                };
                viewModel.getSeasonlistFromlocalDBLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getFarmerListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getFarmerListFromLocalDBStatus();
            if (viewModel.getFarmerDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddFarmerTable> seasonTableList = (List<AddFarmerTable>) o;
                        viewModel.getFarmerDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        farmerCodes.clear();
                        farmerTableList.clear();
                        farmerTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            farmerCodes.add("Select Farmer *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                farmerCodes.add(seasonTableList.get(i).getCode());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":farmer", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(PlotOfferActivity.this,android.R.layout.simple_dropdown_item_1line,farmerCodes);
                            edtRyotNo.setThreshold(3);
                            edtRyotNo.setAdapter(adapter);

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this, android.R.layout.simple_dropdown_item_1line, farmerCodes);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No farmer  list ");
                        }
                    }
                };
                viewModel.getFarmerDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getVillageListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            viewModel.getVillageListFromLocalDBStatus();
            viewModel.getVillageListBasedOnSectionFromLocalDBStatus();
            if (viewModel.getVillageDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<VillageTable> seasonTableList = (List<VillageTable>) o;
                        viewModel.getVillageDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        villageNames.clear();
                        villageTableList.clear();
                        villageTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            villageNames.add("Select village *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                villageNames.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":village", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this,
                                    R.layout.spinner_dropdown_layout, villageNames);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(PlotOfferActivity.this,android.R.layout.simple_dropdown_item_1line,villageNames);
                            edtPlotVillageNo.setThreshold(3);
                            edtPlotVillageNo.setAdapter(adapter);
//                            ArrayAdapter<String> dataAdapterVillage = new ArrayAdapter<String>(PlotOfferActivity.this, android.R.layout.simple_dropdown_item_1line, villageNames);
//                            dataAdapterVillage.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No village list ");
                        }
                    }
                };
                viewModel.getVillageDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getResonListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getResonListFromLocalDBStatus();
            if (viewModel.getReasonDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<ResonForNotPlantingTable> seasonTableList = (List<ResonForNotPlantingTable>) o;
                        viewModel.getReasonDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        reasons.clear();
                        reasonForNotPlantingTableList.clear();
                        reasonForNotPlantingTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            reasons.add("Select reasons *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                reasons.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":reasons", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this,
                                    R.layout.spinner_dropdown_layout, reasons);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No reasons list ");
                        }
                    }
                };
                viewModel.getReasonDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getVarietyListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getVarietyListFromLocalDBStatus();
            if (viewModel.getVarietyDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<VarietyTable> seasonTableList = (List<VarietyTable>) o;
                        viewModel.getVarietyDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        varieties.clear();
                        varietyTableList.clear();
                        varietyTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            varieties.add("Select variety *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                varieties.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":varieties", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this,
                                    R.layout.spinner_dropdown_layout, varieties);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No varieties list ");
                        }
                    }
                };
                viewModel.getVarietyDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getPlantTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getPlantTypeListFromLocalDBStatus();
            if (viewModel.getPlantTypeDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<PlantTypeTable> seasonTableList = (List<PlantTypeTable>) o;
                        viewModel.getPlantTypeDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        plantTypes.clear();
                        plantTypeTableList.clear();
                        plantTypeTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            plantTypes.add("Select Plant Type *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                plantTypes.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":plantType", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this,
                                    R.layout.spinner_dropdown_layout, plantTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Plant Type list ");
                        }
                    }
                };
                viewModel.getPlantTypeDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    private void ui() {
        txtComplain = findViewById(R.id.txtComplain);

        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlotOfferActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","PlotOffer");

                intent.putExtra("strPlotNumber",getIntent().getStringExtra("farmerCode"));
                intent.putExtra("strFarmerCode",getIntent().getStringExtra("farmerCode"));
                intent.putExtra("seasonCode",getIntent().getStringExtra("farmerCode"));
                startActivity(intent);
            }
        });

        edtPlotVillageNo = findViewById(R.id.edtPlotVillageNo);
        edtRyotNo = findViewById(R.id.edtRyotNo);
        spSeason = findViewById(R.id.spSeason);
        edtOfferDate = findViewById(R.id.edtOfferDate);
        radioNewFarmerNo = findViewById(R.id.radioNewFarmerNo);
        radioNewFarmerYes = findViewById(R.id.radioNewFarmerYes);
        spVillage = findViewById(R.id.spVillage);
        spPlantType = findViewById(R.id.spPlantType);
        spFarmer = findViewById(R.id.spFarmer);
        spVariety = findViewById(R.id.spVariety);
        spReason = findViewById(R.id.spReason);
        edtExpectedPlantingDate = findViewById(R.id.edtExpectedPlantingDate);
        edtRyotName = findViewById(R.id.edtRyotName);
        edtRyotFatherName = findViewById(R.id.edtRyotFatherName);

        edtRyotVillage = findViewById(R.id.edtRyotVillage);
        edtRyotDivision = findViewById(R.id.edtRyotDivision);
        edtRyotCircle = findViewById(R.id.edtRyotCircle);
        edtRyotSection = findViewById(R.id.edtRyotSection);

        edtPlotDivision = findViewById(R.id.edtPlotDivision);
        edtPlotCircle = findViewById(R.id.edtPlotCircle);
        edtPlotSection = findViewById(R.id.edtPlotSection);

        edtExpectedArea = findViewById(R.id.edtExpectedArea);
        radioActiveYes = findViewById(R.id.radioActiveYes);
        radioActiveNo = findViewById(R.id.radioActiveNo);
        txtSave = findViewById(R.id.txtSave);

//        spSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
//                try {
//                    strSelectSeason = seasonList.get(position);
//                    String strSeason = seasonListIDs.get(position);
//                    seasonId = Integer.valueOf(strSeason);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        edtOfferDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
                Calendar date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(PlotOfferActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);

//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                           strSelectDate = simpleDateFormat.format(date.getTime());
//                        strSelectDate_updated =  simpleDateFormat.format(date.getTime());
                        // txtDate.setText(strSelectDate);

                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
//                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        String strdisplayDate = displayDate.format(date.getTime());
                        edtOfferDate.setText(strdisplayDate);
                        edtExpectedPlantingDate.setText("");
                        strSelectDatePlantingDate="";

//                        if (!txtDate.getText().toString().isEmpty())
//                        {
//                            llQtyFertlizer.setVisibility(View.VISIBLE);
//                        }else {
//                            llQtyFertlizer.setVisibility(View.GONE);
//                        }
//                        new TimePickerDialog(SinglePlotLogBookActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                                date.set(Calendar.MINUTE, minute);
//                                Log.v(TAG, "The choosen one " + date.getTime());
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
//                                String strSelectDate = simpleDateFormat.format(date.getTime());
//                                txtDate.setText(strSelectDate);
//                            }
//                        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                DatePicker datePicker = datePickerDialog.getDatePicker();
//                Calendar c = Calendar.getInstance();
//                c.add(Calendar.DATE, -1);
//                datePicker.setMinDate(c.getTimeInMillis());
                //   datePicker.setMinDate(System.currentTimeMillis() - 1000);
//                datePicker.setMaxDate(System.currentTimeMillis());  //asked to open
                datePickerDialog.show();
            }
        });

        edtExpectedPlantingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
               if(strSelectDate.length()==0){
                   Toast.makeText(PlotOfferActivity.this, "please set offer date first", Toast.LENGTH_SHORT).show();
               } else {

                   Calendar date = Calendar.getInstance();
                   DatePickerDialog datePickerDialog = new DatePickerDialog(PlotOfferActivity.this, new DatePickerDialog.OnDateSetListener() {
                       @Override
                       public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                           date.set(year, monthOfYear, dayOfMonth);

//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
                           SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                           strSelectDatePlantingDate = simpleDateFormat.format(date.getTime());
//                        strSelectDate_updated =  simpleDateFormat.format(date.getTime());
                           // txtDate.setText(strSelectDate);

                           SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
//                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
//                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY2, Locale.US);
                           String strdisplayDate = displayDate.format(date.getTime());
                           edtExpectedPlantingDate.setText(strdisplayDate);

//                        if (!txtDate.getText().toString().isEmpty())
//                        {
//                            llQtyFertlizer.setVisibility(View.VISIBLE);
//                        }else {
//                            llQtyFertlizer.setVisibility(View.GONE);
//                        }
//                        new TimePickerDialog(SinglePlotLogBookActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                                date.set(Calendar.MINUTE, minute);
//                                Log.v(TAG, "The choosen one " + date.getTime());
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
//                                String strSelectDate = simpleDateFormat.format(date.getTime());
//                                txtDate.setText(strSelectDate);
//                            }
//                        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
                       }
                   }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                   DatePicker datePicker = datePickerDialog.getDatePicker();
//                Calendar c = Calendar.getInstance();
//                c.add(Calendar.DATE, -1);
//                datePicker.setMinDate(c.getTimeInMillis());
                   //   datePicker.setMinDate(System.currentTimeMillis() - 1000);

                   final Calendar calendar3 = Calendar.getInstance();

//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                   //create instance of the Calendar class and set the date to the given date
//                Calendar cal = Calendar.getInstance();
//                try{
//                    cal.setTime(sdf.parse(strSelectDate));
//                }catch(ParseException e){
//                    e.printStackTrace();
//                }

//                String test = getAddedDays(strSowingDate, 120);
//                String test = cal;
                   String str = strSelectDate;
                   String[] splited = str.split("-");
                   calendar3.set(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]) - 1, Integer.parseInt(splited[2]));
                   datePicker.setMinDate(calendar3.getTimeInMillis());

//                datePicker.setMinDate(System.currentTimeMillis());
                   datePickerDialog.show();
               }
            }
        });

        spSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
                try {
                    strSelectSeason = seasonListCode.get(position);
                    String strSeason = seasonListIDs.get(position);
                    seasonId = Integer.valueOf(strSeason);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spFarmer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
                try {
//                    String strSeason = farmerCodes.get(position);
                    if(position>0){
                        farmerId= farmerTableList.get(position-1).getCode();
                        edtRyotName.setText(farmerTableList.get(position-1).getName());
                        edtRyotFatherName.setText(farmerTableList.get(position-1).getFatherName());
                        getVillageDetailsByVillageId(farmerTableList.get(position-1).getVillageId());
                        farmerVillageId = farmerTableList.get(position-1).getVillageId();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
//                try {
//                    if(position>0){
//                        plotVillageId= String.valueOf(villageTableList.get(position-1).getId());
//                        edtPlotDivision.setText(villageTableList.get(position-1).getDivisonName());
//                        edtPlotCircle.setText(villageTableList.get(position-1).getCircleName());
//                        edtPlotSection.setText(villageTableList.get(position-1).getSectionName());
////                        getPlotVillageDetailsByVillageId(villageTableList.get(position-1).getId());
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        spReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
                try {
                    String strSeason = reasons.get(position);
                    if(position>0){
                        reasonId= String.valueOf(reasonForNotPlantingTableList.get(position-1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPlantType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
                try {
                    String strSeason = plantTypes.get(position);
                    if(position>0){
                        plantTypeId= String.valueOf(plantTypeTableList.get(position-1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spVariety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
                try {
                    String strSeason = varieties.get(position);
                    if(position>0){
                        varietyId= String.valueOf(varietyTableList.get(position-1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        txtPlotId = findViewById(R.id.txtPlotId);
//        txtPlantingDate = findViewById(R.id.txtPlantingDate);
//        txtCrop = findViewById(R.id.txtCrop);
//        txtPlantType = findViewById(R.id.txtPlantType);
//        txtPlantSubtype = findViewById(R.id.txtPlantSubtype);
//        txtvariety = findViewById(R.id.txtvariety);
//        txtCultivatedArea = findViewById(R.id.txtCultivatedArea);
//        txtDemoPlotArea = findViewById(R.id.txtDemoPlotArea);
//        txtReportedArea = findViewById(R.id.txtReportedArea);
//        txtAgrementedArea = findViewById(R.id.txtAgrementedArea);
//        txtNetArea = findViewById(R.id.txtNetArea);
//        txtMeasureArea = findViewById(R.id.txtMeasureArea);
//        txtAgreedArea = findViewById(R.id.txtAgreedArea);
//        txtEstimatedArea = findViewById(R.id.txtEstimatedArea);
//        txtSpacing = findViewById(R.id.txtSpacing);
//        txtSetsType = findViewById(R.id.txtSetsType);
//        SeedMaterialUsed = findViewById(R.id.SeedMaterialUsed);
//        txtSave = findViewById(R.id.txtSave);

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlotOfferTable divisionTable = new AddPlotOfferTable();
//                divisionTable.setSeasonCode(spSeason.getSelectedItem().toString());
                divisionTable.setSeasonCode(strSelectSeason);
//                divisionTable.setSeasonCode(String.valueOf(seasonId));
                if(radioNewFarmerYes.isChecked()){
                    divisionTable.setNewFarmer("0");
                } else {
                    divisionTable.setNewFarmer("1");
                }

//                divisionTable.setOfferNo(transactionSyncResponseDTO.getPlotOffer().get(i).getOfferNo());
                divisionTable.setFarmerCode(edtRyotNo.getText().toString());
//                divisionTable.setFarmerCode(farmerId);
                divisionTable.setFarmerName(edtRyotName.getText().toString());
                divisionTable.setFatherName(edtRyotFatherName.getText().toString());
                divisionTable.setFarmerVillageId(farmerVillageId);
//                getPlotVillageIDDetailsByVillage(edtPlotVillageNo.getText().toString());
                divisionTable.setPlotVillageId(plotVillageId);
//                divisionTable.setPlotVillageId(plotVillageId);
                divisionTable.setOfferDate(strSelectDate);
//                divisionTable.setOfferDate(edtOfferDate.getText().toString());
//                divisionTable.setPlotIndNo(transactionSyncResponseDTO.getPlotOffer().get(i).getPlotIndNo());
                divisionTable.setPlotIndNo("1");
                divisionTable.setPlantTypeId(plantTypeId);
                divisionTable.setExpectedVarityId(varietyId);
                divisionTable.setExpectedPlantingDate(strSelectDatePlantingDate);
//                divisionTable.setExpectedPlantingDate(edtExpectedPlantingDate.getText().toString());
                divisionTable.setExpectedArea(edtExpectedArea.getText().toString().trim());
                divisionTable.setReportedArea("1");
                divisionTable.setServerStatus("0");
//                divisionTable.setReportedArea(transactionSyncResponseDTO.getPlotOffer().get(i).getReportedArea());
                divisionTable.setReasonForNotPlantingId(reasonId);
                divisionTable.setActive(radioActiveYes.isChecked());
                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                Log.d("TAG", "onClick: date" + dateTime);
                divisionTable.setCreatedDate(dateTime);
                divisionTable.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
                divisionTable.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID,""));
                divisionTable.setStatus("0");
                divisionTable.setUpdatedDate(dateTime);
                //insertClusterValuesIntoLocalDB(clusterHDr_value);
                viewModel.insertPlotOfferIntoLocalDBQuery(divisionTable);

                finish();

            }
        });

        edtRyotNo.addTextChangedListener(new GenericTextWatchers(edtRyotNo));
        edtPlotVillageNo.addTextChangedListener(new GenericTextWatchers(edtPlotVillageNo));
//
//        edtRyotNo.setText(getIntent().getStringExtra("farmerCode"));
//        getFarmerIDDetailsByFarmerCode(getIntent().getStringExtra("farmerCode"));
//        getFarmerIDDetailsByFarmerCode(edtRyotNo.getText().toString().trim());

    }

    public class GenericTextWatchers implements TextWatcher {
        private View view;

        private GenericTextWatchers(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            DecimalFormat decimalFormatter = new DecimalFormat("########.##");
            switch (view.getId()) {
                case R.id.edtRyotNo:
                    if(edtRyotNo.getText().toString().trim().length()>0){

                        if(edtRyotNo.getText().toString().trim().length()>0){
                            getFarmerIDDetailsByFarmerCode(edtRyotNo.getText().toString().trim());
                        }

//                        etAmount.setText(decimalFormatter.format(Double.valueOf(etFuelLtr.getText().toString().trim())*
//                                Double.valueOf(etFueRate.getText().toString().trim())));

                    } else {

//                        etAmount.setText("0");
                    }

                    break;
                case R.id.edtPlotVillageNo:


                    if(edtPlotVillageNo.getText().toString().trim().length()>0){

                        if(edtPlotVillageNo.getText().toString().trim().length()>3){
                            getPlotVillageIDDetailsByVillage(edtPlotVillageNo.getText().toString());
                        }


//                        etAmount.setText(decimalFormatter.format(Double.valueOf(etFueRate.getText().toString().trim())*
//                                Double.valueOf(etFuelLtr.getText().toString().trim())));

                    } else {
//                        etAmount.setText("0");
                    }

                    break;

            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    public void getVillageDetailsByVillageId(String strPlotVillageId) {
        try {
            viewModel.getVillageDetailsByVillageId(strPlotVillageId);
            if (viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VillageTable villageTable = (VillageTable) o;
                        viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            String strPlotMandalId = String.valueOf(villageTable.getMandalId());
                            String strPlotVillageName = villageTable.getName();
//                            txtPinCode.setText(villageTable.getPinCode());
                            edtRyotVillage.setText(strPlotVillageName);
                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

                            edtRyotDivision.setText(villageTable.getDivisonName());
                            edtRyotCircle.setText(villageTable.getCircleName());
                            edtRyotSection.setText(villageTable.getSectionName());

                        }

                    }


                };
                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

//    public void getPlotVillageDetailsByVillageId(String strPlotVillageId) {
//        try {
//            viewModel.getVillageDetailsByVillageId(strPlotVillageId);
//            if (viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        VillageTable villageTable = (VillageTable) o;
//                        viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
////                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
//                        Log.d("TAG", "onChanged: values" + villageTable);
//                        if (villageTable != null) {
//                            String strPlotMandalId = String.valueOf(villageTable.getMandalId());
//                            String strPlotVillageName = villageTable.getName();
////                            txtPinCode.setText(villageTable.getPinCode());
//                            edtRyotVillage.setText(strPlotVillageName);
//                            Log.d("TAG", strPlotVillageName + strPlotMandalId);
//
//                            edtPlotDivision.setText(villageTable.getDivisonName());
//                            edtPlotCircle.setText(villageTable.getCircleName());
//                            edtPlotSection.setText(villageTable.getSectionName());
//
//                        }
//
//                    }
//
//
//                };
//                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//
////            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
//        }
//    }

    public void getPlotVillageIDDetailsByVillage(String strPlotVillageId) {
        try {
            viewModel.getVillageIdDetailsByVillage(strPlotVillageId);
            if (viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VillageTable villageTable = (VillageTable) o;
                        viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            String strPlotMandalId = String.valueOf(villageTable.getMandalId());
                            String strPlotVillageName = villageTable.getName();
//                            txtPinCode.setText(villageTable.getPinCode());
//                            edtRyotVillage.setText(strPlotVillageName);
                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

                            edtPlotDivision.setText(villageTable.getDivisonName());
                            edtPlotCircle.setText(villageTable.getCircleName());
                            edtPlotSection.setText(villageTable.getSectionName());
                            plotVillageId = String.valueOf(villageTable.getId());

//                            edtPlotDivision.setText(villageTableList.get(position-1).getDivisonName());
//                            edtPlotCircle.setText(villageTableList.get(position-1).getCircleName());
//                            edtPlotSection.setText(villageTableList.get(position-1).getSectionName());
                        }
//                        else {
//                            edtPlotDivision.setText("");
//                            edtPlotCircle.setText("");
//                            edtPlotSection.setText("");
//                            plotVillageId = "";
//                        }

                    }


                };
                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getFarmerIDDetailsByFarmerCode(String farmerCode) {
        try {
            viewModel.getFarmerIDDetailsByFarmerCode(farmerCode);
            if (viewModel.getFarmerDetailsFromLocalDBByFarmerIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AddFarmerTable farmerTable = (AddFarmerTable) o;
                        viewModel.getFarmerDetailsFromLocalDBByFarmerIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + farmerTable);
                        if (farmerTable != null) {
//                            String strPlotMandalId = String.valueOf(villageTable.getMandalId());
//                            String strPlotVillageName = villageTable.getName();
////                            txtPinCode.setText(villageTable.getPinCode());
//                            edtRyotVillage.setText(strPlotVillageName);
//                            Log.d("TAG", strPlotVillageName + strPlotMandalId);
//
//                            edtPlotDivision.setText(villageTable.getDivisonName());
//                            edtPlotCircle.setText(villageTable.getCircleName());
//                            edtPlotSection.setText(villageTable.getSectionName());
//                            plotVillageId = String.valueOf(villageTable.getId());

                            edtRyotName.setText(farmerTable.getName());
                            edtRyotFatherName.setText(farmerTable.getFatherName());
                            getVillageDetailsByVillageId(farmerTable.getVillageId());
                            farmerVillageId = farmerTable.getVillageId();

//                            edtPlotDivision.setText(villageTableList.get(position-1).getDivisonName());
//                            edtPlotCircle.setText(villageTableList.get(position-1).getCircleName());
//                            edtPlotSection.setText(villageTableList.get(position-1).getSectionName());
                        }

                    }


                };
                viewModel.getFarmerDetailsFromLocalDBByFarmerIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

}