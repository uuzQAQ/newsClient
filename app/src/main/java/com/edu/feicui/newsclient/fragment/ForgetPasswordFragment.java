package com.edu.feicui.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/12/2.
 */

public class ForgetPasswordFragment extends Fragment{
    @BindView(R.id.et_email)
    EditText etEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgetpassword,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_confirm) public void confirm(){
        CommonUtils.showShortToast(getActivity(),"密码已发送到邮箱");
    }
}
