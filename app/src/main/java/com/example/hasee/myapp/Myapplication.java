package com.example.hasee.myapp;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.example.hasee.myapp.service.LocationService;

import org.xutils.x;

/**
 * Created by Liber on 2017/4/15.
 */
public class MyApplication extends Application {

    public LocationService locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        x.Ext.init(this);
        //设置是否输出debug
        x.Ext.setDebug(BuildConfig.DEBUG);

        //百度地图api   初始化定位sdk    最好在Application中创建
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

    }
}
