package com.edu.feicui.newsclient.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class LeadViewpageAdapter extends PagerAdapter{

	private List<View> list;
	
	public LeadViewpageAdapter(Context context,List<View> list){
		this.list = list;
	}
	
	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = list.get(position);
		container.removeView(view);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = list.get(position);
		container.addView(view);
		return view;
	}

}
