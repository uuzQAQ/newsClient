package com.edu.feicui.newsclient.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.edu.feicui.newsclient.entity.DaoMaster;
import com.edu.feicui.newsclient.entity.DaoSession;
import com.edu.feicui.newsclient.entity.News;
import com.edu.feicui.newsclient.entity.Newsgreen;
import com.edu.feicui.newsclient.entity.NewsgreenDao;
import com.edu.feicui.newsclient.entity.SubType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/11/28.
 */

public class NewsDBManager {
    private NewsDBHelper newsDBHelper;
    private Context context;

    public NewsDBManager(Context context) {
        this.newsDBHelper = new NewsDBHelper(context);
        this.context = context;
    }

    public List<SubType> getNewsSubType(){
        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from news_type order by _id asc",null);
        List<SubType> list = new ArrayList<>();

        while (cursor.moveToNext()){
            int subid = cursor.getInt(cursor.getColumnIndex("subid"));
            String subgroup = cursor.getString(cursor.getColumnIndex("subgroup"));
            SubType subType = new SubType(subid,subgroup);
            list.add(subType);
        }
        cursor.close();
        cursor = null;
        return list;
    }

//    public void saveNewsType(List<SubType> list){//保存新闻分类
//
//        if(list == null || list.size() == 0){
//            return ;
//        }
//        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
//        for(int i = 0;i < list.size();i++){
//            SubType subType = list.get(i);
//            Cursor cursor = db.rawQuery("select * from news_type where subid = ?", new String[]{subType.getSubId() + ""});
//            if(cursor.moveToFirst()){//第一个位置空就不操作了
//                cursor.close();
//                return;
//            }
//            cursor.close();
//
//
//            //储存
//            ContentValues values = new ContentValues();
//            values.put("subid",subType.getSubId());
//            values.put("subgroup",subType.getSubgroup());
//            db.insert("news_type",null,values);
//        }
//        db.close();
//    }

    public void saveNewsType(List<SubType> list){
        if(list == null || list.size() == 0){
            return;
        }
        System.out.println("*************save****************" + list.size());
        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
        for(int i = 0;i < list.size();i++){
            SubType subType = list.get(i);
//            Cursor cursor = db.rawQuery("select * from news_type where subid = ?", new String[]{subType.getSubId() + ""});
//            if(cursor.moveToFirst()){
//                cursor.close();
//                continue;
//            }
//            cursor.close();
            // TODO: 2016/11/30
            //对该数据进行插入操作
            ContentValues values = new ContentValues();
            values.put("subid", subType.getSubid());
            values.put("subgroup", subType.getSubgroup());
            db.insert("news_type", null, values);
            System.out.println("**********插入第" + (i+1) + "条数据");
        }
        db.close();
    }

    public boolean isCollectNews(News news){//判断是否收藏
        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from lovenews where nid = ?",new String[]{String.valueOf(news.getNid()) + ""});
        boolean isExists = false;
        if(cursor.moveToFirst()){
            isExists = true;
        }

        cursor.close();
        return isExists;
    }

    public void save(News news){//保存收藏的新闻
        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nid",news.getNid());
        values.put("stamp",news.getStamp());
        values.put("icon",news.getIcon());
        values.put("title",news.getTitle());
        values.put("summary",news.getSummary());
        values.put("link",news.getLink());
        db.insert("lovenews",null,values);
    }


//    public boolean isCollectNews(News news){
////        SQLiteDatabase db = newsDBHelper.getReadableDatabase();
////        Cursor cursor = db.rawQuery("select * from lovenews where nid = ?", new String[]{String.valueOf(news.getNid())});
////        boolean isExists = false;
////        if(cursor.moveToFirst()){
////            isExists = true;
////        }
////
////        cursor.close();;
////        db.close();
////        return isExists;
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"news.db");//实例化数据库并制定数据库名
//        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());//database
//        DaoSession daoSession = daoMaster.newSession();//DapSession
//        NewsgreenDao newsgreenDao = daoSession.getNewsgreenDao();//用于数据访问的接口
//        Newsgreen newsgreen = newsgreenDao.queryBuilder().where(NewsgreenDao.Properties.Nid.eq(news.getNid())).build().unique();
//        boolean isExists = false;
//        if(TextUtils.isEmpty(newsgreen.getTitle())){
//            isExists = true;
//        }
//        return isExists;
//    }
//
//    //保存新闻数据
//    public void save(News news){
////        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
////        ContentValues values = new ContentValues();
////        values.put("nid", news.getNid());
////        values.put("stamp", news.getStamp());
////        values.put("icon", news.getIcon());
////        values.put("title", news.getTitle());
////        values.put("summary", news.getSummary());
////        values.put("link", news.getLink());
////        db.insert("lovenews", null, values);
////        db.close();
//
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"news.db");//实例化数据库并制定数据库名
//        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());//database
//        DaoSession daoSession = daoMaster.newSession();//DapSession
//        NewsgreenDao newsgreenDao = daoSession.getNewsgreenDao();//用于数据访问的接口
//        Newsgreen newsgreen = new Newsgreen();
//        newsgreen.setNid(news.getNid());
//        newsgreen.setStamp(news.getStamp());
//        newsgreen.setIcon(news.getIcon());
//        newsgreen.setTitle(news.getTitle());
//        newsgreen.setSummary(news.getSummary());
//        newsgreen.setLink(news.getLink());
//        newsgreenDao.insert(newsgreen);
//    }

}
