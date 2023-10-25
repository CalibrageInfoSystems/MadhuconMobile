package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MMM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.trst01.locationtracker.constant.AppConstant.DeviceUserID;
import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
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
import com.trst01.locationtracker.activity.growthMonitoring.ReportedDetailsGrowthMonitoringActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.LookupHDRTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.PlotExistOnTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
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

public class ReportedAreaActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    AppCompatSpinner spSeason,spCropType,spPlantType,spPlantSubType,spVariety,spPlotType,spSeedMaterialUsed,spSoilType,spSpacing,
            spPreviousLandUse,spSourceOfIrrigation,spMethodOfIrrigation,spPlantingMethod,spPlotExistsOn;
    AutoCompleteTextView edtOfferedNo;
    EditText edtRyotNo,edtOfferDate,edtRyotName,edtRyotFatherName,edtRyotDivision,edtRyotCircle,edtRyotSection,edtRyotVillage,
            edtPlotDivision,edtPlotCircle,edtPlotSection,edtPlotVillageNo,edtPlotNo,edtSurveyNo,edtReportedArea,
            edtAgreedTon,edtFieldName,edtBirNo,edtBirDate,edtDemoPlotArea,edtProfiles,edtTotalArea,edtCultivatedArea,edtDistanceFromPlot,
            edtDeepFurrow,edtDrainageChannel,edtDeepPlough;
    RadioButton settsYes,settsNo,dustYes,dustNo,trashYes,trashNo,redPlotYes,redPlotNo,baselYes,baselNo,compositeYes,compositeNo,
            pressMudYes,pressMudNo,greenManureYes,greenManureNo;
    TextView txtSave,edtExpectedPlantingDate;

//    AddD10Table addPlotTable= new AddD10Table();
    AddPlotOfferTable addPlotTable= new AddPlotOfferTable();
//    AddD10Table addPlotTable;

    String strSelectSeason,strCropType,strOfferDate,plantTypeId,varietyId,strBirDate;
    // TODO: for select season
    List<String> seasonList = new ArrayList<>();
    List<String> seasonListIDs = new ArrayList<>();
    List<String> seasonListCode = new ArrayList<>();

    List<String> varieties = new ArrayList<>();
    List<String> plantTypes = new ArrayList<>();

    List<PlantTypeTable> plantTypeTableList = new ArrayList<>();
    List<VarietyTable> varietyTableList = new ArrayList<>();

    List<String> plantSubTypes = new ArrayList<>();
    List<PlantSubTypeTable> plantSubTypeTableList = new ArrayList<>();
    String plantSubTypeId;
    int plantTypePosition=0;

    List<String> seedMaterials = new ArrayList<>();
    List<LookupDtlTable> seedMaterialIdList = new ArrayList<>();
    String seedMaterialId;

    List<String> spacings = new ArrayList<>();
    List<LookupDtlTable> spacingIdList = new ArrayList<>();
    String spacingId;

    //plotType
    List<String> plotTypes = new ArrayList<>();
    List<LookupDtlTable> plotTypeList = new ArrayList<>();
    String plotTypeId;

    //previous crop id
    List<String> crops = new ArrayList<>();
    List<CropTable> cropTableList = new ArrayList<>();
    String cropId="0";

    //plantingMethods
    List<String> plotExistsOn = new ArrayList<>();
    List<LookupDtlTable> plotExistsOnList = new ArrayList<>();
    String plotExistsOnId;

    //plantingMethods
    List<String> plantingMethods = new ArrayList<>();
    List<LookupDtlTable> plantingMethodsList = new ArrayList<>();
    String plantingMethodsId;


    //plantingMethods
    List<String> cropTypes = new ArrayList<>();
    List<LookupDtlTable> cropTypesList = new ArrayList<>();
    String cropTypeId;

    //source of irrigation
    List<String> sourceOfIrrigations = new ArrayList<>();
    List<LookupDtlTable> sourceOfIrrigationList = new ArrayList<>();
    String sourceOfIrrigationId;

    //soil Type
    List<String> soilTypes = new ArrayList<>();
    List<LookupDtlTable> soilTypeList = new ArrayList<>();
    String soilTypeId;

    //method of irrigation
    List<String> methodOfIrrigations = new ArrayList<>();
    List<LookupDtlTable> methodOfIrrigationList = new ArrayList<>();
    String methodOfIrrigationId;

    ArrayList<String> plotCodes = new ArrayList<>();
    List<AddPlotOfferTable> plotOfferList = new ArrayList<AddPlotOfferTable>();

    String farmerCode;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
    String strFinalDate;
    TextView txtComplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_area);

