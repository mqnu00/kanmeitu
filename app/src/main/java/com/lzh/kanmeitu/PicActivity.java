package com.lzh.kanmeitu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        ImageView iv_pic = findViewById(R.id.iv_pic);

        Intent intent = getIntent();
        Bitmap bitmap = data.bitmap;
        iv_pic.setImageBitmap(bitmap);
    }
}