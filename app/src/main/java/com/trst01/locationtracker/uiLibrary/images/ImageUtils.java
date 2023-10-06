package com.trst01.locationtracker.uiLibrary.images;

import static com.trst01.locationtracker.uiLibrary.images.FileUtils.getExtension;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImageUtils {

    private static final float maxHeight = 1280.0f;
    private static final float maxWidth = 1280.0f;

    public static String compressImage(String imagePath) {
        Bitmap scaledBitmap = null;
        String filename = "";
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(imagePath, options);

            int actualHeight = options.outHeight;
            int actualWidth = options.outWidth;
            float imgRatio = (float) actualWidth / (float) actualHeight;
            float maxRatio = maxWidth / maxHeight;

            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;

                }
            }

            options.inSampleSize = ImageUtils.calculateInSampleSize(options, actualWidth, actualHeight);
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16 * 1024];

            try {
                bmp = BitmapFactory.decodeFile(imagePath, options);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();

            }
            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();
            }

            float ratioX = actualWidth / (float) options.outWidth;
            float ratioY = actualHeight / (float) options.outHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            if (scaledBitmap != null) {
                Canvas canvas = new Canvas(scaledBitmap);
                canvas.setMatrix(scaleMatrix);
                canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

                ExifInterface exif;
                try {
                    exif = new ExifInterface(imagePath);
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
                    Matrix matrix = new Matrix();
                    if (orientation == 6) {
                        matrix.postRotate(90);
                    } else if (orientation == 3) {
                        matrix.postRotate(180);
                    } else if (orientation == 8) {
                        matrix.postRotate(270);
                    }
                    scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

                    FileOutputStream out = null;
                    String type = getExtension(imagePath);
                    File f = new File(imagePath);
                    filename = f.getPath();
                    try {
                        out = new FileOutputStream(filename);
                        /*write the compressed bitmap at the destination specified by filename.*/
                        if (type.equals(".png")) {
                            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 80, out);
                        } else {
                            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
                        }
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filename;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        try {
            final int height = options.outHeight;
            final int width = options.outWidth;

            if (height > reqHeight || width > reqWidth) {
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inSampleSize;
    }

    public static void saveToInternalStorage(Context context, Bitmap bitmapImage, String userId) {
        /*File directory = new File(Environment.getExternalStorageDirectory().getPath(), "Telekha/ProfileImages");*/
        try {
            ContextWrapper cw = new ContextWrapper(context);
            String name_ = "ProfileImages"; //Folder name in device android/data/
            File directory = cw.getDir(name_, Context.MODE_PRIVATE);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File[] children = directory.listFiles();
            for (File aChildren : children) {
                if (aChildren.getName().equals(userId)) {
                    aChildren.delete();
                }
            }
            File myImage = new File(directory, userId + ".jpg");
            myImage.createNewFile();

            FileOutputStream fos = null;
            fos = new FileOutputStream(myImage);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
