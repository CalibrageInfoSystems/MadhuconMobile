package com.trst01.locationtracker.activity.growthMonitoring.adapter;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_ZERO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.database.entity.AddD10Table;
import com.trst01.locationtracker.database.entity.D20FertilizerTable;
import com.trst01.locationtracker.database.entity.D20WeedTable;
import com.trst01.locationtracker.database.entity.FertilizerTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WeedTable;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class BioFertilizerDetailsListAdapter extends RecyclerView.Adapter<BioFertilizerDetailsListAdapter.LoanTypeViewHolder>  {
    private Context context;
    List<FertilizerTable> rawDataTableList;
    List<FertilizerTable> rawDataTableListFiltered;
//    List<AddD20Table> rawDataTableList;
//    List<AddD20Table> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    AppViewModel viewModel;
    List<D20FertilizerTable> rawDataTableListFilteredD20;
    public BioFertilizerDetailsListAdapter(Context context, List<FertilizerTable> rawDataTableList,List<D20FertilizerTable> d20WeedTableList,
                                           SyncCallbackInterface syncCallbackInterface,
                                           AppHelper appHelper, AppViewModel viewModel) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.rawDataTableListFiltered = rawDataTableList;
        this.rawDataTableListFilteredD20 = d20WeedTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
        this.viewModel = viewModel;

    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.individual_field, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, @SuppressLint("RecyclerView") int i) {
        try {
//            loanTypeViewHolder.txtCheckBox.setText(rawDataTableList.get(i).getName());
//            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
//                AddD10Table farmerTable = rawDataTableListFiltered.get(i);
//
//                getFarmerVillageDetailsByVillageId(farmerTable.getPlotVillageId(), loanTypeViewHolder.txtVillage);
//                getVarietyDetailsByVarietyId(farmerTable.getVarietyId(), loanTypeViewHolder.txtVariety);
//
////                getFarmerStateDetailsFromlocalDB(farmerTable.getStateId(), loanTypeViewHolder.txtAddress);
////                loanTypeViewHolder.txtPlantationDate.setText("Plantation Date : "+farmerTable.getPlantingDate());
//
//                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_ZERO);
//                Date dateOrder = sourceFormat.parse(farmerTable.getPlantingDate());
//
//                SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY2, Locale.US);
//                String strdisplayDate = displayDate.format(dateOrder);
//
//                loanTypeViewHolder.txtPlantationDate.setText("Plantation Date : "+strdisplayDate);
//                loanTypeViewHolder.txtPlotId.setText("Plot Id : "+farmerTable.getPlotNo());
//                loanTypeViewHolder.txtSurveyNo.setText("Survey No : "+farmerTable.getSurveyNo());
////                loanTypeViewHolder.txtFarmerName.setText("FarmerName : "+farmerTable.getFatherName());
//                loanTypeViewHolder.txtFarmerCode.setText("Farmer Code : "+farmerTable.getFarmerCode());
//                loanTypeViewHolder.txtFarmerCode.setVisibility(View.VISIBLE);
////                loanTypeViewHolder.txtVillage.setText(farmerTable.getPlotVillageId());
//                String stage = "";
//                loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getReportedArea()); //aggremented area
//
////                if(farmerTable.getStage().equals("10")){
////                    stage = "Reported Area";
////                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.bfil_blue));
////                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getReportedArea()); //aggremented area
////
////                } else if(farmerTable.getStage().equals("20")) {
////                    stage = "Measured Area";
////                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.colorRed));
////                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getMeasureArea()); //aggremented area
////
////                } else if(farmerTable.getStage().equals("30")) {
////                    stage = "Agreemented Area";
////                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.greenCardBg));
////                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getAggrementedArea()); //aggremented area
////
////                } else if(farmerTable.getStage().equals("40")) {
////                    stage = "Net Area";
////                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.yellowish_color));
////                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getNetArea()); //aggremented area
////                }
//                loanTypeViewHolder.txtPlotLocation.setText("Reported Area");
//                loanTypeViewHolder.txtPlotLocation.setVisibility(View.GONE);
////                loanTypeViewHolder.txtPlotLocation.setText("Status "+stage);
////                loanTypeViewHolder.txtState.setText("Acres "+farmerTable.getCultivatedArea()); //aggremented area
////                loanTypeViewHolder.txtPlotLocation.setText(farmerTable.());
////                loanTypeViewHolder.txtState.setText(farmerTable.getSurveyNo());
////                loanTypeViewHolder.txtPattadharBookNo.setText(farmerTable.getPattadarBookNo());
//
////                loanTypeViewHolder.txtFarmerId.setText("Farmer ID : "+farmerTable.getFarmerCode());
////                loanTypeViewHolder.txtFarmerId.setText("Address : "+farmerTable.getFarmerCode());
//
////                String imagePathCheck = farmerTable.getDocUrl().substring(0, 4);
////
////                if (imagePathCheck.equalsIgnoreCase("http")) {
////                    Picasso.get()
////                            .load(farmerTable.getDocUrl())
////                            .error(R.drawable.farmer_pic)
////                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
////                            .into(loanTypeViewHolder.farmerImage);
////                } else {
////                    Uri uri = null;
////                    File imgFile = new File(farmerTable.getLocalImage());
////                    if (imgFile.exists()) {
////                        uri = Uri.fromFile(imgFile);
////                        Picasso.get()
////                                .load(uri)
////                                .error(R.drawable.farmer_pic)
////                                .placeholder(R.drawable.farmer_pic)
////                                .into(loanTypeViewHolder.farmerImage);
////                    }
////
////                }
//
//
//                loanTypeViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        syncCallbackInterface.openScreenCallback(i, farmerTable,rawDataTableList, String.valueOf(farmerTable.getId()));
//                    }
//                });
//
//
//            }
            loanTypeViewHolder.txtCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    syncCallbackInterface.updateItemCallback(i, rawDataTableListFilteredD20.get(i),rawDataTableListFilteredD20, String.valueOf(rawDataTableList.get(i)));
                }
            });

            getWeedByID(rawDataTableListFilteredD20.get(i).getFertilizerId(), loanTypeViewHolder.txtCheckBox);
            if(rawDataTableListFilteredD20.get(i).getIsActive().equals("true")) {
                loanTypeViewHolder.txtCheckBox.setChecked(true);
                loanTypeViewHolder.txtCheckBox.setEnabled(false);
//                return true;
            }

