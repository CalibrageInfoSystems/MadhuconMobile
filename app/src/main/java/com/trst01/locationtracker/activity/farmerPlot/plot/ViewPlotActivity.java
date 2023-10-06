package com.trst01.locationtracker.activity.farmerPlot.plot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.BaseActivity;
import com.trst01.locationtracker.activity.complains.ComplainFormActivity;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.CircleTable;
import com.trst01.locationtracker.database.entity.CropTable;
import com.trst01.locationtracker.database.entity.DivisionTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.MandalTable;
import com.trst01.locationtracker.database.entity.PlantTypeTable;
import com.trst01.locationtracker.database.entity.PlotExistOnTable;
import com.trst01.locationtracker.database.entity.SectionTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.view_models.AppViewModel;

import org.w3c.dom.Text;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ViewPlotActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;

    TextView txtPlotId,txtPlotArea,txtPattadharBookNo,txtSurveyNo,txtNominee,txtGuarantor1,txtGuarantor2,
            txtGuarantor3,txtPincode,txtVillage,txtMandal,txtDistrict,txtAddress,txtPlotType,txtSoilType,
            txtIrrigationType,txtPreviousCrop,txtInterCrop,txtLat,txtLong,txtSave,txtDivision,txtSection,txtCircle,txtPlantType,txtVariety;

    AddPlotTable addPlotTable;
    TextView txtComplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plot);
        addPlotTable = (AddPlotTable) getIntent().getSerializableExtra("data");
        configureDagger();
        configureViewModel();
        ui();

    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getVillageDetailsByVillageId(addPlotTable.getPlotVillageId());
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    private void ui() {
        txtComplain = findViewById(R.id.txtComplain);

        txtComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPlotActivity.this, ComplainFormActivity.class);
                intent.putExtra("from","Plot");
                intent.putExtra("strPlotNumber",addPlotTable.getPlotNo());
                intent.putExtra("strFarmerCode",addPlotTable.getFarmerCode());
                intent.putExtra("seasonCode",addPlotTable.getSeasonCode());
                startActivity(intent);
            }
        });

        txtVariety = findViewById(R.id.txtVariety);
        txtCircle = findViewById(R.id.txtCircle);
        txtDivision = findViewById(R.id.txtDivision);
        txtSection = findViewById(R.id.txtSection);
        txtPlantType = findViewById(R.id.txtPlantType);
//        txtDivision = findViewById(R.id.txtDivision);
        txtPlotId = findViewById(R.id.txtPlotId);
        txtPlotArea = findViewById(R.id.txtPlotArea);
        txtPattadharBookNo = findViewById(R.id.txtPattadharBookNo);
        txtSurveyNo = findViewById(R.id.txtSurveyNo);
        txtNominee = findViewById(R.id.txtNominee);
        txtGuarantor1 = findViewById(R.id.txtGuarantor1);
        txtGuarantor2 = findViewById(R.id.txtGuarantor2);
        txtGuarantor3 = findViewById(R.id.txtGuarantor3);
        txtPincode = findViewById(R.id.txtPincode);
        txtVillage = findViewById(R.id.txtVillage);
        txtMandal = findViewById(R.id.txtMandal);
        txtDistrict = findViewById(R.id.txtDistrict);
        txtAddress = findViewById(R.id.txtAddress);
        txtPlotType = findViewById(R.id.txtPlotType);
        txtSoilType = findViewById(R.id.txtSoilType);
        txtIrrigationType = findViewById(R.id.txtIrrigationType);
        txtPreviousCrop = findViewById(R.id.txtPreviousCrop);
        txtInterCrop = findViewById(R.id.txtInterCrop);
        txtLat = findViewById(R.id.txtLat);
        txtLong = findViewById(R.id.txtLong);
        txtSave = findViewById(R.id.txtSave);

        txtSave.setVisibility(View.GONE);

        txtPlotId.setText(addPlotTable.getPlotNo());
        txtPlotArea.setText(addPlotTable.getDemoPlotArea());
