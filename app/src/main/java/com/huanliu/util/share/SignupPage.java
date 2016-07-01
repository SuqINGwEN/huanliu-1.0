package com.huanliu.util.share;


import android.content.Context;
import android.content.SharedPreferences;

import com.mob.tools.FakeActivity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public class SignupPage extends FakeActivity {
    private Platform platform;
    private HashMap<String,Object> userData;
    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        if(platform!=null){
            PlatformDb db=platform.getDb();
            String username=db.getUserName();
            SharedPreferences.Editor editor=activity.getSharedPreferences("userinfo",Context.MODE_PRIVATE).edit();
            editor.putString("username",username);
        }
    }

    public void setUserData(HashMap<String, Object> userData) {
        this.userData = userData;
    }

    public void setPlatform(String platName) {
        platform = ShareSDK.getPlatform(platName);
    }
}
