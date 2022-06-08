package com.example.imapplication.model;

import android.content.Context;

import com.example.imapplication.model.bean.UserInfo;
import com.example.imapplication.model.dao.UserAccountDao;
import com.example.imapplication.model.db.DBManager;
import com.example.imapplication.model.db.EventListener;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//数据模型层全局类
public class Model {
    private Context mContext;
    private ExecutorService executors = Executors.newCachedThreadPool();

    //create a model
    private static Model model = new Model();
    private UserAccountDao userAccountDao;
    private DBManager dbManager;

    //私有化构造
    private Model(){

    }
    //获取单例对象
    public static Model getInstance(){
        return model;
    }

    //init
    public void init(Context context){

        mContext = context;

        //创建用户账号数据库操作了对象
        userAccountDao = new UserAccountDao(mContext);

        //开启全局监听
        EventListener eventListener = new EventListener(mContext);
    }

    //获取全局线程池对象
    public ExecutorService getGloabalThreadPool(){
        return executors;
    }

    //用户登录成功后的处理方法
    public void loginSuccess(UserInfo account) {
        // 校验
        if(account == null) {
            return;
        }
        if (dbManager != null){
            dbManager.close();

        }

        dbManager = new DBManager(mContext,account.getName());
    }

    //获取用户账号数据库的操作类对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }


    public DBManager getDbManager() {
        return dbManager;
    }

}
