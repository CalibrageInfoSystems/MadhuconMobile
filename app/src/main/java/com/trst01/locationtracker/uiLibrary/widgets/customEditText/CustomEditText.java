package com.trst01.locationtracker.uiLibrary.widgets.customEditText;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {

    private static final String TAG = CustomEditText.class.getCanonicalName();

    private boolean isMandatory;
    private String errorMsg;
    private boolean hasValidInput;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHasValidInput(boolean hasValidInput) {
        Log.i(TAG, "inside setHasValidInput ---> ");
        this.hasValidInput = hasValidInput;
        if (!hasValidInput){
            Log.i(TAG, "errorMsg---> " + errorMsg);
            requestFocus();
            setError(errorMsg);
        } else {
            setError(null);
        }
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public boolean hasValidInput() {
        return this.hasValidInput;
    }

    public void setErrorMsg(String errorMsg) {
        Log.i(TAG, "inside setErrorMsg ---> ");
        this.errorMsg = errorMsg;
        try {
            if (!TextUtils.isEmpty(errorMsg)){
                setHasValidInput(false);
            } else {
                setHasValidInput(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    @Override
    public void setTag(Object tag) {
        Log.i(TAG, "inside setTag ---> ");
        String strTag = "";
        if (getTag() != null){
            strTag = getTag().toString().trim();
        }
        SpannableStringBuilder hintWithAsterisk = null;
        try {
            String sHint = this.getHint().toString();
            if ( !TextUtils.isEmpty(strTag)){
                hintWithAsterisk = setStarToLabel(sHint);
                setMandatory(true);
            } else {
                hintWithAsterisk = new SpannableStringBuilder(sHint);
                setMandatory(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setHint(hintWithAsterisk);
        super.setTag(tag);
    }

    private SpannableStringBuilder setStarToLabel(String text) {
        SpannableStringBuilder builder = null;
        try {
            String colored = " *";
            builder = new SpannableStringBuilder();
            builder.append(text);
            int start = builder.length();
            builder.append(colored);
            int end = builder.length();
            builder.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }
}
