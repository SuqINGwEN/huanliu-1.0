package com.huanliu.shouye.bofang;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.huanliu.R;
import com.huanliu.adapter.BoFangFragmentAdapter;
import com.huanliu.fragment.bofangfragment.JianJieFragment;
import com.huanliu.fragment.bofangfragment.PingLunFragment;
import com.huanliu.fragment.bofangfragment.XiangGuanFragment;
import com.huanliu.fragment.bofangfragment.XuanJiFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class BoFang2 extends FragmentActivity {
    private ViewPager vp;
    private TabPageIndicator indicator;

    private FragmentManager fm;
    private BoFangFragmentAdapter boFangFragmentAdapter;
    private List<Fragment> fragmentList;

    private JianJieFragment jianJieFragment;
    private PingLunFragment pingLunFragment;
    private XiangGuanFragment xiangGuanFragment;
    private XuanJiFragment xuanJiFragment;

    private CharSequence[] titles = {"简介", "选集", "评论", "相关"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bofang2);
        init();
    }

    private void init() {
        fragmentList = new ArrayList<Fragment>();

        vp = (ViewPager) findViewById(R.id.vp_bofang);
        indicator = (TabPageIndicator) findViewById(R.id.indicator_bofang);

        jianJieFragment = new JianJieFragment();
        xuanJiFragment = new XuanJiFragment();
        pingLunFragment = new PingLunFragment();
        xiangGuanFragment = new XiangGuanFragment();

        fm = getSupportFragmentManager();

        boFangFragmentAdapter = new BoFangFragmentAdapter(fm);

        fragmentList.add(jianJieFragment);
        fragmentList.add(xuanJiFragment);
        fragmentList.add(pingLunFragment);
        fragmentList.add(xiangGuanFragment);

        boFangFragmentAdapter.setFragmentList(fragmentList);
        boFangFragmentAdapter.setTitles(titles);

        vp.setAdapter(boFangFragmentAdapter);
        vp.setCurrentItem(0);

        indicator.setViewPager(vp);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_bo_fang2, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
