package com.edu.feicui.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.XListView.XListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/12/2.
 */

public class CollectFragment extends Fragment{

    @BindView(R.id.xlv)
    XListView xlv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
