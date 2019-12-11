package com.thoughtworks.themoments.imageloader.learning;

import android.graphics.Bitmap;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Zhu on 2019-12-11
 */
public class ClosableTryCatch {

    public void put(Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("  ");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(fos);
        }

    }


    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
