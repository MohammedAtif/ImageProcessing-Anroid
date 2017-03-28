package example.zemoso.com.imageprocessing.image_processing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by atif on 28/3/17.
 */

public class ImageProcessing {

    public static Bitmap toGrayscale(Bitmap bmpOriginal, float intensity) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(intensity);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        bmpOriginal.recycle();
        return bmpGrayscale;
    }

    public static Bitmap changeBrightnessBy(Bitmap bmpOriginal, float brightness){
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpBrightImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpBrightImage);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.set(getBrightnessMatrix(cm.getArray(), brightness));
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        bmpOriginal.recycle();
        return bmpBrightImage;
    }

    public static Bitmap setContrast(Bitmap bmpOriginal, float contrast){
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpBrightImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpBrightImage);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.set(getContrastMatrix(cm.getArray(), contrast));
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        bmpOriginal.recycle();
        return bmpBrightImage;
    }

    public static Bitmap invertColor(Bitmap bmpOriginal){
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpBrightImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpBrightImage);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        float[] mArray = cm.getArray();
        mArray[0] = -1; mArray[4] = 255;
        mArray[6] = -1; mArray[9] = 255;
        mArray[12] = -1; mArray[14] = 255;
        mArray[18] = 1; mArray[19] = 0;
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        bmpOriginal.recycle();
        return bmpBrightImage;
    }

    public static Bitmap colorFilter(Bitmap bmpOriginal, int R, int G, int B){
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpBrightImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpBrightImage);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        float[] mArray = cm.getArray();
        mArray[0] = R;
        mArray[6] = G;
        mArray[12] = B;
        mArray[18] = 1;
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        bmpOriginal.recycle();
        return bmpBrightImage;
    }


    private static float[] getBrightnessMatrix(float[] mArray, float brightness){
        mArray[4] = brightness;
        mArray[9] = brightness;
        mArray[14] = brightness;
        return mArray;
    }

    private static float[] getContrastMatrix(float[] mArray, float contrast){
        mArray[0] = contrast;
        mArray[6] = contrast;
        mArray[12] = contrast;
        return mArray;
    }
}
