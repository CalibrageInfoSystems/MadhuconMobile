package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MMM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddD30Table;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundariesTrackingTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.PlotExistOnTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AgreementedAreaActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    AddD20Table addPlotTable;
    AppCompatSpinner spWeedStatus,spRelationshipType,spInterCrop;

    //relationTypes
    List<String> relationTypes = new ArrayList<>();
    List<LookupDtlTable> relationTypesList = new ArrayList<>();
//    List<LookupHDRTable> relationTypesList = new ArrayList<>();
    String relationTypeId;

    //previous crop id
    List<String> crops = new ArrayList<>();
    List<CropTable> cropTableList = new ArrayList<>();
    String cropId;

    //weedStatus
    List<String> weedStatus = new ArrayList<>();
    List<LookupDtlTable> weedStatusList = new ArrayList<>();
    String weedStatusId;

    TextView txtSave;

    EditText edtSeasonCode,edtNominee,edtPlotNo,etCropCode,etCropType,edtFarmerCode,edtRyotName,edtRyotFatherName,edtRyotDivision,edtRyotCircle,edtRyotSection,
            edtRyotVillage,edtPlotDivision,edtPlotCircle,edtPlotSection,edtPlotAreaVillage,edtPlantType,edtSurveyNo,edtAgreedTon,edtReportedArea,
            edtMeasuredArea,edtExpectedPlantingDate,edtVariety,edtFieldName,edtBirNo,edtBirDate,edtPlotType,edtInspectionDate,edtAgreementedArea,edtBioFertilizersAppliedArea,edtDeTrashingArea,edtDeepPloughedArea,edtEarthingUpArea,edtRatoonManagedUsedArea,
            edtTrashShedderArea,edtDeepFurrow,edtDrainageChannel,edtDeepPlough,edtLoadShedderArea;
    Spinner spSeason;
    RadioButton microNutrientDeficiencyYes,microNutrientDeficiencyNo,trashMulchingYes,trashMulchingNo,gapsFilledYes,gapsFilledNo;
    EditText edtGuarantor1,
            edtGuarantor2,edtGuarantor3;
    List<String> farmerCodes = new ArrayList<>();
    List<AddFarmerTable> farmerTableList = new ArrayList<>();
    int id =0;
    String strFinalPlantingDate="";
    String strFinalBirDate="";
    String stringInspectionDate="";
    Double estimatedTon=0.0;
    TextView txtComplain;
    private static final int REQUEST = 1888;

    int indicator=0;
    String g1="";
    String g2="";
    String g3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreemented_area);

        addPlotTable = (AddD20Table) getIntent().getSerializableExtra("data");
