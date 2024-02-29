package com.lzh.kanmeitu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicViewAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.util.ApiUtils;
import com.lzh.kanmeitu.util.ToastUtils;

import java.util.List;
import java.util.Map;

public class PicViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Handler viewHandler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                picPackage.setPicUrls((List<String>) msg.obj);
                //        设置adapter
                PicViewAdapter picViewAdapter = new PicViewAdapter(PicViewActivity.this, picPackage);
                lv_pic.setAdapter(picViewAdapter);
            } else {
                ToastUtils.simpleToast(PicViewActivity.this, (String) msg.obj);
            }
        }
    };

    private PicPackage picPackage;
    private ListView lv_pic;
    private ProgressBar ps_view;
    private TextView tv_process;
    private Thread viewThread;
    private Thread processThread;

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
        lv_pic.setOnItemClickListener(this);
//        进度条
        ps_view = findViewById(R.id.ps_view);
//        精确显示数据
        tv_process = findViewById(R.id.tv_process);

//        获取图片链接
        viewThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> res = ApiUtils.picUrlList(picPackage.getUrl());
                Message message = new Message();
                if ((Boolean) res.get("status")) {
                    message.what = 1;
                    message.obj = res.get("data");
                    viewHandler.sendMessage(message);
                } else {
                    message.what = 0;
                    message.obj = res.get("msg");
                    viewHandler.sendMessage(message);
                }

            }
        });
        viewThread.start();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("check", "?????1");
        if (viewThread != null) {
            Log.d("check", "?????2");
            viewThread.interrupt();
        }
        if (processThread != null) {
            Log.d("check", "?????3");
            processThread.interrupt();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ImageView iv_view = view.findViewById(R.id.iv_view);
        Bitmap bitmap = ((BitmapDrawable) iv_view.getDrawable()).getBitmap();
        data.bitmap = bitmap;
        Intent intent = new Intent(this, PicActivity.class);
        startActivity(intent);
    }
}