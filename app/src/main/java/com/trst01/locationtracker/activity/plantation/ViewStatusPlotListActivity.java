package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.DashBoardActivity;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotD20AgreementedDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotD20DetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.plot.ViewPlotActivity;
import com.trst01.locationtracker.activity.growthMonitoring.ChooseGrowthMonitoringActivity;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.services.areacalculator.FieldCalculatorActivity;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewStatusPlotListActivity extends BaseActivity implements HasSupportFragmentInjector, PlotD20DetailsListAdapter.SyncCallbackInterface,PlotD20AgreementedDetailsListAdapter.SyncCallbackInterface {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    PlotD20DetailsListAdapter farmerDetailsListAdapter;
    PlotD20AgreementedDetailsListAdapter agreementedDetailsListAdapter;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    RecyclerView rvFarmer;
    SearchView svFarmerName;
    int indicator =0;
    TextView txtFarmerRegistration;
    String farmerCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        indicator= getIntent().getIntExtra("indicator",0);
        rvFarmer = findViewById(R.id.rvFarmer);
        svFarmerName = findViewById(R.id.svFarmerName);
        txtFarmerRegistration = findViewById(R.id.txtFarmerRegistration);

        farmerCode = getIntent().getStringExtra("farmerCode");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvFarmer.setLayoutManager(mLayoutManager);
        rvFarmer.setItemAnimator(new DefaultItemAnimator());
        rvFarmer.setNestedScrollingEnabled(false);
        rvFarmer.setHasFixedSize(true);
        ((SimpleItemAnimator) rvFarmer.getItemAnimator()).setSupportsChangeAnimations(false);

        searchView();
        configureDagger();
        configureViewModel();

    }

    private void searchView() {
        if(indicator==2){
            txtFarmerRegistration.setText("Doc-30 plots Agreemented");
//            txtFarmerRegistration.setText("Doc-20 plots Agreemented");
            SearchManager searchManager = (SearchManager) getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
            svFarmerName.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            svFarmerName.setMaxWidth(Integer.MAX_VALUE);
            // listening to search query text change

            svFarmerName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    if (agreementedDetailsListAdapter != null && agreementedDetailsListAdapter.getFilter() != null) {
                        agreementedDetailsListAdapter.getFilter().filter(query);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    // filter recycler view when text is changed
//                spinnerSortByDate.setSelection(0);
//                spinnerSortByLoanType.setSelection(0);
//                spinnerSortByInterested.setSelection(0);

                    if (agreementedDetailsListAdapter != null && agreementedDetailsListAdapter.getFilter() != null) {
                        agreementedDetailsListAdapter.getFilter().filter(query);
                    }
                    return false;
                }
            });

        }
        else {
            if(indicator==4){
                txtFarmerRegistration.setText("Plots");
            } else {
                txtFarmerRegistration.setText("Doc-20 plots(Measured)");
            }

            SearchManager searchManager = (SearchManager) getApplicationContext().getSystemService(Context.SEARCH_SERVICE);
            svFarmerName.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            svFarmerName.setMaxWidth(Integer.MAX_VALUE);
            // listening to search query text change

            svFarmerName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    if (farmerDetailsListAdapter != null && farmerDetailsListAdapter.getFilter() != null) {
                        farmerDetailsListAdapter.getFilter().filter(query);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    // filter recycler view when text is changed
//                spinnerSortByDate.setSelection(0);
//                spinnerSortByLoanType.setSelection(0);
//                spinnerSortByInterested.setSelection(0);

                    if (farmerDetailsListAdapter != null && farmerDetailsListAdapter.getFilter() != null) {
                        farmerDetailsListAdapter.getFilter().filter(query);
                    }
                    return false;
                }
            });


        }


    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        String status = "";
        if(indicator==2){//agreemented
            status="20";
            getAgreementedFarmerPLotListFromLocalDb(farmerCode);
        }
