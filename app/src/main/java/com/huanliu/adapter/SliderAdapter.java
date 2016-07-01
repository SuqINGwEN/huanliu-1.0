package com.huanliu.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by cb on 2016/5/5.
 */
public class SliderAdapter extends PagerAdapter {
    private List<Integer> mPaths;

    private Context mContext;

    public SliderAdapter(Context cx) {
        mContext = cx;
    }

    public void change(List<Integer> paths) {
        mPaths = paths;
    }

    @Override
    public int getCount() {
        return mPaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == (View) obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);

//		Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), mPaths.get(position));// 载入bitmap
//		iv.setImageBitmap(bm);
        Drawable bitmapDrawable = mContext.getResources().getDrawable(mPaths.get(position));
       iv.setImageDrawable(bitmapDrawable);

        ((ViewPager) container).addView(iv, 0);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
