package com.example.hasee.myapp;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.myapp.fragment.FirstFragment;
import com.example.hasee.myapp.fragment.IndentFragment;
import com.example.hasee.myapp.fragment.LookFragment;
import com.example.hasee.myapp.fragment.MineFragment;
import com.example.hasee.myapp.fragment.NearbyFragment;

import static com.example.hasee.myapp.R.mipmap.first_light;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //布局
    private LinearLayout firstLinearLayout;
    private LinearLayout nearbyLinearLayout;
    private LinearLayout lookinearLayout;
    private LinearLayout indentLinearLayout;
    private LinearLayout mineLinearLayout;
    //图片
    private ImageView firstImageView;
    private ImageView nearbyImageView;
    private ImageView lookImageView;
    private ImageView indentImageView;
    private ImageView mineImageView;
    //文字
    private TextView firstTextView;
    private TextView nearbyTextView;
    private TextView lookTextView;
    private TextView indentTextView;
    private TextView mineTextView;

    //fragment
    private FirstFragment firstFragment;
    private NearbyFragment nearbyFragment;
    private LookFragment lookFragment;
    private IndentFragment indentFragment;
    private MineFragment mineFragment;

    //关闭软件
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //初始化
        initialize();

        //默认第一个页面
        showCurrentContent(firstLinearLayout);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initialize(){
        firstLinearLayout = (LinearLayout) findViewById(R.id.main_first);
        nearbyLinearLayout = (LinearLayout) findViewById(R.id.main_nearby);
        lookinearLayout = (LinearLayout) findViewById(R.id.main_look);
        indentLinearLayout = (LinearLayout) findViewById(R.id.main_indent);
        mineLinearLayout = (LinearLayout) findViewById(R.id.main_mine);

        firstImageView = (ImageView) findViewById(R.id.image_first);
        nearbyImageView = (ImageView) findViewById(R.id.image_nearby);
        lookImageView = (ImageView) findViewById(R.id.image_look);
        indentImageView = (ImageView) findViewById(R.id.image_indent);
        mineImageView = (ImageView) findViewById(R.id.image_mine);


        firstTextView = (TextView) findViewById(R.id.text_first);
        nearbyTextView = (TextView) findViewById(R.id.text_nearby);
        lookTextView = (TextView) findViewById(R.id.text_look);
        indentTextView = (TextView) findViewById(R.id.text_indent);
        mineTextView = (TextView) findViewById(R.id.text_mine);

        firstLinearLayout.setOnClickListener(this);
        nearbyLinearLayout.setOnClickListener(this);
        lookinearLayout.setOnClickListener(this);
        indentLinearLayout.setOnClickListener(this);
        mineLinearLayout.setOnClickListener(this);

    }

    //监听5个布局
    @Override
    public void onClick(View view) {
        restart();

        switch (view.getId()){
            case R.id.main_first:
                showCurrentContent(firstLinearLayout);
                break;
            case R.id.main_nearby:
                showCurrentContent(nearbyLinearLayout);
                break;
            case R.id.main_look:
                showCurrentContent(lookinearLayout);
                break;
            case R.id.main_indent:
                showCurrentContent(indentLinearLayout);
                break;
            case R.id.main_mine:
                showCurrentContent(mineLinearLayout);
                break;
            default:
                break;

        }
    }

    private void restart(){
        firstImageView.setImageResource(R.mipmap.first);
        nearbyImageView.setImageResource(R.mipmap.nearby);
        lookImageView.setImageResource(R.mipmap.look);
        indentImageView.setImageResource(R.mipmap.indent);
        mineImageView.setImageResource(R.mipmap.mine);

        firstTextView.setTextColor(Color.parseColor("#737373"));
        nearbyTextView.setTextColor(Color.parseColor("#737373"));
        lookTextView.setTextColor(Color.parseColor("#737373"));
        indentTextView.setTextColor(Color.parseColor("#737373"));
        mineTextView.setTextColor(Color.parseColor("#737373"));
    }


    private void showCurrentContent(View v){
        //这两句话无法写在监听中
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        hideFragment(fragmentTransaction);
        switch (v.getId()){
            case R.id.main_first:
                firstImageView.setImageResource(R.mipmap.first_light);
                firstTextView.setTextColor(Color.parseColor("#06C1AE"));
                // 如果homeFragment为空，则创建一个并添加到界面上
                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                    fragmentTransaction.add(R.id.main_fragment,firstFragment);
                } else {
                    fragmentTransaction.show(firstFragment);
                }


                break;
            case R.id.main_nearby:
                nearbyImageView.setImageResource(R.mipmap.nearby_light);
                nearbyTextView.setTextColor(Color.parseColor("#06C1AE"));

                if (nearbyFragment == null) {
                    nearbyFragment = new NearbyFragment();
                    fragmentTransaction.add(R.id.main_fragment,nearbyFragment);
                } else {
                    fragmentTransaction.show(nearbyFragment);
                }

                break;
            case R.id.main_look:
                lookImageView.setImageResource(R.mipmap.look_light);
                lookTextView.setTextColor(Color.parseColor("#06C1AE"));

                if (lookFragment == null) {
                    lookFragment = new LookFragment();
                    fragmentTransaction.add(R.id.main_fragment,lookFragment);
                } else {
                    fragmentTransaction.show(lookFragment);
                }

                break;
            case R.id.main_indent:
                indentImageView.setImageResource(R.mipmap.indent_light);
                indentTextView.setTextColor(Color.parseColor("#06C1AE"));

                if (indentFragment == null) {
                    indentFragment = new IndentFragment();
                    fragmentTransaction.add(R.id.main_fragment,indentFragment);
                } else {
                    fragmentTransaction.show(indentFragment);
                }

                break;
            case R.id.main_mine:
                mineImageView.setImageResource(R.mipmap.mine_light);
                mineTextView.setTextColor(Color.parseColor("#06C1AE"));

                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.main_fragment,mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }

                break;
            default:
                break;
        }
        //提交所改代码
        fragmentTransaction.commit();


    }

    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (firstFragment != null){
            fragmentTransaction.hide(firstFragment);
        }
        if (nearbyFragment != null) {
            fragmentTransaction.hide(nearbyFragment);
        }
        if (lookFragment != null) {
            fragmentTransaction.hide(lookFragment);
        }
        if (indentFragment != null) {
            fragmentTransaction.hide(indentFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }

    }




}
