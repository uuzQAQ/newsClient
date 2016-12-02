package com.edu.feicui.newsclient.Activity;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.edu.feicui.newsclient.R;

public class LogoActivity extends Activity{
	
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		
		AlphaAnimation animation = new AlphaAnimation(0.1f, 1f);
		animation.setDuration(2000);
    	animation.setFillAfter(true);
    	
    	animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {

				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {

				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(LogoActivity.this,MainActivity.class);
		    	startActivity(intent);
		    	overridePendingTransition(R.anim.right_in, R.anim.bottom_out);
				finish();
				
			}
		});
    	
    	imageView = (ImageView) findViewById(R.id.animation_logo);
    	imageView.startAnimation(animation);
	}
}
