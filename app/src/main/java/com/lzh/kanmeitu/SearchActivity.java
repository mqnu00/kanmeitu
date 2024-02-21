package com.lzh.kanmeitu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicPackageAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.bean.SearchResult;
import com.lzh.kanmeitu.util.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    private SearchResult searchResult;

    private List<String> buttonList = new ArrayList<>();
    private EditText et_search;
    private ListView lv_search_result;
    private TextView tv_total_page;
    private TextView tv_count;
    private EditText et_jump_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        搜索框
        et_search = findViewById(R.id.et_search);
//        搜索按钮
        Button bt_search = findViewById(R.id.bt_search);
//        列表
        lv_search_result = findViewById(R.id.lv_search_result);
//        总页数
        tv_total_page = findViewById(R.id.tv_total_page);
//        图包总数
        tv_count = findViewById(R.id.tv_count);
//        跳转页数
        et_jump_page = findViewById(R.id.et_jump_page);

        lv_search_result.setOnItemClickListener(this);
        bt_search.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this, PicViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("picPackage", searchResult.getPicPackageList().get(i));
        intent.putExtras(bundle);
        startActivity(intent);
    }

//    点击搜索
    @Override
    public void onClick(View view) {

        // 创建一个线程池，可以根据需要调整大小
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 提交一个Callable任务，返回一个Future对象
        Future<SearchResult> future = executorService.submit(new Callable<SearchResult>() {
            @Override
            public SearchResult call() throws Exception {
                // 调用PreviewUrlList函数，获取结果列表
                // 返回结果给Future对象
                int page = 0;
                if (!et_jump_page.getText().toString().equals("")) page = Integer.parseInt(et_jump_page.getText().toString());
                return ApiUtils.PreviewUrlList(et_search.getText().toString(), "", page - 1);
            }
        });
// 在主线程中通过Future对象获取结果
        try {
            // 获取结果，如果任务还没完成，会阻塞等待
            searchResult  = future.get();
            tv_count.setText(String.format("%d组图", searchResult.getCount()));
            tv_total_page.setText(String.format("%d页", searchResult.getTotalPage()));
            List<PicPackage> picPackageList = searchResult.getPicPackageList();
            PicPackageAdapter picPackageAdapter = new PicPackageAdapter(this, picPackageList);
            lv_search_result.setAdapter(picPackageAdapter);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
// 关闭线程池
        executorService.shutdown();
    }
}