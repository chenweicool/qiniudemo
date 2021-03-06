package com.chen.example.bucketmanager;

import com.chen.Utils.UserConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

/**
 * @Author Chen
 * @Date 2020/7/26 18:23
 * 设置水印的照片
 * 不成功
 * //TODO  原因不知道
 **/
public class TranserWaterImage {
    public static void main(String[] args) throws QiniuException {
        //设置账号的AK,SK
        String ACCESS_KEY = UserConstant.ACCESS_KEy;
        String SECRET_KEY = UserConstant.SECRET_KEY;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Client client = new Client();
        //新建一个OperationManager对象
        OperationManager operater = new OperationManager(auth,client);
        //设置要转码的空间和key，并且这个key在你空间中存在
        String bucket = "chenweitest";
        String key = "view";
        //需要添加水印的图片UrlSafeBase64,可以参考http://developer.qiniu.com/code/v6/api/dora-api/av/video-watermark.html
        String pictureurl = UrlSafeBase64.encodeToString("http://cwww.huangbowei.com/view");
        //设置转码操作参数
        String fops = "avthumb/mp4/s/640x360/vb/1.25m/wmImage/" + pictureurl;
        //设置转码的队列
        String pipeline = "cutStyle";
        //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
        String urlbase64 = UrlSafeBase64.encodeToString("waterImage");
        String pfops = fops + "|saveas/" + urlbase64;
        //设置pipeline参数
        StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
        try {
            String persistid = operater.pfop(bucket, key, pfops, params);
            //打印返回的persistid
            System.out.println(persistid);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            // 请求失败时简单状态信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
