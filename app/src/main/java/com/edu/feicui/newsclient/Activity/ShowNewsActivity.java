package com.edu.feicui.newsclient.Activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.base.BaseActivity;
import com.edu.feicui.newsclient.biz.CommentManager;
import com.edu.feicui.newsclient.biz.NewsManager;
import com.edu.feicui.newsclient.db.NewsDBManager;
import com.edu.feicui.newsclient.entity.BaseEntity;
import com.edu.feicui.newsclient.entity.News;
import com.edu.feicui.newsclient.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.Since;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/12/1.
 */

public class ShowNewsActivity extends BaseActivity {

    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.iv_menu) ImageView ivMenu;
    @BindView(R.id.tv_comment_num) TextView tvCommentNum;
    @BindView(R.id.pb_show_news) ProgressBar pb;
    @BindView(R.id.wv) WebView wv;
    private News news;
    private PopupWindow popupWindow;
    private NewsDBManager newsDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(CommonUtils.isNetConnect(this)){
            setContentView(R.layout.show_news);
            ButterKnife.bind(this);
            news = (News) getIntent().getSerializableExtra("news");
            newsDBManager = new NewsDBManager(this);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wv.getSettings().setAllowContentAccess(true);
            wv.setWebChromeClient(webChromeClient);
            wv.setWebViewClient(new WebViewClient(){//防止链接调整到浏览器
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(news.getLink());
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });

            wv.loadUrl(news.getLink());
            CommentManager.getCommentNum(this,news.getNid(),listener,errorListener);
            initPopupWindow();

        }else{
            setContentView(R.layout.show_news_error);
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            pb.setProgress(newProgress);
            if(pb.getProgress() >= 100){
                pb.setVisibility(View.GONE);
            }
        }
    };

    public void initPopupWindow(){
        View view = getLayoutInflater().inflate(R.layout.popup_window,null);
        TextView tvCollectNews = (TextView) view.findViewById(R.id.tv_add);
        tvCollectNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                if(!newsDBManager.isCollectNews(news)){
                    newsDBManager.save(news);
                    CommonUtils.showShortToast(ShowNewsActivity.this,"收藏成功");
                }else{
                    CommonUtils.showShortToast(ShowNewsActivity.this,"已收藏，可以从收藏选项中查看");
                }
            }
        });

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//点击外面不消失和返回键无效时，设置有Drawable可以解决

    }

    @OnClick(R.id.iv_menu) public void showMenu(){
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }else if(popupWindow != null){
            popupWindow.showAsDropDown(ivMenu,5,15);
        }
    }

    @OnClick(R.id.iv_back) public void exit(){
        finish();
    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Gson gson = new Gson();
            BaseEntity<Integer> baseEntity = gson.fromJson(s,new TypeToken<BaseEntity<Integer>>(){}.getType());
            int commentNum = baseEntity.getData();
            tvCommentNum.setText(commentNum + "跟帖");
        }
    };

    private  Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            CommonUtils.showShortToast(ShowNewsActivity.this,"加载失败");
        }
    };

    @OnClick(R.id.tv_comment_num) public void goToComment(){
        Bundle bundle = new Bundle();
        bundle.putInt("nid",news.getNid());
        startActivity(CommentActivity.class,bundle);
    }
}
