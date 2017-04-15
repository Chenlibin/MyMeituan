package com.example.hasee.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hasee.myapp.R;
import com.example.hasee.myapp.activity.CateActivity;

import java.util.List;

/**
 * Created by hasee on 2017/3/29.
 */
public class MenuPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private List<View> views;
    private Context context;

    public MenuPagerAdapter(List<View> views,Context context) {
        this.views = views;
        this.context = context;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (views != null) {
            count = views.size();
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position),0);
        if (position == 0){
            container.findViewById(R.id.first_cate).setOnClickListener(this);
            container.findViewById(R.id.first_movie).setOnClickListener(this);
            container.findViewById(R.id.first_stay).setOnClickListener(this);
            container.findViewById(R.id.first_entertainment).setOnClickListener(this);
            container.findViewById(R.id.first_take_out).setOnClickListener(this);
            container.findViewById(R.id.first_fare).setOnClickListener(this);
            container.findViewById(R.id.first_ktv).setOnClickListener(this);
            container.findViewById(R.id.first_outside).setOnClickListener(this);
            container.findViewById(R.id.first_hair).setOnClickListener(this);
            container.findViewById(R.id.first_travel).setOnClickListener(this);
        }
        else {
            container.findViewById(R.id.first_room).setOnClickListener(this);
            container.findViewById(R.id.first_fitness).setOnClickListener(this);
            container.findViewById(R.id.first_life).setOnClickListener(this);
            container.findViewById(R.id.first_mother).setOnClickListener(this);
            container.findViewById(R.id.first_marry).setOnClickListener(this);
            container.findViewById(R.id.first_sign).setOnClickListener(this);
            container.findViewById(R.id.first_zoo).setOnClickListener(this);
            container.findViewById(R.id.first_haircut).setOnClickListener(this);
            container.findViewById(R.id.first_foot).setOnClickListener(this);
            container.findViewById(R.id.first_all).setOnClickListener(this);
        }

        return views.get(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_cate:
                Intent cateIntent = new Intent(context,CateActivity.class);
                context.startActivity(cateIntent);
                break;
            case R.id.first_movie:
                break;
            case R.id.first_stay:
                break;
            case R.id.first_entertainment:
                break;
            case R.id.first_take_out:
                break;
            case R.id.first_fare:
                break;
            case R.id.first_ktv:
                break;
            case R.id.first_outside:
                break;
            case R.id.first_hair:
                break;
            case R.id.first_travel:
                break;

            //后十个
            case R.id.first_room:
                break;
            case R.id.first_fitness:
                break;
            case R.id.first_life:
                break;
            case R.id.first_mother:
                break;
            case R.id.first_marry:
                break;
            case R.id.first_sign:
                break;
            case R.id.first_zoo:
                break;
            case R.id.first_haircut:
                break;
            case R.id.first_foot:
                break;
            case R.id.first_all:
                break;
            default:
                break;
        }

    }
}
