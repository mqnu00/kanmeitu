package com.lzh.kanmeitu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.lzh.kanmeitu.adapter.PicPackageAdapter;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.util.ApiUtils;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private List<PicPackage> picPackageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        picPackageList = ApiUtils.PreviewUrlList();
        PicPackageAdapter picPackageAdapter = new PicPackageAdapter(this, picPackageList);
        ListView lv_pic = findViewById(R.id.lv_pic);
        lv_pic.setAdapter(picPackageAdapter);

        lv_pic.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, picPackageList.get(i).getName(), Toast.LENGTH_SHORT).show();
    }
}