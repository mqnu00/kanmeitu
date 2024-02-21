package com.lzh.kanmeitu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzh.kanmeitu.R;
import com.lzh.kanmeitu.bean.PicPackage;

public class picViewAdapter extends BaseAdapter {

    private Context context;

    private PicPackage picPackage;

    @Override
    public int getCount() {
        return picPackage.getPicUrls().size();
    }

    @Override
    public Object getItem(int i) {
        return picPackage.getPicUrls().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pic_view, null);
//        图片展示控件
        ImageView iv_view = inflate.findViewById(R.id.iv_view);
//        向视图导入数据
        String picUrl = picPackage.getPicUrls().get(i);
//        设置图片加载标志和提示
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .fallback(R.drawable.loading);
        Glide.with(inflate)
                .load(picPackage.getPreview())
                .apply(options)
                .into(iv_view);
        return inflate;
    }
}
