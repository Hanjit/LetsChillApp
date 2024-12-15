package com.example.letschill;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class TopCrop extends BitmapTransformation {
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (toTransform == null) {
            return null;
        }

        // Calculate the scale to fill the ImageView while maintaining aspect ratio
        float scale = Math.max((float) outWidth / toTransform.getWidth(),
                (float) outHeight / toTransform.getHeight());

        // Create a transformation matrix
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);

        // Align the top of the image
        float dx = 0; // No horizontal offset
        float dy = 0; // Keep the top of the image aligned
        matrix.postTranslate(dx, dy);

        // Create a new bitmap and draw the transformed image onto it
        Bitmap result = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(toTransform, matrix, null);

        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
