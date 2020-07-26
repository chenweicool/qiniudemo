package com.chen.example;

import com.chen.Utils.UserConstant;
import com.google.gson.Gson;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Chen
 * @Date 2020/7/26 16:29
 * 1.异步抓取的demo的实现
 * 主要是他的accesstoken的生成一定要懂，一定要会。
 * 测试通过
 **/
public class FetchDemo {
    public static void main(String[] args) throws Exception {

        //要上传的空间
        String bucketname = "chenweitest";
        Auth auth = Auth.create(UserConstant.ACCESS_KEy, UserConstant.SECRET_KEY);

        // 构造post请求body
        Gson gson = new Gson();
        Map<String, String> m = new HashMap();
        m.put("url", "http://cww.huangbowei.com/chenweiwei.jpg");
        m.put("bucket", bucketname);
        String paraR = gson.toJson(m);
        byte[] bodyByte = paraR.getBytes();

        // 获取签名
        String url = "http://api-z2.qiniu.com/sisyphus/fetch";
        String accessToken = (String) auth.authorizationV2(url, "POST", bodyByte, "application/json")
                .get("Authorization");
        Client client = new Client();
        StringMap headers = new StringMap();
        headers.put("Authorization", accessToken);
        try {
            com.qiniu.http.Response resp = client.post(url, bodyByte, headers, Client.JsonMime);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
