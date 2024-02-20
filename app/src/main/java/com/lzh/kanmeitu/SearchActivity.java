package com.lzh.kanmeitu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicPackageAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.util.ApiUtils;
import com.lzh.kanmeitu.util.ToastUtils;

import java.util.List;
import java.util.concurrent.*;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    private List<PicPackage> picPackageList;
    private EditText et_search;
    private ListView lv_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        搜索框
        et_search = findViewById(R.id.et_search);
//        搜索按钮
        Button bt_search = findViewById(R.id.bt_search);
//        列表
        lv_pic = findViewById(R.id.lv_pic);

        lv_pic.setOnItemClickListener(this);
        bt_search.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        ToastUtils.simpleToast(this, picPackageList.get(i).getName());
    }

//    点击搜索
    @Override
    public void onClick(View view) {


        // 创建一个线程池，可以根据需要调整大小
        ExecutorService executorService = Executors.newFixedThreadPool(4);
// 提交一个Callable任务，返回一个Future对象
        Future<List<PicPackage>> future = executorService.submit(new Callable<List<PicPackage>>() {
            @Override
            public List<PicPackage> call() throws Exception {
                // 调用PreviewUrlList函数，获取结果列表
                // 返回结果给Future对象
                return ApiUtils.PreviewUrlList(et_search.getText().toString(), "");
            }
        });
// 在主线程中通过Future对象获取结果
        try {
            // 获取结果，如果任务还没完成，会阻塞等待
            picPackageList  = future.get();
            PicPackageAdapter picPackageAdapter = new PicPackageAdapter(this, picPackageList);
            lv_pic.setAdapter(picPackageAdapter);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
// 关闭线程池
        executorService.shutdown();
    }
}