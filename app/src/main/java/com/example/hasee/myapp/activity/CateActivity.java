package com.example.hasee.myapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.example.hasee.myapp.R;
import com.example.hasee.myapp.adapter.FirstListAdapter;
import com.example.hasee.myapp.bean.CateGson;
import com.example.hasee.myapp.bean.HttpUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by hasee on 2017/4/6.
 */
public class CateActivity extends Activity {

    private ListView listView;
    private String path;

    //线程
    private MyThread myThread = null;
    private Handler listHandler = null;

    private final int START_THREAD = 1;
    private final int END_THREAD = 2;

    //Gson解析对象
    private CateGson cateGson = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_fra_cate);

        listView = (ListView) findViewById(R.id.first_cate_listview);

        path = "http://api.meituan.com/meishi/filter/v5/deal/select/city/279/cate/1?sort=defaults&mypos=21.678586%2C110.923724&ci=279&hasGroup=true&mpt_cate1=-1&mpt_cate2=1&wifi-name=905_4%08905_2%08DIRECT-zjS2002%20S2003W%20Series%08905_1%08&wifi-mac=b0%3A95%3A8e%3A18%3A89%3A2e%08bc%3A46%3A99%3Ae2%3A4a%3A48%0832%3Acd%3Aa7%3A32%3Ae7%3Aa5%088c%3Aa6%3Adf%3A33%3Af7%3Aa2%08&wifi-strength=-49%08-50%08-55%08-52%08&wifi-cur=0&offset=0&limit=25&client=android&__vhost=api.meishi.meituan.com&utm_source=qq&utm_medium=android&utm_term=493&version_name=7.9.3&utm_content=867265025095523&utm_campaign=AgroupBgroupC0E0Ghomepage_category1_1__a1__gfood&msid=8672650250955231491554319289&uuid=CC98BAFAB4E1C5BFB285A9F522C83938E3AB988BF10CD6D6123AEF3249C3D7DD&userid=-1&__reqTraceID=0296786a-0bda-4db7-bdc6-53e1159040e2&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1491554360213&__skua=6929d5704bbc9cb05a89d2ccb8080cac&__skno=55fd9501-764d-497a-9aac-55660a771b56&__skcy=sJI0LNO7DfgjU49tJIxLu4JWB58%3D";


        //启动线程
        myThread = new MyThread();
        myThread.start();

        //防止出现空指针异常
        while (null == listHandler){
        }

        //发送消息
        myThread.sendMessage();

    }
    Handler mainHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 3:
                    CateGson cateGsonData = (CateGson) msg.obj;
                    FirstListAdapter adapter = new FirstListAdapter(CateActivity.this,cateGsonData.data.poiList.poiInfos);
                    listView.setAdapter(adapter);
                    break;
                default:
                    break;

            }

            super.handleMessage(msg);
        }
    };


    class MyThread extends Thread{

        @Override
        public void run() {
            Looper.prepare();

            listHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {

                    switch (msg.what){
                        case START_THREAD:
                            byte[] arr;

                            try {
                                arr = HttpUrl.getData(path);
                                String json = new String(arr,"utf-8");
                                if (json != null) {
                                    Gson gson = new Gson();
                                    //gson解析后自动set
                                    cateGson = gson.fromJson(json, CateGson.class);

                                    //将解析数据发送到主线程
                                    Message mainMsg = mainHandler.obtainMessage(3);
                                    mainMsg.obj = cateGson;
                                    mainHandler.sendMessage(mainMsg);

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case END_THREAD:
                            Looper.myLooper().quit();
                            break;
                        default:
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            Looper.loop();
        }
        public void sendMessage(){
            //启动任务（消息只有标识，立即投递）
            listHandler.sendEmptyMessage(START_THREAD);
            //结束线程
            listHandler.sendEmptyMessageDelayed(END_THREAD,2000);

        }


    }

}
