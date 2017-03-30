package com.example.hasee.myapp.bean;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hasee on 2017/3/18.
 */
public class PreferenceUtil {

    public static void setBoolean(Context context,String key,boolean value){

        //得到SharedPreferences     设置只许本程序读写
        SharedPreferences preferences = context.getSharedPreferences("preference",Context.MODE_PRIVATE);
        //SharedPreferences不能写入数据，但其内部接口中的edit()方法可以获得有写入的Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static boolean getBoolean(Context context,String key){
        SharedPreferences preferences = context.getSharedPreferences("preference",Context.MODE_PRIVATE);

        //返回key的值，设置默认值为false
        return preferences.getBoolean(key,false);
    }

}
