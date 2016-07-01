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
 * Created by susus on 2016/6/28.
 */
public class BoFang1Adapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> mData;

    public BoFang1Adapter(Context context) {
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
        ViewHolder holder = null;
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_scroll_h, null);
            holder.iv_bm = (ImageView) convertView.findViewById(R.id.iv_bm_h);
            holder.tv_mtitle = (TextView) convertView.findViewById(R.id.tv_mtitle_h);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Map<String, Object> item = mData.get(position);
        holder.tv_mtitle.setText(item.get("tv_mtitle").toString());
        x.image().bind(holder.iv_bm, item.get("iv_bm").toString());

        return convertView;
    }

    class ViewHolder {
        TextView tv_mtitle;
        ImageView iv_bm;
    }
}