//        Toast.makeText(appHelper, addPlotTable.toString(), Toast.LENGTH_SHORT).show();
        txtComplain = findViewById(R.id.txtComplain);

        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgreementedAreaActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","AgreementedArea");
                intent.putExtra("strPlotNumber",addPlotTable.getPlotNo());
                intent.putExtra("strFarmerCode",addPlotTable.getFarmerCode());
                intent.putExtra("seasonCode",addPlotTable.getSeasonCode());
                startActivity(intent);
            }
        });

        txtSave = findViewById(R.id.txtSave);
        spInterCrop = findViewById(R.id.spInterCrop);
        spRelationshipType = findViewById(R.id.spRelationshipType);
        spWeedStatus = findViewById(R.id.spWeedStatus);

        edtLoadShedderArea = findViewById(R.id.edtLoadShedderArea);
        edtSeasonCode = findViewById(R.id.edtSeasonCode);
        edtNominee = findViewById(R.id.edtNominee);
        edtPlotNo = findViewById(R.id.edtPlotNo);
        etCropCode = findViewById(R.id.etCropCode);
        etCropType = findViewById(R.id.etCropType);
        edtFarmerCode = findViewById(R.id.edtFarmerCode);
        edtRyotName = findViewById(R.id.edtRyotName);
        edtRyotFatherName = findViewById(R.id.edtRyotFatherName);
        edtRyotDivision = findViewById(R.id.edtRyotDivision);
        edtRyotCircle = findViewById(R.id.edtRyotCircle);
        edtRyotSection = findViewById(R.id.edtRyotSection);
        edtRyotVillage = findViewById(R.id.edtRyotVillage);
        edtPlotDivision = findViewById(R.id.edtPlotDivision);
        edtPlotCircle = findViewById(R.id.edtPlotCircle);
        edtPlotSection = findViewById(R.id.edtPlotSection);
        edtPlotAreaVillage = findViewById(R.id.edtPlotAreaVillage);
        edtPlantType = findViewById(R.id.edtPlantType);
        edtSurveyNo = findViewById(R.id.edtSurveyNo);
        edtAgreedTon = findViewById(R.id.edtAgreedTon);
        edtReportedArea = findViewById(R.id.edtReportedArea);
        edtMeasuredArea = findViewById(R.id.edtMeasuredArea);
        edtExpectedPlantingDate = findViewById(R.id.edtExpectedPlantingDate);
        edtVariety = findViewById(R.id.edtVariety);
        edtFieldName = findViewById(R.id.edtFieldName);
        edtBirNo = findViewById(R.id.edtBirNo);
        edtBirDate = findViewById(R.id.edtBirDate);
        edtPlotType = findViewById(R.id.edtPlotType);
        edtInspectionDate = findViewById(R.id.edtInspectionDate);
        edtAgreementedArea = findViewById(R.id.edtAgreementedArea);
        edtGuarantor1 = findViewById(R.id.edtGuarantor1);
        edtGuarantor2 = findViewById(R.id.edtGuarantor2);
        edtGuarantor3 = findViewById(R.id.edtGuarantor3);
        edtBioFertilizersAppliedArea = findViewById(R.id.edtBioFertilizersAppliedArea);
        edtDeTrashingArea = findViewById(R.id.edtDeTrashingArea);
        edtDeepPloughedArea = findViewById(R.id.edtDeepPloughedArea);
        edtEarthingUpArea = findViewById(R.id.edtEarthingUpArea);
        edtRatoonManagedUsedArea = findViewById(R.id.edtRatoonManagedUsedArea);
        edtTrashShedderArea = findViewById(R.id.edtTrashShedderArea);
        edtDeepFurrow = findViewById(R.id.edtDeepFurrow);
        edtDrainageChannel = findViewById(R.id.edtDrainageChannel);
        edtDeepPlough = findViewById(R.id.edtDeepPlough);

        spSeason = findViewById(R.id.spSeason);

        microNutrientDeficiencyYes = findViewById(R.id.microNutrientDeficiencyYes);
        microNutrientDeficiencyNo = findViewById(R.id.microNutrientDeficiencyNo);
        trashMulchingYes = findViewById(R.id.trashMulchingYes);
        trashMulchingNo = findViewById(R.id.trashMulchingNo);
        gapsFilledYes = findViewById(R.id.gapsFilledYes);
        gapsFilledNo = findViewById(R.id.gapsFilledNo);

