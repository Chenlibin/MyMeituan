package com.example.hasee.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hasee.myapp.fragment.lookFragment.BaseFragment;

import java.util.List;

/**
 * Created by hasee on 2017/3/22.
 */
public class LookFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public LookFragmentPagerAdapter(FragmentManager fm, List<Fragment> lookFragment) {
        super(fm);
        this.list = lookFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }

        return ret;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        String ret = null;
        BaseFragment baseFragment = (BaseFragment) list.get(position);

        ret = baseFragment.getFragmentTitle();

        return ret;

    }
}