//        addPlotTable = (AddD10Table) getIntent().getSerializableExtra("data");
        addPlotTable = (AddPlotOfferTable) getIntent().getSerializableExtra("data");
        farmerCode = getIntent().getStringExtra("farmerCode");
//        Toast.makeText(appHelper, addPlotTable.toString(), Toast.LENGTH_SHORT).show();

        initializeUi();
        configureDagger();
        configureViewModel();

    }

    private void initializeUi() {

        txtComplain = findViewById(R.id.txtComplain);

        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportedAreaActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","ReportedArea");
                intent.putExtra("strPlotNumber","addPlotTable.getPlotNo()");
                intent.putExtra("strFarmerCode",addPlotTable.getFarmerCode());
                intent.putExtra("seasonCode",addPlotTable.getSeasonCode());
                startActivity(intent);
            }
        });

        txtSave = findViewById(R.id.txtSave);
        spPlotExistsOn = findViewById(R.id.spPlotExistsOn);
        edtOfferDate = findViewById(R.id.edtOfferDate);
        edtRyotNo = findViewById(R.id.edtRyotNo);
        edtOfferedNo = findViewById(R.id.edtOfferedNo);
        edtRyotName = findViewById(R.id.edtRyotName);
        edtRyotFatherName = findViewById(R.id.edtRyotFatherName);
        edtRyotDivision = findViewById(R.id.edtRyotDivision);
        edtRyotCircle = findViewById(R.id.edtRyotCircle);
        edtRyotSection = findViewById(R.id.edtRyotSection);
        edtRyotVillage = findViewById(R.id.edtRyotVillage);
        edtPlotDivision = findViewById(R.id.edtPlotDivision);
        edtPlotCircle = findViewById(R.id.edtPlotCircle);
        edtPlotSection = findViewById(R.id.edtPlotSection);
        edtPlotVillageNo = findViewById(R.id.edtPlotVillageNo);
        edtPlotNo = findViewById(R.id.edtPlotNo);
        edtSurveyNo = findViewById(R.id.edtSurveyNo);
        edtReportedArea = findViewById(R.id.edtReportedArea);
        edtExpectedPlantingDate = findViewById(R.id.edtExpectedPlantingDate);
        edtAgreedTon = findViewById(R.id.edtAgreedTon);
        edtFieldName = findViewById(R.id.edtFieldName);
        edtBirNo = findViewById(R.id.edtBirNo);
        edtBirDate = findViewById(R.id.edtBirDate);
        edtDemoPlotArea = findViewById(R.id.edtDemoPlotArea);
        edtProfiles = findViewById(R.id.edtProfiles);
        edtTotalArea = findViewById(R.id.edtTotalArea);
        edtCultivatedArea = findViewById(R.id.edtCultivatedArea);
        edtDistanceFromPlot = findViewById(R.id.edtDistanceFromPlot);
        edtDeepFurrow = findViewById(R.id.edtDeepFurrow);
        edtDrainageChannel = findViewById(R.id.edtDrainageChannel);
        edtDeepPlough = findViewById(R.id.edtDeepPlough);
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
        spSeason = findViewById(R.id.spSeason);
        spCropType = findViewById(R.id.spCropType);
        spPlantType = findViewById(R.id.spPlantType);
        spPlantSubType = findViewById(R.id.spPlantSubType);
        spVariety = findViewById(R.id.spVariety);
        spPlotType = findViewById(R.id.spPlotType);
        spSeedMaterialUsed = findViewById(R.id.spSeedMaterialUsed);
        spSoilType = findViewById(R.id.spSoilType);
        spSpacing = findViewById(R.id.spSpacing);
        spPreviousLandUse = findViewById(R.id.spPreviousLandUse);
        spSourceOfIrrigation = findViewById(R.id.spSourceOfIrrigation);
        spPlantingMethod = findViewById(R.id.spPlantingMethod);
        spMethodOfIrrigation = findViewById(R.id.spMethodOfIrrigation);
