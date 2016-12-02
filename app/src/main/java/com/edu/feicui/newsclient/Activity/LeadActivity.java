package com.edu.feicui.newsclient.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.adapter.LeadViewpageAdapter;


public class LeadActivity extends Activity {

	private List<View> list = new ArrayList<View>();
	private ViewPager vp;
	private ImageView[] imageView = new ImageView[4];

	private int[] imageArray = {
			R.drawable.welcome,
			R.drawable.wy,
			R.drawable.bd,
			R.drawable.small
	};

	
	private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead);
        SharedPreferences sp = this.getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        boolean isFirstRun = sp.getBoolean("isFirstRun", true);
        if(isFirstRun){
        	saveData();
        	setContentView(R.layout.lead);
        	initView();
        	initImagePager();
        	vp.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int arg0) {
					for(int i = 0;i < imageArray.length;i++){
						imageView[i].setAlpha(0.5f);
					}
					imageView[arg0].setAlpha(1f);
					
					if(arg0 == 3){
						Intent intent = new Intent(LeadActivity.this,LogoActivity.class);
			        	startActivity(intent);
			        	overridePendingTransition(R.anim.right_in, R.anim.bottom_out);
			        	finish();
					}
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					
					
				}
			});
        	
        	
        }else{
        	Intent intent = new Intent(LeadActivity.this,LogoActivity.class);
        	startActivity(intent);
        	finish();
        }
    }

    public void initView(){
    	vp = (ViewPager) findViewById(R.id.viewPager);
    	imageView[0] = (ImageView) findViewById(R.id.imageview1);
    	imageView[1] = (ImageView) findViewById(R.id.imageview2);
    	imageView[2] = (ImageView) findViewById(R.id.imageview3);
    	imageView[3] = (ImageView) findViewById(R.id.imageview4);

    }
    
    public void saveData(){
    	SharedPreferences sp = this.getSharedPreferences("lead_config", Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sp.edit();
    	
    	editor.putBoolean("isFirstRun", false);
		editor.commit();
    }
    
    public void initImagePager(){
    	for(int i = 0;i < imageArray.length;i++){
    		ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.lead_item, null);
    		imageView.setImageResource(imageArray[i]);
    		
    		list.add(imageView);
    	}
    	
    	LeadViewpageAdapter adapter = new LeadViewpageAdapter(LeadActivity.this, list);
    	vp.setAdapter(adapter);
    }
   
    
}
