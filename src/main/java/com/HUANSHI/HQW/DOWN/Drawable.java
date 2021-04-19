package com.HUANSHI.HQW.DOWN;


import com.HUANSHI.HQW.home;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Drawable {

    public android.graphics.drawable.Drawable drawable(String img){
        android.graphics.drawable.Drawable drawable = null;

        try {
            URL url = new URL(img);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            drawable= android.graphics.drawable.Drawable.createFromStream(inputStream,null);
            return drawable;
        } catch (Exception v) {
        }
        return drawable;
    }
}