//           ,,,,,,,,,,,,
//            ,,,;,,,,,,,,,
//            ,,,;    ,,,,,,,,
//                ,,,,,,,,,
//                ,,;
    }

    private void setViews() {

        try {

//            String inputPattern2 = DATE_FORMAT_DD_MM_YYYY2;
//            String outputPattern = DATE_FORMAT_YYYY_MM_DD;
            String inputPattern2 = DATE_FORMAT_YYYY_MM_DD;
            String outputPattern = DATE_FORMAT_DD_MMM_YYYY2;
            String outputPattern2 = DATE_FORMAT_YYYY_MM_DD;
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            SimpleDateFormat outputFormat2 = new SimpleDateFormat(outputPattern2);
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern2);
            Date getDateActDateGermination = null;
            getDateActDateGermination = inputFormat.parse(addPlotTable.getOfferDate());
           String strGetActDateGermination = outputFormat.format(getDateActDateGermination);
            strOfferDate = outputFormat2.format(getDateActDateGermination);
//            strOfferDate= String.valueOf(getDateActDateGermination);
            edtOfferDate.setText(strGetActDateGermination);
        } catch (Exception ex){
            Toast.makeText(appHelper, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
//        edtOfferDate.setText(addPlotTable.getOfferDate());
        edtOfferedNo.setText(addPlotTable.getOfferNo());
        edtReportedArea.setText(addPlotTable.getExpectedArea());
        spSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    strSelectSeason = seasonListCode.get(position);
                    String strSeason = seasonListIDs.get(position);
//                    seasonId = Integer.valueOf(strSeason);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edtOfferDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
                Calendar date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportedAreaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
                        String strdisplayDate = displayDate.format(date.getTime());
                        strOfferDate = simpleDateFormat.format(date.getTime());
                        edtOfferDate.setText(strdisplayDate);

                        edtExpectedPlantingDate.setText("");
                        strFinalDate="";

                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                DatePicker datePicker = datePickerDialog.getDatePicker();
//                datePicker.setMaxDate(System.currentTimeMillis()); // removed
                datePickerDialog.show();
            }
        });

        edtRyotNo.setText(addPlotTable.getFarmerCode());

        spPlantType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    strSelectSeason = parent.getItemAtPosition(position).toString().trim();
                plantTypePosition = position;
                try {
                    String plantTypePosition = plantTypes.get(position);
                    if(position>0){
                        plantTypeId= String.valueOf(plantTypeTableList.get(position-1).getId());
                        getPlantSubTypeListDataFromLocalDB(plantTypeTableList.get(position-1).getId(),spPlantSubType);
                        if(edtReportedArea.getText().toString().trim().length()>0){
                            edtAgreedTon.setText(String.valueOf(Double.valueOf(edtReportedArea.getText().toString())*Double.valueOf(plantTypeTableList.get(position-1).getEstimatedTon())));
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtPlotNo.setText(addPlotTable.getPlotIndNo());
//        edtPlotNo.setText(addPlotTable.getPlotNo());

//        edtReportedArea.setText(addPlotTable.getReportedArea());

        String inputPattern = DATE_FORMAT_YYYY_MM_DD;
        String outputPattern = DATE_FORMAT_DD_MMM_YYYY2;
//        String outputPattern = DATE_FORMAT_DD_MM_YYYY2;
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        strFinalDate = null;
        String strFinalDates = null;
        String strFinalDateDisplay = null;
        try {
            date = inputFormat.parse(addPlotTable.getExpectedPlantingDate());
//            date = inputFormat.parse(addPlotTable.getPlantingDate());
            strFinalDates = outputFormat.format(date);
          //  strFinalDate =addPlotTable.getExpectedPlantingDate();
      //      Log.e("===========>",strFinalDate);

            // Parse the original date string
            OffsetDateTime dateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTime = OffsetDateTime.parse(addPlotTable.getExpectedPlantingDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }

            // Format the date in the desired format
          //  String strFinalDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                strFinalDate = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }

            System.out.println("Original Date: " + addPlotTable.getExpectedPlantingDate());
            System.out.println("Formatted Date: " + strFinalDate);

           // strFinalDate = simpleDateFormat.format(addPlotTable.getExpectedPlantingDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtExpectedPlantingDate.setText(strFinalDates);

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

        edtReportedArea.addTextChangedListener(new GenericTextWatchers(edtReportedArea));
        edtOfferedNo.addTextChangedListener(new GenericTextWatchers(edtOfferedNo));

        spPlantSubType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
//                    if(position>0){
                        plantSubTypeId= String.valueOf(plantSubTypeTableList.get(position).getId());//as same position you need not subtract one from position
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtBirDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
                Calendar date = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportedAreaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);

                   //     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
                        String strdisplayDate = displayDate.format(date.getTime());
                        strBirDate = simpleDateFormat.format(date.getTime());
                        edtBirDate.setText(strdisplayDate);

                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        edtExpectedPlantingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strOfferDate.isEmpty()){
                    Toast.makeText(ReportedAreaActivity.this, "please select offer date", Toast.LENGTH_SHORT).show();
                } else {
                    final Calendar currentDate = Calendar.getInstance();
                    Calendar date = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ReportedAreaActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.set(year, monthOfYear, dayOfMonth);


                            SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
                            String strdisplayDate = displayDate.format(date.getTime());
                            strFinalDate = simpleDateFormat.format(date.getTime());
                            edtExpectedPlantingDate.setText(strdisplayDate);

                        }
                    }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                    DatePicker datePicker = datePickerDialog.getDatePicker();
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
                    String str = strOfferDate;

                    try {
//                        String inputPattern2 = DATE_FORMAT_YYYY_MM_DD;
//                        String inputPattern2 = DATE_FORMAT_YYYY_MM_DD;
//                        String outputPattern = DATE_FORMAT_DD_MM_YYYY2;
//                        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
//                        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern2);
//                        Date getDateActDateGermination = null;
//                        getDateActDateGermination = inputFormat.parse(addPlotTable.getOfferDate());
//                        String str = String.valueOf(getDateActDateGermination);
//                        String str = addPlotTable.getOfferDate();
                        String[] splited = str.split("-");
                        calendar3.set(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]) - 1, Integer.parseInt(splited[2]));

                        datePicker.setMinDate(calendar3.getTimeInMillis());

                    } catch (Exception ex){

                    }




