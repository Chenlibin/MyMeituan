package com.example.hasee.myapp.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.hasee.myapp.R;

/**
 * Created by hasee on 2017/3/14.
 */
public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //改变状态栏颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getActivity().getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //获取样式中的属性值
//            TypedValue typedValue = new TypedValue();
//            getActivity().getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
//            int[] attribute = new int[] { android.R.attr.colorPrimary };
//            TypedArray array = getActivity().obtainStyledAttributes(typedValue.resourceId, attribute);
//            int color = array.getColor(0, Color.TRANSPARENT);
//            array.recycle();
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                window.setStatusBarColor(color);
//            }
//        }
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.mine_fragment,container,false);


        return ret;
    }
}
