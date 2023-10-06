package com.trst01.locationtracker.activity.complains;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.constant.AppHelper;
import com.trst01.locationtracker.database.entity.AddComplaintsDetailsTable;
import com.trst01.locationtracker.database.entity.SavingComplainImagesTable;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComplainDetailsListAdapter extends RecyclerView.Adapter<ComplainDetailsListAdapter.LoanTypeViewHolder> {
    private Context context;
    List<AddComplaintsDetailsTable> addComplaintsDetailsTableList;
    List<SavingComplainImagesTable> savingComplainImagesTableList;
    AppHelper appHelper;
    AppViewModel viewModel;

    public ComplainDetailsListAdapter(Context context, List<AddComplaintsDetailsTable> addComplaintsDetailsTableList,
                                      List<SavingComplainImagesTable> savingComplainImagesTableList,
                                      AppHelper appHelper, AppViewModel viewModel) {
        this.context = context;
        this.addComplaintsDetailsTableList = addComplaintsDetailsTableList;
        this.savingComplainImagesTableList = savingComplainImagesTableList;
        this.appHelper = appHelper;
        this.viewModel = viewModel;

    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complain_lists_adapter, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (addComplaintsDetailsTableList != null && addComplaintsDetailsTableList.size() > 0) {
                AddComplaintsDetailsTable addComplaintsDetailsTable = addComplaintsDetailsTableList.get(i);

                loanTypeViewHolder.tv_complain_id.setText(addComplaintsDetailsTable.getCode());
                loanTypeViewHolder.tv_logbook_no.setText(addComplaintsDetailsTable.getLogBookNo());
//                loanTypeViewHolder.tv_complain_type.setText(addComplaintsDetailsTable.getComplaintType());
//                loanTypeViewHolder.tv_complain_status.setText(addComplaintsDetailsTable.getComplaintStatus());
                loanTypeViewHolder.tv_comments.setText(addComplaintsDetailsTable.getSolution());


//                getFieldName(String.valueOf(addComplaintsDetailsTable.getComplaintType()),loanTypeViewHolder.tv_complain_type);

//                getFieldName(String.valueOf(addComplaintsDetailsTable.getComplaintStatus()),loanTypeViewHolder.tv_complain_status);

                loanTypeViewHolder.rl_complain_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SavingComplainImagesTable savingComplainImagesTable = getSelectedComplaintImageObject(addComplaintsDetailsTable);
                        Intent intent = new Intent(context, ComplainListDetailsActivity.class);
                        intent.putExtra("COMPLAIN_DETAILS", (Serializable) addComplaintsDetailsTable);
                        intent.putExtra("COMPLAIN_IMAGE_DETAILS", (Serializable) savingComplainImagesTable);
//                        intent.putExtra("COMPLAIN_IMAGE_DETAILS_SECOND", (Serializable) savingComplainImagesTableList.get(1));



                        //filter images based on the complain code
                        List<SavingComplainImagesTable> savingComplainImagesTableList_filter = new ArrayList<>();
                        for (int j = 0; j < savingComplainImagesTableList.size(); j++) {
                            if (addComplaintsDetailsTable.getCode().equalsIgnoreCase(savingComplainImagesTableList.get(j).getComplaintCode())){
//                            if (addComplaintsDetailsTable.getCode().equalsIgnoreCase(savingComplainImagesTableList.get(j).getComplaintCode())){
                                SavingComplainImagesTable savingComplainImagesTable1 = new SavingComplainImagesTable();
                                savingComplainImagesTable1.setLocalDocUrl(savingComplainImagesTableList.get(j).getLocalDocUrl());
                                savingComplainImagesTable1.setFileLocation(savingComplainImagesTableList.get(j).getFileLocation());
                                savingComplainImagesTable1.setServerStatus(savingComplainImagesTableList.get(j).getServerStatus());
                                savingComplainImagesTableList_filter.add(savingComplainImagesTable1);
                            }
                        }

                        intent.putExtra("COMPLAIN_IMAGE_DETAILS_FILTER", (Serializable) savingComplainImagesTableList_filter);



                        context.startActivity(intent);

                    }
                });


