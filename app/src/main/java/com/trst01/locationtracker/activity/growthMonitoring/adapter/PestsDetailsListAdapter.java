package com.trst01.locationtracker.activity.growthMonitoring.adapter;

import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_DD_MMM_YYYY2;
import static com.trst01.locationtracker.constant.AppConstant.DATE_FORMAT_YYYY_MM_DD;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
import com.trst01.locationtracker.database.entity.D20DiseaseTable;
import com.trst01.locationtracker.database.entity.D20PestTable;
import com.trst01.locationtracker.database.entity.DiseaseTable;
import com.trst01.locationtracker.database.entity.PestTable;
import com.trst01.locationtracker.database.entity.VarietyTable;
import com.trst01.locationtracker.database.entity.VillageTable;
import com.trst01.locationtracker.database.entity.WeedTable;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PestsDetailsListAdapter extends RecyclerView.Adapter<PestsDetailsListAdapter.LoanTypeViewHolder>  {
//public class PestsDetailsListAdapter extends RecyclerView.Adapter<PestsDetailsListAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<PestTable> rawDataTableList;
    List<PestTable> rawDataTableListFiltered;
//    List<AddD20Table> rawDataTableList;
//    List<AddD20Table> rawDataTableListFiltered;
List<D20PestTable> rawDataTableListFilteredD20;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    AppViewModel viewModel;

    public PestsDetailsListAdapter(Context context, List<PestTable> rawDataTableList,List<D20PestTable> d20WeedTableList,
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.individual_disease, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            loanTypeViewHolder.txtName.setText(rawDataTableList.get(i).getName());
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
            loanTypeViewHolder.txtIdentifiedDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar currentDate = Calendar.getInstance();
                    Calendar date = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.set(year, monthOfYear, dayOfMonth);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                            SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
                            String strdisplayDate = displayDate.format(date.getTime());
                            String strOfferDate = simpleDateFormat.format(date.getTime());
                            loanTypeViewHolder.txtIdentifiedDate.setText(strOfferDate);
//                            loanTypeViewHolder.txtControlDate.setText(strdisplayDate);
                            syncCallbackInterface.addPlotDetailsCallback(i,rawDataTableList.get(i),strOfferDate);
//                        edtExpectedPlantingDate.setText("");
//                        String strFinalDate="";

                        }
                    }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                    DatePicker datePicker = datePickerDialog.getDatePicker();
//                datePicker.setMaxDate(System.currentTimeMillis()); // removed
                    datePickerDialog.show();

//                    syncCallbackInterface.addPlotDetailsCallback(i,rawDataTableList.get(i),loanTypeViewHolder.txtIdentifiedDate.getText().toString());

//                    syncCallbackInterface.updateItemCallback(i,rawDataTableList.get(i),loanTypeViewHolder.txtIdentifiedDate.getText().toString());
//                    syncCallbackInterface.updateItemCallback(i,rawDataTableList.get(i),rawDataTableList.get(i).getId());
                }
            });

            loanTypeViewHolder.txtControlDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!loanTypeViewHolder.txtIdentifiedDate.getText().toString().isEmpty()){

                        final Calendar currentDate = Calendar.getInstance();
                    Calendar date = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

//                            }
                            date.set(year, monthOfYear, dayOfMonth);

//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                            String  strSelectDatePlantingDate = simpleDateFormat.format(date.getTime());
//                        strSelectDate_updated =  simpleDateFormat.format(date.getTime());
                            // txtDate.setText(strSelectDate);

                            SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
//                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
//                        SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY2, Locale.US);
                            String strdisplayDate = displayDate.format(date.getTime());
                            loanTypeViewHolder.txtControlDate.setText(strSelectDatePlantingDate);
//                            loanTypeViewHolder.txtIdentifiedDate.setText(strdisplayDate);
                            syncCallbackInterface.updateItemCallback(i,rawDataTableList.get(i),strSelectDatePlantingDate);

//                        if (!txtDate.getText().toString().isEmpty())
//                        {
//                            llQtyFertlizer.setVisibility(View.VISIBLE);
//                        }else {
//                            llQtyFertlizer.setVisibility(View.GONE);
//                        }
//                        new TimePickerDialog(SinglePlotLogBookActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                                date.set(Calendar.MINUTE, minute);
//                                Log.v(TAG, "The choosen one " + date.getTime());
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, Locale.US);
//                                String strSelectDate = simpleDateFormat.format(date.getTime());
//                                txtDate.setText(strSelectDate);
//                            }
//                        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
                        }
                    }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
                    DatePicker datePicker = datePickerDialog.getDatePicker();
//                Calendar c = Calendar.getInstance();
//                c.add(Calendar.DATE, -1);
//                datePicker.setMinDate(c.getTimeInMillis());
                    //   datePicker.setMinDate(System.currentTimeMillis() - 1000);

                    final Calendar calendar3 = Calendar.getInstance();

//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    //create instance of the Calendar class and set the date to the given date
//                Calendar cal = Calendar.getInstance();
//                try{
//                    cal.setTime(sdf.parse(strSelectDate));
//                }catch(ParseException e){
//                    e.printStackTrace();
//                }

//                String test = getAddedDays(strSowingDate, 120);
//                String test = cal;
                    String str = loanTypeViewHolder.txtIdentifiedDate.getText().toString();
