package com.trst01.locationtracker.activity.growthMonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerListActivity;
import com.trst01.locationtracker.activity.farmerPlot.plot.ViewFarmerPlotListActivity;
import com.trst01.locationtracker.activity.plantation.ViewFarmerListPlantationActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotListActivity;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ChooseGrowthMonitoringActivity extends BaseActivity implements HasSupportFragmentInjector {

    private static final String TAG = ChooseGrowthMonitoringActivity.class.getCanonicalName();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

//    TextView txtMeasuredArea;
    CardView cvNetArea,cardMeasuredArea,cvAgreementedArea,cvReportedArea,cardPests,cvWeedicide,cvBioFertilizers,cvDiseases;
//    CardView cardPlantation;
    String plotNo="";
    String farmerCode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_growth_monitoring);

        plotNo = getIntent().getStringExtra("plotNo");
        farmerCode = getIntent().getStringExtra("farmerCode");

        ui();

        ChooseGrowthMonitoringActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure Dagger
                configureDagger();
            }
        });

        ChooseGrowthMonitoringActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure View Model
                configureViewModel();
            }
        });

    }

    private void ui() {
        cvNetArea = findViewById(R.id.cvNetArea);
        cardMeasuredArea = findViewById(R.id.cardMeasuredArea);
//        cardPlantation = findViewById(R.id.cardPlantation);
        cvReportedArea = findViewById(R.id.cvReportedArea);
        cvAgreementedArea = findViewById(R.id.cvAgreementedArea);
        cardPests = findViewById(R.id.cardPests);
        cvWeedicide = findViewById(R.id.cvWeedicide);
        cvBioFertilizers = findViewById(R.id.cvBioFertilizers);
        cvDiseases = findViewById(R.id.cvDiseases);

        String stage = getIntent().getStringExtra("stage");
        if(!stage.isEmpty()){
            if(stage.equals("40")){

            } else if(stage.equals("30")){
                cvNetArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvNetArea.setEnabled(false);
            } else if(stage.equals("20")){

                cvAgreementedArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvAgreementedArea.setEnabled(false);
                cvNetArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvNetArea.setEnabled(false);
            } else if(stage.equals("10")){

                cardMeasuredArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cardMeasuredArea.setEnabled(false);
                cvAgreementedArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvAgreementedArea.setEnabled(false);
                cvNetArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvNetArea.setEnabled(false);
            } else {
                cvReportedArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvReportedArea.setEnabled(false);
                cardMeasuredArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cardMeasuredArea.setEnabled(false);
                cvAgreementedArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvAgreementedArea.setEnabled(false);
                cvNetArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                cvNetArea.setEnabled(false);
            }
        }

        click();
    }

    private void click() {
        cvWeedicide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, PlotOfferActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, WeedicideActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });
        cvBioFertilizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, PlotOfferActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, BioFertilizerActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });
        cvDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, PlotOfferActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, DiseaseActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });
        cardPests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, PlotOfferActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, PestsActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });

        cvNetArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, PlotOfferActivity.class);
//                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, ViewFarmerListActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, NetAreaDetailsGrowthMonitoringActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });
//        cardPlantation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, ViewStatusPlotListActivity.class);
////                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
//                intent.putExtra("indicator",2);
//                startActivity(intent);
//            }
//        });
        cvAgreementedArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, AgreementedAreaActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
//                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, ViewFarmerListPlantationActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, AgreementedDetailsGrowthMonitoringActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
                intent.putExtra("indicator",3);
                startActivity(intent);
            }
        });

        cvReportedArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotOfferReportedAreaListActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
//                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, ViewFarmerListPlantationActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, ReportedDetailsGrowthMonitoringActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ReportedAreaActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
                intent.putExtra("indicator",1);
                startActivity(intent);
            }
        });

        cardMeasuredArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, ViewFarmerListPlantationActivity.class);
                Intent intent = new Intent(ChooseGrowthMonitoringActivity.this, MeasuredDetailsGrowthMonitoringActivity.class);
                intent.putExtra("plotNo",plotNo);
                intent.putExtra("farmerCode",farmerCode);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
                intent.putExtra("indicator",2);
                startActivity(intent);
//                Intent intent = new Intent(DashBoardActivity.this, ViewFarmerActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewFarmerListActivity.class);
//                intent.putExtra("indicator",1);
//                startActivity(intent);

            }
        });

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
//        getFarmerPLotListFromLocalDb("20",farmerCode,plotNo);
//        getFarmerPLotListFromLocalDb30("30",farmerCode,plotNo);
//        getFarmerPLotListFromLocalDb40("40",farmerCode,plotNo);
    }

    public void getFarmerPLotListFromLocalDb(String status,String farmerCode,String plotCode) {
        try {
            viewModel.getLandDetailsStageListFromLocalDb(status,farmerCode,plotCode);
            if (viewModel.getFarmerPlotDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotTable> odVisitSurveyTableList = (List<AddPlotTable>) o;
                        viewModel.getFarmerPlotDetailsListLiveData().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {

                        } else {
                            cardMeasuredArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                            cardMeasuredArea.setEnabled(false);
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer Plot list");
                        }
                    }
                };
                viewModel.getFarmerPlotDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

                        } else {
                            cvAgreementedArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                            cvAgreementedArea.setEnabled(false);
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer Plot list");
                        }
                    }
                };
                viewModel.getFarmerPlotDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFarmerPLotListFromLocalDb40(String status,String farmerCode,String plotCode) {
        try {
            viewModel.getLandDetailsStageListFromLocalDb(status,farmerCode,plotCode);
            if (viewModel.getFarmerPlotDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotTable> odVisitSurveyTableList = (List<AddPlotTable>) o;
                        viewModel.getFarmerPlotDetailsListLiveData().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {

                        } else {
                            cvNetArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
                            cvNetArea.setEnabled(false);
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer Plot list");
                        }
                    }
                };
                viewModel.getFarmerPlotDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}