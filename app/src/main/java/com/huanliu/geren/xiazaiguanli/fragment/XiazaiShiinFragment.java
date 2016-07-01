package com.huanliu.geren.xiazaiguanli.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.huanliu.R;
import com.huanliu.VideoTestActivity;
import com.huanliu.adapter.DownloadListviewAdapter;
import com.huanliu.model.Video;
import com.huanliu.model.VideoInfo;
import com.huanliu.util.Util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class XiazaiShiinFragment extends Fragment {
    private View view;
    private ListView mListView=null;
    private DownloadListviewAdapter adapter;
    private static List<VideoInfo> allVideoList = null;// 视频信息集合 
    private List<Video> mData;
    
    private int Value=0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_download_management_fragment_xiazaishipin,container,false);
        
        mListView=(ListView)view.findViewById(R.id.bendishipin_myEmpty_listview);
        mListView.setEmptyView(view.findViewById(R.id.bendishipin_myEmpty_LinearLayout));
        adapter=new DownloadListviewAdapter(view.getContext());
        mData=new ArrayList<>();
        allVideoList = new ArrayList<VideoInfo>();
        if(Value!=0){
            getData();
        }
        return view;
    }

    private void getData() {
        getVideoFile(allVideoList, Environment.getExternalStorageDirectory());
        for(VideoInfo info:allVideoList) {
            Video video=new Video();
            String name = info.getName();
            String path = info.getPath();
            String size = Util.getAutoFileOrFilesSize(path);
            Bitmap icon = Util.getVideoThumbnail(path);
            video.setName(name);
            video.setPath(path);
            video.setSize(size);
            video.setIcon(icon);
            mData.add(video);
        }
        adapter.setData(mData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video=mData.get(position);
                String path=video.getPath();
                Intent intent=new Intent(view.getContext(), VideoTestActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
    }

    private String[] generateStrings() {
        String[] strings = new String[0];
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = "String " + i;
        }
        return strings;
    }

    // 获得视频文件  
    private void  getVideoFile(final List<VideoInfo> list, File file) {// 获得视频文件  
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称  
                String name = file.getName();

                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        VideoInfo vi = new VideoInfo();
                        vi.setName(file.getName());
                        vi.setPath(file.getAbsolutePath());
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });
    }
    
    public void startGet(int value){
        this.Value=value;
    }
}
