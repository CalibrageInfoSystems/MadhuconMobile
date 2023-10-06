package com.trst01.locationtracker.activity.plantation;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotD20DetailsListAdapter;
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotOfferDetailsListAdapter;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.services.areacalculator.FieldCalculatorActivity;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewStatusPlotOfferListActivity extends BaseActivity implements HasSupportFragmentInjector, PlotOfferDetailsListAdapter.SyncCallbackInterface {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    PlotOfferDetailsListAdapter farmerDetailsListAdapter;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    RecyclerView rvFarmer;
    SearchView svFarmerName;
    TextView txtFarmerRegistration,txtAddPlotOffer;
    String farmerCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        configureDagger();
        configureViewModel();

        farmerCode = getIntent().getStringExtra("farmerCode");

        rvFarmer = findViewById(R.id.rvFarmer);
        svFarmerName = findViewById(R.id.svFarmerName);
        txtFarmerRegistration = findViewById(R.id.txtFarmerRegistration);
        txtAddPlotOffer = findViewById(R.id.txtAddPlotOffer);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvFarmer.setLayoutManager(mLayoutManager);
        rvFarmer.setItemAnimator(new DefaultItemAnimator());
        rvFarmer.setNestedScrollingEnabled(false);
        rvFarmer.setHasFixedSize(true);
        ((SimpleItemAnimator) rvFarmer.getItemAnimator()).setSupportsChangeAnimations(false);

        txtAddPlotOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStatusPlotOfferListActivity.this,PlotOfferActivity.class);
                intent.putExtra("farmerCode",farmerCode);
                startActivity(intent);
            }
        });

        searchView();


        txtFarmerRegistration.setText("Plot Offer");
        txtAddPlotOffer.setVisibility(View.VISIBLE);
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
//        getFarmerPLotListFromLocalDb("10");
        getPlotOfferListFromLocalDbCheckDBNotSync(getIntent().getStringExtra("farmerCode"));
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

    public void getPlotOfferListFromLocalDbCheckDBNotSync(String farmerCode) {
        try {
            viewModel.getPlotOfferListFromLocalDB(farmerCode);
            if (viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotOfferTable> addFertilizerDetailsTables = (List<AddPlotOfferTable>) o;
                        viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().removeObserver(this);


                        if (addFertilizerDetailsTables != null && addFertilizerDetailsTables.size() > 0) {

                            farmerDetailsListAdapter = new PlotOfferDetailsListAdapter(ViewStatusPlotOfferListActivity.this,
                                    addFertilizerDetailsTables, ViewStatusPlotOfferListActivity.this, appHelper, viewModel,2);
                            rvFarmer.setAdapter(farmerDetailsListAdapter);
                            farmerDetailsListAdapter.notifyDataSetChanged();
                        } else {

                        }


                    }

                };
                viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }



    @Override
    public void openScreenCallback(int position, AddPlotOfferTable farmerTable, List<AddPlotOfferTable> farmer, String applicationType) {
//        Intent intent = new Intent(ViewStatusPlotListActivity.this, AddPlantation.class);
//        intent.putExtra("data",farmerTable);
//        startActivity(intent);
//        Intent intent = new Intent(ViewStatusPlotOfferListActivity.this, FieldCalculatorActivity.class);
//        intent.putExtra("plot",farmerTable.getPlotNo());
//        intent.putExtra("seasonCode",farmerTable.getSeasonCode());

//        startActivity(intent);
    }

    @Override
    public void updateItemCallback(int position, AddPlotOfferTable applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, AddPlotOfferTable applicationStatusTable, String strFarmercode) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPlotOfferListFromLocalDbCheckDBNotSync(getIntent().getStringExtra("farmerCode"));
    }
}