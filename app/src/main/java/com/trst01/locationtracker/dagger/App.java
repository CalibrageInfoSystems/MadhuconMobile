package com.trst01.locationtracker.dagger;

import static com.trst01.locationtracker.constant.AppConstant.APP_FOLDER;
import static com.trst01.locationtracker.constant.AppConstant.DB_FOLDER;
import static com.trst01.locationtracker.constant.AppConstant.DB_NAME;
import static com.trst01.locationtracker.constant.AppConstant.DB_SUB_FOLDER;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;

import com.trst01.locationtracker.di.component.DaggerAppComponent;
import com.trst01.locationtracker.helper.ApplicationThread;

import java.io.File;
import java.io.FileOutputStream;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    public static Context context;
    public static final boolean DEVELOPER_MODE = false;
    @Override
    public void onCreate() {

        if (DEVELOPER_MODE   && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }
        super.onCreate();
        this.initDagger();
        ApplicationThread.start();
        context = getApplicationContext();
    }


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    public static String createDBPath(){
        String filePath="";
        try {

            // TODO: DATABASE NAME
            String dbName=DB_NAME;
            String appFolderName=APP_FOLDER;
            String appDBFolderName=DB_FOLDER;
            String appEnvFolderName=DB_SUB_FOLDER;


            // TODO: For LEAD
           /* String rootPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/LOS_DB/";*/

            // TODO: FOR LOS
            String appFolderPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator ;
            String appDBPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator + appDBFolderName + File.separator ;
            String appEnvPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator + appDBFolderName + File.separator
                    + appEnvFolderName + File.separator ;
            // TODO: FULL FILE PATH
            filePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator + appDBFolderName + File.separator
                    + appEnvFolderName + File.separator + dbName ;


            File folder = new File(appFolderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            folder = new File(appDBPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            folder = new File(appEnvPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if( !TextUtils.isEmpty(filePath)) {
                File f = new File(filePath);
                if (!f.exists()) {
                    boolean isFileCreated= f.createNewFile();
                    if(isFileCreated) {
                        FileOutputStream out = new FileOutputStream(f);
                        out.flush();
                        out.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }



    public void onTerminate() {
        ApplicationThread.stop();
        super.onTerminate();
    }

//    public static String createDBPath() {
//        String filePath = "";
//        try {
////            String rootPath = Environment.getExternalStorageDirectory()
////                    .getAbsolutePath() + "/"+APP_FOLDER+"/";
//            String rootPath = context.getExternalFilesDir(null).getAbsolutePath() + "/" + APP_FOLDER + "/";
//
////            String subFolderPath = Environment.getExternalStorageDirectory()
////                    .getAbsolutePath() + "/"+APP_FOLDER+"/"+DB_FOLDER+"/";
//            String subFolderPath = context.getExternalFilesDir(null).getAbsolutePath() + "/" + APP_FOLDER + "/" + DB_FOLDER + "/";
//
////            String envFolderPath = Environment.getExternalStorageDirectory()
////                    .getAbsolutePath() + "/"+APP_FOLDER+"/"+DB_FOLDER+"/"+DB_SUB_FOLDER+"/";
//
//            String envFolderPath = context.getExternalFilesDir(null).getAbsolutePath() + "/" + APP_FOLDER + "/" + DB_FOLDER + "/" + DB_SUB_FOLDER + "/";
//
//            String fileName = DB_NAME;
//            File root = new File(rootPath);
//            if (!root.exists()) {
//                root.mkdirs();
//            }
//            File subRoot = new File(subFolderPath);
//            if (!subRoot.exists()) {
//                subRoot.mkdirs();
//            }
//            File envSubRoot = new File(envFolderPath);
//            if (!envSubRoot.exists()) {
//                envSubRoot.mkdirs();
//            }
//            File f = new File(envFolderPath + fileName);
//            if (!f.exists()) {
//                f.createNewFile();
//                FileOutputStream out = new FileOutputStream(f);
//                out.flush();
//                out.close();
//            }
//            filePath = envFolderPath + fileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return filePath;
//    }
}





