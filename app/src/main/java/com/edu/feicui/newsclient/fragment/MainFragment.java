package com.edu.feicui.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.edu.feicui.newsclient.Activity.MainActivity;
import com.edu.feicui.newsclient.Activity.ShowNewsActivity;
import com.edu.feicui.newsclient.R;
import com.edu.feicui.newsclient.View.HorizontalListView;
import com.edu.feicui.newsclient.XListView.XListView;
import com.edu.feicui.newsclient.adapter.NewsAdapter;
import com.edu.feicui.newsclient.adapter.NewsTypeAdapter;
import com.edu.feicui.newsclient.base.BaseActivity;
import com.edu.feicui.newsclient.biz.NewsManager;
import com.edu.feicui.newsclient.db.NewsDBManager;
import com.edu.feicui.newsclient.entity.News;
import com.edu.feicui.newsclient.entity.SubType;
import com.edu.feicui.newsclient.parser.NewsParser;
import com.edu.feicui.newsclient.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainFragment extends Fragment {

    @BindView(R.id.hlv)
    HorizontalListView hlv;
    @BindView(R.id.iv_typo_more)
    ImageView ivType;
    @BindView(R.id.xlv)
    XListView xlv;



    private NewsDBManager dbManager;
    private NewsTypeAdapter adapter;
    private NewsAdapter adapterNews;

    private int subid = 2;//分类编号
    private int refreshMode = 1;//属性模式

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);

        adapter = new NewsTypeAdapter(getActivity());
        adapterNews = new NewsAdapter(getActivity());
        dbManager = new NewsDBManager(getActivity());
        hlv.setAdapter(adapter);
        hlv.setOnItemClickListener(typeItemClickListener);
        loadNewsType();

        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(listViewListener);
        xlv.setAdapter(adapterNews);
        xlv.setOnItemClickListener(newsItemClickListener);

        refreshMode = NewsManager.MODE_PULL_REFRESH;
        loadNextNews(true);

        //进度对话框
        ((BaseActivity)getActivity()).showDialog(null,false);
        return view;
    }

    private XListView.IXListViewListener listViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {//下拉刷新
            refreshMode = NewsManager.MODE_PULL_REFRESH;
            loadNextNews(false);
        }

        @Override
        public void onLoadMore() {//上拉加载
            refreshMode = NewsManager.MODE_LOAD_MODE;
            loadPrevNews();
        }
    };

    private void loadNextNews(boolean isNewType){
        int nid = 1;
        if(!isNewType){//第一次加载 nid = 1，如果不是第一次，获取第一条新闻编号
            if(adapterNews.getData().size() > 0){
                nid = adapterNews.getData().get(0).getNid();
            }
        }

        if(CommonUtils.isNetConnect(getActivity())){
            NewsManager.getNewsList(getActivity(),subid,refreshMode,nid,newsListener,errorListener);
        }else{
            //从缓存获取数据
        }
    }

    private void loadPrevNews(){
        //没有数据不需要上拉
        if(adapterNews.getData().size() == 0){
            return;
        }

        int lastIndex = adapterNews.getData().size() - 1;
        int nid = adapterNews.getData().get(lastIndex).getNid();
        if(CommonUtils.isNetConnect(getActivity())){
            NewsManager.getNewsList(getActivity(),subid,refreshMode,nid,newsListener,errorListener);
        }else{
            //从缓存获取数据
        }
    }

    private void loadNewsType(){//加载新闻分类
        //判断数据库是否有缓存数据，有，使用缓存数据，没，检测是否有网络，有则去服务器加载
        if(dbManager.getNewsSubType().size() == 0){
            if(CommonUtils.isNetConnect(getActivity())){
                NewsManager.getNewsType(getActivity(),listener,errorListener);
            }
        }else{
            List<SubType> list = dbManager.getNewsSubType();
            adapter.appendDataToAdapter(list,true);
            adapter.notifyDataSetChanged();
        }
    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            System.out.println(s);
            List<SubType> list = NewsParser.parseNewsType(s);
            adapter.appendDataToAdapter(list,true);
            adapter.notifyDataSetChanged();
            //缓存新闻分类
            dbManager.saveNewsType(list);
        }
    };


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            xlv.stopLoadMore();
            xlv.stopRefresh();
            Toast.makeText(getActivity(),"加载数据时报",Toast.LENGTH_LONG).show();//取消进度对话框
            ((BaseActivity)getActivity()).cancelDialog();
        }
    };

    private Response.Listener<String> newsListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            List<News> list = NewsParser.parseNews(s);
            boolean isClear = refreshMode == NewsManager.MODE_PULL_REFRESH ? true : false;
            adapterNews.appendDataToAdapter(list,isClear);
            adapterNews.notifyDataSetChanged();
            xlv.stopLoadMore();
            xlv.stopRefresh();
            xlv.setRefreshTime(CommonUtils.getCurrentDate());

            ((BaseActivity)getActivity()).cancelDialog();
        }
    };

    private AdapterView.OnItemClickListener typeItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            System.out.println("当前项:" + i);
            SubType subType = (SubType) adapter.getItem(i);
            subid = subType.getSubid();
            System.out.println("当前subId:" + subid);
            adapter.setCurrentPosition(i);
            adapter.notifyDataSetChanged();
            ((BaseActivity)getActivity()).showDialog(null,false);
            refreshMode = NewsManager.MODE_PULL_REFRESH;
            loadNextNews(true);
        }
    };

    private AdapterView.OnItemClickListener newsItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            News news = (News) adapterView.getItemAtPosition(i);
            Bundle bundle = new Bundle();
            bundle.putSerializable("news",news);
            BaseActivity activity = (BaseActivity) getActivity();
            activity.startActivity(ShowNewsActivity.class,bundle);

        }
    };
}
