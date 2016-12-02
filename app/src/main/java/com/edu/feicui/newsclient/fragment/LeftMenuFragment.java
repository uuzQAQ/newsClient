package com.edu.feicui.newsclient.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by user on 2016/11/23.
 */

public class LeftMenuFragment extends Fragment {
    private RelativeLayout rlNews;
    private RelativeLayout rlFavorite;
    private RelativeLayout rlLocal;
    private RelativeLayout rlComment;
    private RelativeLayout rlPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu,container,false);
        rlNews = (RelativeLayout) view.findViewById(R.id.rl_news);
        rlFavorite = (RelativeLayout) view.findViewById(R.id.rl_favorite);
        rlLocal = (RelativeLayout) view.findViewById(R.id.rl_local);
        rlComment = (RelativeLayout) view.findViewById(R.id.rl_comment);
        rlPhoto = (RelativeLayout) view.findViewById(R.id.rl_photo);

        rlNews.setOnClickListener(listener);
        rlComment.setOnClickListener(listener);
        rlFavorite.setOnClickListener(listener);
        rlPhoto.setOnClickListener(listener);
        rlLocal.setOnClickListener(listener);

        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //重置颜色
            rlPhoto.setBackgroundColor(0);
            rlLocal.setBackgroundColor(0);
            rlNews.setBackgroundColor(0);
            rlComment.setBackgroundColor(0);
            rlFavorite.setBackgroundColor(0);

            switch(view.getId()){
                case R.id.rl_news:
                    rlNews.setBackgroundColor(Color.parseColor("#40282c"));
                    Toast.makeText(getActivity(),"新闻",Toast.LENGTH_SHORT).show();
                    publishAddMainFragmentEvent();
                    break;
                case R.id.rl_favorite:
                    rlFavorite.setBackgroundColor(Color.parseColor("#40282c"));
                    Toast.makeText(getActivity(),"收藏",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_local:
                    rlLocal.setBackgroundColor(Color.parseColor("#40282c"));
                    Toast.makeText(getActivity(),"本地",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_comment:
                    rlComment.setBackgroundColor(Color.parseColor("#40282c"));
                    Toast.makeText(getActivity(),"跟帖",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_photo:
                    rlPhoto.setBackgroundColor(Color.parseColor("#40282c"));
                    Toast.makeText(getActivity(),"图片",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void publishAddMainFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_MAIN_FRAGMENT);
        event.setFragmentFullName(MainFragment.class.getName());
        EventBus.getDefault().post(event);//发布事件
    }
}
