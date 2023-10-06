package com.trst01.locationtracker.uiLibrary.widgets.customEditText;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.trst01.locationtracker.R;
import com.trst01.locationtracker.uiLibrary.helpers.AppConstants;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.uiLibrary.helpers.Verhoeff;


public abstract class EditTextTextWatcher implements TextWatcher {

    private static final String TAG = EditTextTextWatcher.class.getCanonicalName();

    private AppHelper appHelper;
    private CustomEditText mCustomEditText;
    private String currentText = "";
//    private CharSequence hint = "";
//    private CharSequence hintWithAsterisk = "";
    private Context context;

    public EditTextTextWatcher(final Context context, CustomEditText customTextInputLayout) {
        try {
            this.context = context;
            this.appHelper = new AppHelper(context);
            this.mCustomEditText = customTextInputLayout;
            currentText = mCustomEditText.getText().toString().trim();
//            hintWithAsterisk = mCustomEditText.getHint();
//            String str = hintWithAsterisk.toString();
//            if (mCustomEditText.isMandatory()){
//                hint = str.subSequence(0, str.length() - 2);
//            } else {
//                hint = str;
//            }
//            if (TextUtils.isEmpty(currentText)){
//                mCustomEditText.setHint(null);
//                mCustomEditText.setHint(hintWithAsterisk);
//            }
            mCustomEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    try {
                        if (!hasFocus) {
                            if (!currentText.isEmpty()) {
                                if (mCustomEditText.hasValidInput()) {
                                    mCustomEditText.setErrorMsg("");
                                } else if (mCustomEditText.hasValidInput()){
                                    mCustomEditText.setErrorMsg(mCustomEditText.getErrorMsg());
                                } else if (TextUtils.isEmpty(mCustomEditText.getErrorMsg())){
                                    mCustomEditText.setErrorMsg(context.getString(R.string.MANDATORY_LENGTH_ERROR));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i(TAG, "INSIDE beforeTextChanged ---> ");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i(TAG, "INSIDE onTextChanged ---> ");
        try {
            currentText = s.toString();
            mCustomEditText.setErrorMsg("");
            boolean moveToNext = false;
            String tag = "";
            String error = "";
            int iLength = -1;
            if (mCustomEditText.getTag() != null){
                tag = mCustomEditText.getTag().toString();
                Log.i(TAG, "TAG VALUE ---> " + tag);
                iLength = Integer.parseInt(context.getString(getIdentifier(tag + "_LENGTH")));
            }

            if (!TextUtils.isEmpty(tag)){
                if (s.length() > 0){
                    if (s.length() < iLength){
                        error = context.getString(getIdentifier(tag + "_LENGTH_ERROR"));
                    } else {
                        if (tag.equals("AADHAAR") || tag.equals("VID")){
                            if (!Verhoeff.validateVerhoeff(s.toString())){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("MOBILE")){
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, s.toString())){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("PANCARD")){
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PANCARD, s.toString())){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("VOTERID")){
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_VOTERID, s.toString())){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("AGE")){
                            if (Integer.parseInt(s.toString()) < 18){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR_18"));
                            } else if (Integer.parseInt(s.toString()) > 75){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR_75"));
                            } else{
                                moveToNext = true;
                            }
                        } else if (tag.equals("VINTAGE")){
                            if (Integer.parseInt(s.toString()) == 0 || Integer.parseInt(s.toString()) > 11){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("AREA")){
                            if (Integer.parseInt(s.toString()) < 50){
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else {
                            moveToNext = true;
                        }
                    }
                } else {
                    error = context.getString(R.string.EMPTY_ERROR);
                }
            } else {
                moveToNext = true;
            }
            Log.i(TAG, "ERROR VALUE ---> " + error);
            mCustomEditText.setErrorMsg(error);
            onValidated(moveToNext, mCustomEditText, mCustomEditText.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i(TAG, "INSIDE afterTextChanged ---> ");
        try {
            if (!mCustomEditText.hasValidInput()){
                mCustomEditText.setErrorMsg(mCustomEditText.getErrorMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void onValidated(boolean moveToNext, CustomEditText name, String trim);

    private int getIdentifier(String tag){
        int id = -1;
        try {
            id = context.getResources().getIdentifier(tag, "string", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
