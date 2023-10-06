package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.constant.CommonConstants;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddPlantationTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.services.areacalculator.FieldCalculatorActivity;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

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

public class AddPlantation extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    TextView txtPlotId, txtPlantingDate,txtCultivatedArea,
            txtAgreedTon,txtEstimatedTon,
             txtSave;
    Spinner spCrop,spSpacing,spSettsType,spSeedMaterialUsed,spVariety,spPlantSubType,spPlantType;
    EditText edtDemoPlotArea,edtReportedArea,edtAgreementedArea,edtNetArea,edtMeasureArea;

    List<String> plantTypes = new ArrayList<>();
    List<PlantTypeTable> plantTypeTableList = new ArrayList<>();
    String plantTypeId;

    List<String> plantSubTypes = new ArrayList<>();
    List<PlantSubTypeTable> plantSubTypeTableList = new ArrayList<>();
    String plantSubTypeId;

    List<String> varieties = new ArrayList<>();
    List<VarietyTable> varietyTableList = new ArrayList<>();
    String varietyId;

    List<String> crops = new ArrayList<>();
    List<CropTable> cropTableList = new ArrayList<>();
    String cropId;

    List<String> seedMaterials = new ArrayList<>();
    List<LookupHDRTable> seedMaterialIdList = new ArrayList<>();
    String seedMaterialId;

    List<String> settsTypes = new ArrayList<>();
    List<LookupHDRTable> settsTypeIdList = new ArrayList<>();
    String settsTypeId;

    List<String> spacings = new ArrayList<>();
    List<LookupHDRTable> spacingIdList = new ArrayList<>();
    String spacingId;

    Integer countValuePlot;
    String formatted;

    AddPlotTable addPlotTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plantation);



        addPlotTable = (AddPlotTable) getIntent().getSerializableExtra("data");
        ui();
        configureDagger();
        configureViewModel();

    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getPlantTypeListDataFromLocalDB(spPlantType);
        getPlantSubTypeListDataFromLocalDB(spPlantSubType);
        getVarietyListDataFromLocalDB(spVariety);
        getCropListDataFromLocalDB(spCrop);
        getSeedMaterialUsedListDataFromLocalDB(spSeedMaterialUsed);
        getSettsTypeListDataFromLocalDB(spSettsType);
        getSpacingListDataFromLocalDB(spSpacing);
//        getLandlistFromLocalDbCheckDBCount();
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    private void ui() {
        txtPlotId = findViewById(R.id.txtPlotId);
        txtPlantingDate = findViewById(R.id.txtPlantingDate);
        spCrop = findViewById(R.id.spCrop);
        spPlantType = findViewById(R.id.spPlantType);
        spPlantSubType = findViewById(R.id.spPlantSubType);
        spVariety = findViewById(R.id.spVariety);
        txtCultivatedArea = findViewById(R.id.txtCultivatedArea);
        edtDemoPlotArea = findViewById(R.id.edtDemoPlotArea);
        edtReportedArea = findViewById(R.id.edtReportedArea);
        edtAgreementedArea = findViewById(R.id.edtAgreementedArea);
        edtNetArea = findViewById(R.id.edtNetArea);
        edtMeasureArea = findViewById(R.id.edtMeasureArea);
        txtAgreedTon = findViewById(R.id.txtAgreedTon);
        txtEstimatedTon = findViewById(R.id.txtEstimatedTon);
        spSpacing = findViewById(R.id.spSpacing);
        spSettsType = findViewById(R.id.spSettsType);
        spSeedMaterialUsed = findViewById(R.id.spSeedMaterialUsed);
        txtSave = findViewById(R.id.txtSave);

        if(addPlotTable!=null){
            txtPlotId.setText(addPlotTable.getPlotNo());
            txtAgreedTon.setText(addPlotTable.getAgreedTon());
            txtEstimatedTon.setText(addPlotTable.getEstimatedTon());
        }


        txtPlantingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                Calendar date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPlantation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);

