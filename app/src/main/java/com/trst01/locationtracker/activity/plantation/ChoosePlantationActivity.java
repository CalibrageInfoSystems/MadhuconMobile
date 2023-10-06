package com.trst01.locationtracker.activity.plantation;

import static com.trst01.locationtracker.constant.AppConstant.SeasonCode;
import static com.trst01.locationtracker.constant.CommonUtils.isLocationPermissionGranted;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerListActivity;
import com.trst01.locationtracker.activity.plantation.adapter.SeasonsAdapter;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.services.FaLogTracking.FalogService;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ChoosePlantationActivity extends BaseActivity implements HasSupportFragmentInjector, SeasonsAdapter.SyncCallbackInterface {

    private static final String TAG = ChoosePlantationActivity.class.getCanonicalName();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

//    TextView txtMeasuredArea;
    CardView cardPlotOffer,cardMeasuredArea,cardPlantation,cvAgreementedArea,cvReportedArea;
    TextView txtSeason;
    Dialog adddialog,addAddressDialog;
    RecyclerView recAddressList;
    SeasonsAdapter seasonsAdapter;
    String seasonCode ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ui();

        ChoosePlantationActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure Dagger
                configureDagger();
            }
        });

        ChoosePlantationActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure View Model
                configureViewModel();
            }
        });

    }

    private void ui() {
        txtSeason = findViewById(R.id.txtSeason);
        cardPlotOffer = findViewById(R.id.cardPlotOffer);
        cardMeasuredArea = findViewById(R.id.cardMeasuredArea);
        cardPlantation = findViewById(R.id.cardPlantation);
        cvReportedArea = findViewById(R.id.cvReportedArea);
        cvAgreementedArea = findViewById(R.id.cvAgreementedArea);

        seasonCode = appHelper.getSharedPrefObj().getString(SeasonCode,"");

        if(!seasonCode.isEmpty()){
            txtSeason.setText("Season : "+seasonCode);
        } else {
            Toast.makeText(ChoosePlantationActivity.this, "please select season", Toast.LENGTH_SHORT).show();
        }

        click();
    }

    private void showAddressListData() {

        adddialog = new Dialog(ChoosePlantationActivity.this, R.style.MyAlertDialogThemeNew);
        adddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        adddialog.setContentView(R.layout.address_show_dialogue);
        adddialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);
        adddialog.setCanceledOnTouchOutside(true);
        adddialog.setCancelable(true);

        recAddressList = adddialog.findViewById(R.id.rec_address_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChoosePlantationActivity.this, LinearLayoutManager.VERTICAL, false);
        recAddressList.setLayoutManager(linearLayoutManager);
        recAddressList.setItemAnimator(new DefaultItemAnimator());
        recAddressList.setNestedScrollingEnabled(false);


//        Window window = adddialog.getWindow();
//        WindowManager.LayoutParams wlp = window.getAttributes();
//
//        wlp.gravity = Gravity.CENTER;
//        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        window.setAttributes(wlp);

        getSeasonListDataFromLocalDB();

        adddialog.show();

    }


    private void click() {
        txtSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressListData();
            }
        });
        cardPlotOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, PlotOfferActivity.class);
                Intent intent = new Intent(ChoosePlantationActivity.this, ViewFarmerListActivity.class);
                intent.putExtra("indicator",1);
                startActivity(intent);
            }
        });
        cardPlantation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });
        cvAgreementedArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, AgreementedAreaActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
                Intent intent = new Intent(ChoosePlantationActivity.this, ViewFarmerListPlantationActivity.class);
                intent.putExtra("indicator",3);
                startActivity(intent);
            }
        });

        cvReportedArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotOfferReportedAreaListActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ViewStatusPlotListActivity.class);
                Intent intent = new Intent(ChoosePlantationActivity.this, ViewFarmerListPlantationActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, ReportedAreaActivity.class);
//                Intent intent = new Intent(ChoosePlantationActivity.this, AddPlantation.class);
                intent.putExtra("indicator",1);
                startActivity(intent);
            }
        });

        cardMeasuredArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePlantationActivity.this, ViewFarmerListPlantationActivity.class);
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
//        getSeasonListDataFromLocalDB();
    }

    public void getSeasonListDataFromLocalDB() {
        try {
            //     appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getSeasonListForLogBookFromLocalDB();
            if (viewModel.getSeasonlistFromlocalDBLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<SeasonTable> seasonTableList = (List<SeasonTable>) o;
                        viewModel.getSeasonlistFromlocalDBLiveData().removeObserver(this);

                        if (seasonTableList != null && seasonTableList.size() > 0) {

                            seasonsAdapter = new SeasonsAdapter(ChoosePlantationActivity.this,
                                    seasonTableList, ChoosePlantationActivity.this,appHelper,viewModel,1);
                            recAddressList.setAdapter(seasonsAdapter);
                            seasonsAdapter.notifyDataSetChanged();

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



    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void openScreenCallback(int position, SeasonTable farmerTable, List<SeasonTable> farmer, String applicationType) {
        appHelper.getSharedPrefObj().edit().putString(SeasonCode,farmerTable.getCode()).apply();
        seasonCode = farmerTable.getCode();
        txtSeason.setText("Season : "+seasonCode);

        adddialog.dismiss();
    }

    @Override
    public void updateItemCallback(int position, SeasonTable applicationStatusTable, String strFarmerID) {

    }

    @Override
    public void addPlotDetailsCallback(int position, SeasonTable applicationStatusTable, String strFarmercode) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        startService(new Intent(this, FalogService.class));
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