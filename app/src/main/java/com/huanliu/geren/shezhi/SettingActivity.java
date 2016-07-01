package com.huanliu.geren.shezhi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.huanliu.BaseActivity;
import com.huanliu.R;


public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private TextView textView;
    
    private CheckBox xiaoxi;
    private CheckBox zhuomian;
    private CheckBox netxiazai;
    
    private TextView tv_xiazaishu;
    private TextView tv_downloadpath;
    private TextView tv_xiazaiyouxian;
    
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        title();
        init();
    }

    private void init() {
        preferences=getSharedPreferences("mysetting",Context.MODE_PRIVATE);
        editor=getSharedPreferences("mysetting", Context.MODE_PRIVATE).edit();
        String str_xiazaishu=preferences.getString("xiazaishu","1");
        String str_xiazailujing=preferences.getString("xiazailujing","SD卡存储");
        String str_xiazaiyouxian=preferences.getString("xiazaiyouxian","流畅");
        
        boolean b_xiaoxi=preferences.getBoolean("jieshouxiaoxi",true);
        boolean b_zhuomian=preferences.getBoolean("zhuomianxiaoxi",true);
        boolean b_netxiazai=preferences.getBoolean("netxiazai",true);
        
        xiaoxi= (CheckBox) findViewById(R.id.geren_setting_checkbox_jieshouxiaoxi);
        xiaoxi.setChecked(b_xiaoxi);
               xiaoxi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       if(isChecked==false){
                           editor.putBoolean("jieshouxiaoxi",false);
                       }else{
                           editor.putBoolean("jieshouxiaoxi",true);
                       }
                       editor.commit();
                   }
               });
        
        
        zhuomian=(CheckBox) findViewById(R.id.geren_setting_checkbox_zhuomiantongzhi);
        zhuomian.setChecked(b_zhuomian);
        zhuomian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putBoolean("zhuomianxiaoxi",true);
                }else{
                    editor.putBoolean("zhuomianxiaoxi",false);
                }
                editor.commit();
            }
        });
        netxiazai= (CheckBox) findViewById(R.id.geren_setting_checkbox_netdownload);
        netxiazai.setChecked(b_netxiazai);
                netxiazai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            editor.putBoolean("netxiazai",true);
                        }else{
                            editor.putBoolean("netxiazai",false);
                        }
                        editor.commit();
                    }
                });
                
                
        findViewById(R.id.geren_setting_layout_xiazaishu).setOnClickListener(this);
        findViewById(R.id.geren_setting_layout_xiazailujing).setOnClickListener(this);
        findViewById(R.id.geren_setting_layout_xiazaiyouxian).setOnClickListener(this);
        findViewById(R.id.geren_setting_layout_clearcache).setOnClickListener(this);
        tv_xiazaishu= (TextView) findViewById(R.id.geren_setting_textview_xiazaishu);
        tv_xiazaishu.setText(str_xiazaishu);
        tv_downloadpath= (TextView) findViewById(R.id.geren_setting_textview_xiazailujing);
        tv_downloadpath.setText(str_xiazailujing);
        tv_xiazaiyouxian= (TextView) findViewById(R.id.geren_setting_textview_xiazaiyouxian);
        tv_xiazaiyouxian.setText(str_xiazaiyouxian);
    }

    private void title() {
        textView= (TextView) findViewById(R.id.geren_back_tital_textview);
        textView.setText(R.string.activity_fragment_geren_shezhi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
    public void onClick(View v) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(SettingActivity.this);
        
        switch (v.getId()){
            case  R.id.geren_setting_layout_xiazaishu:
                final String[] xiazaishu={"1", "2", "3"};
                builder.setTitle("同时下载任务数");
                builder.setItems(xiazaishu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        tv_xiazaishu.setText(xiazaishu[which]);
                        editor.putString("xiazaishu",xiazaishu[which]);
                        editor.commit();
                    }
                });
                builder.show();
                break;
            case R.id.geren_setting_layout_xiazailujing:
                final String[] xiazailujing={"手机存储", "SD卡存储"};
                builder.setTitle("下载路径");
                builder.setItems(xiazailujing, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        tv_downloadpath.setText(xiazailujing[which]);
                        editor.putString("xiazailujing",xiazailujing[which]);
                        editor.commit();
                    }
                });
                builder.show();
                break;
            case R.id.geren_setting_layout_xiazaiyouxian:
                final String[] xiazaiyouxian={"流畅", "标准","高清","超清"};
                builder.setTitle("下载时优先选择");
                builder.setItems(xiazaiyouxian, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        tv_xiazaiyouxian.setText(xiazaiyouxian[which]);
                        editor.putString("xiazaiyouxian",xiazaiyouxian[which]);
                        editor.commit();
                    }
                });
                builder.show();
                break;
            case R.id.geren_setting_layout_clearcache:
                builder.setTitle("提示");
                builder.setMessage("确定要清除缓存么?");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             
                    }
                });
                builder.show();
                break;
        }
    }
}
