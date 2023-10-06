package com.trst01.locationtracker.uiLibrary.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trst01.locationtracker.R;


public class ConfirmationDialogNew {

    private Context context;
    private AlertDialog notificationDialog;

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String ALERT = "alert";

    public interface ActionCallback {
        void onAction();
    }

    public ConfirmationDialogNew(Context context){
        this.context = context;
    }

    /*  SINGLE BUTTON   */
    public void show(String type, CharSequence notificationMessage) {
        show(false, type, notificationMessage, null);
    }
    public void show(String type, CharSequence notificationMessage, final ActionCallback callback) {
        show(false, type, notificationMessage, callback);
    }
    public void show(boolean isCancellable, String type, CharSequence notificationMessage,
                     final ActionCallback callback) {
        try {
            notificationDialog = new AlertDialog.Builder(context).create();


            notificationDialog.setCancelable(isCancellable);

//            LinearLayout linearLayout = (LinearLayout)notificationDialogView.findViewById(R.id.linear_lay);
//            ImageView icon = (ImageView)notificationDialogView.findViewById(R.id.icon) ;
//            TextView subtitle = (TextView) notificationDialogView.findViewById(R.id.subtitle);
//            View lineView = notificationDialogView.findViewById(R.id.line_view);

//            subtitle.setText(notificationMessage);
            View notificationDialogView = null;


            switch (type) {
                case SUCCESS:
//                    icon.setImageResource(R.drawable.success);
//                    linearLayout.setBackgroundColor(context.getResources().getColor(R.color.success));
////                    lineView.setBackgroundColor(context.getResources().getColor(R.color.success));
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_dark_blue));
                     notificationDialogView = LayoutInflater.from(context).inflate(R.layout.popup_positive, null);
                    notificationDialog.setView(notificationDialogView);
                    break;

                case ERROR:
//                    icon.setImageResource(R.drawable.error);
//                    linearLayout.setBackgroundColor(context.getResources().getColor(R.color.error));
////                    lineView.setBackgroundColor(context.getResources().getColor(R.color.error));
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_dark_red));
                     notificationDialogView = LayoutInflater.from(context).inflate(R.layout.popup_positive, null);
                    notificationDialog.setView(notificationDialogView);
                    break;

                case ALERT:
