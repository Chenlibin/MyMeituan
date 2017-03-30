package com.example.hasee.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.hasee.myapp.R;
import com.example.hasee.myapp.adapter.LookFragmentPagerAdapter;
import com.example.hasee.myapp.fragment.lookFragment.CateFragment;
import com.example.hasee.myapp.fragment.lookFragment.FilmFragment;
import com.example.hasee.myapp.fragment.lookFragment.HeadLineFragment;
import com.example.hasee.myapp.fragment.lookFragment.PloyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/3/14.
 */
public class LookFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ScrollView scrollView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.look_fragment,container,false);


        tabLayout = (TabLayout) ret.findViewById(R.id.look_tab);
        viewPager = (ViewPager) ret.findViewById(R.id.look_viewpager);
        scrollView = (ScrollView) ret.findViewById(R.id.look_scrollview);


        List<Fragment> lookFragment = new ArrayList<Fragment>();

        FragmentManager fm = getActivity().getSupportFragmentManager();

        lookFragment.add(new HeadLineFragment());
        lookFragment.add(new CateFragment());
        lookFragment.add(new PloyFragment());
        lookFragment.add(new FilmFragment());

        LookFragmentPagerAdapter adapter = new LookFragmentPagerAdapter(fm,lookFragment);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //设置默认的tabLayout
//        tabLayout.getTabAt().select();

        return ret;


    }
}