//        EditText edtNominee,edtPlotNo,etCropCode,etCropType,edtFarmerCode,edtRyotName,edtRyotFatherName,edtRyotDivision,edtRyotCircle,edtRyotSection,
//                edtRyotVillage,edtPlotDivision,edtPlotCircle,edtPlotSection,edtPlotAreaVillage,edtPlantType,edtSurveyNo,edtAgreedTon,edtReportedArea,
//                edtMeasuredArea,edtExpectedPlantingDate,edtVariety,edtFieldName,edtBirNo,edtBirDate,edtPlotType,edtInspectionDate,edtAgreementedArea,edtGuarantor1,
//                edtGuarantor2,edtGuarantor3,edtBioFertilizersAppliedArea,edtDeTrashingArea,edtDeepPloughedArea,edtEarthingUpArea,edtRatoonManagedUsedArea,
//                edtTrashShedderArea,edtDeepFurrow,edtDrainageChannel,edtDeepPlough;
//        Spinner spSeason;
//        RadioButton microNutrientDeficiencyYes,microNutrientDeficiencyNo,trashMulchingYes,trashMulchingNo,gapsFilledYes,gapsFilledNo;

        spInterCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spRelationshipType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if(position>0) {
                        relationTypeId = String.valueOf(relationTypesList.get(position - 1).getId());
                    }

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
                    if(position>0){
                        weedStatusId= String.valueOf(weedStatusList.get(position-1).getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edtInspectionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
                Calendar date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AgreementedAreaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
                        String strdisplayDate = displayDate.format(date.getTime());
                         stringInspectionDate = simpleDateFormat.format(date.getTime());
                        edtInspectionDate.setText(strdisplayDate);

//                        edtExpectedPlantingDate.setText("");
//                        String strFinalDate="";

                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                DatePicker datePicker = datePickerDialog.getDatePicker();
//                datePicker.setMaxDate(System.currentTimeMillis()); // removed
                datePickerDialog.show();
            }
        });


        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtInspectionDate.getText().toString().trim().isEmpty()){
                    Toast.makeText(appHelper, "please enter Inspection date", Toast.LENGTH_SHORT).show();
                } else if(edtGuarantor1.getText().toString().trim().isEmpty()&&edtGuarantor2.getText().toString().trim().isEmpty()&&edtGuarantor3.getText().toString().trim().isEmpty()){
                    Toast.makeText(appHelper, "please enter guarantors", Toast.LENGTH_SHORT).show();
                } else {
                    AddD30Table addD10Table = new AddD30Table();
                    addD10Table.setSeasonCode(edtSeasonCode.getText().toString());
//                    addD10Table.setId();
                    addD10Table.setId(id);
//                addD10Table.setId(addPlotTable.getId());
                    addD10Table.setOfferedNo(addPlotTable.getOfferedNo());//offeredNo
                    addD10Table.setFatherName(addPlotTable.getFatherName());
                    addD10Table.setFarmerCode(addPlotTable.getFarmerCode());
                    addD10Table.setDemoPlotArea(addPlotTable.getDemoPlotArea());
                    addD10Table.setEstimatedTon(addPlotTable.getEstimatedTon());
                    addD10Table.setPlotVillageId(addPlotTable.getPlotVillageId());
                    addD10Table.setCropTypeId(addPlotTable.getCropTypeId());

                    addD10Table.setFarmerVillageId(addPlotTable.getFarmerVillageId());
                    addD10Table.setPlantTypeId(addPlotTable.getPlantTypeId());
                    addD10Table.setPlantSubTypeId(addPlotTable.getPlantSubTypeId());
                    addD10Table.setPlotNo(addPlotTable.getPlotNo());
                    addD10Table.setSurveyNo(addPlotTable.getSurveyNo());
//                addD10Table.setSurveyNo(addPlotTable.getSurveyNo());
                    addD10Table.setReportedArea(addPlotTable.getReportedArea());
                    addD10Table.setPlantingDate(addPlotTable.getPlantingDate());
                    addD10Table.setVarietyId(addPlotTable.getVarietyId());
//                addD10Table.setAgreedTon(addPlotTable.getAgreedTon());
                    addD10Table.setAgreedTon(edtAgreedTon.getText().toString());
                    addD10Table.setFieldName(addPlotTable.getFieldName());
                    addD10Table.setBIRDate(addPlotTable.getBIRDate());
                    addD10Table.setBIRNo(addPlotTable.getBIRNo());
                    addD10Table.setMeasureArea(addPlotTable.getMeasureArea());
                    addD10Table.setPlotTypeId(addPlotTable.getPlotTypeId());
//                addD10Table.setPlotTypeId(addPlotTable.getPlotTypeId());
                    addD10Table.setInspectionDate(stringInspectionDate);
//                    addD10Table.setInspectionDate(edtInspectionDate.getText().toString());
                    addD10Table.setSettsTypeId("");
                    addD10Table.setAggrementedArea(edtAgreementedArea.getText().toString().trim());
//                addD10Table.setReas("");
                    addD10Table.setRemovedArea("");
                    addD10Table.setIsMicronutrientDeficiency("0");
                    addD10Table.setInterCropId(cropId);
                    addD10Table.setIsTrashMulching("0");
                    addD10Table.setIsGapsFilled("0");
                    addD10Table.setBioFertilizerAppliedArea(edtBioFertilizersAppliedArea.getText().toString());
                    addD10Table.setWeedStatusId(weedStatusId);
                    addD10Table.setDeepPloughedArea(edtDeepPloughedArea.getText().toString());
                    addD10Table.setDeTrashingArea(edtDeTrashingArea.getText().toString());
                    addD10Table.setEarthingUpArea(edtEarthingUpArea.getText().toString());
                    addD10Table.setRatoonManagedUsedArea(edtRatoonManagedUsedArea.getText().toString());
                    addD10Table.setLoadShedderArea(edtLoadShedderArea.getText().toString()); //doubt//weedicides/pesticides
                    addD10Table.setTrashShedderArea(edtTrashShedderArea.getText().toString());

                    addD10Table.setRelationShipTypeId(relationTypeId);
                    addD10Table.setNominee(edtNominee.getText().toString());
                    addD10Table.setGuarantor1(g1);
                    addD10Table.setGuarantor2(g2);
                    addD10Table.setGuarantor3(g3);
//                    addD10Table.setGuarantor1(edtGuarantor1.getText().toString());
//                    addD10Table.setGuarantor2(edtGuarantor2.getText().toString());
//                    addD10Table.setGuarantor3(edtGuarantor3.getText().toString());
                    addD10Table.setPerishableReasonId("0");
                    addD10Table.setServerStatus("0");
                    addD10Table.setActive(true);
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    Log.d("TAG", "onClick: date" + dateTime);
                    addD10Table.setCreatedDate(dateTime);
                    addD10Table.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addD10Table.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addD10Table.setUpdatedDate(dateTime);
                    String one = edtGuarantor1.getText().toString().trim();
                    String two = edtGuarantor2.getText().toString().trim();
                    String three = edtGuarantor3.getText().toString().trim();
                    boolean isSame = false;

                    if (!one.isEmpty()) {
                        if (!two.isEmpty()) {
                            if (one.equals(two)) {
                                isSame = true;
                            }
                            if (!three.isEmpty()) {
                                if (three.equals(two) || one.equals(three)) {
                                    isSame = true;
                                }
                            }
                        }
                    }

                    if (!isSame) {
                     //   viewModel.insertD30IntoLocalDBQuery(addD10Table)
                              viewModel.updateD30values(stringInspectionDate,edtAgreementedArea.getText().toString().trim(),edtNominee.getText().toString(),g1,g2,g3,relationTypeId,
                                                false,dateTime,(appHelper.getSharedPrefObj().getString(DeviceUserID, "")),addPlotTable.getPlotNo(),"0");
                        viewModel.updatePLotNoAgreemented(String.valueOf(edtAgreementedArea.getText().toString().trim()), appHelper.getSharedPrefObj().getString(DeviceUserID, ""), dateTime, addPlotTable.getPlotNo());
                        finish();
                    } else {
                        Toast.makeText(AgreementedAreaActivity.this, "guarantors cant be same", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        edtGuarantor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator=1;
                Intent intent = new Intent(AgreementedAreaActivity.this, GuarantorListActivity.class);
//                    Intent intent = new Intent(PlotDetailSubmitActivity.this, GepBoundariesMap.class);
//                intent.putExtra("plot_code", etPlotNo.getText().toString());
                startActivityForResult(intent, 2);
            }
        });

        edtGuarantor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator=2;
                Intent intent = new Intent(AgreementedAreaActivity.this, GuarantorListActivity.class);
