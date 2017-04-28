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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.hasee.myapp.R;
import com.example.hasee.myapp.activity.CateActivity;
import com.example.hasee.myapp.activity.QrActivity;
import com.example.hasee.myapp.adapter.FirstListAdapter;
import com.example.hasee.myapp.adapter.MenuPagerAdapter;
import com.example.hasee.myapp.bean.CateGson;
import com.example.hasee.myapp.bean.Xhttp;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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

    //下面的项目
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.first_fragment,container,false);

        //下拉按钮
        scanButton = (RadioButton) view.findViewById(R.id.first_scan);

        //分类菜单
        cricleLayout = (LinearLayout) view.findViewById(R.id.cricle_layout);
        menuViewPager = (ViewPager) view.findViewById(R.id.first_menu_viewpager);

        //list项目
        listView = (ListView) view.findViewById(R.id.first_listview);

        listHttp();


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

    private void listHttp() {

        String path = "http://api.meituan.com/meishi/filter/v5/deal/select/city/279/cate/1?sort=defaults&mypos=21.678586%2C110.923724&ci=279&hasGroup=true&mpt_cate1=-1&mpt_cate2=1&wifi-name=905_4%08905_2%08DIRECT-zjS2002%20S2003W%20Series%08905_1%08&wifi-mac=b0%3A95%3A8e%3A18%3A89%3A2e%08bc%3A46%3A99%3Ae2%3A4a%3A48%0832%3Acd%3Aa7%3A32%3Ae7%3Aa5%088c%3Aa6%3Adf%3A33%3Af7%3Aa2%08&wifi-strength=-49%08-50%08-55%08-52%08&wifi-cur=0&offset=0&limit=25&client=android&__vhost=api.meishi.meituan.com&utm_source=qq&utm_medium=android&utm_term=493&version_name=7.9.3&utm_content=867265025095523&utm_campaign=AgroupBgroupC0E0Ghomepage_category1_1__a1__gfood&msid=8672650250955231491554319289&uuid=CC98BAFAB4E1C5BFB285A9F522C83938E3AB988BF10CD6D6123AEF3249C3D7DD&userid=-1&__reqTraceID=0296786a-0bda-4db7-bdc6-53e1159040e2&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1491554360213&__skua=6929d5704bbc9cb05a89d2ccb8080cac&__skno=55fd9501-764d-497a-9aac-55660a771b56&__skcy=sJI0LNO7DfgjU49tJIxLu4JWB58%3D";
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("jiexi",result);
                //解析result
                Gson gson = new Gson();
                CateGson cateGson = gson.fromJson(result,CateGson.class);
                FirstListAdapter adapter = new FirstListAdapter(getActivity(),cateGson.data.poiList.poiInfos);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
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
