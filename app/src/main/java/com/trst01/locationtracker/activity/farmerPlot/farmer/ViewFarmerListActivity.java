package com.trst01.locationtracker.activity.farmerPlot.farmer;

import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;
import static com.trst01.locationtracker.constant.CommonUtils.isLocationPermissionGranted;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.app.ActivityManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.farmerPlot.adapter.FarmerDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.plot.ViewFarmerPlotListActivity;
import com.trst01.locationtracker.activity.plantation.ViewStatusPlotOfferListActivity;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.services.FaLogTracking.FalogService;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewFarmerListActivity extends BaseActivity implements HasSupportFragmentInjector,FarmerDetailsListAdapter.SyncCallbackInterface {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    FarmerDetailsListAdapter farmerDetailsListAdapter;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    RecyclerView rvFarmer;
    int indicator =0;
    SearchView svFarmerName;
    String seasonCode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_farmer_list);

        indicator = getIntent().getIntExtra("indicator",0);

        rvFarmer = findViewById(R.id.rvFarmer);
        svFarmerName = findViewById(R.id.svFarmerName);

        seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");

        if(seasonCode.isEmpty()){
            seasonCode="2022-23";
        }

        configureDagger();
        configureViewModel();

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
        getFarmerlistFromLocalDb();
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



    public void getFarmerlistFromLocalDb() {
        try {
            INSERT_LOG("getFarmerlistFromLocalDb", "BEGIN");
            //appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            final ProgressDialog  progressDialog = new ProgressDialog(RegisterdFarmerListActivity.this, R.style.AppCompatAlertDialogStyle);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("data loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.show();
            if(indicator==0||indicator==1){
                viewModel.getFarmerListFromLocalDBStatus();
            } else {
                viewModel.getFarmerListFromLocalDBStatus(seasonCode);
//                viewModel.getFarmerListFromLocalDBStatus("2022-23");
            }

//            viewModel.getFarmerListFromLocalDBStatus("2022-23");
            if (viewModel.getFarmerDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddFarmerTable> odVisitSurveyTableList = (List<AddFarmerTable>) o;
                        viewModel.getFarmerDetailsListLiveData().removeObserver(this);
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
                            farmerDetailsListAdapter = new FarmerDetailsListAdapter(ViewFarmerListActivity.this,
                                    odVisitSurveyTableList, ViewFarmerListActivity.this, appHelper, viewModel,indicator);
                            rvFarmer.setAdapter(farmerDetailsListAdapter);
                            farmerDetailsListAdapter.notifyDataSetChanged();
                        } else {
                            // progressDialog.dismiss();
                            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer list");
                        }
                    }
                };
                viewModel.getFarmerDetailsListLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //progressDialog.dismiss();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }

    @Override
    public void openScreenCallback(int position, AddFarmerTable farmerTable, List<AddFarmerTable> farmer, String applicationType) {
        if(indicator==0){
            Intent intent = new Intent(ViewFarmerListActivity.this,ViewFarmerActivity.class);
            intent.putExtra("data",farmerTable);
            startActivity(intent);
        }
//        else {
//            Intent intent = new Intent(ViewFarmerListActivity.this, ViewFarmerPlotListActivity.class);
//            intent.putExtra("farmerCode",farmerTable.getCode());
//            startActivity(intent);
//        }

    }

    @Override
    public void updateItemCallback(int position, AddFarmerTable farmerTable, String strFarmerID) {
        if(indicator==0){
            Intent intent = new Intent(ViewFarmerListActivity.this, ViewFarmerPlotListActivity.class);
            intent.putExtra("farmerCode",farmerTable.getCode());
            startActivity(intent);
        } else {
            Intent intent = new Intent(ViewFarmerListActivity.this, ViewStatusPlotOfferListActivity.class);
            intent.putExtra("farmerCode",farmerTable.getCode());
            startActivity(intent);
        }

    }

    @Override
    public void addPlotDetailsCallback(int position, AddFarmerTable applicationStatusTable, String strFarmercode) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        svFarmerName.setQuery("", false);
        svFarmerName.setIconified(true);
        if(!isMyServiceRunning(FalogService.class)){
            if (isLocationPermissionGranted(this) ) {
                try {
                    startService(new Intent(this, FalogService.class));
                } catch (Exception ex){

                }

            }
//            Toast.makeText(this, "Service has started", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "Service is running", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}