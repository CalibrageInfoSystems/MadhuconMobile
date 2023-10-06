package com.trst01.locationtracker.uiLibrary.printer;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class CustomDialog {

    private Context context;
    private AlertDialog customDialog;

    public CustomDialog(Context context){
        this.context = context;
    }

    public void show(View dialogView){
        show(true, dialogView);
    }

    public void show(String title, String message, View dialogView){
        show(true, title, message, dialogView);
    }

    public void show(boolean isCancellable, View dialogView) {
        customDialog = new AlertDialog.Builder(context).create();
        customDialog.setView(dialogView);
        customDialog.setCancelable(isCancellable);

        customDialog.show();
    }

    public void show(boolean isCancellable, String title, String message, View dialogView) {

        customDialog = new AlertDialog.Builder(context).create();
        customDialog.setView(dialogView);
        customDialog.setTitle(title);
        customDialog.setMessage(message);
        customDialog.setCancelable(isCancellable);

        customDialog.show();
    }

    public void closeDialog(){
        if ( customDialog != null && customDialog.isShowing()){
            customDialog.dismiss();
        }
    }

}