//        else if(indicator==2){//agreemented
//            status="20";
//            getAgreementedFarmerPLotListFromLocalDb(farmerCode);
//        }
        else {//measured
            status="10";
            getFarmerPLotListFromLocalDb(farmerCode);
//            getFarmerPLotListFromLocalDb(status);
        }

//        getFarmerPLotListFromLocalDb("10");
    }
    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
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

    public void getFarmerPLotListFromLocalDb(String farmerCode) {
        try {
            INSERT_LOG("getFarmerPLotListFromLocalDb", "BEGIN");
            //appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            final ProgressDialog  progressDialog = new ProgressDialog(RegisterdFarmerListActivity.this, R.style.AppCompatAlertDialogStyle);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("data loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.show();
            viewModel.getLandDetailsMeasureStageListFromLocalDb(farmerCode);
            if (viewModel.getD10DetailsListLiveData() != null) {
//            if (viewModel.getD20DetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddD10Table> odVisitSurveyTableList = (List<AddD10Table>) o;
//                        List<AddD20Table> odVisitSurveyTableList = (List<AddD20Table>) o;
                        viewModel.getD10DetailsListLiveData().removeObserver(this);
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
                            rvFarmer.setVisibility(View.VISIBLE);
                            farmerDetailsListAdapter = new PlotD20DetailsListAdapter(ViewStatusPlotListActivity.this,
                                    odVisitSurveyTableList, ViewStatusPlotListActivity.this, appHelper, viewModel,indicator);
                            rvFarmer.setAdapter(farmerDetailsListAdapter);
                            farmerDetailsListAdapter.notifyDataSetChanged();
                        } else {
                            // progressDialog.dismiss();
                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            rvFarmer.setVisibility(View.GONE);
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Plot list");
                        }
                    }
                };
                viewModel.getD10DetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //progressDialog.dismiss();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }

    public void getAgreementedFarmerPLotListFromLocalDb(String farmerCode) {
        try {
            INSERT_LOG("getFarmerPLotListFromLocalDb", "BEGIN");
            //appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            final ProgressDialog  progressDialog = new ProgressDialog(RegisterdFarmerListActivity.this, R.style.AppCompatAlertDialogStyle);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("data loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.show();
//            viewModel.getLandD20DetailsStageListFromLocalDb("2022-23",farmerCode);
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getLandD20DetailsStageListFromLocalDb("2022-23",farmerCode);
            } else {
                viewModel.getLandD20DetailsStageListFromLocalDb(seasonCode,farmerCode);
            }
            if (viewModel.getD20DetailsListLiveData() != null) {
//            if (viewModel.getD20DetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
//                        List<AddD10Table> odVisitSurveyTableList = (List<AddD10Table>) o;
                        List<AddD20Table> odVisitSurveyTableList = (List<AddD20Table>) o;
                        viewModel.getD20DetailsListLiveData().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {

                            agreementedDetailsListAdapter = new PlotD20AgreementedDetailsListAdapter(ViewStatusPlotListActivity.this,
                                    odVisitSurveyTableList, ViewStatusPlotListActivity.this, appHelper, viewModel);
                            rvFarmer.setAdapter(agreementedDetailsListAdapter);
                            agreementedDetailsListAdapter.notifyDataSetChanged();
                        } else {

                            agreementedDetailsListAdapter = new PlotD20AgreementedDetailsListAdapter(ViewStatusPlotListActivity.this,
                                    odVisitSurveyTableList, ViewStatusPlotListActivity.this, appHelper, viewModel);
                            rvFarmer.setAdapter(agreementedDetailsListAdapter);
                            agreementedDetailsListAdapter.notifyDataSetChanged();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Plot list");
                        }
                    }
                };
                viewModel.getD20DetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //progressDialog.dismiss();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }

    @Override
    public void openScreenCallback(int position, AddD10Table farmerTable, List<AddD10Table> farmer, String applicationType) {
//        Intent intent = new Intent(ViewStatusPlotListActivity.this, AddPlantation.class);
//        intent.putExtra("data",farmerTable);
//        startActivity(intent);
        if(indicator==3){
            if(farmerTable!=null){
                Intent intent = new Intent(ViewStatusPlotListActivity.this, ReportedAreaActivity.class);
                intent.putExtra("data",farmerTable);
                startActivity(intent);
            }
        }else if(indicator==2){
//            Intent intent = new Intent(ViewStatusPlotListActivity.this, AgreementedAreaActivity.class);
//        intent.putExtra("data",farmerTable);
//        startActivity(intent);
        } else if(indicator==4){
//            Intent intent = new Intent(ViewStatusPlotListActivity.this, AgreementedAreaActivity.class);
//        intent.putExtra("data",farmerTable);
//        startActivity(intent);
            Intent intent = new Intent(ViewStatusPlotListActivity.this, ChooseGrowthMonitoringActivity.class);
            intent.putExtra("plotNo",farmerTable.getPlotNo());
            intent.putExtra("farmerCode",farmerTable.getFarmerCode());
//                Intent intent = new Intent(DashBoardActivity.this, ReportedDetailsGrowthMonitoringActivity.class);
//                Intent intent = new Intent(DashBoardActivity.this, ChooseFarmerPlotActivity.class);
            startActivity(intent);
        } else {

            Log.e("===========plot Report created date",farmer.get(position).getCreatedDate() +farmer.get(position).getFarmerCode() );
            String inputDate = farmer.get(position).getCreatedDate();

            // Define a SimpleDateFormat for parsing the input date
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            // Get the current date
            Date currentDate = new Date();

            // Define a SimpleDateFormat for parsing the formatted date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate;
            Date date;

            try {
                date = inputFormat.parse(inputDate);

                // Define a SimpleDateFormat for formatting the date in the desired format
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Format the date as "yyyy-MM-dd"
                String formattedDate = outputFormat.format(date);

                System.out.println("Formatted Date: " + formattedDate);
                // Parse the formatted date string
                parsedDate = dateFormat.parse(formattedDate);

                // Calculate the time difference in milliseconds
              //  long timeDifferenceMillis = parsedDate.getTime() - currentDate.getTime();
                // Calculate the difference in days
                long differenceInMillis = parsedDate.getTime() - currentDate.getTime();
                int differenceInDays = (int) (differenceInMillis / (24 * 60 * 60 * 1000));

                // Convert the time difference to days

                System.out.println("Days Difference: " + differenceInDays);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        Intent intent = new Intent(ViewStatusPlotListActivity.this, FieldCalculatorActivity.class);
        intent.putExtra("plot",farmerTable.getPlotNo());
        intent.putExtra("seasonCode",farmerTable.getSeasonCode());
        intent.putExtra("estimatedTon",farmerTable.getEstimatedTon());
        startActivity(intent);
//        finish();
        }
    }

    @Override
    public void updateItemCallback(int position, AddD10Table applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, AddD10Table applicationStatusTable, String strFarmercode) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        String status = "";
        if(indicator==2){//agreemented
            status="20";
            getAgreementedFarmerPLotListFromLocalDb(farmerCode);
        } else {//measured
            status="10";
            getFarmerPLotListFromLocalDb(farmerCode);
//            getFarmerPLotListFromLocalDb(status);
        }
    }

    @Override
    public void openScreenCallback(int position, AddD20Table farmerTable, List<AddD20Table> farmer, String applicationType) {
        Intent intent = new Intent(ViewStatusPlotListActivity.this, AgreementedAreaActivity.class);
        intent.putExtra("data",farmerTable);
        startActivity(intent);
    }

    @Override
    public void updateItemCallback(int position, AddD20Table applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, AddD20Table applicationStatusTable, String strFarmercode) {

    }
}