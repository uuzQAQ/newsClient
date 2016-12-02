package com.edu.feicui.newsclient.biz;

import android.content.Context;

import com.android.volley.Response;
import com.edu.feicui.newsclient.utils.CommonUtils;
import com.edu.feicui.newsclient.utils.Url;
import com.edu.feicui.newsclient.utils.VolleyUtils;

/**
 * Created by user on 2016/11/28.
 */

public class NewsManager {
    public static final int MODE_PULL_REFRESH = 1;//下拉
    public static final int MODE_LOAD_MODE = 2;//上拉

    public static void getNewsType(Context context, Response.Listener<String> listener,Response.ErrorListener errorListener){
        String imei = CommonUtils.getIMEI(context);//6.0要动态申请
        VolleyUtils volleyUtils = new VolleyUtils(context);
        volleyUtils.sendStringRequest(Url.GET_NEWS_TYPE + "?ver=0&imei=" + imei,listener,errorListener);
    }

    //获取新闻列表
    public static void getNewsList(Context context,int subid,int dir,int nid,Response.Listener<String> listener,Response.ErrorListener errorListener){
        String stamp = CommonUtils.getCurrentDate();
        VolleyUtils volleyUtils = new VolleyUtils(context);
        volleyUtils.sendStringRequest(Url.GET_NEWS + "?ver=0&subid=" + subid + "&dir=" + dir +
                "&nid=" + nid + "&stamp=" + stamp + "&cnt=20",listener,errorListener);
    }
}
