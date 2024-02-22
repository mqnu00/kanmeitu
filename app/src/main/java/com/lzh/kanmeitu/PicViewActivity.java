package com.lzh.kanmeitu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicViewAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.util.ApiUtils;

import java.util.List;
import java.util.Map;

public class PicViewActivity extends AppCompatActivity {

    private Handler viewHandler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            picPackage.setPicUrls((List<String>) msg.obj);
//        设置adapter
            PicViewAdapter picViewAdapter = new PicViewAdapter(PicViewActivity.this, picPackage);
            lv_pic.setAdapter(picViewAdapter);
        }
    };

    private Handler processHandler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Map<String, Integer> res = (Map<String, Integer>) msg.obj;
            Integer psMax = res.get("max");
            Integer psNow = res.get("process");
            ps_view.setMax(psMax);
            ps_view.setProgress(psNow);
            tv_process.setText(String.format("%d/%d", psNow, psMax));
            if (psNow >= psMax) {

                if (ps_view.getVisibility() == View.GONE) {
                    ps_view.setVisibility(View.VISIBLE);
                } else {
                    ps_view.setVisibility(View.GONE);
                }
            }
        }
    };
    private PicPackage picPackage;
    private ListView lv_pic;
    private ProgressBar ps_view;
    private TextView tv_process;

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
//        进度条
        ps_view = findViewById(R.id.ps_view);
//        精确显示数据
        tv_process = findViewById(R.id.tv_process);

//        获取图片链接
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<String> picUrlList = ApiUtils.picUrlList(picPackage.getUrl());
                Message message = new Message();
                message.obj = picUrlList;
                viewHandler.sendMessage(message);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean downloadTag = true;
                while (downloadTag) {

                    try {
                        Thread.sleep(2000);
                        Map<String, Integer> res = ApiUtils.viewProcess();
                        if (res.get("max") <= res.get("process")) {

                            downloadTag = false;
                        }
                        Message message = new Message();
                        message.obj = res;
                        processHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();


    }
}