package com.lzh.kanmeitu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.kanmeitu.bean.PicPackage;

import java.util.ArrayList;
import java.util.List;

public class ApiUtils {

    private static final String searchUrl = "http://192.168.10.233:8000/index";

    public static List<PicPackage> PreviewUrlList () {

        String jsonStr = HttpUtils.getHttpResult(searchUrl);

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<PicPackage> res = new ArrayList<>();
        for (Object o : jsonArray) {

            PicPackage picPackage = JSON.parseObject(o.toString(), PicPackage.class);
            res.add(picPackage);
        }
        return res;
    }
}
