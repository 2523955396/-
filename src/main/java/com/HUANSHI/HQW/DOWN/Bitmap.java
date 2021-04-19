package com.HUANSHI.HQW.DOWN;

import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Bitmap {
    public android.graphics.Bitmap bitmap(String img) {

        android.graphics.Bitmap bitmap = null;

        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception v) {
        }
        return bitmap;
    }
}
