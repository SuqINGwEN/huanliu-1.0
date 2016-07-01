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

import java.util.List;
import java.util.Map;

/**
 * Created by susus on 2016/6/19.
 */
public class ShouYeAdapterH extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> mData;

    public ShouYeAdapterH() {
    }

    public ShouYeAdapterH(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Map<String, Object>> mData) {
        this.mData = mData;
    }

    public ShouYeAdapterH(Context context, LayoutInflater inflater, List<Map<String, Object>> mData) {
        this.context = context;
        this.inflater = inflater;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return 6;
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
            view = inflater.inflate(R.layout.item_scroll_h, null);

            viewHolder.iv_bm = (ImageView) view.findViewById(R.id.iv_bm_h);
            viewHolder.tv_mtitle = (TextView) view.findViewById(R.id.tv_mtitle_h);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Map<String, Object> item = mData.get(i);
        viewHolder.tv_mtitle.setText(item.get("tv_mtitle").toString());
        x.image().bind(viewHolder.iv_bm,item.get("iv_bm").toString());
        return view;
    }

    class ViewHolder {
        ImageView iv_bm;
        TextView tv_mtitle;
    }
}
