package com.example.imapplication.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.imapplication.ChatActivity;
import com.example.imapplication.R;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.modules.conversation.EaseConversationListFragment;
import com.hyphenate.easeui.modules.conversation.model.EaseConversationInfo;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseTitleBar;

//会话列表页面
public class ChatFragment extends EaseConversationListFragment {

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        //添加搜索布局和标题栏
        addSearchView();

    }

    private void addSearchView() {
        //添加搜索会话布局
        View searchView = LayoutInflater.from(mContext).inflate(R.layout.contract_search, null);
        llRoot.addView(searchView,0);

        //获取控件
        EaseTitleBar titleBar=findViewById(R.id.fragment_title_bar);

        //设置标题
        titleBar.setTitle("会话列表");

        //添加右侧图标
        titleBar.setRightImageResource(R.drawable.em_contact_menu_add);
    }

    //设置条目的点击事件
    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);

        Intent intent = new Intent(getActivity(), ChatActivity.class);

        //获取到该条目的所有数据
        EaseConversationInfo conversationInfo = conversationListLayout.getItem(position);
        //拿到该条目的会话信息
        EMConversation conversation = (EMConversation) conversationInfo.getInfo();

        //传递参数   会话信息id =hxid
        intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID, conversation.conversationId());

        //会话类型,是否是群聊
        if (conversationInfo.isGroup()) {
            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        }

        startActivity(intent);
    }

    //刷新消息
}
