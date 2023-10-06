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
import com.trst01.locationtracker.database.entity.SeasonTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.ArrayList;
import java.util.List;


public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.LoanTypeViewHolder> {
    private Context context;
    List<SeasonTable> rawDataTableList;
    List<SeasonTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    AppViewModel viewModel;
    int indicator=0;

    public SeasonsAdapter(Context context, List<SeasonTable> rawDataTableList,
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.individual_pin, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                SeasonTable farmerTable = rawDataTableListFiltered.get(i);

                  loanTypeViewHolder.txtSeason.setText(farmerTable.getName());
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
                loanTypeViewHolder.txtSeason.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        if (rawDataTableListFiltered != null) {
            return rawDataTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtSeason;
        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSeason = (TextView) itemView.findViewById(R.id.txtSeason);
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
        void openScreenCallback(int position, SeasonTable farmerTable, List<SeasonTable> farmer, String applicationType);

        void updateItemCallback(int position, SeasonTable applicationStatusTable, String strFarmerID);

        void addPlotDetailsCallback(int position, SeasonTable applicationStatusTable, String strFarmercode);
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