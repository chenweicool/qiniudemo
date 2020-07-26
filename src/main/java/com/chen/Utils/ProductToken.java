package com.chen.Utils;

import com.google.gson.Gson;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Chen
 * @Date 2020/7/26 10:49
 **/
public class ProductToken {

    public static void main(String[] args) throws Exception {

        String ACCESS_KEY = UserConstant.ACCESS_KEy;
        String SECRET_KEY = UserConstant.SECRET_KEY;
        //要上传的空间
        String bucketname = "chenweitest";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        // 构造post请求body
        Gson gson = new Gson();
        Map<String, String> m = new HashMap();
        m.put("url", "http://cww.huangbowei.com/chenweiwei.jpg");
        m.put("bucket", bucketname);
        String paraR = gson.toJson(m);
        byte[] bodyByte = paraR.getBytes();
        // 获取签名
        String url = "http://api.qiniu.com//pfop/";
        String accessToken = (String) auth.authorizationV2(url, "POST", bodyByte, "application/json")
                .get("Authorization");
        System.out.println(accessToken);
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
