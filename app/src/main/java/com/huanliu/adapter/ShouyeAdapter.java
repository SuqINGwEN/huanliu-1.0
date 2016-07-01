package com.huanliu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huanliu.R;

import java.util.List;
import java.util.Map;

/**
 * Created by susus on 2016/6/19.
 */
public class ShouyeAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> mData;

    public ShouyeAdapter() {
    }

    public ShouyeAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Map<String, Object>> mData) {
        this.mData = mData;
    }

    public ShouyeAdapter(Context context, LayoutInflater inflater, List<Map<String, Object>> mData) {
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
            view = inflater.inflate(R.layout.item_scroll_h, null);

//            view = inflater.inflate(R.layout.item_scroll_v, null);
            viewHolder.iv_bm = (ImageView) view.findViewById(R.id.iv_bm_h);
            viewHolder.tv_mtitle = (TextView) view.findViewById(R.id.tv_mtitle_h);
//            viewHolder.tv_mdetail = (TextView) view.findViewById(R.id.tv_mdetail_h);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Map<String, Object> item = mData.get(i);
        viewHolder.iv_bm.setImageResource((Integer) item.get("imageId"));
        viewHolder.tv_mtitle.setText(item.get("textId").toString());
//        viewHolder.tv_mdetail.setText(item.get("detailId").toString());

        return view;
    }

    class ViewHolder {
        ImageView iv_bm;
        TextView tv_mtitle;
        TextView tv_mdetail;
//        GridView gv;
    }
}
