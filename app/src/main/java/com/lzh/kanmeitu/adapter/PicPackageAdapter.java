package com.lzh.kanmeitu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzh.kanmeitu.R;
import com.lzh.kanmeitu.bean.PicPackage;

import java.util.List;

public class PicPackageAdapter extends BaseAdapter {

    private Context context;
    private List<PicPackage> picPackageList;

    public PicPackageAdapter(Context context, List<PicPackage> picPackageList) {
        this.context = context;
        this.picPackageList = picPackageList;
    }

    //    获取数据个数
    @Override
    public int getCount() {
        return picPackageList.size();
    }

    //    获取第几个数据
    @Override
    public Object getItem(int i) {
        return picPackageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        将布局文件转换成视图
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_simple, null);
        ImageView iv_preview = inflate.findViewById(R.id.iv_preview);
        TextView tv_name = inflate.findViewById(R.id.tv_name);
        TextView tv_url = inflate.findViewById(R.id.tv_url);
//        向视图导入数据
        PicPackage picPackage = picPackageList.get(i);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .fallback(R.drawable.loading);
        Glide.with(inflate)
                .load(picPackage.getPreview())
                .apply(options)
                .into(iv_preview);
        Log.d("check", picPackage.getPreview());
        tv_name.setText(picPackage.getName());
        tv_url.setText(picPackage.getUrl());
        return inflate;
    }
}
