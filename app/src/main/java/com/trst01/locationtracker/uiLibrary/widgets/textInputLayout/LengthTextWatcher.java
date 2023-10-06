package com.trst01.locationtracker.uiLibrary.widgets.textInputLayout;

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


public abstract class LengthTextWatcher implements TextWatcher {

    private static final String TAG = LengthTextWatcher.class.getCanonicalName();

    private AppHelper appHelper;
    private CustomTextInputLayout mCustomTextInputLayout;
    private String currentText = "";
    private CharSequence hint = "";
    private CharSequence hintWithAsterisk = "";
    private Context context;

    public LengthTextWatcher(final Context context, CustomTextInputLayout customTextInputLayout) {
        try {
            this.context = context;
            this.appHelper = new AppHelper(context);
            this.mCustomTextInputLayout = customTextInputLayout;
            currentText = mCustomTextInputLayout.getEditText().getText().toString().trim();
            hintWithAsterisk = mCustomTextInputLayout.getHint();
            String str = hintWithAsterisk.toString();
            if (mCustomTextInputLayout.isMandatory()) {
                hint = str.subSequence(0, str.length() - 2);
            } else {
                hint = str;
            }
            if (TextUtils.isEmpty(currentText)) {
                mCustomTextInputLayout.setHint(null);

                mCustomTextInputLayout.getEditText().setHint(hintWithAsterisk);
            }
            mCustomTextInputLayout.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    try {
                        if (!hasFocus) {
                            if (TextUtils.isEmpty(currentText)) {
                                mCustomTextInputLayout.setHint(null);
                                mCustomTextInputLayout.getEditText().setHint(hintWithAsterisk);
                            } else {
                                mCustomTextInputLayout.setHint(hint);
                                mCustomTextInputLayout.getEditText().setHint(null);
                            }
                            if (!currentText.isEmpty()) {
                                if (mCustomTextInputLayout.hasValidInput()) {
                                    mCustomTextInputLayout.setErrorMsg("");
                                    mCustomTextInputLayout.setHasValidInput(true);
                                } else {
                                    if (TextUtils.isEmpty(mCustomTextInputLayout.getErrorMsg())) {
                                        mCustomTextInputLayout.setErrorMsg(context.getString(R.string.MANDATORY_LENGTH_ERROR));
                                        mCustomTextInputLayout.setHasValidInput(false);
                                    }
                                }
                            }
                        } else {
                            mCustomTextInputLayout.setHint(hint);
                            mCustomTextInputLayout.getEditText().setHint(null);
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
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            currentText = s.toString();
            if (mCustomTextInputLayout.getHint() == null) {
                if (currentText.length() > 0) {
                    mCustomTextInputLayout.setHint(hint);
                } else {
                    mCustomTextInputLayout.setHint(null);
                }
            } else {
                mCustomTextInputLayout.getEditText().setHint(null);
            }
            mCustomTextInputLayout.setErrorEnabled(false);
            mCustomTextInputLayout.setError("");
            boolean moveToNext = false;
            String tag = "";
            String error = "";
            int iLength = -1;
            if (mCustomTextInputLayout.getTag() != null) {
                tag = mCustomTextInputLayout.getTag().toString();
                Log.i(TAG, "TAG VALUE ---> " + tag);
                iLength = Integer.parseInt(context.getString(getIdentifier(tag + "_LENGTH")));
            }

            if (!TextUtils.isEmpty(tag)) {
                if (s.length() > 0) {
                    if (s.length() < iLength) {
                        error = context.getString(getIdentifier(tag + "_LENGTH_ERROR"));
                    } else {
                        if (tag.equals("AADHAAR") || tag.equals("VID")) {
                            if (!Verhoeff.validateVerhoeff(s.toString())) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("MOBILE")) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, s.toString())) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("PANCARD")) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PANCARD, s.toString())) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("VOTERID")) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_VOTERID, s.toString())) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("AGE")) {
                            if (Integer.parseInt(s.toString()) < 18) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR_18"));
                            } else if (Integer.parseInt(s.toString()) > 75) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR_75"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("VINTAGE")) {
                            if (Integer.parseInt(s.toString()) == 0 || Integer.parseInt(s.toString()) > 11) {
                                error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equals("AREA")) {
                            if (Integer.parseInt(s.toString()) < 50) {
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
            mCustomTextInputLayout.setErrorMsg(error);
            mCustomTextInputLayout.setHasValidInput(moveToNext);
            onValidated(moveToNext, mCustomTextInputLayout, mCustomTextInputLayout.getEditText().getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    protected abstract void onValidated(boolean moveToNext, CustomTextInputLayout name, String trim);

    private int getIdentifier(String tag) {
        int id = -1;
        try {
            id = context.getResources().getIdentifier(tag, "string", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
