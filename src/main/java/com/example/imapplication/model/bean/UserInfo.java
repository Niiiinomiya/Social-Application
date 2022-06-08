package com.example.imapplication.model.bean;

//用户账号信息的bean类
public class UserInfo {
    private String name;//用户名称
    private String hxid;//环信id
    private String nickname;
    private String photo;//头像

    public UserInfo(String name) {
        this.name = name;
        this.hxid = name;
        this.nickname = name;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHxid() {
        return hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
