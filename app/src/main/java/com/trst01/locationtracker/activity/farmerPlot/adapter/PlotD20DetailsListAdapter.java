package com.trst01.locationtracker.activity.farmerPlot.adapter;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_ZERO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddPlotTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.dialogs.ConfirmationDialog;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PlotD20DetailsListAdapter extends RecyclerView.Adapter<PlotD20DetailsListAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<AddD10Table> rawDataTableList;
    List<AddD10Table> rawDataTableListFiltered;
//    List<AddD20Table> rawDataTableList;
//    List<AddD20Table> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    AppViewModel viewModel;
    Integer indicator;

    public PlotD20DetailsListAdapter(Context context, List<AddD10Table> rawDataTableList,
                                     SyncCallbackInterface syncCallbackInterface,
                                     AppHelper appHelper, AppViewModel viewModel,Integer indicator) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.rawDataTableListFiltered = rawDataTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
        this.viewModel = viewModel;
        this.indicator = indicator;

    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.individual_plot, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                AddD10Table farmerTable = rawDataTableListFiltered.get(i);

                getFarmerVillageDetailsByVillageId(farmerTable.getPlotVillageId(), loanTypeViewHolder.txtVillage);
                getVarietyDetailsByVarietyId(farmerTable.getVarietyId(), loanTypeViewHolder.txtVariety);
//                getFarmerStateDetailsFromlocalDB(farmerTable.getStateId(), loanTypeViewHolder.txtAddress);
//                loanTypeViewHolder.txtPlantationDate.setText("Plantation Date : "+farmerTable.getPlantingDate());
                SimpleDateFormat sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_ZERO);
                Date dateOrder = sourceFormat.parse(farmerTable.getPlantingDate());

                SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY2, Locale.US);
                String strdisplayDate = displayDate.format(dateOrder);

                loanTypeViewHolder.txtPlantationDate.setText("Plantation Date : "+strdisplayDate);
                loanTypeViewHolder.txtPlotId.setText("Plot Id : "+farmerTable.getPlotNo());
                loanTypeViewHolder.txtSurveyNo.setText("Survey No : "+farmerTable.getSurveyNo());
//                loanTypeViewHolder.txtFarmerName.setText("FarmerName : "+farmerTable.getFatherName());
                loanTypeViewHolder.txtFarmerCode.setText("Farmer Code : "+farmerTable.getFarmerCode());
                loanTypeViewHolder.txtFarmerCode.setVisibility(View.VISIBLE);
//                loanTypeViewHolder.txtVillage.setText(farmerTable.getPlotVillageId());
                String stage = "";
                loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getReportedArea()); //aggremented area


                loanTypeViewHolder.txtPlotLocation.setText("Reported Area");
                loanTypeViewHolder.txtPlotLocation.setVisibility(View.GONE);

                getFarmerPLotListFromLocalDb40("",farmerTable.getFarmerCode(),farmerTable.getPlotNo(),loanTypeViewHolder.txtPlotLocation);



//                if(indicator==4){
//
//                    if(farmerTable.getStage().equals("10")){
//                        stage = "Reported Area";
//                        loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.bfil_blue));
//                        loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getReportedArea()); //aggremented area
//
//                    } else if(farmerTable.getStage().equals("20")) {
//                        stage = "Measured Area";
//                        loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.colorRed));
//                        loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getMeasureArea()); //aggremented area
//
//                    } else if(farmerTable.getStage().equals("30")) {
//                        stage = "Agreemented Area";
//                        loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.greenCardBg));
//                        loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getAggrementedArea()); //aggremented area
//
//                    } else if(farmerTable.getStage().equals("40")) {
//                        stage = "Net Area";
//                        loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.yellowish_color));
//                        loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getNetArea()); //aggremented area
//                    }
//                }


//                loanTypeViewHolder.txtPlotLocation.setText("Status "+stage);
//                loanTypeViewHolder.txtState.setText("Acres "+farmerTable.getCultivatedArea()); //aggremented area
//                loanTypeViewHolder.txtPlotLocation.setText(farmerTable.());
//                loanTypeViewHolder.txtState.setText(farmerTable.getSurveyNo());
//                loanTypeViewHolder.txtPattadharBookNo.setText(farmerTable.getPattadarBookNo());

//                loanTypeViewHolder.txtFarmerId.setText("Farmer ID : "+farmerTable.getFarmerCode());
//                loanTypeViewHolder.txtFarmerId.setText("Address : "+farmerTable.getFarmerCode());

