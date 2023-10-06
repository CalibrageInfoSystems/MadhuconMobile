package com.trst01.locationtracker.uiLibrary.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.trst01.locationtracker.R;


public class LoadingDialog {

    private Context context;
    private AlertDialog loadingDialog;

    public interface CallBack{
        void onItemClick(int position);
    }

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showGIFLoading(){
        showGIFLoading(false, 0);
    }
    public void showGIFLoading(boolean show, int gif) {
        try {
            if (gif == 0){
//                gif = R.raw.bfil_logo;
                gif = R.raw.loading;
            }
            loadingDialog = new AlertDialog.Builder(context).create();
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            View loadingDialogView = LayoutInflater.from(context).inflate(R.layout.diag_gif_loading, null);
            loadingDialog.setView(loadingDialogView);
            loadingDialog.setCancelable(show);
            ImageView imageView = (ImageView) loadingDialogView.findViewById(R.id.img_bfil_loading);
            Glide.with(imageView.getContext())
                    .asGif()
                    .load(gif)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
                    .into(imageView);
            loadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProgress(String message){
        showProgress(false, message);
    }
    public void showProgress(boolean show, String message) {
        try {
            loadingDialog = new AlertDialog.Builder(context).create();
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            View loadingDialogView = LayoutInflater.from(context).inflate(R.layout.diag_progress_loading, null);
            loadingDialog.setView(loadingDialogView);
            loadingDialog.setCancelable(show);
            TextView loadingTitle = (TextView) loadingDialogView.findViewById(R.id.txt_message);
            loadingTitle.setText(message);
            loadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showItems(String title, String[] items, CallBack callBack){
        showItems(false, title, items, callBack);
    }
    public void showItems(boolean show, String title, String[] items, final CallBack callBack) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setCancelable(show);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    closeDialog();
                    if (callBack != null){
                        callBack.onItemClick(i);
                    }
                }
            });
            loadingDialog = builder.create();
            loadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialog(){
        try {
            if (loadingDialog != null && loadingDialog.isShowing()){
                ImageView imageView = loadingDialog.findViewById(R.id.img_bfil_loading);
                if (imageView != null){
                    imageView.setImageResource(0);
                }
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AlertDialog getDialog(){
        return loadingDialog;
    }
}