//                    String str = rawDataTableList.get(i).get;
                    String[] splited = str.split("-");
                    calendar3.set(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]) - 1, Integer.parseInt(splited[2]));
                    datePicker.setMinDate(calendar3.getTimeInMillis());

//                datePicker.setMinDate(System.currentTimeMillis());
                    datePickerDialog.show();
//                    syncCallbackInterface.updateItemCallback(i,rawDataTableList.get(i),loanTypeViewHolder.txtControlDate.getText().toString());
//                    syncCallbackInterface.updateItemCallback(i,rawDataTableList.get(i),rawDataTableList.get(i).getId());
                }}
            });

//            loanTypeViewHolder.txtControlDate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final Calendar currentDate = Calendar.getInstance();
//                    Calendar date = Calendar.getInstance();
//                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                            date.set(year, monthOfYear, dayOfMonth);
//
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
//                            SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
//                            String strdisplayDate = displayDate.format(date.getTime());
//                            String strOfferDate = simpleDateFormat.format(date.getTime());
//                            loanTypeViewHolder.txtControlDate.setText(strdisplayDate);
//
////                        edtExpectedPlantingDate.setText("");
////                        String strFinalDate="";
//
//                        }
//                    }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
//                    DatePicker datePicker = datePickerDialog.getDatePicker();
////                datePicker.setMaxDate(System.currentTimeMillis()); // removed
//                    datePickerDialog.show();
//                    syncCallbackInterface.updateItemCallback(i,rawDataTableList.get(i),loanTypeViewHolder.txtControlDate.getText().toString());
//                }
//            });
//
//            loanTypeViewHolder.txtIdentifiedDate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final Calendar currentDate = Calendar.getInstance();
//                    Calendar date = Calendar.getInstance();
//                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                            date.set(year, monthOfYear, dayOfMonth);
//
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
//                            SimpleDateFormat displayDate = new SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY2, Locale.US);
//                            String strdisplayDate = displayDate.format(date.getTime());
//                            String strOfferDate = simpleDateFormat.format(date.getTime());
//                            loanTypeViewHolder.txtIdentifiedDate.setText(strdisplayDate);
//
////                        edtExpectedPlantingDate.setText("");
////                        String strFinalDate="";
//
//                        }
//                    }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
//                    DatePicker datePicker = datePickerDialog.getDatePicker();
////                datePicker.setMaxDate(System.currentTimeMillis()); // removed
//                    datePickerDialog.show();
//                    syncCallbackInterface.addPlotDetailsCallback(i,rawDataTableList.get(i),loanTypeViewHolder.txtIdentifiedDate.getText().toString());
//                }
//            });

            isAvailable(rawDataTableList.get(i), loanTypeViewHolder.txtControlDate);
            isAvailableIdentified(rawDataTableList.get(i), loanTypeViewHolder.txtIdentifiedDate);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isAvailable(PestTable seq, TextView checkBox){
        for(int i=0;i<rawDataTableListFilteredD20.size();i++){
            if(rawDataTableListFilteredD20.get(i).getPestId().equals(String.valueOf(seq.getId()))){
                if(rawDataTableListFilteredD20.get(i).getActive()) {
                    if(!rawDataTableListFilteredD20.get(i).getControlDate().isEmpty()){
                        checkBox.setText(rawDataTableListFilteredD20.get(i).getControlDate());
                        checkBox.setEnabled(false);
                    }
//                if(rawDataTableListFilteredD20.get(i).getActive().equals("true")) {

                    return true;
                }
            }
        }

        return false;
    }

    private boolean isAvailableIdentified(PestTable seq, TextView checkBox){
        for(int i=0;i<rawDataTableListFilteredD20.size();i++){
            if(rawDataTableListFilteredD20.get(i).getPestId().equals(String.valueOf(seq.getId()))){
                if(rawDataTableListFilteredD20.get(i).getActive()) {
                    if(!rawDataTableListFilteredD20.get(i).getIdentifiedDate().isEmpty()){
                        checkBox.setText(rawDataTableListFilteredD20.get(i).getIdentifiedDate());
                        checkBox.setEnabled(false);
                    }
//                if(rawDataTableListFilteredD20.get(i).getActive().equals("true")) {
                    return true;
                }
            }
        }

        return false;
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
//
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
//                    List<PestTable> filteredList = new ArrayList<>();
//                    for (int i = rawDataTableList.size(); i >= 1; i--) {
//                        filteredList.add(rawDataTableList.get(i - 1));
//                    }
//                    rawDataTableListFiltered = filteredList;
//
//                } else {
//                    List<PestTable> filteredList = new ArrayList<>();
//                    for (PestTable row : rawDataTableList) {
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
//                rawDataTableListFiltered = (List<PestTable>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtControlDate,txtIdentifiedDate;
//        CardView card_view;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtIdentifiedDate = (TextView) itemView.findViewById(R.id.txtIdentifiedDate);
            txtControlDate = (TextView) itemView.findViewById(R.id.txtControlDate);


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
        void openScreenCallback(int position, PestTable farmerTable, List<PestTable> farmer, String applicationType);

        void updateItemCallback(int position, PestTable applicationStatusTable, String strFarmerID);

        void addPlotDetailsCallback(int position, PestTable applicationStatusTable, String strFarmercode);
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