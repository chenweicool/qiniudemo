package com.chen.example;

import com.google.gson.Gson;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class GetToken {
    public static void main(String[] args) {

        // 生成Url 防水印
       String base = UrlSafeBase64.encodeToString("shuiyin.jpeg");

        System.out.println(base);

        // 编辑原图
        String base11 = UrlSafeBase64.encodeToString("chenweiwei.jpg");
        System.out.println(base11);

        //String unBase = UrlSafeBase64.decode(base.getBytes());


        //要上传的空间
        String bucketname = "chenweitest";
        Auth auth = Auth.create("HL8-iq6CTZxKXE9cfEjVZp-_ipDOPfZLkBRVzy6v","u5UQO9LDEvWZ7chOz4Jl28fuWwekjw7QeYyNWPi7");

        // 构造post请求body
        Gson gson = new Gson();
        Map<String, String> m = new HashMap();
        // m.put("url", "http://cww.huangbowei.com/chenweiwei.jpg");
        m.put("id", "xxxx");
        String paraR = gson.toJson(m);
        System.out.println(paraR);
        byte[] bodyByte = paraR.getBytes();

        // 获取签名
        String url = "http://api.qiniu.com/qiniu";

        //authorizationV2('', 'POST', 'body', 'application/json');
        String accessToken = (String) auth.authorizationV2(url, "POST", bodyByte, "application/json")
                .get("Authorization");
        System.out.println(accessToken);


        
    }
}
