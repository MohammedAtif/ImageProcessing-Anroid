package example.zemoso.com.imageprocessing.runnables;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

import example.zemoso.com.imageprocessing.databinding.ActivityMainBinding;
import example.zemoso.com.imageprocessing.image_processing.ImageProcessing;
import example.zemoso.com.imageprocessing.utils.FileHandler;
import example.zemoso.com.imageprocessing.view_controller.ImageViewController;

/**
 * Created by atif on 28/3/17.
 */

public class ImageProcessingRunnable implements Runnable {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext;

    private int id;
    private WeakReference<ActivityMainBinding> activityMainBinding;

    public ImageProcessingRunnable(int id, ActivityMainBinding activityMainBinding) {
        this.id = id;
        this.activityMainBinding = new WeakReference<>(activityMainBinding);
        this.mContext = activityMainBinding.myEditedImage.getContext();
    }

    @Override
    public void run() {
        Log.d(TAG, "Current processing image is "+id);
        Bitmap largeIcon = BitmapFactory.decodeResource(mContext.getResources(), id);
        largeIcon = ImageProcessing.toGrayscale(largeIcon, 0);
//        largeIcon = ImageProcessing.changeBrightnessBy(largeIcon, -50);
//        largeIcon = ImageProcessing.setContrast(largeIcon, 2f);
//        largeIcon = ImageProcessing.invertColor(largeIcon);
//        largeIcon = ImageProcessing.colorFilter(largeIcon, 0, 0, 1);
        String path = FileHandler.storeImage(mContext, largeIcon, TAG);
        ImageViewController controller = activityMainBinding.get().getImageController();
        controller.setImageUrl(path);
        activityMainBinding.get().setImageController(controller);
        largeIcon.recycle();
        Log.d(TAG, "end time");
    }
}
