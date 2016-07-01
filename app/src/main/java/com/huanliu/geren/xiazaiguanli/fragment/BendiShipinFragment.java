package com.huanliu.geren.xiazaiguanli.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.huanliu.R;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class BendiShipinFragment extends Fragment {
    private View view;
    private ListView mListView=null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_download_management_fragment_bendishipin,container,false);
        mListView=(ListView)view.findViewById(R.id.downlad_myEmpty_listview);
        mListView.setEmptyView(view.findViewById(R.id.downlad_myEmpty_LinearLayout));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, generateStrings());

        mListView.setAdapter(adapter);
        return view;
    }

    private String[] generateStrings() {
        String[] strings = new String[0];
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = "String " + i;
        }
        return strings;
    }
}