//            isAvailable(rawDataTableList.get(i), loanTypeViewHolder.txtCheckBox);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    private boolean isAvailable(FertilizerTable seq, CheckBox checkBox){
//        for(int i=0;i<rawDataTableListFilteredD20.size();i++){
//            if(rawDataTableListFilteredD20.get(i).getFertilizerId().equals(String.valueOf(seq.getFertilizerId()))){
//                if(rawDataTableListFilteredD20.get(i).getActive()){
//                    checkBox.setChecked(true);
//                    checkBox.setEnabled(false);
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }

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
                            tvFarmerVillage.setText("Village : "+villageTable.getName());
                            //getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe((LifecycleOwner) context, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getVarietyDetailsByVarietyId(String strVillageId, TextView tvFarmerVillage) {
        try {
            viewModel.getVarietyById(strVillageId);
            if (viewModel.getVarietyFromLocalDBByIdLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        VarietyTable villageTable = (VarietyTable) o;
                        viewModel.getVarietyFromLocalDBByIdLiveData().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText("Variety: "+villageTable.getName());
                            //getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getVarietyFromLocalDBByIdLiveData().observe((LifecycleOwner) context, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (rawDataTableListFilteredD20 != null) {
            return rawDataTableListFilteredD20.size();
        } else {
            return 0;
        }
    }


    public void getWeedByID(String strVillageId, CheckBox tvFarmerVillage) {
        try {
            viewModel.getFertilizerById(strVillageId);
            if (viewModel.getInsertFertilizerIntoLocalDBQueryLiveDataLocalDB() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        FertilizerTable villageTable = (FertilizerTable) o;
                        viewModel.getInsertFertilizerIntoLocalDBQueryLiveDataLocalDB().removeObserver(this);
                        if (villageTable != null) {
                            tvFarmerVillage.setText(villageTable.getName());
                            //getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
                        }
                    }
                };
                viewModel.getInsertFertilizerIntoLocalDBQueryLiveDataLocalDB().observe((LifecycleOwner) context, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    rawDataTableListFiltered = rawDataTableList;
//                } else if (charString.equalsIgnoreCase("All")) {
//                    rawDataTableListFiltered = rawDataTableList;
//                } else if (charString.equalsIgnoreCase("ASC")) {
//                    rawDataTableListFiltered = rawDataTableList;
//                } else if (charString.equalsIgnoreCase("DESC")) {
//                    List<FertilizerTable> filteredList = new ArrayList<>();
//                    for (int i = rawDataTableList.size(); i >= 1; i--) {
//                        filteredList.add(rawDataTableList.get(i - 1));
//                    }
//                    rawDataTableListFiltered = filteredList;
//
//                } else {
//                    List<FertilizerTable> filteredList = new ArrayList<>();
//                    for (FertilizerTable row : rawDataTableList) {
//                        if (row.getCode().toLowerCase().contains(charString.toLowerCase())||row.getName().toLowerCase().contains(charString.toLowerCase())) {//plotNo
//                            filteredList.add(row);
//                        }
//                    }
//
//                    rawDataTableListFiltered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = rawDataTableListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                rawDataTableListFiltered = (List<FertilizerTable>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
//        TextView txtPlotId, txtPattadharBookNo, txtSurveyNo,
//                txtVillage, txtState,txtPlotLocation,txtVariety,txtPlantationDate,txtFarmerName,txtFarmerCode;
////        CircleImageView farmerImage;
//        CardView card_view;
CheckBox txtCheckBox;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCheckBox = (CheckBox) itemView.findViewById(R.id.txtCheckBox);
    //            txtPlotId = (TextView) itemView.findViewById(R.id.txtPlotId);
    //            txtPattadharBookNo = (TextView) itemView.findViewById(R.id.txtPattadharBookNo);
    //            txtSurveyNo = (TextView) itemView.findViewById(R.id.txtSurveyNo);
    //            txtVillage = (TextView) itemView.findViewById(R.id.txtVillage);
    //            txtState = itemView.findViewById(R.id.txtState);
    //            txtPlotLocation = itemView.findViewById(R.id.txtPlotLocation);
    //            card_view = itemView.findViewById(R.id.card_view);
    //            txtVariety = itemView.findViewById(R.id.txtVariety);
    //            txtPlantationDate = itemView.findViewById(R.id.txtPlantationDate);
    //            txtFarmerName = itemView.findViewById(R.id.txtFarmerName);
    //            txtFarmerCode = itemView.findViewById(R.id.txtFarmerCode);

//            txtFarmerId = (TextView) itemView.findViewById(R.id.txtFarmerId);
//            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
//            farmerImage = itemView.findViewById(R.id.imgFarmer);

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
//                            //getFarmerStateDetailsFromlocalDB(stateID, tvFarmerState);
//                        }
//                    }
//                };
//                viewModel.getVillageDetailsFromLocalDBByVillageIdLiveData().observe((LifecycleOwner) context, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


//    public void getFarmerStateDetailsFromlocalDB(String stateID, TextView tvFarmerState) {
//        try {
//            viewModel.getStateDetailsFromlocalDB(stateID);
//            if (viewModel.getStateTablelDetailsFromLocalDBLiveData() != null) {
//                Observer getLeadRawDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        StatesTable statesTable = (StatesTable) o;
//                        viewModel.getStateTablelDetailsFromLocalDBLiveData().removeObserver(this);
//                        if (statesTable != null) {
//                            // progressDialog.dismiss();
//                            tvFarmerState.setText("Address : "+statesTable.getName());
//                        }
//                    }
//                };
//                viewModel.getStateTablelDetailsFromLocalDBLiveData().observe((LifecycleOwner) context, getLeadRawDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, FertilizerTable farmerTable, List<FertilizerTable> farmer, String applicationType);

        void updateItemCallback(int position, D20FertilizerTable d20FertilizerTable,List<D20FertilizerTable> d20WeedTableList, String strFarmerID);

        void addPlotDetailsCallback(int position, FertilizerTable applicationStatusTable, String strFarmercode);
    }
}