//                    Intent intent = new Intent(PlotDetailSubmitActivity.this, GepBoundariesMap.class);
//                intent.putExtra("plot_code", etPlotNo.getText().toString());
                startActivityForResult(intent, 2);
            }
        });

        edtGuarantor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicator=3;
                Intent intent = new Intent(AgreementedAreaActivity.this, GuarantorListActivity.class);
//                    Intent intent = new Intent(PlotDetailSubmitActivity.this, GepBoundariesMap.class);
//                intent.putExtra("plot_code", etPlotNo.getText().toString());
                startActivityForResult(intent, 2);
            }
        });

        configureDagger();
        configureViewModel();
        edtAgreementedArea.addTextChangedListener(new GenericTextWatcher(edtAgreementedArea));
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }
    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getInterCropListDataFromLocalDB(spInterCrop);
        getWeedStatusHDRListDataFromLocalDB(spWeedStatus);
        getRelationTypeListDataFromLocalDB(spRelationshipType);
        getD10Data(addPlotTable.getPlotNo(),addPlotTable.getSeasonCode());
//        getD10Data(addPlotTable.getPlotNo(),"2022-23");
        getD30ListFromLocalDbCheckDBNotSync("30",addPlotTable.getFarmerCode(),addPlotTable.getPlotNo());
