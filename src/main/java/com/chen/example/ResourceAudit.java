package com.chen.example;

import com.chen.Utils.UserConstant;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Chen
 * @Date 2020/7/26 17:22
 * 资源审核的测试
 *测是成功
 **/
public class ResourceAudit {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = UserConstant.ACCESS_KEy;
    private static final String SECRET_KEY = UserConstant.SECRET_KEY;
    private final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private final Client client = new Client();

    public static void main(String args[]) {
        ResourceAudit resourcesCensor = new ResourceAudit();
        String result;
        try {
            result = resourcesCensor.ImageCensor();
            System.out.printf(result);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /**
     * 参考api文档 https://developer.qiniu.com/dora/manual/4252/image-review
     *  图片审核的url地址
     * @return
     * @throws QiniuException
     */
    public String ImageCensor() throws QiniuException {

        // 构造post请求body
        Gson gson = new Gson();
        Map<String, Object> uri = new HashMap<String, Object>();
        uri.put("uri", "http://cww.huangbowei.com/chenweiwei.jpg");

        Map<String, Object> scenes = new HashMap<String, Object>();
        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"pulp", "terror", "politician", "ads"};
        scenes.put("scenes", types);

        Map params = new HashMap();
        params.put("data", uri);
        params.put("params", scenes);

        String paraR = gson.toJson(params);
        byte[] bodyByte = paraR.getBytes();

        // 接口请求地址
        String url = "http://ai.qiniuapi.com/v3/image/censor";

        return post(url, bodyByte);
    }

    /**
     *  参考api文档 https://developer.qiniu.com/dora/manual/4258/video-pulp
     *  视频审核
     * @return
     * @throws QiniuException
     */
    public String VideoCensor() throws QiniuException {
        // 构造post请求body

        Gson gson = new Gson();
        Map bodyData = new HashMap();

        Map<String, Object> uri = new HashMap<String, Object>();
        uri.put("uri", "https://mars-assets.qnssl.com/scene.mp4");

        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> scenes = new HashMap<String, Object>();

        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"pulp", "terror", "politician"};
        Map<String, Object> cut_param = new HashMap<String, Object>();
        cut_param.put("interval_msecs", 500);

        params.put("scenes", types);
        params.put("cut_param", cut_param);

        bodyData.put("data", uri);
        bodyData.put("params", params);
        String paraR = gson.toJson(bodyData);
        byte[] bodyByte = paraR.getBytes();

        // 接口请求地址
        String url = "http://ai.qiniuapi.com/v3/video/censor";
        return post(url, bodyByte);
    }

    private String post(String url, byte[] body) throws QiniuException {
        String accessToken = (String) auth.authorizationV2(url, "POST", body, "application/json")
                .get("Authorization");

        StringMap headers = new StringMap();
        headers.put("Authorization", accessToken);

        com.qiniu.http.Response resp = client.post(url, body, headers, Client.JsonMime);
        return resp.bodyString();
    }
}
