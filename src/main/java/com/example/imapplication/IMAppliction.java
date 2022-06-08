package com.example.imapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.imapplication.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseIM;

public class IMAppliction extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        //init easeim
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);//设置需要同意后才能接受邀请
        options.setAutoAcceptGroupInvitation(false);//设置需要同意后才能接受群邀请

        EaseIM.getInstance().init(this,options);

        if (EaseIM.getInstance().init(this, options)) {
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(true);

        }

        //super.attachBaseContext(this);
        Model.getInstance().init(this);

        mContext = this;
    }
    public static Context getGlobalApplication(){
        return mContext;
    }
}