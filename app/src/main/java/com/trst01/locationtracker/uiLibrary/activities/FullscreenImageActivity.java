package com.trst01.locationtracker.uiLibrary.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.trst01.locationtracker.R;

public class FullscreenImageActivity extends AppCompatActivity {

//    private static final String TAG = FullscreenImageActivity.class.getCanonicalName();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fullscreen_image);
//
//        try {
//            Bundle bundle = getIntent().getExtras();
//            String filePath = null;
//            String title = "";
//            if (bundle != null) {
//                filePath = bundle.getString("image");
//                title = bundle.getString("title");
//                Log.i(TAG, "Bundle received FILE PATH-----> " + filePath);
//                Log.i(TAG, "Bundle received TITLE-----> " + title);
//            }
//
//            try {
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                if (toolbar != null) {
//                    if (!TextUtils.isEmpty(title)){
//                        toolbar.setTitle(title);
//                    } else {
//                        toolbar.setTitle("Full Image");
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            ImageView imageView = (ImageView) findViewById(R.id.img_skyc);
//
//            if (!TextUtils.isEmpty(filePath)) {
//                RequestOptions requestOptions=new RequestOptions()
//                        .diskCacheStrategy(DiskCacheStrategy.DATA)
//                        .sizeMultiplier(0.5f)
//                        .signature(new MediaStoreSignature("JPEG", System.currentTimeMillis(), 0))
//                        .dontAnimate();
//
//                Glide.with(this)
//                        .asBitmap()
//                        .load(filePath)
//                        .apply(requestOptions)
//                        .thumbnail(
//                                Glide.with(this)
//                                        .asBitmap()
//                                        .load(filePath)
//                                        .apply(requestOptions))
//                        .into(imageView);
//            }
//
//            ImageMatrixTouchHandler imageMatrixTouchHandler = new ImageMatrixTouchHandler(this);
//            imageView.setOnTouchListener(imageMatrixTouchHandler);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
