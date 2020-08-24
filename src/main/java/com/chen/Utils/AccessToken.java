package com.chen.Utils;

import com.google.gson.Gson;
import com.qiniu.http.Headers;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成管理凭证
 */
public class AccessToken {
    public static void main(String[] args) {
        String  AccessKey = "MY_ACCESS_KEY";
        String SecretKey = "MY_SECRET_KEY";
         String url = "http://rs.qiniu.com/move/bmV3ZG9jczpmaW5kX21hbi50eHQ=/bmV3ZG9jczpmaW5kLm1hbi50eHQ=";
         String method = "POST";

        String signingStr = "POST /move/bmV3ZG9jczpmaW5kX21hbi50eHQ=/bmV3ZG9jczpmaW5kLm1hbi50eHQ=\nHost: rs.qiniu.com\n\n";
        String sign = HmacShalUtils.hmacSha1(signingStr,"MY_SECRET_KEY");

        String ecodeSign = UrlSafeBase64.encodeToString(sign);

        // 生成的16进制的签名
        System.out.println("生成十六进制的签名:"+sign);

        //生成的签名信息
        System.out.println("生成的token信息:"+ecodeSign);


        // 使用sdk的生成的管理凭证

        Auth auth = Auth.create(UserConstant.ACCESS_KEy6,UserConstant.SECRET_KEY6);

        // 构造post请求body
        Gson gson = new Gson();
        Map<String, String> m = new HashMap();
        m.put("day", "2020-08-24");
        m.put("domains","cww.chenweiwei.top");
        String paraR = gson.toJson(m);
        System.out.println(paraR);
        byte[] bodyByte = paraR.getBytes();

        String accessToken = auth.signQiniuAuthorization("http://fusion.qiniuapi.com/v2/tune/log/list","Post",bodyByte,"application/json");
        Headers accessToken1 = auth.qiniuAuthorization("http://fusion.qiniuapi.com/v2/tune/log/list","Post",bodyByte,null);

        System.out.println("生成的管理凭证"+accessToken);


    }
}