//                String imagePathCheck = addComplaintsDetailsTable.getDocUrl().substring(0, 4);

                try {
                    //match two lists logbbok number if match then will displaces images
                    for (int j = 0; j < addComplaintsDetailsTableList.size(); j++) {
                        for (int k = 0; k < savingComplainImagesTableList.size(); k++) {
                            if (addComplaintsDetailsTableList.get(j).getLogBookNo().equalsIgnoreCase(savingComplainImagesTableList.get(k).getLogBookNo())){

                                //first image set
                                if (savingComplainImagesTableList.get(0).getServerStatus().equalsIgnoreCase("0")){

                                    Uri uri = null;
                                    File imgFile = new File(savingComplainImagesTableList.get(0).getLocalDocUrl());
                                    if (imgFile.exists()) {
                                        uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                        Picasso.get()
                                                .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                .placeholder(R.drawable.loading_icon)
                                                .into(loanTypeViewHolder.imageview_complain_first);
                                    }
                                }else {
                                    Picasso.get()
                                            .load(savingComplainImagesTableList.get(0).getFileLocation())
                                            .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                                            .placeholder(R.drawable.loading_icon)
                                            .into(loanTypeViewHolder.imageview_complain_first);
                                }


                                //second image set
                                if (savingComplainImagesTableList.get(1).getServerStatus().equalsIgnoreCase("0")){

                                    Uri uri = null;
                                    File imgFile = new File(savingComplainImagesTableList.get(1).getLocalDocUrl());
                                    if (imgFile.exists()) {
                                        uri = Uri.fromFile(imgFile);
//                                     loanTypeViewHolder.farmer_image_new.setVisibility(View.GONE);
                                        Picasso.get()
                                                .load(uri)
//                                .error(R.drawable.round_image_shape)
                                                .error(R.drawable.loading_icon)
//                                .placeholder(R.drawable.round_image_shape)
                                                .placeholder(R.drawable.loading_icon)
                                                .into(loanTypeViewHolder.imageview_complain_second);
                                    }
                                }else {
                                    Picasso.get()
                                            .load(savingComplainImagesTableList.get(1).getFileLocation())
                                            .error(R.drawable.loading_icon)
//                            .error(R.drawable.placeholder_image)
//                            .placeholder(R.drawable.loading_icon)
//                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                                            .placeholder(R.drawable.loading_icon)
                                            .into(loanTypeViewHolder.imageview_complain_second);
                                }


                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private SavingComplainImagesTable getSelectedComplaintImageObject(AddComplaintsDetailsTable addComplaintsDetailsTable) {
        SavingComplainImagesTable imagesTableTemp = null;
        for (SavingComplainImagesTable imagesTable : savingComplainImagesTableList) {
            if (addComplaintsDetailsTable.getCode().equalsIgnoreCase(imagesTable.getComplaintCode())) {
                imagesTableTemp = imagesTable;
                break;
            }
        }
        return imagesTableTemp;
    }




    @Override
    public int getItemCount() {
        if (addComplaintsDetailsTableList != null) {
            return addComplaintsDetailsTableList.size();
        } else {
            return 0;
        }
    }
    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tv_complain_id, tv_logbook_no, tv_complain_type,
                tv_complain_status, tv_comments;
        ImageView imageview_complain_first,imageview_complain_second;
        RelativeLayout rl_complain_details;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_complain_id = (TextView) itemView.findViewById(R.id.tv_complain_id);
            tv_logbook_no = (TextView) itemView.findViewById(R.id.tv_logbook_no);
            tv_complain_type = (TextView) itemView.findViewById(R.id.tv_complain_type);
            tv_complain_status = (TextView) itemView.findViewById(R.id.tv_complain_status);
            tv_comments = (TextView)  itemView.findViewById(R.id.tv_comments);

            imageview_complain_first = itemView.findViewById(R.id.imageview_complain_first);
            imageview_complain_second = itemView.findViewById(R.id.imageview_complain_second);
            rl_complain_details = itemView.findViewById(R.id.rl_complain_details);
        }
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
