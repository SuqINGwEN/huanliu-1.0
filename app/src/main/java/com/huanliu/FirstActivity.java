package com.huanliu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.huanliu.adapter.ShouyeFragmentAdapter;
import com.huanliu.fragment.shouyefragment.GeRenFragment;
import com.huanliu.fragment.shouyefragment.PinDaoFragment;
import com.huanliu.fragment.shouyefragment.ShouYeFragment;
import com.huanliu.geren.guankanlishi.HistoryActivity;
import com.huanliu.geren.xiazaiguanli.DownloadManagementActivity;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class FirstActivity extends FragmentActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private TabPageIndicator indicator;

    private FragmentManager fm;
    private ShouyeFragmentAdapter shouyeFragmentAdapter;
    private List<Fragment> fragmentList;
    
    private ShouYeFragment shouYeFragment;
    private PinDaoFragment pinDaoFragment;
    private GeRenFragment geRenFragment;

    private CharSequence[] titles={"首页","频道","个人"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        init();
        title();
    }

    private void title() {
        findViewById(R.id.layout_title_bar_item_download).setOnClickListener(this);
        findViewById(R.id.layout_title_bar_item_history).setOnClickListener(this);
        findViewById(R.id.layout_title_bar_item_search).setOnClickListener(this);
    }

    private void init() {
        
        
        fragmentList = new ArrayList<Fragment>();
        
        viewPager= (ViewPager) findViewById(R.id.shouye_title_viewpager);
        indicator= (TabPageIndicator) findViewById(R.id.shouye_title_indicator);

        shouYeFragment=new ShouYeFragment();
        pinDaoFragment=new PinDaoFragment();
        geRenFragment=new GeRenFragment();
        
        fm = getSupportFragmentManager();
        shouyeFragmentAdapter=new ShouyeFragmentAdapter(fm);
        
        fragmentList.add(shouYeFragment);
        fragmentList.add(pinDaoFragment);
        fragmentList.add(geRenFragment);

        shouyeFragmentAdapter.setFragmentList(fragmentList);
        shouyeFragmentAdapter.setTitles(titles);
        
        viewPager.setAdapter(shouyeFragmentAdapter);
        Intent intent=getIntent();
        int id = intent.getIntExtra("fragid", 0);
        if(id==1){
            viewPager.setCurrentItem(2);
        }else{
            viewPager.setCurrentItem(0);
        }

        indicator.setViewPager(viewPager);
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

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.layout_title_bar_item_download:
                intent.setClass(FirstActivity.this, DownloadManagementActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_title_bar_item_history:
                intent.setClass(FirstActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_title_bar_item_search:
                break;
        }
    }

    private static Boolean isExit = false;
    Timer tExit = new Timer();
    MyTimerTask  task;
    class MyTimerTask  extends TimerTask {
        @Override
        public void run() {
            isExit = false;
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再按一次后退键退出应用程序", Toast.LENGTH_SHORT).show();
                if (task != null)
                    task.cancel(); //将原任务从队列中移除  
                task = new MyTimerTask();// 新建一个任务        
                tExit.schedule(task, 2000);
            } else {
                finish();
                ActivityCollector.finishAll();
                System.exit(0);
            }
        }
        return false;
    }

}