//                Glide.with(context)
//                        .load(new File(uri.getPath()))
//                        .into(imageView);
//                File f;
//                f = new File(farmerDetailListTable.getLocalImage());
//                Log.e(TAG, "onBindViewHolder: Image" + farmerDetailListTable.getLocalImage() );
//                Uri contentUri = Uri.fromFile(f);
//
//                bitmapFarmerImg = BitmapFactory.decodeFile(farmerDetailListTable.getLocalImage());
//                getBytesFromBitmap(bitmapFarmerImg);
//                bitmapFarmerImg = ImageUtility.rotatePicture(90, bitmapFarmerImg);
//                loanTypeViewHolder.farmerImage.setImageBitmap(bitmapFarmerImg);


//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Uri uri = null;
//                                File imgFile = new File(farmerTable.getLocalImage());
//                                if (imgFile.exists()) {
//                                    uri = Uri.fromFile(imgFile);
//
////                                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
////                                    myBitmap = ImageUtility.rotatePicture(90, myBitmap);
////                                    loanTypeViewHolder.farmerImage.setImageBitmap(myBitmap);
//
//                                    Picasso.get()
//                                            .load(uri)
//                                            .error(R.drawable.placeholder_image)
//                                       //     .placeholder(android.R.drawable.progress_indeterminate_horizontal)
//                                            .into(loanTypeViewHolder.farmerImage);
//
////                                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
////                                    myBitmap = ImageUtility.rotatePicture(90, myBitmap);
////                                    loanTypeViewHolder.farmerImage.setImageBitmap(myBitmap);
//                                }
//                            } catch (Exception e) {
//
//                            }
//                        }
//                    }, 80);


//                                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                                    myBitmap = ImageUtility.rotatePicture(90, myBitmap);
//                                    loanTypeViewHolder.farmerImage.setImageBitmap(myBitmap);