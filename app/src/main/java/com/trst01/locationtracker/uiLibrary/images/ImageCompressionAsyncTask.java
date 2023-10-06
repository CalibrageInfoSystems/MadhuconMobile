package com.trst01.locationtracker.uiLibrary.images;

import android.os.AsyncTask;

/**
 * Created by telekha on 11/5/17.
 */

public abstract class ImageCompressionAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        if(strings.length == 0 || strings[0] == null)
            return null;
        return ImageUtils.compressImage(strings[0]);
    }

    protected abstract void onPostExecute(String imageBytes) ;
}
