package com.trst01.locationtracker.activity.complains;

import static com.trst01.locationtracker.constant.CommonUtils.isLocationPermissionGranted;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;


import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.DashBoardActivity;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.services.FaLogTracking.FalogService;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ComplainListsActivity extends ComplainListsBaseActivity implements View.OnClickListener, HasSupportFragmentInjector{
    private static final String TAG = ComplainListsActivity.class.getCanonicalName();
    ImageView img_cancel_complaints;
    RecyclerView rv_complain_lists;
    String strKeyValue;
    ComplainDetailsListAdapter complainDetailsListAdapter;
    List<SavingComplainImagesTable> savingComplainImagesTableList;
    Dialog adddialog, CropDialogue;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
//    public static String strDocFarmerCode = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_lists);

        // appHelper.getSharedPrefObj().edit().remove(strDocFarmerCode).apply();


        initializeUI();
        initializeValues();
        configureDagger();
        configureViewModel();
    }

    private void initializeUI() {
        //prgFarmerSpin = findViewById(R.id.spin_farmer);
        // img_search = findViewById(R.id.img_search);
        rv_complain_lists = (RecyclerView) findViewById(R.id.rv_complain_lists);
        img_cancel_complaints = findViewById(R.id.img_cancel_complaints);

//        EditText searchEditText = searchByName.findViewById(androidx.appcompat.R.id.search_src_text);
//        searchEditText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789"));
//        searchEditText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
//        searchEditText.setTextColor(getResources().getColor(R.color.black));
//        searchEditText.setHintTextColor(getResources().getColor(R.color.black));
//        searchEditText.setTextSize(20);
    }

    private void initializeValues() {
        // img_search.setOnClickListener(this);
        img_cancel_complaints.setOnClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_complain_lists.setLayoutManager(mLayoutManager);
        rv_complain_lists.setItemAnimator(new DefaultItemAnimator());
        rv_complain_lists.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) rv_complain_lists.getItemAnimator()).setSupportsChangeAnimations(false);
        //add complain details
    }

    private void configureDagger() {
        AndroidInjection.inject(ComplainListsActivity.this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(ComplainListsActivity.this, viewModelFactory).get(AppViewModel.class);
        getComplainImageslistFromLocalDb();
//        getComplainlistFromLocalDb();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ComplainListsActivity.this, DashBoardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ComplainListsActivity.this.overridePendingTransition(R.anim.left_to_right_anim, R.anim.right_to_left_anim);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_cancel_complaints:
                Intent intent = new Intent(ComplainListsActivity.this, DashBoardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }

    }

    public void getComplainlistFromLocalDb() {
        try {
            INSERT_LOG("getAddComplainlistFromLocalDb", "BEGIN");
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getFarmerListFromLocalDBStatus();
            if ((viewModel.getAddComplainformTableLiveDataFromLocalDBList() != null)) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddComplaintsDetailsTable> odVisitSurveyTableList_complain = (List<AddComplaintsDetailsTable>) o;
//                        viewModel.getAddComplainDataDetailsListNotSyncLiveData().removeObserver(this);
                        Log.d(TAG, "getComplainlistFromLocalDb: "+o);
                        INSERT_LOG("getComplainlistFromLocalDb", "END: "+o);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        if (odVisitSurveyTableList_complain != null && odVisitSurveyTableList_complain.size() > 0) {
//                            Collections.reverse(odVisitSurveyTableList_complain);
                            complainDetailsListAdapter = new ComplainDetailsListAdapter(ComplainListsActivity.this,
                                    odVisitSurveyTableList_complain, savingComplainImagesTableList, appHelper, viewModel);
                            rv_complain_lists.setAdapter(complainDetailsListAdapter);
                            complainDetailsListAdapter.notifyDataSetChanged();
                        }else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No complaints list");
                        }
                    }
                };
                viewModel.getAddComplainformTableLiveDataFromLocalDBList().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getFarmerlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }

    public void getComplainImageslistFromLocalDb() {
        try {
            INSERT_LOG("getAddComplainImageslistFromLocalDb", "BEGIN");
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getComplainImageslistsLocalDB();
            if ((viewModel.getSavingComplainImagesNotSyncLiveData() != null)) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        Log.d(TAG, "getComplainImageslistFromLocalDb: "+o);
                        List<SavingComplainImagesTable> odVisitSurveyTableList_complain_image = (List<SavingComplainImagesTable>) o;
                        viewModel.getSavingComplainImagesNotSyncLiveData().removeObserver(this);
                        INSERT_LOG("getComplainImagelistFromLocalDb", "END");

                        if (odVisitSurveyTableList_complain_image != null && odVisitSurveyTableList_complain_image.size() > 0) {
                           savingComplainImagesTableList = odVisitSurveyTableList_complain_image ;
                            getComplainlistFromLocalDb();
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        }else {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer list");
                        }
                    }
                };
                viewModel.getSavingComplainImagesNotSyncLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getComplainlistFromLocalDb", "Exception : " + ex.getMessage());
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        try {
//            searchByName.setQuery("", false);
//            searchByName.setIconified(true);
//            getComplainlistFromLocalDb();
//            if (appHelper.isNetworkAvailable()) {
//
//            } else {
//                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MESSAGE_NO_INTERNET_CONNECTION);
//            }
//            startService(new Intent(this, FalogService.class));
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

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onResume", "Exception : " + ex.getMessage());
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
    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, "userId", "", "ComplainListActivity"
                        , "BreederId", "", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }


}