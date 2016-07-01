package com.huanliu.geren.zhucedenglu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huanliu.BaseActivity;
import com.huanliu.FirstActivity;
import com.huanliu.R;

import java.lang.reflect.Field;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

public class RegisterLoginActivity extends BaseActivity
        implements Handler.Callback, View.OnClickListener, PlatformActionListener {
    private TextView textView;

    private Handler handler;

    private static final int MSG_SMSSDK_CALLBACK = 1;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR = 3;
    private static final int MSG_AUTH_COMPLETE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_login);
        ShareSDK.initSDK(this);
        title();
    }

    private void title() {
        textView = (TextView) findViewById(R.id.geren_back_tital_textview);
        textView.setText(R.string.activity_fragment_geren_zhucedenglu);
        findViewById(R.id.qQlogin).setOnClickListener(this);
        findViewById(R.id.sinaWbLogin).setOnClickListener(this);
        findViewById(R.id.register_login_layout_login).setOnClickListener(this);
        handler = new Handler(this);
    }

    Platform qzon = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qQlogin:
                //qq平台
                qzon = ShareSDK.getPlatform(QZone.NAME);
                authorize(qzon);
                break;
            case R.id.sinaWbLogin:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                authorize(weibo);
                break;
            case R.id.register_login_layout_login:

                View view = View.inflate(RegisterLoginActivity.this, R.layout.login_builder_view, null);
                final View view1 = view.inflate(RegisterLoginActivity.this, R.layout.register_builder_view, null);
                final EditText edt_uname = (EditText) view.findViewById(R.id.login_builder_edt_username);
                final EditText edt_pwd = (EditText) view.findViewById(R.id.login_builder_edt_password);
                TextView tv_zhuce = (TextView) view.findViewById(R.id.login_builder_tv_reiguster);
                
                
                
                
                tv_zhuce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterLoginActivity.this);
                        builder.setTitle("用户注册");
                        builder.setView(view1);
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(dialog, true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }   
                            }
                        });
                        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText re_username= (EditText) view1.findViewById(R.id.register_builder_edt_username);
                                EditText re_password= (EditText) view1.findViewById(R.id.register_builder_edt_password);
                                EditText re_ag_password= (EditText) view1.findViewById(R.id.register_builder_edt_againpassword);
                                String r_username=re_username.getText().toString();
                                String r_password=re_password.getText().toString();
                                String r_ag_password=re_ag_password.getText().toString();
                                if(!r_username.isEmpty()&&!r_password.isEmpty()&&!r_ag_password.isEmpty()){
                                    if(r_password.equals(r_ag_password)){
                                        SharedPreferences.Editor editor = getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit();
                                        editor.putString("username", r_username);
                                        editor.putString("r_ag_password", r_ag_password);
                                        editor.commit();
                                        Intent intent = new Intent(RegisterLoginActivity.this, FirstActivity.class);
                                        intent.putExtra("register", 1);
                                        startActivity(intent);
                                        Toast.makeText(RegisterLoginActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                                        RegisterLoginActivity.this.finish();
                                    }else {
                                        Toast.makeText(RegisterLoginActivity.this, "两次密码不一致!", Toast.LENGTH_SHORT).show();
                                        try {
                                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                            field.setAccessible(true);
                                            field.set(dialog, false);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }else{
                                    Toast.makeText(RegisterLoginActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                                    try {
                                        Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                        field.setAccessible(true);
                                        field.set(dialog, false);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                        builder.show();
                    }
                });
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                final String s_username = sharedPreferences.getString("username", "");
                final String s_password = sharedPreferences.getString("r_ag_password", "");


                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterLoginActivity.this);
                builder.setView(view);
                builder.setTitle("用户登录");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);
                            field.set(dialog, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edt_uname.getText().toString();
                        String password = edt_pwd.getText().toString();
                        if (!name.isEmpty() && !password.isEmpty()) {
                            if (!s_username.isEmpty() && !s_password.isEmpty()) {
                                Toast.makeText(RegisterLoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterLoginActivity.this, FirstActivity.class);
                                intent.putExtra("register",1);
                                startActivity(intent);
                                RegisterLoginActivity.this.finish();
                                
                            } else {
                                Toast.makeText(RegisterLoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                                try {
                                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(dialog, false);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Toast.makeText(RegisterLoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                            try {
                                Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                builder.show();
                break;
        }
    }

    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        plat.setPlatformActionListener(this);
        //sso授权
        plat.SSOSetting(true);
        //显示用户信息
        plat.showUser(null);
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_CANCEL:
                Toast.makeText(this, "授权已取消", Toast.LENGTH_SHORT).show();
                break;
            case MSG_AUTH_ERROR:
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                break;
            case MSG_AUTH_COMPLETE:
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                Object[] res = (Object[]) msg.obj;
                String platName = (String) res[0];
                Platform platform = ShareSDK.getPlatform(platName);
                PlatformDb db = platform.getDb();
                String username = db.getUserName();
                SharedPreferences.Editor editor = getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit();
                editor.putString("username", username);
                editor.commit();
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }


    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> map) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[]{platform.getName(), map};
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        t.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }
}
