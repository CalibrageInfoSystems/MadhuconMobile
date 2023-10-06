package com.trst01.locationtracker.activity.plantation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.database.entity.AddFarmerTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.ArrayList;
import java.util.List;


public class FarmerGuarantorsListAdapter extends RecyclerView.Adapter<FarmerGuarantorsListAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<AddFarmerTable> rawDataTableList;
    List<AddFarmerTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    AppViewModel viewModel;
    int indicator=0;

    public FarmerGuarantorsListAdapter(Context context, List<AddFarmerTable> rawDataTableList,
                                       SyncCallbackInterface syncCallbackInterface,
                                       AppHelper appHelper, AppViewModel viewModel, int indicator) {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.individual_farmer, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                AddFarmerTable farmerTable = rawDataTableListFiltered.get(i);

                getFarmerVillageDetailsByVillageId(farmerTable.getVillageId(), loanTypeViewHolder.txtVillage);
//                getFarmerStateDetailsFromlocalDB(farmerTable.getStateId(), loanTypeViewHolder.txtAddress);
                loanTypeViewHolder.txtFarmer.setText("Farmer name : "+farmerTable.getName());
                loanTypeViewHolder.txtFarmerId.setText("GL No: "+farmerTable.getCode());
//                loanTypeViewHolder.txtFarmerId.setText("Farmer Id GL No: "+farmerTable.getCode());
                loanTypeViewHolder.txtAddress.setText("Address : "+farmerTable.getAddress());
                loanTypeViewHolder.txtFarmerMobileNo.setText("Mobile No : "+farmerTable.getMobile());
                loanTypeViewHolder.txtFarmerFatherName.setText("Father Name : "+farmerTable.getFatherName());

                if(indicator!=0){
                    loanTypeViewHolder.txtViewPlot.setText("View Plot Offer");
                }

                if(indicator==4){
                    loanTypeViewHolder.txtViewPlot.setText("View Plots");
                }

                loanTypeViewHolder.imgFarmer.setImageDrawable(null);

                if(farmerTable.getImageUrl().length()>0){
                    Picasso.get()
                            .load(farmerTable.getImageUrl())
                            .error(R.drawable.current_farmers)
                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                            .into(loanTypeViewHolder.imgFarmer);
                }
                else {
                    loanTypeViewHolder.imgFarmer.setImageDrawable(context.getDrawable(R.drawable.current_farmers));
                }
//                loanTypeViewHolder.txtVillage.setText("Village : "+farmerTable.getGLCode());
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

                loanTypeViewHolder.surface_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(i, farmerTable,rawDataTableList, String.valueOf(farmerTable.getId()));
                    }
                });

                loanTypeViewHolder.txtViewPlot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.updateItemCallback(i, farmerTable, String.valueOf(farmerTable.getId()));
                    }
                });

                loanTypeViewHolder.txtViewPlot.setVisibility(View.GONE);
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
                    Log.e("filter","?All");
                } else if (charString.equalsIgnoreCase("ASC")) {
                    rawDataTableListFiltered = rawDataTableList;
                    Log.e("filter","?Asc");
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<AddFarmerTable> filteredList = new ArrayList<>();
                    for (int i = rawDataTableList.size(); i >= 1; i--) {
                        filteredList.add(rawDataTableList.get(i - 1));
                        Log.e("filter","?DESC");
                    }
                    rawDataTableListFiltered = filteredList;

                } else {
                    List<AddFarmerTable> filteredList = new ArrayList<>();

                    for (AddFarmerTable row : rawDataTableList) {
//                        if(indicator!=0){
//                            if (row.getName().toLowerCase().contains(charString.toLowerCase())|| row.getCode().toLowerCase().contains(charString.toLowerCase())
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())
                        ) {//name
                            filteredList.add(row);
                        }
//                        } else {
//                            if (row.getName().toLowerCase().contains(charString.toLowerCase())||
//                                    row.getCode().toLowerCase().contains(charString.toLowerCase())
//                            ) {//name
//                                filteredList.add(row);
//                            }
//
//                        }
                    }
                    for (AddFarmerTable row : rawDataTableList) {
//                        if(indicator!=0){
//                            if (row.getName().toLowerCase().contains(charString.toLowerCase())|| row.getCode().toLowerCase().contains(charString.toLowerCase())
//                            if (row.getCode().toLowerCase().contains(charString.toLowerCase())
                            if (row.getCode().toLowerCase().contains(charString.toLowerCase())
                            ) {//name
                                filteredList.add(row);
                            }
//                        } else {
//                            if (row.getName().toLowerCase().contains(charString.toLowerCase())||
//                                    row.getCode().toLowerCase().contains(charString.toLowerCase())
//                            ) {//name
//                                filteredList.add(row);
//                            }
//
//                        }
                    }
                    Log.e("filter","?Else");
                    rawDataTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = rawDataTableListFiltered;
                Log.e("filter",filterResults.toString());
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                rawDataTableListFiltered = (List<AddFarmerTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtFarmer,txtFarmerId,txtAddress,txtViewPlot,txtFarmerMobileNo,txtVillage,txtFarmerFatherName;
        CardView card_view;
        ImageView imgFarmer;
        LinearLayout surface_layout;
//        CircleImageView farmerImage;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFarmerFatherName = (TextView) itemView.findViewById(R.id.txtFarmerFatherName);
            txtFarmer = (TextView) itemView.findViewById(R.id.txtFarmer);
            txtFarmerId = (TextView) itemView.findViewById(R.id.txtFarmerId);
            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            txtViewPlot = (TextView) itemView.findViewById(R.id.txtViewPlot);
            txtFarmerMobileNo = (TextView) itemView.findViewById(R.id.txtFarmerMobileNo);
            txtVillage = (TextView) itemView.findViewById(R.id.txtVillage);
            imgFarmer = (ImageView) itemView.findViewById(R.id.imgFarmer);
            surface_layout = (LinearLayout) itemView.findViewById(R.id.surface_layout);
//            farmerImage = itemView.findViewById(R.id.imgFarmer);

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
        void openScreenCallback(int position, AddFarmerTable farmerTable, List<AddFarmerTable> farmer, String applicationType);

        void updateItemCallback(int position, AddFarmerTable applicationStatusTable, String strFarmerID);

        void addPlotDetailsCallback(int position, AddFarmerTable applicationStatusTable, String strFarmercode);
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