//        txtPattadharBookNo.setText(addPlotTable.get());
        txtSurveyNo.setText(addPlotTable.getSurveyNo());
        txtNominee.setText(addPlotTable.getNominee());
        txtGuarantor1.setText(addPlotTable.getGuarantor1());
        txtGuarantor2.setText(addPlotTable.getGuarantor2());
        txtGuarantor3.setText(addPlotTable.getGuarantor3());
//        txtPincode.setText(addPlotTable.getGuarantor3());
//        getVillageDetailsByVillageId(addPlotTable.getPlotVillageId());
//        getFarmerVillageDetailsByVillageId(addPlotTable.getPlotVillageId(),txtVillage);
//        txtVillage.setText(addPlotTable.getPlotVillageId());
//        txtMandal.setText(addPlotTable.getMa());
//        txtDistrict.setText(addPlotTable.getGuarantor3());
//        txtAddress.setText(addPlotTable.getAd());
//        txtPlotType.setText(addPlotTable.getPlotTypeId());
//        txtSoilType.setText(addPlotTable.getSoilTypeId());
//        txtIrrigationType.setText(addPlotTable.getIrrigationTypeId());
//        txtVariety.setText(addPlotTable.getVarietyId());
        getCropDetailsByCropId(addPlotTable.getPreviousCropId(),txtPreviousCrop);
        getCropDetailsByCropId(addPlotTable.getInterCropId(),txtInterCrop);
        getLookUpDtlDetailsById(addPlotTable.getPlotTypeId(),txtSoilType);
        getLookUpDtlDetailsById(addPlotTable.getSoilTypeId(),txtPlotType);
        getPlotExistOnDetailsById(addPlotTable.getPlotExistOnId(),txtIrrigationType);
        getVarietyById(addPlotTable.getVarietyId(),txtVariety);
        getPlantTypeDetailsByPlantId(addPlotTable.getPlantTypeId());

//        txtPreviousCrop.setText(addPlotTable.getPreviousCropId());
//        txtInterCrop.setText(addPlotTable.getInterCropId());
        txtLat.setText(addPlotTable.getLatitude());
        txtLong.setText(addPlotTable.getLongitude());

        if(addPlotTable.getStage().equals("10")){
//            stage = "Reported Area";
            txtPlotArea.setText(addPlotTable.getReportedArea());
        } else if(addPlotTable.getStage().equals("20")) {
//            stage = "Measured Area";
            txtPlotArea.setText(addPlotTable.getMeasureArea());
        } else if(addPlotTable.getStage().equals("30")) {
//            stage = "Agreemented Area";
            txtPlotArea.setText(addPlotTable.getAggrementedArea());
        } else if(addPlotTable.getStage().equals("40")) {
//            stage = "Net Area";
            txtPlotArea.setText(addPlotTable.getNetArea());
        }

