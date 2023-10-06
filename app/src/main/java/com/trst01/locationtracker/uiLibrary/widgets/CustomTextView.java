package com.trst01.locationtracker.uiLibrary.widgets;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        String tag = "";
        if (getTag() != null){
            tag = getTag().toString().trim();
        }
        SpannableStringBuilder hintWithAsterisk = null;
        try {
            String sHint;
            if (text != null) {
                if ( !TextUtils.isEmpty(tag)){
                    sHint = text.toString();
                    hintWithAsterisk = setStarToLabel(sHint);
                } else {
                    hintWithAsterisk = new SpannableStringBuilder(text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setText(hintWithAsterisk, type);
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
