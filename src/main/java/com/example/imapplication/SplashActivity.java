package com.example.imapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.imapplication.model.Model;
import com.example.imapplication.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //如果当前activity已经退出，就不处理handler
            if (isFinishing()) {
                return;
            }

            //主界面还是登陆页面
            toMainOrLogin();
        }
    };

    private void toMainOrLogin() {
        Model.getInstance().getGloabalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if (EMClient.getInstance().isLoggedInBefore()) {//have login
                    //collect client's information
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());

                    if (account == null) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Model.getInstance().loginSuccess(account);
                        Intent intent1 = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }
                } else { //do not login
                    Intent intent1 = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent1);
                }

                //finish
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        //设置顶部状态栏为透明
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);


        //2 seconds delay
        handler.sendMessageDelayed(Message.obtain(), 2000);
    }

    protected void onDestroy() {
        super.onDestroy();
        //delete message
        handler.removeCallbacksAndMessages(null);
    }
}
