package com.huanliu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huanliu.R;

import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by susus on 2016/6/28.
 */
public class ShouYeAdapterV extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<Map<String,Object>> mData;

    public ShouYeAdapterV(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Map<String, Object>> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_scroll_v,null);

            viewHolder.iv_bm_v = (ImageView) convertView.findViewById(R.id.iv_bm_v);
            viewHolder.tv_name_v = (TextView) convertView.findViewById(R.id.tv_mtitle_v);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Map<String,Object> item = mData.get(position);
        viewHolder.tv_name_v.setText(item.get("tv_mtitle_v").toString());
        x.image().bind(viewHolder.iv_bm_v,item.get("iv_bm_v").toString());


        return convertView;
    }

    class ViewHolder{
        TextView tv_name_v;
        ImageView iv_bm_v;
    }
}
