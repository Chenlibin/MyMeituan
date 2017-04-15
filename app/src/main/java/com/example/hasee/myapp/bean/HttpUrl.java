package com.example.hasee.myapp.bean;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hasee on 2017/4/7.
 */
public class HttpUrl {
    public static byte[] getData(String path) throws IOException {

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);

        conn.connect();

        InputStream in = conn.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] arr = new byte[1024];
        int len = 0;

        while ((len = in.read(arr)) != -1 ){
            bos.write(arr,0,len);
        }

        in.close();
        return bos.toByteArray();
    }
}
