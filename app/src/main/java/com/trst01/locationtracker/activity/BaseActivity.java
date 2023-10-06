package com.trst01.locationtracker.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;


public class BaseActivity extends AppCompatActivity {

    
    public AppHelper appHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appHelper=new AppHelper(this);
    }

}
