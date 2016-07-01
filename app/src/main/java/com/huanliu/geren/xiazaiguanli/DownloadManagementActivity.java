package com.huanliu.geren.xiazaiguanli;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huanliu.ActivityCollector;
import com.huanliu.R;
import com.huanliu.adapter.ShouyeFragmentAdapter;
import com.huanliu.geren.xiazaiguanli.fragment.BendiShipinFragment;
import com.huanliu.geren.xiazaiguanli.fragment.XiazaiShiinFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class DownloadManagementActivity extends FragmentActivity {
    private CharSequence[] titles={"下载视频","本地视频"};
    private FragmentManager fm;
    private ShouyeFragmentAdapter shouyeFragmentAdapter;
    private List<Fragment> fragmentList;

    private ViewPager viewPager;
    private TabPageIndicator indicator;
    
    private BendiShipinFragment bendiShipinFragment;
    private XiazaiShiinFragment xiazaiShiinFragment;

    private TextView textView;
    private TextView flashTv;
    
    
    private ProgressBar progressBar_download;
    private TextView tv_download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download_management);
        ActivityCollector.addActivity(this);
        init();
        title();
    }

    private void title() {
        textView= (TextView) findViewById(R.id.geren_back_tital_textview);
        textView.setText(R.string.activity_fragment_geren_xiazaiguanli);
        flashTv= (TextView) findViewById(R.id.geren_back_tital_flash_textview);
    }
    
    private void init() {
        progressBar_download= (ProgressBar) findViewById(R.id.bottom_bar_geren_progressbar);
        tv_download= (TextView) findViewById(R.id.bottom_bar_geren_textview);

        long freeSpace= Environment.getDataDirectory().getFreeSpace();
        long totalSpace=Environment.getDataDirectory().getTotalSpace();
        //可用
        String avairom= Formatter.formatFileSize(this, freeSpace);
        //总量
        String totalrom=Formatter.formatFileSize(this,totalSpace);
        String a=Formatter.formatFileSize(this,totalSpace-freeSpace);
        int usedProGress= (int) (((float)(totalSpace-freeSpace)/totalSpace)*100);
        ////////////////////////////////////////////////////////////////////////////
        //SD卡
        long sDfreeSpace=Environment.getExternalStorageDirectory().getFreeSpace();
        long sDtotalSpace=Environment.getExternalStorageDirectory().getTotalSpace();
        //总量
        String sdtotalRom=Formatter.formatFileSize(this,sDtotalSpace+totalSpace);
        //  String sdavairom=Formatter.formatFileSize(this,sDfreeSpace);
        String b=Formatter.formatFileSize(this,(totalSpace-freeSpace)+(sDtotalSpace-sDfreeSpace));
        tv_download.setText(b+"/"+sdtotalRom);
        int SDusedProGress= (int) (((float)((sDtotalSpace-sDfreeSpace)+(totalSpace-freeSpace))/(sDtotalSpace+totalSpace))*100);
        progressBar_download.setMax(100);
        progressBar_download.setProgress(SDusedProGress);
        
        
        
        bendiShipinFragment=new BendiShipinFragment();
        xiazaiShiinFragment=new XiazaiShiinFragment();
        
        fragmentList = new ArrayList<Fragment>();

        viewPager= (ViewPager) findViewById(R.id.downlad_management_viewpager);
        indicator= (TabPageIndicator) findViewById(R.id.downlad_management_indicator);

        fm = getSupportFragmentManager();
        shouyeFragmentAdapter=new ShouyeFragmentAdapter(fm);

        fragmentList.add(bendiShipinFragment);
        fragmentList.add(xiazaiShiinFragment);

        shouyeFragmentAdapter.setFragmentList(fragmentList);
        shouyeFragmentAdapter.setTitles(titles);

        viewPager.setAdapter(shouyeFragmentAdapter);
                viewPager.setCurrentItem(0);
        
        
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        flashTv.setVisibility(View.GONE);
                        break;
                    case 1:
                        flashTv.setText("刷新");
                        flashTv.setVisibility(View.VISIBLE);
                        flashTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                xiazaiShiinFragment.startGet(1);
                                viewPager.setAdapter(shouyeFragmentAdapter);
                                viewPager.setCurrentItem(1);

                            }
                        });
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_download_management, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
