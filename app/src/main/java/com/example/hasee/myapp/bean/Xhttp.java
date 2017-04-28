package com.example.hasee.myapp.bean;

import android.widget.Toast;

import com.bumptech.glide.request.RequestListener;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Liber on 2017/4/15.
 */
public final class Xhttp {


    private Xhttp(){}

    ///////   GET  请求
    public static void getDataByGet(RequestParams params,final com.example.hasee.myapp.bean.RequestListener requestListener){
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                requestListener.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                if (ex instanceof HttpException){

                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    Toast.makeText(x.app(),"请检查您的网络状态",Toast.LENGTH_SHORT);
                } else{
                    Toast.makeText(x.app(),"未知异常",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }



}
