package com.huanliu.fragment.shouyefragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.huanliu.R;
import com.huanliu.adapter.ShouYeAdapterH;
import com.huanliu.adapter.ShouYeAdapterV;
import com.huanliu.shouye.bofang.BoFang1;

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

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class ShouYeFragment extends Fragment {
    private View view;
    private GridView
            gv_1, gv_2, gv_3, gv_4, gv_5,
            gv_6, gv_7, gv_8, gv_9, gv_10,
            gv_11, gv_12, gv_13, gv_14, gv_15;
    private List<Map<String, Object>> mData = new ArrayList<>();
    private Integer[] imageId = {R.drawable.icon,
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };
    private ShouYeAdapterH adapter;
    private ShouYeAdapterV adapter1;

    //    private ViewPager viewpager;
//    private SliderAdapter adapter1;
//    private LinearLayout viewGroup;
//    private ImageView dot, dots[];
//    private Runnable runnable;
//    private int autoChangeTime = 5000;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_shouye, container, false);

        init();
        initData1();
        initData2();
//        updateGridView();
//        setListener();
//        initViewPager();


        return view;
    }

    private void initData2() {
        String strUrl = "http://bb.shoujiduoduo.com/baby/bb.php?type=getlistv2&act=home&pagesize=30&pid=26";
        RequestParams params = new RequestParams(strUrl);
        x.http().get(params,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray list = jsonObject.getJSONArray("list");
                    for (int i=1;i<=list.length();i++){
                        JSONObject object = list.getJSONObject(i);
                        Map<String,Object> item = new HashMap<String, Object>();
                        item.put("tv_mtitle_v",object.getString("name"));
                        item.put("iv_bm_v",object.getString("pic"));
                        item.put("","");
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initData1() {

        String strUrl = "http://bb.shoujiduoduo.com/baby/bb.php?type=getvideos&pagesize=30&collectid=29";
//        String strUrl = "http://localhost:8080/SeeVideo/video!SelectByPager.action?pager.pageIndex=1";
        RequestParams params2 = new RequestParams(strUrl);
        x.http().get(params2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Log.i("---shouyefragment---", "json" + o);
                try {
                    JSONObject jsonObject = new JSONObject(o);
//                    Log.i("---shouyefragment---","jsonObject"+jsonObject);
                    JSONArray list = jsonObject.getJSONArray("list");
//                    Log.i("---shouyefragment---","list"+list);
                    for (int i = 1; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        Map<String, Object> map = new HashMap<>();
                        map.put("tv_mtitle", object.getString("name"));
                        map.put("iv_bm", object.getString("pic"));
                        map.put("downurl", object.getString("downurl"));
//                        map.put("tv_mtitle",object.getString("title"));
//                        map.put("iv_bm",object.getString("image"));
                        mData.add(map);
                    }
                    adapter = new ShouYeAdapterH(getActivity());
//                    Log.i("---shouyefragment---","mData"+mData);
                    adapter.setData(mData);
//                    Log.i("---shouyefragment---","adapter"+adapter);
                    gv_1.setAdapter(adapter);
//                    Log.i("---shouyefragment---","gv_1"+gv_1);
                    gv_1.setSelector(new ColorDrawable(Color.TRANSPARENT));
                    gv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
                            String url = (String) mData.get(position).get("downurl");
                            Intent intent = new Intent(getActivity(), BoFang1.class);
                            Log.i("---shouyefragment---", "url" + url);
                            intent.putExtra("url", url);
                            ShouYeFragment.this.startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("---shouyefragment---", throwable + "解析错误" + b);
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("---shouyefragment---", "解析取消" + e);
            }

            @Override
            public void onFinished() {
                Log.i("---shouyefragment---", "finish");
            }
        });
    }

    public void setListener() {
//        gv_1.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang1.class);
//                startActivity(intent);
//            }
//        });
//        gv_2.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_3.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_4.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_5.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_6.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_7.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_8.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_9.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_10.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_11.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang1.class);
//                startActivity(intent);
//            }
//        });
//        gv_12.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang1.class);
//                startActivity(intent);
//            }
//        });
//        gv_13.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_13.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang1.class);
//                startActivity(intent);
//            }
//        });
//        gv_14.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_14.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang2.class);
//                startActivity(intent);
//            }
//        });
//        gv_15.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gv_15.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), BoFang1.class);
//                startActivity(intent);
//            }
//        });
    }

    public void init() {
        gv_1 = (GridView) view.findViewById(R.id.gv_1);
        gv_2 = (GridView) view.findViewById(R.id.gv_2);
//        gv_3 = (GridView) view.findViewById(R.id.gv_3);
//        gv_4 = (GridView) view.findViewById(R.id.gv_4);
//        gv_5 = (GridView) view.findViewById(R.id.gv_5);
//        gv_6 = (GridView) view.findViewById(R.id.gv_6);
//        gv_7 = (GridView) view.findViewById(R.id.gv_7);
//        gv_8 = (GridView) view.findViewById(R.id.gv_8);
//        gv_9 = (GridView) view.findViewById(R.id.gv_9);
//        gv_10 = (GridView) view.findViewById(R.id.gv_10);
//        gv_11 = (GridView) view.findViewById(R.id.gv_11);
//        gv_12 = (GridView) view.findViewById(R.id.gv_12);
//        gv_13 = (GridView) view.findViewById(R.id.gv_13);
//        gv_14 = (GridView) view.findViewById(R.id.gv_14);
//        gv_15 = (GridView) view.findViewById(R.id.gv_15);
//        mData = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < imageId.length; i++) {
//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("imageId", imageId[i]);
//            item.put("textId", "item" + (i + 1));
////            item.put("detailId","简介："+(i+1));
//            mData.add(item);
//        }
//
//        adapter = new ShouyeAdapter(getActivity());
    }

    public void updateGridView() {
//        adapter.setData(mData);
//        gv_1.setAdapter(adapter);
//        gv_2.setAdapter(adapter);
//        gv_3.setAdapter(adapter);
//        gv_4.setAdapter(adapter);
//        gv_5.setAdapter(adapter);
//        gv_6.setAdapter(adapter);
//        gv_7.setAdapter(adapter);
//        gv_8.setAdapter(adapter);
//        gv_9.setAdapter(adapter);
//        gv_10.setAdapter(adapter);
//        gv_11.setAdapter(adapter);
//        gv_12.setAdapter(adapter);
//        gv_13.setAdapter(adapter);
//        gv_14.setAdapter(adapter);
//        gv_15.setAdapter(adapter);
    }

//    private void initViewPager() {
//        adapter1 = new SliderAdapter(getActivity());
//        adapter1.change(getList());
//        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
//        viewpager.setAdapter(adapter1);
//        viewpager.setOnPageChangeListener(myOnPageChangeListener);

//        initDot();

//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                int next = viewpager.getCurrentItem() + 1;
//                if (next >= adapter.getCount()) {
//                    next = 0;
//                }
//                viewHandler.sendEmptyMessage(next);
//            }
//        };
//
//        viewHandler.postDelayed(runnable, autoChangeTime);//只执行一次
//      viewHandler.post(runnable);
//    }

    List<Integer> list;

//    private List<Integer> getList() {
//        list = new ArrayList<Integer>();
//        list.add(R.drawable.btn_dowload_press);
//        list.add(R.drawable.btn_pause_press);
//        list.add(R.drawable.btn_retry_press);
//        list.add(R.drawable.btn_wait_press);
//        list.add(R.drawable.btn_dowload_press);
//        list.add(R.drawable.btn_pause_press);
//        list.add(R.drawable.btn_retry_press);
//        list.add(R.drawable.btn_wait_press);
//        return list;
//    }

//    // 初始化dot视图
//    private void initDot() {
//        viewGroup = (LinearLayout) view.findViewById(R.id.viewGroup);
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                20, 20);
//        layoutParams.setMargins(1, 1, 1, 1);
//
//        dots = new ImageView[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            dot = new ImageView(getActivity());
//            dot.setLayoutParams(layoutParams);
//            dots[i] = dot;
//            if (i == 0) {
//                dots[i].setBackgroundResource(R.drawable.dotc);
//            } else {
//                dots[i].setBackgroundResource(R.drawable.dotn);
//            }
//            dots[i].setTag(i);
//            dots[i].setOnClickListener(onClick);
//            viewGroup.addView(dots[i]);
//        }
//    }
//
//    ViewPager.OnPageChangeListener myOnPageChangeListener = new ViewPager.OnPageChangeListener() {
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void onPageSelected(int arg0) {
//            // TODO Auto-generated method stub
//            setCurDot(arg0);
//            viewHandler.removeCallbacks(runnable);
//            viewHandler.postDelayed(runnable, autoChangeTime);
//        }
//
//    };
//
//    // 实现dot点击响应功能
//    View.OnClickListener onClick = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            int position = (Integer) v.getTag();
//            setCurView(position);
//        }
//
//    };
//
//    /**
//     * 设置当前的引导页
//     */
//    private void setCurView(int position) {
//        if (position < 0 || position >= adapter.getCount()) {
//            return;
//        }
//        viewpager.setCurrentItem(position);
//    }
//
//    /**
//     * 选中当前引导小点
//     */
//    private void setCurDot(int position) {
//        for (int i = 0; i < dots.length; i++) {
//            if (position == i) {
//                dots[i].setBackgroundResource(R.drawable.dotc);
//            } else {
//                dots[i].setBackgroundResource(R.drawable.dotn);
//            }
//        }
//    }
//
//    /**
//     * 每隔固定时间切换广告栏图片
//     */
//    private final Handler viewHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            setCurView(msg.what);
//        }
//
//    };
}