//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        //   String strSelectDate = simpleDateFormat.format(date.getTime());
//                        strSelectDate_updated =  simpleDateFormat.format(date.getTime());
                        // txtDate.setText(strSelectDate);

                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        String strdisplayDate = displayDate.format(date.getTime());
                        txtPlantingDate.setText(strdisplayDate);
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
                datePicker.setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
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

        spPlantSubType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if(position>0){
                        plantSubTypeId= String.valueOf(plantSubTypeTableList.get(position-1).getId());
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
                try {

                    if(position>0) {
                        varietyId = String.valueOf(varietyTableList.get(position - 1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if(position>0) {
                        cropId = String.valueOf(cropTableList.get(position - 1).getId());
                    }

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

                    if(position>0) {
                        spacingId = String.valueOf(spacingIdList.get(position - 1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtCultivatedArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlantation.this, FieldCalculatorActivity.class);
                intent.putExtra("plot",txtPlotId.getText().toString());
                startActivity(intent);
            }
        });

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlantationTable addPlantationTable = new AddPlantationTable();
                addPlantationTable.setName(txtPlotId.getText().toString());
                addPlantationTable.setArea(txtCultivatedArea.getText().toString());
                addPlantationTable.setSync("0");
                viewModel.insertPlantationIntoLocalDBQuery(addPlantationTable);
                finish();
            }
        });

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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
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

    public void getPlantSubTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getPlantSubTypeListFromLocalDBStatus();
            if (viewModel.getPlantSubTypeDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<PlantSubTypeTable> seasonTableList = (List<PlantSubTypeTable>) o;
                        viewModel.getPlantSubTypeDetailsListLiveData().removeObserver(this);

                        plantSubTypes.clear();
                        plantSubTypeTableList.clear();
                        plantSubTypeTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            plantSubTypes.add("Select PlantSubType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                plantSubTypes.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":plantType", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
                                    R.layout.spinner_dropdown_layout, plantSubTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No PlantSubType list ");
                        }
                    }
                };
                viewModel.getPlantSubTypeDetailsListLiveData().observe(this, getLeadRawDataObserver);
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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
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

    public void getCropListDataFromLocalDB(Spinner spSelectSeason) {
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
                        cropTableList.clear();
                        cropTableList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            crops.add("Select crop *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                crops.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":crops", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
                                    R.layout.spinner_dropdown_layout, crops);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
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
                        spacings.clear();
                        spacingIdList.clear();
                        spacingIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            spacings.add("Select Spacing *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                spacings.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
                                    R.layout.spinner_dropdown_layout, spacings);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
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

    public void getSettsTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLogBookHdrListFromLocalDBStatus("SettsType");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        settsTypes.clear();
                        settsTypeIdList.clear();
                        settsTypeIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            settsTypes.add("Select SettsType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                settsTypes.add(seasonTableList.get(i).getName());
                                Log.e(":SettsType", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
                                    R.layout.spinner_dropdown_layout, settsTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No SettsType list ");
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

    public void getSeedMaterialUsedListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLogBookHdrListFromLocalDBStatus("SeedMaterialUsed");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        seedMaterials.clear();
                        seedMaterialIdList.clear();
                        seedMaterialIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            seedMaterials.add("Select SeedMaterialUsed *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                seedMaterials.add(seasonTableList.get(i).getName());
                                Log.e(":seedMaterials", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddPlantation.this,
                                    R.layout.spinner_dropdown_layout, seedMaterials);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No SeedMaterialUsed list ");
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

    public void getLandlistFromLocalDbCheckDBCount() {
        try {
//            INSERT_LOG("getFarmerlistFromLocalDb", "BEGIN");
            // appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCountLand().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    countValuePlot = integer;
                    //  appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    Log.d("TAG", "onChanged: CountValue" + countValuePlot);
                    formatted = String.format("%03d", Integer.parseInt(appHelper.getSharedPrefObj().getString(DeviceUserID, "0")));
                    CommonConstants.PLOT_CODE = CommonUtils.getGeneratedPlotId(formatted, countValuePlot);
//                    plotNumber = CommonConstants.PLOT_CODE ;
                    txtPlotId.setText(CommonConstants.PLOT_CODE);
                    removePlotGeoBoundaries(CommonConstants.PLOT_CODE);
//                    getCropListFromLocalDb();
                    //  word_count.setText(String.valueOf(integer));
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }


    private void removePlotGeoBoundaries(String plotCode) {
        viewModel.getDeleteGeoBoundariestablesFromlocalDB(plotCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPlotGeoBoundariesDetails();
    }

    private void getPlotGeoBoundariesDetails() {
        try {
            viewModel.getPlotGeoBoundariesDetailsFromLocalDB(txtPlotId.getText().toString());
            if (viewModel.getPlotGeoBoundsListNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddGeoBoundriesTable> odVisitSurveyTableList = (List<AddGeoBoundriesTable>) o;
                        viewModel.getPlotGeoBoundsListNotSyncLiveData().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {
                         String gpsPlotArea = odVisitSurveyTableList.get(0).getArea();
                            txtCultivatedArea.setText(gpsPlotArea);
                        } else {

                        }
                    }

                };
                viewModel.getPlotGeoBoundsListNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}