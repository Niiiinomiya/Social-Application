package com.example.imapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.imapplication.controller.fragment.ChatFragment;
import com.example.imapplication.controller.fragment.ContactListFragment;
import com.example.imapplication.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;
    ChatFragment chatFragment;
    ContactListFragment contactFragment;
    SettingFragment settingFragment;
    private RadioButton rb_main_chat;
    private RadioButton rb_main_contact;
    private RadioButton rb_main_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        initData();

        initListener();
    }

    private void initListener() {

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Fragment fragment = null;

                switch (checkedId){
                    case R.id.rb_main_chat:
                        fragment = chatFragment;
                        break;
                    case R.id.rb_main_contact:
                        fragment = contactFragment;

                        break;
                    case R.id.rb_main_setting:
                        fragment = settingFragment;
                        break;
                }

                //实现fragment切换
                switchFragment(fragment);
            }
        });

        //默认选择会话列表页面
        rg_main.check(R.id.rb_main_chat);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }

    private void initData() {

        //创建三个fragment对象
        chatFragment = new ChatFragment();

        contactFragment = new ContactListFragment();

        settingFragment = new SettingFragment();
    }

    private void init() {
        rg_main = findViewById(R.id.rg_main);
        rb_main_contact=findViewById(R.id.rb_main_contact);
        rb_main_setting=findViewById(R.id.rb_main_setting);
        rb_main_chat=findViewById(R.id.rb_main_chat);
    }
}