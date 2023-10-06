package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;

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
import com.trst01.locationtracker.activity.farmerPlot.adapter.PlotOfferDetailsListAdapter;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewStatusPlotOfferReportedAreaListActivity extends BaseActivity implements HasSupportFragmentInjector, PlotOfferDetailsListAdapter.SyncCallbackInterface {

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

    // in this activity we need to show list which contains farmers in plotOffer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

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

        txtAddPlotOffer.setVisibility(View.GONE);

//        txtAddPlotOffer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewStatusPlotOfferReportedAreaListActivity.this,PlotOfferActivity.class);
//                intent.putExtra("farmerCode",farmerCode);
//                startActivity(intent);
//            }
//        });

        searchView();
        configureDagger();
        configureViewModel();

//        txtFarmerRegistration.setText("Plot Offer");
//        txtAddPlotOffer.setVisibility(View.VISIBLE);
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
//        getPlotOfferListFromLocalDbCheckDBNotSync(getIntent().getStringExtra("farmerCode"));
//        getFarmerPLotListFromLocalDb(getIntent().getStringExtra("farmerCode"));
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

//    public void getPlotOfferListFromLocalDbCheckDBNotSync(String farmerCode, List<AddPlotOfferTable> odVisitSurveyTableList) {
    public void getPlotOfferListFromLocalDbCheckDBNotSync(String farmerCode) {
        try {
//            viewModel.getPlotOfferReportedListD10("2022-23","N27609","1");
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getPlotOfferReportedListD10("2022-23",farmerCode,"1");
            } else {
                viewModel.getPlotOfferReportedListD10(seasonCode,farmerCode,"1");
            }
//            viewModel.getPlotOfferReportedListD10("2022-23",farmerCode,"1");
//            viewModel.getPlotOfferListD10("2022-23",farmerCode,"1");
            if (viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotOfferTable> addFertilizerDetailsTables = (List<AddPlotOfferTable>) o;
                        viewModel.getFertilizerDetailsTableDetailsListNotSyncLiveData().removeObserver(this);


                        if (addFertilizerDetailsTables != null && addFertilizerDetailsTables.size() > 0) {
//                            List<AddPlotOfferTable> addPlotOfferTables = new ArrayList<>();
//                            addPlotOfferTables=addFertilizerDetailsTables;
                            ArrayList<Integer> test = new ArrayList<>();
                            farmerDetailsListAdapter = new PlotOfferDetailsListAdapter(ViewStatusPlotOfferReportedAreaListActivity.this,
                                    addFertilizerDetailsTables, ViewStatusPlotOfferReportedAreaListActivity.this, appHelper, viewModel,2);
                            rvFarmer.setAdapter(farmerDetailsListAdapter);
                            farmerDetailsListAdapter.notifyDataSetChanged();
//                            if(!odVisitSurveyTableList.isEmpty()){
//                                for(int i =0;i<odVisitSurveyTableList.size();i++){
//                                    for(int j=0;j<addFertilizerDetailsTables.size();j++){
////                                        if(String.valueOf(odVisitSurveyTableList.get(i).getOfferedNo()).equals(String.valueOf(addFertilizerDetailsTables.get(j).getOfferNo()))){
//                                        //could have equated with int but null can be stored...
//                                        if(String.valueOf(odVisitSurveyTableList.get(i).getOfferNo()).equals(String.valueOf(addFertilizerDetailsTables.get(j).getOfferNo()))){
//                                            test.add(j);
//                                            break;
////                                            test.add(i);
//                                        }
//                                    }
//                                }
//                                ArrayList<Integer> testNew = new ArrayList<>();
//                               testNew= removeDuplicates(test);
//
////                                if(testNew.size()>0){
////                                    for(int i=testNew.size()-1;i>=0;i--){
////                                        addPlotOfferTables.remove(i);
////                                    }
////                                }
////                                farmerDetailsListAdapter = new PlotOfferDetailsListAdapter(ViewStatusPlotOfferReportedAreaListActivity.this, addFertilizerDetailsTables, ViewStatusPlotOfferReportedAreaListActivity.this, appHelper, viewModel,2);
//                                farmerDetailsListAdapter = new PlotOfferDetailsListAdapter(ViewStatusPlotOfferReportedAreaListActivity.this,
//                                        addPlotOfferTables, ViewStatusPlotOfferReportedAreaListActivity.this, appHelper, viewModel,2);
//                                rvFarmer.setAdapter(farmerDetailsListAdapter);
//                                farmerDetailsListAdapter.notifyDataSetChanged();
//                            } else{
//                                farmerDetailsListAdapter = new PlotOfferDetailsListAdapter(ViewStatusPlotOfferReportedAreaListActivity.this,
//                                        addFertilizerDetailsTables, ViewStatusPlotOfferReportedAreaListActivity.this, appHelper, viewModel,2);
//                                rvFarmer.setAdapter(farmerDetailsListAdapter);
//                                farmerDetailsListAdapter.notifyDataSetChanged();
//                            }


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

    // Function to remove duplicates from an ArrayList
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
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
//            viewModel.getD10ListByStatusSeasonCode("2022-23",farmerCode);
            String seasonCode= appHelper.getSharedPrefObj().getString(SeasonCode,"");
            if(seasonCode.isEmpty()){
                viewModel.getD10ListByStatusSeasonCode("2022-23",farmerCode);
            } else {
                viewModel.getD10ListByStatusSeasonCode(seasonCode,farmerCode);
            }
//            viewModel.getLandDetailsMeasureStageListFromLocalDb(farmerCode);
            if (viewModel.getD10DetailsListLiveData() != null) {
//            if (viewModel.getD20DetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddD10Table> odVisitSurveyTableList = (List<AddD10Table>) o;
//                        List<AddD20Table> odVisitSurveyTableList = (List<AddD20Table>) o;
                        viewModel.getD10DetailsListLiveData().removeObserver(this);
//                        getPlotOfferListFromLocalDbCheckDBNotSync(getIntent().getStringExtra("farmerCode"),odVisitSurveyTableList);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {

                        } else {

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

    @Override
    public void openScreenCallback(int position, AddPlotOfferTable farmerTable, List<AddPlotOfferTable> farmer, String applicationType) {
//        Intent intent = new Intent(ViewStatusPlotListActivity.this, AddPlantation.class);
//        intent.putExtra("data",farmerTable);
//        startActivity(intent);
//        Intent intent = new Intent(ViewStatusPlotOfferListActivity.this, FieldCalculatorActivity.class);
//        intent.putExtra("plot",farmerTable.getPlotNo());
//        intent.putExtra("seasonCode",farmerTable.getSeasonCode());

//        startActivity(intent);
        Intent intent = new Intent(ViewStatusPlotOfferReportedAreaListActivity.this, ReportedAreaActivity.class);
        intent.putExtra("data",farmerTable);
        startActivity(intent);
        finish();
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
//        getPlotOfferListFromLocalDbCheckDBNotSync(farmerCode, odVisitSurveyTableList);
//        getFarmerPLotListFromLocalDb(farmerCode);
        getPlotOfferListFromLocalDbCheckDBNotSync(farmerCode);

    }
}