//        getFarmerPLotListFromLocalDb30("30",addPlotTable.getFarmerCode(),addPlotTable.getPlotNo());
        /*getSeasonListDataFromLocalDB(spSeason);
        getFarmerListDataFromLocalDB(spFarmer);
        getVillageListDataFromLocalDB(spVillage);
        getResonListDataFromLocalDB(spReason);
        getVarietyListDataFromLocalDB(spVariety);
        getPlantTypeListDataFromLocalDB(spPlantType);*/
        setValues();
    }

    private void setValues() {
        edtPlotNo.setText(addPlotTable.getPlotNo());
        edtSeasonCode.setText(addPlotTable.getSeasonCode());
//        edtSeasonCode.setText("2022-23");
//        etCropCode.setText(addPlotTable.getCropTypeId()+"");//doubt
//        etCropType.setText(addPlotTable.getCropTypeId()+"");
//        getCropDetailsByCropId(String.valueOf(addPlotTable.getInterCropId()));
        if(addPlotTable.getCropTypeId()==1){
            etCropType.setText("SugarCane");
        } else {
            etCropType.setText("Bulk");
        }
//        etCropCode.setText(addPlotTable.getInterCropId());
//        getFarmerListDataFromLocalDB();
//        etCropType.setText(addPlotTable.getCropTypeId()+"");
        getLookUpDtlDetailsByIdPlot(String.valueOf(addPlotTable.getPlotTypeId()),edtPlotType);
        getLookUpDtlDetailsById(String.valueOf(addPlotTable.getCropTypeId()),etCropType);
        edtFarmerCode.setText(addPlotTable.getFarmerCode());
        edtRyotName.setText(addPlotTable.getFarmerCode());
        edtSurveyNo.setText(addPlotTable.getSurveyNo());
//        edtAgreedTon.setText(addPlotTable.getAgreedTon());
        edtReportedArea.setText(addPlotTable.getReportedArea());
        edtMeasuredArea.setText(addPlotTable.getMeasureArea());
        getVarietyDetailsByVarietyId(addPlotTable.getVarietyId());
//        edtVariety.setText(addPlotTable.getVarietyId());
        edtFieldName.setText(addPlotTable.getFieldName());
        edtBirNo.setText(addPlotTable.getBIRNo());
        edtBirDate.setText(addPlotTable.getBIRDate());
//        getPlotExistOnDetailsById(addPlotTable.getPlotTypeId());
        getPlantTypeDetailsByPlantId(addPlotTable.getPlantTypeId());
//        edtPlotType.setText(addPlotTable.getPlotTypeId());
//        edtPlotType.setText(addPlotTable.getPlotTypeId());
        edtReportedArea.setText(addPlotTable.getReportedArea());
        edtMeasuredArea.setText(addPlotTable.getMeasureArea());
        edtAgreementedArea.setText(addPlotTable.getMeasureArea());
        edtExpectedPlantingDate.setText(addPlotTable.getPlantingDate());
        edtVariety.setText(addPlotTable.getVarietyId());

        getFarmerIDDetailsByFarmerCode(addPlotTable.getFarmerCode());
        getPlotVillageDetailsByVillageId(addPlotTable.getPlotVillageId());
//        edtDeepFurrow.setText(addPlotTable.getPlotTypeId());


        String inputPattern = DATE_FORMAT_YYYY_MM_DD;
        String outputPattern = DATE_FORMAT_DD_MMM_YYYY2;
//        String outputPattern = DATE_FORMAT_DD_MM_YYYY2;
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        Date date1 = null;
        strFinalPlantingDate = null;
        String strFinalDates = null;
        strFinalBirDate = null;
        String strFinalBirDates = null;
        String strFinalDateDisplay = null;
        try {
            date = inputFormat.parse(addPlotTable.getPlantingDate());
//            date = inputFormat.parse(addPlotTable.getPlantingDate());
            strFinalDates = outputFormat.format(date);
            strFinalPlantingDate = addPlotTable.getPlantingDate();
            if(addPlotTable.getBIRDate()!=null){
            date1 = inputFormat.parse(addPlotTable.getBIRDate());
//            date = inputFormat.parse(addPlotTable.getPlantingDate());
            strFinalBirDates = outputFormat.format(date1);
            strFinalBirDate = addPlotTable.getBIRDate();}
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtExpectedPlantingDate.setText(strFinalDates);
        edtBirDate.setText(strFinalBirDates);


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

                            edtRyotName.setText(farmerTable.getName());
                            edtRyotFatherName.setText(farmerTable.getFatherName());
                            getVillageDetailsByVillageId(farmerTable.getVillageId());
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

    public void getPlotVillageDetailsByVillageId(String strPlotVillageId) {
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
//                            edtRyotVillage.setText(strPlotVillageName);
                            edtPlotAreaVillage.setText(strPlotVillageName);
                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

                            edtPlotDivision.setText(villageTable.getDivisonName());
                            edtPlotCircle.setText(villageTable.getCircleName());
                            edtPlotSection.setText(villageTable.getSectionName());

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

    public void getLookUpDtlDetailsById(String plantTypeId, EditText look) {
        try {
            viewModel.getLookupDtlDetailsById(plantTypeId);
            if (viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        LookupDtlTable villageTable = (LookupDtlTable) o;
                        viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            look.setText(villageTable.getName());
                            etCropCode.setText(villageTable.getCode());
                        }

                    }


                };
                viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getLookUpDtlDetailsByIdPlot(String plantTypeId, EditText look) {
        try {
            viewModel.getLookupDtlDetailsById(plantTypeId);
            if (viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        LookupDtlTable villageTable = (LookupDtlTable) o;
                        viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            look.setText(villageTable.getName());

                        }

                    }


                };
                viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
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
                        weedStatusList.clear();
                        weedStatusList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            weedStatus.add("Select WeedStatus *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                weedStatus.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AgreementedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, weedStatus);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
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

    public void getRelationTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("RelationType");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getRelationTypeDetailsListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No RelationType list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getRelationTypeDetailsListDataFromLocalDB(Spinner spSelectSeason, Integer id) {
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
                        relationTypes.clear();
                        relationTypesList.clear();
                        relationTypesList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            relationTypes.add("Select RelationType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                relationTypes.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AgreementedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, relationTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No RelationType list ");
                        }
                    }
                };
                viewModel.getLookupDetailsListLiveData().observe(this, getLeadRawDataObserver);
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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AgreementedAreaActivity.this,
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

    public void getD10Data(String plotNum,String seasonCode) {
        try {
            viewModel.getD10Data(plotNum,seasonCode);
            if (viewModel.getInsertD10IntoLocalDBQueryLiveDataLocalDB() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AddD10Table addFertilizerDetailsTables = (AddD10Table) o;
                        viewModel.getInsertD10IntoLocalDBQueryLiveDataLocalDB().removeObserver(this);

                        if (addFertilizerDetailsTables != null ) {



                        }


                    }

                };
                viewModel.getInsertD10IntoLocalDBQueryLiveDataLocalDB().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void getVarietyDetailsByVarietyId(String strVillageId) {
        try {
            viewModel.getVarietyById(strVillageId);
            if (viewModel.getVarietyFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VarietyTable villageTable = (VarietyTable) o;
                        viewModel.getVarietyFromLocalDBByIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            edtVariety.setText(villageTable.getName());
                            //getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getVarietyFromLocalDBByIdLiveData().observe( this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlantTypeDetailsByPlantId(String plantTypeId) {
        try {
            viewModel.getPlantTypeDetailsByPlantId(plantTypeId);
            if (viewModel.getPlantTypeDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        PlantTypeTable villageTable = (PlantTypeTable) o;
                        viewModel.getPlantTypeDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            edtPlantType.setText(villageTable.getName());
                            estimatedTon  =  Double.valueOf(villageTable.getEstimatedTon());
                            edtAgreedTon.setText(String.valueOf(Double.valueOf(addPlotTable.getMeasureArea())*estimatedTon));
                        }

                    }


                };
                viewModel.getPlantTypeDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getPlotExistOnDetailsById(String plantTypeId) {
        try {
            viewModel.getPlotExistOnDetailsById(plantTypeId);
            if (viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        PlotExistOnTable villageTable = (PlotExistOnTable) o;
                        viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            edtPlotType.setText(villageTable.getName());
                        }

                    }


                };
                viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getCropDetailsByCropId(String strPlotVillageId) {
        try {
            viewModel.getCropDetailsByCropId(strPlotVillageId);
            if (viewModel.geCropListDetailsFromLocalDBByCropIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        CropTable villageTable = (CropTable) o;
                        viewModel.geCropListDetailsFromLocalDBByCropIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
//                           String strPlotMandalId = String.valueOf(villageTable.getMandalId());
                            String strPlotVillageName = villageTable.getName();

                            etCropType.setText(strPlotVillageName);
                            etCropCode.setText(villageTable.getCode());
//                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

//                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
//                            getMandalDetailsByVillageId(String.valueOf(villageTable.getSectionId()),txtMandal);
//                            getMandalDetailsFromLocalDB(strPlotMandalId);
                        }

                    }


                };
                viewModel.geCropListDetailsFromLocalDBByCropIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

//    public void getFarmerListDataFromLocalDB() {
//        try {
//            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            viewModel.getFarmerListFromLocalDBStatus("2022-23");
//            if (viewModel.getFarmerDetailsListLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        List<AddFarmerTable> seasonTableList = (List<AddFarmerTable>) o;
//                        viewModel.getFarmerDetailsListLiveData().removeObserver(this);
//                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        //clusterList.add("Select Cluster");
//                        farmerCodes.clear();
//                        farmerTableList.clear();
//                        farmerTableList = seasonTableList;
//
//                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            farmerCodes.add("Select Farmer *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                farmerCodes.add(seasonTableList.get(i).getCode()+"-"+seasonTableList.get(i).getName());
////                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":farmer", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AgreementedAreaActivity.this,android.R.layout.simple_dropdown_item_1line,farmerCodes);
//                            edtGuarantor1.setThreshold(3);
//                            edtGuarantor1.setAdapter(adapter);
//                            edtGuarantor2.setThreshold(3);
//                            edtGuarantor2.setAdapter(adapter);
//                            edtGuarantor3.setThreshold(3);
//                            edtGuarantor3.setAdapter(adapter);
//
//                        } else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No farmer  list ");
//                        }
//                    }
//                };
//                viewModel.getFarmerDetailsListLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//
////            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
//        }
//    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    public void getFarmerPLotListFromLocalDb30(String status,String farmerCode,String plotCode) {
        try {
            viewModel.getLandDetailsStageListFromLocalDb(status,farmerCode,plotCode);
            if (viewModel.getFarmerPlotDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotTable> odVisitSurveyTableList = (List<AddPlotTable>) o;
                        viewModel.getFarmerPlotDetailsListLiveData().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {
                            id=odVisitSurveyTableList.get(0).getId();
                        } else {

                        }
                    }
                };
                viewModel.getFarmerPlotDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getD30ListFromLocalDbCheckDBNotSync(String status,String farmerCode,String plotCode) {
        try {
            viewModel.getD30ListFromLocalDBNotSync(addPlotTable.getSeasonCode(),farmerCode,plotCode);
//            viewModel.getD30ListFromLocalDBNotSync("2022-23",farmerCode,plotCode);
            if (viewModel.getD30TablesDetailsListNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddD30Table> addFertilizerDetailsTables = (List<AddD30Table>) o;
                        viewModel.getD30TablesDetailsListNotSyncLiveData().removeObserver(this);

                        if (addFertilizerDetailsTables != null && addFertilizerDetailsTables.size() > 0) {
                            id=addFertilizerDetailsTables.get(0).getId();

                        } else {

                        }


                    }

                };
                viewModel.getD30TablesDetailsListNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
//        ui();
//        syncProgressDialog.dismiss();
    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
//            String text = editable.toString();
//            switch (view.getId()) {
//                case R.id.edt_one:
//                    if (text.length() == 1)
//                        etOtpSecond.requestFocus();
//                    etOtpSecond.setSelection(etOtpSecond.getText().length());
//                    break;
//
//            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            String text = charSequence.toString();
            switch (view.getId()) {
                case R.id.edtAgreementedArea:
                    if (text.length() >= 1){
                        edtAgreedTon.setText(String.valueOf(Double.valueOf(edtAgreementedArea.getText().toString())*estimatedTon));
                    }
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//                if (resultCode == RESULT_OK) {
                if (resultCode == 2) {
                    try {
                        if(indicator==1){
                            g1=data.getStringExtra("farmer");
                            edtGuarantor1.setText(data.getStringExtra("farmerName"));
                        } else if(indicator==2){
                            g2=data.getStringExtra("farmer");
                            edtGuarantor2.setText(data.getStringExtra("farmerName"));
                        } else if(indicator==3){
                            g3=data.getStringExtra("farmer");
                            edtGuarantor3.setText(data.getStringExtra("farmerName"));
                        }
//                        handleBigCameraPhoto();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {

                }

        } // switch




}