//                String imagePathCheck = farmerTable.getDocUrl().substring(0, 4);
//
//                if (imagePathCheck.equalsIgnoreCase("http")) {
//                    Picasso.get()
//                            .load(farmerTable.getDocUrl())
//                            .error(R.drawable.farmer_pic)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
//                            .into(loanTypeViewHolder.farmerImage);
//                } else {
//                    Uri uri = null;
//                    File imgFile = new File(farmerTable.getLocalImage());
//                    if (imgFile.exists()) {
//                        uri = Uri.fromFile(imgFile);
//                        Picasso.get()
//                                .load(uri)
//                                .error(R.drawable.farmer_pic)
//                                .placeholder(R.drawable.farmer_pic)
//                                .into(loanTypeViewHolder.farmerImage);
//                    }
//
//                }
                loanTypeViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(i, farmerTable,rawDataTableList, String.valueOf(farmerTable.getId()));
                    }
                });


            }


        } catch (Exception ex) {
            ex.printStackTrace();
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

    public void getFarmerPLotListFromLocalDb40(String status, String farmerCode, String plotCode, TextView txtPlotLocation) {
        try {
            viewModel.getLandDetailsStageListFromLocalDb(farmerCode,plotCode);
            if (viewModel.getFarmerPlotDetailsListLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<AddPlotTable> odVisitSurveyTableList = (List<AddPlotTable>) o;
                        viewModel.getFarmerPlotDetailsListLiveData().removeObserver(this);
                        if (odVisitSurveyTableList != null && odVisitSurveyTableList.size() > 0) {
                            txtPlotLocation.setVisibility(View.VISIBLE);
                if(indicator==4){

                    if(odVisitSurveyTableList.get(0).getStage().equals("10")){
//                        stage = "Reported Area";
                        txtPlotLocation.setTextColor(context.getResources().getColor(R.color.bfil_blue));
                        txtPlotLocation.setText("Reported Area");//aggremented area

                    } else if(odVisitSurveyTableList.get(0).getStage().equals("20")) {
//                        stage = "Measured Area";
                        txtPlotLocation.setTextColor(context.getResources().getColor(R.color.colorRed));
                        txtPlotLocation.setText("Measured Area");
                    } else if(odVisitSurveyTableList.get(0).getStage().equals("30")) {
//                        stage = "Agreemented Area";
                        txtPlotLocation.setTextColor(context.getResources().getColor(R.color.greenCardBg));
                        txtPlotLocation.setText("Agreemented Area");

                    } else if(odVisitSurveyTableList.get(0).getStage().equals("40")) {
//                        stage = "Net Area";
                        txtPlotLocation.setTextColor(context.getResources().getColor(R.color.yellowish_color));
                        txtPlotLocation.setText("Net Area");                    }
                   }

                        } else {
//                            cvNetArea.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.greyBg));
//                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Farmer Plot list");
                        }
                    }
                };
                viewModel.getFarmerPlotDetailsListLiveData().observe((LifecycleOwner) context, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        if (rawDataTableListFiltered != null) {
            return rawDataTableListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("All")) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<AddD10Table> filteredList = new ArrayList<>();
                    for (int i = rawDataTableList.size(); i >= 1; i--) {
                        filteredList.add(rawDataTableList.get(i - 1));
                    }
                    rawDataTableListFiltered = filteredList;

                } else {
                    List<AddD10Table> filteredList = new ArrayList<>();
                    for (AddD10Table row : rawDataTableList) {
                        if (row.getFarmerCode().toLowerCase().contains(charString.toLowerCase())||row.getPlotNo().toLowerCase().contains(charString.toLowerCase())) {//plotNo
                            filteredList.add(row);
                        }
                    }

                    rawDataTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = rawDataTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                rawDataTableListFiltered = (List<AddD10Table>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtPlotId, txtPattadharBookNo, txtSurveyNo,
                txtVillage, txtState,txtPlotLocation,txtVariety,txtPlantationDate,txtFarmerName,txtFarmerCode;
//        CircleImageView farmerImage;
        CardView card_view;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPlotId = (TextView) itemView.findViewById(R.id.txtPlotId);
            txtPattadharBookNo = (TextView) itemView.findViewById(R.id.txtPattadharBookNo);
            txtSurveyNo = (TextView) itemView.findViewById(R.id.txtSurveyNo);
            txtVillage = (TextView) itemView.findViewById(R.id.txtVillage);
            txtState = itemView.findViewById(R.id.txtState);
            txtPlotLocation = itemView.findViewById(R.id.txtPlotLocation);
            card_view = itemView.findViewById(R.id.card_view);
            txtVariety = itemView.findViewById(R.id.txtVariety);
            txtPlantationDate = itemView.findViewById(R.id.txtPlantationDate);
            txtFarmerName = itemView.findViewById(R.id.txtFarmerName);
            txtFarmerCode = itemView.findViewById(R.id.txtFarmerCode);

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
        void openScreenCallback(int position, AddD10Table farmerTable, List<AddD10Table> farmer, String applicationType);

        void updateItemCallback(int position, AddD10Table applicationStatusTable, String strFarmerID);

        void addPlotDetailsCallback(int position, AddD10Table applicationStatusTable, String strFarmercode);
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