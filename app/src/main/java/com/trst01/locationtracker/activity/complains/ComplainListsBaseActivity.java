package com.trst01.locationtracker.activity.complains;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.trst01.locationtracker.constant.AppHelper;


public class ComplainListsBaseActivity extends AppCompatActivity {
    AppHelper appHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_complain_lists_base);
        appHelper=new AppHelper(this);
    }

//    @Override
//    public void onBackPressed() {
//        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to leave from this screen ? ", new ConfirmationDialog.ActionCallback() {
//            @Override
//            public void onAction() {
//                finish();
//            }
//        });
//    }
}