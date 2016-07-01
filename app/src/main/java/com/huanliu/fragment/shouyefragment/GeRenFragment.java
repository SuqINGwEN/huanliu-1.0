package com.huanliu.fragment.shouyefragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huanliu.R;
import com.huanliu.geren.guankanlishi.HistoryActivity;
import com.huanliu.geren.guanyu.AboutProjectActivity;
import com.huanliu.geren.shezhi.SettingActivity;
import com.huanliu.geren.wentifankui.FeedbackActivity;
import com.huanliu.geren.wodeshoucang.MyCollectionActivity;
import com.huanliu.geren.xiazaiguanli.DownloadManagementActivity;
import com.huanliu.geren.zhucedenglu.RegisterLoginActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.zXing.CaptureActivity;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class GeRenFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button quit_btn;
    private Platform qzone;
    private Platform weibo;
    //已登录
    private static final int LOGIN = 0;
    //未登录
    private static final int QUIT = 1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOGIN:
                    quit_btn.setVisibility(View.VISIBLE);
                    break;
                case QUIT:
                    quit_btn.setVisibility(View.GONE);
                    break;
            }
        }
    };
    
    private Intent intent;
    private TextView tv_username;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_geren, container, false);
        view.findViewById(R.id.fragment_geren_xiazaiguanli).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_guankanlishi).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_wodeshoucang).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_shezhi).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_erweima).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_tuijian).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_wentifankui).setOnClickListener(this);
        view.findViewById(R.id.fragment_geren_guanyu).setOnClickListener(this);

        intent=new Intent();
        tv_username= (TextView) view.findViewById(R.id.tv_geren_username);
       //判断是否登录,如果登录显示退出按钮,并禁用登录的点击事件,显示姓名,否则隐藏
        ShareSDK.initSDK(view.getContext());
        quit_btn= (Button) view.findViewById(R.id.btn_geren_quit);
        qzone = ShareSDK.getPlatform(view.getContext(), QZone.NAME);
        weibo=ShareSDK.getPlatform(view.getContext(), SinaWeibo.NAME);
        SharedPreferences preferences=view.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String username=preferences.getString("username","");
        
//        if(qzone.isValid()||weibo.isValid()){
//            handler.sendEmptyMessage(LOGIN);
//            view.findViewById(R.id.btn_geren_quit).setOnClickListener(this);
//            tv_username.setText(username);
//        }else{
//            handler.sendEmptyMessage(QUIT);
//            view.findViewById(R.id.fragment_geren_zhucedenglu).setOnClickListener(this);
//        }
        if(!username.isEmpty()){
            handler.sendEmptyMessage(LOGIN);
            view.findViewById(R.id.btn_geren_quit).setOnClickListener(this);
            tv_username.setText(username);
        }else{
            handler.sendEmptyMessage(QUIT);
            view.findViewById(R.id.fragment_geren_zhucedenglu).setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.fragment_geren_zhucedenglu:
                intent.setClass(view.getContext(),RegisterLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_xiazaiguanli:
                intent.setClass(view.getContext(),DownloadManagementActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_guankanlishi:
                intent.setClass(view.getContext(),HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_wodeshoucang:
                intent.setClass(view.getContext(),MyCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_shezhi:
                intent.setClass(view.getContext(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_erweima:
                intent.setClass(view.getContext(),CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_tuijian:
                OnekeyShare oks = new OnekeyShare();
                oks.disableSSOWhenAuthorize();
                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("分享");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("https://github.com/zhengyu111");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("郑煜的github");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("https://github.com/zhengyu111");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("此处是评论");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("https://github.com/zhengyu111");
                // 启动分享GUI
                oks.show(view.getContext());
                break;
            case R.id.fragment_geren_wentifankui:
                intent.setClass(view.getContext(),FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_geren_guanyu:
                intent.setClass(view.getContext(),AboutProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_geren_quit:
//                String DATA_URL = "/data/data/";
//                String SHARED_MAIN_XML = "main.xml";
//                File file = new File(DATA_URL + "/com.huanliu"
//                        + "/userinfo");
//                if (file.exists()) {
//                    file.delete();
//                }
                qzone.removeAccount();
                handler.sendEmptyMessage(QUIT);
                tv_username.setText(R.string.activity_fragment_geren_zhucedenglu);
                view.findViewById(R.id.fragment_geren_zhucedenglu).setOnClickListener(this);
                Toast.makeText(view.getContext(),R.string.activity_fragment_geren_quit_success,Toast.LENGTH_SHORT).show();
                break;
        }
           
    }
}
