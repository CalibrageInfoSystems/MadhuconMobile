package com.trst01.locationtracker.activity.complains;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.LookUpDropDownDataTable;
import com.trst01.locationtracker.database.entity.LookupDtlTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ComplainListDetailsActivity extends ComplainListsBaseActivity implements  HasSupportFragmentInjector , View.OnClickListener {

    private static final String TAG = "ComplainListDetailsActi";
    TextView tv_complain_id,tv_logbook_no,tv_complain_type,tv_complain_status,tv_comments;
    ImageView imageview_complain_first,imageview_complain_second;
    ImageView img_cancel_complain_details;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    List<SavingComplainImagesTable> savingComplainImagesTableList_filter;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list_details);

        initView();
        configureDagger();
        configureViewModel();
        setValues();
    }

    private void initView() {
        img_cancel_complain_details = findViewById(R.id.img_cancel_complain_details);
        tv_complain_id = findViewById(R.id.tv_complain_id);
        tv_logbook_no = findViewById(R.id.tv_logbook_no);
        tv_complain_type = findViewById(R.id.tv_complain_type);
        tv_complain_status = findViewById(R.id.tv_complain_status);
        tv_comments = findViewById(R.id.tv_comments);
        imageview_complain_first = findViewById(R.id.imageview_complain_first);
        imageview_complain_second = findViewById(R.id.imageview_complain_second);
    }

    private void setValues() {
        try {
            Intent i = getIntent();
            AddComplaintsDetailsTable addComplaintsDetailsTable = (AddComplaintsDetailsTable)i.getSerializableExtra("COMPLAIN_DETAILS");
            SavingComplainImagesTable savingComplainImagesTable = (SavingComplainImagesTable)i.getSerializableExtra("COMPLAIN_IMAGE_DETAILS");
            savingComplainImagesTableList_filter = (List<SavingComplainImagesTable>)i.getSerializableExtra("COMPLAIN_IMAGE_DETAILS_FILTER");
//            SavingComplainImagesTable savingComplainImagesTable_second = (SavingComplainImagesTable)i.getSerializableExtra("COMPLAIN_IMAGE_DETAILS_SECOND");
            Log.d(TAG, "setValues: AddComplaintsDetailsTable>>"+addComplaintsDetailsTable);
            Log.d(TAG, "setValues: savingComplainImagesTable>>"+savingComplainImagesTable);
//            Log.d(TAG, "setValues: savingComplainImagesTable_second>>"+savingComplainImagesTable_second);


            tv_complain_id.setText(addComplaintsDetailsTable.getCode());
            tv_logbook_no.setText(addComplaintsDetailsTable.getLogBookNo());
            tv_comments.setText(addComplaintsDetailsTable.getSolution());

            getFieldName(String.valueOf(addComplaintsDetailsTable.getComplaintType()),tv_complain_type);

            getFieldName(String.valueOf(addComplaintsDetailsTable.getComplaintStatus()),tv_complain_status);


        }catch (Exception e){
            e.printStackTrace();
        }

        //image set
        try {
            //first image set
            if (savingComplainImagesTableList_filter.get(0).getServerStatus().equalsIgnoreCase("0")){
                Uri uri = null;
                File imgFile = new File(savingComplainImagesTableList_filter.get(0).getLocalDocUrl());
                if (imgFile.exists()) {
                    uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                    Picasso.get()
                            .load(uri)
//                                .error(R.drawable.round_image_shape)
                            .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                            .placeholder(R.drawable.loading_icon)
                            .into(imageview_complain_first);
                }
            }else if (savingComplainImagesTableList_filter.get(0).getServerStatus().equalsIgnoreCase("1")){
                if(savingComplainImagesTableList_filter.get(0).getFileLocation().length()>300){
                    Uri uri = null;
                    File imgFile = new File(savingComplainImagesTableList_filter.get(0).getLocalDocUrl());
                    if (imgFile.exists()) {
                        uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                        Picasso.get()
                                .load(uri)
//                                .error(R.drawable.round_image_shape)
                                .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                .placeholder(R.drawable.loading_icon)
                                .into(imageview_complain_first);
                    }
                } else {
                    Picasso.get()
                            .load(savingComplainImagesTableList_filter.get(0).getFileLocation())
                            .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                            .placeholder(R.drawable.loading_icon)
                            .into(imageview_complain_first);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            //second image set
            if (savingComplainImagesTableList_filter.get(1).getServerStatus().equalsIgnoreCase("0")){
                Uri uri = null;
                File imgFile = new File(savingComplainImagesTableList_filter.get(1).getLocalDocUrl());
                if (imgFile.exists()) {
                    uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                    Picasso.get()
                            .load(uri)
//                                .error(R.drawable.round_image_shape)
                            .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                            .placeholder(R.drawable.loading_icon)
                            .into(imageview_complain_second);
                }
            }else if (savingComplainImagesTableList_filter.get(1).getServerStatus().equalsIgnoreCase("1")){
                if(savingComplainImagesTableList_filter.get(1).getFileLocation().length()>300){
                    Uri uri = null;
                    File imgFile = new File(savingComplainImagesTableList_filter.get(1).getLocalDocUrl());
                    if (imgFile.exists()) {
                        uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                        Picasso.get()
                                .load(uri)
//                                .error(R.drawable.round_image_shape)
                                .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                .placeholder(R.drawable.loading_icon)
                                .into(imageview_complain_second);
                    }
                } else {
                    Picasso.get()
                            .load(savingComplainImagesTableList_filter.get(1).getFileLocation())
                            .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                            .placeholder(R.drawable.loading_icon)
                            .into(imageview_complain_second);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        img_cancel_complain_details.setOnClickListener(this);
    }

    private void configureDagger() {
        AndroidInjection.inject(ComplainListDetailsActivity.this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(ComplainListDetailsActivity.this, viewModelFactory).get(AppViewModel.class);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_cancel_complain_details:
                Intent intent = new Intent(ComplainListDetailsActivity.this, ComplainListsActivity.class);
                startActivity(intent);
                break;
        }

    }

//    public void getFieldName(String fieldID, TextView tvFieldName) {
//        try {
//            viewModel.getLookUpSelectionNameLookUpId(fieldID);
//            if (viewModel.getLookUpDropDownDetailsFromLocalDBByLookUPIDLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        LookUpDropDownDataTable lookUpDropDownDataTable = (LookUpDropDownDataTable) o;
//                        viewModel.getLookUpDropDownDetailsFromLocalDBByLookUPIDLiveData().removeObserver(this);
//                        if (lookUpDropDownDataTable != null) {
//                            tvFieldName.setText(lookUpDropDownDataTable.getName());
//                        }
//                    }
//                };
//                viewModel.getLookUpDropDownDetailsFromLocalDBByLookUPIDLiveData().observe((LifecycleOwner) this, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void getFieldName(String plantTypeId, TextView look) {
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


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }



}