//                    icon.setImageResource(R.drawable.alert);
////                    linearLayout.setBackgroundColor(context.getResources().getColor(R.color.alert));
//                    linearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_dark_yellow));
//                    lineView.setBackgroundColor(context.getResources().getColor(R.color.alert));
                     notificationDialogView = LayoutInflater.from(context).inflate(R.layout.popup_positive, null);
                    notificationDialog.setView(notificationDialogView);

                    break;
            }

            notificationDialogView.findViewById(R.id.ok_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (callback != null) {
                            closeDialog();
                            callback.onAction();
                        } else {
                            closeDialog();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            notificationDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*  TWO BUTTON s  */
    public void showTwoButtons(CharSequence notificationMessage, final ActionCallback callback) {
        showTwoButtons(false, notificationMessage, callback);
    }
    public void showTwoButtons(boolean isCancellable, CharSequence notificationMessage,
                               final ActionCallback callback) {

        notificationDialog = new AlertDialog.Builder(context).create();

        View notificationDialogView = LayoutInflater.from(context).inflate(R.layout.
                dialog_confirmation_dual, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(isCancellable);

        TextView subtitle = (TextView) notificationDialogView.findViewById(R.id.subtitle);
        subtitle.setText(notificationMessage);
        TextView txtOk = (TextView) notificationDialogView.findViewById(R.id.negative);
        txtOk.setText(context.getResources().getString(R.string.str_no));
        TextView txtPrint = (TextView) notificationDialogView.findViewById(R.id.positive);
        txtPrint.setText(context.getResources().getString(R.string.str_yes));

        LinearLayout linearLayout = (LinearLayout)notificationDialogView.findViewById(R.id.ll_root);
        View lineView = notificationDialogView.findViewById(R.id.line_view_1);

        linearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_dark_yellow));
        lineView.setBackgroundColor(context.getResources().getColor(R.color.alert));

        notificationDialogView.findViewById(R.id.negative_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        notificationDialogView.findViewById(R.id.positive_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (callback !=  null){
                        closeDialog();
                        callback.onAction();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        notificationDialog.show();
    }
    public void showTwoButtons(CharSequence notificationMessage, CharSequence lblPositive,
                               CharSequence lblNegative, final PrintActionCallback callback) {

        notificationDialog = new AlertDialog.Builder(context).create();

        View notificationDialogView = LayoutInflater.from(context).inflate(R.layout.
                dialog_confirmation_dual, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        TextView subtitle = (TextView) notificationDialogView.findViewById(R.id.subtitle);
        TextView txtPositive = (TextView) notificationDialogView.findViewById(R.id.positive);
        TextView txtNegative = (TextView) notificationDialogView.findViewById(R.id.negative);

        subtitle.setText(notificationMessage);
        txtPositive.setText(lblPositive);
        txtNegative.setText(lblNegative);

        notificationDialogView.findViewById(R.id.negative_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                        callback.onAction();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        notificationDialogView.findViewById(R.id.positive_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (callback !=  null){
                        closeDialog();
                        callback.onPrint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        notificationDialog.show();
    }

    /*  PRINT BUTTON   */
    public interface PrintActionCallback {
        void onAction();
        void onPrint();
    }
    public void showPrintButton(String type, CharSequence notificationMessage, final PrintActionCallback callback) {
        showPrintButton(type, false, notificationMessage, callback);
    }
    public void showPrintButton(String type, boolean isCancellable, CharSequence notificationMessage,
                               final PrintActionCallback callback) {

        notificationDialog = new AlertDialog.Builder(context).create();

        View notificationDialogView = LayoutInflater.from(context).inflate(R.layout.
                dialog_confirmation_dual, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(isCancellable);

        LinearLayout linearLayout = (LinearLayout)notificationDialogView.findViewById(R.id.linlay);
        ImageView icon = (ImageView)notificationDialogView.findViewById(R.id.icon) ;
        TextView subtitle = (TextView) notificationDialogView.findViewById(R.id.subtitle);
        View lineView1 = notificationDialogView.findViewById(R.id.line_view_1);
        View lineView2 = notificationDialogView.findViewById(R.id.line_view_2);
        TextView txtOk = (TextView) notificationDialogView.findViewById(R.id.negative);
        txtOk.setText(context.getResources().getString(R.string.str_Ok));
        TextView txtPrint = (TextView) notificationDialogView.findViewById(R.id.positive);
        txtPrint.setText(context.getResources().getString(R.string.Print));

        subtitle.setText(notificationMessage);

        switch (type) {
            case SUCCESS:
                icon.setImageResource(R.drawable.success);
                linearLayout.setBackgroundColor(context.getResources().getColor(R.color.success));
                lineView1.setBackgroundColor(context.getResources().getColor(R.color.success));
                lineView2.setBackgroundColor(context.getResources().getColor(R.color.success));
                break;

            case ERROR:
                icon.setImageResource(R.drawable.error);
                linearLayout.setBackgroundColor(context.getResources().getColor(R.color.error));
                lineView1.setBackgroundColor(context.getResources().getColor(R.color.error));
                lineView2.setBackgroundColor(context.getResources().getColor(R.color.error));
                break;

            case ALERT:
                icon.setImageResource(R.drawable.alert);
                linearLayout.setBackgroundColor(context.getResources().getColor(R.color.alert));
                lineView1.setBackgroundColor(context.getResources().getColor(R.color.alert));
                lineView2.setBackgroundColor(context.getResources().getColor(R.color.alert));
                break;
        }

        notificationDialogView.findViewById(R.id.negative_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                        callback.onAction();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        notificationDialogView.findViewById(R.id.positive_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (callback !=  null){
                        callback.onPrint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        notificationDialog.show();
    }

    public void closeDialog(){
        try {
            if (notificationDialog != null && notificationDialog.isShowing()){
                notificationDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

