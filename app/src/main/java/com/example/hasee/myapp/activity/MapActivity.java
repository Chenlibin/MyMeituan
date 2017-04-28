package com.example.hasee.myapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.example.hasee.myapp.MyApplication;
import com.example.hasee.myapp.R;
import com.example.hasee.myapp.bean.PreferenceUtil;
import com.example.hasee.myapp.service.LocationService;

import java.util.ArrayList;
import java.util.SimpleTimeZone;


/**
 * Created by hasee on 2017/3/18.
 */
public class MapActivity extends Activity implements View.OnClickListener{

    //是否显示引导界面
    boolean isShow = false;
    //显示地址
    private TextView localAdress;
    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);

        //得到PreferenceUtil的数据
        isShow = PreferenceUtil.getBoolean(this, "showguide");
        //地图定位的权限允许
        getPersimmions();
        //测试这个界面
//        isShow = false;

        //判断是否进入引导界面
        if (isShow) {
            inMain();
        } else {
            initView();
        }

    }

    //地图定位的权限允许
    private void getPersimmions(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ArrayList<String> permissions = new ArrayList<String>();

            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            //checkSelfPermission()这个方法至少要在minSdkVersion23以后才可以用
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
        }

    }

    //进入引导界面
    private void initView() {
        localAdress = (TextView) findViewById(R.id.local_adress);
        localAdress.setMovementMethod(ScrollingMovementMethod.getInstance());
        //城市定位选择
        findViewById(R.id.local_adress).setOnClickListener(this);
        findViewById(R.id.beijing).setOnClickListener(this);
        findViewById(R.id.chengdu).setOnClickListener(this);
        findViewById(R.id.chongqing).setOnClickListener(this);
        findViewById(R.id.guangzhou).setOnClickListener(this);
        findViewById(R.id.hangzhou).setOnClickListener(this);
        findViewById(R.id.nanjing).setOnClickListener(this);
        findViewById(R.id.shanghai).setOnClickListener(this);
        findViewById(R.id.sengzhen).setOnClickListener(this);
        findViewById(R.id.tianjin).setOnClickListener(this);
        findViewById(R.id.wuhan).setOnClickListener(this);
        findViewById(R.id.xi_an).setOnClickListener(this);
    }
    //进入主界面
    private void inMain() {
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //显示地图返回的数据
    public void logMsg(String str){
        final String s = str;
        try{
            if (localAdress != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        localAdress.post(new Runnable() {
                            @Override
                            public void run() {
                                localAdress.setText(s);
                            }
                        });
                    }
                }).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        //地图注销监听
        locationService.unregisterListener(mListener);
        //地图停止定位
        locationService.stop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //获取LocationService实例  地图服务
        locationService = ((MyApplication)getApplication()).locationService;
        //注册监听 地图服务
        locationService.registerListener(mListener);

        int type = getIntent().getIntExtra("from",0);
        if (type == 0){
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1){
            locationService.setLocationOption(locationService.getOption());
        }

        //开始定位  地图
        locationService.start();
    }
    //地图定位结果回调   只改了返回的数据

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
//                logMsg(sb.toString());
                logMsg(location.getCity());
            }
        }

        public void onConnectHotSpotMessage(String s, int i){
        }
    };


    @Override
    public void onClick(View view) {

        PreferenceUtil.setBoolean(this,"showguide",true);

        switch (view.getId()) {
            case R.id.local_adress:
                inMain();
                break;
            case R.id.beijing:
                inMain();
                break;
            case R.id.chengdu:
                inMain();
                break;
            case R.id.chongqing:
                inMain();
                break;
            case R.id.guangzhou:
                inMain();
                break;
            case R.id.hangzhou:
                inMain();
                break;
            case R.id.nanjing:
                inMain();
                break;
            case R.id.shanghai:
                inMain();
                break;
            case R.id.sengzhen:
                inMain();
                break;
            case R.id.tianjin:
                inMain();
                break;
            case R.id.wuhan:
                inMain();
                break;
            case R.id.xi_an:
                inMain();
                break;
        }
    }

}
