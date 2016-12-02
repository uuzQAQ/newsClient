package com.edu.feicui.newsclient.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.utils.Url;


/**
 * Created by user on 2016/11/23.
 */

public class BaseActivity extends FragmentActivity{

    private Dialog dialog;

    public void showDialog(String message,boolean isCancel){
        View view = getLayoutInflater().inflate(R.layout.loading,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_loading);
        TextView textView = (TextView) view.findViewById(R.id.tv_loading);

        if(!TextUtils.isEmpty(message)){
            textView.setText(message);
        }

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_load);
        imageView.startAnimation(animation);

        dialog = new Dialog(this,R.style.loading_style);
        dialog.setContentView(view,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        dialog.setCancelable(isCancel);
        dialog.show();
    }

    public void cancelDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();//
        }
    }

    public  void startActivity(Class<? extends Activity> clazz){
        startActivity(clazz,null,null);
    }

    public  void startActivity(Class<? extends Activity> clazz, Bundle bundle){
        startActivity(clazz,bundle,null);
    }

    public void startActivity(Class<? extends Activity> clazz, Bundle bundle,Uri uri){
        Intent intent = new Intent(this,clazz);

        if(bundle != null){
            intent.putExtras(bundle);
        }

        if(uri != null){
            intent.setData(uri);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.bottom_out);
    }

}
