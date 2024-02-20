package com.lzh.kanmeitu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzh.kanmeitu.bean.PicPackage;

import java.util.ArrayList;
import java.util.List;

public class ApiUtils {

    public static List<PicPackage> PreviewUrlList () {

        String jsonStr = "{\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"name\": \"Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - \\u9ed1\\u592a\\u5b50\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://oss-img.ojbkcdn.com/tutuji/20230902/shiunwsr1ml.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/59489.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - \\u5965\\u53e4\\u65af\\u7279\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://oss-img.ojbkcdn.com/tutuji/20230714/bejwezb5vpn.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/58386.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[\\u841d\\u8389COS] \\u5357\\u5bab - \\u9ad8\\u96c4\\u7231\\u5b95\\u65d7\\u888d \\u5357\\u5bab\\u7259\\u59b9\\u53cc\\u4eba\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/k0dir1f0g11.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/15874.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[\\u841d\\u8389COS] \\u5357\\u5bab - \\u5c11\\u5973\\u524d\\u7ebfAN-94\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/gbmu3uqclku.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/15862.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[\\u841d\\u8389COS] \\u5357\\u5bab - \\u7c89\\u7ea2\\u5154\\u5973\\u90ce\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/yopexd25uiw.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/15787.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[\\u841d\\u8389COS] \\u5357\\u5bab - \\u6027\\u611f\\u9b54\\u5973\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/mq0khipp21v.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/15442.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[COS\\u798f\\u5229] Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - \\u541b\\u4e3b\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/emfnj52b2wb.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/10325.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[COS\\u798f\\u5229] Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - K2\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/jzlnvwjhrsz.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/10314.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[COS\\u798f\\u5229] Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - \\u95ea\\u7075\\u6cf3\\u88c5\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/uuzz2d2n1rp.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/10312.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[COS\\u798f\\u5229] Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - G36C\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/orzcgf402si.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/10305.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[COS\\u798f\\u5229] Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - \\u742a\\u4e9a\\u5a1c\\u6cf3\\u88c5\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/mgnlqsc3ntj.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/10187.html\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"[COS\\u798f\\u5229] Coser\\u5c0f\\u59d0\\u59d0\\u5357\\u5bab - \\u8389\\u8389\\u4e1d\",\n" +
                "            \"preview\": \"https://thumbnail.ojbkcdn.com/timthumb.php?src=https://mm.ojbkcdn.com/a/202112/vokhoesstfl.jpg&w=215&h=300&zc=1\",\n" +
                "            \"url\": \"https://www.sfjpg.net/mm/10143.html\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"searchid\": \"2330\"\n" +
                "}";

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
