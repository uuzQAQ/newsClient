package com.edu.feicui.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.edu.feicui.newsclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/12/2.
 */

public class RegisterFragment extends Fragment{

    @BindView(R.id.et_register_username)
    EditText etUsername;
    @BindView(R.id.et_register_password)
    EditText etPassword;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.cb_agree)
    CheckBox cb;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_registering) public void registering(){

    }
}
