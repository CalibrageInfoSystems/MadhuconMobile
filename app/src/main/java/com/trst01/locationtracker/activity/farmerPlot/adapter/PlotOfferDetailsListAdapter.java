package com.trst01.locationtracker.activity.farmerPlot.adapter;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_ZERO;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_Z_HH_MM_SS_SSS;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_Z_HH_MM_SS_XXX;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.database.entity.AddD20Table;
import com.trst01.locationtracker.database.entity.AddPlotOfferTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class PlotOfferDetailsListAdapter extends RecyclerView.Adapter<PlotOfferDetailsListAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<AddPlotOfferTable> rawDataTableList;
    List<AddPlotOfferTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    AppViewModel viewModel;
    int indicator =0;

    public PlotOfferDetailsListAdapter(Context context, List<AddPlotOfferTable> rawDataTableList,
                                       SyncCallbackInterface syncCallbackInterface,
                                       AppHelper appHelper, AppViewModel viewModel,int indicator) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                AddPlotOfferTable farmerTable = rawDataTableListFiltered.get(i);

                getFarmerVillageDetailsByVillageId(farmerTable.getPlotVillageId(), loanTypeViewHolder.txtVillage);
                getVarietyDetailsByVarietyId(farmerTable.getExpectedVarityId(), loanTypeViewHolder.txtVariety);

//                getFarmerStateDetailsFromlocalDB(farmerTable.getStateId(), loanTypeViewHolder.txtAddress);
//                loanTypeViewHolder.txtPlantationDate.setText("Plantation Date : "+farmerTable.getPlantingDate());

                loanTypeViewHolder.txtPlotId.setText("Farmer Code : "+farmerTable.getFarmerCode());
//                loanTypeViewHolder.txtPlotId.setText("PlotOffer Id : "+farmerTable.getPlotOfferId());
//                loanTypeViewHolder.txtVillage.setText(farmerTable.getPlotVillageId());
                String stage = "";
                loanTypeViewHolder.txtState.setText("Expected Plant Area (Acres) : "+farmerTable.getExpectedArea()); //aggremented area

//                if(farmerTable.getStage().equals("10")){
//                    stage = "Reported Area";
//                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.bfil_blue));
//                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getReportedArea()); //aggremented area
//
//                } else if(farmerTable.getStage().equals("20")) {
//                    stage = "Measured Area";
//                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.colorRed));
//                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getMeasureArea()); //aggremented area
//
//                } else if(farmerTable.getStage().equals("30")) {
//                    stage = "Agreemented Area";
//                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.greenCardBg));
//                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getAggrementedArea()); //aggremented area
//
//                } else if(farmerTable.getStage().equals("40")) {
//                    stage = "Net Area";
//                    loanTypeViewHolder.txtPlotLocation.setTextColor(context.getResources().getColor(R.color.yellowish_color));
//                    loanTypeViewHolder.txtState.setText("Acres : "+farmerTable.getNetArea()); //aggremented area
//                }
                loanTypeViewHolder.txtPlotLocation.setText("Reported Area");
                loanTypeViewHolder.txtPlotLocation.setVisibility(View.GONE);
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


//                SimpleDateFormat sourceFormat = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                    sourceFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_T_Z_HH_MM_SS_XXX);
//                }
//                Date dateOrder = sourceFormat.parse(farmerTable.getExpectedPlantingDate());
//                Date dateOrder1 = sourceFormat.parse(farmerTable.getOfferDate());
//
//                SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY2, Locale.US);
//                String strdisplayDate = displayDate.format(dateOrder);
//                String strdisplayDate1 = displayDate.format(dateOrder1);
//
//                loanTypeViewHolder.txtPlantationDate.setText("Expected Planting Date : "+strdisplayDate);

//                loanTypeViewHolder.txtSurveyNo.setText("Offer Date : "+farmerTable.g);

                loanTypeViewHolder.txtPlantationDate.setVisibility(View.GONE);
                loanTypeViewHolder.txtSurveyNo.setVisibility(View.GONE);

                if(indicator==2){

                    loanTypeViewHolder.txtSurveyNo.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.txtSurveyNo.setText("Offer No : "+ farmerTable.getOfferNo());

                    String dateTimes = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD);
                    String strToday = dateTimes;
                    SimpleDateFormat myFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD);
//                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
//                    String inputString1 = farmerTable.getExpectedPlantingDate();
                    String inputString1 = firstTwo(farmerTable.getExpectedPlantingDate());
                    String inputString2 = strToday;

                    //hidden as we need to add it in measured and agreemented for 15 and 30 days respectively//
//                    String inputString1 = "23 01 1997";
//                    String inputString2 = "27 04 1997";

//                    try {
//                        Date date1 = myFormat.parse(inputString1);
//                        Date date2 = myFormat.parse(inputString2);
//                        long diff = date2.getTime() - date1.getTime();
//
//                        float days = (diff / (1000*60*60*24));
//
//                        if(days>14){
//                            loanTypeViewHolder.card_view.setEnabled(false);
//                            loanTypeViewHolder.card_view.setCardBackgroundColor(context.getResources().getColor(R.color.greyBg));
//                        } else {
//
//                        }
//
//                        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }



                }

            }

//            if(indicator!=0){
//                loanTypeViewHolder
//            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String firstTwo(String str) {
        return str.length() < 9 ? str : str.substring(0, 10);
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
                    List<AddPlotOfferTable> filteredList = new ArrayList<>();
                    for (int i = rawDataTableList.size(); i >= 1; i--) {
                        filteredList.add(rawDataTableList.get(i - 1));
                    }
                    rawDataTableListFiltered = filteredList;

                } else {
                    List<AddPlotOfferTable> filteredList = new ArrayList<>();
                    for (AddPlotOfferTable row : rawDataTableList) {
                        if (String.valueOf(row.getPlotOfferId()).toLowerCase().contains(charString.toLowerCase())||
                                row.getFarmerCode().toLowerCase().equals(charString.toLowerCase())||
                                row.getOfferNo().toLowerCase().equals(charString.toLowerCase())) {//plotNo
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
                rawDataTableListFiltered = (List<AddPlotOfferTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtPlotId, txtPattadharBookNo, txtSurveyNo,
                txtVillage, txtState,txtPlotLocation,txtVariety,txtPlantationDate;
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
        void openScreenCallback(int position, AddPlotOfferTable farmerTable, List<AddPlotOfferTable> farmer, String applicationType);

        void updateItemCallback(int position, AddPlotOfferTable applicationStatusTable, String strFarmerID);

        void addPlotDetailsCallback(int position, AddPlotOfferTable applicationStatusTable, String strFarmercode);
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