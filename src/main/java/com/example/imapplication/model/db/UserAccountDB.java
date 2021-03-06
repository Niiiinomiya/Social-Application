package com.example.imapplication.model.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imapplication.model.dao.UserAccountTable;

import java.sql.SQLClientInfoException;

public class UserAccountDB extends SQLiteOpenHelper {
    //构造
    public UserAccountDB(@Nullable Context context) {
        super(context, "account.db", null, 1);
    }

    //数据库创建时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表的语句
        db.execSQL(UserAccountTable.CREATE_TAB);
    }

    //数据库更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
