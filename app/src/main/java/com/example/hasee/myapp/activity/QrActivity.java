package com.example.hasee.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.hasee.myapp.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;



//这个dome不好，不会识别，不知道是什么问题


/**
 * Created by hasee on 2017/3/25.
 */
public class QrActivity extends Activity implements QRCodeView.Delegate {

    private QRCodeView qrCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qr);


        qrCodeView = (QRCodeView) findViewById(R.id.zxing_view);
        qrCodeView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        qrCodeView.startCamera();
        qrCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        qrCodeView.stopCamera();

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        qrCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate(){

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);

    }

    //扫描结果
    @Override
    public void onScanQRCodeSuccess(String result) {

        Log.e("aaa",result);
        Toast.makeText(this,"扫描成功",Toast.LENGTH_SHORT).show();
        vibrate();
        qrCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this,"打开相机出错",Toast.LENGTH_SHORT).show();

    }


    //返回扫描到的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        qrCodeView.showScanRect();

        if (resultCode == Activity.RESULT_OK && requestCode == 666){

        }


    }
}
