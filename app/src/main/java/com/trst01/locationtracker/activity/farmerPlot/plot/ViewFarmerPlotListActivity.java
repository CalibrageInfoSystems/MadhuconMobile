package com.trst01.locationtracker.activity.farmerPlot.plot;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
import com.trst01.locationtracker.activity.farmerPlot.adapter.FarmerDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerActivity;
import com.trst01.locationtracker.activity.growthMonitoring.ChooseGrowthMonitoringActivity;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewFarmerPlotListActivity extends BaseActivity implements HasSupportFragmentInjector,PlotDetailsListAdapter.SyncCallbackInterface {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    PlotDetailsListAdapter farmerDetailsListAdapter;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    RecyclerView rvFarmer;
    SearchView svFarmerName;
    int indicator =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_farmer_list);

        indicator= getIntent().getIntExtra("indicator",0);

        configureDagger();
        configureViewModel();

        rvFarmer = findViewById(R.id.rvFarmer);
        svFarmerName = findViewById(R.id.svFarmerName);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvFarmer.setLayoutManager(mLayoutManager);
        rvFarmer.setItemAnimator(new DefaultItemAnimator());
        rvFarmer.setNestedScrollingEnabled(false);
        rvFarmer.setHasFixedSize(true);
        ((SimpleItemAnimator) rvFarmer.getItemAnimator()).setSupportsChangeAnimations(false);

        searchView();
    }

    private void searchView() {
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

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getFarmerPLotListFromLocalDb(getIntent().getStringExtra("farmerCode"));
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
            viewModel.getLandDetailsListFromLocalDb(farmerCode);
            if (viewModel.getFarmerPlotDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotTable> odVisitSurveyTableList = (List<AddPlotTable>) o;
                        viewModel.getFarmerPlotDetailsListLiveData().removeObserver(this);
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
                            farmerDetailsListAdapter = new PlotDetailsListAdapter(ViewFarmerPlotListActivity.this,
                                    odVisitSurveyTableList, ViewFarmerPlotListActivity.this, appHelper, viewModel);
                            rvFarmer.setAdapter(farmerDetailsListAdapter);
                            farmerDetailsListAdapter.notifyDataSetChanged();
                        } else {
                            // progressDialog.dismiss();
                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer Plot list");
                        }
                    }
                };
                viewModel.getFarmerPlotDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //progressDialog.dismiss();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }

    @Override
    public void openScreenCallback(int position, AddPlotTable farmerTable, List<AddPlotTable> farmer, String applicationType) {

        if(indicator!=4){
            Intent intent = new Intent(ViewFarmerPlotListActivity.this, ViewPlotActivity.class);
            intent.putExtra("data",farmerTable);
            startActivity(intent);
        } else {//chooseGrowth
            Intent intent = new Intent(ViewFarmerPlotListActivity.this, ChooseGrowthMonitoringActivity.class);
            intent.putExtra("plotNo",farmerTable.getPlotNo());
            intent.putExtra("farmerCode",farmerTable.getFarmerCode());
            intent.putExtra("stage",farmerTable.getStage());
            startActivity(intent);
        }
    }

    @Override
    public void updateItemCallback(int position, AddPlotTable applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, AddPlotTable applicationStatusTable, String strFarmercode) {

    }
}