//        txtDivision.setText(addPlotTable.getD);
//        txtSection.setText(addPlotTable.getse);
//        txtCircle.setText(addPlotTable.getcir);
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
//                            txtPincode.setText(villageTable.getPinCode());
                            txtVillage.setText(strPlotVillageName);
                            txtDistrict.setText(villageTable.getDistrictName());
                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
                            getMandalDetailsByVillageId(String.valueOf(villageTable.getSectionId()),txtMandal);
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

    public void getCropDetailsByCropId(String strPlotVillageId,TextView test) {
        try {
            viewModel.getCropDetailsByCropId(strPlotVillageId);
            if (viewModel.geCropListDetailsFromLocalDBByCropIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        CropTable villageTable = (CropTable) o;
                        viewModel.geCropListDetailsFromLocalDBByCropIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
//                           String strPlotMandalId = String.valueOf(villageTable.getMandalId());
                         String strPlotVillageName = villageTable.getName();

                            test.setText(strPlotVillageName);
//                            Log.d("TAG", strPlotVillageName + strPlotMandalId);

//                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
//                            getMandalDetailsByVillageId(String.valueOf(villageTable.getSectionId()),txtMandal);
//                            getMandalDetailsFromLocalDB(strPlotMandalId);
                        }

                    }


                };
                viewModel.geCropListDetailsFromLocalDBByCropIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getLookUpDtlDetailsById(String plantTypeId, TextView look) {
        try {
            viewModel.getLookupDtlDetailsById(plantTypeId);
            if (viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        LookupDtlTable villageTable = (LookupDtlTable) o;
                        viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            look.setText(villageTable.getName());
                        }

                    }


                };
                viewModel.getLookupDtlDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getPlotExistOnDetailsById(String plantTypeId, TextView look) {
        try {
            viewModel.getPlotExistOnDetailsById(plantTypeId);
            if (viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        PlotExistOnTable villageTable = (PlotExistOnTable) o;
                        viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            look.setText(villageTable.getName());
                        }

                    }


                };
                viewModel.getPlotExistOnDetailsFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getVarietyById(String varietyId, TextView look) {
        try {
            viewModel.getVarietyById(varietyId);
            if (viewModel.getVarietyFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VarietyTable villageTable = (VarietyTable) o;
                        viewModel.getVarietyFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            look.setText(villageTable.getName());
                        }

                    }


                };
                viewModel.getVarietyFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getIrrigationById(String varietyId, TextView look) {
        try {
            viewModel.getVarietyById(varietyId);
            if (viewModel.getVarietyFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VarietyTable villageTable = (VarietyTable) o;
                        viewModel.getVarietyFromLocalDBByIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            look.setText(villageTable.getName());
                        }

                    }

                };
                viewModel.getVarietyFromLocalDBByIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getPlantTypeDetailsByPlantId(String plantTypeId) {
        try {
            viewModel.getPlantTypeDetailsByPlantId(plantTypeId);
            if (viewModel.getPlantTypeDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        PlantTypeTable villageTable = (PlantTypeTable) o;
                        viewModel.getPlantTypeDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
//                        INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "END");
                        Log.d("TAG", "onChanged: values" + villageTable);
                        if (villageTable != null) {
                            txtPlantType.setText(villageTable.getName()
                            );
                        }

                    }


                };
                viewModel.getPlantTypeDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//            INSERT_LOG("getVillageDetailsFromLocalDBByVillageIdLiveData", "Exception : " + ex.getMessage());
        }
    }

    public void getFarmerVillageDetailsByVillageId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getVillageDetailsByVillageId(strVillageId);
            if (viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VillageTable villageTable = (VillageTable) o;
                        viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
                            getMandalDetailsByVillageId(String.valueOf(villageTable.getSectionId()),txtMandal);
                        }
                    }
                };
                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
//                            getSectionByVillageId(String.valueOf(villageTable.getSectionId()),txtSection);
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
                            getCircleBySectionId(String.valueOf(villageTable.getCircleId()), txtCircle);
                                getDivisionBySectionId(String.valueOf(villageTable.getDivisionalId()), txtDivision);
                        }
                    }
                };
                viewModel.getSectionDetailsFromLocalDBBySectionIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getCircleBySectionId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getCircleDetailsBySectionId(strVillageId);
            if (viewModel.getCircleDetailsFromLocalDBByCircleIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        CircleTable villageTable = (CircleTable) o;
                        viewModel.getCircleDetailsFromLocalDBByCircleIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getCircleDetailsFromLocalDBByCircleIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDivisionBySectionId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getDivisionDetailsBySectionId(strVillageId);
            if (viewModel.getDivisionDetailsFromLocalDBBySectionIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DivisionTable villageTable = (DivisionTable) o;
                        viewModel.getDivisionDetailsFromLocalDBBySectionIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
//                            getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getDivisionDetailsFromLocalDBBySectionIdLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}