package com.huanliu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huanliu.R;
import com.huanliu.model.Video;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class DownloadListviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Video> mData;

    public DownloadListviewAdapter() {
    }

    public DownloadListviewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Video> mData) {
        this.mData = mData;
    }

    public DownloadListviewAdapter(Context context, LayoutInflater inflater, List<Video> mData) {
        this.context = context;
        this.inflater = inflater;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        
        if (view == null) {
            view = inflater.inflate(R.layout.item_download_video, null);
            viewHolder.name = (TextView) view.findViewById(R.id.shipin_lv_item_name);
            viewHolder.path = (TextView) view.findViewById(R.id.shipin_lv_item_path);
            viewHolder.size = (TextView) view.findViewById(R.id.shipin_lv_item_size);
            viewHolder.icon= (ImageView) view.findViewById(R.id.shipin_lv_item_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Video video = mData.get(i);
        viewHolder.name.setText(video.getName());
        viewHolder.path.setText(video.getPath());
        viewHolder.size.setText(video.getSize());
        viewHolder.icon.setImageBitmap(video.getIcon());
        return view;
}
    class ViewHolder{
        TextView name;
        TextView path;
        TextView size;
        ImageView icon;
    }
}
