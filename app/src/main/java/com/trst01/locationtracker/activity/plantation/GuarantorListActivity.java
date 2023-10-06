package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;

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

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.farmerPlot.adapter.FarmerDetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerListActivity;
import com.trst01.locationtracker.activity.plantation.adapter.FarmerGuarantorsListAdapter;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class GuarantorListActivity extends BaseActivity implements HasSupportFragmentInjector,FarmerGuarantorsListAdapter.SyncCallbackInterface {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    FarmerGuarantorsListAdapter farmerDetailsListAdapter;

    RecyclerView rvFarmer;
    int indicator =8;
    SearchView svFarmerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarantor_list);

        rvFarmer = findViewById(R.id.rvFarmer);
        svFarmerName = findViewById(R.id.svFarmerName);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvFarmer.setLayoutManager(mLayoutManager);
        rvFarmer.setItemAnimator(new DefaultItemAnimator());
        rvFarmer.setNestedScrollingEnabled(false);
        rvFarmer.setHasFixedSize(true);
        ((SimpleItemAnimator) rvFarmer.getItemAnimator()).setSupportsChangeAnimations(false);

        configureDagger();
        configureViewModel();
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


    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getFarmerListDataFromLocalDB();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    @Override
    public void openScreenCallback(int position, AddFarmerTable farmerTable, List<AddFarmerTable> farmer, String applicationType) {
        Intent intent = new Intent();
        intent.putExtra("farmer", farmerTable.getCode());
        intent.putExtra("farmerName", farmerTable.getName());
        setResult(2, intent);
        finish();
    }

    @Override
    public void updateItemCallback(int position, AddFarmerTable applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, AddFarmerTable applicationStatusTable, String strFarmercode) {

    }

    public void getFarmerListDataFromLocalDB() {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getFarmerListFromLocalDBStatus("2022-23");
            } else {
                viewModel.getFarmerListFromLocalDBStatus(seasonCode);
            }
//            viewModel.getFarmerListFromLocalDBStatus("2022-23");
            if (viewModel.getFarmerDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddFarmerTable> seasonTableList = (List<AddFarmerTable>) o;
                        viewModel.getFarmerDetailsListLiveData().removeObserver(this);
                        //appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        //clusterList.add("Select Cluster");


                        if (seasonTableList != null && seasonTableList.size() > 0) {

                            farmerDetailsListAdapter = new FarmerGuarantorsListAdapter(GuarantorListActivity.this,
                                    seasonTableList, GuarantorListActivity.this, appHelper, viewModel,indicator);
                            rvFarmer.setAdapter(farmerDetailsListAdapter);
                            farmerDetailsListAdapter.notifyDataSetChanged();

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

}