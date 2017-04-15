package com.example.hasee.myapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.myapp.R;
import com.example.hasee.myapp.activity.CateActivity;
import com.example.hasee.myapp.bean.CateGson;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by hasee on 2017/4/10.
 */
public class FirstListAdapter extends BaseAdapter {

    private Context context;
    private List<CateGson.GsonData.PoiList.PoiInfos> list;

    public FirstListAdapter(Context context, List<CateGson.GsonData.PoiList.PoiInfos> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (list != null) {
            count = list.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //加载布局 重复使用以前布局，除第一次
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        }
        else {
            ret = LayoutInflater.from(context).inflate(R.layout.first_fra_cate_listview,parent,false);
        }

        //获取对应控件
        ViewHolder viewHolder = (ViewHolder) ret.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.nameTv = (TextView) ret.findViewById(R.id.first_name);
            viewHolder.avgPriceTv = (TextView) ret.findViewById(R.id.first_avgPrice);
            viewHolder.areaNameTv = (TextView) ret.findViewById(R.id.first_areaName);
            viewHolder.cateNameTv = (TextView) ret.findViewById(R.id.first_cateName);
            viewHolder.smartTagsTv = (TextView) ret.findViewById(R.id.smartTags_content);
            viewHolder.extraServiceTagsTv = (TextView) ret.findViewById(R.id.extraServiceTags_content);
            viewHolder.preferentialInfoTv = (TextView) ret.findViewById(R.id.preferentialInfo_content);

            //图片显示
            viewHolder.frontImg = (ImageView) ret.findViewById(R.id.first_frontImg);

            ret.setTag(viewHolder);
        }


        CateGson.GsonData.PoiList.PoiInfos data = list.get(position);
        String name = data.getName();
        String avgPrice = data.getAvgPrice();
        String cateName = data.getCateName();


        viewHolder.nameTv.setText(name);
        viewHolder.avgPriceTv.setText(avgPrice);
        viewHolder.cateNameTv.setText(cateName);

        Glide
                .with(context)
                .load(data.getFrontImg())
                .centerCrop()
                .into(viewHolder.frontImg);

        Log.e("frontImg",data.getFrontImg());


        return ret;
    }

    private static class ViewHolder{
        public TextView nameTv,avgPriceTv,areaNameTv,cateNameTv,smartTagsTv,extraServiceTagsTv,preferentialInfoTv;
        public ImageView frontImg,preferentialInfoIv;
    }



}
