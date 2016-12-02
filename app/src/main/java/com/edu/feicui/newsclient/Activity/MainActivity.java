package com.edu.feicui.newsclient.Activity;

import android.provider.Settings;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.base.BaseActivity;
import com.edu.feicui.newsclient.entity.MessageEvent;
import com.edu.feicui.newsclient.fragment.ForgetPasswordFragment;
import com.edu.feicui.newsclient.fragment.LeftMenuFragment;
import com.edu.feicui.newsclient.fragment.LoginFragment;
import com.edu.feicui.newsclient.fragment.MainFragment;
import com.edu.feicui.newsclient.fragment.RegisterFragment;
import com.edu.feicui.newsclient.fragment.RightMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    private ImageView ivHome;
    private ImageView ivShare;
//    private TextView tvTitle;
    public  static SlidingMenu slidingMenu;
    private LeftMenuFragment leftMenuFragment;
    private RightMenuFragment rightMenuFragment;
    private long prevTime;
    private MainFragment mainFragment;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private TextView tvTitle;
    private ForgetPasswordFragment forgetPasswordFragment;

    FragmentManager fm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        initView();
        initSlidingMenu();
        initFragment();
        ivHome.setOnClickListener(listener);
        ivShare.setOnClickListener(listener);
    }

    public void initFragment(){
        if(mainFragment == null){
            mainFragment = new MainFragment();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_contain,mainFragment,"mainFragment");
        ft.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchFragment(MessageEvent event){
        String title = "";
        switch (event.getType()){
            case MessageEvent.TYPE_FORGOT_FRAGMENT:
                if(forgetPasswordFragment == null){
                    forgetPasswordFragment = new ForgetPasswordFragment();
                }
                fm.beginTransaction().replace(R.id.fl_contain,forgetPasswordFragment).commit();
                title = "忘记密码";
                break;
            case MessageEvent.TYPE_MAIN_FRAGMENT:
                if(mainFragment == null){
                    mainFragment = new MainFragment();
                }
                fm.beginTransaction().replace(R.id.fl_contain,mainFragment).commit();
                title = "资讯";
                break;
            case MessageEvent.TYPE_LOGIN_FRAGMENT:
                if(loginFragment == null){
                    loginFragment = new LoginFragment();
                }
                fm.beginTransaction().replace(R.id.fl_contain,loginFragment).commit();
                title = "用户登录";
                break;
            case MessageEvent.TYPE_REGISTER_FRAGMENT:
                if(registerFragment == null){
                    registerFragment = new RegisterFragment();
                }
                fm.beginTransaction().replace(R.id.fl_contain,registerFragment).commit();
                title = "用户注册";
                break;

        }
        if(slidingMenu != null && slidingMenu.isMenuShowing()){
            slidingMenu.showContent();
        }
        tvTitle.setText(title);
    }

    private void initSlidingMenu() {
        leftMenuFragment = new LeftMenuFragment();
        rightMenuFragment = new RightMenuFragment();

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_margin);
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.left_menu);
        slidingMenu.setSecondaryMenu(R.layout.right_menu);


        getSupportFragmentManager().beginTransaction().replace(R.id.ll_left,leftMenuFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_right,rightMenuFragment).commit();
    }

    private void initView() {
        ivHome = (ImageView) findViewById(R.id.iv_home);
        ivShare = (ImageView) findViewById(R.id.iv_share);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }


    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.iv_home:
                    if(slidingMenu != null && slidingMenu.isMenuShowing()){
                        slidingMenu.showContent();
                    }else{
                        slidingMenu.showMenu();
                    }
                    break;
                case R.id.iv_share:
                    if(slidingMenu != null && slidingMenu.isMenuShowing()){
                        slidingMenu.showContent();
                    }else{
                        slidingMenu.showSecondaryMenu();
                    }
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {

        if(slidingMenu != null && slidingMenu.isMenuShowing()){
            slidingMenu.showContent();

        }else{

            twiceExit();
        }
    }

    //两次退出
    private void twiceExit(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - prevTime > 1500){
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            prevTime = currentTime;

        }else{

            prevTime = currentTime;
            finish();
            System.exit(0);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