//                datePicker.setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();
                }
            }
        });

        spSeedMaterialUsed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if(position>0){
                    seedMaterialId= String.valueOf(seedMaterialIdList.get(position-1).getId());
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

        spPreviousLandUse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spPlantingMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if(position>0) {
                        plantingMethodsId = String.valueOf(plantingMethodsList.get(position - 1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        List<String> cropTypes = new ArrayList<>();
//        cropTypes.add("SugarCane");
//        cropTypes.add("Bulk");
//        ArrayAdapter<String> data_Adapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                R.layout.spinner_dropdown_layout, cropTypes);
//        data_Adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//        spCropType.setAdapter(data_Adapter);

//        spCropType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                strCropType = parent.getItemAtPosition(position).toString().trim();
//                strCropType = parent.getItemAtPosition(position).toString().trim();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//

        spCropType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    if(position>0) {
                        cropTypeId = String.valueOf(cropTypesList.get(position).getId());
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPlotExistsOn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if(position>0) {
                        plotExistsOnId = String.valueOf(plotExistsOnList.get(position - 1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPlotType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if(position>0){
                    plotTypeId= String.valueOf(plotTypeList.get(position-1).getId());
                    }
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
                    if(position>0){
                    soilTypeId= String.valueOf(soilTypeList.get(position-1).getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSourceOfIrrigation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if(position>0) {
                        sourceOfIrrigationId = String.valueOf(sourceOfIrrigationList.get(position - 1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMethodOfIrrigation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if(position>0) {
                        methodOfIrrigationId = String.valueOf(methodOfIrrigationList.get(position - 1).getId());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUI()) {
                    AddD10Table addD10Table = new AddD10Table();
                    addD10Table.setVarietyId(varietyId);
                    addD10Table.setSeasonCode(strSelectSeason);
                    addD10Table.setOfferedNo(Integer.parseInt(edtOfferedNo.getText().toString()));
                    addD10Table.setCropTypeId(Integer.parseInt(cropTypeId));
//                addD10Table.setCropTypeId(2);
//                addD10Table.setOfferedNo(Integer.parseInt(edtOfferedNo.getText().toString()));
//                if(spCropType.getSelectedItemPosition()==0){
//                    addD10Table.setCropTypeId(1);
////                    addD10Table.setCropTypeId(2);
//                } else {
//                    addD10Table.setCropTypeId(2);
////                    addD10Table.setCropTypeId(1);
////                    addD10Table.setCropTypeId(spCropType.getSelectedItemPosition());
//                }

//                addD10Table.setCropTypeId(Integer.parseInt(strCropType));
//                addD10Table.setCropTypeId(Integer.parseInt(cropId));
                    addD10Table.setIrrigationTypeId(methodOfIrrigationId);
//                addD10Table.setIrrigationTypeId(addPlotTable.getIrrigationTypeId());
                    addD10Table.setGuarantor1("");
                    addD10Table.setGuarantor2("");
                    addD10Table.setGuarantor3("");
                    addD10Table.setRelationShipTypeId("");
                    addD10Table.setIsSettsHotWaterTreatment("0");
                    addD10Table.setIsGreenManures("0");
                    addD10Table.setIsFilterPressMud("0");
                    addD10Table.setIsCompositeFormYard("0");
                    addD10Table.setIsPreviousRedPlot("0");
                    addD10Table.setIsTrashMulching("0");
                    addD10Table.setIsBasalFertilization("0");
                    addD10Table.setIsDustApplied("0");
                    addD10Table.setPlantSubTypeId(Integer.parseInt(plantSubTypeId));
                    addD10Table.setTotalArea(edtTotalArea.getText().toString());
                    addD10Table.setDistanceFromPlot(edtDistanceFromPlot.getText().toString());
                    addD10Table.setSpacingId(spacingId);
                    addD10Table.setPreviousCropId(cropId);
                    addD10Table.setPlotExistOnId(plotExistsOnId);
                    addD10Table.setProfile(edtProfiles.getText().toString());
                    addD10Table.setCultivatedArea(edtCultivatedArea.getText().toString());
//                addD10Table.setOfferedNo(Integer.parseInt(cropId));//offered
                    addD10Table.setFarmerCode(addPlotTable.getFarmerCode());
                    addD10Table.setFatherName(addPlotTable.getFatherName());
                    addD10Table.setFarmerVillageId(addPlotTable.getFarmerVillageId());
                    addD10Table.setPlotVillageId(addPlotTable.getPlotVillageId());
                    addD10Table.setPlantTypeId(plantTypeId);
                    addD10Table.setPlotNo("");
//                addD10Table.setVarietyId("");
                    addD10Table.setSurveyNo(edtSurveyNo.getText().toString());
                    addD10Table.setReportedArea(edtReportedArea.getText().toString().trim());
                    addD10Table.setPlantingDate(strFinalDate);
//                addD10Table.setPlantingDate(edtExpectedPlantingDate.getText().toString());

                    addD10Table.setAgreedTon(edtAgreedTon.getText().toString());
                    addD10Table.setFieldName(edtFieldName.getText().toString());
                    addD10Table.setBIRNo(edtBirNo.getText().toString().trim());
                    addD10Table.setDemoPlotArea(edtDemoPlotArea.getText().toString());
                    addD10Table.setBIRDate(strBirDate);
//                addD10Table.setBIRDate(edtBirDate.getText().toString());
                    addD10Table.setPlotTypeId(plotTypeId);
                    addD10Table.setSeedMaterialUsedId(seedMaterialId);
                    addD10Table.setPlantingMethodId(plantingMethodsId);
//                addD10Table.setPlantingMethodId(plantingMethodsId);
                    addD10Table.setServerStatus("0");
                    addD10Table.setSync(false);
                    addD10Table.setActive(true);
//                addD10Table.setSeedMaterialUsedId(seedMaterialId);
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    Log.d("TAG", "onClick: date" + dateTime);
                    addD10Table.setCreatedDate(dateTime);
                    addD10Table.setCreatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addD10Table.setUpdatedByUserId(appHelper.getSharedPrefObj().getString(DeviceUserID, ""));
                    addD10Table.setUpdatedDate(dateTime);

                    viewModel.insertD10IntoLocalDBQuery(addD10Table);
//                viewModel.updatePLotNoStage(String.valueOf(measuredArea),"1",dateTime,plot);
                    Intent intent = new Intent(ReportedAreaActivity.this, ViewStatusPlotOfferReportedAreaListActivity.class);
                    intent.putExtra("farmerCode", addPlotTable.getFarmerCode());
                    startActivity(intent);
                    finish();
                }
            }
        });

//        spPlantType.setSelection();

    }

    private boolean validateUI() {
        String reportedAreaText = edtReportedArea.getText().toString();
        if (reportedAreaText.isEmpty()) {
            // Handle the case where the input is empty
            Toast.makeText(ReportedAreaActivity.this, "Please enter a reported area", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            double reportedArea = Double.valueOf(reportedAreaText);
            int MAX_ACRE_FOR_AGGREMENT = viewModel.getlockedkeyvalue("MAX_ACRE_FOR_AGGREMENT");
            System.out.println("MAX_ACRE_FOR_AGGREMENT: " + MAX_ACRE_FOR_AGGREMENT);
            if (reportedArea <= MAX_ACRE_FOR_AGGREMENT) {
                if (plantTypePosition > 0) {
                    edtAgreedTon.setText(String.valueOf(reportedArea * Double.valueOf(plantTypeTableList.get(plantTypePosition - 1).getEstimatedTon())));
                }
            } else {
                Toast.makeText(ReportedAreaActivity.this, "Entered Reported Area should be less than or equal to " + MAX_ACRE_FOR_AGGREMENT + " Acres", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            // Handle the case where the input cannot be parsed as a double
            Toast.makeText(ReportedAreaActivity.this, "Invalid input for Reported Area", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private int setSpinnerPosition(List<PlantTypeTable> codeList,String code) {
        int position=0;
        for(int toFind=0;toFind<codeList.size();toFind++){
            if(String.valueOf(codeList.get(toFind).getId()).equals(code)){
                position= toFind;
                break;
            }
        }
        return position;
    }

    private int setSpinnerPosition(List<VarietyTable> codeList,String code,Boolean test) {
        int position=0;
        for(int toFind=0;toFind<codeList.size();toFind++){
            if(codeList.get(toFind).getId().equals(code)){
                position= toFind;
                break;
            }
        }
        return position;
    }

//    private int setSpinnerPosition(List<String> codeList,String code) {
//        int position=0;
//        for(int toFind=0;toFind<codeList.size();toFind++){
//            if(codeList.get(toFind).equals(code)){
//                position= toFind;
//                break;
//            }
//        }
//        return position;
//    }
//

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getSeasonListDataFromLocalDB(spSeason);
        getFarmerIDDetailsByFarmerCode(addPlotTable.getFarmerCode());//here
        getPlotVillageDetailsByVillageId(addPlotTable.getPlotVillageId());//here
        getVarietyListDataFromLocalDB(spVariety);
        getPlotTypeListDataFromLocalDB(spPlotType);
        getPlantTypeListDataFromLocalDB(spPlantType,false);
        getSeedMaterialUsedListDataFromLocalDB(spSeedMaterialUsed);
        getSoilTypeListDataFromLocalDB(spSoilType);
        getSpacingListDataFromLocalDB(spSpacing);
        getPreviousLandUseCropListDataFromLocalDB(spPreviousLandUse);//previousLandUse
        getSourceOfIrrigationListDataFromLocalDB(spSourceOfIrrigation);
        getMethodOfIrrigationListDataFromLocalDB(spMethodOfIrrigation);
        getPlantingMethodListDataFromLocalDB(spPlantingMethod);
        getCropTypeListDataFromLocalDB(spCropType);
//        getPlotExistsOnListDataFromLocalDB(spPlotExistsOn);
        getPlotOfferListDataFromLocalDB();
        /*getSeasonListDataFromLocalDB(spSeason);
        getFarmerListDataFromLocalDB(spFarmer);
        getVillageListDataFromLocalDB(spVillage);
        getResonListDataFromLocalDB(spReason);
        getVarietyListDataFromLocalDB(spVariety);
        getPlantTypeListDataFromLocalDB(spPlantType);*/

//        setSpinnerPosition();

        setViews();
    }

    private int setSpinnerPosition(ArrayList<String> codeList,String code) {
        int position=0;
        for(int toFind=0;toFind<codeList.size();toFind++){
            if(codeList.get(toFind).equals(code)){
                position= toFind;
                break;
            }
        }
        return position;
    }

    public void getPlotOfferListDataFromLocalDBSearch(String code) {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getPlotOfferListD10("2022-23",farmerCode,"1",code);
            } else {
                viewModel.getPlotOfferListD10(seasonCode,farmerCode,"1",code);
            }
//            viewModel.getPlotOfferListD10("2022-23",farmerCode,"1",code);
            if (viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotOfferTable> seasonTableList = (List<AddPlotOfferTable>) o;
                        viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (seasonTableList != null && seasonTableList.size() > 0) {



                        } else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No farmer  list ");
                        }
                    }
                };
                viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getPlotOfferListDataFromLocalDB() {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getPlotOfferListD10("2022-23",farmerCode,"1");
            } else {
                viewModel.getPlotOfferListD10(seasonCode,farmerCode,"1");
            }
//            viewModel.getPlotOfferListD10("2022-23",farmerCode,"1");
            if (viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotOfferTable> seasonTableList = (List<AddPlotOfferTable>) o;
                        viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
                        plotCodes.clear();
                        plotOfferList.clear();
                        plotOfferList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                plotCodes.add(seasonTableList.get(i).getOfferNo());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":farmer", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(ReportedAreaActivity.this,android.R.layout.simple_dropdown_item_1line,plotCodes);
                            edtOfferedNo.setThreshold(3);
                            edtOfferedNo.setAdapter(adapter);

//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlotOfferActivity.this, android.R.layout.simple_dropdown_item_1line, farmerCodes);
//                            dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No offered no's  list ");
                        }
                    }
                };
                viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getSeasonlistFromlocalDBLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getSeasonListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getSeasonListForLogBookFromLocalDB();
            if (viewModel.getSeasonlistFromlocalDBLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<SeasonTable> seasonTableList = (List<SeasonTable>) o;
                        viewModel.getSeasonlistFromlocalDBLiveData().removeObserver(this);
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
                                Log.e(":dsvbjl_id_season", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
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
                            edtPlotVillageNo.setText(strPlotVillageName);
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
                                Log.e(":varieties", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, varieties);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            int pos = setSpinnerPosition(varietyTableList,addPlotTable.getExpectedVarityId(),false);
                            spSelectSeason.setSelection(pos+1,true);
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No varieties list ");
                        }
                    }
                };
                viewModel.getVarietyDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlantTypeListDataFromLocalDB(Spinner spSelectSeason,Boolean before) {
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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, plantTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();

                            int pos = setSpinnerPosition(plantTypeTableList,addPlotTable.getPlantTypeId());
                            spSelectSeason.setSelection(pos+1,true);
//                            if(before)
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Plant Type list ");
                        }
                    }
                };
                viewModel.getPlantTypeDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlantSubTypeListDataFromLocalDB(Integer plantTypeId,Spinner spSelectSeason) {
        try {
            viewModel.getPlantSubTypeDetailsListFromLocalDBByPlantType(plantTypeId);
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
//                            plantSubTypes.add("Select PlantSubType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                plantSubTypes.add(seasonTableList.get(i).getName());
//                                seasonListIDs.add(String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":plantType", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
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
//                        seedMaterials.clear();
//                        seedMaterialIdList.clear();
//                        seedMaterialIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSeedMaterialUsedListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            seedMaterials.add("Select SeedMaterialUsed *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                seedMaterials.add(seasonTableList.get(i).getName());
//                                Log.e(":seedMaterials", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, seedMaterials);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
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

    public void getSeedMaterialUsedListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
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
                        soilTypeList.clear();
                        soilTypeList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                                                        seedMaterials.add("Select SeedMaterialUsed *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                seedMaterials.add(seasonTableList.get(i).getName());
                                Log.e(":seedMaterials", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, seedMaterials);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No SeedMaterialUsed list ");
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
            viewModel.getLogBookHdrListFromLocalDBStatus("Spacing");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
//                        spacings.clear();
//                        spacingIdList.clear();
//                        spacingIdList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSpacingListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            spacings.add("Select Spacing *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                spacings.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, spacings);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No spacings list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, spacings);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Spacing list ");
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

    public void getPreviousLandUseCropListDataFromLocalDB(Spinner spSelectSeason) {
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
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
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

    public void getPlotTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("PlotType");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
//                        plotTypes.clear();
//                        plotTypeList.clear();
//                        plotTypeList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getPlotTypeListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            plotTypes.add("Select PLotType *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                plotTypes.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, plotTypes);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No plotTypes list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlotTypeListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
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
                        plotTypes.clear();
                        plotTypeList.clear();
                        plotTypeList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            plotTypes.add("Select PLotType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                plotTypes.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, plotTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No plotTypes list ");
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


    public void getSoilTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("SoilType");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");

//                        soilTypes.clear();
//                        soilTypeList.clear();
//                        soilTypeList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSoilTypeListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            soilTypes.add("Select Soil Type *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                soilTypes.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, soilTypes);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No soilType list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                        soilTypeList.clear();
                        soilTypeList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            soilTypes.add("Select SoilType *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                soilTypes.add(seasonTableList.get(i).getName());
                                Log.e(":SoilType", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, soilTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
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

    public void getSourceOfIrrigationListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("Source of Irrigation");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
//                        sourceOfIrrigations.clear();
//                        sourceOfIrrigationList.clear();
//                        sourceOfIrrigationList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getSourceOfIrrigationListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            sourceOfIrrigations.add("Select Source of Irrigation *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                sourceOfIrrigations.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, sourceOfIrrigations);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No source of irrigation list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                        sourceOfIrrigations.clear();
                        sourceOfIrrigationList.clear();
                        sourceOfIrrigationList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            sourceOfIrrigations.add("Select Source of Irrigation *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                sourceOfIrrigations.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, sourceOfIrrigations);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
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

    public void getMethodOfIrrigationListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("Method of Irrigation");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
//                        methodOfIrrigations.clear();
//                        methodOfIrrigationList.clear();
//                        methodOfIrrigationList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getMethodOfIrrigationListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            methodOfIrrigations.add("Select Method of Irrigation *");

//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                methodOfIrrigations.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, methodOfIrrigations);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No method of irrigation list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMethodOfIrrigationListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
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
                        methodOfIrrigations.clear();
                        methodOfIrrigationList.clear();
                        methodOfIrrigationList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            methodOfIrrigations.add("Select Method of Irrigation *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                methodOfIrrigations.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, methodOfIrrigations);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Method of Irrigation list ");
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


    public void getPlantingMethodListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("Planting Method");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
//                        plantingMethods.clear();
//                        plantingMethodsList.clear();
//                        plantingMethodsList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getPlantingMethodListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            plantingMethods.add("Select Planting Methods *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                plantingMethods.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, plantingMethods);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No plantingMethods list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getPlantingMethodListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
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
                        plantingMethods.clear();
                        plantingMethodsList.clear();
                        plantingMethodsList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            plantingMethods.add("Select Planting Methods *");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                plantingMethods.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, plantingMethods);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Planting Methods list ");
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


    public void getCropTypeListDataFromLocalDB(Spinner spSelectSeason) {
        try {
            viewModel.getLogBookHdrListFromLocalDBStatus("CropType");
            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");
//                        plantingMethods.clear();
//                        plantingMethodsList.clear();
//                        plantingMethodsList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
                            getCropTypeListDataFromLocalDB(spSelectSeason,seasonTableList.get(0).getId());
//                            plantingMethods.add("Select Planting Methods *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                plantingMethods.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, plantingMethods);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No plantingMethods list ");
                        }
                    }
                };
                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getCropTypeListDataFromLocalDB(Spinner spSelectSeason,Integer id) {
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
                        cropTypes.clear();
                        cropTypesList.clear();
                        cropTypesList = seasonTableList;

                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            cropTypes.add("Select CropType*");

                            for (int i = 0; i < seasonTableList.size(); i++) {
                                cropTypes.add(seasonTableList.get(i).getName());
                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
                                    R.layout.spinner_dropdown_layout, cropTypes);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                            spSelectSeason.setAdapter(dataAdapter);
                            dataAdapter.notifyDataSetChanged();
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No CropTypes list ");
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


//    public void getPlotExistsOnListDataFromLocalDB(Spinner spSelectSeason) {
//        try {
//            viewModel.getLogBookHdrListFromLocalDBStatus("Planting Method");
//            if (viewModel.getLookupHdrDetailsListLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        List<LookupHDRTable> seasonTableList = (List<LookupHDRTable>) o;
//                        viewModel.getLookupHdrDetailsListLiveData().removeObserver(this);
//                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        //clusterList.add("Select Cluster");
//
//                        plotExistsOn.clear();
//                        plotExistsOnList.clear();
//                        plotExistsOnList = seasonTableList;
//
//                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            plotExistsOn.add("Select PLot Exists On Methods *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                plotExistsOn.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, plotExistsOn);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
//                        } else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No plot exists on list");
//                        }
//                    }
//                };
//                viewModel.getLookupHdrDetailsListLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

//    public void getPlotExistsOnListDataFromLocalDB(Spinner spSelectSeason) {
//        try {
//            viewModel.getPlotExistOnDetailsById();
//            if (viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        List<PlotExistOnTable> seasonTableList = (List<PlotExistOnTable>) o;
//                        viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData().removeObserver(this);
//                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        //clusterList.add("Select Cluster");
//                        plotExistsOn.clear();
//                        plotExistsOnList.clear();
//                        plotExistsOnList = seasonTableList;
//
//                        if (seasonTableList != null && seasonTableList.size() > 0) {
//                            plotExistsOn.add("Select PLot Exists On Methods *");
//
//                            for (int i = 0; i < seasonTableList.size(); i++) {
//                                plotExistsOn.add(seasonTableList.get(i).getName());
//                                Log.e(":spacing", String.valueOf(seasonTableList.get(i).getId()));
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getName());
//                                Log.e(":dsvbjl_season", seasonTableList.get(i).getCode());
//                            }
//                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ReportedAreaActivity.this,
//                                    R.layout.spinner_dropdown_layout, plotExistsOn);
//                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
//                            spSelectSeason.setAdapter(dataAdapter);
//                            dataAdapter.notifyDataSetChanged();
//                        } else {
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No plot exists on list");
//                        }
//                    }
//                };
//                viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

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
                case R.id.edtReportedArea:
                    if(edtReportedArea.getText().toString().trim().length()>0) {

                        double reportedArea = Double.valueOf(edtReportedArea.getText().toString());
                        int MAX_ACRE_FOR_AGGREMENT = viewModel.getlockedkeyvalue("MAX_ACRE_FOR_AGGREMENT");
                        System.out.println("MAX_ACRE_FOR_AGGREMENT: " + MAX_ACRE_FOR_AGGREMENT);

                        if (reportedArea <= MAX_ACRE_FOR_AGGREMENT) { // Check if reportedArea is less than or equal to 5
                            if (plantTypePosition > 0) {
                                edtAgreedTon.setText(String.valueOf(reportedArea * Double.valueOf(plantTypeTableList.get(plantTypePosition - 1).getEstimatedTon())));
                            }
                        } else {
                            Toast.makeText(ReportedAreaActivity.this, "Entered Reported Area is Should be less than or equal to "+MAX_ACRE_FOR_AGGREMENT  + " Acres", Toast.LENGTH_SHORT).show();
                            return ;
                            // Handle the case where reportedArea is greater than 5, if needed
                            // You can display an error message or take appropriate action.
                        }
                    }
                    break;
                case R.id.edtOfferedNo:


                    if(edtOfferedNo.getText().toString().trim().length()>0){

                        getPlotOfferListDataFromLocalDBSearch(edtPlotVillageNo.getText().toString());
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
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReportedAreaActivity.this, ViewStatusPlotOfferReportedAreaListActivity.class);
        intent.putExtra("farmerCode",addPlotTable.getFarmerCode());
        startActivity(intent);
        finish();
    }
}