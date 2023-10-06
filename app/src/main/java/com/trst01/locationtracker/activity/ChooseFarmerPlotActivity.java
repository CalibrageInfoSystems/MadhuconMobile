package com.trst01.locationtracker.activity;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static com.trst01.locationtracker.constant.AppConstant.MESSAGE_NO_INTERNET_CONNECTION;
import static com.trst01.locationtracker.uiLibrary.helpers.AppConstants.DATE_FORMAT_YYYY_MM_DD;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.farmerPlot.farmer.ViewFarmerListActivity;
import com.trst01.locationtracker.dagger.App;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddGeoBoundriesTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.ParameterTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.PlantSubTypeTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.SampleSlabTable;
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.UOMTable;
import com.trst01.locationtracker.database.entity.UsersTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WarehouseTable;
import com.trst01.locationtracker.database.entity.WeedTable;
import com.trst01.locationtracker.models.MastersResponseDTO;
import com.trst01.locationtracker.models.TransactionSyncResponseDTO;
import com.trst01.locationtracker.repositories.Retrofit_funtion_class;
import com.trst01.locationtracker.services.api.AppAPI;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseFarmerPlotActivity extends BaseActivity  implements HasSupportFragmentInjector {

    private static final String TAG = ChooseFarmerPlotActivity.class.getCanonicalName();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;


    TextView txtFramer,txtPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ui();


        ChooseFarmerPlotActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure Dagger
                configureDagger();
            }
        });

        ChooseFarmerPlotActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: Configure View Model
                configureViewModel();
            }
        });

    }

    private void ui() {
        txtFramer = findViewById(R.id.txtFramer);
        txtPlot = findViewById(R.id.txtPlot);

        click();
    }

    private void click() {
        txtPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFarmerPlotActivity.this, ViewFarmerListActivity.class);
                intent.putExtra("indicator",2);
                startActivity(intent);
            }
        });

        txtFramer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DashBoardActivity.this, ViewFarmerActivity.class);
                Intent intent = new Intent(ChooseFarmerPlotActivity.this, ViewFarmerListActivity.class);
                intent.putExtra("indicator",1);
                startActivity(intent);
            }
        });

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }
}