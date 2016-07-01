package com.huanliu.fragment.shouyefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huanliu.R;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class PinDaoFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_fragment_pindao,container,false);
        return view;
    }
}
