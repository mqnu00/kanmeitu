package com.lzh.kanmeitu.util;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.bean.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class ApiUtils {

    private static final String host = "192.168.10.233";

    private static final String port = "8000";

    private static final String apiUrl = "http://%s:%s/%s%s";

    private static final String searchPath = "api/kanmeitu/search";

    private static final String searchArgs = "?keyboard=%s&search_id=%s&page=%d";

    public static SearchResult PreviewUrlList (String keyboard, String search_id, int page) {

        String searchArgsF = String.format(searchArgs, keyboard, search_id, page);
        String searchUrl = String.format(apiUrl, host, port, searchPath, searchArgsF);
        String jsonStr = HttpUtils.getHttpResult(String.format(searchUrl, keyboard, search_id));
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<PicPackage> picPackageList = new ArrayList<>();
        for (Object o : jsonArray) {

            PicPackage picPackage = JSON.parseObject(o.toString(), PicPackage.class);
            picPackageList.add(picPackage);
        }
        SearchResult result = new SearchResult();
        result.setCount(jsonObject.getIntValue("count"));
        result.setTotalPage(jsonObject.getIntValue("totalPage"));
        result.setPicPackageList(picPackageList);
        Log.d("check", result.toString());
        return result;
    }

}
