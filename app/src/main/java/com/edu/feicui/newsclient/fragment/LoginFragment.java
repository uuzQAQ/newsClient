package com.edu.feicui.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/12/2.
 */

public class LoginFragment extends Fragment{
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_login) public void login(){

    }

    @OnClick(R.id.btn_register) public void register(){
        publishAddRegisterFragmentEvent();
    }

    @OnClick(R.id.btn_forget_password) public void forgetPassword(){
        publishAddForgotFragmentEvent();
    }

    public void publishAddRegisterFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_REGISTER_FRAGMENT);
        event.setFragmentFullName(LoginFragment.class.getName());
        EventBus.getDefault().post(event);//发布事件
    }

    public void publishAddForgotFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_FORGOT_FRAGMENT);
        event.setFragmentFullName(LoginFragment.class.getName());
        EventBus.getDefault().post(event);//发布事件
    }
}
