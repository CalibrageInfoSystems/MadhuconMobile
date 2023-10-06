package com.trst01.locationtracker.uiLibrary.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.uiLibrary.activities.FullscreenImageActivity;
import com.trst01.locationtracker.uiLibrary.dialogs.DialogHelper;
import com.trst01.locationtracker.uiLibrary.widgets.customEditText.CustomEditText;
import com.trst01.locationtracker.uiLibrary.widgets.customSpinner.SearchableSpinner;
import com.trst01.locationtracker.uiLibrary.widgets.textInputLayout.CustomTextInputLayout;

import java.io.InputStream;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppHelper extends AppCompatActivity {

    private static final String TAG = AppHelper.class.getCanonicalName();
    private Context context;

    private DialogHelper dialogHelper;

    public AppHelper(Context context) {
        this.context = context;
    }

    public DialogHelper getDialogHelper() {
        if (dialogHelper == null){
            dialogHelper = new DialogHelper(context);
        }
        return dialogHelper;
    }



    public void showToast(String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTILError(String message, TextInputLayout textInputLayout) {
        try {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideTIL(TextInputLayout textInputLayout) {
        try {
            textInputLayout.getEditText().setText("");
            textInputLayout.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTIL(TextInputLayout textInputLayout) {
        try {
            textInputLayout.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isGPSEnabled() {
        try {
            LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return statusOfGPS;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public boolean isValid = true;
    public boolean checkErrors(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            Log.i(TAG, "Checking with" + view.toString());
            try {
                if (view.getVisibility() == View.VISIBLE){
                    if (view instanceof CustomTextInputLayout && view.getTag() != null) {

                        if (TextUtils.isEmpty(((CustomTextInputLayout) view).getEditText().getText().toString().trim())) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            ((CustomTextInputLayout) view).setErrorMsg(context.getString(R.string.EMPTY_ERROR));
                            ((CustomTextInputLayout) view).setHasValidInput(false);
                            Log.i(TAG, "error with CustomTextInputLayout" + view.toString());
                        } else if (!((CustomTextInputLayout) view).hasValidInput()) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            Log.i(TAG, "error with CustomTextInputLayout" + view.toString());
                        }
                    }

                    if (view instanceof CustomEditText && view.getTag() != null) {
                        if (TextUtils.isEmpty(((CustomEditText) view).getText().toString().trim())) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            ((CustomEditText) view).setErrorMsg(context.getString(R.string.EMPTY_ERROR));
                            Log.i(TAG, "error with CustomEditText" + view.toString());
                        } else if (!((CustomEditText) view).hasValidInput()) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            ((CustomEditText) view).setErrorMsg(((CustomEditText) view).getErrorMsg());
                            Log.i(TAG, "error with CustomEditText" + view.toString());
                        }
                    }

                    if (view instanceof TextInputEditText && view.getTag() != null) {
                        if (TextUtils.isEmpty(((TextInputEditText) view).getText().toString().trim())) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            ((TextInputEditText) view).setError(context.getString(R.string.EMPTY_ERROR));
                            Log.i(TAG, "error with TextInputEditText" + view.toString());
                        }
                    }

                    if (view instanceof SearchableSpinner && view.getTag() != null
                            && ((SearchableSpinner) view).getAdapter() != null
                            && ((SearchableSpinner) view).getAdapter().getCount() > 0) {
                        if (((SearchableSpinner) view).getSelectedItemPosition() < 1) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            ((SearchableSpinner) view).setHasValidInput(false);
                            Log.i(TAG, "error with SearchableSpinner" + view.toString());
                        } else if (!((SearchableSpinner) view).isHasValidInput()) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            ((SearchableSpinner) view).setHasValidInput(false);
                            Log.i(TAG, "error with SearchableSpinner" + view.toString());
                        }
                    }

                    if (view instanceof AppCompatSpinner && view.getTag() != null
                            && ((AppCompatSpinner) view).getAdapter() != null
                            && ((AppCompatSpinner) view).getAdapter().getCount() > 0) {
                        if (((AppCompatSpinner) view).getSelectedItemPosition() < 1) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            TextView textView = (TextView) ((AppCompatSpinner) view).getSelectedView();
                            if (textView != null){
                                textView.setError("Please select");
                            }
                            Log.i(TAG, "error with AppCompatSpinner" + view.toString());
                        }
                    }

                    if (view instanceof Spinner && view.getTag() != null
                            && ((Spinner) view).getAdapter() != null
                            && ((Spinner) view).getAdapter().getCount() > 0) {
                        if (((Spinner) view).getSelectedItemPosition() < 1) {
                            isValid = false;
                            view.getParent().requestChildFocus(view, view);
                            TextView textView = (TextView) ((Spinner) view).getSelectedView();
                            if (textView != null){
                                textView.setError("Please select");
                            }
                            Log.i(TAG, "error with Spinner" + view.toString());
                        }
                    }

                    if (view instanceof RadioGroup && view.getTag() != null) {
                        if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
                            isValid = false;
                            int total = ((RadioGroup) view).getChildCount();
                            for (int j = 0; j < total; j++) {
                                View v = ((RadioGroup) view).getChildAt(j);
                                if (v instanceof RadioButton) {
                                    ((RadioButton) v).setError("Please check");
                                }
                            }
                            view.getParent().requestChildFocus(view, view);
                            Log.i(TAG, "error with RadioGroup" + view.toString());
                        }
                    }

                    if (view instanceof CheckBox && view.getTag() != null) {
                        if (!((CheckBox) view).isChecked()){
                            isValid = false;
                            ((CheckBox) view).setError("Please check");
                            Log.i(TAG, "error with CheckBox" + view.toString());
                        }
                    }
                    if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)){
                        checkErrors((ViewGroup) view);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    public void viewsVisibility(int visible, ViewGroup group) {
        try {
            if (group instanceof LinearLayout){
                group.setVisibility(visible);
            }
            for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                View view = group.getChildAt(i);
                if (view instanceof CustomTextInputLayout
                        || view instanceof SearchableSpinner
                        || view instanceof CustomEditText
                        || view instanceof Spinner
                        || view instanceof EditText
                        || view instanceof RadioGroup
                        || view instanceof CardView) {
                    view.setVisibility(visible);
                }

                if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)){
                    viewsVisibility(visible, (ViewGroup) view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAllData(ViewGroup group) {
        try {
            for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                View view = group.getChildAt(i);
                if (view.getVisibility() == View.VISIBLE){
                    if (view instanceof CustomTextInputLayout && view.getVisibility() == View.VISIBLE) {
                        ((CustomTextInputLayout) view).getEditText().setText("");
                        ((CustomTextInputLayout) view).getEditText().setError(null);
                        ((CustomTextInputLayout) view).getEditText().setEnabled(true);
                    }

                    if (view instanceof CustomEditText && view.getVisibility() == View.VISIBLE) {
                        ((CustomEditText) view).setText("");
                        ((CustomEditText) view).setError(null);
                        ((CustomEditText) view).setEnabled(true);
                    }

                    if (view instanceof SearchableSpinner && view.getVisibility() == View.VISIBLE) {
                        if (((SearchableSpinner) view).getAdapter() != null
                                && ((SearchableSpinner) view).getAdapter().getCount() > 0){
                            ((SearchableSpinner) view).setSelection(0);
                        }
                    }

                    if (view instanceof Spinner && view.getVisibility() == View.VISIBLE) {
                        if (((Spinner) view).getAdapter() != null
                                && ((Spinner) view).getAdapter().getCount() > 0){
                            ((Spinner) view).setSelection(0);
                        }
                    }

                    if (view instanceof RadioGroup && view.getVisibility() == View.VISIBLE) {
                        ((RadioGroup) view).clearCheck();
                        ((RadioGroup) view).setEnabled(true);
                    }

                    if (view instanceof CheckBox && view.getVisibility() == View.VISIBLE) {
                        ((CheckBox) view).setChecked(false);
                        ((CheckBox) view).setEnabled(true);
                    }

                    if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                        clearAllData((ViewGroup) view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disableViews(ViewGroup group) {
        try {
            for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                View view = group.getChildAt(i);
                if (view.getVisibility() == View.VISIBLE){
                    if (view instanceof CustomTextInputLayout && view.getVisibility() == View.VISIBLE) {
                        ((CustomTextInputLayout) view).getEditText().setEnabled(false);
                    }

                    if (view instanceof CustomEditText && view.getVisibility() == View.VISIBLE) {
                        ((CustomEditText) view).setEnabled(false);
                    }

                    if (view instanceof Spinner && view.getVisibility() == View.VISIBLE) {
                        ((Spinner) view).setEnabled(false);
                    }

                    if (view instanceof SearchableSpinner && view.getVisibility() == View.VISIBLE) {
                        ((SearchableSpinner) view).setEnabled(false);
                    }

                    if (view instanceof RadioGroup && view.getTag() != null) {
                        if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
                            int total = ((RadioGroup) view).getChildCount();
                            for (int j = 0; j < total; j++) {
                                View v = ((RadioGroup) view).getChildAt(j);
                                if (v instanceof RadioButton) {
                                    ((RadioButton) v).setEnabled(false);
                                }
                            }
                        }
                    }

                    if (view instanceof CheckBox && view.getVisibility() == View.VISIBLE) {
                        ((CheckBox) view).setEnabled(false);
                    }

                    if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                        clearAllData((ViewGroup) view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTILText(TextInputLayout customTextInputLayout) {
        return customTextInputLayout.getEditText().getText().toString().trim();
    }

    public void setTILText(TextInputLayout customTextInputLayout, String message, boolean enable) {
        try {
            customTextInputLayout.getEditText().setText(message);
            customTextInputLayout.setEnabled(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSpinnerText(Spinner spinner) {
        String text = "";
        try {
            if (spinner.getSelectedItemPosition() > 0){
                text = spinner.getSelectedItem().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public boolean isNetworkAvailable() {
        boolean isAvailable = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null){
                @SuppressLint("MissingPermission")
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    isAvailable = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAvailable;
    }

    public void showDatePicker(final Context context , final View etDate,
                               final boolean isShowDefValue, final String fromDate, final String toDate) {
        try {
            final Calendar myCalendar = Calendar.getInstance();
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT_MMM_DD_YYYY, Locale.US);
                    if(etDate instanceof EditText) {
                        Log.i(TAG , "inside Date EditText OnClick -->" + sdf.format(myCalendar.getTime()));
                        ((EditText)etDate).setText(sdf.format(myCalendar.getTime()));
                    } else if(etDate instanceof TextView) {
                        Log.i(TAG , "inside Date TextView OnClick -->" + sdf.format(myCalendar.getTime()));
                        ((TextView)etDate).setText(sdf.format(myCalendar.getTime()));
                    }
                }

            };

            if(isShowDefValue){
                String strCurrDate = getCurrentDate(AppConstants.DATE_FORMAT_MMM_DD_YYYY);
                if(etDate instanceof EditText) {
                    Log.i(TAG , "inside Date EditText strCurrDate -->" + strCurrDate);
                    ((EditText)etDate).setText(strCurrDate);
                } else if(etDate instanceof TextView) {
                    Log.i(TAG , "inside Date TextView strCurrDate -->" + strCurrDate);
                    ((TextView)etDate).setText(strCurrDate);
                }
            }else {
                if(etDate instanceof EditText) {
                    ((EditText)etDate).setText("");
                } else if(etDate instanceof TextView) {
                    ((TextView)etDate).setText("");
                }
            }

            etDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG , "inside etDate setOnClickListener -->" );
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context , date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));

                    Date fromLDate = null , toLDate = null , lessFromDate = null,lessToDate = null;

                    if(toDate != null && !toDate.equals("")){
                        Calendar myCalendar = Calendar.getInstance();
                        toLDate = convertStringToDate(toDate , AppConstants.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS);
                        myCalendar.setTime(toLDate);
                        myCalendar.set(Calendar.HOUR_OF_DAY, myCalendar.getMaximum(Calendar.HOUR_OF_DAY));
                        myCalendar.set(Calendar.MINUTE, myCalendar.getMaximum(Calendar.MINUTE));
                        myCalendar.set(Calendar.SECOND, myCalendar.getMaximum(Calendar.SECOND));
                        myCalendar.set(Calendar.MILLISECOND, myCalendar.getMaximum(Calendar.MILLISECOND));
                        datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                    }
                    if(fromDate != null && !fromDate.equals("")){
                        Calendar myCalendar1 = Calendar.getInstance();
                        fromLDate = convertStringToDate(fromDate , AppConstants.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS);
                        myCalendar1.setTime(fromLDate);
                        myCalendar1.set(Calendar.HOUR_OF_DAY, myCalendar1.getMinimum(Calendar.HOUR_OF_DAY));
                        myCalendar1.set(Calendar.MINUTE, myCalendar1.getMinimum(Calendar.MINUTE));
                        myCalendar1.set(Calendar.SECOND, myCalendar1.getMinimum(Calendar.SECOND));
                        myCalendar1.set(Calendar.MILLISECOND, myCalendar1.getMinimum(Calendar.MILLISECOND));
                        datePickerDialog.getDatePicker().setMinDate(myCalendar1.getTimeInMillis());
                    }

                    datePickerDialog.show();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Date convertStringToDate(String strDate, String strDateFormat1) {
        Date dstrCurrDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat1);
            dstrCurrDate = sdf.parse(strDate);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dstrCurrDate;
    }


    public String convertJSONDateToGivenDateFormat(String strJsonDate , String strDateFormat) {
        String strFormattedDate = strJsonDate;
        try {
            if(strFormattedDate != null && strFormattedDate.contains("Date")) {
                System.out.println("Date Format ----" + strFormattedDate);

                String timestamp = strJsonDate.split("\\(")[1].split("\\+")[0];
                Date createdOn = new Date(Long.parseLong(timestamp));

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                strFormattedDate = sdf.format(createdOn);
            }
            System.out.println("Result Date Format ----" + strFormattedDate);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return strFormattedDate;
    }

    public String convertDateToJSONFormat(String strDate , String strDateFormat) {
        String strCurrDateJson = null;
        try {
            Date currDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            if(strDate == null) {
                strDate = sdf.format(new Date());
            }
            currDate = sdf.parse(strDate);
            if(currDate != null) {
                strCurrDateJson = "/Date("+currDate.getTime()+"+0000)/";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return strCurrDateJson;
    }

    public String getCurrentDate(String strDateFormat) {
        String strCurrDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Calendar c1 = Calendar.getInstance(); // today
            strCurrDate = sdf.format(c1.getTime());
            //CspLog.WriteLog(TAG,"INFO",  "Today Date is " + strCurrDate + "::Format ::" + sdf.format(c1.getTime()));
            //CspLog.WriteLog(TAG,"INFO","Today Date is " + strCurrDate + "::Format ::" + sdf.format(c1.getTime()));
        } catch(Exception e) {
            //CspLog.WriteLog(TAG,"ERROR", "Exception in getCurrentDate :: "+e);
        }
        return strCurrDate;
    }

    public String getMonthByDate(String strDateFormat,String strDate) {
        
        String strMonth = null;
        try {
            Date date = convertStringToDate(strDate ,strDateFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            strMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
            strMonth= strMonth+ " "+cal.get(Calendar.YEAR);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return strMonth;
    }

    public String getCurrentDateTime(String strDateFormat) {
        String strCurrDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Calendar c1 = Calendar.getInstance(); // today
            strCurrDate = sdf.format(c1.getTime());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return strCurrDate;
    }


    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999

        SecureRandom  rnd=new SecureRandom();

//        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public String convertDateToGivenDateFormat(String strDate , String strDateFormat1 , String outputDateFormat) {
        String strCurrDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat1);
            Date dstrCurrDate = sdf.parse(strDate);
            SimpleDateFormat sdf2 = new SimpleDateFormat(outputDateFormat);
            strCurrDate = sdf2.format(dstrCurrDate);
            Log.i(TAG , "Result Date->" + strCurrDate);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return strCurrDate;
    }

    public boolean compareTwoDates(String strDate , String strDateFormat1 , String outputDateFormat) {
        boolean isDateSame = false;
        try {
            String strDate1 = convertDateToGivenDateFormat(strDate,strDateFormat1 , outputDateFormat);
            System.out.println("Converted Date -->"+strDate1);
            String strDate2 = getCurrentDate(outputDateFormat);
            System.out.println("Converted Date -->"+strDate2);
            if(strDate1.equals(strDate2)) {
                System.out.println("Same Date -->"+strDate2);
                isDateSame = true;
            } else {
                System.out.println("Same Date 3333-->"+strDate2);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return isDateSame;
    }

    public boolean isEmailValid(String strEmail) {
        boolean isValidEmail = false;
        try {
            Pattern patternObj = Pattern.compile(AppConstants.EMAIL_VALIDATION_PATTERN);
            Matcher matcherObj = patternObj.matcher(strEmail);
            if(matcherObj.matches()) {
                isValidEmail = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return isValidEmail;
    }

    public String convertDateTimeToJsonDateTime(String javaDate) {
        Date currentDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            currentDate = dateFormat.parse(javaDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  "/Date("+currentDate.getTime()+"+0000)/";
    }

    public String convertServerInputStreamToString(InputStream ins) {
        Scanner sc = null;
        String inputStreamString = "";
        try {
            sc = new Scanner(ins, "UTF-8");
            inputStreamString = sc.useDelimiter("\\A").next();
            ins.reset();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                sc.close();
                sc = null;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return inputStreamString;
    }

    public void askForPermission(Activity activity, String[] permission, Integer requestCode) {
        for (String aPermission : permission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, aPermission)) {
                ActivityCompat.requestPermissions(activity, permission, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, permission, requestCode);
            }
        }
    }

    public boolean patternMatch(String strRegexPattern, String data) {
        boolean isMatched = false;
        try {
            Pattern patternObj = Pattern.compile(strRegexPattern);
            Matcher matcherObj = patternObj.matcher(data);

            if(matcherObj.find()) {
                isMatched = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMatched;
    }


    public String getSelectedRadioButtonItem(View view, int iRadioGroupCheckedId) {
        String strSelectedItem = "";
        try {
            RadioButton rbRelation = (RadioButton) view.findViewById(iRadioGroupCheckedId);
            if (rbRelation != null){
                strSelectedItem = rbRelation.getText().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strSelectedItem;
    }

    public void checkRadioButton(RadioGroup radioGroup, String strItem,boolean enable) {
        try {
            List<RadioButton> rbList = new ArrayList<>();
            int total = radioGroup.getChildCount();
            for (int j = 0; j < total; j++) {
                RadioButton rb = (RadioButton) radioGroup.getChildAt(j);
                if (rb != null){
                    rbList.add(rb);
                    if (rb.getText().toString().equalsIgnoreCase(strItem)) {
                        rb.setChecked(true);
                    }
                }
            }
            for (int j = 0; j < rbList.size(); j++) {
                RadioButton rb = rbList.get(j);
                if (radioGroup.getCheckedRadioButtonId() != -1){
                    if(!enable) {
                        rb.setEnabled(false);
                    }else{
                        rb.setEnabled(true);
                    }
                } else {
                    rb.setEnabled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: 21-08-2019 created by Lakshmi
    public String getDOBbasedOnAge(String age) {
        int iage = Integer.parseInt(age);
        String dateOfBirth = "";
        try{
            Calendar today = Calendar.getInstance();
            int yearOfBirth =  today.get(Calendar.YEAR) - iage;
            dateOfBirth = "01-01-"+yearOfBirth;

        }catch (Exception e){
            e.printStackTrace();
        }
        return dateOfBirth;
    }

    public int getAgeBasedOnDOB(String strDOB){
        int iAge = 0;
        try {
            int year=0, month=0, day=0;
            String[] strDate = strDOB.split("-");
            if(strDate != null && strDate.length > 0) {
                day = Integer.parseInt(strDate[0]);
                month = Integer.parseInt(strDate[1]);
                year = Integer.parseInt(strDate[2]);
            }

            Calendar dob = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            dob.set(year, month-1, day);

            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                age--;
            }

            Integer ageInt = new Integer(age);
            iAge = ageInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iAge;
    }
    public int getbusinessvintageBasedOnYEAR(String strDOB){
        int iAge = 0;
        try {
            int year=0, month=0, day=0;
            String[] strDate = strDOB.split("-");
            if(strDate != null && strDate.length > 0) {
                day = Integer.parseInt(strDate[0]);
                month = Integer.parseInt(strDate[1]);
                year = Integer.parseInt(strDate[2]);
            }

            Calendar dob = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            dob.set(year, month-1, day);

            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

//            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
//                age--;
//            }

            Integer ageInt = new Integer(age);
            iAge = ageInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iAge;
    }

    public int getSpinnerPosition(Spinner spinner , String spinnerValue){
        int position = 0;
        try{
            if(spinnerValue != null && !spinnerValue.equals("")) {
                position = ((ArrayAdapter)spinner.getAdapter()).getPosition(spinnerValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    public String getCurrentDay() {
        String strCurrDay = null;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            strCurrDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  strCurrDay;
    }

    public String getVersion(){
        String version="";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public String getMobileIPAddress() {
        String ip = null;
        try {
            WifiManager wifiMan = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInf = wifiMan.getConnectionInfo();
            int ipAddress = wifiInf.getIpAddress();
            ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    @SuppressLint("MissingPermission")
    public String getIMEI() {
        String myAndroidDeviceId = "";
        try {
            TelephonyManager mTelephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null){
                myAndroidDeviceId = mTelephony.getDeviceId();
            } else{
                myAndroidDeviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            return myAndroidDeviceId;
        } catch (Exception e){
            e.printStackTrace();
            myAndroidDeviceId = "123451234512345";
        }
        return myAndroidDeviceId;
    }

    public SharedPreferences.Editor getAndCreateEditorObj() {
        SharedPreferences sharedPrefObj = null;
        SharedPreferences.Editor editorObj = null;
        try {
            sharedPrefObj = getSharedPrefObj();
            editorObj = sharedPrefObj.edit();
        } catch(Exception ex) {
            Log.e(TAG, "Exception getAndCreateEditorObj" + ex);
        }
        return editorObj;
    }

    public SharedPreferences getSharedPrefObj() {
        SharedPreferences sharedPrefObj = null;
        try {
            sharedPrefObj = context.getSharedPreferences(AppConstants.App_SHARED_PREF_NAME , Context.MODE_PRIVATE);
        } catch(Exception ex) {
            Log.e(TAG, "Exception getAndCreateSessionObj" + ex);
        }
        return sharedPrefObj;
    }

    public void showFullScreenImage(Context context, String filePath, String title){
        try {
            Intent intent = new Intent(context, FullscreenImageActivity.class);
            intent.putExtra("image", filePath);
            intent.putExtra("title", title);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom method to get all installed apps package name list
    public List<String> getInstalledAppsPackageNameList(Context context){
        // Initialize a new intent
        Intent intent = new Intent(Intent.ACTION_MAIN,null);

        // Set intent category
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        // Set intent flags
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        // Initialize a new list of resolve info
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent,0);

        // Initialize a new list of package name
        List<String> packageNameList = new ArrayList<>();

        for(ResolveInfo resolveInfo: resolveInfoList){

            // Get the activity info from resolve info
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            // Get the package name from activity info's application info
            // Add the package name to the list
            packageNameList.add(activityInfo.applicationInfo.packageName);
        }

        // Return the package name list
        return packageNameList;
    }

}
