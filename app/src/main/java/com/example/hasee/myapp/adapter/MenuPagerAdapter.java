package com.example.hasee.myapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hasee on 2017/3/29.
 */
public class MenuPagerAdapter extends PagerAdapter {

    private List<View> views;

    public MenuPagerAdapter(List<View> views) {
        this.views = views;
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

        return views.get(position);
    }
}
