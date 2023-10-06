package com.trst01.locationtracker.uiLibrary.dialogs;

import android.content.Context;

public class DialogHelper {

    private Context context;
    private LoadingDialog loadingDialog;
    private ConfirmationDialog confirmationDialog;

    public DialogHelper(Context context) {
        this.context = context;
    }

    public LoadingDialog getLoadingDialog() {
//        if (loadingDialog == null){
//            loadingDialog = new LoadingDialog(context);
//        }
        closeDialog();
        return loadingDialog = new LoadingDialog(context);
    }

    public ConfirmationDialog getConfirmationDialog() {
//        if (confirmationDialog == null){
//            confirmationDialog = new ConfirmationDialog(context);
//        }
//        return confirmationDialog;
        closeDialog();
        return confirmationDialog = new ConfirmationDialog(context);
    }

    public void closeDialog(){
        try {
            if (loadingDialog != null){
                loadingDialog.closeDialog();
                loadingDialog = null;
            }
            if (confirmationDialog != null){
                confirmationDialog.closeDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
