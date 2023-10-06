package com.trst01.locationtracker.uiLibrary.widgets.textInputLayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.trst01.locationtracker.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class CustomTextInputLayout extends TextInputLayout {

    private boolean isMandatory;
    private String errorMsg;
    private boolean hasValidInput;
    private Object collapsingTextHelper;
    private Rect bounds;
    private Method recalculateMethod;

    public CustomTextInputLayout(Context context) {
        this(context, null, R.style.CustomTextInputLayout);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        try {
            Field cthField = TextInputLayout.class.getDeclaredField("mCollapsingTextHelper");
            cthField.setAccessible(true);
            collapsingTextHelper = cthField.get(this);


            Field boundsField = collapsingTextHelper.getClass().getDeclaredField("mCollapsedBounds");
            boundsField.setAccessible(true);
            bounds = (Rect) boundsField.get(collapsingTextHelper);

            recalculateMethod = collapsingTextHelper.getClass().getDeclaredMethod("recalculate");

        } catch (Exception e) {
            collapsingTextHelper = null;
            bounds = null;
            recalculateMethod = null;
//            e.printStackTrace();
        }
    }

    public boolean hasValidInput() {
        return this.hasValidInput;
    }

    public void setHasValidInput(boolean hasValidInput) {
        this.hasValidInput = hasValidInput;
        if (!hasValidInput){
            setError(errorMsg);
            setErrorTextAppearance(R.style.TextInputLayoutErrorColor);
        } else {
            setError("");
        }
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        /*if (!TextUtils.isEmpty(errorMsg)){
            setHasValidInput(false);
        } else {
            setHasValidInput(true);
        }*/
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public void setHint(String hint, boolean isMandatory){
        setMandatory(isMandatory);
        setHint(hint);

    }

    @Override
    public void setHint(@Nullable CharSequence hint) {
        String tag = "";
        if (getTag() != null){
            tag = getTag().toString().trim();
        }
        SpannableStringBuilder hintWithAsterisk = null;
        try {
            String sHint;
            if (hint != null) {
                if ( isMandatory){
                    sHint = hint.toString();
                    hintWithAsterisk = setStarToLabel(sHint);
                    setMandatory(true);
                } else {
                    hintWithAsterisk = new SpannableStringBuilder(hint);
                    setMandatory(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setHint(hintWithAsterisk);
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
