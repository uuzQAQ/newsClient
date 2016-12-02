package com.edu.feicui.newsclient.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by user on 2016/11/24.
 */

public class RightMenuFragment extends Fragment{
    private LinearLayout llLogin;
    private TextView tvUpdate;
    private ImageView ivWeixin;
    private ImageView ivQQ;
    private ImageView ivFriends;
    private ImageView ivWeibo;
    private TextView tvName;
    private ImageView ivLogin;
    private TextView tvLogining;
    private ImageView ivLogining;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_menu,container,false);

        llLogin = (LinearLayout) view.findViewById(R.id.ll_unlogin);
        tvUpdate = (TextView) view.findViewById(R.id.tv_update_vision);
        ivWeibo = (ImageView) view.findViewById(R.id.iv_weobo);
        ivWeixin = (ImageView) view.findViewById(R.id.iv_weixin);
        ivQQ = (ImageView) view.findViewById(R.id.iv_qq);
        ivFriends = (ImageView) view.findViewById(R.id.iv_friends);
        ivLogin = (ImageView) view.findViewById(R.id.iv_lodined);
        tvName = (TextView) view.findViewById(R.id.tv_logined_name);

        tvLogining = (TextView) view.findViewById(R.id.tv_logining);
        ivLogining = (ImageView) view.findViewById(R.id.iv_logining);

        tvName.setOnClickListener(listener);
        tvUpdate.setOnClickListener(listener);
        ivFriends.setOnClickListener(listener);
        ivQQ.setOnClickListener(listener);
        ivWeibo.setOnClickListener(listener);
        ivWeixin.setOnClickListener(listener);
        ivLogin.setOnClickListener(listener);
        ivLogining.setOnClickListener(listener);
        tvLogining.setOnClickListener(listener);

        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_logining:
                    publishAddLoginFragmentEvent();
                    break;
                case R.id.iv_logining:
                    publishAddLoginFragmentEvent();
                    break;
                case R.id.tv_update_vision:
                    break;
                case R.id.iv_weixin:
                    break;
                case R.id.iv_qq:
                    break;
                case R.id.iv_friends:
                    break;
                case R.id.iv_weobo:
                    break;
            }
        }
    };

    public void publishAddLoginFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_LOGIN_FRAGMENT);
        event.setFragmentFullName(LoginFragment.class.getName());
        EventBus.getDefault().post(event);//发布事件
    }


}
