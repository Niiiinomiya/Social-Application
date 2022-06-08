package com.example.imapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imapplication.model.Model;
import com.example.imapplication.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends Activity {

    private EditText userETLogin, passETLogin;
    private Button LoginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //控件初始化
        initView();

        //initListener
        initListener();
    }

    private void initListener() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        final String loginName = userETLogin.getText().toString();
        final String loginPwd = passETLogin.getText().toString();

        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)){
            Toast.makeText(LoginActivity.this,"Please fill all fields!",Toast.LENGTH_SHORT).show();
            return;
        }

        Model.getInstance().getGloabalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(loginName, loginPwd, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Model.getInstance().loginSuccess(new UserInfo(loginName));

                        //保存用户账号信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginName));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                                //跳转到主页面
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                                startActivity(intent);

                                finish();
                            }
                        });

                    }

                    @Override
                    public void onError(int code, String error) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"Login failed" + error,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });
    }

    private void regist() {
        String registName = userETLogin.getText().toString();
        String registPwd = passETLogin.getText().toString();

        if (TextUtils.isEmpty(registName) || TextUtils.isEmpty(registPwd)){
            Toast.makeText(LoginActivity.this,"Please fill all fields!",Toast.LENGTH_SHORT).show();
            return;
        }
        Model.getInstance().getGloabalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //register an account
                    EMClient.getInstance().createAccount(registName,registPwd);

                    //update
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"Register successful",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"Register failed!"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        userETLogin = findViewById(R.id.editText);
        passETLogin = findViewById(R.id.editTextTextPassword);
        LoginBtn = findViewById(R.id.buttonLogin);
        registerBtn = findViewById(R.id.registerBtn);
    }
}