package com.lzh.kanmeitu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicViewAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.util.ApiUtils;

import java.util.List;
import java.util.concurrent.*;

public class PicViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_view);

//        读取search界面传递过来的信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PicPackage picPackage = (PicPackage) bundle.getSerializable("picPackage");

//        显示图片的列表
        ListView lv_pic = findViewById(R.id.lv_pic);

//        获取图片链接
        // 创建一个线程池，可以根据需要调整大小
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 提交一个Callable任务，返回一个Future对象
        Future<List<String>> future = executorService.submit(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                // 调用PreviewUrlList函数，获取结果列表
                // 返回结果给Future对象
                return ApiUtils.picUrlList(picPackage.getUrl());
            }
        });
// 在主线程中通过Future对象获取结果
        try {
            // 获取结果，如果任务还没完成，会阻塞等待
            List<String> res = future.get();
            picPackage.setPicUrls(res);
//        设置adapter
            PicViewAdapter picViewAdapter = new PicViewAdapter(this, picPackage);
            lv_pic.setAdapter(picViewAdapter);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
// 关闭线程池
        executorService.shutdown();
    }
}