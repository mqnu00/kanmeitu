package com.lzh.kanmeitu.util;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.kanmeitu.bean.PicPackage;
import com.lzh.kanmeitu.bean.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {

    private static final String host = "192.168.232.223";

    private static final String port = "8000";

    private static final String apiUrl = "http://%s:%s/%s%s";

    private static final String searchPath = "api/kanmeitu/search";

    private static final  String viewPath = "api/kanmeitu/view";

    private static final String searchArgs = "?keyboard=%s&search_id=%s&page=%d";

    private static final String viewArgs = "?url=%s";

    public static SearchResult PreviewUrlList (String keyboard, String search_id, int page) {

        String searchArgsF = String.format(searchArgs, keyboard, search_id, page);
        String searchUrl = String.format(apiUrl, host, port, searchPath, searchArgsF);
        String jsonStr = HttpUtils.getHttpResult(searchUrl);
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
        return result;
    }

    public static Map<String, Object> picUrlList (String picPackageUrl) {

        String viewF= String.format(viewArgs, picPackageUrl);
        String viewUrl = String.format(apiUrl, host, port, viewPath, viewF);
        String jsonStr = HttpUtils.getHttpResult(viewUrl);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String msg = jsonObject.getString("msg");
        Boolean status = jsonObject.getBoolean("status");
        Map<String, Object> res = new HashMap<>();
        res.put("msg", msg);
        res.put("status", status);
        if (status) {

            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<String> data = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {

                JSONArray now = JSONArray.parseArray(jsonArray.getString(i));
                data.add(now.getString(2));
            }
            res.put("data", data);
        }
        return res;
    }
}
