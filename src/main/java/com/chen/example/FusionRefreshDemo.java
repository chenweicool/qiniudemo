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
 * @Date 2020/7/26 16:36
 *
 * 1.调用域名的刷新接口是该怎么的实现的，也是刷新的文件，就是控制台的刷新文件接口的实现
 * 测试通过。
 **/
public class FusionRefreshDemo {

    public static void main(String[] args) throws Exception {


        Auth auth = Auth.create(UserConstant.ACCESS_KEy,UserConstant.SECRET_KEY);
        //要上传的空间
        String bucketname = "chenweitest";

        // 构造post请求body
        Gson gson = new Gson();
        Map<String, String[]> m = new HashMap();
        String[] urls = {
                "http://cww.huangbowei.com/chenweitess/chenweiwei.jpg"};
        m.put("urls", urls);
        String paraR = gson.toJson(m);
        byte[] bodyByte = paraR.getBytes();
        // 获取签名
        String url = "http://fusion.qiniuapi.com/v2/tune/refresh";
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
