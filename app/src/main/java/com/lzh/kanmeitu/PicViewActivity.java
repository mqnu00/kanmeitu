package com.lzh.kanmeitu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicViewAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.util.ApiUtils;

import java.util.List;

public class PicViewActivity extends AppCompatActivity {

    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            picPackage.setPicUrls((List<String>) msg.obj);
//        设置adapter
            PicViewAdapter picViewAdapter = new PicViewAdapter(PicViewActivity.this, picPackage);
            lv_pic.setAdapter(picViewAdapter);
        }
    };
    private PicPackage picPackage;
    private ListView lv_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_view);

//        读取search界面传递过来的信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        picPackage = (PicPackage) bundle.getSerializable("picPackage");

//        显示图片的列表
        lv_pic = findViewById(R.id.lv_pic);

//        获取图片链接
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<String> picUrlList = ApiUtils.picUrlList(picPackage.getUrl());
                Message message = new Message();
                message.obj = picUrlList;
                handler.sendMessage(message);
            }
        }).start();
    }
}