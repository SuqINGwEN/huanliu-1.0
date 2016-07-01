package com.huanliu.util;

import android.app.Application;

import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/6/26 0026.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        x.Ext.init(this);
        ShareSDK.initSDK(this);
        super.onCreate();
    }
}
