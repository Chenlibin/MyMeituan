package com.example.hasee.myapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.myapp.MainActivity;
import com.example.hasee.myapp.R;
import com.example.hasee.myapp.bean.PreferenceUtil;

import java.util.List;


/**
 * Created by hasee on 2017/3/18.
 */
public class MapActivity extends Activity implements View.OnClickListener {

    //是否显示引导界面
    private boolean isShow = false;

    private TextView localAdress;

    private LocationManager locationManager;
    private String locationProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);

        //得到PreferenceUtil的数据
        isShow = PreferenceUtil.getBoolean(this, "showguide");

        if (isShow) {
            //进入主页面
            inMain();
        } else {
            initView();
        }

    }

    //进入引导界面
    private void initView() {
        localAdress = (TextView) findViewById(R.id.local_adress);


        //地理位置管理器
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
//        if (providers.contains(LocationManager.GPS_PROVIDER)) {
//            //GPS
//            locationProvider = LocationManager.GPS_PROVIDER;
//        } else
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是NETWORK
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "定位失败，请打开GPS", Toast.LENGTH_SHORT).show();
            return;
        }

        //获取位置      if以内是自己报错出来的
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);

        Log.e("locationaa",location+"");

        if (location != null) {
            showLocaation(location);
        }
        else {
            Toast.makeText(this,"无法定位",Toast.LENGTH_SHORT).show();
        }


    }

    //进入主界面
    private void inMain() {
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.local_adress:
                break;
            case R.id.beijing:
                break;
            case R.id.chengdu:
                break;
            case R.id.chongqing:
                break;
            case R.id.guangzhou:
                break;
            case R.id.hangzhou:
                break;
            case R.id.nanjing:
                break;
            case R.id.shanghai:
                break;
            case R.id.sengzhen:
                break;
            case R.id.tianjin:
                break;
            case R.id.wuhan:
                break;
            case R.id.xi_an:
                break;
        }
    }
    private void showLocaation(Location location){
        String locationString = location.getLatitude() + "+" + location.getLongitude();
        localAdress.setText(locationString);
    }



}
