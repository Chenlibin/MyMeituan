package com.example.hasee.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.hasee.myapp.R;
import com.example.hasee.myapp.activity.CateActivity;
import com.example.hasee.myapp.activity.QrActivity;
import com.example.hasee.myapp.adapter.MenuPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/3/14.
 */
public class FirstFragment extends Fragment {

    //下拉菜单
    private RadioButton scanButton;

    //分类菜单
    private ViewPager menuViewPager;
    private LinearLayout cricleLayout;
    private MenuPagerAdapter menuPagerAdapter;

    //页面
    private List<View> views;

    //viewPager下面的小圆圈
    private ImageView[] cricleImageViews;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.first_fragment,container,false);

        //下拉按钮
        scanButton = (RadioButton) view.findViewById(R.id.first_scan);

        //分类菜单
        cricleLayout = (LinearLayout) view.findViewById(R.id.cricle_layout);
        menuViewPager = (ViewPager) view.findViewById(R.id.first_menu_viewpager);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(scanButton);
            }
        });

        //分类菜单
        initView();

        return view;
    }
    //显示下拉菜单的选项
    private void showPopupMenu(View view){

        //View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(getActivity(),view);
        //menu的布局
        popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
        //menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.item_scan:

                        Intent intent = new Intent(getActivity(),QrActivity.class);
                        startActivity(intent);

                        break;
                    case R.id.item_pay:
                        break;
                }
                return false;
            }
        });
        //PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });


        //显示选项图标     使用反射，强制显示菜单图标
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);

            MenuPopupHelper menuPopupHelper = (MenuPopupHelper) field.get(popupMenu);
            menuPopupHelper.setForceShowIcon(true);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //显示PopupMenu
        popupMenu.show();
    }


    //分类菜单
    private void initView(){

        views = new ArrayList<View>();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        views.add(inflater.inflate(R.layout.first_fra_menu_one,null));
        views.add(inflater.inflate(R.layout.first_fra_menu_two,null));

        menuPagerAdapter = new MenuPagerAdapter(views,getActivity());
        cricleImageViews = new ImageView[views.size()];
        drawCricle();

        menuViewPager.setAdapter(menuPagerAdapter);
        menuViewPager.addOnPageChangeListener(new MenuPagerChangeListener());




    }

    //画圆圈
    private void drawCricle(){

        int num = views.size();

        for (int i = 0; i < num; i++) {

            //实例化每一个cricleImageViews[i]
            cricleImageViews[i] = new ImageView(getActivity());
            if (i == 0) {
                cricleImageViews[i].setImageResource(R.mipmap.green_dot);
            }
            else {
                cricleImageViews[i].setImageResource(R.mipmap.dark_dot);
            }

         //设置每个小圈的间隔
            cricleImageViews[i].setPadding(7,7,7,7);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;

            cricleLayout.addView(cricleImageViews[i],params);
        }
    }

//    //分类菜单的转跳
//    private void showMenu(View view){
//
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        switch (view.getId()){
//            case R.id.first_cate:
//                break;
//            case R.id.first_movie:
//                break;
//            case R.id.first_stay:
//                break;
//            case R.id.first_entertainment:
//                break;
//            case R.id.first_take_out:
//                break;
//            case R.id.first_fare:
//                break;
//            case R.id.first_ktv:
//                break;
//            case R.id.first_outside:
//                break;
//            case R.id.first_hair:
//                break;
//            case R.id.first_travel:
//                break;
//
//            //后十个
//            case R.id.first_room:
//                break;
//            case R.id.first_fitness:
//                break;
//            case R.id.first_life:
//                break;
//            case R.id.first_mother:
//                break;
//            case R.id.first_marry:
//                break;
//            case R.id.first_sign:
//                break;
//            case R.id.first_zoo:
//                break;
//            case R.id.first_haircut:
//                break;
//            case R.id.first_foot:
//                break;
//            case R.id.first_all:
//                break;
//            default:
//                break;
//
//        }
//        //提交上面的代码
//        fragmentTransaction.commit();
//
//
//    }


    //监听分类菜单翻页滑动
    private class MenuPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //如果是当前页，将小圆圈改变颜色
            for (int i = 0; i < cricleImageViews.length; i++) {
                if (position == i){
                    cricleImageViews[position].setImageResource(R.mipmap.green_dot);

                }
                else {
                    cricleImageViews[i].setImageResource(R.mipmap.dark_dot);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            
        }
    }


}
