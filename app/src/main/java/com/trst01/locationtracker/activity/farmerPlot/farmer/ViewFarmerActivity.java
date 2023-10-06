package com.trst01.locationtracker.activity.farmerPlot.farmer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.BankTable;
import com.trst01.locationtracker.database.entity.BranchTable;
import com.trst01.locationtracker.database.entity.CastTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.DistrictTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.StateTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.view_models.AppViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewFarmerActivity extends BaseActivity implements HasSupportFragmentInjector {

    TextView txtCode,txtFarmerName,txtAliasName,txtGender,txtCast,txtFatherName,txtPinCode,txtVillage,
            txtmandal,txtDistrict,txtState,txtAddress,txtMobile,txtEmail,txtPAN,txtAADHAR,txtBankAccount,
            txtReEnter,txtBankName,txtIFSCCode,txtSave;
    ImageView imgFarmer;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    AddFarmerTable addFarmerTable;
    TextView txtComplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_farmer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        addFarmerTable = (AddFarmerTable) getIntent().getSerializableExtra("data");
//        ui();
        configureDagger();
        configureViewModel();
        ui();
    }


    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }


    private void ui() {
//        txtComplain = findViewById(R.id.txtComplain);
//
//        txtComplain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewFarmerActivity.this, ComplainFormActivity.class);
//                intent.putExtra("from","Farmer");
//                intent.putExtra("plotNo","Farmer");
//                startActivity(intent);
//            }
//        });
        txtCode = findViewById(R.id.txtCodes);
        txtFarmerName = findViewById(R.id.txtFarmerName);
        txtAliasName = findViewById(R.id.txtAliasName);
        txtGender = findViewById(R.id.txtGender);
        txtCast = findViewById(R.id.txtCast);
        txtFatherName = findViewById(R.id.txtFatherName);
        txtPinCode = findViewById(R.id.txtPinCode);
        txtVillage = findViewById(R.id.txtVillage);
        txtmandal = findViewById(R.id.txtmandal);
        txtDistrict = findViewById(R.id.txtDistrict);
        txtState = findViewById(R.id.txtState);
        txtAddress = findViewById(R.id.txtAddress);
        txtMobile = findViewById(R.id.txtMobile);
        txtEmail = findViewById(R.id.txtEmail);
        txtPAN = findViewById(R.id.txtPAN);
        txtAADHAR = findViewById(R.id.txtAADHAR);
        txtBankAccount = findViewById(R.id.txtBankAccount);
        txtReEnter = findViewById(R.id.txtReEnter);
        txtBankName = findViewById(R.id.txtBankName);
        txtIFSCCode = findViewById(R.id.txtIFSCCode);
        txtSave = findViewById(R.id.txtSave);
        imgFarmer = findViewById(R.id.imgFarmer);

        txtSave.setVisibility(View.GONE);

        txtCode.setText(addFarmerTable.getCode());
        txtFarmerName.setText(addFarmerTable.getName());
        txtAliasName.setText(addFarmerTable.getAliasName());
        if(addFarmerTable.getGender().equals("M")){
            txtGender.setText("Male");
        } else {
            txtGender.setText("Female");
        }
//        txtGender.setText(addFarmerTable.getGender());
        txtCast.setText(addFarmerTable.getCastId());
        txtFatherName.setText(addFarmerTable.getFatherName());
//        txtPinCode.setText(addFarmerTable.getName());
//        txtVillage.setText(addFarmerTable.getVillageId());
        getVillageDetailsByVillageId(addFarmerTable.getVillageId());
        getCastDetailsByCastId(addFarmerTable.getCastId());
//        getDistrictBySectionId(String.valueOf(addFarmerTable.getVillageId()),txtDistrict);
//        txtmandal.setText(addFarmerTable.get());
//        txtDistrict.setText(addFarmerTable.());
//        txtState.setText(addFarmerTable.getS());
        getBankNameById(addFarmerTable.getBranchId(),txtBankName);
        getBranchNameById(addFarmerTable.getBranchId(),txtBankName);
        txtAddress.setText(addFarmerTable.getAddress());
        txtMobile.setText(addFarmerTable.getMobile());
        txtEmail.setText(addFarmerTable.getEmail());
        txtPAN.setText(addFarmerTable.getPanNo());
        txtAADHAR.setText(addFarmerTable.getAadharNo());
        txtBankAccount.setText(addFarmerTable.getACNo());
        txtReEnter.setText(addFarmerTable.getACNo());
        txtBankName.setText(addFarmerTable.getBranchId());
//        txtIFSCCode.setText(addFarmerTable.getIf());
        if(addFarmerTable.getImageUrl().length()>0){
            Picasso.get()
                    .load(addFarmerTable.getImageUrl())
                    .error(R.drawable.farmer_pic)
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .into(imgFarmer);
        } else {
            imgFarmer.setVisibility(View.GONE);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }


    public void getVillageDetailsByVillageId(String strPlotVillageId) {
        try {
            viewModel.getVillageDetailsByVillageId(strPlotVillageId);
            if (viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VillageTable villageTable = (VillageTable) o;
                        viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            String strPlotMandalId = String.valueOf(villageTable.getMandalId());
                            String strPlotVillageName = villageTable.getName();
//                            txtPinCode.setText(villageTable.getPinCode());
                            txtVillage.setText(strPlotVillageName);
                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

                            txtDistrict.setText(villageTable.getDistrictName());
                            txtmandal.setText(villageTable.getMandalName());
//                            txtState.setText(villageTable.getSt());
//                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
//                            getDistrictBySectionId(String.valueOf(addFarmerTable.getVillageId()),txtDistrict);
//                            getMandalDetailsByVillageId(String.valueOf(villageTable.getMandalId()),txtmandal);
//                            getMandalDetailsByVillageId(String.valueOf(villageTable.getSectionId()),txtmandal);
//                            getMandalDetailsFromLocalDB(strPlotMandalId);
                        }

                    }


                };
                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getCastDetailsByCastId(String strPlotVillageId) {
        try {
            viewModel.getCastDetailsByCastId(strPlotVillageId);
            if (viewModel.getCastDetailsFromLocalDBByCastIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        CastTable villageTable = (CastTable) o;
                        viewModel.getCastDetailsFromLocalDBByCastIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
//                            String strPlotMandalId = String.valueOf(villageTable.getMandalId());
//                            String strPlotVillageName = villageTable.getName();
//                            txtPinCode.setText(villageTable.getPinCode());
//                            txtVillage.setText(strPlotVillageName);
//                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

                            txtCast.setText(villageTable.getName());
                        }

                    }


                };
                viewModel.getCastDetailsFromLocalDBByCastIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getBankNameById(String bankId, TextView txtID) {
        try {
            viewModel.getBankDetailsById(bankId);
            if (viewModel.getBankDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        BankTable villageTable = (BankTable) o;
                        viewModel.getBankDetailsFromLocalDBByIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            txtID.setText(villageTable.getName());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getBankDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getBranchNameById(String branchID, TextView txtID) {
        try {
            viewModel.getBranchDetailsById(branchID);
            if (viewModel.getBranchDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        BranchTable villageTable = (BranchTable) o;
                        viewModel.getBranchDetailsFromLocalDBByIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            txtID.setText(villageTable.getName());
                            txtIFSCCode.setText(villageTable.getIFSC());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getBranchDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getStateNameById(String branchID, TextView txtID) {
        try {
            viewModel.getBranchDetailsById(branchID);
            if (viewModel.getBranchDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        StateTable villageTable = (StateTable) o;
                        viewModel.getBranchDetailsFromLocalDBByIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            txtID.setText(villageTable.getName());
//                            txtIFSCCode.setText(villageTable.getIFSC());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getBranchDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void getFarmerVillageDetailsByVillageId(String strVillageId, TextView tvFarmerVillage) {
//        try {
//            viewModel.getVillageDetailsByVillageId(strVillageId);
//            if (viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        VillageTable villageTable = (VillageTable) o;
//                        viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
//                        if (villageTable != null) {
//                            tvFarmerVillage.setText(villageTable.getName());
////                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
//                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
//                            getMandalDetailsByVillageId(String.valueOf(villageTable.getSectionId()),txtMandal);
//                        }
//                    }
//                };
//                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void getMandalDetailsByVillageId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getMandalDetailsByVillageId(strVillageId);
            if (viewModel.getMandalDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        MandalTable villageTable = (MandalTable) o;
                        viewModel.getMandalDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
//                            getDistrictBySectionId((villageTable.getDistrictId()),txtDistrict);
                        }
                    }
                };
                viewModel.getMandalDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getSectionByVillageId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getSectionDetailsBySectionId(strVillageId);
            if (viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        SectionTable villageTable = (SectionTable) o;
                        viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getCircleBySectionId(String.valueOf(villageTable.getCircleId()), txtCircle);
//                            getDivisionBySectionId(String.valueOf(villageTable.getCircleId()), txtDivision);
                        }
                    }
                };
                viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDistrictByVillageId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getSectionDetailsBySectionId(strVillageId);
            if (viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        SectionTable villageTable = (SectionTable) o;
                        viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getCircleBySectionId(String.valueOf(villageTable.getCircleId()), txtCircle);
//                            getDivisionBySectionId(String.valueOf(villageTable.getCircleId()), txtDivision);
                        }
                    }
                };
                viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDistrictBySectionId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getDistrictDetailsBySectionId(strVillageId);
            if (viewModel.getDistrictDetailsFromLocalDBBySectionIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DistrictTable villageTable = (DistrictTable) o;
                        viewModel.getDistrictDetailsFromLocalDBBySectionIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getDistrictDetailsFromLocalDBBySectionIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


//    public void getCircleBySectionId(String strVillageId, TextView tvFarmerVillage) {
//        try {
//            viewModel.getCircleDetailsBySectionId(strVillageId);
//            if (viewModel.getCircleDetailsFromLocalDBByCircleIdLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        CircleTable villageTable = (CircleTable) o;
//                        viewModel.getCircleDetailsFromLocalDBByCircleIdLiveData().removeObserver(this);
//                        if (villageTable != null) {
//                            tvFarmerVillage.setText(villageTable.getName());
////                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
//                        }
//                    }
//                };
//                viewModel.getCircleDetailsFromLocalDBByCircleIdLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

//    public void getDivisionBySectionId(String strVillageId, TextView tvFarmerVillage) {
//        try {
//            viewModel.getDivisionDetailsBySectionId(strVillageId);
//            if (viewModel.getDivisionDetailsFromLocalDBBySectionIdLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        DivisionTable villageTable = (DivisionTable) o;
//                        viewModel.getDivisionDetailsFromLocalDBBySectionIdLiveData().removeObserver(this);
//                        if (villageTable != null) {
//                            tvFarmerVillage.setText(villageTable.getName());
////                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
//                        }
//                    }
//                };
//                viewModel.getDivisionDetailsFromLocalDBBySectionIdLiveData().observe(this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}