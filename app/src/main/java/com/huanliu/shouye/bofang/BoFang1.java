package com.huanliu.shouye.bofang;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.huanliu.R;
import com.huanliu.adapter.BoFang1Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoFang1 extends Activity {
    private VideoView videoView;
    private MediaController mediaController;
    private GridView gv_xiangguan;
    private BoFang1Adapter adapter;
    private List<Map<String,Object>> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bofang1);
        init();
        initData();
    }

    private void initData() {
        String strUrl = "http://bb.shoujiduoduo.com/baby/bb.php?type=getvideos&pagesize=30&collectid=29";
//        String strUrl = "http://localhost:8080/SeeVideo/video!SelectByPager.action?pager.pageIndex=1";
        RequestParams params2=new RequestParams(strUrl);
        x.http().get(params2,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Log.i("---BoFang1Activity---","json"+o);
                try {
                    JSONObject jsonObject=new JSONObject(o);
//                    Log.i("---BoFang1Activity---","jsonObject"+jsonObject);
                    JSONArray list=jsonObject.getJSONArray("list");
//                    Log.i("---BoFang1Activity---","list"+list);
                    for(int i=1;i<list.length();i++){
                        JSONObject object=list.getJSONObject(i);
                        Map<String,Object> map=new HashMap<>();
                        map.put("tv_mtitle",object.getString("name"));
                        map.put("iv_bm",object.getString("pic"));
                        map.put("downurl",object.getString("downurl"));
//                        map.put("tv_mtitle",object.getString("title"));
//                        map.put("iv_bm",object.getString("image"));
                        mData.add(map);
                    }
                    adapter = new BoFang1Adapter(getApplication());
//                    Log.i("---BoFang1Activity---","mData"+mData);
                    adapter.setData(mData);
//                    Log.i("---BoFang1Activity---","adapter"+adapter);
                    gv_xiangguan.setAdapter(adapter);
//                    Log.i("---BoFang1Activity---","gv_1"+gv_1);
                    gv_xiangguan.setSelector(new ColorDrawable(Color.TRANSPARENT));
                    gv_xiangguan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
                            String url= (String) mData.get(position).get("downurl");
                            Intent intent = new Intent(getApplicationContext(), BoFang1.class);
                            Log.i("---BoFang1Activity---","url"+url);
                            intent.putExtra("url",url);
                            BoFang1.this.startActivity(intent);
                            BoFang1.this.finish();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("---BoFang1Activity---",throwable+"解析错误"+b);
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("---BoFang1Activity---","解析取消"+e);
            }

            @Override
            public void onFinished() {
                Log.i("---BoFang1Activity---","finish");
            }
        });
    }

    private void init() {
        adapter = new BoFang1Adapter(this);
        gv_xiangguan = (GridView) findViewById(R.id.gv_xiangguan);
        videoView= (VideoView) findViewById(R.id.vv_bofang);
        mediaController=new MediaController(this);
        Intent intent = getIntent(); //用于激活它的意图对象
        String strUrl = intent.getStringExtra("url");
        Log.i("---BoFangActivity---","strUrl"+strUrl);
        Uri uri = Uri.parse(strUrl);
        Log.i("---BoFangActivity---","uri"+uri);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.start();
//        videoView.requestFocus();

    }



}
